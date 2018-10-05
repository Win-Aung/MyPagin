package com.example.sanwinaung.mypagin.paging

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.example.sanwinaung.mypagin.model.GsonMember
import com.example.sanwinaung.mypagin.utils.DataLoadState

interface MemberRepository {

    fun getMembers(): LiveData<PagedList<GsonMember>>
    fun getDataLoadStatus(): LiveData<DataLoadState>
}