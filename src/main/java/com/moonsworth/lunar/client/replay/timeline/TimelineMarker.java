package com.moonsworth.lunar.client.replay.timeline;

public class TimelineMarker {
   private final int field1;
   private final int field2;
   private final int field3;
   private final String field4;

   public TimelineMarker(int value, int value2, int value3, String text) {
      this.field1 = value;
      this.field2 = value2;
      this.field3 = value3;
      this.field4 = text;
   }

   public int method1() {
      return this.field1;
   }

   public int method2() {
      return this.field2;
   }

   public int method3() {
      return this.field3;
   }

   public String method4() {
      return this.field4;
   }
}
