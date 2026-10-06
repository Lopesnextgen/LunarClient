package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;

public class RenderEntityHandle {
   private final CosmeticMetadata field1;
   private final boolean field2;

   public RenderEntityHandle(CosmeticMetadata handler, boolean flag) {
      this.field1 = handler;
      this.field2 = flag;
   }

   public CosmeticMetadata method1() {
      return this.field1;
   }

   public boolean method2() {
      return this.field2;
   }
}
