package com.appfactory.kaldi;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RegisterMerchantTests {

    @Rule
    public ActivityTestRule<RegisterMerchantActivity> activityRule = new ActivityTestRule<>(RegisterMerchantActivity.class);


    //User can enter text into administrator field on merchant registration page
    @Test
    public void administratorTest(){
        onView(withId(R.id.adminInput)).perform(typeText("admin test"));
    }

    //User can enter text into store field on merchant registration page
    @Test
    public void storeTest(){
        onView(withId(R.id.storeInput)).perform(typeText("store test"));
    }

    //User can enter text into email field on merchant registration page
    @Test
    public void emailTest(){
        onView(withId(R.id.emailInput)).perform(typeText("email test"));
    }

    //User can enter text into  password field on login page
    @Test
    public void passwordTest(){
        onView(withId(R.id.passwordInput)).perform(typeText("password test"));
    }

    //User can enter text into confirm password field on login page
    @Test
    public void confirmPasswordTest(){
        onView(withId(R.id.confirmPasswordInput)).perform(typeText("confirm password test"));
    }

    //User can enter text into store field on login page
    @Test
    public void addressTest(){
        onView(withId(R.id.addressInput)).perform(typeText("address test"));
    }

    //User can enter text into store field on login page
    @Test
    public void inputItemTest(){
        onView(withId(R.id.initialItemInput)).perform(typeText("item test"));
    }

    //User can enter text into store field on login page
    @Test
    public void caffineTest(){
        onView(withId(R.id.caffeineInput)).perform(typeText("100"));
    }


    //Register button is clickable
    @Test
    public void registerButtonTest(){
        onView(withId(R.id.register_button)).check(matches(isClickable()));
    }


}