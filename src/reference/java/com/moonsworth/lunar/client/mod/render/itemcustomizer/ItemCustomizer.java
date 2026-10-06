package com.moonsworth.lunar.client.mod.render.itemcustomizer;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.itemcounter.ItemCounterEntry;
import com.moonsworth.lunar.client.config.option.ItemSelectOption;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ItemCustomizer extends AbstractFeature {
   private final CustomHeldItems field8 = new CustomHeldItems(this);
   private final HeldItemAnimations field9 = new HeldItemAnimations(this);
   private final CustomDroppedItems field10 = new CustomDroppedItems(this);
   private final ItemCounterEntry field11 = new ItemCounterEntry("minecraft:air");
   private final Cache<String, ItemCounterEntry> field12 = Caffeine.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build();

   public ItemCustomizer() {
      super(false);
   }

   public String getId() {
      return "ITEM_CUSTOMIZER";
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method2(new String[]{"Animations", "UhcOverlay", "ItemSize", "ItemResizer", "ItemScale", "HeldItem", "Overlay", "ItemModel", "ViewModel"})
         .method11(this);
   }

   protected List<Framework7Extension> method9() {
      return List.of(this.field8, this.field9, this.field10);
   }

   @Nullable
   public ItemCounterEntry method3(ItemStackBridge bridgeextension_41) {
      if (bridgeextension_41 == null) {
         return null;
      }

      ItemCounterEntry itemcounter_22 = (ItemCounterEntry)this.field12.getIfPresent(bridgeextension_41.bridge$getDisplayName());
      if (itemcounter_22 == this.field11) {
         return null;
      }

      if (itemcounter_22 != null) {
         return itemcounter_22;
      }

      for (ItemCounterEntry itemcounter_24 : ItemSelectOption.method11().values()) {
         if (itemcounter_24.method2(bridgeextension_41)) {
            this.field12.put(bridgeextension_41.bridge$getDisplayName(), itemcounter_24);
            return itemcounter_24;
         }
      }

      this.field12.put(bridgeextension_41.bridge$getDisplayName(), this.field11);
      return null;
   }

   @Nullable
   public ItemCounterEntry method4(String text1) {
      return (ItemCounterEntry)ItemSelectOption.method11().get(text1);
   }

   public static void method5(Framework7Extension framework7extension0, boolean flag1) {
      if (flag1) {
         ((OptionContainer)framework7extension0.method1(ModTraits.field14)).method5(framework7extension0);
      }

      LcuiScreen.method144();
   }

   public static void method6(Framework7Extension framework7extension0, boolean flag1) {
      method5(framework7extension0, flag1);
      Ref.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   @Generated
   public CustomHeldItems method13() {
      return this.field8;
   }

   @Generated
   public HeldItemAnimations method14() {
      return this.field9;
   }

   @Generated
   public CustomDroppedItems method15() {
      return this.field10;
   }
}
