package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.graph.Network;

@Immutable(containerOf = "N")
@Annotation2
public abstract class IterableBase<N> implements Iterable<N> {
   private final N field1;
   private final N field2;

   private IterableBase(N var1, N var2) {
      this.field1 = Preconditions.checkNotNull((N)var1);
      this.field2 = Preconditions.checkNotNull((N)var2);
   }

   public static <N> IterableBase<N> method1(N var0, N var1) {
      return new IterableBase.Data2<>(var0, var1);
   }

   public static <N> IterableBase<N> method2(N var0, N var1) {
      return new IterableBase.Data<>(var1, var0);
   }

   static <N> IterableBase<N> method3(MixinHelper622<?> var0, N var1, N var2) {
      return var0.isDirected() ? method1((N)var1, (N)var2) : method2((N)var1, (N)var2);
   }

   static <N> IterableBase<N> method4(Network<?, ?> var0, N var1, N var2) {
      return var0.isDirected() ? method1((N)var1, (N)var2) : method2((N)var1, (N)var2);
   }

   public abstract N source();

   public abstract N target();

   public final N method5() {
      return this.field1;
   }

   public final N method6() {
      return this.field2;
   }

   public final N method7(Object var1) {
      if (var1.equals(this.field1)) {
         return this.field2;
      } else if (var1.equals(this.field2)) {
         return this.field1;
      } else {
         throw new IllegalArgumentException("EndpointPair " + this + " does not contain node " + var1);
      }
   }

   public abstract boolean isOrdered();

   public final MixinHelperIterator3<N> method8() {
      return Iterators.method18(this.field1, this.field2);
   }

   @Override
   public abstract boolean equals(@Nullable Object var1);

   @Override
   public abstract int hashCode();

   private static final class Data<N> extends IterableBase<N> {
      private Data(N var1, N var2) {
         super(var1, var2);
      }

      @Override
      public N source() {
         throw new UnsupportedOperationException(
            "Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't."
         );
      }

      @Override
      public N target() {
         throw new UnsupportedOperationException(
            "Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't."
         );
      }

      @Override
      public boolean isOrdered() {
         return false;
      }

      @Override
      public boolean equals(@Nullable Object var1) {
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof IterableBase)) {
            return false;
         } else {
            IterableBase var2 = (IterableBase)var1;
            if (this.isOrdered() != var2.isOrdered()) {
               return false;
            } else {
               return this.method5().equals(var2.method5())
                  ? this.method6().equals(var2.method6())
                  : this.method5().equals(var2.method6()) && this.method6().equals(var2.method5());
            }
         }
      }

      @Override
      public int hashCode() {
         return this.method5().hashCode() + this.method6().hashCode();
      }

      @Override
      public String toString() {
         return "[" + this.method5() + ", " + this.method6() + "]";
      }
   }

   private static final class Data2<N> extends IterableBase<N> {
      private Data2(N var1, N var2) {
         super(var1, var2);
      }

      @Override
      public N source() {
         return (N)this.method5();
      }

      @Override
      public N target() {
         return (N)this.method6();
      }

      @Override
      public boolean isOrdered() {
         return true;
      }

      @Override
      public boolean equals(@Nullable Object var1) {
         if (var1 == this) {
            return true;
         }

         if (!(var1 instanceof IterableBase)) {
            return false;
         }

         IterableBase var2 = (IterableBase)var1;
         return this.isOrdered() != var2.isOrdered() ? false : this.source().equals(var2.source()) && this.target().equals(var2.target());
      }

      @Override
      public int hashCode() {
         return MixinHelper72.hashCode(this.source(), this.target());
      }

      @Override
      public String toString() {
         return "<" + this.source() + " -> " + this.target() + ">";
      }
   }
}
