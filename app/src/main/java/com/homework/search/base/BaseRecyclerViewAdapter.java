package com.homework.search.base;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList.OnListChangedCallback;
import android.support.v7.widget.RecyclerView;

public abstract class BaseRecyclerViewAdapter<ITEM, VH extends BaseDataBindingViewHolder> extends
    RecyclerView.Adapter<VH> {

   private ObservableArrayList<ITEM> items;

   public void setItems(ObservableArrayList<ITEM> list) {
      this.items = list;
      this.items.addOnListChangedCallback(listChangedCallback);
      notifyDataSetChanged();
   }

   @Override
   public int getItemCount() {
      return items.size();
   }

   protected ITEM getItem(int position) {
      return items.get(position);
   }

   private OnListChangedCallback<ObservableArrayList<ITEM>> listChangedCallback = new OnListChangedCallback<ObservableArrayList<ITEM>>() {
      @Override
      public void onChanged(ObservableArrayList<ITEM> sender) {
         notifyDataSetChanged();
      }

      @Override
      public void onItemRangeChanged(ObservableArrayList<ITEM> sender, int positionStart,
          int itemCount) {
         notifyItemRangeChanged(positionStart, itemCount);
      }

      @Override
      public void onItemRangeInserted(ObservableArrayList<ITEM> sender, int positionStart,
          int itemCount) {
         notifyItemRangeInserted(positionStart, itemCount);
      }

      @Override
      public void onItemRangeMoved(ObservableArrayList<ITEM> sender, int fromPosition,
          int toPosition, int itemCount) {

      }

      @Override
      public void onItemRangeRemoved(ObservableArrayList<ITEM> sender, int positionStart,
          int itemCount) {
         notifyItemRangeInserted(positionStart, itemCount);
      }
   };

   public void onDestroyView() {
      items.removeOnListChangedCallback(listChangedCallback);
      items.clear();
   }
}
