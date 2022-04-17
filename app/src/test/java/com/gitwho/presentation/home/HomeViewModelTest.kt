package com.gitwho.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gitwho.common.DummyData
import com.gitwho.common.MainCoroutineRule
import com.gitwho.data.repository.FakeUserRepository
import com.gitwho.domain.use_case.GetUserBySearchUseCase
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
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var getUserBySearchUseCase: GetUserBySearchUseCase
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        getUserBySearchUseCase = GetUserBySearchUseCase(fakeUserRepository)
        homeViewModel = HomeViewModel(getUserBySearchUseCase)
        homeViewModel.getUserByQuery("")
    }

    @Test
    fun `Check complete response, success`() = runBlocking {
        Truth.assertThat(homeViewModel.state.value.data).isEqualTo(DummyData.searchResultResponse)
    }

    @Test
    fun `Check index 1 response, success`() = runBlocking {
        Truth.assertThat(homeViewModel.state.value.data[0]).isEqualTo(DummyData.itemOneResult)
    }

    @Test
    fun `Check index 2 response, success`() = runBlocking {
        Truth.assertThat(homeViewModel.state.value.data[1]).isEqualTo(DummyData.itemTwoResult)
    }
}