package com.moonsworth.lunar.genesis;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.function.Consumer;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;
import com.google.common.base.Preconditions;

@Deprecated
@Annotation2
@GwtCompatible
public abstract class MixinHelper17<T> {
   @Deprecated
   public static <T> MixinHelper17<T> method1(final MixinHelper24_2<T, ? extends Iterable<T>> var0) {
      Preconditions.checkNotNull(var0);
      return new MixinHelper17<T>() {
         @Override
         public Iterable<T> children(T var1) {
            return (Iterable<T>)var0.apply(var1);
         }
      };
   }

   public abstract Iterable<T> children(T var1);

   @Deprecated
   public final FluentIterable<T> method2(final T var1) {
      Preconditions.checkNotNull(var1);
      return new FluentIterable<T>() {
         public MixinHelperIterator3<T> method1() {
            return (MixinHelperIterator3<T>)MixinHelper17.this.method3(var1);
         }

         @Override
         public void forEach(final Consumer<? super T> var1x) {
            Preconditions.checkNotNull(var1x);
            (new Consumer<T>() {
               @Override
               public void accept(T var1xx) {
                  var1x.accept(var1xx);
                  MixinHelper17.this.children(var1xx).forEach(this);
               }
            }).accept((T)var1);
         }
      };
   }

   MixinHelperIterator3<T> method3(T var1) {
      return new MixinHelper17.Data7(var1);
   }

   @Deprecated
   public final FluentIterable<T> method4(final T var1) {
      Preconditions.checkNotNull(var1);
      return new FluentIterable<T>() {
         public MixinHelperIterator3<T> method1() {
            return (MixinHelperIterator3<T>)MixinHelper17.this.method5(var1);
         }

         @Override
         public void forEach(final Consumer<? super T> var1x) {
            Preconditions.checkNotNull(var1x);
            (new Consumer<T>() {
               @Override
               public void accept(T var1xx) {
                  MixinHelper17.this.children(var1xx).forEach(this);
                  var1x.accept(var1xx);
               }
            }).accept((T)var1);
         }
      };
   }

   MixinHelperIterator3<T> method5(T var1) {
      return new MixinHelper17.Data4(var1);
   }

   @Deprecated
   public final FluentIterable<T> method6(final T var1) {
      Preconditions.checkNotNull(var1);
      return new FluentIterable<T>() {
         public MixinHelperIterator3<T> method1() {
            return MixinHelper17.this.new Data5(var1);
         }
      };
   }

   private final class Data4 extends MixinHelperIterator32_2<T> {
      private final ArrayDeque<MixinHelper17.Data6<T>> field2 = new ArrayDeque<>();

      Data4(T var2) {
         this.field2.addLast(this.method1((T)var2));
      }

      @Override
      protected T computeNext() {
         while (!this.field2.isEmpty()) {
            MixinHelper17.Data6 var1 = this.field2.getLast();
            if (!var1.field2.hasNext()) {
               this.field2.removeLast();
               return var1.field1;
            }

            Object var2 = var1.field2.next();
            this.field2.addLast(this.method1((T)var2));
         }

         return (T)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
      }

      private MixinHelper17.Data6<T> method1(T var1) {
         return new MixinHelper17.Data6<>((T)var1, MixinHelper17.this.children((T)var1).iterator());
      }
   }

   private final class Data5 extends MixinHelperIterator3<T> implements PeekingIterator<T> {
      private final Queue<T> field1 = new ArrayDeque<>();

      Data5(T var2) {
         this.field1.add((T)var2);
      }

      @Override
      public boolean hasNext() {
         return !this.field1.isEmpty();
      }

      @Override
      public T peek() {
         return this.field1.element();
      }

      @Override
      public T next() {
         Object var1 = this.field1.remove();
         Iterables.addAll(this.field1, MixinHelper17.this.children((T)var1));
         return (T)var1;
      }
   }

   private static final class Data6<T> {
      final T field1;
      final Iterator<T> field2;

      Data6(T var1, Iterator<T> var2) {
         this.field1 = Preconditions.checkNotNull((T)var1);
         this.field2 = Preconditions.checkNotNull(var2);
      }
   }

   private final class Data7 extends MixinHelperIterator3<T> {
      private final Deque<Iterator<T>> field1 = new ArrayDeque<>();

      Data7(T var2) {
         this.field1.addLast(Iterators.method20(Preconditions.checkNotNull((T)var2)));
      }

      @Override
      public boolean hasNext() {
         return !this.field1.isEmpty();
      }

      @Override
      public T next() {
         Iterator var1 = this.field1.getLast();
         Object var2 = Preconditions.checkNotNull(var1.next());
         if (!var1.hasNext()) {
            this.field1.removeLast();
         }

         Iterator var3 = MixinHelper17.this.children((T)var2).iterator();
         if (var3.hasNext()) {
            this.field1.addLast(var3);
         }

         return (T)var2;
      }
   }
}
