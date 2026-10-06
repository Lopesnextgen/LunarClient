package com.moonsworth.lunar.legacy.mixin;

import com.lunarclient.apollo.module.limb.BodyPart;
import com.lunarclient.apollo.module.limb.LimbModule;
import com.moonsworth.lunar.bridge.Bridge2_46;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Bridge_53;
import com.moonsworth.lunar.bridge.MixinHelper_6;
import com.moonsworth.lunar.client.network.apollo.LimbApolloHandler;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.framework.feature.pkg.Pkg3;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.render.EventPlayerModelRender;
import com.moonsworth.lunar.client.event.render.EventPlayerPreRender;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.MixinCore2;
import com.moonsworth.lunar.legacy.ModelBipedImpl;
import javax.vecmath.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.RenderPlayer_v1_7;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(max = 0)
@Mixin(RenderPlayer_v1_7.class)
public abstract class RenderPlayer_v1_7Mixin3 extends RendererLivingEntity implements MixinHelper_6 {
   @Shadow
   public ModelBiped modelBipedMain;
   @Unique
   private float lunar$savedPartialTicks;
   @Unique
   private ModelBipedImpl lunar$alexModel;
   @Unique
   private ModelBipedImpl lunar$steveModel;
   @Unique
   private ModelBiped lunar$original;

   @Inject(method = "<init>", at = @At("TAIL"))
   public void lunar$setupEnhancedPlayerModels(CallbackInfo var1) {
      this.lunar$steveModel = new ModelBipedImpl(0.0F, true, false);
      this.lunar$alexModel = new ModelBipedImpl(0.0F, true, true);
      this.lunar$original = this.modelBipedMain;
   }

   public void renderModel(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      Bridge_53 var8 = MixinCore2.field1;
      MixinCore2.field1 = null;
      Matrix4f var9 = var8.getMatrix();
      var8.method3();
      ClientEventBus.method29()
         .method12(EventPlayerModelRender.class, () -> new EventPlayerModelRender((Bridge6_10)var1, var9, this.bridge$getMainModel(), this.lunar$savedPartialTicks));
      var8.method4();
      super.renderModel(var1, var2, var3, var4, var5, var6, var7);
   }

   @Inject(method = "doRender*", at = @At("HEAD"), cancellable = true)
   private void lunar$preRenderPlayerEvent$v1_7(AbstractClientPlayer var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      if (EventPlayerPreRender.method1((Bridge6_10)var1, var2, var4, var6, var9).isCancelled()) {
         var10.cancel();
      } else {
         GL11.glEnable(3042);
         GL14.glBlendEquation(32774);
         OpenGlHelper.glBlendFunc(770, 771, 1, 771);
         if (var1.hasSkin() && this.isModernSkin(var1)) {
            if ("slim".equals(((Bridge5_11)var1).bridge$getSkinType())) {
               this.mainModel = this.modelBipedMain = this.lunar$alexModel;
            } else {
               this.mainModel = this.modelBipedMain = this.lunar$steveModel;
            }
         }
      }

      MixinCore2.field1 = new Bridge_53();
      this.lunar$savedPartialTicks = var9;
   }

   @Inject(method = "renderFirstPersonArm", at = @At("HEAD"), cancellable = true)
   private void lunar$onRenderHand(EntityPlayer var1, CallbackInfo var2) {
      if (var1 instanceof AbstractClientPlayer var3 && var3.hasSkin() && this.isModernSkin(var3)) {
         var2.cancel();
         boolean var4 = "slim".equals(((Bridge5_11)var1).bridge$getSkinType());
         if (var4) {
            this.modelBipedMain = this.lunar$alexModel;
         } else {
            this.modelBipedMain = this.lunar$steveModel;
         }

         if (Skins3d.method13().isEnabled() && Skins3d.method13().method19().get()) {
            ((ModelBipedImpl)this.modelBipedMain).bipedRightArmwear.showModel = false;
         }

         float var5 = 1.0F;
         GL11.glColor3f(var5, var5, var5);
         GL11.glEnable(3042);
         GL11.glDisable(2884);
         GL14.glBlendEquation(32774);
         GL11.glBlendFunc(770, 771);
         this.modelBipedMain.swingProgress = 0.0F;
         this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, var1);
         this.modelBipedMain.bipedRightArm.render(0.0625F);
         ((ModelBipedImpl)this.modelBipedMain).bipedRightArmwear.render(0.0625F);
         Pkg3.method4(
            AbstractRenderContext.method32(),
            0.0625F,
            (Bridge2_46)this.modelBipedMain.bipedRightArm,
            ((ModelBipedImpl)this.modelBipedMain).bipedRightArmwear.showModel,
            (Bridge5_11)var3,
            var4,
            false
         );
         GL11.glEnable(2884);
      }
   }

   @Inject(method = "renderEquippedItems", at = @At("HEAD"), cancellable = true)
   private void apollo$hideHeldItem(AbstractClientPlayer var1, float var2, CallbackInfo var3) {
      ThreadModuleDump63.method4()
         .method84()
         .<ApolloModuleHandler>method3(LimbModule.class)
         .map(var1x -> ((LimbApolloHandler)var1x).method8().get(var1.getUniqueID()))
         .filter(var0 -> var0 != null && var0.contains(BodyPart.RIGHT_ARM))
         .ifPresent(var1x -> var3.cancel());
   }

   @Inject(method = "renderFirstPersonArm", at = @At("TAIL"))
   private void lunar$onRenderHand$tail(EntityPlayer var1, CallbackInfo var2) {
      this.modelBipedMain = this.lunar$original;
   }

   @Override
   public boolean hasModernSkin(EntityPlayerBridge var1) {
      return this.isModernSkin((AbstractClientPlayer)var1);
   }

   private boolean isModernSkin(AbstractClientPlayer var1) {
      ITextureObject var2 = Minecraft.getMinecraft().getTextureManager().getTexture(var1.getLocationSkin());
      if (var2 != null && !(var2 instanceof ThreadDownloadImageData)) {
         return false;
      }

      ThreadDownloadImageData var3 = AbstractClientPlayer.getDownloadImageSkin(var1.getLocationSkin(), var1.getGameProfile().getName());
      return var3.textureUploaded && var3.bufferedImage.getHeight() > 32;
   }

   @Inject(method = "doRender*", at = @At("TAIL"))
   private void lunar$onDoRender$tail(AbstractClientPlayer var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      this.lunar$showAllParts(this.modelBipedMain);
      this.mainModel = this.lunar$original;
      this.modelBipedMain = this.lunar$original;
   }

   @Unique
   private void lunar$showAllParts(ModelBiped var1) {
      var1.bipedHead.showModel = true;
      var1.bipedHeadwear.showModel = true;
      var1.bipedBody.showModel = true;
      var1.bipedRightArm.showModel = true;
      var1.bipedLeftArm.showModel = true;
      var1.bipedRightLeg.showModel = true;
      var1.bipedLeftLeg.showModel = true;
      if (var1 instanceof ModelBipedImpl var2) {
         var2.bipedBodyWear.showModel = true;
         var2.bipedRightArmwear.showModel = true;
         var2.bipedLeftArmwear.showModel = true;
         var2.bipedRightLegwear.showModel = true;
         var2.bipedLeftLegwear.showModel = true;
      }
   }

   @Redirect(
      method = "rotateCorpse(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V",
      at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glRotatef(FFFF)V", remap = false),
      require = 3,
      expect = 3
   )
   private void lunar$onRotatef(float var1, float var2, float var3, float var4) {
      GL11.glRotatef(var1, var2, var3, var4);
      if (MixinCore2.field1 != null) {
         MixinCore2.field1.method6(var1, var2, var3, var4);
      }
   }

   @Redirect(
      method = "preRenderCallback(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
      at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glScalef (FFF)V", remap = false),
      require = 1
   )
   private void lunar$onScalef(float var1, float var2, float var3) {
      GL11.glScalef(var1, var2, var3);
      if (MixinCore2.field1 != null) {
         MixinCore2.field1.scale(var1, var2, var3);
      }
   }

   @Redirect(
      method = {
            "rotateCorpse(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V", "preRenderCallback(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V"
      },
      at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glTranslatef(FFF)V", remap = false),
      require = 0,
      expect = 0
   )
   private void lunar$onTranslatef(float var1, float var2, float var3) {
      GL11.glTranslatef(var1, var2, var3);
      if (MixinCore2.field1 != null) {
         MixinCore2.field1.method7(var1, var2, var3);
      }
   }
}
