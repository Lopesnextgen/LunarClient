package com.moonsworth.lunar.ichor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import org.cadixdev.bombe.analysis.InheritanceProvider;
import org.cadixdev.bombe.analysis.InheritanceType;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.bombe.type.FieldType;
import org.cadixdev.bombe.type.MethodDescriptor;
import org.cadixdev.bombe.type.signature.FieldSignature;
import org.cadixdev.bombe.type.signature.MethodSignature;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.Mapping;

public class RemappedClassInfo implements ClassInfo {
   protected final ClassInfo field1;
   @Nullable
   protected final MappingSet field2;
   private final ClassMapping<?, ?> field3;

   public RemappedClassInfo(ClassInfo classinfo1, @Nullable MappingSet mappingset2) {
      this.field1 = classinfo1;
      if (mappingset2 != null) {
         this.field2 = mappingset2;
         String text3 = MappingSetUtils.method3(mappingset2, classinfo1.getName(), null);
         if (text3 != null) {
            this.field3 = (ClassMapping<?, ?>)mappingset2.getClassMapping(text3).orElse(null);
         } else {
            this.field3 = null;
         }
      } else {
         this.field2 = null;
         this.field3 = null;
      }
   }

   public String getName() {
      return this.field3 != null ? this.field3.getFullDeobfuscatedName() : this.field1.getName();
   }

   public boolean isInterface() {
      return this.field1.isInterface();
   }

   public String getSuperName() {
      String text1 = this.field1.getSuperName();
      if (this.field2 != null) {
         String text2 = MappingSetUtils.method3(this.field2, text1, null);
         if (text2 != null) {
            Optional optional3 = this.field2.getClassMapping(text2);
            if (optional3.isPresent()) {
               return ((ClassMapping)optional3.get()).getFullDeobfuscatedName();
            }
         }
      }

      return text1;
   }

   public List<String> getInterfaces() {
      ArrayList list1 = new ArrayList();

      for (String text3 : this.field1.getInterfaces()) {
         if (this.field2 != null) {
            String text4 = MappingSetUtils.method3(this.field2, text3, null);
            if (text4 != null) {
               Optional optional5 = this.field2.getClassMapping(text4);
               if (optional5.isPresent()) {
                  text3 = ((ClassMapping)optional5.get()).getFullDeobfuscatedName();
               }
            }
         }

         list1.add(text3);
      }

      return list1;
   }

   public Map<FieldSignature, InheritanceType> getFields() {
      Map map1 = this.field1.getFields();
      HashMap map2 = new HashMap();

      for (Entry entry4 : map1.entrySet()) {
         FieldSignature fieldsignature5 = (FieldSignature)entry4.getKey();
         InheritanceType inheritancetype6 = (InheritanceType)entry4.getValue();
         if (this.field3 != null && this.field2 != null) {
            Optional optional7 = this.field3.getFieldMapping(fieldsignature5).map(Mapping::getDeobfuscatedName);
            if (optional7.isPresent()) {
               FieldType fieldtype8 = (FieldType)fieldsignature5.getType().orElseThrow();
               FieldType fieldtype9 = this.field2.deobfuscate(fieldtype8);
               map2.putIfAbsent(new FieldSignature((String)optional7.get(), fieldtype9), inheritancetype6);
               continue;
            }
         }

         map2.putIfAbsent(fieldsignature5, inheritancetype6);
      }

      return map2;
   }

   public Map<String, InheritanceType> getFieldsByName() {
      Map map1 = this.field1.getFieldsByName();
      HashMap map2 = new HashMap();

      for (Entry entry4 : map1.entrySet()) {
         String text5 = (String)entry4.getKey();
         InheritanceType inheritancetype6 = (InheritanceType)entry4.getValue();
         if (this.field3 != null) {
            Optional optional7 = this.field3.getFieldMapping(text5).map(Mapping::getDeobfuscatedName);
            if (optional7.isPresent()) {
               map2.putIfAbsent((String)optional7.get(), inheritancetype6);
               continue;
            }
         }

         map2.putIfAbsent(text5, inheritancetype6);
      }

      return map2;
   }

   public Map<MethodSignature, InheritanceType> getMethods() {
      Map map1 = this.field1.getMethods();
      HashMap map2 = new HashMap();

      for (Entry entry4 : map1.entrySet()) {
         MethodSignature methodsignature5 = (MethodSignature)entry4.getKey();
         InheritanceType inheritancetype6 = (InheritanceType)entry4.getValue();
         if (this.field3 != null && this.field2 != null) {
            Optional optional7 = this.field3.getMethodMapping(methodsignature5).map(Mapping::getDeobfuscatedName);
            if (optional7.isPresent()) {
               MethodDescriptor methoddescriptor8 = this.field2.deobfuscate(methodsignature5.getDescriptor());
               map2.put(new MethodSignature((String)optional7.get(), methoddescriptor8), inheritancetype6);
               continue;
            }
         }

         map2.put(methodsignature5, inheritancetype6);
      }

      return map2;
   }

   public Set<ClassInfo> provideParents(InheritanceProvider inheritanceprovider1) {
      LinkedHashSet set2 = new LinkedHashSet();
      this.provideParents(inheritanceprovider1, set2);
      LinkedHashSet set3 = new LinkedHashSet();

      for (ClassInfo classinfo5 : set2) {
         set3.add(new RemappedClassInfo(classinfo5, this.field2).lazy());
      }

      return set3;
   }

   public void provideParents(InheritanceProvider inheritanceprovider1, Collection<ClassInfo> list2) {
      String text3 = this.getSuperName();
      if (text3 != null && !text3.isEmpty()) {
         Optional optional4 = inheritanceprovider1.provide(this.getSuperName());
         if (optional4.isPresent()) {
            ClassInfo classinfo5 = (ClassInfo)optional4.get();
            list2.add(classinfo5);
            classinfo5.provideParents(inheritanceprovider1, list2);
         }
      }

      for (String text9 : this.getInterfaces()) {
         Optional optional6 = inheritanceprovider1.provide(text9);
         if (optional6.isPresent()) {
            ClassInfo classinfo7 = (ClassInfo)optional6.get();
            list2.add(classinfo7);
            classinfo7.provideParents(inheritanceprovider1, list2);
         }
      }
   }

   @Override
   public final boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      }

      if (!(obj1 instanceof InheritanceProvider)) {
         return false;
      }

      ClassInfo classinfo2 = (ClassInfo)obj1;
      return Objects.equals(this.getName(), classinfo2.getName());
   }

   @Override
   public final int hashCode() {
      return this.getName().hashCode();
   }

   @Override
   public String toString() {
      return "IchorClassInfo{name='"
         + this.getName()
         + "', interface="
         + this.isInterface()
         + ", superName='"
         + this.getSuperName()
         + "', interfaces="
         + this.getInterfaces()
         + ", fields="
         + this.getFields()
         + ", methods="
         + this.getMethods()
         + "}";
   }
}
