package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.ui.hud.HudComponent;
import com.moonsworth.lunar.client.ui.hud.ItemStackHudComponent;
import com.moonsworth.lunar.client.ui.hud.BackgroundHudComponent;
import com.moonsworth.lunar.client.ui.hud.PaddedHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentStyle;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.files.ValuePair;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public final class WidgetFactory {
   public static HudComponent withBackground(HudComponent mixincore50) {
      return withBackground(mixincore50, arg0x -> {});
   }

   public static HudComponent withBackground(HudComponent mixincore50, Consumer<HudComponentStyle> consumer1) {
      PaddedHudComponent mixincore5impl42 = new PaddedHudComponent(mixincore50).method2(4.0F);
      consumer1.accept(mixincore5impl42);
      return new BackgroundHudComponent(mixincore5impl42);
   }

   public static BackgroundHudComponent withBackground(HudComponent mixincore50, Supplier<Boolean> supplier1) {
      return withBackground(mixincore50, supplier1, arg0x -> {});
   }

   public static BackgroundHudComponent withBackground(HudComponent mixincore50, Supplier<Boolean> supplier1, Consumer<HudComponentStyle> consumer2) {
      PaddedHudComponent mixincore5impl43 = new PaddedHudComponent(mixincore50).method2(4.0F);
      consumer2.accept(mixincore5impl43);
      return new BackgroundHudComponent(mixincore5impl43).method6(supplier1);
   }

   public static ItemStackHudComponent createItem(ItemBridge bridge6_40) {
      ItemStackBridge bridgeextension_41 = Bridge.method8().method38(bridge6_40);
      if (Ref.MC_VERSION >= 22) {
         bridgeextension_41.bridge$setFoil(true);
      }

      return new ItemStackHudComponent(bridgeextension_41);
   }

   public static List<TextComponent> createLore(String text0, int number1, int number2, int number3, List<ValuePair<String, String>> list4) {
      Style style5 = Style.style(TextColor.color(number1 & 16777215), new TextDecoration[]{TextDecoration.BOLD});
      Style style6 = Style.style(TextColor.color(number2 & 16777215));
      Style style7 = Style.style(TextColor.color(number3 & 16777215));
      ArrayList list8 = new ArrayList();
      list8.add((TextComponent)Component.text(text0).style(style5));

      for (ValuePair files6_210 : list4) {
         list8.add(
            (TextComponent)((TextComponent)Component.text((String)files6_210.field1 + ": ").style(style6)).append(Component.text((String)files6_210.field2).style(style7))
         );
      }

      return list8;
   }

   @Generated
   private WidgetFactory() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
