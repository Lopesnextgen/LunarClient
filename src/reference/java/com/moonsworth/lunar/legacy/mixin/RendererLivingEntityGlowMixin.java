package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.lunarclient.apollo.module.glow.GlowModule;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.RendererLivingEntityLayerBridge;
import com.moonsworth.lunar.client.network.apollo.GlowApolloHandler;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.render.EventRenderEntityModel;
import com.moonsworth.lunar.client.event.entity.EventLivingEntityBase.EventEntityScale;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.combat.reachdisplay.ReachDisplay;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.Optional;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScorePlayerTeam;
import org.lwjgl.actually3.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@VersionGate(min = 1)
@Mixin(RendererLivingEntity.class)
public abstract class RendererLivingEntityGlowMixin<T extends EntityLivingBase> extends Render<T> implements RendererLivingEntityLayerBridge {
   @Unique
   private ColorOption lunar$highlightColor = null;
   @Unique
   private boolean lunar$renderOutlines;
   @Unique
   private static final FloatBuffer BUF_FLOAT_4 = BufferUtils.createFloatBuffer(4);
   @Unique
   private float lunar$partialTicks;

   public RendererLivingEntityGlowMixin() {
   }

   @Shadow
   public abstract void renderLayers(T value1, float value2, float value3, float value4, float value5, float value6, float value7, float value8);

   @Redirect(
      method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderLivingBase;renderModel(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V")
   )
   private void lunar$doRenderModel(RendererLivingEntity rendererlivingentity1, T value2, float value3, float value4, float value5, float value6, float value7, float value8) {
      if (value2 instanceof Bridge6_10) {
         EventRenderEntityModel highlightimpl7_29 = (EventRenderEntityModel)LunarEventBus.method29().method12(EventRenderEntityModel.class, () -> new EventRenderEntityModel((Bridge6_10)value2));
         if (highlightimpl7_29 != null && highlightimpl7_29.isCancelled()) {
            return;
         }
      }

      rendererlivingentity1.renderModel(value2, value3, value4, value5, value6, value7, value8);
   }

   @WrapOperation(
      method = "setBrightness",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/RenderLivingBase;getColorMultiplier(Lnet/minecraft/entity/EntityLivingBase;FF)I"
      )
   )
   private int lunar$highlightHook(RendererLivingEntity rendererlivingentity1, T value2, float value3, float value4, Operation<Integer> operation5) {
      this.lunar$highlightColor = null;
      if (value2.hurtTime <= 0 && value2.deathTime <= 0) {
         ReachDisplay reachdisplay6 = Ref.method4().method40().method39();
         if (reachdisplay6.isEnabled() && value2 instanceof EntityPlayer player7 && reachdisplay6.method4((Bridge6_10)player7)) {
            ColorOption lightingextension42228 = reachdisplay6.method13();
            this.lunar$highlightColor = lightingextension42228;
            return lightingextension42228.method14(0.0F);
         }
      }

      return (Integer)operation5.call(new Object[]{rendererlivingentity1, value2, value3, value4});
   }

   @WrapOperation(method = "setBrightness", at = @At(value = "INVOKE", target = "Ljava/nio/FloatBuffer;flip()Ljava/nio/Buffer;"))
   private Buffer lunar$highlightHook(FloatBuffer floatbuffer1, Operation<Buffer> operation2) {
      ColorOption lightingextension42223 = this.lunar$highlightColor;
      this.lunar$highlightColor = null;
      if (lightingextension42223 != null) {
         int number4 = lightingextension42223.method1(0.0F);
         floatbuffer1.clear();
         floatbuffer1.put(ColorUtils.method5(number4));
         floatbuffer1.put(ColorUtils.method6(number4));
         floatbuffer1.put(ColorUtils.method7(number4));
         floatbuffer1.put(ColorUtils.method8(number4));
         floatbuffer1.flip();
         return floatbuffer1;
      } else {
         return (Buffer)operation2.call(new Object[]{floatbuffer1});
      }
   }

   @VersionGate(min = 1)
   @ModifyArg(
      method = "setBrightness",
      at = @At(value = "INVOKE", target = "java/nio/FloatBuffer.put (F)Ljava/nio/FloatBuffer;", ordinal = 0),
      slice = @Slice(
         from = @At(value = "FIELD", target = "net/minecraft/client/renderer/OpenGlHelper.GL_INTERPOLATE : I"),
         to = @At(value = "CONSTANT", args = "floatValue=255.0")
      )
   )
   private float lunar$modifyHitColorRed(float value1) {
      return Ref.method4().method40().method14().method4(value1);
   }

   @VersionGate(min = 1)
   @ModifyArg(
      method = "setBrightness",
      at = @At(value = "INVOKE", target = "java/nio/FloatBuffer.put (F)Ljava/nio/FloatBuffer;", ordinal = 1),
      slice = @Slice(
         from = @At(value = "FIELD", target = "net/minecraft/client/renderer/OpenGlHelper.GL_INTERPOLATE : I"),
         to = @At(value = "CONSTANT", args = "floatValue=255.0")
      )
   )
   private float lunar$modifyHitColorGreen(float value1) {
      return Ref.method4().method40().method14().method5(value1);
   }

   @VersionGate(min = 1)
   @ModifyArg(
      method = "setBrightness",
      at = @At(value = "INVOKE", target = "java/nio/FloatBuffer.put (F)Ljava/nio/FloatBuffer;", ordinal = 2),
      slice = @Slice(
         from = @At(value = "FIELD", target = "net/minecraft/client/renderer/OpenGlHelper.GL_INTERPOLATE : I"),
         to = @At(value = "CONSTANT", args = "floatValue=255.0")
      )
   )
   private float lunar$modifyHitColorBlue(float value1) {
      return Ref.method4().method40().method14().method6(value1);
   }

   @VersionGate(min = 1)
   @ModifyArg(
      method = "setBrightness",
      at = @At(value = "INVOKE", target = "java/nio/FloatBuffer.put (F)Ljava/nio/FloatBuffer;", ordinal = 3),
      slice = @Slice(
         from = @At(value = "FIELD", target = "net/minecraft/client/renderer/OpenGlHelper.GL_INTERPOLATE : I"),
         to = @At(value = "CONSTANT", args = "floatValue=255.0")
      )
   )
   private float lunar$modifyHitColorAlpha(float value1) {
      return Ref.method4().method40().method14().method7(value1);
   }

   @VersionGate(1)
   @Unique
   private int lunar$getTeamColor$v1_8(EntityLivingBase entity1) {
      int number2 = 16777215;
      ScorePlayerTeam scoreplayerteam3 = (ScorePlayerTeam)entity1.getTeam();
      if (scoreplayerteam3 != null) {
         String text4 = FontRenderer.getFormatFromString(scoreplayerteam3.getColorPrefix());
         if (text4.length() >= 2) {
            number2 = this.getFontRendererFromRenderManager().getColorCode(text4.charAt(1));
         }
      }

      return number2;
   }

   @VersionGate(1)
   @Redirect(method = "setScoreTeamColor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V"))
   private void lunar$setGlowingColor$v1_8(float value1, float value2, float value3, float value4, @Local(ordinal = 0, argsOnly = true) EntityLivingBase entity5) {
      BridgeExtension bridgeextension6 = (BridgeExtension)entity5;
      Optional optional7 = Ref.method4()
         .method84()
         .method3(GlowModule.class)
         .filter(arg1x -> ((GlowApolloHandler)arg1x).method3(bridgeextension6))
         .map(arg1x -> ((GlowApolloHandler)arg1x).method4(bridgeextension6));
      if (optional7.isPresent()) {
         int number8 = (Integer)optional7.get();
         if (number8 == Integer.MIN_VALUE) {
            number8 = this.lunar$getTeamColor$v1_8(entity5);
         }

         value1 = ColorUtils.method5(number8);
         value2 = ColorUtils.method6(number8);
         value3 = ColorUtils.method7(number8);
      } else if (bridgeextension6.bridge$getGlowingColor() != -1) {
         int number10 = bridgeextension6.bridge$getGlowingColor();
         value1 = ColorUtils.method5(number10);
         value2 = ColorUtils.method6(number10);
         value3 = ColorUtils.method7(number10);
      }

      RewindHandlers rewindhandlers11 = Ref.method4().method40().method85().method35();
      if (rewindhandlers11 != null && rewindhandlers11.method50().method14() == bridgeextension6) {
         int number9 = rewindhandlers11.method50().getHighlightColor();
         value1 = ColorUtils.method5(number9);
         value2 = ColorUtils.method6(number9);
         value3 = ColorUtils.method7(number9);
      }

      GlStateManager.color(value1, value2, value3, value4);
   }

   @VersionGate(min = 5)
   @Redirect(
      method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;enableOutlineMode$v1_12(I)V")
   )
   public void lunar$setGlowingColor$v1_12(int number1, @Local(ordinal = 0, argsOnly = true) EntityLivingBase entity2) {
      BridgeExtension bridgeextension4 = (BridgeExtension)entity2;
      Optional optional5 = Ref.method4()
         .method84()
         .method3(GlowModule.class)
         .filter(arg1x -> ((GlowApolloHandler)arg1x).method3(bridgeextension4))
         .map(arg1x -> ((GlowApolloHandler)arg1x).method4(bridgeextension4));
      int number3;
      if (optional5.isPresent()) {
         number3 = (Integer)optional5.get();
         if (number3 == Integer.MIN_VALUE) {
            number3 = this.getTeamColor$v1_12(entity2);
         }
      } else if (bridgeextension4.bridge$getGlowingColor() != -1) {
         number3 = bridgeextension4.bridge$getGlowingColor();
      } else {
         number3 = this.getTeamColor$v1_12(entity2);
      }

      RewindHandlers rewindhandlers6 = Ref.method4().method40().method85().method35();
      if (rewindhandlers6 != null && rewindhandlers6.method50().method14() == bridgeextension4) {
         number3 = rewindhandlers6.method50().getHighlightColor();
      }

      GlStateManager.enableOutlineMode$v1_12(number3);
   }

   @Unique
   private static void lunar$enableOutlineMode(int number0) {
      BUF_FLOAT_4.put(0, (number0 >> 16 & 0xFF) / 255.0F);
      BUF_FLOAT_4.put(1, (number0 >> 8 & 0xFF) / 255.0F);
      BUF_FLOAT_4.put(2, (number0 >> 0 & 0xFF) / 255.0F);
      BUF_FLOAT_4.put(3, (number0 >> 24 & 0xFF) / 255.0F);
      GL11.glTexEnv(8960, 8705, BUF_FLOAT_4);
      GL11.glTexEnvi(8960, 8704, 34160);
      GL11.glTexEnvi(8960, 34161, 7681);
      GL11.glTexEnvi(8960, 34176, 34166);
      GL11.glTexEnvi(8960, 34192, 768);
      GL11.glTexEnvi(8960, 34162, 7681);
      GL11.glTexEnvi(8960, 34184, 5890);
      GL11.glTexEnvi(8960, 34200, 770);
   }

   @Unique
   private static void lunar$disableOutlineMode() {
      GL11.glTexEnvi(8960, 8704, 8448);
      GL11.glTexEnvi(8960, 34161, 8448);
      GL11.glTexEnvi(8960, 34162, 8448);
      GL11.glTexEnvi(8960, 34176, 5890);
      GL11.glTexEnvi(8960, 34184, 5890);
      GL11.glTexEnvi(8960, 34192, 768);
      GL11.glTexEnvi(8960, 34200, 770);
   }

   @Unique
   private int lunar$setScoreTeamColor(EntityLivingBase entity1) {
      int number2 = 16777215;
      if (entity1 instanceof EntityPlayer) {
         ScorePlayerTeam scoreplayerteam3 = (ScorePlayerTeam)(Ref.MC_VERSION >= 5 ? entity1.getTeam() : entity1.getTeam());
         if (scoreplayerteam3 != null) {
            String text4 = FontRenderer.getFormatFromString(Ref.MC_VERSION >= 5 ? scoreplayerteam3.getColor().formattingCode + "" : scoreplayerteam3.getColorPrefix());
            if (text4.length() >= 2) {
               char character5 = text4.charAt(1);
               int index6 = "0123456789abcdefklmnor".indexOf(character5);
               number2 = this.getFontRendererFromRenderManager().colorCode[index6];
            }
         }
      }

      float value7 = (number2 >> 16 & 0xFF) / 255.0F;
      float value8 = (number2 >> 8 & 0xFF) / 255.0F;
      float value9 = (number2 & 0xFF) / 255.0F;
      GL11.glDisable(2896);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
      GL11.glColor4f(value7, value8, value9, 1.0F);
      GL11.glDisable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glDisable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
      return number2;
   }

   @Unique
   public void lunar$unsetScoreTeamColor() {
      GL11.glEnable(2896);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
      GL11.glEnable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glEnable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   public void bridge$setRenderOutlines(boolean flag1) {
      this.lunar$renderOutlines = flag1;
   }

   @Inject(method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V", at = @At("HEAD"))
   private void lunar$storePartialTicks(T value1, double value2, double value4, double value6, float value8, float value9, CallbackInfo callback10) {
      this.lunar$partialTicks = value9;
   }

   @WrapOperation(
      method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/RenderLivingBase;renderModel(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V",
         ordinal = 0
      )
   )
   private void lunar$doOutlinePass(
      RendererLivingEntity rendererlivingentity1, T value2, float value3, float value4, float value5, float value6, float value7, float value8, Operation<Void> operation9
   ) {
      int number10 = -1;
      if (this.lunar$renderOutlines) {
         number10 = Ref.method4()
            .method84()
            .method3(GlowModule.class)
            .filter(arg1x -> ((GlowApolloHandler)arg1x).method3((BridgeExtension)value2))
            .map(arg1x -> ((GlowApolloHandler)arg1x).method4((BridgeExtension)value2))
            .map(
               arg2x -> arg2x == Integer.MIN_VALUE
                  ? (Ref.MC_VERSION >= 5 ? this.getTeamColor$v1_12(value2) : this.lunar$getTeamColor$v1_8(value2))
                  : arg2x
            )
            .orElseGet(() -> this.lunar$setScoreTeamColor(value2));
         RewindHandlers rewindhandlers11 = Ref.method4().method40().method85().method35();
         if (rewindhandlers11 != null && rewindhandlers11.method50().method14() == value2) {
            number10 = rewindhandlers11.method50().getHighlightColor();
         }

         GL11.glEnable(2903);
         lunar$enableOutlineMode(number10);
      }

      operation9.call(new Object[]{rendererlivingentity1, value2, value3, value4, value5, value6, value7, value8});
      if (this.lunar$renderOutlines) {
         this.lunar$unsetScoreTeamColor();
         lunar$disableOutlineMode();
         GL11.glDisable(2903);
         if (Ref.MC_VERSION < 5 && (!(value2 instanceof EntityPlayer) || !((EntityPlayer)value2).isSpectator())) {
            GL11.glEnable(2903);
            lunar$enableOutlineMode(number10);
            this.renderLayers((T)value2, value3, value4, this.lunar$partialTicks, value5, value6, value7, value8);
            lunar$disableOutlineMode();
            GL11.glDisable(2903);
            this.lunar$unsetScoreTeamColor();
         }
      }
   }

   @VersionGate(1)
   @Inject(
      method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V",
      at = @At(
         value = "INVOKE",
         target = "net/minecraft/client/renderer/entity/RendererLivingEntity.preRenderCallback (Lnet/minecraft/entity/EntityLivingBase;F)V"
      )
   )
   private void lunar$changeEntityScale$v1_8(T value1, double value2, double value4, double value6, float value8, float value9, CallbackInfo callback10) {
      EventEntityScale data711 = (EventEntityScale)LunarEventBus.method29().method12(EventEntityScale.class, () -> new EventEntityScale((EntityLivingBridge)value1, 1.0F));
      if (data711 != null) {
         float value12 = data711.getScale();
         GlStateManager.scale(value12, value12, value12);
      }
   }

   @VersionGate(min = 5)
   @Inject(
      method = "prepareScale$v1_12",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/RenderLivingBase;preRenderCallback(Lnet/minecraft/entity/EntityLivingBase;F)V"
      )
   )
   private void lunar$changeEntityScale$v1_12(EntityLivingBase entity1, float value2, CallbackInfoReturnable<Float> callbackinforeturnable3) {
      EventEntityScale data74 = (EventEntityScale)LunarEventBus.method29().method12(EventEntityScale.class, () -> new EventEntityScale((EntityLivingBridge)entity1, 1.0F));
      if (data74 != null) {
         float value5 = data74.getScale();
         GlStateManager.scale(value5, value5, value5);
      }
   }

   @Inject(method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V", at = @At("HEAD"), cancellable = true)
   private void lunar$skipArmorStandRenderLogic(T value1, double value2, double value4, double value6, float value8, float value9, CallbackInfo callback10) {
      if (value1 instanceof EntityArmorStand entityarmorstand11) {
         if (entityarmorstand11.isInvisible()) {
            if (Ref.MC_VERSION >= 5) {
               for (ItemStack stack13 : entityarmorstand11.armorItems$v1_12) {
                  if (stack13 != ItemStack.EMPTY$v1_12) {
                     return;
                  }
               }

               for (ItemStack stack18 : entityarmorstand11.handItems$v1_12) {
                  if (stack18 != ItemStack.EMPTY$v1_12) {
                     return;
                  }
               }
            } else {
               for (ItemStack stack15 : entityarmorstand11.getInventory()) {
                  if (stack15 != null) {
                     return;
                  }
               }
            }

            callback10.cancel();
            this.renderName(value1, value2, value4, value6);
         }
      }
   }

   @WrapOperation(
      method = "canRenderName(Lnet/minecraft/entity/EntityLivingBase;)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isGuiEnabled()Z")
   )
   private boolean lunar$rewindForceNametagRenderingInFreecam(Operation<Boolean> operation1) {
      RewindMod rewind2 = Ref.method4().method40().method85();
      return rewind2.method17(arg0 -> !arg0.method45().method15().isFixedToPlayer()) ? true : (Boolean)operation1.call(new Object[0]);
   }
}
