package com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates;

import com.moonsworth.lunar.client.framework.feature.mod.Module;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ItemRarity;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import java.util.HashMap;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class SkillLevelCalculator {
   private static final Object2LongOpenHashMap<CoordinatesType> xpCache = new Object2LongOpenHashMap();

   public static int getLevel(double value0, CoordinatesType coordinatesType) {
      IntArrayList intarraylist3 = getXpTable(coordinatesType);
      if (intarraylist3 == null) {
         return -1;
      }

      com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.MaxLevels coordinates4 = Ref.method4()
         .method40()
         .method82()
         .method15()
         .method24();
      if (coordinates4 == null) {
         return -1;
      }

      int number5 = coordinates4.getMaxLevels().getInt(coordinatesType);
      int number6 = 0;

      for (int index7 = 0; index7 < number5 && index7 < intarraylist3.size(); index7++) {
         number6 += intarraylist3.getInt(index7);
         if (value0 < number6) {
            return index7;
         }
      }

      return number5;
   }

   public static double getXpForLevel(int value, double value2, CoordinatesType coordinatesType) {
      IntArrayList intarraylist4 = getXpTable(coordinatesType);
      if (intarraylist4 == null) {
         return -1.0;
      }

      double value5 = 0.0;

      int index7;
      for (index7 = 0; index7 < value && index7 < intarraylist4.size(); index7++) {
         value5 += intarraylist4.getInt(index7);
      }

      return index7 < intarraylist4.size() ? value5 + intarraylist4.getInt(index7) * value2 : value5;
   }

   public static double getCatacombsLevel(double value0) {
      IntArrayList intarraylist2 = Ref.method4().method40().method82().method15().method22().get("catacombs");
      long number3 = 0L;

      for (int index5 = 0; index5 < intarraylist2.size(); index5++) {
         int number6 = intarraylist2.getInt(index5);
         number3 += number6;
         if (value0 < number3) {
            return index5 + (1.0 - (number3 - value0) / number6);
         }
      }

      return ClampUtils.clamp(50.0 + (value0 - number3) / 2.0E8, 0.0, 100.0);
   }

   public static int getPetLevel(double value0, ItemRarity guitype32) {
      IntArrayList intarraylist3 = Ref.method4().method40().method82().method15().method22().get("pet100");

      byte number4 = switch (guitype32) {
         case UNCOMMON -> 6;
         case RARE -> 11;
         case EPIC -> 16;
         case LEGENDARY -> 20;
         case MYTHIC -> 20;
         default -> 0;
      };
      long number5 = 0L;

      for (int index7 = number4; index7 < number4 + 99 && index7 < intarraylist3.size(); index7++) {
         number5 += intarraylist3.getInt(index7);
         if (value0 < number5) {
            return index7 + 1 - number4;
         }
      }

      return 100;
   }

   public static int getPetLevel200(double value0, ItemRarity guitype32) {
      IntArrayList intarraylist3 = Ref.method4().method40().method82().method15().method22().get("pet200");

      byte number4 = switch (guitype32) {
         case UNCOMMON -> 6;
         case RARE -> 11;
         case EPIC -> 16;
         case LEGENDARY -> 20;
         case MYTHIC -> 20;
         default -> 0;
      };
      long number5 = 0L;

      for (int index7 = number4; index7 < number4 + 199 && index7 < intarraylist3.size(); index7++) {
         number5 += intarraylist3.getInt(index7);
         if (value0 < number5) {
            return index7 + 1 - number4;
         }
      }

      return 200;
   }

   public static long getMaxLevelXp(CoordinatesType coordinatestype0) {
      if (field1.containsKey(coordinatestype0)) {
         return field1.getLong(coordinatestype0);
      }

      IntArrayList intarraylist1 = getXpTable(coordinatestype0);
      if (intarraylist1 == null) {
         return -1L;
      }

      com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.MaxLevels coordinates2 = Ref.method4()
         .method40()
         .method82()
         .method15()
         .method24();
      if (coordinates2 == null) {
         return -1L;
      }

      int number3 = coordinates2.getMaxLevels().getInt(coordinatestype0);
      long number4 = 0L;

      for (int index6 = 0; index6 < number3; index6++) {
         number4 += intarraylist1.getInt(index6);
      }

      field1.put(coordinatestype0, number4);
      return number4;
   }

   @Nullable
   private static IntArrayList getXpTable(CoordinatesType coordinatestype0) {
      Module module1 = Ref.method4().method40().method82().method15();
      HashMap map2 = module1.method22();
      if (map2 == null) {
         return null;
      } else if (coordinatestype0 == CoordinatesType.RUNECRAFTING) {
         return (IntArrayList)map2.get("runecrafting");
      } else {
         return coordinatestype0 == CoordinatesType.SOCIAL ? (IntArrayList)map2.get("social") : (IntArrayList)map2.get("skill");
      }
   }

   @Generated
   private SkillLevelCalculator() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
