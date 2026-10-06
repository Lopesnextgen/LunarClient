package com.moonsworth.lunar.client.guiRewindhandlers.mixin;

import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerBrandEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerJoinEvent;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class GuiRewindhandlers6Impl implements EventRegistrar {
   public GuiRewindhandlers6Impl() {
      this.handle(
         ServerJoinEvent.class,
         var0 -> {
            Bridge3_19 var1 = ThreadModuleDump63.method3().bridge$getCurrentServerData();
            if (var1 != null) {
               String var2 = var1.bridge$serverIP();
               ThreadModuleDump63.method4()
                  .method40()
                  .method1()
                  .forEach(var1x -> var1x.method3(Framework.field4).ifPresent(var2x -> var2x.method4(var1x, var2)));
               ThreadModuleDump63.method4()
                  .method41()
                  .method2()
                  .forEach(
                     (var1x, var2x) -> var2x.method13()
                        .forEach(var1xx -> var1xx.method3(OptionTraits.field5).ifPresent(var2xx -> var2xx.method4(var1xx, var2)))
                  );
            }
         }
      );
      this.handle(
         DisconnectEvent.class,
         var0 -> {
            ThreadModuleDump63.method4()
               .method40()
               .method1()
               .forEach(var0x -> var0x.method3(Framework.field4).ifPresent(var1 -> var1.method5(var0x)));
            ThreadModuleDump63.method4()
               .method41()
               .method2()
               .forEach(
                  (var0x, var1) -> var1.method13()
                     .forEach(var0xx -> var0xx.method3(OptionTraits.field5).ifPresent(var1x -> var1x.method5(var0xx)))
               );
         }
      );
      this.handle(
         ServerBrandEvent.class,
         var0 -> {
            if (var0.method1().toLowerCase().contains("brand")) {
               Bridge3_19 var1 = ThreadModuleDump63.method3().bridge$getCurrentServerData();
               String var2 = var1 == null ? null : var1.bridge$serverIP();
               ThreadModuleDump63.method4()
                  .method40()
                  .method1()
                  .forEach(var1x -> var1x.method3(Framework.field4).ifPresent(var2x -> var2x.method4(var1x, var2)));
               ThreadModuleDump63.method4()
                  .method41()
                  .method2()
                  .forEach(
                     (var1x, var2x) -> var2x.method13()
                        .forEach(var1xx -> var1xx.method3(OptionTraits.field5).ifPresent(var2xx -> var2xx.method4(var1xx, var2)))
                  );
            }
         }
      );
   }
}
