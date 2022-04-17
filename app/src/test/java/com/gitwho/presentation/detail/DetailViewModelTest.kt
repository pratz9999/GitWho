package com.gitwho.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gitwho.common.DummyData
import com.gitwho.common.MainCoroutineRule
import com.gitwho.data.repository.FakeUserRepository
import com.gitwho.domain.use_case.GetUserByUsernameUseCase
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var getUserByUsernameUseCase: GetUserByUsernameUseCase
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        getUserByUsernameUseCase = GetUserByUsernameUseCase(fakeUserRepository)
        detailViewModel = DetailViewModel(getUserByUsernameUseCase)
        detailViewModel.getUserByUsername("")
    }

    @Test
    fun `Check complete response, success`() = runBlocking {
        Truth.assertThat(detailViewModel.state.value.item).isEqualTo(DummyData.usernameResponse)
    }
}