package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge2_19;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge8Handler2;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.MixinHelper_6;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.mod.combat.hitbox.Hitbox;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.Render_v1_7;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderManager.class)
public abstract class RenderManagerMixin implements Bridge2_43 {
   @Annotation2(max = 0)
   @Shadow
   public static double renderPosX$v1_7;
   @Annotation2(max = 0)
   @Shadow
   public static double renderPosY$v1_7;
   @Annotation2(max = 0)
   @Shadow
   public static double renderPosZ$v1_7;
   @Annotation2(min = 1)
   @Shadow
   public double renderPosX;
   @Annotation2(min = 1)
   @Shadow
   public double renderPosY;
   @Annotation2(min = 1)
   @Shadow
   public double renderPosZ;
   @Annotation2(min = 1)
   @Shadow
   public float playerViewY;
   @Annotation2(min = 1)
   @Shadow
   public float playerViewX;
   @Shadow
   public TextureManager renderEngine;
   @Shadow
   public Entity pointedEntity;
   @Shadow
   public GameSettings options;
   @Annotation2(min = 1)
   @Shadow
   public boolean renderShadow;
   @Shadow
   public double viewerPosX;
   @Shadow
   public double viewerPosY;
   @Shadow
   public double viewerPosZ;
   @Unique
   private HashMap<String, MixinHelper_6> skinMap_v1_7;
   @Annotation2(min = 1)
   @Shadow
   public boolean debugBoundingBox;
   @Annotation2(max = 0)
   @Shadow
   public static boolean debugBoundingBox$v1_7;
   @Final
   @Shadow
   public Map<String, RenderPlayer> skinMap;
   @Final
   @Shadow
   public RenderPlayer playerRenderer;
   @Shadow
   public Map entityRenderMap;
   @Shadow
   public World world;
   @Unique
   private Entity lunar$lastRenderedEntity;

   @Annotation2(min = 1)
   @Shadow
   public abstract void setDebugBoundingBox(boolean var1);

   @Annotation2(max = 1)
   @Shadow
   public abstract boolean renderEntityWithPosYaw(Entity var1, double var2, double var4, double var6, float var8, float var9);

   @Annotation2(min = 5)
   @Shadow
   public abstract void renderEntity$v1_12(Entity var1, double var2, double var4, double var6, float var8, float var9, boolean var10);

   @Shadow
   public abstract boolean isRenderShadow();

   @Override
   public double bridge$renderPosX() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.renderPosX : renderPosX$v1_7;
   }

   @Override
   public double bridge$renderPosY() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.renderPosY : renderPosY$v1_7;
   }

   @Override
   public double bridge$renderPosZ() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.renderPosZ : renderPosZ$v1_7;
   }

   @Override
   public double bridge$playerViewX() {
      return this.playerViewX;
   }

   @Override
   public double bridge$playerViewY() {
      return this.playerViewY;
   }

   @Override
   public MixinHelper_6 bridge$defaultPlayerRenderer() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_6)this.playerRenderer : (MixinHelper_6)this.entityRenderMap.get(EntityPlayer.class);
   }

   @Override
   public void bridge$setTextureManager(Bridge8Handler2 var1) {
      this.renderEngine = (TextureManager)var1;
   }

   @Override
   public void bridge$setLivingEntity(BridgeExtension2_5 var1) {
      this.pointedEntity = (EntityLivingBase)var1;
   }

   @Override
   public void bridge$setOptions(GameOptionsBridge var1) {
      this.options = (GameSettings)var1;
   }

   @Override
   public void bridge$setRenderShadow(boolean var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.renderShadow = var1;
      }
   }

   @Override
   public void bridge$setPlayerViewY(float var1) {
      this.playerViewY = var1;
   }

   @Override
   public void bridge$renderEntityWithPosYaw(
      @Nullable AbstractRenderContext var1, BridgeExtension var2, double var3, double var5, double var7, float var9, float var10, int var11
   ) {
      if (var11 != Bridge.method8().method92()) {
         int var12 = var11 % 65536;
         int var13 = var11 / 65536;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var12, var13);
      }

      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.renderEntity$v1_12((Entity)var2, var3, var5, var7, var9, var10, false);
      } else {
         this.renderEntityWithPosYaw((Entity)var2, var3, var5, var7, var9, var10);
      }
   }

   @Override
   public void bridge$renderShadow(
      @NotNull AbstractRenderContext var1, Itemcounter6 var2, double var3, double var5, double var7, float var9, double var10, double var12
   ) {
      if (ThreadModuleDump63.MC_VERSION <= 0 || this.isRenderShadow()) {
         float var14 = (float)((1.0 - var10 / 256.0) * var12);
         if (var14 > 0.0F) {
            this.lunar$renderShadow((World)var2, var3, var5, var7, var9, var14);
         }
      }
   }

   @Unique
   private void lunar$renderShadow(World var1, double var2, double var4, double var6, float var8, float var9) {
      float var10 = Math.min(var9 / 0.5F, var8);
      int var11 = (int)Math.floor(var2 - var8);
      int var12 = (int)Math.floor(var2 + var8);
      int var13 = (int)Math.floor(var4 - var10);
      int var14 = (int)Math.floor(var4);
      int var15 = (int)Math.floor(var6 - var8);
      int var16 = (int)Math.floor(var6 + var8);
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.enableBlend();
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            GlStateManager.blendFunc$v1_12(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
         }

         this.renderEngine.bindTexture(Render.shadowTextures);
         GlStateManager.depthMask(false);
         Tessellator var17 = Tessellator.getInstance();
         WorldRenderer var18 = var17.worldRenderer;
         var18.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         MutableBlockPos var19 = new MutableBlockPos();

         for (int var20 = var15; var20 <= var16; var20++) {
            for (int var21 = var11; var21 <= var12; var21++) {
               for (int var22 = var13; var22 <= var14; var22++) {
                  if (ThreadModuleDump63.MC_VERSION >= 5) {
                     var19.setPos(var21, var22, var20);
                  } else {
                     var19.set(var21, var22, var20);
                  }

                  if (this.world.getLightFromNeighbors(var19) > 3) {
                     this.lunar$renderShadowSingle$v1_12((Itemcounter6)var1, var18, var19, var2, var4, var6, var8, var9);
                  }
               }
            }
         }

         var17.draw();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableBlend();
         GlStateManager.depthMask(true);
      } else {
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         this.renderEngine.bindTexture(Render_v1_7.shadowTextures);
         GL11.glDepthMask(false);
         Tessellator var23 = Tessellator.theMinecraft;
         var23.startDrawingQuads$v1_7();

         for (int var24 = var15; var24 <= var16; var24++) {
            for (int var25 = var11; var25 <= var12; var25++) {
               for (int var26 = var13; var26 <= var14; var26++) {
                  Block var27 = this.world.getBlock(var25, var26 - 1, var24);
                  if (var27.getMaterial() != Material.air && this.world.getBlockLightValue$v1_7(var25, var26, var24) > 3) {
                     this.lunar$renderShadowSingle$v1_7(var27, var23, var25, var26, var24, var2, var4, var6, var8, var9);
                  }
               }
            }
         }

         var23.draw();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(3042);
         GL11.glDepthMask(true);
      }
   }

   @Annotation2(min = 1)
   @Unique
   private void lunar$renderShadowSingle$v1_12(
      Itemcounter6 var1, WorldRenderer var2, BlockPos var3, double var4, double var6, double var8, float var10, float var11
   ) {
      BlockPos var12 = var3.down();
      Bridge2_17 var13 = var1.method2((Vector3iBridge)var12);
      if (var13.bridge$getRenderShape() != ItemcounterType_4.INVISIBLE && var13.bridge$isCollisionShapeFullBlock(var1, (Horsestats20Extension2)var12)) {
         double var14 = (var11 - (var6 - var3.getY()) / 2.0) * 0.5 * ((World)var1).getLightBrightness(var3);
         if (!(var14 < 0.0)) {
            if (var14 > 1.0) {
               var14 = 1.0;
            }

            AxisAlignedBB var16;
            if (ThreadModuleDump63.MC_VERSION >= 5) {
               var16 = ((IBlockProperties)var13).getBoundingBox((IBlockAccess)var1, var3);
            } else {
               var16 = (AxisAlignedBB)var13.bridge$getBlock().bridge$getAABB(var1, (Horsestats20Extension2)var3);
            }

            if (var16 == null) {
               return;
            }

            double var17 = var3.getX() + var16.minX;
            double var19 = var3.getX() + var16.maxX;
            double var21 = var3.getY() + var16.minY + 0.015625;
            double var23 = var3.getZ() + var16.minZ;
            double var25 = var3.getZ() + var16.maxZ;
            float var27 = (float)((var4 - var17) / 2.0 / var10 + 0.5);
            float var28 = (float)((var4 - var19) / 2.0 / var10 + 0.5);
            float var29 = (float)((var8 - var23) / 2.0 / var10 + 0.5);
            float var30 = (float)((var8 - var25) / 2.0 / var10 + 0.5);
            var2.pos(var17 - var4, var21 - var6, var23 - var8).tex(var27, var29).color(1.0F, 1.0F, 1.0F, (float)var14).endVertex();
            var2.pos(var17 - var4, var21 - var6, var25 - var8).tex(var27, var30).color(1.0F, 1.0F, 1.0F, (float)var14).endVertex();
            var2.pos(var19 - var4, var21 - var6, var25 - var8).tex(var28, var30).color(1.0F, 1.0F, 1.0F, (float)var14).endVertex();
            var2.pos(var19 - var4, var21 - var6, var23 - var8).tex(var28, var29).color(1.0F, 1.0F, 1.0F, (float)var14).endVertex();
         }
      }
   }

   @Annotation2(max = 0)
   @Unique
   private void lunar$renderShadowSingle$v1_7(
      Block var1, Tessellator var2, int var3, int var4, int var5, double var6, double var8, double var10, float var12, float var13
   ) {
      if (var1.renderAsNormalBlock$v1_7()) {
         double var14 = (var13 - (var8 - var4) / 2.0) * 0.5 * this.world.getLightBrightness(var3, var4, var5);
         if (!(var14 < 0.0)) {
            if (var14 > 1.0) {
               var14 = 1.0;
            }

            var2.setColorRGBA_F$v1_7(1.0F, 1.0F, 1.0F, (float)var14);
            double var16 = var3 + var1.getBlockBoundsMinX();
            double var18 = var3 + var1.getBlockBoundsMaxX();
            double var20 = var4 + var1.getBlockBoundsMaxY() + 0.015625;
            double var22 = var5 + var1.getBlockBoundsMinZ();
            double var24 = var5 + var1.getBlockBoundsMaxZ();
            float var26 = (float)((var6 - var16) / 2.0 / var12 + 0.5);
            float var27 = (float)((var6 - var18) / 2.0 / var12 + 0.5);
            float var28 = (float)((var10 - var22) / 2.0 / var12 + 0.5);
            float var29 = (float)((var10 - var24) / 2.0 / var12 + 0.5);
            var2.addVertexWithUV$v1_7(var16 - var6, var20 - var8 - 1.0, var22 - var10, var26, var28);
            var2.addVertexWithUV$v1_7(var16 - var6, var20 - var8 - 1.0, var24 - var10, var26, var29);
            var2.addVertexWithUV$v1_7(var18 - var6, var20 - var8 - 1.0, var24 - var10, var27, var29);
            var2.addVertexWithUV$v1_7(var18 - var6, var20 - var8 - 1.0, var22 - var10, var27, var28);
         }
      }
   }

   @Override
   public Map<String, MixinHelper_6> bridge$getSkinMap() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return this.skinMap;
      }

      if (this.skinMap_v1_7 == null) {
         this.skinMap_v1_7 = new HashMap<>();
         this.skinMap_v1_7.put("default", this.bridge$defaultPlayerRenderer());
         this.skinMap_v1_7.put("slim", this.bridge$defaultPlayerRenderer());
      }

      return this.skinMap_v1_7;
   }

   @Annotation2(1)
   @Inject(
      method = "doRenderEntity$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/Render_v1_8;doRender(Lnet/minecraft/entity/Entity;DDDFF)V")
   )
   private void impl$doRender(CallbackInfoReturnable<Boolean> var1) {
      GlStateManager.enableDepth();
   }

   @Annotation2(min = 5)
   @Inject(
      method = "renderEntity$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/Render_v1_8;doRender(Lnet/minecraft/entity/Entity;DDDFF)V")
   )
   private void impl$doRender(CallbackInfo var1) {
      GlStateManager.enableDepth();
   }

   @Override
   public double bridge$viewerPosX() {
      return this.viewerPosX;
   }

   @Override
   public double bridge$viewerPosY() {
      return this.viewerPosY;
   }

   @Override
   public double bridge$viewerPosZ() {
      return this.viewerPosZ;
   }

   @Override
   public void bridge$setDebugBoundingBox(boolean var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.setDebugBoundingBox(var1);
      } else {
         debugBoundingBox$v1_7 = var1;
      }
   }

   @Override
   public void bridge$prepare(Itemcounter6 var1, BridgeExtension var2) {
   }

   @Inject(method = {"cacheActiveRenderInfo$v1_7", "cacheActiveRenderInfo$v1_8"}, at = @At("RETURN"))
   public void impl$cacheActiveRenderInfo_return(CallbackInfo var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         if (Minecraft.getMinecraft().thePlayer == null) {
            return;
         }
      } else if (Minecraft.getMinecraft().thePlayer$v1_7 == null) {
         return;
      }

      if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 2) {
         this.playerViewX = -this.playerViewX;
      }
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "renderDebugBoundingBox",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/RenderGlobal;drawOutlinedBoundingBox$v1_7(Lnet/minecraft/util/math/AxisAlignedBB;I)V",
         ordinal = 0
      )
   )
   private void impl$onDrawOutlinedBoundingBox(AxisAlignedBB var1, int var2) {
      Hitbox var3 = Client.method109().method40().method54();
      Entity var4 = this.lunar$lastRenderedEntity;
      if (ThreadModuleDump63.MC_VERSION <= 0 && var4 == ThreadModuleDump63.method7()) {
         float var5 = 1.62F;
         ((AxisAlignedBBMixin2)var1).bridge$setMaxY(var1.maxY - var5);
         ((AxisAlignedBBMixin2)var1).bridge$setMinY(var1.minY - var5);
      }

      if (var3.isEnabled() && var4 != null) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         ColorOption var8 = var3.method4((BridgeExtension)var4).method3((BridgeExtension)var4);
         FloatOption var6 = var3.method4((BridgeExtension)var4).method5();
         Hitbox.Type var7 = var3.method14().get();
         GL11.glLineWidth(var6.get());
         GL11.glEnable(2852);
         GL11.glLineStipple(var7.getFactor(), (short)var7.getPattern());
         if (var8.method1(0.0F) / 255.0F < 0.95F) {
            GL11.glEnable(6406);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 769);
         }

         RenderGlobal.drawOutlinedBoundingBox(var1, var8.method14(0.0F));
         GL11.glLineWidth(1.0F);
         GL11.glDisable(2852);
      } else {
         RenderGlobal.drawOutlinedBoundingBox(var1, var2);
      }
   }

   @Annotation2(1)
   @Redirect(
      method = "renderDebugBoundingBox",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/RenderGlobal;drawOutlinedBoundingBox$v1_8(Lnet/minecraft/util/math/AxisAlignedBB;IIII)V",
         ordinal = 0
      )
   )
   private void impl$onDrawOutlinedBoundingBox(AxisAlignedBB var1, int var2, int var3, int var4, int var5) {
      Hitbox var6 = Client.method109().method40().method54();
      if (var6.isEnabled() && this.lunar$lastRenderedEntity != null) {
         ColorOption var7 = var6.method4((BridgeExtension)this.lunar$lastRenderedEntity).method3((BridgeExtension)this.lunar$lastRenderedEntity);
         FloatOption var8 = var6.method4((BridgeExtension)this.lunar$lastRenderedEntity).method5();
         Hitbox.Type var9 = var6.method14().get();
         GL11.glLineWidth(var8.get());
         GL11.glEnable(2852);
         GL11.glLineStipple(var9.getFactor(), (short)var9.getPattern());
         if (var7.getAlpha() / 255.0F < 0.95F) {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 769);
         }

         int var10 = var7.method1(0.0F);
         RenderGlobal.drawOutlinedBoundingBox(
            var1, ThreadModuleDump23.method1(var10), ThreadModuleDump23.method2(var10), ThreadModuleDump23.method3(var10), ThreadModuleDump23.method4(var10)
         );
         GL11.glLineWidth(1.0F);
         GL11.glDisable(2852);
      } else {
         RenderGlobal.drawOutlinedBoundingBox(var1, var2, var3, var4, var5);
      }
   }

   @Annotation2(min = 5)
   @Redirect(
      method = "renderDebugBoundingBox",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;drawBoundingBox$v1_12(DDDDDDFFFF)V", ordinal = 0, opcode = 184)
   )
   private void impl$onDrawOutlinedBoundingBox(
      double var1, double var3, double var5, double var7, double var9, double var11, float var13, float var14, float var15, float var16
   ) {
      Hitbox var17 = Client.method109().method40().method54();
      if (var17.isEnabled() && this.lunar$lastRenderedEntity != null) {
         ColorOption var18 = var17.method4((BridgeExtension)this.lunar$lastRenderedEntity).method3((BridgeExtension)this.lunar$lastRenderedEntity);
         FloatOption var19 = var17.method4((BridgeExtension)this.lunar$lastRenderedEntity).method5();
         Hitbox.Type var20 = var17.method14().get();
         GL11.glLineWidth(var19.get());
         GL11.glEnable(2852);
         GL11.glLineStipple(var20.getFactor(), (short)var20.getPattern());
         if (var18.method1(0.0F) / 255.0F < 0.95F) {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 769);
         }

         int var21 = var18.method1(0.0F);
         RenderGlobal.drawBoundingBox$v1_12(
            var1, var3, var5, var7, var9, var11, ThreadModuleDump23.method5(var21), ThreadModuleDump23.greenFloat(var21), ThreadModuleDump23.method7(var21), 1.0F
         );
         GL11.glLineWidth(1.0F);
         GL11.glDisable(2852);
      } else {
         RenderGlobal.drawBoundingBox$v1_12(var1, var3, var5, var7, var9, var11, var13, var14, var15, var16);
      }
   }

   @Annotation2(min = 1)
   @Inject(
      method = "renderDebugBoundingBox",
      at = {
            @At(
               value = "INVOKE",
               target = "Lnet/minecraft/client/renderer/RenderGlobal;drawOutlinedBoundingBox$v1_8(Lnet/minecraft/util/math/AxisAlignedBB;IIII)V",
               ordinal = 1
            ),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;drawBoundingBox$v1_12(DDDDDDFFFF)V", ordinal = 2, opcode = 184)
      },
      require = 1,
      cancellable = true
   )
   public void impl$onRenderDebugBoundingBox(Entity var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      Hitbox var11 = Client.method109().method40().method54();
      if (var11.isEnabled() && !var11.method4((BridgeExtension)var1).method6().get()) {
         var10.cancel();
         GlStateManager.enableTexture2D();
         GlStateManager.enableLighting();
         GlStateManager.enableCull();
         GlStateManager.disableBlend();
         GlStateManager.depthMask(true);
      }
   }

   @Annotation2(min = 1)
   @Inject(
      method = "renderDebugBoundingBox",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;getInstance$v1_8()Lnet/minecraft/client/renderer/Tessellator;"),
      cancellable = true
   )
   public void impl$disableLookVector(Entity var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      Hitbox var11 = Client.method109().method40().method54();
      if (var11.isEnabled() && !var11.method4((BridgeExtension)var1).method6().get()) {
         var10.cancel();
         GlStateManager.enableTexture2D();
         GlStateManager.enableLighting();
         GlStateManager.enableCull();
         GlStateManager.disableBlend();
         GlStateManager.depthMask(true);
      }
   }

   @Annotation2(min = 1)
   @Inject(
      method = "renderDebugBoundingBox",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getLook$v1_8(F)Lnet/minecraft/util/math/Vec3d;"),
      cancellable = true
   )
   public void impl$onRenderDebugBoundingBox$getLook(Entity var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      Hitbox var11 = Client.method109().method40().method54();
      if (var11.isEnabled() && !var11.method4((BridgeExtension)var1).method6().get()) {
         var10.cancel();
         GlStateManager.enableTexture2D();
         GlStateManager.enableLighting();
         GlStateManager.enableCull();
         GlStateManager.disableBlend();
         GlStateManager.depthMask(true);
      }
   }

   @Inject(method = "renderDebugBoundingBox", at = @At("HEAD"), cancellable = true)
   public void impl$onRenderDebugBoundingBox$head(Entity var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      Hitbox var11 = Client.method109().method40().method54();
      if (var1 == HologramsIterator2.method14()) {
         var10.cancel();
      } else {
         if (var11.isEnabled()) {
            if (!var11.method4((BridgeExtension)var1).method2((BridgeExtension)var1)) {
               var10.cancel();
            } else {
               this.lunar$lastRenderedEntity = var1;
            }
         }
      }
   }

   @Inject(method = "renderDebugBoundingBox", at = @At("TAIL"))
   public void impl$onRenderDebugBoundingBox$tail(Entity var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      this.lunar$lastRenderedEntity = null;
   }

   @Override
   public boolean bridge$showDebugBoundingBox() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.debugBoundingBox : debugBoundingBox$v1_7;
   }

   @Override
   public Optional<Bridge2_19> bridge$getCamera() {
      return Optional.empty();
   }

   @Override
   public Vec3Bridge bridge$getCameraPos() {
      Bridge2_43 var1 = ThreadModuleDump63.method13();
      return (Vec3Bridge)(
         new Vec3(var1.bridge$renderPosX(), var1.bridge$renderPosY() + ThreadModuleDump63.method7().bridge$getEyeHeight(), var1.bridge$renderPosZ())
      );
   }

   @Override
   public int bridge$getPackedLightCoords(BridgeExtension var1, float var2) {
      if (var1.bridge$isOnFire()) {
         return Bridge.method8().method92();
      } else {
         return ThreadModuleDump63.MC_VERSION == 5 ? ((Entity)var1).getBrightnessForRender() : ((Entity)var1).getBrightnessForRender(var2);
      }
   }
}
