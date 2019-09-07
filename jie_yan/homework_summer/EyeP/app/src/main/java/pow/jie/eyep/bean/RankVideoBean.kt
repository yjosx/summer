package pow.jie.eyep.bean

data class RankVideoBean(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Item>,
    val nextPageUrl: String,
    val total: Int
)
