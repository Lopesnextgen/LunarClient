package com.moonsworth.lunar.client.itemtracker;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.time.LocalDate;

public class TypeAdapter extends com.google.gson.TypeAdapter<LocalDate> {
   public void method1(JsonWriter var1, LocalDate var2) {
      var1.value(var2.toString());
   }

   public LocalDate method2(JsonReader var1) {
      return LocalDate.parse(var1.nextString());
   }
}
