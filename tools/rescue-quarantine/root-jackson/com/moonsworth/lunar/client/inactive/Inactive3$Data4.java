package com.moonsworth.lunar.client.inactive;

import com.moonsworth.lunar.IOException22;
import com.moonsworth.lunar.MixinHelper27$Data3;
import com.moonsworth.lunar.MixinHelper312;
import com.moonsworth.lunar.MixinHelper3234;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper73_3;
import com.moonsworth.lunar.MixinHelper9_9;
import com.moonsworth.lunar.client.inactive.mixin.gui.Gui2;

class Inactive3$Data4 extends Inactive3$Data7<Gui2> {
   public Inactive3$Data4(MixinHelper27$Data3 var1, MixinHelper312 var2) {
      super(var1, var2);
   }

   public Gui2 method1(MixinHelper53 var1, MixinHelper73_3 var2) {
      MixinHelper9_9 var3 = var1.method98();
      if (var3.method7("name") instanceof MixinHelper3234 var4) {
         String var7 = var4.method39();
         Class var6 = Gui2.field1.get(var7);
         if (var6 == null) {
            throw new IOException22(var1, "Could not find valid state machine with name: " + var7);
         } else {
            return Inactive3_2.method3(var1, this.method1().method223(var6).method98(var3.toString()));
         }
      } else {
         throw new IOException22(var1, "GLStateMachine must be a string: " + var3.method1().name());
      }
   }
}
