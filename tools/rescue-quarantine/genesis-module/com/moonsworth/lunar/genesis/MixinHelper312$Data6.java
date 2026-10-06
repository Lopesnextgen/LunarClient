package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.TypeToken;

@Annotation2
public final class MixinHelper312$Data6<B> {
   private final ImmutableMap.Data2<TypeToken<? extends B>, B> field1 = ImmutableMap.method7();

   private MixinHelper312$Data6() {
   }

   @CanIgnoreReturnValue
   public <T extends B> MixinHelper312$Data6<B> method1(Class<T> var1, T var2) {
      this.field1.method1(TypeToken.method1(var1), (B)var2);
      return this;
   }

   @CanIgnoreReturnValue
   public <T extends B> MixinHelper312$Data6<B> method2(TypeToken<T> var1, T var2) {
      this.field1.method1(var1.method27(), (B)var2);
      return this;
   }

   public MixinHelper312_4<B> method3() {
      return new MixinHelper312_4<>(this.field1.method7());
   }
}
