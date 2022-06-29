package com.jesy.marvelsuperheroes.util

sealed class Failure {
    object NetworkConnection : Failure()
    data class ServerError(val errorCode: Int, val message: String) : Failure()
    data class CustomError(val message: String) : Failure()
    data class Throwable(val throwable: kotlin.Throwable) : Failure()
}
