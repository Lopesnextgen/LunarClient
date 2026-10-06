package com.moonsworth.lunar.client.framework.feature.mod;

import com.google.common.collect.Sets;
import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ContainerClickType;
import com.moonsworth.lunar.bridge.horsestats.MathHelperBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.inventorymod.Inventorymod;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin.Gui2Extension3;
import com.moonsworth.lunar.client.mod.skyblock.protectitem.SkyblockProtectItem;
import com.moonsworth.lunar.client.mod.player.inventorymod.InventoryMods;
import com.moonsworth.lunar.client.mod.player.slotlocking.SlotLocking;
import com.moonsworth.lunar.client.util.ThreadModuleDump62;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import java.util.Set;
import lombok.Generated;

@Annotation2(min = 1)
public class MixinHelper23 extends MixinHelper2_3 {
   private final Set<Bridge3_18> quickCraftSlots = Sets.newHashSet();
   private boolean quickCrafting;
   private int quickCraftRemaining;
   private int quickCraftButton;
   private boolean quickCraftPending;
   private ItemStackBridge lastClickedStack;
   private Bridge3_18 lastClickedSlot;
   private long lastClickTime;

   public MixinHelper23(MixinHelper var1) {
      super(var1);
   }

   @Override
   public void end() {
      if (this.quickCraftPending) {
         if (this.quickCrafting && this.quickCraftSlots.size() > 1 && this.field1.method44() instanceof Bridge5Extension_3 var1) {
            var1.bridge$clickSlot(-999, (this.quickCraftButton & 3) << 2, ContainerClickType.QUICK_CRAFT);

            for (Bridge3_18 var3 : this.quickCraftSlots) {
               int var4 = var3.bridge$getNumber();
               if (ThreadModuleDump63.MC_VERSION >= 6) {
                  var4 = var3.bridge$getIndex();
               }

               var1.bridge$clickSlot(var4, 1 | (this.quickCraftButton & 3) << 2, ContainerClickType.QUICK_CRAFT);
            }

            var1.bridge$clickSlot(-999, 2 | (this.quickCraftButton & 3) << 2, ContainerClickType.QUICK_CRAFT);
         }

         if (!this.method12().method17()) {
            this.quickCraftPending = false;
         }

         this.quickCrafting = false;
         this.quickCraftSlots.clear();
      }
   }

   public int method1(String var1, int var2, int var3, List<ItemStackBridge> var4) {
      return this.drawSlots(var1, var2, var3, var4, false);
   }

   public int drawSlots(String var1, int var2, int var3, List<ItemStackBridge> var4, boolean var5) {
      short var6 = 164;
      int var7 = 18 * var4.size() / 9 + 2;
      byte var8 = 0;
      if (var1 != null) {
         var7 += 10;
         var8 = 10;
      }

      if (this.method3().method5(var2, var3, var6, var7)) {
         return -1;
      }

      int var9 = !var5 ? this.field1.method41(var2, var3, var6, var7) : -1;
      this.field1.method37(var2, var3, var6, var7);
      ThreadModuleDump62.field1 = false;
      ThreadModuleDump62.field3 = true;
      if (var1 != null) {
         this.field1.method26(AdventureChatFormatting.DARK_GRAY + var1, var2 + 2, var3 + 2, false);
      }

      for (int var10 = 0; var10 < (int)Math.ceil(var4.size() / 9.0); var10++) {
         for (int var11 = 0; var11 < 9; var11++) {
            int var12 = var11 + var10 * 9;
            if (var12 < var4.size()) {
               int var13 = var2 + 2 + var11 * 18;
               int var14 = var3 + 2 + var10 * 18 + var8;
               if (this.method1().field7) {
                  this.method10().method3(var13, var14, 16, 16);
                  ItemStackBridge var15 = (ItemStackBridge)var4.get(var12);
                  this.method8().method1(var15, var13, var14);
               }
            }
         }
      }

      ThreadModuleDump62.reset();

      for (int var16 = 0; var16 < (int)Math.ceil(var4.size() / 9.0); var16++) {
         for (int var17 = 0; var17 < 9; var17++) {
            int var18 = var17 + var16 * 9;
            if (var18 < var4.size()) {
               ItemStackBridge var19 = (ItemStackBridge)var4.get(var18);
               int var20 = var2 + 2 + var17 * 18;
               int var21 = var3 + 2 + var16 * 18 + var8;
               this.method8().method2(var19, var20, var21, true, true);
            }
         }
      }

      return var9;
   }

   public void method3(String var1, int var2, int var3, List<Bridge3_18> var4, boolean var5, boolean var6) {
      short var7 = 164;
      int var8 = 18 * var4.size() / 9 + 2;
      byte var9 = 0;
      if (var1 != null) {
         var8 += 10;
         var9 = 10;
      }

      if (!this.method3().method5(var2, var3, var7, var8)) {
         if (this.method1().field7) {
            this.field1.method37(var2, var3, var7, var8);
            if (var1 != null) {
               this.field1.method26(AdventureChatFormatting.DARK_GRAY + var1, var2 + 2, var3 + 2, false);
            }
         }

         for (int var10 = 0; var10 < (int)Math.ceil(var4.size() / 9.0); var10++) {
            for (int var11 = 0; var11 < 9; var11++) {
               int var12 = var11 + var10 * 9;
               if (var12 < var4.size()) {
                  Bridge3_18 var13 = (Bridge3_18)var4.get(var12);
                  ItemStackBridge var14 = var13.bridge$getItemStack();
                  int var15 = var2 + 2 + var11 * 18;
                  int var16 = var3 + 2 + var10 * 18 + var9;
                  if (this.method1().field7) {
                     this.field1.method38(var15, var16, 16, 16);
                  }

                  String var17 = null;
                  if (var5 && this.field1.method44() instanceof Bridge5Extension_3 var18) {
                     ItemStackBridge var27 = var18.bridge$getCursor();
                     if (this.quickCrafting && this.quickCraftSlots.size() > 1 && this.quickCraftSlots.contains(var13) && !var27.bridge$isEmpty()) {
                        if (this.quickCraftSlots.size() == 1) {
                           return;
                        }

                        if (this.canStackInto(var13, var27, false)) {
                           var14 = var27.bridge$copy();
                           this.distributeCraftSize(
                              this.quickCraftButton, var14, var13.bridge$getItemStack().bridge$isEmpty() ? 0 : var13.bridge$getItemStack().bridge$getStackSize()
                           );
                           if (var14.bridge$getStackSize() > var14.bridge$getMaxStackSize()) {
                              var17 = "" + AdventureChatFormatting.YELLOW + var14.bridge$getMaxStackSize();
                              var14.bridge$setStackSize(var14.bridge$getMaxStackSize());
                           }

                           if (var14.bridge$getStackSize() > var13.bridge$getInventory().bridge$getInventoryStackLimit()) {
                              var17 = "" + AdventureChatFormatting.YELLOW + var13.bridge$getInventory().bridge$getInventoryStackLimit();
                              var14.bridge$setStackSize(var13.bridge$getInventory().bridge$getInventoryStackLimit());
                           }
                        } else {
                           this.quickCraftSlots.remove(var13);
                           this.recalculateQuickCraft();
                        }
                     }
                  }

                  int var26 = this.field1.method22(var14, var15, var16, true);
                  if (var17 != null) {
                     int var28 = var2 + 17 - this.field1.getStringWidth(var17);
                     int var20 = var3 + 9;
                     this.field1.method26(var17, var28, var20, true);
                  }

                  if (var5) {
                     if (this.field1.method42(var15, var16, 16, 16)
                        && this.method12().method17()
                        && this.field1.method44() instanceof Bridge5Extension_3 var29) {
                        ItemStackBridge var34 = var29.bridge$getCursor();
                        if (!this.quickCrafting && !var34.bridge$isEmpty()) {
                           this.quickCrafting = true;
                           this.quickCraftSlots.clear();
                           if (ThreadModuleDump63.method3().bridge$isMouseButtonDown(0)) {
                              this.quickCraftButton = 0;
                           } else if (ThreadModuleDump63.method3().bridge$isMouseButtonDown(1)) {
                              this.quickCraftButton = 1;
                           }
                        }

                        if (this.quickCrafting && var34.bridge$getStackSize() > this.quickCraftSlots.size() && this.canStackInto(var13, var34, false)) {
                           this.quickCraftSlots.add(var13);
                           this.recalculateQuickCraft();
                        }
                     }

                     if (this.quickCrafting && this.quickCraftSlots.contains(var13)) {
                        this.field1.method43(var15, var16, 16, 16, -2130706434);
                     }
                  }

                  if (var5
                     && this.method1().field7
                     && ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension_3 var35) {
                     int var21 = Inventorymod.method3(var35, var13);
                     InventoryMods var22 = ThreadModuleDump63.method4().method40().method93();
                     SlotLocking var23 = var22.method14();
                     SkyblockProtectItem var24 = ThreadModuleDump63.method4().method40().method82().method183();
                     if (((Gui2Extension3)ThreadModuleDump63.method4().method40().method82().method186().method13().get()).isVisible()) {
                        if (var6 && var22.isEnabled() && var23.isEnabled() && var23.method11(var21)) {
                           this.method6().method17();
                           this.method16().method24(SlotLocking.field9, var15 + 10, var16, 6, 6, var23.method14().method14(0.0F));
                        }

                        if (var24.isEnabled() && var24.method7(var14)) {
                           this.method6().method17();
                           this.method16().method24(SkyblockProtectItem.field8, var15, var16, 6, 6, var24.method15().method14(0.0F));
                        }
                     }
                  }

                  if (var5
                     && this.field1.method42(var15, var16, 16, 16)
                     && this.field1.method44() instanceof Bridge5Extension_3 var31) {
                     var31.bridge$setHoveredSlot(var13);
                  }

                  if (var5
                     && var26 != -1
                     && (!this.quickCrafting || this.quickCraftSlots.size() <= 1)
                     && this.field1.method44() instanceof Bridge5Extension_3 var32) {
                     int var38 = var13.bridge$getNumber();
                     if (ThreadModuleDump63.MC_VERSION >= 6 && var6) {
                        var38 = var12;
                        var38 += var32.bridge$getLowerChestSizeInventory();
                     }

                     if (this.lastClickedSlot == var13 && ThreadModuleDump63.method3().bridge$getSystemTime() - this.lastClickTime < 250L) {
                        if (this.method13().method17()) {
                           if (var13.bridge$getInventory() != null && this.lastClickedStack != null && !this.lastClickedStack.bridge$isEmpty()) {
                              for (int var41 = 0; var41 < (int)Math.ceil(var4.size() / 9.0); var41++) {
                                 for (int var43 = 0; var43 < 9; var43++) {
                                    int var44 = var43 + var41 * 9;
                                    if (var44 < var4.size()) {
                                       Bridge3_18 var45 = (Bridge3_18)var4.get(var44);
                                       int var25 = var45.bridge$getNumber();
                                       if (ThreadModuleDump63.MC_VERSION >= 6 && var6) {
                                          var25 = var44;
                                          var25 += var32.bridge$getLowerChestSizeInventory();
                                       }

                                       if (!var45.bridge$getItemStack().bridge$isEmpty() && this.canStackInto(var45, this.lastClickedStack, true)) {
                                          var32.bridge$clickSlot(var25, var26, ContainerClickType.QUICK_MOVE);
                                       }
                                    }
                                 }
                              }
                           }
                        } else {
                           var32.bridge$clickSlot(var38, var26, ContainerClickType.PICKUP_ALL);
                        }

                        this.lastClickTime = 0L;
                     } else {
                        ContainerClickType var40 = ContainerClickType.PICKUP;
                        if (LcuiScreen.isShiftKeyDown()) {
                           var40 = ContainerClickType.QUICK_MOVE;
                        }

                        ItemStackBridge var42 = var32.bridge$getCursor();
                        if (!var42.bridge$isEmpty()) {
                           this.lastClickedStack = var13.bridge$getItemStack().bridge$copy();
                           this.lastClickedSlot = var13;
                        }

                        var32.bridge$clickSlot(var38, var26, var40);
                        this.lastClickTime = ThreadModuleDump63.method3().bridge$getSystemTime();
                     }
                  }
               }
            }
         }
      }
   }

   private boolean canStackInto(Bridge3_18 var1, ItemStackBridge var2, boolean var3) {
      boolean var4 = var1 == null || var1.bridge$getItemStack().bridge$isEmpty();
      if (var1 != null
         && !var1.bridge$getItemStack().bridge$isEmpty()
         && var2 != null
         && var2.bridge$isItemEqual(var1.bridge$getItemStack())
         && this.matchesItemData(var1.bridge$getItemStack(), var2)) {
         var4 |= var1.bridge$getItemStack().bridge$getStackSize() + (var3 ? 0 : var2.bridge$getStackSize()) <= var2.bridge$getMaxStackSize();
      }

      return var4;
   }

   private boolean matchesItemData(ItemStackBridge var1, ItemStackBridge var2) {
      return var1 == null && var2 == null
         || var1 != null
            && var2 != null
            && (var1.bridge$getTagCompound() != null || var2.bridge$getTagCompound() == null)
            && (var1.bridge$getTagCompound() == null || var1.bridge$getTagCompound().equals(var2.bridge$getTagCompound()));
   }

   private void recalculateQuickCraft() {
      if (this.field1.method44() instanceof Bridge5Extension_3 var1) {
         ItemStackBridge var7 = var1.bridge$getCursor();
         if (!var7.bridge$isEmpty() && this.quickCrafting) {
            this.quickCraftRemaining = var7.bridge$getStackSize();

            for (Bridge3_18 var4 : this.quickCraftSlots) {
               ItemStackBridge var5 = var7.bridge$copy();
               int var6 = var4.bridge$getItemStack().bridge$getStackSize();
               this.distributeCraftSize(this.quickCraftButton, var5, var6);
               if (var5.bridge$getStackSize() > var5.bridge$getMaxStackSize()) {
                  var5.bridge$setStackSize(var5.bridge$getMaxStackSize());
               }

               if (var5.bridge$getStackSize() > var4.bridge$getInventory().bridge$getInventoryStackLimit()) {
                  var5.bridge$setStackSize(var4.bridge$getInventory().bridge$getInventoryStackLimit());
               }

               this.quickCraftRemaining = this.quickCraftRemaining - (var5.bridge$getStackSize() - var6);
            }
         }
      }
   }

   private void distributeCraftSize(int var1, ItemStackBridge var2, int var3) {
      switch (var1) {
         case 0:
            var2.bridge$setStackSize((int)MathHelperBridge.method3((float)var2.bridge$getStackSize() / this.quickCraftSlots.size()));
            break;
         case 1:
            var2.bridge$setStackSize(1);
      }

      var2.bridge$setStackSize(var2.bridge$getStackSize() + var3);
   }

   public int method18() {
      return this.quickCrafting && this.quickCraftSlots.size() > 1 ? this.quickCraftRemaining : -1;
   }

   @Generated
   public boolean method19() {
      return this.quickCrafting;
   }

   @Generated
   public void method10(boolean var1) {
      this.quickCraftPending = var1;
   }
}
