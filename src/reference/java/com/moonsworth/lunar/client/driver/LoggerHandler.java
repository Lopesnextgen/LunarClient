package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.webosr.handler.Logger;
import com.moonsworth.webosr.handler.Logger.Level;
import com.moonsworth.webosr.wrappers.Browser;

public class LoggerHandler implements Logger {
   public void onBrowserMessage(Browser var1, Level var2, String var3, String text, int value) {
      this.method1(var2, text);
   }

   public void onStandardOut(Level var1, String var2) {
      this.method1(var1, var2);
   }

   public void onStandardErr(Level var1, String var2) {
      this.method1(var1, var2);
   }

   private void method1(Level var1, String var2) {
      if (com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.isDebug() || var1 == Level.ERROR) {
         String var3 = "[%s] %s";
         switch (var1) {
            case DEBUG:
               Slayer.method4("GameUI Debug", var3, var1.name(), var2);
               break;
            case INFO:
               Slayer.method4("GameUI", var3, var1.name(), var2);
               break;
            case WARNING:
               Slayer.method6("GameUI", var3, var1.name(), var2);
               break;
            case ERROR:
               Slayer.method8("GameUI", var3, var1.name(), var2);
         }
      }
   }
}
