package com.example.sanwinaung.mypagin.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.sanwinaung.mypagin.model.GsonMember

class MemberAdapter{

    inner class MemberViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)

    private val memberImg = itemView.member_imt

    private val memberName = itemVeiw.member_name

    fun bindTo(gsonMember: GsonMember){

    }
}