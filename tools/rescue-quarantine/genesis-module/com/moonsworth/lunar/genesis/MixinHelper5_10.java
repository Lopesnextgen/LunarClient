package com.moonsworth.lunar.genesis;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Map;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

@Annotation2
public final class MixinHelper5_10 {
   private final MixinHelper5$Data24 field1;

   public MixinHelper5_10() {
      this.field1 = new MixinHelper5$Data24();
   }

   private MixinHelper5_10(MixinHelper5$Data24 var1) {
      this.field1 = var1;
   }

   static MixinHelper5_10 method1(Type var0) {
      return new MixinHelper5_10().method4(MixinHelper5$Data25.method1(var0));
   }

   static MixinHelper5_10 method2(Type var0) {
      Type var1 = MixinHelper5$Data27.field1.method1(var0);
      return new MixinHelper5_10().method4(MixinHelper5$Data25.method1(var1));
   }

   public MixinHelper5_10 method3(Type var1, Type var2) {
      HashMap var3 = Maps.newHashMap();
      populateTypeMappings(var3, Preconditions.checkNotNull(var1), Preconditions.checkNotNull(var2));
      return this.method4(var3);
   }

   MixinHelper5_10 method4(Map<MixinHelper5$Data26, ? extends Type> var1) {
      return new MixinHelper5_10(this.field1.method1(var1));
   }

   private static void populateTypeMappings(final Map<MixinHelper5$Data26, Type> var0, Type var1, final Type var2) {
      if (!var1.equals(var2)) {
         (new MixinHelper7_2() {
            @Override
            void visitTypeVariable(TypeVariable<?> var1) {
               var0.put(new MixinHelper5$Data26(var1), var2);
            }

            @Override
            void visitWildcardType(WildcardType var1) {
               if (var2 instanceof WildcardType) {
                  WildcardType var2x = (WildcardType)var2;
                  Type[] var3 = var1.getUpperBounds();
                  Type[] var4 = var2x.getUpperBounds();
                  Type[] var5 = var1.getLowerBounds();
                  Type[] var6 = var2x.getLowerBounds();
                  Preconditions.checkArgument(var3.length == var4.length && var5.length == var6.length, "Incompatible type: %s vs. %s", var1, var2);

                  for (int var7 = 0; var7 < var3.length; var7++) {
                     MixinHelper5_10.populateTypeMappings(var0, var3[var7], var4[var7]);
                  }

                  for (int var8 = 0; var8 < var5.length; var8++) {
                     MixinHelper5_10.populateTypeMappings(var0, var5[var8], var6[var8]);
                  }
               }
            }

            @Override
            void visitParameterizedType(ParameterizedType var1) {
               if (!(var2 instanceof WildcardType)) {
                  ParameterizedType var2x = MixinHelper5_10.expectArgument(ParameterizedType.class, var2);
                  if (var1.getOwnerType() != null && var2x.getOwnerType() != null) {
                     MixinHelper5_10.populateTypeMappings(var0, var1.getOwnerType(), var2x.getOwnerType());
                  }

                  Preconditions.checkArgument(var1.getRawType().equals(var2x.getRawType()), "Inconsistent raw type: %s vs. %s", var1, var2);
                  Type[] var3 = var1.getActualTypeArguments();
                  Type[] var4 = var2x.getActualTypeArguments();
                  Preconditions.checkArgument(var3.length == var4.length, "%s not compatible with %s", var1, var2x);

                  for (int var5 = 0; var5 < var3.length; var5++) {
                     MixinHelper5_10.populateTypeMappings(var0, var3[var5], var4[var5]);
                  }
               }
            }

            @Override
            void visitGenericArrayType(GenericArrayType var1) {
               if (!(var2 instanceof WildcardType)) {
                  Type var2x = MixinHelper3_2.getComponentType(var2);
                  Preconditions.checkArgument(var2x != null, "%s is not an array type.", var2);
                  MixinHelper5_10.populateTypeMappings(var0, var1.getGenericComponentType(), var2x);
               }
            }

            @Override
            void visitClass(Class<?> var1) {
               if (!(var2 instanceof WildcardType)) {
                  throw new IllegalArgumentException("No type mapping from " + var1 + " to " + var2);
               }
            }
         }).method1(new Type[]{var1});
      }
   }

   public Type resolveType(Type var1) {
      Preconditions.checkNotNull(var1);
      if (var1 instanceof TypeVariable) {
         return this.field1.method2((TypeVariable<?>)var1);
      } else if (var1 instanceof ParameterizedType) {
         return this.resolveParameterizedType((ParameterizedType)var1);
      } else if (var1 instanceof GenericArrayType) {
         return this.resolveGenericArrayType((GenericArrayType)var1);
      } else {
         return var1 instanceof WildcardType ? this.resolveWildcardType((WildcardType)var1) : var1;
      }
   }

   Type[] resolveTypesInPlace(Type[] var1) {
      for (int var2 = 0; var2 < var1.length; var2++) {
         var1[var2] = this.resolveType(var1[var2]);
      }

      return var1;
   }

   private Type[] resolveTypes(Type[] var1) {
      Type[] var2 = new Type[var1.length];

      for (int var3 = 0; var3 < var1.length; var3++) {
         var2[var3] = this.resolveType(var1[var3]);
      }

      return var2;
   }

   private WildcardType resolveWildcardType(WildcardType var1) {
      Type[] var2 = var1.getLowerBounds();
      Type[] var3 = var1.getUpperBounds();
      return new MixinHelper3$Data44(this.resolveTypes(var2), this.resolveTypes(var3));
   }

   private Type resolveGenericArrayType(GenericArrayType var1) {
      Type var2 = var1.getGenericComponentType();
      Type var3 = this.resolveType(var2);
      return MixinHelper3_2.newArrayType(var3);
   }

   private ParameterizedType resolveParameterizedType(ParameterizedType var1) {
      Type var2 = var1.getOwnerType();
      Type var3 = var2 == null ? null : this.resolveType(var2);
      Type var4 = this.resolveType(var1.getRawType());
      Type[] var5 = var1.getActualTypeArguments();
      Type[] var6 = this.resolveTypes(var5);
      return MixinHelper3_2.newParameterizedTypeWithOwner(var3, (Class<?>)var4, var6);
   }

   private static <T> T expectArgument(Class<T> var0, Object var1) {
      try {
         return (T)var0.cast(var1);
      } catch (ClassCastException var3) {
         throw new IllegalArgumentException(var1 + " is not a " + var0.getSimpleName());
      }
   }
}
