package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;

public enum DungeonClass {
   HEALER(Bridge.method28().method9(), "Healer", NamedTextColor.LIGHT_PURPLE),
   ARCHER(Bridge.method28().method8(), "Archer", NamedTextColor.GREEN),
   TANK(Bridge.method28().method13(), "Tank", NamedTextColor.GRAY),
   MAGE(Bridge.method28().method7(), "Mage", NamedTextColor.AQUA),
   BERSERK(Bridge.method28().method16(), "Berserk", NamedTextColor.RED);

   private final char firstLetter = this.name().charAt(0);
   private final ItemStackBridge item;
   private final String chatDisplayName;
   private final NamedTextColor color;

   DungeonClass(ItemBridge bridge6_43, String text4, NamedTextColor namedtextcolor5) {
      this.item = Bridge.method8().method38(bridge6_43);
      this.chatDisplayName = text4;
      this.color = namedtextcolor5;
   }

   public static DungeonClass fromFirstLetter(char character0) {
      for (DungeonClass hologramstype2_24 : values()) {
         if (hologramstype2_24.firstLetter == character0) {
            return hologramstype2_24;
         }
      }

      CrashReporter.method5(new IllegalStateException("Unrecognized first letter: " + character0), "DungeonClass");
      return null;
   }

   public static DungeonClass fromDisplayName(String text0) {
      for (DungeonClass hologramstype2_24 : values()) {
         if (hologramstype2_24.chatDisplayName.equals(text0)) {
            return hologramstype2_24;
         }
      }

      return null;
   }

   public char getFirstLetter() {
      return this.chatDisplayName.charAt(0);
   }

   public static Set<String> ids() {
      HashSet set0 = new HashSet();

      for (DungeonClass hologramstype2_24 : values()) {
         set0.add(hologramstype2_24.chatDisplayName);
      }

      return set0;
   }

   @Generated
   public ItemStackBridge getItem() {
      return this.item;
   }

   @Generated
   public String getChatDisplayName() {
      return this.chatDisplayName;
   }

   @Generated
   public NamedTextColor getColor() {
      return this.color;
   }
}
