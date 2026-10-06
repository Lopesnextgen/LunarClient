package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.itemcounter.Itemcounter2_3;
import lombok.Generated;

public enum HologramsType7 {
   FOUR_BY_FOUR(new HologramsType7.Data(22, 22, 22, 22), 4, 4),
   FOUR_BY_FIVE(new HologramsType7.Data(11, 11, 22, 22), 4, 5),
   FIVE_BY_FIVE(new HologramsType7.Data(11, 11, 11, 11), 5, 5),
   FIVE_BY_SIX(new HologramsType7.Data(5, 7, 16, 16), 5, 6),
   SIX_BY_FIVE(new HologramsType7.Data(16, 16, 5, 7), 6, 5),
   SIX_BY_SIX(new HologramsType7.Data(5, 7, 5, 7), 6, 6);

   private final HologramsType7.Data mapMargins;
   private final int roomWidth;
   private final int roomHeight;
   private final int blockWidth;
   private final int blockHeight;

   HologramsType7(HologramsType7.Data var3, int var4, int var5) {
      this.mapMargins = var3;
      this.roomWidth = var4;
      this.roomHeight = var5;
      this.blockWidth = var4 * 32 - 2;
      this.blockHeight = var5 * 32 - 2;
   }

   public static HologramsType7 getByDungeonMap(Itemcounter2_3 var0) {
      if (var0 == null) {
         return null;
      }

      byte[] var1 = var0.bridge$getColors();
      int var2 = 0;
      int var3 = 0;

      for (int var4 = 0; var4 < var1.length; var4++) {
         if (var2 == 0 && var1[var4] == 30) {
            var2 = var4;
         } else if (var2 != 0 && var1[var4] == 0) {
            var3 = var4 - var2;
            break;
         }
      }

      int var6 = var2 % 128;
      int var5 = var2 / 128;
      if (var3 == 18) {
         if (var6 % 2 == 0) {
            return var5 % 2 == 0 ? FOUR_BY_FOUR : FOUR_BY_FIVE;
         } else {
            return var5 % 2 == 0 ? null : FIVE_BY_FIVE;
         }
      } else if (var3 == 16) {
         if (var6 % 2 == 0) {
            return var5 % 2 == 0 ? null : FIVE_BY_SIX;
         } else {
            return var5 % 2 == 0 ? SIX_BY_FIVE : SIX_BY_SIX;
         }
      } else {
         return null;
      }
   }

   @Generated
   public HologramsType7.Data getMapMargins() {
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

      public Data(int var1, int var2, int var3, int var4) {
         this.top = var1;
         this.field1 = var2;
         this.left = var3;
         this.field2 = var4;
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
