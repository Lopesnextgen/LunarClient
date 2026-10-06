package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.Annotation10;
import com.moonsworth.lunar.Annotation23;
import java.io.IOException;

public enum FormatVersion {
   VERSION_1_12_0,
   VERSION_1_14_0,
   VERSION_1_21_20;

   FormatVersion() {
   }

   @Annotation10
   public String toValue() {
      switch (this) {
         case VERSION_1_12_0:
            return "1.12.0";
         case VERSION_1_14_0:
            return "1.14.0";
         case VERSION_1_21_20:
            return "1.21.20";
         default:
            return null;
      }
   }

   @Annotation23
   public static FormatVersion forValue(String text0) {
      if (text0.equals("1.12.0")) {
         return VERSION_1_12_0;
      } else if (text0.equals("1.14.0")) {
         return VERSION_1_14_0;
      } else if (text0.equals("1.21.20")) {
         return VERSION_1_21_20;
      } else {
         throw new IOException("Cannot deserialize FormatVersion");
      }
   }
}
