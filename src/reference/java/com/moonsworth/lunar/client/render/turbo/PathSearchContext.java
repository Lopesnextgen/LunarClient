package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_5;
import com.moonsworth.lunar.bridge.Matrix3fBridge;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import com.moonsworth.lunar.bridge.RenderSystemBridge.Extension2;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.function.DoubleSupplier;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

@Annotation2(min = 8)
public class PathSearchContext {
   public static final float field1 = 64.0F;

   public static RenderLayerBridge method1(RenderLayerBridge var0) {
      return Fishing.method2(Fishing2Extension.class).map(var1 -> var1.lunar$unwrapRenderType(var0)).orElse(var0);
   }

   public static boolean method2() {
      Fishing2Extension var0 = (Fishing2Extension)Fishing.method2(Fishing2Extension.class).orElse(null);
      return var0 != null && var0.lunar$areShadersEnabledInConfig() && !"(off)".equals(var0.lunar$getShaderPack());
   }

   public static Matrix3fBridge method3(MixinHelper_21 var0) {
      MixinHelper_21 var1 = var0.bridge$copy();
      var1.bridge$invert();
      var1.bridge$transpose();
      Matrix3fBridge var2 = var1.bridge$truncateToMatrix3f();
      var2.bridge$invert();
      return var2;
   }

   public static void method4(double var0, double var2, double var4, @Nullable Vector3iBridge var6, MixinHelper_21 var7) {
      if (var6 == null) {
         var7.bridge$translate((float)(-var0), (float)(-var2), (float)(-var4));
      } else {
         var7.bridge$translate((float)(var6.bridge$getX() - var0), (float)(var6.bridge$getY() - var2), (float)(var6.bridge$getZ() - var4));
      }
   }

   public static void method5(MixinHelper_21 var0, RenderLayerBridge var1, Bridge2_5 var2) {
      var2.bridge$bind();
      var2.bridge$drawWithoutFog(var0, var1);
      var2.bridge$unbind();
   }

   public static void method6(
      RenderLayerBridge var0, @Nullable Vec3Bridge var1, @Nullable Vector3iBridge var2, Bridge2_5 var3, DoubleSupplier var4, long var5, boolean var7, boolean var8
   ) {
      if (var1 != null && var3 instanceof MatrixBuffer var9 && (var7 || !var8 || method10(var4.getAsDouble(), var5))) {
         if (var2 == null) {
            var9.method1(var0, new Vector3f((float)var1.bridge$xCoord(), (float)var1.bridge$yCoord(), (float)var1.bridge$zCoord()));
         } else {
            var9.method1(
               var0,
               new Vector3f(
                  (float)(var1.bridge$xCoord() - var2.bridge$getX()),
                  (float)(var1.bridge$yCoord() - var2.bridge$getY()),
                  (float)(var1.bridge$zCoord() - var2.bridge$getZ())
               )
            );
         }
      }
   }

   public static boolean method7(RenderSystemBridge var0) {
      return var0.method66() <= 64.0F;
   }

   public static int method8(RenderSystemBridge var0) {
      float var1 = var0.method67()[3];
      int var2 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getEffectiveRenderDistance();
      return var1 != 1.0F ? var2 : (int)Math.floor(Math.min(var2 * 16.0F, var0.method65() + 0.5F) / 16.0F);
   }

   public static float method9(RenderSystemBridge var0) {
      return var0.method64() + 1.0F;
   }

   private static boolean method10(double var0, long var2) {
      if (var0 < 256.0) {
         return true;
      } else if (var0 < 512.0) {
         return var2 % 4L != 0L;
      } else if (var0 < 1024.0) {
         return var2 % 3L != 0L;
      } else if (var0 < 2048.0) {
         return var2 % 2L == 0L;
      } else if (var0 < 4096.0) {
         return var2 % 3L == 0L;
      } else {
         return var0 < 8192.0 ? var2 % 4L == 0L : var2 % 5L == 0L;
      }
   }

   public static void method11(@Nullable Matrix3fBridge var0, MixinHelper_21 var1, RenderLayerBridge var2, Bridge2_5 var3) {
      Extension2 var4 = Bridge.method42().method83();
      Vector3f[] var5 = var4.method16();
      Vector3f[] var6 = new Vector3f[]{new Vector3f(var5[0]), new Vector3f(var5[1])};
      Vector3f[] var7 = new Vector3f[2];

      for (int var8 = 0; var8 < 2; var8++) {
         Vector3f var9 = var6[var8];
         var7[var8] = new Vector3f(
            var0.bridge$getTransformX(var9.x, var9.y, var9.z),
            var0.bridge$getTransformY(var9.x, var9.y, var9.z),
            var0.bridge$getTransformZ(var9.x, var9.y, var9.z)
         );
      }

      var4.method14(var7[0], var7[1]);
      method5(var1, var2, var3);
      var4.method14(var6[0], var6[1]);
   }
}
