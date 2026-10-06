package com.moonsworth.lunar.bridge;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.cadixdev.bombe.provider.ClassProvider;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class TypeConversionAdapter implements MixinHelper_16 {
   private static final String field1 = Type.getInternalName(Bridge5_10.class);
   private final Map<String, String> field2 = new HashMap<>();

   public TypeConversionAdapter(ClassProvider var1) {
      ClassNode var2 = var1.getAsNode(field1, 1);

      for (MethodNode var4 : var2.methods) {
         if ((var4.access & 8) != 0 && Type.getReturnType(var4.desc) != Type.VOID_TYPE && Type.getArgumentTypes(var4.desc).length == 1) {
            String var5 = Objects.requireNonNullElse(var4.signature, var4.desc);
            if (this.field2.containsKey(var5)) {
               throw new IllegalStateException(
                  String.format(
                     "Both %s and %s in TypeConversionUtil share the signature %s. Ensure only one method exists per signature!",
                     this.field2.get(var5),
                     var4.name,
                     var5
                  )
               );
            }

            this.field2.put(var5, var4.name);
         }
      }
   }

   @Override
   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method1(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3
   ) {
      com.moonsworth.lunar.ichor.mixin.DescriptorType var4 = var1.method2();
      com.moonsworth.lunar.ichor.mixin.DescriptorType var5 = var2.method2();
      String var6 = "(" + var4.getDescriptor() + ")" + var5.getDescriptor();
      String var7 = "(" + var1.toString() + ")" + var2;
      if (this.field2.containsKey(var7)) {
         var3.add(new MethodInsnNode(184, field1, this.field2.get(var7), var6));
         return var2;
      } else {
         return var1;
      }
   }

   @Override
   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method2(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3
   ) {
      com.moonsworth.lunar.ichor.mixin.DescriptorType var4 = var1.method2();
      com.moonsworth.lunar.ichor.mixin.DescriptorType var5 = var2.method2();
      String var6 = "(" + var4.getDescriptor() + ")" + var5.getDescriptor();
      String var7 = "(" + var1.toString() + ")" + var2;
      if (this.field2.containsKey(var7)) {
         var3.add(new MethodInsnNode(184, field1, this.field2.get(var7), var6));
         return var1;
      } else {
         return var2;
      }
   }
}
