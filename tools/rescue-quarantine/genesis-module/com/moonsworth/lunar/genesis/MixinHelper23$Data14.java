package com.moonsworth.lunar.genesis;

import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;

final class MixinHelper23$Data14 extends MixinHelper23$Data16 {
   private final transient MixinHelper23$Data16 field4;
   private transient @Nullable ImmutableSet<TypeToken<? super T>> field5;
   private static final long field6 = 0L;

   MixinHelper23$Data14(TypeToken var1, MixinHelper23$Data16 var2) {
      super(var1);
      this.field7 = var1;
      this.field4 = var2;
   }

   @Override
   protected Set<TypeToken<? super T>> delegate() {
      ImmutableSet var1 = this.field5;
      return var1 == null
         ? (
            this.field5 = (ImmutableSet<TypeToken<? super T>>)FluentIterable.method1(this.field4)
               .method17(MixinHelper23$Type2.INTERFACE_ONLY)
               .method31()
         )
         : var1;
   }

   @Override
   public MixinHelper23$Data16 method1() {
      return this;
   }

   @Override
   public Set<Class<? super T>> rawTypes() {
      ImmutableList var1 = MixinHelper23$Data17.field2.method3(TypeToken.method40(this.field7));
      return (Set<Class<? super T>>)FluentIterable.method1(var1).method17(new PredicateExtension<Class<?>>() {
         public boolean apply(Class<?> var1) {
            return var1.isInterface();
         }
      }).method31();
   }

   @Override
   public MixinHelper23$Data16 method2() {
      throw new UnsupportedOperationException("interfaces().classes() not supported.");
   }

   private Object readResolve() {
      return this.field7.method13().method1();
   }
}
