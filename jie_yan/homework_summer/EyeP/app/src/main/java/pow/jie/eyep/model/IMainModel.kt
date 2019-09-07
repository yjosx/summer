package pow.jie.eyep.model

import android.content.Context
import pow.jie.eyep.bean.MainPageVideoBean
import pow.jie.eyep.util.DataRequestListener

interface IMainModel {

    /**
     * 请求数据
     * @param context 上下文对象
     * @param listener 请求回调监听
     */
    fun loadData(context: Context?,url:String, listener: DataRequestListener<MainPageVideoBean>)

    /**
     * 再次请求
     * @param context 上下文对象
     * @param listener 请求回调监听
     */
    fun loadMore(context: Context?, listener: DataRequestListener<MainPageVideoBean>)
}