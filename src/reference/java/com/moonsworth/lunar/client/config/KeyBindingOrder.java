package com.moonsworth.lunar.client.config;

import com.google.common.collect.Lists;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public final class KeyBindingOrder {
   private static final List<KeyBindingBridge> field1 = new ArrayList<>();

   public static KeyBindingBridge[] method1(KeyBindingBridge[] items0) {
      ArrayList list1 = Lists.newArrayList(items0);
      list1.removeAll(field1);
      list1.addAll(field1);
      return list1.toArray(new KeyBindingBridge[0]);
   }

   public static void method2(KeyBindingBridge mixinhelper_150) {
      field1.add(mixinhelper_150);
   }

   @Generated
   private KeyBindingOrder() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
