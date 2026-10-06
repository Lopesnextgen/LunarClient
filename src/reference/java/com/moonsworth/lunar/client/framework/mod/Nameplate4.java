package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Nameplate4 implements ModEnabledState {
   @NotNull
   protected final ClientOption<Boolean> field1;
   private boolean field2 = false;

   public Nameplate4(@NotNull ClientOption<Boolean> var1) {
      this.field1 = var1;
   }

   @NotNull
   @Override
   public Optional<ClientOption<Boolean>> method1() {
      return Optional.of(this.field1);
   }

   @Override
   public void method2() {
      this.field1.reset();
      this.field2 = false;
   }

   @Override
   public boolean isEnabled() {
      return (Boolean)this.field1.get();
   }

   @Override
   public void setEnabled(boolean var1) {
      this.field1.method10(var1);
   }

   @Override
   public boolean method3() {
      return this.field2;
   }

   @Override
   public void method4(boolean var1) {
      this.field2 = var1;
   }
}
