package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.CameraFacing;
import com.moonsworth.lunar.client.render.particle.Interpolations;
import com.moonsworth.lunar.client.driver.hologram.HologramRenderer;
import javax.vecmath.Matrix3d;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import com.moonsworth.lunar.client.render.particle.HologramParticleEmitter;

public class NameplateBillboardComponent extends com.moonsworth.lunar.client.render.particle.BedrockComponentAppearanceBillboard implements NameplateGlintRenderer {
   private Matrix4f field22 = new Matrix4f();
   private Matrix4f field23 = new Matrix4f();
   private Vector4f[] field24 = new Vector4f[]{
      new Vector4f(0.0F, 0.0F, 0.0F, 1.0F), new Vector4f(0.0F, 0.0F, 0.0F, 1.0F), new Vector4f(0.0F, 0.0F, 0.0F, 1.0F), new Vector4f(0.0F, 0.0F, 0.0F, 1.0F)
   };
   private Vector3f field25 = new Vector3f();

   public NameplateBillboardComponent() {
   }

   @Override
   public void method1(HologramParticleEmitter glintcolorizer5iterator1, BedrockParticle glintcolorizer4_22, DrawBufferBridge bridge2_323, AbstractRenderContext bridgeextension_94, float value5, ResourceLocationBridge horsestats146) {
      float value7 = bridgeextension_94.method28();
      this.method2(glintcolorizer4_22, value7);
      double value8 = Interpolations.lerp(glintcolorizer4_22.field27.x, glintcolorizer4_22.field25.x, value7);
      double value10 = Interpolations.lerp(glintcolorizer4_22.field27.y, glintcolorizer4_22.field25.y, value7);
      double value12 = Interpolations.lerp(glintcolorizer4_22.field27.z, glintcolorizer4_22.field25.z, value7);
      float value14 = Interpolations.lerp(glintcolorizer4_22.field21, glintcolorizer4_22.rotation, value7);
      Vector3d vector3d15 = this.method3(glintcolorizer5iterator1, glintcolorizer4_22, value8, value10, value12);
      value8 = vector3d15.x;
      value10 = vector3d15.y;
      value12 = vector3d15.z;
      float value16 = glintcolorizer5iterator1.field33;
      float value17 = glintcolorizer5iterator1.field34;
      double value18 = glintcolorizer5iterator1.field35;
      double value20 = glintcolorizer5iterator1.field36;
      double value22 = glintcolorizer5iterator1.field37;
      boolean flag24 = this.HRRRCOHOOHHRCCHIHHIOIICCRHIRHC == CameraFacing.LOOKAT_XYZ
         || this.HRRRCOHOOHHRCCHIHHIOIICCRHIRHC == CameraFacing.LOOKAT_Y;
      if (HologramRenderer.field1 && !flag24) {
         this.w = -this.w;
      }

      if (!HologramRenderer.field1 && flag24) {
         this.w = -this.w;
      }

      int number25 = glintcolorizer5iterator1.method17(value7, value8, value10, value12);
      this.method2(glintcolorizer5iterator1, glintcolorizer4_22);
      this.field22.setIdentity();
      if (this.HRRRCOHOOHHRCCHIHHIOIICCRHIRHC == CameraFacing.ROTATE_XYZ || this.HRRRCOHOOHHRCCHIHHIOIICCRHIRHC == CameraFacing.LOOKAT_XYZ) {
         this.field23.rotY(value16 / 180.0F * (float) Math.PI);
         this.field22.mul(this.field23);
         this.field23.rotX(value17 / 180.0F * (float) Math.PI);
         this.field22.mul(this.field23);
      } else if (this.HRRRCOHOOHHRCCHIHHIOIICCRHIRHC == CameraFacing.ROTATE_Y || this.HRRRCOHOOHHRCCHIHHIOIICCRHIRHC == CameraFacing.LOOKAT_Y) {
         this.field23.rotY(value16 / 180.0F * (float) Math.PI);
         this.field22.mul(this.field23);
      }

      this.field23.rotZ(value14 / 180.0F * (float) Math.PI);
      this.field22.mul(this.field23);
      this.field22.setTranslation(new Vector3f((float)(value8 - value18), (float)(value10 - value20), (float)(value12 - value22)));
      this.field22.setScale(value5);

      for (Vector4f vector4f29 : this.field24) {
         this.field22.transform(vector4f29);
      }

      float value33 = this.RHHROORHRRHOIORICOOIOOROHOOCRH / this.HRRCORICRRCOCCOHRIRORRIHROIRCR;
      float value34 = this.RIHIIHIRIOROCHRHRRCRHRCIIHRIHR / this.HRRCORICRRCOCCOHRIRORRIHROIRCR;
      float value35 = this.IROOOHHHOCOOICRRCHIRORORCRHRRO / this.RRICIRHROHRORROCRRRHCCRRCCOOIO;
      float value36 = this.OCOOIOIIIOHRIRRHIRHCHRIIHCIOIR / this.RRICIRHROHRORROCRRRHCCRRCCOOIO;
      bridge2_323.method2(this.field24[0].x, this.field24[0].y, this.field24[0].z)
         .method10(value33, value35)
         .method8(glintcolorizer4_22.field35, glintcolorizer4_22.field36, glintcolorizer4_22.field37, glintcolorizer4_22.field38)
         .method11(number25)
         .method16();
      bridge2_323.method2(this.field24[1].x, this.field24[1].y, this.field24[1].z)
         .method10(value34, value35)
         .method8(glintcolorizer4_22.field35, glintcolorizer4_22.field36, glintcolorizer4_22.field37, glintcolorizer4_22.field38)
         .method11(number25)
         .method16();
      bridge2_323.method2(this.field24[2].x, this.field24[2].y, this.field24[2].z)
         .method10(value34, value36)
         .method8(glintcolorizer4_22.field35, glintcolorizer4_22.field36, glintcolorizer4_22.field37, glintcolorizer4_22.field38)
         .method11(number25)
         .method16();
      bridge2_323.method2(this.field24[3].x, this.field24[3].y, this.field24[3].z)
         .method10(value33, value36)
         .method8(glintcolorizer4_22.field35, glintcolorizer4_22.field36, glintcolorizer4_22.field37, glintcolorizer4_22.field38)
         .method11(number25)
         .method16();
   }

   protected void method2(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      float value3 = this.w * 0.5F;
      float value4 = this.h * 0.5F;
      if (glintcolorizer4_22.field11) {
         float value5 = glintcolorizer5_21.getScale();
         value3 *= value5;
         value4 *= value5;
      }

      this.field24[0].set(value3, -value4, 0.0F, 1.0F);
      this.field24[1].set(-value3, -value4, 0.0F, 1.0F);
      this.field24[2].set(-value3, value4, 0.0F, 1.0F);
      this.field24[3].set(value3, value4, 0.0F, 1.0F);
   }

   protected Vector3d method3(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22, double value3, double value5, double value7) {
      if (glintcolorizer4_22.field7 && glintcolorizer4_22.field8) {
         this.field25.set((float)value3, (float)value5, (float)value7);
         if (glintcolorizer4_22.field10 && glintcolorizer5_21.getScale() != 1.0F) {
            Vector3d vector3d14 = new Vector3d(value3, value5, value7);
            Matrix3d matrix3d15 = new Matrix3d(glintcolorizer5_21.getScale(), 0.0, 0.0, 0.0, glintcolorizer5_21.getScale(), 0.0, 0.0, 0.0, glintcolorizer5_21.getScale());
            matrix3d15.transform(vector3d14);
            this.field25.x = (float)vector3d14.x;
            this.field25.y = (float)vector3d14.y;
            this.field25.z = (float)vector3d14.z;
         }

         glintcolorizer5_21.field10.transform(this.field25);
         value3 = this.field25.x;
         value5 = this.field25.y;
         value7 = this.field25.z;
         value3 += glintcolorizer5_21.field8.x;
         value5 += glintcolorizer5_21.field8.y;
         value7 += glintcolorizer5_21.field8.z;
      } else if (glintcolorizer4_22.field10 && glintcolorizer5_21.getScale() != 1.0F) {
         Vector3d vector3d9 = new Vector3d(value3, value5, value7);
         Matrix3d matrix3d10 = new Matrix3d(glintcolorizer5_21.getScale(), 0.0, 0.0, 0.0, glintcolorizer5_21.getScale(), 0.0, 0.0, 0.0, glintcolorizer5_21.getScale());
         vector3d9.sub(glintcolorizer5_21.field8);
         matrix3d10.transform(vector3d9);
         vector3d9.add(glintcolorizer5_21.field8);
         value3 = vector3d9.x;
         value5 = vector3d9.y;
         value7 = vector3d9.z;
      }

      return new Vector3d(value3, value5, value7);
   }

   @Override
   public void method2(BedrockParticle glintcolorizer4_21, int number2, int number3, float value4, AbstractRenderContext bridgeextension_95, ResourceLocationBridge horsestats146) {
      float value7 = bridgeextension_95.method28();
      this.method2(glintcolorizer4_21, value7);
      this.w = this.h = 0.5F;
      float value8 = Interpolations.lerp(glintcolorizer4_21.field21, glintcolorizer4_21.rotation, value7);
      this.field24[0].set(-this.w / 2.0F, -this.h / 2.0F, 0.0F, 1.0F);
      this.field24[1].set(this.w / 2.0F, -this.h / 2.0F, 0.0F, 1.0F);
      this.field24[2].set(this.w / 2.0F, this.h / 2.0F, 0.0F, 1.0F);
      this.field24[3].set(-this.w / 2.0F, this.h / 2.0F, 0.0F, 1.0F);
      this.field22.setIdentity();
      this.field22.setScale(value4 * 2.75F);
      this.field22.setTranslation(new Vector3f(number2, number3 - value4 / 2.0F, 0.0F));
      this.field23.rotZ(value8 / 180.0F * (float) Math.PI);
      this.field22.mul(this.field23);

      for (Vector4f vector4f12 : this.field24) {
         this.field22.transform(vector4f12);
      }

      float value14 = this.RHHROORHRRHOIORICOOIOOROHOOCRH / this.HRRCORICRRCOCCOHRIRORRIHROIRCR;
      float value15 = this.RIHIIHIRIOROCHRHRRCRHRCIIHRIHR / this.HRRCORICRRCOCCOHRIRORRIHROIRCR;
      float value16 = this.IROOOHHHOCOOICRRCHIRORORCRHRRO / this.RRICIRHROHRORROCRRRHCCRRCCOOIO;
      float value17 = this.OCOOIOIIIOHRIRRHIRHCHRIIHCIOIR / this.RRICIRHROHRORROCRRRHCCRRCCOOIO;
      DrawBufferBridge bridge2_3213 = bridgeextension_95.method10(LunarRenderTypes.field33.get(horsestats146));
      bridge2_3213.method1();
      bridge2_3213.method2(this.field24[0].x, this.field24[0].y, this.field24[0].z)
         .method10(value14, value16)
         .method8(glintcolorizer4_21.field35, glintcolorizer4_21.field36, glintcolorizer4_21.field37, glintcolorizer4_21.field38)
         .method16();
      bridge2_3213.method2(this.field24[1].x, this.field24[1].y, this.field24[1].z)
         .method10(value15, value16)
         .method8(glintcolorizer4_21.field35, glintcolorizer4_21.field36, glintcolorizer4_21.field37, glintcolorizer4_21.field38)
         .method16();
      bridge2_3213.method2(this.field24[2].x, this.field24[2].y, this.field24[2].z)
         .method10(value15, value17)
         .method8(glintcolorizer4_21.field35, glintcolorizer4_21.field36, glintcolorizer4_21.field37, glintcolorizer4_21.field38)
         .method16();
      bridge2_3213.method2(this.field24[3].x, this.field24[3].y, this.field24[3].z)
         .method10(value14, value17)
         .method8(glintcolorizer4_21.field35, glintcolorizer4_21.field36, glintcolorizer4_21.field37, glintcolorizer4_21.field38)
         .method16();
      bridge2_3213.method17(BufferMode.BATCHED);
   }
}
