package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.google.common.collect.ImmutableList;
import com.lunarclient.items.item.Item;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.CrosshairEditorWidget;
import com.moonsworth.lunar.client.ui.widget.DropdownWidget;
import com.moonsworth.lunar.client.ui.widget.ProgressBarWidget;
import com.moonsworth.lunar.client.ui.widget.TitledWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.itemcounter.Itemcounter_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.Highlight5;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ItemSelectOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Annotation2(min = 33)
public class Bridge7Iterator extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private TitledWidget field19;
   private static final float field20 = 320.0F;
   private static final float field21 = 300.0F;
   private final Highlight5 field22;
   private CrosshairEditorWidget field23 = null;
   private final DropdownWidget field24;
   private final ProgressBarWidget field25;

   public Bridge7Iterator(com.moonsworth.lunar.client.ui.LcuiScreen var1, Highlight5 var2) {
      this.field22 = var2;
      FloatOption var3 = (FloatOption)((Data)((Data)OptionFactory.method2("inventoryButtonSize").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(var2.getSize()))
            .method8(16.0F, 64.0F))
         .method31();
      FloatOption var4 = (FloatOption)((Data)((Data)OptionFactory.method2("inventoryButtonScale").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(var2.getScale()))
            .method8(0.5F, 5.0F))
         .method31();
      EnumOption var5 = (EnumOption)OptionFactory.method10("inventoryButtonShape", var2.method17()).method31();
      ColorOption var6 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
               "inventoryButtonButtonColor"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(var2.method18()))
         .method15()
         .method31();
      var6.method1(-1862270977);
      ColorOption var7 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
               "inventoryButtonBorderColor"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(var2.method19()))
         .method15()
         .method31();
      var7.method1(1076176165);
      TextOption var8 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
               "inventoryButtonCommand"
            )
            .method2(var2.getCommand()))
         .method31();
      TextOption var9 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
               "inventoryButtonHoverText"
            )
            .method2(var2.method20()))
         .method31();
      EnumOption var10 = (EnumOption)OptionFactory.method10("inventoryButtonTextSide", var2.method21()).method31();
      com.moonsworth.lunar.client.config.option.ItemSelectOption.Data var11 = (com.moonsworth.lunar.client.config.option.ItemSelectOption.Data)((com.moonsworth.lunar.client.config.option.ItemSelectOption.Data)((com.moonsworth.lunar.client.config.option.ItemSelectOption.Data)OptionFactory.method28(
                  "inventoryButtonItem"
               )
               .method3(method3()))
            .method6(var0 -> {
               if (var0.startsWith("skyblock:")) {
                  String var3x = var0.substring(9);
                  String var2x = com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui2.field4.get(var3x);
                  return var2x != null ? var2x : var0;
               } else {
                  Itemcounter_2 var1x = (Itemcounter_2)ItemSelectOption.method11().get(var0);
                  return var1x != null ? var1x.toString() : var0;
               }
            }))
         .OOOHICCHHHRHCORIRCRHOCROROIOCR(var1x -> {
            LinkedHashSet var2x = new LinkedHashSet();
            var2x.add(var1x);
            ((MultiSelectOption)this.field23.getOption()).method1(var2x);
            this.field22.setItemId(var1x);
            this.field22.method10();
         });
      if (var2.getItemId() != null) {
         var11.method2(Set.of(var2.getItemId()));
      }

      MultiSelectOption var12 = (MultiSelectOption)var11.method31();
      ToggleOption var13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("inventoryButtonShowInContainers")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(var2.method23()))
         .method31();
      LinkedHashSet var14 = this.method10(
         new GuiWidget[]{
            var3.method25(this.field19),
            var4.method25(this.field19),
            var5.method25(this.field19),
            var6.method1(this.field19),
            var7.method1(this.field19),
            var8.method25(this.field19),
            var9.method25(this.field19),
            var10.method25(this.field19),
            var13.method25(this.field19),
            this.field23 = (CrosshairEditorWidget)var12.method25(this.field19),
            this.field24 = new DropdownWidget(this.field19),
            this.field25 = new ProgressBarWidget(this.field19, ResourceLocationBridge.create("lunar", "icons/cosmetics/back-40x40.png"))
         }
      );
      this.field19.method14(new ArrayList(var14));
      this.field19.setTitle("editInventoryButton");
      var3.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2::setSize);
      var4.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2::setScale);
      var5.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2::method28);
      var6.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2::method29);
      var7.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2::method30);
      var8.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2::setCommand);
      var9.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2::method32);
      var10.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2::method33);
      var13.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2::method35);
      this.field25.method4((var1x, var2x) -> {
         ThreadModuleDump63.method3().bridge$displayScreen(var1 == null ? null : Bridge.method8().method18(var1));
         return true;
      });
   }

   private static List<String> method3() {
      ArrayList var0 = new ArrayList(ItemSelectOption.method10());

      for (Item var4 : com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui2.items) {
         if (var4.material().equals("SKULL_ITEM")) {
            var0.add("skyblock:" + var4.id());
         }
      }

      return var0;
   }

   protected List<GuiWidget> method25() {
      this.field19 = new TitledWidget(null, "editInventoryButton") {
         public void method3(float var1, float var2, float var3, float var4) {
            float var5 = 0.0F;

            for (GuiWidget var7 : Bridge7Iterator.this.field19.method14()) {
               if (var7 instanceof OptionWidget var8) {
                  float var9 = var7.getHeight();
                  var8.method1(var1 + 8.0F, var2 + 32.0F + var5, var3 - 16.0F);
                  var5 += var9 + 4.0F;
               }
            }

            Bridge7Iterator.this.field24.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var3 - 6.0F, var2 + 30.0F, 4.0F, var4 - 60.0F);
            Bridge7Iterator.this.field24.method15(var5);
            Bridge7Iterator.this.field25.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + 6.0F, var2 + 300.0F - 24.0F, 18.0F, 18.0F);
         }

         public void method3(MixinHelper_4 var1, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var2, boolean var3) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, this.x, this.y + 24.0F, this.width, 0.5F, 553648127);
            com.moonsworth.lunar.client.ui.LcuiScreen.method106(var1, this.x, this.y + 1.0F, this.width, 23.0F, 5.0F, 620756992);
            FontRegistry.method19().method17(var1, this.getTitle(), this.x + 8.0F, this.y + 6.0F, -1, false);
            com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, this.x, this.y + this.height - 30.0F, this.width, 0.5F, 553648127);
            com.moonsworth.lunar.client.ui.LcuiScreen.method105(
               var1, this.x, this.y + this.height - 29.5F, this.width, 28.5F, 5.0F, 1157627904
            );
            com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, this.x - 1.0F, this.y, this.width + 2.0F, this.height, 4.0F, 1073741824);
            com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, this.x, this.y + 1.0F, this.width, this.height - 2.0F, 3.0F, 553648127);
            com.moonsworth.lunar.client.ui.LcuiScreen.method117(
               var1, this.x, this.y + 1.0F, this.width, this.height - 2.0F, 5.0F, Integer.MIN_VALUE
            );
            com.moonsworth.lunar.client.ui.LcuiScreen.method111(
               var1, this.x - 2.0F, this.y + 24.0F, this.width + 4.0F, this.height - 54.0F, 1.0F
            );
            Bridge7Iterator.this.field24.method5(var1, var2, var3);
            com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var4 = Bridge7Iterator.this.field24.method4(var2);

            for (GuiWidget var6 : this.field11) {
               if (var6.method24() && !(var6 instanceof ProgressBarWidget)) {
                  boolean var7 = var6.getY() + var6.getHeight() + Bridge7Iterator.this.field24.method3() < Bridge7Iterator.this.field24.getY();
                  boolean var8 = var6.getY() + Bridge7Iterator.this.field24.method3()
                     > Bridge7Iterator.this.field24.getY() + Bridge7Iterator.this.field24.getHeight();
                  if (var6.getX() >= this.x && !var7 && !var8) {
                     var6.method3(var1, var4, var3 && this.HORHROIOIOICIRHIOCOICHHHIHCIIO(var6, var2, new GuiWidget[0]));
                  }
               }
            }

            Bridge7Iterator.this.field24.method7(var1, var2, var3);
            com.moonsworth.lunar.client.ui.LcuiScreen.method112(var1);
            Bridge7Iterator.this.field25.method3(var1, var2, var3);
         }
      };
      this.field19.method4((var1, var2) -> {
         com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var3 = this.field24.method4(var1);
         boolean var4 = this.method3(var1.method12());

         for (GuiWidget var6 : this.field19.method14()) {
            com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var7;
            if (var6 != this.field25 && var6 != this.field24) {
               if (!var4) {
                  continue;
               }

               var7 = var3;
            } else {
               var7 = var1;
            }

            if (var6.method1(var7) && var6.method6(var7, var2)) {
               com.moonsworth.lunar.client.ui.LcuiScreen.method15();
               return true;
            }
         }

         return false;
      });
      this.field19.method17((var1, var2) -> {
         com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var3 = this.field24.method4(var1);
         boolean var4 = this.method3(var1.method12());

         for (GuiWidget var6 : this.field19.method14()) {
            com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var7;
            if (var6 != this.field25 && var6 != this.field24) {
               if (!var4) {
                  continue;
               }

               var7 = var3;
            } else {
               var7 = var1;
            }

            if (var6.method1(var7) && var6.method7(var7, var2)) {
               com.moonsworth.lunar.client.ui.LcuiScreen.method15();
               return true;
            }
         }

         return false;
      });
      this.field19.method3((var1, var2) -> {
         com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var3 = this.field24.method4(var1);

         for (GuiWidget var5 : this.field19.method14()) {
            com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var6 = var5 != this.field25 && var5 != this.field24 ? var3 : var1;
            if (!var5.method1(var6) && var5.method8(var6, var2)) {
               com.moonsworth.lunar.client.ui.LcuiScreen.method15();
               return true;
            }
         }

         return false;
      });
      return ImmutableList.of(this.field19);
   }

   private boolean method3(float var1) {
      return var1 >= this.field24.getY() && var1 <= this.field24.getY() + this.field24.getHeight();
   }

   public void init() {
      float var1 = this.method22() / 2.0F - 160.0F;
      float var2 = this.method23() / 2.0F - 150.0F;
      this.field19.method2(var1, var2, 320.0F, 300.0F);
   }

   public void update() {
   }

   public void method10(MixinHelper_4 var1, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var2) {
      List var3 = ThreadModuleDump63.method4().method40().method82().method165().method13();

      for (Highlight5 var5 : var3) {
         var5.method11(var1, false, com.moonsworth.lunar.client.ui.LcuiScreen.getScale());
      }

      for (Highlight5 var7 : var3) {
         if (var7.method14(var2)) {
            var7.method13(var1, false, com.moonsworth.lunar.client.ui.LcuiScreen.getScale());
         }
      }
   }

   public void method11(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var1, int var2) {
   }

   public void method12(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var1, int var2) {
   }

   public void method14(char var1, KeyCode var2) {
   }

   public String getLanguagePath() {
      return super.getLanguagePath() + ".skyblockInventoryButtonsEditor";
   }

   public void close() {
   }
}
