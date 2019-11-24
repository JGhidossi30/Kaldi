package com.appfactory.kaldi;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MerchantMainTests {

    @Rule
    public ActivityTestRule<MerchantMainActivity> activityRule = new ActivityTestRule<>(MerchantMainActivity.class);

    //Manage Store button is clickable
    @Test
    public void manageStoreTest(){
        onView(withId(R.id.manageStore)).check(matches(isClickable()));
    }

    //Drinker Profile button is clickable
    @Test
    public void drinkerProfileTest(){
        onView(withId(R.id.drinkerProfile)).check(matches(isClickable()));
    }
}
