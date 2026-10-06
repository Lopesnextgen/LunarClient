package com.moonsworth.lunar.client.inactive;

import com.moonsworth.lunar.IOException22;
import com.moonsworth.lunar.MixinHelper27$Data3;
import com.moonsworth.lunar.MixinHelper312;
import com.moonsworth.lunar.MixinHelper3234;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper73_3;
import com.moonsworth.lunar.MixinHelper9_9;
import com.moonsworth.lunar.client.inactive.mixin.fishing.Fishing;
import com.moonsworth.lunar.client.inactive.mixin.fishing.FishingType;

class Inactive3$Data6 extends Inactive3$Data7<Fishing> {
   public Inactive3$Data6(MixinHelper27$Data3 var1, MixinHelper312 var2) {
      super(var1, var2);
   }

   public Fishing method1(MixinHelper53 var1, MixinHelper73_3 var2) {
      MixinHelper9_9 var3 = var1.method98();
      if (var3.method7("type") instanceof MixinHelper3234 var4) {
         String var7 = var4.method39();
         Class var6 = FishingType.fromId(var7);
         if (var6 == null) {
            throw new IOException22(var1, "Could not find valid task with id: " + var7);
         } else {
            return Inactive3_2.method3(var1, this.method1().method223(var6).method98(var3.toString()));
         }
      } else {
         throw new IOException22(var1, "Task type must be a string: " + var3.method1().name());
      }
   }
}
