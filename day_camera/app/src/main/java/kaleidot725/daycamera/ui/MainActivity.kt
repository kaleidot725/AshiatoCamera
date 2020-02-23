package kaleidot725.daycamera.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.tabs.TabLayout
import kaleidot725.daycamera.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private val navController: NavController get() = findNavController(R.id.main_fragment)
    private val barConfig = AppBarConfiguration.Builder(R.id.homeFragment, R.id.historyFragment).build()
    private val optionVisibility get() = (navController.currentDestination?.id == R.id.homeFragment || navController.currentDestination?.id == R.id.historyFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController.addOnDestinationChangedListener(this)
        setupActionBarWithNavController(this, navController, barConfig)
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    "Home" -> navController.navigate(R.id.homeFragment)
                    "History" -> navController.navigate(R.id.historyFragment)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (optionVisibility) {
            val inflater: MenuInflater = menuInflater
            inflater.inflate(R.menu.action_bar_menu, menu)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.configFragment -> navController.navigate(R.id.configFragment)
            R.id.privacyFragment -> navController.navigate(R.id.privacyFragment)
            R.id.licenseFragment -> {
                startActivity(Intent(this, OssLicensesMenuActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        val id = controller.currentDestination?.id
        if (id == R.id.permissionFragment) {
            tab_layout.visibility = View.GONE
            supportActionBar?.hide()
        } else if (id != R.id.homeFragment && id != R.id.historyFragment) {
            tab_layout.visibility = View.GONE
            supportActionBar?.show()
        } else {
            tab_layout.visibility = View.VISIBLE
            supportActionBar?.show()
        }
        invalidateOptionsMenu()
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}
