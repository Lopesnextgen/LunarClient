package com.moonsworth.lunar.client.mod.skyblock.pickonimbusdurability;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.CompoundTagComponent;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.event.render.EventRenderItemDurability;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import java.util.Optional;

public class SkyblockPickonimbusDurability extends AbstractFeature {
   private static final Integer field8 = 2000;

   public SkyblockPickonimbusDurability(Skyblock skyblock1) {
      super(false);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderItemDurability.class, this::method1);
   }

   private void method1(EventRenderItemDurability highlightimpl121) {
      this.method2(highlightimpl121.getItem()).ifPresent(arg1x -> highlightimpl121.method2((double)arg1x.intValue() / field8.intValue()));
   }

   private Optional<Integer> method2(ItemStackBridge bridgeextension_41) {
      if (bridgeextension_41 != null && !bridgeextension_41.bridge$isEmpty()) {
         String text2 = SkyblockItemUtil.method2(bridgeextension_41);
         if (!"PICKONIMBUS".equals(text2)) {
            return Optional.empty();
         } else {
            CompoundTagComponent mixinhelper_103 = (CompoundTagComponent)bridgeextension_41.bridge$getDataComponent(DataComponentTypes.field1);
            if (mixinhelper_103 != null && mixinhelper_103.bridge$getData() != null) {
               return mixinhelper_103.bridge$getData().bridge$contains("pickonimbus_durability", 3)
                  ? Optional.of(mixinhelper_103.bridge$getData().bridge$getInteger("pickonimbus_durability"))
                  : Optional.of(field8);
            } else {
               return Optional.empty();
            }
         }
      } else {
         return Optional.empty();
      }
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_PICKONIMBUS_DURABILITY";
   }
}
