package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.JsonConfigurable;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;

public class DefaultedBooleanOption extends EnabledOption implements JsonConfigurable {
   public DefaultedBooleanOption(boolean flag1) {
      super(((ToggleOptionBuilder)OptionFactory.method7("enabled").OOOIROIIOCOOHICRIRHHHRROHHHHIO(flag1)).RIRRHIRCCHCCHOICRHRHHRIHOIHHRH());
      this.HOCCCRRIRRIHHCRROCHRHICCCOIHHI.method11(flag1, true);
   }

   public DefaultedBooleanOption(boolean flag1, ClientOption<Boolean> lightingextension2) {
      super(lightingextension2);
      this.HOCCCRRIRRIHHCRROCHRHICCCOIHHI.method11(flag1, true);
   }

   public void load(JsonObject json1) {
      this.HOCCCRRIRRIHHCRROCHRHICCCOIHHI.load(json1);
   }

   public void method1(JsonObject json1) {
      this.HOCCCRRIRRIHHCRROCHRHICCCOIHHI.method1(json1);
   }

   public int priority() {
      return 100;
   }
}
