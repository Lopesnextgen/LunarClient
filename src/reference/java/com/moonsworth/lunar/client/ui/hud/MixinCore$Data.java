package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.client.util.ThreadModuleDumpType5.Data2;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType5.Data3;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType5.Data4;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType5.Data5;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class MixinCore$Data {
   @Nullable
   private Data3 field1;
   @Nullable
   private Data3 field2;
   @Nullable
   private Data3 field3;
   @Nullable
   private Data3 field4;

   public MixinCore$Data method1(boolean var1) {
      this.field1 = new Data4(var1);
      return this;
   }

   public MixinCore$Data method2(boolean var1) {
      this.field2 = new Data4(var1);
      return this;
   }

   public MixinCore$Data method3(boolean var1) {
      this.field3 = new Data4(var1);
      return this;
   }

   public MixinCore$Data method4(boolean var1) {
      this.field4 = new Data4(var1);
      return this;
   }

   public MixinCore$Data method5(Function<String, Boolean> var1) {
      this.field1 = new Data2(var1);
      return this;
   }

   public MixinCore$Data method6(BooleanSupplier var1) {
      this.field3 = new Data5(var1);
      return this;
   }

   public MixinCore$Data method7(BooleanSupplier var1) {
      this.field4 = new Data5(var1);
      return this;
   }

   public HudConditionSet method8() {
      return new HudConditionSet(Data3.method2(this.field1), Data3.method2(this.field2), Data3.method2(this.field3), Data3.method2(this.field4));
   }

   @Generated
   private MixinCore$Data() {
   }
}
