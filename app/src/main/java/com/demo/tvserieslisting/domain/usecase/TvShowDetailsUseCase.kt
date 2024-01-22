package com.demo.tvserieslisting.domain.usecase

import com.demo.tvserieslisting.common.Resource
import com.demo.tvserieslisting.domain.module.TvShowDetails
import com.demo.tvserieslisting.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TvShowDetailsUseCase @Inject constructor(
    private val repository : TvShowRepository
) {

    operator fun invoke(id : Int) : Flow<Resource<TvShowDetails>> = flow {
        try {
            emit(Resource.Loading())
            /* TODO First Access Local Storage if its gives null then make network call */

            var tvShowDetails = repository.getLocalStoredTvShow(id)

            if (tvShowDetails == null){
                tvShowDetails = repository.getRemoteStoredTvShow(id)
            }

            emit(Resource.Success(tvShowDetails))

        }
        catch (http : HttpException){
            emit(Resource.Error(message = http.localizedMessage ?: "Unknown Error"))
        }
        catch (io : IOException){
            emit(Resource.Error(message = "Turn On Your InterNet Connections"))
        }
        catch (e : Exception){
            emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }
}

