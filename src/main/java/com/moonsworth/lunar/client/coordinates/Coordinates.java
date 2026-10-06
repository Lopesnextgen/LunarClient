package com.moonsworth.lunar.client.coordinates;

import java.util.UUID;

public class Coordinates {
   private final UUID field1;
   private final String field2;

   public Coordinates(UUID uUID, String text) {
      this.field1 = uUID;
      this.field2 = text;
   }

   public UUID uuid() {
      return this.field1;
   }

   public String username() {
      return this.field2;
   }
}
