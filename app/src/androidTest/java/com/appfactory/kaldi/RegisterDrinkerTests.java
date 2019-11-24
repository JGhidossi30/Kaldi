package com.appfactory.kaldi;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RegisterDrinkerTests {

    @Rule
    public ActivityTestRule<RegisterDrinkerActivity> activityRule = new ActivityTestRule<>(RegisterDrinkerActivity.class);

    //User can enter text into Name field on drinker registration page
    @Test
    public void nameTest(){
        onView(withId(R.id.adminInput)).perform(typeText("Name Test"));
    }

    //User can enter text into Email field on drinker registration page
    @Test
    public void emailTest(){
        onView(withId(R.id.emailInput)).perform(typeText("Email Test"));
    }

    //User can enter text into password field on drinker registration page
    @Test
    public void passwordTest(){
        onView(withId(R.id.passwordInput)).perform(typeText("password"));
    }

    //User can enter text into confirm password field on drinker registration page
    @Test
    public void confirmPasswordTest(){
        onView(withId(R.id.confirmPasswordInput)).perform(typeText("confirm"));
    }


}
