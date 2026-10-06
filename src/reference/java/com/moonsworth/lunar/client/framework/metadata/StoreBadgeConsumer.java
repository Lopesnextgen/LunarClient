package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonElement;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class StoreBadgeConsumer extends com.moonsworth.lunar.client.framework.metadata.MetadataConsumer {
   @Nullable
   private String field1;

   @Override
   public void method4(JsonElement var1) {
      if (var1 != null && !var1.isJsonNull() && var1.isJsonPrimitive()) {
         this.field1 = var1.getAsString();
      } else {
         this.field1 = null;
      }
   }

   @Nullable
   @Generated
   public String method2() {
      return this.field1;
   }
}
