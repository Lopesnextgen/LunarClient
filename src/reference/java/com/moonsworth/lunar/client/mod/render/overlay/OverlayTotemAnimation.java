package com.moonsworth.lunar.client.mod.render.overlay;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.combat.totemcounter.TotemCounterHudChild;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;

public class OverlayTotemAnimation extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hidePopAnim")
      .method31();
   private final ToggleOption field9 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("popAnimCustomPos")
      .method31();
   private final ToggleOption field10 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("lockPopAnimRotation")
      .method31();
   private final ToggleOption field11 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("lockPopAnimPos")
      .method31();
   private final FloatOption field12 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "popAnimDuration"
               )
               .method4(2.0F))
            .method8(0.25F, 10.0F))
         .method6(4))
      .method31();
   private final FloatOption field13 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "popRotateSpeed"
            )
            .method4(1.0F))
         .method8(0.1F, 2.0F))
      .method31();

   protected OverlayTotemAnimation(OverlayMod overlaymod1) {
      super(false);
      this.method12(ModTraits.field18, arg0 -> arg0.method11(Config.field6));
      this.method12(ModTraits.field16, ChildModBinding.method3(overlaymod1));
      this.method12(ModTraits.field17, ModCategories.method2(SettingsPage.OTHER));
      this.method12(ModTraits.field1, new OverlayTotemAnimation.Data());
   }

   public String getId() {
      return "TOTEM_ANIMATION_OVERLAY_CHILD";
   }

   public int method13() {
      return Math.round((Float)this.field12.get() * 20.0F);
   }

   public float method2(boolean flag1) {
      float value2 = (Float)((MixinCore9Extension)this.method7(ModTraits.field1)).method9().get();
      if ((Boolean)this.field9.get()) {
         if (flag1) {
            value2 /= LcuiScreen.method151().method3();
         } else {
            value2 *= 0.5F;
         }
      }

      return value2;
   }

   public boolean method14() {
      return (Boolean)this.field8.get();
   }

   public float method15() {
      return (Float)this.field13.get();
   }

   public boolean method16() {
      return !(Boolean)this.field10.get();
   }

   public boolean method17() {
      return !(Boolean)this.field11.get();
   }

   public float method7(int number1) {
      if (!(Boolean)this.field9.get()) {
         return number1;
      }

      float value2 = (float)((MixinCore9Extension)this.method7(ModTraits.field1)).method13();
      value2 += this.method9(0) / 2.0F;
      value2 /= LcuiScreen.method151().method3();
      return value2 * LcuiScreen.method17();
   }

   public float method8(int number1) {
      if (!(Boolean)this.field9.get()) {
         return number1;
      }

      float value2 = (float)((MixinCore9Extension)this.method7(ModTraits.field1)).method14();
      value2 += this.method10(0) / 2.0F;
      value2 /= LcuiScreen.method151().method3();
      return value2 * LcuiScreen.method17();
   }

   public int method9(int number1) {
      return !this.field9.get() ? number1 : (int)((MixinCore9Extension)this.method7(ModTraits.field1)).method10();
   }

   public int method10(int number1) {
      return !this.field9.get() ? number1 : (int)((MixinCore9Extension)this.method7(ModTraits.field1)).method11();
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if (Ref.MC_VERSION >= 5 && flag4) {
            MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
            mixinhelper_45.push();
            mixinhelper_45.method38(value2 + this.getWidth() / 2.0F, value3 + this.getHeight() / 2.0F + 4.0F, 0.0F);
            mixinhelper_45.scale(2.0F, 2.0F, 1.0F);
            String text6 = OverlayTotemAnimation.this.method13("previewText", new Object[0]);
            Bridge10_2 bridge10_27 = OverlayTotemAnimation.this.mc.bridge$getFontRenderer();
            float value8 = bridge10_27.bridge$getStringWidth(text6);
            mixinhelper_45.method19(bridge10_27, text6, -value8 / 2.0F, 0.0F, -1, true);
            mixinhelper_45.method44(arg0 -> arg0.method29().method6(arg0x -> {
               arg0x.IHORHICICIHRCOCRROCHHOROCHCHCR();
               Bridge.method14().method2();
            }));
            mixinhelper_45.method34(TotemCounterHudChild.field12, -9, -18, OverlayTotemAnimation.this.mc);
            mixinhelper_45.method44(arg0 -> arg0.method29().method6(arg0x -> {
               Bridge.method14().method3();
               arg0x.ICOHHORICHCROOOCOHIRIHOHORRCHH();
               arg0x.ICRCRICCCORRHICIHHIHORROOHIROO();
            }));
            mixinhelper_45.pop();
         }
      }

      public void method18() {
         this.method1(HudAnchor.TOP_CENTER);
         super.method18();
      }

      public boolean method4(boolean flag1) {
         if (!(Boolean)OverlayTotemAnimation.this.field8.get() && (Boolean)OverlayTotemAnimation.this.field9.get()) {
            this.method6(240.0F, 135.0F);
            return flag1;
         } else {
            this.method6(0.0F, 0.0F);
            return false;
         }
      }

      public void method1(RootSettingsBuilder lightingextension231) {
         lightingextension231.method9(
            new ClientOption[]{
               OverlayTotemAnimation.this.field8,
               OverlayTotemAnimation.this.field9,
               OverlayTotemAnimation.this.field10,
               OverlayTotemAnimation.this.field11
            }
         );
         lightingextension231.method9(
            new ClientOption[]{OverlayTotemAnimation.this.field12, OverlayTotemAnimation.this.field13, this.ICHRCHCIHROCHRCIRCCCCHCRCCCHOH()}
         );
      }
   }
}
