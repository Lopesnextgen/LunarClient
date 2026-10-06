package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public enum ScoreGrade {
   S_PLUS(NamedTextColor.GOLD, true, "S+"),
   S(NamedTextColor.YELLOW, false, "S"),
   A(NamedTextColor.DARK_PURPLE, false, "A"),
   B(NamedTextColor.GREEN, false, "B"),
   C(NamedTextColor.BLUE, false, "C"),
   D(NamedTextColor.RED, false, "D");

   private final NamedTextColor color;
   private final boolean bold;
   private final String name;

   public static ScoreGrade valueOf(int number0) {
      if (number0 >= 300) {
         return S_PLUS;
      } else if (number0 >= 270) {
         return S;
      } else if (number0 >= 230) {
         return A;
      } else if (number0 >= 160) {
         return B;
      } else {
         return number0 >= 100 ? C : D;
      }
   }

   public static TextComponent toComponent(int number0) {
      ScoreGrade hologramstype91 = valueOf(number0);
      return hologramstype91.bold
         ? (TextComponent)((TextComponent)Component.text("Score: ")
               .append(((TextComponent)Component.text(number0 + " ").color(hologramstype91.color)).decorate(TextDecoration.BOLD)))
            .append(
               ((TextComponent)((TextComponent)((TextComponent)Component.text("(").color(NamedTextColor.GRAY)).decorate(TextDecoration.BOLD))
                     .append(((TextComponent)Component.text(hologramstype91.getName()).color(hologramstype91.color)).decorate(TextDecoration.BOLD)))
                  .append(((TextComponent)Component.text(")").color(NamedTextColor.GRAY)).decorate(TextDecoration.BOLD))
            )
         : (TextComponent)((TextComponent)Component.text("Score: ").append(Component.text(number0 + " ").color(hologramstype91.color)))
            .append(
               ((TextComponent)((TextComponent)Component.text("(").color(NamedTextColor.GRAY)).append(Component.text(hologramstype91.getName()).color(hologramstype91.color)))
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
   ScoreGrade(NamedTextColor namedtextcolor3, boolean flag, String text) {
      this.color = namedtextcolor3;
      this.bold = flag;
      this.name = text;
   }
}
