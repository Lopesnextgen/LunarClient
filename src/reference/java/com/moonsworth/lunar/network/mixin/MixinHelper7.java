package com.moonsworth.lunar.network.mixin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.moonsworth.lunar.network.MixinHelper11;
import com.moonsworth.lunar.network.MixinHelper12;
import com.moonsworth.lunar.network.MixinHelper13;
import com.moonsworth.lunar.network.MixinHelper14;
import com.moonsworth.lunar.network.MixinHelper15;
import com.moonsworth.lunar.network.GameRewindLayerGameplay;
import com.moonsworth.lunar.network.GameRewindLayerAudio;
import com.moonsworth.lunar.network.GameRewindLayerEffect;
import com.moonsworth.lunar.network.MixinHelper22;
import com.moonsworth.lunar.network.MixinHelper23;
import com.moonsworth.lunar.network.GamePromotionInteractionEventData;
import com.moonsworth.lunar.network.GameBlogPostInteractionEventData;
import com.moonsworth.lunar.network.MixinHelper26;
import com.moonsworth.lunar.network.GameRewindEditorSessionEventData;
import com.moonsworth.lunar.network.MixinHelper62;
import com.moonsworth.lunar.network.MixinHelper63;
import com.moonsworth.lunar.network.NetworkIterator;
import com.moonsworth.lunar.network.GameRewindLayerAddEvent;
import com.moonsworth.lunar.network.GameRewindProjectExportEvent;
import com.moonsworth.lunar.network.GamePromotionInteractionEvent;
import com.moonsworth.lunar.network.GameRewindEditorSessionEvent;
import com.moonsworth.lunar.network.GameRewindRecordingEvent;
import io.gsonfire.GsonFireBuilder;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import okio.ByteString;

public class MixinHelper7 {
   private static Gson gson;
   private static boolean field1 = false;
   private static MixinHelper7.Data2 field2 = new MixinHelper7.Data2();
   private static MixinHelper7.Data6 field3 = new MixinHelper7.Data6();
   private static MixinHelper7.Data5 field4 = new MixinHelper7.Data5();
   private static MixinHelper7.Data4 field5 = new MixinHelper7.Data4();
   private static MixinHelper7.Data3 field6 = new MixinHelper7.Data3();
   private static final DateFormat field7 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
   private static final DateTimeFormatter field8 = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

   public static GsonBuilder method1() {
      GsonFireBuilder var0 = new GsonFireBuilder();
      return var0.createGsonBuilder();
   }

   private static String method2(JsonElement var0, String var1) {
      JsonElement var2 = var0.getAsJsonObject().get(var1);
      if (null == var2) {
         throw new IllegalArgumentException("missing discriminator field: <" + var1 + ">");
      } else {
         return var2.getAsString();
      }
   }

   private static Class method3(Map var0, String var1) {
      Class var2 = (Class)var0.get(var1);
      if (null == var2) {
         throw new IllegalArgumentException("cannot determine model class of name: <" + var1 + ">");
      } else {
         return var2;
      }
   }

   public static Gson getGson() {
      return gson;
   }

   public static void setGson(Gson var0) {
      gson = var0;
   }

   public static void method6(boolean var0) {
      field1 = var0;
   }

   public static String method7(Object var0) {
      return gson.toJson(var0);
   }

   public static <T> T method8(String var0, Type var1) {
      try {
         if (field1) {
            JsonReader var2 = new JsonReader(new StringReader(var0));
            var2.setLenient(true);
            return (T)gson.fromJson(var2, var1);
         } else {
            return (T)gson.fromJson(var0, var1);
         }
      } catch (JsonParseException var3) {
         if (var1.equals(String.class)) {
            return (T)var0;
         } else {
            throw var3;
         }
      }
   }

   public static void method9(DateTimeFormatter var0) {
      field4.method1(var0);
   }

   public static void method10(DateTimeFormatter var0) {
      field5.method1(var0);
   }

   public static void method11(DateFormat var0) {
      field2.method1(var0);
   }

   public static void method12(DateFormat var0) {
      field3.method1(var0);
   }

   static {
      GsonBuilder var0 = method1();
      var0.registerTypeAdapter(Date.class, field2);
      var0.registerTypeAdapter(java.sql.Date.class, field3);
      var0.registerTypeAdapter(OffsetDateTime.class, field4);
      var0.registerTypeAdapter(LocalDate.class, field5);
      var0.registerTypeAdapter(byte[].class, field6);
      var0.registerTypeAdapterFactory(new com.moonsworth.lunar.network.MixinHelper7.Data());
      var0.registerTypeAdapterFactory(new com.moonsworth.lunar.network.MixinHelper9.Data());
      var0.registerTypeAdapterFactory(new MixinHelper14.Data());
      var0.registerTypeAdapterFactory(new com.moonsworth.lunar.network.MixinHelper10.Data());
      var0.registerTypeAdapterFactory(new MixinHelper12.Data());
      var0.registerTypeAdapterFactory(new MixinHelper13.Data());
      var0.registerTypeAdapterFactory(new MixinHelper62.Data());
      var0.registerTypeAdapterFactory(new NetworkIterator.Data());
      var0.registerTypeAdapterFactory(new GameBlogPostInteractionEventData.Data());
      var0.registerTypeAdapterFactory(new MixinHelper11.Data());
      var0.registerTypeAdapterFactory(new com.moonsworth.lunar.network.MixinHelper4.Data());
      var0.registerTypeAdapterFactory(new MixinHelper15.Data());
      var0.registerTypeAdapterFactory(new GamePromotionInteractionEvent.Data());
      var0.registerTypeAdapterFactory(new GamePromotionInteractionEventData.Data());
      var0.registerTypeAdapterFactory(new GameRewindEditorSessionEvent.Data());
      var0.registerTypeAdapterFactory(new GameRewindEditorSessionEventData.Data());
      var0.registerTypeAdapterFactory(new com.moonsworth.lunar.network.MixinHelper8.Data());
      var0.registerTypeAdapterFactory(new com.moonsworth.lunar.network.GameRewindExportAudio.Data());
      var0.registerTypeAdapterFactory(new com.moonsworth.lunar.network.MixinHelper.Data());
      var0.registerTypeAdapterFactory(new MixinHelper63.Data());
      var0.registerTypeAdapterFactory(new GameRewindLayerAddEvent.Data());
      var0.registerTypeAdapterFactory(new MixinHelper23.Data());
      var0.registerTypeAdapterFactory(new GameRewindLayerAudio.Data());
      var0.registerTypeAdapterFactory(new GameRewindLayerEffect.Data());
      var0.registerTypeAdapterFactory(new GameRewindLayerGameplay.Data());
      var0.registerTypeAdapterFactory(new GameRewindProjectExportEvent.Data());
      var0.registerTypeAdapterFactory(new MixinHelper22.Data());
      var0.registerTypeAdapterFactory(new GameRewindRecordingEvent.Data());
      var0.registerTypeAdapterFactory(new MixinHelper26.Data());
      var0.registerTypeAdapterFactory(new com.moonsworth.lunar.network.GameRewindRecordingLocation.Data());
      gson = var0.create();
   }

   public static class Data2 extends TypeAdapter<Date> {
      private DateFormat field1;

      public Data2() {
      }

      public Data2(DateFormat var1) {
         this.field1 = var1;
      }

      public void method1(DateFormat var1) {
         this.field1 = var1;
      }

      public void write(JsonWriter var1, Date var2) {
         if (var2 == null) {
            var1.nullValue();
         } else {
            String var3;
            if (this.field1 != null) {
               var3 = this.field1.format(var2);
            } else {
               var3 = var2.toInstant().atOffset(ZoneOffset.UTC).format(MixinHelper7.field8);
            }

            var1.value(var3);
         }
      }

      public Date read(JsonReader var1) {
         try {
            switch (var1.peek()) {
               case NULL:
                  var1.nextNull();
                  return null;
               default:
                  String var2 = var1.nextString();

                  try {
                     return this.field1 != null ? this.field1.parse(var2) : MixinHelper7.field7.parse(var2);
                  } catch (ParseException var4) {
                     throw new JsonParseException(var4);
                  }
            }
         } catch (IllegalArgumentException var5) {
            throw new JsonParseException(var5);
         }
      }
   }

   public static class Data3 extends TypeAdapter<byte[]> {
      public void method1(JsonWriter var1, byte[] var2) {
         if (var2 == null) {
            var1.nullValue();
         } else {
            var1.value(ByteString.of(var2).base64());
         }
      }

      public byte[] method2(JsonReader var1) {
         switch (var1.peek()) {
            case NULL:
               var1.nextNull();
               return null;
            default:
               String var2 = var1.nextString();
               ByteString var3 = ByteString.decodeBase64(var2);
               return var3.toByteArray();
         }
      }
   }

   public static class Data4 extends TypeAdapter<LocalDate> {
      private DateTimeFormatter field1;

      public Data4() {
         this(DateTimeFormatter.ISO_LOCAL_DATE);
      }

      public Data4(DateTimeFormatter var1) {
         this.field1 = var1;
      }

      public void method1(DateTimeFormatter var1) {
         this.field1 = var1;
      }

      public void method2(JsonWriter var1, LocalDate var2) {
         if (var2 == null) {
            var1.nullValue();
         } else {
            var1.value(this.field1.format(var2));
         }
      }

      public LocalDate method3(JsonReader var1) {
         switch (var1.peek()) {
            case NULL:
               var1.nextNull();
               return null;
            default:
               String var2 = var1.nextString();
               return LocalDate.parse(var2, this.field1);
         }
      }
   }

   public static class Data5 extends TypeAdapter<OffsetDateTime> {
      private DateTimeFormatter field1;

      public Data5() {
         this(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
      }

      public Data5(DateTimeFormatter var1) {
         this.field1 = var1;
      }

      public void method1(DateTimeFormatter var1) {
         this.field1 = var1;
      }

      public void method2(JsonWriter var1, OffsetDateTime var2) {
         if (var2 == null) {
            var1.nullValue();
         } else {
            var1.value(this.field1.format(var2));
         }
      }

      public OffsetDateTime method3(JsonReader var1) {
         switch (var1.peek()) {
            case NULL:
               var1.nextNull();
               return null;
            default:
               String var2 = var1.nextString();
               if (var2.endsWith("+0000")) {
                  var2 = var2.substring(0, var2.length() - 5) + "Z";
               }

               return OffsetDateTime.parse(var2, this.field1);
         }
      }
   }

   public static class Data6 extends TypeAdapter<java.sql.Date> {
      private DateFormat field1;

      public Data6() {
      }

      public Data6(DateFormat var1) {
         this.field1 = var1;
      }

      public void method1(DateFormat var1) {
         this.field1 = var1;
      }

      public void write(JsonWriter var1, java.sql.Date var2) {
         if (var2 == null) {
            var1.nullValue();
         } else {
            String var3;
            if (this.field1 != null) {
               var3 = this.field1.format(var2);
            } else {
               var3 = var2.toString();
            }

            var1.value(var3);
         }
      }

      public java.sql.Date read(JsonReader var1) {
         switch (var1.peek()) {
            case NULL:
               var1.nextNull();
               return null;
            default:
               String var2 = var1.nextString();

               try {
                  return this.field1 != null
                     ? new java.sql.Date(this.field1.parse(var2).getTime())
                     : new java.sql.Date(MixinHelper7.field7.parse(var2).getTime());
               } catch (ParseException var4) {
                  throw new JsonParseException(var4);
               }
         }
      }
   }
}
