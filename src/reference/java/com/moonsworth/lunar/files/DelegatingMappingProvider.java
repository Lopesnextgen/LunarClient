package com.moonsworth.lunar.files;

import java.util.Optional;
import lombok.Generated;

public abstract class DelegatingMappingProvider extends Files4_2 {
   private final Files4_2 field1;

   public DelegatingMappingProvider(Files4_2 files4_21) {
      this.field1 = files4_21;
   }

   public Optional<ArtifactData> method4(Files6 files61, Files3 files32) {
      return this.field1.method4(files61, files32);
   }

   public void method5(Files6 files61, ArtifactData files2_22) {
      this.field1.method5(files61, files2_22);
   }

   @Generated
   public Files4_2 method3() {
      return this.field1;
   }
}
