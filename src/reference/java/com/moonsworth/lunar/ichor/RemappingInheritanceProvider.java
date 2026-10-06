package com.moonsworth.lunar.ichor;

import java.util.Optional;
import org.cadixdev.bombe.analysis.InheritanceProvider;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;

public class RemappingInheritanceProvider implements InheritanceProvider {
   private final InheritanceProvider field1;
   private final MappingSet field2;

   public RemappingInheritanceProvider(InheritanceProvider provider, MappingSet map, InheritanceFixer mixinhelper6$extension3) {
      this.field1 = provider;
      this.field2 = map;
   }

   public Optional<ClassInfo> provide(String text) {
      ClassInfo classinfo2 = (ClassInfo)this.field1.provide(text).orElse(null);
      if (classinfo2 == null) {
         Optional optional3 = this.field2.getClassMapping(text);
         if (optional3.isPresent()) {
            ClassMapping classmapping4 = (ClassMapping)optional3.get();
            Optional optional5 = this.field1.provide(classmapping4.getFullDeobfuscatedName());
            if (optional5.isPresent()) {
               classinfo2 = (ClassInfo)optional5.get();
            }
         }
      }

      return classinfo2 == null ? Optional.empty() : Optional.ofNullable(new RemappedClassInfo(classinfo2, this.field2).lazy());
   }
}
