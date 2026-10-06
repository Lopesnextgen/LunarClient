package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.DescriptorType;
import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import com.moonsworth.lunar.ichor.mixin.ClassTypeSignature;
import com.moonsworth.lunar.ichor.util.AsmUtils;
import java.util.Set;
import org.cadixdev.bombe.provider.ClassProvider;
import org.objectweb.asm.tree.InsnList;

public class SubtypeCoercion implements TypeCoercion {
   private final ClassProvider field1;

   public SubtypeCoercion(ClassProvider provider) {
      this.field1 = provider;
   }

   public MixinHelper3 method1(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      if (mixinhelper31.equals(mixinhelper32)) {
         return mixinhelper32;
      } else {
         DescriptorType mixinhelper4 = mixinhelper31.method2();
         DescriptorType mixinhelper5 = mixinhelper32.method2();
         if (mixinhelper4 instanceof ClassTypeSignature mixinhelper76 && mixinhelper5 instanceof ClassTypeSignature mixinhelper77) {
            String text8 = mixinhelper76.name();
            Set set9 = AsmUtils.method9(text8, this.field1);
            return set9.contains(mixinhelper77.name()) ? mixinhelper32 : mixinhelper31;
         } else {
            return mixinhelper31;
         }
      }
   }

   public MixinHelper3 method2(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      if (mixinhelper31.equals(mixinhelper32)) {
         return mixinhelper31;
      } else {
         DescriptorType mixinhelper4 = mixinhelper31.method2();
         DescriptorType mixinhelper5 = mixinhelper32.method2();
         if (mixinhelper4 instanceof ClassTypeSignature mixinhelper76 && mixinhelper5 instanceof ClassTypeSignature mixinhelper77) {
            String text8 = mixinhelper76.name();
            Set set9 = AsmUtils.method9(text8, this.field1);
            return set9.contains(mixinhelper77.name()) ? mixinhelper31 : mixinhelper32;
         } else {
            return mixinhelper32;
         }
      }
   }

   public ClassProvider method3() {
      return this.field1;
   }
}
