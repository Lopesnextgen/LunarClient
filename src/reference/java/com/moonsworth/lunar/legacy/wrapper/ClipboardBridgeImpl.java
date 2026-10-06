package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.minecraft.ClipboardBridge;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;

public class ClipboardBridgeImpl implements ClipboardBridge {
   public ClipboardBridgeImpl() {
   }

   public String method1() {
      return ClipboardUtils.method1();
   }

   public void method2(String text) {
      ClipboardUtils.method2(text);
   }
}
