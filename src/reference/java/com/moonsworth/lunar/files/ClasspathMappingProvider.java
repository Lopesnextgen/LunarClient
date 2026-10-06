package com.moonsworth.lunar.files;

import java.io.InputStream;
import java.util.Optional;
import lombok.Generated;

public class ClasspathMappingProvider extends DelegatingMappingProvider {
   private ClassLoader classLoader;

   public ClasspathMappingProvider(Files4_2 files4_21) {
      this(files4_21, ClasspathMappingProvider.class.getClassLoader());
   }

   public ClasspathMappingProvider(Files4_2 files4_21, ClassLoader classloader2) {
      super(files4_21);
      this.classLoader = classloader2;
   }

   public Optional<ArtifactData> method4(Files6 files61, Files3 files32) {
      try {
         InputStream input3 = this.classLoader.getResourceAsStream(files32.method2(files61));
         return input3 == null ? super.method4(files61, files32) : Optional.of(new ArtifactData(files32, MappingDownloader.toByteArray(input3)));
      } catch (Throwable exception4) {
         throw exception4;
      }
   }

   @Generated
   public ClassLoader getClassLoader() {
      return this.classLoader;
   }

   @Generated
   public void setClassLoader(ClassLoader classloader1) {
      this.classLoader = classloader1;
   }
}
