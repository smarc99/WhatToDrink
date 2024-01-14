package de.htw.wtd.gui;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.htw.wtd.R;

@RunWith(AndroidJUnit4.class)
public class EndToEndTests {

    @Rule
    public ActivityScenarioRule<MapActivity> activityRule = new ActivityScenarioRule<>(MapActivity.class);

    @Test
    public void testOpeningApp_ShowsMapAndRequestButton() {
        Espresso.onView(withId(R.id.map)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.request_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testRequestingLocationSuggestion_ShowsTwoButtons() {
        Espresso.onView(withId(R.id.request_button)).perform(click());

        Espresso.onView(withId(R.id.yes_button)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.no_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testRejectingSuggestion_ShowsMapAndRequestButton() {
        Espresso.onView(withId(R.id.request_button)).perform(click());

        Espresso.onView(withId(R.id.no_button)).perform(click());

        Espresso.onView(withId(R.id.map)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.request_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testAcceptingSuggestion_ShowsMapNewLocationAndRequestButton() {
        Espresso.onView(withId(R.id.request_button)).perform(click());

        Espresso.onView(withId(R.id.yes_button)).perform(click());

        Espresso.onView(withId(R.id.request_button)).check(matches(isDisplayed()));
    }
}
