package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public enum HologramsType9 {
   S_PLUS(NamedTextColor.GOLD, true, "S+"),
   S(NamedTextColor.YELLOW, false, "S"),
   A(NamedTextColor.DARK_PURPLE, false, "A"),
   B(NamedTextColor.GREEN, false, "B"),
   C(NamedTextColor.BLUE, false, "C"),
   D(NamedTextColor.RED, false, "D");

   private final NamedTextColor color;
   private final boolean bold;
   private final String name;

   public static HologramsType9 valueOf(int var0) {
      if (var0 >= 300) {
         return S_PLUS;
      } else if (var0 >= 270) {
         return S;
      } else if (var0 >= 230) {
         return A;
      } else if (var0 >= 160) {
         return B;
      } else {
         return var0 >= 100 ? C : D;
      }
   }

   public static TextComponent toComponent(int var0) {
      HologramsType9 var1 = valueOf(var0);
      return var1.bold
         ? (TextComponent)((TextComponent)Component.text("Score: ")
               .append(((TextComponent)Component.text(var0 + " ").color(var1.color)).decorate(TextDecoration.BOLD)))
            .append(
               ((TextComponent)((TextComponent)((TextComponent)Component.text("(").color(NamedTextColor.GRAY)).decorate(TextDecoration.BOLD))
                     .append(((TextComponent)Component.text(var1.getName()).color(var1.color)).decorate(TextDecoration.BOLD)))
                  .append(((TextComponent)Component.text(")").color(NamedTextColor.GRAY)).decorate(TextDecoration.BOLD))
            )
         : (TextComponent)((TextComponent)Component.text("Score: ").append(Component.text(var0 + " ").color(var1.color)))
            .append(
               ((TextComponent)((TextComponent)Component.text("(").color(NamedTextColor.GRAY)).append(Component.text(var1.getName()).color(var1.color)))
                  .append(Component.text(")").color(NamedTextColor.GRAY))
            );
   }

   @Generated
   public NamedTextColor getColor() {
      return this.color;
   }

   @Generated
   public boolean isBold() {
      return this.bold;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   HologramsType9(NamedTextColor namedTextColor, boolean flag, String text) {
      this.color = namedTextColor;
      this.bold = flag;
      this.name = text;
   }
}
