package com.homework.search.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class DateUtils {

   public static Date StringToDate(String dateStr) throws ParseException {
      SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
      sourceFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
      Date convertedDate = sourceFormat.parse(dateStr);
      return convertedDate;
   }
}
