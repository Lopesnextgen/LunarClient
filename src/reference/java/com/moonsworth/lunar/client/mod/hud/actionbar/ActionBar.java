package com.moonsworth.lunar.client.mod.hud.actionbar;

import com.moonsworth.lunar.bridge.GuiIngameBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.shader.HudShaderTarget;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.TriStateOption;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;

public class ActionBar extends AbstractFeature {
   private final TriStateOption field8 = (TriStateOption)OptionFactory.method11("textShadow")
      .method5("minecraftDefault")
      .method31();

   public ActionBar() {
      super(false);
      this.method4(ModTraits.field1, new ActionBar.Data());
      ((SettingIntercept)this.method7(ModTraits.field4))
         .method6(arg0 -> Ref.method4().method99().method6(HudShaderTarget.ACTION_BAR_MOD), false);
   }

   public String getId() {
      return "ACTION_BAR";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field3}).method9(true).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
   }

   private class Data extends HudElementBase {
      private static final Component field9 = Component.text("Action Bar");

      public Data() {
         super(0.0F, -61.0F, HudAnchor.BOTTOM_CENTER_R);
         this.method58(60.0F, ActionBar.this.mc.bridge$getFontRenderer().method19());
      }

      public void method18() {
         super.method18();
         this.method3(this.HHIHOHIIHCRIRIRRCCOHHRORHHCRIH());
      }

      public boolean method31() {
         return Ref.MC_VERSION >= 30;
      }

      public boolean method4(boolean flag1) {
         if (Ref.method10() == null) {
            return false;
         }

         if (flag1) {
            return true;
         }

         GuiIngameBridge bridge5extension92 = ActionBar.this.mc.bridge$getGuiIngame();
         return bridge5extension92 != null && bridge5extension92.bridge$getOverlayMessage() != null && bridge5extension92.bridge$getOverlayMessageTime() > 0;
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if (!Ref.method4().method40().method85().method17(arg0 -> !arg0.method53().method22())) {
            MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
            GuiIngameBridge bridge5extension96 = ActionBar.this.mc.bridge$getGuiIngame();
            Component component7;
            float value8;
            boolean flag9;
            if (flag4) {
               component7 = field9;
               value8 = 20.0F;
               flag9 = false;
            } else {
               component7 = bridge5extension96.bridge$getOverlayMessage();
               if (component7 == null) {
                  return;
               }

               value8 = bridge5extension96.bridge$getOverlayMessageTime() - mixinhelper_45.method43();
               flag9 = bridge5extension96.bridge$isAnimateOverlayMessageColor();
            }

            int number10 = Math.min(255, (int)(value8 * 255.0F / 20.0F));
            if (number10 > (Ref.MC_VERSION >= 30 ? 0 : 8)) {
               float value11 = ActionBar.this.mc.bridge$getFontRenderer().bridge$getStringWidth(component7);
               float value12 = value2 + this.getWidth() / 2.0F - (int)value11 / 2;
               int number13 = ActionBar.this.mc.bridge$getGameSettings().bridge$getTextBackgroundColor(0.0F);
               if (number13 != 0) {
                  int number14 = (number13 >> 24 & 0xFF) * number10 / 255;
                  LcuiScreen.method94(mixinhelper_45, value12 - 2.0F, value3 - 2.0F, value11 + 4.0F, 13.0F, number14 << 24);
               }

               int number15 = flag9 ? ColorUtils.method49(value8 / 50.0F, 0.7F, 0.6F) & 16777215 : 16777215;
               mixinhelper_45.method11(
                  Ref.method10(),
                  component7,
                  value12,
                  value3,
                  ColorUtils.method22(number15, number10),
                  ActionBar.this.field8.method1(Ref.MC_VERSION >= 14)
               );
            }
         }
      }
   }
}
