package com.moonsworth.lunar.client.framework.feature.crosshair;

import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairGridSize;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import org.jetbrains.annotations.NotNull;

public class CrosshairPattern {
   private final CrosshairGridSize field1;
   private final boolean[] field2;

   public CrosshairPattern(CrosshairGridSize gui2extension21) {
      this(gui2extension21, new boolean[gui2extension21.size() * gui2extension21.size()]);
   }

   public CrosshairPattern(boolean[] items1) {
      this(CrosshairGridSize.fromGridLength((int)Math.floor(Math.sqrt(items1.length))), items1);
   }

   public CrosshairPattern(CrosshairGridSize gui2extension21, boolean[] items2) {
      this.field1 = gui2extension21;
      this.field2 = items2;
   }

   public static CrosshairPattern method1() {
      return new CrosshairPattern(CrosshairGridSize.MEDIUM);
   }

   public CrosshairPattern method2() {
      boolean[] items1 = new boolean[this.field2.length];
      System.arraycopy(this.field2, 0, items1, 0, items1.length);
      return new CrosshairPattern(this.field1, items1);
   }

   public CrosshairPattern method3(CrosshairGridSize gui2extension21, boolean flag) {
      int index3 = this.method12().size();
      int index4 = gui2extension21.size();
      if (index3 == index4) {
         return this.method2();
      }

      BufferedImage bufferedimage5 = new BufferedImage(index3, index3, 2);

      for (int index6 = 0; index6 < index3; index6++) {
         for (int index7 = 0; index7 < index3; index7++) {
            bufferedimage5.setRGB(index6, index7, this.field2[index6 + index7 * index3] ? -1 : 0);
         }
      }

      BufferedImage bufferedimage11 = new BufferedImage(index4, index4, 2);
      Graphics2D graphics2d12 = bufferedimage11.createGraphics();
      if (flag) {
         graphics2d12.drawImage(bufferedimage5, 0, 0, index4, index4, null);
      } else {
         int number8 = index4 / 2 - index3 / 2;
         graphics2d12.drawImage(bufferedimage5, number8, number8, index3, index3, null);
      }

      graphics2d12.dispose();
      boolean[] items13 = new boolean[index4 * index4];

      for (int index9 = 0; index9 < index4; index9++) {
         for (int index10 = 0; index10 < index4; index10++) {
            items13[index9 + index10 * index4] = bufferedimage11.getRGB(index9, index10) != 0;
         }
      }

      return new CrosshairPattern(items13);
   }

   public boolean isEmpty() {
      for (boolean flag4 : this.field2) {
         if (flag4) {
            return false;
         }
      }

      return true;
   }

   @NotNull
   public String method4() {
      return new Crosshair("LCCH", this.method10(), this.method7()).toString();
   }

   @NotNull
   public static CrosshairPattern method5(String text) {
      Crosshair crosshair1 = Crosshair.method1(text);
      return crosshair1 != null && crosshair1.type().equals("LCCH") ? method6(crosshair1.method2(), crosshair1.method3()) : method1();
   }

   public static CrosshairPattern method6(int value, byte[] items1) {
      CrosshairPattern crosshair22 = new CrosshairPattern(CrosshairGridSize.fromGridLength(value));
      int number3 = crosshair22.field1.size();
      int number4 = crosshair22.method9(value);

      for (int index5 = 0; index5 < value; index5++) {
         for (int index6 = 0; index6 < value; index6++) {
            int index7 = index5 + number4 + (index6 + number4) * number3;
            if (index7 >= 0 && index7 < number3 * number3) {
               int index8 = (index5 + index6 * value) / 8;
               int number9 = 1 << (index5 + index6 * value) % 8;
               crosshair22.field2[index7] = (items1[index8] & number9) != 0;
            }
         }
      }

      return crosshair22;
   }

   public byte[] method7() {
      int number1 = this.field1.size();
      int number2 = this.method10();
      int number3 = this.method9(number2);
      byte[] items4 = new byte[this.method8(number2)];

      for (int index5 = 0; index5 < number2; index5++) {
         for (int index6 = 0; index6 < number2; index6++) {
            int index7 = index6 + number3 + (index5 + number3) * number1;
            if (index7 >= 0 && index7 < number1 * number1 && this.field2[index7]) {
               int index8 = (index6 + index5 * number2) / 8;
               int number9 = 1 << (index6 + index5 * number2) % 8;
               items4[index8] |= (byte)number9;
            }
         }
      }

      return items4;
   }

   public int method8(int number1) {
      return (int)Math.ceil(number1 * number1 / 8.0F);
   }

   public int method9(int number1) {
      return (int)Math.floor((this.field1.size() - number1) / 2.0F);
   }

   public int method10() {
      int number1 = this.field1.size();
      int number2 = 1;

      for (int index3 = 0; index3 < this.field2.length; index3++) {
         if (this.field2[index3]) {
            int number4 = index3 % number1;
            int number5 = index3 / number1;
            number2 = Math.max(number2, this.method11(number4, number1));
            number2 = Math.max(number2, this.method11(number5, number1));
         }
      }

      return number2;
   }

   private int method11(int number1, int number2) {
      int number3 = number1 - number2 / 2;
      return number3 <= 0 ? -number3 * 2 + 1 : number3 * 2;
   }

   public CrosshairGridSize method12() {
      return this.field1;
   }

   public boolean[] method13() {
      return this.field2;
   }
}
