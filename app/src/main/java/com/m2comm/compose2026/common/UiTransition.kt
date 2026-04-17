package com.m2comm.compose2026.common

import android.content.Context
import android.widget.TextView
import com.m2comm.compose2026.R

class UiTransition (private val con: Context) {

    fun switchTextbox(textView:TextView, on:Boolean){
        if (on){
            selectTextbox(textView)
        } else {
            unselectTextbox(textView)
        }
    }

    fun switchTextboxList(on:TextView, offList: List<TextView>){
        for (off in offList){
            unselectTextbox(off)
        }
        selectTextbox(on)
    }


   private fun unselectTextbox(textView: TextView){
        //textView.setTextColor(con.resources.getColor(R.color.date_unselected,null))
        //textView.setBackgroundColor(con.resources.getColor(R.color.date_unselected_bg,null))
    }

    private fun selectTextbox(textView: TextView){
        //textView.setTextColor(con.resources.getColor(R.color.date_selected, null))
        //textView.setBackgroundColor(con.resources.getColor(R.color.date_selected_bg,null))
    }

}