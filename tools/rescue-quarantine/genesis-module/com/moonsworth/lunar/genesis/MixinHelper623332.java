package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.util.Set;
import com.google.common.collect.ImmutableMap;
import com.google.common.graph.ElementOrder;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;

@Immutable(containerOf = {"N", "V"})
@Annotation2
public final class MixinHelper623332<N, V> extends MixinHelper62333<N, V> {
   private MixinHelper623332(MixinHelper624<N, V> var1) {
      super(MixinHelper82.method3(var1), method5(var1), var1.edges().size());
   }

   public static <N, V> MixinHelper623332<N, V> method1(MixinHelper624<N, V> var0) {
      return var0 instanceof MixinHelper623332 ? (MixinHelper623332)var0 : new MixinHelper623332<>(var0);
   }

   @Deprecated
   public static <N, V> MixinHelper623332<N, V> method2(MixinHelper623332<N, V> var0) {
      return Preconditions.checkNotNull(var0);
   }

   @Override
   public ElementOrder<N> method2() {
      return ElementOrder.method2();
   }

   public MixinHelper623223<N> method4() {
      return new MixinHelper623223<>(this);
   }

   private static <N, V> ImmutableMap<N, MixinHelper3_13<N, V>> method5(MixinHelper624<N, V> var0) {
      ImmutableMap.Data2 var1 = ImmutableMap.method7();

      for (Object var3 : var0.nodes()) {
         var1.method1(var3, method6(var0, var3));
      }

      return var1.method7();
   }

   private static <N, V> MixinHelper3_13<N, V> method6(final MixinHelper624<N, V> var0, final N var1) {
      MixinHelper24_2 var2 = new MixinHelper24_2<N, V>() {
         @Override
         public V apply(N var1x) {
            return (V)var0.edgeValueOrDefault(var1, var1x, null);
         }
      };
      return var0.isDirected()
         ? MixinHelper32_2.method2((N)var1, (Iterable<IterableBase<N>>)var0.incidentEdges(var1), var2)
         : MixinHelper33_2.method2(Maps.method8((Set<N>)var0.adjacentNodes(var1), var2));
   }

   public static class Data<N, V> {
      private final MixinHelper6242<N, V> field1;

      Data(MixinHelper82<N, V> var1) {
         this.field1 = var1.method10().method8(ElementOrder.method2()).method9();
      }

      @CanIgnoreReturnValue
      public MixinHelper623332.Data<N, V> method1(N var1) {
         this.field1.addNode((N)var1);
         return this;
      }

      @CanIgnoreReturnValue
      public MixinHelper623332.Data<N, V> method2(N var1, N var2, V var3) {
         this.field1.putEdgeValue((N)var1, (N)var2, (V)var3);
         return this;
      }

      @CanIgnoreReturnValue
      public MixinHelper623332.Data<N, V> method3(IterableBase<N> var1, V var2) {
         this.field1.method1(var1, (V)var2);
         return this;
      }

      public MixinHelper623332<N, V> method4() {
         return MixinHelper623332.method1(this.field1);
      }
   }
}
