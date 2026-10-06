package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.lunarclient.apollo.module.entity.EntityModule;
import com.lunarclient.apollo.module.nametag.NametagModule;
import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.LayerCapeBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingStateBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LayerRendererBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.RenderTypeLookup;
import com.moonsworth.lunar.bridge.RendererLivingEntityLayerBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.EntityApolloHandler;
import com.moonsworth.lunar.client.network.apollo.NametagApolloHandler;
import com.moonsworth.lunar.client.network.apollo.ServerRuleApolloHandler;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.account.BadgeManager;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager.Data;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.mod.render.nametag.Nametag;
import com.moonsworth.lunar.client.mod.render.onesevenvisuals.OneSevenVisuals;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.cosmetics.DummyPlayer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.wrapper.LayerRendererBridgeAdapter;
import com.moonsworth.lunar.legacy.wrapper.NameTagRendererBridgeImpl;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.scoreboard.Team.EnumVisible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@VersionGate(min = 1)
@Mixin(RendererLivingEntity.class)
public abstract class RendererLivingEntityMixin<T extends EntityLivingBase & EntityLivingBridge, S extends EntityLivingStateBridge>
   extends Render<T>
   implements RendererLivingEntityLayerBridge<T, S> {
   @Shadow
   public List<LayerRenderer<T>> layerRenderers;
   @Unique
   private LayerRenderer<?> bridge$layerCape;

   public RendererLivingEntityMixin(RenderManager rendermanager1) {
      super(rendermanager1);
   }

   @Shadow
   public abstract <V extends EntityLivingBase, U extends LayerRenderer<V>> boolean addLayer(U value1);

   @Shadow
   public abstract boolean canRenderName(T value1);

   @WrapMethod(method = "renderName")
   private void lunar$renderName(T value1, double value2, double value4, double value6, Operation<Void> operation8) {
      if (value1.getName() != null) {
         LinkedList list9 = new LinkedList();
         list9.add(0, ((EntityLivingBridge)value1).bridge$getDisplayNameComponent());
         EventRenderNameTag highlightimpl1110 = (EventRenderNameTag)LunarEventBus.method29()
            .method12(EventRenderNameTag.class, () -> new EventRenderNameTag((EntityLivingStateBridge)value1, value2, value4, value6, list9, (Component)list9.get(0)));
         if ((highlightimpl1110 == null || !highlightimpl1110.isCancelled()) && (this.canRenderName((T)value1) || this.lunar$canShowInThirdPerson((T)value1))) {
            int index11 = 0;

            for (Component component13 : highlightimpl1110 == null ? list9 : highlightimpl1110.getLines()) {
               if (component13 instanceof TextComponent text14) {
                  double value15 = highlightimpl1110 == null ? value4 : highlightimpl1110.getY() + index11 / 3.5F + value1.height + 0.5;
                  value15 -= value1.isChild() ? value1.height / 2.0F : 0.0;
                  boolean flag17 = false;
                  String text18;
                  if (Ref.MC_VERSION >= 1) {
                     text18 = value1.getName();
                  } else {
                     text18 = value1.getCommandSenderName$v1_7();
                  }

                  if (TextBridge.getFirstTextComponent(((EntityLivingBridge)value1).bridge$getDisplayNameComponent()) != null) {
                     flag17 = TextBridge.doesComponentContain(text14, new String[]{text18});
                  }

                  if (value1.isSneaking()) {
                     this.impl$renderLabelSneaking(value1, text14, value2, value15 - 0.25, value6, flag17);
                  } else {
                     AtomicInteger number19 = new AtomicInteger(value1.isSneaking() ? 32 : 64);
                     Ref.method4()
                        .method84()
                        .method3(ServerRuleModule.class)
                        .filter(arg0 -> (Boolean)arg0.getOptions().get(ServerRuleModule.OVERRIDE_NAMETAG_RENDER_DISTANCE))
                        .map(arg0 -> ((ServerRuleApolloHandler)arg0).method3())
                        .ifPresent(arg2x -> number19.set(value1.isSneaking() ? arg2x / 2 : arg2x));
                     double value20 = highlightimpl1110 == null
                        ? this.impl$renderLivingLabel((T)value1, text14, value2, value15, value4, number19.get(), flag17)
                        : this.impl$renderLivingLabel((T)value1, text14, highlightimpl1110.getX(), value15, highlightimpl1110.getZ(), number19.get(), flag17);
                     if (value20 != value15) {
                        index11++;
                     }
                  }

                  index11++;
               }
            }
         }
      }
   }

   @Unique
   private boolean lunar$canShowInThirdPerson(T value1) {
      return Ref.method4().method40().method51().method4((BridgeExtension)value1);
   }

   @VersionGate(1)
   @Inject(method = "getSwingProgress", at = @At("HEAD"), cancellable = true)
   private void impl$getSwingProgress(EntityLivingBase entity1, float value2, CallbackInfoReturnable<Float> callbackinforeturnable3) {
      OneSevenVisuals onesevenvisuals4 = Client.method109().method40().method98();
      if (entity1 == Minecraft.getMinecraft().thePlayer && onesevenvisuals4.isEnabled()) {
         float value5 = entity1.getSwingProgress(value2);
         callbackinforeturnable3.setReturnValue(onesevenvisuals4.getItemsLegacy().method4(value5, value2));
      }
   }

   private void impl$setupLabelTransform(double value1, double value3, double value5) {
      float value7 = 0.02666667F;
      GlStateManager.translate(value1, value3, value5);
      if (HologramsIterator2.field7) {
         GlStateManager.rotate(-HologramsIterator2.playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(HologramsIterator2.playerViewX, 1.0F, 0.0F, 0.0F);
      } else {
         GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
      }

      GlStateManager.scale(-0.02666667F, -0.02666667F, 0.02666667F);
   }

   @Unique
   private void impl$renderLabelSneaking(Entity entity1, TextComponent text2, double value3, double value5, double value7, boolean flag9) {
      Nametag nametag10 = Ref.method4().method40().method51();
      float value11 = nametag10.getBackgroundOpacity();
      FontRenderer font12 = this.getFontRendererFromRenderManager();
      GlStateManager.pushMatrix();
      this.impl$setupLabelTransform(value3, value5, value7);
      GlStateManager.disableLighting();
      GlStateManager.depthMask(false);
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      int number13 = (int)TextBridge.getTextWidth(text2, (Bridge10_2)font12) / 2;
      int number14 = -(number13 + 1);
      int number15 = number13 + 1;
      float value16 = 0.0F;
      Tessellator tessellator17 = Tessellator.getInstance();
      WorldRenderer worldrenderer18;
      if (Ref.MC_VERSION >= 5) {
         worldrenderer18 = tessellator17.getBuffer$v1_12();
      } else if (Ref.MC_VERSION >= 1) {
         worldrenderer18 = tessellator17.getWorldRenderer();
      } else {
         worldrenderer18 = null;
      }

      GlStateManager.disableTexture2D();
      worldrenderer18.begin(7, DefaultVertexFormats.POSITION_COLOR);
      worldrenderer18.pos(number14, -1.0F + value16, 0.0).color(0.0F, 0.0F, 0.0F, value11).endVertex();
      worldrenderer18.pos(number14, 8.0F + value16, 0.0).color(0.0F, 0.0F, 0.0F, value11).endVertex();
      worldrenderer18.pos(number15, 8.0F + value16, 0.0).color(0.0F, 0.0F, 0.0F, value11).endVertex();
      worldrenderer18.pos(number15, -1.0F + value16, 0.0).color(0.0F, 0.0F, 0.0F, value11).endVertex();
      tessellator17.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.depthMask(true);
      int number19 = 553648127;
      if (nametag10.isEnabled() && (Boolean)nametag10.getNametagShadow().get()) {
         ((Bridge10_2)font12).bridge$drawShadow(AbstractRenderContext.method32(), text2, number14 + 1, value16, number19);
         GlStateManager.translate(0.0F, 0.0F, -0.001F);
      }

      ((Bridge10_2)font12).method6(AbstractRenderContext.method32(), text2, number14 + 1, value16, number19, false, true);
      GlStateManager.enableLighting();
      GlStateManager.disableBlend();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   @Unique
   public double impl$renderLivingLabel(T value1, TextComponent text2, double value3, double value5, double value7, int number9, boolean flag10) {
      Object obj11;
      if (Ref.MC_VERSION >= 1) {
         obj11 = this.renderManager.livingPlayer;
      } else {
         obj11 = this.renderManager.livingPlayer$v1_7;
      }

      double value12 = 0.0;
      if (obj11 != null && !(value1 instanceof DummyPlayer)) {
         if (Ref.MC_VERSION >= 5) {
            value12 = value1.getDistanceSq((Entity)obj11);
         } else {
            value12 = value1.getDistanceSqToEntity((Entity)obj11);
         }

         if (value12 > number9 * number9) {
            return value5;
         }
      }

      if (value1 instanceof AbstractClientPlayer && flag10 && value12 < 100.0) {
         Scoreboard scoreboard14 = ((AbstractClientPlayer)value1).getWorldScoreboard();
         ScoreObjective scoreobjective15 = scoreboard14.getObjectiveInDisplaySlot(2);
         if (scoreobjective15 != null) {
            Score score16;
            if (Ref.MC_VERSION >= 5) {
               score16 = scoreboard14.getOrCreateScore$v1_12(value1.getName(), scoreobjective15);
            } else if (Ref.MC_VERSION >= 1) {
               score16 = scoreboard14.getValueFromObjective(value1.getName(), scoreobjective15);
            } else {
               score16 = scoreboard14.getValueFromObjective(value1.getCommandSenderName$v1_7(), scoreobjective15);
            }

            this.impl$drawLabel((T)value1, Component.text(score16.getScorePoints() + " " + scoreobjective15.getDisplayName()), value3, value5, value7, false);
            value5 += this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * 0.02666667F;
         }
      }

      return this.impl$drawLabel((T)value1, text2, value3, value5, value7, flag10);
   }

   public double impl$drawLabel(T value1, TextComponent text2, double value3, double value5, double value7, boolean flag9) {
      if (Ref.method4().method98().method4() instanceof NameTagRendererBridgeImpl bridgeiterator_210
         && bridgeiterator_210.method1(text2, value3, value5, value7, (BridgeExtension)value1, flag9)) {
         return value5;
      } else {
         Nametag nametag26 = Ref.method4().method40().method51();
         float value27 = nametag26.getBackgroundOpacity();
         BridgeExtension3_5 bridgeextension3_512 = AbstractRenderContext.method32();
         FontRenderer font13 = Minecraft.getMinecraft().fontRendererObj;
         GlStateManager.pushMatrix();
         this.impl$setupLabelTransform(value3, value5, value7);
         GlStateManager.disableLighting();
         GlStateManager.depthMask(false);
         GlStateManager.disableDepth();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         Tessellator tessellator14 = Tessellator.theMinecraft;
         WorldRenderer worldrenderer15;
         if (Ref.MC_VERSION >= 5) {
            worldrenderer15 = tessellator14.getBuffer$v1_12();
         } else if (Ref.MC_VERSION >= 1) {
            worldrenderer15 = tessellator14.getWorldRenderer();
         } else {
            worldrenderer15 = null;
         }

         float value16 = 0.0F;
         if (text2.content().equals("deadmau5")) {
            value16 = -10.0F;
         }

         Data data17 = (Data)Ref.method4().method53().method63().get(value1.getUniqueID());
         boolean flag18 = value1 instanceof EntityPlayer && flag9 && Ref.method4().method53().method63().containsKey(value1.getUniqueID());
         boolean flag19 = flag18 && data17 != null && (data17.method8() || (Boolean)Ref.method4().method41().method6().method43().get());
         boolean flag20 = flag18
            && data17 != null
            && data17.method11() != null
            && (data17.method8() || (Boolean)Ref.method4().method41().method6().method44().get());
         int number21 = (int)TextBridge.getTextWidth(text2, (Bridge10_2)font13) / 2;
         if (flag20) {
            number21 += 5;
         }

         if (flag19) {
            number21 += 6;
         }

         int number22 = -(number21 + 1);
         int number23 = number21 + 1;
         int number24 = -number21 + (flag19 ? 12 : 0);
         GlStateManager.disableTexture2D();
         worldrenderer15.begin(7, DefaultVertexFormats.POSITION_COLOR);
         worldrenderer15.pos(number22, -1.0F + value16, 0.0).color(0.0F, 0.0F, 0.0F, value27).endVertex();
         worldrenderer15.pos(number22, 8.0F + value16, 0.0).color(0.0F, 0.0F, 0.0F, value27).endVertex();
         worldrenderer15.pos(number23, 8.0F + value16, 0.0).color(0.0F, 0.0F, 0.0F, value27).endVertex();
         worldrenderer15.pos(number23, -1.0F + value16, 0.0).color(0.0F, 0.0F, 0.0F, value27).endVertex();
         tessellator14.draw();
         GlStateManager.enableTexture2D();
         int number25 = 553648127;
         if (nametag26.isEnabled() && (Boolean)nametag26.getNametagShadow().get()) {
            ((Bridge10_2)font13).bridge$drawShadow(bridgeextension3_512, text2, number24, value16, number25);
            GlStateManager.translate(0.0F, 0.0F, -0.001F);
         }

         ((Bridge10_2)font13).method6(bridgeextension3_512, text2, number24, value16, number25, false, true);
         this.lunar$renderNametagAdditions(bridgeextension3_512, flag19, flag20, data17, value16, number23, number22, 0.15F, LunarRenderTypes.field33);
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         this.lunar$renderNametagAdditions(bridgeextension3_512, flag19, flag20, data17, value16, number23, number22, 1.0F, LunarRenderTypes.field34);
         if (nametag26.isEnabled() && (Boolean)nametag26.getNametagShadow().get()) {
            ((Bridge10_2)font13).bridge$drawShadow(bridgeextension3_512, text2, number24, value16, -1);
            GlStateManager.translate(0.0F, 0.0F, -0.001F);
         }

         ((Bridge10_2)font13).method6(bridgeextension3_512, text2, number24, value16, -1, false, true);
         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.popMatrix();
         return value5;
      }
   }

   @Unique
   private void lunar$renderNametagAdditions(
      AbstractRenderContext bridgeextension_91, boolean flag2, boolean flag3, Data data4, float value5, float value6, float value7, float value8, RenderTypeLookup mixinhelper6_39
   ) {
      if (flag2) {
         bridgeextension_91.push();
         int number10 = ColorUtils.method11(data4.method5(), data4.method6(), data4.method7(), value8);
         LcuiScreen.method41(
            mixinhelper6_39.get(CosmeticManager.field40), bridgeextension_91, value7 + 1.0F, value5 - 1.5F, (float)LcuiScreen.z, 0.0F, 0.0F, 10.0F, 10.0F, 10.0F, 10.0F, number10
         );
         if (data4.method1()) {
            GlStateManager.translate(0.0F, 0.0F, -1.0F);
            LcuiScreen.method41(
               mixinhelper6_39.get(CosmeticManager.field37),
               bridgeextension_91,
               value7 + 1.0F + 7.25F,
               value5 - 1.5F + 1.5F,
               (float)LcuiScreen.z,
               0.0F,
               0.0F,
               3.0F,
               3.0F,
               3.0F,
               3.0F,
               ColorUtils.method18(data4.method9(), value8)
            );
         }

         bridgeextension_91.pop();
      }

      if (flag3) {
         int number11 = ColorUtils.method18(-1, value8);
         BadgeManager.method9(bridgeextension_91, data4.method11(), value6 - 10.0F, value5 - 0.5F, 8.0F, 8.0F, number11, mixinhelper6_39);
      }
   }

   @Inject(method = "canRenderName", at = @At("HEAD"), cancellable = true)
   private void bridge$onShouldShowName(T value1, CallbackInfoReturnable<Boolean> callbackinforeturnable2) {
      if (value1 instanceof DummyPlayer threadmoduledump543) {
         callbackinforeturnable2.setReturnValue(threadmoduledump543.shouldRenderNametag());
      } else {
         if (Ref.MC_VERSION >= 1) {
            if (Minecraft.getMinecraft().thePlayer == null) {
               callbackinforeturnable2.setReturnValue(false);
            }
         } else if (Minecraft.getMinecraft().thePlayer$v1_7 == null) {
            callbackinforeturnable2.setReturnValue(false);
         }
      }
   }

   @Inject(method = "canRenderName", at = @At("HEAD"), cancellable = true)
   private void apollo$hiddenNametagOverride(T value1, CallbackInfoReturnable<Boolean> callbackinforeturnable2) {
      if (!(value1 instanceof DummyPlayer)) {
         if (value1 != Ref.method7()) {
            if (Ref.method4().method40().method51().isEnabled()) {
               Ref.method4().method84().method3(NametagModule.class).ifPresent(arg2x -> {
                  NametagApolloHandler highlight3iterator173 = (NametagApolloHandler)arg2x;
                  if (highlight3iterator173.method6(((BridgeExtension)value1).bridge$getUniqueID())) {
                     callbackinforeturnable2.setReturnValue(false);
                  }
               });
            }
         }
      }
   }

   @WrapOperation(
      method = "canRenderName",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/Team;getNameTagVisibility$v1_8()Lnet/minecraft/scoreboard/Team$EnumVisible;")
   )
   private EnumVisible apollo$shownNametagOverride(Team team1, Operation<EnumVisible> operation2, @Local(argsOnly = true) EntityLivingBase entity3) {
      if (Ref.method4().method40().method51().isEnabled()) {
         UUID uuid4 = ((BridgeExtension)entity3).bridge$getUniqueID();
         boolean flag5 = Ref.method4()
            .method84()
            .method3(NametagModule.class)
            .map(arg1x -> ((NametagApolloHandler)arg1x).method5(uuid4))
            .orElse(false);
         if (flag5) {
            return EnumVisible.ALWAYS;
         }
      }

      return (EnumVisible)operation2.call(new Object[]{team1});
   }

   @VersionGate(min = 5)
   @Inject(
      method = "applyRotations$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/text/TextFormatting;getTextWithoutFormattingCodes(Ljava/lang/String;)Ljava/lang/String;"),
      cancellable = true
   )
   public void apollo$applyRotations$v1_12(T value1, float value2, float value3, float value4, CallbackInfo callback5) {
      this.apollo$applyRotations((T)value1, callback5);
   }

   @VersionGate(max = 1)
   @Inject(
      method = "rotateCorpse$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/text/TextFormatting;getTextWithoutFormattingCodes(Ljava/lang/String;)Ljava/lang/String;"),
      cancellable = true
   )
   public void apollo$applyRotations$v1_8(T value1, float value2, float value3, float value4, CallbackInfo callback5) {
      this.apollo$applyRotations((T)value1, callback5);
   }

   @Unique
   private void apollo$applyRotations(T value1, CallbackInfo callback2) {
      Ref.method4().method84().method3(EntityModule.class).ifPresent(arg2x -> {
         EntityApolloHandler highlight3iterator123 = (EntityApolloHandler)arg2x;
         boolean flag4 = Ref.MC_VERSION >= 5 ? highlight3iterator123.method6(value1.getUniqueID()) : highlight3iterator123.method7(value1.getEntityId());
         if (flag4) {
            GlStateManager.translate(0.0F, value1.height + 0.1F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            callback2.cancel();
         }
      });
   }

   public void bridge$addLayer(LayerRendererBridge<T, S> mextension1, boolean flag2) {
      if (this instanceof RenderPlayer) {
         this.addLayer(new LayerRendererBridgeAdapter(mextension1, (RenderPlayer)this));
      }
   }

   public LayerCapeBridge bridge$getLayerCape() {
      if (this.bridge$layerCape != null) {
         return (LayerCapeBridge)this.bridge$layerCape;
      }

      for (LayerRenderer layerrenderer2 : this.layerRenderers) {
         if (layerrenderer2 instanceof LayerCapeBridge) {
            return (LayerCapeBridge)(this.bridge$layerCape = layerrenderer2);
         }
      }

      return null;
   }
}
