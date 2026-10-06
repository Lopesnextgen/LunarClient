package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.util.ThreadModuleDump40;
import com.moonsworth.lunar.client.util.ThreadModuleDump83;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class Fishing5 {
   private static final Pattern field1 = Pattern.compile("^Rabbit \\w+ - \\[(?<level>\\d{1,3})].*$");
   private final int field2;
   private final boolean field3;

   public String method1() {
      return this.field3 ? this.method2(this.field2) + this.field2 : this.field2 + "";
   }

   private String method2(int var1) {
      if (var1 >= 220) {
         return AdventureChatFormatting.AQUA.toString();
      } else if (var1 >= 200) {
         return AdventureChatFormatting.LIGHT_PURPLE.toString();
      } else if (var1 >= 175) {
         return AdventureChatFormatting.GOLD.toString();
      } else if (var1 >= 125) {
         return AdventureChatFormatting.DARK_PURPLE.toString();
      } else if (var1 >= 75) {
         return AdventureChatFormatting.BLUE.toString();
      } else {
         return var1 >= 10 ? AdventureChatFormatting.GREEN.toString() : AdventureChatFormatting.WHITE.toString();
      }
   }

   public static Optional<Fishing5> method3(ItemStackBridge itemStackBridge) {
      if (itemStackBridge != null && !itemStackBridge.bridge$isEmpty()) {
         String var1 = AdventureChatFormatting.getTextWithoutFormattingCodes(itemStackBridge.bridge$getDisplayName());
         Matcher var2 = field1.matcher(var1);
         if (var2.matches()) {
            int var5 = ThreadModuleDump40.method3(var2.group("level"));
            return Optional.of(new Fishing5(var5, true));
         } else {
            String var3 = var1.substring(var1.lastIndexOf(" ") + 1);
            if (ThreadModuleDump83.isRoman(var3)) {
               int var4 = ThreadModuleDump83.parseRoman(var3);
               return Optional.of(new Fishing5(var4, false));
            } else {
               return Optional.empty();
            }
         }
      } else {
         return Optional.empty();
      }
   }

   @Generated
   public Fishing5(int var1, boolean var2) {
      this.field2 = var1;
      this.field3 = var2;
   }

   @Generated
   public int getLevel() {
      return this.field2;
   }

   @Generated
   public boolean method4() {
      return this.field3;
   }
}
