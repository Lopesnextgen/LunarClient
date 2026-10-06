package com.moonsworth.lunar.client.mod.render.onesevenvisuals;

import com.google.common.collect.ImmutableSet;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6Extension4;
import com.moonsworth.lunar.bridge.Bridge6Extension5;
import com.moonsworth.lunar.bridge.Bridge6Extension7;
import com.moonsworth.lunar.bridge.Bridge6Extension8;
import com.moonsworth.lunar.bridge.Bridge6Extension9;
import com.moonsworth.lunar.bridge.Bridge6Extension_2;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.BridgeExtension$Type;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.ArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_6;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectHitType;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartVisibility;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.onesevenvisuals.legacy.Onesevenvisuals;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.RenderEntityItemLegacyEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemColorRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.GlintTransformEvent;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import lombok.Generated;

@Annotation2(1)
public class OneSevenItemsLegacy extends AbstractFeature {
   private final ToggleOption blockHitAnimation = OneSevenVisuals.toggleOption("blockHitAnimation");
   private final ToggleOption itemTransforms = OneSevenVisuals.toggleOption("itemTransforms");
   private final ToggleOption thirdPersonHeldItems = OneSevenVisuals.toggleOption("thirdPersonHeldItems");
   private final ToggleOption firstPersonCarpet = OneSevenVisuals.toggleOption("firstPersonCarpet");
   private final ToggleOption firstPersonFishingRod = OneSevenVisuals.toggleOption("firstPersonFishingRod");
   private final ToggleOption firstPersonSword = OneSevenVisuals.toggleOption("firstPersonSword");
   private final ToggleOption firstPersonPotions = OneSevenVisuals.toggleOption("firstPersonPotions");
   private final ToggleOption firstPersonFood = OneSevenVisuals.toggleOption("firstPersonFood");
   private final ToggleOption firstPersonBow = OneSevenVisuals.toggleOption("firstPersonBow");
   private boolean swinging;
   private int swingTicks = 0;
   private float swingProgress = 0.0F;
   private float previousSwingProgress = 0.0F;
   private boolean swingResetPending = true;
   private boolean blockHitting = false;
   private boolean renderingFirstPersonItem = false;
   private static final Set<Bridge6_4> EXCLUDED_HELD_ITEMS = ImmutableSet.of(
      Bridge.method28().method1(), Bridge.method28().method2(), Bridge.method28().method3(), Bridge.method28().method4(), Bridge.method28().method5()
   );
   private static final Set<Class<? extends Bridge6_4>> EXCLUDED_ITEM_CLASSES = ImmutableSet.of(
      Bridge6Extension9.class, Bridge6Extension5.class, Bridge6Extension4.class, Bridge6Extension_2.class, Bridge6Extension7.class
   );
   private static final Set<Bridge6_4> THIRD_PERSON_HELD_ITEMS = ImmutableSet.of(Bridge.method28().method10(), Bridge.method28().method7());

   public OneSevenItemsLegacy(OneSevenVisuals var1) {
      super(true);
      this.method6(Framework.field16, Framework4.method3(var1));
      this.handle(RenderEntityItemLegacyEvent.class, this::applyThirdPersonItemTransform);
      this.handle(ItemColorRenderEvent.class, this::onRenderItemFirstPerson);
      this.handle(EventClientTick.class, this::method2);
   }

   @Override
   public String getId() {
      return "ONE_SEVEN_ITEMS_LEGACY";
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

   private void method2(EventClientTick var1) {
      if (ThreadModuleDump63.method8() != null && this.mc.bridge$getPlayer() != null) {
         if (this.blockHitAnimation.get()) {
            int var2 = this.mc.bridge$getPlayer().bridge$getArmSwingAnimationEnd();
            float var3 = this.mc.bridge$getPlayer().bridge$getSwingProgress(1.0F);
            int var4 = Math.round(var3 * var2);
            boolean var5 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindAttack().bridge$isKeyDown();
            boolean var6 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindUseItem().bridge$isKeyDown();
            boolean var7 = ThreadModuleDump63.method3().bridge$getPlayer().bridge$isUsingItem()
               && var5
               && ThreadModuleDump63.method3().bridge$getObjectMouseOver() != null
               && ThreadModuleDump63.method3().bridge$getObjectMouseOver().bridge$isTypeOfHit(MovingObjectHitType.BLOCK);
            if (ThreadModuleDump63.MC_VERSION == 1 && var5 && var6 && ThreadModuleDump63.method3().bridge$getPlayerController().bridge$isHittingBlock()) {
               this.blockHitting = true;
            }

            if (var4 > 0) {
               if (var4 < var2 / 2 || !var7) {
                  this.swingTicks = var4;
                  this.swinging = true;
                  this.previousSwingProgress = this.mc.bridge$getPlayer().bridge$getSwingProgress(0.0F);
                  this.swingProgress = var3;
                  this.swingResetPending = true;
                  return;
               }

               if (this.swingResetPending) {
                  this.swingTicks = -1;
                  this.swinging = true;
               }

               this.swingResetPending = false;
            } else if (var7) {
               this.beginBlockHitSwing(this.mc.bridge$getPlayer());
               this.swingResetPending = false;
            }

            if (this.swinging) {
               if (++this.swingTicks >= var2) {
                  this.swingTicks = 0;
                  this.swinging = false;
               }
            } else {
               this.swingTicks = 0;
            }

            this.previousSwingProgress = this.swingProgress;
            this.swingProgress = (float)this.swingTicks / var2;
         }
      }
   }

   private void beginBlockHitSwing(Bridge5Extension_5 var1) {
      if (this.swingResetPending || !this.swinging || this.swingTicks >= var1.bridge$getArmSwingAnimationEnd() / 2 || this.swingTicks < 0) {
         this.swingTicks = -1;
         this.swinging = true;
      }
   }

   public float getSwingProgress(float var1, float var2) {
      if (!this.blockHitAnimation.get()) {
         return var1;
      }

      float var3 = this.swingProgress - this.previousSwingProgress;
      if (var3 < 0.0F) {
         var3++;
      }

      return this.previousSwingProgress + var3 * var2;
   }

   private boolean isSpecialHeldItem(Bridge6_4 var1) {
      return var1 == Bridge.method28().method2() || var1 == Bridge.method28().method1();
   }

   private boolean method6(BridgeExtension$Type var1) {
      switch (var1) {
         case NONE:
            return true;
         case EAT:
            return this.firstPersonFood.get();
         case DRINK:
            return this.firstPersonPotions.get();
         case BLOCK:
            return this.firstPersonSword.get();
         case BOW:
            return this.firstPersonBow.get();
         default:
            return false;
      }
   }

   private void onRenderItemFirstPerson(ItemColorRenderEvent var1) {
      if (this.isEnabled()) {
         ItemStackBridge var2 = var1.getItem();
         AbstractRenderContext var3 = var1.method3();
         Bridge5Extension_5 var4 = this.mc.bridge$getPlayer();
         if (var2.bridge$getItem() != Bridge.method28().method30()) {
            boolean var5 = false;
            if (this.mc.bridge$getRenderItem().bridge$shouldRenderItemIn3D(var2)) {
               if (!this.firstPersonCarpet.get() || Bridge.method8().method26(var2.bridge$getItem()) != Bridge.method34().method1()) {
                  return;
               }

               var5 = true;
            }

            if (!this.isSpecialHeldItem(var2.bridge$getItem()) || this.firstPersonFishingRod.get()) {
               BridgeExtension$Type var6 = var2.bridge$getItemUseAction();
               if (this.method6(var6)) {
                  var1.setCancelled(true);
                  float var7 = var1.method1();
                  float var8 = var1.method2();
                  float var9 = this.getSwingProgress(var4.bridge$getSwingProgress(var7), var7);
                  int var10 = var4.bridge$getItemInUseCount();
                  if (var6 == BridgeExtension$Type.BLOCK && var9 > 0.01F && var10 > 0) {
                     var8 = 1.0F;
                  }

                  var3.push();
                  boolean var11 = var10 > 0;
                  if (var11 && var6 != BridgeExtension$Type.NONE) {
                     switch (var2.bridge$getItemUseAction()) {
                        case EAT:
                        case DRINK:
                           Onesevenvisuals.method6(var3, var10, var2.bridge$getMaxItemUseDuration(), var7);
                           Onesevenvisuals.method3(var3, var8, var9);
                           break;
                        case BLOCK:
                           if (!(var9 < 0.01F) && !this.blockHitAnimation.get()) {
                              Onesevenvisuals.method4(var3, var9);
                              Onesevenvisuals.method3(var3, var8, var9);
                           } else {
                              Onesevenvisuals.method3(var3, var8, var9);
                              Onesevenvisuals.method5(var3);
                           }
                           break;
                        case BOW:
                           Onesevenvisuals.method3(var3, var8, var9);
                           Onesevenvisuals.method7(var3, var10, var2.bridge$getMaxItemUseDuration(), var7);
                     }
                  } else {
                     Onesevenvisuals.method4(var3, var9);
                     Onesevenvisuals.method3(var3, var8, var9);
                  }

                  if (!this.itemTransforms.get()) {
                     var1.method5().run();
                     var3.pop();
                  } else {
                     ClientEventBus.method29().method12(GlintTransformEvent.class, () -> new GlintTransformEvent(var4, var2, ItemTransformsBridge.Type.FIRST_PERSON_RIGHT_HAND, var3));
                     if (ThreadModuleDump63.MC_VERSION <= 5 && var2.bridge$getItem().bridge$shouldRotateAroundWhenRendering()) {
                        var3.method4(180.0F, 0.0F, 1.0F, 0.0F);
                     }

                     Optional var12 = PlayerModelPartMap.method3(var2);
                     if (var12.isPresent()) {
                        MixinHelper_6 var13 = Bridge.method9().bridge$getEntityRenderDispatcher().bridge$getSkinMap().get(var4.bridge$getSkinType());
                        if (var13 != null) {
                           BridgeExtension2_7 var14 = var13.bridge$getMainModel();
                           boolean var15 = ThreadModuleDump63.method4().method45().method9(var4);
                           EmoteModel var16 = (EmoteModel)((CosmeticMetadata)var12.get()).method4();
                           var3.scale(2.5F, 2.5F, 2.5F);
                           var3.method2(-45.0F, 0.0F, 1.0F, 0.0F);
                           var3.translate(-0.56F, 0.52F, 0.71999997F);
                           var3.translate(0.58F, -0.53F, -0.77F);
                           Consumer var17 = PlayerModelPartMap.method36(var16.method6().get(), false, var3, ItemTransformsBridge.Type.FIRST_PERSON_RIGHT_HAND);
                           RenderContext var18 = RenderContext.method12(var4, var14);
                           PlayerModelPartVisibility.method2(
                              var3,
                              var18,
                              (CosmeticMetadata)var12.get(),
                              var4,
                              var15,
                              var14,
                              false,
                              false,
                              var1.getItem(),
                              ThreadModuleDump91.Type.Data.method2(var4, (CosmeticMetadata)var12.get(), ItemTransformsBridge.Type.FIRST_PERSON_RIGHT_HAND),
                              var17
                           );
                        }

                        var3.pop();
                     } else {
                        Onesevenvisuals.method1(var3);

                        try {
                           this.renderingFirstPersonItem = true;
                           if (var5) {
                              Onesevenvisuals.method2(var3);
                              var3.translate(0.0, -0.6F, 0.0);
                              var1.method5().run();
                           } else {
                              var1.method4().run();
                           }
                        } finally {
                           this.renderingFirstPersonItem = false;
                        }

                        var3.pop();
                     }
                  }
               }
            }
         }
      }
   }

   private void applyThirdPersonItemTransform(RenderEntityItemLegacyEvent var1) {
      if (!(var1.method1() instanceof ArmorStandBridge)) {
         boolean var2 = var1.method1() instanceof Bridge6_10 && ((Bridge6_10)var1.method1()).bridge$isBlocking();
         Bridge6_4 var3 = var1.getItem().bridge$getItem();
         if (var3 != null && !field24.contains(var3)) {
            AbstractRenderContext var4 = var1.method2();
            if (var2 && this.blockHitAnimation.get()) {
               var4.translate(-0.05F, 0.1F, -0.12F);
               var4.method4(-45.0F, 0.0F, 1.0F, 0.0F);
               var4.method4(-30.0F, 1.0F, 0.0F, 0.0F);
               var4.method4(-60.0F, 0.0F, 0.0F, 1.0F);
            }

            if (this.thirdPersonHeldItems.get()) {
               boolean var5 = field26.contains(var3);
               if (!var5) {
                  for (Class var7 : field25) {
                     if (var7.isAssignableFrom(var3.getClass())) {
                        var5 = true;
                        break;
                     }
                  }
               }

               if (var5) {
                  if (!var2) {
                     var4.method4(20.0F, -1.0F, 0.0F, 0.0F);
                     var4.method4(5.0F, 0.0F, 0.0F, 1.0F);
                     var4.translate(-0.01F, 0.03F, 0.1F);
                  } else {
                     var4.method4(15.0F, 0.0F, 1.0F, 0.0F);
                     var4.translate(0.1F, 0.08F, 0.0);
                  }
               } else {
                  boolean var8 = var3 instanceof Bridge6Extension8;
                  if (!var8) {
                     if (var3 == Bridge.method28().method8()) {
                        var4.translate(0.03F, -0.05F, -0.1F);
                        var4.method4(10.0F, -1.5F, -1.0F, 1.0F);
                     } else {
                        var4.method4(180.0F, 0.0F, 0.0F, 1.0F);
                        var4.translate(0.0, -(var1.method1().bridge$isSneaking() ? 0.695F : 0.295F), -0.03F);
                        var4.method4(13.5F, 1.0F, 0.15F, 0.0F);
                        var4.method4(15.0F, 0.0F, 0.9F, 0.8F);
                        var4.translate(0.01F, 0.0, 0.0);
                     }
                  }
               }
            }
         }
      }
   }

   public boolean isBlockHitAnimationEnabled() {
      return this.isEnabled() && this.blockHitAnimation.get();
   }

   @Generated
   public boolean isBlockHitting() {
      return this.blockHitting;
   }

   @Generated
   public void setBlockHitting(boolean var1) {
      this.blockHitting = var1;
   }

   @Generated
   public boolean isRenderingFirstPersonItem() {
      return this.renderingFirstPersonItem;
   }
}
