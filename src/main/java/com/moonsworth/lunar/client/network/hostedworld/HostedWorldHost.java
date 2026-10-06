package com.moonsworth.lunar.client.network.hostedworld;

import java.util.UUID;

public class HostedWorldHost {
   private final UUID field1;
   private final String field2;

   public HostedWorldHost(UUID uuid1, String text) {
      this.field1 = uuid1;
      this.field2 = text;
   }

   public UUID uuid() {
      return this.field1;
   }

   public String username() {
      return this.field2;
   }
}
