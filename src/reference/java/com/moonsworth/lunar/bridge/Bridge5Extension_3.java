package com.moonsworth.lunar.bridge;

import java.util.List;
import java.util.Optional;

public interface Bridge5Extension_3 extends Bridge5_15 {
   List<Bridge3_18> bridge$inventorySlots();

   Bridge2_42 bridge$title();

   Bridge3_18 bridge$getSlotAtPosition(int var1, int var2);

   Bridge3_18 bridge$getHoveredSlot();

   int bridge$getGuiLeft();

   int bridge$getGuiTop();

   void bridge$clickSlot(Bridge3_18 var1);

   void bridge$clickSlot(int var1, int var2, ContainerClickType var3);

   @Override
   default Optional<String> method1() {
      return Optional.of("GuiContainer");
   }

   ItemStackBridge bridge$getCursor();

   void bridge$setHoveredSlot(Bridge3_18 var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   void lunar$handleKeyEventOnState(Bridge2_45 var1, Bridge_7 var2);

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   void lunar$handleMousePressOnState(Bridge2_45 var1, int var2, int var3, int var4);

   int bridge$getLowerChestSizeInventory();
}
