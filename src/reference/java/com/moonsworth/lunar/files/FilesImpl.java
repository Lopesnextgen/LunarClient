package com.moonsworth.lunar.files;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class FilesImpl extends Files_3 {
   private final String field3;

   public FilesImpl(String var1) {
      this.field3 = var1;
   }

   @Override
   public Collection<Files2_2> method1(Files6 var1) {
      return List.copyOf(var1.getData().values());
   }

   @Override
   public String getId() {
      return "freeze/" + Objects.hash(this.method3());
   }

   @Override
   public String getNamespace() {
      return this.field3;
   }

   public static FilesImpl method2(String text, final Files3... var1) {
      return new FilesImpl(text) {
         {
            HashSet var3 = new HashSet<>(Arrays.asList(var1));
            this.field1.addAll(var3);
            this.method6(var1xxx -> var3);
         }
      };
   }
}
