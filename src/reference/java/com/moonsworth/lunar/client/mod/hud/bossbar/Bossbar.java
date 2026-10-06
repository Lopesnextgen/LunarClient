package com.moonsworth.lunar.client.mod.hud.bossbar;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BossInfoBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.shader.HudShaderTarget;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderBossBar;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;

public class Bossbar extends AbstractFeature {
   public static final ResourceLocationBridge field8 = ResourceLocationBridge.create("lunar", "icons/icons.png");
   public static final ResourceLocationBridge field9 = ResourceLocationBridge.create("textures/gui/icons.png");
   public static final ResourceLocationBridge field10 = ResourceLocationBridge.create("textures/gui/bars.png");
   private static final int field11 = 16777215;
   public static final int field12 = Ref.MC_VERSION <= 1 ? -898574 : -1;
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderBar").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderBossText").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("customBossBar").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final ColorOption field16 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "barColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(field12))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("useMinecraftGUIScale").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final List<BossInfoBridge> field18 = new ArrayList<>();

   public Bossbar() {
      super(true);
      this.method2(ModTraits.field1, new Bossbar.Data());
      ((SettingIntercept)this.method7(ModTraits.field4))
         .method6(arg0 -> Ref.method4().method99().method6(HudShaderTarget.BOSSBAR_MOD), false);
      this.handle(EventRenderBossBar.class, this::method1);
   }

   private void method1(EventRenderBossBar highlightimpl101) {
      highlightimpl101.setCancelled(true);
   }

   public String getId() {
      return "BOSSBAR";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field13, this.field14});
      lightingextension231.method7(this.field15, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field16}));
      lightingextension231.method9(new ClientOption[]{this.field17});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method9(true).method11(this);
   }

   @Generated
   public ToggleOption method13() {
      return this.field15;
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 1.0F, HudAnchor.TOP_CENTER);
         this.method5(182.0F, 16.0F);
      }

      public float method7() {
         return 0.5F;
      }

      public float method8() {
         return 1.5F;
      }

      public boolean method33() {
         return (Boolean)Bossbar.this.field17.get();
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if (!Ref.method4().method40().method85().method17(arg0 -> !arg0.method53().method19())) {
            if (flag4) {
               this.method5(highlightimpl1, value2, value3);
            } else {
               MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
               mixinhelper_45.push();
               if (Ref.MC_VERSION >= 22) {
                  mixinhelper_45.method38(0.0F, 0.0F, -600.0F);
               }

               this.method7(highlightimpl1.method2(), value2, value3);
               mixinhelper_45.pop();
            }
         }
      }

      private void method5(EventRenderHudBase highlightimpl1, float value2, float value3) {
         MixinHelper_4 mixinhelper_44 = highlightimpl1.method2();
         if ((Boolean)Bossbar.this.field13.get()) {
            int number5;
            if ((Boolean)Bossbar.this.field15.get()) {
               number5 = Bossbar.this.field16.method14(value2 + value3);
            } else if (Ref.MC_VERSION >= 19) {
               number5 = Bossbar.field12;
            } else {
               number5 = -1;
            }

            ResourceLocationBridge horsestats146 = !Bossbar.this.field15.get() && Ref.MC_VERSION < 19 ? Bossbar.field9 : Bossbar.field8;
            short number7 = 182;
            byte number8 = 0;
            int number9 = (int)(1.0F * (number7 + 1));
            byte number10 = 9;
            LcuiScreen.method47(mixinhelper_44, horsestats146, value2 + number8, value3 + number10, 0, 74, number7, 5, number5);
            LcuiScreen.method47(mixinhelper_44, horsestats146, value2 + number8, value3 + number10, 0, 74, number7, 5, number5);
            LcuiScreen.method47(mixinhelper_44, horsestats146, value2 + number8, value3 + number10, 0, 79, number9, 5, number5);
            mixinhelper_44.method44(arg0 -> arg0.method29().method33());
         }

         if ((Boolean)Bossbar.this.field14.get()) {
            String text11 = "Ender Dragon";
            int number12 = Bridge.method5().map(arg0 -> arg0.getBossTextColor(16777215)).orElse(16777215);
            if (Ref.MC_VERSION <= 1) {
               value3--;
            }

            mixinhelper_44.method19(
               Ref.method10(),
               text11,
               value2 + (this.getWidth() / 2.0F - Ref.method10().bridge$getStringWidth(text11) / 2.0F),
               value3,
               number12,
               true
            );
            mixinhelper_44.method44(arg0 -> arg0.method29().method33());
         }

         this.method5(182.0F, 18.0F);
      }

      public boolean method4(boolean flag1) {
         if (flag1) {
            return true;
         }

         Bossbar.this.field18.clear();

         for (BossInfoBridge bridge2_113 : Bridge.method8().method42()) {
            if (this.method8(bridge2_113) && ((Boolean)Bossbar.this.field13.get() || bridge2_113.method4().isPresent())) {
               this.method9(bridge2_113);
               Bossbar.this.field18.add(bridge2_113);
            }
         }

         return !Bossbar.this.field18.isEmpty();
      }

      private void method7(MixinHelper_4 mixinhelper_41, float value2, float value3) {
         for (BossInfoBridge bridge2_115 : Bossbar.this.field18) {
            if ((Boolean)Bossbar.this.field13.get()) {
               float value6 = ColorUtils.method9(
                  Bossbar.this.field15.get() ? Bossbar.this.field16.IROHICIOOHIRCOCHOOCROHROIIRRIC(value2 + value3) : Bossbar.field12 >> 16 & 0xFF
               );
               float value7 = ColorUtils.method9(
                  Bossbar.this.field15.get() ? Bossbar.this.field16.HHIRRCHCHIIHIOHICHOOOHIRHRRCCR(value2 + value3) : Bossbar.field12 >> 8 & 0xFF
               );
               float value8 = ColorUtils.method9(
                  Bossbar.this.field15.get() ? Bossbar.this.field16.IHIRROIOORHHCOOCCOOHHHCHOCCORR(value2 + value3) : Bossbar.field12 & 0xFF
               );
               bridge2_115.method9(mixinhelper_41, value2, value3 + 9.0F, value6, value7, value8);
               mixinhelper_41.method44(arg0 -> arg0.method29().method33());
            }

            if ((Boolean)Bossbar.this.field14.get()) {
               Component component10 = (Component)bridge2_115.method4().orElse(null);
               if (component10 != null) {
                  int number11 = Bridge.method5().map(arg0 -> arg0.getBossTextColor(16777215)).orElse(16777215);
                  float value12 = this.getWidth() / 2.0F + value2;
                  float value9 = value12 - Ref.method10().bridge$getStringWidth(component10) / 2.0F;
                  if (Ref.MC_VERSION <= 1) {
                     value3--;
                  }

                  mixinhelper_41.method11(Ref.method10(), component10, value9, value3, number11, true);
                  mixinhelper_41.method44(arg0 -> arg0.method29().method33());
               }
            }

            value3 += 19.0F;
         }

         this.method5(182.0F, Bossbar.this.field18.size() * 16.0F);
      }

      private boolean method8(BossInfoBridge bridge2_111) {
         int number2 = bridge2_111.method6();
         return bridge2_111.method4().isPresent() && number2 == -1 || number2 > 0;
      }

      private void method9(BossInfoBridge bridge2_111) {
         int number2 = bridge2_111.method6();
         if (number2 != -1) {
            bridge2_111.method7(number2 - 1);
         }
      }
   }
}
