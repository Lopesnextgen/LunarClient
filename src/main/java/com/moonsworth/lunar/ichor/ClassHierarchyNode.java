package com.moonsworth.lunar.ichor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ClassHierarchyNode {
   private final String field1;
   private final ClassHierarchyNode field2;
   private final List<ClassHierarchyNode> interfaces;
   public static final ClassHierarchyNode field3 = new ClassHierarchyNode("java/lang/Object", null, List.of());

   public ClassHierarchyNode(String text, ClassHierarchyNode mixinlegacy2, List<ClassHierarchyNode> list) {
      this.field1 = text;
      this.field2 = mixinlegacy2;
      this.interfaces = list;
   }

   public String name() {
      return this.field1.replace('.', '/');
   }

   public boolean method1(ClassHierarchyNode mixinlegacy1) {
      if (this.field1.equals(mixinlegacy1.field1)) {
         return true;
      }

      LinkedHashSet set2 = new LinkedHashSet();

      for (ClassHierarchyNode mixinlegacy3 = mixinlegacy1; mixinlegacy3 != null; mixinlegacy3 = mixinlegacy3.field2) {
         set2.add(mixinlegacy3.field1);
      }

      if (set2.contains(this.field1)) {
         return true;
      }

      Set set4 = method2(mixinlegacy1);
      return set4.contains(this.field1);
   }

   private static Set<String> method2(ClassHierarchyNode mixinlegacy0) {
      LinkedHashSet set1 = new LinkedHashSet();

      for (ClassHierarchyNode mixinlegacy3 : mixinlegacy0.interfaces) {
         set1.add(mixinlegacy3.field1);
         set1.addAll(method2(mixinlegacy3));
      }

      return set1;
   }

   public ClassHierarchyNode method3() {
      return this.field2;
   }

   public List<ClassHierarchyNode> getInterfaces() {
      return this.interfaces;
   }
}
