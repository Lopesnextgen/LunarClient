package com.moonsworth.lunar.client.driver.core.gui.mixin.nameplate;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class Nameplate2 {
   public static Nameplate2.Data11 method1(@Nullable String var0, int var1) {
      String var2 = var0 == null ? "" : var0.trim();
      if (var2.length() > 200) {
         return method2();
      }

      try {
         StringBuilder var3 = new StringBuilder("https://api.klipy.com/api/v1/RpEJNT11YK7VjO07Z75cV8iEybS1DXSnhbsIeGP84Azp7xMsAY9oxcS7ZuVhMISv/gifs")
            .append("/")
            .append(var2.isEmpty() ? "trending" : "search")
            .append("?page=")
            .append(Math.max(1, var1))
            .append("&per_page=")
            .append(Math.min(100, Math.max(1, 24)))
            .append("&customer_id=")
            .append(URLEncoder.encode(ThreadModuleDump80.installationId, StandardCharsets.UTF_8))
            .append("&content_filter=high");
         if (!var2.isEmpty()) {
            var3.append("&q=").append(URLEncoder.encode(var2, StandardCharsets.UTF_8));
         }

         HttpURLConnection var4 = (HttpURLConnection)new URL(var3.toString()).openConnection();
         var4.setRequestMethod("GET");
         var4.setConnectTimeout(15000);
         var4.setReadTimeout(15000);
         int var5 = var4.getResponseCode();
         InputStream var6 = var5 >= 200 && var5 < 300 ? var4.getInputStream() : var4.getErrorStream();
         String var7 = method5(var6);
         var4.disconnect();
         if (var5 >= 200 && var5 < 300 && !var7.isEmpty()) {
            Nameplate2.Data9 var8 = (Nameplate2.Data9)ThreadModuleDump48.field22.fromJson(var7, Nameplate2.Data9.class);
            if (var8 != null && var8.result() && var8.method1() != null) {
               Nameplate2.Data6 var9 = var8.method1();
               Nameplate2.Data8[] var10 = var9.method1();
               if (var10 == null) {
                  return method2();
               }

               ArrayList var11 = new ArrayList();

               for (Nameplate2.Data8 var15 : var10) {
                  if (var15 != null) {
                     String var16 = method3(var15.method1());
                     if (var16 != null && !var16.isBlank()) {
                        String var17 = method4(var15.method3(), false);
                        String var18 = method4(var15.method3(), true);
                        if (var17 != null && var18 != null) {
                           String var19 = var15.method2() == null ? "" : var15.method2();
                           String var20 = var15.title() == null ? "" : var15.title();
                           var11.add(new Nameplate2.Data10(var16, var19, var20, var17, var18));
                        }
                     }
                  }
               }

               return new Nameplate2.Data11(var11.toArray(new Nameplate2.Data10[0]), var9.hasNext());
            } else {
               Slayer.method8("Conversation", "Klipy API returned unsuccessful result");
               return method2();
            }
         } else {
            Slayer.method8("Conversation", "Klipy HTTP error: " + var5);
            return method2();
         }
      } catch (Exception var21) {
         Slayer.method8("Conversation", "Klipy request failed", var21);
         return method2();
      }
   }

   private static Nameplate2.Data11 method2() {
      return new Nameplate2.Data11(new Nameplate2.Data10[0], false);
   }

   @Nullable
   private static String method3(@Nullable JsonElement var0) {
      if (var0 != null && !var0.isJsonNull() && var0.isJsonPrimitive()) {
         return var0.getAsJsonPrimitive().isNumber() ? var0.getAsNumber().toString() : var0.getAsString();
      } else {
         return null;
      }
   }

   @Nullable
   private static String method4(@Nullable Nameplate2.Data5 var0, boolean var1) {
      if (var0 == null) {
         return null;
      }

      Nameplate2.Data7 var2 = var1 ? var0.method2() : var0.method1();
      return var2 != null && var2.method1() != null ? var2.method1().url() : null;
   }

   private static String method5(@Nullable InputStream var0) {
      if (var0 == null) {
         return "";
      }

      StringBuilder var1 = new StringBuilder();

      String var3;
      try (BufferedReader var2 = new BufferedReader(new InputStreamReader(var0, StandardCharsets.UTF_8))) {
         while ((var3 = var2.readLine()) != null) {
            var1.append(var3);
         }
      }

      return var1.toString();
   }

   @Generated
   private Nameplate2() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public class Data10 {
      @SerializedName("id")
      private final String field1;
      @SerializedName("slug")
      private final String field2;
      @SerializedName("title")
      private final String field3;
      @SerializedName("url")
      private final String field4;
      @SerializedName("previewUrl")
      private final String field5;

      public Data10(String var1, String var2, String var3, String var4, String var5) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
      }

      @SerializedName("id")
      public String id() {
         return this.field1;
      }

      @SerializedName("slug")
      public String method1() {
         return this.field2;
      }

      @SerializedName("title")
      public String title() {
         return this.field3;
      }

      @SerializedName("url")
      public String url() {
         return this.field4;
      }

      @SerializedName("previewUrl")
      public String method2() {
         return this.field5;
      }
   }

   public class Data11 {
      @SerializedName("gifs")
      private final Nameplate2.Data10[] field1;
      @SerializedName("hasMore")
      private final boolean field2;

      public Data11(Nameplate2.Data10[] var1, boolean var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      @SerializedName("gifs")
      public Nameplate2.Data10[] method1() {
         return this.field1;
      }

      @SerializedName("hasMore")
      public boolean hasMore() {
         return this.field2;
      }
   }

   private class Data12 {
      @SerializedName("url")
      private final String field1;

      private Data12(String var1) {
         this.field1 = var1;
      }

      @SerializedName("url")
      public String url() {
         return this.field1;
      }
   }

   private class Data5 {
      @SerializedName("md")
      private final Nameplate2.Data7 field1;
      @SerializedName("sm")
      private final Nameplate2.Data7 field2;

      private Data5(Nameplate2.Data7 var1, Nameplate2.Data7 var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      @SerializedName("md")
      public Nameplate2.Data7 method1() {
         return this.field1;
      }

      @SerializedName("sm")
      public Nameplate2.Data7 method2() {
         return this.field2;
      }
   }

   private class Data6 {
      @SerializedName("data")
      private final Nameplate2.Data8[] field1;
      @SerializedName("has_next")
      private final boolean hasNext;

      private Data6(Nameplate2.Data8[] var1, boolean var2) {
         this.field1 = var1;
         this.hasNext = var2;
      }

      @SerializedName("data")
      public Nameplate2.Data8[] method1() {
         return this.field1;
      }
   }

   private class Data7 {
      @SerializedName("gif")
      private final Nameplate2.Data12 field1;

      private Data7(Nameplate2.Data12 var1) {
         this.field1 = var1;
      }

      @SerializedName("gif")
      public Nameplate2.Data12 method1() {
         return this.field1;
      }
   }

   private class Data8 {
      @SerializedName("id")
      private final JsonElement field1;
      @SerializedName("slug")
      private final String field2;
      @SerializedName("title")
      private final String field3;
      @SerializedName("file")
      private final Nameplate2.Data5 field4;

      private Data8(JsonElement var1, String var2, String var3, Nameplate2.Data5 var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      @SerializedName("id")
      public JsonElement method1() {
         return this.field1;
      }

      @SerializedName("slug")
      public String method2() {
         return this.field2;
      }

      @SerializedName("title")
      public String title() {
         return this.field3;
      }

      @SerializedName("file")
      public Nameplate2.Data5 method3() {
         return this.field4;
      }
   }

   private class Data9 {
      @SerializedName("result")
      private final boolean result;
      @SerializedName("data")
      private final Nameplate2.Data6 field1;

      private Data9(boolean var1, Nameplate2.Data6 var2) {
         this.result = var1;
         this.field1 = var2;
      }

      @SerializedName("data")
      public Nameplate2.Data6 method1() {
         return this.field1;
      }
   }
}
