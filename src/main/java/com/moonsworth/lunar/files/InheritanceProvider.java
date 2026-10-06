package com.moonsworth.lunar.files;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.cadixdev.bombe.analysis.InheritanceType;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo.Abstract;
import org.cadixdev.bombe.provider.ClassProvider;
import org.cadixdev.bombe.type.signature.FieldSignature;
import org.cadixdev.bombe.type.signature.MethodSignature;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class InheritanceProvider implements org.cadixdev.bombe.analysis.InheritanceProvider {
   private final int field1;
   private final ClassProvider field2;

   public InheritanceProvider(int var1, ClassProvider var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public InheritanceProvider(ClassProvider var1) {
      this(458752, var1);
   }

   public Optional<ClassInfo> provide(String var1) {
      byte[] var2 = this.field2.get(var1);
      if (var2 == null) {
         return Optional.empty();
      }

      ClassReader var3 = new ClassReader(var2);
      InheritanceProvider.Data2 var4 = new InheritanceProvider.Data2(this.field1);
      var3.accept(var4, 7);
      return Optional.of(var4.create());
   }

   public static class Data extends Abstract implements ClassInfo {
      protected final String field1;
      protected final boolean field2;
      protected final String field3;
      protected final List<String> field4;
      protected final Map<FieldSignature, InheritanceType> field5;
      protected final Map<String, InheritanceType> field6;
      protected final Map<MethodSignature, InheritanceType> field7;
      protected Set<ClassInfo> parents;

      public Data(
         String var1,
         boolean var2,
         String var3,
         List<String> var4,
         Map<FieldSignature, InheritanceType> var5,
         Map<String, InheritanceType> var6,
         Map<MethodSignature, InheritanceType> map
      ) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3 != null ? var3 : "";
         this.field4 = Collections.unmodifiableList(var4);
         this.field5 = Collections.unmodifiableMap(var5);
         this.field6 = Collections.unmodifiableMap(var6);
         this.field7 = Collections.unmodifiableMap(map);
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

      public Set<ClassInfo> provideParents(org.cadixdev.bombe.analysis.InheritanceProvider var1) {
         LinkedHashSet var2 = new LinkedHashSet();
         this.provideParents(var1, var2);
         return Collections.unmodifiableSet(var2);
      }

      public void provideParents(org.cadixdev.bombe.analysis.InheritanceProvider var1, Collection<ClassInfo> var2) {
         var1.provide(this.getSuperName()).ifPresent(var2x -> {
            var2.add(var2x);
            var2x.provideParents(var1, var2);
         });

         for (String var4 : this.getInterfaces()) {
            var1.provide(var4).ifPresent(var2x -> {
               var2.add(var2x);
               var2x.provideParents(var1, var2);
            });
         }
      }

      public ClassInfo lazy() {
         return this;
      }
   }

   public static class Data2 extends ClassVisitor {
      private String name;
      private boolean isInterface;
      private String superName;
      private List<String> interfaces = Collections.emptyList();
      private final Map<FieldSignature, InheritanceType> field1 = new HashMap<>();
      private final Map<String, InheritanceType> fieldsByName = new HashMap<>();
      private final Map<MethodSignature, InheritanceType> field2 = new HashMap<>();

      Data2(int var1) {
         super(var1);
      }

      ClassInfo create() {
         return new InheritanceProvider.Data(this.name, this.isInterface, this.superName, this.interfaces, this.field1, this.fieldsByName, this.field2);
      }

      public void visit(int var1, int var2, String var3, String var4, String var5, String[] var6) {
         this.name = var3;
         this.isInterface = (var2 & 512) != 0;
         this.superName = var5;
         this.interfaces = Arrays.asList(var6);
      }

      public FieldVisitor visitField(int var1, String var2, String var3, String var4, Object var5) {
         InheritanceType var6 = InheritanceType.fromModifiers(var1);
         this.field1.put(FieldSignature.of(var2, var3), var6);
         this.fieldsByName.put(var2, var6);
         return null;
      }

      public MethodVisitor visitMethod(int var1, String var2, String var3, String var4, String[] var5) {
         this.field2.put(MethodSignature.of(var2, var3), InheritanceType.fromModifiers(var1));
         return null;
      }
   }
}
