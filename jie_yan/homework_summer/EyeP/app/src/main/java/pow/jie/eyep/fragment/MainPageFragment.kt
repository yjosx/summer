package pow.jie.eyep.fragment

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_detailed_rank.*
import kotlinx.android.synthetic.main.fragment_main_page.*
import kotlinx.android.synthetic.main.fragment_main_page.view.*
import kotlinx.android.synthetic.main.fragment_menu.*
import pow.jie.eyep.R
import pow.jie.eyep.adapter.VideoItemRecyclerAdapter
import pow.jie.eyep.util.Api
import pow.jie.eyep.base.BaseFragment
import pow.jie.eyep.base.LoadMoreWrapper
import pow.jie.eyep.bean.Item
import pow.jie.eyep.contract.MainPageContract
import pow.jie.eyep.presenter.MainPagePresenter
import pow.jie.eyep.util.EndlessRecyclerOnScrollListener

class MainPageFragment :
    BaseFragment<MainPageContract.IMainView, MainPagePresenter>(), MainPageContract.IMainView {

    private var adapter: VideoItemRecyclerAdapter? = null
    private var itemList: MutableList<Item> = mutableListOf()
    private var loadMoreAdapter: LoadMoreWrapper? = null
    private var isDataLoaded = false

    override fun showData(list: List<Item>) {
        Log.d("showdata0",itemList.size.toString())
        itemList.addAll(list)
        Log.d("showdata1",itemList.size.toString())
        if (!isDataLoaded) {
            isDataLoaded = true
            Log.d("main ","create adapter")
            adapter = VideoItemRecyclerAdapter(activity, itemList)
            Log.d("showdata2",itemList.size.toString())
            loadMoreAdapter = LoadMoreWrapper(adapter)
            Log.d("showdata3",itemList.size.toString())
            rv_main_page?.layoutManager = LinearLayoutManager(context)
            rv_main_page?.adapter = loadMoreAdapter
            loadMoreAdapter?.setLoadState(LoadMoreWrapper.LOADING_COMPLETE)
        } else {
            Log.d("main ","adapter set data")
            adapter?.setData(itemList)
            loadMoreAdapter?.setLoadState(LoadMoreWrapper.LOADING_COMPLETE)
        }
    }

    override fun showEmptyView() {
        Toast.makeText(context, "没有更多数据了", Toast.LENGTH_SHORT).show()
        adapter = VideoItemRecyclerAdapter(activity, itemList)
        loadMoreAdapter = LoadMoreWrapper(adapter)
        rv_main_page?.layoutManager = LinearLayoutManager(context)
        rv_main_page?.adapter = loadMoreAdapter
    }

    override fun showError() {
        Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show()
        loadMoreAdapter?.setLoadState(LoadMoreWrapper.LOADING_END)

    }

    override fun showLoading() {
        loadMoreAdapter?.setLoadState(LoadMoreWrapper.LOADING)
    }

    override fun hideLoading() {
        loadMoreAdapter?.setLoadState(LoadMoreWrapper.LOADING_COMPLETE)
    }

    override fun createPresenter(): MainPagePresenter {
        return MainPagePresenter(context)
    }

    override fun initView() {
        mRootView?.rv_main_page!!.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                loadMoreAdapter?.setLoadState(LoadMoreWrapper.LOADING)
                Log.d("mainpage","loadmore")
                requestMore()
            }
        })
    }

    override fun initData() {
        request(Api.mainPageUrl)
    }

    override fun loadData() {
        adapter?.setData(itemList)
    }

    override fun getLayoutId(): Int = R.layout.fragment_main_page

    private fun request(url: String) {
        mPresenter?.requestData(url)
    }

    private fun requestMore() {
        showLoading()
        Log.d("requestmore","mPresenter==null is ${mPresenter==null}")
        mPresenter?.requestMore()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainPageFragment()
    }
}
