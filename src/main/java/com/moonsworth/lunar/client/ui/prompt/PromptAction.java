package com.moonsworth.lunar.client.ui.prompt;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public abstract class PromptAction {
   public PromptAction() {
   }

   public abstract void method1(boolean flag1);

   public abstract Component method2();

   @Nullable
   public Component method3() {
      return null;
   }

   public void method4(boolean flag1) {
   }
}
