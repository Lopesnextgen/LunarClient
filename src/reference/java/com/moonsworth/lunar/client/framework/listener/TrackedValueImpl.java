package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.client.guiRewindhandlers.Annotation;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation2;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers3;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.mod.misc.debug.DynamiclistenerDebugMod;
import com.moonsworth.lunar.client.util.gui.ListExtension3;
import java.util.List;

public class TrackedValueImpl<T> implements TrackedValue<T> {
   private final List<Runnable> listeners = new ListExtension3<>(0);
   private final DynamicListener field1;
   private T val;

   public TrackedValueImpl(T var1) {
      this.field1 = null;
      this.val = (T)var1;
   }

   public TrackedValueImpl(DynamicListener var1, T var2) {
      this.field1 = var1;
      this.val = (T)var2;
      DynamiclistenerDebugMod.method5(this, var1);
   }

   @Override
   public void set(T var1) {
      ClientEventBus.method29().method24(() -> {
         if (var1 != this.val) {
            this.val = (T)var1;
            this.listeners.forEach(Runnable::run);
         }
      });
   }

   @Annotation(method1 = Annotation2.Type.DYNAMICLISTENER_ISENABLED)
   @Override
   public T get() {
      GuiRewindhandlers3.method1(this);
      return this.val;
   }

   @Override
   public void method1(Runnable var1) {
      this.listeners.add(var1);
      if (this.field1 != null) {
         this.field1.method1();
      }
   }

   @Override
   public void method2(Runnable var1) {
      this.listeners.remove(var1);
      if (this.field1 != null) {
         this.field1.method3();
      }
   }

   public boolean method3() {
      return !this.listeners.isEmpty();
   }
}
