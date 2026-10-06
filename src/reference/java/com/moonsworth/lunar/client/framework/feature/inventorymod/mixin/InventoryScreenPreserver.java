package com.moonsworth.lunar.client.framework.feature.inventorymod.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.MouseHelperBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiScreenBookBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class InventoryScreenPreserver extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final List<InventoryScreenPreserver.Extension> field7 = new ArrayList<>();
   private long field8;
   private int field9;
   private int field10;
   @Nullable
   private GuiScreenBridge field11;

   public InventoryScreenPreserver() {
      this.handle(EventScreenOpen.class, this::method1);
   }

   private void method1(EventScreenOpen event) {
      if (this.method3(event.method1())) {
         if (!Ref.method4().method40().method85().method19()) {
            this.field8 = Ref.method14();
            this.field11 = event.method1();
            MouseHelperBridge bridge3_272 = Bridge.method20();
            this.field9 = bridge3_272.getX();
            this.field10 = bridge3_272.getY();
         }
      }
   }

   public void method2(InventoryScreenPreserver.Extension extension1) {
      this.field7.add(extension1);
   }

   public boolean method3(@Nullable GuiScreenBridge bridge5extension61) {
      return bridge5extension61 instanceof GuiContainerBridge || bridge5extension61 instanceof GuiScreenBookBridge;
   }

   public boolean method4(@Nullable GuiScreenBridge bridge5extension61) {
      if (this.method3(bridge5extension61) && this.field11 != null) {
         long number2 = Ref.method14() - this.field8;

         for (InventoryScreenPreserver.Extension extension5 : this.field7) {
            if (extension5.shouldPreserve(bridge5extension61, this.field11, number2)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public int method5() {
      return this.field9;
   }

   public int method6() {
      return this.field10;
   }

   @FunctionalInterface
   public interface Extension {
      boolean shouldPreserve(GuiScreenBridge bridge5extension61, GuiScreenBridge bridge5extension62, long number3);
   }
}
