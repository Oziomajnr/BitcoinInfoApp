package com.ozioma.bitcoininfo

sealed class UiResult<T> {
    class Success<T>(val data: T, val message: String) : UiResult<T>()
    class Loading<T>(val message: String) : UiResult<T>()
    class Error<T>(val message: String) : UiResult<T>()
}