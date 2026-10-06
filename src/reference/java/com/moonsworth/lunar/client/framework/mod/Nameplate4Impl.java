package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.CompositeOption;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class Nameplate4Impl implements ModEnabledState {
   protected final Nameplate4Impl.Type field1;
   @NotNull
   protected final CompositeOption<Boolean> field2;
   private boolean field3 = false;

   @SafeVarargs
   public Nameplate4Impl(@NotNull Nameplate4Impl.Type var1, @NotNull ClientOption<Boolean>... var2) {
      this.field1 = var1;
      this.field2 = new CompositeOption(var2);
   }

   @NotNull
   @Override
   public Optional<ClientOption<Boolean>> method1() {
      return Optional.of(this.field2);
   }

   @Override
   public void method2() {
   }

   @Override
   public boolean isEnabled() {
      if (this.field1 == Nameplate4Impl.Type.OR) {
         for (ClientOption var4 : this.field2.method23()) {
            if ((Boolean)var4.get()) {
               return true;
            }
         }
      } else if (this.field1 == Nameplate4Impl.Type.AND) {
         for (ClientOption var8 : this.field2.method23()) {
            if (!(Boolean)var8.get()) {
               return false;
            }
         }

         return true;
      }

      return false;
   }

   @Override
   public void setEnabled(boolean var1) {
   }

   @Override
   public boolean method3() {
      return this.field3;
   }

   @Override
   public void method4(boolean var1) {
      this.field3 = var1;
   }

   @Generated
   public Nameplate4Impl.Type method5() {
      return this.field1;
   }

   @NotNull
   @Generated
   public CompositeOption<Boolean> method6() {
      return this.field2;
   }

   public enum Type {
      AND,
      OR;
   }
}
