package com.moonsworth.lunar.ichor;

import java.util.HashMap;
import java.util.Optional;
import org.cadixdev.bombe.analysis.InheritanceType;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.bombe.type.signature.FieldSignature;
import org.cadixdev.bombe.type.signature.MethodSignature;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class ClassNodeInheritanceProvider implements org.cadixdev.bombe.analysis.InheritanceProvider {
   private final URLClassLoader field1;

   public ClassNodeInheritanceProvider(URLClassLoader type) {
      this.field1 = type;
   }

   public Optional<ClassInfo> provide(String text) {
      if (text.startsWith("[")) {
         return Optional.empty();
      }

      try {
         ClassNode node2 = this.field1.method6(text, true);
         if (node2 != null) {
            ClassInfo classinfo3 = method1(node2);
            return Optional.of(classinfo3);
         }
      } catch (Exception exception4) {
      }

      return Optional.empty();
   }

   public static ClassInfo method1(ClassNode node0) {
      HashMap map1 = new HashMap();
      HashMap map2 = new HashMap();
      HashMap map3 = new HashMap();

      for (FieldNode field5 : node0.fields) {
         InheritanceType inheritancetype6 = InheritanceType.fromModifiers(field5.access);
         map1.put(FieldSignature.of(field5.name, field5.desc), inheritancetype6);
         map2.put(field5.name, inheritancetype6);
      }

      for (MethodNode method8 : node0.methods) {
         InheritanceType inheritancetype9 = InheritanceType.fromModifiers(method8.access);
         map3.put(MethodSignature.of(method8.name, method8.desc), inheritancetype9);
      }

      return new ClassNodeClassInfo(node0.name, (node0.access & 512) != 0, node0.superName, node0.interfaces, map1, map2, map3);
   }
}
