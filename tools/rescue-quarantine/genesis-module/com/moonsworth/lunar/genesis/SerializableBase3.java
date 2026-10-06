package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;

@GwtCompatible
final class SerializableBase3<T> extends SerializableBase_2<T> {
   static final SerializableBase3<Object> field2 = new SerializableBase3<>();
   private static final long field3 = 0L;

   static <T> SerializableBase_2<T> method2() {
      return (SerializableBase_2<T>)field2;
   }

   private SerializableBase3() {
   }

   @Override
   public boolean isPresent() {
      return false;
   }

   @Override
   public T get() {
      throw new IllegalStateException("Optional.get() cannot be called on an absent value");
   }

   @Override
   public T or(T var1) {
      return Preconditions.checkNotNull((T)var1, "use Optional.orNull() instead of Optional.or(null)");
   }

   @Override
   public SerializableBase_2<T> method6(SerializableBase_2<? extends T> var1) {
      return Preconditions.checkNotNull(var1);
   }

   @Override
   public T method7(SupplierExtension<? extends T> var1) {
      return Preconditions.checkNotNull((T)var1.get(), "use Optional.orNull() instead of a Supplier that returns null");
   }

   @Override
   public @Nullable T orNull() {
      return null;
   }

   @Override
   public Set<T> asSet() {
      return Collections.emptySet();
   }

   @Override
   public <V> SerializableBase_2<V> method8(MixinHelper24_2<? super T, V> var1) {
      Preconditions.checkNotNull(var1);
      return SerializableBase_2.method1();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      return var1 == this;
   }

   @Override
   public int hashCode() {
      return 2040732332;
   }

   @Override
   public String toString() {
      return "Optional.absent()";
   }

   private Object readResolve() {
      return field2;
   }
}
