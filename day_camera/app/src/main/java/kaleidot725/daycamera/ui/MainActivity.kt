package kaleidot725.daycamera.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kaleidot725.daycamera.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private val navController: NavController get() = findNavController(R.id.main_fragment)
    private val barConfig = AppBarConfiguration.Builder(R.id.homeFragment, R.id.historyFragment, R.id.settingFragment).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController.addOnDestinationChangedListener(this)
        setupActionBarWithNavController(this, navController, barConfig)
        bottom_navigation_view.setupWithNavController(navController)
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        val id = controller.currentDestination?.id
        if (id == R.id.permissionFragment) {
            bottom_navigation_view.visibility = View.GONE
            supportActionBar?.hide()
        } else if (id != R.id.homeFragment && id != R.id.historyFragment && id != R.id.settingFragment) {
            bottom_navigation_view.visibility = View.GONE
            supportActionBar?.show()
        } else {
            bottom_navigation_view.visibility = View.VISIBLE
            supportActionBar?.show()
        }
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}
