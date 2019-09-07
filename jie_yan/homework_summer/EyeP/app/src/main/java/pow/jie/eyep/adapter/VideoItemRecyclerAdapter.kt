package pow.jie.eyep.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_video_item.view.*
import pow.jie.eyep.R
import pow.jie.eyep.util.Api
import pow.jie.eyep.bean.Item


class VideoItemRecyclerAdapter
    (var context: Context?, var itemList: MutableList<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = from(parent.context).inflate(R.layout.main_video_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //把position保存在tag里用于以后读取
        holder.itemView.tag = position
        holder.itemView.tv_video_author.tag = position
        holder.itemView.tv_video_title.tag = position
        holder.itemView.iv_video_pic.tag = position
        holder.itemView.iv_video_author.tag = position

        holder.itemView.tv_video_title.text = itemList[position].data.title
        holder.itemView.tv_video_author.text = itemList[position].data.author.name
        Picasso.get().load(Api.videoPicUrl(itemList[position])).into(holder.itemView.iv_video_pic)
        Picasso.get().load(Api.authorPicUrl(itemList[position]))
            .into(holder.itemView.iv_video_author)

    }

    fun setData(mData: List<Item>) {
        Log.d("ada0", itemList.size.toString())
//        itemList.addAll(mData)
//        Log.d("ada1", itemList.size.toString())
        notifyDataSetChanged()
    }

    fun playVideo(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        val type = "video/* "
        val uri = Uri.parse(url)
        intent.setDataAndType(uri, type)
        context?.startActivity(Intent.createChooser(intent, "chooser"))
    }


    //    interface dataChangeListener {
//        fun setData(mData: MainPageVideoBean)
//    }
//
//    private var mCallBack = object : dataChangeListener {
//        override fun setData(mData: MainPageVideoBean) {
//            itemList = mData.issueList[0].itemList.filter { it.type == "video" }
//            notifyDataSetChanged()
//        }
//    }
    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.iv_video_pic.setOnClickListener { v ->
                playVideo(itemList[v.tag as Int].data.playUrl)
            }
        }
    }
}