package com.moonsworth.lunar.files;

import java.util.Optional;
import lombok.Generated;

public abstract class Files4Base extends Files4_2 {
   private final Files4_2 field1;

   public Files4Base(Files4_2 var1) {
      this.field1 = var1;
   }

   @Override
   public Optional<Files2_2> method4(Files6 var1, Files3 var2) {
      return this.field1.method4(var1, var2);
   }

   @Override
   public void method5(Files6 var1, Files2_2 var2) {
      this.field1.method5(var1, var2);
   }

   @Generated
   public Files4_2 method3() {
      return this.field1;
   }
}
