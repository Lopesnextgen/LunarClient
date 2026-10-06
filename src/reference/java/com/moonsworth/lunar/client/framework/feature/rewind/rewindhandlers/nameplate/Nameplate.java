package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate;

import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public class Nameplate {
   private Map<String, Fishing2Iterator> field1 = new HashMap<>();
   private Map<String, Fishing2Iterator> field2 = new HashMap<>();

   public void method1(Fishing2Iterator var1) {
      this.field2.put(var1.type(), var1);
   }

   public void method2() {
      this.field1 = this.field2;
      this.field2 = new HashMap<>();
   }

   public void cleanup() {
      for (Fishing2Iterator var2 : this.field1.values()) {
         if (!this.field2.containsKey(var2.type())) {
            var2.method7();
         }
      }
   }

   @Generated
   public Map<String, Fishing2Iterator> method3() {
      return this.field1;
   }

   @Generated
   public Map<String, Fishing2Iterator> method4() {
      return this.field2;
   }

   @Generated
   public void method5(Map<String, Fishing2Iterator> var1) {
      this.field1 = var1;
   }

   @Generated
   public void method6(Map<String, Fishing2Iterator> var1) {
      this.field2 = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Nameplate var2)) {
         return false;
      } else if (!var2.canEqual(this)) {
         return false;
      } else {
         Map var3 = this.method3();
         Map var4 = var2.method3();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            Map var5 = this.method4();
            Map var6 = var2.method4();
            return var5 == null ? var6 == null : var5.equals(var6);
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Nameplate;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      Map var3 = this.method3();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      Map var4 = this.method4();
      return var2 * 59 + (var4 == null ? 43 : var4.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "LayerPropertiesContext(previouslyAppliedModProperties=" + this.method3() + ", appliedModProperties=" + this.method4() + ")";
   }
}
