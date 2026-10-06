package com.moonsworth.lunar.client.framework.feature.rewind;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_4;
import com.moonsworth.lunar.client.util.Annotation7;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;

@Annotation7
public class Rewind3 {
   String name;
   @SerializedName("projectVersion")
   int field1 = 0;
   @SerializedName("minecraftVersion")
   String field2;
   @SerializedName("minecraftProtocolVersion")
   int field3;
   @SerializedName("timeline")
   UUID field4 = null;
   @SerializedName("mediaPool")
   Highlight_4 field5 = new Highlight_4();
   @SerializedName("repository")
   com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind field6 = new com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind();
   @SerializedName("externalMods")
   Map<String, String> field7;

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public int method1() {
      return this.field1;
   }

   @Generated
   public String method2() {
      return this.field2;
   }

   @Generated
   public int method3() {
      return this.field3;
   }

   @Generated
   public UUID method4() {
      return this.field4;
   }

   @Generated
   public Highlight_4 method5() {
      return this.field5;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind method6() {
      return this.field6;
   }

   @Generated
   public Map<String, String> method7() {
      return this.field7;
   }

   @Generated
   public void setName(String var1) {
      this.name = var1;
   }

   @Generated
   public void method8(int var1) {
      this.field1 = var1;
   }

   @Generated
   public void method9(String var1) {
      this.field2 = var1;
   }

   @Generated
   public void method10(int var1) {
      this.field3 = var1;
   }

   @Generated
   public void method11(UUID var1) {
      this.field4 = var1;
   }

   @Generated
   public void method12(Highlight_4 var1) {
      this.field5 = var1;
   }

   @Generated
   public void method13(com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind var1) {
      this.field6 = var1;
   }

   @Generated
   public void method14(Map<String, String> var1) {
      this.field7 = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Rewind3 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         if (this.method1() != var2.method1()) {
            return false;
         }

         if (this.method3() != var2.method3()) {
            return false;
         }

         String var3 = this.getName();
         String var4 = var2.getName();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            String var5 = this.method2();
            String var6 = var2.method2();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               UUID var7 = this.method4();
               UUID var8 = var2.method4();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  Highlight_4 var9 = this.method5();
                  Highlight_4 var10 = var2.method5();
                  if (var9 == null ? var10 == null : var9.equals(var10)) {
                     com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind var11 = this.method6();
                     com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind var12 = var2.method6();
                     if (var11 == null ? var12 == null : var11.equals(var12)) {
                        Map var13 = this.method7();
                        Map var14 = var2.method7();
                        return var13 == null ? var14 == null : var13.equals(var14);
                     } else {
                        return false;
                     }
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Rewind3;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + this.method1();
      var2 = var2 * 59 + this.method3();
      String var3 = this.getName();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      String var4 = this.method2();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      UUID var5 = this.method4();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      Highlight_4 var6 = this.method5();
      var2 = var2 * 59 + (var6 == null ? 43 : var6.hashCode());
      com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind var7 = this.method6();
      var2 = var2 * 59 + (var7 == null ? 43 : var7.hashCode());
      Map var8 = this.method7();
      return var2 * 59 + (var8 == null ? 43 : var8.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "RewindProject(name="
         + this.getName()
         + ", projectVersion="
         + this.method1()
         + ", minecraftVersion="
         + this.method2()
         + ", minecraftProtocolVersion="
         + this.method3()
         + ", selectedTimeline="
         + this.method4()
         + ", mediaPool="
         + this.method5()
         + ", repository="
         + this.method6()
         + ", externalMods="
         + this.method7()
         + ")";
   }
}
