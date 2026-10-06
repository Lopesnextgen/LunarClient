package com.moonsworth.lunar.client.framework.mod;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.JsonPersistable;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.GuardedOption;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Nameplate implements ModEnabledState, JsonPersistable {
   private final ModEnabledState field1;
   private boolean field2;
   private boolean field3 = true;

   public Nameplate(ModEnabledState var1) {
      this.field1 = var1;
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
      return this.field1.method1().map(var1 -> new GuardedOption(var1, () -> this.field3));
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
   public void setEnabled(boolean var1) {
      if (!this.field3) {
         this.field1.setEnabled(var1);
      }
   }

   @Override
   public boolean method3() {
      return this.field3 ? false : this.field1.method3();
   }

   @Override
   public void method4(boolean var1) {
      if (!this.field3) {
         this.field1.method4(var1);
      }
   }

   @Override
   public void method5(Framework7Extension var1, boolean var2) {
      if (this.field3) {
         if (!var2) {
            this.field1.method5(var1, false);
         }
      } else {
         this.field1.method5(var1, var2);
      }
   }

   public void load(JsonObject var1) {
      if (!this.field1.method1().isEmpty()) {
         ClientOption var2 = this.field1.method1().get();
         if (this.field3) {
            String var3 = var2.getId();
            if (var1.has(var3) && !var1.get(var3).isJsonNull()) {
               this.field2 = var1.get(var3).getAsBoolean();
            } else {
               this.field2 = (Boolean)var2.getDefaultValue();
            }
         } else {
            var2.load(var1);
         }
      }
   }

   public void method1(JsonObject var1) {
      if (!this.field1.method1().isEmpty()) {
         ClientOption var2 = this.field1.method1().get();
         if (this.field3) {
            if (((Boolean)var2.getDefaultValue()).equals(this.field2)) {
               var1.remove(var2.getId());
            } else {
               var1.addProperty(var2.getId(), this.field2);
            }
         } else {
            var2.method1(var1);
         }
      }
   }

   public int priority() {
      return this.field1 instanceof JsonPersistable var1 ? var1.priority() : 1000;
   }
}
