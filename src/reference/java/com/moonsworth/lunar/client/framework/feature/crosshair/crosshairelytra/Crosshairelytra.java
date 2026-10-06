package com.moonsworth.lunar.client.framework.feature.crosshair.crosshairelytra;

import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.RenderTypeResolver;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.config.option.ColorOption;
import javax.annotation.Nullable;
import lombok.Generated;

public class Crosshairelytra {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "ui/circle.png");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "ui/circle_small.png");
   private static final ResourceLocationBridge field3 = ResourceLocationBridge.create("lunar", "ui/circle_outline.png");
   private static final ResourceLocationBridge field4 = ResourceLocationBridge.create("lunar", "ui/circle_outline_small.png");
   private static final ResourceLocationBridge field5 = ResourceLocationBridge.create("lunar", "ui/triangle.png");
   @Nullable
   private final ColorOption field6;
   private final boolean field7;
   private final boolean field8;

   public int method1(float var1, float var2) {
      return this.method2() ? this.field6.method14((var1 + var2) * 4.0F) : -1;
   }

   private boolean method2() {
      return this.field6 != null && (!this.field7 || this.field8);
   }

   private boolean method3() {
      return this.field7;
   }

   public void method4(MixinHelper_4 var1, float var2, float var3, float var4, float var5) {
      if (this.method3()) {
         var1.method9(
            this.method10(),
            null,
            var2,
            var3,
            var4,
            var5,
            var5x -> var5x.method2(var2 + var4, var3, 0.0)
               .method9(this.method1(var2 + var4, var3))
               .method16()
               .method2(var2, var3, 0.0)
               .method9(this.method1(var2, var3))
               .method16()
               .method2(var2, var3 + var5, 0.0)
               .method9(this.method1(var2, var3 + var5))
               .method16()
               .method2(var2 + var4, var3, 0.0)
               .method9(this.method1(var2 + var4, var3))
               .method16()
               .method2(var2, var3 + var5, 0.0)
               .method9(this.method1(var2, var3 + var5))
               .method16()
               .method2(var2 + var4, var3 + var5, 0.0)
               .method9(this.method1(var2 + var4, var3 + var5))
               .method16()
         );
      } else if (this.method2()) {
         LcuiScreen.method119(var1, var2, var3, var4, var5, this.field6);
      } else {
         var1.method4(var2, var3, var4, var5, -1);
      }
   }

   public void method5(MixinHelper_4 var1, float var2, float var3, float var4, float var5, float var6) {
      this.method4(var1, var2 - var6, var3 - var6, var4 + 2.0F * var6, var6);
      this.method4(var1, var2 - var6, var3 + var5, var4 + 2.0F * var6, var6);
      this.method4(var1, var2 - var6, var3, var6, var5);
      this.method4(var1, var2 + var4, var3, var6, var5);
   }

   public void method6(MixinHelper_4 var1, float var2, float var3, float var4) {
      ResourceLocationBridge var5 = var4 <= 4.0F ? field2 : field1;
      var1.push();
      var1.method38(var2, var3, 0.0F);
      var1.scale(0.5F, 0.5F, 1.0F);
      var4 *= 2.0F;
      var4 -= 0.75F;
      this.method11(var1, var5, -var4 / 2.0F, -var4 / 2.0F, var4, var4, this.method1(var2, var3));
      var1.pop();
   }

   public void method7(MixinHelper_4 var1, float var2, float var3, float var4, float var5) {
      ResourceLocationBridge var6 = var4 <= 4.0F ? field4 : field3;
      var1.push();
      var1.method38(var2, var3, 0.0F);
      var1.scale(0.5F, 0.5F, 1.0F);
      var4 *= 2.0F;
      var4 = Math.round(var4 + 0.5F);
      if (var6 == field3) {
         var5++;
         var4 += 0.25F;
      }

      int var7 = (int)Math.round((var5 - 1.0F) * 8.0F / 4.0);
      var7 = Math.max(1, var7);

      for (int var8 = 0; var8 < var7; var8++) {
         float var9 = var4 + var8;
         this.method11(var1, var6, -var9 / 2.0F, -var9 / 2.0F, var9, var9, this.method1(var2 - var9 / 2.0F, var3 - var9 / 2.0F));
      }

      var1.pop();
   }

   public void method8(MixinHelper_4 var1, float var2, float var3, float var4, float var5, float var6) {
      float var7 = var6 / 2.0F;
      float var8 = Math.min(var2, var4);
      float var9 = Math.min(var3, var5) - var7;
      float var10 = Math.abs(var4 - var2);
      float var11 = Math.abs(var5 - var3) + var6;
      var1.method9(
         this.method10(),
         null,
         var8,
         var9,
         var10,
         var11,
         var6x -> var6x.method2(var2, var3 + var7, 0.0)
            .method9(this.method1(var2, var3 + var7))
            .method16()
            .method2(var4, var5 + var7, 0.0)
            .method9(this.method1(var4, var5 + var7))
            .method16()
            .method2(var4, var5 - var7, 0.0)
            .method9(this.method1(var4, var5 - var7))
            .method16()
            .method2(var2, var3 + var7, 0.0)
            .method9(this.method1(var2, var3 + var7))
            .method16()
            .method2(var4, var5 - var7, 0.0)
            .method9(this.method1(var4, var5 - var7))
            .method16()
            .method2(var2, var3 - var7, 0.0)
            .method9(this.method1(var2, var3 - var7))
            .method16()
      );
   }

   public void method9(MixinHelper_4 var1, float var2, float var3, float var4, float var5, float var6) {
      var4 *= 2.0F;
      var5 *= 2.0F;
      var1.push();
      var1.method38(var2 - var4 / 2.0F, var3 - var5 / 2.0F, 0.0F);

      for (int var7 = 0; var7 < var6 * 2.0F; var7++) {
         float var8 = var7 / 3.0F;
         this.method11(var1, field5, -var8 / 2.0F, -var8 / 2.0F - var7 / 20.0F, var4 + var8, var5 + var8, this.method1(var2, var3));
      }

      var1.pop();
   }

   private RenderLayerBridge method10() {
      return this.method3() ? LunarRenderTypes.field20 : LunarRenderTypes.field19;
   }

   private void method11(MixinHelper_4 var1, ResourceLocationBridge var2, float var3, float var4, float var5, float var6, int var7) {
      RenderTypeResolver var8 = this.method3() ? LunarRenderTypes.field13 : LunarRenderTypes.field36;
      var1.method9(
         var8.get(var2),
         var2,
         var3,
         var4,
         var5,
         var6,
         var5x -> var5x.method2(var3, var4, 0.0)
            .method10(0.0F, 0.0F)
            .method9(var7)
            .method16()
            .method2(var3, var4 + var6, 0.0)
            .method10(0.0F, 1.0F)
            .method9(var7)
            .method16()
            .method2(var3 + var5, var4 + var6, 0.0)
            .method10(1.0F, 1.0F)
            .method9(var7)
            .method16()
            .method2(var3 + var5, var4, 0.0)
            .method10(1.0F, 0.0F)
            .method9(var7)
            .method16()
      );
   }

   @Generated
   public Crosshairelytra(@Nullable ColorOption var1, boolean var2, boolean var3) {
      this.field6 = var1;
      this.field7 = var2;
      this.field8 = var3;
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
