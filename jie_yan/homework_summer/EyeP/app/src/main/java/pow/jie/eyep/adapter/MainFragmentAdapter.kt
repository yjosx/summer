package pow.jie.eyep.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pow.jie.eyep.fragment.MainPageFragment
import pow.jie.eyep.fragment.MenuFragment
import pow.jie.eyep.fragment.RankFragment

class MainFragmentAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val fragmentList = mutableListOf<Fragment>()

    init {
        fragmentList.add(MainPageFragment.newInstance())
        fragmentList.add(RankFragment.newInstance())
        fragmentList.add(MenuFragment.newInstance())
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}