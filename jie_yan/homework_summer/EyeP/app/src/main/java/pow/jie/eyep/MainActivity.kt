package pow.jie.eyep

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import pow.jie.eyep.adapter.MainFragmentAdapter


class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                view_pager_main.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_rank -> {
                view_pager_main.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                view_pager_main.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val onPageChangeListener = object :OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            nav_view.menu.getItem(position).isChecked = true
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        view_pager_main.addOnPageChangeListener(onPageChangeListener)
        val adapter = MainFragmentAdapter(supportFragmentManager)
        view_pager_main.adapter = adapter

    }

}

