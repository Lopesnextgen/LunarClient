package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.config.Config;
import java.util.Optional;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.Mapping;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.TypeInsnNode;

public class MappedTypeCoercion implements MixinHelper_16 {
   private final Bridge2_24 field1;
   private final Config field2;

   public MappedTypeCoercion(Bridge2_24 var1, Config var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Override
   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method1(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3
   ) {
      com.moonsworth.lunar.ichor.mixin.MixinHelper var4 = var1.method2();
      com.moonsworth.lunar.ichor.mixin.MixinHelper var5 = var2.method2();
      if (var4 instanceof com.moonsworth.lunar.ichor.mixin.MixinHelper7 && var5 instanceof com.moonsworth.lunar.ichor.mixin.MixinHelper7) {
         String var6 = ((com.moonsworth.lunar.ichor.mixin.MixinHelper7)var4).name();
         Optional var7 = this.field1.method4(var6, this.field2);
         if (var7.isPresent()) {
            String var8 = ((Bridge2_35)var7.get()).method1(this.field2);
            if (var8 != null) {
               MappingSet var9 = this.field1.getMappings();
               if (var9 != null) {
                  var8 = var9.getClassMapping(var8).<String>map(Mapping::getFullDeobfuscatedName).orElse(var8);
               }

               var3.add(new TypeInsnNode(192, var8));
               return new com.moonsworth.lunar.ichor.mixin.MixinHelper3(new com.moonsworth.lunar.ichor.mixin.MixinHelper7(var8));
            }
         }

         return var1;
      } else {
         return var1;
      }
   }

   @Override
   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method2(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3
   ) {
      com.moonsworth.lunar.ichor.mixin.MixinHelper var4 = var1.method2();
      com.moonsworth.lunar.ichor.mixin.MixinHelper var5 = var2.method2();
      if (var4 instanceof com.moonsworth.lunar.ichor.mixin.MixinHelper7 && var5 instanceof com.moonsworth.lunar.ichor.mixin.MixinHelper7) {
         String var6 = ((com.moonsworth.lunar.ichor.mixin.MixinHelper7)var5).name();
         Optional var7 = this.field1.method4(var6, this.field2);
         if (var7.isPresent()) {
            String var8 = ((Bridge2_35)var7.get()).method1(this.field2);
            if (var8 != null) {
               MappingSet var9 = this.field1.getMappings();
               if (var9 != null) {
                  var8 = var9.getClassMapping(var8).<String>map(Mapping::getFullDeobfuscatedName).orElse(var8);
               }

               var3.add(new TypeInsnNode(192, var6));
               return new com.moonsworth.lunar.ichor.mixin.MixinHelper3(new com.moonsworth.lunar.ichor.mixin.MixinHelper7(var8));
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   public Bridge2_24 method3() {
      return this.field1;
   }

   public Config method4() {
      return this.field2;
   }
}
