package de.syntaxinstitut.e_sport_news

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import de.syntaxinstitut.e_sport_news.databinding.ActivityMainBinding

/**
 * Main Activity, dient als Einstiegspunkt für die App
 */
class MainActivity : AppCompatActivity() {

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    //private val adapter = BottemAdapter(supportFragmentManager,0)
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    /* -------------------- Klassen Variablen -------------------- */

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */


    /* -------------------- Lifecycle -------------------- */

    /**
     * Lifecycle Methode, wird aufgerufen wenn Activity erstellt wird
     *
     * @param savedInstanceState      Save state vom view
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Das Binding zur XML-Datei
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val navView: BottomNavigationView = binding.bottomNavigation

        navController= this.findNavController(R.id.main_fragment)
        NavigationUI.setupActionBarWithNavController(this,navController)

        NavigationUI.setupWithNavController(navView,navController)

        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.LoginFragment -> navView.visibility = View.GONE
                else -> navView.visibility = View.VISIBLE
            }
        }
//        val navView : BottomNavigationView = binding.bottomNavigation
//
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment )
//                as NavHostFragment
//
//        navController = navHostFragment.navController
//
//
//        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
