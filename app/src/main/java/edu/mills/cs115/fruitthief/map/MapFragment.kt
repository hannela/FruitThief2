package edu.mills.cs115.fruitthief.map

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import edu.mills.cs115.fruitthief.R
import edu.mills.cs115.fruitthief.database.FruitTreeDAO
import edu.mills.cs115.fruitthief.database.FruitTreeDatabase
import edu.mills.cs115.fruitthief.database.Tree

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment() {

    private lateinit var mMap: GoogleMap
    private lateinit var viewModel: MarkerViewModel
    private lateinit var viewModelFactory: MarkerViewModelFactory
    private var mapReady = false
    private lateinit var treesToDisplay: LiveData<List<Tree>>
    private lateinit var trees: LiveData<List<Tree>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = FruitTreeDatabase.getInstance(application).fruitTreeDAO
        viewModelFactory = MarkerViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MarkerViewModel::class.java)

        trees = viewModel.allTrees
        treesToDisplay = viewModel.allTrees

        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_map, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            mMap = googleMap
            mapReady = true
            updateMap()
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        treesToDisplay.observe(viewLifecycleOwner, Observer {
//            trees -> this.treesToDisplay = trees
            trees = this.treesToDisplay
            updateMap()
        })
    }

    fun updateMap() {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(37.892860, -122.078400)))
//        if (mapReady && trees != null) {
            trees.value?.forEach {
                tree ->
                val marker = LatLng(tree.lat, tree.lng)
                mMap.addMarker(
                    MarkerOptions().position(marker).title(tree.fruit.toString())
                )
            }
//        }
    }
}