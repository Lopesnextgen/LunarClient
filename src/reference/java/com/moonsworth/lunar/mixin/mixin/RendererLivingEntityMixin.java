package com.moonsworth.lunar.mixin.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.lunarclient.apollo.module.entity.EntityModule;
import com.lunarclient.apollo.module.glow.GlowModule;
import com.lunarclient.apollo.module.nametag.NametagModule;
import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.RenderTypeLookup;
import com.moonsworth.lunar.bridge.RendererLivingEntityLayerBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.EntityApolloHandler;
import com.moonsworth.lunar.client.network.apollo.NametagApolloHandler;
import com.moonsworth.lunar.client.network.apollo.GlowApolloHandler;
import com.moonsworth.lunar.client.network.apollo.ServerRuleApolloHandler;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.account.BadgeManager;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager.Data;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemGlint;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemGlint.GlintTarget;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.render.nametag.Nametag;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.cosmetics.DummyPlayer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.collection.CollectionUtils;
import java.nio.FloatBuffer;
import java.util.LinkedList;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.StringUtils;
import org.lwjgl.actually3.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RendererLivingEntity.class)
public abstract class RendererLivingEntityMixin extends Render implements RendererLivingEntityLayerBridge {
   @Unique
   private static final FloatBuffer BUF_FLOAT_4 = BufferUtils.createFloatBuffer(4);
   @Shadow
   public ModelBase renderPassModel;
   @Unique
   private static final int NAMETAG_RANGE = 64;
   @Unique
   private static final float NAMETAG_RANGE_SNEAK = 32.0F;
   @Unique
   private boolean lunar$renderOutlines = false;
   private static Entity model_e;
   private static float model_v;
   private static float model_v1;
   private static float model_v2;
   private static float model_v3;
   private static float model_v4;
   private static float model_v5;

   public RendererLivingEntityMixin() {
   }

   @Shadow
   public abstract boolean canRenderName(EntityLivingBase entity1);

   @Shadow
   public abstract int shouldRenderPass(EntityLivingBase entity1, int number2, float value3);

   @Shadow
   public abstract void func_82408_c(EntityLivingBase entity1, int number2, float value3);

   @WrapMethod(method = "passSpecialRender")
   public void proxy$passSpecialRender(EntityLivingBase entity1, double value2, double value4, double value6, Operation<Void> operation8) {
      Component component9 = ((EntityLivingBridge)entity1).bridge$getDisplayNameComponent();
      List list10 = (List)CollectionUtils.method1(LinkedList::new, arg1x -> arg1x.add(component9));
      EventRenderNameTag highlightimpl1111 = (EventRenderNameTag)LunarEventBus.method29()
         .method12(EventRenderNameTag.class, () -> new EventRenderNameTag((EntityLivingBridge)entity1, value2, value4, value6, list10, component9));
      if ((highlightimpl1111 == null || !highlightimpl1111.isCancelled()) && (this.canRenderName(entity1) || this.lunar$canShowInThirdPerson(entity1))) {
         int index12 = 0;

         for (Component component14 : highlightimpl1111 == null ? list10 : highlightimpl1111.getLines()) {
            if (component14 instanceof TextComponent text15) {
               double value16 = highlightimpl1111 == null ? value4 : highlightimpl1111.getY() + index12 / 3.5F;
               if (entity1.isChild()) {
                  if (entity1 instanceof EntityAgeable) {
                     float value18 = ((EntityAgeable)entity1).getGrowingAge();
                     float value19 = value18 >= 0.0F ? 1.0F : 0.5F + (-24000.0F - value18 / -24000.0F * 0.5F);
                     value16 -= entity1.height / (2.0F * value19);
                  } else {
                     value16 -= entity1.height / 2.0F;
                  }
               }

               if (entity1.isSneaking()) {
                  Float value20 = Ref.method4()
                     .method84()
                     .method3(ServerRuleModule.class)
                     .filter(arg0 -> (Boolean)arg0.getOptions().get(ServerRuleModule.OVERRIDE_NAMETAG_RENDER_DISTANCE))
                     .map(arg0 -> ((ServerRuleApolloHandler)arg0).method3() / 2.0F)
                     .orElse(32.0F);
                  this.impl$renderLabelSneaking(entity1, text15, value2, value16 + 0.225F, value6, value20);
               } else {
                  double value21 = highlightimpl1111 == null
                     ? this.impl$renderOffsetLivingLabel(entity1, text15, value2, value16, value6)
                     : this.impl$renderOffsetLivingLabel(entity1, text15, highlightimpl1111.getX(), value16, highlightimpl1111.getZ());
                  if (value21 != value16) {
                     index12++;
                  }
               }

               index12++;
            }
         }
      }
   }

   @Unique
   private boolean lunar$canShowInThirdPerson(EntityLivingBase entity1) {
      return Ref.method4().method40().method51().method4((BridgeExtension)entity1);
   }

   private void impl$renderLabelSneaking(EntityLivingBase entity1, TextComponent text2, double value3, double value5, double value7, float value9) {
      GL11.glAlphaFunc(516, 0.1F);
      if (this.canRenderName(entity1)) {
         float value10 = 1.6F;
         float value11 = 0.016666668F * value10;
         double value12 = entity1.getDistanceSqToEntity(this.renderManager.livingPlayer);
         if (value12 < value9 * value9) {
            Nametag nametag14 = Ref.method4().method40().method51();
            FontRenderer font15 = Minecraft.getMinecraft().fontRendererObj;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)value3 + 0.0F, (float)value5 + entity1.height + 0.5F, (float)value7);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-value11, -value11, value11);
            GL11.glDisable(2896);
            GL11.glTranslatef(0.0F, 0.25F / value11, 0.0F);
            GL11.glDepthMask(false);
            GL11.glEnable(3042);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            Tessellator tessellator16 = Tessellator.theMinecraft;
            float value17 = 0.0F;
            Data data18 = (Data)Ref.method4().method53().method63().get(entity1.getUniqueID());
            GL11.glDisable(3553);
            tessellator16.startDrawingQuads();
            float value19 = TextBridge.getTextWidth(text2, (Bridge10_2)font15) / 2.0F;
            float value20 = -value19 - 1.0F;
            float value21 = value19 + 1.0F;
            float value22 = value20 + 2.0F;
            tessellator16.setColorRGBA_F(0.0F, 0.0F, 0.0F, nametag14.getBackgroundOpacity());
            tessellator16.addVertex(value20, -1.0F + value17, 0.0);
            tessellator16.addVertex(value20, 8.0F + value17, 0.0);
            tessellator16.addVertex(value21, 8.0F + value17, 0.0);
            tessellator16.addVertex(value21, -1.0F + value17, 0.0);
            tessellator16.draw();
            GL11.glEnable(3553);
            GL11.glDepthMask(true);
            BridgeExtension3_5 bridgeextension3_523 = AbstractRenderContext.method32();
            int number24 = 553648127;
            if (nametag14.isEnabled() && (Boolean)nametag14.getNametagShadow().get()) {
               ((Bridge10_2)font15).bridge$drawShadow(bridgeextension3_523, text2, value22, value17, number24);
               GL11.glTranslatef(0.0F, 0.0F, -0.001F);
            }

            ((Bridge10_2)font15).method6(bridgeextension3_523, text2, value22, value17, number24, false, true);
            this.lunar$renderNametagAdditions(bridgeextension3_523, false, false, data18, value17, value21, value20, 0.15F, LunarRenderTypes.field33);
            GL11.glEnable(2896);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
         }
      }
   }

   private double impl$renderOffsetLivingLabel(EntityLivingBase entity1, TextComponent text2, double value3, double value5, double value7) {
      return entity1.isPlayerSleeping()
         ? this.impl$renderLivingLabel(entity1, text2, value3, value5 - 1.5, value7) + 1.5
         : this.impl$renderLivingLabel(entity1, text2, value3, value5, value7);
   }

   public double impl$renderLivingLabel(Entity entity1, TextComponent text2, double value3, double value5, double value7) {
      int number9 = Ref.method4()
         .method84()
         .method3(ServerRuleModule.class)
         .filter(arg0 -> (Boolean)arg0.getOptions().get(ServerRuleModule.OVERRIDE_NAMETAG_RENDER_DISTANCE))
         .map(arg0 -> ((ServerRuleApolloHandler)arg0).method3())
         .orElse(64);
      if (entity1 instanceof DummyPlayer threadmoduledump5410 && !threadmoduledump5410.shouldRenderNametag()) {
         return 0.0;
      } else {
         BridgeExtension3_5 bridgeextension3_530 = AbstractRenderContext.method32();
         boolean flag11 = false;
         EntityLivingBase entity12 = this.renderManager.livingPlayer;
         double value13 = entity12 == null ? Double.MAX_VALUE : entity1.getDistanceSqToEntity(entity12);
         if (entity1 instanceof AbstractClientPlayer) {
            String text15 = StringUtils.stripControlCodes(((AbstractClientPlayer)entity1).getGameProfile().getName());
            if (!text15.isEmpty()) {
               flag11 = TextBridge.doesComponentContain(text2, new String[]{text15});
               if (flag11 && value13 < 100.0) {
                  Scoreboard scoreboard16 = ((AbstractClientPlayer)entity1).getWorldScoreboard();
                  ScoreObjective scoreobjective17 = scoreboard16.getObjectiveInDisplaySlot(2);
                  if (scoreobjective17 != null) {
                     Score score18 = scoreboard16.getValueFromObjective(entity1.getCommandSenderName(), scoreobjective17);
                     this.impl$renderLivingLabel(entity1, Component.text(score18.getScorePoints() + " " + scoreobjective17.getDisplayName()), value3, value5, value7);
                     value5 += this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * 0.02666667F;
                  }
               }
            }
         }

         if (value13 <= number9 * number9 || HologramsIterator2.field7) {
            Nametag nametag31 = Ref.method4().method40().method51();
            FontRenderer font32 = Minecraft.getMinecraft().fontRendererObj;
            float value33 = 1.6F;
            float value34 = 0.016666668F * value33;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)value3 + 0.0F, (float)value5 + entity1.height + 0.5F, (float)value7);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            if (HologramsIterator2.field7) {
               GL11.glRotatef(-HologramsIterator2.playerViewY, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(HologramsIterator2.playerViewX, 1.0F, 0.0F, 0.0F);
            } else {
               GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            }

            GL11.glScalef(-value34, -value34, value34);
            GL11.glDisable(2896);
            GL11.glDepthMask(false);
            GL11.glDisable(2929);
            GL11.glEnable(3042);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            Tessellator tessellator19 = Tessellator.theMinecraft;
            float value20 = 0.0F;
            if (text2.content().equals("deadmau5")) {
               value20 = -10.0F;
            }

            Data data21 = (Data)Ref.method4().method53().method63().get(entity1.getUniqueID());
            boolean flag22 = entity1 instanceof EntityPlayer && flag11 && Ref.method4().method53().method63().containsKey(entity1.getUniqueID());
            boolean flag23 = flag22 && data21 != null && (data21.method8() || (Boolean)Ref.method4().method41().method6().method43().get());
            boolean flag24 = flag22
               && data21 != null
               && data21.method11() != null
               && (data21.method8() || (Boolean)Ref.method4().method41().method6().method44().get());
            GL11.glDisable(3553);
            tessellator19.startDrawingQuads();
            float value25 = TextBridge.getTextWidth(text2, (Bridge10_2)font32) / 2.0F;
            float value26 = -value25 - 1.0F;
            float value27 = value25 + 1.0F;
            if (flag24) {
               value26 -= 5.0F;
               value27 += 5.0F;
            }

            float value28 = value26 + 2.0F;
            if (flag23) {
               value26 -= 6.0F;
               value27 += 6.0F;
               value28 = value26 + 13.0F;
            }

            tessellator19.setColorRGBA_F(0.0F, 0.0F, 0.0F, nametag31.getBackgroundOpacity());
            tessellator19.addVertex(value26, -1.0F + value20, 0.0);
            tessellator19.addVertex(value26, 8.0F + value20, 0.0);
            tessellator19.addVertex(value27, 8.0F + value20, 0.0);
            tessellator19.addVertex(value27, -1.0F + value20, 0.0);
            tessellator19.draw();
            GL11.glEnable(3553);
            int number29 = 553648127;
            if (nametag31.isEnabled() && (Boolean)nametag31.getNametagShadow().get()) {
               ((Bridge10_2)font32).bridge$drawShadow(bridgeextension3_530, text2, value28, value20, number29);
               GL11.glTranslatef(0.0F, 0.0F, -0.001F);
            }

            ((Bridge10_2)font32).method6(bridgeextension3_530, text2, value28, value20, number29, false, true);
            this.lunar$renderNametagAdditions(bridgeextension3_530, flag23, flag24, data21, value20, value27, value26, 0.15F, LunarRenderTypes.field33);
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            this.lunar$renderNametagAdditions(bridgeextension3_530, flag23, flag24, data21, value20, value27, value26, 1.0F, LunarRenderTypes.field34);
            if (nametag31.isEnabled() && (Boolean)nametag31.getNametagShadow().get()) {
               ((Bridge10_2)font32).bridge$drawShadow(bridgeextension3_530, text2, value28, value20, -1);
               GL11.glTranslatef(0.0F, 0.0F, -0.001F);
            }

            ((Bridge10_2)font32).method6(bridgeextension3_530, text2, value28, value20, -1, false, true);
            GL11.glEnable(2896);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
         }

         return value5;
      }
   }

   @Unique
   private void lunar$renderNametagAdditions(
      AbstractRenderContext bridgeextension_91, boolean flag2, boolean flag3, Data data4, float value5, float value6, float value7, float value8, RenderTypeLookup mixinhelper6_39
   ) {
      if (flag2) {
         bridgeextension_91.push();
         LcuiScreen.method41(
            mixinhelper6_39.get(CosmeticManager.field40),
            bridgeextension_91,
            value7 + 1.0F,
            value5 - 1.5F,
            (float)LcuiScreen.z,
            0.0F,
            0.0F,
            10.0F,
            10.0F,
            10.0F,
            10.0F,
            ColorUtils.method11(data4.method5(), data4.method6(), data4.method7(), value8)
         );
         if (data4.method1()) {
            GL11.glTranslatef(0.0F, 0.0F, -1.0F);
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
         bridgeextension_91.method25(1.0F, 1.0F, 1.0F, value8);
         BadgeManager.method9(bridgeextension_91, data4.method11(), value6 - 10.0F, value5 - 0.5F, 8.0F, 8.0F, ColorUtils.method18(-1, value8), mixinhelper6_39);
      }
   }

   @Inject(method = "canRenderName", at = @At("HEAD"), cancellable = true)
   private void impl$canRenderNameDummy(EntityLivingBase entity1, CallbackInfoReturnable<Boolean> callbackinforeturnable2) {
      if (entity1 instanceof DummyPlayer threadmoduledump543) {
         callbackinforeturnable2.setReturnValue(threadmoduledump543.shouldRenderNametag());
      }
   }

   @Inject(method = "canRenderName", at = @At("HEAD"), cancellable = true)
   private void apollo$hiddenNametagOverride(EntityLivingBase entity1, CallbackInfoReturnable<Boolean> callbackinforeturnable2) {
      if (!(entity1 instanceof DummyPlayer)) {
         if (entity1 != Ref.method7()) {
            if (Ref.method4().method40().method51().isEnabled()) {
               Ref.method4().method84().method3(NametagModule.class).ifPresent(arg2x -> {
                  NametagApolloHandler highlight3iterator173 = (NametagApolloHandler)arg2x;
                  if (highlight3iterator173.method6(((BridgeExtension)entity1).bridge$getUniqueID())) {
                     callbackinforeturnable2.setReturnValue(false);
                  }
               });
            }
         }
      }
   }

   @Redirect(
      method = "canRenderName",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/entity/RenderManager;livingPlayer:Lnet/minecraft/entity/EntityLivingBase;")
   )
   public EntityLivingBase impl$canRenderName(RenderManager rendermanager1, EntityLivingBase entity2) {
      if (entity2 == Minecraft.getMinecraft().thePlayer && !(entity2 instanceof DummyPlayer)) {
         Nametag nametag3 = Client.method109().method40().method51();
         boolean flag4 = nametag3.isEnabled() && (Boolean)nametag3.getNametag().get()
            || Ref.method4().method40().method85().method17(arg0 -> arg0.method45().method19() || !arg0.method45().method15().isFixedToPlayer());
         return flag4 ? null : entity2;
      } else {
         return null;
      }
   }

   @Redirect(method = "doRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
   public void doRender(ModelBase modelbase1, Entity entity2, float value3, float value4, float value5, float value6, float value7, float value8) {
      model_e = entity2;
      model_v = value3;
      model_v1 = value4;
      model_v2 = value5;
      model_v3 = value6;
      model_v4 = value7;
      model_v5 = value8;
      modelbase1.render(entity2, value3, value4, value5, value6, value7, value8);
   }

   @ModifyConstant(method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V", constant = @Constant(intValue = 15, ordinal = 0))
   public int impl$eventRenderGlint(int number1, EntityLivingBase entity2) {
      EventRenderItemGlint highlightimpl33 = (EventRenderItemGlint)LunarEventBus.method29()
         .method12(
            EventRenderItemGlint.class,
            () -> new EventRenderItemGlint(
               GlintTarget.EQUIPPED_ARMOR,
               arg1xx -> this.renderPassModel.render(model_e, model_v, model_v1, model_v2, model_v3, model_v4, model_v5),
               null,
               (BridgeExtension)entity2,
               null,
               AbstractRenderContext.method32()
            )
         );
      return highlightimpl33 != null && highlightimpl33.isCancelled() ? 0 : number1;
   }

   @Inject(method = "renderArrowsStuckInEntity", at = @At("HEAD"), cancellable = true)
   public void impl$onRenderArrowsStuckInEntity(EntityLivingBase entity1, float value2, CallbackInfo callback3) {
      if (!Ref.method4().method40().method84().method45()) {
         callback3.cancel();
      }
   }

   @Inject(
      method = "rotateCorpse",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/EnumChatFormatting;getTextWithoutFormattingCodes(Ljava/lang/String;)Ljava/lang/String;"),
      cancellable = true
   )
   public void apollo$applyRotations(EntityLivingBase entity1, float value2, float value3, float value4, CallbackInfo callback5) {
      Ref.method4().method84().method3(EntityModule.class).ifPresent(arg2x -> {
         EntityApolloHandler highlight3iterator123x = (EntityApolloHandler)arg2x;
         if (highlight3iterator123x.method7(entity1.getEntityId())) {
            GL11.glTranslatef(0.0F, entity1.height + 0.1F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            callback5.cancel();
         }
      });
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
         ScorePlayerTeam scoreplayerteam3 = (ScorePlayerTeam)entity1.getTeam();
         if (scoreplayerteam3 != null) {
            String text4 = FontRenderer.getFormatFromString(scoreplayerteam3.getColorPrefix());
            if (text4.length() >= 2) {
               char character5 = text4.charAt(1);
               int index6 = "0123456789abcdefklmnor".indexOf(character5);
               number2 = this.getFontRendererFromRenderManager().colorCode[index6];
            }
         }
      }

      RewindHandlers rewindhandlers7 = Ref.method4().method40().method85().method35();
      if (rewindhandlers7 != null && rewindhandlers7.method50().method14() == entity1) {
         number2 = rewindhandlers7.method50().getHighlightColor();
      }

      float value8 = (number2 >> 16 & 0xFF) / 255.0F;
      float value9 = (number2 >> 8 & 0xFF) / 255.0F;
      float value10 = (number2 & 0xFF) / 255.0F;
      GL11.glDisable(2896);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
      GL11.glColor4f(value8, value9, value10, 1.0F);
      GL11.glDisable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glDisable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
      return number2;
   }

   @Unique
   private int lunar$getTeamColor(EntityLivingBase entity1) {
      int number2 = 16777215;
      ScorePlayerTeam scoreplayerteam3 = (ScorePlayerTeam)entity1.getTeam();
      if (scoreplayerteam3 != null) {
         String text4 = FontRenderer.getFormatFromString(scoreplayerteam3.getColorPrefix());
         if (text4.length() >= 2) {
            int index5 = "0123456789abcdef".indexOf(text4.charAt(1));
            number2 = this.getFontRendererFromRenderManager().colorCode[index5];
         }
      }

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

   @WrapOperation(
      method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/RendererLivingEntity;renderModel(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V"
      )
   )
   private void lunar$doOutlinePass(
      RendererLivingEntity rendererlivingentity1, EntityLivingBase entity2, float value3, float value4, float value5, float value6, float value7, float value8, Operation<Void> operation9
   ) {
      int number10 = -1;
      if (this.lunar$renderOutlines) {
         number10 = Ref.method4()
            .method84()
            .method3(GlowModule.class)
            .filter(arg1x -> ((GlowApolloHandler)arg1x).method3((BridgeExtension)entity2))
            .map(arg1x -> ((GlowApolloHandler)arg1x).method4((BridgeExtension)entity2))
            .map(arg2x -> arg2x == Integer.MIN_VALUE ? this.lunar$getTeamColor(entity2) : arg2x)
            .orElseGet(() -> this.lunar$setScoreTeamColor(entity2));
         RewindHandlers rewindhandlers11 = Ref.method4().method40().method85().method35();
         if (rewindhandlers11 != null && rewindhandlers11.method50().method14() == entity2) {
            number10 = rewindhandlers11.method50().getHighlightColor();
         }

         GL11.glEnable(2903);
         lunar$enableOutlineMode(number10);
      }

      operation9.call(new Object[]{rendererlivingentity1, entity2, value3, value4, value5, value6, value7, value8});
      if (this.lunar$renderOutlines) {
         for (int index14 = 0; index14 < 4; index14++) {
            int number12 = this.shouldRenderPass(entity2, index14, value8);
            if (number12 > 0 && this.renderPassModel != null) {
               this.renderPassModel.setLivingAnimations(entity2, value3, value4, value8);
               this.renderPassModel.render(entity2, value3, value4, value5, value6, value7, value8);
               if ((number12 & 240) == 16) {
                  this.func_82408_c(entity2, index14, value8);
                  this.renderPassModel.render(entity2, value3, value4, value5, value6, value7, value8);
               }

               GL11.glDisable(3042);
               GL11.glEnable(3008);
            }
         }
      }
   }

   @Inject(
      method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/OpenGlHelper;setActiveTexture(I)V", ordinal = 2),
      cancellable = true
   )
   private void lunar$cancelRenderingEntity(EntityLivingBase entity1, double value2, double value4, double value6, float value8, float value9, CallbackInfo callback10) {
      if (this.lunar$renderOutlines) {
         this.lunar$unsetScoreTeamColor();
         lunar$disableOutlineMode();
         GL11.glDisable(2903);
         GL11.glDepthMask(true);
         GL11.glDisable(32826);
         OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
         GL11.glEnable(3553);
         OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
         GL11.glEnable(2884);
         GL11.glPopMatrix();
         callback10.cancel();
      }
   }
}
