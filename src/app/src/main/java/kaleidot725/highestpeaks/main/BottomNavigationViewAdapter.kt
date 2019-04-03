package kaleidot725.highestpeaks.main

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.databinding.BindingAdapter

@BindingAdapter("setOnNavigationItemSelectedListener")
fun setOnNavigationItemSelectedListener(
    view: BottomNavigationView, listener: BottomNavigationView.OnNavigationItemSelectedListener
) {
    view.setOnNavigationItemSelectedListener(listener)
}