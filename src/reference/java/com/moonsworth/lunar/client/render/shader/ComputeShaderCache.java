package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.client.cosmetics.gecko.IBoneSerializer;
import com.moonsworth.lunar.client.cosmetics.gecko.BoneList;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelQuad;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelVertex;
import com.moonsworth.lunar.client.cosmetics.gecko.CubeMesh;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.click.Click10;
import com.moonsworth.lunar.client.util.click.Click2;
import com.moonsworth.lunar.ichor.Annotation2;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import java.awt.Color;
import java.lang.ref.Cleaner;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.IntConsumer;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GL42;
import org.lwjgl.opengl.GL43;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelRenderConfig;
import com.moonsworth.lunar.client.cosmetics.gecko.Transform;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelVertexShader;
import com.moonsworth.lunar.client.cosmetics.gecko.TransformStack;
import com.moonsworth.lunar.client.render.texture.GlintTexture;
import com.moonsworth.lunar.client.cosmetics.gecko.RenderPass;

public class ComputeShaderCache {
   private static final Cleaner field1 = Cleaner.create();
   private static ComputeShaderCache field2;
   private final WeakHashMap<AnimationProcessor<?>, ComputeShaderCache.Data> field3 = new WeakHashMap<>();
   private final ConcurrentHashMap<Integer, ComputeShaderCache.Data> field4 = new ConcurrentHashMap<>();
   private final int program = GL20.glCreateProgram();

   public ComputeShaderCache() {
      int var1 = GL20.glCreateShader(37305);
      GL20.glShaderSource(var1, ModelVertexShader.method3());
      GL20.glCompileShader(var1);
      if (GL20.glGetShaderi(var1, 35713) != 1) {
         throw new RuntimeException(GL20.glGetShaderInfoLog(var1, 4096));
      }

      GL20.glAttachShader(this.program, var1);
      GL20.glLinkProgram(this.program);
      if (GL20.glGetProgrami(this.program, 35714) != 1) {
         throw new RuntimeException(GL20.glGetProgramInfoLog(this.program));
      }

      GL20.glDeleteShader(var1);
   }

   public void method1(IBoneSerializer var1, List<CubeMesh> var2, IntArrayList var3) {
      var2.addAll(var1.field2);
      if (!var1.field2.isEmpty()) {
         var3.add(var1.field2.size());
      }

      for (IBoneSerializer var5 : var1.field1) {
         this.method1(var5, var2, var3);
      }
   }

   private ComputeShaderCache.Data method2(AnimationProcessor<?> var1, BoneList var2) {
      ArrayList var3 = new ArrayList();
      IntArrayList var4 = new IntArrayList();

      for (IBoneSerializer var6 : var2.field1) {
         this.method1(var6, var3, var4);
      }

      int var30 = 0;

      for (CubeMesh var7 : var3) {
         for (ModelQuad var11 : var7.field1) {
            if (var11 != null) {
               var30++;
            }
         }
      }

      if (var30 == 0) {
         return null;
      }

      int var32 = var30 * 4;
      FloatBuffer var33 = Click10.method1(var32 * 7 * 4).asFloatBuffer();
      int var34 = 0;
      int var39 = var32 * 5;
      int var41 = 0;
      IntListIterator var42 = var4.iterator();
      int var12 = (Integer)var42.next();
      Matrix4f var13 = new Matrix4f();
      Vector3f var14 = new Vector3f();

      for (CubeMesh var16 : var3) {
         if (var12 == 0) {
            var41++;
            var12 = (Integer)var42.next();
         }

         var13.identity();
         PlayerModelPartMap.method21(var16, var13);

         for (ModelQuad var20 : var16.field1) {
            if (var20 != null) {
               Vector3f var21 = var20.field4;
               var13.transformDirection(var21, var14);
               byte var22 = 0;
               if (var16.field4.y == 0.0F || var16.field4.z == 0.0F) {
                  var22 |= 1;
               }

               if (var16.field4.x == 0.0F || var16.field4.z == 0.0F) {
                  var22 |= 2;
               }

               if (var16.field4.x == 0.0F || var16.field4.y == 0.0F) {
                  var22 |= 4;
               }

               int var23 = ((int)(var14.x * 127.0F) & 0xFF) << 24;
               var23 |= ((int)(var14.y * 127.0F) & 0xFF) << 16;
               var23 |= ((int)(var14.z * 127.0F) & 0xFF) << 8;
               float var24 = Float.intBitsToFloat(Integer.reverseBytes(var23 | var22));

               for (ModelVertex var28 : var20.field3) {
                  Vector3f var29 = var28.field1;
                  var13.transformPosition(var29, var14);
                  var33.put(var34++, var14.x);
                  var33.put(var34++, var14.y);
                  var33.put(var34++, var14.z);
                  var33.put(var34++, var24);
                  var33.put(var34++, Float.intBitsToFloat(var41));
                  var33.put(var39++, var28.field2);
                  var33.put(var39++, var28.field3);
               }
            }
         }

         var12--;
      }

      int var43 = GL30.glGenVertexArrays();
      int var44 = GL15.glGenBuffers();
      int var45 = GL15.glGenBuffers();
      int var46 = GL15.glGenBuffers();
      int var47 = GL15.glGenBuffers();
      int var48 = GL15.glGenBuffers();
      GL15.glBindBuffer(37074, var45);
      var33.limit(var32 * 5);
      GL15.glBufferData(37074, var33, 35045);
      GL15.glBindBuffer(37074, var46);
      GL15.glBufferData(37074, var32 * 16L, 35050);
      GL15.glBindBuffer(37074, var47);
      GL15.glBufferData(37074, var4.size() * 40L, 35048);
      GL15.glBindBuffer(37074, 0);
      GL30.glBindVertexArray(var43);
      GL15.glBindBuffer(34962, var44);
      GL15.glBufferData(34962, var32 * 24L, 35050);
      var33.limit(var32 * 7);
      var33.position(var32 * 5);
      GL15.glBufferSubData(34962, var32 * 16L, var33);
      GL11.glEnableClientState(32884);
      GL11.glEnableClientState(32885);
      GL11.glEnableClientState(32888);
      GL11.glVertexPointer(3, 5126, 16, 0L);
      GL11.glNormalPointer(5120, 16, 12L);
      GL11.glTexCoordPointer(2, 5126, 8, var32 * 16L);
      ByteBuffer var49 = Click10.method1(var32 * 4);
      IntBuffer var50 = var49.asIntBuffer();

      for (byte var53 = 0; var53 < var32; var53 += 4) {
         var50.put(var53 + 1);
         var50.put(var53);
         var50.put(var53 + 3);
         var50.put(var53 + 2);
      }

      var49.limit(var32 * 4);
      GL15.glBindBuffer(34963, var48);
      GL15.glBufferData(34963, var49, 35044);
      GL30.glBindVertexArray(0);
      GL15.glBindBuffer(34962, 0);
      ComputeShaderCache.Data var54 = new ComputeShaderCache.Data(var43, var44, var45, var46, var47, var48, var32, var4.size());
      this.field4.put(var54.field1, var54);
      field1.register(var1, () -> {
         ComputeShaderCache.Data var2x = this.field4.remove(var43);
         ThreadModuleDump63.method3().bridge$submit(var2x::delete);
      });
      return var54;
   }

   private int method3(IBoneSerializer var1, TransformStack var2, float[] var3, int var4, boolean var5) {
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
         var4 = this.method3(var7, var2, var3, var4, var5 | var7.isHidden);
      }

      var2.pop();
      return var4;
   }

   public void method4(AnimationProcessor<?> var1, BoneList var2) {
      ComputeShaderCache.Data var3 = this.field3.get(var1);
      if (var3 != null) {
         Click2 var4 = Click2.field1;
         var4.method1(var3.field8 * 10);
         int var5 = var3.field8 * 4 * 10;
         TransformStack var6 = new TransformStack();
         int var7 = 0;

         for (IBoneSerializer var9 : var2.field1) {
            var7 = this.method3(var9, var6, var4.field2, var7, var9.isHidden);
         }

         ByteBuffer var10 = Click10.method1(var5);
         var10.asFloatBuffer().put(var4.field2, 0, var3.field8 * 10);
         var10.limit(var5);
         var4.size = 0;
         GL15.glBindBuffer(37074, var3.field5);
         GL15.glBufferData(37074, var10, 35048);
         GL15.glBindBuffer(37074, 0);
      }
   }

   @Annotation2(max = 5)
   public void method5(ModelRenderConfig var1) {
      AnimationProcessor var2 = var1.method8();
      ComputeShaderCache.Data var3 = this.field3.get(var2);
      if (var3 == null) {
         var3 = this.method2(var2, var1.method10());
         if (var3 == null) {
            return;
         }

         this.field3.put(var2, var3);
      }

      this.method4(var2, var1.method10());
      GL30.glBindBufferBase(37074, 0, var3.field3);
      GL30.glBindBufferBase(37074, 1, var3.field5);
      GL30.glBindBufferBase(37074, 2, var3.field4);
      GL20.glUseProgram(this.program);
      GL43.glDispatchCompute((int)Math.ceil(var3.field7 / 32.0F), 1, 1);
      GL42.glMemoryBarrier(8192);
      GL20.glUseProgram(0);
      GL30.glBindBufferBase(37074, 0, 0);
      GL30.glBindBufferBase(37074, 1, 0);
      GL30.glBindBufferBase(37074, 2, 0);
      GL15.glBindBuffer(36662, var3.field4);
      GL15.glBindBuffer(36663, var3.field2);
      GL31.glCopyBufferSubData(36662, 36663, 0L, 0L, var3.field7 * 16L);
      GL15.glBindBuffer(36662, 0);
      GL15.glBindBuffer(36663, 0);
      Bridge.method42().method7(1.0F, 1.0F, 1.0F, 1.0F);
      RenderLayerBridge var4 = var1.getRenderType().get(var1.getTexture());
      GL30.glBindVertexArray(var3.field1);
      var4.bridge$setupRenderState();
      method6(var1, var3);
      var4.bridge$clearRenderState();
      if (var1.method14() == RenderPass.NORMAL_GLINT) {
         method7(var1, var3);
      }

      GL30.glBindVertexArray(0);
   }

   @Annotation2(max = 5)
   private static void method6(ModelRenderConfig var0, ComputeShaderCache.Data var1) {
      if (var0.method12()) {
         GL11.glDrawElements(7, var1.field7, 5125, 0L);
      } else {
         GL11.glDrawArrays(7, 0, var1.field7);
      }
   }

   @Annotation2(max = 5)
   private static void method7(ModelRenderConfig var0, ComputeShaderCache.Data var1) {
      if (GlintTexture.method5()) {
         IntConsumer var3 = var2x -> {
            Color var3x = new Color(var2x);
            var0.method7().method31().method1(var3x.getRed() / 255.0F, var3x.getGreen() / 255.0F, var3x.getBlue() / 255.0F, var3x.getAlpha() / 255.0F);
            method6(var0, var1);
         };
         GlintTexture.method6(var3);
      } else {
         Color var2 = GlintTexture.method4();
         var0.method7().method31().method1(var2.getRed() / 255.0F, var2.getGreen() / 255.0F, var2.getBlue() / 255.0F, var2.getAlpha() / 255.0F);
         GlintTexture.method9();
         method6(var0, var1);
         GlintTexture.method10();
         method6(var0, var1);
         GlintTexture.method11();
      }
   }

   public static ComputeShaderCache method8() {
      if (field2 == null) {
         if (!Bridge.method22().method25()) {
            throw new RuntimeException("Tried to use compute shaders for gecko cosmetics when its not supported!");
         }

         field2 = new ComputeShaderCache();
      }

      return field2;
   }

   public static boolean method9() {
      if (ThreadModuleDump63.MC_VERSION != 1 || !Bridge.method22().method25()) {
         return false;
      } else {
         return ThreadModuleDump63.method4().method40().method73().isValid() && !ThreadModuleDump63.method4().method40().method73().field31.get()
            ? false
            : Bridge.method5().isEmpty() || !Bridge.method5().get().getConfig().hasShaders();
      }
   }

   private class Data {
      private final int field1;
      private final int field2;
      private final int field3;
      private final int field4;
      private final int field5;
      private final int field6;
      private final int field7;
      private final int field8;

      private Data(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
         this.field6 = var6;
         this.field7 = var7;
         this.field8 = var8;
      }

      void delete() {
         GL30.glDeleteVertexArrays(this.field1);
         GL15.glDeleteBuffers(this.field2);
         GL15.glDeleteBuffers(this.field3);
         GL15.glDeleteBuffers(this.field4);
         GL15.glDeleteBuffers(this.field5);
         GL15.glDeleteBuffers(this.field6);
      }

      public int method1() {
         return this.field1;
      }

      public int draw() {
         return this.field2;
      }

      public int method2() {
         return this.field3;
      }

      public int method3() {
         return this.field4;
      }

      public int method4() {
         return this.field5;
      }

      public int method5() {
         return this.field6;
      }

      public int method6() {
         return this.field7;
      }

      public int method7() {
         return this.field8;
      }
   }
}
