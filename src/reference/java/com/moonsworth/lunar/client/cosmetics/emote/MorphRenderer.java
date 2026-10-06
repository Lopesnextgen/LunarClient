package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.bridge.CameraBridge;
import com.moonsworth.lunar.bridge.RenderBlocksBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.render.particle.BedrockScheme;
import com.moonsworth.lunar.client.render.particle.HologramParticleEmitter;
import com.moonsworth.lunar.client.framework.Ref;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;
import mchorse.emoticons.morph.Morph;

public interface MorphRenderer {
   String getBone();

   MorphTransform method2();

   void method3();

   void method4(Vector4f vector4f1, Matrix3f matrix3f2);

   void reset(boolean flag1);

   void method5(BridgeExtension bridgeextension1);

   BridgeExtension method6();

   void method7(AbstractRenderContext bridgeextension_91, CameraBridge bridge2_192);

   default void method8(AbstractRenderContext bridgeextension_91, RenderBlocksBridge bridge6_22, CameraBridge bridge2_193) {
      this.method7(bridgeextension_91, bridge2_193);
   }

   void method9();

   boolean method12();

   MorphRenderType method11();

   static MorphRenderer method12(Morph morph0, EntityLivingBridge bridgeextension2_51) {
      String text2 = morph0.getMorphNbt().bridge$getString("Name");
      if ("snowstorm".equals(text2)) {
         String text9 = morph0.getMorphNbt().bridge$getString("Scheme");
         if (text9 != null && !text9.isEmpty()) {
            BedrockScheme glintcolorizer3_25 = (BedrockScheme)Ref.method4().method70().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(text9);
            HologramParticleEmitter glintcolorizer5iterator4 = new HologramParticleEmitter();
            float value6 = bridgeextension2_51.bridge$getScale();
            glintcolorizer5iterator4.method3(glintcolorizer3_25);
            glintcolorizer5iterator4.bone = morph0.getBone();
            glintcolorizer5iterator4.field38 = new MorphTransform(morph0);
            glintcolorizer5iterator4.running = true;
            glintcolorizer5iterator4.method5(bridgeextension2_51);
            glintcolorizer5iterator4.setScale(value6);
            glintcolorizer5iterator4.field8.set(bridgeextension2_51.bridge$getPosX(), bridgeextension2_51.bridge$getPosY(), bridgeextension2_51.bridge$getPosZ());
            Matrix4f matrix4f7 = new Matrix4f();
            matrix4f7.setIdentity();
            matrix4f7.rotY((float)Math.toRadians(180.0F - (bridgeextension2_51.bridge$getBodyRot() - 180.0F)));
            Matrix3f matrix3f8 = new Matrix3f();
            matrix3f8.m00 = matrix4f7.m00;
            matrix3f8.m01 = matrix4f7.m01;
            matrix3f8.m02 = matrix4f7.m02;
            matrix3f8.m10 = matrix4f7.m10;
            matrix3f8.m11 = matrix4f7.m11;
            matrix3f8.m12 = matrix4f7.m12;
            matrix3f8.m20 = matrix4f7.m20;
            matrix3f8.m21 = matrix4f7.m21;
            matrix3f8.m22 = matrix4f7.m22;
            glintcolorizer5iterator4.field10.set(matrix3f8);
            return glintcolorizer5iterator4;
         } else {
            throw new IllegalArgumentException("configuration.json is corrupt; snowstorm morph " + morph0 + " is missing a Scheme");
         }
      } else if ("block".equals(text2)) {
         BlockMorphRenderer holograms8handler3 = new BlockMorphRenderer(morph0);
         holograms8handler3.method5(bridgeextension2_51);
         return holograms8handler3;
      } else {
         throw new IllegalArgumentException("Unknown morph type " + text2);
      }
   }

   default double method13(CameraBridge bridge2_191) {
      Vec3iBridge horsestats202 = this.method14();
      double value3 = horsestats202.bridge$getX() - bridge2_191.bridge$getPosX();
      double value5 = horsestats202.bridge$getY() - bridge2_191.bridge$getPosY();
      double value7 = horsestats202.bridge$getZ() - bridge2_191.bridge$getPosZ();
      return value3 * value3 + value5 * value5 + value7 * value7;
   }

   Vec3iBridge method14();
}
