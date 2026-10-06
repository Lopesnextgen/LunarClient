package com.moonsworth.lunar.client.framework.feature.markers;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public class Markers2_3 {
   @SerializedName("value")
   @Nullable
   private final String field1;
   public static final Markers2_3 field2 = new Markers2_3(null);

   public Markers2_3(@Nullable String var1) {
      this.field1 = var1;
   }

   public String method1(String var1) {
      return this.isEmpty() ? var1 : this.field1;
   }

   public boolean isEmpty() {
      return this == field2 || this.value() == null;
   }

   public static Markers2_3 method2() {
      return field2;
   }

   public static Markers2_3 method3(@Nullable String var0) {
      return new Markers2_3(var0);
   }

   @SerializedName("value")
   @Nullable
   public String value() {
      return this.field1;
   }
}
