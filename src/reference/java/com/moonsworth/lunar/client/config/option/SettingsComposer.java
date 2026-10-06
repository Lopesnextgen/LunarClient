package com.moonsworth.lunar.client.config.option;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public interface SettingsComposer<Setting, Parent> {
   void method1(Consumer<SettingsComposer<Setting, Parent>> var1, Consumer<Setting> var2);

   Setting method2(ClientOption<?> var1, Consumer<Parent> var2);

   default Setting method3(OptionSupplier<?, ?> var1, Consumer<Parent> var2) {
      return this.method2(var1.method1(), var2);
   }

   Setting method4(ClientOption<?> var1, Consumer<Parent> var2, Consumer<Setting> var3);

   default Setting method5(OptionSupplier<?, ?> var1, Consumer<Parent> var2, Consumer<Setting> var3) {
      return this.method4(var1.method1(), var2, var3);
   }

   Setting method6(ClientOption<?>... var1);

   default Setting method7(OptionSupplier<?, ?>... var1) {
      return this.method6(OptionSupplier.method2(var1));
   }

   Setting method8(ClientOption<?> var1, ClientOption<?>... var2);

   default Setting method9(ClientOption<?> var1, OptionSupplier<?, ?>... var2) {
      return this.method8(var1, OptionSupplier.method2(var2));
   }

   Setting method10(ClientOption<?> var1, ClientOption<?>... var2);

   default Setting method11(ClientOption<?> var1, OptionSupplier<?, ?>... var2) {
      return this.method10(var1, OptionSupplier.method2(var2));
   }

   Setting method12(ClientOption<?>... var1);

   default Setting method13(OptionSupplier<?, ?>... var1) {
      return this.method12(OptionSupplier.method2(var1));
   }

   default Setting method14(Collection<? extends OptionSupplier<?, ?>> var1) {
      return this.method12(OptionSupplier.method2(var1.toArray(new OptionSupplier[0])));
   }

   Setting method15(String var1);

   Setting method21();

   Setting method17();

   List<Setting> method18();
}
