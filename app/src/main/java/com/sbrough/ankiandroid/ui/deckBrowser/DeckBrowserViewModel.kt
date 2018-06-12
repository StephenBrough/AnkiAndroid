package com.sbrough.ankiandroid.ui.deckBrowser

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sbrough.ankiandroid.db.AnkiDatabase
import com.sbrough.ankiandroid.db.entities.DECK_TABLE_CREATION
import com.sbrough.ankiandroid.db.json.Deck
import com.sbrough.ankiandroid.util.copyTo
import com.sbrough.ankiandroid.util.unzip
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import timber.log.Timber
import java.io.File

class DeckBrowserViewModel(private val appContext: Application) : AndroidViewModel(appContext) {

    val mutableDeckList = MutableLiveData<List<Deck>>()
    private var deckList: LiveData<List<Deck>>? = mutableDeckList

    /**
     * Unzips a file to a zip location
     *
     * @param filesDir File pointing to the app's file dir as provided by the Android system
     * @param ankiFilename Name of the anki collection file; used for the db file name as well
     * @param ankiUnzipLocation File pointing to where we will unzip the anki collection file
     * @param input The input stream for the anki collection file
     */
    fun loadDb(uri: Uri) {
        // TODO: Put this in a coroutine
        // Assemble the info the ViewModel needs to unzip the Anki collection file
        val ankiFilename = DocumentFile.fromSingleUri(appContext, uri)?.name ?: ""
        val ankiDbUnzipLocation = appContext.getDatabasePath(AnkiDatabase.DB_NAME) ?: return
        val input = appContext.contentResolver?.openInputStream(uri) ?: return // TODO: Add some error message
        val filesDir = appContext.filesDir ?: return // TODO: Add some error message

        // Unzip and copy the anki collection file to our internal storage
        val ankiZipFile = File(filesDir, ankiFilename)
        input.copyTo(ankiZipFile)
        ankiZipFile.unzip(ankiDbUnzipLocation)

        // TODO: There might be a race condition here where we access the DB before
        // TODO: it's done loading up for the first time... maybe it has to do with
        // TODO: when Google Drive has to download the file, could be some lag causing db access before it's ready
        initialDatabaseSetup()

        setupDecks()
    }

    /**
     * Setup the database so we can use it with Room, and also add some convenience tables
     */
    private fun initialDatabaseSetup() {

        SQLiteDatabase.openDatabase(appContext.getDatabasePath(AnkiDatabase.DB_NAME).absolutePath, null, SQLiteDatabase.OPEN_READWRITE).apply {
            // Bump the version number so we can get Room to do an initial do-nothing migration
            version = 1

            // Adds the Deck table (originally just Json data on the Deck column in the Collection table)
            execSQL(DECK_TABLE_CREATION)

            close()
            Timber.d("Finished initial setup db operations")
        }
        Timber.d("End of initial DB setup")
    }

    /**
     * Get the decks from the Json stored in the Collection table's deck column and store them in the Room database
     * Example of Json deck data:
     * {
     *  "1": { ... },
     *  "1295837958324": { ... },
     *  ...
     * }
     */
    fun setupDecks() {
        Timber.d("Setup Decks")
        val type = Types.newParameterizedType(Map::class.java, String::class.java, Deck::class.java)
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<Map<String, Deck>>(type)

        val collection = AnkiDatabase.get().collectionDao().getCollection()

        val decks = jsonAdapter.fromJson(collection.decks) ?: return

        // Map the map of decks to a list of decks
        mutableDeckList.value = decks.map { it.value }
    }

    fun init() {
        if (appContext.getDatabasePath(AnkiDatabase.DB_NAME).exists()) {
            setupDecks()
        }
    }
}
