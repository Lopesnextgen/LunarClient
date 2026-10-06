package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public enum HudAnchor implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   TOP_LEFT("topLeft", HudPlacement.LEFT, HudPlacement.TOP),
   TOP_CENTER("topCenter", HudPlacement.MIDDLE, HudPlacement.TOP),
   TOP_RIGHT("topRight", HudPlacement.RIGHT, HudPlacement.TOP),
   MIDDLE_LEFT("middleLeft", HudPlacement.LEFT, HudPlacement.MIDDLE),
   MIDDLE_CENTER("middleCenter", HudPlacement.MIDDLE, HudPlacement.MIDDLE),
   MIDDLE_RIGHT("middleRight", HudPlacement.RIGHT, HudPlacement.MIDDLE),
   BOTTOM_LEFT("bottomLeft", HudPlacement.LEFT, HudPlacement.BOTTOM),
   BOTTOM_CENTER_L("bottomCenterLeft", HudPlacement.RIGHT, HudPlacement.BOTTOM),
   BOTTOM_CENTER_R("bottomCenterRight", HudPlacement.LEFT, HudPlacement.BOTTOM),
   BOTTOM_RIGHT("bottomRight", HudPlacement.RIGHT, HudPlacement.BOTTOM);

   public static final float PAD = 2.0F;
   private static final float BOTTOM_CENTER_REACH = 30.0F;
   private final String id;
   private final HudPlacement horizontal;
   private final HudPlacement vertical;

   @Override
   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.id;
   }

   public static HudAnchor fromId(String var0) {
      for (HudAnchor var4 : values()) {
         if (var0.equals(var4.id)) {
            return var4;
         }
      }

      return null;
   }

   public static HudAnchor getMousePosition(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var0) {
      return getMousePosition(var0, MarkerModel.method1().method5());
   }

   public static HudAnchor getMousePosition(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var0, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var1) {
      double var2 = var0.HHHCHORHIHRCOHIOICICICHCRRICCI();
      double var4 = var0.IHRCCHHROHIRCOOOHRRIHOORRHIOHO();
      double var6 = var1.HHHCHORHIHRCOHIOICICICHCRRICCI();
      double var8 = var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO();
      if (var2 < var6 / 3.0 && var4 < var8 / 3.0) {
         return TOP_LEFT;
      } else if (var2 > var6 / 3.0 * 2.0 && var4 < var8 / 3.0) {
         return TOP_RIGHT;
      } else if (var4 < var8 / 3.0) {
         return TOP_CENTER;
      } else if (var2 < var6 / 3.0 && var4 < var8 / 3.0 * 2.0) {
         return MIDDLE_LEFT;
      } else if (var2 > var6 / 3.0 * 2.0 && var4 < var8 / 3.0 * 2.0) {
         return MIDDLE_RIGHT;
      } else if (var4 < var8 / 3.0 * 2.0) {
         return MIDDLE_CENTER;
      } else if (var2 < var6 / 3.0) {
         return BOTTOM_LEFT;
      } else if (var2 < var6 / 3.0 * 2.0) {
         return var2 > var6 / 3.0 + var6 / 6.0 ? BOTTOM_CENTER_R : BOTTOM_CENTER_L;
      } else {
         return BOTTOM_RIGHT;
      }
   }

   public static double anchorOriginX(HudAnchor var0, double var1, double var3) {
      return switch (var0) {
         case TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT -> 2.0;
         case TOP_CENTER, MIDDLE_CENTER -> var1 / 2.0 - var3 / 2.0;
         case BOTTOM_CENTER_L -> var1 / 2.0 - var3 + 30.0;
         case BOTTOM_CENTER_R -> var1 / 2.0 - 30.0;
         case TOP_RIGHT, MIDDLE_RIGHT, BOTTOM_RIGHT -> var1 - var3 - 2.0;
      };
   }

   public static double scalePivotX(HudAnchor var0, double var1, double var3, double var5) {
      return var1 - edgeRateX(var0, var3, false) * var5;
   }

   public static double scalePivotY(HudAnchor var0, double var1, double var3, double var5) {
      return var1 - edgeRateY(var0, var3, false) * var5;
   }

   private static double edgeRateX(HudAnchor var0, double var1, boolean var3) {
      double var4 = switch (var0) {
         case TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT -> 2.0;
         case TOP_CENTER, MIDDLE_CENTER -> -var1 / 2.0;
         case BOTTOM_CENTER_L -> 30.0 - var1;
         case BOTTOM_CENTER_R -> -30.0;
         case TOP_RIGHT, MIDDLE_RIGHT, BOTTOM_RIGHT -> -var1 - 2.0;
      };
      return var3 ? var4 + var1 : var4;
   }

   private static double edgeRateY(HudAnchor var0, double var1, boolean var3) {
      double var4 = switch (var0) {
         case TOP_LEFT, TOP_CENTER, TOP_RIGHT -> 2.0;
         case MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT -> -var1 / 2.0;
         case BOTTOM_LEFT, BOTTOM_CENTER_L, BOTTOM_CENTER_R, BOTTOM_RIGHT -> -var1 - 2.0;
      };
      return var3 ? var4 + var1 : var4;
   }

   public static double anchorOriginY(HudAnchor var0, double var1, double var3) {
      return switch (var0) {
         case TOP_LEFT, TOP_CENTER, TOP_RIGHT -> 2.0;
         case MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT -> var1 / 2.0 - var3 / 2.0;
         case BOTTOM_LEFT, BOTTOM_CENTER_L, BOTTOM_CENTER_R, BOTTOM_RIGHT -> var1 - var3 - 2.0;
      };
   }

   @Generated
   HudAnchor(String var3, HudPlacement var4, HudPlacement var5) {
      this.id = var3;
      this.horizontal = var4;
      this.vertical = var5;
   }

   @Generated
   public HudPlacement getHorizontal() {
      return this.horizontal;
   }

   @Generated
   public HudPlacement getVertical() {
      return this.vertical;
   }
}
