package com.homework.search.channelsearchapp;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.not;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.homework.search.R;
import com.homework.search.channelsearchapp.util.RecyclerViewItemCountAssertion;
import com.homework.search.channelsearchapp.util.RecyclerViewItemZeroCountAssertion;
import com.homework.search.ui.MainActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SearchFragmentUiTest {

   @Rule
   public ActivityTestRule activityTestRule = new ActivityTestRule(MainActivity.class);

   @Test
   public void searchView_Query_Delay_1sec_send_RecyclerView_Count() throws InterruptedException {
      onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("Test"));
      Thread.sleep(1000);
      onView(withId(R.id.rv_search_contents)).check(new RecyclerViewItemCountAssertion());
   }

   @Test
   public void searchView_Query_Delay_1sec_send_RecyclerView_Zero_Count()
       throws InterruptedException {
      onView(withId(android.support.design.R.id.search_src_text)).perform(replaceText("ㅣㅡㄱㄷ"));
      Thread.sleep(1000);
      onView(withId(R.id.rv_search_contents)).check(new RecyclerViewItemZeroCountAssertion());
   }

   @Test
   public void searchView_Query_Delay_1sec_send_No_More_Searching_Message()
       throws InterruptedException {
      onView(withId(android.support.design.R.id.search_src_text)).perform(replaceText("ㅣㅡㄱㄷ"));
      Thread.sleep(1100);
      onView(allOf(withId(android.support.design.R.id.snackbar_text),
          withText(R.string.no_more_searching_message)))
          .check(matches(isDisplayed()));
   }

   @Test
   public void searchView_Query_Delay_1sec_send_After_Progress_Gone() throws InterruptedException {
      onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("Test"));
      Thread.sleep(1500);
      onView(withId(R.id.progress)).check(matches(not(isDisplayed())));
   }

}
