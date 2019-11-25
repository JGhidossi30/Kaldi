package com.appfactory.kaldi;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class MerchantMainTests {

    @Rule
    public ActivityTestRule<MerchantMainActivity> activityRule = new ActivityTestRule<>(MerchantMainActivity.class);

    //Manage Store button is clickable
    @Test
    public void manageStoreTest(){
        //onView(withId(R.id.manageStore)).check(matches(isClickable()));
        Instrumentation.ActivityMonitor monitor1 = getInstrumentation().addMonitor(ManageStoreActivity.class.getName(), null, false);

        onView(withId(R.id.manageStore)).perform(click());

        Activity manageStore = getInstrumentation().waitForMonitorWithTimeout(monitor1, 5000);

        assertNotNull(manageStore);

        manageStore.finish();
    }

    //Drinker Profile button is clickable
    @Test
    public void drinkerProfileTest(){
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(DrinkerMainActivity.class.getName(), null, false);

        onView(withId(R.id.drinkerProfile)).perform(click());

        Activity drinkerProfile = getInstrumentation().waitForMonitorWithTimeout(monitor2, 5000);

        assertNotNull(drinkerProfile);

        drinkerProfile.finish();
    }
}
