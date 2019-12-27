# Flickr Application
An app which displays list of images and demonstrate paginated call using Android Paging Library with offline persistence


# Tech Stack


* __Language__ : Kotlin
* __Architecture__ : MVVM
* __Retrofit__ : For Network calls
* __Coroutines__ : For background operations like fetching network response
* __Live Data__ : To notify view for change
* __Room Database__ : For offline persistence
* __Work Manager__ : To enqueue auto fetch data task
* __Dagger__ : For dependency injection

Every response from network are wrapped in a single data type DATA, with NetworkState as a field which defines the state in which the call is.
