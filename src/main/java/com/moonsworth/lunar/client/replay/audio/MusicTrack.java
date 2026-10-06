package com.moonsworth.lunar.client.replay.audio;

public class MusicTrack {
   private final String field1;
   private final String field2;
   private final String field3;

   public MusicTrack(String text, String text2, String text3) {
      this.field1 = text;
      this.field2 = text2;
      this.field3 = text3;
   }

   public String name() {
      return this.field1;
   }

   public String method1() {
      return this.field2;
   }

   public String method2() {
      return this.field3;
   }
}
