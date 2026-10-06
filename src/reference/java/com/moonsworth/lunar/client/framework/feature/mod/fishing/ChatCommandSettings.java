package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;

public class ChatCommandSettings {
   private final ToggleOption field1;
   private final ToggleOption field2;
   private final MultiSelectOption field3;

   public ChatCommandSettings(ToggleOption option, ToggleOption option2, MultiSelectOption option3) {
      this.field1 = option;
      this.field2 = option2;
      this.field3 = option3;
   }

   public boolean isEnabled() {
      return (Boolean)this.method3().get();
   }

   public boolean method1() {
      return (Boolean)this.method4().get();
   }

   public boolean method2(FishingChatType fishingChatType) {
      return this.method5().contains(fishingChatType.getId());
   }

   public ToggleOption method3() {
      return this.field1;
   }

   public ToggleOption method4() {
      return this.field2;
   }

   public MultiSelectOption method5() {
      return this.field3;
   }
}
