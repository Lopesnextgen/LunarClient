package com.moonsworth.lunar.bridge;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.function.Consumer;
import lombok.Generated;
import org.joml.Quaternionf;

public abstract class AbstractRenderContext implements GlStateControlBridge, Bridge_8 {
   public static boolean field1 = false;
   protected final RenderSystemBridge field2 = Bridge.method42();
   protected final GlStateControlBridge field3;
   protected Bridge2_41 field4;

   public AbstractRenderContext() {
      this.field4 = this.field3 = this.method29();
   }

   protected GlStateControlBridge method29() {
      return new BridgeExtension3_4(this.field2, this.method38());
   }

   public abstract float method28();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   public BridgeExtension2_11 method30() {
      return (BridgeExtension2_11)this;
   }

   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   public BridgeExtension3_5 method31() {
      return (BridgeExtension3_5)this;
   }

   public void method5(@com.moonsworth.lunar.ichor.Annotation2(min = 6) Consumer<BridgeExtension2_11> var1) {
      if (this.method38()) {
         var1.accept((BridgeExtension2_11)this);
      }
   }

   public void method6(@com.moonsworth.lunar.ichor.Annotation2(max = 5) Consumer<BridgeExtension3_5> var1) {
      if (!this.method38()) {
         var1.accept((BridgeExtension3_5)this);
      }
   }

   public void method7(
      @com.moonsworth.lunar.ichor.Annotation2(max = 5) Consumer<BridgeExtension3_5> var1,
      @com.moonsworth.lunar.ichor.Annotation2(min = 6) Consumer<BridgeExtension2_11> var2
   ) {
      if (this.method38()) {
         var2.accept((BridgeExtension2_11)this);
      } else {
         var1.accept((BridgeExtension3_5)this);
      }
   }

   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   public static BridgeExtension3_5 method32() {
      return new BridgeExtension3_5();
   }

   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   public static BridgeExtension3_5 method9(float value) {
      return new BridgeExtension3_5(value);
   }

   public abstract Bridge2_32 method10(RenderLayerBridge var1);

   public Bridge_28 method11(float var1) {
      return this.method12(var1, false);
   }

   public abstract Bridge_28 method12(float var1, boolean var2);

   @Override
   public abstract void translate(double var1, double var3, double var5);

   @Override
   public abstract void scale(float var1, float var2, float var3);

   public abstract void method13(Quaternionf var1);

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      Bridge_8.super.method2(var1, var2, var3, var4);
   }

   @Override
   public void method3(float var1, float var2, float var3) {
      Bridge_8.super.method3(var1, var2, var3);
   }

   @Override
   public abstract void method4(float var1, float var2, float var3, float var4);

   @Override
   public abstract void method5(float var1, float var2, float var3);

   public abstract void pop();

   public abstract void push();

   public void method18(int var1) {
      float var2 = (var1 >> 24 & 0xFF) / 255.0F;
      float var3 = (var1 >> 16 & 0xFF) / 255.0F;
      float var4 = (var1 >> 8 & 0xFF) / 255.0F;
      float var5 = (var1 & 0xFF) / 255.0F;
      this.method25(var3, var4, var5, var2);
   }

   public void method19(float var1, float var2, float var3) {
      this.method25(var1, var2, var3, 1.0F);
   }

   public void method33() {
      this.method25(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public void method34() {
   }

   public void method22(double var1, double var3, double var5, double var7, double var9, double var11) {
      this.field2.method59(var1, var3, var5, var7, var9, var11);
   }

   public void method23(float var1, float var2, float var3, FloatBuffer var4, FloatBuffer var5, IntBuffer intBuffer, FloatBuffer var7) {
      this.field2.method76(var1, var2, var3, var4, var5, intBuffer, var7);
   }

   public void method35() {
      this.field2.method62();
   }

   public void method25(float var1, float var2, float var3, float var4) {
      this.field2.method7(var1, var2, var3, var4);
   }

   public void method26(float var1, float var2) {
      this.field2.method53(var1, var2);
   }

   public void method27(int var1, int var2, int var3, int var4) {
      this.field2.method54(var1, var2, var3, var4);
   }

   public abstract void method36();

   public abstract void method29(float var1, float var2, float var3, float var4);

   public Bridge6_2 method37() {
      return Bridge.method8().method63();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6, onReturn = true)
   public abstract boolean method38();

   @com.moonsworth.lunar.ichor.Annotation2(max = 5, onReturn = true)
   public boolean method39() {
      return !this.method38();
   }

   public void method33(RenderLayerBridge var1) {
   }

   public abstract void method40();

   public abstract void method35(double var1, double var3, double var5, double var7, double var9, double var11);

   public abstract void method36(float var1, float var2, float var3, float var4);

   public abstract void method41();

   public MixinHelper_4 method42() {
      return this.method38() && this.method30().method45().isPresent() && Bridge.getMinecraftVersion().method25()
         ? new ModernGuiGraphicsBridge(this.method30().method45().get())
         : new LegacyGuiGraphicsBridge(this);
   }

   @Generated
   @Override
   public boolean method1() {
      return this.field3.method1();
   }

   @Generated
   @Override
   public boolean method2() {
      return this.field3.method2();
   }

   @Generated
   @Override
   public int method3() {
      return this.field3.method3();
   }

   @Generated
   @Override
   public boolean method4() {
      return this.field3.method4();
   }

   @Generated
   @Override
   public boolean method5() {
      return this.field3.method5();
   }

   @Generated
   @Override
   public boolean method6() {
      return this.field3.method6();
   }

   @Generated
   @Override
   public boolean method7() {
      return this.field3.method7();
   }

   @Generated
   @Override
   public int method8() {
      return this.field3.method8();
   }

   @Generated
   @Override
   public int method9() {
      return this.field3.method9();
   }

   @Generated
   @Override
   public void method1(int var1) {
      this.field4.method1(var1);
   }

   @Generated
   @Override
   public void method2(BridgeType2_9 var1, BridgeType2_9 var2) {
      this.field4.method2(var1, var2);
   }

   @Generated
   @Override
   public void method3(DepthComparison var1, float var2) {
      this.field4.method3(var1, var2);
   }

   @Generated
   @Override
   public void method4(BridgeType2_9 var1, BridgeType2_9 var2, BridgeType2_9 var3, BridgeType2_9 var4) {
      this.field4.method4(var1, var2, var3, var4);
   }

   @Generated
   @Override
   public void method5(boolean var1) {
      this.field4.method5(var1);
   }

   @Generated
   @Override
   public void method6(boolean var1) {
      this.field4.method6(var1);
   }

   @Generated
   @Override
   public void method7(DepthComparison var1) {
      this.field4.method7(var1);
   }

   @Generated
   @Override
   public void method8(BridgeType3_3 var1) {
      this.field4.method8(var1);
   }

   @Generated
   @Override
   public void method9(ShadingModel var1) {
      this.field4.method9(var1);
   }

   @Generated
   @Override
   public void method10() {
      this.field4.method10();
   }

   @Generated
   @Override
   public void method11() {
      this.field4.method11();
   }

   @Generated
   @Override
   public void method12() {
      this.field4.method12();
   }

   @Generated
   @Override
   public void method13() {
      this.field4.method13();
   }

   @Generated
   @Override
   public void method14() {
      this.field4.method14();
   }

   @Generated
   @Override
   public void method15() {
      this.field4.method15();
   }

   @Generated
   @Override
   public void method16() {
      this.field4.method16();
   }

   @Generated
   @Override
   public void method17() {
      this.field4.method17();
   }

   @Generated
   @Override
   public void method18() {
      this.field4.method18();
   }

   @Generated
   @Override
   public void method19() {
      this.field4.method19();
   }

   @Generated
   @Override
   public void method20() {
      this.field4.method20();
   }

   @Generated
   @Override
   public void method21() {
      this.field4.method21();
   }

   @Generated
   @Override
   public void method22() {
      this.field4.method22();
   }

   @Generated
   @Override
   public void method23() {
      this.field4.method23();
   }

   @Generated
   @Override
   public void method24() {
      this.field4.method24();
   }

   @Generated
   @Override
   public void method25() {
      this.field4.method25();
   }

   @Generated
   @Override
   public void method26() {
      this.field4.method26();
   }

   @Generated
   @Override
   public void method27() {
      this.field4.method27();
   }

   @Generated
   @Override
   public void method28(int var1) {
      this.field4.method28(var1);
   }
}
