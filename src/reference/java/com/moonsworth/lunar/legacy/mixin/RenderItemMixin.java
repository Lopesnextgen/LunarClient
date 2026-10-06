package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_29;
import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.Bridge5_19;
import com.moonsworth.lunar.bridge.ItemEntityBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BakedModelExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.bridge.slayer.Slayer6;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemGlintRenderEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.ichor.Annotation_2;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderItem.class)
public abstract class RenderItemMixin implements Bridge5_19 {
   @Shadow
   public float zLevel;
   @Annotation2(min = 1)
   @Shadow
   @Final
   public ItemModelMesher itemModelMesher;
   @Annotation2(min = 1)
   @Final
   @Shadow
   public TextureManager textureManager;
   @Final
   @Shadow
   public static ResourceLocation RES_ITEM_GLINT;
   @Annotation2(min = 0)
   @Shadow
   public static boolean renderInFrame$v1_7;
   @Annotation2(min = 0)
   @Shadow
   public RenderBlocks renderBlocksRi$v1_7;
   private boolean renderItemGui = false;

   @Annotation2(min = 1)
   @Shadow
   public abstract void renderModel(IBakedModel var1, int var2);

   @Annotation2(min = 1)
   @Shadow
   public abstract void renderItemAndEffectIntoGUI(ItemStack var1, int var2, int var3);

   @Annotation2(min = 1)
   @Shadow
   public abstract void renderItemOverlays(FontRenderer var1, ItemStack var2, int var3, int var4);

   @Annotation2(min = 1)
   @Shadow
   public abstract void renderItem(ItemStack var1, IBakedModel var2);

   @Annotation2(min = 1)
   @Shadow
   public abstract void renderItem(ItemStack var1, TransformType var2);

   @Annotation2(min = 1)
   @Shadow
   public abstract void renderModel(IBakedModel var1, ItemStack var2);

   @Annotation2(min = 1)
   @Shadow
   public abstract void renderEffect(IBakedModel var1);

   @Annotation2(min = 1)
   @Shadow
   public abstract boolean shouldRenderItemIn3D(ItemStack var1);

   @Annotation2(min = 0)
   @Shadow
   public abstract void renderItemAndEffectIntoGUI(FontRenderer var1, TextureManager var2, ItemStack var3, int var4, int var5);

   @Annotation2(min = 0)
   @Shadow
   public abstract void renderItemOverlayIntoGUI(FontRenderer var1, TextureManager var2, ItemStack var3, int var4, int var5);

   @Annotation2(min = 0)
   @Shadow
   public abstract void renderDroppedItem$v1_7(EntityItem var1, IIcon var2, int var3, float var4, float var5, float var6, float var7);

   @Shadow
   public abstract IBakedModel getItemModelWithOverrides$v1_12(ItemStack var1, World var2, EntityLivingBase var3);

   @Override
   public void bridge$renderModel(BakedModelExtension var1, int var2) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.renderModel((IBakedModel)var1, var2);
      } else {
         Tessellator var3 = Tessellator.theMinecraft;
         ItemRenderer.renderItemIn2D$v1_7(var3, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, var2);
      }
   }

   @Override
   public Bridge2_29 bridge$getItemModelShaper() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return (Bridge2_29)this.itemModelMesher;
      } else {
         throw new RuntimeException("bridge$getItemModelShaper() cannot be called in 1.7!");
      }
   }

   @Annotation_2(absent = "optifine")
   @Override
   public BakedModelExtension bridge$getModel(ItemStackBridge var1, Itemcounter6 var2, Object var3) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return (BakedModelExtension)this.getItemModelWithOverrides$v1_12((ItemStack)var1, (World)var2, (EntityLivingBase)var3);
      } else if (ThreadModuleDump63.MC_VERSION == 1) {
         return (BakedModelExtension)this.itemModelMesher.getItemModel((ItemStack)var1);
      } else {
         throw new UnsupportedOperationException("unavailable function in 1.7.10");
      }
   }

   @Override
   public void bridge$renderItem(ItemStackBridge var1, BakedModelExtension var2) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.renderItem((ItemStack)var1, (IBakedModel)var2);
      } else {
         throw new UnsupportedOperationException("unavailable function in 1.7.10");
      }
   }

   @Override
   public void bridge$setZLevel(float var1) {
      this.zLevel = var1;
   }

   @Override
   public float bridge$getZLevel() {
      return this.zLevel;
   }

   @Override
   public boolean bridge$shouldRenderItemIn3D(ItemStackBridge var1) {
      ItemStack var2 = (ItemStack)var1;
      return ThreadModuleDump63.MC_VERSION >= 1
         ? this.shouldRenderItemIn3D(var2)
         : var2.getItemSpriteNumber$v1_7() == 0 && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(var2.getItem()).getRenderType());
   }

   @Override
   public void bridge$renderItemAndEffectIntoGUI(AbstractRenderContext var1, ItemStackBridge var2, int var3, int var4) {
      this.bridge$renderItemAndEffectIntoGUI(var1, var2, var3, var4, false);
   }

   @Override
   public void bridge$renderItemAndEffectIntoGUI(AbstractRenderContext var1, ItemStackBridge var2, int var3, int var4, boolean var5) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.renderItemAndEffectIntoGUI((ItemStack)var2, var3, var4);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         if (var5) {
            this.renderItemOverlays(Minecraft.getMinecraft().fontRendererObj, (ItemStack)var2, var3, var4);
         }
      } else {
         this.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRendererObj, Minecraft.getMinecraft().getTextureManager(), (ItemStack)var2, var3, var4);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         if (var5) {
            this.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, Minecraft.getMinecraft().getTextureManager(), (ItemStack)var2, var3, var4);
         }
      }
   }

   @Annotation2(min = 1)
   @Override
   public void bridge$renderItemInWorld(AbstractRenderContext var1, ItemStackBridge var2) {
      this.renderItem((ItemStack)var2, TransformType.FIXED);
   }

   @Annotation2(max = 0)
   @Override
   public void bridge$renderDroppedItem(ItemEntityBridge var1, Bridge4_8 var2, int var3, float var4, float var5, float var6, float var7) {
      ItemStack var8 = ((EntityItem)var1).getEntityItem();
      Block var9 = Block.getBlockFromItem(var8.getItem());
      if (var8.getItemSpriteNumber$v1_7() == 0 && RenderBlocks.renderItemIn3d(var9.getRenderType())) {
         this.renderBlocksRi$v1_7.renderBlockAsItem(var9, ((ItemStackBridge)var1.bridge$getItemState()).bridge$getItemDamage(), 1.0F);
      } else {
         renderInFrame$v1_7 = true;
         this.renderDroppedItem$v1_7((EntityItem)var1, (IIcon)var2, var3, var4, var5, var6, var7);
         renderInFrame$v1_7 = false;
      }
   }

   @Annotation2(min = 1)
   @Inject(
      method = "renderItem$v1_8(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V", shift = Shift.BEFORE),
      cancellable = true
   )
   public void impl$renderItem(ItemStack var1, IBakedModel var2, CallbackInfo var3) {
      if (!var2.isBuiltInRenderer()) {
         GlStateManager.translate(-0.5F, -0.5F, -0.5F);
         Optional var4 = Bridge.method5();
         if (var4.isPresent()) {
            Slayer2 var5 = (Slayer2)var4.get();
            if (var5.getConfig().hasCustomItems()) {
               Optional var6 = var5.getCustomItems();
               if (var6.isPresent()) {
                  Slayer6 var7 = (Slayer6)var6.get();
                  ResourceLocationBridge var8 = this.bridge$getModelLocation();
                  Optional var9 = var7.getCustomItemModel((ItemStackBridge)var1, (BakedModelExtension)var2, var8, false);
                  if (var9.isPresent()) {
                     var2 = (IBakedModel)var9.get();
                  }
               }
            }
         }

         boolean var10 = ThreadModuleDump63.method4().method40().method24().isEnabled()
            && this.renderItemGui
            && var1.getItem() != null
            && var1.getItem() instanceof ItemPotion
            && var1.hasEffect();
         if (!var10 || !ThreadModuleDump63.method4().method40().method24().method15().get()) {
            this.renderModel(var2, var1);
         }

         if (var10) {
            GlStateManager.pushMatrix();
            this.renderPotion_v1_8(var2, ThreadModuleDump63.method4().method40().method24().method4((ItemStackBridge)var1));
            GlStateManager.popMatrix();
         }

         if (var10 && ThreadModuleDump63.method4().method40().method24().method15().get()) {
            this.renderModel(var2, var1);
         }

         IBakedModel var11 = var2;
         boolean var12 = var4.flatMap(Slayer2::getCustomItems)
            .map(var3x -> var3x.renderCustomEffect(this, (ItemStackBridge)var1, (BakedModelExtension)var11))
            .orElse(false);
         if (!var10 && var1.hasEffect() && !var12) {
            this.textureManager.bindTexture(RES_ITEM_GLINT);
            ItemGlintRenderEvent var13 = ClientEventBus.method29()
               .method12(
                  ItemGlintRenderEvent.class,
                  () -> {
                     ItemGlintRenderEvent.Type var3x = this.renderItemGui ? ItemGlintRenderEvent.Type.GUI : ItemGlintRenderEvent.Type.ITEM;
                     return new ItemGlintRenderEvent(
                        var3x, var2xx -> this.renderModel(var11, var2xx), (BakedModelExtension)var11, null, (ItemStackBridge)var1, AbstractRenderContext.method32()
                     );
                  }
               );
            this.textureManager.bindTexture(TextureMap.locationBlocksTexture);
            if (var13 != null && var13.isCancelled()) {
               GlStateManager.popMatrix();
               var3.cancel();
               return;
            }

            this.renderEffect(var2);
         }

         GlStateManager.popMatrix();
         var3.cancel();
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "renderItemIntoGUI$v1_8(Lnet/minecraft/item/ItemStack;II)V", at = @At("HEAD"))
   public void impl$renderItemIntoGui$HEAD(ItemStack var1, int var2, int var3, CallbackInfo var4) {
      this.renderItemGui = true;
   }

   @Annotation2(min = 1)
   @Inject(method = "renderItemIntoGUI$v1_8(Lnet/minecraft/item/ItemStack;II)V", at = @At("TAIL"))
   public void impl$renderItemIntoGui$TAIL(ItemStack var1, int var2, int var3, CallbackInfo var4) {
      this.renderItemGui = false;
   }

   @Annotation2(min = 1)
   private void renderPotion_v1_8(IBakedModel var1, int var2) {
      double var3 = 1.25;
      double var5 = -0.1;
      double var7 = -0.1;
      GlStateManager.depthMask(false);
      GlStateManager.disableLighting();
      GlStateManager.blendFunc(768, 1);
      if (ThreadModuleDump63.method4().method40().method24().method16().get()) {
         GlStateManager.scale(var3, var3, var3);
         GlStateManager.translate(var7, var5, 0.0);
      }

      this.textureManager.bindTexture(RES_ITEM_GLINT);
      GlStateManager.matrixMode(5890);
      GlStateManager.pushMatrix();
      GlStateManager.scale(8.0F, 8.0F, 8.0F);
      float var9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
      GlStateManager.translate(var9, 0.0F, 0.0F);
      GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
      this.renderModel(var1, var2);
      GlStateManager.popMatrix();
      GlStateManager.pushMatrix();
      GlStateManager.scale(8.0F, 8.0F, 8.0F);
      float var10 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
      GlStateManager.translate(-var10, 0.0F, 0.0F);
      GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
      this.renderModel(var1, var2);
      GlStateManager.popMatrix();
      GlStateManager.matrixMode(5888);
      GlStateManager.blendFunc(770, 771);
      GlStateManager.enableLighting();
      GlStateManager.depthMask(true);
      this.textureManager.bindTexture(TextureMap.locationBlocksTexture);
   }
}
