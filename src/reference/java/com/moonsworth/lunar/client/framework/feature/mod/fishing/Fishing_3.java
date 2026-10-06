package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayDeque;
import lombok.Generated;

public final class Fishing_3 {
   private static final ArrayDeque<String> field1 = new ArrayDeque<>();

   public static void method1(String var0) {
      field1.add(var0);
   }

   public static void method2() {
      if (!field1.isEmpty()) {
         Bridge5Extension_5 var0 = ThreadModuleDump63.method7();
         if (var0 != null) {
            Bridge5Extension6 var1 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
            if (var1 == null || var1.method1(Bridge5Extension612.class)) {
               var0.bridge$sendChatMessage(field1.poll());
            }
         }
      }
   }

   @Generated
   private Fishing_3() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
