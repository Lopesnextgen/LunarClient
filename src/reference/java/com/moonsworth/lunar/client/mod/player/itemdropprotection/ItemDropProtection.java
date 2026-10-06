package com.moonsworth.lunar.client.mod.player.itemdropprotection;

import com.moonsworth.lunar.bridge.GuiContainerCreativeBridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiRecipeBookBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClickTypeBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramText;
import com.moonsworth.lunar.client.event.player.EventItemDrop;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderInventoryScreen.EventRenderHotbarPost;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ItemSelectOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.mod.player.slotlocking.SlotLocking;
import com.moonsworth.lunar.client.mod.player.inventorymod.InventoryMods;

public class ItemDropProtection extends AbstractFeature {
   private final ItemSelectOption field8 = (ItemSelectOption)OptionFactory.method28("itemDropProtectionProtectedItems").method31();
   private final ColorOption field9 = (ColorOption)((Data)OptionFactory.method8("itemDropProtectionIconColor")
         .method4(-171))
      .method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("itemDropProtectionNonInventory").method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("itemDropProtectCreativeInventory").method31();

   public ItemDropProtection(InventoryMods inventorymod1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(inventorymod1));
      this.handle(EventItemDrop.class, this::method2);
      this.handle(EventRenderSlot.class, this::method3);
      this.handle(EventRenderHologramText.class, this::method6);
      this.handle(EventRenderHotbarPost.class, this::method7);
   }

   @ConstantName
   public String getId() {
      return "ITEM_DROP_PROTECTION";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8, this.field9, this.field10, this.field11});
   }

   private void method2(EventItemDrop highlightimpl2_21) {
      if (this.method9(highlightimpl2_21.method1())) {
         highlightimpl2_21.cancel();
      }
   }

   private void method3(EventRenderSlot highlightimpl51) {
      if (this.method8(highlightimpl51.method4())) {
         if (highlightimpl51.method7() == ClickTypeBridge.THROW && highlightimpl51.getSlotId() >= 0 && highlightimpl51.method5() != null) {
            if (!this.method5(highlightimpl51) && this.method9(highlightimpl51.method5().bridge$getItemStack())) {
               highlightimpl51.cancel();
            }
         } else {
            if (highlightimpl51.method7() == ClickTypeBridge.PICKUP && (highlightimpl51.getSlotId() < 0 || this.method4(highlightimpl51)) && this.method9(highlightimpl51.method4().bridge$getCursor())) {
               highlightimpl51.cancel();
            }
         }
      }
   }

   private boolean method4(EventRenderSlot highlightimpl51) {
      return highlightimpl51.getSlotId() == 0
         && highlightimpl51.method5() != null
         && highlightimpl51.method5().bridge$getNumber() == 0
         && highlightimpl51.method5().bridge$getItemStack().bridge$isEmpty()
         && highlightimpl51.method4() instanceof GuiContainerCreativeBridge;
   }

   private boolean method5(EventRenderSlot highlightimpl51) {
      SlotBridge bridge3_182 = highlightimpl51.method5();
      return bridge3_182 != null
         && bridge3_182.bridge$getNumber() == highlightimpl51.getSlotId()
         && !bridge3_182.bridge$getItemStack().bridge$isEmpty()
         && highlightimpl51.method4() instanceof GuiContainerCreativeBridge;
   }

   private void method6(EventRenderHologramText data61) {
      if (this.method8(data61.method2())) {
         if (this.method9(data61.method3().bridge$getItemStack())) {
            int number2 = data61.method3().bridge$getXDisplayPosition();
            int number3 = data61.method3().bridge$getYDisplayPosition();
            if (Ref.MC_VERSION <= 5 || Ref.MC_VERSION >= 16) {
               number2 += data61.method2().bridge$getGuiLeft();
               number3 += data61.method2().bridge$getGuiTop();
            }

            data61.method1().method24(SlotLocking.field9, number2, number3, 6, 6, this.field9.method14(0.0F));
         }
      }
   }

   private void method7(EventRenderHotbarPost data101) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      data101.method1().push();
      data101.method1().method38(0.0F, 0.0F, 301.0F);

      for (int index3 = 0; index3 < 9; index3++) {
         ItemStackBridge bridgeextension_44 = (ItemStackBridge)bridge5extension_52.bridge$getInventory().bridge$getMainInventory().get(index3);
         if (this.method9(bridgeextension_44)) {
            int number5 = data101.getX() + 3 + index3 * 20;
            int number6 = data101.getY() + 3;
            data101.method1().method24(SlotLocking.field9, number5, number6, 6, 6, this.field9.method14(0.0F));
         }
      }

      if (Ref.MC_VERSION >= 2) {
         ItemStackBridge bridgeextension_47 = (ItemStackBridge)bridge5extension_52.bridge$getInventory().bridge$getOffhandInventory().get(0);
         if (this.method9(bridgeextension_47)) {
            int number8 = data101.getX() + 3 + (bridge5extension_52.bridge$isMainHandSwapped() ? 189 : -29);
            int number9 = data101.getY() + 3;
            data101.method1().method24(SlotLocking.field9, number8, number9, 6, 6, this.field9.method14(0.0F));
         }
      }

      data101.method1().pop();
   }

   private boolean method8(GuiContainerBridge bridge5extension_31) {
      return !this.field11.get() && bridge5extension_31 instanceof GuiContainerCreativeBridge
         ? false
         : (Boolean)this.field10.get() || bridge5extension_31 instanceof GuiRecipeBookBridge || bridge5extension_31 instanceof GuiContainerCreativeBridge;
   }

   private boolean method9(ItemStackBridge bridgeextension_41) {
      return bridgeextension_41 != null && !bridgeextension_41.bridge$isEmpty() ? this.field8.method5(bridgeextension_41) : false;
   }
}
