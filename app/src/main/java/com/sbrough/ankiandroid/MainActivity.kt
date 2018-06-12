package com.sbrough.ankiandroid

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val navHostFrag = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment?
                ?: return

        NavigationUI.setupActionBarWithNavController(this, navHostFrag.navController, drawerLayout)
        NavigationUI.setupWithNavController(drawerNavigationView, navHostFrag.navController)

//        navHostFrag.navController.addOnNavigatedListener { _, destination ->
//            val dest = try {
//                resources.getResourceName(destination.id)
//            } catch (e: Resources.NotFoundException) {
//                Integer.toString(destination.id)
//            }
//
//            Timber.d("Navigated to: $dest")
//        }
    }

    override fun onSupportNavigateUp(): Boolean = NavigationUI.navigateUp(drawerLayout, Navigation.findNavController(this, R.id.navHostFragment))

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this, R.id.navHostFragment)) || super.onOptionsItemSelected(item)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_deck_browser, menu)
        return true
    }
}
