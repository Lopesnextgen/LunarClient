package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.DescriptorType;
import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import com.moonsworth.lunar.ichor.mixin.PrimitiveTypeSignature;
import com.moonsworth.lunar.ichor.mixin.ClassTypeSignature;
import com.moonsworth.lunar.ichor.util.AsmUtils;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;

public class CharacterCoercion implements TypeCoercion {
   public CharacterCoercion() {
   }

   public MixinHelper3 method1(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      DescriptorType mixinhelper4 = mixinhelper31.method2();
      DescriptorType mixinhelper5 = mixinhelper32.method2();
      if (mixinhelper4 instanceof ClassTypeSignature mixinhelper76 && mixinhelper5 instanceof PrimitiveTypeSignature) {
         Character character7 = AsmUtils.method42(mixinhelper76.name());
         if (character7 == null) {
            return mixinhelper31;
         }

         instructions3.add(new MethodInsnNode(182, mixinhelper76.name(), AsmUtils.method43(character7), "()" + character7));
         return new MixinHelper3(new PrimitiveTypeSignature(character7));
      } else {
         return mixinhelper31;
      }
   }

   public MixinHelper3 method2(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      DescriptorType mixinhelper4 = mixinhelper31.method2();
      if (mixinhelper4 instanceof PrimitiveTypeSignature && mixinhelper32.method2() instanceof ClassTypeSignature mixinhelper76) {
         Character character7 = AsmUtils.method42(mixinhelper76.name());
         if (character7 == null) {
            return mixinhelper32;
         }

         instructions3.add(new MethodInsnNode(184, mixinhelper76.name(), "valueOf", "(" + character7 + ")L" + mixinhelper76.name() + ";"));
         return new MixinHelper3(new PrimitiveTypeSignature(character7));
      } else {
         return mixinhelper32;
      }
   }
}
