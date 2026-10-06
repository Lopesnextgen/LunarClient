package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.client.util.ThreadModuleDump68;
import com.moonsworth.webosr.handler.Clipboard;

public class ClipboardHandlerLegacy implements Clipboard {
   public String read() {
      return ThreadModuleDump68.getClipboardString();
   }

   public void write(String text) {
      ThreadModuleDump68.setClipboardString(text);
   }

   public void clear() {
      ThreadModuleDump68.setClipboardString("");
   }
}
