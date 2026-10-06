package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;

public enum KingRelic {
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
   private final Vec3iBridge deadIndicator;
   private final Vec3iBridge relicHomeLocation;
   private final Vec3iBridge relicDestinationLocation;
   private final NamedTextColor textColor;
   private final String name;
   private final String altName;

   public static KingRelic getByAltName(String text0) {
      for (KingRelic hologramstype44 : values()) {
         if (hologramstype44.altName.equals(text0)) {
            return hologramstype44;
         }
      }

      return null;
   }

   public static KingRelic getByEntity(BridgeExtension bridge) {
      if (bridge instanceof EntityArmorStandBridge bridgeextension_21) {
         ItemStackBridge bridgeextension_42 = bridgeextension_21.bridge$getHelmet();
         return bridgeextension_42 == null ? null : getByItemStack(bridgeextension_42);
      } else {
         return null;
      }
   }

   public static KingRelic getByItemStack(ItemStackBridge bridgeextension_40) {
      String text1 = SkyblockItemUtil.method2(bridgeextension_40);

      return switch (text1) {
         case "PURPLE_KING_RELIC" -> PURPLE;
         case "RED_KING_RELIC" -> RED;
         case "GREEN_KING_RELIC" -> GREEN;
         case "ORANGE_KING_RELIC" -> ORANGE;
         case "BLUE_KING_RELIC" -> BLUE;
         default -> null;
      };
   }

   public static KingRelic getByName(String text0) {
      for (KingRelic hologramstype44 : values()) {
         if (hologramstype44.name.equals(text0)) {
            return hologramstype44;
         }
      }

      return null;
   }

   @Generated
   public AxisAlignedBBBridge getParticleBounds() {
      return this.particleBounds;
   }

   @Generated
   public Vec3iBridge getDeadIndicator() {
      return this.deadIndicator;
   }

   @Generated
   public Vec3iBridge getRelicHomeLocation() {
      return this.relicHomeLocation;
   }

   @Generated
   public Vec3iBridge getRelicDestinationLocation() {
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
   KingRelic(AxisAlignedBBBridge horsestats123, Vec3iBridge horsestats204, Vec3iBridge horsestats205, Vec3iBridge horsestats206, NamedTextColor namedtextcolor7, String text, String text2) {
      this.particleBounds = horsestats123;
      this.deadIndicator = horsestats204;
      this.relicHomeLocation = horsestats205;
      this.relicDestinationLocation = horsestats206;
      this.textColor = namedtextcolor7;
      this.name = text;
      this.altName = text2;
   }
}
