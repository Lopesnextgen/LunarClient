package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 1)
public class PanelRenderer extends GuiComponent {
   public PanelRenderer(GuiRenderer mixinhelper1) {
      super(mixinhelper1);
   }

   public int method1(String text1, int number2, int number3, int number4, int number5) {
      boolean flag6 = this.HRROORRCRHHHCCIORROORCIHOHRIHH.method42(number2 - 2, number3 - 2, number4 + 4, number5 + 4);
      byte number7 = 0;
      if (this.method12().method17() && flag6) {
         if (this.method1().field7) {
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2 - 2, number3 - 2, number4 + 4, number5 + 4, this.getTheme().panelBorderOut);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2 + number4, number3 - 1, 1, number5 + 1, this.getTheme().panelBorderLeft);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2 - 1, number3 + number5, number4 + 2, 1, this.getTheme().panelBorderLeft);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2 - 1, number3 - 1, number4 + 2, 1, this.getTheme().panelBorderRight);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2 - 1, number3 - 1, 1, number5 + 2, this.getTheme().panelBorderRight);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2, number3, number4, number5, this.getTheme().insetBackground);
         }

         number7 = 1;
      } else {
         this.HRROORRCRHHHCCIORROORCIHOHRIHH.method37(number2, number3, number4, number5);
      }

      if (text1 != null) {
         float value8 = this.HRROORRCRHHHCCIORROORCIHOHRIHH.method51(text1, number4, number5);
         this.HRROORRCRHHHCCIORROORCIHOHRIHH.method23(text1, number2 + number4 / 2 + number7 - 1, number3 + number5 / 2 + number7 - 1, false, value8);
      }

      return this.HRROORRCRHHHCCIORROORCIHOHRIHH.method41(number2 - 2, number3 - 2, number4 + 4, number5 + 4);
   }

   public int method2(String text1, int number2, int number3, int number4, int number5, int number6) {
      boolean flag7 = this.HRROORRCRHHHCCIORROORCIHOHRIHH.method42(number2 - 2, number3 - 2, number4 + 4, number5 + 4);
      byte number8 = 0;
      if (this.method12().method17() && flag7) {
         if (this.method1().field7) {
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2 - 2, number3 - 2, number4 + 4, number5 + 4, this.getTheme().panelBorderOut);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2 + number4, number3 - 1, 1, number5 + 1, this.getTheme().panelBorderLeft);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2 - 1, number3 + number5, number4 + 2, 1, this.getTheme().panelBorderLeft);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2 - 1, number3 - 1, number4 + 2, 1, this.getTheme().panelBorderRight);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2 - 1, number3 - 1, 1, number5 + 2, this.getTheme().panelBorderRight);
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2, number3, number4, number5, this.getTheme().insetBackground);
         }

         number8 = 1;
      } else {
         this.HRROORRCRHHHCCIORROORCIHOHRIHH.method37(number2, number3, number4, number5);
      }

      if (text1 != null) {
         float value9 = this.HRROORRCRHHHCCIORROORCIHOHRIHH.method51(text1, number4 - 2, number5);
         if (number6 < 2) {
            number6 = 2;
         }

         this.HRROORRCRHHHCCIORROORCIHOHRIHH.method25(text1, number2 + number6 + number8 - 1, (int)(number3 + number5 / 2 + number8 - 2 - 2.0F * value9), false, value9);
      }

      return this.HRROORRCRHHHCCIORROORCIHOHRIHH.method41(number2 - 2, number3 - 2, number4 + 4, number5 + 4);
   }
}
