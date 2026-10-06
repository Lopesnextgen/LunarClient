package com.moonsworth.lunar.client.mixin;

import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Proxy;
import java.util.UUID;

public class EntityRenderer2 {
   private static final Constructor<YggdrasilAuthenticationService> field1;

   public static YggdrasilAuthenticationService method1() {
      if (ThreadModuleDump63.MC_VERSION >= 19) {
         return new YggdrasilAuthenticationService(Proxy.NO_PROXY);
      }

      try {
         return field1.newInstance(Proxy.NO_PROXY, UUID.randomUUID().toString());
      } catch (InstantiationException | InvocationTargetException | IllegalAccessException var1) {
         throw new RuntimeException("Failed to create profile repository through reflection.", var1);
      }
   }

   static {
      try {
         field1 = ThreadModuleDump63.MC_VERSION >= 19 ? null : YggdrasilAuthenticationService.class.getDeclaredConstructor(Proxy.class, String.class);
      } catch (NoSuchMethodException var1) {
         throw new RuntimeException(var1);
      }
   }
}
