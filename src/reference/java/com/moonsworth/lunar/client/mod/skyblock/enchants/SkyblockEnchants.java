package com.moonsworth.lunar.client.mod.skyblock.enchants;

import com.lunarclient.adventure.transform.ComponentTransform;
import com.lunarclient.adventure.transform.ComponentTransformer;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper$Extension3;
import com.moonsworth.lunar.bridge.ClickableTextExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui;
import com.moonsworth.lunar.client.event.render.TooltipRenderEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump40;
import com.moonsworth.lunar.client.util.ThreadModuleDump83;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockEnchants extends AbstractFeature {
   public static final Pattern field8 = Pattern.compile("(?<= )(?<numeral>[IVXLCDM]+)(?=[^\\w\\.]|$)");
   private final ToggleOption field9 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("skyblockReplaceRoman")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("skyblockReplaceOneRoman")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("skyblockEnchantColors")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("skyblockHideVanillaEnchants")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field13 = (ColorOption)((ColorOption.Data)OptionFactory.method8("skyblockNormalColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0xFF000000 | NamedTextColor.BLUE.value()))
      .method31();
   private final ColorOption field14 = (ColorOption)((ColorOption.Data)OptionFactory.method8("skyblockMaxColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0xFF000000 | NamedTextColor.GOLD.value()))
      .method31();
   private final ColorOption field15 = (ColorOption)((ColorOption.Data)OptionFactory.method8("skyblockHypermaxColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0xFF000000 | NamedTextColor.AQUA.value()))
      .method14()
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("skyblockHypermaxBold")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private int line = 0;
   private final ComponentTransformer field17 = ComponentTransformer.of(field8, ComponentTransform.functor((var1x, var2) -> {
      if (var1x == null) {
         return var2;
      }

      Component var3 = var2.asComponent();
      if (var3.hasDecoration(TextDecoration.OBFUSCATED)) {
         return var2;
      }

      String var4 = var1x.group(0);
      if (!ThreadModuleDump83.isRoman(var4)) {
         return var2;
      }

      int var5 = ThreadModuleDump83.parseRoman(var4);
      return !this.field10.get() && var5 == 1 ? var2 : Component.text(var5 + "").toBuilder();
   }));
   private final ComponentTransformer field18 = ComponentTransformer.of(Gui.field6, ComponentTransform.functor((var0, var1x) -> {
      if (var0 == null) {
         return var1x;
      }

      Gui var2 = Gui.method1(var0.group(1).trim());
      return var2 == null ? var1x : var1x.style(Style.style(NamedTextColor.BLUE));
   }));
   private final ComponentTransformer field19 = ComponentTransformer.of(Gui.field6, ComponentTransform.functor((var1x, var2) -> {
      if (var1x == null) {
         return var2;
      }

      Gui var3 = Gui.method1(var1x.group(1).trim());
      if (var3 == null) {
         return var2;
      }

      if (var3.method2()) {
         return var2.color(NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.BOLD);
      }

      String var4 = var1x.group(2);
      int var5 = ThreadModuleDump40.method5(var4);
      ColorOption var6 = this.method4(var3, var5);
      var2 = this.method1(var1x, var2, var6);
      if (var5 >= var3.method4() && this.field16.get()) {
         var2.decorate(TextDecoration.BOLD);
      }

      return var2;
   }));

   private ComponentBuilder<?, ?> method1(MatchResult var1, ComponentBuilder<?, ?> var2, ColorOption var3) {
      if (var3 == null) {
         return var2;
      } else {
         return var3.method19().get() && var3.method9()
            ? this.method5(var1.group(0), var3, var1.start() + this.line)
            : var2.color(TextColor.color(var3.method13()));
      }
   }

   public SkyblockEnchants(Skyblock var1) {
      super(true);
      this.method4(Framework.field16, Framework4.method3(var1));
      this.method4(Framework.field17, Framework2.method2(SettingsPage.ITEMS));
      this.method4(Framework.field19, Framework11.method1(this, Click3::hasIsland));
      this.handle(TooltipRenderEvent.TooltipPreRenderEvent.class, this::method3);
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field9, var1xx -> var1xx.method9(new ClientOption[]{this.field10}));
            var1x.method9(new ClientOption[]{this.field12, this.field11});
            var1x.method9(new ClientOption[]{this.field13, this.field14, this.field15, this.field16})
               .method3(() -> !this.field11.get());
         }
      );
   }

   private void method3(TooltipRenderEvent.TooltipPreRenderEvent var1) {
      ItemStackBridge var2 = (ItemStackBridge)var1.method1().orElse(null);
      if (!Gui3.method29(var2).isEmpty()) {
         List var3 = var1.method3();

         for (int var4 = 0; var4 < var3.size(); var4++) {
            this.line = var4 * 5;
            if (var3.get(var4) instanceof MixinHelper$Extension3 var5) {
               Component var8 = var5.bridge$getComponent();
               Component var7 = var8;
               if (this.field9.get()) {
                  var7 = this.field17.transform(var7).compact();
               }

               if (this.field11.get()) {
                  var7 = this.field18.transform(var7).compact();
                  var7 = this.field19.transform(var7).compact();
               }

               if (!var8.equals(var7)) {
                  var3.set(var4, (ClickableTextExtension)Bridge.method8().method89(var7));
               }
            }
         }

         var1.method2(var3);
      }
   }

   @Nullable
   private ColorOption method4(Gui var1, int var2) {
      if (var2 < var1.method3()) {
         return this.field13;
      } else {
         return var2 < var1.method4() ? this.field14 : this.field15;
      }
   }

   private ComponentBuilder<?, ?> method5(String var1, ColorOption var2, int var3) {
      ComponentBuilder var4 = Component.empty().toBuilder();
      int var5 = var3;

      for (char var9 : var1.toCharArray()) {
         var4.append(Component.text(var9).color(TextColor.color(var2.method14(var5 * 10))));
         var5++;
      }

      return var4;
   }

   @Override
   public String getId() {
      return "SKYBLOCK_ENCHANTS";
   }

   @Generated
   public ToggleOption method13() {
      return this.field12;
   }
}
