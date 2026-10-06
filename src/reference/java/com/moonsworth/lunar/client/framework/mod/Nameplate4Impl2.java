package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class Nameplate4Impl2 implements ModEnabledState {
   private final boolean field1;
   private boolean value;
   private boolean field2 = false;

   public Nameplate4Impl2(boolean var1) {
      this.field1 = var1;
      this.value = var1;
   }

   @NotNull
   @Override
   public Optional<ClientOption<Boolean>> method1() {
      return Optional.empty();
   }

   @Override
   public void method2() {
      this.value = this.field1;
   }

   @Override
   public boolean isEnabled() {
      return this.value;
   }

   @Override
   public void setEnabled(boolean var1) {
      this.value = var1;
   }

   @Override
   public boolean method3() {
      return this.field2;
   }

   @Override
   public void method4(boolean var1) {
      this.field2 = var1;
   }

   @Generated
   public boolean method5() {
      return this.field1;
   }

   @Generated
   public boolean isValue() {
      return this.value;
   }
}
