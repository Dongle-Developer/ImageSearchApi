package com.homework.search.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.homework.search.R;
import com.homework.search.base.BaseRecyclerViewAdapter;
import com.homework.search.data.ThumbnailItem;

public final class BindingUtils {

   @BindingAdapter("imageUrl")
   public static void setImageUrl(ImageView imageView, String url) {
      Context context = imageView.getContext();
      GlideApp.with(context)
          .load(url)
          .placeholder(R.drawable.ic_placeholder_24dp)
          .error(R.drawable.ic_error_24dp)
          .centerCrop()
          .into(imageView);
   }

   @BindingAdapter({"adapter"})
   public static void addThumbnailItems(RecyclerView recyclerView, ObservableArrayList<ThumbnailItem> thumbnailItems) {
      BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) recyclerView.getAdapter();
      if (adapter != null) {
         adapter.setItems(thumbnailItems);
      }
   }

}
