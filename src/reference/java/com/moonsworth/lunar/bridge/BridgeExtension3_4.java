package com.moonsworth.lunar.bridge;

import lombok.Generated;

public class BridgeExtension3_4 implements GlStateControlBridge {
   protected final RenderSystemBridge field1;
   private final boolean field2;

   @Override
   public void method1(int var1) {
      if (this.field2) {
         this.field1.method10(var1);
      } else {
         this.field1.method12(var1);
      }
   }

   @Override
   public void method28(int var1) {
      this.field1.method58(var1);
   }

   @Override
   public void method2(BridgeType2_9 var1, BridgeType2_9 var2) {
      this.field1.method15(var1.getId(), var2.getId());
   }

   @Override
   public void method3(DepthComparison var1, float var2) {
      this.field1.method16(var1.getId(), var2);
   }

   @Override
   public void method4(BridgeType2_9 var1, BridgeType2_9 var2, BridgeType2_9 bridgeType2_9, BridgeType2_9 bridgeType2_92) {
      this.field1.method18(var1.getId(), var2.getId(), bridgeType2_9.getId(), bridgeType2_92.getId());
   }

   @Override
   public void method6(boolean var1) {
      this.field1.method21(var1);
   }

   @Override
   public void method7(DepthComparison var1) {
      this.field1.method23(var1.getId());
   }

   @Override
   public void method8(BridgeType3_3 var1) {
      this.field1.method24(var1.getId());
   }

   @Override
   public int method8() {
      return this.field1.method25();
   }

   @Override
   public void method9(ShadingModel var1) {
      this.field1.method26(var1.getId());
   }

   @Override
   public int method9() {
      return this.field1.method27();
   }

   @Override
   public void method10() {
      this.field1.method28();
   }

   @Override
   public void method11() {
      this.field1.method29();
   }

   @Override
   public boolean method6() {
      return this.field1.method30();
   }

   @Override
   public void method12() {
      this.field1.method31();
   }

   @Override
   public void method13() {
      this.field1.method32();
   }

   @Override
   public void method14() {
      this.field1.method34();
   }

   @Override
   public boolean method1() {
      return this.field1.method37();
   }

   @Override
   public void method15() {
      this.field1.method35();
   }

   @Override
   public void method16() {
      this.field1.method38();
   }

   @Override
   public void method17() {
      this.field1.method40();
   }

   @Override
   public boolean method2() {
      return this.field1.method39();
   }

   @Override
   public int method3() {
      return this.field1.method17();
   }

   @Override
   public void method18() {
      this.field1.method41();
   }

   @Override
   public void method19() {
      this.field1.method42();
   }

   @Override
   public boolean method4() {
      return this.field1.method43();
   }

   @Override
   public boolean method5() {
      return this.field1.method22();
   }

   @Override
   public void method20() {
      this.field1.method44();
   }

   @Override
   public void method21() {
      this.field1.method45();
   }

   @Override
   public boolean method7() {
      return this.field1.method46();
   }

   @Override
   public void method22() {
      this.field1.method47();
   }

   @Override
   public void method23() {
      this.field1.method48();
   }

   @Override
   public void method24() {
      this.field1.method50();
   }

   @Override
   public void method25() {
      this.field1.method51();
   }

   @Override
   public void method26() {
      this.field1.method55();
   }

   @Override
   public void method27() {
      this.field1.method56();
   }

   @Override
   public void method5(boolean var1) {
      if (this.field2) {
         this.field1.method77(var1);
      }
   }

   @Generated
   public BridgeExtension3_4(RenderSystemBridge var1, boolean var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
