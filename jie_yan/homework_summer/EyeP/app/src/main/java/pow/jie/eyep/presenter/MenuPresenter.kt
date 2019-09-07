package pow.jie.eyep.presenter

import android.content.Context
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import pow.jie.eyep.bean.Item
import pow.jie.eyep.bean.RankVideoBean
import pow.jie.eyep.contract.MenuContract
import pow.jie.eyep.model.MenuModelImpl
import pow.jie.eyep.util.DataRequestListener

class MenuPresenter(private val mContext: Context?) : MenuContract.AbstractHomePresenter() {

    private val mModel =MenuModelImpl()

    override fun requestData(url: String) {
        val mView = getView() ?: return

        async {
            mModel.loadData(mContext, url, object : DataRequestListener<RankVideoBean> {
                override fun onSuccess(t: RankVideoBean) {
                    //是否有数据
                    if (t.itemList.isNotEmpty()) {
                        val itemList: MutableList<Item> = mutableListOf()
                        itemList.addAll(t.itemList.filter { it.type == "video" })
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

        async {
            mModel.loadMore(mContext, object : DataRequestListener<RankVideoBean> {
                override fun onSuccess(t: RankVideoBean) {
                    //是否有数据
                    if (t.itemList.isNotEmpty()) {
                        val itemList: MutableList<Item> = mutableListOf()
                        itemList.addAll(t.itemList.filter { it.type == "video" })
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