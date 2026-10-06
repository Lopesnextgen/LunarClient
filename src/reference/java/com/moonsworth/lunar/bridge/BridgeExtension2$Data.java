package com.moonsworth.lunar.bridge;

import java.util.Set;
import java.util.Stack;
import lombok.Generated;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

@Generated
public class BridgeExtension2$Data {
   @Generated
   private Bridge5_16 field1;
   @Generated
   private Stack<Set<RenderLayerBridge>> field2;
   @Generated
   private BatchingBufferSourceBridge field3;
   @Generated
   private Bridge8_2 field4;
   @Generated
   private Integer field5;
   @Generated
   private boolean field6;
   @Generated
   private float field7;

   @Generated
   BridgeExtension2$Data() {
   }

   @Generated
   public BridgeExtension2$Data method1(@NonNull Bridge5_16 var1) {
      if (var1 == null) {
         throw new NullPointerException("poseStack is marked non-null but is null");
      }

      this.field1 = var1;
      return this;
   }

   @Generated
   public BridgeExtension2$Data method2(Stack<Set<RenderLayerBridge>> var1) {
      this.field2 = var1;
      return this;
   }

   @Generated
   public BridgeExtension2$Data method3(BatchingBufferSourceBridge var1) {
      this.field3 = var1;
      return this;
   }

   @Generated
   public BridgeExtension2$Data method4(@Nullable Bridge8_2 var1) {
      this.field4 = var1;
      return this;
   }

   @Generated
   public BridgeExtension2$Data method5(Integer var1) {
      this.field5 = var1;
      return this;
   }

   @Generated
   public BridgeExtension2$Data method6(float var1) {
      this.field7 = var1;
      this.field6 = true;
      return this;
   }

   @Generated
   public BridgeExtension2_11 method7() {
      float var1 = this.field7;
      if (!this.field6) {
         var1 = BridgeExtension2_11.method49();
      }

      return new BridgeExtension2_11(this.field1, this.field2, this.field3, this.field4, this.field5, var1);
   }

   @Generated
   @Override
   public String toString() {
      return "RenderContextModern.RenderContextModernBuilder(poseStack="
         + this.field1
         + ", trackedStack="
         + this.field2
         + ", bufferSource="
         + this.field3
         + ", guiGraphics="
         + this.field4
         + ", packedLight="
         + this.field5
         + ", partialTicks$value="
         + this.field7
         + ")";
   }
}
