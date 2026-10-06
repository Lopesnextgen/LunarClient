package com.moonsworth.lunar.genesis;
import com.google.common.base.Preconditions;

@Annotation2
@Annotation3
public abstract class MixinHelper11_4 {
   public static MixinHelper11$Data18 method1(double var0, double var2) {
      Preconditions.checkArgument(MixinHelper5_11.isFinite(var0) && MixinHelper5_11.isFinite(var2));
      return new MixinHelper11$Data18(var0, var2);
   }

   public static MixinHelper11_4 method2(double var0) {
      Preconditions.checkArgument(MixinHelper5_11.isFinite(var0));
      return new MixinHelper11$Data20(var0);
   }

   public static MixinHelper11_4 method3(double var0) {
      Preconditions.checkArgument(MixinHelper5_11.isFinite(var0));
      double var2 = 0.0;
      return new MixinHelper11$Data19(var2, var0);
   }

   public static MixinHelper11_4 method4() {
      return MixinHelper11$Data17.field1;
   }

   public abstract boolean isVertical();

   public abstract boolean isHorizontal();

   public abstract double slope();

   public abstract double transform(double var1);

   public abstract MixinHelper11_4 method5();
}
