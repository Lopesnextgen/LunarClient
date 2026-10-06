package com.moonsworth.lunar.client.framework.feature.mod.gui.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.AdvancedOptionWidget;
import com.moonsworth.lunar.client.ui.widget.ColorPickerOptionWidget;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudRenderContext;
import com.moonsworth.lunar.client.ui.hud.HudComponent;
import com.moonsworth.lunar.client.ui.hud.EmptyHudComponent;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.HideableHudComponent;
import com.moonsworth.lunar.client.ui.hud.BackgroundHudComponent;
import com.moonsworth.lunar.client.ui.hud.PaddedHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Task;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonWaypointBoxMode;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointVisibility;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.mod.skyblock.dungeonwaypoints.DungeonWaypoints;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;

public class DungeonWaypointHudSettings extends MixinCore9Base implements Translatable {
   private static final float field13 = 0.125F;
   private static final float field14 = 8.0F;
   private static final int field15 = ColorUtils.method22(NamedTextColor.DARK_GRAY.value(), 255);
   private static final int field16 = -13421773;
   private static final float field17 = 30.0F;
   private static final float field18 = 0.75F;
   private final DungeonWaypoints field19;
   private final List<TextHudComponent> field20 = new ArrayList<>();
   private final List<MixinCore5Task> field21 = new ArrayList<>();

   public DungeonWaypointHudSettings(DungeonWaypoints dungeonwaypoints1) {
      super(0.0F, 30.0F, HudAnchor.TOP_CENTER);
      this.field19 = dungeonwaypoints1;
      this.method9().IHRHHRIHICHOOICIRIOOHOICHIRHOI(0.75F);
      this.method9().method1(0.75F);
      this.method39(arg1x -> arg1x || dungeonwaypoints1.method50().method7());
      this.method45(
         WidgetFactory.withBackground(
            new HudComponentGroup(true, HudComponentGroup.Type.START)
               .method3(1.0F)
               .method5(new PaddedHudComponent(this.method5()).method10(3.0F))
               .method5(this.method3("dungeonWaypointHudPreset", this.method16()))
               .method5(
                  this.method3(
                     "dungeonWaypointRenderMode",
                     this.method7(
                        dungeonwaypoints1.method35(), com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointRenderMode.values()
                     )
                  )
               )
               .method5(this.method3("dungeonWaypointBoxMode", this.method7(dungeonwaypoints1.method38(), DungeonWaypointBoxMode.values())))
               .method5(this.method3("dungeonWaypointWhen", this.method7(dungeonwaypoints1.method36(), WaypointVisibility.values())))
               .method5(this.method3("dungeonWaypointThroughWalls", this.method8(dungeonwaypoints1.method41())))
               .method5(this.method3("dungeonWaypointHudOffset", this.method9(dungeonwaypoints1.method42(), dungeonwaypoints1.method43(), dungeonwaypoints1.method44())))
               .method5(this.method3("dungeonWaypointHudSize", this.method9(dungeonwaypoints1.method45(), dungeonwaypoints1.method46(), dungeonwaypoints1.method47())))
               .method5(this.method3("dungeonWaypointHudColors", this.method19()))
               .method5(new PaddedHudComponent(this.method20()).method8(3.0F))
         )
      );
   }

   private HudComponent method5() {
      return new HudComponentGroup()
         .method3(4.0F)
         .method5(
            new TextHudComponent()
               .method4(() -> Component.text(this.method32("dungeonWaypointEditHud", new Object[0])).decorate(TextDecoration.BOLD))
               .method9(ChatFormatting.GOLD)
               .method14(1.25)
         )
         .method5(this.method15());
   }

   private HudComponent method15() {
      TextHudComponent mixincore5handler31 = new TextHudComponent()
         .method2(
            () -> this.method32("dungeonWaypointHudPreset", new Object[0]).toUpperCase()
               + ": "
               + this.field19.method22(this.field19.method49().method40()).toUpperCase()
         )
         .method9(ChatFormatting.GOLD);
      return new BackgroundHudComponent(new PaddedHudComponent(mixincore5handler31).method2(1.0F).method4(3.0F).method6(3.0F)).method1(-13421773);
   }

   private HudComponent method3(String text1, HudComponent mixincore52) {
      return new HudComponentGroup().method5(new DungeonWaypointHudSettings.SettingsLabel(text1)).method5(mixincore52);
   }

   private Component method4(String text1, boolean flag2) {
      TextComponent text3 = Component.text("[" + text1 + "] ");
      return (Component)(flag2 ? text3.decorate(TextDecoration.BOLD) : text3);
   }

   private HudComponent method16() {
      HudComponentGroup mixincore5iterator1 = new HudComponentGroup();

      for (WaypointPreset dungeonwaypoints53 : this.field19.method49().method20()) {
         String text4 = dungeonwaypoints53.name();
         mixincore5iterator1.method5(
            new MixinCore5Task(
                  new TextHudComponent()
                     .method4(() -> this.method4(this.field19.method22(text4), this.field19.method49().method40().equals(text4)))
                     .method6(() -> this.field19.method49().method40().equals(text4) ? this.method6(text4) : field15)
               )
               .method10(() -> this.field19.method21(text4))
         );
      }

      return mixincore5iterator1;
   }

   private int method6(String text1) {
      return this.field19.method49().method21(text1).map(arg0 -> ColorUtils.method22(arg0.method3().method12(), 255)).orElse(field15);
   }

   private <E extends Enum<E> & com.moonsworth.lunar.client.config.option.OptionEnumValue> HudComponent method7(EnumOption<E> lightingextension4971, E[] items2) {
      HudComponentGroup mixincore5iterator3 = new HudComponentGroup();

      for (Enum value7 : items2) {
         mixincore5iterator3.method5(
            new MixinCore5Task(
                  new TextHudComponent()
                     .method4(() -> this.method4(value7.toString(), lightingextension4971.get() == value7))
                     .method10(() -> lightingextension4971.get() == value7 ? ChatFormatting.GREEN : ChatFormatting.DARK_GRAY)
               )
               .method10(() -> lightingextension4971.OIRHOOIICOCIOOHICRRRICORIHHIHC(value7))
         );
      }

      return mixincore5iterator3;
   }

   private HudComponent method8(ToggleOption lightingextension4431) {
      return new HudComponentGroup()
         .method5(
            new MixinCore5Task(
                  new TextHudComponent()
                     .method4(() -> this.method4("YES", (Boolean)lightingextension4431.get()))
                     .method10(() -> lightingextension4431.get() ? ChatFormatting.GREEN : ChatFormatting.DARK_GRAY)
               )
               .method10(() -> lightingextension4431.method10(true))
         )
         .method5(
            new MixinCore5Task(
                  new TextHudComponent()
                     .method4(() -> this.method4("NO", !(Boolean)lightingextension4431.get()))
                     .method10(() -> lightingextension4431.get() ? ChatFormatting.DARK_GRAY : ChatFormatting.RED)
               )
               .method10(() -> lightingextension4431.method10(false))
         );
   }

   private HudComponent method9(FloatOption lightingextension4721, FloatOption lightingextension4722, FloatOption lightingextension4723) {
      return new HudComponentGroup()
         .method5(this.method10(lightingextension4721))
         .method5(new TextHudComponent(" / ", ChatFormatting.GRAY))
         .method5(this.method10(lightingextension4722))
         .method5(new TextHudComponent(" / ", ChatFormatting.GRAY))
         .method5(this.method10(lightingextension4723));
   }

   private HudComponent method10(FloatOption lightingextension4721) {
      MixinCore5Task mixincore5task2 = new MixinCore5Task(new TextHudComponent(() -> String.format("%.3f", lightingextension4721.get()), ChatFormatting.WHITE))
         .method10(() -> method12(lightingextension4721, 0.125F))
         .method11(() -> method12(lightingextension4721, -0.125F))
         .method12(arg1x -> method12(lightingextension4721, arg1x > 0.0 ? 0.125F : -0.125F));
      this.field21.add(mixincore5task2);
      return mixincore5task2;
   }

   public boolean method17() {
      if (MixinCore5Task.method17()) {
         return false;
      }

      for (MixinCore5Task mixincore5task2 : this.field21) {
         if (mixincore5task2.method18()) {
            return true;
         }
      }

      return false;
   }

   private static void method12(FloatOption lightingextension4720, float value1) {
      float value2 = Math.round(((Float)lightingextension4720.get() + value1) / 0.125F) * 0.125F;
      lightingextension4720.method1(Math.max(0.0F, Math.min(1.0F, value2)));
   }

   private HudComponent method19() {
      return new HudComponentGroup()
         .method5(this.method14(() -> this.field19.method39().getColor()))
         .method5(
            new MixinCore5Task(new TextHudComponent(() -> "[" + this.method32("dungeonWaypointHudFill", new Object[0]) + "...] ", ChatFormatting.AQUA))
               .method10(() -> this.method16(this.field19.method39()))
         )
         .method5(this.method14(() -> this.field19.method40().method14(0.0F)))
         .method5(
            new MixinCore5Task(new TextHudComponent(() -> "[" + this.method32("dungeonWaypointHudWire", new Object[0]) + "...]", ChatFormatting.AQUA))
               .method10(() -> this.method16(this.field19.method40()))
         );
   }

   private HudComponent method14(Supplier<Integer> supplier1) {
      return new PaddedHudComponent(new BackgroundHudComponent(new PaddedHudComponent(new EmptyHudComponent()).method2(4.5F)).method2(supplier1)).method6(2.0F);
   }

   private HudComponent method20() {
      return new HudComponentGroup()
         .method3(6.0F)
         .method5(
            new MixinCore5Task(new TextHudComponent(() -> "[" + this.method32("dungeonWaypointHudOpenSettings", new Object[0]) + "]", ChatFormatting.AQUA))
               .method10(() -> this.method16(null))
         )
         .method5(
            new HideableHudComponent(new TextHudComponent(() -> this.method32("dungeonWaypointHudOpenChat", new Object[0]), ChatFormatting.DARK_GRAY))
               .method2(MixinCore5Task::method16)
         );
   }

   private void method16(@Nullable ClientOption<?> lightingextension1) {
      com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen bridge7iterator2 = new com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen(
         Ref.method3().bridge$getCurrentScreen()
      );
      Framework7Extension framework7extension3 = ((ChildModBinding)this.field19.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
      FeatureSettingsWidget calculator2iterator34 = new FeatureSettingsWidget(bridge7iterator2.method10(), framework7extension3);
      bridge7iterator2.method10().method2(calculator2iterator34);

      for (AdvancedOptionWidget calculator2iterator32246 : calculator2iterator34.method2(AdvancedOptionWidget.class)) {
         if (calculator2iterator32246.method14() == this.field19) {
            calculator2iterator32246.method12(true);
            Object obj7 = calculator2iterator32246;
            if (lightingextension1 != null) {
               for (com.moonsworth.lunar.client.ui.widget.OptionWidget calculator2iterator39 : calculator2iterator32246.IOHOIHHHHCCOHROROHRIIHOCOHHIRR()) {
                  if (calculator2iterator39.getOption() == lightingextension1) {
                     if (calculator2iterator39 instanceof ColorPickerOptionWidget calculator2iterator3910) {
                        calculator2iterator3910.method8(true);
                     }

                     obj7 = calculator2iterator39;
                     break;
                  }
               }
            }

            calculator2iterator34.method19((GuiWidget)obj7);
            break;
         }
      }

      bridge7iterator2.method2(0);
      Ref.method3().bridge$displayScreen(Bridge.method8().method18(bridge7iterator2));
   }

   public String getLanguagePath() {
      return "settings";
   }

   private class SettingsLabel implements HudComponent {
      private final TextHudComponent field1;

      SettingsLabel(String text2) {
         this.field1 = new TextHudComponent(() -> DungeonWaypointHudSettings.this.method32(text2, new Object[0]), ChatFormatting.GRAY);
         DungeonWaypointHudSettings.this.field20.add(this.field1);
      }

      @Override
      public float getWidth() {
         float value1 = 0.0F;

         for (TextHudComponent mixincore5handler33 : DungeonWaypointHudSettings.this.field20) {
            value1 = Math.max(value1, mixincore5handler33.getWidth());
         }

         return value1 + 8.0F;
      }

      @Override
      public float getHeight() {
         return this.field1.getHeight();
      }

      @Override
      public void method1(float value1, float value2, HudRenderContext mixincore43) {
         this.field1.method1(value1, value2, mixincore43);
      }

      @Override
      public void clearCache() {
         this.field1.clearCache();
      }
   }
}
