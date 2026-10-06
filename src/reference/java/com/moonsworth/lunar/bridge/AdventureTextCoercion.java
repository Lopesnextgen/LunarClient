package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.config.Config;
import net.kyori.adventure.text.Component;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class AdventureTextCoercion implements MixinHelper_16 {
   private static final String field1 = Type.getType(AdventureTextBridge.class).getInternalName();
   private static final Type field2 = Type.getType(Bridge2_42.class);
   private static final String field3 = field2.getInternalName();
   private static final Type field4 = Type.getType(Component.class);
   private static final String field5 = field4.getInternalName();
   private static final Type field6 = Type.getType(String.class);
   private static final String field7 = field6.getInternalName();
   private final String field8;

   public AdventureTextCoercion(Bridge2_24 var1, Config var2) {
      this.field8 = var1.method4(field3, var2).orElseThrow().method1(var2);
   }

   @Override
   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method1(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3
   ) {
      com.moonsworth.lunar.ichor.mixin.DescriptorType var4 = var1.method2();
      com.moonsworth.lunar.ichor.mixin.DescriptorType var5 = var2.method2();
      if (!(var4 instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature) || !((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)var4).name().equals(field5)) {
         return var1;
      }

      if (!(var5 instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)) {
         return var1;
      }

      String var6 = ((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)var5).name();
      if (var6.equals(field7)) {
         var3.add(new MethodInsnNode(184, field1, "asLegacyString", Type.getMethodDescriptor(field6, new Type[]{field4})));
      } else {
         if (!var6.equals(this.field8)) {
            throw new UnsupportedOperationException("Don't know how to coerce type " + var1 + " to an Adventure component!");
         }

         var3.add(new MethodInsnNode(184, field1, "asBridge", Type.getMethodDescriptor(field2, new Type[]{field4})));
         var3.add(new TypeInsnNode(192, var6));
      }

      return var2;
   }

   @Override
   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method2(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3
   ) {
      com.moonsworth.lunar.ichor.mixin.DescriptorType var4 = var1.method2();
      com.moonsworth.lunar.ichor.mixin.DescriptorType var5 = var2.method2();
      if (!(var4 instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)) {
         return var2;
      }

      if (var5 instanceof com.moonsworth.lunar.ichor.mixin.ClassTypeSignature && ((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)var5).name().equals(field5)) {
         String var6 = ((com.moonsworth.lunar.ichor.mixin.ClassTypeSignature)var4).name();
         if (var6.equals(field7)) {
            var3.add(new MethodInsnNode(184, field1, "asAdventure", Type.getMethodDescriptor(field4, new Type[]{field6})));
         } else {
            if (!var6.equals(this.field8)) {
               throw new UnsupportedOperationException("Don't know how to coerce type " + var1 + " to an Adventure component!");
            }

            var3.add(new TypeInsnNode(192, field3));
            var3.add(new MethodInsnNode(184, field1, "asAdventure", Type.getMethodDescriptor(field4, new Type[]{field2})));
         }

         return var1;
      } else {
         return var2;
      }
   }
}
