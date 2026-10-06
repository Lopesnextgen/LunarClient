package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.DescriptorType;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.cadixdev.bombe.provider.ClassProvider;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class TypeConversionCoercion implements TypeCoercion {
   private static final String field1 = Type.getInternalName(TypeConversionUtilBridge.class);
   private final Map<String, String> field2 = new HashMap<>();

   public TypeConversionCoercion(ClassProvider provider) {
      ClassNode node2 = provider.getAsNode(field1, 1);

      for (MethodNode method4 : node2.methods) {
         if ((method4.access & 8) != 0 && Type.getReturnType(method4.desc) != Type.VOID_TYPE && Type.getArgumentTypes(method4.desc).length == 1) {
            String text5 = Objects.requireNonNullElse(method4.signature, method4.desc);
            if (this.field2.containsKey(text5)) {
               throw new IllegalStateException(
                  String.format(
                     "Both %s and %s in TypeConversionUtil share the signature %s. Ensure only one method exists per signature!",
                     this.field2.get(text5),
                     method4.name,
                     text5
                  )
               );
            }

            this.field2.put(text5, method4.name);
         }
      }
   }

   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method1(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 mixinhelper31, com.moonsworth.lunar.ichor.mixin.MixinHelper3 mixinhelper32, InsnList instructions3
   ) {
      DescriptorType mixinhelper4 = mixinhelper31.method2();
      DescriptorType mixinhelper5 = mixinhelper32.method2();
      String text6 = "(" + mixinhelper4.getDescriptor() + ")" + mixinhelper5.getDescriptor();
      String text7 = "(" + mixinhelper31.toString() + ")" + mixinhelper32;
      if (this.field2.containsKey(text7)) {
         instructions3.add(new MethodInsnNode(184, field1, this.field2.get(text7), text6));
         return mixinhelper32;
      } else {
         return mixinhelper31;
      }
   }

   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method2(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 mixinhelper31, com.moonsworth.lunar.ichor.mixin.MixinHelper3 mixinhelper32, InsnList instructions3
   ) {
      DescriptorType mixinhelper4 = mixinhelper31.method2();
      DescriptorType mixinhelper5 = mixinhelper32.method2();
      String text6 = "(" + mixinhelper4.getDescriptor() + ")" + mixinhelper5.getDescriptor();
      String text7 = "(" + mixinhelper31.toString() + ")" + mixinhelper32;
      if (this.field2.containsKey(text7)) {
         instructions3.add(new MethodInsnNode(184, field1, this.field2.get(text7), text6));
         return mixinhelper31;
      } else {
         return mixinhelper32;
      }
   }
}
