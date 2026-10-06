package com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.ItemStackRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.AnimatedValue;
import com.moonsworth.lunar.client.framework.listener.PersistentValuesListener;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import lombok.Generated;

@VersionGate(min = 33)
public class Storageoverlay {
   private final transient AnimatedValue field1 = new AnimatedValue(0.0, AnimatedValue.Type.SIN_IN_OUT);
   private final transient AnimatedValue field2 = new AnimatedValue(0.0, AnimatedValue.Type.SIN_IN_OUT);
   private transient boolean field3 = false;
   @com.moonsworth.lunar.ichor.util.KeepName
   private final List<String> itemNbts = new ArrayList<>();
   private transient List<ItemStackBridge> field4 = null;
   @com.moonsworth.lunar.ichor.util.KeepName
   private int menuIndex;
   @com.moonsworth.lunar.ichor.util.KeepName
   private boolean isEnderChestPage;
   @com.moonsworth.lunar.ichor.util.KeepName
   private boolean itemsKnown = false;
   @com.moonsworth.lunar.ichor.util.KeepName
   private String name = "";
   private transient boolean field5 = false;
   private transient String field6 = "";
   private transient boolean field7 = false;

   public Storageoverlay(int number1, boolean flag2) {
      this.menuIndex = number1;
      this.isEnderChestPage = flag2;
   }

   public Storageoverlay() {
   }

   public List<ItemStackBridge> method1() {
      if (this.field4 == null) {
         this.field4 = new ArrayList<>();

         for (String text2 : this.itemNbts) {
            if (text2 == null) {
               this.field4.add(Bridge.method8().method41());
            } else {
               CompoundTagBridge bridge_573 = (CompoundTagBridge)Bridge.method8().method69(text2).orElse(null);
               if (bridge_573 == null) {
                  this.field4.add(Bridge.method8().method41());
               } else {
                  ItemStackBridge bridgeextension_44 = Bridge.method8().method40(bridge_573);
                  if (bridgeextension_44 == null) {
                     bridgeextension_44 = Bridge.method8().method41();
                  }

                  this.field4.add(bridgeextension_44);
               }
            }
         }
      }

      return this.field4;
   }

   public void method2(PersistentValuesListener guirewindhandlershandler261) {
      if (this.field7 && this.field4 != null) {
         this.field7 = false;
         this.itemNbts.clear();

         for (ItemStackBridge bridgeextension_43 : this.field4) {
            if (bridgeextension_43.bridge$isEmpty()) {
               this.itemNbts.add(null);
            } else {
               Optional optional4 = bridgeextension_43.bridge$saveTagCompound();
               if (optional4.isEmpty()) {
                  this.itemNbts.add(null);
               } else {
                  this.itemNbts.add(((CompoundTagBridge)optional4.get()).toString());
               }
            }
         }

         guirewindhandlershandler261.markDirty();
      }
   }

   public boolean method3(String text1) {
      if (text1 == null) {
         return false;
      }

      text1 = ChatFormatting.getTextWithoutFormattingCodes(text1);
      return !this.isEnderChestPage && text1.contains(" Backpack ") && text1.endsWith("(Slot #" + this.menuIndex + ")")
         ? true
         : this.isEnderChestPage && text1.startsWith("Ender Chest (" + this.menuIndex + "/");
   }

   public boolean method4() {
      if (Ref.method3().bridge$getCurrentScreen() instanceof GuiContainerBridge bridge5extension_32) {
         String text3 = TextBridge.getTextContent(TextBridge.asAdventure(bridge5extension_32.bridge$title()));
         if (!this.method3(text3)) {
            return false;
         }

         this.name = ChatFormatting.getTextWithoutFormattingCodes(text3);
         this.itemsKnown = true;
         if (this.field4 == null) {
            this.field4 = new ArrayList<>();
         } else {
            this.field4.clear();
         }

         for (int index4 = 9; index4 < bridge5extension_32.bridge$getLowerChestSizeInventory(); index4++) {
            ItemStackBridge bridgeextension_45 = ((SlotBridge)bridge5extension_32.bridge$inventorySlots().get(index4)).bridge$getItemStack();
            if (this.field4.size() <= index4 - 9) {
               this.field4.add(bridgeextension_45);
            } else {
               this.field4.set(index4 - 9, bridgeextension_45);
            }
         }

         this.field6 = "";
         this.field7 = true;
         return true;
      } else {
         return false;
      }
   }

   public List<SlotBridge> method5() {
      if (!(Ref.method3().bridge$getCurrentScreen() instanceof GuiContainerBridge bridge5extension_32)) {
         return new ArrayList<>();
      } else {
         String text3 = TextBridge.getTextContent(TextBridge.asAdventure(bridge5extension_32.bridge$title()));
         if (!this.method3(text3)) {
            return new ArrayList<>();
         }

         ArrayList list4 = new ArrayList();

         for (int index5 = 9; index5 < bridge5extension_32.bridge$getLowerChestSizeInventory(); index5++) {
            SlotBridge bridge3_186 = (SlotBridge)bridge5extension_32.bridge$inventorySlots().get(index5);
            list4.add(bridge3_186);
         }

         return list4;
      }
   }

   public boolean method6(String text1, boolean flag2) {
      text1 = text1.toLowerCase(Locale.ROOT);
      String text3 = text1 + " - " + flag2;
      if (text3.equals(this.field6)) {
         return this.field5;
      }

      this.field6 = text3;
      this.field5 = false;

      for (ItemStackBridge bridgeextension_45 : this.method1()) {
         if (ItemStackRenderer.method3(bridgeextension_45, text1, flag2)) {
            this.field5 = true;
            break;
         }
      }

      return this.field5;
   }

   public boolean method7() {
      return this.itemsKnown && !this.method1().isEmpty();
   }

   public void method8(List<ItemStackBridge> list1) {
      if (this.field4 == null) {
         this.field4 = new ArrayList<>();
      } else {
         this.field4.clear();
      }

      this.field4.addAll(list1);

      while (this.field4.size() % 9 != 0) {
         this.field4.add(Bridge.method8().method41());
      }

      this.itemsKnown = true;
   }

   public boolean method9() {
      return this.field1.isAnimating() || this.field2.isAnimating();
   }

   @Generated
   public AnimatedValue method10() {
      return this.field1;
   }

   @Generated
   public AnimatedValue method11() {
      return this.field2;
   }

   @Generated
   public void method12(boolean flag1) {
      this.field3 = flag1;
   }

   @Generated
   public boolean method13() {
      return this.field3;
   }

   @Generated
   public int getMenuIndex() {
      return this.menuIndex;
   }

   @Generated
   public boolean isEnderChestPage() {
      return this.isEnderChestPage;
   }

   @Generated
   public void setName(String text1) {
      this.name = text1;
   }

   @Generated
   public String getName() {
      return this.name;
   }
}
