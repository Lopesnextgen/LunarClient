package com.moonsworth.lunar.ichor;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import org.cadixdev.bombe.analysis.InheritanceType;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo.Impl;
import org.cadixdev.bombe.type.signature.FieldSignature;
import org.cadixdev.bombe.type.signature.MethodSignature;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class InheritanceProvider implements org.cadixdev.bombe.analysis.InheritanceProvider {
   private final URLClassLoader field1;

   public InheritanceProvider(URLClassLoader var1) {
      this.field1 = var1;
   }

   public Optional<ClassInfo> provide(String var1) {
      if (var1.startsWith("[")) {
         return Optional.empty();
      }

      try {
         ClassNode var2 = this.field1.method6(var1, true);
         if (var2 != null) {
            ClassInfo var3 = method1(var2);
            return Optional.of(var3);
         }
      } catch (Exception var4) {
      }

      return Optional.empty();
   }

   public static ClassInfo method1(ClassNode type) {
      HashMap var1 = new HashMap();
      HashMap var2 = new HashMap();
      HashMap var3 = new HashMap();

      for (FieldNode var5 : type.fields) {
         InheritanceType var6 = InheritanceType.fromModifiers(var5.access);
         var1.put(FieldSignature.of(var5.name, var5.desc), var6);
         var2.put(var5.name, var6);
      }

      for (MethodNode var8 : type.methods) {
         InheritanceType var9 = InheritanceType.fromModifiers(var8.access);
         var3.put(MethodSignature.of(var8.name, var8.desc), var9);
      }

      return new Impl(type.name, (type.access & 512) != 0, type.superName, type.interfaces, var1, var2, var3) {
         public synchronized Set<ClassInfo> provideParents(org.cadixdev.bombe.analysis.InheritanceProvider var1) {
            return super.provideParents(var1);
         }
      };
   }
}
