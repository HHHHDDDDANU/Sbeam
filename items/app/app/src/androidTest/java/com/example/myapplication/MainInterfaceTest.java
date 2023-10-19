package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.fragment.app.Fragment;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.myapplication.GameList.GameListFragment;
import com.example.myapplication.News.NewsFragment;
import com.example.myapplication.Profile.ProfileFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
/**
 * @author u7587847 Yongsong
 * This class use espresso to test if user could log in maininterface
 */

@RunWith(AndroidJUnit4.class)
public class MainInterfaceTest {

    public Fragment position0;
    public Fragment position1;
    public Fragment position2;

    public class CustomIdlingResource implements IdlingResource {

        private ResourceCallback resourceCallback;
        private boolean isIdle = false;


        @Override
        public String getName() {
            return CustomIdlingResource.class.getName();
        }

        @Override
        public boolean isIdleNow() {
            return isIdle;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback callback) {
            this.resourceCallback = callback;
        }


        public void setIdle(boolean isIdle) {
            this.isIdle = isIdle;
            if (isIdle && resourceCallback != null) {
                resourceCallback.onTransitionToIdle();
            }
        }
    }


    @Rule
    public ActivityScenarioRule<MainInterface> mActivityRule = new ActivityScenarioRule<>(MainInterface.class);

    @Test
    public void tabLayoutDisplaysCorrectTest() {
        onView(withText("News")).check(matches(isDisplayed()));
        onView(withText("Game List")).check(matches(isDisplayed()));
        onView(withText("Profile")).check(matches(isDisplayed()));
    }

    @Test
    public void FragmentsNumberTest() {
        mActivityRule.getScenario().onActivity(activity -> {
            MenuAdapter menuAdapter = new MenuAdapter(activity);
            assert (menuAdapter.getItemCount() == 3);
        });

    }
    @Test
    public void FragmentsTest() {
        mActivityRule.getScenario().onActivity(activity -> {
            MenuAdapter menuAdapter = new MenuAdapter(activity);
            position0 =menuAdapter.createFragment(0);
            position1 =menuAdapter.createFragment(1);
            position2=menuAdapter.createFragment(2);
            assert (position0 instanceof NewsFragment);
            assert(position1 instanceof GameListFragment);
            assert(position2 instanceof ProfileFragment);
        });
         }
    @Test
    public void SwipeOneTimeTest() throws InterruptedException {
        onView(withId(R.id.main_viewpager)).perform(swipeLeft());
        CustomIdlingResource customIdlingResource = new CustomIdlingResource();
        IdlingRegistry.getInstance().register(customIdlingResource);
        customIdlingResource.setIdle(false);
        Thread.sleep(500);
        customIdlingResource.setIdle(true);
        IdlingRegistry.getInstance().unregister(customIdlingResource);
        onView(withId(R.id.news_slide)).check(matches(isDisplayed()));


        onView(withId(R.id.main_viewpager)).perform(swipeRight());
        IdlingRegistry.getInstance().register(customIdlingResource);
        customIdlingResource.setIdle(false);
        Thread.sleep(500);
        customIdlingResource.setIdle(true);
        IdlingRegistry.getInstance().unregister(customIdlingResource);
        onView(withId(R.id.news_slide)).check(matches(isDisplayed()));


    }

}
