package com.moonsworth.lunar.files;

import java.util.Optional;
import lombok.Generated;

public class Files4Base3 extends Files4Base {
   private final Files7 field2;

   public Files4Base3(Files4_2 var1, Files7 var2) {
      super(var1);
      this.field2 = var2;
   }

   @Override
   public Optional<Files2_2> method4(Files6 var1, Files3 var2) {
      return this.field2.method9(var2).or(() -> super.method4(var1, var2));
   }

   @Override
   public void method5(Files6 var1, Files2_2 var2) {
      this.field2.method1(var2);
      super.method5(var1, var2);
   }

   @Generated
   public Files7 method4() {
      return this.field2;
   }
}
