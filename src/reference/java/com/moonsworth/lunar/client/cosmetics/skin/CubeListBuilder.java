package com.moonsworth.lunar.client.cosmetics.skin;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Generated;

public class CubeListBuilder {
   private final List<CustomizableCube> field1 = Lists.newArrayList();
   private int field2;
   private int field3;
   private boolean field4;
   private final int field5;
   private final int field6;

   public static CubeListBuilder method1(int value, int number1) {
      return new CubeListBuilder(value, number1);
   }

   public CubeListBuilder method2(int number1, int number2) {
      this.field2 = number1;
      this.field3 = number2;
      return this;
   }

   public CubeListBuilder method3(boolean flag) {
      this.field4 = flag;
      return this;
   }

   public List<CustomizableCube> method4() {
      return this.field1;
   }

   public CubeListBuilder method5(float value, float value2, float value3, float value4, CubeDirection[] items5, CubeDirection[][] items6) {
      this.field1
         .add(new CustomizableCube(this.field2, this.field3, value, value2, value3, value4, value4, value4, 0.0F, 0.0F, 0.0F, this.field4, this.field5, this.field6, items5, items6));
      return this;
   }

   @Generated
   public CubeListBuilder(int number1, int number2) {
      this.field5 = number1;
      this.field6 = number2;
   }
}
