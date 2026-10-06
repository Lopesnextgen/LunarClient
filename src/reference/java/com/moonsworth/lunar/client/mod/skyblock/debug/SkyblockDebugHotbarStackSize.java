package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize.ItemStackSize;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ConstantName;
import java.util.Objects;

public class SkyblockDebugHotbarStackSize extends AbstractFeature {
   private static final int field8 = 64;
   private int field9;

   public SkyblockDebugHotbarStackSize(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
      this.handle(ItemStackSize.class, this::method3);
      this.handle(EventRenderItemStackSize.class, this::method3);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_DEBUG_HOTBAR_STACK_SIZE";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5, ModCategory.field7}).method8().method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(
         new ClientOption[]{OptionFactory.method14("debugRerollStackSizes").method4(() -> this.field9++).method31()}
      );
   }

   private void method3(EventRenderItemStackSize highlightimpl121) {
      ItemStackBridge bridgeextension_42 = highlightimpl121.getItem();
      if (bridgeextension_42 != null && !bridgeextension_42.bridge$isEmpty()) {
         if (this.mc.bridge$getCurrentScreen() == null) {
            int number3 = Math.floorMod(Objects.hash(bridgeextension_42.bridge$getDisplayName(), this.field9), 64) + 1;
            highlightimpl121.setText("" + ChatFormatting.AQUA + number3);
         }
      }
   }
}
