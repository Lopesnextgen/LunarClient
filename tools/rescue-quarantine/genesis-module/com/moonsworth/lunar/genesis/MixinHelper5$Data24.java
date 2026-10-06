package com.moonsworth.lunar.genesis;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import com.google.common.collect.ImmutableMap;
import com.google.common.base.Preconditions;

class MixinHelper5$Data24 {
   private final ImmutableMap<MixinHelper5$Data26, Type> field1;

   MixinHelper5$Data24() {
      this.field1 = ImmutableMap.method1();
   }

   private MixinHelper5$Data24(ImmutableMap<MixinHelper5$Data26, Type> var1) {
      this.field1 = var1;
   }

   final MixinHelper5$Data24 method1(Map<MixinHelper5$Data26, ? extends Type> var1) {
      ImmutableMap.Data2 var2 = ImmutableMap.method7();
      var2.method3(this.field1);

      for (Entry var4 : var1.entrySet()) {
         MixinHelper5$Data26 var5 = (MixinHelper5$Data26)var4.getKey();
         Type var6 = (Type)var4.getValue();
         Preconditions.checkArgument(!var5.equalsType(var6), "Type variable %s bound to itself", var5);
         var2.method1(var5, var6);
      }

      return new MixinHelper5$Data24(var2.method7());
   }

   final Type method2(final TypeVariable<?> var1) {
      final MixinHelper5$Data24 var2 = this;
      MixinHelper5$Data24 var3 = new MixinHelper5$Data24() {
         @Override
         public Type method3(TypeVariable<?> var1x, MixinHelper5$Data24 var2x) {
            return var1x.getGenericDeclaration().equals(var1.getGenericDeclaration()) ? var1x : var2.method3(var1x, var2x);
         }
      };
      return this.method3(var1, var3);
   }

   Type method3(TypeVariable<?> var1, MixinHelper5$Data24 var2) {
      Type var3 = this.field1.get(new MixinHelper5$Data26(var1));
      if (var3 == null) {
         Type[] var4 = var1.getBounds();
         if (var4.length == 0) {
            return var1;
         }

         Type[] var5 = MixinHelper5_10.method5(new MixinHelper5_10(var2), var4);
         return MixinHelper3$Data41.field1 && Arrays.equals(var4, var5)
            ? var1
            : MixinHelper3_2.newArtificialTypeVariable(var1.getGenericDeclaration(), var1.getName(), var5);
      } else {
         return new MixinHelper5_10(var2).resolveType(var3);
      }
   }
}
