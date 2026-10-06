package com.moonsworth.lunar.client.util.game;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class ItemTypeLookup {
   @Nullable
   public static ItemStackBridge method1(String text0) {
      ItemBridge bridge6_41 = Bridge.method28().method22(text0);
      return bridge6_41 == null ? null : Bridge.method8().method38(bridge6_41);
   }

   @Nullable
   public static ItemStackBridge method2(int number0) {
      ItemBridge bridge6_41 = Bridge.method28().method21(number0);
      return bridge6_41 == null ? null : Bridge.method8().method38(bridge6_41);
   }

   @Generated
   private ItemTypeLookup() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
