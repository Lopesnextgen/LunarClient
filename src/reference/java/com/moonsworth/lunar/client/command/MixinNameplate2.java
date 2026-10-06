package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.command.Nameplate_2;
import java.util.List;
import lombok.Generated;

public class MixinNameplate2 {
   private final MixinNameplateImpl field1;

   public MixinNameplate2(MixinNameplateImpl var1) {
      this.field1 = var1;
   }

   public String getName() {
      return this.field1.method5();
   }

   public String method1() {
      return "/" + this.getName();
   }

   public boolean isEnabled() {
      return true;
   }

   public boolean method2(String var1) {
      return MixinHelper3.method1(this.field1, var1);
   }

   public List<String> method3(String var1) {
      return Nameplate_2.method4(this.field1, var1);
   }

   public boolean method4() {
      return true;
   }

   @Generated
   public MixinNameplateImpl method5() {
      return this.field1;
   }
}
