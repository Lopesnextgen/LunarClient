package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;

public enum HologramsType4 {
   PURPLE(
      AxisAlignedBBBridge.method2(53.0, 15.0, 122.0, 59.0, 22.0, 128.0),
      Bridge.method8().method4(56, 21, 130),
      Bridge.method8().method4(56, 9, 132),
      Bridge.method8().method4(54, 7, 41),
      NamedTextColor.DARK_PURPLE,
      "Purple",
      "Soul"
   ),
   BLUE(
      AxisAlignedBBBridge.method2(82.0, 15.0, 91.0, 88.0, 22.0, 97.0),
      Bridge.method8().method4(89, 21, 94),
      Bridge.method8().method4(91, 7, 94),
      Bridge.method8().method4(59, 7, 44),
      NamedTextColor.AQUA,
      "Blue",
      "Ice"
   ),
   RED(
      AxisAlignedBBBridge.method2(24.0, 15.0, 56.0, 30.0, 22.0, 62.0),
      Bridge.method8().method4(22, 21, 59),
      Bridge.method8().method4(20, 7, 59),
      Bridge.method8().method4(51, 7, 42),
      NamedTextColor.RED,
      "Red",
      "Power"
   ),
   GREEN(
      AxisAlignedBBBridge.method2(23.0, 15.0, 91.0, 29.0, 22.0, 97.0),
      Bridge.method8().method4(22, 21, 94),
      Bridge.method8().method4(20, 7, 94),
      Bridge.method8().method4(49, 7, 44),
      NamedTextColor.GREEN,
      "Green",
      "Apex"
   ),
   ORANGE(
      AxisAlignedBBBridge.method2(82.0, 15.0, 53.0, 88.0, 22.0, 59.0),
      Bridge.method8().method4(90, 21, 56),
      Bridge.method8().method4(92, 7, 56),
      Bridge.method8().method4(57, 7, 42),
      NamedTextColor.GOLD,
      "Orange",
      "Flame"
   );

   private final AxisAlignedBBBridge particleBounds;
   private final Vector3iBridge deadIndicator;
   private final Vector3iBridge relicHomeLocation;
   private final Vector3iBridge relicDestinationLocation;
   private final NamedTextColor textColor;
   private final String name;
   private final String altName;

   public static HologramsType4 getByAltName(String var0) {
      for (HologramsType4 var4 : values()) {
         if (var4.altName.equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   public static HologramsType4 getByEntity(BridgeExtension var0) {
      if (var0 instanceof ArmorStandBridge var1) {
         ItemStackBridge var2 = var1.bridge$getHelmet();
         return var2 == null ? null : getByItemStack(var2);
      } else {
         return null;
      }
   }

   public static HologramsType4 getByItemStack(ItemStackBridge var0) {
      String var1 = Gui3.method2(var0);

      return switch (var1) {
         case "PURPLE_KING_RELIC" -> PURPLE;
         case "RED_KING_RELIC" -> RED;
         case "GREEN_KING_RELIC" -> GREEN;
         case "ORANGE_KING_RELIC" -> ORANGE;
         case "BLUE_KING_RELIC" -> BLUE;
         default -> null;
      };
   }

   public static HologramsType4 getByName(String var0) {
      for (HologramsType4 var4 : values()) {
         if (var4.name.equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   @Generated
   public AxisAlignedBBBridge getParticleBounds() {
      return this.particleBounds;
   }

   @Generated
   public Vector3iBridge getDeadIndicator() {
      return this.deadIndicator;
   }

   @Generated
   public Vector3iBridge getRelicHomeLocation() {
      return this.relicHomeLocation;
   }

   @Generated
   public Vector3iBridge getRelicDestinationLocation() {
      return this.relicDestinationLocation;
   }

   @Generated
   public NamedTextColor getTextColor() {
      return this.textColor;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String getAltName() {
      return this.altName;
   }

   @Generated
   HologramsType4(AxisAlignedBBBridge var3, Vector3iBridge var4, Vector3iBridge var5, Vector3iBridge var6, NamedTextColor var7, String var8, String var9) {
      this.particleBounds = var3;
      this.deadIndicator = var4;
      this.relicHomeLocation = var5;
      this.relicDestinationLocation = var6;
      this.textColor = var7;
      this.name = var8;
      this.altName = var9;
   }
}
