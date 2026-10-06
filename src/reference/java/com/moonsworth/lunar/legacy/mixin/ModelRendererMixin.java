package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ModelRendererBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ModelRenderer.class)
public abstract class ModelRendererMixin implements ModelRendererBridge {
   @Shadow
   public float rotateAngleX;
   @Shadow
   public float rotateAngleY;
   @Shadow
   public float rotateAngleZ;
   @Shadow
   public float rotationPointX;
   @Shadow
   public float rotationPointY;
   @Shadow
   public float rotationPointZ;
   @Shadow
   public int textureOffsetX;
   @Shadow
   public int textureOffsetY;
   @Shadow
   public boolean showModel;

   public ModelRendererMixin() {
   }

   @Shadow
   public abstract void render(float value1);

   public void bridge$setTextureOffsetX(int number1) {
      this.textureOffsetX = number1;
   }

   public void bridge$setTextureOffsetY(int number1) {
      this.textureOffsetY = number1;
   }

   public void bridge$setRotateAngleX(float value1) {
      this.rotateAngleX = value1;
   }

   public void bridge$setRotateAngleY(float value1) {
      this.rotateAngleY = value1;
   }

   public void bridge$setRotateAngleZ(float value1) {
      this.rotateAngleZ = value1;
   }

   public float bridge$getRotateAngleX() {
      return this.rotateAngleX;
   }

   public float bridge$getRotateAngleY() {
      return this.rotateAngleY;
   }

   public float bridge$getRotateAngleZ() {
      return this.rotateAngleZ;
   }

   public void bridge$setRotatePointX(float value1) {
      this.rotationPointX = value1;
   }

   public void bridge$setRotatePointY(float value1) {
      this.rotationPointY = value1;
   }

   public void bridge$setRotatePointZ(float value1) {
      this.rotationPointZ = value1;
   }

   public float bridge$getRotatePointX() {
      return this.rotationPointX;
   }

   public float bridge$getRotatePointY() {
      return this.rotationPointY;
   }

   public float bridge$getRotatePointZ() {
      return this.rotationPointZ;
   }

   public boolean bridge$isVisible() {
      return this.showModel;
   }

   public void bridge$setVisible(boolean flag) {
      this.showModel = flag;
   }

   public void bridge$render(float value1, ResourceLocationBridge horsestats142) {
      Ref.method3().bridge$getTextureManager().bridge$bindTexture(horsestats142);
      this.render(value1);
   }

   public void bridge$postRender(float value1) {
      if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F) {
         if (this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F) {
            if (Ref.MC_VERSION >= 1) {
               GlStateManager.translate(this.rotationPointX * value1, this.rotationPointY * value1, this.rotationPointZ * value1);
            } else {
               GL11.glTranslatef(this.rotationPointX * value1, this.rotationPointY * value1, this.rotationPointZ * value1);
            }
         }
      } else {
         if (Ref.MC_VERSION >= 1) {
            GlStateManager.translate(this.rotationPointX * value1, this.rotationPointY * value1, this.rotationPointZ * value1);
         } else {
            GL11.glTranslatef(this.rotationPointX * value1, this.rotationPointY * value1, this.rotationPointZ * value1);
         }

         if (this.rotateAngleZ != 0.0F) {
            lunar$rotatef(this.rotateAngleZ * (180.0F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
         }

         if (this.rotateAngleY != 0.0F) {
            lunar$rotatef(this.rotateAngleY * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
         }

         if (this.rotateAngleX != 0.0F) {
            lunar$rotatef(this.rotateAngleX * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
         }
      }
   }

   @Unique
   private static void lunar$rotatef(float value, float value1, float value2, float value3) {
      if (Ref.MC_VERSION >= 1) {
         GlStateManager.rotate(value, value1, value2, value3);
      } else {
         GL11.glRotatef(value, value1, value2, value3);
      }
   }
}
