package com.moonsworth.lunar.client.guiRewindhandlers;

import com.moonsworth.lunar.client.event.input.InputActionLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButtonLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import java.util.function.LongPredicate;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class GuiRewindhandlersHandler22 extends DynamicListener {
   private final LongList field7 = new LongArrayList();
   private final LongList field8 = new LongArrayList();
   private final LongList field9 = new LongArrayList();
   private static final LongPredicate field10 = var0 -> var0 < System.currentTimeMillis() - 1000L;

   public GuiRewindhandlersHandler22() {
      this.handle(EventClientTick.class, this::method1);
      this.handle(EventMouseButtonLegacy.class, this::method2);
   }

   private void method1(EventClientTick var1) {
      this.field7.removeIf(field10);
      this.field8.removeIf(field10);
      this.field9.removeIf(field10);
   }

   private void method2(EventMouseButtonLegacy var1) {
      if (ThreadModuleDump63.method3().bridge$getCurrentScreen() == null && var1.method4() == InputActionLegacy.DOWN) {
         long var2 = System.currentTimeMillis();
         if (var1.method2() == 0) {
            this.field7.add(var2);
            if (ThreadModuleDump63.method7() != null && !ThreadModuleDump63.method7().bridge$isUsingItem()) {
               this.field8.add(var2);
            }
         }

         if (var1.method2() == 1) {
            this.field9.add(var2);
         }
      }
   }

   public int method3(boolean var1, boolean var2) {
      return var1 ? this.method5(var2) : this.method8();
   }

   public void method5() {
      this.field7.clear();
   }

   public int method5(boolean var1) {
      return var1 ? this.field8.size() : this.field7.size();
   }

   public int method6() {
      return this.field8.size();
   }

   public void method7() {
      this.field9.clear();
   }

   public int method8() {
      return this.field9.size();
   }
}
