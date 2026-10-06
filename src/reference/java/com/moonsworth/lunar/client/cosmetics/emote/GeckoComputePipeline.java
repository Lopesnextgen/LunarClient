package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge$Type2;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_5;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBindingTarget;
import com.moonsworth.lunar.bridge.Bridge_29;
import com.moonsworth.lunar.bridge.Bridge_35;
import com.moonsworth.lunar.bridge.Matrix3fBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import com.moonsworth.lunar.client.cosmetics.gecko.IBoneSerializer;
import com.moonsworth.lunar.client.cosmetics.gecko.BoneList;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelQuad;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelVertex;
import com.moonsworth.lunar.client.cosmetics.gecko.CubeMesh;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.click.Click10;
import com.moonsworth.lunar.client.util.click.Click2;
import com.moonsworth.lunar.ichor.Annotation2;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntArrays;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.awt.Color;
import java.lang.ref.Cleaner;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelRenderConfig;
import com.moonsworth.lunar.client.cosmetics.gecko.TransformStack;
import com.moonsworth.lunar.client.cosmetics.gecko.RenderPass;
import com.moonsworth.lunar.client.cosmetics.gecko.GlResources;
import com.moonsworth.lunar.client.cosmetics.gecko.Transform;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelVertexShader;

@Annotation2(min = 8)
public class GeckoComputePipeline {
   private static final Cleaner field1 = Cleaner.create();
   private static GeckoComputePipeline field2;
   private static boolean field3 = false;
   private static int field4 = -1;
   private static int field5 = -1;
   private final WeakHashMap<AnimationProcessor<?>, Int2ObjectMap<GlResources>> field6 = new WeakHashMap<>();
   private final WeakHashMap<AnimationProcessor<?>, Int2ObjectMap<GlResources>> field7 = new WeakHashMap<>();
   private final Map<Bridge_35, GlResources> field8 = Collections.synchronizedMap(new IdentityHashMap<>());
   private Bridge_35 field9;
   private final Vector3f field10 = new Vector3f();
   private final Quaternionf field11 = new Quaternionf();
   private float[] field12 = new float[0];
   private int[] field13 = new int[0];

   private static void method1() {
      Map var0 = Map.of(0, BufferBindingTarget.STORAGE_BUFFER, 1, BufferBindingTarget.STORAGE_BUFFER, 2, BufferBindingTarget.STORAGE_BUFFER, 3, BufferBindingTarget.UNIFORM_BUFFER);
      Bridge_29 var1 = Bridge.method42().method88().orElseThrow();
      field4 = var1.method1(ModelVertexShader.method2(), var0);
      if (field4 != -1) {
         Map var2 = Map.of(0, BufferBindingTarget.STORAGE_BUFFER, 1, BufferBindingTarget.STORAGE_BUFFER, 2, BufferBindingTarget.UNIFORM_BUFFER);
         field5 = var1.method1(ModelVertexShader.method1(), var2);
      }
   }

   public void method2(IBoneSerializer var1, List<CubeMesh> var2, IntArrayList var3) {
      var2.addAll(var1.field2);
      if (!var1.field2.isEmpty()) {
         var3.add(var1.field2.size());
      }

      for (IBoneSerializer var5 : var1.field1) {
         this.method2(var5, var2, var3);
      }
   }

   private GlResources method3(AnimationProcessor<?> var1, BoneList var2, RenderLayerBridge var3, boolean var4) {
      ArrayList var5 = new ArrayList();
      IntArrayList var6 = new IntArrayList();

      for (IBoneSerializer var8 : var2.field1) {
         this.method2(var8, var5, var6);
      }

      int var40 = 0;

      for (CubeMesh var9 : var5) {
         for (ModelQuad var13 : var9.field1) {
            if (var13 != null) {
               var40++;
            }
         }
      }

      if (var40 == 0) {
         return null;
      }

      Bridge2_5 var42 = Bridge.method42()
         .method83()
         .method10(
            var3,
            var1x -> {
               for (CubeMesh var3x : var5) {
                  for (ModelQuad var7 : var3x.field1) {
                     if (var7 != null) {
                        for (int var8x = 0; var8x < 4; var8x++) {
                           var1x.bridge$vertex(0.0, 0.0, 0.0)
                              .bridge$color(0, 0, 0, 0)
                              .bridge$uv(0.0F, 0.0F)
                              .bridge$overlayCoords(0, 10)
                              .bridge$uv2(0, 0)
                              .bridge$normal(0.0F, 0.0F, 0.0F)
                              .bridge$endVertex();
                        }
                     }
                  }
               }
            },
            true
         );
      Bridge2_5 var43 = null;
      if (var4) {
         var43 = Bridge.method42().method83().method10(LunarRenderTypes.field14, var1x -> {
            for (CubeMesh var3x : var5) {
               for (ModelQuad var7 : var3x.field1) {
                  if (var7 != null) {
                     for (int var8x = 0; var8x < 4; var8x++) {
                        var1x.bridge$vertex(0.0, 0.0, 0.0).bridge$uv(0.0F, 0.0F).bridge$endVertex();
                     }
                  }
               }
            }
         }, true);
      }

      int var44 = var40 * 4;
      ByteBuffer var45 = Click10.method1(var44 * 10 * 4);
      FloatBuffer var46 = var45.asFloatBuffer();
      int var47 = 0;
      float[] var14 = new float[var40 * 3];
      int[] var15 = new int[var40];
      int var16 = 0;
      int var17 = 0;
      IntListIterator var18 = var6.iterator();
      int var19 = (Integer)var18.next();
      Matrix4f var20 = new Matrix4f();
      Vector3f var21 = new Vector3f();

      for (CubeMesh var23 : var5) {
         if (var19 == 0) {
            var17++;
            var19 = (Integer)var18.next();
         }

         var20.identity();
         PlayerModelPartMap.method21(var23, var20);

         for (ModelQuad var27 : var23.field1) {
            if (var27 != null) {
               Vector3f var28 = var27.field4;
               var20.transformDirection(var28, var21);
               byte var29 = 0;
               if (var23.field4.y == 0.0F || var23.field4.z == 0.0F) {
                  var29 |= 1;
               }

               if (var23.field4.x == 0.0F || var23.field4.z == 0.0F) {
                  var29 |= 2;
               }

               if (var23.field4.x == 0.0F || var23.field4.y == 0.0F) {
                  var29 |= 4;
               }

               int var30 = ((int)(var21.x * 127.0F) & 0xFF) << 24;
               var30 |= ((int)(var21.y * 127.0F) & 0xFF) << 16;
               var30 |= ((int)(var21.z * 127.0F) & 0xFF) << 8;
               float var31 = Float.intBitsToFloat(Integer.reverseBytes(var30 | var29));
               float var32 = 0.0F;
               float var33 = 0.0F;
               float var34 = 0.0F;

               for (ModelVertex var38 : var27.field3) {
                  Vector3f var39 = var38.field1;
                  var20.transformPosition(var39, var21);
                  var32 += var21.x;
                  var33 += var21.y;
                  var34 += var21.z;
                  var46.put(var47++, var21.x);
                  var46.put(var47++, var21.y);
                  var46.put(var47++, var21.z);
                  var46.put(var47++, Float.intBitsToFloat(-1));
                  var46.put(var47++, var38.field2);
                  var46.put(var47++, var38.field3);
                  var46.put(var47++, Float.intBitsToFloat(655360));
                  var46.put(var47++, Float.intBitsToFloat(Bridge.method8().method92()));
                  var46.put(var47++, var31);
                  var46.put(var47++, Float.intBitsToFloat(var17));
               }

               var14[var16 * 3] = var32 / 4.0F;
               var14[var16 * 3 + 1] = var33 / 4.0F;
               var14[var16 * 3 + 2] = var34 / 4.0F;
               var15[var16] = var17;
               var16++;
            }
         }

         var19--;
      }

      var45.limit(var44 * 10 * 4);
      Bridge_35 var57 = Bridge.method42()
         .method89("gecko-compute-vertex-in", var44 * 10L * 4L, RenderSystemBridge.Type.UNIFORM, RenderSystemBridge.Type.COPY_DST, RenderSystemBridge.Type.STORAGE_BUFFER);
      Bridge.method42().method90(var57, 0L, var45);
      Bridge_29 var58 = Bridge.method42().method88().orElseThrow();
      var58.method3(var57);
      Bridge_35 var59 = Bridge.method42()
         .method89("gecko-compute-transform", var6.size() * 40L, RenderSystemBridge.Type.UNIFORM, RenderSystemBridge.Type.COPY_DST, RenderSystemBridge.Type.STORAGE_BUFFER);
      GlResources var60 = new GlResources(var57, var42, var59, var44, var6.size(), var43, var14, var15);
      this.field8.put(var60.field1, var60);
      field1.register(var1, () -> {
         GlResources var2x = this.field8.remove(var57);
         ThreadModuleDump63.method3().bridge$submit(var2x::delete);
      });
      return var60;
   }

   private int method4(IBoneSerializer var1, TransformStack var2, float[] var3, int var4, boolean var5) {
      var2.push();
      PlayerModelPartMap.method18(var1, var2.method1());
      if (!var1.field2.isEmpty()) {
         Transform var6 = var2.method1();
         var3[var4] = var6.field3.x;
         var3[var4 + 1] = var6.field3.y;
         var3[var4 + 2] = var6.field3.z;
         if (var5) {
            var3[var4 + 3] = 0.0F;
            var3[var4 + 4] = 0.0F;
            var3[var4 + 5] = 0.0F;
         } else {
            var3[var4 + 3] = var6.field1.x;
            var3[var4 + 4] = var6.field1.y;
            var3[var4 + 5] = var6.field1.z;
         }

         var3[var4 + 6] = var6.field2.x;
         var3[var4 + 7] = var6.field2.y;
         var3[var4 + 8] = var6.field2.z;
         var3[var4 + 9] = var6.field2.w;
         var4 += 10;
      }

      for (IBoneSerializer var7 : var1.field1) {
         var4 = this.method4(var7, var2, var3, var4, var5 | var7.isHidden);
      }

      var2.pop();
      return var4;
   }

   public void method5(AnimationProcessor<?> var1, BoneList var2) {
      Int2ObjectMap var3 = this.field7.get(var1);
      if (var3 != null) {
         ObjectIterator var4 = var3.values().iterator();

         while (var4.hasNext()) {
            GlResources var5 = (GlResources)var4.next();
            this.method6(var5, var2);
         }
      }
   }

   private float[] method6(GlResources var1, BoneList var2) {
      Click2 var3 = Click2.field1;
      var3.method1(var1.field5 * 10);
      int var4 = var1.field5 * 4 * 10;
      TransformStack var5 = new TransformStack();
      int var6 = 0;

      for (IBoneSerializer var8 : var2.field1) {
         var6 = this.method4(var8, var5, var3.field2, var6, var8.isHidden);
      }

      ByteBuffer var9 = Click10.method1(var4);
      var9.asFloatBuffer().put(var3.field2, 0, var1.field5 * 10);
      var9.limit(var4);
      var3.size = 0;
      Bridge.method42().method90(var1.field3, 0L, var9);
      return var3.field2;
   }

   private static void method7(Bridge2_5 var0, int[] var1, int var2) {
      Bridge$Type2 var3 = var0.bridge$getIndexType();
      int var4 = var3.getBytes();
      int var5 = var2 * 6 * var4;
      ByteBuffer var6 = Click10.method1(var5);
      long var7 = MemoryUtil.memAddress(var6);

      for (int var9 = 0; var9 < var2; var9++) {
         int var10 = var1[var9] * 4;
         long var11 = var7 + var9 * 6L * var4;
         method8(var11, var3, var10);
         method8(var11 + var4, var3, var10 + 1);
         method8(var11 + var4 * 2L, var3, var10 + 2);
         method8(var11 + var4 * 3L, var3, var10 + 2);
         method8(var11 + var4 * 4L, var3, var10 + 3);
         method8(var11 + var4 * 5L, var3, var10);
      }

      var6.limit(var5);
      var0.bridge$updateIndexBuffer(var6);
   }

   private static void method8(long var0, Bridge$Type2 var2, int var3) {
      switch (var2) {
         case BYTE:
            MemoryUtil.memPutByte(var0, (byte)var3);
            break;
         case SHORT:
            MemoryUtil.memPutShort(var0, (short)var3);
            break;
         case INT:
            MemoryUtil.memPutInt(var0, var3);
      }
   }

   private int[] method9(GlResources var1, float[] var2, MixinHelper_21 var3) {
      int var4 = var1.field4 / 4;
      Matrix4f var5 = var3.method8(true);
      Matrix4f var6 = var5.invert(new Matrix4f());
      float var7 = var6.m30();
      float var8 = var6.m31();
      float var9 = var6.m32();
      if (this.field13.length < var4) {
         this.field13 = new int[var4];
         this.field12 = new float[var4];
      }

      float[] var10 = this.field12;
      int[] var11 = this.field13;

      for (int var12 = 0; var12 < var4; var11[var12] = var12++) {
         int var13 = var1.field8[var12] * 10;
         this.field10.set(var1.field7[var12 * 3] * var2[var13 + 3], var1.field7[var12 * 3 + 1] * var2[var13 + 4], var1.field7[var12 * 3 + 2] * var2[var13 + 5]);
         this.field11.set(var2[var13 + 6], var2[var13 + 7], var2[var13 + 8], var2[var13 + 9]);
         this.field11.transform(this.field10);
         float var14 = this.field10.x + var2[var13] - var7;
         float var15 = this.field10.y + var2[var13 + 1] - var8;
         float var16 = this.field10.z + var2[var13 + 2] - var9;
         var10[var12] = var14 * var14 + var15 * var15 + var16 * var16;
      }

      IntArrays.mergeSort(var11, 0, var4, (var1x, var2x) -> Float.compare(var10[var2x], var10[var1x]));
      return var11;
   }

   public void method10(ModelRenderConfig var1, int var2) {
      boolean var3 = var1.method14() == RenderPass.NORMAL_GLINT;
      WeakHashMap var4 = var3 ? this.field6 : this.field7;
      AnimationProcessor var5 = var1.method8();
      BoneList var6 = var1.method10();
      Bridge5_16 var7 = var1.method7().method30().method51();
      RenderLayerBridge var8 = var1.getRenderType().get(var1.getTexture());
      Int2ObjectMap var9 = var4.computeIfAbsent(var5, var0 -> new Int2ObjectOpenHashMap(2));
      GlResources var10 = (GlResources)var9.computeIfAbsent(var1.method17(), var5x -> this.method3(var5, var6, var8, var3));
      if (var10 != null) {
         Bridge_29 var11 = Bridge.method42().method88().orElseThrow();
         float[] var12 = this.method6(var10, var6);
         var11.method3(var10.field3);
         if (this.field9 == null) {
            this.field9 = Bridge.method42().method89("Context Buffer", 128L, RenderSystemBridge.Type.COPY_DST, RenderSystemBridge.Type.UNIFORM);
         }

         ByteBuffer var13 = Click10.method1(128);
         MixinHelper_21 var14 = var7.bridge$last().bridge$pose();
         Color var15 = var1.getColor();
         Matrix3fBridge var16 = var7.bridge$last().bridge$normal();
         var16.bridge$getColumnMajorBuffer(var13.asFloatBuffer());
         var13.putInt(48, var2);
         var13.putInt(52, var10.field4);
         var13.put(56, (byte)var15.getRed());
         var13.put(57, (byte)var15.getGreen());
         var13.put(58, (byte)var15.getBlue());
         var13.put(59, (byte)var15.getAlpha());
         var13.putInt(60, var1.method12() ? 1 : 0);
         Matrix4f var17 = var14.method8(true);
         var17.get(16, var13.asFloatBuffer());
         var13.limit(128);
         Bridge.method42().method90(this.field9, 0L, var13);
         var11.method3(this.field9);
         MixinHelper_21 var18 = Bridge.method42().method83().method15().bridge$copy();
         int var19 = var10.field4 / 4;
         if (var1.method11()) {
            MixinHelper_21 var20 = var18.bridge$copy();
            var20.bridge$multiply(var14);
            int[] var21 = this.method9(var10, var12, var20);
            method7(var10.field2, var21, var19);
            if (var10.field6 != null) {
               method7(var10.field6, var21, var19);
            }
         }

         Bridge_35 var26 = var10.field2.bridge$getVertexBuffer();
         var11.method4(var26);
         Map var27 = Map.of(0, var10.field1, 1, var10.field3, 2, var26, 3, this.field9);
         var11.method2(field4, (int)Math.ceil(var10.field4 / 32.0F), 1, 1, var27);
         var11.method5(var26);
         AbstractRenderContext var22 = var1.method7();
         var22.method30().method43().orElseThrow().bridge$renderUnbatchableAfter(var8, () -> var10.field2.bridge$draw(var18, var8));
         if (var3 && var10.field6 != null && field5 != -1) {
            Bridge_35 var23 = var10.field6.bridge$getVertexBuffer();
            Map var24 = Map.of(0, var26, 1, var23, 2, this.field9);
            var11.method6(var26);
            var11.method4(var23);
            int var25 = (int)Math.ceil(var10.field4 / 32.0F);
            var11.method2(field5, var25, 1, 1, var24);
            var11.method5(var23);
            var22.method30()
               .method43()
               .orElseThrow()
               .bridge$renderUnbatchableAfter(LunarRenderTypes.field14, () -> var10.field6.bridge$draw(var18, LunarRenderTypes.field14));
         }
      }
   }

   public void close() {
      for (GlResources var2 : this.field8.values()) {
         var2.delete();
      }

      this.field8.clear();
      if (this.field9 != null) {
         Bridge.method42().method91(this.field9);
         this.field9 = null;
      }

      Bridge.method42().method88().ifPresent(Bridge_29::close);
   }

   private static boolean method11() {
      return Bridge.method42().method88().isEmpty();
   }

   public static GeckoComputePipeline method12() {
      if (field2 == null) {
         if (method11()) {
            throw new RuntimeException("Tried to use compute shaders for gecko cosmetics when its not supported!");
         }

         field2 = new GeckoComputePipeline();
      }

      return field2;
   }

   public static boolean method13() {
      if (!Bridge.getMinecraftVersion().method23() || method11()) {
         return false;
      }

      if (ThreadModuleDump63.method4().method40().method73().isValid() && !ThreadModuleDump63.method4().method40().method73().field31.get()) {
         return false;
      }

      if (!field3) {
         method1();
         field3 = true;
      }

      return field4 == -1 ? false : Bridge.method5().isEmpty() || !Bridge.method5().get().getConfig().hasShaders();
   }
}
