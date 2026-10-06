package com.moonsworth.lunar.client.inactive;

import com.moonsworth.lunar.MixinHelper102_4;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper73_3;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

class Inactive3$Data5 extends MixinHelper102_4<OffsetDateTime> {
   private Inactive3$Data5() {
   }

   public OffsetDateTime method1(MixinHelper53 var1, MixinHelper73_3 var2) {
      return ZonedDateTime.from(Inactive3_2.field1.parse(var1.getText())).toOffsetDateTime();
   }
}
