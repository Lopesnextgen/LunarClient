package com.moonsworth.lunar.genesis;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import com.google.common.collect.ImmutableList;
import com.google.common.base.Preconditions;

final class MixinHelper3$Data42<D extends GenericDeclaration> {
   private final D field1;
   private final String field2;
   private final ImmutableList<Type> field3;

   MixinHelper3$Data42(D var1, String var2, Type[] var3) {
      MixinHelper3_2.access$200(var3, "bound for type variable");
      this.field1 = Preconditions.checkNotNull((D)var1);
      this.field2 = Preconditions.checkNotNull(var2);
      this.field3 = ImmutableList.method17(var3);
   }

   public Type[] getBounds() {
      return MixinHelper3_2.access$300(this.field3);
   }

   public D getGenericDeclaration() {
      return this.field1;
   }

   public String getName() {
      return this.field2;
   }

   public String getTypeName() {
      return this.field2;
   }

   @Override
   public String toString() {
      return this.field2;
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode() ^ this.field2.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (MixinHelper3$Data41.field1) {
         if (var1 != null && Proxy.isProxyClass(var1.getClass()) && Proxy.getInvocationHandler(var1) instanceof MixinHelper3$Data43) {
            MixinHelper3$Data43 var4 = (MixinHelper3$Data43)Proxy.getInvocationHandler(var1);
            MixinHelper3$Data42 var3 = MixinHelper3$Data43.method1(var4);
            return this.field2.equals(var3.getName()) && this.field1.equals(var3.getGenericDeclaration()) && this.field3.equals(var3.field3);
         } else {
            return false;
         }
      } else {
         if (!(var1 instanceof TypeVariable)) {
            return false;
         }

         TypeVariable var2 = (TypeVariable)var1;
         return this.field2.equals(var2.getName()) && this.field1.equals(var2.getGenericDeclaration());
      }
   }
}
