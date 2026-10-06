package com.moonsworth.lunar.client.framework.feature.pkg.mixin;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Generated;

public class Pkg {
   private final List<Pkg2> field1 = Lists.newArrayList();
   private int field2;
   private int field3;
   private boolean field4;
   private final int field5;
   private final int field6;

   public static Pkg method1(int value, int var1) {
      return new Pkg(value, var1);
   }

   public Pkg method2(int var1, int var2) {
      this.field2 = var1;
      this.field3 = var2;
      return this;
   }

   public Pkg method3(boolean var1) {
      this.field4 = var1;
      return this;
   }

   public List<Pkg2> method4() {
      return this.field1;
   }

   public Pkg method5(float var1, float var2, float value, float value2, PkgType[] items, PkgType[][] items2) {
      this.field1
         .add(new Pkg2(this.field2, this.field3, var1, var2, value, value2, value2, value2, 0.0F, 0.0F, 0.0F, this.field4, this.field5, this.field6, items, items2));
      return this;
   }

   @Generated
   public Pkg(int var1, int var2) {
      this.field5 = var1;
      this.field6 = var2;
   }
}
