package com.moonsworth.lunar.client.mod.skyblock.middleclickgui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClickTypeBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.MiddleClickGuiRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.util.List;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockMiddleClickGui extends AbstractFeature {
   private final ScreenTitleListener field8 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);

   public SkyblockMiddleClickGui(Skyblock skyblock1) {
      super(false);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.INVENTORY));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderSlot.class, this::method1);
   }

   private void method1(EventRenderSlot highlightimpl51) {
      if (!LcuiScreen.isCtrlKeyDown() && !LcuiScreen.isShiftKeyDown()) {
         if (highlightimpl51.method6() == 0) {
            SlotBridge bridge3_182 = highlightimpl51.method5();
            if (bridge3_182 != null) {
               ItemStackBridge bridgeextension_43 = bridge3_182.bridge$getItemStack();
               if (bridgeextension_43 != null && !bridgeextension_43.bridge$isEmpty()) {
                  String text4 = this.field8.method5();
                  if (text4 != null) {
                     List list5 = this.field8.method6().bridge$inventorySlots();
                     if (list5.size() > 51) {
                        ItemStackBridge bridgeextension_46 = ((SlotBridge)list5.get(51)).bridge$getItemStack();
                        if (bridgeextension_46 != null && bridgeextension_46.bridge$getDisplayName().endsWith("Rename Loadout")) {
                           text4 = "Loadout";
                        }
                     }

                     if (this.method2(text4, bridgeextension_43, bridge3_182)) {
                        highlightimpl51.method2(2);
                        highlightimpl51.method3(ClickTypeBridge.CLONE);
                     }
                  }
               }
            }
         }
      }
   }

   private boolean method2(String text1, ItemStackBridge bridgeextension_42, SlotBridge bridge3_183) {
      if (text1.equals("Your Equipment and Stats")) {
         return true;
      }

      if (!this.method3(bridge3_183)) {
         return false;
      }

      MiddleClickGuiRegistry click64 = Ref.method4().method40().method82().method15().method20();
      if (click64 != null) {
         IntOpenHashSet intopenhashset5 = click64.find(text1);
         if (intopenhashset5 != null) {
            return intopenhashset5.contains(bridge3_183.bridge$getIndex());
         }
      }

      String text6 = SkyblockItemUtil.method2(bridgeextension_42);
      return text6.isEmpty();
   }

   private boolean method3(SlotBridge bridge3_181) {
      GuiContainerBridge bridge5extension_32 = this.field8.method6();
      return bridge5extension_32 == null ? false : ((SlotBridge)bridge5extension_32.bridge$inventorySlots().get(bridge3_181.bridge$getIndex())).equals(bridge3_181);
   }

   public static MiddleClickGuiRegistry method4(JsonArray array0) {
      if (array0 == null) {
         return null;
      }

      MiddleClickGuiRegistry click61 = new MiddleClickGuiRegistry();

      for (JsonElement element3 : array0) {
         JsonObject json4 = element3.getAsJsonObject();
         String text5 = json4.get("name").getAsString();
         JsonArray array6 = json4.getAsJsonArray("slots");
         IntOpenHashSet intopenhashset7 = new IntOpenHashSet();

         for (JsonElement element9 : array6) {
            String text10 = element9.getAsString();
            if (text10.contains("-")) {
               String[] items11 = text10.split("-");
               int number12 = Integer.parseInt(items11[0]);
               int number13 = Integer.parseInt(items11[1]);

               for (int index14 = number12; index14 <= number13; index14++) {
                  intopenhashset7.add(index14);
               }
            } else {
               intopenhashset7.add(Integer.parseInt(text10));
            }
         }

         if (text5.length() > 2 && text5.startsWith("*") && text5.endsWith("*")) {
            click61.registerContains(text5.substring(1, text5.length() - 1), intopenhashset7);
         } else if (text5.startsWith("^")) {
            click61.registerPrefix(text5.substring(1), intopenhashset7);
         } else if (text5.endsWith("$")) {
            click61.registerSuffix(text5.substring(0, text5.length() - 1), intopenhashset7);
         } else {
            click61.registerExact(text5, intopenhashset7);
         }
      }

      return click61;
   }

   public String getId() {
      return "SKYBLOCK_MIDDLE_CLICK_GUI";
   }
}
