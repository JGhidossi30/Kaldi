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
public class MainActivityTests {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//
//        assertEquals("com.appfactory.kaldi", appContext.getPackageName());
//    }

    //User can enter text into email field on login page
    @Test
    public void loginTest(){
        onView(withId(R.id.email)).perform(typeText("Hello"));
    }

    //User can enter text into password field on login page
    @Test
    public void passwordTest(){
        onView(withId(R.id.password)).perform(typeText("password test"));
    }

    //Register button is clickable
    @Test
    public void registerButtonTest(){
        onView(withId(R.id.regButton)).check(matches(isClickable()));
    }

    //Login button is clickable
    @Test
    public void loginButtonTest(){
        onView(withId(R.id.loginButton)).check(matches(isClickable()));
    }

}
