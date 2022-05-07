package com.amazingtalker.assessment

import android.R.id
import android.content.Context
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.amazingtalker.assessment.activity.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    /**
     * Use [ActivityScenarioRule] to create and launch the activity under test before each test,
     * and close it after each test. This is a replacement for
     * [androidx.test.rule.ActivityTestRule].
     */
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun verifyText_MainActivity() {

        // Type text and then press the button.
        //Espresso.onView(withId(R.id.editTextUserInput)) .perform(ViewActions.typeText(STRING_TO_BE_TYPED), ViewActions.closeSoftKeyboard())
        //Espresso.onView(withId(R.id.changeTextBt)).perform(ViewActions.click())
        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

        // Check that the text was changed.
        Espresso.onView(withId(R.id.tutor_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(targetContext.getResources().getString(R.string.list_of_tutors))))
    }
}