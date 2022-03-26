package org.ada.school.dogsapp.data.repository.remote

import org.ada.school.dogsapp.data.repository.remote.dto.DogBreedImages
import org.ada.school.dogsapp.data.repository.remote.dto.DogBreedsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @author Santiago Carrillo
 * 26/03/22.
 */
interface DogService {

    @GET("breeds/list/all")
   suspend fun listDogBreeds(): Response<DogBreedsList>

   @GET("breed/{breed}/images")
   suspend fun getDogBreedImages(@Path("breed") breed: String): Response<DogBreedImages>

}