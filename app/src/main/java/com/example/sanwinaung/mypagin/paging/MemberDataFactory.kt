package com.example.sanwinaung.mypagin.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.sanwinaung.mypagin.model.GsonMember

class MemberDataFactory(val dataSourceLiveData: MutableLiveData<MemberDataSource> = MutableLiveData()) : DataSource.Factory<Long, GsonMember>() {
    override fun create(): DataSource<Long, GsonMember> {
        val dataSource = MemberDataSource()
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}