package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates;

import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.List;

public class Coordinates2 {
   private final String type;
   private final List<ClientOption<?>> options;

   public Coordinates2(String text, List<ClientOption<?>> list) {
      this.type = text;
      this.options = list;
   }

   public String type() {
      return this.type;
   }

   public List<ClientOption<?>> options() {
      return this.options;
   }
}
