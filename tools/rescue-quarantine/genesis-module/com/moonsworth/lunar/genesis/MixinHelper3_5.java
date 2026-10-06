package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.Spliterator.OfDouble;
import java.util.Spliterator.OfInt;
import java.util.Spliterator.OfLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
final class MixinHelper3_5 {
   private MixinHelper3_5() {
   }

   static <T> Spliterator<T> indexed(int var0, int var1, IntFunction<T> var2) {
      return indexed(var0, var1, var2, null);
   }

   static <T> Spliterator<T> indexed(int var0, final int var1, final IntFunction<T> var2, final java.util.Comparator<? super T> var3) {
      if (var3 != null) {
         Preconditions.checkArgument((var1 & 4) != 0);
      }

      class Data29 implements Spliterator<T> {
         private final OfInt field1;

         Data29(OfInt var1x) {
            this.field1 = var1x;
         }

         @Override
         public boolean tryAdvance(Consumer<? super T> var1x) {
            return this.field1.tryAdvance(var2xx -> var1x.accept(var2.apply(var2xx)));
         }

         @Override
         public void forEachRemaining(Consumer<? super T> var1x) {
            this.field1.forEachRemaining(var2xx -> var1x.accept(var2.apply(var2xx)));
         }

         @Override
         public @Nullable Spliterator<T> trySplit() {
            OfInt var1x = this.field1.trySplit();
            return var1x == null ? null : new Data29(var1x);
         }

         @Override
         public long estimateSize() {
            return this.field1.estimateSize();
         }

         @Override
         public int characteristics() {
            return 16464 | var1;
         }

         @Override
         public java.util.Comparator<? super T> getComparator() {
            if (this.hasCharacteristics(4)) {
               return var3;
            } else {
               throw new IllegalStateException();
            }
         }
      }

      return new Data29(IntStream.range(0, var0).spliterator());
   }

   static <InElementT, OutElementT> Spliterator<OutElementT> map(
      final Spliterator<InElementT> var0, final Function<? super InElementT, ? extends OutElementT> var1
   ) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      return new Spliterator<OutElementT>() {
         @Override
         public boolean tryAdvance(Consumer<? super OutElementT> var1x) {
            return var0.tryAdvance(var2 -> var1x.accept(var1.apply(var2)));
         }

         @Override
         public void forEachRemaining(Consumer<? super OutElementT> var1x) {
            var0.forEachRemaining(var2 -> var1x.accept(var1.apply(var2)));
         }

         @Override
         public Spliterator<OutElementT> trySplit() {
            Spliterator var1x = var0.trySplit();
            return var1x != null ? MixinHelper3_5.map(var1x, var1) : null;
         }

         @Override
         public long estimateSize() {
            return var0.estimateSize();
         }

         @Override
         public int characteristics() {
            return var0.characteristics() & -262;
         }
      };
   }

   static <T> Spliterator<T> filter(final Spliterator<T> var0, final Predicate<? super T> var1) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);

      class Data30 implements Spliterator<T>, Consumer<T> {
         Object holder = null;

         @Override
         public void accept(T var1x) {
            this.holder = var1x;
         }

         @Override
         public boolean tryAdvance(Consumer<? super T> var1x) {
            while (var0.tryAdvance(this)) {
               try {
                  if (var1.test(this.holder)) {
                     var1x.accept(this.holder);
                     return true;
                  }
               } finally {
                  this.holder = null;
               }
            }

            return false;
         }

         @Override
         public Spliterator<T> trySplit() {
            Spliterator var1x = var0.trySplit();
            return var1x == null ? null : MixinHelper3_5.filter(var1x, var1);
         }

         @Override
         public long estimateSize() {
            return var0.estimateSize() / 2L;
         }

         @Override
         public java.util.Comparator<? super T> getComparator() {
            return var0.getComparator();
         }

         @Override
         public int characteristics() {
            return var0.characteristics() & 277;
         }
      }

      return new Data30();
   }

   static <InElementT, OutElementT> Spliterator<OutElementT> flatMap(
      Spliterator<InElementT> var0, Function<? super InElementT, Spliterator<OutElementT>> var1, int var2, long var3
   ) {
      Preconditions.checkArgument((var2 & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
      Preconditions.checkArgument((var2 & 4) == 0, "flatMap does not support SORTED characteristic");
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      return new MixinHelper3$Data34<>(null, var0, var1, var2, var3);
   }

   static <InElementT> OfInt flatMapToInt(Spliterator<InElementT> var0, Function<? super InElementT, OfInt> var1, int var2, long var3) {
      Preconditions.checkArgument((var2 & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
      Preconditions.checkArgument((var2 & 4) == 0, "flatMap does not support SORTED characteristic");
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      return new MixinHelper3$Data36(null, var0, var1, var2, var3);
   }

   static <InElementT> OfLong flatMapToLong(Spliterator<InElementT> var0, Function<? super InElementT, OfLong> var1, int var2, long var3) {
      Preconditions.checkArgument((var2 & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
      Preconditions.checkArgument((var2 & 4) == 0, "flatMap does not support SORTED characteristic");
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      return new MixinHelper3$Data33(null, var0, var1, var2, var3);
   }

   static <InElementT> OfDouble flatMapToDouble(Spliterator<InElementT> var0, Function<? super InElementT, OfDouble> var1, int var2, long var3) {
      Preconditions.checkArgument((var2 & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
      Preconditions.checkArgument((var2 & 4) == 0, "flatMap does not support SORTED characteristic");
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      return new MixinHelper3$Data32(null, var0, var1, var2, var3);
   }
}
