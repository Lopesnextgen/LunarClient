package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.TypeToken;
import com.google.common.collect.ForwardingMap;
import com.google.common.reflect.TypeToInstanceMap;

@Annotation2
public final class MixinHelper312_4<B> extends ForwardingMap<TypeToken<? extends B>, B> implements TypeToInstanceMap<B> {
   private final ImmutableMap<TypeToken<? extends B>, B> field1;

   public static <B> MixinHelper312_4<B> method1() {
      return new MixinHelper312_4<>(ImmutableMap.method1());
   }

   public static <B> MixinHelper312$Data6<B> method2() {
      return new MixinHelper312$Data6<>();
   }

   private MixinHelper312_4(ImmutableMap<TypeToken<? extends B>, B> var1) {
      this.field1 = var1;
   }

   @Override
   public <T extends B> T method1(TypeToken<T> var1) {
      return this.method6(var1.method27());
   }

   @Override
   public <T extends B> T getInstance(Class<T> var1) {
      return this.method6(TypeToken.method1(var1));
   }

   @Deprecated
   @CanIgnoreReturnValue
   @Override
   public <T extends B> T method2(TypeToken<T> var1, T var2) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   @CanIgnoreReturnValue
   @Override
   public <T extends B> T putInstance(Class<T> var1, T var2) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   @CanIgnoreReturnValue
   public B method5(TypeToken<? extends B> var1, B var2) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   @Override
   public void putAll(Map<? extends TypeToken<? extends B>, ? extends B> var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   protected Map<TypeToken<? extends B>, B> delegate() {
      return this.field1;
   }

   private <T extends B> T method6(TypeToken<T> var1) {
      return (T)this.field1.get(var1);
   }
}
