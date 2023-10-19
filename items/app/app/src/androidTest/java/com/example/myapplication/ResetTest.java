package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.myapplication.LoginAndSignup.ResetPassword;
import com.example.myapplication.LoginAndSignup.SignActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * @author u7587847 Yongsong
 * This class use espresso to test if user could log in reset passport interface
 */
@RunWith(AndroidJUnit4.class)
public class ResetTest {
    @Before
    public void setUp() {
        Intents.init();
    }
    @After
    public void tearDown() {
        Intents.release();
    }

    @Rule
    public ActivityScenarioRule<SignActivity> mActivityRule = new ActivityScenarioRule<>(SignActivity.class);

    @Test
    public void LogSuccessfulText() {
        onView(withId(R.id.login_reset)).perform(click());
        Espresso.closeSoftKeyboard();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intents.intended(hasComponent(ResetPassword.class.getName()));
    }}
