package pow.jie.eyep.presenter

import android.content.Context
import android.util.Log
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import pow.jie.eyep.bean.Item
import pow.jie.eyep.bean.MainPageVideoBean
import pow.jie.eyep.contract.MainPageContract
import pow.jie.eyep.model.MainModelImpl
import pow.jie.eyep.util.DataRequestListener


class MainPagePresenter(private val mContext: Context?) : MainPageContract.AbstractHomePresenter() {

    private val mModel = MainModelImpl()

    override fun requestData(url: String) {
        val mView = getView() ?: return

        async {
            mModel.loadData(mContext, url, object : DataRequestListener<MainPageVideoBean> {
                override fun onSuccess(t: MainPageVideoBean) {
                    //是否有数据
                    if (t.issueList.isNotEmpty()) {
                        val itemList: MutableList<Item> = mutableListOf()
                        itemList.addAll(t.issueList[0].itemList.filter { it.type == "video" })
                        uiThread { mView.showData(itemList) }
                    } else {
                        uiThread { mView.showEmptyView() }
                    }
                }

                override fun onFailed() {
                    uiThread { mView.showError() }
                }

                override fun onNoMore() {
                    uiThread { mView.showEmptyView() }
                }
            })

        }

    }

    override fun requestMore() {
        val mView = getView() ?: return
        Log.d("main ", (getView()==null).toString())

        async {
            Log.d("async ","load")
            mModel.loadMore(mContext, object : DataRequestListener<MainPageVideoBean> {
                override fun onSuccess(t: MainPageVideoBean) {
                    //是否有数据
                    if (t.issueList.isNotEmpty()) {
                        val itemList: MutableList<Item> = mutableListOf()
                        itemList.addAll(t.issueList[0].itemList.filter { it.type == "video" })
                        Log.d("requestMore ", itemList.size.toString())
                        uiThread { mView.showData(itemList) }
                    } else {
                        uiThread { mView.showEmptyView() }
                    }
                }

                override fun onFailed() {
                    uiThread { mView.showError() }
                }

                override fun onNoMore() {
                    uiThread { mView.showEmptyView() }
                }
            })

        }

    }
}