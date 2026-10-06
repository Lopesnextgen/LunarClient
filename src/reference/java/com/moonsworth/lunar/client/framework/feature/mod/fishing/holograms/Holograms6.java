package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.joml.Vector3i;

public class Holograms6 {
   public Map<String, List<Holograms6.Data2>> field1;
   public Map<String, Map<Holograms6.Type, Set<Holograms6.Data2>>> field2;

   public static class Data implements JsonDeserializer<Holograms6> {
      public Holograms6 method1(JsonElement var1, java.lang.reflect.Type var2, JsonDeserializationContext var3) {
         Holograms6 var4 = new Holograms6();
         JsonObject var5 = var1.getAsJsonObject();
         JsonObject var6 = var5.get("fast").getAsJsonObject();
         JsonObject var7 = var5.get("normal").getAsJsonObject();
         var4.field1 = this.method2(var6);
         var4.field2 = this.method3(var7);
         return var4;
      }

      private Map<String, List<Holograms6.Data2>> method2(JsonObject var1) {
         HashMap var2 = new HashMap();

         for (Entry var4 : var1.entrySet()) {
            JsonArray var5 = ((JsonElement)var4.getValue()).getAsJsonArray();
            ArrayList var6 = new ArrayList();

            for (JsonElement var8 : var5) {
               var6.add(this.method4(var8.getAsJsonObject(), true));
            }

            var2.put((String)var4.getKey(), var6);
         }

         return var2;
      }

      private Map<String, Map<Holograms6.Type, Set<Holograms6.Data2>>> method3(JsonObject var1) {
         HashMap var2 = new HashMap();

         for (Entry var4 : var1.entrySet()) {
            HashMap var5 = new HashMap();

            for (Entry var7 : ((JsonElement)var4.getValue()).getAsJsonObject().entrySet()) {
               JsonArray var8 = ((JsonElement)var7.getValue()).getAsJsonArray();
               HashSet var9 = new HashSet();

               for (JsonElement var11 : var8) {
                  var9.add(this.method4(var11.getAsJsonObject(), false));
               }

               var5.put(Holograms6.Type.bySerializedName((String)var7.getKey()), var9);
            }

            var2.put((String)var4.getKey(), var5);
         }

         return var2;
      }

      private Holograms6.Data2 method4(JsonObject var1, boolean var2) {
         Holograms6.Type2 var3 = Holograms6.Type2.byId(var1.get("a").getAsString());
         int var4 = var2 ? var1.get("b").getAsInt() : -1;
         Holograms6.Data2.Type var5 = var2 ? Holograms6.Data2.Type.FLIP : Holograms6.Data2.Type.byId(var1.get("b").getAsString());
         return new Holograms6.Data2(var4, var3, var5);
      }
   }

   public class Data2 {
      private final int field1;
      private final Holograms6.Type2 field2;
      private final Holograms6.Data2.Type field3;

      public Data2(int var1, Holograms6.Type2 var2, Holograms6.Data2.Type var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public int method1() {
         return this.field1;
      }

      public Holograms6.Type2 method2() {
         return this.field2;
      }

      public Holograms6.Data2.Type method3() {
         return this.field3;
      }

      public enum Type {
         FLIP("FLIP"),
         UP("UP"),
         DOWN("DOWN");

         private final String id;

         private static Holograms6.Data2.Type byId(String var0) {
            for (Holograms6.Data2.Type var4 : values()) {
               if (var4.id.equals(var0)) {
                  return var4;
               }
            }

            return null;
         }

         @Generated
         public String getId() {
            return this.id;
         }

         @Generated
         Type(String var3) {
            this.id = var3;
         }
      }
   }

   public enum Type {
      PURPLE(Component.text("Purple").color(NamedTextColor.LIGHT_PURPLE), new Vector3i(16, 57, 19), "PURPLE_WOOL"),
      ORANGE(Component.text("Orange").color(NamedTextColor.GOLD), new Vector3i(16, 57, 18), "ORANGE_WOOL"),
      BLUE(Component.text("Blue").color(NamedTextColor.BLUE), new Vector3i(16, 57, 17), "BLUE_WOOL"),
      GREEN(Component.text("Green").color(NamedTextColor.GREEN), new Vector3i(16, 57, 16), "LIME_WOOL"),
      RED(Component.text("Red").color(NamedTextColor.RED), new Vector3i(16, 57, 15), "RED_WOOL");

      private final Component pretty;
      private final Vector3i relativePos;
      private final String serializedName;

      private static Holograms6.Type bySerializedName(String var0) {
         for (Holograms6.Type var4 : values()) {
            if (var4.serializedName.equals(var0)) {
               return var4;
            }
         }

         return null;
      }

      @Generated
      public Component getPretty() {
         return this.pretty;
      }

      @Generated
      public Vector3i getRelativePos() {
         return this.relativePos;
      }

      @Generated
      public String getSerializedName() {
         return this.serializedName;
      }

      @Generated
      Type(Component var3, Vector3i var4, String var5) {
         this.pretty = var3;
         this.relativePos = var4;
         this.serializedName = var5;
      }
   }

   public enum Type2 {
      COAL(Bridge.method34().method51(), new Vector3i(20, 61, 10), "COAL_BLOCK"),
      GOLD(Bridge.method34().method14(), new Vector3i(20, 61, 15), "GOLD_BLOCK"),
      QUARTZ(Bridge.method34().method17(), new Vector3i(20, 61, 20), "QUARTZ_BLOCK"),
      TERRACOTTA(Bridge.method34().method18(), new Vector3i(10, 61, 10), "HARDENED_CLAY"),
      EMERALD(Bridge.method34().method16(), new Vector3i(10, 61, 15), "EMERALD_BLOCK"),
      DIAMOND(Bridge.method34().method15(), new Vector3i(10, 61, 20), "DIAMOND_BLOCK"),
      WATER(Bridge.method34().method3(), new Vector3i(15, 60, 5), "WATER");

      private final Bridge3_23 block;
      private final Vector3i relativePos;
      private final String id;

      public static Holograms6.Type2 byId(String var0) {
         for (Holograms6.Type2 var4 : values()) {
            if (var4.id.equals(var0)) {
               return var4;
            }
         }

         return null;
      }

      @Generated
      public Bridge3_23 getBlock() {
         return this.block;
      }

      @Generated
      public Vector3i getRelativePos() {
         return this.relativePos;
      }

      @Generated
      public String getId() {
         return this.id;
      }

      @Generated
      Type2(Bridge3_23 var3, Vector3i var4, String var5) {
         this.block = var3;
         this.relativePos = var4;
         this.id = var5;
      }
   }
}
