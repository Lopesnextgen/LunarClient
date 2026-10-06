package com.moonsworth.lunar.client.config.option;

import java.util.List;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import com.moonsworth.lunar.client.config.option.ClientOption;

public class OptionBaker extends PruningOptionBaker<BakedOptionNode<ClientOption<?>>, ClientOption<?>> {
   private final String field1;

   public OptionBaker(String text1) {
      this.field1 = text1;
   }

   public BakedOptionNode<ClientOption<?>> method2(SettingsNode lightinghandler21, List<ClientOption<?>> list2, BooleanSupplier booleansupplier3) {
      return new BakedOptionNode<>(this.field1, list2, booleansupplier3);
   }

   public ClientOption<?> method3(SettingsNode lightinghandler21) {
      return lightinghandler21.method3();
   }

   @Generated
   public String getId() {
      return this.field1;
   }
}
