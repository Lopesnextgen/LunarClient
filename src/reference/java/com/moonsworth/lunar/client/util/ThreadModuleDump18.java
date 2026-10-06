package com.moonsworth.lunar.client.util;

import lombok.Generated;
import toxi.math.MathUtils;

public class ThreadModuleDump18 {
   private static final float HALF_PI = (float) (Math.PI / 2);
   public static final ThreadModuleDump18 LINEAR = new ThreadModuleDump18();
   public static final ThreadModuleDump18 STEP_END = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return var1 < 1.0F ? 0.0F : 1.0F;
      }

      @Override
      public double map(double var1) {
         return var1 < 1.0 ? 0.0 : 1.0;
      }

      @Override
      protected double average() {
         return 0.0;
      }
   };
   public static final ThreadModuleDump18 STEP_START = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return var1 > 0.0F ? 1.0F : 0.0F;
      }

      @Override
      public double map(double var1) {
         return var1 > 0.0 ? 1.0 : 0.0;
      }

      @Override
      protected double average() {
         return 1.0;
      }
   };
   public static final ThreadModuleDump18 STEP_MIDDLE = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return var1 > 0.5F ? 1.0F : 0.0F;
      }

      @Override
      public double map(double var1) {
         return var1 > 0.5 ? 1.0 : 0.0;
      }
   };
   public static final ThreadModuleDump18 SMOOTHSTEP = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return var1 * var1 * (3.0F - 2.0F * var1);
      }

      @Override
      public double map(double var1) {
         return var1 * var1 * (3.0 - 2.0 * var1);
      }
   };
   public static final ThreadModuleDump18 SMOOTHSTEP_DOUBLED = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         var1 = var1 * var1 * (3.0F - 2.0F * var1);
         return var1 * var1 * (3.0F - 2.0F * var1);
      }

      @Override
      public double map(double var1) {
         var1 = var1 * var1 * (3.0 - 2.0 * var1);
         return var1 * var1 * (3.0 - 2.0 * var1);
      }
   };
   public static final ThreadModuleDump18 SMOOTHERSTEP = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return var1 * var1 * var1 * (var1 * (var1 * 6.0F - 15.0F) + 10.0F);
      }

      @Override
      public double map(double var1) {
         return var1 * var1 * var1 * (var1 * (var1 * 6.0 - 15.0) + 10.0);
      }
   };
   public static final ThreadModuleDump18 QUADRATIC_IN = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return var1 * var1;
      }

      @Override
      public double map(double var1) {
         return var1 * var1;
      }

      @Override
      protected double average() {
         return 0.3333333333333333;
      }
   };
   public static final ThreadModuleDump18 QUADRATIC_OUT = new ThreadModuleDump18() {
      @Override
      protected float map(float var1) {
         return 1.0F - (1.0F - var1) * (1.0F - var1);
      }

      @Override
      protected double map(double var1) {
         return 1.0 - (1.0 - var1) * (1.0 - var1);
      }

      @Override
      protected double average() {
         return 0.6666666666666666;
      }
   };
   public static final ThreadModuleDump18 QUADRATIC_IN_OUT = new ThreadModuleDump18() {
      @Override
      protected float map(float var1) {
         return var1 < 0.5 ? 2.0F * var1 * var1 : (float)(1.0 - Math.pow(-2.0F * var1 + 2.0F, 2.0) / 2.0);
      }

      @Override
      protected double map(double var1) {
         return var1 < 0.5 ? 2.0 * var1 * var1 : 1.0 - Math.pow(-2.0 * var1 + 2.0, 2.0) / 2.0;
      }
   };
   public static final ThreadModuleDump18 CUBIC_IN = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return var1 * var1 * var1;
      }

      @Override
      public double map(double var1) {
         return var1 * var1 * var1;
      }

      @Override
      protected double average() {
         return 0.25;
      }
   };
   public static final ThreadModuleDump18 CUBIC_OUT = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return --var1 * var1 * var1 + 1.0F;
      }

      @Override
      public double map(double var1) {
         return --var1 * var1 * var1 + 1.0;
      }

      @Override
      protected double average() {
         return 0.75;
      }
   };
   public static final ThreadModuleDump18 CUBIC_IN_OUT = new ThreadModuleDump18() {
      @Override
      protected float map(float var1) {
         return var1 < 0.5 ? 4.0F * var1 * var1 * var1 : (float)(1.0 - Math.pow(-2.0F * var1 + 2.0F, 3.0) / 2.0);
      }

      @Override
      protected double map(double var1) {
         return var1 < 0.5 ? 4.0 * var1 * var1 * var1 : 1.0 - Math.pow(-2.0 * var1 + 2.0, 3.0) / 2.0;
      }
   };
   public static final ThreadModuleDump18 SINE_IN = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return (float)(1.0 - Math.cos(var1 * (float) (Math.PI / 2)));
      }

      @Override
      public double map(double var1) {
         return 1.0 - Math.cos(var1 * (float) (Math.PI / 2));
      }

      @Override
      protected double average() {
         return 0.3633802276324186;
      }
   };
   public static final ThreadModuleDump18 SINE_OUT = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return (float)Math.sin(var1 * (float) (Math.PI / 2));
      }

      @Override
      public double map(double var1) {
         return Math.sin(var1 * (float) (Math.PI / 2));
      }

      @Override
      protected double average() {
         return 0.6366197723675814;
      }
   };
   public static final ThreadModuleDump18 SINE_IN_OUT = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return (float)((1.0 - Math.cos(var1 * Math.PI)) * 0.5);
      }

      @Override
      public double map(double var1) {
         return (1.0 - Math.cos(var1 * Math.PI)) * 0.5;
      }
   };
   public static final ThreadModuleDump18 EXPONENTIAL_IN = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return var1 == 0.0F ? 0.0F : (float)Math.pow(2.0, 10.0F * var1 - 10.0F);
      }

      @Override
      public double map(double var1) {
         return var1 == 0.0 ? 0.0 : Math.pow(2.0, 10.0 * var1 - 10.0);
      }

      @Override
      protected double average() {
         return (1.0 - Math.pow(2.0, -10.0)) / (10.0 * Math.log(2.0));
      }
   };
   public static final ThreadModuleDump18 EXPONENTIAL_OUT = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return var1 == 1.0F ? 1.0F : (float)(1.0 - Math.pow(2.0, -10.0F * var1));
      }

      @Override
      public double map(double var1) {
         return var1 == 1.0 ? 1.0 : 1.0 - Math.pow(2.0, -10.0 * var1);
      }

      @Override
      protected double average() {
         return 1.0 - (1.0 - Math.pow(2.0, -10.0)) / (10.0 * Math.log(2.0));
      }
   };
   public static final ThreadModuleDump18 EXPONENTIAL_IN_OUT = new ThreadModuleDump18() {
      @Override
      protected float map(float var1) {
         if (var1 == 0.0F) {
            return 0.0F;
         } else if (var1 == 1.0F) {
            return 1.0F;
         } else {
            return var1 < 0.5F ? (float)(Math.pow(2.0, 20.0F * var1 - 10.0F) / 2.0) : (float)((2.0 - Math.pow(2.0, -20.0F * var1 + 10.0F)) / 2.0);
         }
      }

      @Override
      protected double map(double var1) {
         if (var1 == 0.0) {
            return 0.0;
         } else if (var1 == 1.0) {
            return 1.0;
         } else {
            return var1 < 0.5 ? Math.pow(2.0, 20.0 * var1 - 10.0) / 2.0 : (2.0 - Math.pow(2.0, -20.0 * var1 + 10.0)) / 2.0;
         }
      }
   };
   public static final ThreadModuleDump18 CIRCULAR_IN = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return 1.0F - (float)Math.sqrt(1.0F - var1 * var1);
      }

      @Override
      public double map(double var1) {
         return 1.0 - Math.sqrt(1.0 - var1 * var1);
      }

      @Override
      protected double average() {
         return 0.21460183660255172;
      }
   };
   public static final ThreadModuleDump18 CIRCULAR_OUT = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         var1--;
         return (float)Math.sqrt(1.0F - var1 * var1);
      }

      @Override
      public double map(double var1) {
         var1--;
         return Math.sqrt(1.0 - var1 * var1);
      }

      @Override
      protected double average() {
         return Math.PI / 4;
      }
   };
   public static final ThreadModuleDump18 CIRCULAR_IN_OUT = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         var1 *= 2.0F;
         if (var1 <= 1.0F) {
            return (1.0F - (float)Math.sqrt(1.0F - var1 * var1)) * 0.5F;
         }

         var1 -= 2.0F;
         return ((float)Math.sqrt(1.0F - var1 * var1) + 1.0F) * 0.5F;
      }

      @Override
      public double map(double var1) {
         var1 *= 2.0;
         if (var1 <= 1.0) {
            return (1.0 - Math.sqrt(1.0 - var1 * var1)) * 0.5;
         }

         var1 -= 2.0;
         return (Math.sqrt(1.0 - var1 * var1) + 1.0) * 0.5;
      }
   };
   public static final ThreadModuleDump18 BACK_IN = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return 2.70158F * var1 * var1 * var1 - 1.70158F * var1 * var1;
      }

      @Override
      public double map(double var1) {
         return 2.70158 * var1 * var1 * var1 - 1.70158 * var1 * var1;
      }

      @Override
      protected double average() {
         return 0.108202;
      }
   };
   public static final ThreadModuleDump18 BACK_OUT = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         return 1.0F + 2.70158F * (--var1 * var1 * var1) + 1.70158F * (var1 * var1);
      }

      @Override
      public double map(double var1) {
         return 1.0 + 2.70158 * (--var1 * var1 * var1) + 1.70158 * (var1 * var1);
      }

      @Override
      protected double average() {
         return 0.891798;
      }
   };
   public static final ThreadModuleDump18 BACK_IN_OUT = new ThreadModuleDump18() {
      @Override
      public float map(float var1) {
         var1 *= 2.0F;
         if (var1 < 1.0F) {
            return var1 * var1 * (3.5949094F * var1 - 2.5949094F) * 0.5F;
         }

         var1 -= 2.0F;
         return (var1 * var1 * (3.5949094F * var1 + 2.5949094F) + 2.0F) * 0.5F;
      }

      @Override
      public double map(double var1) {
         var1 *= 2.0;
         if (var1 < 1.0) {
            return var1 * var1 * (3.5949095 * var1 - 2.5949095) * 0.5;
         }

         var1 -= 2.0;
         return (var1 * var1 * (3.5949095 * var1 + 2.5949095) + 2.0) * 0.5;
      }
   };
   public static final ThreadModuleDump18 BEZIER = new ThreadModuleDump18() {
      @Override
      public float interpolate(float var1, float var2, float var3, float var4, float var5) {
         float var6 = var5 * var5;
         float var7 = var6 * var5;
         float[] var8 = new float[]{-var7 + 2.0F * var6 - var5, 3.0F * var7 - 5.0F * var6 + 2.0F, -3.0F * var7 + 4.0F * var6 + var5, var7 - var6};
         return 0.5F * (var1 * var8[0] + var2 * var8[1] + var3 * var8[2] + var4 * var8[3]);
      }

      @Override
      public double interpolate(double var1, double var3, double var5, double var7, double var9) {
         double var11 = var9 * var9;
         double var13 = var11 * var9;
         double[] var15 = new double[]{-var13 + 2.0 * var11 - var9, 3.0 * var13 - 5.0 * var11 + 2.0, -3.0 * var13 + 4.0 * var11 + var9, var13 - var11};
         return 0.5 * (var1 * var15[0] + var3 * var15[1] + var5 * var15[2] + var7 * var15[3]);
      }
   };
   public static final ThreadModuleDump18.Data ELASTIC_IN = new ThreadModuleDump18.Data(2.0F, 10.0F, 6, 1.0F);
   public static final ThreadModuleDump18.Data3 ELASTIC_OUT = new ThreadModuleDump18.Data3(2.0F, 10.0F, 7, 1.0F);
   public static final ThreadModuleDump18.Data2 ELASTIC_IN_OUT = new ThreadModuleDump18.Data2(2.0F, 10.0F, 7, 1.0F);
   public static final ThreadModuleDump18.Data6 BACK_IN_OVERSHOOT = new ThreadModuleDump18.Data6(2.0F);
   public static final ThreadModuleDump18.Data5 BACK_OUT_OVERSHOOT = new ThreadModuleDump18.Data5(2.0F);
   public static final ThreadModuleDump18.Data4 BACK_IN_OUT_OVERSHOOT = new ThreadModuleDump18.Data4(1.5F);
   private final double averageValue = this.average();

   protected float map(float var1) {
      return var1;
   }

   protected double map(double var1) {
      return var1;
   }

   public float interpolate(float var1, float var2, float var3, float var4, float var5) {
      return var2 + (var3 - var2) * this.map(var5);
   }

   public double interpolate(double var1, double var3, double var5, double var7, double var9) {
      return var3 + (var5 - var3) * this.map(var9);
   }

   protected double average() {
      return 0.5;
   }

   @Generated
   public double getAverageValue() {
      return this.averageValue;
   }

   public static class Data extends ThreadModuleDump18.Data2 {
      public Data(float var1, float var2, int var3, float var4) {
         super(var1, var2, var3, var4);
      }

      @Override
      public float map(float var1) {
         return var1 >= 0.99
            ? 1.0F
            : (float)Math.pow(this.IRCCCICCIOCCRIOHIHHCOOIROIIIHR, this.RIIOIRHRROHOIICCRRHOHOORICIOHH * (var1 - 1.0F))
               * MathUtils.sin(var1 * this.RRHOIRCOIHICRHIICIROIOOIICICHI)
               * this.CRIIOHHRIOCICCOOHCRIHHRRRHRCRH;
      }

      @Override
      public double map(double var1) {
         return var1 > 0.99
            ? 1.0
            : Math.pow(this.IRCCCICCIOCCRIOHIHHCOOIROIIIHR, this.RIIOIRHRROHOIICCRRHOHOORICIOHH * (var1 - 1.0))
               * MathUtils.sin(var1 * this.RRHOIRCOIHICRHIICIROIOOIICICHI)
               * this.CRIIOHHRIOCICCOOHCRIHHRRRHRCRH;
      }

      @Override
      protected double average() {
         return 0.01;
      }
   }

   public static class Data2 extends ThreadModuleDump18 {
      final float field35;
      final float field36;
      final float field37;
      final float field38;

      public Data2(float var1, float var2, int var3, float var4) {
         this.field35 = var1;
         this.field36 = var2;
         this.field37 = var4;
         this.field38 = (float)(var3 * Math.PI * (var3 % 2 == 0 ? 1 : -1));
      }

      @Override
      public float map(float var1) {
         if (var1 <= 0.5F) {
            var1 *= 2.0F;
            return (float)Math.pow(this.field35, this.field36 * (var1 - 1.0F)) * MathUtils.sin(var1 * this.field38) * this.field37 * 0.5F;
         } else {
            var1 = 1.0F - var1;
            var1 *= 2.0F;
            return 1.0F - (float)Math.pow(this.field35, this.field36 * (var1 - 1.0F)) * MathUtils.sin(var1 * this.field38) * this.field37 * 0.5F;
         }
      }

      @Override
      public double map(double var1) {
         if (var1 <= 0.5) {
            var1 *= 2.0;
            return Math.pow(this.field35, this.field36 * (var1 - 1.0)) * MathUtils.sin(var1 * this.field38) * this.field37 * 0.5;
         } else {
            var1 = 1.0 - var1;
            var1 *= 2.0;
            return 1.0 - Math.pow(this.field35, this.field36 * (var1 - 1.0)) * MathUtils.sin(var1 * this.field38) * this.field37 * 0.5;
         }
      }
   }

   public static class Data3 extends ThreadModuleDump18.Data2 {
      public Data3(float var1, float var2, int var3, float var4) {
         super(var1, var2, var3, var4);
      }

      @Override
      public float map(float var1) {
         if (var1 == 0.0F) {
            return 0.0F;
         }

         var1 = 1.0F - var1;
         return 1.0F
            - (float)Math.pow(this.IRCCCICCIOCCRIOHIHHCOOIROIIIHR, this.RIIOIRHRROHOIICCRRHOHOORICIOHH * (var1 - 1.0F))
               * MathUtils.sin(var1 * this.RRHOIRCOIHICRHIICIROIOOIICICHI)
               * this.CRIIOHHRIOCICCOOHCRIHHRRRHRCRH;
      }

      @Override
      public double map(double var1) {
         if (var1 == 0.0) {
            return 0.0;
         }

         var1 = 1.0 - var1;
         return 1.0
            - Math.pow(this.IRCCCICCIOCCRIOHIHHCOOIROIIIHR, this.RIIOIRHRROHOIICCRRHOHOORICIOHH * (var1 - 1.0))
               * MathUtils.sin(var1 * this.RRHOIRCOIHICRHIICIROIOOIICICHI)
               * this.CRIIOHHRIOCICCOOHCRIHHRRRHRCRH;
      }

      @Override
      protected double average() {
         return 0.999999;
      }
   }

   public static class Data4 extends ThreadModuleDump18 {
      final float field35;

      public Data4(float var1) {
         this.field35 = var1 * 2.0F;
      }

      @Override
      public float map(float var1) {
         var1 *= 2.0F;
         if (var1 <= 1.0F) {
            return var1 * var1 * ((this.field35 + 1.0F) * var1 - this.field35) * 0.5F;
         }

         var1 -= 2.0F;
         return var1 * var1 * ((this.field35 + 1.0F) * var1 + this.field35) * 0.5F + 1.0F;
      }

      @Override
      public double map(double var1) {
         var1 *= 2.0;
         if (var1 <= 1.0) {
            return var1 * var1 * ((this.field35 + 1.0F) * var1 - this.field35) * 0.5;
         }

         var1 -= 2.0;
         return var1 * var1 * ((this.field35 + 1.0F) * var1 + this.field35) * 0.5 + 1.0;
      }
   }

   public static class Data5 extends ThreadModuleDump18.Data4 {
      public Data5(float var1) {
         super(var1);
      }

      @Override
      public float map(float var1) {
         return --var1 * var1 * ((this.RICCHRIHCCHIIRHIOOOCHICCORROOO + 1.0F) * var1 + this.RICCHRIHCCHIIRHIOOOCHICCORROOO) + 1.0F;
      }

      @Override
      public double map(double var1) {
         return --var1 * var1 * ((this.RICCHRIHCCHIIRHIOOOCHICCORROOO + 1.0F) * var1 + this.RICCHRIHCCHIIRHIOOOCHICCORROOO) + 1.0;
      }

      @Override
      protected double average() {
         return 0.75;
      }
   }

   public static class Data6 extends ThreadModuleDump18.Data4 {
      public Data6(float var1) {
         super(var1);
      }

      @Override
      public float map(float var1) {
         return var1 * var1 * ((this.RICCHRIHCCHIIRHIOOOCHICCORROOO + 1.0F) * var1 - this.RICCHRIHCCHIIRHIOOOCHICCORROOO);
      }

      @Override
      public double map(double var1) {
         return var1 * var1 * ((this.RICCHRIHCCHIIRHIOOOCHICCORROOO + 1.0F) * var1 - this.RICCHRIHCCHIIRHIOOOCHICCORROOO);
      }

      @Override
      protected double average() {
         return 0.25;
      }
   }
}
