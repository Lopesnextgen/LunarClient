package com.moonsworth.lunar.client.config.option;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import com.moonsworth.lunar.client.config.option.ClientOption;

public interface SettingsBuilder<Setting, Parent> {
   void method1(Consumer<SettingsBuilder<Setting, Parent>> consumer1, Consumer<Setting> consumer2);

   Setting method2(ClientOption<?> lightingextension1, Consumer<Parent> consumer2);

   default Setting method3(OptionProvider<?, ?> lighting_71, Consumer<Parent> consumer2) {
      return this.method2(lighting_71.method1(), consumer2);
   }

   Setting method4(ClientOption<?> lightingextension1, Consumer<Parent> consumer2, Consumer<Setting> consumer3);

   default Setting method5(OptionProvider<?, ?> lighting_71, Consumer<Parent> consumer2, Consumer<Setting> consumer3) {
      return this.method4(lighting_71.method1(), consumer2, consumer3);
   }

   Setting method6(ClientOption<?>... items1);

   default Setting method7(OptionProvider<?, ?>... items1) {
      return this.method6(OptionProvider.method2(items1));
   }

   Setting method8(ClientOption<?> lightingextension1, ClientOption<?>... items2);

   default Setting method9(ClientOption<?> lightingextension1, OptionProvider<?, ?>... items2) {
      return this.method8(lightingextension1, OptionProvider.method2(items2));
   }

   Setting method10(ClientOption<?> lightingextension1, ClientOption<?>... items2);

   default Setting method11(ClientOption<?> lightingextension1, OptionProvider<?, ?>... items2) {
      return this.method10(lightingextension1, OptionProvider.method2(items2));
   }

   Setting method12(ClientOption<?>... items1);

   default Setting method13(OptionProvider<?, ?>... items1) {
      return this.method12(OptionProvider.method2(items1));
   }

   default Setting method14(Collection<? extends OptionProvider<?, ?>> list) {
      return this.method12(OptionProvider.method2(list.toArray(new OptionProvider[0])));
   }

   Setting method15(String text1);

   Setting method21();

   Setting method17();

   List<Setting> method18();
}
