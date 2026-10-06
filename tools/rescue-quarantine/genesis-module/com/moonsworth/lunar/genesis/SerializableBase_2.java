package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.DoNotMock;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@DoNotMock("Use Optional.of(value) or Optional.absent()")
@GwtCompatible(serializable = true)
public abstract class SerializableBase_2<T> implements Serializable {
   private static final long field1 = 0L;

   public static <T> SerializableBase_2<T> method1() {
      return SerializableBase3.method2();
   }

   public static <T> SerializableBase_2<T> method2(T var0) {
      return new SerializableBase2_2<>(Preconditions.checkNotNull((T)var0));
   }

   public static <T> SerializableBase_2<T> method3(@Nullable T var0) {
      return var0 == null ? method1() : new SerializableBase2_2<>((T)var0);
   }

   public static <T> @Nullable SerializableBase_2<T> method4(@Nullable Optional<T> var0) {
      return var0 == null ? null : method3((T)var0.orElse(null));
   }

   public static <T> @Nullable Optional<T> method5(@Nullable SerializableBase_2<T> var0) {
      return var0 == null ? null : var0.toJavaUtil();
   }

   public Optional<T> toJavaUtil() {
      return Optional.ofNullable(this.orNull());
   }

   SerializableBase_2() {
   }

   public abstract boolean isPresent();

   public abstract T get();

   public abstract T or(T var1);

   public abstract SerializableBase_2<T> method6(SerializableBase_2<? extends T> var1);

   @Annotation2
   public abstract T method7(SupplierExtension<? extends T> var1);

   public abstract @Nullable T orNull();

   public abstract Set<T> asSet();

   public abstract <V> SerializableBase_2<V> method8(MixinHelper24_2<? super T, V> var1);

   @Override
   public abstract boolean equals(@Nullable Object var1);

   @Override
   public abstract int hashCode();

   @Override
   public abstract String toString();

   @Annotation2
   public static <T> Iterable<T> presentInstances(final Iterable<? extends SerializableBase_2<? extends T>> var0) {
      Preconditions.checkNotNull(var0);
      return new Iterable<T>() {
         @Override
         public Iterator<T> iterator() {
            return new MixinHelperIterator<T>() {
               private final Iterator<? extends SerializableBase_2<? extends T>> field2 = Preconditions.checkNotNull(var0.iterator());

               @Override
               protected T computeNext() {
                  while (this.field2.hasNext()) {
                     SerializableBase_2 var1 = this.field2.next();
                     if (var1.isPresent()) {
                        return (T)var1.get();
                     }
                  }

                  return (T)this.HORRCIIHHHHHRIROCHHCIRHORRHRHR();
               }
            };
         }
      };
   }
}
