package com.moonsworth.lunar.client.framework.feature.f3display.chart;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.f3display.F3DebugRenderer;
import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule;
import com.moonsworth.lunar.client.mod.hud.f3display.F3Display.Data;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.function.Consumer;

public abstract class F3Chart {
   public static final int field1 = 242;
   public static final int field2 = 52;
   private final int[] values;
   private final String label;
   protected int field3 = 0;
   protected int field4 = 0;
   protected String field5;
   private long field6 = 0L;
   private int valueIndex = -1;
   private boolean field7 = false;

   public F3Chart(int index1, String text2) {
      this.values = new int[index1];
      this.label = text2;
   }

   public void method1() {
      this.valueIndex = -1;
      this.field7 = false;
      this.field4 = 0;
      this.field6 = 0L;
   }

   public void forEach(Consumer<Integer> consumer1) {
      for (int index2 = this.valueIndex; index2 >= 0; index2--) {
         consumer1.accept(this.values[index2]);
      }

      if (this.field7) {
         for (int index3 = this.values.length - 1; index3 > this.valueIndex; index3--) {
            consumer1.accept(this.values[index3]);
         }
      }
   }

   public void method2(int number1) {
      this.valueIndex++;
      if (this.valueIndex >= this.values.length) {
         this.valueIndex = 0;
         this.field7 = true;
      }

      if (this.field7) {
         int number2 = this.values[this.valueIndex];
         this.field6 -= number2;
         if (number2 == this.field4 && number1 < this.field4) {
            this.method4(this.valueIndex);
         }
      }

      this.values[this.valueIndex] = number1;
      this.field6 += number1;
      if (number1 > this.field4) {
         this.field4 = number1;
      }
   }

   protected void method3(int number1, String text2) {
      if (Ref.MC_VERSION < number1) {
         this.field5 = text2;
      }
   }

   private void method4(int number1) {
      int number2 = 0;

      for (int index3 = 0; index3 < this.values.length; index3++) {
         if (index3 != number1 && this.values[index3] > number2) {
            number2 = this.values[index3];
         }
      }

      this.field4 = number2;
   }

   public int method5() {
      return this.field7 ? this.values.length : this.valueIndex + 1;
   }

   public double method6() {
      int number1 = this.method5();
      return number1 == 0 ? 0.0 : (double)this.field6 / number1;
   }

   public int method7() {
      return this.valueIndex == -1 ? 0 : this.values[this.valueIndex];
   }

   protected int[] method8() {
      return null;
   }

   public int method9() {
      return Math.max(this.field4, this.field3);
   }

   public boolean method10() {
      return this.field5 != null;
   }

   public abstract String method11(int number1);

   protected int method12(int number1, F3DisplayModule f3modulechildmod2) {
      return f3modulechildmod2.getDisplayOptions().field5.method14(0.0F);
   }

   public boolean method13(F3DebugRenderer f3display21, F3DisplayModule f3modulechildmod2, float value, float value2, boolean flag) {
      if (this.method10() && !flag) {
         return false;
      }

      byte number6 = 50;
      short number7 = 240;
      value++;
      value2++;
      Data data8 = f3modulechildmod2.getDisplayOptions();
      int number9 = data8.field4.method14(0.0F);
      int number10 = f3modulechildmod2.getDisplayOptions().field5.method14(0.0F);
      MixinHelper_4 mixinhelper_411 = f3display21.method13();
      this.method14(mixinhelper_411, (int)value - 1, (int)value2 - 1, number7 + 2, number6 + 2, number9);
      int number12 = number7 / this.values.length;
      int number13 = this.method9();
      if (!this.method10()) {
         int[] items14 = new int[this.method5()];
         int index15 = items14.length;

         for (int index16 = this.valueIndex; index16 >= 0; index16--) {
            items14[--index15] = this.values[index16];
         }

         if (this.field7) {
            for (int index34 = this.values.length - 1; index34 > this.valueIndex; index34--) {
               items14[--index15] = this.values[index34];
            }
         }

         int number35 = (int)(value + number7 - items14.length * number12);
         mixinhelper_411.method33(number35, value2, number12, number6, number10, items14, number13);
      }

      Bridge10_2 bridge10_232 = Ref.method10();
      boolean flag33 = (Boolean)data8.field1.get();
      if (this.label != null && !this.label.isEmpty()) {
         float value36 = bridge10_232.bridge$getStringWidth(this.label);
         mixinhelper_411.method22(bridge10_232, this.label, (int)(value + number7 - value36 - 1.0F), (int)(value2 + 1.0F), number9, flag33);
      }

      if (this.method10()) {
         value += 121.0F;
         value2 += 26.0F;
         String text38 = "This chart is not available for";
         String text39 = "versions lower than " + this.field5;
         int number40 = bridge10_232.method19();
         value2 -= number40 / 2.0F + 1.0F;
         mixinhelper_411.method30(bridge10_232, text38, (int)value, (int)value2, -43691, flag33);
         value2 += number40;
         mixinhelper_411.method30(bridge10_232, text39, (int)value, (int)value2, -43691, flag33);
         return true;
      }

      String text37 = this.method11(number13);
      mixinhelper_411.method22(bridge10_232, text37, (int)value + 1, (int)value2 + 1, number9, flag33);
      int[] items17 = this.method8();
      if (items17 == null) {
         return true;
      }

      for (int index22 : items17) {
         double value23 = (double)index22 / number13;
         float value18 = (float)(value2 + number6 - value23 * number6);
         mixinhelper_411.method1((int)value, (int)value18, (int)(value + number7), (int)(value18 + 1.0F), number9);
         String text25 = this.method11(index22);
         mixinhelper_411.method22(bridge10_232, text25, (int)value + 1, (int)value18 + 2, number9, flag33);
      }

      return true;
   }

   private void method14(MixinHelper_4 mixinhelper_41, int number2, int value, int value2, int value3, int number6) {
      mixinhelper_41.method1(number2, value, number2 + value2, value + 1, number6);
      mixinhelper_41.method1(number2, value, number2 + 1, value + value3, number6);
      mixinhelper_41.method1(number2 + value2 - 1, value, number2 + value2, value + value3, number6);
      mixinhelper_41.method1(number2, value + value3 - 1, number2 + value2, value + value3, number6);
   }
}
