package com.moonsworth.lunar.files;

import javax.annotation.Nullable;

public class ArtifactData {
   @Nullable
   private final Files3 field1;
   private final byte[] field2;

   public ArtifactData(@Nullable Files3 files31, byte[] items2) {
      this.field1 = files31;
      this.field2 = items2;
   }

   @Nullable
   public Files3 method1() {
      return this.field1;
   }

   public byte[] method2() {
      return this.field2;
   }
}
