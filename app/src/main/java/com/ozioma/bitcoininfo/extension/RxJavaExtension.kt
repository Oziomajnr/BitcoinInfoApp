package com.ozioma.bitcoininfo.extension

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxJavaExtension {
    fun <T> Single<T>.applyDefaultSchedulers(): Single<T> = compose {
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}