package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.ui.hud.MixinCore5;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Handler4;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Impl2;
import com.moonsworth.lunar.client.ui.hud.PaddedHudComponent;
import com.moonsworth.lunar.client.ui.hud.MixinCore7;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.files.Files6_2;
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

public final class Click17 {
   public static MixinCore5 withPadding(MixinCore5 var0) {
      return withPadding(var0, var0x -> {});
   }

   public static MixinCore5 withPadding(MixinCore5 var0, Consumer<MixinCore7> var1) {
      PaddedHudComponent var2 = new PaddedHudComponent(var0).method2(4.0F);
      var1.accept(var2);
      return new MixinCore5Impl2(var2);
   }

   public static MixinCore5Impl2 withBackground(MixinCore5 var0, Supplier<Boolean> var1) {
      return withBackground(var0, var1, var0x -> {});
   }

   public static MixinCore5Impl2 withBackground(MixinCore5 var0, Supplier<Boolean> var1, Consumer<MixinCore7> var2) {
      PaddedHudComponent var3 = new PaddedHudComponent(var0).method2(4.0F);
      var2.accept(var3);
      return new MixinCore5Impl2(var3).method6(var1);
   }

   public static MixinCore5Handler4 createItemComponent(Bridge6_4 var0) {
      ItemStackBridge var1 = Bridge.method8().method38(var0);
      if (ThreadModuleDump63.MC_VERSION >= 22) {
         var1.bridge$setFoil(true);
      }

      return new MixinCore5Handler4(var1);
   }

   public static List<TextComponent> createLoreLines(String var0, int var1, int var2, int var3, List<Files6_2<String, String>> list) {
      Style var5 = Style.style(TextColor.color(var1 & 16777215), new TextDecoration[]{TextDecoration.BOLD});
      Style var6 = Style.style(TextColor.color(var2 & 16777215));
      Style var7 = Style.style(TextColor.color(var3 & 16777215));
      ArrayList var8 = new ArrayList();
      var8.add((TextComponent)Component.text(var0).style(var5));

      for (Files6_2 var10 : list) {
         var8.add(
            (TextComponent)((TextComponent)Component.text((String)var10.field1 + ": ").style(var6)).append(Component.text((String)var10.field2).style(var7))
         );
      }

      return var8;
   }

   @Generated
   private Click17() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
