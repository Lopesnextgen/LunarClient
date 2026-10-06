package com.moonsworth.lunar.genesis;
import com.google.common.graph.ElementOrder;
import com.google.common.base.Preconditions;
import com.google.common.graph.Graphs;

@Annotation2
public final class MixinHelper82<N, V> extends MixinHelper8<N> {
   private MixinHelper82(boolean var1) {
      super(var1);
   }

   public static MixinHelper82<Object, Object> method1() {
      return new MixinHelper82<>(true);
   }

   public static MixinHelper82<Object, Object> method2() {
      return new MixinHelper82<>(false);
   }

   public static <N, V> MixinHelper82<N, V> method3(MixinHelper624<N, V> var0) {
      return new MixinHelper82(var0.isDirected()).method5(var0.allowsSelfLoops()).method7(var0.method1()).method8(var0.method2());
   }

   public <N1 extends N, V1 extends V> MixinHelper623332.Data<N1, V1> method4() {
      MixinHelper82 var1 = this.method11();
      return new MixinHelper623332.Data<>(var1);
   }

   public MixinHelper82<N, V> method5(boolean var1) {
      this.allowsSelfLoops = var1;
      return this;
   }

   public MixinHelper82<N, V> method6(int var1) {
      this.field4 = SerializableBase_2.method2(Graphs.checkNonNegative(var1));
      return this;
   }

   public <N1 extends N> MixinHelper82<N1, V> method7(ElementOrder<N1> var1) {
      MixinHelper82 var2 = this.method11();
      var2.IICOIRRCCHRHCHCOCOIHRIIORCOHCO = Preconditions.checkNotNull(var1);
      return var2;
   }

   public <N1 extends N> MixinHelper82<N1, V> method8(ElementOrder<N1> var1) {
      Preconditions.checkArgument(
         var1.method6() == MixinHelper4$Type.UNORDERED || var1.method6() == MixinHelper4$Type.STABLE,
         "The given elementOrder (%s) is unsupported. incidentEdgeOrder() only supports ElementOrder.unordered() and ElementOrder.stable().",
         var1
      );
      MixinHelper82 var2 = this.method11();
      var2.RCHHIRRRHOHCIIOCRCROCRCOHIIORR = Preconditions.checkNotNull(var1);
      return var2;
   }

   public <N1 extends N, V1 extends V> MixinHelper6242<N1, V1> method9() {
      return new MixinHelper623333<>(this);
   }

   MixinHelper82<N, V> method10() {
      MixinHelper82 var1 = new MixinHelper82(this.COHIIRHIICOICRHIIHRHRCCCIIROHI);
      var1.allowsSelfLoops = this.allowsSelfLoops;
      var1.IICOIRRCCHRHCHCOCOIHRIIORCOHCO = this.IICOIRRCCHRHCHCOCOIHRIIORCOHCO;
      var1.field4 = this.field4;
      var1.RCHHIRRRHOHCIIOCRCROCRCOHIIORR = this.RCHHIRRRHOHCIIOCRCROCRCOHIIORR;
      return var1;
   }

   private <N1 extends N, V1 extends V> MixinHelper82<N1, V1> method11() {
      return this;
   }
}
