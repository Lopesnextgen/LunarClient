package com.moonsworth.lunar.client.framework.feature.inventorymod;

import com.moonsworth.lunar.bridge.Bridge2_27;
import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge4_15;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.files.Files6_2;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class Inventorymod {
   public static int field1 = 999;
   public static int field2 = 36;
   public static int field3 = field2 + 4;

   public static int method1() {
      if (ThreadModuleDump63.method3().bridge$getCurrentScreenOrRewind() instanceof Bridge5Extension_3 var1) {
         Bridge3_18 var2 = var1.bridge$getHoveredSlot();
         return var2 == null ? -1 : method4(var1, var1.bridge$inventorySlots().indexOf(var2));
      } else {
         return -1;
      }
   }

   public static int method2() {
      Bridge5Extension_5 var0 = ThreadModuleDump63.method7();
      if (var0 == null) {
         return -1;
      }

      ItemStackBridge var1 = var0.bridge$getHeldItem();
      if (var1 == null) {
         return -1;
      }

      List var2 = var0.bridge$getInventory().bridge$getMainInventory();
      int var3 = var2.indexOf(var1);
      return var3 > 9 ? -1 : 9 - var3 - 1;
   }

   public static int method3(Bridge5Extension_3 var0, Bridge3_18 var1) {
      List var2 = var0.bridge$inventorySlots();
      int var3 = var2.size() - var2.indexOf(var1) - 1;
      if (ThreadModuleDump63.MC_VERSION >= 5 && var0 instanceof Bridge4_15) {
         if (--var3 < 0) {
            return field1;
         }
      }

      return var3;
   }

   public static int method4(Bridge5Extension_3 var0, int var1) {
      List var2 = var0.bridge$inventorySlots();
      int var3 = var2.size() - var1 - 1;
      if (ThreadModuleDump63.MC_VERSION >= 5 && var0 instanceof Bridge4_15) {
         if (--var3 < 0) {
            return field1;
         }
      }

      return var3;
   }

   public static int method5(Bridge5Extension_3 var0, ItemStackBridge var1) {
      for (Bridge3_18 var4 : var0.bridge$inventorySlots()) {
         if (var4.bridge$getItemStack().equals(var1)) {
            return method4(var0, var4.bridge$getIndex());
         }
      }

      return -1;
   }

   public static int method6(Bridge5Extension_3 var0, int var1) {
      if (var1 == field1) {
         return 45;
      }

      List var2 = var0.bridge$inventorySlots();
      if (ThreadModuleDump63.MC_VERSION >= 5 && var0 instanceof Bridge4_15) {
         var1++;
      }

      return var2.size() - var1 - 1;
   }

   public static boolean method7(int var0) {
      Bridge5Extension6 var1 = ThreadModuleDump63.method3().bridge$getCurrentScreenOrRewind();
      if (!(var1 instanceof Bridge5Extension_3)) {
         return false;
      } else if (var1 instanceof Bridge2_27) {
         return false;
      } else {
         return var1 instanceof Bridge4_15 ? var0 >= 0 && (var0 < field3 || var0 == field1) : var0 >= 0 && var0 < field2;
      }
   }

   @Nullable
   public static Files6_2<Integer, Integer> method8(int var0) {
      Bridge5Extension6 var1 = ThreadModuleDump63.method3().bridge$getCurrentScreenOrRewind();
      if (var1 instanceof Bridge5Extension_3 var2) {
         if (var1 instanceof Bridge4_15) {
            if (ThreadModuleDump63.MC_VERSION >= 5 && var0 == field1) {
               return method9(var2, 45);
            }

            if (ThreadModuleDump63.MC_VERSION >= 5) {
               var0++;
            }
         }

         List var3 = var2.bridge$inventorySlots();
         int var4 = var3.size() - var0 - 1;
         return method9(var2, var4);
      } else {
         return null;
      }
   }

   private static Files6_2<Integer, Integer> method9(Bridge5Extension_3 var0, int var1) {
      List var2 = var0.bridge$inventorySlots();
      if (var1 >= 0 && var1 < var2.size()) {
         Bridge3_18 var3 = (Bridge3_18)var2.get(var1);
         return new Files6_2(var0.bridge$getGuiLeft() + var3.bridge$getXDisplayPosition(), var0.bridge$getGuiTop() + var3.bridge$getYDisplayPosition());
      } else {
         return null;
      }
   }
}
