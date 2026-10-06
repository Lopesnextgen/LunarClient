package com.moonsworth.lunar.client.mod.misc.replaymod;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.ui.external.RecordingExternalLink;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.misc.replaymod.ReplayMod;
import com.moonsworth.lunar.client.framework.Ref;

public class ReplayModRecordingIndicator extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)OptionFactory.method7("textShadow").method31();

   public ReplayModRecordingIndicator(Framework7Extension framework7extension1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(framework7extension1));
      this.method2(ModTraits.field1, new ReplayModRecordingIndicator.Data());
   }

   public String getId() {
      return "REPLAYMOD_RECORDING_INDICATOR_CHILD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
   }

   private class Data extends HudElementBase {
      private final RecordingExternalLink field9 = (RecordingExternalLink)ExternalLinkRegistry.method2(RecordingExternalLink.class).orElse(null);

      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         TranslationManager foghandler285 = Client.method109().method67();
         if (flag4) {
            String text7 = foghandler285.method4(ImmutableList.of("replaymod"), "replaymod.gui.recording", new Object[0]);
            this.method3(highlightimpl1.method2(), this.field9, text7, value2, value3);
         } else {
            if (this.field9.isRecording() && this.field9.method5()) {
               String text6 = this.field9.method3()
                  ? foghandler285.method4(ImmutableList.of("replaymod"), "replaymod.gui.paused", new Object[0])
                  : foghandler285.method4(ImmutableList.of("replaymod"), "replaymod.gui.recording", new Object[0]);
               this.method3(highlightimpl1.method2(), this.field9, text6, value2, value3);
            } else {
               this.method58(24.0F, 24.0F);
            }
         }
      }

      public boolean method4(boolean flag1) {
         return this.field9 != null;
      }

      private void method3(MixinHelper_4 mixinhelper_41, RecordingExternalLink fishing2extension2, String text3, float value4, float value5) {
         Bridge10_2 bridge10_26 = Ref.method10();
         if (bridge10_26 != null) {
            mixinhelper_41.method19(
               bridge10_26, text3.toUpperCase(), value4 + 20.0F, value5 + 8.0F - bridge10_26.method19() / 2.0F, -1, (Boolean)ReplayModRecordingIndicator.this.field8.get()
            );
         }

         ResourceLocationBridge horsestats147 = Ref.method4().method40().method64().method14().get() ? ReplayMod.field9 : fishing2extension2.method6();
         if (horsestats147 != null) {
            int number8 = fishing2extension2.method7();
            LcuiScreen.method46(mixinhelper_41, horsestats147, (int)value4, (int)value5, 58.0F, 20.0F, 16.0F, 16.0F, number8, number8, -1);
         }

         this.method58(24.0F + bridge10_26.bridge$getStringWidth(text3), bridge10_26.method19() + 6);
      }
   }
}
