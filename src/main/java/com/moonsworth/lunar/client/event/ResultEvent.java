package com.moonsworth.lunar.client.event;

import lombok.Generated;

public class ResultEvent extends LunarEvent {
   private ResultEvent.Outcome field1 = ResultEvent.Outcome.DEFAULT;

   public ResultEvent() {
   }

   @Generated
   public ResultEvent.Outcome method1() {
      return this.field1;
   }

   @Generated
   public void method2(ResultEvent.Outcome outcome) {
      this.field1 = outcome;
   }

   public enum Outcome {
      ALLOW,
      DENY,
      DEFAULT;

      Outcome() {
      }
   }
}
