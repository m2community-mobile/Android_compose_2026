package com.m2comm.compose2026

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m2comm.compose2026.api.ApiRepository
import com.m2comm.compose2026.data.NoticeData
import com.m2comm.compose2026.data.SponsorData
import com.m2comm.compose2026.data.request.RequestToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    private val _sid = SingleLiveEvent<String>()
    val sid: LiveData<String> get() = _sid

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _menuSwitch = SingleLiveEvent<Unit>()
    val menuSwitch: LiveData<Unit> get() = _menuSwitch

    private val _toQr = SingleLiveEvent<Unit>()
    val toQr: LiveData<Unit> get() = _toQr

    private val _logout = SingleLiveEvent<Unit>()
    val logout: LiveData<Unit> get() = _logout

    private val _fragment = SingleLiveEvent<Fragment>()
    val fragment: LiveData<Fragment> get() = _fragment

    private val _popFragment = SingleLiveEvent<Fragment>()
    val popFragment: LiveData<Fragment> get() = _popFragment

    private val _toActivity = SingleLiveEvent<Activity>()
    val toActivity: LiveData<Activity> get() = _toActivity

    private val _closePop = SingleLiveEvent<Unit>()
    val closePop: LiveData<Unit> get() = _closePop

    private val _notice = SingleLiveEvent<NoticeData>()
    val notice: LiveData<NoticeData> get() = _notice

    private val _sponsors = SingleLiveEvent<SponsorData>()
    val sponsors: LiveData<SponsorData> get() = _sponsors


    fun toQr(){
        _toQr.call()
    }

    fun closePop(){
        _closePop.call()
    }

    fun menuClick(){
        _menuSwitch.call()
    }

    fun toFragment(frag: Fragment){
        _fragment.postValue(frag)
    }

    fun toPopFragment(frag: Fragment){
        _popFragment.postValue(frag)
    }

    fun startActivity(activity: Activity){
        _toActivity.postValue(activity)
    }

    fun logout(){
        _logout.call()
    }



    fun getSponsors(){
        viewModelScope.launch {
            try {
                val result = repository.getSponsor()
                _sponsors.postValue(result)
            }catch (e:Exception){

            }
        }
    }

    fun getNotice(){
        viewModelScope.launch {
            try {
                val result = repository.getNotice()
                _notice.postValue(result)
            }catch (e:Exception){

            }
        }
    }

    fun setToken(req: RequestToken){
        viewModelScope.launch {
            try {
                val res = repository.setToken(req)
            }catch (e:Exception) {
                _error.postValue(e.message)
                e.printStackTrace()
            }
        }
    }
/*


    fun getMyInfo(sid: String){
        viewModelScope.launch {
            try {
                val res = repository.getMyInfo(sid)
                if(res!=null){
                    if(res.code=="200"){
                        _myInfo.postValue(res)
                    } else {
                        _error.postValue(res.message)
                    }
                }
            }catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }*/
}