package com.moonsworth.lunar.client.framework.feature.heightlimit;

import org.jetbrains.annotations.Nullable;

public class Heightlimit {
   private final int field1;
   @Nullable
   private final String field2;

   public Heightlimit(int value, @Nullable String text2) {
      this.field1 = value;
      this.field2 = text2;
   }

   public int limit() {
      return this.field1;
   }

   @Nullable
   public String displayName() {
      return this.field2;
   }
}
