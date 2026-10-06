package com.moonsworth.lunar.ichor;

public final class ResourcePayload {
   private String resourcePath;
   private byte[] field1;

   public ResourcePayload(String text1, byte[] items2) {
      this.resourcePath = text1;
      this.field1 = items2;
   }

   public String getResourcePath() {
      return this.resourcePath;
   }

   public void setResourcePath(String text1) {
      this.resourcePath = text1;
   }

   public byte[] method3() {
      return this.field1;
   }

   public void method4(byte[] items1) {
      this.field1 = items1;
   }
}
