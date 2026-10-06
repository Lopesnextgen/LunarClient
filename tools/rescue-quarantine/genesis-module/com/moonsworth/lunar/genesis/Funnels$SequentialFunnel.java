package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.hash.PrimitiveSink;
import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;

class Funnels$SequentialFunnel<E> implements Funnel<Iterable<? extends E>>, Serializable {
   private final Funnel<E> field1;

   Funnels$SequentialFunnel(Funnel<E> serializableextension1) {
      this.field1 = (Funnel<E>)Preconditions.checkNotNull(serializableextension1);
   }

   public void method1(Iterable<? extends E> list1, PrimitiveSink mixinhelper4_32) {
      for (Object obj4 : list1) {
         this.field1.funnel(obj4, mixinhelper4_32);
      }
   }

   @Override
   public String toString() {
      return "Funnels.sequentialFunnel(" + this.field1 + ")";
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Funnels$SequentialFunnel) {
         Funnels$SequentialFunnel mixinhelper3$data372 = (Funnels$SequentialFunnel)obj1;
         return this.field1.equals(mixinhelper3$data372.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Funnels$SequentialFunnel.class.hashCode() ^ this.field1.hashCode();
   }
}
