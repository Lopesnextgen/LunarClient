package com.moonsworth.lunar.client.render;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump19;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import lombok.Generated;
import org.jetbrains.annotations.TestOnly;
import org.lwjgl.actually3.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

@TestOnly
public class OpenGlInfo {
   private static int field1 = -1;
   private static final OpenGlInfo.Data3[] field2 = new OpenGlInfo.Data3[3];
   private static OpenGlInfo.Data3 field3 = null;
   private OpenGlInfo.Data4 field4 = null;
   private boolean field5 = true;
   private boolean field6 = true;
   private boolean field7 = true;
   private boolean field8 = true;
   private boolean field9 = true;

   public OpenGlInfo() {
      if (field1 == -1) {
         field1 = GL11.glGetInteger(33307);
      }

      for (int var1 = 0; var1 < Math.min(field2.length, field1); var1++) {
         if (field2[var1] == null) {
            field2[var1] = method18(var1);
         }
      }

      if (field3 == null) {
         Builder var9 = new Builder();
         Builder var2 = new Builder();
         Builder var3 = new Builder();
         Builder var4 = new Builder();

         for (OpenGlInfo.Data3 var8 : field2) {
            if (var8 != null) {
               var9.putAll(var8.field1);
               var2.putAll(var8.field2);
               var3.putAll(var8.field3);
               var4.putAll(var8.field4);
            }
         }

         field3 = new OpenGlInfo.Data3(var9.build(), var2.build(), var3.build(), var4.build());
      }
   }

   public OpenGlInfo method1(boolean var1) {
      this.field5 = var1;
      return this;
   }

   public OpenGlInfo method2(boolean var1) {
      this.field6 = var1;
      return this;
   }

   public OpenGlInfo method3(boolean var1) {
      this.field7 = var1;
      return this;
   }

   public OpenGlInfo method4(boolean var1) {
      this.field8 = var1;
      return this;
   }

   public OpenGlInfo method5(boolean var1) {
      this.field9 = var1;
      return this;
   }

   public void method6(int var1, int var2, IntConsumer var3) {
      int var4 = GL11.glGetInteger(var1);
      if (var4 != var2) {
         var3.accept(var4);
         if (this.field9) {
            System.exit(0);
         }
      }
   }

   public void method7(int var1, boolean var2, BooleanConsumer var3) {
      boolean var4 = GL11.glIsEnabled(var1);
      if (var4 != var2) {
         var3.accept(var4);
         if (this.field9) {
            System.exit(0);
         }
      }
   }

   public void method8(int var1, int var2, Object var3, Consumer<Object> var4) {
      OpenGlInfo.Data var5 = field3.field4.get(var1);
      if (var5 == null) {
         throw new RuntimeException("GLAnalyser does not contain array information for: " + var1);
      }

      OpenGlInfo.Type var6 = var5.field2;
      Buffer var7 = var6.createBuffer.apply(var6.objectSize * var5.field3);
      var6.getData.accept(var1, var7);
      Object var8 = BufferUtils.toArray(var7);
      Object var9 = Array.get(var8, var2);
      MemoryUtil.memFree(var7);
      if (var9 != var3) {
         var4.accept(var9);
         if (this.field9) {
            System.exit(0);
         }
      }
   }

   public void start() {
      OpenGlInfo.Data4 var1 = new OpenGlInfo.Data4();
      if (this.field5) {
         for (Integer var3 : field3.field1.keySet()) {
            var1.field1.put(var3, GL11.glGetInteger(var3));
         }
      }

      if (this.field6) {
         for (Integer var11 : field3.field2.keySet()) {
            var1.field2.put(var11, GL11.glIsEnabled(var11));
         }
      }

      if (this.field7) {
         for (Integer var12 : field3.field3.keySet()) {
            var1.field3.put(var12, GL11.glGetInteger(var12));
         }
      }

      if (this.field8) {
         for (Entry var13 : field3.field4.entrySet()) {
            Integer var4 = (Integer)var13.getKey();
            OpenGlInfo.Data var5 = (OpenGlInfo.Data)var13.getValue();
            OpenGlInfo.Type var6 = var5.field2;
            Buffer var7 = var6.createBuffer.apply(var6.objectSize * var5.field3);
            var6.getData.accept(var4, var7);
            var1.field4.put(var4, BufferUtils.toArray(var7));
            MemoryUtil.memFree(var7);
         }
      }

      this.field4 = var1;
   }

   public void method9() {
      try {
         if (this.field4 == null) {
            throw new Exception("GlAnalyser requires you to run start() before analyse()!");
         }

         StringBuilder var1 = new StringBuilder();
         var1.append("\nOpenGl: ")
            .append(GL11.glGetString(7938))
            .append(" - Major Version: ")
            .append(field1)
            .append("\n========================= GL Analysis =========================\n");
         StringBuilder var2 = new StringBuilder();
         StringBuilder var3 = new StringBuilder();
         if (this.field5) {
            method10(var2, field3.field1, this.field4.field1, "s");
         }

         if (this.field6) {
            method12(var2, field3.field2, this.field4.field2, "c");
         }

         if (this.field7) {
            method11(var2, field3.field3, this.field4.field3, "p");
         }

         if (this.field8) {
            method13(var3, field3.field4, this.field4.field4, "a");
         }

         if (var2.isEmpty() && var3.isEmpty()) {
            var1.append("\n\nNo changes where found between points!");
         } else {
            if (!var2.isEmpty()) {
               var1.append("\n ++++ Single State Changes ++++\n")
                  .append("%-3s%-30s%-20s%-45s%-45s%n".formatted("ID", "Name", "Change", "Get Method", "Set Method"))
                  .append(var2);
               var1.append("\nExample:\n---------------------------------------------------------------\n")
                  .append("var lastValue = <GetMethod>;\n**Run Code Here**\n<SetMethod>;\n---------------------------------------------------------------\n");
            }

            if (!var3.isEmpty()) {
               if (!var2.isEmpty()) {
                  var1.append("\n===============================================================\n");
               }

               var1.append("\n ++++ Array State Changes ++++\n")
                  .append("%-3s%-20s%-30s%-72s%-80s%n".formatted("ID", "Name", "Create Buffer", "Get Method", "Change"))
                  .append(var3);
               var1.append("\nExample:\n---------------------------------------------------------------\n")
                  .append(
                     "var buffer = <CreateBufferMethod>; \n<GetMethod>;\n# Access the buffer object, changes are reflected.\n# Use `BufferUtils.toArray()` to get it as an array!\n---------------------------------------------------------------"
                  );
            }

            var1.append("\n\n===============================================================\n\n");
         }

         System.out.println(var1);
         if (this.field9) {
            System.exit(0);
         }
      } catch (Throwable var4) {
         throw var4;
      }
   }

   public void dump() {
      try {
         StringBuilder var1 = new StringBuilder();
         var1.append("\n ===================================\n ======= Dumping OpenGL Info =======\n ===================================\n");
         if (this.field5) {
            var1.append("\n ========== Single States ==========\n");
            method14(var1, field3.field1, "s");
         }

         if (this.field6) {
            var1.append("\n ========== Capabilities ===========\n");
            method16(var1, field3.field2, "c");
         }

         if (this.field7) {
            var1.append("\n ========== Pixel Stores ===========\n");
            method15(var1, field3.field3, "p");
         }

         if (this.field8) {
            var1.append("\n ============= Arrays ==============\n");
            method17(var1, field3.field4, "a");
         }

         var1.append("\n ===================================\n");
         System.out.println(var1);
         if (this.field9) {
            System.exit(0);
         }
      } catch (Throwable var2) {
         throw var2;
      }
   }

   private static void method10(StringBuilder var0, Map<Integer, OpenGlInfo.Data2> var1, Map<Integer, Integer> var2, String var3) {
      for (Entry var5 : var1.entrySet()) {
         int var6 = (Integer)var5.getKey();
         int var7 = (Integer)var2.get(var6);
         int var8 = GL11.glGetInteger(var6);
         if (var7 != var8) {
            OpenGlInfo.Data2 var9 = (OpenGlInfo.Data2)var5.getValue();
            var0.append(
               "%-3s%-30s%-20s%-45s%-45s%n"
                  .formatted(var3, var9.field1, var7 + " -> " + var8, var9.field2.getMethod().formatted(var9.field1), var9.field3.formatted("lastValue"))
            );
         }
      }
   }

   private static void method11(StringBuilder var0, Map<Integer, String> var1, Map<Integer, Integer> var2, String var3) {
      for (Entry var5 : var1.entrySet()) {
         int var6 = (Integer)var5.getKey();
         int var7 = (Integer)var2.get(var6);
         int var8 = GL11.glGetInteger(var6);
         if (var7 != var8) {
            String var9 = (String)var5.getValue();
            var0.append("%-3s%-30s%-20s%-45s%-45s%n".formatted(var3, var9, var7 + " -> " + var8, "glGetInteger(" + var9 + ")", "glPixelStorei(lastValue)"));
         }
      }
   }

   private static void method12(StringBuilder var0, Map<Integer, String> var1, Map<Integer, Boolean> var2, String var3) {
      for (Entry var5 : var1.entrySet()) {
         int var6 = (Integer)var5.getKey();
         boolean var7 = (Boolean)var2.get(var6);
         boolean var8 = GL11.glIsEnabled(var6);
         if (var7 != var8) {
            String var9 = (String)var5.getValue();
            var0.append(
               "%-3s%-30s%-20s%-45s%-45s%n"
                  .formatted(var3, var9, var7 + " -> " + var8, "glIsEnabled(" + var9 + ")", (var7 ? "glEnable(" : "glDisable(") + var9 + ")")
            );
         }
      }
   }

   private static void method13(StringBuilder var0, Map<Integer, OpenGlInfo.Data> var1, Map<Integer, Object> var2, String var3) {
      for (Entry var5 : var1.entrySet()) {
         int var6 = (Integer)var5.getKey();
         OpenGlInfo.Data var7 = (OpenGlInfo.Data)var5.getValue();
         Object var8 = var2.get(var6);
         OpenGlInfo.Type var9 = var7.field2;
         Buffer var10 = var9.createBuffer.apply(var9.objectSize * var7.field3);
         var9.getData.accept(var6, var10);
         Object var11 = BufferUtils.toArray(var10);
         MemoryUtil.memFree(var10);
         if (!ThreadModuleDump19.arrayEquals(var8, var11)) {
            var0.append(
               "%-3s%-20s%-30s%-72s%-80s%n"
                  .formatted(
                     var3,
                     var7.field1,
                     var9.method.formatted(var9.objectSize * var7.field3),
                     "BridgeManager.getGlHelper().bridge$glGet"
                        + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, var9.name())
                        + "v("
                        + var7.field1
                        + ", buffer)",
                     ThreadModuleDump19.arrayToString(var8) + " -> " + ThreadModuleDump19.arrayToString(var11)
                  )
            );
            return;
         }
      }
   }

   private static void method14(StringBuilder var0, Map<Integer, OpenGlInfo.Data2> var1, String var2) {
      for (Entry var4 : var1.entrySet()) {
         int var5 = (Integer)var4.getKey();
         int var6 = GL11.glGetInteger(var5);
         OpenGlInfo.Data2 var7 = (OpenGlInfo.Data2)var4.getValue();
         var0.append("%-3s%-30s%-40s%n".formatted(var2, var7.field1, var6));
      }
   }

   private static void method15(StringBuilder var0, Map<Integer, String> var1, String var2) {
      for (Entry var4 : var1.entrySet()) {
         int var5 = (Integer)var4.getKey();
         int var6 = GL11.glGetInteger(var5);
         String var7 = (String)var4.getValue();
         var0.append("%-3s%-30s%-40s%n".formatted(var2, var7, var6));
      }
   }

   private static void method16(StringBuilder var0, Map<Integer, String> var1, String var2) {
      for (Entry var4 : var1.entrySet()) {
         int var5 = (Integer)var4.getKey();
         boolean var6 = GL11.glIsEnabled(var5);
         String var7 = (String)var4.getValue();
         var0.append("%-3s%-30s%-40s%n".formatted(var2, var7, var6));
      }
   }

   private static void method17(StringBuilder var0, Map<Integer, OpenGlInfo.Data> var1, String var2) {
      for (Entry var4 : var1.entrySet()) {
         int var5 = (Integer)var4.getKey();
         OpenGlInfo.Data var6 = (OpenGlInfo.Data)var4.getValue();
         OpenGlInfo.Type var7 = var6.field2;
         Buffer var8 = var7.createBuffer.apply(var7.objectSize * var6.field3);
         var7.getData.accept(var5, var8);
         Object var9 = BufferUtils.toArray(var8);
         MemoryUtil.memFree(var8);
         var0.append("%-3s%-20s%-80s%n".formatted(var2, var6.field1, ThreadModuleDump19.arrayToString(var9)));
      }
   }

   private static OpenGlInfo.Data3 method18(int var0) {
      return switch (var0) {
         case 1 -> {
            Builder var6 = ImmutableMap.builder();
            var6.put(32873, OpenGlInfo.Data2.method1("GL_TEXTURE_BINDING_2D", OpenGlInfo.Type2.INTEGER, "GL11.glBindTexture(GL_TEXTURE_2D, %s)"))
               .put(34016, OpenGlInfo.Data2.method1("GL_ACTIVE_TEXTURE", OpenGlInfo.Type2.INTEGER, "GL13.glActiveTexture(%s)"))
               .put(34964, OpenGlInfo.Data2.method1("GL_ARRAY_BUFFER_BINDING", OpenGlInfo.Type2.INTEGER, "GL15.glBindBuffer(GL_ARRAY_BUFFER, %s)"))
               .put(32969, OpenGlInfo.Data2.method1("GL_BLEND_SRC_RGB", OpenGlInfo.Type2.INTEGER, "GL14.glBlendFuncSeparate(%s, blendDst, blendSrcAlpha, blendDstAlpha)"))
               .put(32968, OpenGlInfo.Data2.method1("GL_BLEND_DST_RGB", OpenGlInfo.Type2.INTEGER, "GL14.glBlendFuncSeparate(blendSrc, %s, blendSrcAlpha, blendDstAlpha)"))
               .put(32971, OpenGlInfo.Data2.method1("GL_BLEND_SRC_ALPHA", OpenGlInfo.Type2.INTEGER, "GL14.glBlendFuncSeparate(blendSrc, blendDst, %s, blendDstAlpha)"))
               .put(32970, OpenGlInfo.Data2.method1("GL_BLEND_DST_ALPHA", OpenGlInfo.Type2.INTEGER, "GL14.glBlendFuncSeparate(blendSrc, blendDst, blendSrcAlpha, %s)"));
            Builder var7 = ImmutableMap.builder();
            var7.put(3042, "GL_BLEND")
               .put(3058, "GL_COLOR_LOGIC_OP")
               .put(2884, "GL_CULL_FACE")
               .put(2929, "GL_DEPTH_TEST")
               .put(3024, "GL_DITHER")
               .put(2848, "GL_LINE_SMOOTH")
               .put(32823, "GL_POLYGON_OFFSET_FILL")
               .put(10754, "GL_POLYGON_OFFSET_LINE")
               .put(10753, "GL_POLYGON_OFFSET_POINT")
               .put(2881, "GL_POLYGON_SMOOTH")
               .put(3089, "GL_SCISSOR_TEST")
               .put(2960, "GL_STENCIL_TEST")
               .put(32925, "GL_MULTISAMPLE")
               .put(32926, "GL_SAMPLE_ALPHA_TO_COVERAGE")
               .put(32927, "GL_SAMPLE_ALPHA_TO_ONE")
               .put(32928, "GL_SAMPLE_COVERAGE");
            Builder var3 = ImmutableMap.builder();
            var3.put(3328, "GL_PACK_SWAP_BYTES")
               .put(3329, "GL_PACK_LSB_FIRST")
               .put(3330, "GL_PACK_ROW_LENGTH")
               .put(32876, "GL_PACK_IMAGE_HEIGHT")
               .put(3332, "GL_PACK_SKIP_PIXELS")
               .put(3331, "GL_PACK_SKIP_ROWS")
               .put(32875, "GL_PACK_SKIP_IMAGES")
               .put(3333, "GL_PACK_ALIGNMENT")
               .put(3312, "GL_UNPACK_SWAP_BYTES")
               .put(3313, "GL_UNPACK_LSB_FIRST")
               .put(3314, "GL_UNPACK_ROW_LENGTH")
               .put(32878, "GL_UNPACK_IMAGE_HEIGHT")
               .put(3316, "GL_UNPACK_SKIP_PIXELS")
               .put(3315, "GL_UNPACK_SKIP_ROWS")
               .put(32877, "GL_UNPACK_SKIP_IMAGES")
               .put(3317, "GL_UNPACK_ALIGNMENT");
            Builder var4 = ImmutableMap.builder();
            var4.put(2978, OpenGlInfo.Data.method1("GL_VIEWPORT", OpenGlInfo.Type.INTEGER, 4)).put(3088, OpenGlInfo.Data.method1("GL_SCISSOR_BOX", OpenGlInfo.Type.INTEGER, 4));
            yield new OpenGlInfo.Data3(var6.build(), var7.build(), var3.build(), var4.build());
         }
         case 2 -> {
            Builder var5 = ImmutableMap.builder();
            var5.put(35725, OpenGlInfo.Data2.method1("GL_CURRENT_PROGRAM", OpenGlInfo.Type2.INTEGER, "GL20.glUseProgram(%s)"))
               .put(32777, OpenGlInfo.Data2.method1("GL_BLEND_EQUATION_RGB", OpenGlInfo.Type2.INTEGER, "GL20.glBlendEquationSeparate(%s, blendEquationAlpha)"))
               .put(34877, OpenGlInfo.Data2.method1("GL_BLEND_EQUATION_ALPHA", OpenGlInfo.Type2.INTEGER, "GL20.glBlendEquationSeparate(blendEquationRgb, %s)"));
            yield new OpenGlInfo.Data3(var5.build(), Map.of(), Map.of(), Map.of());
         }
         case 3 -> {
            Builder var1 = ImmutableMap.builder();
            var1.put(35097, OpenGlInfo.Data2.method1("GL_SAMPLER_BINDING", OpenGlInfo.Type2.INTEGER, "GL33.glBindSampler(0, %s)"))
               .put(34229, OpenGlInfo.Data2.method1("GL_VERTEX_ARRAY_BINDING", OpenGlInfo.Type2.INTEGER, "GL30.glBindVertexArray(%s)"));
            Builder var2 = ImmutableMap.builder();
            var2.put(34383, "GL_DEPTH_CLAMP")
               .put(36281, "GL_FRAMEBUFFER_SRGB")
               .put(34370, "GL_PROGRAM_POINT_SIZE")
               .put(35977, "GL_RASTERIZER_DISCARD")
               .put(36433, "GL_SAMPLE_MASK");
            yield new OpenGlInfo.Data3(var1.build(), var2.build(), Map.of(), Map.of());
         }
         default -> null;
      };
   }

   static class Data {
      private final String field1;
      private final OpenGlInfo.Type field2;
      private final int field3;

      public static OpenGlInfo.Data method1(String var0, OpenGlInfo.Type var1, int var2) {
         return new OpenGlInfo.Data(var0, var1, var2);
      }

      @Generated
      public Data(String var1, OpenGlInfo.Type var2, int var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }
   }

   static class Data2 {
      private final String field1;
      private final OpenGlInfo.Type2 field2;
      private final String field3;

      public static OpenGlInfo.Data2 method1(String var0, OpenGlInfo.Type2 var1, String var2) {
         return new OpenGlInfo.Data2(var0, var1, var2);
      }

      @Generated
      public Data2(String var1, OpenGlInfo.Type2 var2, String var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }
   }

   static class Data3 {
      private final Map<Integer, OpenGlInfo.Data2> field1;
      private final Map<Integer, String> field2;
      private final Map<Integer, String> field3;
      private final Map<Integer, OpenGlInfo.Data> field4;

      @Generated
      public Data3(Map<Integer, OpenGlInfo.Data2> var1, Map<Integer, String> var2, Map<Integer, String> var3, Map<Integer, OpenGlInfo.Data> var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }
   }

   static class Data4 {
      private final Map<Integer, Integer> field1 = new HashMap<>();
      private final Map<Integer, Boolean> field2 = new HashMap<>();
      private final Map<Integer, Integer> field3 = new HashMap<>();
      private final Map<Integer, Object> field4 = new HashMap<>();
   }

   enum Type {
      BOOLEAN("MemoryUtil.memAlloc(%s)", 1, MemoryUtil::memAlloc, (var0, var1) -> Bridge.method22().method13(var0, (ByteBuffer)var1)),
      INTEGER("MemoryUtil.memAllocInt(%s)", 4, MemoryUtil::memAllocInt, (var0, var1) -> Bridge.method22().method14(var0, (IntBuffer)var1)),
      FLOAT("MemoryUtil.memAllocFloat(%s)", 4, MemoryUtil::memAllocFloat, (var0, var1) -> Bridge.method22().method15(var0, (FloatBuffer)var1)),
      DOUBLE("MemoryUtil.memAllocDouble(%s)", 8, MemoryUtil::memAllocDouble, (var0, var1) -> Bridge.method22().method16(var0, (DoubleBuffer)var1));

      private final String method;
      private final int objectSize;
      private final IntFunction<Buffer> createBuffer;
      private final BiConsumer<Integer, Buffer> getData;

      @Generated
      public String getMethod() {
         return this.method;
      }

      @Generated
      public int getObjectSize() {
         return this.objectSize;
      }

      @Generated
      public IntFunction<Buffer> getCreateBuffer() {
         return this.createBuffer;
      }

      @Generated
      public BiConsumer<Integer, Buffer> getGetData() {
         return this.getData;
      }

      @Generated
      Type(String var3, int var4, IntFunction<Buffer> var5, BiConsumer<Integer, Buffer> var6) {
         this.method = var3;
         this.objectSize = var4;
         this.createBuffer = var5;
         this.getData = var6;
      }
   }

   enum Type2 {
      BOOLEAN("GL11.glGetBoolean(%s)"),
      INTEGER("GL11.glGetInteger(%s)"),
      DOUBLE("GL11.glGetDouble(%s)"),
      FLOAT("GL11.glGetFloat(%s)"),
      POINTER("GL11.glGetPointer(%s)"),
      STRING("GL11.glGetString(%s)");

      private final String method;

      @Generated
      public String getMethod() {
         return this.method;
      }

      @Generated
      Type2(String var3) {
         this.method = var3;
      }
   }
}
