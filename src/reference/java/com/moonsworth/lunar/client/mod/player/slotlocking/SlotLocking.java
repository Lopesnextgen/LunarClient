package com.moonsworth.lunar.client.mod.player.slotlocking;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClickTypeBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.inventorymod.InventorySlotUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.Storageoverlay2;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotAfterItems;
import com.moonsworth.lunar.client.event.player.EventItemDrop;
import com.moonsworth.lunar.client.event.input.InputAction;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderInventoryScreen.EventRenderHotbarPost;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.files.ValuePair;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.player.inventorymod.InventoryMods;

public class SlotLocking extends AbstractFeature {
   private final DungeonMapListener field8 = (DungeonMapListener)this.method63(DungeonMapListener.class);
   public static final ResourceLocationBridge field9 = ResourceLocationBridge.create("lunar", "icons/cosmetics/padlock-32x32.png");
   private final Set<Integer> field10 = new HashSet<>();
   private final ModifierKeybindOption field11 = (ModifierKeybindOption)OptionFactory.method18("slotLockingKeyBind")
      .method5(KeyCode.KEY_L)
      .method31();
   private final ToggleOption field12 = (ToggleOption)OptionFactory.method7("slotLockingSkyblockOnly").method31();
   private final ColorOption field13 = (ColorOption)((Data)OptionFactory.method8("slotLockingColorOption")
         .method4(-13619152))
      .method31();
   private long field14 = 0L;
   private KeyCode field15 = null;

   public SlotLocking(InventoryMods inventorymod1) {
      super(true);
      this.method10(ModTraits.field16, ChildModBinding.method3(inventorymod1));
      this.handle(EventRenderSlot.class, this::method3);
      this.handle(EventItemDrop.class, this::method4);
      this.handle(EventKeybind.class, this::method2);
      this.handle(EventRenderHotbarPost.class, this::method8);
      this.handle(EventRenderContainerSlotAfterItems.class, this::method7);
   }

   public void method3(boolean flag1) {
      if (!flag1) {
         ToggleOption lightingextension4432 = Ref.method4().method40().method93().method15().method14();
         if ((Boolean)lightingextension4432.get()) {
            lightingextension4432.method10(false);
         }
      }
   }

   private void method2(EventKeybind highlightimpl1) {
      if (this.method9(true)) {
         if (highlightimpl1.method11() == InputAction.DOWN) {
            KeyCode bridgetype_82 = highlightimpl1.method10();
            if (this.field11.method5(bridgetype_82, ModifierKeybindOption.method18())) {
               if (bridgetype_82 == this.field15 && Ref.method3().bridge$getSystemTime() - this.field14 < 50L) {
                  return;
               }

               this.method13();
            }

            this.field15 = bridgetype_82;
            this.field14 = Ref.method3().bridge$getSystemTime();
         }
      }
   }

   private void method3(EventRenderSlot highlightimpl51) {
      if (this.method9(false)) {
         int number2 = InventorySlotUtils.method4(highlightimpl51.method4(), highlightimpl51.getSlotId());
         if (highlightimpl51.method7() == ClickTypeBridge.SWAP) {
            int number3 = highlightimpl51.method6();
            if (number3 == 40 && this.field10.contains(InventorySlotUtils.field1)) {
               highlightimpl51.cancel();
               return;
            }

            int number4 = 9 - number3 - 1;
            if (this.field10.contains(number4)) {
               highlightimpl51.cancel();
               return;
            }
         }

         if (InventorySlotUtils.method7(number2) && this.field10.contains(number2)) {
            highlightimpl51.cancel();
         }
      }
   }

   private void method4(EventItemDrop highlightimpl2_21) {
      if (!this.field8.method5().<Boolean>map(DungeonStateTracker::method38).orElse(false)) {
         if (this.method9(false)) {
            if (Ref.method3().bridge$getCurrentScreen() instanceof GuiContainerBridge bridge5extension_32) {
               int number4 = InventorySlotUtils.method5(bridge5extension_32, highlightimpl2_21.method1());
               if (InventorySlotUtils.method7(number4) && this.field10.contains(number4)) {
                  highlightimpl2_21.cancel();
               }
            } else {
               int number5 = InventorySlotUtils.method2();
               if (number5 != -1 && this.field10.contains(number5)) {
                  highlightimpl2_21.cancel();
               }
            }
         }
      }
   }

   private void method13() {
      int number1 = InventorySlotUtils.method1();
      if (InventorySlotUtils.method7(number1)) {
         this.method6(number1);
      }
   }

   private void method6(int index1) {
      if (this.field10.contains(index1)) {
         this.field10.remove(index1);
      } else {
         this.field10.add(index1);
      }
   }

   private void method7(EventRenderContainerSlotAfterItems data21) {
      if (this.method9(false)) {
         GuiScreenBridge bridge5extension62 = data21.method3();
         if (bridge5extension62 instanceof GuiContainerBridge) {
            MixinHelper_4 mixinhelper_43 = data21.method5();

            for (Integer index5 : this.field10) {
               if (InventorySlotUtils.method7(index5)) {
                  ValuePair files6_26 = InventorySlotUtils.method8(index5);
                  if (files6_26 != null) {
                     int number7 = (Integer)files6_26.field1;
                     int number8 = (Integer)files6_26.field2;
                     mixinhelper_43.method24(field9, number7 + 10, number8, 6, 6, this.field13.method14(0.0F));
                  }
               }
            }
         }
      }
   }

   private void method8(EventRenderHotbarPost data101) {
      if (this.method9(true)) {
         MixinHelper_4 mixinhelper_42 = data101.method1();

         for (int index3 = 0; index3 < 9; index3++) {
            int number4 = 9 - index3 - 1;
            if (this.field10.contains(number4)) {
               int number5 = data101.getX() + 3 + index3 * 20;
               int number6 = data101.getY() + 3;
               mixinhelper_42.method24(field9, number5 + 10, number6, 6, 6, this.field13.method14(0.0F));
            }
         }

         if (Ref.MC_VERSION >= 5) {
            Bridge5Extension_5 bridge5extension_57 = Ref.method7();
            if (this.field10.contains(InventorySlotUtils.field1)
               && bridge5extension_57 != null
               && bridge5extension_57.bridge$getOffHandItemRenderState() instanceof ItemStackBridge bridgeextension_48
               && !bridgeextension_48.bridge$isEmpty()) {
               mixinhelper_42.method24(field9, data101.getX() - 16, data101.getY() + 3, 6, 6, this.field13.method14(0.0F));
            }
         }
      }
   }

   public boolean method9(boolean flag1) {
      if ((Boolean)this.field12.get() && !IslandUtils.isOnIsland()) {
         return false;
      }

      if (!flag1 && Ref.MC_VERSION >= 1 && Storageoverlay2.method1()) {
         return false;
      }

      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      return bridge5extension_52 != null && !bridge5extension_52.bridge$getPlayerCapabilities().bridge$isCreativeMode();
   }

   public void method10(int index1, boolean flag2) {
      if (flag2) {
         this.field10.add(index1);
      } else {
         this.field10.remove(index1);
      }
   }

   public boolean method11(int number1) {
      return this.field10.contains(number1);
   }

   @ConstantName
   public String getId() {
      return "SLOT_LOCKING";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(new ClientOption[]{this.field11, this.field12, this.field13});
   }

   public void method1(JsonObject json1) {
      super.method1(json1);
      json1.add("lockedSlots", LunarConstants.field22.toJsonTree(this.field10));
   }

   public void load(JsonObject json1) {
      super.load(json1);
      if (json1.has("lockedSlots")) {
         JsonElement element2 = json1.get("lockedSlots");

         try {
            this.field10.addAll((Collection<? extends Integer>)LunarConstants.field22.fromJson(element2, new TypeToken<Collection<? extends Integer>>() {}));
         } catch (JsonSyntaxException jsonsyntaxexception4) {
         }
      }
   }

   @Generated
   public ColorOption method14() {
      return this.field13;
   }
}
