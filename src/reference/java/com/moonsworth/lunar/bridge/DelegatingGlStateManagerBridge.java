package com.moonsworth.lunar.bridge;

import lombok.Generated;

public class DelegatingGlStateManagerBridge implements GlStateManagerBridge {
   protected final RenderSystemBridge field1;
   private final boolean field2;

   public void method1(int number1) {
      if (this.field2) {
         this.field1.method10(number1);
      } else {
         this.field1.method12(number1);
      }
   }

   public void method28(int number1) {
      this.field1.method58(number1);
   }

   public void method2(GlBlendFactor bridgetype2_91, GlBlendFactor bridgetype2_92) {
      this.field1.method15(bridgetype2_91.getId(), bridgetype2_92.getId());
   }

   public void method3(DepthFunction bridgetype_101, float value) {
      this.field1.method16(bridgetype_101.getId(), value);
   }

   public void method4(GlBlendFactor bridgetype2_91, GlBlendFactor bridgetype2_92, GlBlendFactor bridgetype2_93, GlBlendFactor bridgetype2_94) {
      this.field1.method18(bridgetype2_91.getId(), bridgetype2_92.getId(), bridgetype2_93.getId(), bridgetype2_94.getId());
   }

   public void method6(boolean flag1) {
      this.field1.method21(flag1);
   }

   public void method7(DepthFunction bridgetype_101) {
      this.field1.method23(bridgetype_101.getId());
   }

   public void method8(GlMatrixMode bridgetype3_31) {
      this.field1.method24(bridgetype3_31.getId());
   }

   public int method8() {
      return this.field1.method25();
   }

   public void method9(ShadeModel shadeModel) {
      this.field1.method26(shadeModel.getId());
   }

   public int method9() {
      return this.field1.method27();
   }

   public void method10() {
      this.field1.method28();
   }

   public void method11() {
      this.field1.method29();
   }

   public boolean method6() {
      return this.field1.method30();
   }

   public void method12() {
      this.field1.method31();
   }

   public void method13() {
      this.field1.method32();
   }

   public void method14() {
      this.field1.method34();
   }

   public boolean method1() {
      return this.field1.method37();
   }

   public void method15() {
      this.field1.method35();
   }

   public void method16() {
      this.field1.method38();
   }

   public void method17() {
      this.field1.method40();
   }

   public boolean method2() {
      return this.field1.method39();
   }

   public int method3() {
      return this.field1.method17();
   }

   public void method18() {
      this.field1.method41();
   }

   public void method19() {
      this.field1.method42();
   }

   public boolean method4() {
      return this.field1.method43();
   }

   public boolean method5() {
      return this.field1.method22();
   }

   public void method20() {
      this.field1.method44();
   }

   public void method21() {
      this.field1.method45();
   }

   public boolean method7() {
      return this.field1.method46();
   }

   public void method22() {
      this.field1.method47();
   }

   public void method23() {
      this.field1.method48();
   }

   public void method24() {
      this.field1.method50();
   }

   public void method25() {
      this.field1.method51();
   }

   public void method26() {
      this.field1.method55();
   }

   public void method27() {
      this.field1.method56();
   }

   public void method5(boolean flag1) {
      if (this.field2) {
         this.field1.method77(flag1);
      }
   }

   @Generated
   public DelegatingGlStateManagerBridge(RenderSystemBridge bridge121, boolean flag) {
      this.field1 = bridge121;
      this.field2 = flag;
   }
}
