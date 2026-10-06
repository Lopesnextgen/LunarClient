package com.moonsworth.lunar.client.mod.skyblock.skeletonhelmethud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.EntityItemBridge;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;

public class SkyblockSkeletonHelmetHud extends AbstractFeature {
   private final ToggleOption hideSkeletonHatBones = (ToggleOption)OptionFactory.method7("hideSkeletonHatBones").method31();
   private boolean hasSkeletonHelmet = false;
   private int boneShieldCount = 0;

   public SkyblockSkeletonHelmetHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockSkeletonHelmetHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.HUD));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method51(this::onDisable);
      this.handle(EventTick.class, this::onTick);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method2);
      this.handle(EventRenderEntity.class, this::method3);
      this.handle(EventWorldChange.class, this::onWorldChange);
   }

   private void onTick(EventTick highlightimpl21) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         ItemStackBridge bridgeextension_43 = bridge5extension_52.bridge$getEquipmentInSlot(EntityEquipmentSlotBridge.HEAD);
         this.hasSkeletonHelmet = SkyblockItemUtil.method2(bridgeextension_43).equals("SKELETON_HELMET");
         if (!this.hasSkeletonHelmet) {
            this.boneShieldCount = 0;
         }
      }
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (this.hasSkeletonHelmet) {
         String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
         if (text2.startsWith("Your Bone Shield gained an extra bone!")) {
            this.boneShieldCount++;
         } else if (text2.startsWith("Bone Shield Health: ")) {
            this.boneShieldCount--;
         } else if (text2.equals("Your Bone Shield was destroyed!")) {
            this.boneShieldCount = 0;
         }
      }
   }

   private void method3(EventRenderEntity data81) {
      if ((Boolean)this.hideSkeletonHatBones.get()) {
         if (this.hasSkeletonHelmet) {
            if (data81.method1() instanceof EntityItemBridge bridgeextension522) {
               if (bridgeextension522.bridge$getItemStack().bridge$getItem() == Bridge.method28().method60()) {
                  if (bridgeextension522.bridge$getRidingEntity() instanceof EntityArmorStandBridge) {
                     if (!(bridgeextension522.method4(Ref.method7()) > 25.0)) {
                        data81.setCancelled(true);
                     }
                  }
               }
            }
         }
      }
   }

   private void onDisable() {
      this.hasSkeletonHelmet = false;
      this.boneShieldCount = 0;
   }

   private void onWorldChange(EventWorldChange data31) {
      this.hasSkeletonHelmet = false;
      this.boneShieldCount = 0;
   }

   public String getId() {
      return "SKYBLOCK_SKELETON_HELMET_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.hideSkeletonHatBones}));
   }

   private class Data extends TypedHudRenderer<HudLine> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 20, 30, 50, 120, 200);
      }

      @Nullable
      public HudLine method2(boolean flag1) {
         if (flag1) {
            return this.method3(3);
         } else {
            return !SkyblockSkeletonHelmetHud.this.hasSkeletonHelmet ? null : this.method3(SkyblockSkeletonHelmetHud.this.boneShieldCount);
         }
      }

      private HudLine method3(int number1) {
         return new HudLine(
            Bridge.method28().method60(),
            TextComponentFactory.builder()
               .method2("Bone Shield")
               .method4(number1 + "/3")
               .method5(NamedTextColor.GOLD)
               .method7(number1 == 0 ? NamedTextColor.GRAY : NamedTextColor.GREEN)
               .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
               .build()
         );
      }

      protected boolean method23() {
         return true;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }
}
