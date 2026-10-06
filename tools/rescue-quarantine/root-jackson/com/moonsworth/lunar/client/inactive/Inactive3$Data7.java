package com.moonsworth.lunar.client.inactive;

import com.moonsworth.lunar.MixinHelper102_4;
import com.moonsworth.lunar.MixinHelper27;
import com.moonsworth.lunar.MixinHelper27$Data3;
import com.moonsworth.lunar.MixinHelper312;
import lombok.Generated;

abstract class Inactive3$Data7<T> extends MixinHelper102_4<T> {
   private final MixinHelper27$Data3 field1;
   private final MixinHelper312 field2;

   protected MixinHelper27 method1() {
      return ((MixinHelper27$Data3)this.field1.method5(this.field2)).method1();
   }

   @Generated
   public Inactive3$Data7(MixinHelper27$Data3 var1, MixinHelper312 var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
