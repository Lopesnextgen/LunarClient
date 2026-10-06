package com.moonsworth.lunar.client.framework.feature.pkg.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_15;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge2_46;
import com.moonsworth.lunar.bridge.Bridge3_10;
import com.moonsworth.lunar.bridge.Bridge4_6;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.DrawMode;
import com.moonsworth.lunar.bridge.Bridge_27;
import com.moonsworth.lunar.bridge.Bridge_45;
import com.moonsworth.lunar.bridge.Bridge_63;
import com.moonsworth.lunar.bridge.Bridge_66;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.RenderTypeResolver;
import com.moonsworth.lunar.bridge.MixinHelper7$Data7;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.bridge.slayer.Slayer4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;

public class Pkg3 {
   @Annotation2(max = 5)
   public static RenderTypeResolver field1;
   public float x;
   public float y;
   public float z;
   public float xRot;
   public float yRot;
   public float zRot;
   public boolean visible = true;
   private final List<Pkg2> field2;
   private boolean field3 = false;
   private float field4 = 0.0F;
   private Bridge_66 field5 = null;

   public Pkg3(List<Pkg2> var1) {
      this.field2 = var1;
   }

   public void method1(Bridge2_46 var1) {
      this.xRot = var1.bridge$getRotateAngleX();
      this.yRot = var1.bridge$getRotateAngleY();
      this.zRot = var1.bridge$getRotateAngleZ();
      this.x = var1.bridge$getRotatePointX();
      this.y = var1.bridge$getRotatePointY();
      this.z = var1.bridge$getRotatePointZ();
   }

   public void method2(float var1, float var2, float var3) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
   }

   public void method3(float var1, float var2, float var3) {
      this.xRot = var1;
      this.yRot = var2;
      this.zRot = var3;
   }

   public void method4(Bridge5_16 var1, Bridge4_6 var2, int var3, int var4) {
      this.method5(var1, var2, var3, var4, 1.0F, 1.0F, 1.0F, 1.0F, 0.0625F);
   }

   public void method5(Bridge5_16 var1, Bridge4_6 var2, int var3, int var4, float var5, float var6, float var7, float var8, float var9) {
      if (this.visible) {
         if (!this.field2.isEmpty()) {
            var1.bridge$pushPose();
            this.method9(var1, var9);
            Bridge3_10 var10 = var1.bridge$last();

            for (Pkg2 var12 : this.field2) {
               var12.method6(var10, var2, var3, var4, var5, var6, var7, var8, var9);
            }

            var1.bridge$popPose();
         }
      }
   }

   @Annotation2(max = 5)
   public void method6(BridgeExtension3_5 var1, float var2, ResourceLocationBridge var3) {
      this.method7(var1, var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, var3);
   }

   @Annotation2(max = 5)
   public void method7(BridgeExtension3_5 var1, float var2, float var3, float var4, float var5, float var6, float var7, ResourceLocationBridge var8) {
      if (this.visible) {
         if (!this.field2.isEmpty()) {
            var1.push();
            var1.translate(this.x * var2, this.y * var2, this.z * var2);
            if (this.yRot != 0.0F) {
               var1.method4(this.yRot * (float) (180.0 / Math.PI), 0.0F, 1.0F, 0.0F);
            }

            if (this.xRot != 0.0F) {
               var1.method4(this.xRot * (float) (180.0 / Math.PI), 1.0F, 0.0F, 0.0F);
            }

            if (this.zRot != 0.0F) {
               var1.method4(this.zRot * (float) (180.0 / Math.PI), 0.0F, 0.0F, 1.0F);
            }

            var1.translate(var3 * var2, var4 * var2, 0.0);
            var1.scale(var5, var6, var7);
            boolean var9 = Bridge.method5().map(Slayer2::getConfig).map(Slayer4::hasShaders).orElse(false);
            if (var9 != this.field3 || var2 != this.field4) {
               if (this.field5 != null) {
                  var1.method3(this.field5);
               }

               this.field5 = null;
               this.field3 = var9;
               this.field4 = var2;
            }

            if (this.field5 == null) {
               this.field5 = var1.method1(var2x -> this.method8(var1));
            }

            RenderLayerBridge var10 = field1.get(var8);
            var10.bridge$setupRenderState();
            var1.method2(this.field5);
            var10.bridge$clearRenderState();
            var1.pop();
         }
      }
   }

   @Annotation2(max = 5)
   private void method8(BridgeExtension3_5 var1) {
      Bridge_63 var2;
      if (this.field3) {
         var2 = Bridge.method5().get().getShaders().getEntityModelVertexFormat();
      } else {
         var2 = Bridge_27.field5;
      }

      Bridge2_32 var3 = var1.method10(LunarRenderTypes.field64.apply(var2));
      var3.method1();

      for (Pkg2 var5 : this.field2) {
         var5.method5(var3, this.field4);
      }

      var3.method17(BufferBuildMode.BATCHED);
   }

   public void method9(Bridge5_16 var1, float var2) {
      var1.bridge$translate(this.x * var2, this.y * var2, this.z * var2);
      if (this.zRot != 0.0F) {
         var1.bridge$mulPose(new Quaternionf(new AxisAngle4f(this.zRot, HorsestatsType_2.SOUTH.getUnitVector())));
      }

      if (this.yRot != 0.0F) {
         var1.bridge$mulPose(new Quaternionf(new AxisAngle4f(this.yRot, HorsestatsType_2.UP.getUnitVector())));
      }

      if (this.xRot != 0.0F) {
         var1.bridge$mulPose(new Quaternionf(new AxisAngle4f(this.xRot, HorsestatsType_2.EAST.getUnitVector())));
      }
   }

   static {
      if (ThreadModuleDump63.MC_VERSION <= 5) {
         Bridge_45 var0 = Bridge.method8()
            .method80()
            .method1("none")
            .method7("none")
            .method4("none")
            .method11(Bridge_27.field5, DrawMode.QUADS)
            .method32(Bridge2_15.field13)
            .method44();
         field1 = LunarRenderTypes.method3(
            var1 -> Bridge.method8()
               .method81()
               .method8(MixinHelper7$Data7.method1(var1))
               .method12(true)
               .method14(var0, "lunar_3d_skins", 4096, false, false, true)
         );
      }
   }
}
