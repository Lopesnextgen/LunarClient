package com.moonsworth.lunar.client.config.option;

import java.util.List;
import java.util.function.BooleanSupplier;
import lombok.Generated;

public class FeatureOptionBaker extends OptionTreePruner<ResolvedOptionNode<ClientOption<?>>, ClientOption<?>> {
   private final String field1;

   public FeatureOptionBaker(String var1) {
      this.field1 = var1;
   }

   public ResolvedOptionNode<ClientOption<?>> method2(OptionGraphNode var1, List<ClientOption<?>> var2, BooleanSupplier var3) {
      return new ResolvedOptionNode<>(this.field1, var2, var3);
   }

   public ClientOption<?> method3(OptionGraphNode var1) {
      return var1.method3();
   }

   @Generated
   public String getId() {
      return this.field1;
   }
}
