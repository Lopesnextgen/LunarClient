package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.client.guiRewindhandlers.Annotation2;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers2;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public interface TrackedValue<T> extends GuiRewindhandlers2 {
   static <T> TrackedValue<T> method1(DynamicListener var0, T var1) {
      return new TrackedValueImpl<>(var0, (T)var1);
   }

   static <T> TrackedValue<T> method2(T var0) {
      return new TrackedValueImpl<>((T)var0);
   }

   void set(T var1);

   @Annotation2(method1 = Annotation2.Type.DYNAMICLISTENER_ISENABLED)
   T get();
}
