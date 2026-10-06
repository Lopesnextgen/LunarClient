package com.moonsworth.lunar.client.event;

import lombok.Generated;
import com.moonsworth.lunar.client.highlight.Highlight;

public class OutcomeEvent extends Highlight {
   private OutcomeEvent.Type field1 = OutcomeEvent.Type.DEFAULT;

   @Generated
   public OutcomeEvent.Type method1() {
      return this.field1;
   }

   @Generated
   public void method2(OutcomeEvent.Type type) {
      this.field1 = type;
   }

   public enum Type {
      ALLOW,
      DENY,
      DEFAULT;
   }
}
