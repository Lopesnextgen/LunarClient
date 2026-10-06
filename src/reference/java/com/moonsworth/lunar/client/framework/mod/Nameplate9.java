package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Generated;

public class Nameplate9 implements ModSearchIndex {
   private final List<String> field1 = new ArrayList<>();
   private final List<String> field2 = new ArrayList<>();

   @Override
   public void method3(Framework7Extension var1) {
      if (this.field1.isEmpty()) {
         Pair var2 = ModSearchIndex.method8(var1);
         this.field1.addAll((Collection<? extends String>)var2.first());
         this.field2.addAll((Collection<? extends String>)var2.second());
      }
   }

   @Override
   public boolean method4(String var1) {
      var1 = var1.toLowerCase();
      return this.method5(var1) || this.method6(var1);
   }

   @Override
   public boolean method5(String var1) {
      for (String var3 : this.method1()) {
         if (var3.startsWith(var1)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean method6(String var1) {
      if ((Boolean)ThreadModuleDump63.method4().method41().method6().method52().get()) {
         for (String var3 : this.method2()) {
            if (var3.startsWith(var1)) {
               return true;
            }
         }
      }

      return false;
   }

   @Generated
   @Override
   public List<String> method1() {
      return this.field1;
   }

   @Generated
   @Override
   public List<String> method2() {
      return this.field2;
   }
}
