package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.EventEquippedItemChange;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.Set;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class EquippedItemListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final List<String> field7 = ImmutableList.of(
      "THEORETICAL_HOE", "PUMPKIN_DICER", "MELON_DICER", "COCO_CHOPPER", "CACTUS_KNIFE", "FUNGI_CUTTER"
   );
   private static final Set<String> field8 = Set.of("ANCESTRAL_SPADE", "ARCHAIC_SPADE", "DEIFIC_SPADE");
   private ItemStackBridge field9;
   @NotNull
   private String id = "";
   @NotNull
   private String field10 = "";

   public EquippedItemListener() {
      this.handle(EventTick.class, this::method1);
   }

   private void method1(EventTick event) {
      String text2 = this.field10;
      if (!this.method5()) {
         this.field9 = null;
         this.id = "";
         this.field10 = "";
      }

      if (!this.field10.equals(text2)) {
         LunarEventBus.method29().method12(EventEquippedItemChange.class, () -> new EventEquippedItemChange(this.field9, this.id, this.field10));
      }
   }

   private boolean method5() {
      if (!IslandUtils.isOnIsland()) {
         return false;
      }

      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 == null) {
         return false;
      }

      ItemStackBridge bridgeextension_42 = bridge5extension_51.bridge$getCurrentEquippedItem();
      if (bridgeextension_42 == null) {
         return false;
      }

      this.field9 = bridgeextension_42;
      this.id = SkyblockItemUtil.method2(bridgeextension_42);
      this.field10 = SkyblockItemUtil.method3(bridgeextension_42);
      return true;
   }

   public boolean method6() {
      for (String text2 : field7) {
         if (this.id.startsWith(text2)) {
            return true;
         }
      }

      return false;
   }

   public boolean method7() {
      for (String text2 : field8) {
         if (this.id.equals(text2)) {
            return true;
         }
      }

      return false;
   }

   @Generated
   public ItemStackBridge method8() {
      return this.field9;
   }

   @NotNull
   @Generated
   public String getId() {
      return this.id;
   }

   @NotNull
   @Generated
   public String method9() {
      return this.field10;
   }
}
