package com.moonsworth.lunar.ichor;

import java.util.Optional;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;

public class MappingSetNameMapper implements ClassNameMapper {
   private final MappingSet field1;

   public MappingSetNameMapper(MappingSet map) {
      this.field1 = map;
   }

   public String remap(String text1) {
      if (text1 != null && !text1.isEmpty()) {
         Optional optional2 = this.field1.computeClassMapping(text1);
         if (optional2.isPresent()) {
            text1 = ((ClassMapping)optional2.get()).getFullDeobfuscatedName();
         }

         return text1;
      } else {
         return text1;
      }
   }

   public String unmap(String text1) {
      return text1 != null && !text1.isEmpty() ? MappingSetUtils.method2(this.field1, text1) : text1;
   }
}
