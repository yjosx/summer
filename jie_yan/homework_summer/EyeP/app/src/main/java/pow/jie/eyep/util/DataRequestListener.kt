package pow.jie.eyep.util

interface DataRequestListener<T> {

    fun onSuccess(t: T)

    fun onFailed()

    fun onNoMore()

}