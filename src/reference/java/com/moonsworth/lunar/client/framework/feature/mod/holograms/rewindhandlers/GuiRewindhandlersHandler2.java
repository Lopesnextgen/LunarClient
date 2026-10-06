package com.moonsworth.lunar.client.framework.feature.mod.holograms.rewindhandlers;

import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.EventNameplateExtensionLegacy;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import org.jetbrains.annotations.Nullable;

public class GuiRewindhandlersHandler2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final GuiRewindhandlersHandler25 field7 = (GuiRewindhandlersHandler25)this.method3(GuiRewindhandlersHandler25.class);
   @Nullable
   static String field8 = null;

   public GuiRewindhandlersHandler2() {
      this.handle(Data.class, this::method1);
   }

   protected boolean isEnabled() {
      return Highlight3Iterator.method8(KeystrokesType.HYPIXEL);
   }

   private void method1(Data var1) {
      field8 = this.field7.method6();
      Rewindhandlers.method1(var1.OROIIOCCOORRCRCIIHHOCCCRHICRCC())
         .ifPresent(var0 -> ClientEventBus.method29().method12(EventNameplateExtensionLegacy.class, () -> new EventNameplateExtensionLegacy(var0)));
   }
}
