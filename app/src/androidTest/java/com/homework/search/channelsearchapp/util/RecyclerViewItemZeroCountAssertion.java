package com.homework.search.channelsearchapp.util;

import static org.junit.Assert.assertTrue;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecyclerViewItemZeroCountAssertion implements ViewAssertion {

   @Override
   public void check(View view, NoMatchingViewException noViewFoundException) {
      if (noViewFoundException != null) {
         throw noViewFoundException;
      }

      RecyclerView recyclerView = (RecyclerView) view;
      RecyclerView.Adapter adapter = recyclerView.getAdapter();
      assert adapter != null;
      assertTrue(adapter.getItemCount() == 0);
   }
}