package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge11Extension2;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.Bridge13_3;
import com.moonsworth.lunar.bridge.Bridge_46;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.mod.render.nametag.Nametag;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class TurboPipelineHook implements Bridge13_3, LoadableHandler, EventRegistrar {
   private final Bridge_46 field1 = ThreadModuleDump63.MC_VERSION == 1 ? Bridge.method8().method85() : null;
   private boolean field2;
   private boolean field3;
   private float field4;
   private boolean field5;
   private boolean set = false;

   @Override
   public void close() {
   }

   @Override
   public void init() {
      if (this.field1 != null) {
         this.handle(EventClientTick.class, this::method2);
         if (ThreadModuleDump63.method3().bridge$getResourceManager() instanceof Bridge11Extension2 var1) {
            var1.bridge$registerReloadListener(this);
         }
      }
   }

   public void method1(Bridge11_2 var1) {
      this.field1.free();
   }

   private void method2(EventClientTick var1) {
      if (!this.set) {
         this.method3();
         this.set = true;
      } else {
         if (this.method3()) {
            this.field1.free();
         }
      }
   }

   private boolean method3() {
      boolean var1 = false;
      boolean var2 = this.field2;
      this.field2 = ThreadModuleDump63.method3().bridge$unicode();
      var1 |= var2 != this.field2;
      Nametag var3 = ThreadModuleDump63.method4().method40().method51();
      boolean var4 = this.field3;
      this.field3 = var3.isEnabled() && (Boolean)var3.getNametagShadow().get();
      var1 |= var4 != this.field3;
      float var5 = this.field4;
      this.field4 = var3.getBackgroundOpacity();
      var1 |= var5 != this.field4;
      boolean var6 = this.field5;
      this.field5 = (Boolean)ThreadModuleDump63.method4().method41().method6().method43().get();
      return var1 | var6 != this.field5;
   }

   public Bridge_46 method4() {
      return this.field1;
   }

   public void onDisable() {
      if (this.field1 != null) {
         this.field1.free();
      }
   }
}
