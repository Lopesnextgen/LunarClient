package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.ShadeModel;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import java.util.ArrayList;
import java.util.List;

public class LineBatchRenderer {
   private static DrawBufferBridge field1;
   private static List<LineBatchRenderer.Data> field2;
   private static float field3;
   private static float field4;
   private static float field5;
   private static float field6;

   public LineBatchRenderer() {
   }

   public static void method1(float value0, float value1, float value2, float value3, RewindhandlersExtension rewindhandlersextension4) {
      field2.add(new LineBatchRenderer.Data(value0, value1, value2, value3, rewindhandlersextension4));
      if (value0 < field3) {
         field3 = value0;
      }

      if (value1 < field4) {
         field4 = value1;
      }

      if (value0 + value2 > field5) {
         field5 = value0 + value2;
      }

      if (value1 + value3 > field6) {
         field6 = value1 + value3;
      }
   }

   public static void method2(float value0, float value1, float value2, float value3, int number4) {
      field1.method2(value0, value1 + value3, 0.0)
         .method9(number4)
         .method16()
         .method2(value0 + value2, value1 + value3, 0.0)
         .method9(number4)
         .method16()
         .method2(value0 + value2, value1, 0.0)
         .method9(number4)
         .method16()
         .method2(value0, value1, 0.0)
         .method9(number4)
         .method16();
   }

   public static void method3(AbstractRenderContext bridgeextension_90) {
      bridgeextension_90.method21();
      bridgeextension_90.method13();
      bridgeextension_90.method9(ShadeModel.GL_SMOOTH);
      field1 = bridgeextension_90.method10(LunarRenderTypes.field31).method1();
   }

   public static void method4(AbstractRenderContext bridgeextension_90) {
      field1.method17(BufferMode.IMMEDIATE);
      field1 = null;
      bridgeextension_90.method12();
      bridgeextension_90.method20();
      bridgeextension_90.method33();
   }

   public static void method5(MixinHelper_4 mixinhelper_40) {
      field3 = Float.MAX_VALUE;
      field4 = Float.MAX_VALUE;
      field5 = -Float.MAX_VALUE;
      field6 = -Float.MAX_VALUE;
      field2 = new ArrayList<>();
   }

   public static void method6(MixinHelper_4 mixinhelper_40) {
      if (field2.isEmpty()) {
         field2 = null;
      } else {
         for (LineBatchRenderer.Data data3 : field2) {
            LcuiScreen.method119(mixinhelper_40, data3.field1, data3.field2, data3.w, data3.field3, data3.field4);
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

      private Data(float value1, float value2, float value3, float value4, RewindhandlersExtension rewindhandlersextension5) {
         this.field1 = value1;
         this.field2 = value2;
         this.w = value3;
         this.field3 = value4;
         this.field4 = rewindhandlersextension5;
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
