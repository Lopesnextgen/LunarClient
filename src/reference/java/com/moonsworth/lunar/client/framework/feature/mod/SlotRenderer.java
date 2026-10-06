package com.moonsworth.lunar.client.framework.feature.mod;

import com.google.common.collect.Sets;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClickTypeBridge;
import com.moonsworth.lunar.bridge.horsestats.MathHelperBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.inventorymod.InventorySlotUtils;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin.Gui2Extension3;
import com.moonsworth.lunar.client.mod.skyblock.protectitem.SkyblockProtectItem;
import com.moonsworth.lunar.client.mod.player.inventorymod.InventoryMods;
import com.moonsworth.lunar.client.mod.player.slotlocking.SlotLocking;
import com.moonsworth.lunar.client.ui.GuiClipState;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import java.util.Set;
import lombok.Generated;

@VersionGate(min = 1)
public class SlotRenderer extends GuiComponent {
   private final Set<SlotBridge> field2 = Sets.newHashSet();
   private boolean field3;
   private int field4;
   private int field5;
   private boolean field6;
   private ItemStackBridge field7;
   private SlotBridge field8;
   private long field9;

   public SlotRenderer(GuiRenderer mixinhelper1) {
      super(mixinhelper1);
   }

   @Override
   public void end() {
      if (this.field6) {
         if (this.field3 && this.field2.size() > 1 && this.HRROORRCRHHHCCIORROORCIHOHRIHH.method44() instanceof GuiContainerBridge bridge5extension_31) {
            bridge5extension_31.bridge$clickSlot(-999, (this.field5 & 3) << 2, ClickTypeBridge.QUICK_CRAFT);

            for (SlotBridge bridge3_183 : this.field2) {
               int number4 = bridge3_183.bridge$getNumber();
               if (Ref.MC_VERSION >= 6) {
                  number4 = bridge3_183.bridge$getIndex();
               }

               bridge5extension_31.bridge$clickSlot(number4, 1 | (this.field5 & 3) << 2, ClickTypeBridge.QUICK_CRAFT);
            }

            bridge5extension_31.bridge$clickSlot(-999, 2 | (this.field5 & 3) << 2, ClickTypeBridge.QUICK_CRAFT);
         }

         if (!this.method12().method17()) {
            this.field6 = false;
         }

         this.field3 = false;
         this.field2.clear();
      }
   }

   public int method1(String text1, int number2, int number3, List<ItemStackBridge> list4) {
      return this.method2(text1, number2, number3, list4, false);
   }

   public int method2(String text1, int number2, int number3, List<ItemStackBridge> list4, boolean flag5) {
      short number6 = 164;
      int number7 = 18 * list4.size() / 9 + 2;
      byte number8 = 0;
      if (text1 != null) {
         number7 += 10;
         number8 = 10;
      }

      if (this.method3().method5(number2, number3, number6, number7)) {
         return -1;
      }

      int number9 = !flag5 ? this.HRROORRCRHHHCCIORROORCIHOHRIHH.method41(number2, number3, number6, number7) : -1;
      this.HRROORRCRHHHCCIORROORCIHOHRIHH.method37(number2, number3, number6, number7);
      GuiClipState.field1 = false;
      GuiClipState.field3 = true;
      if (text1 != null) {
         this.HRROORRCRHHHCCIORROORCIHOHRIHH.method26(ChatFormatting.DARK_GRAY + text1, number2 + 2, number3 + 2, false);
      }

      for (int index10 = 0; index10 < (int)Math.ceil(list4.size() / 9.0); index10++) {
         for (int index11 = 0; index11 < 9; index11++) {
            int index12 = index11 + index10 * 9;
            if (index12 < list4.size()) {
               int number13 = number2 + 2 + index11 * 18;
               int number14 = number3 + 2 + index10 * 18 + number8;
               if (this.method1().field7) {
                  this.method10().method3(number13, number14, 16, 16);
                  ItemStackBridge bridgeextension_415 = (ItemStackBridge)list4.get(index12);
                  this.method8().method1(bridgeextension_415, number13, number14);
               }
            }
         }
      }

      GuiClipState.reset();

      for (int index16 = 0; index16 < (int)Math.ceil(list4.size() / 9.0); index16++) {
         for (int index17 = 0; index17 < 9; index17++) {
            int index18 = index17 + index16 * 9;
            if (index18 < list4.size()) {
               ItemStackBridge bridgeextension_419 = (ItemStackBridge)list4.get(index18);
               int number20 = number2 + 2 + index17 * 18;
               int number21 = number3 + 2 + index16 * 18 + number8;
               this.method8().method2(bridgeextension_419, number20, number21, true, true);
            }
         }
      }

      return number9;
   }

   public void method3(String text1, int number2, int number3, List<SlotBridge> list4, boolean flag5, boolean flag6) {
      short number7 = 164;
      int number8 = 18 * list4.size() / 9 + 2;
      byte number9 = 0;
      if (text1 != null) {
         number8 += 10;
         number9 = 10;
      }

      if (!this.method3().method5(number2, number3, number7, number8)) {
         if (this.method1().field7) {
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method37(number2, number3, number7, number8);
            if (text1 != null) {
               this.HRROORRCRHHHCCIORROORCIHOHRIHH.method26(ChatFormatting.DARK_GRAY + text1, number2 + 2, number3 + 2, false);
            }
         }

         for (int index10 = 0; index10 < (int)Math.ceil(list4.size() / 9.0); index10++) {
            for (int index11 = 0; index11 < 9; index11++) {
               int index12 = index11 + index10 * 9;
               if (index12 < list4.size()) {
                  SlotBridge bridge3_1813 = (SlotBridge)list4.get(index12);
                  ItemStackBridge bridgeextension_414 = bridge3_1813.bridge$getItemStack();
                  int number15 = number2 + 2 + index11 * 18;
                  int number16 = number3 + 2 + index10 * 18 + number9;
                  if (this.method1().field7) {
                     this.HRROORRCRHHHCCIORROORCIHOHRIHH.method38(number15, number16, 16, 16);
                  }

                  String text17 = null;
                  if (flag5 && this.HRROORRCRHHHCCIORROORCIHOHRIHH.method44() instanceof GuiContainerBridge bridge5extension_318) {
                     ItemStackBridge bridgeextension_427 = bridge5extension_318.bridge$getCursor();
                     if (this.field3 && this.field2.size() > 1 && this.field2.contains(bridge3_1813) && !bridgeextension_427.bridge$isEmpty()) {
                        if (this.field2.size() == 1) {
                           return;
                        }

                        if (this.method4(bridge3_1813, bridgeextension_427, false)) {
                           bridgeextension_414 = bridgeextension_427.bridge$copy();
                           this.method7(
                              this.field5, bridgeextension_414, bridge3_1813.bridge$getItemStack().bridge$isEmpty() ? 0 : bridge3_1813.bridge$getItemStack().bridge$getStackSize()
                           );
                           if (bridgeextension_414.bridge$getStackSize() > bridgeextension_414.bridge$getMaxStackSize()) {
                              text17 = "" + ChatFormatting.YELLOW + bridgeextension_414.bridge$getMaxStackSize();
                              bridgeextension_414.bridge$setStackSize(bridgeextension_414.bridge$getMaxStackSize());
                           }

                           if (bridgeextension_414.bridge$getStackSize() > bridge3_1813.bridge$getInventory().bridge$getInventoryStackLimit()) {
                              text17 = "" + ChatFormatting.YELLOW + bridge3_1813.bridge$getInventory().bridge$getInventoryStackLimit();
                              bridgeextension_414.bridge$setStackSize(bridge3_1813.bridge$getInventory().bridge$getInventoryStackLimit());
                           }
                        } else {
                           this.field2.remove(bridge3_1813);
                           this.method17();
                        }
                     }
                  }

                  int number26 = this.HRROORRCRHHHCCIORROORCIHOHRIHH.method22(bridgeextension_414, number15, number16, true);
                  if (text17 != null) {
                     int number28 = number2 + 17 - this.HRROORRCRHHHCCIORROORCIHOHRIHH.getStringWidth(text17);
                     int number20 = number3 + 9;
                     this.HRROORRCRHHHCCIORROORCIHOHRIHH.method26(text17, number28, number20, true);
                  }

                  if (flag5) {
                     if (this.HRROORRCRHHHCCIORROORCIHOHRIHH.method42(number15, number16, 16, 16)
                        && this.method12().method17()
                        && this.HRROORRCRHHHCCIORROORCIHOHRIHH.method44() instanceof GuiContainerBridge bridge5extension_329) {
                        ItemStackBridge bridgeextension_434 = bridge5extension_329.bridge$getCursor();
                        if (!this.field3 && !bridgeextension_434.bridge$isEmpty()) {
                           this.field3 = true;
                           this.field2.clear();
                           if (Ref.method3().bridge$isMouseButtonDown(0)) {
                              this.field5 = 0;
                           } else if (Ref.method3().bridge$isMouseButtonDown(1)) {
                              this.field5 = 1;
                           }
                        }

                        if (this.field3 && bridgeextension_434.bridge$getStackSize() > this.field2.size() && this.method4(bridge3_1813, bridgeextension_434, false)) {
                           this.field2.add(bridge3_1813);
                           this.method17();
                        }
                     }

                     if (this.field3 && this.field2.contains(bridge3_1813)) {
                        this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number15, number16, 16, 16, -2130706434);
                     }
                  }

                  if (flag5
                     && this.method1().field7
                     && Ref.method3().bridge$getCurrentScreen() instanceof GuiContainerBridge bridge5extension_335) {
                     int number21 = InventorySlotUtils.method3(bridge5extension_335, bridge3_1813);
                     InventoryMods inventorymod22 = Ref.method4().method40().method93();
                     SlotLocking slotlocking23 = inventorymod22.method14();
                     SkyblockProtectItem skyblockprotectitem24 = Ref.method4().method40().method82().method183();
                     if (((Gui2Extension3)Ref.method4().method40().method82().method186().method13().get()).isVisible()) {
                        if (flag6 && inventorymod22.isEnabled() && slotlocking23.isEnabled() && slotlocking23.method11(number21)) {
                           this.method6().method17();
                           this.method16().method24(SlotLocking.field9, number15 + 10, number16, 6, 6, slotlocking23.method14().method14(0.0F));
                        }

                        if (skyblockprotectitem24.isEnabled() && skyblockprotectitem24.method7(bridgeextension_414)) {
                           this.method6().method17();
                           this.method16().method24(SkyblockProtectItem.field8, number15, number16, 6, 6, skyblockprotectitem24.method15().method14(0.0F));
                        }
                     }
                  }

                  if (flag5
                     && this.HRROORRCRHHHCCIORROORCIHOHRIHH.method42(number15, number16, 16, 16)
                     && this.HRROORRCRHHHCCIORROORCIHOHRIHH.method44() instanceof GuiContainerBridge bridge5extension_331) {
                     bridge5extension_331.bridge$setHoveredSlot(bridge3_1813);
                  }

                  if (flag5
                     && number26 != -1
                     && (!this.field3 || this.field2.size() <= 1)
                     && this.HRROORRCRHHHCCIORROORCIHOHRIHH.method44() instanceof GuiContainerBridge bridge5extension_332) {
                     int number38 = bridge3_1813.bridge$getNumber();
                     if (Ref.MC_VERSION >= 6 && flag6) {
                        number38 = index12;
                        number38 += bridge5extension_332.bridge$getLowerChestSizeInventory();
                     }

                     if (this.field8 == bridge3_1813 && Ref.method3().bridge$getSystemTime() - this.field9 < 250L) {
                        if (this.method13().method17()) {
                           if (bridge3_1813.bridge$getInventory() != null && this.field7 != null && !this.field7.bridge$isEmpty()) {
                              for (int index41 = 0; index41 < (int)Math.ceil(list4.size() / 9.0); index41++) {
                                 for (int index43 = 0; index43 < 9; index43++) {
                                    int index44 = index43 + index41 * 9;
                                    if (index44 < list4.size()) {
                                       SlotBridge bridge3_1845 = (SlotBridge)list4.get(index44);
                                       int number25 = bridge3_1845.bridge$getNumber();
                                       if (Ref.MC_VERSION >= 6 && flag6) {
                                          number25 = index44;
                                          number25 += bridge5extension_332.bridge$getLowerChestSizeInventory();
                                       }

                                       if (!bridge3_1845.bridge$getItemStack().bridge$isEmpty() && this.method4(bridge3_1845, this.field7, true)) {
                                          bridge5extension_332.bridge$clickSlot(number25, number26, ClickTypeBridge.QUICK_MOVE);
                                       }
                                    }
                                 }
                              }
                           }
                        } else {
                           bridge5extension_332.bridge$clickSlot(number38, number26, ClickTypeBridge.PICKUP_ALL);
                        }

                        this.field9 = 0L;
                     } else {
                        ClickTypeBridge bridgetype_1340 = ClickTypeBridge.PICKUP;
                        if (LcuiScreen.isShiftKeyDown()) {
                           bridgetype_1340 = ClickTypeBridge.QUICK_MOVE;
                        }

                        ItemStackBridge bridgeextension_442 = bridge5extension_332.bridge$getCursor();
                        if (!bridgeextension_442.bridge$isEmpty()) {
                           this.field7 = bridge3_1813.bridge$getItemStack().bridge$copy();
                           this.field8 = bridge3_1813;
                        }

                        bridge5extension_332.bridge$clickSlot(number38, number26, bridgetype_1340);
                        this.field9 = Ref.method3().bridge$getSystemTime();
                     }
                  }
               }
            }
         }
      }
   }

   private boolean method4(SlotBridge bridge3_181, ItemStackBridge bridgeextension_42, boolean flag3) {
      boolean flag4 = bridge3_181 == null || bridge3_181.bridge$getItemStack().bridge$isEmpty();
      if (bridge3_181 != null
         && !bridge3_181.bridge$getItemStack().bridge$isEmpty()
         && bridgeextension_42 != null
         && bridgeextension_42.bridge$isItemEqual(bridge3_181.bridge$getItemStack())
         && this.method5(bridge3_181.bridge$getItemStack(), bridgeextension_42)) {
         flag4 |= bridge3_181.bridge$getItemStack().bridge$getStackSize() + (flag3 ? 0 : bridgeextension_42.bridge$getStackSize()) <= bridgeextension_42.bridge$getMaxStackSize();
      }

      return flag4;
   }

   private boolean method5(ItemStackBridge bridgeextension_41, ItemStackBridge bridgeextension_42) {
      return bridgeextension_41 == null && bridgeextension_42 == null
         || bridgeextension_41 != null
            && bridgeextension_42 != null
            && (bridgeextension_41.bridge$getTagCompound() != null || bridgeextension_42.bridge$getTagCompound() == null)
            && (bridgeextension_41.bridge$getTagCompound() == null || bridgeextension_41.bridge$getTagCompound().equals(bridgeextension_42.bridge$getTagCompound()));
   }

   private void method17() {
      if (this.HRROORRCRHHHCCIORROORCIHOHRIHH.method44() instanceof GuiContainerBridge bridge5extension_31) {
         ItemStackBridge bridgeextension_47 = bridge5extension_31.bridge$getCursor();
         if (!bridgeextension_47.bridge$isEmpty() && this.field3) {
            this.field4 = bridgeextension_47.bridge$getStackSize();

            for (SlotBridge bridge3_184 : this.field2) {
               ItemStackBridge bridgeextension_45 = bridgeextension_47.bridge$copy();
               int number6 = bridge3_184.bridge$getItemStack().bridge$getStackSize();
               this.method7(this.field5, bridgeextension_45, number6);
               if (bridgeextension_45.bridge$getStackSize() > bridgeextension_45.bridge$getMaxStackSize()) {
                  bridgeextension_45.bridge$setStackSize(bridgeextension_45.bridge$getMaxStackSize());
               }

               if (bridgeextension_45.bridge$getStackSize() > bridge3_184.bridge$getInventory().bridge$getInventoryStackLimit()) {
                  bridgeextension_45.bridge$setStackSize(bridge3_184.bridge$getInventory().bridge$getInventoryStackLimit());
               }

               this.field4 = this.field4 - (bridgeextension_45.bridge$getStackSize() - number6);
            }
         }
      }
   }

   private void method7(int number1, ItemStackBridge bridgeextension_42, int number3) {
      switch (number1) {
         case 0:
            bridgeextension_42.bridge$setStackSize((int)MathHelperBridge.method3((float)bridgeextension_42.bridge$getStackSize() / this.field2.size()));
            break;
         case 1:
            bridgeextension_42.bridge$setStackSize(1);
      }

      bridgeextension_42.bridge$setStackSize(bridgeextension_42.bridge$getStackSize() + number3);
   }

   public int method18() {
      return this.field3 && this.field2.size() > 1 ? this.field4 : -1;
   }

   @Generated
   public boolean method19() {
      return this.field3;
   }

   @Generated
   public void method10(boolean flag1) {
      this.field6 = flag1;
   }
}
