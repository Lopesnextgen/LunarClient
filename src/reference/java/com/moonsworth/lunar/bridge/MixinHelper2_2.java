package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.config.Config;
import java.util.List;
import org.cadixdev.bombe.provider.ClassProvider;
import org.objectweb.asm.tree.InsnList;

public class MixinHelper2_2 {
   private final List<MixinHelper_16> field1;
   private final List<MixinHelper_16> field2;
   private final Bridge2_24 field3;

   public MixinHelper2_2(Bridge2_24 var1, Config var2, ClassProvider var3) {
      this(
         var1,
         List.of(
            new OptionalUnwrapCoercion(),
            new MappedTypeCoercion(var1, var2),
            new AdventureTextCoercion(var1, var2),
            new CharacterBoxingCoercion(),
            new TypeConversionAdapter(var3),
            new SupertypeCoercion(var3)
         )
      );
   }

   public MixinHelper2_2(Bridge2_24 var1, List<MixinHelper_16> var2) {
      this(var1, var2, var2);
   }

   public MixinHelper2_2(Bridge2_24 var1, List<MixinHelper_16> var2, List<MixinHelper_16> var3) {
      this.field1 = var2;
      this.field2 = var3;
      this.field3 = var1;
   }

   public void method1(com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3) {
      if (!this.method3(var1, var2, var3)) {
         throw new IllegalStateException("Failed to coerce param type %s to %s. Functors: %s".formatted(var1, var2, this.field1));
      }
   }

   public void method2(com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3) {
      if (!this.method4(var1, var2, var3)) {
         throw new IllegalStateException("Failed to coerce return type %s to %s. Functors: %s".formatted(var1, var2, this.field2));
      }
   }

   public boolean method3(com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3) {
      if (var1.equals(var2)) {
         return true;
      }

      InsnList var4 = new InsnList();
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var5 = var1;

      for (MixinHelper_16 var7 : this.field1) {
         var5 = var7.method1(var5, var2, var4);
      }

      this.field3.method5(var5);
      if (!var5.equals(var2)) {
         return false;
      }

      var3.add(var4);
      return true;
   }

   public boolean method4(com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3) {
      if (var1.equals(var2)) {
         return true;
      }

      InsnList var4 = new InsnList();
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var5 = var2;

      for (MixinHelper_16 var7 : this.field2) {
         InsnList var8 = new InsnList();
         var5 = var7.method2(var1, var5, var8);
         var4.insert(var8);
      }

      this.field3.method5(var5);
      if (!var1.equals(var5)) {
         return false;
      }

      var3.add(var4);
      return true;
   }
}
