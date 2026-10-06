package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Optional;
import java.util.function.Consumer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class MixinHelper_4 {
   public abstract void method1(int var1, int var2, int var3, int var4, int var5);

   public void method2(float var1, float var2, float var3, float var4, int var5) {
      if (var3 < var1) {
         float var6 = var1;
         var1 = var3;
         var3 = var6;
      }

      if (var4 < var2) {
         float var7 = var2;
         var2 = var4;
         var4 = var7;
      }

      this.push();
      this.method39(var1, var2);
      this.method40(var3 - var1, var4 - var2);
      this.method1(0, 0, 1, 1, var5);
      this.pop();
   }

   public void method3(int var1, int var2, int var3, int var4, int var5) {
      this.method1(var1, var2, var1 + var3, var2 + var4, var5);
   }

   public void method4(float var1, float var2, float var3, float var4, int var5) {
      this.method2(var1, var2, var1 + var3, var2 + var4, var5);
   }

   public abstract void method5(int var1, int var2, int var3, int var4, int var5, int var6);

   public void method6(float var1, float var2, float var3, float var4, int var5, int var6) {
      if (var5 == var6) {
         this.method2(var1, var2, var3, var4, var5);
      } else {
         this.method9(LunarRenderTypes.field19, null, var1, var2, var3 - var1, var4 - var2, var6x -> {
            var6x.method2(var3, var2, 0.0).method9(var5).method16();
            var6x.method2(var1, var2, 0.0).method9(var5).method16();
            var6x.method2(var1, var4, 0.0).method9(var6).method16();
            var6x.method2(var3, var2, 0.0).method9(var5).method16();
            var6x.method2(var1, var4, 0.0).method9(var6).method16();
            var6x.method2(var3, var4, 0.0).method9(var6).method16();
         });
      }
   }

   public void method7(int var1, int var2, int var3, int var4, int var5, int var6) {
      if (var5 == var6) {
         this.method1(var1, var2, var3, var4, var5);
      } else {
         this.method8(var1, var2, var3, var4, var5, var6);
      }
   }

   public void method8(float var1, float var2, float var3, float var4, int var5, int var6) {
      if (var5 == var6) {
         this.method2(var1, var2, var3, var4, var5);
      } else {
         this.method9(LunarRenderTypes.field19, null, var1, var2, var3 - var1, var4 - var2, var6x -> {
            var6x.method2(var3, var2, 0.0).method9(var6).method16();
            var6x.method2(var1, var2, 0.0).method9(var5).method16();
            var6x.method2(var1, var4, 0.0).method9(var5).method16();
            var6x.method2(var3, var2, 0.0).method9(var6).method16();
            var6x.method2(var1, var4, 0.0).method9(var5).method16();
            var6x.method2(var3, var4, 0.0).method9(var6).method16();
         });
      }
   }

   public abstract void method9(RenderLayerBridge var1, @Nullable ResourceLocationBridge var2, double var3, double var5, double var7, double var9, Consumer<Bridge2_32> var11);

   public abstract void method10(Bridge10_2 var1, Component var2, int var3, int var4, int var5, boolean var6);

   public void method11(Bridge10_2 var1, Component var2, float var3, float var4, int var5, boolean var6) {
      this.push();
      this.method39(var3, var4);
      this.method10(var1, var2, 0, 0, var5, var6);
      this.pop();
   }

   public abstract void method12(Bridge10_2 var1, Bridge2_42 var2, int var3, int var4, int var5, boolean var6);

   public void method13(Bridge10_2 var1, Bridge2_42 var2, float var3, float var4, int var5, boolean var6) {
      this.push();
      this.method39(var3, var4);
      this.method12(var1, var2, 0, 0, var5, var6);
      this.pop();
   }

   public abstract void method14(Bridge10_2 var1, Component var2, int var3, int var4, TextColorSource var5, boolean var6);

   public void method15(Bridge10_2 var1, Component var2, float var3, float var4, TextColorSource var5, boolean var6) {
      int var7 = (int)var3;
      int var8 = (int)var4;
      this.push();
      this.method39(var3 - var7, var4 - var8);
      this.method14(var1, var2, var7, var8, var5, var6);
      this.pop();
   }

   public abstract void method16(Bridge10_2 var1, Bridge2_42 var2, int var3, int var4, TextColorSource var5, boolean var6);

   public void method17(Bridge10_2 var1, Bridge2_42 var2, float var3, float var4, TextColorSource var5, boolean var6) {
      int var7 = (int)var3;
      int var8 = (int)var4;
      this.push();
      this.method39(var3 - var7, var4 - var8);
      this.method16(var1, var2, var7, var8, var5, var6);
      this.pop();
   }

   public abstract void method18(Bridge10_2 var1, String var2, int var3, int var4, int var5, boolean var6);

   public void method19(Bridge10_2 var1, String var2, float var3, float var4, int var5, boolean var6) {
      this.push();
      this.method39(var3, var4);
      this.method18(var1, var2, 0, 0, var5, var6);
      this.pop();
   }

   public abstract void method20(Bridge10_2 var1, String var2, int var3, int var4, TextColorSource var5, boolean var6);

   public void method21(Bridge10_2 var1, String var2, float var3, float var4, TextColorSource var5, boolean var6) {
      int var7 = (int)var3;
      int var8 = (int)var4;
      this.push();
      this.method39(var3 - var7, var4 - var8);
      this.method20(var1, var2, var7, var8, var5, var6);
      this.pop();
   }

   public abstract void method22(Bridge10_2 var1, String var2, int var3, int var4, int var5, boolean var6);

   public void method23(Bridge10_2 var1, String var2, float var3, float var4, int var5, boolean var6) {
      this.push();
      this.method39(var3, var4);
      this.method22(var1, var2, 0, 0, var5, var6);
      this.pop();
   }

   public abstract void method24(ResourceLocationBridge var1, int var2, int var3, int var4, int var5, int var6);

   public abstract void method25(
      @NotNull ResourceLocationBridge var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10
   );

   public abstract void method26(
      @NotNull ResourceLocationBridge var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10
   );

   public void method27(Bridge10_2 var1, Component var2, int var3, int var4, int var5, boolean var6) {
      this.method11(var1, var2, var3 - var1.bridge$getStringWidth(var2) / 2.0F, var4, var5, var6);
   }

   public void method28(Bridge10_2 var1, String var2, int var3, int var4, int var5, boolean var6) {
      this.method19(var1, var2, var3 - var1.bridge$getStringWidth(var2) / 2.0F, var4, var5, var6);
   }

   public void method29(Bridge10_2 var1, String var2, float var3, float var4, int var5, boolean var6) {
      this.method19(var1, var2, var3 - var1.bridge$getStringWidth(var2) / 2.0F, var4, var5, var6);
   }

   public void method30(Bridge10_2 var1, String var2, int var3, int var4, int var5, boolean var6) {
      this.method23(var1, var2, var3 - var1.bridge$getStringWidth(var2) / 2.0F, var4, var5, var6);
   }

   public abstract void method31(float var1, float var2, float var3, float var4, float var5, int var6);

   public abstract void method32(float var1, int var2, Consumer<LineSegmentSink> var3);

   public abstract void method33(float var1, float var2, int var3, int var4, int var5, int[] var6, int var7);

   public void method34(ItemStackBridge var1, int var2, int var3, Bridge5_12 var4) {
      this.method35(var1, var2, var3, false);
   }

   public abstract void method35(ItemStackBridge var1, int var2, int var3, boolean var4);

   public abstract void method36(ItemStackBridge var1, int var2, int var3, boolean var4, Bridge5_12 var5);

   public void method37(ItemStackBridge var1, float var2, float var3, Bridge5_12 var4) {
      this.push();
      this.method39(var2, var3);
      this.method34(var1, 0, 0, var4);
      this.pop();
   }

   public abstract void push();

   public abstract void pop();

   public abstract void method38(float var1, float var2, float var3);

   public void method39(float var1, float var2) {
      this.method38(var1, var2, 0.0F);
   }

   public abstract void scale(float var1, float var2, float var3);

   public void method40(float var1, float var2) {
      this.scale(var1, var2, 1.0F);
   }

   public abstract void method41(float var1);

   public void method42(float var1) {
      this.method41((float)Math.toRadians(var1));
   }

   public float method43() {
      return Bridge.method9().bridge$getTimer().method1();
   }

   public abstract void method44(Consumer<LegacyGuiGraphicsBridge> var1);

   public abstract void method45(@com.moonsworth.lunar.ichor.Annotation2(min = 30) Consumer<ModernGuiGraphicsBridge> var1);

   public abstract LegacyGuiGraphicsBridge method46();

   public abstract ModernGuiGraphicsBridge method47();

   public abstract AbstractRenderContext method48();

   public abstract Optional<Bridge8_2> method49();

   public boolean method50() {
      return this instanceof LegacyGuiGraphicsBridge;
   }
}
