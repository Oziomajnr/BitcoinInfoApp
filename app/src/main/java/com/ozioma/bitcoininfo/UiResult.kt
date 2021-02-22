package com.ozioma.bitcoininfo

sealed class UiResult<T> {
    class Success<T>(val data: T) : UiResult<T>()
    class Loading<T> : UiResult<T>()
    class Error<T> : UiResult<T>()
}