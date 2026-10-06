package com.moonsworth.lunar.client.framework.feature.momentum;

import java.text.DecimalFormat;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   NEAREST("nearest", new DecimalFormat("#")),
   DECIMAL_1("1Decimal", new DecimalFormat("#.#")),
   DECIMAL_2("2Decimal", new DecimalFormat("#.##")),
   DECIMAL_3("3Decimal", new DecimalFormat("#.###"));

   private final String id;
   private final DecimalFormat format;

   Gui2Extension(String text, DecimalFormat decimalFormat) {
      this.id = text;
      this.format = decimalFormat;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   public String id() {
      return this.id;
   }

   public String format(double value) {
      return this.format.format(value);
   }
}
