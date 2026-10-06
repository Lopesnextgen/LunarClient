package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

@GwtCompatible
final class Present<T> extends Optional<T> {
   private final T field2;
   private static final long field3 = 0L;

   Present(T value1) {
      this.field2 = (T)value1;
   }

   public boolean isPresent() {
      return true;
   }

   public T get() {
      return this.field2;
   }

   public T or(T value1) {
      Preconditions.checkNotNull(value1, "use Optional.orNull() instead of Optional.or(null)");
      return this.field2;
   }

   public Optional<T> method6(Optional<? extends T> serializablebase_21) {
      Preconditions.checkNotNull(serializablebase_21);
      return this;
   }

   public T method7(Supplier<? extends T> supplierextension1) {
      Preconditions.checkNotNull(supplierextension1);
      return this.field2;
   }

   public T orNull() {
      return this.field2;
   }

   public Set<T> asSet() {
      return Collections.singleton(this.field2);
   }

   public <V> Optional<V> method8(Function<? super T, V> mixinhelper24_21) {
      return (Optional<V>)(new Present<>(
         Preconditions.checkNotNull(mixinhelper24_21.apply(this.field2), "the Function passed to Optional.transform() must not return null.")
      ));
   }

   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Present) {
         Present serializablebase2_22 = (Present)obj1;
         return this.field2.equals(serializablebase2_22.field2);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return 1502476572 + this.field2.hashCode();
   }

   public String toString() {
      return "Optional.of(" + this.field2 + ")";
   }
}
