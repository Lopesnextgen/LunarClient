package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.DescriptorType;
import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import java.util.Optional;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class OptionalCoercion implements TypeCoercion {
   private static final Type field1 = Type.getType(Optional.class);
   private static final String field2 = field1.getInternalName();
   private static final Type field3 = Type.getType(Object.class);

   public OptionalCoercion() {
   }

   public MixinHelper3 method1(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      DescriptorType mixinhelper4 = mixinhelper31.method2();
      DescriptorType mixinhelper5 = mixinhelper32.method2();
      if (!(mixinhelper4 instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature) || !((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)mixinhelper4).name().equals(field2)) {
         return mixinhelper31;
      } else if (mixinhelper5 instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature && ((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)mixinhelper5).name().equals(field2)) {
         return mixinhelper31;
      } else if (mixinhelper31.getParams() != null && !mixinhelper31.getParams().isEmpty()) {
         MixinHelper3 mixinhelper36 = (MixinHelper3)mixinhelper31.getParams().get(0);
         instructions3.add(new InsnNode(1));
         instructions3.add(new MethodInsnNode(182, field2, "orElse", Type.getMethodDescriptor(field3, new Type[]{field3}), false));
         instructions3.add(new TypeInsnNode(192, ((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)mixinhelper36.method2()).name()));
         return mixinhelper36;
      } else {
         throw new IllegalStateException("Optional type must have a specified generic parameter!");
      }
   }

   public MixinHelper3 method2(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      DescriptorType mixinhelper4 = mixinhelper31.method2();
      DescriptorType mixinhelper5 = mixinhelper32.method2();
      if (mixinhelper4 instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature && ((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)mixinhelper4).name().equals(field2)) {
         return mixinhelper32;
      } else if (!(mixinhelper5 instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)
         || !((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)mixinhelper5).name().equals(field2)) {
         return mixinhelper32;
      } else if (mixinhelper32.getParams() != null && !mixinhelper32.getParams().isEmpty()) {
         MixinHelper3 mixinhelper36 = (MixinHelper3)mixinhelper32.getParams().get(0);
         instructions3.add(new MethodInsnNode(184, field2, "ofNullable", Type.getMethodDescriptor(field1, new Type[]{field3}), false));
         return mixinhelper36;
      } else {
         throw new IllegalStateException("Optional type must have a specified generic parameter!");
      }
   }
}
