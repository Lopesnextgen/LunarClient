package com.moonsworth.lunar.client.mod.hud.shaderpackdisplay;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.optifine.OptifineBridge;
import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.HudConditionSet;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.Nullable;

public class ShaderPackDisplay extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)OptionFactory.method7("displayPrefix").method31();
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("showIfNoShaders").method31();

   public ShaderPackDisplay(Framework7Extension framework7extension1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(framework7extension1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.method2(ModTraits.field1, new ShaderPackDisplay.Data());
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field8, this.field9})
      );
   }

   public String getId() {
      return "SHADER_PACK_DISPLAY";
   }

   private class Data extends TypedHudRenderer<String> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_RIGHT);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
         String text6 = method24().orElseGet(() -> ShaderPackDisplay.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("noShaders", new Object[0]));
         text6 = text6.replace('+', ' ').replace(".zip", "");
         if ((Boolean)ShaderPackDisplay.this.field8.get()) {
            text6 = ShaderPackDisplay.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("shaderPack", new Object[0]) + ": " + text6;
         }

         boolean flag7 = (Boolean)this.HIOICORHOCCRCOIHCRIIROIOIOIRIC.get();
         boolean flag8 = !flag7 && (Boolean)this.HOHRIORRHIIIOOIHOOOCOHOHOHCHII.get();
         if (flag8) {
            text6 = "[" + text6 + "]";
         }

         float value9 = Ref.method10().bridge$getStringWidth(text6);
         int number10 = Ref.method10().method19();
         float value11 = Math.max(number10, (Integer)this.OCRCICOROIRHIOHCCIHHICORCHCOOO.get()) + 4.0F;
         value9 += 8.0F;
         this.method58(value9, value11);
         float value12 = value11 - number10;
         float value13 = value3 + value12 / 2.0F;
         float value14 = 4.0F + value2;
         if (flag7) {
            this.HROIRCHIHORCHCHCRICOOOIOOHIRCH.method11(mixinhelper_45, value2, value3, value9, this.getHeight());
            if ((Boolean)this.OIRIICOHRIHHOCCCIICCRRCICOOCHI.get()) {
               this.CRROIIOHCOROIIOROHHCHIRRCORCRH.method11(mixinhelper_45, this, value2, value3, value9, this.getHeight(), (Float)this.HCHIRRHHICRCCIOOHCOICHHIORICHH.get());
            }
         }

         this.OHOHCCIHCRCOOIIHRHCIIRRRCIOHRR
            .HHRROIIHRRICIIHIIHICRHHRHOHHOO(
               mixinhelper_45, text6, value14, value13, (Boolean)this.ORHRIHRICHICRCOCIIRIOICOIICHOI.get(), flag8 ? this.OOOCCCRICCHOORCCRHHRHHCOOCORRC : null
            );
      }

      public HudConditionSet method5() {
         return HudConditionSet.method5().method1(true).method8();
      }

      public HudSize method15() {
         return HudSize.method1(12, 24, 64, 60, 100, 300);
      }

      @Nullable
      public String method5(boolean flag1) {
         return null;
      }

      public boolean method4(boolean flag1) {
         if (!flag1 && !method24().isPresent() && !(Boolean)ShaderPackDisplay.this.field9.get()) {
            this.method58(0.0F, 0.0F);
            return false;
         } else {
            return true;
         }
      }

      private static Optional<String> method24() {
         String text0 = null;
         Optional optional1 = Bridge.method5();
         Optional optional2 = ExternalLinkRegistry.method2(Fishing2Extension.class);
         if (optional1.isPresent()) {
            OptifineBridge slayer23 = (OptifineBridge)optional1.get();
            text0 = slayer23.getShaders().getShaderPack();
            if (slayer23.getShaders().getPackNone().equals(text0)) {
               text0 = null;
            }
         } else if (optional2.isPresent()) {
            text0 = ((Fishing2Extension)optional2.get()).lunar$getShaderPack();
            if ("(off)".equals(text0)) {
               text0 = null;
            }
         }

         return Optional.ofNullable(text0);
      }
   }
}
