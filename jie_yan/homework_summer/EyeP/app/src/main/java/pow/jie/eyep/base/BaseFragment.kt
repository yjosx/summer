package pow.jie.eyep.base

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment<V, T : BasePresenter<V>> : BaseLazyLoadFragment() {

    protected var mPresenter: T? = null

    protected var mRootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("baseFragment","onCreate")
        mPresenter = createPresenter()
        if (mPresenter != null) {
            mPresenter!!.attachView(this as V)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d("baseFragment","onCreateView")

        if (mRootView != null) {
            Log.d("baseFragmrnt","rootview!=null")
            val parent: ViewGroup? = mRootView?.parent as ViewGroup?
            parent?.removeView(mRootView)
//            loadData()
        } else {
            Log.d("baseFragmrnt","rootview==null")
            mRootView = inflater.inflate(getLayoutId(), container, false)
            initView()
            initData()
        }
        return mRootView
    }


    override fun onDestroyView() {
        super.onDestroyView()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }
    }

    /**
     * 创建Presenter
     *
     * @return Presenter
     */
    abstract fun createPresenter(): T

}