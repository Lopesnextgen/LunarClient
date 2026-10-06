package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.horsestats.SystemClipboardBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump68;

public class Horsestats23Handler implements SystemClipboardBridge {
   public String method1() {
      return ThreadModuleDump68.getClipboardString();
   }

   public void method2(String text) {
      ThreadModuleDump68.setClipboardString(text);
   }
}
