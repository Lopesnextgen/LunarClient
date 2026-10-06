package com.moonsworth.lunar.client.mod.skyblock.highlighttrashdungeonitems;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.CompoundTagComponent;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPre;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.HashSet;
import java.util.List;

public class SkyblockHighlightTrashDungeonItems extends AbstractFeature {
   private final ScreenTitleListener field8 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private final ColorOption field9 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("highlightColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   private final ToggleOption field10 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("highlight50Quality")
      .method31();
   private final ToggleOption field11 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("highlightRecombobulated")
      .method31();
   private final ObjectOpenHashSet<SlotBridge> field12 = new ObjectOpenHashSet();

   public SkyblockHighlightTrashDungeonItems(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderHologramItem.class, this::method1);
      this.handle(EventSlotUpdate.class, this::method2);
      this.handle(EventScreenInitPre.class, arg1x -> this.field12.clear());
   }

   private void method1(EventRenderHologramItem data51) {
      if (IslandUtils.isOnIsland()) {
         if (this.field12.contains(data51.method3())) {
            data51.method1(this.field9.method14(0.0F));
         }
      }
   }

   private void method2(EventSlotUpdate highlightimpl1) {
      if (IslandUtils.isOnIsland()) {
         if (this.method13()) {
            int index2 = highlightimpl1.getSlot();
            if (index2 >= 0 && index2 != 49) {
               SlotBridge bridge3_183 = (SlotBridge)this.field8.method6().bridge$inventorySlots().get(index2);
               ItemStackBridge bridgeextension_44 = highlightimpl1.method3();
               if (this.method4(bridgeextension_44)) {
                  this.field12.add(bridge3_183);
               } else {
                  this.field12.remove(bridge3_183);
               }
            }
         }
      }
   }

   private boolean method13() {
      GuiContainerBridge bridge5extension_31 = this.field8.method6();
      if (bridge5extension_31 == null) {
         return false;
      }

      List list2 = bridge5extension_31.bridge$inventorySlots();
      if (list2.size() < 54) {
         return false;
      }

      ItemStackBridge bridgeextension_43 = ((SlotBridge)list2.get(49)).bridge$getItemStack();
      if (bridgeextension_43 != null && !bridgeextension_43.bridge$isEmpty()) {
         if (bridgeextension_43.bridge$getDisplayName().endsWith("Sell Item")) {
            return true;
         }

         List list4 = SkyblockItemUtil.method15(bridgeextension_43);
         return !list4.isEmpty() && ((String)list4.get(list4.size() - 1)).endsWith("Click to buyback!");
      } else {
         return false;
      }
   }

   private boolean method4(ItemStackBridge bridgeextension_41) {
      if (bridgeextension_41 != null && !bridgeextension_41.bridge$isEmpty()) {
         CompoundTagComponent mixinhelper_102 = (CompoundTagComponent)bridgeextension_41.bridge$getDataComponent(DataComponentTypes.field1);
         if (mixinhelper_102 == null) {
            return false;
         }

         CompoundTagBridge bridge_573 = mixinhelper_102.bridge$getData();
         if (bridge_573 == null) {
            return false;
         }

         if (!(Boolean)this.field10.get() && bridge_573.bridge$getInteger("baseStatBoostPercentage") == 50) {
            return false;
         }

         if (!(Boolean)this.field11.get() && bridge_573.bridge$getInteger("rarity_upgrades") > 0) {
            return false;
         }

         HashSet set4 = ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15().method21();
         return set4 == null ? false : set4.contains(bridge_573.bridge$getString("id"));
      } else {
         return false;
      }
   }

   public String getId() {
      return "SKYBLOCK_HIGHLIGHT_TRASH_DUNGEON_ITEMS";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field9, this.field10, this.field11})
      );
   }
}
