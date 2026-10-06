package com.moonsworth.lunar.client.network.server;

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

public interface ServerBlocklist {
   boolean method1(ResolvedServerAddress pkg1);

   boolean method2(UnresolvedServerAddress pkg31);

   static ServerBlocklist method3() {
      try {
         final Predicate predicate0 = ServerBlocklist.Data3.method2();
         return new ServerBlocklist() {
            @Override
            public boolean method1(ResolvedServerAddress pkg1) {
               String text2 = pkg1.method1();
               String text3 = pkg1.method2();
               return !predicate0.apply(text2) && !predicate0.apply(text3);
            }

            @Override
            public boolean method2(UnresolvedServerAddress pkg31) {
               String text2 = pkg31.getHost();
               return !predicate0.apply(text2);
            }
         };
      } catch (Throwable exception1) {
         throw exception1;
      }
   }

   class MojangBlocklist {
      private static Class<?> field1;
      private static Constructor<?> field2;
      private static final AtomicReference<Object> field3 = new AtomicReference<>();

      public MojangBlocklist() {
      }

      private static Predicate<String> method1() {
         try {
            Predicate predicate0 = arg0x -> false;
            if (field2 == null) {
               return predicate0;
            }

            try {
               URLConnection urlconnection1 = new URL("https://sessionserver.mojang.com/blockedservers").openConnection();

               try (BufferedReader reader2 = new BufferedReader(new InputStreamReader(urlconnection1.getInputStream(), StandardCharsets.ISO_8859_1))) {
                  Collection list3 = reader2.lines().collect(Collectors.toSet());
                  predicate0 = (Predicate)field2.newInstance(list3);
               } catch (Throwable exception7) {
                  exception7.printStackTrace();
               }
            } catch (IOException exception8) {
               exception8.printStackTrace();
            }

            return predicate0;
         } catch (Throwable exception9) {
            throw exception9;
         }
      }

      @Generated
      public static Predicate<String> method2() {
         Object obj0 = field3.get();
         if (obj0 == null) {
            synchronized (field3) {
               obj0 = field3.get();
               if (obj0 == null) {
                  Predicate predicate2 = method1();
                  obj0 = predicate2 == null ? field3 : predicate2;
                  field3.set(obj0);
               }
            }
         }

         return (Predicate<String>)(obj0 == field3 ? null : obj0);
      }

      static {
         try {
            field1 = Class.forName("com.mojang.patchy.BlockedServers");
            field2 = field1.getConstructor(Collection.class);
         } catch (ClassNotFoundException | NoSuchMethodException classnotfoundexception1) {
            System.err.println("Failed to find BlockList using Reflection");
            classnotfoundexception1.printStackTrace();
         }
      }
   }
}
