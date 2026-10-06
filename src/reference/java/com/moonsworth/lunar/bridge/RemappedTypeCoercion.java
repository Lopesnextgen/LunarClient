package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.mixin.DescriptorType;
import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import com.moonsworth.lunar.ichor.mixin.ClassTypeSignature;
import java.util.Optional;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.Mapping;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.TypeInsnNode;

public class RemappedTypeCoercion implements TypeCoercion {
   private final Bridge2_24 field1;
   private final Config field2;

   public RemappedTypeCoercion(Bridge2_24 bridge2_241, Config config2) {
      this.field1 = bridge2_241;
      this.field2 = config2;
   }

   public MixinHelper3 method1(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      DescriptorType mixinhelper4 = mixinhelper31.method2();
      DescriptorType mixinhelper5 = mixinhelper32.method2();
      if (mixinhelper4 instanceof ClassTypeSignature && mixinhelper5 instanceof ClassTypeSignature) {
         String text6 = ((ClassTypeSignature)mixinhelper4).name();
         Optional optional7 = this.field1.method4(text6, this.field2);
         if (optional7.isPresent()) {
            String text8 = ((Bridge2_35)optional7.get()).method1(this.field2);
            if (text8 != null) {
               MappingSet mappingset9 = this.field1.getMappings();
               if (mappingset9 != null) {
                  text8 = mappingset9.getClassMapping(text8).<String>map(Mapping::getFullDeobfuscatedName).orElse(text8);
               }

               instructions3.add(new TypeInsnNode(192, text8));
               return new MixinHelper3(new ClassTypeSignature(text8));
            }
         }

         return mixinhelper31;
      } else {
         return mixinhelper31;
      }
   }

   public MixinHelper3 method2(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      DescriptorType mixinhelper4 = mixinhelper31.method2();
      DescriptorType mixinhelper5 = mixinhelper32.method2();
      if (mixinhelper4 instanceof ClassTypeSignature && mixinhelper5 instanceof ClassTypeSignature) {
         String text6 = ((ClassTypeSignature)mixinhelper5).name();
         Optional optional7 = this.field1.method4(text6, this.field2);
         if (optional7.isPresent()) {
            String text8 = ((Bridge2_35)optional7.get()).method1(this.field2);
            if (text8 != null) {
               MappingSet mappingset9 = this.field1.getMappings();
               if (mappingset9 != null) {
                  text8 = mappingset9.getClassMapping(text8).<String>map(Mapping::getFullDeobfuscatedName).orElse(text8);
               }

               instructions3.add(new TypeInsnNode(192, text6));
               return new MixinHelper3(new ClassTypeSignature(text8));
            }
         }

         return mixinhelper32;
      } else {
         return mixinhelper32;
      }
   }

   public Bridge2_24 method3() {
      return this.field1;
   }

   public Config method4() {
      return this.field2;
   }
}
