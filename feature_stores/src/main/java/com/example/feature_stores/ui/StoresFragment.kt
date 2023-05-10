package com.example.feature_stores.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bisinesautomatizationtask.core.LoadIndicator
import com.example.core_module.theme.ComposeMaterialTheme.ComposeMaterialTheme
import com.example.feature_stores.R
import com.example.feature_stores.domain.models.StoresEntity
import com.example.feature_stores.vm.StoresViewModel
import org.koin.androidx.compose.koinViewModel


class StoresFragment : Fragment() {

//    private const val LONGITUDE = "longitude"
//    private const val LATITUDE = "latitude"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


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
                        storesEntity.id?.let { viewModel.navigateToMaps(storesId = it) }

//                        val activity = context as? AppCompatActivity
//                        val navController =
//                            activity?.findNavController(R.id.nav_host_fragment_activity_main)
//                        val bundle = Bundle()
//                        storesEntity.id?.let { bundle.putDouble("id", it) }
//                        navController?.navigate(R.id.navigation_notifications, bundle)
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
                    .background(Color.LightGray),
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

