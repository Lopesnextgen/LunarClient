package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import java.util.Set;
import org.cadixdev.bombe.provider.ClassProvider;
import org.objectweb.asm.tree.InsnList;

public class SupertypeCoercion implements MixinHelper_16 {
   private final ClassProvider field1;

   public SupertypeCoercion(ClassProvider var1) {
      this.field1 = var1;
   }

   @Override
   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method1(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3
   ) {
      if (var1.equals(var2)) {
         return var2;
      } else {
         com.moonsworth.lunar.ichor.mixin.MixinHelper var4 = var1.method2();
         com.moonsworth.lunar.ichor.mixin.MixinHelper var5 = var2.method2();
         if (var4 instanceof com.moonsworth.lunar.ichor.mixin.MixinHelper7 var6 && var5 instanceof com.moonsworth.lunar.ichor.mixin.MixinHelper7 var7) {
            String var8 = var6.name();
            Set var9 = FatalIchorError6.method9(var8, this.field1);
            return var9.contains(var7.name()) ? var2 : var1;
         } else {
            return var1;
         }
      }
   }

   @Override
   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method2(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var2, InsnList var3
   ) {
      if (var1.equals(var2)) {
         return var1;
      } else {
         com.moonsworth.lunar.ichor.mixin.MixinHelper var4 = var1.method2();
         com.moonsworth.lunar.ichor.mixin.MixinHelper var5 = var2.method2();
         if (var4 instanceof com.moonsworth.lunar.ichor.mixin.MixinHelper7 var6 && var5 instanceof com.moonsworth.lunar.ichor.mixin.MixinHelper7 var7) {
            String var8 = var6.name();
            Set var9 = FatalIchorError6.method9(var8, this.field1);
            return var9.contains(var7.name()) ? var1 : var2;
         } else {
            return var2;
         }
      }
   }

   public ClassProvider method3() {
      return this.field1;
   }
}
