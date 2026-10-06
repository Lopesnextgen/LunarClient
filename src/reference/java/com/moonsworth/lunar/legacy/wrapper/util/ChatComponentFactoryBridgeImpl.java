package com.moonsworth.lunar.legacy.wrapper.util;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.minecraft.ChatComponentFactoryBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class ChatComponentFactoryBridgeImpl implements ChatComponentFactoryBridge {
   public ChatComponentFactoryBridgeImpl() {
   }

   public String method1(Bridge2_42 bridge2_421) {
      return Ref.MC_VERSION >= 1 ? ((IChatComponent)bridge2_421).getFormattedText() : ((IChatComponent)bridge2_421).getFormattedText();
   }

   public Bridge2_42 method2(String text) {
      return (Bridge2_42)(new ChatComponentTranslation(text, new Object[0]));
   }
}
