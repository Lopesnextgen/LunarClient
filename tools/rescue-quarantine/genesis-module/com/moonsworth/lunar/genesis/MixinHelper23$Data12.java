package com.moonsworth.lunar.genesis;

import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;

final class MixinHelper23$Data12 extends MixinHelper23$Data16 {
   private transient @Nullable ImmutableSet<TypeToken<? super T>> field4;
   private static final long field5 = 0L;

   private MixinHelper23$Data12(TypeToken var1) {
      super(var1);
      this.field6 = var1;
   }

   @Override
   protected Set<TypeToken<? super T>> delegate() {
      ImmutableSet var1 = this.field4;
      if (var1 == null) {
         ImmutableList var2 = MixinHelper23$Data17.field1.method1().method2(this.field6);
         return this.field4 = (ImmutableSet<TypeToken<? super T>>)FluentIterable.method1(var2)
            .method17(MixinHelper23$Type2.IGNORE_TYPE_VARIABLE_OR_WILDCARD)
            .method31();
      } else {
         return var1;
      }
   }

   @Override
   public MixinHelper23$Data16 method2() {
      return this;
   }

   @Override
   public Set<Class<? super T>> rawTypes() {
      ImmutableList var1 = MixinHelper23$Data17.field2.method1().method3(TypeToken.method40(this.field6));
      return ImmutableSet.method10(var1);
   }

   @Override
   public MixinHelper23$Data16 method1() {
      throw new UnsupportedOperationException("classes().interfaces() not supported.");
   }

   private Object readResolve() {
      return this.field6.method13().method2();
   }
}
