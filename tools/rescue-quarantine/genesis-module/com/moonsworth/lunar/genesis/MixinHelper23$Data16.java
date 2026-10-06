package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;

public class MixinHelper23$Data16 extends MixinHelper3165<TypeToken<? super T>> implements Serializable {
   private transient @Nullable ImmutableSet<TypeToken<? super T>> field1;
   private static final long field2 = 0L;

   MixinHelper23$Data16(TypeToken var1) {
      this.field3 = var1;
   }

   public MixinHelper23$Data16 method1() {
      return new MixinHelper23$Data14(this.field3, this);
   }

   public MixinHelper23$Data16 method2() {
      return new MixinHelper23$Data12(this.field3);
   }

   @Override
   protected Set<TypeToken<? super T>> delegate() {
      ImmutableSet var1 = this.field1;
      if (var1 == null) {
         ImmutableList var2 = MixinHelper23$Data17.field1.method2(this.field3);
         return this.field1 = (ImmutableSet<TypeToken<? super T>>)FluentIterable.method1(var2)
            .method17(MixinHelper23$Type2.IGNORE_TYPE_VARIABLE_OR_WILDCARD)
            .method31();
      } else {
         return var1;
      }
   }

   public Set<Class<? super T>> rawTypes() {
      ImmutableList var1 = MixinHelper23$Data17.field2.method3(TypeToken.method40(this.field3));
      return ImmutableSet.method10(var1);
   }
}
