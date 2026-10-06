package com.moonsworth.lunar.ichor;

import lombok.Generated;

public class InnerClassRenameInfo {
   private String field1;
   private String field2;
   private String field3;
   private String outerMethodDesc;
   private String innerName;
   private int access;
   private InnerClassRenameInfo.Type2 field4;

   public String method1() {
      return this.field2 + "$" + this.innerName;
   }

   @Generated
   public String method2() {
      return this.field1;
   }

   @Generated
   public String method3() {
      return this.field2;
   }

   @Generated
   public String method4() {
      return this.field3;
   }

   @Generated
   public String getOuterMethodDesc() {
      return this.outerMethodDesc;
   }

   @Generated
   public String getInnerName() {
      return this.innerName;
   }

   @Generated
   public int getAccess() {
      return this.access;
   }

   @Generated
   public InnerClassRenameInfo.Type2 method8() {
      return this.field4;
   }

   @Generated
   public InnerClassRenameInfo method9(String var1) {
      this.field1 = var1;
      return this;
   }

   @Generated
   public InnerClassRenameInfo method10(String var1) {
      this.field2 = var1;
      return this;
   }

   @Generated
   public InnerClassRenameInfo method11(String var1) {
      this.field3 = var1;
      return this;
   }

   @Generated
   public InnerClassRenameInfo method12(String var1) {
      this.outerMethodDesc = var1;
      return this;
   }

   @Generated
   public InnerClassRenameInfo method13(String var1) {
      this.innerName = var1;
      return this;
   }

   @Generated
   public InnerClassRenameInfo method14(int var1) {
      this.access = var1;
      return this;
   }

   @Generated
   public InnerClassRenameInfo method15(InnerClassRenameInfo.Type2 var1) {
      this.field4 = var1;
      return this;
   }

   @Generated
   public InnerClassRenameInfo(String var1, String text, String text2, String text3, String text4, int value, InnerClassRenameInfo.Type2 type2) {
      this.field1 = var1;
      this.field2 = text;
      this.field3 = text2;
      this.outerMethodDesc = text3;
      this.innerName = text4;
      this.access = value;
      this.field4 = type2;
   }

   enum Type2 {
      LOCAL,
      ANONYMOUS,
      INNER;
   }
}
