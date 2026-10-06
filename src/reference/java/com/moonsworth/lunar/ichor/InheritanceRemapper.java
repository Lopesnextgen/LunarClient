package com.moonsworth.lunar.ichor;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Generated;
import org.cadixdev.bombe.analysis.InheritanceProvider;
import org.cadixdev.bombe.analysis.InheritanceType;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.bombe.type.MethodDescriptor;
import org.cadixdev.bombe.type.signature.MethodSignature;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.FieldMapping;
import org.cadixdev.lorenz.model.MethodMapping;

public class InheritanceRemapper extends MappingSetRemapper {
   public static boolean DEBUG = System.getProperty("ichor.debug.remapper", null) != null;
   protected final AutoCloseableIterator field3;
   protected boolean field4;
   protected boolean field5;
   protected Set<String> field6 = Collections.newSetFromMap(new ConcurrentHashMap<>());

   public InheritanceRemapper(AutoCloseableIterator autocloseableiterator1, MappingSet mappingset2, InheritanceProvider inheritanceprovider3, boolean flag4, boolean flag5) {
      super(mappingset2, inheritanceprovider3);
      this.field3 = autocloseableiterator1;
      this.field4 = flag4;
      this.field5 = flag5;
   }

   public ClassMapping<?, ?> getCompletedClassMapping(String text1) {
      ClassMapping classmapping2 = this.RIHROHCIRORRICORIORCRIOIOORRII.getOrCreateClassMapping(text1);
      if (this.field4) {
         this.method1(classmapping2);
      }

      classmapping2.complete(this.HCICRHCHCHHCICOCORCCOHHHOOCHIH);
      return classmapping2;
   }

   public void method1(ClassMapping<?, ?> classmapping1) {
      if (this.field6.add(classmapping1.getFullObfuscatedName())) {
         ClassInfo classinfo2 = (ClassInfo)this.HCICRHCHCHHCICOCORCCOHHHOOCHIH.provide(classmapping1.getFullDeobfuscatedName()).orElse(null);
         if (classinfo2 != null) {
            Set set3 = classinfo2.provideParents(this.HCICRHCHCHHCICOCORCCOHHHOOCHIH);
            if (!set3.isEmpty()) {
               HashMap map4 = new HashMap();

               for (Entry entry6 : classinfo2.getMethods().entrySet()) {
                  Set set7 = map4.computeIfAbsent(((MethodSignature)entry6.getKey()).getName(), arg0 -> new HashSet());
                  set7.add((MethodSignature)entry6.getKey());
               }

               for (ClassInfo classinfo18 : set3) {
                  String text19 = classinfo18.getName();
                  if (!text19.equals("java/lang/Object")) {
                     String text8 = this.field4 ? MappingSetUtils.method2(this.RIHROHCIRORRICORIORCRIOIOORRII, text19) : null;
                     ClassMapping classmapping9 = text8 == null ? null : (ClassMapping)this.RIHROHCIRORRICORIORCRIOIOORRII.getClassMapping(text8).orElse(null);
                     if (classmapping9 != null) {
                        classmapping9 = this.getCompletedClassMapping(classmapping9.getFullObfuscatedName());

                        for (FieldMapping fieldmapping11 : classmapping9.getFieldMappings()) {
                           if (!classmapping1.computeFieldMapping(fieldmapping11.getSignature()).isPresent()
                              && classinfo18.canInherit(classinfo2, fieldmapping11.getDeobfuscatedSignature())
                              && classmapping1.getFieldMapping(fieldmapping11.getSignature()).isEmpty()) {
                              classmapping1.createFieldMapping(fieldmapping11.getSignature(), fieldmapping11.getDeobfuscatedName());
                           }
                        }

                        for (MethodMapping methodmapping22 : classmapping9.getMethodMappings()) {
                           if (!this.field5
                              || classinfo18.getMethod(methodmapping22.getSignature()) != InheritanceType.NONE
                              || classinfo18.getMethod(methodmapping22.getDeobfuscatedSignature()) != InheritanceType.NONE) {
                              if (classinfo18.canInherit(classinfo2, methodmapping22.getDeobfuscatedSignature())) {
                                 Optional optional12 = classmapping1.getMethodMapping(methodmapping22.getSignature());
                                 if (optional12.isEmpty()) {
                                    classmapping1.createMethodMapping(methodmapping22.getSignature(), methodmapping22.getDeobfuscatedName());
                                 }
                              }

                              if (map4.containsKey(methodmapping22.getObfuscatedName())) {
                                 for (MethodSignature methodsignature13 : (Set)map4.get(methodmapping22.getObfuscatedName())) {
                                    MethodDescriptor methoddescriptor14 = methodsignature13.getDescriptor();
                                    MethodSignature methodsignature15 = methodmapping22.getSignature();
                                    MethodDescriptor methoddescriptor16 = methodsignature15.getDescriptor();
                                    if (Objects.equals(methoddescriptor14.getParamTypes(), methoddescriptor16.getParamTypes())
                                       && methoddescriptor16.getReturnType().isAssignableFrom(methoddescriptor14.getReturnType(), this.HCICRHCHCHHCICOCORCCOHHHOOCHIH)
                                       && classmapping1.getMethodMapping(methodsignature13).isEmpty()) {
                                       classmapping1.createMethodMapping(methodsignature13, methodmapping22.getDeobfuscatedName());
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Generated
   public boolean method2() {
      return this.field4;
   }
}
