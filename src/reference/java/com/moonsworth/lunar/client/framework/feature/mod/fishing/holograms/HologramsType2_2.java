package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;

public enum HologramsType2_2 {
   HEALER(Bridge.method28().method9(), "Healer", NamedTextColor.LIGHT_PURPLE),
   ARCHER(Bridge.method28().method8(), "Archer", NamedTextColor.GREEN),
   TANK(Bridge.method28().method13(), "Tank", NamedTextColor.GRAY),
   MAGE(Bridge.method28().method7(), "Mage", NamedTextColor.AQUA),
   BERSERK(Bridge.method28().method16(), "Berserk", NamedTextColor.RED);

   private final char firstLetter = this.name().charAt(0);
   private final ItemStackBridge item;
   private final String chatDisplayName;
   private final NamedTextColor color;

   HologramsType2_2(Bridge6_4 var3, String var4, NamedTextColor var5) {
      this.item = Bridge.method8().method38(var3);
      this.chatDisplayName = var4;
      this.color = var5;
   }

   public static HologramsType2_2 fromFirstLetter(char var0) {
      for (HologramsType2_2 var4 : values()) {
         if (var4.firstLetter == var0) {
            return var4;
         }
      }

      Inventorymod2.method5(new IllegalStateException("Unrecognized first letter: " + var0), "DungeonClass");
      return null;
   }

   public static HologramsType2_2 fromDisplayName(String var0) {
      for (HologramsType2_2 var4 : values()) {
         if (var4.chatDisplayName.equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   public char getFirstLetter() {
      return this.chatDisplayName.charAt(0);
   }

   public static Set<String> ids() {
      HashSet var0 = new HashSet();

      for (HologramsType2_2 var4 : values()) {
         var0.add(var4.chatDisplayName);
      }

      return var0;
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
