package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.FishingType;
import javax.annotation.Nullable;
import lombok.Generated;

public class Fishing3 {
   private final Fishing3.Type field1;
   private final boolean field2;
   @Nullable
   private final FishingType field3;
   @Nullable
   private final String field4;
   @Nullable
   private final String field5;
   @Nullable
   private final String field6;
   @Nullable
   private final String field7;
   @Nullable
   private final String field8;
   @Nullable
   private final String field9;

   public static Fishing3.Data method1() {
      return new Fishing3.Data();
   }

   @Override
   public String toString() {
      if (this.field1 == null) {
         return "SkyBlockCommission {no goal}";
      }

      StringBuilder var1 = new StringBuilder();
      var1.append("SkyBlockCommission {");
      var1.append("goal=").append(this.field1).append(",");
      if (this.field3 != null) {
         var1.append("glaciteTunnelsResource=").append(this.field3.getId()).append(",");
      }

      if (this.field4 != null) {
         var1.append("resourceStr=").append(this.field4).append(",");
      }

      if (this.field5 != null) {
         var1.append("locationStr=").append(this.field5).append(",");
      }

      if (this.field7 != null) {
         var1.append("mobToKillStr=").append(this.field7).append(",");
      }

      if (this.field8 != null) {
         var1.append("crystalStr=").append(this.field8).append(",");
      }

      if (this.field6 != null) {
         var1.append("eventStr=").append(this.field6).append(",");
      }

      if (this.field9 != null) {
         var1.append("miscStr=").append(this.field9).append(",");
      }

      var1.append("}");
      return var1.toString();
   }

   @Generated
   Fishing3(
      Fishing3.Type var1,
      boolean var2,
      @Nullable FishingType var3,
      @Nullable String var4,
      @Nullable String var5,
      @Nullable String var6,
      @Nullable String var7,
      @Nullable String var8,
      @Nullable String var9
   ) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
      this.field7 = var7;
      this.field8 = var8;
      this.field9 = var9;
   }

   @Generated
   public Fishing3.Type method2() {
      return this.field1;
   }

   @Generated
   public boolean method3() {
      return this.field2;
   }

   @Nullable
   @Generated
   public FishingType method4() {
      return this.field3;
   }

   @Nullable
   @Generated
   public String method5() {
      return this.field4;
   }

   @Nullable
   @Generated
   public String method6() {
      return this.field5;
   }

   @Nullable
   @Generated
   public String method7() {
      return this.field6;
   }

   @Nullable
   @Generated
   public String method8() {
      return this.field7;
   }

   @Nullable
   @Generated
   public String method9() {
      return this.field8;
   }

   @Nullable
   @Generated
   public String method10() {
      return this.field9;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Fishing3 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         Fishing3.Type var3 = this.method2();
         Fishing3.Type var4 = var2.method2();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            FishingType var5 = this.method4();
            FishingType var6 = var2.method4();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               String var7 = this.method5();
               String var8 = var2.method5();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  String var9 = this.method6();
                  String var10 = var2.method6();
                  if (var9 == null ? var10 == null : var9.equals(var10)) {
                     String var11 = this.method7();
                     String var12 = var2.method7();
                     if (var11 == null ? var12 == null : var11.equals(var12)) {
                        String var13 = this.method8();
                        String var14 = var2.method8();
                        if (var13 == null ? var14 == null : var13.equals(var14)) {
                           String var15 = this.method9();
                           String var16 = var2.method9();
                           if (var15 == null ? var16 == null : var15.equals(var16)) {
                              String var17 = this.method10();
                              String var18 = var2.method10();
                              return var17 == null ? var18 == null : var17.equals(var18);
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
      return var1 instanceof Fishing3;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      Fishing3.Type var3 = this.method2();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      FishingType var4 = this.method4();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      String var5 = this.method5();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      String var6 = this.method6();
      var2 = var2 * 59 + (var6 == null ? 43 : var6.hashCode());
      String var7 = this.method7();
      var2 = var2 * 59 + (var7 == null ? 43 : var7.hashCode());
      String var8 = this.method8();
      var2 = var2 * 59 + (var8 == null ? 43 : var8.hashCode());
      String var9 = this.method9();
      var2 = var2 * 59 + (var9 == null ? 43 : var9.hashCode());
      String var10 = this.method10();
      return var2 * 59 + (var10 == null ? 43 : var10.hashCode());
   }

   @Generated
   public static class Data {
      @Generated
      private Fishing3.Type field1;
      @Generated
      private boolean completed;
      @Generated
      private FishingType field2;
      @Generated
      private String field3;
      @Generated
      private String field4;
      @Generated
      private String field5;
      @Generated
      private String field6;
      @Generated
      private String field7;
      @Generated
      private String field8;

      @Generated
      Data() {
      }

      @Generated
      public Fishing3.Data method1(Fishing3.Type var1) {
         this.field1 = var1;
         return this;
      }

      @Generated
      public Fishing3.Data method2(boolean var1) {
         this.completed = var1;
         return this;
      }

      @Generated
      public Fishing3.Data method3(@Nullable FishingType var1) {
         this.field2 = var1;
         return this;
      }

      @Generated
      public Fishing3.Data method4(@Nullable String var1) {
         this.field3 = var1;
         return this;
      }

      @Generated
      public Fishing3.Data method5(@Nullable String var1) {
         this.field4 = var1;
         return this;
      }

      @Generated
      public Fishing3.Data method6(@Nullable String var1) {
         this.field5 = var1;
         return this;
      }

      @Generated
      public Fishing3.Data method7(@Nullable String var1) {
         this.field6 = var1;
         return this;
      }

      @Generated
      public Fishing3.Data method8(@Nullable String var1) {
         this.field7 = var1;
         return this;
      }

      @Generated
      public Fishing3.Data method9(@Nullable String var1) {
         this.field8 = var1;
         return this;
      }

      @Generated
      public Fishing3 method10() {
         return new Fishing3(this.field1, this.completed, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8);
      }

      @Generated
      @Override
      public String toString() {
         return "SkyBlockCommission.SkyBlockCommissionBuilder(goal="
            + this.field1
            + ", completed="
            + this.completed
            + ", glaciteTunnelsResource="
            + this.field2
            + ", resourceStr="
            + this.field3
            + ", locationStr="
            + this.field4
            + ", eventStr="
            + this.field5
            + ", mobToKillStr="
            + this.field6
            + ", crystalStr="
            + this.field7
            + ", miscStr="
            + this.field8
            + ")";
      }
   }

   public enum Type {
      SLAY,
      COLLECT,
      PARTICIPATE,
      CRYSTAL,
      MISC;
   }
}
