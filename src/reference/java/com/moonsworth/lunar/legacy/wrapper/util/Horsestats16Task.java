package com.moonsworth.lunar.legacy.wrapper.util;

import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.TexturedBoxRenderer;
import com.moonsworth.lunar.client.render.texture.NativeImageBuilder;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump92;
import java.nio.FloatBuffer;
import java.util.UUID;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

public class Horsestats16Task implements TexturedBoxRenderer {
   private final float[] field1;
   private final Vector3f[] field2;
   private int field3;

   public static Horsestats16Task method1(float var0, float var1, float var2, int var3, int var4, int var5, int var6, int var7) {
      Vector3f var8 = new Vector3f(var0 - var3, var1 - var4, var2 - var5);
      Vector3f var9 = new Vector3f(var0 + var3, var1 + var4, var2 + var5);
      return new Horsestats16Task(var9, var8, var6, var7);
   }

   public static Horsestats16Task method2(int var0, int var1) {
      return method1(0.0F, 16.0F, -1.0F, 10, 16, 1, var0, var1);
   }

   public Horsestats16Task(Vector3f var1, Vector3f var2, int var3, int var4) {
      Vector3f var5 = var1.sub(var2, new Vector3f());
      var5.x /= 2.0F;
      var5.y /= 2.0F;
      var5.z /= 2.0F;
      float var6 = var5.x;
      float var7 = var5.y;
      float var8 = var5.z;
      this.field2 = ThreadModuleDump92.method4(var1, var2);
      this.field3 = -1;
      FloatBuffer var9 = FloatBuffer.allocate(48);
      var9.put(ThreadModuleDump92.method3((int)var8, (int)var8, var3, var4, (int)var6, (int)var7, 1));
      var9.put(ThreadModuleDump92.method3((int)(var8 + var6), 0, var3, var4, (int)var6, (int)var8, 1));
      var9.put(ThreadModuleDump92.method3((int)var8, 0, var3, var4, (int)var6, (int)var8, 1));
      var9.put(ThreadModuleDump92.method3((int)(var6 + var8), (int)var8, var3, var4, (int)var8, (int)var7, 1));
      var9.put(ThreadModuleDump92.method3(0, (int)var8, var3, var4, 1, (int)var7, 1));
      var9.put(ThreadModuleDump92.method3((int)(var6 + var8 + var8), (int)var8, var3, var4, (int)var6, (int)var7, 1));
      this.field1 = var9.array();
   }

   public void method1(AbstractRenderContext var1, float var2, ResourceLocationBridge var3, UUID var4) {
      RenderLayerBridge var5 = LunarRenderTypes.field55.get(var3);
      Bridge2_32 var6 = var1.method10(var5);
      if (!var6.method22()) {
         if (this.field1 != null) {
            ThreadModuleDump63.method4().method102().method15(NativeImageBuilder.method7(), var2x -> this.method4(var2x::method1, var2, var2x::method2, true), var4, var3);
            boolean var7 = var1.method7();
            var1.method20();
            if (this.field3 == -1) {
               this.field3 = GL11.glGenLists(1);
               if (var6 instanceof com.moonsworth.lunar.bridge.Bridge2Handler) {
                  GL11.glNewList(this.field3, 4864);
               } else {
                  GL11.glNewList(this.field3, 4865);
               }

               var6.method1();
               this.method4(
                  (var1x, var2x, var3x, var4x, var5x, var6x, var7x, var8x) -> var6.method2(var3x, var4x, var5x)
                     .method10(var1x, var2x)
                     .method14(var6x, var7x, var8x)
                     .method16(),
                  var2,
                  null,
                  false
               );
               if (var6 instanceof com.moonsworth.lunar.bridge.Bridge2Handler var8) {
                  var8.method13();
                  GL11.glEndList();
                  var5.bridge$setupRenderState();
                  GL11.glCallList(this.field3);
                  var5.bridge$clearRenderState();
               } else {
                  var6.method17(BufferBuildMode.BATCHED);
                  GL11.glEndList();
               }
            } else if (var6 instanceof com.moonsworth.lunar.bridge.Bridge2Handler) {
               var5.bridge$setupRenderState();
               GL11.glCallList(this.field3);
               var5.bridge$clearRenderState();
            } else {
               GL11.glCallList(this.field3);
            }

            if (!var7) {
               var1.method21();
            }
         }
      }
   }

   private void method4(Horsestats16Task.Extension var1, float var2, Runnable var3, boolean var4) {
      byte var5 = 0;
      byte var6 = 0;

      for (byte var7 = 0; var7 < this.field2.length; var7 += 4) {
         Vector3f var8 = this.field2[var7];
         Vector3f var9 = this.field2[var7 + 1];
         Vector3f var10 = this.field2[var7 + 2];
         Vector3f var11 = this.field2[var7 + 3];
         float var12 = ThreadModuleDump92.field1[var6];
         float var13 = ThreadModuleDump92.field1[var6 + 1];
         float var14 = ThreadModuleDump92.field1[var6 + 2];
         var1.vertex(this.field1[var5], this.field1[var5 + 1], var8.x() * var2, var8.y() * var2, var8.z() * var2, var12, var13, var14);
         if (var4) {
            var1.vertex(this.field1[var5 + 6], this.field1[var5 + 7], var11.x() * var2, var11.y() * var2, var11.z() * var2, var12, var13, var14);
            var1.vertex(this.field1[var5 + 2], this.field1[var5 + 3], var9.x() * var2, var9.y() * var2, var9.z() * var2, var12, var13, var14);
            var1.vertex(this.field1[var5 + 4], this.field1[var5 + 5], var10.x() * var2, var10.y() * var2, var10.z() * var2, var12, var13, var14);
         } else {
            var1.vertex(this.field1[var5 + 2], this.field1[var5 + 3], var9.x() * var2, var9.y() * var2, var9.z() * var2, var12, var13, var14);
            var1.vertex(this.field1[var5 + 4], this.field1[var5 + 5], var10.x() * var2, var10.y() * var2, var10.z() * var2, var12, var13, var14);
            var1.vertex(this.field1[var5 + 6], this.field1[var5 + 7], var11.x() * var2, var11.y() * var2, var11.z() * var2, var12, var13, var14);
         }

         if (var3 != null) {
            var3.run();
         }

         if (var5 + 2 < this.field1.length) {
            var5 += 8;
         }

         var6 += 3;
      }
   }

   @FunctionalInterface
   public interface Extension {
      void vertex(float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8);
   }
}
