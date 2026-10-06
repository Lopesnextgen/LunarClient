package com.moonsworth.lunar.client.mod.hud.ping;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;

public class PingHud extends AbstractFeature {
   private final ColorOption field8 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "pingIconColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711909))
      .method31();
   private final ColorOption field9 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "pingIconBackgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-10461088))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("brackets").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)OptionFactory.method7("border").method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("textShadow").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("iconShadow").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)OptionFactory.method7("overridePingTextColor").method31();
   private final ColorOption field16 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "pingPrefixColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption field17 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "pingTextColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption field18 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   private final ColorOption field19 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "bracketColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption field20 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   private final FloatOption field21 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   private final ToggleOption field22 = (ToggleOption)OptionFactory.method7("iconMode").method31();
   private final ToggleOption field23 = (ToggleOption)OptionFactory.method7("dynamicIconColor").method31();

   public PingHud(Ping ping1) {
      super(true);
      this.method9(ModTraits.field16, ChildModBinding.method3(ping1));
      this.method9(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.method9(ModTraits.field1, new PingHud.Data());
   }

   public String getId() {
      return "PING_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field22, arg1xx -> {
               arg1xx.method9(new ClientOption[]{this.field14});
               arg1xx.method9(new ClientOption[]{this.field8}).method3(this.field23::get);
               arg1xx.method9(new ClientOption[]{this.field9, this.field23});
            });
            arg1x.method9(new ClientOption[]{this.field13});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field10,
               arg1xx -> arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field12, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.field21})
               )
            );
            arg1x.method9(new ClientOption[]{this.field11}).method3(this.field10::get);
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field15, arg1xx -> arg1xx.method9(new ClientOption[]{this.field17}));
            arg1x.method9(new ClientOption[]{this.field16});
         }
      );
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field18}).method1(new ClientOption[]{this.field10});
         arg1x.method9(new ClientOption[]{this.field20}).method1(new ClientOption[]{this.field12});
         arg1x.method9(new ClientOption[]{this.field19}).method1(new ClientOption[]{this.field11});
      });
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
         this.method6(56.0F, this.method5());
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
         Ping ping6 = (Ping)((ChildModBinding)PingHud.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
         int number7 = (Integer)PingHud.this.HRICOROOOCCOCOROCRHHCRRIRCOICO("ping", ping6.method14());
         float value8 = this.getWidth();
         float value9 = this.getHeight();
         if ((Boolean)PingHud.this.field10.get()) {
            PingHud.this.field18.method11(mixinhelper_45, value2, value3, value8, value9);
         }

         if ((Boolean)PingHud.this.field12.get()) {
            PingHud.this.field20.method11(mixinhelper_45, this, value2, value3, value8, value9, (Float)PingHud.this.field21.get());
         }

         float value10 = 0.0F;
         if ((Boolean)PingHud.this.field22.get()) {
            boolean flag11 = (Boolean)PingHud.this.field23.get();
            ColorOption lightingextension422212 = flag11 ? ping6.method8(number7) : PingHud.this.field8;
            mixinhelper_45.push();
            mixinhelper_45.method38(value2 + value8 / 2.0F, value3, 0.0F);
            mixinhelper_45.scale(2.0F, 2.0F, 1.0F);
            mixinhelper_45.method38(-5.0F, 0.0F, 0.0F);
            byte number13;
            if (number7 < 0) {
               number13 = 0;
            } else if (number7 < 150) {
               number13 = 5;
            } else if (number7 < 300) {
               number13 = 4;
            } else if (number7 < 600) {
               number13 = 3;
            } else if (number7 < 1000) {
               number13 = 2;
            } else {
               number13 = 1;
            }

            boolean flag14 = (Boolean)PingHud.this.field14.get();

            for (int index15 = 0; index15 < 5; index15++) {
               if (index15 < number13) {
                  lightingextension422212.method11(mixinhelper_45, index15 * 2, 7.0F, 1.0F, -(2 + index15), flag14);
               } else {
                  PingHud.this.field9.method11(mixinhelper_45, index15 * 2, 7.0F, 1.0F, -(2 + index15), flag14);
               }
            }

            mixinhelper_45.pop();
            value10 += PingHud.this.field10.get() ? 4.0F : 8.0F;
            value3 += 18.0F;
         } else if ((Boolean)PingHud.this.field10.get()) {
            value10 += 4.0F;
            value3 += 5.0F;
         } else {
            value3++;
         }

         String text23 = (String)ping6.field27.get();
         TextComponent text24 = ping6.method3(number7, true, PingHud.this.field15.get() ? PingHud.this.field17 : null);
         float value27 = Ref.method10().bridge$getStringWidth(text24);
         boolean flag28 = (Boolean)ping6.field26.get();
         float value29 = flag28 ? Ref.method10().bridge$getStringWidth(text23) : 0.0F;
         boolean flag16 = !(Boolean)PingHud.this.field10.get() && (Boolean)PingHud.this.field11.get();
         if (flag16) {
            if (flag28) {
               value29 += Ref.method10().bridge$getStringWidth("[");
            } else {
               value27 += Ref.method10().bridge$getStringWidth("[");
            }

            value27 += Ref.method10().bridge$getStringWidth("]");
         }

         value10 += value29 + value27;
         if (!flag16 && !(Boolean)PingHud.this.field10.get()) {
            value10++;
         } else {
            value10 += 4.0F;
         }

         boolean flag17 = (Boolean)PingHud.this.field13.get();
         float value18 = !PingHud.this.field10.get() && !PingHud.this.field22.get() ? value2 : value2 + value8 / 2.0F - value10 / 2.0F + 4.0F;
         if (flag28) {
            TextComponent text19 = Component.text("");
            if (flag16) {
               text19 = (TextComponent)text19.append(Component.text("[").color(TextColor.color(PingHud.this.field19.getColor())));
            }

            text19 = (TextComponent)text19.append(Component.text(text23).color(TextColor.color(PingHud.this.field16.getColor())));
            mixinhelper_45.method11(Ref.method10(), text19, value18, value3, -1, flag17);
         }

         if (flag16) {
            TextComponent text31 = text24;
            text24 = Component.text("");
            if (!flag28) {
               text24 = (TextComponent)text24.append(Component.text("[").color(TextColor.color(PingHud.this.field19.getColor())));
            }

            text24 = (TextComponent)text24.append(text31);
            text24 = (TextComponent)text24.append(Component.text("]").color(TextColor.color(PingHud.this.field19.getColor())));
         }

         mixinhelper_45.method11(Ref.method10(), text24, value18 + value29, value3, -1, flag17);
         this.method6(value10, this.method5());
      }

      private float method5() {
         return PingHud.this.field22.get() ? 28.0F : (PingHud.this.field10.get() ? 18.0F : 10.0F);
      }

      public boolean method4(boolean flag1) {
         return flag1 || PingHud.this.mc.bridge$getCurrentServerData() != null;
      }
   }
}
