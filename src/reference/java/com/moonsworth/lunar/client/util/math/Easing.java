package com.moonsworth.lunar.client.util.math;

import lombok.Generated;
import toxi.math.MathUtils;

public class Easing {
   private static final float field1 = (float) (Math.PI / 2);
   public static final Easing field2 = new Easing();
   public static final Easing field3 = new Easing() {
      @Override
      public float method1(float value1) {
         return value1 < 1.0F ? 0.0F : 1.0F;
      }

      @Override
      public double method2(double value1) {
         return value1 < 1.0 ? 0.0 : 1.0;
      }

      @Override
      protected double method5() {
         return 0.0;
      }
   };
   public static final Easing field4 = new Easing() {
      @Override
      public float method1(float value1) {
         return value1 > 0.0F ? 1.0F : 0.0F;
      }

      @Override
      public double method2(double value1) {
         return value1 > 0.0 ? 1.0 : 0.0;
      }

      @Override
      protected double method5() {
         return 1.0;
      }
   };
   public static final Easing field5 = new Easing() {
      @Override
      public float method1(float value1) {
         return value1 > 0.5F ? 1.0F : 0.0F;
      }

      @Override
      public double method2(double value1) {
         return value1 > 0.5 ? 1.0 : 0.0;
      }
   };
   public static final Easing field6 = new Easing() {
      @Override
      public float method1(float value1) {
         return value1 * value1 * (3.0F - 2.0F * value1);
      }

      @Override
      public double method2(double value1) {
         return value1 * value1 * (3.0 - 2.0 * value1);
      }
   };
   public static final Easing field7 = new Easing() {
      @Override
      public float method1(float value1) {
         value1 = value1 * value1 * (3.0F - 2.0F * value1);
         return value1 * value1 * (3.0F - 2.0F * value1);
      }

      @Override
      public double method2(double value1) {
         value1 = value1 * value1 * (3.0 - 2.0 * value1);
         return value1 * value1 * (3.0 - 2.0 * value1);
      }
   };
   public static final Easing field8 = new Easing() {
      @Override
      public float method1(float value1) {
         return value1 * value1 * value1 * (value1 * (value1 * 6.0F - 15.0F) + 10.0F);
      }

      @Override
      public double method2(double value1) {
         return value1 * value1 * value1 * (value1 * (value1 * 6.0 - 15.0) + 10.0);
      }
   };
   public static final Easing field9 = new Easing() {
      @Override
      public float method1(float value1) {
         return value1 * value1;
      }

      @Override
      public double method2(double value1) {
         return value1 * value1;
      }

      @Override
      protected double method5() {
         return 0.3333333333333333;
      }
   };
   public static final Easing field10 = new Easing() {
      @Override
      protected float method1(float value1) {
         return 1.0F - (1.0F - value1) * (1.0F - value1);
      }

      @Override
      protected double method2(double value1) {
         return 1.0 - (1.0 - value1) * (1.0 - value1);
      }

      @Override
      protected double method5() {
         return 0.6666666666666666;
      }
   };
   public static final Easing field11 = new Easing() {
      @Override
      protected float method1(float value1) {
         return value1 < 0.5 ? 2.0F * value1 * value1 : (float)(1.0 - Math.pow(-2.0F * value1 + 2.0F, 2.0) / 2.0);
      }

      @Override
      protected double method2(double value1) {
         return value1 < 0.5 ? 2.0 * value1 * value1 : 1.0 - Math.pow(-2.0 * value1 + 2.0, 2.0) / 2.0;
      }
   };
   public static final Easing field12 = new Easing() {
      @Override
      public float method1(float value1) {
         return value1 * value1 * value1;
      }

      @Override
      public double method2(double value1) {
         return value1 * value1 * value1;
      }

      @Override
      protected double method5() {
         return 0.25;
      }
   };
   public static final Easing field13 = new Easing() {
      @Override
      public float method1(float value1) {
         return --value1 * value1 * value1 + 1.0F;
      }

      @Override
      public double method2(double value1) {
         return --value1 * value1 * value1 + 1.0;
      }

      @Override
      protected double method5() {
         return 0.75;
      }
   };
   public static final Easing field14 = new Easing() {
      @Override
      protected float method1(float value1) {
         return value1 < 0.5 ? 4.0F * value1 * value1 * value1 : (float)(1.0 - Math.pow(-2.0F * value1 + 2.0F, 3.0) / 2.0);
      }

      @Override
      protected double method2(double value1) {
         return value1 < 0.5 ? 4.0 * value1 * value1 * value1 : 1.0 - Math.pow(-2.0 * value1 + 2.0, 3.0) / 2.0;
      }
   };
   public static final Easing field15 = new Easing() {
      @Override
      public float method1(float value1) {
         return (float)(1.0 - Math.cos(value1 * (float) (Math.PI / 2)));
      }

      @Override
      public double method2(double value1) {
         return 1.0 - Math.cos(value1 * (float) (Math.PI / 2));
      }

      @Override
      protected double method5() {
         return 0.3633802276324186;
      }
   };
   public static final Easing field16 = new Easing() {
      @Override
      public float method1(float value1) {
         return (float)Math.sin(value1 * (float) (Math.PI / 2));
      }

      @Override
      public double method2(double value1) {
         return Math.sin(value1 * (float) (Math.PI / 2));
      }

      @Override
      protected double method5() {
         return 0.6366197723675814;
      }
   };
   public static final Easing field17 = new Easing() {
      @Override
      public float method1(float value1) {
         return (float)((1.0 - Math.cos(value1 * Math.PI)) * 0.5);
      }

      @Override
      public double method2(double value1) {
         return (1.0 - Math.cos(value1 * Math.PI)) * 0.5;
      }
   };
   public static final Easing field18 = new Easing() {
      @Override
      public float method1(float value1) {
         return value1 == 0.0F ? 0.0F : (float)Math.pow(2.0, 10.0F * value1 - 10.0F);
      }

      @Override
      public double method2(double value1) {
         return value1 == 0.0 ? 0.0 : Math.pow(2.0, 10.0 * value1 - 10.0);
      }

      @Override
      protected double method5() {
         return (1.0 - Math.pow(2.0, -10.0)) / (10.0 * Math.log(2.0));
      }
   };
   public static final Easing field19 = new Easing() {
      @Override
      public float method1(float value1) {
         return value1 == 1.0F ? 1.0F : (float)(1.0 - Math.pow(2.0, -10.0F * value1));
      }

      @Override
      public double method2(double value1) {
         return value1 == 1.0 ? 1.0 : 1.0 - Math.pow(2.0, -10.0 * value1);
      }

      @Override
      protected double method5() {
         return 1.0 - (1.0 - Math.pow(2.0, -10.0)) / (10.0 * Math.log(2.0));
      }
   };
   public static final Easing field20 = new Easing() {
      @Override
      protected float method1(float value1) {
         if (value1 == 0.0F) {
            return 0.0F;
         } else if (value1 == 1.0F) {
            return 1.0F;
         } else {
            return value1 < 0.5F ? (float)(Math.pow(2.0, 20.0F * value1 - 10.0F) / 2.0) : (float)((2.0 - Math.pow(2.0, -20.0F * value1 + 10.0F)) / 2.0);
         }
      }

      @Override
      protected double method2(double value1) {
         if (value1 == 0.0) {
            return 0.0;
         } else if (value1 == 1.0) {
            return 1.0;
         } else {
            return value1 < 0.5 ? Math.pow(2.0, 20.0 * value1 - 10.0) / 2.0 : (2.0 - Math.pow(2.0, -20.0 * value1 + 10.0)) / 2.0;
         }
      }
   };
   public static final Easing field21 = new Easing() {
      @Override
      public float method1(float value1) {
         return 1.0F - (float)Math.sqrt(1.0F - value1 * value1);
      }

      @Override
      public double method2(double value1) {
         return 1.0 - Math.sqrt(1.0 - value1 * value1);
      }

      @Override
      protected double method5() {
         return 0.21460183660255172;
      }
   };
   public static final Easing field22 = new Easing() {
      @Override
      public float method1(float value1) {
         value1--;
         return (float)Math.sqrt(1.0F - value1 * value1);
      }

      @Override
      public double method2(double value1) {
         value1--;
         return Math.sqrt(1.0 - value1 * value1);
      }

      @Override
      protected double method5() {
         return Math.PI / 4;
      }
   };
   public static final Easing field23 = new Easing() {
      @Override
      public float method1(float value1) {
         value1 *= 2.0F;
         if (value1 <= 1.0F) {
            return (1.0F - (float)Math.sqrt(1.0F - value1 * value1)) * 0.5F;
         }

         value1 -= 2.0F;
         return ((float)Math.sqrt(1.0F - value1 * value1) + 1.0F) * 0.5F;
      }

      @Override
      public double method2(double value1) {
         value1 *= 2.0;
         if (value1 <= 1.0) {
            return (1.0 - Math.sqrt(1.0 - value1 * value1)) * 0.5;
         }

         value1 -= 2.0;
         return (Math.sqrt(1.0 - value1 * value1) + 1.0) * 0.5;
      }
   };
   public static final Easing field24 = new Easing() {
      @Override
      public float method1(float value1) {
         return 2.70158F * value1 * value1 * value1 - 1.70158F * value1 * value1;
      }

      @Override
      public double method2(double value1) {
         return 2.70158 * value1 * value1 * value1 - 1.70158 * value1 * value1;
      }

      @Override
      protected double method5() {
         return 0.108202;
      }
   };
   public static final Easing field25 = new Easing() {
      @Override
      public float method1(float value1) {
         return 1.0F + 2.70158F * (--value1 * value1 * value1) + 1.70158F * (value1 * value1);
      }

      @Override
      public double method2(double value1) {
         return 1.0 + 2.70158 * (--value1 * value1 * value1) + 1.70158 * (value1 * value1);
      }

      @Override
      protected double method5() {
         return 0.891798;
      }
   };
   public static final Easing field26 = new Easing() {
      @Override
      public float method1(float value1) {
         value1 *= 2.0F;
         if (value1 < 1.0F) {
            return value1 * value1 * (3.5949094F * value1 - 2.5949094F) * 0.5F;
         }

         value1 -= 2.0F;
         return (value1 * value1 * (3.5949094F * value1 + 2.5949094F) + 2.0F) * 0.5F;
      }

      @Override
      public double method2(double value1) {
         value1 *= 2.0;
         if (value1 < 1.0) {
            return value1 * value1 * (3.5949095 * value1 - 2.5949095) * 0.5;
         }

         value1 -= 2.0;
         return (value1 * value1 * (3.5949095 * value1 + 2.5949095) + 2.0) * 0.5;
      }
   };
   public static final Easing field27 = new Easing() {
      @Override
      public float method3(float value1, float value2, float value3, float value4, float value5) {
         float value6 = value5 * value5;
         float value7 = value6 * value5;
         float[] items8 = new float[]{-value7 + 2.0F * value6 - value5, 3.0F * value7 - 5.0F * value6 + 2.0F, -3.0F * value7 + 4.0F * value6 + value5, value7 - value6};
         return 0.5F * (value1 * items8[0] + value2 * items8[1] + value3 * items8[2] + value4 * items8[3]);
      }

      @Override
      public double method4(double value1, double value3, double value5, double value7, double value9) {
         double value11 = value9 * value9;
         double value13 = value11 * value9;
         double[] items15 = new double[]{-value13 + 2.0 * value11 - value9, 3.0 * value13 - 5.0 * value11 + 2.0, -3.0 * value13 + 4.0 * value11 + value9, value13 - value11};
         return 0.5 * (value1 * items15[0] + value3 * items15[1] + value5 * items15[2] + value7 * items15[3]);
      }
   };
   public static final Easing.Data field28 = new Easing.Data(2.0F, 10.0F, 6, 1.0F);
   public static final Easing.ElasticOut field29 = new Easing.ElasticOut(2.0F, 10.0F, 7, 1.0F);
   public static final Easing.ElasticInOut field30 = new Easing.ElasticInOut(2.0F, 10.0F, 7, 1.0F);
   public static final Easing.BackIn field31 = new Easing.BackIn(2.0F);
   public static final Easing.BackOut field32 = new Easing.BackOut(2.0F);
   public static final Easing.BackInOut field33 = new Easing.BackInOut(1.5F);
   private final double field34 = this.method5();

   public Easing() {
   }

   protected float method1(float value1) {
      return value1;
   }

   protected double method2(double value1) {
      return value1;
   }

   public float method3(float value1, float value2, float value3, float value4, float value5) {
      return value2 + (value3 - value2) * this.method1(value5);
   }

   public double method4(double value1, double value3, double value5, double value7, double value9) {
      return value3 + (value5 - value3) * this.method2(value9);
   }

   protected double method5() {
      return 0.5;
   }

   @Generated
   public double method6() {
      return this.field34;
   }

   public static class Data extends Easing.ElasticInOut {
      public Data(float value1, float value2, int number3, float value4) {
         super(value1, value2, number3, value4);
      }

      @Override
      public float method1(float value1) {
         return value1 >= 0.99
            ? 1.0F
            : (float)Math.pow(this.IRCCCICCIOCCRIOHIHHCOOIROIIIHR, this.RIIOIRHRROHOIICCRRHOHOORICIOHH * (value1 - 1.0F))
               * MathUtils.sin(value1 * this.RRHOIRCOIHICRHIICIROIOOIICICHI)
               * this.CRIIOHHRIOCICCOOHCRIHHRRRHRCRH;
      }

      @Override
      public double method2(double value1) {
         return value1 > 0.99
            ? 1.0
            : Math.pow(this.IRCCCICCIOCCRIOHIHHCOOIROIIIHR, this.RIIOIRHRROHOIICCRRHOHOORICIOHH * (value1 - 1.0))
               * MathUtils.sin(value1 * this.RRHOIRCOIHICRHIICIROIOOIICICHI)
               * this.CRIIOHHRIOCICCOOHCRIHHRRRHRCRH;
      }

      @Override
      protected double method5() {
         return 0.01;
      }
   }

   public static class ElasticInOut extends Easing {
      final float field35;
      final float field36;
      final float field37;
      final float field38;

      public ElasticInOut(float value1, float value2, int number3, float value4) {
         this.field35 = value1;
         this.field36 = value2;
         this.field37 = value4;
         this.field38 = (float)(number3 * Math.PI * (number3 % 2 == 0 ? 1 : -1));
      }

      @Override
      public float method1(float value1) {
         if (value1 <= 0.5F) {
            value1 *= 2.0F;
            return (float)Math.pow(this.field35, this.field36 * (value1 - 1.0F)) * MathUtils.sin(value1 * this.field38) * this.field37 * 0.5F;
         } else {
            value1 = 1.0F - value1;
            value1 *= 2.0F;
            return 1.0F - (float)Math.pow(this.field35, this.field36 * (value1 - 1.0F)) * MathUtils.sin(value1 * this.field38) * this.field37 * 0.5F;
         }
      }

      @Override
      public double method2(double value1) {
         if (value1 <= 0.5) {
            value1 *= 2.0;
            return Math.pow(this.field35, this.field36 * (value1 - 1.0)) * MathUtils.sin(value1 * this.field38) * this.field37 * 0.5;
         } else {
            value1 = 1.0 - value1;
            value1 *= 2.0;
            return 1.0 - Math.pow(this.field35, this.field36 * (value1 - 1.0)) * MathUtils.sin(value1 * this.field38) * this.field37 * 0.5;
         }
      }
   }

   public static class ElasticOut extends Easing.ElasticInOut {
      public ElasticOut(float value1, float value2, int number3, float value4) {
         super(value1, value2, number3, value4);
      }

      @Override
      public float method1(float value1) {
         if (value1 == 0.0F) {
            return 0.0F;
         }

         value1 = 1.0F - value1;
         return 1.0F
            - (float)Math.pow(this.IRCCCICCIOCCRIOHIHHCOOIROIIIHR, this.RIIOIRHRROHOIICCRRHOHOORICIOHH * (value1 - 1.0F))
               * MathUtils.sin(value1 * this.RRHOIRCOIHICRHIICIROIOOIICICHI)
               * this.CRIIOHHRIOCICCOOHCRIHHRRRHRCRH;
      }

      @Override
      public double method2(double value1) {
         if (value1 == 0.0) {
            return 0.0;
         }

         value1 = 1.0 - value1;
         return 1.0
            - Math.pow(this.IRCCCICCIOCCRIOHIHHCOOIROIIIHR, this.RIIOIRHRROHOIICCRRHOHOORICIOHH * (value1 - 1.0))
               * MathUtils.sin(value1 * this.RRHOIRCOIHICRHIICIROIOOIICICHI)
               * this.CRIIOHHRIOCICCOOHCRIHHRRRHRCRH;
      }

      @Override
      protected double method5() {
         return 0.999999;
      }
   }

   public static class BackInOut extends Easing {
      final float field35;

      public BackInOut(float value1) {
         this.field35 = value1 * 2.0F;
      }

      @Override
      public float method1(float value1) {
         value1 *= 2.0F;
         if (value1 <= 1.0F) {
            return value1 * value1 * ((this.field35 + 1.0F) * value1 - this.field35) * 0.5F;
         }

         value1 -= 2.0F;
         return value1 * value1 * ((this.field35 + 1.0F) * value1 + this.field35) * 0.5F + 1.0F;
      }

      @Override
      public double method2(double value1) {
         value1 *= 2.0;
         if (value1 <= 1.0) {
            return value1 * value1 * ((this.field35 + 1.0F) * value1 - this.field35) * 0.5;
         }

         value1 -= 2.0;
         return value1 * value1 * ((this.field35 + 1.0F) * value1 + this.field35) * 0.5 + 1.0;
      }
   }

   public static class BackOut extends Easing.BackInOut {
      public BackOut(float value1) {
         super(value1);
      }

      @Override
      public float method1(float value1) {
         return --value1 * value1 * ((this.RICCHRIHCCHIIRHIOOOCHICCORROOO + 1.0F) * value1 + this.RICCHRIHCCHIIRHIOOOCHICCORROOO) + 1.0F;
      }

      @Override
      public double method2(double value1) {
         return --value1 * value1 * ((this.RICCHRIHCCHIIRHIOOOCHICCORROOO + 1.0F) * value1 + this.RICCHRIHCCHIIRHIOOOCHICCORROOO) + 1.0;
      }

      @Override
      protected double method5() {
         return 0.75;
      }
   }

   public static class BackIn extends Easing.BackInOut {
      public BackIn(float value1) {
         super(value1);
      }

      @Override
      public float method1(float value1) {
         return value1 * value1 * ((this.RICCHRIHCCHIIRHIOOOCHICCORROOO + 1.0F) * value1 - this.RICCHRIHCCHIIRHIOOOCHICCORROOO);
      }

      @Override
      public double method2(double value1) {
         return value1 * value1 * ((this.RICCHRIHCCHIIRHIOOOCHICCORROOO + 1.0F) * value1 - this.RICCHRIHCCHIIRHIOOOCHICCORROOO);
      }

      @Override
      protected double method5() {
         return 0.25;
      }
   }
}
