package pow.jie.eyep.base

import java.lang.ref.Reference
import java.lang.ref.WeakReference


abstract class BasePresenter<T> {
    private var mRef: Reference<T>? = null

    fun attachView(mView: T) {
        mRef = WeakReference(mView)
    }

    fun getView(): T? {
        if (isViewAttached())
            return mRef!!.get()
        return null
    }

    fun isViewAttached(): Boolean {
        return mRef != null && mRef?.get() != null
    }

    fun detachView() {
        mRef?.clear()
        mRef = null
    }
}