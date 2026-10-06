package com.moonsworth.lunar.client.event.options;

import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import lombok.Generated;
import com.moonsworth.lunar.client.highlight.HighlightImpl;

public class OptionUpdateEvent extends HighlightImpl {
   private final Options field1;
   private final Option<?, ?, ?> field2;
   private final Object field3;

   @Generated
   public Options getContainer() {
      return this.field1;
   }

   @Generated
   public Option<?, ?, ?> getOption() {
      return this.field2;
   }

   @Generated
   public Object getValue() {
      return this.field3;
   }

   @Generated
   public OptionUpdateEvent(Options option, Option<?, ?, ?> option2, Object object) {
      this.field1 = option;
      this.field2 = option2;
      this.field3 = object;
   }
}
