package pow.jie.eyep.model

import android.content.Context
import pow.jie.eyep.bean.RankVideoBean
import pow.jie.eyep.util.DataRequestListener

interface IMenuModel {

    /**
     * 请求数据
     * @param context 上下文对象
     * @param listener 请求回调监听
     */
    fun loadData(context: Context?, url: String, listener: DataRequestListener<RankVideoBean>)

    /**
     * 再次请求
     * @param context 上下文对象
     * @param listener 请求回调监听
     */
    fun loadMore(context: Context?, listener: DataRequestListener<RankVideoBean>)
}