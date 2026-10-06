package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemEntityBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Bridge_62;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.RenderEntityItemEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.DroppedItemRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemStackSizeRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemRotationRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.GroundItemTransformEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemGlintRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.GlintTransformEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemClumpEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.nio.ByteOrder;
import java.util.Random;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderItem.class)
public class RenderItemEventMixin {
   @Annotation2(max = 0)
   @Shadow
   public static boolean renderInFrame$v1_7;
   @Shadow
   public float zLevel;
   @Unique
   private boolean lunar$isRenderPotion = false;
   @Unique
   private float lunar$clumpSpread = 0.2F;
   @Unique
   private boolean lunar$renderItemGui;
   @Unique
   private EntityLivingBase lunar$currentEntityRenderingItem = null;
   @Unique
   private ItemStack item;

   @Annotation2(min = 1)
   @Redirect(method = "renderQuad$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/BufferBuilder;putColor4$v1_8(I)V"))
   public void lunar$addAlphaToRenderQuad(WorldRenderer var1, int var2) {
      for (int var3 = 0; var3 < 4; var3++) {
         int var4 = var1.getColorIndex(var3 + 1);
         int var5 = var2 >> 24 & 0xFF;
         int var6 = var2 >> 16 & 0xFF;
         int var7 = var2 >> 8 & 0xFF;
         int var8 = var2 & 0xFF;
         if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            var1.rawIntBuffer.put(var4, var5 << 24 | var8 << 16 | var7 << 8 | var6);
         } else {
            var1.rawIntBuffer.put(var4, var6 << 24 | var7 << 16 | var8 << 8 | var5);
         }
      }
   }

   @Annotation2(max = 0)
   @Redirect(method = "renderQuad$v1_7", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorOpaque_I$v1_7(I)V"))
   public void lunar$addAlphaToRenderQuad(Tessellator var1, int var2) {
      int var3 = var2 >> 24 & 0xFF;
      int var4 = var2 >> 16 & 0xFF;
      int var5 = var2 >> 8 & 0xFF;
      int var6 = var2 & 0xFF;
      var1.setColorRGBA$v1_7(var4, var5, var6, var3);
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "renderDroppedItem$v1_7(Lnet/minecraft/entity/item/EntityItem;Lnet/minecraft/util/IIcon;IFFFF)V",
      at = @At(value = "FIELD", target = "net/minecraft/client/settings/GameSettings.fancyGraphics : Z")
   )
   public boolean lunar$renderDroppedItem$fancyGraphics(GameSettings var1) {
      if (var1.fancyGraphics) {
         return true;
      }

      Client var2 = Client.method109();
      return var2 == null ? false : var2.method40().method30().isEnabled();
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "renderDroppedItem$v1_7(Lnet/minecraft/entity/item/EntityItem;Lnet/minecraft/util/IIcon;IFFFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;hasEffect()Z")
   )
   public boolean lunar$renderDroppedItem$hasEffect(ItemStack var1) {
      if (!var1.hasEffect()) {
         return false;
      }

      ItemGlintRenderEvent.Type var2 = ItemGlintRenderEvent.Type.ITEM;
      ItemGlintRenderEvent var3 = ClientEventBus.method29()
         .method12(ItemGlintRenderEvent.class, () -> new ItemGlintRenderEvent(var2, null, null, null, (ItemStackBridge)var1, AbstractRenderContext.method32()));
      return var3 == null || !var3.isCancelled();
   }

   @Annotation2(max = 0)
   @Inject(method = "renderItemAndEffectIntoGUI$v1_7", at = @At("HEAD"))
   private void lunar$cacheRenderedItem(FontRenderer var1, TextureManager var2, ItemStack var3, int var4, int var5, CallbackInfo var6) {
      this.item = var3;
   }

   @Annotation2(max = 0)
   @Inject(method = "renderItemAndEffectIntoGUI$v1_7", at = @At("TAIL"))
   private void lunar$uncacheRenderedItem(FontRenderer var1, TextureManager var2, ItemStack var3, int var4, int var5, CallbackInfo var6) {
      this.item = null;
   }

   @Annotation2(max = 0)
   @Inject(method = "renderGlint$v1_7", at = @At("HEAD"), cancellable = true)
   public void lunar$renderGlint(int var1, int var2, int var3, int var4, int var5, CallbackInfo var6) {
      ItemGlintRenderEvent.Type var7 = this.lunar$renderItemGui ? ItemGlintRenderEvent.Type.GUI : ItemGlintRenderEvent.Type.ITEM;
      ItemGlintRenderEvent var8 = ClientEventBus.method29().method12(ItemGlintRenderEvent.class, () -> new ItemGlintRenderEvent(var7, var5xx -> {
         float var6x = (var5xx >> 24 & 0xFF) / 255.0F;
         float var7x = (var5xx >> 16 & 0xFF) / 255.0F;
         float var8x = (var5xx >> 8 & 0xFF) / 255.0F;
         float var9 = (var5xx & 0xFF) / 255.0F;
         GL11.glColor4f(var7x, var8x, var9, var6x);
         Tessellator var10 = Tessellator.theMinecraft;
         var10.startDrawingQuads$v1_7();
         var10.addVertexWithUV$v1_7(var2 + 0, var3 + var5, this.zLevel, 0.0, 1.0);
         var10.addVertexWithUV$v1_7(var2 + var4, var3 + var5, this.zLevel, 1.0, 1.0);
         var10.addVertexWithUV$v1_7(var2 + var4, var3 + 0, this.zLevel, 1.0, 0.0);
         var10.addVertexWithUV$v1_7(var2 + 0, var3 + 0, this.zLevel, 0.0, 0.0);
         var10.draw();
      }, null, null, (ItemStackBridge)this.item, AbstractRenderContext.method32()));
      if (var8 != null && var8.isCancelled()) {
         var6.cancel();
      }
   }

   @Annotation2(max = 0)
   @Inject(method = "doRender$v1_7(Lnet/minecraft/entity/item/EntityItem;DDDFF)V", at = @At("HEAD"), cancellable = true)
   private void lunar$onRender(EntityItem var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      RenderEntityItemEvent var11 = ClientEventBus.method29()
         .method12(
            RenderEntityItemEvent.class,
            () -> new RenderEntityItemEvent(AbstractRenderContext.method32(), (Bridge_62)this, (ItemEntityBridge)var1, var2, var4, var6, renderInFrame$v1_7, var9)
         );
      if (var11 != null && var11.isCancelled()) {
         var10.cancel();
      }
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "doRender$v1_7(Lnet/minecraft/entity/item/EntityItem;DDDFF)V",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V")
   )
   private void lunar$customTransform(int var1, EntityItem var2, double var3, double var5, double var7, float var9, float var10) {
      GL11.glEnable(var1);
      if (!renderInFrame$v1_7) {
         ClientEventBus.method29()
            .method12(
               GroundItemTransformEvent.class,
               () -> new GroundItemTransformEvent(AbstractRenderContext.method32(), ((ItemEntityBridge)var2).bridge$getItemState(), false, renderInFrame$v1_7)
            );
      }
   }

   @Annotation2(max = 0)
   @Redirect(method = "renderItemAndEffectIntoGUI$v1_7", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V"))
   private void lunar$renderItemAndEffectIntoGUI(
      float var1, float var2, float var3, float var4, FontRenderer var5, TextureManager var6, ItemStack var7, int var8, int var9
   ) {
      if (ThreadModuleDump63.method4().method40().method24().isEnabled() && var7.getItem() != null && var7.getItem() instanceof ItemPotion) {
         this.lunar$isRenderPotion = true;
         int var10 = ThreadModuleDump63.method4().method40().method24().method4((ItemStackBridge)var7);
         var4 = (var10 >> 24 & 0xFF) / 255.0F;
         var1 = (var10 >> 16 & 0xFF) / 255.0F;
         var2 = (var10 >> 8 & 0xFF) / 255.0F;
         var3 = (var10 & 0xFF) / 255.0F;
      } else {
         this.lunar$isRenderPotion = false;
      }

      GL11.glColor4f(var1, var2, var3, var4);
   }

   @Annotation2(max = 0)
   @ModifyConstant(method = "renderGlint$v1_7", constant = @Constant(intValue = 772))
   private int lunar$modifyGlint(int var1) {
      return this.lunar$isRenderPotion ? 773 : var1;
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "renderItemAndEffectIntoGUI$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderGlint$v1_7(IIIII)V")
   )
   private void lunar$renderGlint(RenderItem var1, int var2, int var3, int var4, int var5, int var6) {
      if (this.lunar$isRenderPotion && !ThreadModuleDump63.method4().method40().method24().method16().get()) {
         var3 += 2;
         var4 += 2;
         var5 -= 4;
         var6 -= 4;
      }

      var1.renderGlint(var2, var3, var4, var5, var6);
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "doRender$v1_7(Lnet/minecraft/entity/item/EntityItem;DDDFF)V",
      slice = @Slice(
         from = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;sin(F)F"),
         to = @At(value = "INVOKE", target = "net/minecraft/client/renderer/RenderBlocks.renderItemIn3d (I)Z")
      ),
      at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glTranslatef (FFF)V", remap = false)
   )
   private void lunar$onInitialTranslate(float var1, float var2, float var3, EntityItem var4, double var5, double var7, double var9, float var11, float var12) {
      if (renderInFrame$v1_7) {
         GL11.glTranslatef(var1, var2, var3);
      } else {
         float var13 = (float)(var2 - var7);
         DroppedItemRenderEvent var14 = ClientEventBus.method29().method12(DroppedItemRenderEvent.class, () -> new DroppedItemRenderEvent((ItemEntityBridge)var4, var13));
         if (var14 != null && var14.method2() != var13) {
            GL11.glTranslatef(var1, (float)(var7 + var14.method2()), var3);
         } else {
            GL11.glTranslatef(var1, var2, var3);
         }
      }
   }

   @Annotation2(max = 0)
   @ModifyConstant(
      method = "renderDroppedItem$v1_7(Lnet/minecraft/entity/item/EntityItem;Lnet/minecraft/util/IIcon;IFFFF)V",
      constant = @Constant(floatValue = 0.25F, ordinal = 0)
   )
   private float lunar$get2DItemYOffset(float var1) {
      return Client.method109().method40().method30().isEnabled() ? 0.5F : var1;
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "renderDroppedItem$v1_7(Lnet/minecraft/entity/item/EntityItem;Lnet/minecraft/util/IIcon;IFFFF)V",
      slice = @Slice(
         from = @At(value = "FIELD", target = "net/minecraft/entity/item/EntityItem.hoverStart : F"),
         to = @At(value = "FIELD", target = "net/minecraft/item/ItemStack.stackSize : I")
      ),
      at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glRotatef (FFFF)V", remap = false)
   )
   private void lunar$onRotateFor2D(
      float var1, float var2, float var3, float var4, EntityItem var5, IIcon var6, int var7, float var8, float var9, float var10, float var11
   ) {
      if (renderInFrame$v1_7) {
         GL11.glRotatef(var1, var2, var3, var4);
      } else {
         ItemRotationRenderEvent var12 = ClientEventBus.method29()
            .method12(ItemRotationRenderEvent.class, () -> new ItemRotationRenderEvent(AbstractRenderContext.method32(), (ItemEntityBridge)var5, var8));
         if (var12 == null || !var12.isCancelled()) {
            GL11.glRotatef(var1, var2, var3, var4);
         }
      }
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "doRender$v1_7(Lnet/minecraft/entity/item/EntityItem;DDDFF)V",
      at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glRotatef (FFFF)V", remap = false)
   )
   private void lunar$onRotateFor3D(
      float var1, float var2, float var3, float var4, EntityItem var5, double var6, double var8, double var10, float var12, float var13
   ) {
      if (renderInFrame$v1_7) {
         GL11.glRotatef(var1, var2, var3, var4);
      } else {
         ItemRotationRenderEvent var14 = ClientEventBus.method29()
            .method12(ItemRotationRenderEvent.class, () -> new ItemRotationRenderEvent(AbstractRenderContext.method32(), (ItemEntityBridge)var5, var13));
         if (var14 == null || !var14.isCancelled()) {
            GL11.glRotatef(var1, var2, var3, var4);
         }
      }
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "doRender$v1_7(Lnet/minecraft/entity/item/EntityItem;DDDFF)V",
      at = @At(value = "INVOKE", target = "java/util/Random.setSeed (J)V", remap = false)
   )
   private void lunar$onSetSeed(Random var1, long var2, EntityItem var4, double var5, double var7, double var9, float var11, float var12) {
      if (renderInFrame$v1_7) {
         var1.setSeed(var2);
      } else {
         ItemClumpEvent var13 = ClientEventBus.method29().method12(ItemClumpEvent.class, () -> new ItemClumpEvent((ItemEntityBridge)var4, var2, 0.2F));
         if (var13 == null) {
            var1.setSeed(var2);
            this.lunar$clumpSpread = 0.2F;
         } else {
            var1.setSeed(var13.method2());
            this.lunar$clumpSpread = var13.method3();
         }
      }
   }

   @Annotation2(max = 0)
   @ModifyConstant(method = "doRender$v1_7(Lnet/minecraft/entity/item/EntityItem;DDDFF)V", constant = @Constant(floatValue = 0.2F))
   private float lunar$tweakClumping(float var1) {
      return renderInFrame$v1_7 ? 0.2F : this.lunar$clumpSpread;
   }

   @Annotation2(max = 0)
   @ModifyVariable(
      method = "renderItemOverlayIntoGUI$v1_7(Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
      at = @At("HEAD"),
      ordinal = 0,
      argsOnly = true
   )
   private String lunar$renderGuiItemOverlay$v1_7(String var1, FontRenderer var2, TextureManager var3, ItemStack var4, int var5, int var6, String var7) {
      return this.lunar$renderGuiIemOverlay(var1, var4, var5, var6);
   }

   @Annotation2(min = 1)
   @ModifyVariable(
      method = "renderItemOverlayIntoGUI$v1_8(Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
      at = @At("HEAD"),
      ordinal = 0,
      argsOnly = true
   )
   private String lunar$renderGuiItemOverlay$v1_8(String var1, FontRenderer var2, ItemStack var3, int var4, int var5, String var6) {
      return this.lunar$renderGuiIemOverlay(var1, var3, var4, var5);
   }

   @Unique
   private String lunar$renderGuiIemOverlay(String var1, ItemStack var2, int var3, int var4) {
      if (var2 != null && var2.getItem() != null) {
         ItemStackSizeRenderEvent.Data var5 = ClientEventBus.method29()
            .method12(
               ItemStackSizeRenderEvent.Data.class,
               () -> new ItemStackSizeRenderEvent.Data(new LegacyGuiGraphicsBridge(AbstractRenderContext.method32()), var1, (ItemStackBridge)var2, var3, var4)
            );
         return var5 == null ? var1 : var5.getText();
      } else {
         return var1;
      }
   }

   @Annotation2(1)
   @WrapOperation(
      method = "renderItemModelForEntity$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/RenderItem;renderItemModelTransform$v1_8(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;)V"
      )
   )
   private void lunar$captureEntity$v1_8(
      RenderItem var1, ItemStack var2, IBakedModel var3, TransformType var4, Operation<Void> var5, @Local(argsOnly = true) EntityLivingBase var6
   ) {
      this.lunar$currentEntityRenderingItem = var6;

      try {
         var5.call(new Object[]{var1, var2, var3, var4});
      } finally {
         this.lunar$currentEntityRenderingItem = null;
      }
   }

   @Annotation2(5)
   @WrapOperation(
      method = "renderItem$v1_12",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/RenderItem;renderItemModel$v1_12(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;Z)V"
      )
   )
   private void lunar$captureEntity$v1_12(
      RenderItem var1, ItemStack var2, IBakedModel var3, TransformType var4, boolean var5, Operation<Void> var6, @Local(argsOnly = true) EntityLivingBase var7
   ) {
      this.lunar$currentEntityRenderingItem = var7;

      try {
         var6.call(new Object[]{var1, var2, var3, var4, var5});
      } finally {
         this.lunar$currentEntityRenderingItem = null;
      }
   }

   @Annotation2(1)
   @Inject(
      method = "renderItemModelTransform$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V", shift = Shift.AFTER, ordinal = 0)
   )
   private void lunar$onRenderItemTransform$v1_8(ItemStack var1, IBakedModel var2, TransformType var3, CallbackInfo var4) {
      if (var3 != TransformType.THIRD_PERSON
         && var3 != TransformType.GUI
         && var3 != TransformType.ground
         && this.lunar$currentEntityRenderingItem instanceof Bridge6_10 var5) {
         ClientEventBus.method29().method12(GlintTransformEvent.class, () -> {
            if (var3 == TransformType.NONE && ThreadModuleDump63.method4().method40().method98().method15().method15()) {
               return null;
            }

            ItemTransformsBridge.Type var3x = switch (var3) {
               case NONE -> ItemTransformsBridge.Type.NONE;
               case FIRST_PERSON -> ItemTransformsBridge.Type.FIRST_PERSON_RIGHT_HAND;
               case HEAD -> ItemTransformsBridge.Type.HEAD;
               case FIXED -> ItemTransformsBridge.Type.FIXED;
               default -> throw new IllegalStateException("Unexpected value: " + var3);
            };
            return new GlintTransformEvent(var5, GlintTransformEvent.Type.AFTER_TRANSFORMS, (ItemStackBridge)var1, var3x, AbstractRenderContext.method32());
         });
      }
   }

   @Annotation2(5)
   @Inject(
      method = "renderItemModel$v1_12",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms;applyTransformSide$v1_12(Lnet/minecraft/client/renderer/block/model/ItemTransformVec3f;Z)V",
         shift = Shift.AFTER
      )
   )
   private void lunar$onRenderItemTransform$v1_12(ItemStack var1, IBakedModel var2, TransformType var3, boolean var4, CallbackInfo var5) {
      if (var3 != TransformType.THIRD_PERSON_LEFT_HAND$v1_12
         && var3 != TransformType.THIRD_PERSON_RIGHT_HAND$v1_12
         && var3 != TransformType.GUI
         && var3 != TransformType.ground
         && this.lunar$currentEntityRenderingItem instanceof Bridge6_10 var6) {
         ClientEventBus.method29()
            .method12(
               GlintTransformEvent.class,
               () -> new GlintTransformEvent(
                  var6, GlintTransformEvent.Type.AFTER_TRANSFORMS, (ItemStackBridge)var1, ItemTransformsBridge.Type.VALUES[var3.ordinal()], AbstractRenderContext.method32()
               )
            );
      }
   }
}
