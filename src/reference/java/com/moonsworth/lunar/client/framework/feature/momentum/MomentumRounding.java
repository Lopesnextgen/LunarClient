package com.moonsworth.lunar.client.framework.feature.momentum;

import java.text.DecimalFormat;

public enum MomentumRounding implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   NEAREST("nearest", new DecimalFormat("#")),
   DECIMAL_1("1Decimal", new DecimalFormat("#.#")),
   DECIMAL_2("2Decimal", new DecimalFormat("#.##")),
   DECIMAL_3("3Decimal", new DecimalFormat("#.###"));

   private final String id;
   private final DecimalFormat format;

   MomentumRounding(String text3, DecimalFormat decimalformat4) {
      this.id = text3;
      this.format = decimalformat4;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   public String id() {
      return this.id;
   }

   public String format(double value1) {
      return this.format.format(value1);
   }
}
