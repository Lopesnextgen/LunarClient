package com.moonsworth.lunar.ichor;

import java.util.Objects;
import lombok.Generated;

class MixinErrorRow {
   private final String field1;
   private final String field2;
   private final String field3;
   private final String field4;

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      } else {
         return !(object instanceof MixinErrorRow mixinsupport$data2)
            ? false
            : Objects.equals(mixinsupport$data2.field1, this.field1) && Objects.equals(mixinsupport$data2.field2, this.field2) && Objects.equals(mixinsupport$data2.field3, this.field3);
      }
   }

   @Override
   public String toString() {
      return this.field1 + "," + this.field2 + "," + this.field3 + "," + this.field4;
   }

   @Generated
   public MixinErrorRow(String text, String text2, String text3, String text4) {
      this.field1 = text;
      this.field2 = text2;
      this.field3 = text3;
      this.field4 = text4;
   }
}
