package com.moonsworth.lunar.client.util;

import com.google.protobuf.RpcCallback;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import java.util.function.Consumer;
import com.moonsworth.lunar.client.framework.PacketUtil;

public class ThreadModuleDump13 {
   public static <T> void runOnMainThread(T var0, Consumer<T> var1) {
      ThreadModuleDump63.method3().bridge$submit(() -> {
         try {
            var1.accept(var0);
         } catch (Exception var3) {
            Inventorymod2.method5(var3, "PacketUtil");
         }
      });
   }

   public static <T> void runCallbackOnMainThread(T var0, RpcCallback<T> var1) {
      ThreadModuleDump63.method3().bridge$submit(() -> {
         try {
            var1.run(var0);
         } catch (Exception var3) {
            Inventorymod2.method5(var3, "PacketUtil");
         }
      });
   }
}
