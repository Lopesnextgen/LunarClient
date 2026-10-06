package com.moonsworth.lunar.client.framework.feature.crosshair.crosshairelytra;

import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.RenderTypeLookup;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.config.option.ColorOption;
import javax.annotation.Nullable;
import lombok.Generated;

public class CrosshairShapeDrawer {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "ui/circle.png");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "ui/circle_small.png");
   private static final ResourceLocationBridge field3 = ResourceLocationBridge.create("lunar", "ui/circle_outline.png");
   private static final ResourceLocationBridge field4 = ResourceLocationBridge.create("lunar", "ui/circle_outline_small.png");
   private static final ResourceLocationBridge field5 = ResourceLocationBridge.create("lunar", "ui/triangle.png");
   @Nullable
   private final ColorOption field6;
   private final boolean field7;
   private final boolean field8;

   public int method1(float value1, float value2) {
      return this.method2() ? this.field6.method14((value1 + value2) * 4.0F) : -1;
   }

   private boolean method2() {
      return this.field6 != null && (!this.field7 || this.field8);
   }

   private boolean method3() {
      return this.field7;
   }

   public void method4(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5) {
      if (this.method3()) {
         mixinhelper_41.method9(
            this.method10(),
            null,
            value2,
            value3,
            value4,
            value5,
            arg5x -> arg5x.method2(value2 + value4, value3, 0.0)
               .method9(this.method1(value2 + value4, value3))
               .method16()
               .method2(value2, value3, 0.0)
               .method9(this.method1(value2, value3))
               .method16()
               .method2(value2, value3 + value5, 0.0)
               .method9(this.method1(value2, value3 + value5))
               .method16()
               .method2(value2 + value4, value3, 0.0)
               .method9(this.method1(value2 + value4, value3))
               .method16()
               .method2(value2, value3 + value5, 0.0)
               .method9(this.method1(value2, value3 + value5))
               .method16()
               .method2(value2 + value4, value3 + value5, 0.0)
               .method9(this.method1(value2 + value4, value3 + value5))
               .method16()
         );
      } else if (this.method2()) {
         LcuiScreen.method119(mixinhelper_41, value2, value3, value4, value5, this.field6);
      } else {
         mixinhelper_41.method4(value2, value3, value4, value5, -1);
      }
   }

   public void method5(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5, float value6) {
      this.method4(mixinhelper_41, value2 - value6, value3 - value6, value4 + 2.0F * value6, value6);
      this.method4(mixinhelper_41, value2 - value6, value3 + value5, value4 + 2.0F * value6, value6);
      this.method4(mixinhelper_41, value2 - value6, value3, value6, value5);
      this.method4(mixinhelper_41, value2 + value4, value3, value6, value5);
   }

   public void method6(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4) {
      ResourceLocationBridge horsestats145 = value4 <= 4.0F ? field2 : field1;
      mixinhelper_41.push();
      mixinhelper_41.method38(value2, value3, 0.0F);
      mixinhelper_41.scale(0.5F, 0.5F, 1.0F);
      value4 *= 2.0F;
      value4 -= 0.75F;
      this.method11(mixinhelper_41, horsestats145, -value4 / 2.0F, -value4 / 2.0F, value4, value4, this.method1(value2, value3));
      mixinhelper_41.pop();
   }

   public void method7(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5) {
      ResourceLocationBridge horsestats146 = value4 <= 4.0F ? field4 : field3;
      mixinhelper_41.push();
      mixinhelper_41.method38(value2, value3, 0.0F);
      mixinhelper_41.scale(0.5F, 0.5F, 1.0F);
      value4 *= 2.0F;
      value4 = Math.round(value4 + 0.5F);
      if (horsestats146 == field3) {
         value5++;
         value4 += 0.25F;
      }

      int number7 = (int)Math.round((value5 - 1.0F) * 8.0F / 4.0);
      number7 = Math.max(1, number7);

      for (int index8 = 0; index8 < number7; index8++) {
         float value9 = value4 + index8;
         this.method11(mixinhelper_41, horsestats146, -value9 / 2.0F, -value9 / 2.0F, value9, value9, this.method1(value2 - value9 / 2.0F, value3 - value9 / 2.0F));
      }

      mixinhelper_41.pop();
   }

   public void method8(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5, float value6) {
      float value7 = value6 / 2.0F;
      float value8 = Math.min(value2, value4);
      float value9 = Math.min(value3, value5) - value7;
      float value10 = Math.abs(value4 - value2);
      float value11 = Math.abs(value5 - value3) + value6;
      mixinhelper_41.method9(
         this.method10(),
         null,
         value8,
         value9,
         value10,
         value11,
         arg6x -> arg6x.method2(value2, value3 + value7, 0.0)
            .method9(this.method1(value2, value3 + value7))
            .method16()
            .method2(value4, value5 + value7, 0.0)
            .method9(this.method1(value4, value5 + value7))
            .method16()
            .method2(value4, value5 - value7, 0.0)
            .method9(this.method1(value4, value5 - value7))
            .method16()
            .method2(value2, value3 + value7, 0.0)
            .method9(this.method1(value2, value3 + value7))
            .method16()
            .method2(value4, value5 - value7, 0.0)
            .method9(this.method1(value4, value5 - value7))
            .method16()
            .method2(value2, value3 - value7, 0.0)
            .method9(this.method1(value2, value3 - value7))
            .method16()
      );
   }

   public void method9(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5, float value6) {
      value4 *= 2.0F;
      value5 *= 2.0F;
      mixinhelper_41.push();
      mixinhelper_41.method38(value2 - value4 / 2.0F, value3 - value5 / 2.0F, 0.0F);

      for (int index7 = 0; index7 < value6 * 2.0F; index7++) {
         float value8 = index7 / 3.0F;
         this.method11(mixinhelper_41, field5, -value8 / 2.0F, -value8 / 2.0F - index7 / 20.0F, value4 + value8, value5 + value8, this.method1(value2, value3));
      }

      mixinhelper_41.pop();
   }

   private RenderTypeBridge method10() {
      return this.method3() ? LunarRenderTypes.field20 : LunarRenderTypes.field19;
   }

   private void method11(MixinHelper_4 mixinhelper_41, ResourceLocationBridge horsestats142, float value3, float value4, float value5, float value6, int number7) {
      RenderTypeLookup mixinhelper6_38 = this.method3() ? LunarRenderTypes.field13 : LunarRenderTypes.field36;
      mixinhelper_41.method9(
         mixinhelper6_38.get(horsestats142),
         horsestats142,
         value3,
         value4,
         value5,
         value6,
         arg5x -> arg5x.method2(value3, value4, 0.0)
            .method10(0.0F, 0.0F)
            .method9(number7)
            .method16()
            .method2(value3, value4 + value6, 0.0)
            .method10(0.0F, 1.0F)
            .method9(number7)
            .method16()
            .method2(value3 + value5, value4 + value6, 0.0)
            .method10(1.0F, 1.0F)
            .method9(number7)
            .method16()
            .method2(value3 + value5, value4, 0.0)
            .method10(1.0F, 0.0F)
            .method9(number7)
            .method16()
      );
   }

   @Generated
   public CrosshairShapeDrawer(@Nullable ColorOption lightingextension42221, boolean flag2, boolean flag3) {
      this.field6 = lightingextension42221;
      this.field7 = flag2;
      this.field8 = flag3;
   }

   @Generated
   public boolean method12() {
      return this.field7;
   }

   @Generated
   public boolean method13() {
      return this.field8;
   }
}
