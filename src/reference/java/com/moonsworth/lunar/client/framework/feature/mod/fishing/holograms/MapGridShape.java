package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.world.MapDataBridge;
import lombok.Generated;

public enum MapGridShape {
   FOUR_BY_FOUR(new MapGridShape.Data(22, 22, 22, 22), 4, 4),
   FOUR_BY_FIVE(new MapGridShape.Data(11, 11, 22, 22), 4, 5),
   FIVE_BY_FIVE(new MapGridShape.Data(11, 11, 11, 11), 5, 5),
   FIVE_BY_SIX(new MapGridShape.Data(5, 7, 16, 16), 5, 6),
   SIX_BY_FIVE(new MapGridShape.Data(16, 16, 5, 7), 6, 5),
   SIX_BY_SIX(new MapGridShape.Data(5, 7, 5, 7), 6, 6);

   private final MapGridShape.Data mapMargins;
   private final int roomWidth;
   private final int roomHeight;
   private final int blockWidth;
   private final int blockHeight;

   MapGridShape(MapGridShape.Data data3, int number4, int number5) {
      this.mapMargins = data3;
      this.roomWidth = number4;
      this.roomHeight = number5;
      this.blockWidth = number4 * 32 - 2;
      this.blockHeight = number5 * 32 - 2;
   }

   public static MapGridShape getByDungeonMap(MapDataBridge itemcounter2_30) {
      if (itemcounter2_30 == null) {
         return null;
      }

      byte[] items1 = itemcounter2_30.bridge$getColors();
      int number2 = 0;
      int number3 = 0;

      for (int index4 = 0; index4 < items1.length; index4++) {
         if (number2 == 0 && items1[index4] == 30) {
            number2 = index4;
         } else if (number2 != 0 && items1[index4] == 0) {
            number3 = index4 - number2;
            break;
         }
      }

      int number6 = number2 % 128;
      int number5 = number2 / 128;
      if (number3 == 18) {
         if (number6 % 2 == 0) {
            return number5 % 2 == 0 ? FOUR_BY_FOUR : FOUR_BY_FIVE;
         } else {
            return number5 % 2 == 0 ? null : FIVE_BY_FIVE;
         }
      } else if (number3 == 16) {
         if (number6 % 2 == 0) {
            return number5 % 2 == 0 ? null : FIVE_BY_SIX;
         } else {
            return number5 % 2 == 0 ? SIX_BY_FIVE : SIX_BY_SIX;
         }
      } else {
         return null;
      }
   }

   @Generated
   public MapGridShape.Data getMapMargins() {
      return this.mapMargins;
   }

   @Generated
   public int getRoomWidth() {
      return this.roomWidth;
   }

   @Generated
   public int getRoomHeight() {
      return this.roomHeight;
   }

   @Generated
   public int getBlockWidth() {
      return this.blockWidth;
   }

   @Generated
   public int getBlockHeight() {
      return this.blockHeight;
   }

   public class Data {
      private final int top;
      private final int field1;
      private final int left;
      private final int field2;

      public Data(int number1, int number2, int number3, int number4) {
         this.top = number1;
         this.field1 = number2;
         this.left = number3;
         this.field2 = number4;
      }

      public int method1() {
         return this.top;
      }

      public int method2() {
         return this.field1;
      }

      public int method3() {
         return this.left;
      }

      public int method4() {
         return this.field2;
      }
   }
}
