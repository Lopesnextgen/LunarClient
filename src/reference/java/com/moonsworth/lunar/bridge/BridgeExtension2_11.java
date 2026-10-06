package com.moonsworth.lunar.bridge;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import lombok.Generated;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

@com.moonsworth.lunar.ichor.Annotation2(min = 6)
public class BridgeExtension2_11 extends AbstractRenderContext {
   public static final int field5 = 15728880;
   @NonNull
   private Bridge5_16 field6;
   private Stack<Set<RenderLayerBridge>> field7;
   private BatchingBufferSourceBridge field8;
   @Nullable
   private Bridge8_2 field9;
   private Integer field10;
   private final float field11;

   protected BridgeExtension2_11(@NonNull Bridge5_16 var1, BatchingBufferSourceBridge var2, Bridge8_2 var3, Integer var4) {
      this(var1, new Stack<>(), var2, var3, var4, Bridge.method9().bridge$getTimer().method1());
      if (var1 == null) {
         throw new NullPointerException("poseStack is marked non-null but is null");
      }
   }

   protected BridgeExtension2_11(@NonNull Bridge5_16 var1, Stack<Set<RenderLayerBridge>> var2, BatchingBufferSourceBridge var3, Bridge8_2 var4, Integer var5, float var6) {
      if (var1 == null) {
         throw new NullPointerException("poseStack is marked non-null but is null");
      }

      this.field11 = var6;
      this.field7 = var2 == null ? new Stack<>() : var2;
      this.field6 = var1;
      this.field8 = var3;
      this.field9 = var4;
      this.field10 = var5;
   }

   public Optional<BatchingBufferSourceBridge> method43() {
      return Optional.ofNullable(this.field8);
   }

   public Optional<Bridge4_6> method2(RenderLayerBridge var1) {
      if (this.field8 == null) {
         return Optional.empty();
      }

      if (!this.field7.isEmpty()) {
         this.field7.peek().add(var1);
      }

      return this.field8.bridge$getBuffer(var1);
   }

   public void method44() {
      if (this.field8 instanceof Bridge2_22 var1) {
         var1.bridge$pushTracker(var1x -> {
            if (!this.field7.isEmpty()) {
               this.field7.peek().add(var1x);
            }
         });
      }

      this.field7.push(new HashSet<>());
   }

   public Optional<Bridge8_2> method45() {
      return Optional.ofNullable(this.field9);
   }

   public Optional<Integer> method46() {
      return Optional.ofNullable(this.field10);
   }

   public void method6(Integer var1) {
      this.field10 = var1;
   }

   @Override
   public Bridge2_32 method10(@NotNull RenderLayerBridge var1) {
      Bridge4_6 var2 = this.method2(var1)
         .orElseGet(
            () -> Bridge.method9()
               .bridge$getRenderBuffers()
               .bridge$bufferSource()
               .bridge$getBuffer(var1)
               .orElseThrow(() -> new IllegalStateException("Failed to build buffer for render type " + var1))
         );
      return new Bridge2Handler2_2(var2, this.field6);
   }

   public boolean isOutlineBufferSource() {
      return this.field8 instanceof BufferSourceDecorator var4
         ? var4.unwrap() instanceof Bridge17Extension_2 var6 && var6.isOutlineBufferSource()
         : this.field8 instanceof Bridge17Extension_2 var1 && var1.isOutlineBufferSource();
   }

   @Override
   public Bridge_28 method12(float var1, boolean var2) {
      return this.field2.method83().method1(this, var1, var2);
   }

   @Override
   public void translate(double var1, double var3, double var5) {
      this.field6.bridge$translate(var1, var3, var5);
   }

   @Override
   public void scale(float var1, float var2, float var3) {
      this.field6.bridge$scale(var1, var2, var3);
   }

   @Override
   public void method13(Quaternionf var1) {
      this.field6.bridge$mulPose(var1);
   }

   @Override
   public void method4(float var1, float var2, float var3, float var4) {
      this.field6.bridge$rotateDegrees(var1 * var2, var1 * var3, var1 * var4);
   }

   @Override
   public void method5(float var1, float var2, float var3) {
      this.field6.bridge$rotateDegrees(var1, var2, var3);
   }

   @Override
   public void pop() {
      this.field6.bridge$popPose();
   }

   @Override
   public void push() {
      this.field6.bridge$pushPose();
   }

   @Override
   public void method36() {
      this.field6.bridge$loadIdentity();
   }

   @Override
   public void method29(float var1, float var2, float var3, float var4) {
      RenderSystemBridge.Extension2 var5 = this.field2.method83();
      if (!Bridge.getMinecraftVersion().method23()) {
         MixinHelper_21 var6 = var5.method2(var1, var2, var3, var4);
         var5.method17(var6);
      } else {
         var5.method7();
         MixinHelper_21 var7 = var5.method2(var1, var2, var3, var4);
         var5.method7(var7, BridgeType.DISTANCE_TO_ORIGIN);
      }
   }

   @Override
   public void method34() {
      this.field2.method83().method6();
   }

   @Override
   public boolean method38() {
      return true;
   }

   @Override
   public void method33(RenderLayerBridge var1) {
      if (this.field8 instanceof Bridge17Extension_2 var2) {
         var2.bridge$endBatch(var1);
      } else if (this.field8 instanceof BufferSourceDecorator var3) {
         BatchingBufferSourceBridge var7 = var3.unwrap();

         while (var7 instanceof BufferSourceDecorator) {
            BufferSourceDecorator var5 = (BufferSourceDecorator)var7;
            var7 = var5.unwrap();
         }

         if (var7 instanceof Bridge17Extension_2 var8) {
            var8.bridge$endBatch(var1);
         }
      }
   }

   public void method47() {
      if (this.field8 instanceof Bridge2_22 var1) {
         var1.bridge$popTracker();
      }

      Set var4 = this.field7.pop();
      if (this.field8 instanceof Bridge10_3) {
         this.method48();
      } else {
         for (RenderLayerBridge var3 : var4) {
            this.method33(var3);
         }
      }
   }

   public void method48() {
      if (this.field8 instanceof Bridge17Extension_2 var1) {
         var1.bridge$endBatch();
      }
   }

   @Override
   public void method40() {
      if (Bridge.getMinecraftVersion().method23()) {
         this.field2.method83().method7();
      } else {
         this.field2.method24(BridgeType3_3.GL_PROJECTION.getId());
         this.field2.method4();
         this.field2.method24(BridgeType3_3.GL_MODELVIEW.getId());
      }
   }

   @Override
   public void method35(double var1, double var3, double var5, double var7, double var9, double var11) {
      if (Bridge.getMinecraftVersion().method23()) {
         this.field2.method83().method7();
         this.field2.method59(var1, var3, var5, var7, var9, var11);
      } else {
         this.field2.method24(BridgeType3_3.GL_PROJECTION.getId());
         this.field2.method4();
         this.field2.bridge$loadIdentity();
         this.field2.method59(var1, var3, var5, var7, var9, var11);
         this.field2.method24(BridgeType3_3.GL_MODELVIEW.getId());
      }
   }

   @Override
   public void method36(float var1, float var2, float var3, float var4) {
      if (Bridge.getMinecraftVersion().method23()) {
         this.field2.method83().method7();
         this.field2.method75(var1, var2, var3, var4);
      } else {
         this.field2.method24(BridgeType3_3.GL_PROJECTION.getId());
         this.field2.method4();
         this.field2.bridge$loadIdentity();
         this.field2.method75(var1, var2, var3, var4);
         this.field2.method24(BridgeType3_3.GL_MODELVIEW.getId());
      }
   }

   @Override
   public void method41() {
      if (Bridge.getMinecraftVersion().method23()) {
         this.field2.method83().method8();
      } else {
         this.field2.method24(BridgeType3_3.GL_PROJECTION.getId());
         this.field2.method5();
         this.field2.method24(BridgeType3_3.GL_MODELVIEW.getId());
      }
   }

   @Generated
   private static float method49() {
      return Bridge.method9().bridge$getTimer().method1();
   }

   @Generated
   public static BridgeExtension2$Data method50() {
      return new BridgeExtension2$Data();
   }

   @NonNull
   @Generated
   public Bridge5_16 method51() {
      return this.field6;
   }

   @Generated
   public void method26(@NonNull Bridge5_16 var1) {
      if (var1 == null) {
         throw new NullPointerException("poseStack is marked non-null but is null");
      }

      this.field6 = var1;
   }

   @Generated
   @Override
   public float method28() {
      return this.field11;
   }
}
