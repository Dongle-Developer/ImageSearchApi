package com.homework.search.data;

import android.databinding.BaseObservable;
import java.util.Date;

public class ThumbnailItem extends BaseObservable {

   private Date date;
   private String imageUrl;

   public ThumbnailItem(Date date,String imageUrl) {
      this.date = date;
      this.imageUrl = imageUrl;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

}
