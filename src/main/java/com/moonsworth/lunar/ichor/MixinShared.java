package com.moonsworth.lunar.ichor;

public final class MixinShared {
   private String resourcePath;
   private byte[] field1;

   public MixinShared(String var1, byte[] items) {
      this.resourcePath = var1;
      this.field1 = items;
   }

   public String getResourcePath() {
      return this.resourcePath;
   }

   public void setResourcePath(String var1) {
      this.resourcePath = var1;
   }

   public byte[] method3() {
      return this.field1;
   }

   public void method4(byte[] var1) {
      this.field1 = var1;
   }
}
