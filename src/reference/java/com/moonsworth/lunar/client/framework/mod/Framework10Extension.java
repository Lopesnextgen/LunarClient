package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.NameplateTask;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers;
import com.moonsworth.lunar.client.event.EventRegistrar;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
import org.jetbrains.annotations.VisibleForTesting;
import org.jetbrains.annotations.ApiStatus.Internal;
import com.moonsworth.lunar.client.command.MixinNameplate2;

public interface Framework10Extension extends EventRegistrar {
   void method1(Framework7Extension var1, boolean var2, boolean var3);

   @Internal
   void method2(GuiRewindhandlers var1);

   int method3();

   void method4(Framework7Extension var1);

   void method5(Framework7Extension var1);

   @Internal
   boolean method6();

   @Internal
   void method7(Framework11 var1, @Nullable AlertExtension var2, @Nullable Framework4 var3);

   void method8(MixinNameplate2 var1);

   void method9(Runnable var1);

   void method10(Runnable var1);

   void method11(Runnable var1);

   @TestOnly
   @VisibleForTesting
   boolean method12();

   static Framework10Extension method13() {
      return new NameplateTask();
   }
}
