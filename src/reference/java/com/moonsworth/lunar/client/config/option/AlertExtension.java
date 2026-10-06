package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import java.util.List;

public interface AlertExtension extends com.moonsworth.lunar.client.config.override.AlertExtension<ClientOption<?>> {
   default TraitType<AlertExtension> method1() {
      return OptionTraits.field4;
   }

   static AlertExtension method2() {
      return new OptionChildren();
   }

   static AlertExtension method3(List<ClientOption<?>> list0) {
      return new OptionChildren(list0);
   }
}
