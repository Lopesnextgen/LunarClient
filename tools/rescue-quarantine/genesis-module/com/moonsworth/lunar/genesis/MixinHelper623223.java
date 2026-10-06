package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.util.Set;
import com.google.common.collect.ImmutableMap;
import com.google.common.graph.ElementOrder;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;

@Immutable(containerOf = "N")
@Annotation2
public class MixinHelper623223<N> extends MixinHelper62322<N> {
   private final MixinHelper62<N> field1;

   MixinHelper623223(MixinHelper62<N> var1) {
      this.field1 = var1;
   }

   public static <N> MixinHelper623223<N> method1(MixinHelper622<N> var0) {
      return var0 instanceof MixinHelper623223
         ? (MixinHelper623223)var0
         : new MixinHelper623223<>(new MixinHelper62333<>(MixinHelper84.method3(var0), method4(var0), var0.edges().size()));
   }

   @Deprecated
   public static <N> MixinHelper623223<N> method2(MixinHelper623223<N> var0) {
      return Preconditions.checkNotNull(var0);
   }

   @Override
   public ElementOrder<N> method2() {
      return ElementOrder.method2();
   }

   private static <N> ImmutableMap<N, MixinHelper3_13<N, MixinHelper2$Type6>> method4(MixinHelper622<N> var0) {
      ImmutableMap.Data2 var1 = ImmutableMap.method7();

      for (Object var3 : var0.nodes()) {
         var1.method1(var3, method5(var0, var3));
      }

      return var1.method7();
   }

   private static <N> MixinHelper3_13<N, MixinHelper2$Type6> method5(MixinHelper622<N> var0, N var1) {
      MixinHelper24_2 var2 = MixinHelper6_13.method7(MixinHelper2$Type6.EDGE_EXISTS);
      return var0.isDirected()
         ? MixinHelper32_2.method2((N)var1, (Iterable<IterableBase<N>>)var0.incidentEdges(var1), var2)
         : MixinHelper33_2.method2(Maps.method8((Set<N>)var0.adjacentNodes(var1), var2));
   }

   @Override
   protected MixinHelper62<N> method3() {
      return this.field1;
   }

   public static class Data<N> {
      private final MixinHelper6222<N> field1;

      Data(MixinHelper84<N> var1) {
         this.field1 = var1.method10().method8(ElementOrder.method2()).method9();
      }

      @CanIgnoreReturnValue
      public MixinHelper623223.Data<N> method1(N var1) {
         this.field1.addNode((N)var1);
         return this;
      }

      @CanIgnoreReturnValue
      public MixinHelper623223.Data<N> method2(N var1, N var2) {
         this.field1.putEdge((N)var1, (N)var2);
         return this;
      }

      @CanIgnoreReturnValue
      public MixinHelper623223.Data<N> method3(IterableBase<N> var1) {
         this.field1.method1(var1);
         return this;
      }

      public MixinHelper623223<N> method4() {
         return MixinHelper623223.method1(this.field1);
      }
   }
}
