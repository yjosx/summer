package pow.jie.eyep.fragment

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_menu.view.*
import pow.jie.eyep.R
import pow.jie.eyep.adapter.VideoItemRecyclerAdapter
import pow.jie.eyep.base.BaseFragment
import pow.jie.eyep.base.LoadMoreWrapper
import pow.jie.eyep.bean.Item
import pow.jie.eyep.contract.MenuContract
import pow.jie.eyep.presenter.MenuPresenter
import pow.jie.eyep.util.Api
import pow.jie.eyep.util.EndlessRecyclerOnScrollListener


class MenuFragment : BaseFragment<MenuContract.IMenuView, MenuPresenter>(), MenuContract.IMenuView {

    private var itemList: MutableList<Item> = mutableListOf()
    private var adapter: VideoItemRecyclerAdapter? = null
    private var loadMoreAdapter: LoadMoreWrapper? = null
    private var isDataLoaded = false


    override fun showData(list: List<Item>) {
        itemList.addAll(list)
        if (!isDataLoaded) {
            isDataLoaded = true
            adapter = VideoItemRecyclerAdapter(activity, itemList)
            loadMoreAdapter = LoadMoreWrapper(adapter)
            rv_search?.layoutManager = LinearLayoutManager(context)
            rv_search?.adapter = loadMoreAdapter
            loadMoreAdapter?.setLoadState(LoadMoreWrapper.LOADING_COMPLETE)
        } else {
            adapter?.setData(itemList)
            loadMoreAdapter?.setLoadState(LoadMoreWrapper.LOADING_COMPLETE)
        }
    }

    override fun showEmptyView() {
        Toast.makeText(context, "没有更多数据了", Toast.LENGTH_SHORT).show()
        adapter = VideoItemRecyclerAdapter(activity, itemList)
        loadMoreAdapter = LoadMoreWrapper(adapter)
        rv_search?.layoutManager = LinearLayoutManager(context)
        rv_search?.adapter = loadMoreAdapter
        loadMoreAdapter?.setLoadState(LoadMoreWrapper.LOADING_END)
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

    override fun createPresenter(): MenuPresenter {
        return MenuPresenter(context)
    }

    override fun initView() {
        mRootView?.rv_search!!.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                loadMoreAdapter?.setLoadState(LoadMoreWrapper.LOADING)
                requestMore()
            }
        })
        mRootView?.editText!!.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //点击搜索的时候隐藏软键盘
                hideKeyboard(v)
                // 在这里写搜索的操作,一般都是网络请求数据
                Log.d("editText", editText.text.toString())
                search(editText.text.toString())
            }
            false
        }
        mRootView?.editText!!.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                hideKeyboard(v)
                search(editText.text.toString())
            }
            false
        }
    }
    override fun loadData() {
        adapter?.setData(itemList)
    }

    override fun initData() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_menu


    /**
     * 隐藏软键盘
     * @param view    :一般为EditText
     */
    private fun hideKeyboard(view: View) {
        val manager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun search(text: String) {
        mPresenter?.requestData(Api.searchUrl(text))
    }

    private fun requestMore() {
        showLoading()
        mPresenter?.requestMore()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment MainPageFragment.
         */
        @JvmStatic
        fun newInstance() = MenuFragment()
    }
}
