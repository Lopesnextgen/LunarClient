package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.FeatureLifecycleManager;
import com.moonsworth.lunar.client.framework.listener.GuiRewindhandlers;
import com.moonsworth.lunar.client.event.EventBusAccess;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
import org.jetbrains.annotations.VisibleForTesting;
import org.jetbrains.annotations.ApiStatus.Internal;
import com.moonsworth.lunar.client.command.ClientCommand;

public interface ModLifecycle extends EventBusAccess {
   void method1(Framework7Extension framework7extension1, boolean flag2, boolean flag3);

   @Internal
   void method2(GuiRewindhandlers guirewindhandlers1);

   int method3();

   void method4(Framework7Extension framework7extension1);

   void method5(Framework7Extension framework7extension1);

   @Internal
   boolean method6();

   @Internal
   void method7(DynamicCondition framework111, @Nullable ModChildren alertextension2, @Nullable ChildModBinding framework43);

   void method8(ClientCommand mixinnameplate21);

   void method9(Runnable runnable1);

   void method10(Runnable runnable1);

   void method11(Runnable runnable1);

   @TestOnly
   @VisibleForTesting
   boolean method12();

   static ModLifecycle method13() {
      return new FeatureLifecycleManager();
   }
}
