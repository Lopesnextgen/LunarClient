package com.moonsworth.lunar.client.util.game;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.CompoundTagComponent;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public final class ItemTagUtils {
   private static final String field1 = "lunar";
   private static final int field2 = 10;
   private static final int field3 = 99;

   public static CompoundTagBridge method1(ItemStackBridge bridgeextension_40) {
      if (bridgeextension_40 != null && !bridgeextension_40.bridge$isEmpty()) {
         CompoundTagBridge bridge_571 = Ref.MC_VERSION < 22 ? bridgeextension_40.bridge$getTagCompound() : method5(bridgeextension_40);
         return bridge_571 != null && bridge_571.bridge$contains("lunar", 10) ? bridge_571.bridge$getCompoundTag("lunar") : null;
      } else {
         return null;
      }
   }

   public static String method2(CompoundTagBridge bridge_570, String text1, String text2) {
      String text3 = bridge_570.bridge$getString(text1);
      return text3.isEmpty() ? bridge_570.bridge$getString(text2) : text3;
   }

   public static boolean method3(CompoundTagBridge bridge_570, String text1, String text2) {
      return bridge_570.bridge$getBoolean(text1) || bridge_570.bridge$getBoolean(text2);
   }

   public static boolean method4(CompoundTagBridge bridge_570, String text1) {
      return bridge_570.bridge$contains(text1, 99);
   }

   private static CompoundTagBridge method5(ItemStackBridge bridgeextension_40) {
      CompoundTagComponent mixinhelper_101 = (CompoundTagComponent)bridgeextension_40.bridge$getDataComponent(DataComponentTypes.field1);
      return mixinhelper_101 != null ? mixinhelper_101.bridge$getData() : null;
   }

   @Generated
   private ItemTagUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
