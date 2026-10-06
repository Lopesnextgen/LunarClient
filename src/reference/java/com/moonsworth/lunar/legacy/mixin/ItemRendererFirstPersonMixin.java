package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.framework.feature.freelook.Gui2Extension;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemColorRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.EntityOffsetRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.PlayerRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.GlintTransformEvent;
import com.moonsworth.lunar.client.mod.movement.freelook.Freelook;
import com.moonsworth.lunar.client.mod.render.itemcustomizer.CustomHeldItems;
import com.moonsworth.lunar.client.mod.render.itemcustomizer.HeldItemAnimations;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.MixinHelper2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererFirstPersonMixin {
   @Final
   @Shadow
   public Minecraft mc;
   @Annotation2(max = 1)
   @Shadow
   public ItemStack itemToRender;
   @Annotation2(max = 1)
   @Shadow
   public float equippedProgress;
   @Annotation2(max = 1)
   @Shadow
   public float prevEquippedProgressMainHand;
   @Shadow
   public int equippedItemSlot;
   @Shadow
   public ItemStack itemStackMainHand$v1_12;
   @Shadow
   public ItemStack itemStackOffHand$v1_12;
   @Unique
   private int lunar$previousSlot;
   @Unique
   private Item lunar$previousItem;

   @Annotation2(1)
   @Shadow
   public abstract void renderItem(EntityLivingBase var1, ItemStack var2, TransformType var3);

   @Shadow
   public abstract void transformFirstPerson$v1_12(EnumHandSide var1, float var2);

   @Annotation2(max = 0)
   @Inject(
      method = "renderItemInFirstPerson",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", shift = Shift.BEFORE, ordinal = 2),
      cancellable = true
   )
   public void lunar$renderItemInFirstPerson$rotateYaw(float var1, CallbackInfo var2) {
      Freelook var3 = ThreadModuleDump63.method4().method40().method31();
      EntityClientPlayerMP var4 = Minecraft.getMinecraft().thePlayer$v1_7;
      if (var3.isActive() && var3.method14().get() == Gui2Extension.FIRST) {
         float var5 = var3.isActive() ? var4.rotationYaw - var3.getRotationYaw() : 0.0F;
         float var6 = var4.prevRenderArmYaw + (var4.renderArmYaw - var4.prevRenderArmYaw) * var1 + var5 * 10.0F;
         GL11.glRotatef((var4.rotationYaw - var6) * 0.1F, 0.0F, 1.0F, 0.0F);
      }
   }

   @Annotation2(1)
   @Inject(
      method = "rotateWithPlayerRotations$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V", shift = Shift.BEFORE, ordinal = 1),
      cancellable = true
   )
   public void lunar$rotateWithPlayerRotations$rotateYaw(EntityPlayerSP var1, float var2, CallbackInfo var3) {
      Freelook var4 = ThreadModuleDump63.method4().method40().method31();
      if (var4.isActive() && var4.method14().get() == Gui2Extension.FIRST) {
         float var5 = var4.isActive() ? var1.rotationYaw - var4.getRotationYaw() : 0.0F;
         float var6 = var1.prevRenderArmYaw + (var1.renderArmYaw - var1.prevRenderArmYaw) * var2 + var5 * 10.0F;
         GlStateManager.rotate((var1.rotationYaw - var6) * 0.1F, 0.0F, 1.0F, 0.0F);
         var3.cancel();
      }
   }

   @Annotation2(min = 5)
   @Inject(
      method = "rotateArm$v1_12(F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V", shift = Shift.BEFORE, ordinal = 1),
      cancellable = true
   )
   public void lunar$rotateWithPlayerRotations$rotateYaw(float var1, CallbackInfo var2) {
      Freelook var3 = ThreadModuleDump63.method4().method40().method31();
      EntityPlayerSP var4 = this.mc.thePlayer;
      if (var3.isActive() && var3.method14().get() == Gui2Extension.FIRST) {
         float var5 = var3.isActive() ? var4.rotationYaw - var3.getRotationYaw() : 0.0F;
         float var6 = var4.prevRenderArmYaw + (var4.renderArmYaw - var4.prevRenderArmYaw) * var1 + var5 * 10.0F;
         GlStateManager.rotate((var4.rotationYaw - var6) * 0.1F, 0.0F, 1.0F, 0.0F);
         var2.cancel();
      }
   }

   @Annotation2(max = 0)
   @Redirect(method = "renderFireInFirstPerson$v1_7", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V"))
   public void lunar$renderFire_v1_7(float var1, float var2, float var3) {
      GL11.glTranslatef(var1, var2 * (2.0F - ThreadModuleDump63.method4().method40().method84().method23()), var3);
   }

   @Annotation2(min = 1)
   @Redirect(
      method = {"renderFireInFirstPerson$v1_7", "renderFireInFirstPerson$v1_12"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V")
   )
   public void lunar$renderFire_v1_8(float var1, float var2, float var3) {
      GlStateManager.translate(var1, var2 * (2.0F - ThreadModuleDump63.method4().method40().method84().method23()), var3);
   }

   @Annotation2(1)
   @Inject(
      method = "renderItemInFirstPerson(F)V",
      cancellable = true,
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V", shift = Shift.AFTER)
   )
   private void lunar$onRenderItemInFirstPerson(float var1, CallbackInfo var2) {
      if (this.itemToRender != null) {
         ItemColorRenderEvent var3 = ClientEventBus.method29()
            .method12(
               ItemColorRenderEvent.class,
               () -> new ItemColorRenderEvent(
                  var1,
                  this.prevEquippedProgressMainHand + (this.equippedProgress - this.prevEquippedProgressMainHand) * var1,
                  (ItemStackBridge)this.itemToRender,
                  AbstractRenderContext.method32(),
                  () -> this.renderItem(this.mc.thePlayer, this.itemToRender, TransformType.NONE),
                  () -> this.renderItem(this.mc.thePlayer, this.itemToRender, TransformType.FIRST_PERSON)
               )
            );
         if (var3 != null && var3.isCancelled()) {
            var2.cancel();
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
         }
      }
   }

   @Annotation2(0)
   @Inject(
      method = "renderItemInFirstPerson(F)V",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", shift = Shift.AFTER, ordinal = 0)
   )
   private void lunar$onRenderFirstPerson$v1_7(CallbackInfo var1) {
      if (this.itemToRender != null) {
         ClientEventBus.method29()
            .method12(
               GlintTransformEvent.class,
               () -> new GlintTransformEvent(
                  ThreadModuleDump63.method7(),
                  GlintTransformEvent.Type.BEFORE_TRANSFORMS,
                  (ItemStackBridge)this.itemToRender,
                  ItemTransformsBridge.Type.FIRST_PERSON_RIGHT_HAND,
                  AbstractRenderContext.method32()
               )
            );
      }
   }

   @Annotation2(0)
   @WrapOperation(
      method = "renderItemInFirstPerson(F)V",
      at = {
            @At(
               value = "INVOKE",
               target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;I)V"
            ),
            @At(
               value = "INVOKE",
               target = "Lnet/optifine/v1_7/ItemRendererOF;renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;I)V"
            )
      }
   )
   private void lunar$onRenderFirstPersonPost$v1_7(ItemRenderer var1, EntityLivingBase var2, ItemStack var3, int var4, Operation<Void> var5) {
      if (var4 == 0 && this.itemToRender != null) {
         ClientEventBus.method29()
            .method12(
               GlintTransformEvent.class,
               () -> new GlintTransformEvent(
                  ThreadModuleDump63.method7(),
                  GlintTransformEvent.Type.AFTER_TRANSFORMS,
                  (ItemStackBridge)this.itemToRender,
                  ItemTransformsBridge.Type.FIRST_PERSON_RIGHT_HAND,
                  AbstractRenderContext.method32()
               )
            );
      }

      var5.call(new Object[]{var1, var2, var3, var4});
   }

   @Annotation2(1)
   @Inject(method = "renderItemInFirstPerson(F)V", at = @At("HEAD"))
   private void lunar$onRenderFirstPerson$v1_8(CallbackInfo var1) {
      if (this.itemToRender != null) {
         ClientEventBus.method29()
            .method12(
               GlintTransformEvent.class,
               () -> new GlintTransformEvent(
                  ThreadModuleDump63.method7(),
                  GlintTransformEvent.Type.BEFORE_TRANSFORMS,
                  (ItemStackBridge)this.itemToRender,
                  ItemTransformsBridge.Type.FIRST_PERSON_RIGHT_HAND,
                  AbstractRenderContext.method32()
               )
            );
      }
   }

   @Annotation2(5)
   @Inject(
      method = "renderItemInFirstPerson$v1_12",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/ItemRenderer;transformSideFirstPerson$v1_12(Lnet/minecraft/util/EnumHandSide;F)V",
         shift = Shift.AFTER
      ),
      slice = @Slice(
         from = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItemUseAction()Lnet/minecraft/item/EnumAction;"),
         to = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxItemUseDuration()I")
      )
   )
   private void lunar$blockAnimationHook(
      AbstractClientPlayer var1, float var2, float var3, EnumHand var4, float var5, ItemStack var6, float var7, CallbackInfo var8
   ) {
      if (var4 == EnumHand.MAIN_HAND && ThreadModuleDump63.method4().method40().method98().method17().method8((ItemStackBridge)var6, false)) {
         this.transformFirstPerson$v1_12(this.mc.gameSettings.mainHand$v1_12, var5);
      }
   }

   @Annotation2(5)
   @WrapOperation(
      method = "renderItemInFirstPerson(F)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItemInFirstPerson$v1_12(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V"
      )
   )
   private void lunar$onRenderFirstPerson$v1_12(
      ItemRenderer var1, AbstractClientPlayer var2, float var3, float var4, EnumHand var5, float var6, ItemStack var7, float var8, Operation<Void> var9
   ) {
      boolean var10 = var7 != null && !var7.isEmpty();
      if (var10) {
         GlStateManager.pushMatrix();
         ClientEventBus.method29()
            .method12(
               GlintTransformEvent.class,
               () -> {
                  EnumHandSide var4x = this.mc.thePlayer.getPrimaryHand$v1_12();
                  if (var5 != EnumHand.MAIN_HAND) {
                     var4x = var4x.opposite();
                  }

                  boolean var3x = var4x == EnumHandSide.LEFT;
                  return new GlintTransformEvent(
                     ThreadModuleDump63.method7(),
                     GlintTransformEvent.Type.BEFORE_TRANSFORMS,
                     (ItemStackBridge)var7,
                     var3x ? ItemTransformsBridge.Type.FIRST_PERSON_LEFT_HAND : ItemTransformsBridge.Type.FIRST_PERSON_RIGHT_HAND,
                     AbstractRenderContext.method32()
                  );
               }
            );
      }

      try {
         var9.call(new Object[]{var1, var2, var3, var4, var5, var6, var7, var8});
      } finally {
         if (var10) {
            GlStateManager.popMatrix();
         }
      }
   }

   @Annotation2(5)
   @WrapOperation(
      method = "updateEquippedItem",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;getCooledAttackStrength$v1_12(F)F")
   )
   private float lunar$legacySwingHook(EntityPlayerSP var1, float var2, Operation<Float> var3) {
      ItemStackBridge var4 = (ItemStackBridge)this.mc.thePlayer.getHeldItem(EnumHand.MAIN_HAND);
      return ThreadModuleDump63.method4().method40().method98().method17().method6(var4) ? 1.0F : (Float)var3.call(new Object[]{var1, var2});
   }

   @Annotation2(5)
   @Inject(method = "transformEatFirstPerson$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$doOldEatAnimation(float var1, EnumHandSide var2, ItemStack var3, CallbackInfo var4) {
      ItemStackBridge var5 = (ItemStackBridge)var3;
      if (var2 == EnumHandSide.RIGHT && ThreadModuleDump63.method4().method40().method98().method17().method4(var5)) {
         var4.cancel();
         float var6 = this.mc.thePlayer.getItemInUseCount() - var1 + 1.0F;
         float var7 = 1.0F - var6 / var3.getMaxItemUseDuration();
         float var8 = 1.0F - var7;
         var8 = var8 * var8 * var8;
         var8 = var8 * var8 * var8;
         var8 = var8 * var8 * var8;
         float var9 = 1.0F - var8;
         GlStateManager.translate(0.0F, MathHelper.abs(MathHelper.cos(var6 / 4.0F * (float) Math.PI) * 0.1F) * (var7 > 0.2 ? 1 : 0), 0.0F);
         if (!ThreadModuleDump63.method4().method40().method83().method14().method16()) {
            GlStateManager.translate(var9 * 0.6F, -var9 * 0.5F, 0.0F);
            GlStateManager.rotate(var9 * 90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(var9 * 10.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(var9 * 30.0F, 0.0F, 0.0F, 1.0F);
         }
      }
   }

   @Annotation2(5)
   @WrapOperation(
      method = "renderItemInFirstPerson$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V", ordinal = 2),
      slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxItemUseDuration()I"))
   )
   private void lunar$cancelAttackTransform(float var1, float var2, float var3, Operation<Void> var4) {
      if (!ThreadModuleDump63.method4().method40().method98().method17().method9(false)) {
         var4.call(new Object[]{var1, var2, var3});
      }
   }

   @Annotation2(0)
   @WrapOperation(
      method = "renderItemInFirstPerson(F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;sin(F)F"),
      slice = @Slice(
         from = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxItemUseDuration()I", ordinal = 0),
         to = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;getSwingProgress(F)F", ordinal = 3)
      ),
      require = 3,
      expect = 3
   )
   private float lunar$scaledSwingHook$v1_7(float var1, Operation<Float> var2) {
      return (Float)var2.call(new Object[]{var1}) * this.lunar$getSwingScale(true);
   }

   @Annotation2(1)
   @WrapOperation(method = "doItemUsedTransformations$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;sin(F)F"))
   private float lunar$scaledSwingHook$v1_8(float var1, Operation<Float> var2) {
      return (Float)var2.call(new Object[]{var1}) * this.lunar$getSwingScale(true);
   }

   @Annotation2(5)
   @WrapOperation(
      method = "renderItemInFirstPerson$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;sin(F)F"),
      slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;scale(FFF)V"))
   )
   private float lunar$scaledSwingHook$v1_12(float var1, Operation<Float> var2, @Local(argsOnly = true) EnumHand var3) {
      return (Float)var2.call(new Object[]{var1}) * this.lunar$getSwingScale(var3 == EnumHand.MAIN_HAND);
   }

   @Unique
   private float lunar$getSwingScale(boolean var1) {
      HeldItemAnimations var2 = ThreadModuleDump63.method4().method40().method83().method14();
      if (var2.method13()) {
         CustomHeldItems var3 = ThreadModuleDump63.method4().method40().method83().method13();
         if (var3.isEnabled()) {
            boolean var5 = true;
            ItemStack var4;
            if (ThreadModuleDump63.MC_VERSION == 5) {
               EnumHandSide var6 = this.mc.gameSettings.mainHand$v1_12;
               var5 = (var1 ? var6 : var6.opposite()) == EnumHandSide.RIGHT;
               var4 = this.mc.thePlayer.getHeldItem(var1 ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
            } else {
               var4 = ThreadModuleDump63.MC_VERSION == 0 ? this.mc.thePlayer$v1_7.getHeldItem() : this.mc.thePlayer.getHeldItem();
            }

            if (var4 != null && (ThreadModuleDump63.MC_VERSION != 5 || !var4.isEmpty())) {
               return var3.method9((ItemStackBridge)var4, var5);
            }
         }
      }

      return 1.0F;
   }

   @Annotation2(max = 1)
   @Inject(method = {"resetEquippedProgress$v1_7", "resetEquippedProgress2$v1_7"}, at = @At("HEAD"), cancellable = true)
   private void lunar$cancelReEquip(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method83().method14().method17()) {
         var1.cancel();
      }
   }

   @Annotation2(1)
   @Inject(method = "updateEquippedItem", at = @At("HEAD"))
   private void lunar$cancelReEquipUpdate(CallbackInfo var1) {
      ItemStack var2 = this.mc.thePlayer.inventory.getCurrentItem();
      int var3 = this.mc.thePlayer.inventory.currentItem;
      if (var2 != null && var3 == this.equippedItemSlot && ThreadModuleDump63.method4().method40().method83().method14().method17()) {
         this.itemToRender = var2;
         this.equippedItemSlot = var3;
      }
   }

   @Annotation2(5)
   @Inject(
      method = "updateEquippedItem",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;getCooledAttackStrength$v1_12(F)F", shift = Shift.AFTER)
   )
   private void lunar$cancelReEquipUpdate(CallbackInfo var1, @Local(ordinal = 0) ItemStack var2, @Local(ordinal = 1) ItemStack var3) {
      boolean var4 = ThreadModuleDump63.method4().method40().method83().method14().method17();
      int var5 = this.mc.thePlayer.inventory.currentItem;
      if (var4 && var5 == this.lunar$previousSlot) {
         this.itemStackMainHand$v1_12 = var2;
      }

      this.lunar$previousSlot = var5;
      if (var4 && var3 != null && var3.getItem() == this.lunar$previousItem) {
         this.itemStackOffHand$v1_12 = var3;
      }

      this.lunar$previousItem = var3 == null ? null : var3.getItem();
   }

   @Annotation2(5)
   @Inject(method = "resetEquippedProgress$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$cancelReEquip(EnumHand var1, CallbackInfo var2) {
      ItemStackBridge var3 = (ItemStackBridge)this.mc.thePlayer.getHeldItem(var1);
      if (ThreadModuleDump63.method4().method40().method83().method14().method17()
         || ThreadModuleDump63.method4().method40().method98().method17().method5(var3)) {
         var2.cancel();
      }
   }

   @Annotation2(0)
   @WrapOperation(
      method = "renderItemInFirstPerson(F)V",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V"),
      slice = @Slice(
         from = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxItemUseDuration()I"),
         to = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;getSwingProgress(F)F", ordinal = 2)
      )
   )
   private void lunar$rotationlessDrinkHook$v1_7$1(float var1, float var2, float var3, float var4, Operation<Void> var5) {
      ItemStack var6 = this.mc.thePlayer$v1_7.getHeldItem();
      if (var6 == null
         || var6.getItemUseAction().ordinal() != 1 && var6.getItemUseAction().ordinal() != 2
         || !ThreadModuleDump63.method4().method40().method83().method14().method16()) {
         var5.call(new Object[]{var1, var2, var3, var4});
      }
   }

   @Annotation2(0)
   @WrapOperation(
      method = "renderItemInFirstPerson(F)V",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V", ordinal = 1),
      slice = @Slice(
         from = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxItemUseDuration()I"),
         to = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;getSwingProgress(F)F", ordinal = 2)
      )
   )
   private void lunar$rotationlessDrinkHook$v1_7$2(float var1, float var2, float var3, Operation<Void> var4) {
      ItemStack var5 = this.mc.thePlayer$v1_7.getHeldItem();
      if (var5 == null
         || var5.getItemUseAction().ordinal() != 1 && var5.getItemUseAction().ordinal() != 2
         || !ThreadModuleDump63.method4().method40().method83().method14().method16()) {
         var4.call(new Object[]{var1, var2, var3});
      }
   }

   @Annotation2(1)
   @Inject(method = "performDrinking$v1_8", at = @At(value = "INVOKE", target = "Ljava/lang/Math;pow(DD)D"), cancellable = true)
   private void lunar$rotationlessDrinkHook$v1_8(CallbackInfo var1) {
      ItemStack var2 = this.mc.thePlayer.getHeldItem();
      if (var2 != null
         && (var2.getItemUseAction().ordinal() == 1 || var2.getItemUseAction().ordinal() == 2)
         && ThreadModuleDump63.method4().method40().method83().method14().method16()) {
         var1.cancel();
      }
   }

   @Annotation2(5)
   @Inject(method = "transformEatFirstPerson$v1_12", at = @At(value = "INVOKE", target = "Ljava/lang/Math;pow(DD)D"), cancellable = true)
   private void lunar$rotationlessDrinkHook$v1_12(CallbackInfo var1, @Local(argsOnly = true) ItemStack var2) {
      if (var2 != null
         && (var2.getItemUseAction() == EnumAction.EAT || var2.getItemUseAction() == EnumAction.DRINK)
         && ThreadModuleDump63.method4().method40().method83().method14().method16()) {
         var1.cancel();
      }
   }

   @Annotation2(0)
   @Inject(
      method = "renderItemInFirstPerson(F)V",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V", shift = Shift.AFTER),
      slice = @Slice(
         from = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;isInvisible()Z"),
         to = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/entity/RenderPlayer_v1_7;renderFirstPersonArm(Lnet/minecraft/entity/player/EntityPlayer;)V",
            ordinal = 1,
            shift = Shift.AFTER
         )
      )
   )
   private void lunar$preArmTransform$1_7(CallbackInfo var1) {
      EntityOffsetRenderEvent var2 = ClientEventBus.method29().method12(EntityOffsetRenderEvent.class, () -> new EntityOffsetRenderEvent(false));
      if (var2 != null) {
         GL11.glTranslatef(var2.getXOffset(), var2.getYOffset(), var2.method4());
      }
   }

   @Annotation2(1)
   @Inject(method = "renderPlayerArm$v1_8", at = @At("HEAD"))
   private void lunar$preArmTransform$1_8(CallbackInfo var1) {
      EntityOffsetRenderEvent var2 = ClientEventBus.method29().method12(EntityOffsetRenderEvent.class, () -> new EntityOffsetRenderEvent(false));
      if (var2 != null) {
         GlStateManager.translate(var2.getXOffset(), var2.getYOffset(), var2.method4());
      }
   }

   @Annotation2(1)
   @Inject(
      method = "renderPlayerArm$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;disableCull()V", shift = Shift.AFTER)
   )
   private void lunar$postArmTransform$1_8(CallbackInfo var1) {
      EntityOffsetRenderEvent var2 = ClientEventBus.method29().method12(EntityOffsetRenderEvent.class, () -> new EntityOffsetRenderEvent(false));
      if (var2 != null) {
         GlStateManager.rotate(var2.method6(), 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(var2.method7(), 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(var2.method5(), 0.0F, 0.0F, 1.0F);
         float var3 = var2.getScale();
         GlStateManager.scale(var3, var3, var3);
      }
   }

   @Annotation2(5)
   @Inject(method = "renderArmFirstPerson$v1_12", at = @At("HEAD"))
   private void lunar$preArmTransform$1_12(CallbackInfo var1, @Local(argsOnly = true) EnumHandSide var2) {
      EntityOffsetRenderEvent var3 = ClientEventBus.method29().method12(EntityOffsetRenderEvent.class, () -> new EntityOffsetRenderEvent(false));
      if (var3 != null) {
         GlStateManager.translate(var3.getXOffset(), var3.getYOffset(), var3.method4());
      }
   }

   @Annotation2(5)
   @Inject(
      method = "renderArmFirstPerson$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;disableCull()V", shift = Shift.AFTER)
   )
   private void lunar$postArmTransform$1_12(CallbackInfo var1, @Local(argsOnly = true) EnumHandSide var2) {
      EntityOffsetRenderEvent var3 = ClientEventBus.method29().method12(EntityOffsetRenderEvent.class, () -> new EntityOffsetRenderEvent(var2 == EnumHandSide.LEFT));
      if (var3 != null) {
         GlStateManager.rotate(var3.method6(), 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(var3.method7(), 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(var3.method5(), 0.0F, 0.0F, 1.0F);
         float var4 = var3.getScale();
         GlStateManager.scale(var4, var4, var4);
      }
   }

   @Annotation2(1)
   @Inject(
      method = "renderPlayerArm$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/RenderPlayer_v1_8;renderRightArm(Lnet/minecraft/client/entity/AbstractClientPlayer;)V",
         ordinal = 0
      ),
      cancellable = true
   )
   private void lunar$renderRightArm$1_8(AbstractClientPlayer var1, float var2, float var3, CallbackInfo var4) {
      PlayerRenderEvent var5 = ClientEventBus.method29()
         .method12(PlayerRenderEvent.class, () -> new PlayerRenderEvent(AbstractRenderContext.method32(), (Bridge5_11)var1, false));
      if (var5 != null && var5.isCancelled()) {
         var4.cancel();
      }
   }

   @Annotation2(min = 5)
   @Inject(
      method = "renderArmFirstPerson$v1_12",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/RenderPlayer_v1_8;renderRightArm(Lnet/minecraft/client/entity/AbstractClientPlayer;)V",
         ordinal = 0
      ),
      cancellable = true
   )
   private void lunar$renderRightArm$1_12(CallbackInfo var1) {
      PlayerRenderEvent var2 = ClientEventBus.method29()
         .method12(PlayerRenderEvent.class, () -> new PlayerRenderEvent(AbstractRenderContext.method32(), (Bridge5_11)this.mc.thePlayer, false));
      if (var2 != null && var2.isCancelled()) {
         var1.cancel();
      }
   }

   @Annotation2(min = 5)
   @Inject(
      method = "renderArmFirstPerson$v1_12",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/RenderPlayer_v1_8;renderLeftArm(Lnet/minecraft/client/entity/AbstractClientPlayer;)V",
         ordinal = 0
      ),
      cancellable = true
   )
   private void lunar$renderLeftArm$1_12(CallbackInfo var1) {
      PlayerRenderEvent var2 = ClientEventBus.method29()
         .method12(PlayerRenderEvent.class, () -> new PlayerRenderEvent(AbstractRenderContext.method32(), (Bridge5_11)this.mc.thePlayer, true));
      if (var2 != null && var2.isCancelled()) {
         var1.cancel();
      }
   }

   @Annotation2(max = 0)
   @Inject(
      method = "renderItemInFirstPerson",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/RenderPlayer_v1_7;renderFirstPersonArm(Lnet/minecraft/entity/player/EntityPlayer;)V",
         ordinal = 1
      ),
      cancellable = true
   )
   private void lunar$renderArm$1_7(CallbackInfo var1) {
      EntityOffsetRenderEvent var2 = ClientEventBus.method29().method12(EntityOffsetRenderEvent.class, () -> new EntityOffsetRenderEvent(false));
      if (var2 != null) {
         GL11.glRotatef(var2.method6(), 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(var2.method7(), 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(var2.method5(), 0.0F, 0.0F, 1.0F);
         float var3 = var2.getScale();
         GL11.glScalef(var3, var3, var3);
      }

      PlayerRenderEvent var4 = ClientEventBus.method29()
         .method12(PlayerRenderEvent.class, () -> new PlayerRenderEvent(AbstractRenderContext.method32(), (Bridge5_11)this.mc.thePlayer$v1_7, false));
      if (var4 != null && var4.isCancelled()) {
         var1.cancel();
         GL11.glPopMatrix();
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "renderItemSide$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$renderGeckolibItem$v1_12(EntityLivingBase var1, ItemStack var2, TransformType var3, boolean var4, CallbackInfo var5) {
      if (var3 == TransformType.FIRST_PERSON_LEFT_HAND$v1_12 || var3 == TransformType.FIRST_PERSON_RIGHT_HAND$v1_12) {
         MixinHelper2.method1(var2, ItemTransformsBridge.Type.valueOf(var3.name()), var5);
      }
   }

   @Annotation2(1)
   @Inject(method = "renderItem$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$renderGeckolibItem$v1_8(EntityLivingBase var1, ItemStack var2, TransformType var3, CallbackInfo var4) {
      if (var3 == TransformType.FIRST_PERSON) {
         MixinHelper2.method1(var2, ItemTransformsBridge.Type.FIRST_PERSON_RIGHT_HAND, var4);
      }
   }

   @Annotation2(0)
   @Inject(method = "renderItem$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$renderGeckolibItem$v1_7(EntityLivingBase var1, ItemStack var2, int var3, CallbackInfo var4) {
      if (var1 instanceof EntityPlayerBridge var5) {
         if (PlayerModelPartMap.method39(var5, (ItemStackRenderStateBridge)var2)) {
            var4.cancel();
         }
      }
   }

   @Annotation2(max = 0)
   @Inject(
      method = "renderItemInFirstPerson",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;I)V",
         shift = Shift.BEFORE
      ),
      cancellable = true
   )
   private void lunar$renderGeckolibItem$v1_7(float var1, CallbackInfo var2) {
      if (this.itemToRender != null) {
         MixinHelper2.method1(this.itemToRender, ItemTransformsBridge.Type.FIRST_PERSON_RIGHT_HAND, var2);
         if (var2.isCancelled()) {
            GL11.glPopMatrix();
         }
      }
   }

   @Inject(method = {"renderFireInFirstPerson$v1_7", "renderFireInFirstPerson$v1_12", "renderWaterOverlayTexture"}, at = @At("HEAD"), cancellable = true)
   private void lunar$rewindDontRenderOverlaysInFreecam(CallbackInfo var1) {
      if (ThreadModuleDump63.method4()
         .method40()
         .method85()
         .method17(var0 -> !var0.method44() || var0.method45().method19() || !var0.method45().method15().isFixedToPlayer())) {
         var1.cancel();
      }
   }
}
