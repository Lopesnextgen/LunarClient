package com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers;

import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.gui.ServerTickEvent;
import com.moonsworth.lunar.client.util.collection.AbstractQueueImpl;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.text.DecimalFormat;
import lombok.Generated;

public class GuiRewindhandlersHandler22 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final String field7 = "TPS: Loading...";
   private static final DecimalFormat field8 = new DecimalFormat("#0.#");
   private final AbstractQueueImpl<Integer> field9 = new AbstractQueueImpl<>(30);
   private int count;
   private String field10 = "TPS: Loading...";
   private boolean field11;
   private long field12;

   public GuiRewindhandlersHandler22() {
      this.handle(ServerTickEvent.class, this::method4);
      this.handle(EventEverySecond.class, this::method3);
      this.handle(EventWorldLifecycle.EventWorldChanged.class, this::method1);
   }

   private void method1(EventWorldLifecycle.EventWorldChanged var1) {
      this.field11 = true;
      this.field12 = ThreadModuleDump63.method3().bridge$getSystemTime() + 5000L;
      this.field10 = "TPS: Loading...";
   }

   public String method2(int var1) {
      if (this.field11) {
         if (ThreadModuleDump63.method3().bridge$getSystemTime() > this.field12) {
            this.field11 = false;
         }

         return "TPS: Loading...";
      } else {
         int var2 = Math.min(this.field9.size(), var1);
         if (var2 > 0) {
            int var3 = this.field9.stream().skip(Math.max(this.field9.size() - var1, 0)).mapToInt(Integer::intValue).sum();
            float var4 = ClampUtils.clamp((float)var3 / var2, 0.0F, 20.0F);
            return "TPS: " + field8.format(var4);
         } else {
            return "TPS: Loading...";
         }
      }
   }

   private void method3(EventEverySecond var1) {
      this.field9.offer(this.count);
      this.count = 0;
      this.field10 = this.method2(10);
   }

   private void method4(ServerTickEvent var1) {
      if (!this.field11) {
         this.count++;
      }
   }

   @Generated
   public String method5() {
      return this.field10;
   }
}
