package com.moonsworth.lunar.client.util.io;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.time.LocalDate;

public class JsonDateAdapter extends com.google.gson.TypeAdapter<LocalDate> {
   public JsonDateAdapter() {
   }

   public void method1(JsonWriter jsonwriter1, LocalDate localdate2) {
      jsonwriter1.value(localdate2.toString());
   }

   public LocalDate method2(JsonReader jsonreader1) {
      return LocalDate.parse(jsonreader1.nextString());
   }
}
