package com.rtech.myshoppy.cache

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object CachePref {
    fun getCacheInstance(context: Context): SharedPreferences {
        return context.getSharedPreferences("shoppy_cache", MODE_PRIVATE)
    }

    fun getEditor(sp: SharedPreferences) : SharedPreferences.Editor{
        return sp.edit()
    }

    fun setUserIdToCache(editor: SharedPreferences.Editor, userId: Int) {
        editor.putInt("USER_ID", userId)
        editor.commit()
    }

    fun getUserIdFromCache(sp: SharedPreferences) : Int {
        return sp.getInt("USER_ID", -1)
    }

    fun logoutUserCache(editor: SharedPreferences.Editor) {
        editor.putInt("USER_ID", -1)
        editor.commit()
    }
}