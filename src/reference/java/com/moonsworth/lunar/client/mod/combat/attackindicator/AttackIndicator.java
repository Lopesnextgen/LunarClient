package com.moonsworth.lunar.client.mod.combat.attackindicator;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.attackindicator.AttackIndicatorMode;
import com.moonsworth.lunar.client.framework.feature.attackindicator.mixin.Attackindicator;
import com.moonsworth.lunar.client.framework.feature.attackindicator.AttackIndicatorState;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.VersionGate;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.render.crosshair.Crosshair;

public class AttackIndicator extends AbstractFeature {
   private final EnumOption<AttackIndicatorMode> displayMode = (EnumOption<AttackIndicatorMode>)OptionFactory.method10(
         "attackIndicatorDisplayMode", AttackIndicatorMode.VANILLA
      )
      .method31();
   private final ToggleOption horizontal = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("horizontal").method4(true))
      .method31();
   private final ToggleOption renderIcon = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderIcon").method4(true))
      .method31();
   private final ToggleOption showInHudEditor = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showInHudEditor").method4(true))
      .method31();
   private final ToggleOption alwaysShow = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("alwaysShow").method4(true))
      .method31();
   private final ToggleOption playSoundVanilla = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("attackIndicatorPlaySoundVanilla")
         .method4(false))
      .method31();
   private final ToggleOption playSoundCustom = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("attackIndicatorPlaySoundCustom")
         .method4(false))
      .method31();
   private final ToggleOption background = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").method4(false))
      .method31();
   private final ColorOption backgroundColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Hud)OptionFactory.method8(
            "backgroundColor"
         )
         .method4(1862270976))
      .method31();
   private final ToggleOption border = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("border").method4(false))
      .method31();
   private final ColorOption borderColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Hud)OptionFactory.method8(
            "borderColor"
         )
         .method4(-1627389952))
      .method31();
   private final FloatOption borderThickness = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Hud)((com.moonsworth.lunar.client.config.option.FloatOption.Hud)OptionFactory.method2(
               "borderThickness"
            )
            .method4(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   private final ToggleOption vanillaBlending = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("vanillaBlending").method4(false))
      .method31();
   private final ToggleOption dynamicColor = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("dynamicColor").method4(false))
      .method31();
   private final ColorOption color = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Hud)OptionFactory.method8(
            "color"
         )
         .method4(-1))
      .method31();
   private final ColorOption colorLow = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Hud)OptionFactory.method8(
            "colorLow"
         )
         .method4(-65536))
      .method31();
   private final ColorOption colorHigh = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Hud)OptionFactory.method8(
            "colorHigh"
         )
         .method4(-16711936))
      .method31();
   private final Attackindicator indicator = new Attackindicator();

   public AttackIndicator() {
      super(false);
      this.method17(ModTraits.field1, new AttackIndicator.Hud());
      this.method17(ModTraits.borderColor, arg0 -> arg0.method11(Config.field6));
      this.method18(EventTick.class, this::tick);
   }

   public String getId() {
      return "ATTACK_INDICATOR";
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method2(new String[]{"Crosshair"})
         .method1(new ModCategory[]{ModCategory.field3, ModCategory.field4})
         .method11(this);
   }

   protected boolean method23(String text1) {
      return this.indicator.method5().contains(text1);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.displayMode});
            arg1x.method9(new ClientOption[]{this.horizontal}).method3(() -> this.displayMode.get() != AttackIndicatorMode.PROGRESS);
            arg1x.method9(
               new ClientOption[]{((MixinCore9Extension)this.method7(ModTraits.field1)).method9(), this.showInHudEditor, this.alwaysShow}
            );
            arg1x.method9(new ClientOption[]{this.renderIcon}).method3(() -> ((AttackIndicatorMode)this.displayMode.get()).isVanilla());
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.background,
               arg1xx -> {
                  arg1xx.method9(new ClientOption[]{this.backgroundColor});
                  arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                     this.border, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.borderThickness, this.borderColor})
                  );
               }
            );
            arg1x.method9(new ClientOption[]{this.vanillaBlending}).method3(() -> !((AttackIndicatorMode)this.displayMode.get()).isVanilla());
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.dynamicColor, arg1xx -> arg1xx.method9(new ClientOption[]{this.colorLow, this.colorHigh})
            );
            arg1x.method9(new ClientOption[]{this.color}).method3(this.dynamicColor::get);
         }
      );
      lightingextension231.method13();
      lightingextension231.method1("vanillaAttackCooldowns", arg1x -> {
         this.indicator.method1(arg1x, true);
         arg1x.method9(new ClientOption[]{this.playSoundVanilla});
      });
      lightingextension231.method13();
      lightingextension231.method1("customIndicators", arg1x -> {
         this.indicator.method1(arg1x, false);
         arg1x.method9(new ClientOption[]{this.playSoundCustom});
      });
   }

   private void tick() {
      this.indicator.method4(this.mc.bridge$getPlayer(), this);
   }

   public boolean method13() {
      return this.isEnabled();
   }

   @Generated
   public ToggleOption method14() {
      return this.playSoundVanilla;
   }

   @Generated
   public ToggleOption method15() {
      return this.playSoundCustom;
   }

   private class Hud extends HudElementBase {
      @VersionGate(max = 18)
      private static final ResourceLocationBridge horizontal = ResourceLocationBridge.create("minecraft", "textures/gui/icons.png");
      @VersionGate(min = 19)
      private static final ResourceLocationBridge renderIcon = ResourceLocationBridge.create("minecraft", "textures/gui/sprites/hud/hotbar_attack_indicator_background.png");
      @VersionGate(min = 19)
      private static final ResourceLocationBridge showInHudEditor = ResourceLocationBridge.create("minecraft", "textures/gui/sprites/hud/hotbar_attack_indicator_progress.png");
      @VersionGate(min = 19)
      private static final ResourceLocationBridge alwaysShow = ResourceLocationBridge.create("minecraft", "textures/gui/sprites/hud/crosshair_attack_indicator_background.png");
      @VersionGate(min = 19)
      private static final ResourceLocationBridge playSoundVanilla = ResourceLocationBridge.create("minecraft", "textures/gui/sprites/hud/crosshair_attack_indicator_progress.png");
      @VersionGate(min = 19)
      private static final ResourceLocationBridge playSoundCustom = ResourceLocationBridge.create("minecraft", "textures/gui/sprites/hud/crosshair_attack_indicator_full.png");
      private AttackIndicatorState state;

      public Hud() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_CENTER);
      }

      public void method18() {
         super.method18();
         this.method15(HudAnchor.MIDDLE_CENTER);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         this.method58(24.0F, 24.0F);
         if (!flag4 || (Boolean)AttackIndicator.this.showInHudEditor.get()) {
            MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
            if ((Boolean)AttackIndicator.this.background.get()) {
               AttackIndicator.this.backgroundColor.method11(mixinhelper_45, value2, value3, this.getWidth(), this.getHeight());
               if ((Boolean)AttackIndicator.this.border.get()) {
                  AttackIndicator.this.borderColor.method11(mixinhelper_45, this, value2, value3, this.getWidth(), this.getHeight(), (Float)AttackIndicator.this.borderThickness.get());
               }
            }

            AttackIndicatorMode gui2extension6 = (AttackIndicatorMode)AttackIndicator.this.displayMode.get();
            mixinhelper_45.push();
            mixinhelper_45.method38(value2, value3, 0.0F);
            if (gui2extension6.isVanilla()) {
               this.method3(gui2extension6, mixinhelper_45);
            } else {
               this.method4(gui2extension6, mixinhelper_45);
            }

            mixinhelper_45.pop();
         }
      }

      private void method3(AttackIndicatorMode gui2extension1, MixinHelper_4 mixinhelper_42) {
         if (gui2extension1 == AttackIndicatorMode.VANILLA) {
            if (this.state.method2()) {
               this.method5(mixinhelper_42, 4.0F, 10.0F);
            } else {
               this.method6(mixinhelper_42, 4.0F, 10.0F, false);
               this.method7(mixinhelper_42, 4.0F, 10.0F, false);
            }
         } else if (gui2extension1 == AttackIndicatorMode.VANILLA_ICON) {
            this.method6(mixinhelper_42, 3.5F, 2.0F, true);
            this.method7(mixinhelper_42, 3.5F, 2.0F, true);
         }
      }

      private void method4(AttackIndicatorMode gui2extension1, MixinHelper_4 mixinhelper_42) {
         float value3 = this.state.getValue();
         boolean flag4 = this.state.method2();
         boolean flag5 = (Boolean)AttackIndicator.this.renderIcon.get();
         mixinhelper_42.push();
         if (flag5) {
            ItemStackBridge bridgeextension_46 = this.state.getIcon();
            if (bridgeextension_46 != null) {
               mixinhelper_42.method38(4.0F, 4.0F, 0.0F);
               mixinhelper_42.method44(arg0 -> {
                  arg0.method29().method22();
                  Bridge.method14().method2();
               });
               mixinhelper_42.method34(bridgeextension_46, 0, 0, Ref.method3());
               mixinhelper_42.method44(arg0 -> {
                  Bridge.method14().method3();
                  arg0.method29().method23();
                  arg0.method29().method15();
               });
               mixinhelper_42.method38(0.0F, 0.0F, 500.0F);
            } else {
               flag5 = false;
            }
         }

         if (gui2extension1 == AttackIndicatorMode.PROGRESS) {
            mixinhelper_42.push();
            if ((Boolean)AttackIndicator.this.horizontal.get()) {
               if (flag5) {
                  LcuiScreen.method94(mixinhelper_42, 0.5F, 17.5F, 16.0F, 1.0F, -16777216);
                  LcuiScreen.method94(mixinhelper_42, 0.0F, 17.0F, 16.0F * value3, 1.0F, this.method10(true));
               } else {
                  LcuiScreen.method94(mixinhelper_42, 4.5F, 12.5F, 16.0F, 1.0F, -16777216);
                  LcuiScreen.method94(mixinhelper_42, 4.0F, 12.0F, 16.0F * value3, 1.0F, this.method10(true));
               }

               if (flag4) {
                  float value11 = flag5 ? 16.5F : 13.0F;
                  float value7 = flag5 ? 16.0F : 19.0F;
                  this.method9(mixinhelper_42, AttackIndicator.this.mc.bridge$getFontRenderer(), value11, value7 - 1.0F, 0.85F);
               }
            } else {
               mixinhelper_42.method38(-0.5F, 0.0F, 0.0F);
               if (flag5) {
                  LcuiScreen.method94(mixinhelper_42, 17.5F, 1.5F, 1.0F, 15.0F, -16777216);
                  LcuiScreen.method94(mixinhelper_42, 17.0F, 16.0F - value3 * 15.0F, 1.0F, 15.0F * value3, this.method10(true));
               } else {
                  LcuiScreen.method94(mixinhelper_42, 12.5F, 5.5F, 1.0F, 15.0F, -16777216);
                  LcuiScreen.method94(mixinhelper_42, 12.0F, 20.0F - value3 * 15.0F, 1.0F, 15.0F * value3, this.method10(true));
               }

               if (flag4) {
                  float value12 = flag5 ? 18.3F : 17.5F;
                  float value14 = flag5 ? 0.5F : 14.5F;
                  this.method9(mixinhelper_42, AttackIndicator.this.mc.bridge$getFontRenderer(), value12, value14 - 1.0F, 0.85F);
               }
            }

            mixinhelper_42.pop();
         }

         if (gui2extension1 == AttackIndicatorMode.INDICATOR || gui2extension1 == AttackIndicatorMode.INDICATOR_DOT) {
            float value8;
            String text9;
            float value13;
            float value15;
            if (gui2extension1 == AttackIndicatorMode.INDICATOR_DOT) {
               text9 = "●";
               value13 = flag5 ? 17.5F : 12.8F;
               value15 = flag5 ? 14.2F : 18.2F;
               value8 = 0.75F;
            } else {
               text9 = value3 < 1.0F ? String.valueOf('✕') : String.valueOf('✔');
               value13 = flag5 ? 17.8F : 12.8F;
               value15 = flag5 ? 9.0F : 20.0F;
               value8 = 0.85F;
            }

            Bridge10_2 bridge10_210 = AttackIndicator.this.mc.bridge$getFontRenderer();
            mixinhelper_42.method19(bridge10_210, text9, 12.0F - bridge10_210.bridge$getStringWidth(text9) / 2.0F, 12 - (int)(bridge10_210.method19() / 2.0F), this.method10(false), true);
            if (flag4) {
               this.method9(mixinhelper_42, bridge10_210, value13, value15, value8);
            }
         }

         mixinhelper_42.pop();
      }

      private void method5(MixinHelper_4 mixinhelper_41, float value2, float value3) {
         if (Ref.MC_VERSION >= 19) {
            this.method8(mixinhelper_41, playSoundCustom, value2, value3, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F, 16.0F);
         } else {
            this.method8(mixinhelper_41, horizontal, value2, value3, 68.0F, 94.0F, 16.0F, 16.0F, 256.0F, 256.0F);
         }
      }

      private void method6(MixinHelper_4 mixinhelper_41, float value2, float value3, boolean flag4) {
         if (Ref.MC_VERSION >= 19) {
            if (flag4) {
               this.method8(mixinhelper_41, renderIcon, value2, value3, 0.0F, 0.0F, 18.0F, 18.0F, 18.0F, 18.0F);
            } else {
               this.method8(mixinhelper_41, alwaysShow, value2, value3, 0.0F, 0.0F, 16.0F, 4.0F, 16.0F, 4.0F);
            }
         } else {
            if (flag4) {
               this.method8(mixinhelper_41, horizontal, value2, value3, 0.0F, 94.0F, 18.0F, 18.0F, 256.0F, 256.0F);
            } else {
               this.method8(mixinhelper_41, horizontal, value2, value3, 36.0F, 94.0F, 16.0F, 4.0F, 256.0F, 256.0F);
            }
         }
      }

      private void method7(MixinHelper_4 mixinhelper_41, float value2, float value3, boolean flag4) {
         float value5 = this.state.getValue();
         if (flag4) {
            int number8 = (int)(value5 * 19.0F);
            number8 = Math.min(18, number8);
            if (Ref.MC_VERSION >= 19) {
               this.method8(mixinhelper_41, showInHudEditor, value2, value3 + 18.0F - number8, 0.0F, 18 - number8, 18.0F, number8, 18.0F, 18.0F);
            } else {
               this.method8(mixinhelper_41, horizontal, value2, value3 + 18.0F - number8, 18.0F, 112 - number8, 18.0F, number8, 256.0F, 256.0F);
            }
         } else {
            int number6 = (int)(value5 * 17.0F);
            number6 = Math.min(16, number6);
            if (Ref.MC_VERSION >= 19) {
               this.method8(mixinhelper_41, playSoundVanilla, value2, value3, 0.0F, 0.0F, number6, 4.0F, 16.0F, 4.0F);
            } else {
               this.method8(mixinhelper_41, horizontal, value2, value3, 52.0F, 94.0F, number6, 4.0F, 256.0F, 256.0F);
            }
         }
      }

      private void method8(
         MixinHelper_4 mixinhelper_41, ResourceLocationBridge horsestats142, float value3, float value4, float value5, float value6, float value7, float value8, float value9, float value10
      ) {
         if ((Boolean)AttackIndicator.this.vanillaBlending.get()) {
            RenderTypeBridge bridge2011 = LunarRenderTypes.alwaysShow.get(horsestats142);
            mixinhelper_41.method9(bridge2011, horsestats142, value3, value4, value7, value8, arg8x -> {
               float value9x = 1.0F / value9;
               float value10x = 1.0F / value10;
               arg8x.method2(value3, value4 + value8, 0.0).method10(value5 * value9x, (value6 + value8) * value10x).method9(-1).method16();
               arg8x.method2(value3 + value7, value4 + value8, 0.0).method10((value5 + value7) * value9x, (value6 + value8) * value10x).method9(-1).method16();
               arg8x.method2(value3 + value7, value4, 0.0).method10((value5 + value7) * value9x, value6 * value10x).method9(-1).method16();
               arg8x.method2(value3, value4, 0.0).method10(value5 * value9x, value6 * value10x).method9(-1).method16();
            });
         } else {
            LcuiScreen.method46(mixinhelper_41, horsestats142, value3, value4, value5, value6, value7, value8, value9, value10, this.method10(true));
         }
      }

      private void method9(MixinHelper_4 mixinhelper_41, Bridge10_2 bridge10_22, float value3, float value4, float value5) {
         mixinhelper_41.push();
         mixinhelper_41.method38(value3 - bridge10_22.bridge$getStringWidth("+") / 2.0F, value4 - (int)(bridge10_22.method19() / 2.0F), 0.0F);
         mixinhelper_41.scale(value5, value5, 1.0F);
         mixinhelper_41.method18(bridge10_22, "+", 0, 0, -1, false);
         mixinhelper_41.pop();
      }

      private int method10(boolean flag1) {
         if ((Boolean)AttackIndicator.this.dynamicColor.get()) {
            float value2 = this.state.getValue();
            return flag1
               ? ColorUtils.method35(AttackIndicator.this.colorHigh.method14(0.0F), AttackIndicator.this.colorLow.method14(0.0F), value2)
               : value2 >= 1.0F ? AttackIndicator.this.colorHigh.method14(0.0F) : AttackIndicator.this.colorLow.method14(0.0F);
         } else {
            return AttackIndicator.this.color.method14(0.0F);
         }
      }

      public boolean method4(boolean flag1) {
         this.state = null;
         if (!flag1 && AttackIndicator.this.mc.bridge$getPlayer() != null) {
            GuiScreenBridge bridge5extension62 = AttackIndicator.this.mc.bridge$getCurrentScreen();
            if (bridge5extension62 != null && !(bridge5extension62 instanceof Bridge5Extension612)) {
               return false;
            }

            AttackIndicatorState attackindicator33 = AttackIndicator.this.indicator.method7();
            if (attackindicator33 == null) {
               return false;
            }

            if (!(Boolean)AttackIndicator.this.alwaysShow.get() && !attackindicator33.method1()) {
               return false;
            }

            this.state = attackindicator33;
            return true;
         } else {
            if (flag1 && !(Boolean)AttackIndicator.this.showInHudEditor.get()) {
               return false;
            }

            this.state = AttackIndicator.this.indicator.method6();
            return true;
         }
      }

      public boolean method31() {
         return false;
      }

      public boolean method30() {
         return (Boolean)AttackIndicator.this.showInHudEditor.get();
      }

      public void method1(RootSettingsBuilder lightingextension231) {
      }
   }
}
