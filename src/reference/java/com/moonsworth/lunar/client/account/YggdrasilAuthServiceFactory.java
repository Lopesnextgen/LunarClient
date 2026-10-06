package com.moonsworth.lunar.client.account;

import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.moonsworth.lunar.client.framework.Ref;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Proxy;
import java.util.UUID;

public class YggdrasilAuthServiceFactory {
   private static final Constructor<YggdrasilAuthenticationService> field1;

   public YggdrasilAuthServiceFactory() {
   }

   public static YggdrasilAuthenticationService method1() {
      if (Ref.MC_VERSION >= 19) {
         return new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString());
      }

      try {
         return field1.newInstance(Proxy.NO_PROXY, UUID.randomUUID().toString());
      } catch (InstantiationException | InvocationTargetException | IllegalAccessException instantiationexception1) {
         throw new RuntimeException("Failed to create profile repository through reflection.", instantiationexception1);
      }
   }

   static {
      try {
         field1 = Ref.MC_VERSION >= 19 ? null : YggdrasilAuthenticationService.class.getDeclaredConstructor(Proxy.class, String.class);
      } catch (NoSuchMethodException nosuchmethodexception1) {
         throw new RuntimeException(nosuchmethodexception1);
      }
   }
}
