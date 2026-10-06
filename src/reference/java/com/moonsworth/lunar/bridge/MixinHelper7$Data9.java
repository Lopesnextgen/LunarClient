package com.moonsworth.lunar.bridge;

import java.util.function.Consumer;
import lombok.Generated;

public class MixinHelper7$Data9 {
   private MixinHelper7$Type6 field1 = null;
   private OutputStateShardBridge field2 = null;

   public boolean method1() {
      return this.field1 != null;
   }

   public boolean method2() {
      return this.field2 != null;
   }

   public boolean hasValue() {
      return this.method1() || this.method2();
   }

   public void method3(OutputStateShardBridge var1) {
      this.field2 = var1;
      this.field1 = null;
   }

   public void method4(MixinHelper7$Type6 var1) {
      this.field2 = null;
      this.field1 = var1;
   }

   public void method5(Consumer<MixinHelper7$Type6> var1, Consumer<OutputStateShardBridge> consumer) {
      if (this.method1()) {
         var1.accept(this.field1);
      } else if (this.method2()) {
         consumer.accept(this.field2);
      }
   }

   @Generated
   public MixinHelper7$Type6 method6() {
      return this.field1;
   }

   @Generated
   public OutputStateShardBridge method7() {
      return this.field2;
   }
}
