package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

@GwtCompatible
final class SerializableBase2_2<T> extends SerializableBase_2<T> {
   private final T field2;
   private static final long field3 = 0L;

   SerializableBase2_2(T var1) {
      this.field2 = (T)var1;
   }

   @Override
   public boolean isPresent() {
      return true;
   }

   @Override
   public T get() {
      return this.field2;
   }

   @Override
   public T or(T var1) {
      Preconditions.checkNotNull(var1, "use Optional.orNull() instead of Optional.or(null)");
      return this.field2;
   }

   @Override
   public SerializableBase_2<T> method6(SerializableBase_2<? extends T> var1) {
      Preconditions.checkNotNull(var1);
      return this;
   }

   @Override
   public T method7(SupplierExtension<? extends T> var1) {
      Preconditions.checkNotNull(var1);
      return this.field2;
   }

   @Override
   public T orNull() {
      return this.field2;
   }

   @Override
   public Set<T> asSet() {
      return Collections.singleton(this.field2);
   }

   @Override
   public <V> SerializableBase_2<V> method8(MixinHelper24_2<? super T, V> var1) {
      return (SerializableBase_2<V>)(new SerializableBase2_2<>(
         Preconditions.checkNotNull(var1.apply(this.field2), "the Function passed to Optional.transform() must not return null.")
      ));
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof SerializableBase2_2) {
         SerializableBase2_2 var2 = (SerializableBase2_2)var1;
         return this.field2.equals(var2.field2);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return 1502476572 + this.field2.hashCode();
   }

   @Override
   public String toString() {
      return "Optional.of(" + this.field2 + ")";
   }
}
