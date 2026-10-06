package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true)
final class Comparator11 extends Ordering<Comparable> implements Serializable {
   static final Comparator11 field3 = new Comparator11();
   private transient @Nullable Ordering<Comparable> field4;
   private transient @Nullable Ordering<Comparable> field5;
   private static final long field6 = 0L;

   public int compare(Comparable var1, Comparable var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      return var1.compareTo(var2);
   }

   @Override
   public <S extends Comparable> Ordering<S> method10() {
      Ordering var1 = this.field4;
      if (var1 == null) {
         var1 = this.field4 = super.method10();
      }

      return var1;
   }

   @Override
   public <S extends Comparable> Ordering<S> method11() {
      Ordering var1 = this.field5;
      if (var1 == null) {
         var1 = this.field5 = super.method11();
      }

      return var1;
   }

   @Override
   public <S extends Comparable> Ordering<S> method9() {
      return Comparator6.field3;
   }

   private Object readResolve() {
      return field3;
   }

   @Override
   public String toString() {
      return "Ordering.natural()";
   }

   private Comparator11() {
   }
}
