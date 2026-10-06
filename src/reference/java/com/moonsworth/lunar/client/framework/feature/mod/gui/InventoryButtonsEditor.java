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
import com.moonsworth.lunar.client.framework.feature.itemcounter.ItemCounterEntry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.HighlightButton;
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
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@VersionGate(min = 33)
public class InventoryButtonsEditor extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private TitledWidget field19;
   private static final float field20 = 320.0F;
   private static final float field21 = 300.0F;
   private final HighlightButton field22;
   private CrosshairEditorWidget field23 = null;
   private final DropdownWidget field24;
   private final ProgressBarWidget field25;

   public InventoryButtonsEditor(com.moonsworth.lunar.client.ui.LcuiScreen bridge7iterator1, HighlightButton highlight52) {
      this.field22 = highlight52;
      FloatOption lightingextension4723 = (FloatOption)((Data)((Data)OptionFactory.method2("inventoryButtonSize").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(highlight52.getSize()))
            .HRRCROICHIIROIHRCOIHRRHCCRIIRH(16.0F, 64.0F))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      FloatOption lightingextension4724 = (FloatOption)((Data)((Data)OptionFactory.method2("inventoryButtonScale").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(highlight52.getScale()))
            .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.5F, 5.0F))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      EnumOption lightingextension4975 = (EnumOption)OptionFactory.method10("inventoryButtonShape", highlight52.method17()).RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      ColorOption lightingextension42226 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
               "inventoryButtonButtonColor"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(highlight52.method18()))
         .method15()
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      lightingextension42226.method2(-1862270977);
      ColorOption lightingextension42227 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
               "inventoryButtonBorderColor"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(highlight52.method19()))
         .method15()
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      lightingextension42227.method2(1076176165);
      TextOption lightingextension49158 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
               "inventoryButtonCommand"
            )
            .HIIIOHRRROCICIOIORRRIRCRCHHIII(highlight52.getCommand()))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      TextOption lightingextension49159 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
               "inventoryButtonHoverText"
            )
            .HIIIOHRRROCICIOIORRRIRCRCHHIII(highlight52.method20()))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      EnumOption lightingextension49710 = (EnumOption)OptionFactory.method10("inventoryButtonTextSide", highlight52.method21()).RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      com.moonsworth.lunar.client.config.option.ItemSelectOption.Data data11 = (com.moonsworth.lunar.client.config.option.ItemSelectOption.Data)((com.moonsworth.lunar.client.config.option.ItemSelectOption.Data)((com.moonsworth.lunar.client.config.option.ItemSelectOption.Data)OptionFactory.method28(
                  "inventoryButtonItem"
               )
               .HROHOIOCHIRIHICOORIHOHCIOIRIIH(method3()))
            .OHOOORICRHIIIIRHCICICOCHROICRC(arg0 -> {
               if (arg0.startsWith("skyblock:")) {
                  String text3x = arg0.substring(9);
                  String text2x = com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemRegistry.field4.get(text3x);
                  return text2x != null ? text2x : arg0;
               } else {
                  ItemCounterEntry itemcounter_21x = (ItemCounterEntry)ItemSelectOption.method11().get(arg0);
                  return itemcounter_21x != null ? itemcounter_21x.toString() : arg0;
               }
            }))
         .OOOHICCHHHRHCORIRCRHOCROROIOCR(arg1x -> {
            LinkedHashSet set2x = new LinkedHashSet();
            set2x.add(arg1x);
            ((MultiSelectOption)this.field23.getOption()).method1(set2x);
            this.field22.setItemId(arg1x);
            this.field22.method10();
         });
      if (highlight52.getItemId() != null) {
         data11.HIIIOHRRROCICIOIORRRIRCRCHHIII(Set.of(highlight52.getItemId()));
      }

      MultiSelectOption lightingextension491212 = (MultiSelectOption)data11.RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      ToggleOption lightingextension44313 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("inventoryButtonShowInContainers")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(highlight52.method23()))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      LinkedHashSet set14 = this.method10(
         new GuiWidget[]{
            lightingextension4723.method18(this.field19),
            lightingextension4724.method18(this.field19),
            lightingextension4975.HIOROOCORHICHIRIHIRCCORCIIICCH(this.field19),
            lightingextension42226.method1(this.field19),
            lightingextension42227.method1(this.field19),
            lightingextension49158.method18(this.field19),
            lightingextension49159.method18(this.field19),
            lightingextension49710.HIOROOCORHICHIRIHIRCCORCIIICCH(this.field19),
            lightingextension44313.method18(this.field19),
            this.field23 = (CrosshairEditorWidget)lightingextension491212.method18(this.field19),
            this.field24 = new DropdownWidget(this.field19),
            this.field25 = new ProgressBarWidget(this.field19, ResourceLocationBridge.create("lunar", "icons/cosmetics/back-40x40.png"))
         }
      );
      this.field19.method14(new ArrayList(set14));
      this.field19.setTitle("editInventoryButton");
      lightingextension4723.HORHIRROCIOIICIOHCOCCOOHIRCCRI(highlight52::setSize);
      lightingextension4724.HORHIRROCIOIICIOHCOCCOOHIRCCRI(highlight52::setScale);
      lightingextension4975.HORHIRROCIOIICIOHCOCCOOHIRCCRI(highlight52::method28);
      lightingextension42226.HORHIRROCIOIICIOHCOCCOOHIRCCRI(highlight52::method29);
      lightingextension42227.HORHIRROCIOIICIOHCOCCOOHIRCCRI(highlight52::method30);
      lightingextension49158.HORHIRROCIOIICIOHCOCCOOHIRCCRI(highlight52::setCommand);
      lightingextension49159.HORHIRROCIOIICIOHCOCCOOHIRCCRI(highlight52::method32);
      lightingextension49710.HORHIRROCIOIICIOHCOCCOOHIRCCRI(highlight52::method33);
      lightingextension44313.HORHIRROCIOIICIOHCOCCOOHIRCCRI(highlight52::method35);
      this.field25.method4((arg1x, arg2x) -> {
         Ref.method3().bridge$displayScreen(bridge7iterator1 == null ? null : Bridge.method8().method18(bridge7iterator1));
         return true;
      });
   }

   private static List<String> method3() {
      ArrayList list0 = new ArrayList(ItemSelectOption.method10());

      for (Item item4 : com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemRegistry.items) {
         if (item4.material().equals("SKULL_ITEM")) {
            list0.add("skyblock:" + item4.id());
         }
      }

      return list0;
   }

   protected List<GuiWidget> method25() {
      this.field19 = new TitledWidget(null, "editInventoryButton") {
         public void method3(float value1, float value2, float value3, float value4) {
            float value5 = 0.0F;

            for (GuiWidget calculator2handler7 : InventoryButtonsEditor.this.field19.method14()) {
               if (calculator2handler7 instanceof OptionWidget calculator2iterator38) {
                  float value9 = calculator2handler7.getHeight();
                  calculator2iterator38.method1(value1 + 8.0F, value2 + 32.0F + value5, value3 - 16.0F);
                  value5 += value9 + 4.0F;
               }
            }

            InventoryButtonsEditor.this.field24.RIIICIRHRCIHOOOORHOICRIICCCRHR(value1 + value3 - 6.0F, value2 + 30.0F, 4.0F, value4 - 60.0F);
            InventoryButtonsEditor.this.field24.method15(value5);
            InventoryButtonsEditor.this.field25.RIIICIRHRCIHOOOORHOICRIICCCRHR(value1 + 6.0F, value2 + 300.0F - 24.0F, 18.0F, 18.0F);
         }

         public void method3(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data22, boolean flag3) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method94(mixinhelper_41, this.x, this.y + 24.0F, this.width, 0.5F, 553648127);
            com.moonsworth.lunar.client.ui.LcuiScreen.method106(mixinhelper_41, this.x, this.y + 1.0F, this.width, 23.0F, 5.0F, 620756992);
            FontRegistry.method19().method17(mixinhelper_41, this.getTitle(), this.x + 8.0F, this.y + 6.0F, -1, false);
            com.moonsworth.lunar.client.ui.LcuiScreen.method94(mixinhelper_41, this.x, this.y + this.height - 30.0F, this.width, 0.5F, 553648127);
            com.moonsworth.lunar.client.ui.LcuiScreen.method105(
               mixinhelper_41, this.x, this.y + this.height - 29.5F, this.width, 28.5F, 5.0F, 1157627904
            );
            com.moonsworth.lunar.client.ui.LcuiScreen.method56(mixinhelper_41, this.x - 1.0F, this.y, this.width + 2.0F, this.height, 4.0F, 1073741824);
            com.moonsworth.lunar.client.ui.LcuiScreen.method56(mixinhelper_41, this.x, this.y + 1.0F, this.width, this.height - 2.0F, 3.0F, 553648127);
            com.moonsworth.lunar.client.ui.LcuiScreen.method117(
               mixinhelper_41, this.x, this.y + 1.0F, this.width, this.height - 2.0F, 5.0F, Integer.MIN_VALUE
            );
            com.moonsworth.lunar.client.ui.LcuiScreen.method111(
               mixinhelper_41, this.x - 2.0F, this.y + 24.0F, this.width + 4.0F, this.height - 54.0F, 1.0F
            );
            InventoryButtonsEditor.this.field24.method5(mixinhelper_41, data22, flag3);
            com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data24 = InventoryButtonsEditor.this.field24.method4(data22);

            for (GuiWidget calculator2handler6 : this.field11) {
               if (calculator2handler6.method24() && !(calculator2handler6 instanceof ProgressBarWidget)) {
                  boolean flag7 = calculator2handler6.getY() + calculator2handler6.getHeight() + InventoryButtonsEditor.this.field24.method3() < InventoryButtonsEditor.this.field24.getY();
                  boolean flag8 = calculator2handler6.getY() + InventoryButtonsEditor.this.field24.method3()
                     > InventoryButtonsEditor.this.field24.getY() + InventoryButtonsEditor.this.field24.getHeight();
                  if (calculator2handler6.getX() >= this.x && !flag7 && !flag8) {
                     calculator2handler6.method3(mixinhelper_41, data24, flag3 && this.method10(calculator2handler6, data22, new GuiWidget[0]));
                  }
               }
            }

            InventoryButtonsEditor.this.field24.method7(mixinhelper_41, data22, flag3);
            com.moonsworth.lunar.client.ui.LcuiScreen.method112(mixinhelper_41);
            InventoryButtonsEditor.this.field25.method3(mixinhelper_41, data22, flag3);
         }
      };
      this.field19.method4((arg1, arg2) -> {
         com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data23 = this.field24.method4(arg1);
         boolean flag4 = this.method3(arg1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH());

         for (GuiWidget calculator2handler6 : this.field19.method14()) {
            com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data27;
            if (calculator2handler6 != this.field25 && calculator2handler6 != this.field24) {
               if (!flag4) {
                  continue;
               }

               data27 = data23;
            } else {
               data27 = arg1;
            }

            if (calculator2handler6.method1(data27) && calculator2handler6.method6(data27, arg2)) {
               com.moonsworth.lunar.client.ui.LcuiScreen.method15();
               return true;
            }
         }

         return false;
      });
      this.field19.method17((arg1, arg2) -> {
         com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data23 = this.field24.method4(arg1);
         boolean flag4 = this.method3(arg1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH());

         for (GuiWidget calculator2handler6 : this.field19.method14()) {
            com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data27;
            if (calculator2handler6 != this.field25 && calculator2handler6 != this.field24) {
               if (!flag4) {
                  continue;
               }

               data27 = data23;
            } else {
               data27 = arg1;
            }

            if (calculator2handler6.method1(data27) && calculator2handler6.method7(data27, arg2)) {
               com.moonsworth.lunar.client.ui.LcuiScreen.method15();
               return true;
            }
         }

         return false;
      });
      this.field19.method3((arg1, arg2) -> {
         com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data23 = this.field24.method4(arg1);

         for (GuiWidget calculator2handler5 : this.field19.method14()) {
            com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data26 = calculator2handler5 != this.field25 && calculator2handler5 != this.field24 ? data23 : arg1;
            if (!calculator2handler5.method1(data26) && calculator2handler5.method8(data26, arg2)) {
               com.moonsworth.lunar.client.ui.LcuiScreen.method15();
               return true;
            }
         }

         return false;
      });
      return ImmutableList.of(this.field19);
   }

   private boolean method3(float value1) {
      return value1 >= this.field24.getY() && value1 <= this.field24.getY() + this.field24.getHeight();
   }

   public void init() {
      float value1 = this.method22() / 2.0F - 160.0F;
      float value2 = this.method23() / 2.0F - 150.0F;
      this.field19.method2(value1, value2, 320.0F, 300.0F);
   }

   public void update() {
   }

   public void method10(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data22) {
      List list3 = Ref.method4().method40().method82().method165().method13();

      for (HighlightButton highlight55 : list3) {
         highlight55.method11(mixinhelper_41, false, com.moonsworth.lunar.client.ui.LcuiScreen.getScale());
      }

      for (HighlightButton highlight57 : list3) {
         if (highlight57.method14(data22)) {
            highlight57.method13(mixinhelper_41, false, com.moonsworth.lunar.client.ui.LcuiScreen.getScale());
         }
      }
   }

   public void method11(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data21, int number2) {
   }

   public void method12(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data21, int number2) {
   }

   public void method14(char character1, KeyCode bridgetype_82) {
   }

   public String getLanguagePath() {
      return super.getLanguagePath() + ".skyblockInventoryButtonsEditor";
   }

   public void close() {
   }
}
