package de.syntaxinstitut.e_sport_news

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        // Das Binding zur XML-Datei
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val navView: BottomNavigationView = binding.bottomNavigation

        navController= this.findNavController(R.id.main_fragment)

        NavigationUI.setupWithNavController(navView,navController)

        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.LoginFragment,R.id.chatFragment2,R.id.signUpFragment -> navView.visibility = View.GONE
                else -> navView.visibility = View.VISIBLE
            }
        }

    }


}
