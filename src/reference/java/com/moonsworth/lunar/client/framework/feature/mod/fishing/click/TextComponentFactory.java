package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ClickableText;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.config.option.NamedColorOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEventSource;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public final class TextComponentFactory {
   public static Style styleOf(EnumOption<NamedColorOption> lightingextension4970) {
      ChatFormatting horsestatstype81 = ((NamedColorOption)lightingextension4970.get()).getColor();
      return Style.style(horsestatstype81 == ChatFormatting.RESET ? NamedTextColor.WHITE : horsestatstype81.getAdventureColor());
   }

   public static TextComponentFactory.Data builder() {
      return new TextComponentFactory.Data();
   }

   public static TextColor colorForRatio(double value0, double value2) {
      double value4 = value2 <= 0.0 ? 0.0 : value0 / value2;
      if (value4 > 0.5) {
         return NamedTextColor.GREEN;
      } else {
         return value4 > 0.25 ? NamedTextColor.YELLOW : NamedTextColor.RED;
      }
   }

   public static TextComponent itemLine(String text0, int number1) {
      return itemLine(text0, number1, NamedTextColor.GOLD, NamedTextColor.WHITE);
   }

   public static TextComponent itemLine(String text0, int number1, TextColor textcolor2, TextColor textcolor3) {
      return (TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text("- ").color(NamedTextColor.GRAY))
               .append(Component.text(text0).color(textcolor2)))
            .append(Component.text(" x").color(NamedTextColor.GRAY)))
         .append(Component.text(number1).color(textcolor3));
   }

   public static ClickableText clickable(String text0) {
      return clickable(Component.text(text0));
   }

   public static ClickableText clickable(Component component0) {
      return (ClickableText)Bridge.method8().method89(component0);
   }

   @Generated
   private TextComponentFactory() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static class Data {
      private TextComponent field1 = Component.empty();
      private TextComponent field2 = Component.empty();
      private TextColor field3;
      private TextColor field4;
      private TextColor field5;
      private boolean field6 = true;

      public Data() {
         Skyblock skyblock1 = Ref.method4().method40().method82();
         this.field3 = TextColor.color(skyblock1.method21().method14(0.0F));
         this.field4 = TextColor.color(skyblock1.method23().method14(0.0F));
         this.field5 = TextColor.color(skyblock1.method22().method14(0.0F));
      }

      public TextComponentFactory.Data styleOf(TextComponent text1) {
         this.field1 = text1;
         return this;
      }

      public TextComponentFactory.Data builder(String text1) {
         this.field1 = Component.text(text1, this.field3);
         return this;
      }

      public TextComponentFactory.Data colorForRatio(TextComponent text1) {
         this.field2 = text1;
         return this;
      }

      public TextComponentFactory.Data itemLine(String text1) {
         this.field2 = Component.text(text1, this.field4);
         return this;
      }

      public TextComponentFactory.Data itemLine(TextColor textcolor1) {
         this.field3 = textcolor1;
         this.field1 = (TextComponent)this.field1.color(textcolor1);
         return this;
      }

      public TextComponentFactory.Data clickable(int number1) {
         return this.itemLine(TextColor.color(number1));
      }

      public TextComponentFactory.Data clickable(TextColor textcolor1) {
         this.field4 = textcolor1;
         this.field2 = (TextComponent)this.field2.color(textcolor1);
         return this;
      }

      public TextComponentFactory.Data method8(int number1) {
         return this.clickable(TextColor.color(number1));
      }

      public TextComponentFactory.Data method9(TextColor textcolor1) {
         this.field5 = textcolor1;
         return this;
      }

      public TextComponentFactory.Data method10(int number1) {
         return this.method11(TextColor.color(number1));
      }

      public TextComponentFactory.Data method11(TextColor textcolor1) {
         this.itemLine(textcolor1);
         this.clickable(textcolor1);
         this.method9(textcolor1);
         return this;
      }

      public TextComponentFactory.Data method12(boolean flag1) {
         this.field6 = flag1;
         return this;
      }

      public TextComponentFactory.Data method13(TextDecoration... items1) {
         this.field1 = (TextComponent)this.field1.decorate(items1);
         return this;
      }

      public TextComponentFactory.Data method14(TextDecoration... items1) {
         this.field2 = (TextComponent)this.field2.decorate(items1);
         return this;
      }

      public TextComponentFactory.Data method15(HoverEventSource<?> hovereventsource1) {
         this.field1 = (TextComponent)this.field1.hoverEvent(hovereventsource1);
         return this;
      }

      public TextComponentFactory.Data method16(HoverEventSource<?> hovereventsource1) {
         this.field2 = (TextComponent)this.field2.hoverEvent(hovereventsource1);
         return this;
      }

      public TextComponent build() {
         return this.field6 ? (TextComponent)((TextComponent)this.field1.append(Component.text(": ", this.field5))).append(this.field2) : this.field2;
      }
   }
}
