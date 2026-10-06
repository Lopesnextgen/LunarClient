package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;

public class CharacterBoxingCoercion implements MixinHelper_16 {
   @Override
   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method1(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3
   ) {
      com.moonsworth.lunar.ichor.mixin.DescriptorType var4 = var1.method2();
      com.moonsworth.lunar.ichor.mixin.DescriptorType var5 = var2.method2();
      if (var4 instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature var6 && var5 instanceof com.moonsworth.lunar.ichor.mixin.PrimitiveTypeSignature) {
         Character var7 = FatalIchorError6.method42(var6.name());
         if (var7 == null) {
            return var1;
         }

         var3.add(new MethodInsnNode(182, var6.name(), FatalIchorError6.method43(var7), "()" + var7));
         return new com.moonsworth.lunar.ichor.mixin.MixinHelper3(new com.moonsworth.lunar.ichor.mixin.PrimitiveTypeSignature(var7));
      } else {
         return var1;
      }
   }

   @Override
   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method2(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3
   ) {
      com.moonsworth.lunar.ichor.mixin.DescriptorType var4 = var1.method2();
      if (var4 instanceof com.moonsworth.lunar.ichor.mixin.PrimitiveTypeSignature && var2.method2() instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature var6) {
         Character var7 = FatalIchorError6.method42(var6.name());
         if (var7 == null) {
            return var2;
         }

         var3.add(new MethodInsnNode(184, var6.name(), "valueOf", "(" + var7 + ")L" + var6.name() + ";"));
         return new com.moonsworth.lunar.ichor.mixin.MixinHelper3(new com.moonsworth.lunar.ichor.mixin.PrimitiveTypeSignature(var7));
      } else {
         return var2;
      }
   }
}
