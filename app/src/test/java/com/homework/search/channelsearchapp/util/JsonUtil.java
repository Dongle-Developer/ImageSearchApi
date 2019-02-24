package com.homework.search.channelsearchapp.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonUtil {

   static final String ASSET_BASE_PATH = "../app/src/test/assets/";

   public static String convertStreamToString(InputStream is) throws Exception {
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
         sb.append(line).append("\n");
      }
      reader.close();
      return sb.toString();
   }

   public static String getStringFromFile(String filename) throws Exception {
      final InputStream stream = new FileInputStream(ASSET_BASE_PATH + filename);
      String ret = convertStreamToString(stream);
      stream.close();
      return ret;
   }
}
