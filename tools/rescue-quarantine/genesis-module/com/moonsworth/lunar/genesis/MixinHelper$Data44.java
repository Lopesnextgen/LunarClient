package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.qual.Nullable;

@Annotation4
class MixinHelper$Data44<E> extends MixinHelper$Data35 implements Collection<E> {
   private static final long field4 = 0L;

   private MixinHelper$Data44(Collection<E> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   Collection<E> delegate() {
      return (Collection<E>)super.delegate();
   }

   @Override
   public boolean add(E var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().add((E)var1);
      }
   }

   @Override
   public boolean addAll(Collection<? extends E> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().addAll(var1);
      }
   }

   @Override
   public void clear() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.delegate().clear();
      }
   }

   @Override
   public boolean contains(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().contains(var1);
      }
   }

   @Override
   public boolean containsAll(Collection<?> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().containsAll(var1);
      }
   }

   @Override
   public boolean isEmpty() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().isEmpty();
      }
   }

   @Override
   public Iterator<E> iterator() {
      return this.delegate().iterator();
   }

   @Override
   public Spliterator<E> spliterator() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().spliterator();
      }
   }

   @Override
   public Stream<E> stream() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().stream();
      }
   }

   @Override
   public Stream<E> parallelStream() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().parallelStream();
      }
   }

   @Override
   public void forEach(Consumer<? super E> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.delegate().forEach(var1);
      }
   }

   @Override
   public boolean remove(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().remove(var1);
      }
   }

   @Override
   public boolean removeAll(Collection<?> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().removeAll(var1);
      }
   }

   @Override
   public boolean retainAll(Collection<?> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().retainAll(var1);
      }
   }

   @Override
   public boolean removeIf(Predicate<? super E> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().removeIf(var1);
      }
   }

   @Override
   public int size() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().size();
      }
   }

   @Override
   public Object[] toArray() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().toArray();
      }
   }

   @Override
   public <T> T[] toArray(T[] var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return (T[])this.delegate().toArray(var1);
      }
   }
}
