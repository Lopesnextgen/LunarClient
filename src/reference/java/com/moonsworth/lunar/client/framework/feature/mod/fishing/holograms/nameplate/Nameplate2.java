package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.ShadingModel;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import java.util.ArrayList;
import java.util.List;

public class Nameplate2 {
   private static Bridge2_32 field1;
   private static List<Nameplate2.Data> field2;
   private static float field3;
   private static float field4;
   private static float field5;
   private static float field6;

   public static void method1(float var0, float var1, float var2, float var3, RewindhandlersExtension var4) {
      field2.add(new Nameplate2.Data(var0, var1, var2, var3, var4));
      if (var0 < field3) {
         field3 = var0;
      }

      if (var1 < field4) {
         field4 = var1;
      }

      if (var0 + var2 > field5) {
         field5 = var0 + var2;
      }

      if (var1 + var3 > field6) {
         field6 = var1 + var3;
      }
   }

   public static void method2(float var0, float var1, float var2, float var3, int var4) {
      field1.method2(var0, var1 + var3, 0.0)
         .method9(var4)
         .method16()
         .method2(var0 + var2, var1 + var3, 0.0)
         .method9(var4)
         .method16()
         .method2(var0 + var2, var1, 0.0)
         .method9(var4)
         .method16()
         .method2(var0, var1, 0.0)
         .method9(var4)
         .method16();
   }

   public static void method3(AbstractRenderContext var0) {
      var0.method21();
      var0.method13();
      var0.method9(ShadingModel.GL_SMOOTH);
      field1 = var0.method10(LunarRenderTypes.field31).method1();
   }

   public static void method4(AbstractRenderContext var0) {
      field1.method17(BufferBuildMode.IMMEDIATE);
      field1 = null;
      var0.method12();
      var0.method20();
      var0.method33();
   }

   public static void method5(MixinHelper_4 var0) {
      field3 = Float.MAX_VALUE;
      field4 = Float.MAX_VALUE;
      field5 = -Float.MAX_VALUE;
      field6 = -Float.MAX_VALUE;
      field2 = new ArrayList<>();
   }

   public static void method6(MixinHelper_4 var0) {
      if (field2.isEmpty()) {
         field2 = null;
      } else {
         for (Nameplate2.Data var3 : field2) {
            LcuiScreen.method119(var0, var3.field1, var3.field2, var3.w, var3.field3, var3.field4);
         }

         field2 = null;
      }
   }

   private class Data {
      private final float field1;
      private final float field2;
      private final float w;
      private final float field3;
      private final RewindhandlersExtension field4;

      private Data(float var1, float var2, float var3, float var4, RewindhandlersExtension var5) {
         this.field1 = var1;
         this.field2 = var2;
         this.w = var3;
         this.field3 = var4;
         this.field4 = var5;
      }

      public float x() {
         return this.field1;
      }

      public float y() {
         return this.field2;
      }

      public float method1() {
         return this.w;
      }

      public float h() {
         return this.field3;
      }

      public RewindhandlersExtension method2() {
         return this.field4;
      }
   }
}
