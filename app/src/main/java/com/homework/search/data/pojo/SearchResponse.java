package com.homework.search.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchResponse {

   @SerializedName("documents")
   @Expose
   private List<ThumbnailResponse> documents = null;
   @SerializedName("meta")
   @Expose
   private Meta meta;

   public List<ThumbnailResponse> getDocuments() {
      return documents;
   }

   public void setDocuments(List<ThumbnailResponse> documents) {
      this.documents = documents;
   }

   public Meta getMeta() {
      return meta;
   }

   public void setMeta(Meta meta) {
      this.meta = meta;
   }
}
