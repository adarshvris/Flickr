package com.adarsh.flickrapp.api

import retrofit2.Response
import java.lang.Exception
import com.adarsh.flickrapp.repository.Result

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Result.success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (ex: Exception) {
            return error(ex.message ?: ex.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        return Result.error("Network call has failed for a following reason: $message")
    }
}