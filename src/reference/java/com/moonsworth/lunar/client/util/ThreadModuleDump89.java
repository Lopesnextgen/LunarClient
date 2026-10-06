package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class ThreadModuleDump89 {
   @Nullable
   public static ItemStackBridge getItemStackByName(String var0) {
      Bridge6_4 var1 = Bridge.method28().method22(var0);
      return var1 == null ? null : Bridge.method8().method38(var1);
   }

   @Nullable
   public static ItemStackBridge getItemStackById(int var0) {
      Bridge6_4 var1 = Bridge.method28().method21(var0);
      return var1 == null ? null : Bridge.method8().method38(var1);
   }

   @Generated
   private ThreadModuleDump89() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
