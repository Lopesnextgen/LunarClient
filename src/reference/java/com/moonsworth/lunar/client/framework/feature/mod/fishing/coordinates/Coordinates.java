package com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates;

import com.moonsworth.lunar.client.framework.feature.mod.Module;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.GuiType3;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import java.util.HashMap;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class Coordinates {
   private static final Object2LongOpenHashMap<CoordinatesType> xpCache = new Object2LongOpenHashMap();

   public static int getLevel(double var0, CoordinatesType var2) {
      IntArrayList var3 = getXpTable(var2);
      if (var3 == null) {
         return -1;
      }

      com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.Coordinates var4 = ThreadModuleDump63.method4()
         .method40()
         .method82()
         .method15()
         .method24();
      if (var4 == null) {
         return -1;
      }

      int var5 = var4.getMaxLevels().getInt(var2);
      int var6 = 0;

      for (int var7 = 0; var7 < var5 && var7 < var3.size(); var7++) {
         var6 += var3.getInt(var7);
         if (var0 < var6) {
            return var7;
         }
      }

      return var5;
   }

   public static double getXpForLevel(int var0, double var1, CoordinatesType var3) {
      IntArrayList var4 = getXpTable(var3);
      if (var4 == null) {
         return -1.0;
      }

      double var5 = 0.0;

      int var7;
      for (var7 = 0; var7 < var0 && var7 < var4.size(); var7++) {
         var5 += var4.getInt(var7);
      }

      return var7 < var4.size() ? var5 + var4.getInt(var7) * var1 : var5;
   }

   public static double getCatacombsLevel(double var0) {
      IntArrayList var2 = ThreadModuleDump63.method4().method40().method82().method15().method22().get("catacombs");
      long var3 = 0L;

      for (int var5 = 0; var5 < var2.size(); var5++) {
         int var6 = var2.getInt(var5);
         var3 += var6;
         if (var0 < var3) {
            return var5 + (1.0 - (var3 - var0) / var6);
         }
      }

      return ClampUtils.clamp(50.0 + (var0 - var3) / 2.0E8, 0.0, 100.0);
   }

   public static int getPetLevel(double var0, GuiType3 var2) {
      IntArrayList var3 = ThreadModuleDump63.method4().method40().method82().method15().method22().get("pet100");

      byte var4 = switch (var2) {
         case UNCOMMON -> 6;
         case RARE -> 11;
         case EPIC -> 16;
         case LEGENDARY -> 20;
         case MYTHIC -> 20;
         default -> 0;
      };
      long var5 = 0L;

      for (int var7 = var4; var7 < var4 + 99 && var7 < var3.size(); var7++) {
         var5 += var3.getInt(var7);
         if (var0 < var5) {
            return var7 + 1 - var4;
         }
      }

      return 100;
   }

   public static int getPetLevel200(double var0, GuiType3 var2) {
      IntArrayList var3 = ThreadModuleDump63.method4().method40().method82().method15().method22().get("pet200");

      byte var4 = switch (var2) {
         case UNCOMMON -> 6;
         case RARE -> 11;
         case EPIC -> 16;
         case LEGENDARY -> 20;
         case MYTHIC -> 20;
         default -> 0;
      };
      long var5 = 0L;

      for (int var7 = var4; var7 < var4 + 199 && var7 < var3.size(); var7++) {
         var5 += var3.getInt(var7);
         if (var0 < var5) {
            return var7 + 1 - var4;
         }
      }

      return 200;
   }

   public static long getMaxLevelXp(CoordinatesType var0) {
      if (field1.containsKey(var0)) {
         return field1.getLong(var0);
      }

      IntArrayList var1 = getXpTable(var0);
      if (var1 == null) {
         return -1L;
      }

      com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.Coordinates var2 = ThreadModuleDump63.method4()
         .method40()
         .method82()
         .method15()
         .method24();
      if (var2 == null) {
         return -1L;
      }

      int var3 = var2.getMaxLevels().getInt(var0);
      long var4 = 0L;

      for (int var6 = 0; var6 < var3; var6++) {
         var4 += var1.getInt(var6);
      }

      field1.put(var0, var4);
      return var4;
   }

   @Nullable
   private static IntArrayList getXpTable(CoordinatesType var0) {
      Module var1 = ThreadModuleDump63.method4().method40().method82().method15();
      HashMap var2 = var1.method22();
      if (var2 == null) {
         return null;
      } else if (var0 == CoordinatesType.RUNECRAFTING) {
         return (IntArrayList)var2.get("runecrafting");
      } else {
         return var0 == CoordinatesType.SOCIAL ? (IntArrayList)var2.get("social") : (IntArrayList)var2.get("skill");
      }
   }

   @Generated
   private Coordinates() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
