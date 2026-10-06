package com.moonsworth.lunar.genesis;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.Network;
import com.google.common.base.Preconditions;
import com.google.common.graph.Graphs;
import com.google.common.graph.ImmutableNetwork;

@Annotation2
public final class MixinHelper83<N, E> extends MixinHelper8<N> {
   boolean allowsParallelEdges = false;
   ElementOrder<? super E> field5 = ElementOrder.method3();
   SerializableBase_2<Integer> field6 = SerializableBase_2.method1();

   private MixinHelper83(boolean var1) {
      super(var1);
   }

   public static MixinHelper83<Object, Object> method1() {
      return new MixinHelper83<>(true);
   }

   public static MixinHelper83<Object, Object> method2() {
      return new MixinHelper83<>(false);
   }

   public static <N, E> MixinHelper83<N, E> method3(Network<N, E> var0) {
      return new MixinHelper83<N, E>(var0.isDirected())
         .method5(var0.allowsParallelEdges())
         .method6(var0.allowsSelfLoops())
         .<N>method9(var0.method2())
         .method10(var0.method3());
   }

   public <N1 extends N, E1 extends E> ImmutableNetwork.Data<N1, E1> method4() {
      MixinHelper83 var1 = this.method12();
      return new ImmutableNetwork.Data<>(var1);
   }

   public MixinHelper83<N, E> method5(boolean var1) {
      this.allowsParallelEdges = var1;
      return this;
   }

   public MixinHelper83<N, E> method6(boolean var1) {
      this.allowsSelfLoops = var1;
      return this;
   }

   public MixinHelper83<N, E> method7(int var1) {
      this.field6 = SerializableBase_2.method2(Graphs.checkNonNegative(var1));
      return this;
   }

   public MixinHelper83<N, E> method8(int var1) {
      this.field6 = SerializableBase_2.method2(Graphs.checkNonNegative(var1));
      return this;
   }

   public <N1 extends N> MixinHelper83<N1, E> method9(ElementOrder<N1> var1) {
      MixinHelper83 var2 = this.method12();
      var2.field5 = Preconditions.checkNotNull(var1);
      return var2;
   }

   public <E1 extends E> MixinHelper83<N, E1> method10(ElementOrder<E1> var1) {
      MixinHelper83 var2 = this.method12();
      var2.field5 = Preconditions.checkNotNull(var1);
      return var2;
   }

   public <N1 extends N, E1 extends E> MixinHelper632<N1, E1> method11() {
      return new MixinHelper63332<>(this);
   }

   private <N1 extends N, E1 extends E> MixinHelper83<N1, E1> method12() {
      return this;
   }
}
