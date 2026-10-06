package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.SeaCreature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.SeaCreatureRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SeaCreatureCatchEvent;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.framework.Ref;

public class DoubleHookListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private boolean field7;

   public DoubleHookListener() {
      this.handle(TypedChatMessage.class, this::method1);
   }

   private void method1(TypedChatMessage data1) {
      String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
      if (text2.startsWith("It's a Double Hook!")) {
         this.field7 = true;
      } else {
         SeaCreatureRegistry rewindhandlers23 = Ref.method4().method40().method82().method15().method39();
         if (rewindhandlers23 != null) {
            SeaCreature rewindhandlers4 = rewindhandlers23.method2().get(text2);
            if (rewindhandlers4 != null) {
               LunarEventBus.method29().method12(SeaCreatureCatchEvent.class, () -> new SeaCreatureCatchEvent(rewindhandlers4, this.field7));
               this.field7 = false;
            }
         }
      }
   }
}
