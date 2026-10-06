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

public class LegacyGuiGraphicsBridge extends MixinHelper_4 {
   private static final Logger logger = LogManager.getLogger("Lunar Client");
   private final AbstractRenderContext field1;

   public LegacyGuiGraphicsBridge(AbstractRenderContext var1) {
      this.field1 = var1;
   }

   @Override
   public void method1(int var1, int var2, int var3, int var4, int var5) {
      if (var3 < var1) {
         int var6 = var1;
         var1 = var3;
         var3 = var6;
      }

      if (var4 < var2) {
         int var7 = var2;
         var2 = var4;
         var4 = var7;
      }

      this.field1
         .method10(LunarRenderTypes.field19)
         .method1()
         .method2(var1, var4, 0.0)
         .method9(var5)
         .method16()
         .method2(var3, var4, 0.0)
         .method9(var5)
         .method16()
         .method2(var1, var2, 0.0)
         .method9(var5)
         .method16()
         .method2(var3, var4, 0.0)
         .method9(var5)
         .method16()
         .method2(var3, var2, 0.0)
         .method9(var5)
         .method16()
         .method2(var1, var2, 0.0)
         .method9(var5)
         .method16()
         .method17(BufferBuildMode.BATCHED);
   }

   @Override
   public void method5(int var1, int var2, int var3, int var4, int var5, int var6) {
      float var7 = (var5 >> 24 & 0xFF) / 255.0F;
      float var8 = (var5 >> 16 & 0xFF) / 255.0F;
      float var9 = (var5 >> 8 & 0xFF) / 255.0F;
      float var10 = (var5 & 0xFF) / 255.0F;
      float var11 = (var6 >> 24 & 0xFF) / 255.0F;
      float var12 = (var6 >> 16 & 0xFF) / 255.0F;
      float var13 = (var6 >> 8 & 0xFF) / 255.0F;
      float var14 = (var6 & 0xFF) / 255.0F;
      Bridge2_32 var15 = this.field1.method10(LunarRenderTypes.field19).method1();
      var15.method2(var3, var2, 0.0);
      var15.method8(var8, var9, var10, var7);
      var15.method16();
      var15.method2(var1, var2, 0.0);
      var15.method8(var8, var9, var10, var7);
      var15.method16();
      var15.method2(var3, var4, 0.0);
      var15.method8(var12, var13, var14, var11);
      var15.method16();
      var15.method2(var1, var2, 0.0);
      var15.method8(var8, var9, var10, var7);
      var15.method16();
      var15.method2(var1, var4, 0.0);
      var15.method8(var12, var13, var14, var11);
      var15.method16();
      var15.method2(var3, var4, 0.0);
      var15.method8(var12, var13, var14, var11);
      var15.method16();
      var15.method17(BufferBuildMode.BATCHED);
   }

   @Override
   public void method9(RenderLayerBridge var1, @Nullable ResourceLocationBridge var2, double var3, double var5, double var7, double var9, Consumer<Bridge2_32> var11) {
      Bridge2_32 var12 = this.field1.method10(var1);
      var12.method1();
      var11.accept(var12);
      var12.method17(BufferBuildMode.BATCHED);
   }

   @Override
   public void method10(Bridge10_2 var1, Component var2, int var3, int var4, int var5, boolean var6) {
      var1.bridge$drawString(this.field1, var2, var3, var4, var5, var6);
   }

   @Override
   public void method12(Bridge10_2 var1, Bridge2_42 var2, int var3, int var4, int var5, boolean var6) {
      var1.bridge$drawString(this.field1, var2, var3, var4, var5, var6);
   }

   @Override
   public void method14(Bridge10_2 var1, Component var2, int var3, int var4, TextColorSource var5, boolean var6) {
      if (!var5.method14()) {
         this.method10(var1, var2, var3, var4, var5.getColor(), var6);
      } else {
         var1.bridge$drawString(this.field1, var2, var3, var4, var5::method1, var6);
      }
   }

   @Override
   public void method16(Bridge10_2 var1, Bridge2_42 var2, int var3, int var4, TextColorSource var5, boolean var6) {
      if (!var5.method14()) {
         this.method12(var1, var2, var3, var4, var5.getColor(), var6);
      } else {
         var1.bridge$drawString(this.field1, var2, var3, var4, var5::method1, var6);
      }
   }

   @Override
   public void method18(Bridge10_2 var1, String var2, int var3, int var4, int var5, boolean var6) {
      var1.bridge$drawString(this.field1, var2, var3, var4, var5, var6);
   }

   @Override
   public void method20(Bridge10_2 var1, String var2, int var3, int var4, TextColorSource var5, boolean var6) {
      if (!var5.method14()) {
         this.method18(var1, var2, var3, var4, var5.getColor(), var6);
      } else {
         var1.bridge$drawString(this.field1, var2, var3, var4, var5::method1, var6);
      }
   }

   @Override
   public void method22(Bridge10_2 var1, String var2, int var3, int var4, int var5, boolean var6) {
      this.method18(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void method24(ResourceLocationBridge var1, int var2, int var3, int var4, int var5, int var6) {
      this.method28(
         () -> {
            this.field1
               .method10(LunarRenderTypes.field33.get(var1))
               .method1()
               .method2(var2, var3, 0.0)
               .method10(0.0F, 0.0F)
               .method9(var6)
               .method16()
               .method2(var2, var3 + var5, 0.0)
               .method10(0.0F, 1.0F)
               .method9(var6)
               .method16()
               .method2(var2 + var4, var3 + var5, 0.0)
               .method10(1.0F, 1.0F)
               .method9(var6)
               .method16()
               .method2(var2 + var4, var3, 0.0)
               .method10(1.0F, 0.0F)
               .method9(var6)
               .method16()
               .method17(BufferBuildMode.BATCHED);
            this.field1.method5(var0 -> var0.method48());
         }
      );
   }

   @Override
   public void method25(@NotNull ResourceLocationBridge var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10) {
      this.method14(LunarRenderTypes.field33, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }

   @Override
   public void method26(@NotNull ResourceLocationBridge var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10) {
      this.method14(LunarRenderTypes.field32, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }

   private void method14(
      RenderTypeResolver var1,
      @NotNull ResourceLocationBridge var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      int var11
   ) {
      this.method28(
         () -> {
            float var12 = 1.0F / var9;
            float var13 = 1.0F / var10;
            this.field1
               .method10(var1.get(var2))
               .method1()
               .method2(var3, var4 + var8, 0.0)
               .method10(var5 * var12, (var6 + var8) * var13)
               .method9(var11)
               .method16()
               .method2(var3 + var7, var4 + var8, 0.0)
               .method10((var5 + var7) * var12, (var6 + var8) * var13)
               .method9(var11)
               .method16()
               .method2(var3 + var7, var4, 0.0)
               .method10((var5 + var7) * var12, var6 * var13)
               .method9(var11)
               .method16()
               .method2(var3, var4, 0.0)
               .method10(var5 * var12, var6 * var13)
               .method9(var11)
               .method16()
               .method17(BufferBuildMode.BATCHED);
            this.field1.method5(var0 -> var0.method48());
         }
      );
   }

   @Override
   public void method31(float var1, float var2, float var3, float var4, float var5, int var6) {
      this.method28(() -> {
         Bridge_28 var7 = this.field1.method12(var5, true);
         var7.method1(var6);
         var7.method3(var1, var2, 0.0, var3, var4, 0.0);
         var7.end();
         this.field1.method5(var0 -> var0.method48());
      });
   }

   @Override
   public void method32(float var1, int var2, Consumer<LineSegmentSink> var3) {
      Bridge_28 var4 = this.field1.method11(var1);
      var4.method1(var2);
      var3.accept((var1x, var2x, var3x, var4x) -> var4.method3(var1x, var2x, 0.0, var3x, var4x, 0.0));
      var4.end();
   }

   @Override
   public void method33(float var1, float var2, int var3, int var4, int var5, int[] var6, int var7) {
      Bridge2_32 var8 = this.field1.method10(LunarRenderTypes.field19).method1();
      float var9 = var1;
      float var10 = var2 + var4;

      for (int var14 : var6) {
         var9 += var3;
         float var15 = var9 + 1.0F;
         float var16 = var2 + var4 - (float)var14 / var7 * var4;
         var8.method2(var9, var10, 0.0)
            .method9(var5)
            .method16()
            .method2(var15, var10, 0.0)
            .method9(var5)
            .method16()
            .method2(var9, var16, 0.0)
            .method9(var5)
            .method16()
            .method2(var15, var10, 0.0)
            .method9(var5)
            .method16()
            .method2(var15, var16, 0.0)
            .method9(var5)
            .method16()
            .method2(var9, var16, 0.0)
            .method9(var5)
            .method16();
      }

      var8.method17(BufferBuildMode.BATCHED);
   }

   @Override
   public void method35(ItemStackBridge var1, int var2, int var3, boolean var4) {
      Bridge5_12 var5 = Bridge.method9();
      this.method29().method20();
      this.method29().method18();
      if (!Bridge.getMinecraftVersion().method19()) {
         Bridge.method14().method2();
      }

      var5.bridge$getRenderItem().bridge$renderItemAndEffectIntoGUI(this.method29(), var1, var2, var3);
      this.method29().method21();
      this.method29().method15();
      this.method29().method19();
   }

   @Override
   public void method36(ItemStackBridge var1, int var2, int var3, boolean var4, Bridge5_12 var5) {
      var5.bridge$getRenderItem().bridge$renderItemAndEffectIntoGUI(this.field1, var1, var2, var3, var4);
   }

   @Override
   public void push() {
      this.field1.push();
   }

   @Override
   public void pop() {
      this.field1.pop();
   }

   @Override
   public void method38(float var1, float var2, float var3) {
      this.field1.translate(var1, var2, var3);
   }

   @Override
   public void scale(float var1, float var2, float var3) {
      this.field1.scale(var1, var2, var3);
   }

   @Override
   public void method41(float var1) {
      this.field1.method2(var1, 0.0F, 0.0F, 1.0F);
   }

   @Override
   public void method44(Consumer<LegacyGuiGraphicsBridge> var1) {
      var1.accept(this);
   }

   @Override
   public void method45(Consumer<ModernGuiGraphicsBridge> var1) {
   }

   @Override
   public AbstractRenderContext method48() {
      return this.field1;
   }

   @Override
   public LegacyGuiGraphicsBridge method46() {
      return this;
   }

   @Override
   public ModernGuiGraphicsBridge method47() {
      throw new IllegalStateException();
   }

   @Override
   public Optional<Bridge8_2> method49() {
      return this.field1.method38() ? this.field1.method30().method45() : Optional.empty();
   }

   private void method28(Runnable var1) {
      boolean var2 = this.field1.method4();
      if (var2) {
         this.field1.method19();
      }

      var1.run();
      if (var2) {
         this.field1.method18();
      }
   }

   @Generated
   public AbstractRenderContext method29() {
      return this.field1;
   }
}
