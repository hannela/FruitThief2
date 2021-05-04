package edu.mills.cs115.fruitthief.map

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import edu.mills.cs115.fruitthief.R
import edu.mills.cs115.fruitthief.database.FruitTreeDatabase
import edu.mills.cs115.fruitthief.database.Tree
import timber.log.Timber

class MapFragment : Fragment() {

    private lateinit var mMap: GoogleMap
    private lateinit var viewModel: MarkerViewModel
    private lateinit var viewModelFactory: MarkerViewModelFactory
    private var mapReady = false
    private lateinit var treesToDisplay: LiveData<List<Tree>>
    private lateinit var trees: LiveData<List<Tree>>
    private var locationCoordinates = LatLng(37.804363, -122.271111) // Oakland
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = FruitTreeDatabase.getInstance(application).fruitTreeDAO
        viewModelFactory = MarkerViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MarkerViewModel::class.java)

        treesToDisplay = viewModel.allTrees
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_map, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            mMap = googleMap
            mapReady = true
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        treesToDisplay.observe(viewLifecycleOwner, Observer {
            trees = treesToDisplay
            if (mapReady) {
                updateMap()
            }
        })
    }

    private fun updateMap() {
        cameraLocation()
        trees.value?.forEach { tree ->
            val marker = LatLng(tree.lat, tree.lng)
            mMap.addMarker(
                MarkerOptions().position(marker).title(tree.fruit.toString())
            )
        }
    }

    private fun moveCamera() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationCoordinates, 11f))
    }

    private fun cameraLocation() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        if (location != null) {
                            locationCoordinates = LatLng(location.latitude, location.longitude)
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationCoordinates, 11f))
                        } else {
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationCoordinates, 11f))
                        }
                        // Got last known location. In some rare situations this can be null.
                    }
            }
        }
//        return locationCoordinates
    }
}
