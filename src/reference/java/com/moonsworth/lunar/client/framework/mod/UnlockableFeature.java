package com.moonsworth.lunar.client.framework.mod;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.JsonConfigurable;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ConditionalOption;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class UnlockableFeature implements ModEnabledState, JsonConfigurable {
   private final ModEnabledState field1;
   private boolean field2;
   private boolean field3 = true;

   public UnlockableFeature(ModEnabledState framework31) {
      this.field1 = framework31;
   }

   public void unlock() {
      if (this.field3) {
         this.field3 = false;
         this.field1.setEnabled(this.field2);
      }
   }

   @NotNull
   @Override
   public Optional<ClientOption<Boolean>> method1() {
      return this.field1.method1().map(arg1 -> new ConditionalOption(arg1, () -> this.field3));
   }

   @Override
   public void method2() {
      if (!this.field3) {
         this.field1.method2();
      }
   }

   @Override
   public boolean isEnabled() {
      return this.field3 ? false : this.field1.isEnabled();
   }

   @Override
   public void setEnabled(boolean flag1) {
      if (!this.field3) {
         this.field1.setEnabled(flag1);
      }
   }

   @Override
   public boolean method3() {
      return this.field3 ? false : this.field1.method3();
   }

   @Override
   public void method4(boolean flag1) {
      if (!this.field3) {
         this.field1.method4(flag1);
      }
   }

   @Override
   public void method5(Framework7Extension framework7, boolean flag) {
      if (this.field3) {
         if (!flag) {
            this.field1.method5(framework7, false);
         }
      } else {
         this.field1.method5(framework7, flag);
      }
   }

   public void load(JsonObject json1) {
      if (!this.field1.method1().isEmpty()) {
         ClientOption lightingextension2 = this.field1.method1().get();
         if (this.field3) {
            String text3 = lightingextension2.getId();
            if (json1.has(text3) && !json1.get(text3).isJsonNull()) {
               this.field2 = json1.get(text3).getAsBoolean();
            } else {
               this.field2 = (Boolean)lightingextension2.getDefaultValue();
            }
         } else {
            lightingextension2.load(json1);
         }
      }
   }

   public void method1(JsonObject json1) {
      if (!this.field1.method1().isEmpty()) {
         ClientOption lightingextension2 = this.field1.method1().get();
         if (this.field3) {
            if (((Boolean)lightingextension2.getDefaultValue()).equals(this.field2)) {
               json1.remove(lightingextension2.getId());
            } else {
               json1.addProperty(lightingextension2.getId(), this.field2);
            }
         } else {
            lightingextension2.method1(json1);
         }
      }
   }

   public int priority() {
      return this.field1 instanceof JsonConfigurable lighting31 ? lighting31.priority() : 1000;
   }
}
