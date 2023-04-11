package com.example.bisinesautomatizationtask.features.stores.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.example.bisinesautomatizationtask.R
import com.example.bisinesautomatizationtask.databinding.FragmentNotificationBinding
import com.example.bisinesautomatizationtask.features.stores.domain.usecase.GetStoreById
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.runtime.image.ImageProvider
import org.koin.android.ext.android.inject

private const val ZOOM = 18F

class YandexMapFragment : Fragment() {

    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    private var latitude = 55.751574
    private var longitude = 37.573856
    private var idStore = 0.0

    private val useCase: GetStoreById by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idStore = arguments?.getDouble("id") ?: 0.0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MapKitFactory.initialize(requireContext())

        val storeEntity = useCase.execute(id = idStore)
        latitude = storeEntity?.latitude!!
        longitude = storeEntity.longitude!!

        /** set position **/
        binding.mapview.map.move(
            CameraPosition(Point(latitude, longitude), ZOOM, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5F),
            null
        )
        /** transformation Vector Image to Bitmap **/
        val bitmap = requireContext().getBitmapFromVectorDrawable(R.drawable.geometka_48)

        /** set marker position **/
        binding.mapview.map.mapObjects.addPlacemark(
            Point(latitude, longitude),
            ImageProvider.fromBitmap(bitmap)
        )

        binding.nameStore.text = storeEntity.name
        binding.addressStore.text = storeEntity.address


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapview.onStart()

    }

    override fun onStop() {
        binding.mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    /** функция преобразования Vector to Bitmap */
    private fun Context.getBitmapFromVectorDrawable(drawableId: Int): Bitmap? {
        var drawable = ContextCompat.getDrawable(this, drawableId) ?: return null

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable).mutate()
        }

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        ) ?: return null
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }
}