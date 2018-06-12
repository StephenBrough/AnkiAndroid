package com.sbrough.ankiandroid.util

import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.Fragment
import com.sbrough.ankiandroid.db.AnkiDatabase
import java.io.File

fun Fragment.getAnkiDbFile(): File? = context?.getDatabasePath(AnkiDatabase.DB_NAME)

fun Fragment.getFileNameFromUri(uri: Uri): String = DocumentFile.fromSingleUri(requireContext(), uri)?.name ?: ""