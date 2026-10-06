package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerTick;
import com.moonsworth.lunar.client.util.collection.AbstractQueueImpl;
import com.moonsworth.lunar.client.framework.Ref;
import java.text.DecimalFormat;
import lombok.Generated;

public class TpsTracker extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final String field7 = "TPS: Loading...";
   private static final DecimalFormat field8 = new DecimalFormat("#0.#");
   private final AbstractQueueImpl<Integer> field9 = new AbstractQueueImpl(30);
   private int count;
   private String field10 = "TPS: Loading...";
   private boolean field11;
   private long field12;

   public TpsTracker() {
      this.handle(EventServerTick.class, this::method4);
      this.handle(EventSecond.class, this::method3);
      this.handle(EventWorld.EventWorldChange.class, this::method1);
   }

   private void method1(EventWorld.EventWorldChange event) {
      this.field11 = true;
      this.field12 = Ref.method3().bridge$getSystemTime() + 5000L;
      this.field10 = "TPS: Loading...";
   }

   public String method2(int value) {
      if (this.field11) {
         if (Ref.method3().bridge$getSystemTime() > this.field12) {
            this.field11 = false;
         }

         return "TPS: Loading...";
      } else {
         int number2 = Math.min(this.field9.size(), value);
         if (number2 > 0) {
            int number3 = this.field9.stream().skip(Math.max(this.field9.size() - value, 0)).mapToInt(Integer::intValue).sum();
            float value4 = ClampUtils.clamp((float)number3 / number2, 0.0F, 20.0F);
            return "TPS: " + field8.format(value4);
         } else {
            return "TPS: Loading...";
         }
      }
   }

   private void method3(EventSecond event) {
      this.field9.offer(this.count);
      this.count = 0;
      this.field10 = this.method2(10);
   }

   private void method4(EventServerTick event) {
      if (!this.field11) {
         this.count++;
      }
   }

   @Generated
   public String method5() {
      return this.field10;
   }
}
