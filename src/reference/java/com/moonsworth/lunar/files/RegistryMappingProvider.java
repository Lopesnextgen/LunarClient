package com.moonsworth.lunar.files;

import java.util.Optional;
import lombok.Generated;

public class RegistryMappingProvider extends DelegatingMappingProvider {
   private final Files7 field2;

   public RegistryMappingProvider(Files4_2 files4_21, Files7 files72) {
      super(files4_21);
      this.field2 = files72;
   }

   public Optional<ArtifactData> method4(Files6 files61, Files3 files32) {
      return this.field2.method9(files32).or(() -> super.method4(files61, files32));
   }

   public void method5(Files6 files61, ArtifactData files2_22) {
      this.field2.method1(files2_22);
      super.method5(files61, files2_22);
   }

   @Generated
   public Files7 method4() {
      return this.field2;
   }
}
