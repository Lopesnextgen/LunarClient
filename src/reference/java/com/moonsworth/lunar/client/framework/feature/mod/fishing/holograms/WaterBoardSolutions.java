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

public class WaterBoardSolutions {
   public Map<String, List<WaterBoardSolutions.WaterBoardBlock>> field1;
   public Map<String, Map<WaterBoardSolutions.Type, Set<WaterBoardSolutions.WaterBoardBlock>>> field2;

   public WaterBoardSolutions() {
   }

   public static class Data implements JsonDeserializer<WaterBoardSolutions> {
      public Data() {
      }

      public WaterBoardSolutions method1(JsonElement element1, java.lang.reflect.Type type2, JsonDeserializationContext jsondeserializationcontext3) {
         WaterBoardSolutions holograms64 = new WaterBoardSolutions();
         JsonObject json5 = element1.getAsJsonObject();
         JsonObject json6 = json5.get("fast").getAsJsonObject();
         JsonObject json7 = json5.get("normal").getAsJsonObject();
         holograms64.field1 = this.method2(json6);
         holograms64.field2 = this.method3(json7);
         return holograms64;
      }

      private Map<String, List<WaterBoardSolutions.WaterBoardBlock>> method2(JsonObject json1) {
         HashMap map2 = new HashMap();

         for (Entry entry4 : json1.entrySet()) {
            JsonArray array5 = ((JsonElement)entry4.getValue()).getAsJsonArray();
            ArrayList list6 = new ArrayList();

            for (JsonElement element8 : array5) {
               list6.add(this.method4(element8.getAsJsonObject(), true));
            }

            map2.put((String)entry4.getKey(), list6);
         }

         return map2;
      }

      private Map<String, Map<WaterBoardSolutions.Type, Set<WaterBoardSolutions.WaterBoardBlock>>> method3(JsonObject json1) {
         HashMap map2 = new HashMap();

         for (Entry entry4 : json1.entrySet()) {
            HashMap map5 = new HashMap();

            for (Entry entry7 : ((JsonElement)entry4.getValue()).getAsJsonObject().entrySet()) {
               JsonArray array8 = ((JsonElement)entry7.getValue()).getAsJsonArray();
               HashSet set9 = new HashSet();

               for (JsonElement element11 : array8) {
                  set9.add(this.method4(element11.getAsJsonObject(), false));
               }

               map5.put(WaterBoardSolutions.Type.bySerializedName((String)entry7.getKey()), set9);
            }

            map2.put((String)entry4.getKey(), map5);
         }

         return map2;
      }

      private WaterBoardSolutions.WaterBoardBlock method4(JsonObject json1, boolean flag2) {
         WaterBoardSolutions.WaterBoardMaterial type23 = WaterBoardSolutions.WaterBoardMaterial.byId(json1.get("a").getAsString());
         int number4 = flag2 ? json1.get("b").getAsInt() : -1;
         WaterBoardSolutions.WaterBoardBlock.Type type5 = flag2 ? WaterBoardSolutions.WaterBoardBlock.Type.FLIP : WaterBoardSolutions.WaterBoardBlock.Type.byId(json1.get("b").getAsString());
         return new WaterBoardSolutions.WaterBoardBlock(number4, type23, type5);
      }
   }

   public class WaterBoardBlock {
      private final int field1;
      private final WaterBoardSolutions.WaterBoardMaterial field2;
      private final WaterBoardSolutions.WaterBoardBlock.Type field3;

      public WaterBoardBlock(int number1, WaterBoardSolutions.WaterBoardMaterial type22, WaterBoardSolutions.WaterBoardBlock.Type type3) {
         this.field1 = number1;
         this.field2 = type22;
         this.field3 = type3;
      }

      public int method1() {
         return this.field1;
      }

      public WaterBoardSolutions.WaterBoardMaterial method2() {
         return this.field2;
      }

      public WaterBoardSolutions.WaterBoardBlock.Type method3() {
         return this.field3;
      }

      public enum Type {
         FLIP("FLIP"),
         UP("UP"),
         DOWN("DOWN");

         private final String id;

         private static WaterBoardSolutions.WaterBoardBlock.Type byId(String text0) {
            for (WaterBoardSolutions.WaterBoardBlock.Type type4 : values()) {
               if (type4.id.equals(text0)) {
                  return type4;
               }
            }

            return null;
         }

         @Generated
         public String getId() {
            return this.id;
         }

         @Generated
         Type(String text3) {
            this.id = text3;
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

      private static WaterBoardSolutions.Type bySerializedName(String text0) {
         for (WaterBoardSolutions.Type type4 : values()) {
            if (type4.serializedName.equals(text0)) {
               return type4;
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
      Type(Component component3, Vector3i vector3i4, String text5) {
         this.pretty = component3;
         this.relativePos = vector3i4;
         this.serializedName = text5;
      }
   }

   public enum WaterBoardMaterial {
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

      public static WaterBoardSolutions.WaterBoardMaterial byId(String text0) {
         for (WaterBoardSolutions.WaterBoardMaterial type24 : values()) {
            if (type24.id.equals(text0)) {
               return type24;
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
      WaterBoardMaterial(Bridge3_23 bridge3_233, Vector3i vector3i4, String text5) {
         this.block = bridge3_233;
         this.relativePos = vector3i4;
         this.id = text5;
      }
   }
}
