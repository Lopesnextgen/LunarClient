package com.moonsworth.lunar.ichor;

import java.util.Optional;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;

public class InheritanceProvider2 implements org.cadixdev.bombe.analysis.InheritanceProvider {
   private final org.cadixdev.bombe.analysis.InheritanceProvider field1;
   private final MappingSet field2;

   public InheritanceProvider2(org.cadixdev.bombe.analysis.InheritanceProvider var1, MappingSet var2, RemapTransformInvoker.Extension var3) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public Optional<ClassInfo> provide(String var1) {
      ClassInfo var2 = (ClassInfo)this.field1.provide(var1).orElse(null);
      if (var2 == null) {
         Optional var3 = this.field2.getClassMapping(var1);
         if (var3.isPresent()) {
            ClassMapping var4 = (ClassMapping)var3.get();
            Optional var5 = this.field1.provide(var4.getFullDeobfuscatedName());
            if (var5.isPresent()) {
               var2 = (ClassInfo)var5.get();
            }
         }
      }

      return var2 == null ? Optional.empty() : Optional.ofNullable(new ClassInfoIterator(var2, this.field2).lazy());
   }
}
