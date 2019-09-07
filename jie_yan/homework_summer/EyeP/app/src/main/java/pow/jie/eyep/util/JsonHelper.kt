package pow.jie.eyep.util

import com.google.gson.Gson
import pow.jie.eyep.bean.MainPageVideoBean
import pow.jie.eyep.bean.RankVideoBean

object JsonHelper {
    fun castResponseToBean(response: String?): MainPageVideoBean {
        val gson = Gson()
        return gson.fromJson(response,MainPageVideoBean::class.java)
    }
    fun castRankResponseToBean(response: String?): RankVideoBean {
        val gson = Gson()
        return gson.fromJson(response,RankVideoBean::class.java)
    }
}