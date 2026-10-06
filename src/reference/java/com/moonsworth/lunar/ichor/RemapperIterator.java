package com.moonsworth.lunar.ichor;

import lombok.Generated;
import org.cadixdev.bombe.type.MethodDescriptor;
import org.cadixdev.bombe.type.ObjectType;
import org.cadixdev.bombe.type.signature.FieldSignature;
import org.cadixdev.bombe.type.signature.MethodSignature;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.Mapping;
import org.cadixdev.lorenz.model.MethodMapping;
import org.objectweb.asm.Handle;
import org.objectweb.asm.commons.Remapper;

public class RemapperIterator extends Remapper {
   protected final MappingSet field1;
   protected final org.cadixdev.bombe.analysis.InheritanceProvider field2;

   public RemapperIterator(MappingSet var1, org.cadixdev.bombe.analysis.InheritanceProvider var2) {
      super(589824);
      this.field1 = var1;
      this.field2 = var2;
   }

   public String map(String var1) {
      return this.field1.computeClassMapping(var1).<String>map(Mapping::getFullDeobfuscatedName).orElse(var1);
   }

   public String mapInnerClassName(String var1, String var2, String var3) {
      return this.field1.computeClassMapping(var1).<String>map(Mapping::getDeobfuscatedName).orElse(var3);
   }

   protected ClassMapping<?, ?> getCompletedClassMapping(String var1) {
      ClassMapping var2 = this.field1.getOrCreateClassMapping(var1);
      var2.complete(this.field2);
      return var2;
   }

   public String mapFieldName(String var1, String var2, String var3) {
      return this.getCompletedClassMapping(var1).computeFieldMapping(FieldSignature.of(var2, var3)).<String>map(Mapping::getDeobfuscatedName).orElse(var2);
   }

   public String mapMethodName(String var1, String var2, String var3) {
      try {
         return var1.toCharArray()[0] == '['
            ? var2
            : this.getCompletedClassMapping(var1).getMethodMapping(MethodSignature.of(var2, var3)).<String>map(Mapping::getDeobfuscatedName).orElse(var2);
      } catch (Exception var5) {
         throw new IllegalStateException("Failed to map method " + var1 + "." + var2 + var3, var5);
      }
   }

   public String mapInvokeDynamicMethodName(String var1, String var2, Handle var3, Object... var4) {
      if ("invoke".equals(var1) && !var2.contains("(")) {
         return var1;
      }

      try {
         MethodDescriptor var5 = MethodDescriptor.of(var2);
         if (var5.getReturnType() instanceof ObjectType var6) {
            ClassMapping var11 = this.getCompletedClassMapping(var6.getClassName());

            for (MethodMapping var9 : var11.getMethodMappings()) {
               if (var9.getObfuscatedName().equals(var1)) {
                  return var9.getDeobfuscatedName();
               }
            }
         }
      } catch (Exception var10) {
         var10.printStackTrace();
      }

      return var1;
   }

   public String mapRecordComponentName(String var1, String var2, String var3) {
      return this.mapFieldName(var1, var2, var3);
   }

   @Generated
   public MappingSet getMappings() {
      return this.field1;
   }

   @Generated
   public org.cadixdev.bombe.analysis.InheritanceProvider method1() {
      return this.field2;
   }
}
