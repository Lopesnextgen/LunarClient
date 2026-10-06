package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightImpl3;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class GuiRewindhandlersHandler23 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private boolean field7;

   public GuiRewindhandlersHandler23() {
      this.handle(Data.class, this::method1);
   }

   private void method1(Data var1) {
      String var2 = var1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
      if (var2.startsWith("It's a Double Hook!")) {
         this.field7 = true;
      } else {
         Rewindhandlers2 var3 = ThreadModuleDump63.method4().method40().method82().method15().method39();
         if (var3 != null) {
            Rewindhandlers var4 = var3.method2().get(var2);
            if (var4 != null) {
               ClientEventBus.method29().method12(HighlightImpl3.class, () -> new HighlightImpl3(var4, this.field7));
               this.field7 = false;
            }
         }
      }
   }
}
