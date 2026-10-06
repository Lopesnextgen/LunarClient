package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Optional;
import java.util.function.Consumer;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@com.moonsworth.lunar.ichor.Annotation2(min = 30)
public class ModernGuiGraphicsBridge extends MixinHelper_4 {
   private static final Logger field1 = LogManager.getLogger("Lunar Client");
   private final Bridge8_2 field2;
   private final Bridge5_16 field3;

   public ModernGuiGraphicsBridge(Bridge8_2 var1) {
      this.field2 = var1;
      this.field3 = var1.bridge$getPoseStack();
   }

   @Override
   public void method1(int var1, int var2, int var3, int var4, int var5) {
      this.field2.bridge$fill$v1_20_0(var1, var2, var3, var4, var5);
   }

   @Override
   public void method5(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.field2.bridge$fillGradient$v1_20_0(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void method9(RenderLayerBridge var1, @Nullable ResourceLocationBridge var2, double var3, double var5, double var7, double var9, Consumer<Bridge2_32> var11) {
      this.field2.bridge$submitDynamic$v1_21_6(var1.bridge$getRenderPipeline(), var2, var3, var5, var7, var9, var11);
   }

   @Override
   public void method10(Bridge10_2 var1, Component var2, int var3, int var4, int var5, boolean var6) {
      this.field2.method1(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void method12(Bridge10_2 var1, Bridge2_42 var2, int var3, int var4, int var5, boolean var6) {
      this.field2.bridge$drawString$v1_20_0(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void method14(Bridge10_2 var1, Component var2, int var3, int var4, TextColorSource var5, boolean var6) {
      if (!var5.method14()) {
         this.method10(var1, var2, var3, var4, var5.getColor(), var6);
      } else {
         MixinHelper2_12.method1(var5::method1);

         try {
            this.method10(var1, var2, var3, var4, var5.method1(var3 + var4), var6);
         } finally {
            MixinHelper2_12.clear();
         }
      }
   }

   @Override
   public void method16(Bridge10_2 var1, Bridge2_42 var2, int var3, int var4, TextColorSource var5, boolean var6) {
      if (!var5.method14()) {
         this.method12(var1, var2, var3, var4, var5.getColor(), var6);
      } else {
         MixinHelper2_12.method1(var5::method1);

         try {
            this.method12(var1, var2, var3, var4, var5.method1(var3 + var4), var6);
         } finally {
            MixinHelper2_12.clear();
         }
      }
   }

   @Override
   public void method18(Bridge10_2 var1, String var2, int var3, int var4, int var5, boolean var6) {
      this.field2.method1(var1, Component.text(var2), var3, var4, var5, var6);
   }

   @Override
   public void method20(Bridge10_2 var1, String var2, int var3, int var4, TextColorSource var5, boolean var6) {
      if (!var5.method14()) {
         this.method18(var1, var2, var3, var4, var5.getColor(), var6);
      } else {
         MixinHelper2_12.method1(var5::method1);

         try {
            this.method18(var1, var2, var3, var4, var5.method1(var3 + var4), var6);
         } finally {
            MixinHelper2_12.clear();
         }
      }
   }

   @Override
   public void method22(Bridge10_2 var1, String var2, int var3, int var4, int var5, boolean var6) {
      this.field2.bridge$drawStringNoBidi$v1_20_0(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void method24(ResourceLocationBridge var1, int var2, int var3, int var4, int var5, int var6) {
      this.field2.bridge$blit$v1_21_6(MixinHelper_2.field21, var1, var2, var3, 0.0F, 0.0F, var4, var5, var4, var5, var6);
   }

   @Override
   public void method25(@NotNull ResourceLocationBridge var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10) {
      this.field2
         .bridge$blit$v1_21_6(MixinHelper_2.field21, var1, (int)var2, (int)var3, (int)var4, (int)var5, (int)var6, (int)var7, (int)var8, (int)var9, var10);
   }

   @Override
   public void method26(@NotNull ResourceLocationBridge var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10) {
      this.field2.bridge$blit$v1_21_6(MixinHelper_2.field36, var1, (int)var2, (int)var3, var4, var5, (int)var6, (int)var7, (int)var8, (int)var9, var10);
   }

   @Override
   public void method31(float var1, float var2, float var3, float var4, float var5, int var6) {
      float var7 = var3 - var1;
      float var8 = var4 - var2;
      double var9 = Math.sqrt(var7 * var7 + var8 * var8);
      double var11 = Math.atan2(var8, var7);
      this.push();
      this.method39(var1, var2);
      this.method41((float)var11);
      this.method1((int)(-var5 / 2.0F), (int)(-var5 / 2.0F), (int)(var9 + var5 / 2.0F), (int)(var5 / 2.0F), var6);
      this.pop();
   }

   @Override
   public void method32(float var1, int var2, Consumer<LineSegmentSink> var3) {
      var3.accept((var3x, var4, var5, var6) -> this.method31(var3x, var4, var5, var6, var1, var2));
   }

   @Override
   public void method33(float var1, float var2, int var3, int var4, int var5, int[] var6, int var7) {
      int[] var8 = new int[var6.length];

      for (int var9 = 0; var9 < var6.length; var9++) {
         var8[var9] = (int)((double)var6[var9] / var7 * var4);
      }

      this.field2.bridge$submitGraph$v1_21_6((int)var1, (int)var2, var8, var4, var3, var5);
   }

   @Override
   public void method35(ItemStackBridge var1, int var2, int var3, boolean var4) {
      if (var4) {
         this.field2.bridge$drawItemSharp$v1_21_6(var1, var2, var3);
      } else {
         this.field2.bridge$drawItem$1_20_0(var1, var2, var3);
      }
   }

   @Override
   public void method36(ItemStackBridge var1, int var2, int var3, boolean var4, Bridge5_12 var5) {
      this.field2.bridge$drawItem$1_20_0(var1, var2, var3);
      if (var4) {
         this.field2.bridge$drawItemDecorations$v1_20_0(var1, var2, var3);
      }
   }

   @Override
   public void push() {
      this.field3.bridge$pushPose();
   }

   @Override
   public void pop() {
      this.field3.bridge$popPose();
   }

   @Override
   public void method38(float var1, float var2, float var3) {
      this.field3.bridge$translate(var1, var2, var3);
   }

   @Override
   public void scale(float var1, float var2, float var3) {
      this.field3.bridge$scale(var1, var2, var3);
   }

   @Override
   public void method41(float var1) {
      this.field3.bridge$rotate(0.0F, 0.0F, var1);
   }

   @Override
   public void method44(Consumer<LegacyGuiGraphicsBridge> var1) {
   }

   @Override
   public void method45(Consumer<ModernGuiGraphicsBridge> var1) {
      var1.accept(this);
   }

   @Override
   public LegacyGuiGraphicsBridge method46() {
      throw new IllegalStateException();
   }

   @Override
   public AbstractRenderContext method48() {
      return BridgeExtension2_11.method50()
         .method1(this.field2.bridge$getPoseStack())
         .method3(Bridge.method9().bridge$getRenderBuffers().bridge$bufferSource())
         .method5(Bridge.method8().method92())
         .method7();
   }

   @Override
   public ModernGuiGraphicsBridge method47() {
      return this;
   }

   @Override
   public Optional<Bridge8_2> method49() {
      return Optional.of(this.field2);
   }

   public void method27(int var1, int var2, int var3, int var4) {
      this.field2.bridge$scissor$1_20_0(var1, var2, var3, var4);
   }

   public void method28() {
      this.field2.bridge$stopScissor$1_20_0();
   }

   public int[] method29() {
      return this.field2.bridge$peekScissor$v1_21_6();
   }

   public void method30(int var1, int var2, int var3, int var4) {
      this.field2.bridge$scissorAbsolute$v1_21_6(var1, var2, var3, var4);
   }

   @Generated
   public Bridge8_2 method31() {
      return this.field2;
   }

   @Generated
   public Bridge5_16 method32() {
      return this.field3;
   }
}
