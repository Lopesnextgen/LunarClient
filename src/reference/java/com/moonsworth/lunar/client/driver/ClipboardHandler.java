package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import com.moonsworth.webosr.handler.Clipboard;

public class ClipboardHandlerLegacy implements Clipboard {
   public ClipboardHandlerLegacy() {
   }

   public String read() {
      return ClipboardUtils.method1();
   }

   public void write(String text) {
      ClipboardUtils.method2(text);
   }

   public void clear() {
      ClipboardUtils.method2("");
   }
}
