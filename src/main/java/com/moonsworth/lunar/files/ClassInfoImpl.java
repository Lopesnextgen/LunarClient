package com.moonsworth.lunar.files;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.cadixdev.bombe.analysis.InheritanceProvider;
import org.cadixdev.bombe.analysis.InheritanceType;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo.Abstract;
import org.cadixdev.bombe.type.signature.FieldSignature;
import org.cadixdev.bombe.type.signature.MethodSignature;

public class ClassInfoImpl extends Abstract implements ClassInfo {
   protected final String field1;
   protected final boolean field2;
   protected final String field3;
   protected final List<String> field4;
   protected final Map<FieldSignature, InheritanceType> field5;
   protected final Map<String, InheritanceType> field6;
   protected final Map<MethodSignature, InheritanceType> field7;
   protected Set<ClassInfo> parents;

   public ClassInfoImpl(
      String text,
      boolean flag,
      String text2,
      List<String> list,
      Map<FieldSignature, InheritanceType> map,
      Map<String, InheritanceType> map2,
      Map<MethodSignature, InheritanceType> map3
   ) {
      this.field1 = text;
      this.field2 = flag;
      this.field3 = text2 != null ? text2 : "";
      this.field4 = Collections.unmodifiableList(list);
      this.field5 = Collections.unmodifiableMap(map);
      this.field6 = Collections.unmodifiableMap(map2);
      this.field7 = Collections.unmodifiableMap(map3);
   }

   public String getName() {
      return this.field1;
   }

   public boolean isInterface() {
      return this.field2;
   }

   public String getSuperName() {
      return this.field3;
   }

   public List<String> getInterfaces() {
      return this.field4;
   }

   public Map<FieldSignature, InheritanceType> getFields() {
      return this.field5;
   }

   public Map<String, InheritanceType> getFieldsByName() {
      return this.field6;
   }

   public Map<MethodSignature, InheritanceType> getMethods() {
      return this.field7;
   }

   public Set<ClassInfo> provideParents(InheritanceProvider inheritanceprovider1) {
      LinkedHashSet set2 = new LinkedHashSet();
      this.provideParents(inheritanceprovider1, set2);
      return Collections.unmodifiableSet(set2);
   }

   public void provideParents(InheritanceProvider inheritanceprovider1, Collection<ClassInfo> list) {
      inheritanceprovider1.provide(this.getSuperName()).ifPresent(arg2x -> {
         list.add(arg2x);
         arg2x.provideParents(inheritanceprovider1, list);
      });

      for (String text4 : this.getInterfaces()) {
         inheritanceprovider1.provide(text4).ifPresent(arg2x -> {
            list.add(arg2x);
            arg2x.provideParents(inheritanceprovider1, list);
         });
      }
   }

   public ClassInfo lazy() {
      return this;
   }
}
