package com.moonsworth.lunar.client.mod.skyblock.inventorybuttons;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiRecipeBookBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.config.GeneralSettings.Type;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.GuiModuleManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.HighlightSerializer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.HighlightButton;
import com.moonsworth.lunar.client.framework.feature.mod.gui.SkyblockInventoryButtonsMovementGui;
import com.moonsworth.lunar.client.replay.render.ExportTargetHandler;
import com.moonsworth.lunar.client.event.input.EventMarkerInput;
import com.moonsworth.lunar.client.event.input.MouseInputType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.util.io.CompressionUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import com.moonsworth.lunar.ichor.VersionGate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@VersionGate(min = 33)
public class SkyblockInventoryButtons extends AbstractFeature {
   private final ModifierKeybindOption field8 = (ModifierKeybindOption)((Data)OptionFactory.method18("openInventoryButtonMovementUIKey")
         .method18(this))
      .method31();
   private final List<HighlightButton> field9 = new ArrayList<>();
   private JsonArray field10;

   public SkyblockInventoryButtons(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.INVENTORY));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender.class, this::method2);
      this.handle(EventMarkerInput.class, this::method3);
   }

   public List<HighlightButton> method13() {
      if (this.field10 != null && HighlightSerializer.method1()) {
         for (JsonElement element2 : this.field10) {
            try {
               this.field9.add(HighlightSerializer.method2(element2.getAsJsonObject()));
            } catch (RuntimeException exception4) {
               LunarLogger.error("Dropping a malformed SkyBlock inventory button", exception4);
            }
         }

         this.field10 = null;
      }

      return this.field9;
   }

   private void method2(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender data1) {
      List list2 = this.method13();
      if (!list2.isEmpty()) {
         GuiScreenBridge bridge5extension63 = data1.IOHOCRRIRROIRCHHHHHRCOIIOHCOHR();
         boolean flag4 = bridge5extension63 instanceof GuiRecipeBookBridge;
         if (flag4 || bridge5extension63 instanceof GuiContainerBridge) {
            AbstractRenderContext bridgeextension_95 = data1.OCCRRRIHHOCOHOOOOIRROIORRCHIOR();
            bridgeextension_95.push();
            RewindHandlers rewindhandlers6 = Ref.method4().method40().method85().method35();
            if (rewindhandlers6 != null) {
               ExportTargetHandler rewindhandlers3impl87 = rewindhandlers6.method48();
               float value8 = (float)Ref.method3().bridge$displayWidth() / rewindhandlers3impl87.method26();
               float value9 = (float)Ref.method3().bridge$displayHeight() / rewindhandlers3impl87.method27();
               bridgeextension_95.scale(value8, value9, 1.0F);
            }

            boolean flag12 = Ref.method4().method41().method6().method16() == Type.ALL;
            float value13 = LcuiScreen.getScale();
            if (flag12) {
               value13 /= bridge5extension63.bridge$getInventoryScaleFactor();
            }

            for (HighlightButton highlight510 : list2) {
               highlight510.method11(data1.HHHRIHCIOHCICRCCOCIRRROHIOHRIH(), !flag4, value13);
            }

            Data2 data215 = data1.method1().OCRCCHICRRIROCIHCOROROHCIRCICO();
            if (flag12) {
               data215 = (Data2)data215.HORHIHCCOCCORIIIOHRRRROROCHCIC(bridge5extension63.bridge$getInventoryScaleFactor());
            }

            for (HighlightButton highlight511 : list2) {
               if (highlight511.method14(data215)) {
                  highlight511.method13(data1.HHHRIHCIOHCICRCCOCIRRROHIOHRIH(), !flag4, value13);
               }
            }

            bridgeextension_95.pop();
         }
      }
   }

   private void method3(EventMarkerInput highlightimpl141) {
      List list2 = this.method13();
      if (!list2.isEmpty()) {
         if (highlightimpl141.method4() == MouseInputType.CLICK) {
            if (!GuiModuleManager.isMouseInputConsumed()) {
               GuiScreenBridge bridge5extension63 = highlightimpl141.method1();
               boolean flag4 = bridge5extension63 instanceof GuiRecipeBookBridge;
               if (flag4 || bridge5extension63 instanceof GuiContainerBridge) {
                  Data2 data25 = highlightimpl141.method2().OCRCCHICRRIROCIHCOROROHCIRCICO();
                  data25 = (Data2)data25.HORHIHCCOCCORIIIOHRRRROROCHCIC(Ref.method4().method40().method96().method3(bridge5extension63));
                  RewindHandlers rewindhandlers6 = Ref.method4().method40().method85().method35();
                  if (rewindhandlers6 != null) {
                     ExportTargetHandler rewindhandlers3impl87 = rewindhandlers6.method48();
                     float value8 = (float)Ref.method3().bridge$displayWidth() / rewindhandlers3impl87.method26();
                     float value9 = (float)Ref.method3().bridge$displayHeight() / rewindhandlers3impl87.method27();
                     data25 = (Data2)data25.HCHHRHHCRIIORRRICOOCCOCHIRRRRR(value8, value9);
                  }

                  for (HighlightButton highlight512 : list2) {
                     if ((highlight512.method23() || flag4) && highlight512.method14(data25)) {
                        if (highlightimpl141.method3() == 1 && LcuiScreen.isShiftKeyDown()) {
                           Ref.method7().bridge$closeScreen();
                           Ref.method3()
                              .bridge$displayScreen(
                                 Bridge.method8().method18(new com.moonsworth.lunar.client.framework.feature.mod.gui.InventoryButtonsEditor(null, highlight512))
                              );
                        } else {
                           String text13 = highlight512.getCommand();
                           if (text13 == null || text13.isEmpty()) {
                              return;
                           }

                           if (text13.charAt(0) != '/') {
                              text13 = "/" + text13;
                           }

                           Ref.method7().bridge$closeScreen();
                           Ref.method7().bridge$sendChatMessage(text13);
                        }

                        return;
                     }
                  }
               }
            }
         }
      }
   }

   public void method1(JsonObject json1) {
      if (this.field10 != null) {
         json1.add("inventoryButtons", this.field10);
      } else {
         JsonArray array2 = new JsonArray();

         for (HighlightButton highlight54 : this.field9) {
            array2.add(HighlightSerializer.method3(highlight54));
         }

         json1.add("inventoryButtons", array2);
      }

      super.method1(json1);
   }

   public void load(JsonObject json1) {
      super.load(json1);
      this.field9.clear();
      this.field10 = null;
      if (json1.isJsonObject()) {
         JsonObject json2 = json1.getAsJsonObject();
         if (json2.has("inventoryButtons")) {
            this.field10 = json2.getAsJsonArray("inventoryButtons").deepCopy();
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_INVENTORY_BUTTONS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method2(
               new OptionProvider[]{
                  OptionFactory.method14("importInventoryButtons").method4(this::method15),
                  OptionFactory.method14("exportInventoryButtons").method4(this::method16),
                  this.field8
               }
            );
            ((SettingsSectionBuilder)arg1x.method2(
                  new OptionProvider[]{OptionFactory.method14("openInventoryButtonMovementUI").method4(this::method14)}
               ))
               .method3(() -> Ref.method8() == null);
         }
      );
      this.field8.method3(this::method14);
   }

   private void method14() {
      Ref.method3().bridge$displayScreen(Bridge.method8().method18(new SkyblockInventoryButtonsMovementGui()));
   }

   private void method15() {
      try {
         ArrayList list1 = new ArrayList();
         String text2 = CompressionUtils.method2(ClipboardUtils.method1());

         for (JsonElement element5 : (JsonArray)LunarConstants.field22.fromJson(text2, JsonArray.class)) {
            JsonObject json6 = element5.getAsJsonObject();
            list1.add(HighlightSerializer.method2(json6));
         }

         this.field10 = null;
         this.field9.clear();
         this.field9.addAll(list1);
         Ref.method4().method69().method3(NotificationManager.method15("importInventoryButtons", new Object[0]));
      } catch (Exception exception7) {
         Ref.method4().method69().method3(NotificationManager.method15("importInventoryButtonsFailed", new Object[0]));
      }
   }

   private void method16() {
      JsonArray array1 = new JsonArray();

      for (HighlightButton highlight53 : this.method13()) {
         array1.add(HighlightSerializer.method3(highlight53));
      }

      try {
         String text5 = CompressionUtils.method1(array1.toString());
         ClipboardUtils.method2(text5);
         Ref.method4().method69().method3(NotificationManager.method15("exportInventoryButtons", new Object[0]));
      } catch (IOException exception4) {
         Ref.method4().method69().method3(NotificationManager.method15("exportInventoryButtonsFailed", new Object[0]));
      }
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
