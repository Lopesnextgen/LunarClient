package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.loading.LoadableResource;
import java.util.function.Consumer;
import lombok.Generated;

public abstract class MetadataConsumer implements LoadableResource, Consumer<JsonElement> {
   private boolean loaded = false;
   private String name;

   public boolean isLoaded() {
      return this.loaded;
   }

   public String getName() {
      return this.name;
   }

   public void method3(JsonElement var1) {
      this.method4(var1);
      this.loaded = true;
   }

   public abstract void method4(JsonElement var1);

   @Generated
   public void setName(String var1) {
      this.name = var1;
   }
}
