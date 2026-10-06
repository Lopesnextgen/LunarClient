package com.moonsworth.lunar.client.ui.prompt;

import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import net.kyori.adventure.text.Component;

public class MaliciousUrlPrompt extends PromptAction {
   private final String field1;

   @Override
   public void method1(boolean flag) {
      if (flag) {
         Ref.method4().method41().method6().method86().add(this.field1);
      }
   }

   @Override
   public Component method2() {
      TranslationManager foghandler281 = Ref.method4().method67();
      return Component.text(foghandler281.method2("safety.maliciousUrl.prompt", "saveChoice", new Object[]{this.field1}));
   }

   @Generated
   public MaliciousUrlPrompt(String text) {
      this.field1 = text;
   }
}
