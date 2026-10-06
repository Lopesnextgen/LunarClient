package com.moonsworth.lunar.client.framework.feature.waypoints;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.files.ValuePair;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;
import org.apache.commons.lang3.text.WordUtils;

public class BadlionWaypointImporter extends com.moonsworth.lunar.client.framework.feature.waypoints.mixin.WaypointImporter {
   public static final int field3 = 0;
   private final File field4 = new File(Ref.method3().bridge$getMcDataDir(), "BLClient-Mod-Profiles");
   private DataInputStream field5;
   private Waypoints field6;
   private WaypointStringPool field7;

   public BadlionWaypointImporter(String text1, String text2) {
      super(text1, text2);
   }

   @Override
   public List<ValuePair<String, String>> method2() {
      if (this.field4 != null && this.field4.exists() && this.field4.isDirectory()) {
         File[] items1 = this.field4.listFiles();
         if (items1 == null) {
            return Collections.emptyList();
         }

         ArrayList list2 = new ArrayList();

         for (File file6 : items1) {
            if (this.method2(file6)) {
               String text7 = this.method3(file6);
               if (text7 != null) {
                  list2.add(ValuePair.method1(file6.getName(), text7));
               }
            }
         }

         return list2;
      } else {
         return Collections.emptyList();
      }
   }

   private boolean method2(File file1) {
      if (!file1.isFile()) {
         return false;
      }

      try (ZipFile zipfile2 = new ZipFile(file1)) {
         return true;
      } catch (IOException exception7) {
         return false;
      }
   }

   private String method3(File file1) {
      try (ZipFile zipfile2 = new ZipFile(file1)) {
         ZipEntry zipentry3 = zipfile2.getEntry("data.json");
         if (zipentry3 != null) {
            try (
               InputStream input4 = zipfile2.getInputStream(zipentry3);
               BufferedReader reader5 = new BufferedReader(new InputStreamReader(input4));
            ) {
               StringBuilder builder6 = new StringBuilder();

               String text7;
               while ((text7 = reader5.readLine()) != null) {
                  builder6.append(text7);
               }

               return builder6.toString();
            }
         }
      } catch (IOException exception15) {
         LunarLogger.method8("Waypoints", "Failed to extract JSON from zip file: " + file1.getPath(), new Object[]{exception15});
      }

      return null;
   }

   @Override
   public Collection<Waypoint> method4(@Nullable String text1, String text2) {
      if (text2.startsWith("BLCWP:") && text2.endsWith(";")) {
         return this.method12(text2);
      }

      LunarLogger.method4("Waypoints", "Importing waypoints from Badlion: " + text1, new Object[0]);

      try {
         JsonObject json3 = JsonParser.parseString(text2).getAsJsonObject();
         return this.method5(json3);
      } catch (JsonParseException jsonparseexception4) {
         LunarLogger.method8("Waypoints", "Failed to parse BLC JSON data: " + jsonparseexception4.getMessage(), new Object[]{jsonparseexception4});
         return Collections.emptyList();
      }
   }

   private Collection<Waypoint> method5(JsonObject json1) {
      if (!json1.has("waypoints")) {
         return Collections.emptyList();
      }

      JsonObject json2 = json1.getAsJsonObject("waypoints");
      if (!json2.has("newWaypoints")) {
         return Collections.emptyList();
      }

      JsonArray array3 = json2.getAsJsonArray("newWaypoints");
      ArrayList list4 = new ArrayList();

      for (JsonElement element6 : array3) {
         JsonObject json7 = element6.getAsJsonObject();
         if (json7.has("waypoints")) {
            for (JsonElement element10 : json7.getAsJsonArray("waypoints")) {
               JsonObject json11 = element10.getAsJsonObject();
               Waypoint guihandler212 = this.method6(json11, json7);
               if (guihandler212 != null) {
                  list4.add(guihandler212);
               }
            }
         }
      }

      return list4;
   }

   private Waypoint method6(JsonObject json1, JsonObject json2) {
      try {
         JsonObject json3 = this.method11(json1, json2);
         String text4 = json3.get("TYPE").getAsString();
         String text5 = this.method8(text4, json3);
         String text6 = this.method9(text4, json3);
         return Waypoint.method18()
            .method2(json1.get("name").getAsString())
            .method10(json1.getAsJsonObject("enabled").getAsJsonPrimitive("value").getAsBoolean())
            .method3(Vec3Bridge.method2(json1.get("x").getAsDouble(), json1.get("y").getAsDouble(), json1.get("z").getAsDouble()))
            .method12(this.method7(text4, json3))
            .method5(this.method10(text6))
            .method4(text5)
            .method13(false)
            .method19();
      } catch (Exception exception7) {
         LunarLogger.method8("Waypoints", "Failed to create waypoint from JSON: " + exception7.getMessage(), new Object[]{exception7});
         return null;
      }
   }

   private String method7(String text1, JsonObject json2) {
      return text1.equals("Singleplayer") ? "sp:" + json2.get("WORLD").getAsString() : "mp:" + json2.get("SERVER").getAsString();
   }

   private String method8(String text1, JsonObject json2) {
      return text1.equals("Singleplayer") ? WordUtils.capitalize(json2.get("WORLD").getAsString()) : WordUtils.capitalize(json2.get("SERVER").getAsString());
   }

   private String method9(String text1, JsonObject json2) {
      return text1.equals("Singleplayer")
         ? WordUtils.capitalize(json2.get("WORLD_DIMENSION").getAsString())
         : WordUtils.capitalize(json2.get("SERVER_DIMENSION").getAsString());
   }

   private int method10(String text1) {
      String text2 = text1.toLowerCase();
      if (text2.contains("nether")) {
         return -1;
      } else {
         return text2.contains("end") ? 1 : 0;
      }
   }

   private JsonObject method11(JsonObject json1, JsonObject json2) {
      return json1.has("renderConditions")
         ? json1.getAsJsonObject("renderConditions").getAsJsonObject("values")
         : json2.getAsJsonObject("renderConditions").getAsJsonObject("values");
   }

   public Collection<Waypoint> method12(String text1) {
      byte[] items2 = Base64.getDecoder().decode(text1.substring(6, text1.length() - 1));
      this.field5 = new DataInputStream(new ByteArrayInputStream(items2));
      int number3 = this.readUnsignedByte();
      if (number3 > 0) {
         throw new IOException("Unsupported data version: " + number3);
      }

      this.field6 = new Waypoints(this);

      try {
         this.field7 = new WaypointStringPool(this);
      } catch (DataFormatException dataformatexception5) {
         throw new IOException("Failed to initialize string decoder", dataformatexception5);
      }

      LunarLogger.method6("Waypoints", "Compressed BLC waypoint format not fully implemented", new Object[0]);
      return Collections.emptyList();
   }

   public String readString() {
      return this.field7.readString();
   }

   public boolean readBool() {
      return this.field6.method1();
   }

   public int readInt() {
      return this.field5.readInt();
   }

   public long method13() {
      boolean flag1 = this.readBool();
      if (!flag1) {
         return this.readUnsignedByte();
      }

      boolean flag2 = this.readBool();
      return !flag2 ? this.readUnsignedShort() : this.readUnsignedInt();
   }

   private int readUnsignedShort() {
      return this.field5.readShort() - -32768;
   }

   public long readUnsignedInt() {
      return this.field5.readInt() - Integer.MIN_VALUE;
   }

   public int readUnsignedByte() {
      return this.field5.readByte() - -128;
   }

   public byte[] method14(int index1) {
      byte[] items2 = new byte[index1];
      this.field5.read(items2);
      return items2;
   }
}
