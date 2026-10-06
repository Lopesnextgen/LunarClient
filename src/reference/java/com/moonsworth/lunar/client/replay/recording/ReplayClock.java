package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.horsestats.TimerBridge;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.DoubleOption;
import com.moonsworth.lunar.client.config.option.DoubleOption.Data;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class ReplayClock {
   public static final int field1 = 50;
   private long field2;
   private long field3;
   private long time;
   private long field4;
   private DoubleOption field5 = (DoubleOption)((Data)((Data)((Data)OptionFactory.method1("speed").OIRHOOIICOCIOOHICRRRICORIHHIHC(1.0))
            .RIIIOHCCHRRRORICCHIIHHOORIIOIR(0.1, 10.0))
         .RHIRRICCRHHHIIHHIHHOHRCHIOORCC(true, false))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private ToggleOption field6 = (ToggleOption)OptionFactory.method7("freeze").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private long field7;
   private long field8;
   private long field9;
   private long field10;
   private long field11;
   private boolean field12;
   private boolean field13;
   private boolean field14;
   private boolean field15 = true;
   private boolean field16 = false;

   public ReplayClock() {
      this.reset();
   }

   public void reset() {
      this.field2 = Ref.method3().bridge$getRealSystemTime();
      this.field3 = this.field2;
      this.time = this.field3;
      this.field4 = 0L;
      this.field5.reset();
      this.field8 = 0L;
      this.field9 = 0L;
      this.field10 = 0L;
      this.field11 = 50L;
      this.field12 = false;
      this.field13 = false;
      this.field14 = false;
      this.field15 = true;
      this.field16 = false;
      Ref.method3().bridge$getTimer().bridge$setLastMS(this.time);
      Ref.method3().bridge$getTimer().method2(0.0F);
   }

   public void update() {
      long number1 = Ref.method3().bridge$getRealSystemTime();
      if (!this.method2()) {
         this.field3 = number1;
      } else {
         long number3 = number1 - this.field3;
         if (number3 > 0L) {
            this.time += number3;
            this.field8 -= number3;
            this.field11 -= number3;
            this.field3 = number1;
            this.field16 = true;
         }
      }
   }

   public void method1() {
      if (this.field16) {
         this.field2 = this.time;
         this.field16 = false;
      }
   }

   public boolean method2() {
      return Ref.method3().bridge$areResourcesLoaded() && !Ref.method3().bridge$hasOverlay();
   }

   public void method3(long number1) {
      boolean flag3 = number1 >= 0L;
      number1 = flag3 ? number1 : -number1;
      if (flag3 != this.field15) {
         TimerBridge horsestats284 = Ref.method3().bridge$getTimer();
         int number5 = (int)(horsestats284.method1() * 50.0F);
         int number6 = Math.min(number5, (int)Math.min(number1, 50L));
         horsestats284.method2((number5 - number6) / 50.0F);
         number1 -= number6;
         if (!flag3) {
            this.field4 += number6;
         } else {
            this.time += number6;
            horsestats284.bridge$setLastMS(this.time);
         }

         if (horsestats284.method1() > 0.0F) {
            return;
         }

         this.field13 = !flag3;
         LunarEventBus.method29().method12(EventTick.class, EventTick::new);
         LunarEventBus.method29().method12(EventTick.class, EventTick::new);
      }

      if (flag3) {
         this.field8 = number1;
      } else {
         this.field9 = number1;
      }

      this.field10 = number1;
      this.field15 = flag3;
      this.field11 = 50L;
      this.field3 = Ref.method3().bridge$getRealSystemTime();
      this.field14 = false;
   }

   public void method4(RewindHandlers rewindhandlers1) {
      if (this.method5()) {
         boolean flag2 = this.method12();
         long number3 = Math.min(this.field11, flag2 ? this.field9 : this.field8);
         this.time += number3;
         if (flag2) {
            this.field4 += number3 * 2L;
         }

         this.field3 = Ref.method3().bridge$getRealSystemTime();
         if (flag2) {
            this.field9 -= number3;
            if (this.field9 <= 0L) {
               this.field13 = true;
            }
         } else {
            this.field8 -= number3;
            this.field13 = false;
         }

         this.field11 = 50L;
         this.field14 = true;
         if (!this.method5()) {
            this.field12 = true;
            Ref.method3().bridge$getTimer().bridge$setLastMS(this.time - number3);
            ReplayTimeline highlight_35 = ((ReplayContext)rewindhandlers1.method42().get()).method4();
            if (highlight_35 != null && number3 == this.field11) {
               highlight_35.method6();
            }
         }
      }
   }

   public boolean method5() {
      return this.field8 > 0L || this.field9 > 0L;
   }

   public boolean method6() {
      return this.method5() || this.field12;
   }

   public boolean method7() {
      return this.method12() || this.field13;
   }

   public long method8() {
      return this.time - this.field2 - this.field4;
   }

   public long method9() {
      return this.time + this.field8 - this.field9 - this.field2 - this.field4;
   }

   public void method10() {
      this.field7 = this.time;
   }

   public void method11(long number1) {
      this.field2 -= number1;
   }

   public float getPartialTick() {
      return Math.max(0.0F, Math.min(1.0F, (float)(this.time - this.field7) / 50.0F));
   }

   public boolean method12() {
      return this.field9 > 0L;
   }

   public float method13() {
      if (this.field10 <= 0L) {
         return 1.0F;
      }

      long number1 = this.field10;
      long number3 = this.method12() ? this.field9 : this.field8;
      return 1.0F - (float)number3 / (float)number1;
   }

   @Generated
   public long getTime() {
      return this.time;
   }

   @Generated
   public void method14(DoubleOption lightingextension4521) {
      this.field5 = lightingextension4521;
   }

   @Generated
   public DoubleOption method15() {
      return this.field5;
   }

   @Generated
   public void method16(ToggleOption lightingextension4431) {
      this.field6 = lightingextension4431;
   }

   @Generated
   public ToggleOption method17() {
      return this.field6;
   }

   @Generated
   public long method18() {
      return this.field8;
   }

   @Generated
   public long method19() {
      return this.field10;
   }

   @Generated
   public void method20(boolean flag1) {
      this.field12 = flag1;
   }

   @Generated
   public void method21(boolean flag1) {
      this.field13 = flag1;
   }

   @Generated
   public boolean method22() {
      return this.field14;
   }
}
