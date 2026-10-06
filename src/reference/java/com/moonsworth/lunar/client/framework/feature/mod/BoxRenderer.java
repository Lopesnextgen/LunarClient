package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.ui.GuiClipState;
import com.moonsworth.lunar.client.util.math.IntRectangle;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 1)
public class BoxRenderer extends GuiComponent {
   public BoxRenderer(GuiRenderer mixinhelper1) {
      super(mixinhelper1);
   }

   public void method1(int number1, int number2, int number3, int number4) {
      if (number3 >= -4 && number4 >= -4) {
         if (this.method1().field7) {
            GuiClipState.field2 = IntRectangle.method3(number1 - 2, number2 - 2, number3 + 4, number4 + 4)
               .method6(this.method1().field9, this.method1().field10);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1 - 2, number2 - 2, number3 + 4, number4 + 4, this.getTheme().panelBorderOut);
            GuiClipState.field1 = false;
            GuiClipState.field3 = true;
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1 - 1, number2 - 1, number3 + 1, 1, this.getTheme().panelBorderLeft);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1 - 1, number2 - 1, 1, number4 + 1, this.getTheme().panelBorderLeft);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1 + number3, number2 - 1, 1, number4 + 1, this.getTheme().panelBorderRight);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1 - 1, number2 + number4, number3 + 2, 1, this.getTheme().panelBorderRight);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1, number2, number3, number4, this.getTheme().panelBackground);
            GuiClipState.reset();
         }
      }
   }

   public void drawInsetBox(int number1, int number2, int number3, int number4) {
      if (number3 >= -4 && number4 >= -4) {
         if (this.method1().field7) {
            GuiClipState.field2 = IntRectangle.method3(number1 - 1, number2 - 1, number3 + 2, number4 + 2)
               .method6(this.method1().field9, this.method1().field10);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1, number2, number3, number4, this.getTheme().insetBackground);
            GuiClipState.field1 = false;
            GuiClipState.field3 = true;
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1 - 1, number2 - 1, number3 + 2, 1, this.getTheme().insetBorderLeft);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1 - 1, number2, 1, number4 + 1, this.getTheme().insetBorderLeft);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1 + number3, number2 - 1, 1, number4 + 2, this.getTheme().insetBorderRight);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1 - 1, number2 + number4, number3 + 1, 1, this.getTheme().insetBorderRight);
            GuiClipState.reset();
         }
      }
   }

   public void drawInsetBoxFlat(int number1, int number2, int number3, int number4) {
      if (number3 >= -4 && number4 >= -4) {
         if (this.method1().field7) {
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1, number2, number3, number4, this.getTheme().insetBackground);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1 - 1, number2 - 1, number3 + 2, 1, this.getTheme().insetBorderLeft);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1 - 1, number2, 1, number4 + 1, this.getTheme().insetBorderLeft);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1 + number3, number2 - 1, 1, number4 + 2, this.getTheme().insetBorderRight);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1 - 1, number2 + number4, number3 + 1, 1, this.getTheme().insetBorderRight);
         }
      }
   }

   public void drawSeparator(int number1, int number2, int number3) {
      if (this.method1().field7) {
         this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number1, number2, number3, 1, this.getTheme().insetBorderLeft);
      }
   }
}
