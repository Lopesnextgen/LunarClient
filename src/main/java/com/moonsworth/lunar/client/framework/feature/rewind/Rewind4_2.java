package com.moonsworth.lunar.client.framework.feature.rewind;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.util.Annotation7;
import java.util.Map;
import lombok.Generated;

@Annotation7
public class Rewind4_2 {
   @SerializedName("minecraftVersion")
   String field1;
   @SerializedName("minecraftProtocolVersion")
   int field2;
   @SerializedName("externalMods")
   Map<String, String> field3;

   @Generated
   public String method1() {
      return this.field1;
   }

   @Generated
   public int method2() {
      return this.field2;
   }

   @Generated
   public Map<String, String> method3() {
      return this.field3;
   }

   @Generated
   public void method4(String var1) {
      this.field1 = var1;
   }

   @Generated
   public void method5(int var1) {
      this.field2 = var1;
   }

   @Generated
   public void method6(Map<String, String> var1) {
      this.field3 = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Rewind4_2 var2)) {
         return false;
      } else if (!var2.canEqual(this)) {
         return false;
      } else if (this.method2() != var2.method2()) {
         return false;
      } else {
         String var3 = this.method1();
         String var4 = var2.method1();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            Map var5 = this.method3();
            Map var6 = var2.method3();
            return var5 == null ? var6 == null : var5.equals(var6);
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Rewind4_2;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + this.method2();
      String var3 = this.method1();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      Map var4 = this.method3();
      return var2 * 59 + (var4 == null ? 43 : var4.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "RewindProjectMetadata(minecraftVersion="
         + this.method1()
         + ", minecraftProtocolVersion="
         + this.method2()
         + ", externalMods="
         + this.method3()
         + ")";
   }
}
