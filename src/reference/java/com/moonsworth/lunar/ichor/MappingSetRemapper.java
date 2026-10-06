package com.moonsworth.lunar.ichor;

import lombok.Generated;
import org.cadixdev.bombe.analysis.InheritanceProvider;
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

public class MappingSetRemapper extends Remapper {
   protected final MappingSet field1;
   protected final InheritanceProvider field2;

   public MappingSetRemapper(MappingSet mappingset1, InheritanceProvider inheritanceprovider2) {
      super(589824);
      this.field1 = mappingset1;
      this.field2 = inheritanceprovider2;
   }

   public String map(String text1) {
      return this.field1.computeClassMapping(text1).<String>map(Mapping::getFullDeobfuscatedName).orElse(text1);
   }

   public String mapInnerClassName(String text1, String text2, String text3) {
      return this.field1.computeClassMapping(text1).<String>map(Mapping::getDeobfuscatedName).orElse(text3);
   }

   protected ClassMapping<?, ?> getCompletedClassMapping(String text1) {
      ClassMapping classmapping2 = this.field1.getOrCreateClassMapping(text1);
      classmapping2.complete(this.field2);
      return classmapping2;
   }

   public String mapFieldName(String text1, String text2, String text3) {
      return this.getCompletedClassMapping(text1).computeFieldMapping(FieldSignature.of(text2, text3)).<String>map(Mapping::getDeobfuscatedName).orElse(text2);
   }

   public String mapMethodName(String text1, String text2, String text3) {
      try {
         return text1.toCharArray()[0] == '['
            ? text2
            : this.getCompletedClassMapping(text1).getMethodMapping(MethodSignature.of(text2, text3)).<String>map(Mapping::getDeobfuscatedName).orElse(text2);
      } catch (Exception exception5) {
         throw new IllegalStateException("Failed to map method " + text1 + "." + text2 + text3, exception5);
      }
   }

   public String mapInvokeDynamicMethodName(String text1, String text2, Handle handle3, Object... items4) {
      if ("invoke".equals(text1) && !text2.contains("(")) {
         return text1;
      }

      try {
         MethodDescriptor methoddescriptor5 = MethodDescriptor.of(text2);
         if (methoddescriptor5.getReturnType() instanceof ObjectType objecttype6) {
            ClassMapping classmapping11 = this.getCompletedClassMapping(objecttype6.getClassName());

            for (MethodMapping methodmapping9 : classmapping11.getMethodMappings()) {
               if (methodmapping9.getObfuscatedName().equals(text1)) {
                  return methodmapping9.getDeobfuscatedName();
               }
            }
         }
      } catch (Exception exception10) {
         exception10.printStackTrace();
      }

      return text1;
   }

   public String mapRecordComponentName(String text1, String text2, String text3) {
      return this.mapFieldName(text1, text2, text3);
   }

   @Generated
   public MappingSet getMappings() {
      return this.field1;
   }

   @Generated
   public InheritanceProvider method1() {
      return this.field2;
   }
}
