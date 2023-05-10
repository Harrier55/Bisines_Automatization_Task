package com.example.feature_stores.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_module.LocalStatus
import com.example.feature_stores.navigationApi.NavigationApiFeatureStores
import com.example.feature_stores.domain.usecase.GetListStoresUseCase
import com.example.feature_stores.ui.StoresViewState
import kotlinx.coroutines.launch

class StoresViewModel(
    private val useCase: GetListStoresUseCase,
    private val navigation: NavigationApiFeatureStores
) : ViewModel() {

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

    fun navigateToMaps(storesId: Double) {
        navigation.gotoYandexMap(storesId = storesId)
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