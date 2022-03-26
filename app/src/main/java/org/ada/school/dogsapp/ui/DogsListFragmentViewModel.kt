package org.ada.school.dogsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.ada.school.dogsapp.data.model.Dog
import org.ada.school.dogsapp.data.repository.remote.DogService
import org.ada.school.dogsapp.data.repository.remote.RetrofitGenerator

/**
 * @author Santiago Carrillo
 * 26/03/22.
 */
class DogsListFragmentViewModel : ViewModel() {

    val dogsListLiveData = MutableLiveData<ArrayList<Dog>>()

    init {
        loadDogsList()
    }

    private fun loadDogsList() {
        viewModelScope.launch(Dispatchers.IO) {
            val dogsList = ArrayList<Dog>()

            val dogService = RetrofitGenerator.getInstance().create(DogService::class.java)
            val breedsList = dogService.listDogBreeds().body()
            breedsList?.message?.forEach { (breed, _) ->
                dogsList.add(
                    retrieveBreedPhoto(
                        dogService,
                        breed
                    )
                )
            }

            dogsListLiveData.postValue(dogsList)
        }
    }

    private suspend fun retrieveBreedPhoto(dogService: DogService, breed: String): Dog {
        val dogBreedImages = dogService.getDogBreedImages(breed).body()
        return Dog(breed, dogBreedImages!!.message[0])
    }


}