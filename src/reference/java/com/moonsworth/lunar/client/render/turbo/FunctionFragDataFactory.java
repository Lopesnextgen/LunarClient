package com.moonsworth.lunar.client.render.turbo;

import it.unimi.dsi.fastutil.Function;
import java.util.List;
import lombok.Generated;

public class FunctionFragDataFactory<T> implements FragDataFactory<T> {
   private final FragDataFactory<T> field1;
   private final Function<FragData, FragData> field2;

   @Override
   public FragData createFragData(List<T> var1, TransparencyLayerMap var2) {
      return (FragData)this.field2.apply(this.field1.createFragData(var1, var2));
   }

   @Generated
   public FunctionFragDataFactory(FragDataFactory<T> var1, Function<FragData, FragData> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
