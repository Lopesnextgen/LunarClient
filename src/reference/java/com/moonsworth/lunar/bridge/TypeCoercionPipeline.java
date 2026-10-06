package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import java.util.List;
import org.cadixdev.bombe.provider.ClassProvider;
import org.objectweb.asm.tree.InsnList;

public class TypeCoercionPipeline {
   private final List<TypeCoercion> field1;
   private final List<TypeCoercion> field2;
   private final Bridge2_24 field3;

   public TypeCoercionPipeline(Bridge2_24 bridge2_241, Config config2, ClassProvider provider) {
      this(
         bridge2_241,
         List.of(
            new OptionalCoercion(),
            new RemappedTypeCoercion(bridge2_241, config2),
            new AdventureComponentCoercion(bridge2_241, config2),
            new CharacterCoercion(),
            new TypeConversionCoercion(provider),
            new SubtypeCoercion(provider)
         )
      );
   }

   public TypeCoercionPipeline(Bridge2_24 bridge2_241, List<TypeCoercion> list2) {
      this(bridge2_241, list2, list2);
   }

   public TypeCoercionPipeline(Bridge2_24 bridge2_241, List<TypeCoercion> list2, List<TypeCoercion> list) {
      this.field1 = list2;
      this.field2 = list;
      this.field3 = bridge2_241;
   }

   public void method1(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      if (!this.method3(mixinhelper31, mixinhelper32, instructions3)) {
         throw new IllegalStateException("Failed to coerce param type %s to %s. Functors: %s".formatted(mixinhelper31, mixinhelper32, this.field1));
      }
   }

   public void method2(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      if (!this.method4(mixinhelper31, mixinhelper32, instructions3)) {
         throw new IllegalStateException("Failed to coerce return type %s to %s. Functors: %s".formatted(mixinhelper31, mixinhelper32, this.field2));
      }
   }

   public boolean method3(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      if (mixinhelper31.equals(mixinhelper32)) {
         return true;
      }

      InsnList instructions4 = new InsnList();
      MixinHelper3 mixinhelper35 = mixinhelper31;

      for (TypeCoercion mixinhelper_167 : this.field1) {
         mixinhelper35 = mixinhelper_167.method1(mixinhelper35, mixinhelper32, instructions4);
      }

      this.field3.method5(mixinhelper35);
      if (!mixinhelper35.equals(mixinhelper32)) {
         return false;
      }

      instructions3.add(instructions4);
      return true;
   }

   public boolean method4(MixinHelper3 mixinhelper31, MixinHelper3 mixinhelper32, InsnList instructions3) {
      if (mixinhelper31.equals(mixinhelper32)) {
         return true;
      }

      InsnList instructions4 = new InsnList();
      MixinHelper3 mixinhelper35 = mixinhelper32;

      for (TypeCoercion mixinhelper_167 : this.field2) {
         InsnList instructions8 = new InsnList();
         mixinhelper35 = mixinhelper_167.method2(mixinhelper31, mixinhelper35, instructions8);
         instructions4.insert(instructions8);
      }

      this.field3.method5(mixinhelper35);
      if (!mixinhelper31.equals(mixinhelper35)) {
         return false;
      }

      instructions3.add(instructions4);
      return true;
   }
}
