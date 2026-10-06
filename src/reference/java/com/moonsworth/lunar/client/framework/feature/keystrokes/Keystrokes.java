package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import lombok.Generated;

public class Keystrokes {
   private final Keystrokes2 field1;
   private final Keystrokes3 field2;
   private final Gui2Extension2 field3;

   public Keystrokes(Keystrokes2 var1, Keystrokes3 var2, Gui2Extension2 var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   public void method1(MixinCore9Extension var1, int var2, MixinHelper_4 var3) {
      float var4 = this.field3.compute(ThreadModuleDump67.method1(this.field1.method1(), 0.0F, 1.0F));
      this.field2.method1(var1, var4, var2, var3);
   }

   @Generated
   public Keystrokes2 method2() {
      return this.field1;
   }
}
