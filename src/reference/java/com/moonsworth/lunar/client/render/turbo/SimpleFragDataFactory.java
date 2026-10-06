package com.moonsworth.lunar.client.render.turbo;

import java.util.List;
import lombok.Generated;

public class SimpleFragDataFactory<T> implements FragDataFactory<T> {
   private final FragDataFactory<T> field1;

   @Override
   public FragData createFragData(List<T> var1, TransparencyLayerMap map) {
      return new DelegatingFragData(this.field1.createFragData(var1, map));
   }

   @Generated
   public SimpleFragDataFactory(FragDataFactory<T> var1) {
      this.field1 = var1;
   }
}
