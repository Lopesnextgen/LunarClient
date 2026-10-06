package com.moonsworth.lunar.legacy.wrapper.util;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.horsestats.ChatComponentFactory;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class Horsestats13Handler implements ChatComponentFactory {
   public String method1(Bridge2_42 var1) {
      return ThreadModuleDump63.MC_VERSION >= 1 ? ((IChatComponent)var1).getFormattedText() : ((IChatComponent)var1).getFormattedText();
   }

   public Bridge2_42 method2(String var1) {
      return (Bridge2_42)(new ChatComponentTranslation(var1, new Object[0]));
   }
}
