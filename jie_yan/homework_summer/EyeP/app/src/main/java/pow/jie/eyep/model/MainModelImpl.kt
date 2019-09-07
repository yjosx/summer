package pow.jie.eyep.model

import android.content.Context
import android.util.Log
import okhttp3.*
import pow.jie.eyep.bean.MainPageVideoBean
import pow.jie.eyep.util.DataRequestListener
import pow.jie.eyep.util.JsonHelper
import java.io.IOException

class MainModelImpl : IMainModel {

    lateinit var nextUrl: String
    private val client = OkHttpClient()

    override fun loadData(
        context: Context?,
        url: String,
        listener: DataRequestListener<MainPageVideoBean>
    ) {
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("MainPageFragment", e.toString())
                listener.onFailed()
            }

            override fun onResponse(call: Call, response: Response) {
                val result = JsonHelper.castResponseToBean(response.body?.string())
                Log.d("MainPageFragment", result.toString())
                listener.onSuccess(result)
                nextUrl = result.nextPageUrl
            }
        })
    }

    override fun loadMore(context: Context?, listener: DataRequestListener<MainPageVideoBean>) {
        if (nextUrl == "null") {
            listener.onNoMore()
        } else {
            val request = Request.Builder().url(nextUrl).build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("MainPageFragment", e.toString())
                    listener.onFailed()
                }

                override fun onResponse(call: Call, response: Response) {
                    val result = JsonHelper.castResponseToBean(response.body?.string())
                    Log.d("MainPageFragment", result.toString())
                    listener.onSuccess(result)
                    nextUrl = result.nextPageUrl
                }
            })
        }
    }
}