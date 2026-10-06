package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import java.util.UUID;

public class Nameplate_2 {
   private final String field1;
   private final String field2;
   private final UUID field3;

   public Nameplate_2(String var1, String var2) {
      this(var1, var2, null);
   }

   public Nameplate_2(String var1, String var2, UUID uUID) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = uUID;
   }

   public String type() {
      return this.field1;
   }

   public String name() {
      return this.field2;
   }

   public UUID method1() {
      return this.field3;
   }
}
