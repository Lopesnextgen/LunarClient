package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.calculator.mixin.Calculator;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.util.ThreadModuleDump56;
import java.util.Set;
import lombok.Generated;

public class Nameplate5 implements ModDetails, Calculator2 {
   private final String field1;
   private final Set<Calculator2Handler> field2;
   private final Set<String> field3;
   private final Set<String> field4;
   private final boolean field5;
   private final boolean field6;

   @Override
   public boolean method4() {
      return this.field6;
   }

   public String getLanguagePath() {
      return this.field1 + ".details";
   }

   @Override
   public String getName() {
      return this.method5("name", new Object[0]);
   }

   @Override
   public String getDescription() {
      return this.method5("description", new Object[0]);
   }

   @Override
   public String method5(String var1, Object... var2) {
      return Client.method109().method67().method2(this.field1 + ".info", var1, var2);
   }

   @Override
   public Calculator method6(String var1) {
      return ThreadModuleDump56.method3(Client.method109().method67().method2(this.field1 + ".info", var1, new Object[0]));
   }

   @Generated
   @Override
   public String toString() {
      return "FeatureDetailsImpl(languagePath="
         + this.getLanguagePath()
         + ", categories="
         + this.method1()
         + ", aliases="
         + this.method2()
         + ", originalAuthors="
         + this.method3()
         + ", isVanilla="
         + this.isVanilla()
         + ", allowsKeybind="
         + this.field6
         + ")";
   }

   @Generated
   public Nameplate5(String var1, Set<Calculator2Handler> var2, Set<String> set, Set<String> set2, boolean flag, boolean flag2) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = set;
      this.field4 = set2;
      this.field5 = flag;
      this.field6 = flag2;
   }

   @Generated
   @Override
   public Set<Calculator2Handler> method1() {
      return this.field2;
   }

   @Generated
   @Override
   public Set<String> method2() {
      return this.field3;
   }

   @Generated
   @Override
   public Set<String> method3() {
      return this.field4;
   }

   @Generated
   @Override
   public boolean isVanilla() {
      return this.field5;
   }
}
