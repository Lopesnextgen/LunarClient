package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.List;

public class OptionGroup {
   private final String field1;
   private final List<ClientOption<?>> field2;

   public OptionGroup(String text, List<ClientOption<?>> list) {
      this.field1 = text;
      this.field2 = list;
   }

   public String method1() {
      return this.field1;
   }

   public List<ClientOption<?>> options() {
      return this.field2;
   }
}
