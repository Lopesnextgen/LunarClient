package com.moonsworth.lunar.ichor;

import lombok.Generated;

public class LoaderDescriptor implements IchorLoader {
   private final String field1;
   private final String field2;

   public void loadIchor(IchorTransformer ichorTransformer) {
   }

   @Generated
   public LoaderDescriptor(String text, String text2) {
      this.field1 = text;
      this.field2 = text2;
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
