package com.moonsworth.lunar.client.mod.render.onesevenvisuals;

import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.BridgeExtension$Type;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.BridgeType2_4;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformVec3fBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectHitType;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.onesevenvisuals.modern.Modern;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTickEnd;
import com.moonsworth.lunar.client.event.mixin.highlight.GlintTransformEvent;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import org.joml.Vector3f;

@Annotation2(min = 5)
public class OneSevenItemsModern extends AbstractFeature {
   @Annotation2(5)
   public static final ItemTransformVec3fBridge ITEM_TRANSFORM = new ItemTransformVec3fBridge(
      new Vector3f(-110.0F, 15.0F, 75.0F), new Vector3f(-0.15F, 0.15F, 0.12F), new Vector3f(0.95F, 0.95F, 0.95F)
   );
   private final ToggleOption blockHitAnimation = OneSevenVisuals.toggleOption("blockHitAnimation");
   private final ToggleOption itemTransforms = OneSevenVisuals.toggleOption("itemTransforms");
   private final ToggleOption thirdPersonHeldItems = OneSevenVisuals.toggleOption("thirdPersonHeldItems");
   private final ToggleOption firstPersonCarpet = OneSevenVisuals.toggleOption("firstPersonCarpet");
   private final ToggleOption firstPersonFishingRod = OneSevenVisuals.toggleOption("firstPersonFishingRod");
   private final ToggleOption firstPersonSword = OneSevenVisuals.toggleOption("firstPersonSword");
   private final ToggleOption firstPersonPotions = OneSevenVisuals.toggleOption("firstPersonPotions");
   private final ToggleOption firstPersonFood = OneSevenVisuals.toggleOption("firstPersonFood");
   private final ToggleOption firstPersonBow = OneSevenVisuals.toggleOption("firstPersonBow");
   private boolean attacking = false;

   public OneSevenItemsModern(OneSevenVisuals var1) {
      super(false);
      this.method2(Framework.field16, Framework4.method3(var1));
      this.method2(GlintTransformEvent.class, this::applyItemTransform, Integer.MAX_VALUE);
      this.method10(EventTickEnd.class, this::onClientTick);
   }

   @Override
   public String getId() {
      return "ONE_SEVEN_ITEMS_MODERN";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.method4(
         SettingsPage.GENERAL, var1x -> var1x.method9(new ClientOption[]{this.blockHitAnimation, this.itemTransforms, this.thirdPersonHeldItems})
      );
      var1.method1(
         "itemSpecificToggles",
         var1x -> var1x.method9(
            new ClientOption[]{this.firstPersonSword, this.firstPersonBow, this.firstPersonFishingRod, this.firstPersonCarpet, this.firstPersonPotions, this.firstPersonFood}
         )
      );
   }

   private void onClientTick() {
      boolean var1 = this.mc.bridge$getGameSettings().bridge$keyBindAttack().bridge$isKeyDown();
      if (var1 != this.attacking) {
         this.attacking = var1;
         if (var1 && this.shouldPlayBlockHitAnimation(this.mc.bridge$getPlayer().bridge$getHeldItem(), false)) {
            this.mc.bridge$getPlayer().bridge$swingHandVisually(0);
            return;
         }
      }

      if (var1 && this.mc.bridge$getObjectMouseOver() != null && this.mc.bridge$getObjectMouseOver().bridge$isTypeOfHit(MovingObjectHitType.BLOCK)) {
         ItemStackBridge var2 = this.mc.bridge$getPlayer().bridge$getHeldItem();
         if (var2 != null && !var2.bridge$getItem().bridge$isItemSword() && this.shouldPlayBlockHitAnimation(var2, false)) {
            this.mc.bridge$getPlayer().bridge$swingHandVisually(0);
         }
      }
   }

   private void applyItemTransform(GlintTransformEvent var1) {
      if (this.mc.bridge$getPlayer() != null) {
         ItemTransformsBridge.Type var2 = var1.method8();
         if (var2.rightHand() && (var2.firstPerson() || var2.thirdPerson())) {
            boolean var3 = var2.thirdPerson() ? this.thirdPersonHeldItems.get() : this.itemTransforms.get();
            if (var2.firstPerson()) {
               if (!this.isFirstPersonItemEnabled(var1.method7())) {
                  return;
               }

               if (this.mc.bridge$getPlayer().bridge$isUsingItem()) {
                  BridgeExtension$Type var4 = var1.method7().bridge$getItemUseAction();
                  if (var3) {
                     if (var4 == BridgeExtension$Type.BOW) {
                        var1.method1(-0.03F, 0.04F, 0.01F - (ThreadModuleDump63.MC_VERSION == 5 ? 0.025F : 0.0F));
                        var1.method2(-1.3F);
                        var1.method3(-7.17F);
                     } else if (var4 == BridgeExtension$Type.EAT || var4 == BridgeExtension$Type.DRINK) {
                        var1.method1(ThreadModuleDump63.MC_VERSION == 5 ? 0.07F : 0.0F, ThreadModuleDump63.MC_VERSION == 5 ? 0.0F : -0.1F, 0.09F);
                     }
                  }

                  if (var4 == BridgeExtension$Type.BLOCK && this.isSwordBlockHitting(false)) {
                     var1.method1(0.0F, 0.0F, 0.07F);
                  }
               } else if (var1.method7().bridge$getItemUseAction() != BridgeExtension$Type.BLOCK && this.isSwordBlockHitting(false)) {
                  if (ThreadModuleDump63.MC_VERSION == 5) {
                     var1.method1(-0.39F, 0.05F, 0.0F);
                     var1.method5(180.0F, 100.0F, -80.0F);
                     var1.scale(0.9F, 0.9F, 0.9F);
                  } else {
                     var1.method1(-0.15F, 0.15F, 0.12F);
                     var1.method5(-110.0F, 15.0F, 75.0F);
                     var1.scale(0.95F, 0.95F, 0.95F);
                  }
               }
            }

            if (var3) {
               Modern.Data var6 = Modern.method1(var1.method7());
               if (var6 != null) {
                  var6.method1(var1);
               }

               if (var2.thirdPerson() && this.blockHitAnimation.get()) {
                  boolean var5;
                  if (var1.method6() == ThreadModuleDump63.method7()) {
                     var5 = this.isSwordBlockHitting(true);
                  } else {
                     var5 = var1.method6().bridge$isUsingItem()
                        && var1.method6()
                           .bridge$getItemInUse()
                           .map(var0 -> var0.bridge$getItem().bridge$isItemSword() && var0.bridge$getItemUseAction() == BridgeExtension$Type.BLOCK)
                           .orElse(false);
                  }

                  if (var5) {
                     var1.method1(0.28F, ThreadModuleDump63.MC_VERSION == 5 ? -0.15F : 0.14F, ThreadModuleDump63.MC_VERSION == 5 ? 0.02F : -0.12F);
                     var1.method5(-25.31F, 63.28F, 72.77F);
                  }
               }
            }
         }
      }
   }

   public boolean isFirstPersonItemEnabled(ItemStackBridge var1) {
      if (!this.isEnabled()) {
         return false;
      } else {
         Bridge6_4 var2 = var1.bridge$getItem();
         if (!this.firstPersonCarpet.get() && var2.bridge$isItemCarpet()) {
            return false;
         } else if (!this.firstPersonFishingRod.get() && var2.bridge$isItemRod()) {
            return false;
         } else if (!this.firstPersonSword.get() && var2.bridge$isItemSword()) {
            return false;
         } else if (!this.firstPersonPotions.get() && var1.bridge$isItemSplashPotion()) {
            return false;
         } else {
            BridgeExtension$Type var3 = var1.bridge$getItemUseAction();
            if (var3 == BridgeExtension$Type.DRINK && !this.firstPersonPotions.get()) {
               return false;
            } else {
               return var3 == BridgeExtension$Type.EAT && !this.firstPersonFood.get() ? false : var3 != BridgeExtension$Type.BOW || this.firstPersonBow.get();
            }
         }
      }
   }

   public boolean shouldApplyFirstPersonTransform(ItemStackBridge var1) {
      return this.isEnabled() && this.isFirstPersonItemEnabled(var1);
   }

   public boolean shouldForceLegacySwing(ItemStackBridge var1) {
      return this.isEnabled() && this.isFirstPersonItemEnabled(var1);
   }

   public boolean shouldRenderSplashPotion(ItemStackBridge var1) {
      return var1.bridge$isItemSplashPotion() && this.firstPersonPotions.get() && this.isEnabled();
   }

   public boolean shouldPlayBlockHitAnimation(ItemStackBridge var1, boolean var2) {
      if (var1 == null
         || this.mc.bridge$getPlayer() == null
         || this.mc.bridge$getPlayer().bridge$getHeldItem() != var1
         || this.mc.bridge$getPlayer().bridge$isMainHandSwapped()
         || !this.blockHitAnimation.get()
         || !this.isEnabled()
         || !this.mc.bridge$getGameSettings().bridge$keyBindUseItem().bridge$isKeyDown()) {
         return false;
      }

      if (var2 && !this.thirdPersonHeldItems.get()) {
         return false;
      }

      ItemStackBridge var3 = this.mc.bridge$getPlayer().bridge$getEquipmentInSlot(EquipmentSlotBridge.OFFHAND);
      return var3 != null && var3.bridge$getLunarItemType() == BridgeType2_4.SHIELD
         ? false
         : this.isFirstPersonItemEnabled(var1)
            && this.mc.bridge$getPlayer().bridge$getItemInUse().filter(var0 -> !var0.bridge$isEmpty()).map(var1x -> var1x == var1).orElse(true);
   }

   public boolean isSwordBlockHitting(boolean var1) {
      ItemStackBridge var2 = this.mc.bridge$getPlayer().bridge$getHeldItem();
      return var2 != null && var2.bridge$getItem().bridge$isItemSword() && this.shouldPlayBlockHitAnimation(var2, var1);
   }
}
