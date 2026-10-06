package com.moonsworth.lunar.client.cosmetics.emote;

public class PositionHistory {
   private static final int field1 = 20;
   private final double[] x = new double[20];
   private final double[] y = new double[20];
   private final double[] field2 = new double[20];
   private int field3 = 0;
   private int index = 0;
   private final double[] field4 = new double[]{0.0, 0.0, 0.0};

   public PositionHistory() {
   }

   public void method1(double value, double value2, double value3) {
      this.x[this.index] = value;
      this.y[this.index] = value2;
      this.field2[this.index] = value3;
      this.index++;
      if (this.index > this.field3) {
         this.field3 = this.index;
      }

      if (this.index >= 20) {
         this.index = 0;
      }

      this.method2();
   }

   private void method2() {
      if (this.field3 != 0) {
         this.field4[0] = this.field4[1] = this.field4[2] = 0.0;

         for (int index1 = 0; index1 < this.field3; index1++) {
            this.field4[0] = this.field4[0] + this.x[index1];
            this.field4[1] = this.field4[1] + this.y[index1];
            this.field4[2] = this.field4[2] + this.field2[index1];
         }

         this.field4[0] = this.field4[0] / this.field3;
         this.field4[1] = this.field4[1] / this.field3;
         this.field4[2] = this.field4[2] / this.field3;
      }
   }

   public double[] method3() {
      return this.field4;
   }
}
