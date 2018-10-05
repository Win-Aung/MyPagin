package com.example.sanwinaung.mypagin.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.example.sanwinaung.mypagin.model.GsonMember
import com.example.sanwinaung.mypagin.model.GsonMembers
import com.example.sanwinaung.mypagin.network.MainService
import com.example.sanwinaung.mypagin.network.RestApi
import com.example.sanwinaung.mypagin.utils.DataLoadState
import retrofit2.Response
import java.io.IOException

class MemberDataSource (val dataLoadState: MutableLiveData<DataLoadState> = MutableLiveData())
    : PageKeyedDataSource<Long, GsonMember>()  {
    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, GsonMember>) {
        dataLoadState.postValue(DataLoadState.LOADING)
        val request = RestApi.createService(MainService::class.java).getMembers(1)

        val response: Response<GsonMembers>
        try {
            response = request.execute()
            if (response != null) {
                dataLoadState.postValue(DataLoadState.LOADED)
                callback.onResult(response.body()!!.gsonMembers, 1, 2)
            } else {
                dataLoadState.postValue(DataLoadState.FAILED)
            }
        } catch (ex: IOException) {
            dataLoadState.postValue(DataLoadState.FAILED)
        }

    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, GsonMember>) {
        dataLoadState.postValue(DataLoadState.LOADING)

        val request = RestApi.createService(MainService::class.java).getMembers(params.key)
        val response: Response<GsonMembers>

        try {
            response = request.execute()
            if(response != null) {
                dataLoadState.postValue(DataLoadState.LOADED)
                callback.onResult(response.body()!!.gsonMembers, params.key + 1)
            } else {
                dataLoadState.postValue(DataLoadState.FAILED)
            }
        } catch (ex: IOException) {
            dataLoadState.postValue(DataLoadState.FAILED)
        }

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, GsonMember>) {
        dataLoadState.postValue(DataLoadState.LOADING)

        val request = RestApi.createService(MainService::class.java).getMembers(params.key)
        val response: Response<GsonMembers>

        try {
            response = request.execute()
            if(response != null) {
                dataLoadState.postValue(DataLoadState.LOADED)
                callback.onResult(response.body()!!.gsonMembers, params.key - 1)
            } else {
                dataLoadState.postValue(DataLoadState.FAILED)
            }
        } catch (ex: IOException) {
            dataLoadState.postValue(DataLoadState.FAILED)
        }
    }
}