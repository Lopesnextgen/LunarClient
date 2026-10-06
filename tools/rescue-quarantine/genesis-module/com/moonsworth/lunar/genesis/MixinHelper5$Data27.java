package com.moonsworth.lunar.genesis;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class MixinHelper5$Data27 {
   static final MixinHelper5$Data27 field1 = new MixinHelper5$Data27();
   private final AtomicInteger field2;

   private MixinHelper5$Data27() {
      this(new AtomicInteger());
   }

   private MixinHelper5$Data27(AtomicInteger var1) {
      this.field2 = var1;
   }

   final Type method1(Type var1) {
      Preconditions.checkNotNull(var1);
      if (var1 instanceof Class) {
         return var1;
      }

      if (var1 instanceof TypeVariable) {
         return var1;
      }

      if (var1 instanceof GenericArrayType) {
         GenericArrayType var8 = (GenericArrayType)var1;
         return MixinHelper3_2.newArrayType(this.method3().method1(var8.getGenericComponentType()));
      }

      if (!(var1 instanceof ParameterizedType)) {
         if (var1 instanceof WildcardType) {
            WildcardType var7 = (WildcardType)var1;
            Type[] var9 = var7.getLowerBounds();
            return var9.length == 0 ? this.captureAsTypeVariable(var7.getUpperBounds()) : var1;
         } else {
            throw new AssertionError("must have been one of the known types");
         }
      } else {
         ParameterizedType var2 = (ParameterizedType)var1;
         Class var3 = (Class)var2.getRawType();
         TypeVariable[] var4 = var3.getTypeParameters();
         Type[] var5 = var2.getActualTypeArguments();

         for (int var6 = 0; var6 < var5.length; var6++) {
            var5[var6] = this.method2(var4[var6]).method1(var5[var6]);
         }

         return MixinHelper3_2.newParameterizedTypeWithOwner(this.method3().captureNullable(var2.getOwnerType()), var3, var5);
      }
   }

   TypeVariable<?> captureAsTypeVariable(Type[] var1) {
      String var2 = "capture#" + this.field2.incrementAndGet() + "-of ? extends " + MixinHelper16_2.method2('&').method11(var1);
      return MixinHelper3_2.newArtificialTypeVariable(MixinHelper5$Data27.class, var2, var1);
   }

   private MixinHelper5$Data27 method2(final TypeVariable<?> var1) {
      return new MixinHelper5$Data27(this.field2) {
         @Override
         TypeVariable<?> captureAsTypeVariable(Type[] var1x) {
            LinkedHashSet var2 = new LinkedHashSet<>(Arrays.asList(var1x));
            var2.addAll(Arrays.asList(var1.getBounds()));
            if (var2.size() > 1) {
               var2.remove(Object.class);
            }

            return super.captureAsTypeVariable(var2.toArray(new Type[0]));
         }
      };
   }

   private MixinHelper5$Data27 method3() {
      return new MixinHelper5$Data27(this.field2);
   }

   private Type captureNullable(@Nullable Type var1) {
      return var1 == null ? null : this.method1(var1);
   }
}
