package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderInventoryScreen;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderInventoryScreen.EventRenderHotbarItems;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import com.moonsworth.lunar.client.mod.skyblock.raritybackground.SkyblockRarityBackground;

public class SkyblockDebugRarityBackground extends AbstractFeature {
   private final ColorOption field8 = (ColorOption)((Data)OptionFactory.method8("debugRarityFallbackColor")
         .method4(1722460842))
      .method15()
      .method31();

   public SkyblockDebugRarityBackground(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method6(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
      this.handle(EventRenderHotbarItems.class, this::method3);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender.class, this::method4);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_DEBUG_RARITY_BACKGROUND";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5, ModCategory.field7}).method8().method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
   }

   private void method3(EventRenderInventoryScreen highlightbase21) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         SkyblockRarityBackground skyblockraritybackground3 = Ref.method4().method40().method82().method96();
         List list4 = bridge5extension_52.bridge$getInventory().bridge$getMainInventory();

         for (int index5 = 0; index5 < 9; index5++) {
            ItemStackBridge bridgeextension_46 = (ItemStackBridge)list4.get(index5);
            this.method5(highlightbase21.method1(), skyblockraritybackground3, bridgeextension_46, highlightbase21.getX() + 3 + index5 * 20, highlightbase21.getY() + 3);
         }
      }
   }

   private void method4(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender data1) {
      if (data1.method3() instanceof GuiContainerBridge bridge5extension_32) {
         SkyblockRarityBackground skyblockraritybackground7 = Ref.method4().method40().method82().method96();

         for (SlotBridge bridge3_185 : bridge5extension_32.bridge$inventorySlots()) {
            ItemStackBridge bridgeextension_46 = bridge3_185.bridge$getItemStack();
            this.method5(
               data1.method5(),
               skyblockraritybackground7,
               bridgeextension_46,
               bridge5extension_32.bridge$getGuiLeft() + bridge3_185.bridge$getXDisplayPosition(),
               bridge5extension_32.bridge$getGuiTop() + bridge3_185.bridge$getYDisplayPosition()
            );
         }
      }
   }

   private void method5(MixinHelper_4 mixinhelper_41, SkyblockRarityBackground skyblockraritybackground2, ItemStackBridge bridgeextension_43, int number4, int number5) {
      if (bridgeextension_43 != null && !bridgeextension_43.bridge$isEmpty()) {
         Integer number6 = skyblockraritybackground2.method5(bridgeextension_43);
         int number7 = number6 != null ? number6 : this.field8.method13();
         mixinhelper_41.method45(arg3x -> arg3x.method1(number4, number5, number4 + 16, number5 + 16, number7));
      }
   }
}
