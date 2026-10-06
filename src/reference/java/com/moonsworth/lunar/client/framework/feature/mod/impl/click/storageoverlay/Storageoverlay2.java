package com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ContainerClickType;
import com.moonsworth.lunar.bridge.Bridge_7;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.mod.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.framework.feature.mod.MixinHelper;
import com.moonsworth.lunar.client.framework.feature.mod.ModuleBase;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click4;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler23;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin.Gui2Extension4;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin.Gui2Extension6;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.mod.skyblock.storageoverlay.StorageOverlay;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.concurrent.SupplierExtension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.List;

@Annotation2(min = 33)
public class Storageoverlay2 extends ModuleBase {
   private final Click4 field2 = new Click4(0.0, Click4.Type.SIN_OUT);
   private final Click4 field3 = new Click4(0.0, Click4.Type.SIN_IN_OUT);
   private final Click4 field4 = new Click4(0.0, Click4.Type.SIN_IN_OUT);
   private final Click4 field5 = new Click4(0.0, Click4.Type.SIN_IN_OUT);
   private final Click4 field6 = new Click4(1.0, Click4.Type.SIN_IN_OUT);
   private final SupplierExtension<ItemStackBridge> field7 = SupplierExtension.lazy(() -> Bridge.method8().method38(Bridge.method28().method98()));
   private final SupplierExtension<ItemStackBridge> field8 = SupplierExtension.lazy(() -> Bridge.method8().method39(Bridge.method34().method23()));
   private final StorageOverlay field9;
   private final GuiRewindhandlersHandler23 field10;
   private int field11 = 0;
   private Storageoverlay field12 = null;
   private long field13 = 0L;
   private int field14 = 0;
   private String field15 = "";
   private int field16 = 0;
   private boolean field17 = false;
   private long field18 = 0L;
   private boolean field19 = false;

   public Storageoverlay2(StorageOverlay var1, GuiRewindhandlersHandler23 var2) {
      this.field9 = var1;
      this.field10 = var2;
   }

   public static boolean method1() {
      GuiRewindhandlersHandler2 var0 = com.moonsworth.lunar.client.framework.listener.DynamicListener.method9(GuiRewindhandlersHandler2.class)
         .orElse(null);
      return var0 != null && var0.isScreenActive() && var0.method9() instanceof Storageoverlay2
         ? !ThreadModuleDump63.method4().method40().method82().method186().method13().get().isInteractionsLocked()
         : false;
   }

   @Override
   public boolean method2(Bridge5Extension6 var1) {
      return this.method20(var1) && this.field17 ? false : this.method20(var1) || this.method21(var1) || this.method22(var1);
   }

   @Override
   public void method7() {
      this.field3.setEnabled(this.field9.method14().get());
      this.field5.setEnabled(this.field9.method14().get());
      this.field4.setEnabled(this.field9.method14().get());
      this.field6.setEnabled(this.field9.method14().get());

      for (Storageoverlay var2 : this.field10.method6()) {
         if (var2 != null) {
            var2.method10().method6(this.field9.method14().get());
            var2.method11().method6(this.field9.method14().get());
         }
      }

      if (this.IIIHRCOIICCCRCRHCOCRRIHORHORIH % 3 == 0) {
         List var3 = this.field10.method6();
         if (this.field16 >= var3.size()) {
            this.field16 = 0;
         }

         Storageoverlay var4 = (Storageoverlay)var3.get(this.field16);
         if (var4 != null) {
            MixinHelper.field1.method49("StorageOverlay", "menu-" + var4.isEnderChestPage() + "-" + var4.getMenuIndex());
         }

         this.field16++;
      }

      ThreadModuleDump63.method3().bridge$setRepeatEventsEnabled(true);
      super.method7();
   }

   @Override
   public void method8() {
      Bridge5Extension6 var1 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      if (!(var1 instanceof Bridge5Extension_3 var2)) {
         this.field17 = false;
         this.field12 = null;
      } else {
         for (Storageoverlay var4 : this.field10.method6()) {
            if (var4 != null && var4.method3(this.method11(var1))) {
               MixinHelper.field1.method49("StorageOverlay", "menu-" + var4.isEnderChestPage() + "-" + var4.getMenuIndex());
            }
         }

         if (!this.method20(var1) && this.IIIHRCOIICCCRCRHCOCRRIHORHORIH - this.field18 > 40L) {
            this.field17 = false;
         }

         if (this.IIIHRCOIICCCRCRHCOCRRIHORHORIH - this.field13 > 40L) {
            if (this.method20(var1)) {
               if (this.field12 == null) {
                  return;
               }

               this.field12.method12(true);
               this.field12 = null;
               this.field13 = this.IIIHRCOIICCCRCRHCOCRRIHORHORIH;
            } else {
               String var7 = this.method11(var1);
               if (var7 != null) {
                  if (this.field12 != null && this.field12.method3(var7)) {
                     return;
                  }

                  int var8 = 0;

                  for (Storageoverlay var6 : this.field10.method6()) {
                     var8++;
                     if (var6 != null && var6.method3(var7)) {
                        this.field12 = var6;
                        this.field13 = this.IIIHRCOIICCCRCRHCOCRRIHORHORIH;
                        this.method5(var8);
                     }
                  }
               }
            }
         }

         if (this.field12 != null
            && this.IIIHRCOIICCCRCRHCOCRRIHORHORIH - this.field13 < 5L
            && this.field9.method17().get() == Gui2Extension4.FULLSCREEN_2
            && this.field5.getValue2() != 0.0) {
            this.method5(this.field10.method6().indexOf(this.field12));
         }

         super.method8();
      }
   }

   private void method5(int var1) {
      double var2 = this.method14();
      double var4 = LcuiScreen.method151().getScaledHeight() / var2;
      byte var6 = 102;
      int var7 = -this.field11;
      if (this.field9.method17().get() == Gui2Extension4.FULLSCREEN_2) {
         byte var8 = 18;
         byte var9 = 84;
         int var10 = (int)this.field5.getValue2();
         int var11 = var10 + var9 + var8;
         int var12 = var10 - var6 - var8 * 2;
         if (var7 > var12) {
            this.field11 = -var12;
         } else if (var7 + var4 < var11) {
            this.field11 = (int)(-(var11 - var4));
         }
      } else if (this.field9.method17().get() == Gui2Extension4.WINDOWED) {
         byte var13 = 9;
         int var14 = this.field9.method15().get().getChestsPerRow();
         int var15 = this.field9.method16().get().getHeight();
         int var16 = (int)((var6 + var13) * Math.ceil((float)var1 / var14) + var13);
         int var17 = var16 - var13 * 2 - var6;
         if (var7 > var17) {
            this.field11 = -var17;
         } else if (var7 + var15 < var16) {
            this.field11 = -(var16 - var15);
         }
      }
   }

   @Override
   public void method2(Bridge5Extension6 var1, MixinHelper_4 var2, int var3, int var4, float var5) {
      this.method6(var2);
      if (var1 instanceof Bridge5Extension_3 var6) {
         double var7 = this.method14();
         double var9 = LcuiScreen.method151().getScaledWidth() / var7;
         var2.push();
         var2.method40((float)var7, (float)var7);
         var3 = (int)(var3 / var7);
         var4 = (int)(var4 / var7);
         MixinHelper.field1.setTheme(this.field9.method19().get().getTheme());
         MixinHelper.field1.method1("StorageOverlay", var1, var2, var3, var4, true);
         MixinHelper.field1.method3();
         MixinHelper.field1.method20(var7, var7, 1.0);
         if (this.field19) {
            MixinHelper.field1.method9("textinput", "StorageOverlay-Search");
            this.field19 = false;
         }

         this.method7(var1, var6);
         if (this.method23()) {
            MixinHelper.field1.method3();
            MixinHelper.field1.method16(false);
            MixinHelper.field1.method11(false);
         }

         if (this.field9.method17().get() != Gui2Extension4.FULLSCREEN && this.field9.method17().get() != Gui2Extension4.FULLSCREEN_2) {
            this.method13();
         } else {
            this.method10();
         }

         if (this.method23()) {
            MixinHelper.field1.method4();
         }

         this.method7(var1, var6);
         MixinHelper.field1.end();
         var2.pop();
      }
   }

   private void method7(Bridge5Extension6 var1, Bridge5Extension_3 var2) {
      if (!this.method23()) {
         if (MixinHelper.field1.method34(10, 10, 16, 16) != -1) {
            DriverViewportLegacy.method50().method19(DriverOverlayRegistryLegacy.field5);
         }

         MixinHelper.field1.method21(this.field7.get(), 10, 10);
         if (MixinHelper.field1.method42(8, 8, 20, 20)) {
            MixinHelper.field1.method54(method12("openOptions"));
         }

         if (MixinHelper.field1.method34(31, 10, 16, 16) != -1) {
            this.field18 = this.IIIHRCOIICCCRCRHCOCRRIHORHORIH;
            this.field17 = true;
            if (!this.method20(var1)) {
               var2.bridge$clickSlot(1, 0, ContainerClickType.PICKUP);
               this.field12 = null;
            }
         }

         MixinHelper.field1.method21(this.field8.get(), 31, 10);
         if (MixinHelper.field1.method42(29, 8, 20, 20)) {
            MixinHelper.field1.method54(method12("editBps"));
         }
      }
   }

   private void method10() {
      double var1 = this.method14();
      double var3 = LcuiScreen.method151().getScaledWidth() / var1;
      double var5 = LcuiScreen.method151().getScaledHeight() / var1;
      Bridge5Extension6 var7 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      Bridge5Extension_3 var8 = (Bridge5Extension_3)var7;
      ItemStackBridge var9 = var8.bridge$getCursor();
      boolean var10 = !var9.bridge$isEmpty();
      String var11 = this.method11(var7);
      if (this.method20(var7) && this.field12 != null
         || this.field12 == null && !this.method20(var7)
         || this.field12 != null && !this.method20(var7) && !this.field12.method3(var11)) {
         var10 = false;
      }

      if (var10) {
         MixinHelper.field1.method11(false);
      }

      boolean var12 = MixinHelper.field1.method8("textinput", "StorageOverlay-Search");
      int var13 = MixinHelper.field1.getStringWidth("Search!") + 2;
      this.field6.animateTo(var12 ? 3.0 : 1.0, 200L);
      var13 = (int)(var13 * this.field6.getValue());
      MixinHelper.field1.method3();
      MixinHelper.field1.method12(false);
      String var14 = MixinHelper.field1.method45("StorageOverlay-Search", (int)(var3 - var13 - 10.0), 10, var13, 10, "Search!", true);
      MixinHelper.field1.method4();
      if (!var14.isEmpty()) {
         MixinHelper.field1.method14(var14);
         MixinHelper.field1.method15(this.field9.method23().get());
      }

      if (!var14.equals(this.field15)) {
         for (Storageoverlay var16 : this.field10.method6()) {
            if (var16 != null) {
               MixinHelper.field1.method50("menu-" + var16.isEnderChestPage() + "-" + var16.getMenuIndex());
            }
         }
      }

      Storageoverlay var41 = this.field12;
      if (var41 != null) {
         MixinHelper.field1.method3();
         MixinHelper.field1.method16(false);
         MixinHelper.field1.method11(false);
         MixinHelper.field1.method17(false);
      }

      short var42 = 164;
      byte var17 = 102;
      byte var18 = 18;
      int var19 = (int)((var3 - (this.field9.method17().get() == Gui2Extension4.FULLSCREEN_2 ? 40 : 0)) / (var42 + var18));
      int var20 = (int)((var3 - var19 * (var42 + var18) + var18) / 2.0);
      int var21 = (int)this.field2.getValue();
      int var22 = (int)((var17 + var18) * Math.ceil((float)this.field14 / var19) - var5 + var18);
      if (this.field9.method17().get() == Gui2Extension4.FULLSCREEN_2) {
         byte var23 = 84;
         var22 += var23;
      }

      int var43 = this.field11;
      if (var43 < -var22) {
         var43 = -var22;
      }

      if (var43 > 0) {
         var43 = 0;
      }

      this.field2.animateTo(var43, 250L);
      int var24 = 0;
      this.field14 = 0;
      MixinHelper.field1.method17(false);

      for (Storageoverlay var26 : this.field10.method6()) {
         if (var26 != null) {
            if (!var14.isEmpty()) {
               if (!var26.method6(var14, this.field9.method23().get())) {
                  continue;
               }

               var26.method12(true);
            }

            if (!this.field15.isEmpty()) {
               var26.method12(true);
            }

            this.field14++;
            if (var26 == var41) {
               var24++;
            } else {
               int var27 = var24 % var19 * (var42 + var18) + var20;
               int var28 = var21 + var18 + var24 / var19 * (var17 + var18);
               if (var26.method13()) {
                  var26.method12(false);
                  var26.method10().method1(var27, 250L);
                  var26.method11().method1(var28 - var21, 250L);
               }

               boolean var29 = var26.method10().method3() || var26.method11().method3();
               if (var29) {
                  var24++;
               } else {
                  short var31 = 164;
                  int var32 = 38;
                  int var30;
                  if (var26.method7()) {
                     var32 = 18 * var26.method1().size() / 9 + 12;
                     if (MixinHelper.field1.method42(var27 - 2, var28 - 2, var31 + 4, var32 + 4)) {
                        MixinHelper.field1.method50("menu-" + var26.isEnderChestPage() + "-" + var26.getMenuIndex());
                     }

                     MixinHelper.field1.method47("menu-" + var26.isEnderChestPage() + "-" + var26.getMenuIndex(), var27 - 2, var28 - 2, var31 + 4, var32 + 4);
                     var30 = MixinHelper.field1.method31(var26.getName(), var27, var28, var26.method1());
                  } else {
                     if (MixinHelper.field1.method42(var27 - 2, var28 - 2, var31 + 4, var32 + 4)) {
                        MixinHelper.field1.method50("menu-" + var26.isEnderChestPage() + "-" + var26.getMenuIndex());
                     }

                     MixinHelper.field1.method47("menu-" + var26.isEnderChestPage() + "-" + var26.getMenuIndex(), var27 - 2, var28 - 2, var31 + 4, var32 + 4);
                     var30 = MixinHelper.field1.method41(var27, var28, var31, var32);
                     MixinHelper.field1.method37(var27, var28, var31, var32);
                     MixinHelper.field1.method24("§f" + method12("clickToLoad"), var27 + var31 / 2, var28 + var32 / 2, true);
                  }

                  MixinHelper.field1.method48("menu-" + var26.isEnderChestPage() + "-" + var26.getMenuIndex());
                  if (var30 != -1 && this.method20(var7)) {
                     this.field12 = var26;
                     if (var26.isEnderChestPage()) {
                        ThreadModuleDump63.method7().bridge$sendCommand("/enderchest " + var26.getMenuIndex());
                     } else {
                        ThreadModuleDump63.method7().bridge$sendCommand("/backpack " + var26.getMenuIndex());
                     }

                     this.field13 = this.IIIHRCOIICCCRCRHCOCRRIHORHORIH;
                  }

                  var26.method10().method1(var27, 0L);
                  var26.method11().method1(var28 - var21, 0L);
                  var24++;
               }
            }
         }
      }

      var24 = 0;

      for (Storageoverlay var47 : this.field10.method6()) {
         if (var47 != null && (var14.isEmpty() || var47.method6(var14, this.field9.method23().get()))) {
            if (var47 == var41) {
               var24++;
            } else {
               boolean var49 = var47.method10().method3() || var47.method11().method3();
               if (!var49) {
                  var24++;
               } else {
                  int var51 = (int)var47.method10().getValue();
                  int var54 = (int)var47.method11().getValue() + var21;
                  short var57 = 164;
                  int var59 = 18 * var47.method1().size() / 9 + 12;
                  if (MixinHelper.field1.method42(var51 - 2, var54 - 2, var57 + 4, var59 + 4)) {
                     MixinHelper.field1.method50("menu-" + var47.isEnderChestPage() + "-" + var47.getMenuIndex());
                  }

                  MixinHelper.field1.method47("menu-" + var47.isEnderChestPage() + "-" + var47.getMenuIndex(), var51 - 2, var54 - 2, var57 + 4, var59 + 4);
                  MixinHelper.field1.method31(var47.getName(), var51, var54, var47.method1());
                  MixinHelper.field1.method48("menu-" + var47.isEnderChestPage() + "-" + var47.getMenuIndex());
                  var24++;
               }
            }
         }
      }

      MixinHelper.field1.method17(true);
      int var46 = (int)(119.0 * this.field3.getValue()) * 16777216;
      MixinHelper.field1.method43(0, 0, (int)var3, (int)var5, var46);
      if (var41 != null) {
         MixinHelper.field1.method11(!var10);
         MixinHelper.field1.method17(true);
         MixinHelper.field1.method4();
         if (!var41.method3(var11)) {
            MixinHelper.field1.method16(false);
         }

         MixinHelper.field1.method3();
         this.field3.animateTo(1.0, 250L);
         byte var48 = 84;
         int var50 = 18 * (var41.method1().size() / 9) + 12;

         for (Storageoverlay var55 : this.field10.method6()) {
            if (var55 != null && var55 == this.field12 && !var55.method7()) {
               var50 = 38;
            }
         }

         int var53 = (int)((var3 - var42) / 2.0);
         int var56 = (int)((var5 - var50 - var48 + 6.0) / 2.0);
         if (this.field9.method17().get() != Gui2Extension4.FULLSCREEN_2) {
            var41.method10().method1(var53, 250L);
            var41.method11().method1(var56 - var21, 250L);
         }

         int var58 = (int)var41.method10().getValue();
         int var60 = (int)var41.method11().getValue() + var21;
         if (this.field9.method17().get() == Gui2Extension4.FULLSCREEN_2) {
            var53 = var58;
            var56 = var60;
         }

         for (Storageoverlay var33 : this.field10.method6()) {
            if (var33 != null && var33 == this.field12) {
               if (var33.method7()) {
                  if (var33.method3(var11) && !var33.method9()) {
                     List var66 = var33.method5();
                     MixinHelper.field1.method33(var33.getName(), var58, var60, var66, true, false);
                  } else {
                     List var34 = var33.method1();
                     MixinHelper.field1.method31(var33.getName(), var58, var60, var34);
                  }
               } else {
                  short var67 = 164;
                  byte var35 = 38;
                  MixinHelper.field1.method37(var58, var60, var67, var35);
                  MixinHelper.field1.method24("§f" + method12("clickToLoad"), var58 + var67 / 2, var60 + var35 / 2, true);
               }
            }
         }

         MixinHelper.field1.method41(var58, var60, var42, var50);
         ArrayList var63 = new ArrayList();

         for (int var64 = var8.bridge$getLowerChestSizeInventory(); var64 < var8.bridge$inventorySlots().size(); var64++) {
            var63.add(var8.bridge$inventorySlots().get(var64));
         }

         this.field5.animateTo(var56 + var50 + 6 - var21, this.field5.getValue() == 0.0 ? 0L : 250L);
         int var65 = (int)this.field5.getValue() + var21;
         this.field4.animateTo(var53, this.field4.getValue() == 0.0 ? 0L : 250L);
         int var68 = (int)this.field4.getValue();
         MixinHelper.field1.method33("Inventory", var68, var65, var63, true, true);
         MixinHelper.field1.method41(var68, var65, var42, var48);
         if (!var14.isEmpty()) {
            MixinHelper.field1.method14(null);
         }

         Storageoverlay var69 = this.method11();
         Storageoverlay var36 = this.method12();
         int var37 = var53 + var42 + var18 / 2;
         int var38 = var56 + var50 / 2 + var48 / 2 - 3 - 8;
         MixinHelper.field1.method37(var37 - 2, var38 - 2, 20, 20);
         MixinHelper.field1.method38(var37, var38, 16, 16);
         int var39 = MixinHelper.field1.method22(var8.bridge$inventorySlots().get(7).bridge$getItemStack(), var37, var38, true);
         if (var39 != -1
            && !var8.bridge$inventorySlots().get(7).bridge$getItemStack().bridge$isEmpty()
            && this.field12.method3(var11)
            && (
               !AdventureChatFormatting.getTextWithoutFormattingCodes(var8.bridge$inventorySlots().get(7).bridge$getItemStack().bridge$getDisplayName())
                     .trim()
                     .isEmpty()
                  || this.field12.isEnderChestPage() && var69 != null && !var69.isEnderChestPage()
            )) {
            if (!AdventureChatFormatting.getTextWithoutFormattingCodes(var8.bridge$inventorySlots().get(7).bridge$getItemStack().bridge$getDisplayName())
               .trim()
               .isEmpty()) {
               var8.bridge$clickSlot(7, 0, ContainerClickType.PICKUP);
            } else {
               ThreadModuleDump63.method7().bridge$sendCommand("/backpack " + var69.getMenuIndex());
            }

            this.field12.method12(true);
            this.field12 = var69;
            this.field13 = this.IIIHRCOIICCCRCRHCOCRRIHORHORIH;
         }

         var37 = var53 - var18 / 2 - 16;
         var38 = var56 + var50 / 2 + var48 / 2 - 3 - 8;
         MixinHelper.field1.method37(var37 - 2, var38 - 2, 20, 20);
         MixinHelper.field1.method38(var37, var38, 16, 16);
         var39 = MixinHelper.field1.method22(var8.bridge$inventorySlots().get(6).bridge$getItemStack(), var37, var38, true);
         if (var39 != -1
            && !var8.bridge$inventorySlots().get(6).bridge$getItemStack().bridge$isEmpty()
            && this.field12.method3(var11)
            && (
               !AdventureChatFormatting.getTextWithoutFormattingCodes(var8.bridge$inventorySlots().get(6).bridge$getItemStack().bridge$getDisplayName())
                     .trim()
                     .isEmpty()
                  || !this.field12.isEnderChestPage() && var36 != null && var36.isEnderChestPage()
            )) {
            if (!AdventureChatFormatting.getTextWithoutFormattingCodes(var8.bridge$inventorySlots().get(6).bridge$getItemStack().bridge$getDisplayName())
               .trim()
               .isEmpty()) {
               var8.bridge$clickSlot(6, 0, ContainerClickType.PICKUP);
            } else {
               ThreadModuleDump63.method7().bridge$sendCommand("/enderchest " + var36.getMenuIndex());
            }

            this.field12.method12(true);
            this.field12 = var36;
            this.field13 = this.IIIHRCOIICCCRCRHCOCRRIHORHORIH;
         }

         var37 = var53 + var42 + var18 / 2;
         var38 = var56 + 2;
         MixinHelper.field1.method37(var37 - 2, var38 - 2, 20, 20);
         MixinHelper.field1.method38(var37, var38, 16, 16);
         var39 = MixinHelper.field1.method22(var8.bridge$inventorySlots().get(0).bridge$getItemStack(), var37, var38, true);
         if (var39 != -1 && this.field12.method3(var11) && !var8.bridge$inventorySlots().get(1).bridge$getItemStack().bridge$isEmpty()) {
            var8.bridge$clickSlot(1, 0, ContainerClickType.PICKUP);
            this.field12.method12(true);
            this.field12 = null;
            this.field13 = this.IIIHRCOIICCCRCRHCOCRRIHORHORIH;
         }

         var39 = MixinHelper.field1.method41(0, 0, (int)var3, (int)var5);
         if (var39 != -1) {
            var8.bridge$clickSlot(1, 0, ContainerClickType.PICKUP);
            if (this.field12 != null) {
               this.field12.method12(true);
            }

            this.field12 = null;
            this.field13 = this.IIIHRCOIICCCRCRHCOCRRIHORHORIH;
         }

         MixinHelper.field1.method4();
      } else {
         if (!var14.isEmpty()) {
            MixinHelper.field1.method14(null);
         }

         this.field3.animateTo(0.0, 250L);
         this.field5.animateTo(0.0, 0L);
         this.field4.animateTo(0.0, 0L);
      }

      if (var10) {
         MixinHelper.field1.method11(true);
         MixinHelper.field1.method53();
      }

      MixinHelper.field1.method45("StorageOverlay-Search", (int)(var3 - var13 - 10.0), 10, var13, 10, "Search!", true);
      this.field15 = var14;
   }

   private Storageoverlay method11() {
      boolean var1 = false;

      for (Storageoverlay var3 : this.field10.method6()) {
         if (var3 != null) {
            if (var1) {
               return var3;
            }

            if (this.field12 == var3) {
               var1 = true;
            }
         }
      }

      return null;
   }

   private Storageoverlay method12() {
      Storageoverlay var1 = null;

      for (Storageoverlay var3 : this.field10.method6()) {
         if (var3 != null) {
            if (this.field12 == var3) {
               return var1;
            }

            var1 = var3;
         }
      }

      return null;
   }

   private void method13() {
      double var1 = this.method14();
      double var3 = LcuiScreen.method151().getScaledWidth() / var1;
      double var5 = LcuiScreen.method151().getScaledHeight() / var1;
      int var7 = this.field9.method15().get().getChestsPerRow();
      short var8 = 164;
      byte var9 = 102;
      byte var10 = 9;
      int var11 = (int)this.field2.getValue();
      byte var12 = 84;
      int var13 = (var8 + var10) * var7 + var10;
      int var14 = this.field9.method16().get().getHeight();
      int var15 = (int)(var5 / 2.0 - var14 / 2 - var12 / 2);
      ArrayList var16 = new ArrayList();
      Bridge5Extension6 var17 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      Bridge5Extension_3 var18 = (Bridge5Extension_3)var17;
      String var19 = this.method11(var17);
      ItemStackBridge var20 = var18.bridge$getCursor();
      boolean var21 = !var20.bridge$isEmpty();
      if (this.method20(var17) && this.field12 != null
         || this.field12 == null && !this.method20(var17)
         || this.field12 != null && !this.method20(var17) && !this.field12.method3(var19)) {
         var21 = false;
      }

      if (var21) {
         MixinHelper.field1.method11(false);
      }

      for (int var22 = var18.bridge$getLowerChestSizeInventory(); var22 < var18.bridge$inventorySlots().size(); var22++) {
         var16.add(var18.bridge$inventorySlots().get(var22));
      }

      this.field5.animateTo(var15 + var14 + 2, this.field5.getValue() == 0.0 ? 0L : 250L);
      int var40 = (int)this.field5.getValue();
      MixinHelper.field1.method33("Inventory", (int)(var3 / 2.0 - var8 / 2), var40, var16, true, true);
      int var23 = MixinHelper.field1.getStringWidth("Inventory");
      String var24 = MixinHelper.field1
         .method45("StorageOverlay-Search", (int)(var3 / 2.0 - var8 / 2 + var23 + 10.0), var40 + 2, var8 - 12 - var23, 7, "Search!", false);
      if (!var24.isEmpty()) {
         MixinHelper.field1.method14(var24);
         MixinHelper.field1.method15(this.field9.method23().get());
      }

      if (!var24.equals(this.field15)) {
         for (Storageoverlay var26 : this.field10.method6()) {
            if (var26 != null) {
               MixinHelper.field1.method50("menu-" + var26.isEnderChestPage() + "-" + var26.getMenuIndex());
            }
         }
      }

      int var41 = (int)((var9 + var10) * Math.ceil((float)this.field14 / var7) - var14 + var10);
      int var42 = this.field11;
      if (var42 < -var41) {
         var42 = -var41;
      }

      if (var42 > 0) {
         var42 = 0;
      }

      this.field2.animateTo(var42, 250L);
      MixinHelper.field1.method37((int)(var3 / 2.0 - var13 / 2), var15, var13, var14);
      MixinHelper.field1.method38((int)(var3 / 2.0 - var13 / 2 + 2.0), var15 + 2, var13 - 4, var14 - 4);
      MixinHelper.field1.method3();
      MixinHelper.field1.method18((int)(var3 / 2.0 - var13 / 2 + 2.0), var15 + 2, var13 - 4, var14 - 4);
      int var27 = 0;
      this.field14 = 0;
      MixinHelper.field1.method17(false);

      for (Storageoverlay var29 : this.field10.method6()) {
         if (var29 != null) {
            if (!var24.isEmpty()) {
               if (!var29.method6(var24, this.field9.method23().get())) {
                  continue;
               }

               var29.method12(true);
            }

            if (!this.field15.isEmpty()) {
               var29.method12(true);
            }

            this.field14++;
            int var30 = (int)(var27 % var7 * (var8 + var10) + var3 / 2.0 - var13 / 2 + var10);
            int var31 = var11 + var10 + var27 / var7 * (var9 + var10) + var15;
            if (var29.method13()) {
               var29.method12(true);
               var29.method10().method1(var30, 250L);
               var29.method11().method1(var31 - var11, 250L);
            }

            boolean var32 = var29.method10().method3() || var29.method11().method3();
            if (var32) {
               var30 = (int)var29.method10().getValue();
               var31 = (int)var29.method11().getValue() + var11;
            } else {
               var29.method10().method1(var30, 0L);
               var29.method11().method1(var31 - var11, 0L);
            }

            short var34 = 164;
            int var35 = 38;
            int var33;
            if (var29.method3(var19) && !var29.method9()) {
               MixinHelper.field1.method17(true);
               List var36 = var29.method5();
               var35 = 18 * var36.size() / 9 + 12;
               MixinHelper.field1.method33(var29.getName(), var30, var31, var36, true, false);
               MixinHelper.field1.method17(false);
               var33 = -1;
            } else {
               if (var29.method7()) {
                  var35 = 18 * var29.method1().size() / 9 + 12;
                  if (MixinHelper.field1.method42(var30 - 2, var31 - 2, var34 + 4, var35 + 4)) {
                     MixinHelper.field1.method50("menu-" + var29.isEnderChestPage() + "-" + var29.getMenuIndex());
                  }

                  MixinHelper.field1.method47("menu-" + var29.isEnderChestPage() + "-" + var29.getMenuIndex(), var30 - 2, var31 - 2, var34 + 4, var35 + 4);
                  var33 = MixinHelper.field1.method31(var29.getName(), var30, var31, var29.method1());
               } else {
                  if (MixinHelper.field1.method42(var30 - 2, var31 - 2, var34 + 4, var35 + 4)) {
                     MixinHelper.field1.method50("menu-" + var29.isEnderChestPage() + "-" + var29.getMenuIndex());
                  }

                  MixinHelper.field1.method47("menu-" + var29.isEnderChestPage() + "-" + var29.getMenuIndex(), var30 - 2, var31 - 2, var34 + 4, var35 + 4);
                  var33 = MixinHelper.field1.method41(var30, var31, var34, var35);
                  MixinHelper.field1.method37(var30, var31, var34, var35);
                  MixinHelper.field1.method24("§f" + method12("clickToLoad"), var30 + var34 / 2, var31 + var35 / 2, true);
               }

               MixinHelper.field1.method48("menu-" + var29.isEnderChestPage() + "-" + var29.getMenuIndex());
            }

            if (this.field12 == var29) {
               MixinHelper.field1.method43(var30 - 2, var31 - 2, var34 + 4, 2, -256);
               MixinHelper.field1.method43(var30 - 2, var31 - 2, 2, var35 + 4, -256);
               MixinHelper.field1.method43(var30 + var34, var31 - 2, 2, var35 + 4, -256);
               MixinHelper.field1.method43(var30 - 2, var31 + var35, var34 + 4, 2, -256);
            }

            if (var33 != -1 && (this.field12 != null && this.field12.method3(var19) || this.field12 == null && this.method20(var17))) {
               this.field12 = var29;
               if (var29.isEnderChestPage()) {
                  ThreadModuleDump63.method7().bridge$sendCommand("/enderchest " + var29.getMenuIndex());
               } else {
                  ThreadModuleDump63.method7().bridge$sendCommand("/backpack " + var29.getMenuIndex());
               }

               this.field13 = this.IIIHRCOIICCCRCRHCOCRRIHORHORIH;
               int var43 = var15 + 2;
               int var37 = var15 + var14 - 2;
               int var38 = this.field11 + var10 + var27 / var7 * (var9 + var10) + var15;
               int var39 = var38 + 18 * var29.method1().size() / 9 + 12;
               if (var38 < var43) {
                  this.field11 -= var38 - var43;
               } else if (var39 > var37) {
                  this.field11 -= var39 - var37;
               }
            }

            var27++;
         }
      }

      MixinHelper.field1.method4();
      if (var21) {
         MixinHelper.field1.method11(true);
         MixinHelper.field1.method53();
      }

      if (!var24.isEmpty()) {
         MixinHelper.field1.method14(null);
      }

      this.field15 = var24;
   }

   private static String method12(@Annotation(method1 = Annotation.Type.STORAGE_OVERLAY_INFO) String var0) {
      return Client.method109().method67().method2("features.STORAGE_OVERLAY.info", var0);
   }

   private double method14() {
      if (this.field9.method21().get() != Gui2Extension6.DEFAULT) {
         int var1 = this.field9.method21().get().getScale();
         return (double)var1 / LcuiScreen.method151().method3();
      } else {
         return 1.0;
      }
   }

   @Override
   public boolean method3(Bridge5Extension6 var1, int var2, int var3, int var4) {
      double var5 = this.method14() / ThreadModuleDump63.method4().method40().method96().method3(var1);
      MixinHelper.field1.method27("StorageOverlay", (int)(var2 / var5), (int)(var3 / var5), var4);
      return true;
   }

   @Override
   public boolean method4(Bridge5Extension6 var1, int var2, int var3, int var4) {
      double var5 = this.method14() / ThreadModuleDump63.method4().method40().method96().method3(var1);
      MixinHelper.field1.method28("StorageOverlay", (int)(var2 / var5), (int)(var3 / var5), var4);
      return true;
   }

   @Override
   public boolean method10(Bridge5Extension6 var1, Bridge_7 var2) {
      if (this.field9.method22().get() && !this.method23() && var2.method1()) {
         this.field19 = true;
         MixinHelper.field1.method29("StorageOverlay", var2);
         return true;
      } else {
         return MixinHelper.field1.method29("StorageOverlay", var2);
      }
   }

   @Override
   public boolean method3() {
      return false;
   }

   @Override
   public boolean method5(Bridge5Extension6 var1, double var2) {
      double var4 = this.method14();
      double var6 = LcuiScreen.method151().getScaledWidth() / var4;
      double var8 = LcuiScreen.method151().getScaledHeight() / var4;
      int var10;
      if (this.field9.method17().get() != Gui2Extension4.FULLSCREEN && this.field9.method17().get() != Gui2Extension4.FULLSCREEN_2) {
         int var16 = this.field9.method15().get().getChestsPerRow();
         byte var17 = 102;
         byte var18 = 9;
         int var19 = this.field9.method16().get().getHeight();
         var10 = (int)((var17 + var18) * Math.ceil((float)this.field14 / var16) - var19 + var18);
      } else {
         if (this.field12 != null && this.field9.method17().get() == Gui2Extension4.FULLSCREEN) {
            return true;
         }

         short var11 = 164;
         byte var12 = 102;
         byte var13 = 18;
         int var14 = (int)((var6 - (this.field9.method17().get() == Gui2Extension4.FULLSCREEN_2 ? 40 : 0)) / (var11 + var13));
         var10 = (int)((var12 + var13) * Math.ceil((float)this.field14 / var14) - var8 + var13);
         if (this.field9.method17().get() == Gui2Extension4.FULLSCREEN_2) {
            byte var15 = 84;
            var10 += var15;
         }
      }

      this.field11 = (int)(this.field11 + var2 * 120.0 / LcuiScreen.method151().method3());
      if (this.field11 < -var10) {
         this.field11 = -var10;
      }

      if (this.field11 > 0) {
         this.field11 = 0;
      }

      this.field2.animateTo(this.field11, 250L);
      return true;
   }

   @Override
   public void onClose() {
      MixinHelper.field1.method5("StorageOverlay");
      MixinHelper.field1.method6("StorageOverlay");
      ThreadModuleDump63.method3().bridge$setRepeatEventsEnabled(false);
      DriverViewportLegacy.method50().method21();
      super.onClose();
   }

   @Override
   public void onOpen() {
      MixinHelper.field1.method52("StorageOverlay");
      super.onOpen();
   }

   private boolean method20(Bridge5Extension6 var1) {
      return "Storage".equals(this.method11(var1));
   }

   private boolean method21(Bridge5Extension6 var1) {
      String var2 = this.method11(var1);
      return var2 != null && var2.contains(" Backpack ") && var2.contains("(Slot #");
   }

   private boolean method22(Bridge5Extension6 var1) {
      String var2 = this.method11(var1);
      return var2 != null && var2.startsWith("Ender Chest (");
   }

   private boolean method23() {
      return DriverViewportLegacy.method50().method64() == DriverOverlayRegistryLegacy.field5;
   }
}
