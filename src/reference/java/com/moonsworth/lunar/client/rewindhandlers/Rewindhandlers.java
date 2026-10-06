package com.moonsworth.lunar.client.rewindhandlers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_57;
import com.moonsworth.lunar.bridge.ItemDataComponentTypes;
import com.moonsworth.lunar.bridge.CompoundTagDataComponent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public final class Rewindhandlers {
   private static final String field1 = "lunar";
   private static final int field2 = 10;
   private static final int field3 = 99;

   public static Bridge_57 method1(ItemStackBridge var0) {
      if (var0 != null && !var0.bridge$isEmpty()) {
         Bridge_57 var1 = ThreadModuleDump63.MC_VERSION < 22 ? var0.bridge$getTagCompound() : method5(var0);
         return var1 != null && var1.bridge$contains("lunar", 10) ? var1.bridge$getCompoundTag("lunar") : null;
      } else {
         return null;
      }
   }

   public static String method2(Bridge_57 var0, String var1, String var2) {
      String var3 = var0.bridge$getString(var1);
      return var3.isEmpty() ? var0.bridge$getString(var2) : var3;
   }

   public static boolean method3(Bridge_57 var0, String var1, String var2) {
      return var0.bridge$getBoolean(var1) || var0.bridge$getBoolean(var2);
   }

   public static boolean method4(Bridge_57 var0, String var1) {
      return var0.bridge$contains(var1, 99);
   }

   private static Bridge_57 method5(ItemStackBridge var0) {
      CompoundTagDataComponent var1 = var0.bridge$getDataComponent(ItemDataComponentTypes.field1);
      return var1 != null ? var1.bridge$getData() : null;
   }

   @Generated
   private Rewindhandlers() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
