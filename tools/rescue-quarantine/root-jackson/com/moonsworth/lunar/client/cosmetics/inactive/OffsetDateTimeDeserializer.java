package com.moonsworth.lunar.client.cosmetics.inactive;

import com.moonsworth.lunar.MixinHelper102_4;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper73_3;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

class OffsetDateTimeDeserializer extends MixinHelper102_4<OffsetDateTime> {
   private OffsetDateTimeDeserializer() {
   }

   public OffsetDateTime method1(MixinHelper53 mixinhelper531, MixinHelper73_3 mixinhelper73_32) {
      return ZonedDateTime.from(CosmeticDefinitionMapper.field1.parse(mixinhelper531.getText())).toOffsetDateTime();
   }
}
