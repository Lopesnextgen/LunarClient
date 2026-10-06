package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ClickableTextExtension;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.config.option.NamedColorOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEventSource;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public final class Click12 {
   public static Style getStyle(EnumOption<NamedColorOption> var0) {
      AdventureChatFormatting var1 = ((NamedColorOption)var0.get()).getColor();
      return Style.style(var1 == AdventureChatFormatting.RESET ? NamedTextColor.WHITE : var1.getAdventureColor());
   }

   public static Click12.Data builder() {
      return new Click12.Data();
   }

   public static TextColor getProgressColor(double var0, double var2) {
      double var4 = var2 <= 0.0 ? 0.0 : var0 / var2;
      if (var4 > 0.5) {
         return NamedTextColor.GREEN;
      } else {
         return var4 > 0.25 ? NamedTextColor.YELLOW : NamedTextColor.RED;
      }
   }

   public static TextComponent createItemLine(String var0, int var1) {
      return createItemLine(var0, var1, NamedTextColor.GOLD, NamedTextColor.WHITE);
   }

   public static TextComponent createItemLine(String var0, int var1, TextColor var2, TextColor textColor) {
      return (TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text("- ").color(NamedTextColor.GRAY))
               .append(Component.text(var0).color(var2)))
            .append(Component.text(" x").color(NamedTextColor.GRAY)))
         .append(Component.text(var1).color(textColor));
   }

   public static ClickableTextExtension createClickableText(String var0) {
      return createClickableText(Component.text(var0));
   }

   public static ClickableTextExtension createClickableText(Component var0) {
      return (ClickableTextExtension)Bridge.method8().method89(var0);
   }

   @Generated
   private Click12() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static class Data {
      private TextComponent title = Component.empty();
      private TextComponent value = Component.empty();
      private TextColor titleColor;
      private TextColor valueColor;
      private TextColor separatorColor;
      private boolean showTitle = true;

      public Data() {
         Skyblock var1 = ThreadModuleDump63.method4().method40().method82();
         this.titleColor = TextColor.color(var1.method21().method14(0.0F));
         this.valueColor = TextColor.color(var1.method23().method14(0.0F));
         this.separatorColor = TextColor.color(var1.method22().method14(0.0F));
      }

      public Click12.Data getStyle(TextComponent var1) {
         this.title = var1;
         return this;
      }

      public Click12.Data builder(String var1) {
         this.title = Component.text(var1, this.titleColor);
         return this;
      }

      public Click12.Data getProgressColor(TextComponent var1) {
         this.value = var1;
         return this;
      }

      public Click12.Data createItemLine(String var1) {
         this.value = Component.text(var1, this.valueColor);
         return this;
      }

      public Click12.Data createItemLine(TextColor var1) {
         this.titleColor = var1;
         this.title = (TextComponent)this.title.color(var1);
         return this;
      }

      public Click12.Data createClickableText(int var1) {
         return this.createItemLine(TextColor.color(var1));
      }

      public Click12.Data createClickableText(TextColor var1) {
         this.valueColor = var1;
         this.value = (TextComponent)this.value.color(var1);
         return this;
      }

      public Click12.Data valueColor(int var1) {
         return this.createClickableText(TextColor.color(var1));
      }

      public Click12.Data separatorColor(TextColor var1) {
         this.separatorColor = var1;
         return this;
      }

      public Click12.Data separatorColor(int var1) {
         return this.color(TextColor.color(var1));
      }

      public Click12.Data color(TextColor var1) {
         this.createItemLine(var1);
         this.createClickableText(var1);
         this.separatorColor(var1);
         return this;
      }

      public Click12.Data showTitle(boolean var1) {
         this.showTitle = var1;
         return this;
      }

      public Click12.Data titleDecoration(TextDecoration... var1) {
         this.title = (TextComponent)this.title.decorate(var1);
         return this;
      }

      public Click12.Data valueDecoration(TextDecoration... var1) {
         this.value = (TextComponent)this.value.decorate(var1);
         return this;
      }

      public Click12.Data titleHover(HoverEventSource<?> var1) {
         this.title = (TextComponent)this.title.hoverEvent(var1);
         return this;
      }

      public Click12.Data valueHover(HoverEventSource<?> var1) {
         this.value = (TextComponent)this.value.hoverEvent(var1);
         return this;
      }

      public TextComponent build() {
         return this.showTitle ? (TextComponent)((TextComponent)this.title.append(Component.text(": ", this.separatorColor))).append(this.value) : this.value;
      }
   }
}
