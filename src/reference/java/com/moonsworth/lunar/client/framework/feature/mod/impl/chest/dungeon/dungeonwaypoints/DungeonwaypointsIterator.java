package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointRenderMode;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointVisibility;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.util.io.CompressionUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.jetbrains.annotations.Nullable;

public class DungeonwaypointsIterator implements DungeonWaypointDecoder {
   public DungeonwaypointsIterator() {
   }

   @Nullable
   @Override
   public DungeonWaypointCodec.Data decode(String text1) {
      try {
         String text2 = CompressionUtils.method2(text1.replaceAll("\\s", ""));
         JsonElement element3 = JsonParser.parseString(text2);
         if (!element3.isJsonObject()) {
            return null;
         }

         JsonObject json4 = element3.getAsJsonObject();
         Map map5 = this.method4();
         ArrayList list6 = new ArrayList();

         for (Entry entry8 : json4.entrySet()) {
            if (!((JsonElement)entry8.getValue()).isJsonArray()) {
               return null;
            }

            ArrayList list9 = new ArrayList();

            for (JsonElement element11 : ((JsonElement)entry8.getValue()).getAsJsonArray()) {
               com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints12 = element11.isJsonObject()
                  ? this.method1(element11.getAsJsonObject())
                  : null;
               if (dungeonwaypoints12 == null) {
                  return null;
               }

               list9.add(dungeonwaypoints12);
            }

            if (!list9.isEmpty()) {
               String text14 = (String)entry8.getKey();
               list6.add(
                  new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints(
                     (String)map5.get(text14), text14, list9
                  )
               );
            }
         }

         return list6.isEmpty() ? null : new DungeonWaypointCodec.Data(list6, List.of());
      } catch (Exception exception13) {
         return null;
      }
   }

   @Nullable
   private Dungeonwaypoints method1(JsonObject json1) {
      JsonObject json2 = ThreadModuleDump9.getJsonObject(json1, "blockPos", (JsonObject)null);
      if (json2 == null) {
         return null;
      }

      Integer number3 = this.method2(json2, "field_11175", "x");
      Integer number4 = this.method2(json2, "field_11174", "y");
      Integer number5 = this.method2(json2, "field_11173", "z");
      if (number3 != null && number4 != null && number5 != null) {
         int number6 = this.method3(ThreadModuleDump9.getString(json1, "color", ""));
         boolean flag7 = ThreadModuleDump9.getBoolean(json1, "filled", false);
         boolean flag8 = ThreadModuleDump9.getBoolean(json1, "depth", false);
         float value9 = 0.0F;
         float value10 = 0.0F;
         float value11 = 0.0F;
         float value12 = 1.0F;
         float value13 = 1.0F;
         float value14 = 1.0F;
         JsonObject json15 = ThreadModuleDump9.getJsonObject(json1, "aabb", (JsonObject)null);
         if (json15 != null) {
            double value16 = ThreadModuleDump9.getDouble(json15, "minX", 0.0);
            double value18 = ThreadModuleDump9.getDouble(json15, "minY", 0.0);
            double value20 = ThreadModuleDump9.getDouble(json15, "minZ", 0.0);
            value9 = (float)value16;
            value10 = (float)value18;
            value11 = (float)value20;
            value12 = (float)(ThreadModuleDump9.getDouble(json15, "maxX", 1.0) - value16);
            value13 = (float)(ThreadModuleDump9.getDouble(json15, "maxY", 1.0) - value18);
            value14 = (float)(ThreadModuleDump9.getDouble(json15, "maxZ", 1.0) - value20);
         }

         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointStyle dungeonwaypoints422 = new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointStyle(
            flag7 ? WaypointRenderMode.FILL : WaypointRenderMode.WIREFRAME, WaypointVisibility.BOTH, number6, number6, !flag8, value9, value10, value11, value12, value13, value14
         );
         return new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints(
            Bridge.method8().method4(number3, number4, number5), dungeonwaypoints422
         );
      } else {
         return null;
      }
   }

   @Nullable
   private Integer method2(JsonObject json1, String text2, String text3) {
      JsonElement element4 = json1.has(text2) ? json1.get(text2) : json1.get(text3);
      return element4 != null && element4.isJsonPrimitive() && element4.getAsJsonPrimitive().isNumber() ? element4.getAsInt() : null;
   }

   private int method3(String text1) {
      try {
         if (text1.length() == 9 && text1.charAt(0) == '#') {
            int number2 = (int)Long.parseLong(text1.substring(1), 16);
            return number2 >>> 8 | number2 << 24;
         }

         if (text1.length() == 7 && text1.charAt(0) == '#') {
            return ColorUtils.method47(text1);
         }
      } catch (NumberFormatException numberformatexception3) {
      }

      return 1140915968;
   }

   private Map<String, String> method4() {
      HashMap map1 = new HashMap();
      DungeonRoom[] items2 = Ref.method4() == null ? null : Ref.method4().method40().method82().method15().method12();
      if (items2 == null) {
         return map1;
      }

      for (DungeonRoom holograms6 : items2) {
         if (holograms6.communityName() != null) {
            map1.put(holograms6.communityName(), holograms6.getBlcID());
         }
      }

      return map1;
   }
}
