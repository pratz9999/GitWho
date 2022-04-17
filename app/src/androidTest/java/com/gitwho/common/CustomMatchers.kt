package com.gitwho.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher

class CustomMatchers {
  companion object {
    fun withItemCount(count: Int): Matcher<View> {
      return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

        override fun describeTo(description: Description?) {
          description?.appendText("RecyclerView with item count: $count")
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
          return item?.adapter?.itemCount == count
        }
      }
    }
    fun waitFor(delay: Long): ViewAction {
      return object : ViewAction {
        override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()
        override fun getDescription(): String = "wait for $delay milliseconds"
        override fun perform(uiController: UiController, v: View?) {
          uiController.loopMainThreadForAtLeast(delay)
        }
      }
    }
  }
}