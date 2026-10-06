package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.widget.IconTextButton;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.GeneralSettings.Type4;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10;
import com.moonsworth.lunar.client.framework.mod.Framework12;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump56;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import lombok.Generated;

public class ModSearchWidget extends com.moonsworth.lunar.client.ui.widget.WidgetPanel {
   private List<ChildLabelWidget> field19;
   private List<SelectionWidget<Framework7Extension>> field20;
   private Calculator2Handler field21 = null;
   private SelectionWidget<Framework7Extension> field22;
   private float field23;
   private float field24;
   private SelectionWidget<Framework7Extension> field25;
   private long field26;
   private com.moonsworth.lunar.client.ui.widget.DropdownWidget field27 = new com.moonsworth.lunar.client.ui.widget.DropdownWidget(
      this
   );
   private IconTextButton field28;
   private LayoutToggleWidget field29;
   private SortModeWidget field30;
   private boolean field31;

   public ModSearchWidget(com.moonsworth.lunar.client.ui.widget.GuiWidget var1) {
      super(var1);
      this.field22 = null;
      this.field24 = 0.0F;
      this.field23 = 0.0F;
      this.field25 = null;
      this.field26 = 0L;
      this.method17((var1x, var2x) -> {
         if (this.field31) {
            MarkerModel.Data2 var3 = this.field27.method4(var1x);

            for (SelectionWidget var5x : this.field20) {
               if (var1x.method9() > this.y + 20.0F && var5x.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var3)) {
                  this.field31 = false;
                  return var5x.IIORCIOOIHRRRICOHIRCIHOOCCOHRO(var3, var2x);
               }
            }
         }

         this.field31 = false;
         return false;
      });
      this.method4(
         (var1x, var2x) -> {
            this.field31 = true;
            if (this.field29.method3(var1x)) {
               Client.method109()
                  .method41()
                  .method6()
                  .method68()
                  .OIRHOOIICOCIOOHICRRRICORIHHIHC(!(Boolean)Client.method109().method41().method6().method68().get());
               if (ThreadModuleDump63.method8() != null) {
                  Client.method109().method41().method6().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
               }

               this.method2(this.x, this.y, this.width, this.height);
               return true;
            } else if (this.field30.method3(var1x)) {
               int var8 = ((Type4)Client.method109().method41().method6().method49().get()).ordinal();
               int var10 = var2x == 0 ? 1 : -1;
               int var11 = (var8 + var10) % Type4.values().length;
               Type4 var6 = Type4.values()[var11];
               Client.method109().method41().method6().method49().OIRHOOIICOCIOOHICRRRICORIHHIHC(var6);
               if (ThreadModuleDump63.method8() != null) {
                  Client.method109().method41().method6().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
               }

               this.method2();
               return true;
            } else {
               for (ChildLabelWidget var4x : this.field19) {
                  if (var4x.method3(var1x)) {
                     this.method2(this.x, this.y, this.width, this.height);
                     return var4x.method18(var1x, var2x);
                  }
               }

               MarkerModel.Data2 var7 = this.field27.method4(var1x);

               for (SelectionWidget var5x : this.field20) {
                  if (var1x.method9() > this.y + 20.0F
                     && var5x.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var7)
                     && var5x.method18(var7, var2x)) {
                     this.field22 = null;
                     this.field24 = 0.0F;
                     this.field23 = 0.0F;
                     this.field25 = null;
                     this.field26 = 0L;
                     return true;
                  }
               }

               return this.field27.method1(var1x) && this.field27.method18(var1x, var2x)
                  || this.field28.method3(var1x) && this.field28.method18(var1x, var2x);
            }
         }
      );
      this.method3((var1x, var2x) -> this.field28.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1x, var2x));
      this.field19 = new ArrayList<>();
      ChildLabelWidget var2;
      this.field19.add(var2 = new ChildLabelWidget("all", this));
      var2.setActive(true);
      var2.method4((var2x, var3) -> {
         for (ChildLabelWidget var5x : this.field19) {
            var5x.setActive(false);
         }

         var2.setActive(true);
         this.field21 = null;
         this.method2(this.x, this.y, this.width, this.height);
         return true;
      });

      for (Calculator2Handler var4 : Calculator2Handler.method1()) {
         ChildLabelWidget var5;
         this.field19.add(var5 = new ChildLabelWidget(var4, this));
         var5.method4((var2x, var3) -> {
            for (ChildLabelWidget var5x : this.field19) {
               var5x.setActive(false);
            }

            var5.setActive(true);
            this.field21 = var5.method3();
            this.method2(this.x, this.y, this.width, this.height);
            return true;
         });
      }

      this.field29 = new LayoutToggleWidget(this);
      this.field30 = new SortModeWidget(this);
      this.field20 = new LinkedList<>();
      this.method2();
   }

   @Override
   public void method11() {
      this.method2(this.x, this.y, this.width, this.height);
   }

   public void method2() {
      this.field20.clear();
      this.field22 = null;
      this.field25 = null;

      for (Framework7Extension var2 : this.field15.method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         if (var2.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(Framework.field10)
            && (var2 != ThreadModuleDump63.method4().method40().method85() || FeatureFlag.REWIND.isEnabled())) {
            this.field20
               .add(
                  Client.method109().method41().method6().method68().get()
                     ? new ModConflictAlertWidget(this, (ModMenuWidget)this.field4, this, var2)
                     : new EditorToolbarWidget(this, (ModMenuWidget)this.field4, var2)
               );
         }
      }
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      Type4 var5 = (Type4)ThreadModuleDump63.method4().method41().method6().method49().get();
      if (var5 == Type4.LAST_MODIFIED) {
         this.field20.sort(Comparator.<SelectionWidget<Framework7Extension>, Boolean>comparing(var0 -> {
            Framework10 var1x = (Framework10)var0.getValue().HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field15);
            return var1x != null && var1x.method6();
         }).reversed().thenComparing(var0 -> {
            Framework10 var1x = (Framework10)var0.getValue().HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field15);
            return var1x != null ? -var1x.getLastModified() : 0L;
         }));
      } else if (var5 == Type4.ALPHABETICAL) {
         this.field20.sort(Comparator.<SelectionWidget<Framework7Extension>, Boolean>comparing(var0 -> {
            Framework10 var1x = (Framework10)var0.getValue().HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field15);
            return var1x != null && var1x.method6();
         }).reversed().thenComparing(var0 -> {
            Framework7Extension var1x = var0.getValue();
            ModDetails var2x = (ModDetails)var1x.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
            return var2x != null ? var2x.getName() : var1x.getId();
         }));
      }

      boolean var6 = false;
      ArrayList var7 = new ArrayList();

      for (SelectionWidget var9 : this.field20) {
         Framework7Extension var10 = (Framework7Extension)var9.getValue();
         ModDetails var11 = (ModDetails)var10.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
         if (var11 != null && !var11.method1().contains(Calculator2Handler.field8)) {
            Framework12 var12 = (Framework12)var10.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field10);
            if (var7.contains(var12.method5())) {
               var12.method2(0.0F);
               var12.method4(0.0F);
               var12.method6(0);
               var12.setReset(true);
               var6 = true;
            } else {
               var7.add(var12.method5());
            }
         }
      }

      float var24 = var7.stream().max(Integer::compare).orElse(0).intValue();
      float var25 = var7.stream().min(Integer::compare).orElse(0).intValue();
      if (var24 != var7.size() - 1) {
         boolean var26 = false;

         for (int var31 = 0; var31 < var7.size() - 1; var31++) {
            if (!var7.contains(var31)) {
               for (SelectionWidget var13 : this.field20) {
                  Framework12 var14 = (Framework12)((Framework7Extension)var13.getValue()).RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field10);
                  if (var14.method5() >= var31) {
                     var14.method6(var14.method5() - 1);
                     var6 = true;
                  }
               }

               var26 = true;
               break;
            }
         }

         if (!var26) {
            for (SelectionWidget var37 : this.field20) {
               Framework12 var41 = (Framework12)((Framework7Extension)var37.getValue()).RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field10);
               if (var41.method5() == var24) {
                  var41.setReset(true);
                  var41.method2(0.0F);
                  var41.method4(0.0F);
                  var41.method6(0);
                  var6 = true;
               }
            }
         }
      }

      if (var25 != 0.0F) {
         for (int var27 = 0; var27 < var7.size() - 1; var27++) {
            if (!var7.contains(var27)) {
               for (SelectionWidget var38 : this.field20) {
                  Framework12 var42 = (Framework12)((Framework7Extension)var38.getValue()).RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field10);
                  if (var42.method5() < var27) {
                     var42.method6(var42.method5() + 1);
                     var6 = true;
                  }
               }
               break;
            }
         }
      }

      super.method2(var1, var2, var3, var4);
      float var28 = 0.0F;

      for (ChildLabelWidget var39 : this.field19) {
         var39.method1(var1 + var28, var2 + 1.0F);
         var28 += var39.getWidth() + 4.0F;
      }

      int var35 = 0;
      int var40 = 0;
      LinkedHashMap var43 = new LinkedHashMap();
      if (!this.field28.getText().isEmpty()) {
         String var44 = ThreadModuleDump56.method4(this.field28.getText());

         for (SelectionWidget var16 : this.field20) {
            Framework7Extension var17 = (Framework7Extension)var16.getValue();
            ModDetails var18 = (ModDetails)var17.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
            String var19 = var18 != null ? var18.getName() : var17.getId();
            if (!var43.containsKey(var19)) {
               ModSearchIndex var20 = (ModSearchIndex)var17.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field9);
               if (var20 != null && var20.method5(var44)) {
                  var43.put(var19, var16);
               }
            }
         }

         for (SelectionWidget var50 : this.field20) {
            Framework7Extension var52 = (Framework7Extension)var50.getValue();
            ModDetails var54 = (ModDetails)var52.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
            String var56 = var54 != null ? var54.getName() : var52.getId();
            if (!var43.containsKey(var56)) {
               ModSearchIndex var58 = (ModSearchIndex)var52.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field9);
               if (var58 != null && var58.method6(var44)) {
                  var43.put(var56, var50);
               }
            }
         }

         ArrayList var48 = new ArrayList(var43.values());
         this.field20.sort(Comparator.comparingInt(var48::indexOf));
      }

      for (SelectionWidget var49 : this.field20) {
         Framework7Extension var51 = (Framework7Extension)var49.getValue();
         ModDetails var53 = (ModDetails)var51.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
         if (this.field21 != null && (var53 == null || !var53.method1().contains(this.field21))
            || !this.field28.getText().isEmpty() && !var43.containsKey(var53 != null ? var53.getName() : var51.getId())) {
            var49.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 - this.method15() - 10.0F, var2, this.method15(), this.method17());
         } else {
            if (var35 == 3) {
               var35 = 0;
               var40++;
            }

            int var55 = var35 + var40 * 3;
            Framework12 var57 = (Framework12)var51.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field10);
            if (var57.isReset() && var57.method1() == 0.0F && var57.method3() == 0.0F && var57.method5() == 0) {
               var57.method6(var55);
               var57.method2(var1 + (this.method15() + 8.0F) * var35);
               var57.method4(var2 + 22.0F + (this.method17() + 8.0F) * var40);
               var57.setReset(false);
               var6 = true;

               for (SelectionWidget var21 : this.field20) {
                  ModDetails var22 = (ModDetails)((Framework7Extension)var21.getValue()).HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
                  if (var22 != null && !var22.method1().contains(Calculator2Handler.field8) && !var21.equals(var49)) {
                     Framework12 var23 = (Framework12)((Framework7Extension)var21.getValue()).RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field10);
                     if ((!var23.isReset() || var23.method1() != 0.0F || var23.method3() != 0.0F || var23.method5() != 0) && var55 <= var23.method5()) {
                        var23.method6(var23.method5() + 1);
                        var23.method2(var1 + (this.method15() + 8.0F) * (var23.method5() % 3));
                        var23.method4(var2 + 22.0F + (this.method17() + 8.0F) * (var23.method5() - var23.method5() % 3) / 3.0F);
                        var23.setReset(false);
                     }
                  }
               }
            }

            if (this.field25 == null && this.field22 == null) {
               var57.method2(var1 + (this.method15() + 8.0F) * (var57.method5() % 3));
               var57.method4(var2 + 22.0F + (this.method17() + 8.0F) * (var57.method5() - var57.method5() % 3) / 3.0F);
            }

            if (var5 == Type4.CUSTOM && this.field21 == null && this.field28.getText().isEmpty()) {
               var49.RIIICIRHRCIHOOOORHOICRIICCCRHR(var57.method1(), var57.method3(), this.method15(), this.method17());
            } else {
               var49.RIIICIRHRCIHOOOORHOICRIICCCRHR(
                  var1 + (var49.getWidth() + 8.0F) * var35, var2 + 22.0F + (var49.getHeight() + 8.0F) * var40, this.method15(), this.method17()
               );
            }

            var35++;
         }
      }

      this.field27.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var3 - 6.0F, var2 + 20.0F, 4.0F, var4 - 20.0F);
      this.field27.method15(var40 == 0 ? this.method15() + 4.0F : 4.0F + this.method17() + (this.method17() + 8.0F) * var40);
      this.field29.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var28, var2 + 1.0F, 14.0F, 14.0F);
      var28 += 16.0F;
      this.field30.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var28, var2 + 1.0F, 14.0F, 14.0F);
      var28 += 16.0F;
      float var46 = 14.0F;
      this.field28.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var28, var2 + 1.0F, var3 - var28 - 2.0F, var46);
      Bridge.method18().method3(true);
      if (var6) {
         ThreadModuleDump63.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      }
   }

   @Override
   protected List<com.moonsworth.lunar.client.ui.widget.GuiWidget> method5() {
      return Arrays.asList(
         this.field28 = new IconTextButton(
            this, ResourceLocationBridge.create("lunar", "icons/assets/magnifying-glass-12x12.png"), FontRegistry.field14, "searchPlaceholder", 553648127, 905969663
         )
      );
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      for (ChildLabelWidget var5 : this.field19) {
         var5.method3(var1, var2, var3);
      }

      this.field29.method3(var1, var2, var3);
      this.field30.method3(var1, var2, var3);
      if (!this.field31) {
         if (this.field22 != null) {
            for (SelectionWidget var19 : this.field20) {
               Framework12 var6 = (Framework12)((Framework7Extension)var19.getValue()).RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field10);
               var6.method2(this.x + (this.method15() + 8.0F) * (var6.method5() % 3));
               var6.method4(this.y + 22.0F + (this.method17() + 8.0F) * (var6.method5() - var6.method5() % 3) / 3.0F);
            }

            ThreadModuleDump63.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
            this.field22 = null;
         }

         if (this.field25 != null) {
            this.field25 = null;
            this.field26 = 0L;
         }
      } else if (this.field26 != 0L && this.field25 != null && System.currentTimeMillis() - this.field26 >= 350L) {
         this.field22 = this.field25;
         this.field25 = null;
         this.field26 = 0L;
      }

      if (this.field22 != null) {
         Framework12 var16 = (Framework12)this.field22.getValue().RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field10);
         var16.method2(Math.max(this.x, Math.min(this.field23 + var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR(), this.x + this.width - this.method15())));
         if (var16.method3() >= this.y + this.height - this.method17() - this.field27.method3()) {
            this.field27.method12(this.field27.method14() - 10.0);
         } else if (var16.method3() <= this.y + 20.0F - this.field27.method3()) {
            this.field27.method12(this.field27.method14() + 10.0);
         }

         var16.method4(
            Math.max(
               this.y - this.field27.method3(),
               Math.min(this.y + this.height - this.field27.method3(), this.field24 + var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() - this.field27.method3())
            )
         );
         MarkerModel.Data2 var20 = this.field27.method4(var2);

         for (SelectionWidget var7 : this.field20) {
            if (!var7.equals(this.field22) && var7.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var20)) {
               boolean var8 = false;
               Framework12 var9 = (Framework12)((Framework7Extension)var7.getValue()).RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field10);
               boolean var10 = var9.method5() - var16.method5() < 0;
               float var11 = var16.method5();
               if (var11 != var9.method5()) {
                  var16.method6(var9.method5());
                  var8 = true;
               }

               for (SelectionWidget var13 : this.field20) {
                  if (!var13.equals(this.field22)) {
                     Framework12 var14 = (Framework12)((Framework7Extension)var13.getValue()).RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field10);
                     if (var10 && var14.method5() >= var9.method5() && var14.method5() < var11) {
                        var14.method6(var14.method5() + 1);
                        var8 = true;
                     }

                     if (!var10 && var14.method5() <= var9.method5() && var14.method5() > var11) {
                        var14.method6(var14.method5() - 1);
                        var8 = true;
                     }

                     var14.method2(this.x + (this.method15() + 8.0F) * (var14.method5() % 3));
                     var14.method4(this.y + 22.0F + (this.method17() + 8.0F) * (var14.method5() - var14.method5() % 3) / 3.0F);
                  }
               }

               if (var8) {
                  ThreadModuleDump63.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
               }
               break;
            }
         }
      }

      com.moonsworth.lunar.client.ui.LcuiScreen.method111(var1, this.x - 2.0F, this.y + 20.0F, this.width + 4.0F, this.height - 15.0F, 1.0F);
      if (this.field22 != null) {
         Framework12 var17 = (Framework12)this.field22.getValue().RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field10);
         float var21 = this.x + (this.method15() + 8.0F) * (var17.method5() % 3);
         float var24 = this.y + 22.0F + (this.method17() + 8.0F) * (var17.method5() - var17.method5() % 3) / 3.0F + this.field27.method3();
         com.moonsworth.lunar.client.ui.LcuiScreen.method52(var1, var21, var24, this.method15(), this.method17(), 3.0F, 721420287);
      }

      this.field27.method5(var1, var2, var3);
      MarkerModel.Data2 var18 = this.field27.method4(var2);

      for (SelectionWidget var25 : this.field20) {
         boolean var26 = var25.getY() + var25.getHeight() + this.field27.method3() < this.field27.getY();
         boolean var27 = var25.getY() + this.field27.method3() > this.field27.getY() + this.field27.getHeight();
         if (var25.getX() >= this.x && !var26 && !var27) {
            if (this.field22 != null && this.field22.equals(var25)) {
               var25.RIIICIRHRCIHOOOORHOICRIICCCRHR(var25.getX(), var25.getY(), var25.getWidth() + 3.0F, var25.getHeight() + 3.0F);
            }

            var25.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var18, this.field22 == null && var3 && !this.field27.method17());
         }
      }

      this.field27.method7(var1, var2, var3);
      com.moonsworth.lunar.client.ui.LcuiScreen.method112(var1);
      super.method3(var1, var2, var3);
   }

   @Override
   public boolean method6(MarkerModel.Data2 var1, int var2) {
      if (this.field21 == null
         && !(Boolean)ThreadModuleDump63.method4().method41().method6().method50().get()
         && ThreadModuleDump63.method4().method41().method6().method49().get() == Type4.CUSTOM
         && this.field28.getText().isEmpty()
         && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() > this.y + 20.0F
         && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.y + this.height) {
         MarkerModel.Data2 var3 = this.field27.method4(var1);

         for (SelectionWidget var5 : this.field20) {
            boolean var6 = var5.getY() + var5.getHeight() + this.field27.method3() < this.field27.getY();
            boolean var7 = var5.getY() + this.field27.method3() > this.field27.getY() + this.field27.getHeight();
            if (var5.getX() >= this.x && !var6 && !var7 && var5.method3(var3) && this.field25 == null && this.field22 == null) {
               this.field25 = var5;
               this.field26 = System.currentTimeMillis();
               Framework12 var8 = (Framework12)this.field25.getValue().RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field10);
               this.field23 = var8.method1() - var3.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
               this.field24 = var8.method3() - var3.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
               break;
            }
         }
      }

      return super.method18(var1, var2);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (var2 == KeyCode.KEY_ESCAPE && this.field28.method18()) {
         this.field28.method17(false);
         this.field28.setText("");
      } else {
         super.method4(var1, var2);
         if (!this.field28.method18()) {
            this.field28.method17(true);
            this.field28.method4(var1, var2);
         }
      }
   }

   @Override
   public boolean method1(MarkerModel.Data2 var1) {
      return this.field22 != null ? true : super.method3(var1);
   }

   @Override
   public boolean method5(int var1) {
      return super.method5(var1) || this.field27.method5(var1);
   }

   @Override
   public void close() {
      super.close();
      Bridge.method18().method3(false);
   }

   private float method15() {
      return Client.method109().method41().method6().method68().get() ? 115.0F : 115.0F;
   }

   private float method17() {
      return Client.method109().method41().method6().method68().get() ? 22.0F : 112.0F;
   }

   @Generated
   public SelectionWidget<Framework7Extension> method18() {
      return this.field22;
   }

   @Generated
   public com.moonsworth.lunar.client.ui.widget.DropdownWidget method19() {
      return this.field27;
   }

   @Generated
   public IconTextButton method20() {
      return this.field28;
   }
}
