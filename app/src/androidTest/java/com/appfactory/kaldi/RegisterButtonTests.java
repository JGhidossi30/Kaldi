package com.appfactory.kaldi;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RegisterButtonTests {

    @Rule
    public ActivityTestRule<Register> activityRule = new ActivityTestRule<>(Register.class);


    //Student button is clickable
    @Test
    public void drinkerButtonTest(){
        onView(withId(R.id.drinkerButton)).check(matches(isClickable()));
    }

    //Merchant button is clickable
    @Test
    public void merchantButtonTest(){
        onView(withId(R.id.merchantButton)).check(matches(isClickable()));
    }
}
