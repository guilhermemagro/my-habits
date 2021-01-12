package com.guilhermemagro.myhabits.views

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.guilhermemagro.myhabits.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Test
    fun createHabit() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.add_habit_edt)).perform(typeText("TestHabit"))
        onView(withText("Adicionar"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withText("TestHabit")).check(matches(isDisplayed()))
    }
}