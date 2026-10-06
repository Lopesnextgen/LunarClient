package com.moonsworth.lunar.files;

import java.io.InputStream;
import java.util.Optional;
import lombok.Generated;

public class Files4Base4 extends Files4Base {
   private ClassLoader classLoader;

   public Files4Base4(Files4_2 var1) {
      this(var1, Files4Base4.class.getClassLoader());
   }

   public Files4Base4(Files4_2 var1, ClassLoader var2) {
      super(var1);
      this.classLoader = var2;
   }

   @Override
   public Optional<Files2_2> method4(Files6 var1, Files3 var2) {
      try {
         InputStream var3 = this.classLoader.getResourceAsStream(var2.method2(var1));
         return var3 == null ? super.method4(var1, var2) : Optional.of(new Files2_2(var2, Files3_2.toByteArray(var3)));
      } catch (Throwable var4) {
         throw var4;
      }
   }

   @Generated
   public ClassLoader getClassLoader() {
      return this.classLoader;
   }

   @Generated
   public void setClassLoader(ClassLoader var1) {
      this.classLoader = var1;
   }
}
