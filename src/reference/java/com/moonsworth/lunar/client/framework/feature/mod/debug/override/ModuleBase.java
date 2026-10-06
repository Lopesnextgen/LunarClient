package com.moonsworth.lunar.client.framework.feature.mod.debug.override;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.KeyEventBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.mod.GuiRenderer;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.List;

@VersionGate(min = 1)
public class ModuleBase extends com.moonsworth.lunar.client.framework.feature.mod.ModuleBase {
   private boolean field2 = false;

   public ModuleBase() {
   }

   @Override
   public boolean method2(GuiScreenBridge bridge5extension61) {
      return "test".equals(this.method11(bridge5extension61));
   }

   @Override
   public void method2(GuiScreenBridge bridge5extension61, MixinHelper_4 mixinhelper_42, int number3, int number4, float value) {
      GuiRenderer.field1.method1("TestGui", bridge5extension61, mixinhelper_42, number3, number4, true);
      ItemStackBridge bridgeextension_46 = this.method3(bridge5extension61);
      boolean flag7 = bridgeextension_46 != null && !bridgeextension_46.bridge$isEmpty();
      if (flag7) {
         GuiRenderer.field1.method11(false);
      }

      GuiRenderer.field1.method43(10, 10, 10, 10, -16711936);
      if (GuiRenderer.field1.method35("Toggle perf test", 10, 30, 100, 16) != -1) {
         this.field2 = !this.field2;
      }

      String text8 = GuiRenderer.field1.method45("TestGui-Textinput", 10, 60, 100, 10, "Text box", true);
      if (GuiRenderer.field1.method35("Text: " + text8, 10, 80, 100, 16) != -1) {
         System.out.println("Text button was clicked with '" + text8 + "' as input!");
      }

      List list9 = this.method4(bridge5extension61);
      GuiRenderer.field1.method33(this.method11(bridge5extension61), 10, 105, list9, true, false);
      if (bridge5extension61 instanceof GuiContainerBridge bridge5extension_310) {
         ArrayList list11 = new ArrayList();

         for (int index12 = bridge5extension_310.bridge$getLowerChestSizeInventory(); index12 < bridge5extension_310.bridge$inventorySlots().size(); index12++) {
            list11.add((SlotBridge)bridge5extension_310.bridge$inventorySlots().get(index12));
         }

         GuiRenderer.field1.method33("Inventory", 300, 105, list11, true, true);
         if (this.field2) {
            for (int index14 = 0; index14 < 5; index14++) {
               for (int index13 = 1; index13 < 50; index13++) {
                  GuiRenderer.field1.method33("Inventory", 200 * index14, 200 + index13 * 10, list11, true, true);
               }
            }
         }
      }

      if (flag7) {
         GuiRenderer.field1.method53();
      }

      GuiRenderer.field1.end();
   }

   private ItemStackBridge method3(GuiScreenBridge bridge5extension61) {
      return bridge5extension61 instanceof GuiContainerBridge bridge5extension_32 ? bridge5extension_32.bridge$getCursor() : null;
   }

   private List<SlotBridge> method4(GuiScreenBridge bridge5extension61) {
      if (!(bridge5extension61 instanceof GuiContainerBridge bridge5extension_32)) {
         return new ArrayList<>();
      } else {
         ArrayList list3 = new ArrayList();

         for (int index4 = 0; index4 < bridge5extension_32.bridge$getLowerChestSizeInventory(); index4++) {
            list3.add((SlotBridge)bridge5extension_32.bridge$inventorySlots().get(index4));
         }

         return list3;
      }
   }

   @Override
   public boolean method3(GuiScreenBridge bridge5extension61, int number2, int number3, int number4) {
      GuiRenderer.field1.method27("TestGui", number2, number3, number4);
      return true;
   }

   @Override
   public boolean method4(GuiScreenBridge bridge5extension61, int number2, int number3, int number4) {
      GuiRenderer.field1.method28("TestGui", number2, number3, number4);
      return true;
   }

   @Override
   public boolean method10(GuiScreenBridge bridge5extension61, KeyEventBridge bridge_72) {
      return GuiRenderer.field1.method29("TestGui", bridge_72);
   }

   @Override
   public void onOpen() {
      System.out.println("Opened TestGui");
   }

   @Override
   public void onClose() {
      System.out.println("Closed TestGui");
      GuiRenderer.field1.method5("TestGui");
   }
}
