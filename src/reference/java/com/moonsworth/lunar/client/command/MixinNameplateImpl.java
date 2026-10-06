package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.util.ThreadModuleDump27;
import lombok.Generated;

public class MixinNameplateImpl extends MixinNameplate {
   private final String field3;

   public MixinNameplateImpl(String var1) {
      this.field3 = var1;
   }

   public static MixinNameplateImpl method1(String text) {
      return new MixinNameplateImpl(text);
   }

   public MixinNameplateImpl method2(MixinNameplate var1) {
      super.method1(var1);
      return this;
   }

   public MixinNameplateImpl method3(MixinHelper var1) {
      super.method2(var1);
      return this;
   }

   @Override
   public boolean method3(ThreadModuleDump27 var1, MixinHelper22 mixinHelper22) {
      return this.field3.equals(StringArgumentParser.field1.method2(var1));
   }

   @Generated
   public String method5() {
      return this.field3;
   }
}
