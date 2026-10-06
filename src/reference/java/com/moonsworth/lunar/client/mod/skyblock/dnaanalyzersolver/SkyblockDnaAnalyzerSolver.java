package com.moonsworth.lunar.client.mod.skyblock.dnaanalyzersolver;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemsBridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil.DyeColor;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.files.ValuePair;
import java.util.List;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockDnaAnalyzerSolver extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final ScreenTitleListener field9 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private final ColorOption field10 = (ColorOption)((Data)OptionFactory.method8("highlightColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hideTooltips").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private ValuePair<SlotBridge, SlotBridge> field12;

   public SkyblockDnaAnalyzerSolver(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventSlotUpdate.class, this::method1);
      this.handle(EventRenderHologramItem.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre.class, this::method6);
   }

   private void method1(EventSlotUpdate highlightimpl1) {
      if (this.field8.method7() == SkyblockMenuType.DNA_SOLVER) {
         this.field12 = null;
         List list2 = this.field9.method6().bridge$inventorySlots();

         for (int index3 = 1; index3 <= 8; index3++) {
            for (int index4 = 1; index4 <= 4; index4++) {
               int index5 = index3 + index4 * 9;
               SlotBridge bridge3_186 = (SlotBridge)list2.get(index5);
               ItemStackBridge bridgeextension_47 = bridge3_186.bridge$getItemStack();
               DyeColor type28 = this.method4(bridgeextension_47);
               if (type28 == null) {
                  return;
               }

               boolean flag9 = false;

               for (SlotBridge bridge3_1811 : this.method2(list2, index5)) {
                  DyeColor type212 = this.method4(bridge3_1811.bridge$getItemStack());
                  if (type28 == type212) {
                     flag9 = true;
                     break;
                  }
               }

               if (!flag9) {
                  this.field12 = ValuePair.method1(bridge3_186, this.method3(list2, index5, type28));
                  return;
               }
            }
         }
      }
   }

   private List<SlotBridge> method2(List<SlotBridge> list1, int index2) {
      return List.of((SlotBridge)list1.get(index2 - 10), (SlotBridge)list1.get(index2 - 1), (SlotBridge)list1.get(index2 + 8));
   }

   private SlotBridge method3(List<SlotBridge> list1, int number2, DyeColor type23) {
      int number4 = number2 % 9 - 1;

      for (int index5 = 1; index5 <= 4; index5++) {
         int index6 = number4 + index5 * 9;
         SlotBridge bridge3_187 = (SlotBridge)list1.get(index6);
         if (this.method4(bridge3_187.bridge$getItemStack()) == type23) {
            return (SlotBridge)list1.get(index6 + 1);
         }
      }

      return null;
   }

   private DyeColor method4(ItemStackBridge bridgeextension_41) {
      if (bridgeextension_41 != null && !bridgeextension_41.bridge$isEmpty()) {
         ItemBridge bridge6_42 = bridgeextension_41.bridge$getItem();
         ItemsBridge bridge2_213 = Bridge.method28();
         if (bridge6_42 == bridge2_213.method5()) {
            String text4 = bridgeextension_41.bridge$getDisplayName();
            if (text4 != null && text4.endsWith("DNA String")) {
               char character5 = text4.charAt(1);

               return switch (character5) {
                  case '9' -> DyeColor.BLUE;
                  case 'a' -> DyeColor.LIME;
                  case 'c' -> DyeColor.RED;
                  case 'e' -> DyeColor.YELLOW;
                  default -> null;
               };
            } else {
               return null;
            }
         } else if (bridge2_213.method96().contains(bridge6_42)) {
            return SkyblockItemUtil.method7(bridgeextension_41);
         } else {
            return bridge2_213.method97().contains(bridge6_42) ? SkyblockItemUtil.method6(bridgeextension_41) : null;
         }
      } else {
         return null;
      }
   }

   private void method5(EventRenderHologramItem data51) {
      if (this.field12 != null) {
         if (this.field8.method7() == SkyblockMenuType.DNA_SOLVER) {
            SlotBridge bridge3_182 = data51.method3();
            if (bridge3_182 == this.field12.field1 || bridge3_182 == this.field12.field2) {
               data51.method1(this.field10.method14(0.0F));
            }
         }
      }
   }

   private void method6(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre data21) {
      if ((Boolean)this.field11.get()) {
         if (this.field8.method7() == SkyblockMenuType.DNA_SOLVER) {
            data21.cancel();
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_DNA_ANALYZER_SOLVER";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field10, this.field11});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
