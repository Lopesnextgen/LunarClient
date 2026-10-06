package com.moonsworth.lunar.client.framework.feature.mod.holograms.gui;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class GuiRewindhandlersHandler2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private long field7 = 0L;
   private int field8 = 0;

   public GuiRewindhandlersHandler2() {
      this.handle(EventClientTick.class, this::method1);
      this.handle(Data.class, this::method3);
   }

   protected boolean isEnabled() {
      return Click3.getIsland() == Gui2Extension3.CRYSTAL_HOLLOWS;
   }

   protected void onDisable() {
      this.field7 = 0L;
      this.field8 = 0;
   }

   private void method1(EventClientTick var1) {
      this.field8++;
      if (this.field8 >= 5) {
         this.field8 = 0;
         this.method5();
      }
   }

   private void method5() {
      if (this.field7 != 0L && ThreadModuleDump63.method8() != null) {
         if (ThreadModuleDump63.method3().bridge$getSystemTime() - this.field7 > 10000L) {
            this.field7 = 0L;
         } else {
            for (BridgeExtension var2 : ThreadModuleDump63.method8().bridge$getEntities()) {
               if (var2.bridge$getCustomName() != null) {
                  String var3 = AdventureTextBridge.getTextContent(var2.bridge$getCustomName());
                  if (var3.startsWith("[Lv5] Worm")) {
                     ClientEventBus.method29().method12(HighlightBase.Data2.class, () -> new HighlightBase.Data2(false));
                     this.field7 = 0L;
                     break;
                  }

                  if (var3.startsWith("[Lv10] Scatha")) {
                     ClientEventBus.method29().method12(HighlightBase.Data2.class, () -> new HighlightBase.Data2(true));
                     this.field7 = 0L;
                     break;
                  }
               }
            }
         }
      }
   }

   private void method3(Data var1) {
      String var2 = var1.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
      if ("You hear the sound of something approaching...".equals(var2)) {
         this.field7 = ThreadModuleDump63.method3().bridge$getSystemTime();
         ClientEventBus.method29().method12(HighlightBase.Data.class, HighlightBase.Data::new);
      }
   }
}
