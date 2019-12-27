package com.adarsh.flickrapp.api

enum class Status {
    LOADING,
    SUCCESS,
    FAILED
}

data class NetworkState(val status: Status, val message: String? = null) {

    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.LOADING)
        fun error(message: String?) = NetworkState(Status.FAILED, message)
    }
}