package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.bridge.FluentTessellatorBridge;
import com.moonsworth.lunar.bridge.LegacyVertexFormat;
import com.moonsworth.lunar.bridge.DrawMode;
import com.moonsworth.lunar.bridge.VertexFormatBridge;
import com.moonsworth.lunar.bridge.Matrix3fBridge;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import com.moonsworth.lunar.client.framework.Client;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Tessellator.class)
public abstract class TessellatorMixin implements FluentTessellatorBridge {
   @Shadow
   public boolean isDrawing;
   private float posX;
   private float posY;
   private float posZ;
   private boolean hasUV = false;
   private float texU;
   private float texV;
   private boolean hasColor = false;
   private float colorR = 1.0F;
   private float colorG = 1.0F;
   private float colorB = 1.0F;
   private float colorA = 1.0F;

   public TessellatorMixin() {
   }

   @Shadow
   public abstract void startDrawing(int number1);

   @Shadow
   public abstract int draw();

   @Shadow
   public abstract void addVertex(double value1, double value3, double value5);

   @Shadow
   public abstract void addVertexWithUV(double value1, double value3, double value5, double value7, double value9);

   @Shadow
   public abstract void setColorRGBA_F(float value1, float value2, float value3, float value4);

   @Shadow
   public abstract void setNormal(float value1, float value2, float value3);

   @Shadow
   public abstract void setColorRGBA(int number1, int number2, int number3, int number4);

   @Shadow
   public abstract void setTranslation(double value1, double value3, double value5);

   public FluentTessellatorBridge bridge$pos(float value1, float value2, float value3) {
      this.posX = value1;
      this.posY = value2;
      this.posZ = value3;
      return this;
   }

   public FluentTessellatorBridge bridge$pos(MixinHelper_21 mixinhelper_211, float value2, float value3, float value4) {
      throw new RuntimeException("Tessellator pos with matrix's are unsupported in legacy!");
   }

   public FluentTessellatorBridge bridge$normal(float value1, float value2, float value3) {
      this.setNormal(value1, value2, value3);
      return this;
   }

   public FluentTessellatorBridge bridge$normal(Matrix3fBridge mixinhelper2_61, float value2, float value3, float value4) {
      throw new RuntimeException("Tessellator normal with matrix's are unsupported in legacy!");
   }

   public FluentTessellatorBridge bridge$color(float value1, float value2, float value3, float value4) {
      if (!this.hasColor) {
         throw new IllegalStateException("TessellatorBridge#bridge$color with hasColor = false");
      }

      this.colorR = value1;
      this.colorG = value2;
      this.colorB = value3;
      this.colorA = value4;
      return this;
   }

   public FluentTessellatorBridge bridge$uv(float value1, float value2) {
      if (!this.hasUV) {
         throw new IllegalStateException("TessellatorBridge#bridge$uv with hasUV = false");
      }

      this.texU = value1;
      this.texV = value2;
      return this;
   }

   public FluentTessellatorBridge bridge$lightmap(int number1) {
      ((Tessellator)this).setBrightness(number1);
      return this;
   }

   public FluentTessellatorBridge bridge$endVertex() {
      if (this.hasColor) {
         this.setColorRGBA_F(this.colorR, this.colorG, this.colorB, this.colorA);
      }

      if (this.hasUV) {
         this.addVertexWithUV(this.posX, this.posY, this.posZ, this.texU, this.texV);
      } else {
         this.addVertex(this.posX, this.posY, this.posZ);
      }

      this.posX = this.posY = this.posZ = 0.0F;
      this.colorR = this.colorG = this.colorB = this.colorA = 1.0F;
      this.texU = this.texV = 0.0F;
      return this;
   }

   public void bridge$end() {
      this.draw();
   }

   public boolean bridge$isDrawing() {
      return this.isDrawing;
   }

   public void bridge$begin(DrawMode bridgetype_61, VertexFormatBridge bridge_632) {
      if (bridge_632 instanceof LegacyVertexFormat bridgetype2_63) {
         this.startDrawing(bridgetype_61.getGLMode(false));
         this.hasUV = bridgetype2_63.isHasUV();
         this.hasColor = bridgetype2_63.isHasColor();
      } else {
         throw new RuntimeException("Illegal VertexFormatBridge used in 1.7 - " + bridge_632.getClass().getSimpleName());
      }
   }

   public void bridge$setTranslation(double value1, double value3, double value5) {
      this.setTranslation(value1, value3, value5);
   }

   @Overwrite
   public void setColorOpaque(int number1, int number2, int number3) {
      this.setColorRGBA(
         number1,
         number2,
         number3,
         Client.method109() != null
               && Client.method109().method44() != null
               && Client.method109().method44().method10().method16()
               && Client.method109().method44().method10().isEnabled()
            ? (Integer)Client.method109().method44().method10().method15().get()
            : 255
      );
   }
}
