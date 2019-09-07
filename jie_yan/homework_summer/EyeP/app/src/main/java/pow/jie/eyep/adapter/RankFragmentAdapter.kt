package pow.jie.eyep.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pow.jie.eyep.util.Api
import pow.jie.eyep.fragment.DetailedRankFragment

class RankFragmentAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val fragmentList = mutableListOf<Fragment>()

    init {
        fragmentList.add(DetailedRankFragment.newInstance(Api.strategy[0]))
        fragmentList.add(DetailedRankFragment.newInstance(Api.strategy[1]))
        fragmentList.add(DetailedRankFragment.newInstance(Api.strategy[2]))
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return Api.strategy[position]
    }
}