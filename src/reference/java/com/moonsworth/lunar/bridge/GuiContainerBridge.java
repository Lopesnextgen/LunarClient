package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import java.util.Optional;

public interface GuiContainerBridge extends GuiBridge {
   List<SlotBridge> bridge$inventorySlots();

   Bridge2_42 bridge$title();

   SlotBridge bridge$getSlotAtPosition(int number1, int number2);

   SlotBridge bridge$getHoveredSlot();

   int bridge$getGuiLeft();

   int bridge$getGuiTop();

   void bridge$clickSlot(SlotBridge bridge3_181);

   void bridge$clickSlot(int number1, int number2, ClickTypeBridge bridgetype_133);

   default Optional<String> method1() {
      return Optional.of("GuiContainer");
   }

   ItemStackBridge bridge$getCursor();

   void bridge$setHoveredSlot(SlotBridge bridge3_181);

   @VersionGate(min = 1)
   void lunar$handleKeyEventOnState(TextFieldStateBridge bridge2_451, KeyEventBridge bridge_72);

   @VersionGate(min = 1)
   void lunar$handleMousePressOnState(TextFieldStateBridge bridge2_451, int number2, int number3, int number4);

   int bridge$getLowerChestSizeInventory();
}
