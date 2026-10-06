package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper$Data43<E> extends MixinHelper$Data44<E> implements List<E> {
   private static final long field5 = 0L;

   MixinHelper$Data43(List<E> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   List<E> delegate() {
      return (List<E>)super.delegate();
   }

   @Override
   public void add(int var1, E var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.delegate().add(var1, (E)var2);
      }
   }

   @Override
   public boolean addAll(int var1, Collection<? extends E> var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().addAll(var1, var2);
      }
   }

   @Override
   public E get(int var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().get(var1);
      }
   }

   @Override
   public int indexOf(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().indexOf(var1);
      }
   }

   @Override
   public int lastIndexOf(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().lastIndexOf(var1);
      }
   }

   @Override
   public ListIterator<E> listIterator() {
      return this.delegate().listIterator();
   }

   @Override
   public ListIterator<E> listIterator(int var1) {
      return this.delegate().listIterator(var1);
   }

   @Override
   public E remove(int var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().remove(var1);
      }
   }

   @Override
   public E set(int var1, E var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().set(var1, (E)var2);
      }
   }

   @Override
   public void replaceAll(UnaryOperator<E> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.delegate().replaceAll(var1);
      }
   }

   @Override
   public void sort(java.util.Comparator<? super E> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.delegate().sort(var1);
      }
   }

   @Override
   public List<E> subList(int var1, int var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$200(this.delegate().subList(var1, var2), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      }

      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().equals(var1);
      }
   }

   @Override
   public int hashCode() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().hashCode();
      }
   }
}
