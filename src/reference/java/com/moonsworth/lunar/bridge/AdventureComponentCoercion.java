package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import com.moonsworth.lunar.ichor.mixin.ClassTypeSignature;
import net.kyori.adventure.text.Component;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class AdventureComponentCoercion implements TypeCoercion {
   private static final String field1 = Type.getType(TextBridge.class).getInternalName();
   private static final Type field2 = Type.getType(Bridge2_42.class);
   private static final String field3 = field2.getInternalName();
   private static final Type field4 = Type.getType(Component.class);
   private static final String field5 = field4.getInternalName();
   private static final Type field6 = Type.getType(String.class);
   private static final String field7 = field6.getInternalName();
   private final String field8;

   public AdventureComponentCoercion(Bridge2_24 bridge2_241, Config config2) {
      this.field8 = ((Bridge2_35)bridge2_241.method4(field3, config2).orElseThrow()).method1(config2);
   }

   public MixinHelper3 method1(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      com.moonsworth.lunar.ichor.mixin.DescriptorType mixinhelper4 = mixinhelper31.method2();
      com.moonsworth.lunar.ichor.mixin.DescriptorType mixinhelper5 = mixinhelper32.method2();
      if (!(mixinhelper4 instanceof ClassTypeSignature) || !((ClassTypeSignature)mixinhelper4).name().equals(field5)) {
         return mixinhelper31;
      }

      if (!(mixinhelper5 instanceof ClassTypeSignature)) {
         return mixinhelper31;
      }

      String text6 = ((ClassTypeSignature)mixinhelper5).name();
      if (text6.equals(field7)) {
         instructions3.add(new MethodInsnNode(184, field1, "asLegacyString", Type.getMethodDescriptor(field6, new Type[]{field4})));
      } else {
         if (!text6.equals(this.field8)) {
            throw new UnsupportedOperationException("Don't know how to coerce type " + mixinhelper31 + " to an Adventure component!");
         }

         instructions3.add(new MethodInsnNode(184, field1, "asBridge", Type.getMethodDescriptor(field2, new Type[]{field4})));
         instructions3.add(new TypeInsnNode(192, text6));
      }

      return mixinhelper32;
   }

   public MixinHelper3 method2(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      com.moonsworth.lunar.ichor.mixin.DescriptorType mixinhelper4 = mixinhelper31.method2();
      com.moonsworth.lunar.ichor.mixin.DescriptorType mixinhelper5 = mixinhelper32.method2();
      if (!(mixinhelper4 instanceof ClassTypeSignature)) {
         return mixinhelper32;
      }

      if (mixinhelper5 instanceof ClassTypeSignature && ((ClassTypeSignature)mixinhelper5).name().equals(field5)) {
         String text6 = ((ClassTypeSignature)mixinhelper4).name();
         if (text6.equals(field7)) {
            instructions3.add(new MethodInsnNode(184, field1, "asAdventure", Type.getMethodDescriptor(field4, new Type[]{field6})));
         } else {
            if (!text6.equals(this.field8)) {
               throw new UnsupportedOperationException("Don't know how to coerce type " + mixinhelper31 + " to an Adventure component!");
            }

            instructions3.add(new TypeInsnNode(192, field3));
            instructions3.add(new MethodInsnNode(184, field1, "asAdventure", Type.getMethodDescriptor(field4, new Type[]{field2})));
         }

         return mixinhelper31;
      } else {
         return mixinhelper32;
      }
   }
}
