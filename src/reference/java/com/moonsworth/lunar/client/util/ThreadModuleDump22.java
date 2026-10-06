package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import java.util.UUID;
import lombok.Generated;

public final class ThreadModuleDump22 {
   public static boolean method1(String var0, UUID var1, boolean var2) {
      if (var1.version() == 4 || var2 && var1.version() == 1) {
         if (var0.contains(" ")) {
            return true;
         } else {
            return var0.isBlank() ? true : var0.contains("§");
         }
      } else {
         return true;
      }
   }

   public static boolean method2(Bridge6_10 var0, boolean var1) {
      if (var0.bridge$isSelf()) {
         return false;
      }

      if (var0 instanceof Bridge5_11) {
         String var2 = ((Bridge5_11)var0).bridge$getTeamName().orElse("");
         if (var2.contains("npc") || var2.startsWith("CIT-")) {
            return true;
         }
      }

      return method1(var0.bridge$getName(), var0.bridge$getUniqueID(), var1);
   }

   public static boolean method3(EntityPlayerBridge var0, boolean var1) {
      if (var0.bridge$isSelf()) {
         return false;
      }

      if (var0 instanceof Bridge5_11) {
         String var2 = ((Bridge5_11)var0).bridge$getTeamName().orElse("");
         if (var2.contains("npc") || var2.startsWith("CIT-")) {
            return true;
         }
      }

      return method1(var0.bridge$getName(), var0.bridge$getUniqueID(), var1);
   }

   @Generated
   private ThreadModuleDump22() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
