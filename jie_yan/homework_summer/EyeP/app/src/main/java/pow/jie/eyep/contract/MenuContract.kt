package pow.jie.eyep.contract

import pow.jie.eyep.base.BasePresenter
import pow.jie.eyep.base.BaseView
import pow.jie.eyep.bean.Item

interface MenuContract {
    interface IMenuView : BaseView {

        /**
         * 显示列表数据
         * @param list 列表数据集合
         */
        fun showData(list: List<Item>)
    }


    abstract class AbstractHomePresenter : BasePresenter<IMenuView>() {

        /**
         * 请求列表信息
         */
        abstract fun requestData(url: String)

        /**
         * 请求更多列表信息
         */
        abstract fun requestMore()
    }

}