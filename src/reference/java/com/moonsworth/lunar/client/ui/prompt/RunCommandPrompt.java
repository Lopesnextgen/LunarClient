package com.moonsworth.lunar.client.ui.prompt;

import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.chat.ChatActionHandler;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import net.kyori.adventure.text.Component;

public class RunCommandPrompt extends PromptAction {
   private final String field1;

   @Override
   public void method1(boolean flag1) {
      String text2 = ChatActionHandler.method7(this.field1);
      if (flag1 && text2 != null) {
         Ref.method4().method41().method6().method87().add(text2);
      }
   }

   @Override
   public void method4(boolean flag1) {
      String text2 = ChatActionHandler.method7("*");
      if (flag1 && text2 != null) {
         Ref.method4().method41().method6().method87().add(text2);
      }
   }

   @Override
   public Component method2() {
      TranslationManager foghandler281 = Ref.method4().method67();
      return Component.text(foghandler281.method2("gui.apollo.button.runCommandPrompt", "saveChoice", new Object[]{this.field1}));
   }

   @Override
   public Component method3() {
      TranslationManager foghandler281 = Ref.method4().method67();
      return Component.text(foghandler281.method2("gui.apollo.button.runCommandPrompt", "saveChoiceAll", new Object[0]));
   }

   @Generated
   public RunCommandPrompt(String text) {
      this.field1 = text;
   }
}
