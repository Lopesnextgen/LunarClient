package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.DoNotMock;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.Graphs;
import com.google.common.base.Preconditions;

@DoNotMock
@Annotation2
public final class MixinHelper84<N> extends MixinHelper8<N> {
   private MixinHelper84(boolean var1) {
      super(var1);
   }

   public static MixinHelper84<Object> method1() {
      return new MixinHelper84<>(true);
   }

   public static MixinHelper84<Object> method2() {
      return new MixinHelper84<>(false);
   }

   public static <N> MixinHelper84<N> method3(MixinHelper622<N> var0) {
      return new MixinHelper84(var0.isDirected()).method5(var0.allowsSelfLoops()).method7(var0.method1()).method8(var0.method2());
   }

   public <N1 extends N> MixinHelper623223.Data<N1> method4() {
      MixinHelper84 var1 = this.method11();
      return new MixinHelper623223.Data<>(var1);
   }

   public MixinHelper84<N> method5(boolean var1) {
      this.allowsSelfLoops = var1;
      return this;
   }

   public MixinHelper84<N> method6(int var1) {
      this.field4 = SerializableBase_2.method2(Graphs.checkNonNegative(var1));
      return this;
   }

   public <N1 extends N> MixinHelper84<N1> method7(ElementOrder<N1> var1) {
      MixinHelper84 var2 = this.method11();
      var2.field2 = Preconditions.checkNotNull(var1);
      return var2;
   }

   public <N1 extends N> MixinHelper84<N1> method8(ElementOrder<N1> var1) {
      Preconditions.checkArgument(
         var1.method6() == MixinHelper4$Type.UNORDERED || var1.method6() == MixinHelper4$Type.STABLE,
         "The given elementOrder (%s) is unsupported. incidentEdgeOrder() only supports ElementOrder.unordered() and ElementOrder.stable().",
         var1
      );
      MixinHelper84 var2 = this.method11();
      var2.field3 = Preconditions.checkNotNull(var1);
      return var2;
   }

   public <N1 extends N> MixinHelper6222<N1> method9() {
      return new MixinHelper623222<>(this);
   }

   MixinHelper84<N> method10() {
      MixinHelper84 var1 = new MixinHelper84(this.field1);
      var1.allowsSelfLoops = this.allowsSelfLoops;
      var1.IICOIRRCCHRHCHCOCOIHRIIORCOHCO = this.field2;
      var1.field4 = this.field4;
      var1.RCHHIRRRHOHCIIOCRCROCRCOHIIORR = this.field3;
      return var1;
   }

   private <N1 extends N> MixinHelper84<N1> method11() {
      return this;
   }
}
