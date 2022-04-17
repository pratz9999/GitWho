package com.gitwho.presentation.home

import android.content.Intent
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.gitwho.MainActivity
import com.gitwho.R
import com.gitwho.common.CustomMatchers.Companion.waitFor
import com.gitwho.common.CustomMatchers.Companion.withItemCount
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var mainActivityTestRule = ActivityTestRule(
        MainActivity::class.java
    )

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mainActivityTestRule.launchActivity(Intent())
        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(3000))
    }

    @Test
    fun testRecyclerViewItemCountWithJ_Success() {
        Espresso.onView(withId(R.id.sv_users))
            .perform(typeSearchViewText("J"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.rv_user))
            .check(ViewAssertions.matches(withItemCount(30)))
    }

    @Test
    fun testRecyclerViewItemCountWithMyUser_Success() {
        Espresso.onView(withId(R.id.sv_users))
            .perform(typeSearchViewText("Pratz9999"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.rv_user))
            .check(ViewAssertions.matches(withItemCount(1)))
    }

    @Test
    fun testRecyclerViewItemCount_Failure() {
        Espresso.onView(withId(R.id.sv_users))
            .perform(typeSearchViewText("jkjkjkjsjdsjdsjd"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.rv_user))
            .check(ViewAssertions.matches(withItemCount(0)))
    }

    @Test
    fun testSearchDefaultText_Success() {
        Espresso.onView(withId(R.id.sv_users))
            .perform(typeSearchViewText("jkjkjkjsjdsjdsjd"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.tv_search_ph))
            .check(
                ViewAssertions.matches(
                    CoreMatchers.not(ViewMatchers.isDisplayed())
                )
            )
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    private fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "Change view text"
            }

            override fun getConstraints(): Matcher<View> {
                return CoreMatchers.allOf(
                    ViewMatchers.isDisplayed(),
                    ViewMatchers.isAssignableFrom(SearchView::class.java)
                )
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as SearchView).setQuery(text, false)
            }
        }
    }
}