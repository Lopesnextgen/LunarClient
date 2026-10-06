package com.moonsworth.webosr.handler;

import com.moonsworth.webosr.wrappers.Browser;

public interface Logger {
   void onBrowserMessage(Browser var1, Logger.Level var2, String var3, String var4, int var5);

   void onStandardOut(Logger.Level var1, String var2);

   void onStandardErr(Logger.Level var1, String var2);

   enum Level {
      DEBUG,
      INFO,
      WARNING,
      ERROR;

      public static Logger.Level fromValue(int value) {
         return values()[value];
      }

      public int getValue() {
         return this.ordinal();
      }
   }
}
