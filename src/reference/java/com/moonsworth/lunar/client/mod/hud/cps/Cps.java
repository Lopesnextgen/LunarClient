package com.moonsworth.lunar.client.mod.hud.cps;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.chat.translation.SharedTranslations;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.listener.CpsListener;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import org.jetbrains.annotations.Nullable;

public class Cps extends AbstractFeature {
   private final ToggleOption rightClick = (ToggleOption)OptionFactory.method7("rightClick").method31();
   private final ToggleOption showCpsText = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showCPSText").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption reverseText = (ToggleOption)OptionFactory.method7("reverseText").method31();
   private final ToggleOption ignoreCancelledClicks = (ToggleOption)OptionFactory.method7("ignoreCancelledClicks").method31();
   private final ColorOption lineColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "lineColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-14671840))
      .method31();
   private final CpsListener cpsListener = (CpsListener)this.method63(CpsListener.class);

   public Cps() {
      super(false);
      this.method5(ModTraits.field1, new Cps.Data());
      this.rightClick.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1 -> this.cpsListener.method7());
   }

   public String getId() {
      return "CPS";
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.SETTINGS, arg1x -> {
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.rightClick, arg1xx -> arg1xx.method9(new ClientOption[]{this.lineColor}));
         arg1x.method9(new ClientOption[]{this.showCpsText});
         arg1x.method9(new ClientOption[]{this.reverseText});
         arg1x.method9(new ClientOption[]{this.ignoreCancelledClicks});
      });
   }

   private class Data extends TypedHudRenderer<String> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public HudSize getSize() {
         return HudSize.method1(10, 18, 22, 40, 56, 62);
      }

      @Nullable
      public String registerOptions(boolean flag1) {
         String text2 = Cps.this.rightClick.get() ? "  " : "";
         String text3 = Cps.this.showCpsText.get() ? " " : "";
         String text4 = Integer.toString((Integer)Cps.this.HRICOROOOCCOCOROCRHHCRRIRCOICO("leftCPS", Cps.this.cpsListener.method5((Boolean)Cps.this.ignoreCancelledClicks.get())));
         String text5 = Integer.toString((Integer)Cps.this.HRICOROOOCCOCOROCRHHCRRIRCOICO("rightCPS", Cps.this.cpsListener.method8()));
         if (!(Boolean)Cps.this.rightClick.get()) {
            text5 = "";
         }

         String text6 = Cps.this.showCpsText.get() ? SharedTranslations.field1 : "";
         return Cps.this.reverseText.get() ? text6 + text3 + text4 + text2 + text5 : text4 + text2 + text5 + text3 + text6;
      }

      protected void renderDecoration(MixinHelper_4 mixinhelper_41, float value2, float value3, boolean flag4, boolean flag5) {
         if (flag5) {
            if ((Boolean)Cps.this.rightClick.get()) {
               boolean flag6 = (Boolean)Cps.this.ignoreCancelledClicks.get();
               float value7 = Ref.method10().bridge$getStringWidth(this.registerOptions(flag4));
               float value8 = Ref.method10().bridge$getStringWidth(Cps.this.cpsListener.method5(flag6) + " ");
               float value9;
               if ((Boolean)Cps.this.reverseText.get()) {
                  float value10 = Cps.this.showCpsText.get() ? Ref.method10().bridge$getStringWidth(SharedTranslations.field1 + " ") : 0.0F;
                  value9 = value2 + this.getWidth() / 2.04F - value7 / 2.0F + value10 + value8;
               } else {
                  value9 = value2 + this.getWidth() / 2.04F - value7 / 2.0F + value8;
               }

               if ((Boolean)this.ORHRIHRICHICRCOCIIRIOICOIICHOI.get()) {
                  mixinhelper_41.method4(value9 + 0.25F, value3 + this.getHeight() / 2.0F - 4.0F, 1.0F, 9.0F, 1862270976);
               }

               Cps.this.lineColor.method11(mixinhelper_41, value9 - 0.5F, value3 + this.getHeight() / 2.0F - 4.5F, 1.0F, 9.0F);
            }
         }
      }
   }
}
