package com.example.mviapp

import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.mviapp.ui.main.intent.MainIntent
import com.example.mviapp.ui.main.viewmodel.PostViewModel
import com.example.mviapp.ui.main.viewstate.MainViewState
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class PostListTest {

    @get: Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 2)
    val composeRule = createAndroidComposeRule<MainActivity>()

    lateinit var viewModel: PostViewModel
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        hiltRule.inject()
        Dispatchers.setMain(testDispatcher)
        viewModel = composeRule.activity.viewModels<PostViewModel>().value
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun isLoadingDisplaying() = runTest {
        composeRule.onNodeWithTag("LoadingView").assertIsDisplayed()
        composeRule.onNodeWithTag("ErrorView").assertDoesNotExist()
    }

    @Test
    fun isListDisplaying() = runTest {
        viewModel.mainIntent.send(MainIntent.GetPosts)
        viewModel.state.collect() {
            when (it) {
                is MainViewState.Error -> verifyErrorViewDisplaying()
                MainViewState.Idle -> allTheViewsNotVisble()
                MainViewState.Loading -> isLoadingDisplaying()
                is MainViewState.Success -> isListViewDisplaying()
            }
        }
    }

    @Test
    fun isListViewDisplaying() {
        composeRule.onNodeWithTag("ListView").assertIsDisplayed()
    }

    @Test
    fun allTheViewsNotVisble() {
        composeRule.onNodeWithTag("ErrorView").assertDoesNotExist()
        composeRule.onNodeWithTag("LoadingView").assertDoesNotExist()
    }

    @Test
    fun verifyErrorViewDisplaying() {
        composeRule.onNodeWithTag("ErrorView").assertIsDisplayed()
        composeRule.onNodeWithTag("LoadingView").assertDoesNotExist()
    }
}