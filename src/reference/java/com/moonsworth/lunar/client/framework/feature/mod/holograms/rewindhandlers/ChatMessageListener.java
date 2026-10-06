package com.moonsworth.lunar.client.framework.feature.mod.holograms.rewindhandlers;

import com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.EventNameplateExtension;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import org.jetbrains.annotations.Nullable;

public class ChatMessageListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final LocalPlayerNameListener field7 = (LocalPlayerNameListener)this.method3(LocalPlayerNameListener.class);
   @Nullable
   static String field8 = null;

   public ChatMessageListener() {
      this.handle(TypedChatMessage.class, this::method1);
   }

   protected boolean isEnabled() {
      return ServerBrandWatcher.method8(KeystrokesType.HYPIXEL);
   }

   private void method1(TypedChatMessage data1) {
      field8 = this.field7.method6();
      ChatMessageParser.method1(data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC())
         .ifPresent(arg0 -> LunarEventBus.method29().method12(EventNameplateExtension.class, () -> new EventNameplateExtension(arg0)));
   }
}
