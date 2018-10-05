package com.example.sanwinaung.mypagin.paging

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.sanwinaung.mypagin.model.GsonMember
import com.example.sanwinaung.mypagin.utils.Const
import com.example.sanwinaung.mypagin.utils.DataLoadState
import java.util.concurrent.Executors


class MemberRepoImplementation (val memberDataFactory: MemberDataFactory = MemberDataFactory()) : MemberRepository {
    override fun getMembers(): LiveData<PagedList<GsonMember>> {

        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(Const.MEMBER_PAGE_SIZE)
                .setPageSize(Const.MEMBER_PAGE_SIZE)
                .build()
        val executer = Executors.newFixedThreadPool(5)
        val mymembers = LivePagedListBuilder(memberDataFactory, config)
                .setInitialLoadKey(1)
                .setFetchExecutor(executer)
                .build()
        return mymembers

    }

    override fun getDataLoadStatus(): LiveData<DataLoadState> {
        return Transformations.switchMap(memberDataFactory.dataSourceLiveData
        ) { dataSource -> dataSource.dataLoadState }
    }

}