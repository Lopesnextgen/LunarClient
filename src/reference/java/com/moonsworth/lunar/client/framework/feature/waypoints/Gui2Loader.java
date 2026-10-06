package com.moonsworth.lunar.client.framework.feature.waypoints;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.files.Files6_2;
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

public class Gui2Loader extends com.moonsworth.lunar.client.framework.feature.waypoints.mixin.Gui2Loader {
   public static final int field3 = 0;
   private final File field4 = new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "BLClient-Mod-Profiles");
   private DataInputStream field5;
   private Waypoints field6;
   private Waypoints2 field7;

   public Gui2Loader(String var1, String var2) {
      super(var1, var2);
   }

   @Override
   public List<Files6_2<String, String>> method2() {
      if (this.field4 != null && this.field4.exists() && this.field4.isDirectory()) {
         File[] var1 = this.field4.listFiles();
         if (var1 == null) {
            return Collections.emptyList();
         }

         ArrayList var2 = new ArrayList();

         for (File var6 : var1) {
            if (this.method2(var6)) {
               String var7 = this.method3(var6);
               if (var7 != null) {
                  var2.add(Files6_2.method1(var6.getName(), var7));
               }
            }
         }

         return var2;
      } else {
         return Collections.emptyList();
      }
   }

   private boolean method2(File var1) {
      if (!var1.isFile()) {
         return false;
      }

      try (ZipFile var2 = new ZipFile(var1)) {
         return true;
      } catch (IOException var7) {
         return false;
      }
   }

   private String method3(File var1) {
      try (ZipFile var2 = new ZipFile(var1)) {
         ZipEntry var3 = var2.getEntry("data.json");
         if (var3 != null) {
            try (
               InputStream var4 = var2.getInputStream(var3);
               BufferedReader var5 = new BufferedReader(new InputStreamReader(var4));
            ) {
               StringBuilder var6 = new StringBuilder();

               String var7;
               while ((var7 = var5.readLine()) != null) {
                  var6.append(var7);
               }

               return var6.toString();
            }
         }
      } catch (IOException var15) {
         Slayer.method8("Waypoints", "Failed to extract JSON from zip file: " + var1.getPath(), new Object[]{var15});
      }

      return null;
   }

   @Override
   public Collection<GuiHandler2> method4(@Nullable String var1, String var2) {
      if (var2.startsWith("BLCWP:") && var2.endsWith(";")) {
         return this.method12(var2);
      }

      Slayer.method4("Waypoints", "Importing waypoints from Badlion: " + var1, new Object[0]);

      try {
         JsonObject var3 = JsonParser.parseString(var2).getAsJsonObject();
         return this.method5(var3);
      } catch (JsonParseException var4) {
         Slayer.method8("Waypoints", "Failed to parse BLC JSON data: " + var4.getMessage(), new Object[]{var4});
         return Collections.emptyList();
      }
   }

   private Collection<GuiHandler2> method5(JsonObject var1) {
      if (!var1.has("waypoints")) {
         return Collections.emptyList();
      }

      JsonObject var2 = var1.getAsJsonObject("waypoints");
      if (!var2.has("newWaypoints")) {
         return Collections.emptyList();
      }

      JsonArray var3 = var2.getAsJsonArray("newWaypoints");
      ArrayList var4 = new ArrayList();

      for (JsonElement var6 : var3) {
         JsonObject var7 = var6.getAsJsonObject();
         if (var7.has("waypoints")) {
            for (JsonElement var10 : var7.getAsJsonArray("waypoints")) {
               JsonObject var11 = var10.getAsJsonObject();
               GuiHandler2 var12 = this.method6(var11, var7);
               if (var12 != null) {
                  var4.add(var12);
               }
            }
         }
      }

      return var4;
   }

   private GuiHandler2 method6(JsonObject var1, JsonObject var2) {
      try {
         JsonObject var3 = this.method11(var1, var2);
         String var4 = var3.get("TYPE").getAsString();
         String var5 = this.method8(var4, var3);
         String var6 = this.method9(var4, var3);
         return GuiHandler2.method18()
            .method2(var1.get("name").getAsString())
            .method10(var1.getAsJsonObject("enabled").getAsJsonPrimitive("value").getAsBoolean())
            .method3(Vec3Bridge.method2(var1.get("x").getAsDouble(), var1.get("y").getAsDouble(), var1.get("z").getAsDouble()))
            .method12(this.method7(var4, var3))
            .method5(this.method10(var6))
            .method4(var5)
            .method13(false)
            .method19();
      } catch (Exception var7) {
         Slayer.method8("Waypoints", "Failed to create waypoint from JSON: " + var7.getMessage(), new Object[]{var7});
         return null;
      }
   }

   private String method7(String var1, JsonObject var2) {
      return var1.equals("Singleplayer") ? "sp:" + var2.get("WORLD").getAsString() : "mp:" + var2.get("SERVER").getAsString();
   }

   private String method8(String var1, JsonObject var2) {
      return var1.equals("Singleplayer") ? WordUtils.capitalize(var2.get("WORLD").getAsString()) : WordUtils.capitalize(var2.get("SERVER").getAsString());
   }

   private String method9(String var1, JsonObject var2) {
      return var1.equals("Singleplayer")
         ? WordUtils.capitalize(var2.get("WORLD_DIMENSION").getAsString())
         : WordUtils.capitalize(var2.get("SERVER_DIMENSION").getAsString());
   }

   private int method10(String var1) {
      String var2 = var1.toLowerCase();
      if (var2.contains("nether")) {
         return -1;
      } else {
         return var2.contains("end") ? 1 : 0;
      }
   }

   private JsonObject method11(JsonObject var1, JsonObject var2) {
      return var1.has("renderConditions")
         ? var1.getAsJsonObject("renderConditions").getAsJsonObject("values")
         : var2.getAsJsonObject("renderConditions").getAsJsonObject("values");
   }

   public Collection<GuiHandler2> method12(String var1) {
      byte[] var2 = Base64.getDecoder().decode(var1.substring(6, var1.length() - 1));
      this.field5 = new DataInputStream(new ByteArrayInputStream(var2));
      int var3 = this.readUnsignedByte();
      if (var3 > 0) {
         throw new IOException("Unsupported data version: " + var3);
      }

      this.field6 = new Waypoints(this);

      try {
         this.field7 = new Waypoints2(this);
      } catch (DataFormatException var5) {
         throw new IOException("Failed to initialize string decoder", var5);
      }

      Slayer.method6("Waypoints", "Compressed BLC waypoint format not fully implemented", new Object[0]);
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
      boolean var1 = this.readBool();
      if (!var1) {
         return this.readUnsignedByte();
      }

      boolean var2 = this.readBool();
      return !var2 ? this.readUnsignedShort() : this.readUnsignedInt();
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

   public byte[] method14(int var1) {
      byte[] var2 = new byte[var1];
      this.field5.read(var2);
      return var2;
   }
}
