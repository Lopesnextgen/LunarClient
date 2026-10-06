package com.moonsworth.lunar.client.chat;

public class Sticker {
   private final String field1;
   private final String field2;
   private final String field3;

   public Sticker(String text, String text2, String text3) {
      this.field1 = text;
      this.field2 = text2;
      this.field3 = text3;
   }

   public String id() {
      return this.field1;
   }

   public String url() {
      return this.field2;
   }

   public String name() {
      return this.field3;
   }
}
