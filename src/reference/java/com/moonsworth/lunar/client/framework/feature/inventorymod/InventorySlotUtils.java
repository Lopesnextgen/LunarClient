package com.moonsworth.lunar.client.framework.feature.inventorymod;

import com.moonsworth.lunar.bridge.GuiContainerCreativeBridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiRecipeBookBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.files.ValuePair;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class InventorySlotUtils {
   public static int field1 = 999;
   public static int field2 = 36;
   public static int field3 = field2 + 4;

   public InventorySlotUtils() {
   }

   public static int method1() {
      if (Ref.method3().bridge$getCurrentScreenOrRewind() instanceof GuiContainerBridge bridge5extension_31) {
         SlotBridge bridge3_182 = bridge5extension_31.bridge$getHoveredSlot();
         return bridge3_182 == null ? -1 : method4(bridge5extension_31, bridge5extension_31.bridge$inventorySlots().indexOf(bridge3_182));
      } else {
         return -1;
      }
   }

   public static int method2() {
      Bridge5Extension_5 bridge5extension_50 = Ref.method7();
      if (bridge5extension_50 == null) {
         return -1;
      }

      ItemStackBridge bridgeextension_41 = bridge5extension_50.bridge$getHeldItem();
      if (bridgeextension_41 == null) {
         return -1;
      }

      List list2 = bridge5extension_50.bridge$getInventory().bridge$getMainInventory();
      int number3 = list2.indexOf(bridgeextension_41);
      return number3 > 9 ? -1 : 9 - number3 - 1;
   }

   public static int method3(GuiContainerBridge bridge5extension_30, SlotBridge bridge3_181) {
      List list2 = bridge5extension_30.bridge$inventorySlots();
      int number3 = list2.size() - list2.indexOf(bridge3_181) - 1;
      if (Ref.MC_VERSION >= 5 && bridge5extension_30 instanceof GuiRecipeBookBridge) {
         if (--number3 < 0) {
            return field1;
         }
      }

      return number3;
   }

   public static int method4(GuiContainerBridge bridge5extension_30, int value) {
      List list2 = bridge5extension_30.bridge$inventorySlots();
      int number3 = list2.size() - value - 1;
      if (Ref.MC_VERSION >= 5 && bridge5extension_30 instanceof GuiRecipeBookBridge) {
         if (--number3 < 0) {
            return field1;
         }
      }

      return number3;
   }

   public static int method5(GuiContainerBridge bridge5extension_30, ItemStackBridge bridgeextension_41) {
      for (SlotBridge bridge3_184 : bridge5extension_30.bridge$inventorySlots()) {
         if (bridge3_184.bridge$getItemStack().equals(bridgeextension_41)) {
            return method4(bridge5extension_30, bridge3_184.bridge$getIndex());
         }
      }

      return -1;
   }

   public static int method6(GuiContainerBridge bridge5extension_30, int index1) {
      if (index1 == field1) {
         return 45;
      }

      List list2 = bridge5extension_30.bridge$inventorySlots();
      if (Ref.MC_VERSION >= 5 && bridge5extension_30 instanceof GuiRecipeBookBridge) {
         index1++;
      }

      return list2.size() - index1 - 1;
   }

   public static boolean method7(int value) {
      GuiScreenBridge bridge5extension61 = Ref.method3().bridge$getCurrentScreenOrRewind();
      if (!(bridge5extension61 instanceof GuiContainerBridge)) {
         return false;
      } else if (bridge5extension61 instanceof GuiContainerCreativeBridge) {
         return false;
      } else {
         return bridge5extension61 instanceof GuiRecipeBookBridge ? value >= 0 && (value < field3 || value == field1) : value >= 0 && value < field2;
      }
   }

   @Nullable
   public static ValuePair<Integer, Integer> method8(int index0) {
      GuiScreenBridge bridge5extension61 = Ref.method3().bridge$getCurrentScreenOrRewind();
      if (bridge5extension61 instanceof GuiContainerBridge bridge5extension_32) {
         if (bridge5extension61 instanceof GuiRecipeBookBridge) {
            if (Ref.MC_VERSION >= 5 && index0 == field1) {
               return method9(bridge5extension_32, 45);
            }

            if (Ref.MC_VERSION >= 5) {
               index0++;
            }
         }

         List list3 = bridge5extension_32.bridge$inventorySlots();
         int number4 = list3.size() - index0 - 1;
         return method9(bridge5extension_32, number4);
      } else {
         return null;
      }
   }

   private static ValuePair<Integer, Integer> method9(GuiContainerBridge bridge5extension_30, int index1) {
      List list2 = bridge5extension_30.bridge$inventorySlots();
      if (index1 >= 0 && index1 < list2.size()) {
         SlotBridge bridge3_183 = (SlotBridge)list2.get(index1);
         return new ValuePair(bridge5extension_30.bridge$getGuiLeft() + bridge3_183.bridge$getXDisplayPosition(), bridge5extension_30.bridge$getGuiTop() + bridge3_183.bridge$getYDisplayPosition());
      } else {
         return null;
      }
   }
}
