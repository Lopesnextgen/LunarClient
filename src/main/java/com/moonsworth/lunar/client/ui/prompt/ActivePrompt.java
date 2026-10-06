package com.moonsworth.lunar.client.ui.prompt;

import javax.annotation.Nullable;

public class ActivePrompt {
   @Nullable
   private com.moonsworth.lunar.client.ui.prompt.PromptAction field1;

   public ActivePrompt() {
   }

   public void method1(com.moonsworth.lunar.client.ui.prompt.PromptAction animations1) {
      this.field1 = animations1;
   }

   public void method2() {
      this.field1 = null;
   }

   public com.moonsworth.lunar.client.ui.prompt.PromptAction method3() {
      return this.field1;
   }
}
