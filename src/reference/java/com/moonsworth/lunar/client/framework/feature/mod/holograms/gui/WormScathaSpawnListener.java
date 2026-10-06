package com.moonsworth.lunar.client.framework.feature.mod.holograms.gui;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.WormScathaEventBase;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.framework.Ref;

public class WormScathaSpawnListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private long field7 = 0L;
   private int field8 = 0;

   public WormScathaSpawnListener() {
      this.handle(EventTick.class, this::method1);
      this.handle(TypedChatMessage.class, this::method3);
   }

   protected boolean isEnabled() {
      return IslandUtils.getIsland() == SkyblockIsland.CRYSTAL_HOLLOWS;
   }

   protected void onDisable() {
      this.field7 = 0L;
      this.field8 = 0;
   }

   private void method1(EventTick highlightimpl21) {
      this.field8++;
      if (this.field8 >= 5) {
         this.field8 = 0;
         this.method5();
      }
   }

   private void method5() {
      if (this.field7 != 0L && Ref.method8() != null) {
         if (Ref.method3().bridge$getSystemTime() - this.field7 > 10000L) {
            this.field7 = 0L;
         } else {
            for (BridgeExtension bridgeextension2 : Ref.method8().bridge$getEntities()) {
               if (bridgeextension2.bridge$getCustomName() != null) {
                  String text3 = TextBridge.getTextContent(bridgeextension2.bridge$getCustomName());
                  if (text3.startsWith("[Lv5] Worm")) {
                     LunarEventBus.method29().method12(WormScathaEventBase.WormScathaSpawnEvent.class, () -> new WormScathaEventBase.WormScathaSpawnEvent(false));
                     this.field7 = 0L;
                     break;
                  }

                  if (text3.startsWith("[Lv10] Scatha")) {
                     LunarEventBus.method29().method12(WormScathaEventBase.WormScathaSpawnEvent.class, () -> new WormScathaEventBase.WormScathaSpawnEvent(true));
                     this.field7 = 0L;
                     break;
                  }
               }
            }
         }
      }
   }

   private void method3(TypedChatMessage data1) {
      String text2 = data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
      if ("You hear the sound of something approaching...".equals(text2)) {
         this.field7 = Ref.method3().bridge$getSystemTime();
         LunarEventBus.method29().method12(WormScathaEventBase.Data.class, WormScathaEventBase.Data::new);
      }
   }
}
