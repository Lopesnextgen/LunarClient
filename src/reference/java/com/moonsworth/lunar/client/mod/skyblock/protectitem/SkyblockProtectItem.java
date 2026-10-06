package com.moonsworth.lunar.client.mod.skyblock.protectitem;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiRecipeBookBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClickTypeBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.overlay.HudColorOverride;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.listener.PersistentValuesListener;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramText;
import com.moonsworth.lunar.client.event.player.EventItemDrop;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderInventoryScreen.EventRenderHotbarPost;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockProtectItem extends AbstractFeature {
   public static final ResourceLocationBridge field8 = ResourceLocationBridge.create("lunar", "icons/stars/star-12x12.png");
   private final PersistentValuesListener field9 = (PersistentValuesListener)this.method63(PersistentValuesListener.class);
   private final DungeonMapListener field10 = (DungeonMapListener)this.method63(DungeonMapListener.class);
   private final HighlightTypeListener field11 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final ModifierKeybindOption field12 = (ModifierKeybindOption)OptionFactory.method18("slotProtectkeybind")
      .method5(KeyCode.KEY_P)
      .method3(false)
      .method31();
   private final ModifierKeybindOption field13 = (ModifierKeybindOption)OptionFactory.method18("slotProtectAllKeybind")
      .method5(KeyCode.KEY_NONE)
      .method3(false)
      .method31();
   private final ColorOption field14 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "slotLockingColorOption"
         )
         .method4(-171))
      .method31();
   private long field15 = 0L;
   private KeyCode field16 = null;

   public SkyblockProtectItem(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.ITEMS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventItemDrop.class, this::method1);
      this.handle(EventKeybind.class, this::method3);
      this.handle(EventRenderHotbarPost.class, this::method6);
      this.handle(EventRenderHologramText.class, this::method5);
      this.handle(EventRenderSlot.class, this::method2);
   }

   private void method1(EventItemDrop highlightimpl2_21) {
      if (!this.field10.method5().<Boolean>map(DungeonStateTracker::method38).orElse(false)) {
         if (this.method7(highlightimpl2_21.method1())) {
            highlightimpl2_21.cancel();
         }
      }
   }

   private void method2(EventRenderSlot highlightimpl51) {
      boolean flag2 = highlightimpl51.method5() != null
         && highlightimpl51.method5()
            .bridge$getItemStack()
            .bridge$getTooltip(Ref.method7(), false)
            .stream()
            .anyMatch(arg0 -> ChatFormatting.getTextWithoutFormattingCodes(arg0).trim().equals("Sell Price"));
      boolean flag3 = this.field11.method7() == SkyblockMenuType.SALVAGE;
      boolean flag4 = this.field11.method7() == SkyblockMenuType.DRACONIC_SACRIFICE;
      if ((flag2 || flag3 || flag4) && highlightimpl51.method5() != null && this.method7(highlightimpl51.method5().bridge$getItemStack())) {
         highlightimpl51.cancel();
      } else if (highlightimpl51.method7() == ClickTypeBridge.THROW && highlightimpl51.getSlotId() > 0 && highlightimpl51.method5() != null && this.method7(highlightimpl51.method5().bridge$getItemStack())) {
         highlightimpl51.cancel();
      } else if (highlightimpl51.method7() == ClickTypeBridge.PICKUP && highlightimpl51.getSlotId() < 0 && this.method7(highlightimpl51.method4().bridge$getCursor())) {
         highlightimpl51.cancel();
      }
   }

   private void method3(EventKeybind highlightimpl1) {
      if (highlightimpl1.method11() == com.moonsworth.lunar.client.event.input.InputAction.DOWN) {
         KeyCode bridgetype_82 = highlightimpl1.method10();
         int number3 = ModifierKeybindOption.method18();
         if (bridgetype_82 != this.field16 || Ref.method3().bridge$getSystemTime() - this.field15 >= 50L) {
            if (this.field12.method5(bridgetype_82, number3)) {
               this.method13();
            }

            if (this.field13.method5(bridgetype_82, number3)) {
               this.method14();
            }

            this.field16 = bridgetype_82;
            this.field15 = Ref.method3().bridge$getSystemTime();
         }
      }
   }

   private void method13() {
      if (Ref.method3().bridge$getCurrentScreenOrRewind() instanceof GuiContainerBridge bridge5extension_32) {
         SlotBridge bridge3_183 = bridge5extension_32.bridge$getHoveredSlot();
         if (bridge3_183 != null) {
            String text4 = SkyblockItemUtil.method18(bridge3_183.bridge$getItemStack());
            if (text4 != null && !text4.isBlank()) {
               this.method9(text4);
            }
         }
      }
   }

   private void method5(EventRenderHologramText data61) {
      if (this.method7(data61.method3().bridge$getItemStack())) {
         int number2 = data61.method2().bridge$getGuiLeft() + data61.method3().bridge$getXDisplayPosition();
         int number3 = data61.method2().bridge$getGuiTop() + data61.method3().bridge$getYDisplayPosition();
         data61.method1().method24(field8, number2, number3, 6, 6, this.field14.method14(0.0F));
      }
   }

   private void method6(EventRenderHotbarPost data101) {
      MixinHelper_4 mixinhelper_42 = data101.method1();
      Bridge5Extension_5 bridge5extension_53 = Ref.method7();

      for (int index4 = 0; index4 < 9; index4++) {
         ItemStackBridge bridgeextension_45 = (ItemStackBridge)bridge5extension_53.bridge$getInventory().bridge$getMainInventory().get(index4);
         if (this.method7(bridgeextension_45)) {
            int number6 = data101.getX() + 3 + index4 * 20;
            int number7 = data101.getY() + 3;
            HudColorOverride.method3();

            try {
               mixinhelper_42.method24(field8, number6, number7, 6, 6, this.field14.method14(0.0F));
            } finally {
               HudColorOverride.method4();
            }
         }
      }
   }

   public boolean method7(ItemStackBridge bridgeextension_41) {
      String text2 = SkyblockItemUtil.method18(bridgeextension_41);
      if (text2 != null && !text2.isBlank()) {
         Set set3 = this.field9.method5().protectedItems.protectedItems;
         return set3.contains(text2);
      } else {
         return false;
      }
   }

   private void method14() {
      GuiScreenBridge bridge5extension61 = Ref.method3().bridge$getCurrentScreenOrRewind();
      if (bridge5extension61 instanceof GuiContainerBridge bridge5extension_32) {
         if (bridge5extension61 instanceof GuiRecipeBookBridge) {
            for (SlotBridge bridge3_187 : bridge5extension_32.bridge$inventorySlots()) {
               String text8 = SkyblockItemUtil.method18(bridge3_187.bridge$getItemStack());
               if (text8 != null && !text8.isBlank()) {
                  this.method9(text8);
               }
            }
         } else {
            for (int index3 = 0; index3 < bridge5extension_32.bridge$getLowerChestSizeInventory(); index3++) {
               SlotBridge bridge3_184 = (SlotBridge)bridge5extension_32.bridge$inventorySlots().get(index3);
               String text5 = SkyblockItemUtil.method18(bridge3_184.bridge$getItemStack());
               if (text5 != null && !text5.isBlank()) {
                  this.method9(text5);
               }
            }
         }
      }
   }

   private void method9(String text1) {
      Set set2 = this.field9.method5().protectedItems.protectedItems;
      if (!set2.remove(text1)) {
         set2.add(text1);
      }

      this.field9.markDirty();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(new ClientOption[]{this.field12, this.field13, this.field14});
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_PROTECT_ITEM";
   }

   @Generated
   public ColorOption method15() {
      return this.field14;
   }

   @KeepName
   public static class Data {
      @com.moonsworth.lunar.client.util.io.NotNullSerialized
      private final Set<String> protectedItems = new HashSet<>();

      public Data() {
      }
   }
}
