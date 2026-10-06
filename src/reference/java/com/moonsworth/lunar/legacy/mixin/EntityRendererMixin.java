package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GameRendererBridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.MapItemRendererBridge;
import com.moonsworth.lunar.bridge.Bridge7_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin implements GameRendererBridge {
   @Final
   @Shadow
   public Minecraft mc;
   @Final
   @Shadow
   public IResourceManager resourceManager;
   @Shadow
   public ShaderGroup shaderGroup;
   @Final
   @Shadow
   public MapItemRenderer mapItemRenderer;
   @Shadow
   public ItemStack itemActivationItem$v1_12;
   @Shadow
   public int itemActivationTicks$v1_12;
   @Shadow
   public float itemActivationOffX$v1_12;
   @Shadow
   public float itemActivationOffY$v1_12;
   @Unique
   private int lunar$renderTargetsWidth;
   @Unique
   private int lunar$renderTargetsHeight;

   public EntityRendererMixin() {
   }

   @VersionGate(min = 1)
   @Shadow
   public abstract void loadShader(ResourceLocation location1);

   @Shadow
   public abstract boolean isShaderActive();

   @Shadow
   public abstract ShaderGroup getShaderGroup();

   @VersionGate(min = 1)
   @Shadow
   public abstract void stopUseShader();

   @VersionGate(max = 0)
   @Shadow
   public abstract void deactivateShader$v1_7();

   @VersionGate(max = 0)
   @Shadow
   public abstract void enableLightmap(double value1);

   @VersionGate(max = 0)
   @Shadow
   public abstract void disableLightmap(double value1);

   @VersionGate(min = 1)
   @Shadow
   public abstract void enableLightmap();

   @VersionGate(min = 1)
   @Shadow
   public abstract void disableLightmap();

   @Shadow
   public abstract void updateShaderGroupSize(int number1, int number2);

   public void bridge$loadPostEffectShader(ResourceLocationBridge horsestats141, ResourceLocationBridge horsestats142, ResourceLocationBridge horsestats143) {
      if (Ref.MC_VERSION >= 1) {
         if (ShaderLinkHelper.getStaticShaderLinkHelper() != null) {
            if (Bridge.getMinecraftVersion().method23()) {
               this.loadShader((ResourceLocation)horsestats142);
               return;
            }

            this.loadShader((ResourceLocation)horsestats141);
         }
      } else if (OpenGlHelper.isFramebufferEnabled() && OpenGlHelper.shadersSupported) {
         if (this.shaderGroup != null) {
            this.shaderGroup.deleteShaderGroup();
         }

         try {
            if (Bridge.getMinecraftVersion().method23()) {
               this.shaderGroup = new ShaderGroup(this.mc.getTextureManager(), this.resourceManager, this.mc.getFramebuffer(), (ResourceLocation)horsestats142);
            } else {
               this.shaderGroup = new ShaderGroup(this.mc.getTextureManager(), this.resourceManager, this.mc.getFramebuffer(), (ResourceLocation)horsestats141);
            }

            if (!Ref.method4().method40().method85().method19()) {
               this.shaderGroup.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
            } else {
               RewindHandlers rewindhandlers4 = Ref.method4().method40().method85().method35();
               Bridge3_24 bridge3_245 = rewindhandlers4.method48().method24().method11();
               this.shaderGroup.createBindFramebuffers(bridge3_245.bridge$framebufferTextureWidth(), bridge3_245.bridge$framebufferTextureHeight());
            }
         } catch (Exception exception6) {
         }
      }
   }

   public boolean bridge$isShaderActive() {
      return this.isShaderActive();
   }

   public Bridge7_10 bridge$getShaderGroup() {
      return (Bridge7_10)this.getShaderGroup();
   }

   public void bridge$stopUseShader() {
      if (Ref.MC_VERSION >= 1) {
         this.stopUseShader();
      } else {
         this.deactivateShader$v1_7();
      }
   }

   public void bridge$enableLightmap() {
      if (Ref.MC_VERSION >= 1) {
         this.enableLightmap();
      } else {
         this.enableLightmap(0.0);
      }
   }

   public void bridge$disableLightmap() {
      if (Ref.MC_VERSION >= 1) {
         this.disableLightmap();
      } else {
         this.disableLightmap(0.0);
      }
   }

   public MapItemRendererBridge bridge$getMapItemRenderer() {
      return (MapItemRendererBridge)this.mapItemRenderer;
   }

   @VersionGate(min = 5)
   @Nullable
   public ItemStackBridge bridge$getItemActivationItem() {
      return (ItemStackBridge)this.itemActivationItem$v1_12;
   }

   @VersionGate(min = 5)
   public int bridge$getItemActivationTicks() {
      return this.itemActivationTicks$v1_12;
   }

   @VersionGate(min = 5)
   public float bridge$getItemActivationOffsetX() {
      return this.itemActivationOffX$v1_12;
   }

   @VersionGate(min = 5)
   public float bridge$getItemActivationOffsetY() {
      return this.itemActivationOffY$v1_12;
   }

   public void bridge$resize(int number1, int number2) {
      this.updateShaderGroupSize(number1, number2);
   }

   @Inject(method = "updateShaderGroupSize", at = @At("HEAD"))
   private void lunar$setRenderTargetsSize(int number1, int number2, CallbackInfo callback3) {
      this.lunar$renderTargetsWidth = number1;
      this.lunar$renderTargetsHeight = number2;
   }

   public int bridge$getRenderTargetsWidth() {
      return this.lunar$renderTargetsWidth;
   }

   public int bridge$getRenderTargetsHeight() {
      return this.lunar$renderTargetsHeight;
   }

   public void bridge$setRenderTargetsWidth(int number1) {
      this.lunar$renderTargetsWidth = number1;
   }

   public void bridge$setRenderTargetsHeight(int number1) {
      this.lunar$renderTargetsHeight = number1;
   }
}
