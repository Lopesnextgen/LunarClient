package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayDeque;
import lombok.Generated;

public final class ChatMessageQueue {
   private static final ArrayDeque<String> field1 = new ArrayDeque<>();

   public static void method1(String text) {
      field1.add(text);
   }

   public static void method2() {
      if (!field1.isEmpty()) {
         Bridge5Extension_5 bridge5extension_50 = Ref.method7();
         if (bridge5extension_50 != null) {
            GuiScreenBridge bridge5extension61 = Ref.method3().bridge$getCurrentScreen();
            if (bridge5extension61 == null || bridge5extension61.method1(Bridge5Extension612.class)) {
               bridge5extension_50.bridge$sendChatMessage(field1.poll());
            }
         }
      }
   }

   @Generated
   private ChatMessageQueue() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
