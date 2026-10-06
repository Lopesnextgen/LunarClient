package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2$Data;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_19;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.render.particle.BedrockScheme;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.HologramSorting;
import com.moonsworth.lunar.client.render.texture.GifTexture;
import com.moonsworth.lunar.client.cosmetics.emote.MorphTransform;
import com.moonsworth.lunar.client.cosmetics.emote.MorphRenderer;
import com.moonsworth.lunar.client.cosmetics.emote.MorphRenderType;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector4f;
import lombok.Generated;
import com.moonsworth.lunar.client.render.particle.NameplateGlintRenderer;

public class HologramParticleEmitter extends BedrockEmitter implements MorphRenderer {
   private Vector3iBridge.Extension field32;
   public int perspective;
   public float field33;
   public float field34;
   public double field35;
   public double field36;
   public double field37;
   public MorphTransform field38 = MorphTransform.field2;
   public String bone;
   private boolean field39;

   @Override
   public double method13(Bridge2_19 var1) {
      double var2 = var1.bridge$getPosX() - this.field8.x;
      double var4 = var1.bridge$getPosY() - this.field8.y;
      double var6 = var1.bridge$getPosZ() - this.field8.z;
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   public double method2(BedrockParticle var1) {
      Vector3d var2 = var1.method2(this);
      double var3 = this.field35 - var2.x;
      double var5 = this.field36 - var2.y;
      double var7 = this.field37 - var2.z;
      return var3 * var3 + var5 * var5 + var7 * var7;
   }

   @Override
   public Vector3iBridge method14() {
      return Bridge.method8().method6(this.field35, this.field36, this.field37);
   }

   @Override
   public void method5(BridgeExtension var1) {
      this.field1 = var1;
      this.method7(var1 == null ? null : var1.bridge$getWorld());
   }

   public void method5(BridgeExtension var1, Itemcounter6Extension var2) {
      this.field1 = var1;
      this.method7(var2);
   }

   @Override
   public BridgeExtension method6() {
      return this.field1;
   }

   public void method7(Itemcounter6 var1) {
      this.field2 = var1;
   }

   @Override
   public void method3(BedrockScheme var1) {
      super.method3(var1);
      this.bone = var1.getBone();
   }

   @Override
   public void method7(AbstractRenderContext var1, Bridge2_19 var2) {
      if (this.field3 != null) {
         float var3 = 1.0F;
         if (this.field1 instanceof BridgeExtension2_5 var4) {
            var3 = var4.bridge$getScale();
         }

         float var12 = var1.method28();
         this.method14(var2);
         List var13 = this.field3.field10;

         for (com.moonsworth.lunar.client.render.particle.IComponentParticleRender var7 : var13) {
            var7.method1(this, var12);
         }

         if (!this.field4.isEmpty()) {
            if (HologramSorting.method1()) {
               this.field4.sort((var1x, var2x) -> {
                  double var3x = this.method2(var1x);
                  double var5 = this.method2(var2x);
                  if (var3x < var5) {
                     return 1;
                  } else {
                     return var3x > var5 ? -1 : 0;
                  }
               });
            }

            GifTexture.method1(this.field3.texture, this.field13, var12);
            RenderLayerBridge var14 = LunarRenderTypes.field39.get(this.field3.texture);
            Bridge2_32 var16 = var1.method10(var14);
            var16.method1();

            for (BedrockParticle var9 : this.field4) {
               this.setScale(var12);
               this.method2(var9, var12);

               for (com.moonsworth.lunar.client.render.particle.IComponentParticleRender var11 : var13) {
                  if (var11 instanceof NameplateGlintRenderer) {
                     ((NameplateGlintRenderer)var11).method1(this, var9, var16, var1, var3, this.field3.texture);
                  }
               }
            }

            var16.method17(BufferBuildMode.BATCHED);
            if (var1.method38() && this.perspective == 100) {
               var1.method30().method33(var14);
            }
         }

         for (com.moonsworth.lunar.client.render.particle.IComponentParticleRender var17 : var13) {
            var17.method2(this, var12);
         }
      }
   }

   @Override
   public void method9() {
      this.running = false;
   }

   @Override
   public boolean method12() {
      return !this.isFinished();
   }

   @Override
   public MorphRenderType method11() {
      return MorphRenderType.PARTICLE;
   }

   private void method14(Bridge2_19 var1) {
      if (Bridge.getMinecraftVersion().method21()) {
         Bridge2_43 var2 = ThreadModuleDump63.method3().bridge$getEntityRenderDispatcher();
         this.field33 = 180.0F - (float)var2.bridge$playerViewY();
         this.field34 = 180.0F - (float)var2.bridge$playerViewX();
      } else {
         this.field33 = 180.0F - var1.bridge$getYaw();
         this.field34 = 180.0F - var1.bridge$getPitch();
      }

      this.field35 = var1.bridge$getPosX();
      this.field36 = var1.bridge$getPosY();
      this.field37 = var1.bridge$getPosZ();
      if (var1 instanceof Bridge2$Data var3 && var3.method1()) {
         this.perspective = 100;
         this.field37 = -0.5;
         this.field33 = 180.0F - var1.bridge$getYaw();
         this.field34 = 180.0F - var1.bridge$getPitch();
      } else {
         this.perspective = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getThirdPersonView();
      }
   }

   @Override
   public int method17(float var1, double var2, double var4, double var6) {
      if (!this.field39 && this.field2 != null && this.perspective != 100) {
         if (this.field32 == null) {
            this.field32 = Bridge.method8().method10(var2, var4, var6);
         } else {
            this.field32.method1(var2, var4, var6);
         }

         return this.field2.bridge$isBlockLoaded(this.field32)
            ? this.field2.bridge$getPackedLight(this.field32)
            : 0;
      } else {
         return Bridge.method8().method92();
      }
   }

   @Override
   public String getBone() {
      return this.bone;
   }

   @Override
   public MorphTransform method2() {
      return this.field38;
   }

   @Override
   public void method3() {
      this.update();

      for (com.moonsworth.lunar.client.render.particle.IComponentParticleRender var2 : this.field3.field10) {
         var2.method2(this, ThreadModuleDump63.method3().bridge$getTimer().method1());
      }
   }

   @Override
   public void method4(Vector4f var1, Matrix3f var2) {
      if (!this.field39) {
         this.field39 = true;
         double var3 = var1.x - this.field8.x;
         double var5 = var1.y - this.field8.y;
         double var7 = var1.z - this.field8.z;
         if (var3 != 0.0 || var5 != 0.0 || var7 != 0.0) {
            for (BedrockParticle var10 : this.field4) {
               if (!var10.field7 || !var10.field8) {
                  var10.field25.x += var3;
                  var10.field25.y += var5;
                  var10.field25.z += var7;
                  var10.field27.x += var3;
                  var10.field27.y += var5;
                  var10.field27.z += var7;
                  var10.field26.x += var3;
                  var10.field26.y += var5;
                  var10.field26.z += var7;
               }
            }
         }

         this.field9.set(var1.x, var1.y, var1.z);
      }

      if (this.field7 != this.field13) {
         this.field11.set(this.field10);
         this.field9.set(this.field8);
      }

      this.field7 = this.field13;
      this.field8.x = var1.x;
      this.field8.y = var1.y;
      this.field8.z = var1.z;
      this.field10 = var2;
   }

   @Override
   public void reset(boolean var1) {
      this.field4.clear();
      if (var1) {
         this.running = false;
      }
   }
}
