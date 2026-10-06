package com.moonsworth.lunar.client.mod.skyblock.farmingtoolcropicon;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.files.ValuePair;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SkyblockFarmingToolCropIcon extends AbstractFeature {
   private static final List<ValuePair<String, ItemBridge>> field8 = List.of(
      new ValuePair("THEORETICAL_HOE_WHEAT", Bridge.method28().method44()),
      new ValuePair("THEORETICAL_HOE_CARROT", Bridge.method28().method80()),
      new ValuePair("THEORETICAL_HOE_POTATO", Bridge.method28().method81()),
      new ValuePair("PUMPKIN_DICER", Bridge.method28().method82()),
      new ValuePair("THEORETICAL_HOE_CANE", Bridge.method28().method83()),
      new ValuePair("MELON_DICER", Bridge.method28().method84()),
      new ValuePair("CACTUS_KNIFE", Bridge.method28().method85()),
      new ValuePair("COCO_CHOPPER", Bridge.method28().method86()),
      new ValuePair("FUNGI_CUTTER", Bridge.method28().method87()),
      new ValuePair("THEORETICAL_HOE_WARTS", Bridge.method28().method88()),
      new ValuePair("THEORETICAL_HOE_SUNFLOWER", Bridge.method28().method89()),
      new ValuePair("THEORETICAL_HOE_WILD_ROSE", Bridge.method28().method90())
   );
   private final HighlightTypeListener field9 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final LoadingCache<ItemStackBridge, Optional<ItemBridge>> field10 = CacheBuilder.newBuilder()
      .expireAfterAccess(1L, TimeUnit.MINUTES)
      .build(new CacheLoader<ItemStackBridge, Optional<ItemBridge>>() {
         public Optional<ItemBridge> method1(ItemStackBridge bridgeextension_41) {
            return SkyblockFarmingToolCropIcon.this.method2(bridgeextension_41);
         }
      });

   public SkyblockFarmingToolCropIcon(Skyblock skyblock1) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderHologramItem.class, this::method1);
   }

   private void method1(EventRenderHologramItem data51) {
      if (this.field9.method7() == SkyblockMenuType.FARMING_TOOLKIT) {
         if (!LcuiScreen.isCtrlKeyDown() && !LcuiScreen.isShiftKeyDown()) {
            try {
               SlotBridge bridge3_182 = data51.method3();
               if (bridge3_182.bridge$getIndex() >= 45) {
                  return;
               }

               Optional optional3 = (Optional)this.field10.get(bridge3_182.bridge$getItemStack());
               if (optional3.isEmpty()) {
                  return;
               }

               ItemStackBridge bridgeextension_44 = Bridge.method8().method38((ItemBridge)optional3.get());
               data51.method2(bridgeextension_44);
            } catch (ExecutionException executionexception5) {
               CrashReporter.method5(executionexception5, "SkyBlockFarmingToolCropIcon");
            }
         }
      }
   }

   private Optional<ItemBridge> method2(ItemStackBridge bridgeextension_41) {
      String text2 = SkyblockItemUtil.method2(bridgeextension_41);
      if (text2.isEmpty()) {
         return Optional.empty();
      }

      for (ValuePair files6_24 : field8) {
         if (text2.startsWith((String)files6_24.field1)) {
            return Optional.of((ItemBridge)files6_24.field2);
         }
      }

      return Optional.empty();
   }

   public String getId() {
      return "SKYBLOCK_FARMING_TOOL_CROP_ICON";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
