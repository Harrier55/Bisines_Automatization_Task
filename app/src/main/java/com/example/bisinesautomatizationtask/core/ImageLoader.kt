package com.example.bisinesautomatizationtask.features.dress

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy

/** Загрузка изображения**/
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageLoader(stringBase64: String) {

    val decodedString = Base64.decode(stringBase64, Base64.DEFAULT)
    val decodedByte = BitmapFactory.decodeByteArray(decodedString,0,decodedString.size )

    GlideImage(
        model = decodedByte,
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    ) {
        it.diskCacheStrategy(DiskCacheStrategy.ALL)
    }
}

