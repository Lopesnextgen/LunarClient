package com.moonsworth.lunar.genesis;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;

final class MixinHelper5$Data25 extends MixinHelper7_2 {
   private final Map<MixinHelper5$Data26, Type> field2 = Maps.newHashMap();

   private MixinHelper5$Data25() {
   }

   static ImmutableMap<MixinHelper5$Data26, Type> method1(Type var0) {
      Preconditions.checkNotNull(var0);
      MixinHelper5$Data25 var1 = new MixinHelper5$Data25();
      var1.method1(new Type[]{var0});
      return ImmutableMap.method9(var1.field2);
   }

   @Override
   void visitClass(Class<?> var1) {
      this.method1(new Type[]{var1.getGenericSuperclass()});
      this.method1(var1.getGenericInterfaces());
   }

   @Override
   void visitParameterizedType(ParameterizedType var1) {
      Class var2 = (Class)var1.getRawType();
      TypeVariable[] var3 = var2.getTypeParameters();
      Type[] var4 = var1.getActualTypeArguments();
      Preconditions.checkState(var3.length == var4.length);

      for (int var5 = 0; var5 < var3.length; var5++) {
         this.method2(new MixinHelper5$Data26(var3[var5]), var4[var5]);
      }

      this.method1(new Type[]{var2});
      this.method1(new Type[]{var1.getOwnerType()});
   }

   @Override
   void visitTypeVariable(TypeVariable<?> var1) {
      this.method1(var1.getBounds());
   }

   @Override
   void visitWildcardType(WildcardType var1) {
      this.method1(var1.getUpperBounds());
   }

   private void method2(MixinHelper5$Data26 var1, Type var2) {
      if (!this.field2.containsKey(var1)) {
         for (Type var3 = var2; var3 != null; var3 = this.field2.get(MixinHelper5$Data26.method1(var3))) {
            if (var1.equalsType(var3)) {
               Type var4 = var2;

               while (var4 != null) {
                  var4 = this.field2.remove(MixinHelper5$Data26.method1(var4));
               }

               return;
            }
         }

         this.field2.put(var1, var2);
      }
   }
}
