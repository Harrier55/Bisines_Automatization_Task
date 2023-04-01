package com.example.bisinesautomatizationtask.features.stores.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisinesautomatizationtask.core.LocalStatus
import com.example.bisinesautomatizationtask.features.stores.domain.usecase.GetListStoresUseCase
import com.example.bisinesautomatizationtask.features.stores.ui.StoresViewState
import kotlinx.coroutines.launch

class StoresViewModel(private val useCase: GetListStoresUseCase) : ViewModel() {

    private val _viewState = MutableLiveData<StoresViewState>()
    val viewState: LiveData<StoresViewState> = _viewState


    init {
        getData()
        _viewState.postValue(
            StoresViewState(
            isLoading = true
        )
        )
    }

    private fun getData() {
        viewModelScope.launch {
            useCase.execute().collect { localResult ->
                when (localResult.status) {
                    LocalStatus.SUCCESS -> {
                        _viewState.postValue(
                            localResult.data?.let { listStoreEntity ->
                                StoresViewState(
                                    listStores = listStoreEntity,
                                    isLoading = false
                                )
                            }
                        )
                    }
                    LocalStatus.ERROR -> {
                        // todo Show Error
                    }
                }
            }
        }
    }
}