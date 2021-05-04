package edu.mills.cs115.fruitthief.map

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import edu.mills.cs115.fruitthief.database.FruitTreeDAO
import edu.mills.cs115.fruitthief.database.Tree

class Markers () : Observer<Tree> {

//    lateinit var layoutManager: LinearLayoutManager
//    val trees = database.getTreeList()



    /**
     * Adds marker representations of the places list on the provided GoogleMap object
     */
    fun addMarkers(googleMap: GoogleMap) {
//        places.forEach { place ->
//            val marker = googleMap.addMarker(
//                MarkerOptions()
//                    .title(place.name)
//                    .position(place.latLng)
//            )
//        }
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onChanged(t: Tree?) {
        TODO("Not yet implemented")
    }
}