package com.moonsworth.lunar.genesis;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

final class TypeResolver$TypeVariableKey {
   private final TypeVariable<?> field1;

   TypeResolver$TypeVariableKey(TypeVariable<?> typevariable1) {
      this.field1 = (TypeVariable<?>)Preconditions.checkNotNull(typevariable1);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(new Object[]{this.field1.getGenericDeclaration(), this.field1.getName()});
   }

   @Override
   public boolean equals(Object obj1) {
      if (obj1 instanceof TypeResolver$TypeVariableKey) {
         TypeResolver$TypeVariableKey mixinhelper5$data262 = (TypeResolver$TypeVariableKey)obj1;
         return this.equalsTypeVariable(mixinhelper5$data262.field1);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.field1.toString();
   }

   static TypeResolver$TypeVariableKey method1(Type type0) {
      return type0 instanceof TypeVariable ? new TypeResolver$TypeVariableKey((TypeVariable<?>)type0) : null;
   }

   boolean equalsType(Type type1) {
      return type1 instanceof TypeVariable ? this.equalsTypeVariable((TypeVariable<?>)type1) : false;
   }

   private boolean equalsTypeVariable(TypeVariable<?> typevariable1) {
      return this.field1.getGenericDeclaration().equals(typevariable1.getGenericDeclaration()) && this.field1.getName().equals(typevariable1.getName());
   }
}
