package com.moonsworth.lunar.client.driver.core;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.ClipboardHandlerLegacy;
import com.moonsworth.lunar.client.driver.FileSystemIterator;
import com.moonsworth.lunar.client.driver.FunctionBusImplLegacy;
import com.moonsworth.lunar.client.driver.TextureHandlerLegacy;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.webosr.config.BrowserConfig;
import com.moonsworth.webosr.config.EngineConfig;
import com.moonsworth.webosr.javascript.FunctionBus;
import com.moonsworth.webosr.wrappers.Browser;
import com.moonsworth.webosr.wrappers.Browser.Listener;
import lombok.Generated;

public abstract class DriverViewContextLegacy<T extends EngineConfig> {
   private final String field1;
   private final FunctionBus field2;
   private final boolean field3;
   private T field4;

   public DriverViewContextLegacy(String var1, boolean var2) {
      this.field1 = var1;
      this.field3 = var2;
      this.field2 = new FunctionBusImplLegacy();
   }

   protected abstract T method1();

   public final T method2() {
      if (this.field4 == null) {
         this.field4 = this.method1();
         this.field4.textureHandler = TextureHandlerLegacy.method2();
         this.field4.clipboard = new ClipboardHandlerLegacy();
         this.field4.fileSystem = new FileSystemIterator(this.field3);
         if (!LunarBuildData.field4) {
            this.field4.remoteDebugPort = 9222;
         }
      }

      return this.field4;
   }

   public final Browser method3(int var1, int var2, double value, Listener listener) {
      try {
         BrowserConfig var6 = new BrowserConfig();
         var6.accelerated = false;
         var6.width = var1;
         var6.height = var2;
         var6.initialScale = value;
         var6.eventHandler = listener;
         var6.transparent = true;
         Browser var7 = DriverViewportLegacy.method52().createBrowser(var6);
         var7.bindFunction("lunarInternalInvoke", this.field2);
         return var7;
      } catch (Exception var8) {
         throw new RuntimeException(var8);
      }
   }

   public final String getUserAgent() {
      return "LunarClient " + Client.method19() + " (" + ThreadModuleDump48.field3 + "; " + LunarBuildData.field2 + "; " + this.field1 + ")";
   }

   @Generated
   public String method4() {
      return this.field1;
   }

   @Generated
   public FunctionBus method5() {
      return this.field2;
   }
}
