package com.moonsworth.lunar.client.replay.export;

import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.List;

public class ExportSettingsSection {
   private final String field1;
   private final List<ClientOption<?>> field2;

   public ExportSettingsSection(String text, List<ClientOption<?>> list) {
      this.field1 = text;
      this.field2 = list;
   }

   public String type() {
      return this.field1;
   }

   public List<ClientOption<?>> options() {
      return this.field2;
   }
}
