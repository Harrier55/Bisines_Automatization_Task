package com.example.bisinesautomatizationtask.features.dress.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.bisinesautomatizationtask.core.theme.ComposeMaterialTheme
import com.example.bisinesautomatizationtask.features.LoadIndicator
import com.example.bisinesautomatizationtask.features.dress.domain.models.DressEntity
import com.example.bisinesautomatizationtask.features.dress.ImageLoader
import com.example.bisinesautomatizationtask.features.dress.vm.DressViewModel
import org.koin.androidx.compose.koinViewModel

class DressFragment : Fragment() {

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
                        NewFeatureScreen()
                    }
                }
            }
        }
    }
}


@Composable
private fun NewFeatureScreen(viewModel: DressViewModel = koinViewModel()) {
    val viewState = viewModel.viewState.observeAsState(DressViewState())
    val data = viewState.value.dressList
    val isLoading = viewState.value.isLoading

    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 36.dp)
        ) {
            data?.let { list ->
                list.forEach { dressEntity ->
                    CardDress(entity = dressEntity)
                    Spacer(Modifier.size(16.dp))
                }
            }
        }
        if (isLoading) LoadIndicator()
    }
}


@Composable
private fun CardDress(entity: DressEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                entity.picture?.let { ImageLoader(stringBase64 = it) }
            }
            Spacer(Modifier.size(8.dp))
            entity.name?.let { Text(text = it, style = MaterialTheme.typography.titleMedium) }
            Spacer(Modifier.size(8.dp))
            Text(text = "Цена: ${entity.price}  руб.", style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.size(8.dp))
        }
    }
}