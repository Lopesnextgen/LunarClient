package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.util.math.MathUtils;
import lombok.Generated;

public class KeystrokeAnimation {
   private final KeystrokeTimer field1;
   private final KeystrokeRenderer field2;
   private final EasingFunction field3;

   public KeystrokeAnimation(KeystrokeTimer keystrokes21, KeystrokeRenderer keystrokes32, EasingFunction function) {
      this.field1 = keystrokes21;
      this.field2 = keystrokes32;
      this.field3 = function;
   }

   public void method1(MixinCore9Extension mixinCore9, int value, MixinHelper_4 mixinhelper_43) {
      float value4 = this.field3.compute(MathUtils.method1(this.field1.method1(), 0.0F, 1.0F));
      this.field2.method1(mixinCore9, value4, value, mixinhelper_43);
   }

   @Generated
   public KeystrokeTimer method2() {
      return this.field1;
   }
}
