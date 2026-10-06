package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionCombiner;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class CompoundBooleanOption implements ModEnabledState {
   protected final CompoundBooleanOption.Type field1;
   @NotNull
   protected final OptionCombiner<Boolean> field2;
   private boolean field3 = false;

   @SafeVarargs
   public CompoundBooleanOption(@NotNull CompoundBooleanOption.Type type1, @NotNull ClientOption<Boolean>... items2) {
      this.field1 = type1;
      this.field2 = new OptionCombiner(items2);
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
      if (this.field1 == CompoundBooleanOption.Type.OR) {
         for (ClientOption lightingextension4 : this.field2.method23()) {
            if ((Boolean)lightingextension4.get()) {
               return true;
            }
         }
      } else if (this.field1 == CompoundBooleanOption.Type.AND) {
         for (ClientOption lightingextension8 : this.field2.method23()) {
            if (!(Boolean)lightingextension8.get()) {
               return false;
            }
         }

         return true;
      }

      return false;
   }

   @Override
   public void setEnabled(boolean flag1) {
   }

   @Override
   public boolean method3() {
      return this.field3;
   }

   @Override
   public void method4(boolean flag1) {
      this.field3 = flag1;
   }

   @Generated
   public CompoundBooleanOption.Type method5() {
      return this.field1;
   }

   @NotNull
   @Generated
   public OptionCombiner<Boolean> method6() {
      return this.field2;
   }

   public enum Type {
      AND,
      OR;

      Type() {
      }
   }
}
