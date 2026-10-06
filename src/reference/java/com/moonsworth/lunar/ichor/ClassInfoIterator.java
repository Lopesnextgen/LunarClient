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
import org.cadixdev.bombe.analysis.InheritanceType;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.bombe.type.FieldType;
import org.cadixdev.bombe.type.MethodDescriptor;
import org.cadixdev.bombe.type.signature.FieldSignature;
import org.cadixdev.bombe.type.signature.MethodSignature;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.Mapping;

public class ClassInfoIterator implements ClassInfo {
   protected final ClassInfo field1;
   @Nullable
   protected final MappingSet field2;
   private final ClassMapping<?, ?> field3;

   public ClassInfoIterator(ClassInfo var1, @Nullable MappingSet var2) {
      this.field1 = var1;
      if (var2 != null) {
         this.field2 = var2;
         String var3 = MixinMisc4.method3(var2, var1.getName(), null);
         if (var3 != null) {
            this.field3 = (ClassMapping<?, ?>)var2.getClassMapping(var3).orElse(null);
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
      String var1 = this.field1.getSuperName();
      if (this.field2 != null) {
         String var2 = MixinMisc4.method3(this.field2, var1, null);
         if (var2 != null) {
            Optional var3 = this.field2.getClassMapping(var2);
            if (var3.isPresent()) {
               return ((ClassMapping)var3.get()).getFullDeobfuscatedName();
            }
         }
      }

      return var1;
   }

   public List<String> getInterfaces() {
      ArrayList var1 = new ArrayList();

      for (String var3 : this.field1.getInterfaces()) {
         if (this.field2 != null) {
            String var4 = MixinMisc4.method3(this.field2, var3, null);
            if (var4 != null) {
               Optional var5 = this.field2.getClassMapping(var4);
               if (var5.isPresent()) {
                  var3 = ((ClassMapping)var5.get()).getFullDeobfuscatedName();
               }
            }
         }

         var1.add(var3);
      }

      return var1;
   }

   public Map<FieldSignature, InheritanceType> getFields() {
      Map var1 = this.field1.getFields();
      HashMap var2 = new HashMap();

      for (Entry var4 : var1.entrySet()) {
         FieldSignature var5 = (FieldSignature)var4.getKey();
         InheritanceType var6 = (InheritanceType)var4.getValue();
         if (this.field3 != null && this.field2 != null) {
            Optional var7 = this.field3.getFieldMapping(var5).map(Mapping::getDeobfuscatedName);
            if (var7.isPresent()) {
               FieldType var8 = (FieldType)var5.getType().orElseThrow();
               FieldType var9 = this.field2.deobfuscate(var8);
               var2.putIfAbsent(new FieldSignature((String)var7.get(), var9), var6);
               continue;
            }
         }

         var2.putIfAbsent(var5, var6);
      }

      return var2;
   }

   public Map<String, InheritanceType> getFieldsByName() {
      Map var1 = this.field1.getFieldsByName();
      HashMap var2 = new HashMap();

      for (Entry var4 : var1.entrySet()) {
         String var5 = (String)var4.getKey();
         InheritanceType var6 = (InheritanceType)var4.getValue();
         if (this.field3 != null) {
            Optional var7 = this.field3.getFieldMapping(var5).map(Mapping::getDeobfuscatedName);
            if (var7.isPresent()) {
               var2.putIfAbsent((String)var7.get(), var6);
               continue;
            }
         }

         var2.putIfAbsent(var5, var6);
      }

      return var2;
   }

   public Map<MethodSignature, InheritanceType> getMethods() {
      Map var1 = this.field1.getMethods();
      HashMap var2 = new HashMap();

      for (Entry var4 : var1.entrySet()) {
         MethodSignature var5 = (MethodSignature)var4.getKey();
         InheritanceType var6 = (InheritanceType)var4.getValue();
         if (this.field3 != null && this.field2 != null) {
            Optional var7 = this.field3.getMethodMapping(var5).map(Mapping::getDeobfuscatedName);
            if (var7.isPresent()) {
               MethodDescriptor var8 = this.field2.deobfuscate(var5.getDescriptor());
               var2.put(new MethodSignature((String)var7.get(), var8), var6);
               continue;
            }
         }

         var2.put(var5, var6);
      }

      return var2;
   }

   public Set<ClassInfo> provideParents(org.cadixdev.bombe.analysis.InheritanceProvider var1) {
      LinkedHashSet var2 = new LinkedHashSet();
      this.provideParents(var1, var2);
      LinkedHashSet var3 = new LinkedHashSet();

      for (ClassInfo var5 : var2) {
         var3.add(new ClassInfoIterator(var5, this.field2).lazy());
      }

      return var3;
   }

   public void provideParents(org.cadixdev.bombe.analysis.InheritanceProvider var1, Collection<ClassInfo> var2) {
      String var3 = this.getSuperName();
      if (var3 != null && !var3.isEmpty()) {
         Optional var4 = var1.provide(this.getSuperName());
         if (var4.isPresent()) {
            ClassInfo var5 = (ClassInfo)var4.get();
            var2.add(var5);
            var5.provideParents(var1, var2);
         }
      }

      for (String var9 : this.getInterfaces()) {
         Optional var6 = var1.provide(var9);
         if (var6.isPresent()) {
            ClassInfo var7 = (ClassInfo)var6.get();
            var2.add(var7);
            var7.provideParents(var1, var2);
         }
      }
   }

   @Override
   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      }

      if (!(var1 instanceof org.cadixdev.bombe.analysis.InheritanceProvider)) {
         return false;
      }

      ClassInfo var2 = (ClassInfo)var1;
      return Objects.equals(this.getName(), var2.getName());
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
