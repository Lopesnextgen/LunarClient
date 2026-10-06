package com.moonsworth.lunar.client.replay.recording;

public class ReplayLocation {
   private final ReplayEnvironment field1;
   private final String field2;

   public ReplayLocation(ReplayEnvironment replayEnvironment, String text) {
      this.field1 = replayEnvironment;
      this.field2 = text;
   }

   public ReplayEnvironment method1() {
      return this.field1;
   }

   public String value() {
      return this.field2;
   }
}
