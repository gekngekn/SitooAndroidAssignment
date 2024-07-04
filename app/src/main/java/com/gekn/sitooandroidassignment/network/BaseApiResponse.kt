package com.gekn.sitooandroidassignment.network

import retrofit2.HttpException
import retrofit2.Response

interface BaseApiResponse {

    /**
     * Executes a network request and handles its result.
     *
     * @param execute The network request to execute.
     * @return A [NetworkResult] representing the result of the network request.
     */
    suspend fun <T> safeApiCall(execute: suspend () -> Response<T>): NetworkResult<T?> {
        return try {
            val response = execute()
            if (response.isSuccessful) {
                val body = response.body()
                NetworkResult.Success(body)
            } else {
                error(response.code(), response.message())
            }
        } catch (e: HttpException) {
            error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorCode: Int, errorMessage: String): NetworkResult<T> =
        NetworkResult.Error(errorCode, errorMessage)

}