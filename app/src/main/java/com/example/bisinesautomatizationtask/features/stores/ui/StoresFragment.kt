package com.example.bisinesautomatizationtask.features.stores.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bisinesautomatizationtask.R
import com.example.bisinesautomatizationtask.core.theme.ComposeMaterialTheme
import com.example.bisinesautomatizationtask.features.LoadIndicator
import com.example.bisinesautomatizationtask.features.stores.domain.models.StoresEntity
import com.example.bisinesautomatizationtask.features.stores.vm.StoresViewModel
import org.koin.androidx.compose.koinViewModel

private const val LONGITUDE = "longitude"
private const val LATITUDE = "latitude"

class StoresFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ComposeMaterialTheme {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        val context = LocalContext.current
                        StoragesScreen(context = context)
                    }
                }
            }
        }
    }
}

@Composable
private fun StoragesScreen(
context: Context,
    viewModel: StoresViewModel = koinViewModel()
) {

    val viewState = viewModel.viewState.observeAsState(initial = StoresViewState())
    val data = viewState.value.listStores
    val isLoading = viewState.value.isLoading

    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 40.dp)
        ) {
            data.forEach { storesEntity ->
                ItemStore(storesEntity,
                    onButtonClick = {
                        val activity = context as? AppCompatActivity
                        val navController =  activity?.findNavController(R.id.nav_host_fragment_activity_main)
                        val bundle = Bundle()
                        storesEntity.longitude?.let { bundle.putDouble(LONGITUDE, it) }
                        storesEntity.latitude?.let { bundle.putDouble(LATITUDE, it) }
                        navController?.navigate(R.id.navigation_notifications, bundle)
                    })
                Spacer(Modifier.size(16.dp))
            }
        }
        if (isLoading) LoadIndicator()
    }
}

@Composable
private fun ItemStore(
    entity: StoresEntity,
    onButtonClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.size(8.dp))
            Box(
                modifier = Modifier
                    .size(94.dp)
                    .padding(4.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painterResource(id = R.drawable.modern_city),
                    contentDescription = "town"
                )
            }
            Spacer(Modifier.size(16.dp))
            Column() {
                Text(text = stringResource(id = R.string.town) + entity.name)
                Spacer(Modifier.size(8.dp))
                Text(text = stringResource(id = R.string.address) + entity.address)
                Spacer(Modifier.size(8.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = {
                        onButtonClick.invoke()
                    }) {
                        Text(text = stringResource(R.string.see_maps))
                    }
                }
            }
        }
    }
}

