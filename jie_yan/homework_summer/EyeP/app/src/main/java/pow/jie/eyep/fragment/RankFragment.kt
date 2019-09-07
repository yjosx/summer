package pow.jie.eyep.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.fragment_rank.view.*
import pow.jie.eyep.R
import pow.jie.eyep.adapter.RankFragmentAdapter
import pow.jie.eyep.base.BaseLazyLoadFragment

class RankFragment : BaseLazyLoadFragment() {
    override fun initView() {
    }
    override fun initData() {
    }
    override fun loadData() {
    }
    override fun getLayoutId(): Int = R.layout.fragment_rank

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_rank, container, false)
        view.vp_rank.adapter = RankFragmentAdapter(childFragmentManager)
        view.tab_layout_rank.setupWithViewPager(view.vp_rank)

        view.tab_layout_rank.getTabAt(0)!!.text = "周"
        view.tab_layout_rank.getTabAt(1)!!.text = "月"
        view.tab_layout_rank.getTabAt(2)!!.text = "总"
        view.tab_layout_rank.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(p0: TabLayout.Tab?) {
                view.vp_rank?.currentItem = p0!!.position
            }
        })

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment MainPageFragment.
         */
        @JvmStatic
        fun newInstance() = RankFragment()
    }
}
