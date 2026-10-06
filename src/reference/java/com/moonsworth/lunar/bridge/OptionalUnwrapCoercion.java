package com.moonsworth.lunar.bridge;

import java.util.Optional;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class OptionalUnwrapCoercion implements MixinHelper_16 {
   private static final Type field1 = Type.getType(Optional.class);
   private static final String field2 = field1.getInternalName();
   private static final Type field3 = Type.getType(Object.class);

   @Override
   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method1(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3
   ) {
      com.moonsworth.lunar.ichor.mixin.DescriptorType var4 = var1.method2();
      com.moonsworth.lunar.ichor.mixin.DescriptorType var5 = var2.method2();
      if (!(var4 instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature) || !((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)var4).name().equals(field2)) {
         return var1;
      } else if (var5 instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature && ((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)var5).name().equals(field2)) {
         return var1;
      } else if (var1.getParams() != null && !var1.getParams().isEmpty()) {
         com.moonsworth.lunar.ichor.mixin.MixinHelper3 var6 = var1.getParams().get(0);
         var3.add(new InsnNode(1));
         var3.add(new MethodInsnNode(182, field2, "orElse", Type.getMethodDescriptor(field3, new Type[]{field3}), false));
         var3.add(new TypeInsnNode(192, ((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)var6.method2()).name()));
         return var6;
      } else {
         throw new IllegalStateException("Optional type must have a specified generic parameter!");
      }
   }

   @Override
   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method2(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3
   ) {
      com.moonsworth.lunar.ichor.mixin.DescriptorType var4 = var1.method2();
      com.moonsworth.lunar.ichor.mixin.DescriptorType var5 = var2.method2();
      if (var4 instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature && ((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)var4).name().equals(field2)) {
         return var2;
      } else if (!(var5 instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)
         || !((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)var5).name().equals(field2)) {
         return var2;
      } else if (var2.getParams() != null && !var2.getParams().isEmpty()) {
         com.moonsworth.lunar.ichor.mixin.MixinHelper3 var6 = var2.getParams().get(0);
         var3.add(new MethodInsnNode(184, field2, "ofNullable", Type.getMethodDescriptor(field1, new Type[]{field3}), false));
         return var6;
      } else {
         throw new IllegalStateException("Optional type must have a specified generic parameter!");
      }
   }
}
