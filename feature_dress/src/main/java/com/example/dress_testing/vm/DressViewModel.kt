package com.example.dress_testing.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_module.LocalStatus
import com.example.dress_testing.ui.DressViewState
import com.example.dress_testing.domain.usecase.GetDressListUseCase
import kotlinx.coroutines.launch

class DressViewModel(private val useCase: GetDressListUseCase) : ViewModel() {



    private val _viewState = MutableLiveData<DressViewState>()
    val viewState: LiveData<DressViewState> = _viewState

    init {
        getData()
        _viewState.postValue(
            DressViewState(
            isLoading = true
        )
        )
    }

    private fun getData(){
        viewModelScope.launch {
            useCase.execute().collect{result ->
                when(result.status){
                    LocalStatus.SUCCESS ->{
                        _viewState.postValue(
                            DressViewState(
                                dressList = result.data,
                                isLoading = false
                            )
                        )
                    }
                    LocalStatus.ERROR ->{
                        // todo показать Тост
                    }
                }
            }
        }
    }
}