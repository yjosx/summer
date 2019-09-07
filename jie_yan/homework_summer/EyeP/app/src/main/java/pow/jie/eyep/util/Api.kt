package pow.jie.eyep.util

import pow.jie.eyep.bean.Item

object Api {

    private fun createUdid(): String {
        val udid: StringBuilder = java.lang.StringBuilder()
        for (i in 1..40) {
            val randoms = (0..16).random().toString(16)
            udid.append(randoms)
        }
        return udid.toString()
    }

    private var udid = createUdid()

    val strategy = arrayOf("weekly", "monthly", "historical")

    fun searchUrl(query: String) = "http://baobab.kaiyanapp.com/api/v1/search?num=10&query=$query&start=10"

    fun detailedRankUrl(type: String) =
        "http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=$type&udid=$udid&vc=83"

    fun videoPicUrl(item: Item) = item.data.cover.feed

    fun authorPicUrl(item: Item) = item.data.author.icon

    val mainPageUrl = "http://baobab.wandoujia.com/api/v2/feed?num=2&udid=$udid&vc=83"
}