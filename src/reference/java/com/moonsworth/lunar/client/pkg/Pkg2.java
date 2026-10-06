package com.moonsworth.lunar.client.pkg;

import com.google.common.base.Predicate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.Generated;

public interface Pkg2 {
   boolean method1(Pkg var1);

   boolean method2(Pkg3 var1);

   static Pkg2 method3() {
      try {
         final Predicate var0 = Pkg2.Data3.method2();
         return new Pkg2() {
            @Override
            public boolean method1(Pkg var1) {
               String var2 = var1.method1();
               String var3 = var1.method2();
               return !var0.apply(var2) && !var0.apply(var3);
            }

            @Override
            public boolean method2(Pkg3 var1) {
               String var2 = var1.getHost();
               return !var0.apply(var2);
            }
         };
      } catch (Throwable var1) {
         throw var1;
      }
   }

   class Data3 {
      private static Class<?> field1;
      private static Constructor<?> field2;
      private static final AtomicReference<Object> field3 = new AtomicReference<>();

      private static Predicate<String> method1() {
         try {
            Predicate var0 = var0x -> false;
            if (field2 == null) {
               return var0;
            }

            try {
               URLConnection var1 = new URL("https://sessionserver.mojang.com/blockedservers").openConnection();

               try (BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getInputStream(), StandardCharsets.ISO_8859_1))) {
                  Collection var3 = var2.lines().collect(Collectors.toSet());
                  var0 = (Predicate)field2.newInstance(var3);
               } catch (Throwable var7) {
                  var7.printStackTrace();
               }
            } catch (IOException var8) {
               var8.printStackTrace();
            }

            return var0;
         } catch (Throwable var9) {
            throw var9;
         }
      }

      @Generated
      public static Predicate<String> method2() {
         Object var0 = field3.get();
         if (var0 == null) {
            synchronized (field3) {
               var0 = field3.get();
               if (var0 == null) {
                  Predicate var2 = method1();
                  var0 = var2 == null ? field3 : var2;
                  field3.set(var0);
               }
            }
         }

         return (Predicate<String>)(var0 == field3 ? null : var0);
      }

      static {
         try {
            field1 = Class.forName("com.mojang.patchy.BlockedServers");
            field2 = field1.getConstructor(Collection.class);
         } catch (ClassNotFoundException | NoSuchMethodException var1) {
            System.err.println("Failed to find BlockList using Reflection");
            var1.printStackTrace();
         }
      }
   }
}
