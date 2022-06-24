package com.jesy.marvelsuperheroes.util

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object{
        const val BASE_URL = "http://gateway.marvel.com/v1/public/"
        const val API_KEY = "70f95a4950ab43c420ffa9b425003fc5"
        const val PRIVATE_API_KEY = "9d3a3ae62e921628705e3e3023c7303d206222f2"
        const val limit = 100
        val timeStamp= Timestamp(System.currentTimeMillis()).time.toString()
        fun hash(): String{
            val input = "$timeStamp$PRIVATE_API_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}