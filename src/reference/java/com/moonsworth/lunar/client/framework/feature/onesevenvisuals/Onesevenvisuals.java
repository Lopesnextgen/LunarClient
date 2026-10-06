package com.moonsworth.lunar.client.framework.feature.onesevenvisuals;

import com.moonsworth.lunar.client.event.render.EventEyeHeight;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class Onesevenvisuals {
   private final ToggleOption field1;
   private final ToggleOption field2;
   private float field3 = 1.0F;
   private boolean field4 = false;
   private float field5;
   private float field6;
   private int field7;
   private float field8;
   private float field9;
   private float height;

   public void method1() {
      this.field9 = this.height;
      if (this.height != this.field8) {
         float value1 = this.field8 - this.height;
         float value2 = this.field4 ? 1.0F : (Ref.MC_VERSION > 5 ? 0.5F : 0.6F);
         this.height = this.height + value1 * value2 * this.method2();
         if (this.field9 > this.height && this.height < this.field8 || this.height > this.field9 && this.height > this.field8) {
            this.height = this.field8;
         }
      }
   }

   private float method2() {
      float value1 = Math.max(0.1F, this.field3);
      if (Ref.MC_VERSION > 5) {
         value1 *= Math.max(0.1F, (Float)Ref.method4().method41().method6().method67().get());
      }

      return value1;
   }

   public void method3(EventEyeHeight event) {
      if ((Boolean)this.field1.get()) {
         this.field4 = Ref.method3().bridge$getGameSettings().bridge$keyBindSneak().bridge$isKeyDown();
         if (this.field7 >= 5 && !this.field4 && !Ref.method7().bridge$isSneaking()) {
            this.field5 = event.getEyeHeight();
         }

         if (this.field6 == event.getEyeHeight()) {
            this.field7++;
         } else {
            this.field6 = event.getEyeHeight();
            this.field7 = 0;
         }

         if (this.field4) {
            float value2 = Ref.MC_VERSION > 5 && this.field2.get() ? 0.09F : event.method2();
            this.field8 = this.field5 - value2;
         } else {
            this.field8 = this.field5;
         }

         if (Ref.MC_VERSION <= 5 || Ref.method7().bridge$canEnterStandingPose()) {
            float value4 = this.height;
            if (Ref.MC_VERSION <= 5) {
               float value3 = Ref.method3().bridge$getTimer().method1();
               value4 = this.field9 + (this.height - this.field9) * value3;
            }

            event.method1(value4);
         }
      }
   }

   @Generated
   public Onesevenvisuals(ToggleOption option, ToggleOption option2) {
      this.field1 = option;
      this.field2 = option2;
   }

   @Generated
   public void method4(float value1) {
      this.field3 = value1;
   }
}
