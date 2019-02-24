package com.homework.search.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThumbnailResponse {

   @SerializedName("datetime")
   @Expose
   private String datetime;

   @SerializedName("image_url")
   @Expose
   private String thumbnailUrl;

   public String getDatetime() {
      return datetime;
   }

   public void setDatetime(String datetime) {
      this.datetime = datetime;
   }

   public String getThumbnailUrl() {
      return thumbnailUrl;
   }

   public void setThumbnailUrl(String thumbnailUrl) {
      this.thumbnailUrl = thumbnailUrl;
   }

   @Override
   public String toString() {
      return "ThumbnailResponse{" +
          "datetime='" + datetime + '\'' +
          ", thumbnailUrl='" + thumbnailUrl + '\'' +
          '}';
   }

}
