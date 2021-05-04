package edu.mills.cs115.fruitthief.ui.addtree

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import edu.mills.cs115.fruitthief.database.FruitTreeDAO
import edu.mills.cs115.fruitthief.database.Tree
import kotlinx.coroutines.runBlocking

class AddTreeViewModel : ViewModel() {
    var fruit = ""
    var loc = LatLng(0.0, 0.0)

    fun onItemSelected(string: String){
        fruit = string
    }

    fun onButtonClicked(dataSource: FruitTreeDAO){
        runBlocking {
            dataSource
                .insert(Tree(0, dataSource.getFruitByName(fruit).fruitId, loc.latitude, loc.longitude))
            // need additional action to add tree to map?
        }
    }
}