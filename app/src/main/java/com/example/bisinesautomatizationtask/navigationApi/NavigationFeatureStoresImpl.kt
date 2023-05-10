package com.example.bisinesautomatizationtask.navigationApi


import android.os.Bundle
import androidx.navigation.findNavController
import com.example.bisinesautomatizationtask.MainActivity
import com.example.bisinesautomatizationtask.R
import com.example.feature_stores.navigationApi.NavigationApiFeatureStores

class NavigationFeatureStoresImpl() : NavigationApiFeatureStores {

    override fun gotoYandexMap(storesId: Double?) {

        val mainActivity = MainActivity.activityInstance
        val navController = mainActivity.findNavController(R.id.nav_host_fragment_activity_main)

        val bundle = Bundle()
        if (storesId != null) {
            bundle.putDouble("id", storesId)
        }

        navController.navigate(R.id.navigation_maps,bundle)
    }
}