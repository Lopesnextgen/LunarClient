package com.moonsworth.lunar.client.framework.feature.mod.gui.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.AdvancedOptionWidget;
import com.moonsworth.lunar.client.ui.widget.ColorPickerOptionWidget;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudRenderContext;
import com.moonsworth.lunar.client.ui.hud.MixinCore5;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Handler;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Impl;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Impl2;
import com.moonsworth.lunar.client.ui.hud.PaddedHudComponent;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Iterator;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Task;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click17;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Gui2Extension3;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.mod.skyblock.dungeonwaypoints.DungeonWaypoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;

public class Gui extends MixinCore9Base implements Calculator2 {
   private static final float field13 = 0.125F;
   private static final float field14 = 8.0F;
   private static final int field15 = ThreadModuleDump23.method22(NamedTextColor.DARK_GRAY.value(), 255);
   private static final int field16 = -13421773;
   private static final float field17 = 30.0F;
   private static final float field18 = 0.75F;
   private final DungeonWaypoints field19;
   private final List<TextHudComponent> field20 = new ArrayList<>();
   private final List<MixinCore5Task> field21 = new ArrayList<>();

   public Gui(DungeonWaypoints var1) {
      super(0.0F, 30.0F, HudAnchor.TOP_CENTER);
      this.field19 = var1;
      this.method9().IHRHHRIHICHOOICIRIOOHOICHIRHOI(0.75F);
      this.method9().method1(0.75F);
      this.method39(var1x -> var1x || var1.method50().method7());
      this.method45(
         Click17.withPadding(
            new MixinCore5Iterator(true, MixinCore5Iterator.Type.START)
               .method3(1.0F)
               .method5(new PaddedHudComponent(this.method5()).method10(3.0F))
               .method5(this.method3("dungeonWaypointHudPreset", this.method16()))
               .method5(
                  this.method3(
                     "dungeonWaypointRenderMode",
                     this.method7(
                        var1.method35(), com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Gui2Extension2.values()
                     )
                  )
               )
               .method5(this.method3("dungeonWaypointBoxMode", this.method7(var1.method38(), Gui2Extension.values())))
               .method5(this.method3("dungeonWaypointWhen", this.method7(var1.method36(), Gui2Extension3.values())))
               .method5(this.method3("dungeonWaypointThroughWalls", this.method8(var1.method41())))
               .method5(this.method3("dungeonWaypointHudOffset", this.method9(var1.method42(), var1.method43(), var1.method44())))
               .method5(this.method3("dungeonWaypointHudSize", this.method9(var1.method45(), var1.method46(), var1.method47())))
               .method5(this.method3("dungeonWaypointHudColors", this.method19()))
               .method5(new PaddedHudComponent(this.method20()).method8(3.0F))
         )
      );
   }

   private MixinCore5 method5() {
      return new MixinCore5Iterator()
         .method3(4.0F)
         .method5(
            new TextHudComponent()
               .method4(() -> Component.text(this.method32("dungeonWaypointEditHud", new Object[0])).decorate(TextDecoration.BOLD))
               .method9(AdventureChatFormatting.GOLD)
               .method14(1.25)
         )
         .method5(this.getProvider());
   }

   private MixinCore5 getProvider() {
      TextHudComponent var1 = new TextHudComponent()
         .method2(
            () -> this.method32("dungeonWaypointHudPreset", new Object[0]).toUpperCase()
               + ": "
               + this.field19.method22(this.field19.method49().method40()).toUpperCase()
         )
         .method9(AdventureChatFormatting.GOLD);
      return new MixinCore5Impl2(new PaddedHudComponent(var1).method2(1.0F).method4(3.0F).method6(3.0F)).method1(-13421773);
   }

   private MixinCore5 method3(String var1, MixinCore5 var2) {
      return new MixinCore5Iterator().method5(new Gui.Data2(var1)).method5(var2);
   }

   private Component method4(String var1, boolean var2) {
      TextComponent var3 = Component.text("[" + var1 + "] ");
      return (Component)(var2 ? var3.decorate(TextDecoration.BOLD) : var3);
   }

   private MixinCore5 method16() {
      MixinCore5Iterator var1 = new MixinCore5Iterator();

      for (Dungeonwaypoints5 var3 : this.field19.method49().method20()) {
         String var4 = var3.name();
         var1.method5(
            new MixinCore5Task(
                  new TextHudComponent()
                     .method4(() -> this.method4(this.field19.method22(var4), this.field19.method49().method40().equals(var4)))
                     .method6(() -> this.field19.method49().method40().equals(var4) ? this.method6(var4) : field15)
               )
               .method10(() -> this.field19.method21(var4))
         );
      }

      return var1;
   }

   private int method6(String var1) {
      return this.field19.method49().method21(var1).map(var0 -> ThreadModuleDump23.method22(var0.method3().method12(), 255)).orElse(field15);
   }

   private <E extends Enum<E> & com.moonsworth.lunar.client.config.option.OptionEnumValue> MixinCore5 method7(EnumOption<E> var1, E[] var2) {
      MixinCore5Iterator var3 = new MixinCore5Iterator();

      for (Enum var7 : var2) {
         var3.method5(
            new MixinCore5Task(
                  new TextHudComponent()
                     .method4(() -> this.method4(var7.toString(), var1.get() == var7))
                     .method10(() -> var1.get() == var7 ? AdventureChatFormatting.GREEN : AdventureChatFormatting.DARK_GRAY)
               )
               .method10(() -> var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(var7))
         );
      }

      return var3;
   }

   private MixinCore5 method8(ToggleOption var1) {
      return new MixinCore5Iterator()
         .method5(
            new MixinCore5Task(
                  new TextHudComponent()
                     .method4(() -> this.method4("YES", (Boolean)var1.get()))
                     .method10(() -> var1.get() ? AdventureChatFormatting.GREEN : AdventureChatFormatting.DARK_GRAY)
               )
               .method10(() -> var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(true))
         )
         .method5(
            new MixinCore5Task(
                  new TextHudComponent()
                     .method4(() -> this.method4("NO", !(Boolean)var1.get()))
                     .method10(() -> var1.get() ? AdventureChatFormatting.DARK_GRAY : AdventureChatFormatting.RED)
               )
               .method10(() -> var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(false))
         );
   }

   private MixinCore5 method9(FloatOption var1, FloatOption var2, FloatOption var3) {
      return new MixinCore5Iterator()
         .method5(this.method10(var1))
         .method5(new TextHudComponent(" / ", AdventureChatFormatting.GRAY))
         .method5(this.method10(var2))
         .method5(new TextHudComponent(" / ", AdventureChatFormatting.GRAY))
         .method5(this.method10(var3));
   }

   private MixinCore5 method10(FloatOption var1) {
      MixinCore5Task var2 = new MixinCore5Task(new TextHudComponent(() -> String.format("%.3f", var1.get()), AdventureChatFormatting.WHITE))
         .method10(() -> method12(var1, 0.125F))
         .method11(() -> method12(var1, -0.125F))
         .method12(var1x -> method12(var1, var1x > 0.0 ? 0.125F : -0.125F));
      this.field21.add(var2);
      return var2;
   }

   public boolean method17() {
      if (MixinCore5Task.method17()) {
         return false;
      }

      for (MixinCore5Task var2 : this.field21) {
         if (var2.method18()) {
            return true;
         }
      }

      return false;
   }

   private static void method12(FloatOption var0, float var1) {
      float var2 = Math.round(((Float)var0.get() + var1) / 0.125F) * 0.125F;
      var0.method1(Math.max(0.0F, Math.min(1.0F, var2)));
   }

   private MixinCore5 method19() {
      return new MixinCore5Iterator()
         .method5(this.method14(() -> this.field19.method39().getColor()))
         .method5(
            new MixinCore5Task(new TextHudComponent(() -> "[" + this.method32("dungeonWaypointHudFill", new Object[0]) + "...] ", AdventureChatFormatting.AQUA))
               .method10(() -> this.method16(this.field19.method39()))
         )
         .method5(this.method14(() -> this.field19.method40().method14(0.0F)))
         .method5(
            new MixinCore5Task(new TextHudComponent(() -> "[" + this.method32("dungeonWaypointHudWire", new Object[0]) + "...]", AdventureChatFormatting.AQUA))
               .method10(() -> this.method16(this.field19.method40()))
         );
   }

   private MixinCore5 method14(Supplier<Integer> var1) {
      return new PaddedHudComponent(new MixinCore5Impl2(new PaddedHudComponent(new MixinCore5Handler()).method2(4.5F)).method2(var1)).method6(2.0F);
   }

   private MixinCore5 method20() {
      return new MixinCore5Iterator()
         .method3(6.0F)
         .method5(
            new MixinCore5Task(new TextHudComponent(() -> "[" + this.method32("dungeonWaypointHudOpenSettings", new Object[0]) + "]", AdventureChatFormatting.AQUA))
               .method10(() -> this.method16(null))
         )
         .method5(
            new MixinCore5Impl(new TextHudComponent(() -> this.method32("dungeonWaypointHudOpenChat", new Object[0]), AdventureChatFormatting.DARK_GRAY))
               .method2(MixinCore5Task::method16)
         );
   }

   private void method16(@Nullable ClientOption<?> var1) {
      com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen var2 = new com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen(
         ThreadModuleDump63.method3().bridge$getCurrentScreen()
      );
      Framework7Extension var3 = ((Framework4)this.field19.method7(Framework.field16)).method1();
      FeatureSettingsWidget var4 = new FeatureSettingsWidget(var2.method10(), var3);
      var2.method10().method2(var4);

      for (AdvancedOptionWidget var6 : var4.method11(AdvancedOptionWidget.class)) {
         if (var6.method14() == this.field19) {
            var6.method12(true);
            Object var7 = var6;
            if (var1 != null) {
               for (com.moonsworth.lunar.client.ui.widget.OptionWidget var9 : var6.IOHOIHHHHCCOHROROHRIIHOCOHHIRR()) {
                  if (var9.getOption() == var1) {
                     if (var9 instanceof ColorPickerOptionWidget var10) {
                        var10.method8(true);
                     }

                     var7 = var9;
                     break;
                  }
               }
            }

            var4.method19((GuiWidget)var7);
            break;
         }
      }

      var2.method2(0);
      ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(var2));
   }

   public String getLanguagePath() {
      return "settings";
   }

   private class Data2 implements MixinCore5 {
      private final TextHudComponent field1;

      Data2(String var2) {
         this.field1 = new TextHudComponent(() -> Gui.this.method32(var2, new Object[0]), AdventureChatFormatting.GRAY);
         Gui.this.field20.add(this.field1);
      }

      @Override
      public float getWidth() {
         float var1 = 0.0F;

         for (TextHudComponent var3 : Gui.this.field20) {
            var1 = Math.max(var1, var3.getWidth());
         }

         return var1 + 8.0F;
      }

      @Override
      public float getHeight() {
         return this.field1.getHeight();
      }

      @Override
      public void method1(float var1, float var2, HudRenderContext var3) {
         this.field1.method1(var1, var2, var3);
      }

      @Override
      public void clearCache() {
         this.field1.clearCache();
      }
   }
}
