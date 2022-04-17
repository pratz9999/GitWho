package com.gitwho.domain.use_case

import com.gitwho.common.Constants
import com.gitwho.common.Resource
import com.gitwho.data.remote.dto.toUser
import com.gitwho.domain.model.User
import com.gitwho.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserByUsernameUseCase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(
        username: String
    ): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            val positionResponse = repository.getUserByUsername(
                username
            ).toUser()
            emit(Resource.Success(positionResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: Constants.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(Constants.UNEXPECTED_NETWORK_ERROR))
        }
    }
}