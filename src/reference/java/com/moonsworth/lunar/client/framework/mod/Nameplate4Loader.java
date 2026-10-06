package com.moonsworth.lunar.client.framework.mod;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.JsonPersistable;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;

public class Nameplate4Loader extends Nameplate4 implements JsonPersistable {
   public Nameplate4Loader(boolean var1) {
      super(((ToggleOptionBuilder)OptionFactory.method7("enabled").method4(var1)).method31());
      this.field1.method11(var1, true);
   }

   public Nameplate4Loader(boolean var1, ClientOption<Boolean> var2) {
      super(var2);
      this.field1.method11(var1, true);
   }

   public void load(JsonObject var1) {
      this.field1.load(var1);
   }

   public void method1(JsonObject var1) {
      this.field1.method1(var1);
   }

   public int priority() {
      return 100;
   }
}
