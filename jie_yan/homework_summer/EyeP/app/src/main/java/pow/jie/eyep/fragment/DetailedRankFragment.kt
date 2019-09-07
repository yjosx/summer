package pow.jie.eyep.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_detailed_rank.*
import pow.jie.eyep.R
import pow.jie.eyep.adapter.VideoItemRecyclerAdapter
import pow.jie.eyep.base.BaseFragment
import pow.jie.eyep.base.LoadMoreWrapper
import pow.jie.eyep.bean.Item
import pow.jie.eyep.contract.RankContract
import pow.jie.eyep.presenter.RankPresenter
import pow.jie.eyep.util.Api

private const val ARG_TYPE = "type"

class DetailedRankFragment : BaseFragment<RankContract.IRankView, RankPresenter>(),
    RankContract.IRankView {


    private lateinit var type: String
    private var adapter: VideoItemRecyclerAdapter? = null
    private var itemList: MutableList<Item> = mutableListOf()
    private var isDataLoaded = false


    override fun createPresenter(): RankPresenter {
        return RankPresenter(context)
    }

    override fun showData(list: List<Item>) {
        itemList.addAll(list)
        if (!isDataLoaded) {
            isDataLoaded = true
            adapter = VideoItemRecyclerAdapter(activity, itemList)
            val loadMoreAdapter = LoadMoreWrapper(adapter)
            rv_rank_detail?.layoutManager = LinearLayoutManager(context)
            rv_rank_detail?.adapter = loadMoreAdapter
        } else {
            adapter?.setData(itemList)
        }
    }

    override fun showEmptyView() {
        Toast.makeText(context, "没有更多数据了", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }
    override fun loadData() {
        adapter?.setData(itemList)
    }

    override fun initView() {
        arguments?.let {
            type = it.getString(ARG_TYPE)!!
        }
    }

    override fun initData() {
        request(Api.detailedRankUrl(type))
    }

    override fun getLayoutId(): Int = R.layout.fragment_detailed_rank

    private fun request(url: String) {
        mPresenter?.requestData(url)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param type RankType.
         * @return A new instance of fragment DetailedRankFragment.
         */
        @JvmStatic
        fun newInstance(type: String) =
            DetailedRankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TYPE, type)
                }
            }
    }
}
