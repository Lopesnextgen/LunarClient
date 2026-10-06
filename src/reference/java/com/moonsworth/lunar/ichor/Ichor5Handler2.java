package com.moonsworth.lunar.ichor;

import lombok.Generated;

public class Ichor5Handler2 implements Ichor5 {
   private final String field1;
   private final String field2;

   @Override
   public void loadIchor(IchorTransformer var1) {
   }

   @Generated
   public Ichor5Handler2(String var1, String text) {
      this.field1 = var1;
      this.field2 = text;
   }

   @Generated
   public String getId() {
      return this.field1;
   }

   @Generated
   public String getVersion() {
      return this.field2;
   }
}
