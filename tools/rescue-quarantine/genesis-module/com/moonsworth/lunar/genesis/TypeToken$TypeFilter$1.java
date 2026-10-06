package com.moonsworth.lunar.genesis;

import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

enum TypeToken$TypeFilter$1 {
   ;
   TypeToken$TypeFilter$1() {
   }

   public boolean apply(MixinHelper23_2<?> mixinhelper23_21) {
      return !(MixinHelper23_2.method41(mixinhelper23_21) instanceof TypeVariable) && !(MixinHelper23_2.method41(mixinhelper23_21) instanceof WildcardType);
   }
}
