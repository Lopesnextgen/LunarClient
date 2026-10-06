package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.FishingType;
import javax.annotation.Nullable;
import lombok.Generated;

public class SkyBlockCommission {
   private final SkyBlockCommission.Type field1;
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

   public static SkyBlockCommission.Data method1() {
      return new SkyBlockCommission.Data();
   }

   @Override
   public String toString() {
      if (this.field1 == null) {
         return "SkyBlockCommission {no goal}";
      }

      StringBuilder builder1 = new StringBuilder();
      builder1.append("SkyBlockCommission {");
      builder1.append("goal=").append(this.field1).append(",");
      if (this.field3 != null) {
         builder1.append("glaciteTunnelsResource=").append(this.field3.getId()).append(",");
      }

      if (this.field4 != null) {
         builder1.append("resourceStr=").append(this.field4).append(",");
      }

      if (this.field5 != null) {
         builder1.append("locationStr=").append(this.field5).append(",");
      }

      if (this.field7 != null) {
         builder1.append("mobToKillStr=").append(this.field7).append(",");
      }

      if (this.field8 != null) {
         builder1.append("crystalStr=").append(this.field8).append(",");
      }

      if (this.field6 != null) {
         builder1.append("eventStr=").append(this.field6).append(",");
      }

      if (this.field9 != null) {
         builder1.append("miscStr=").append(this.field9).append(",");
      }

      builder1.append("}");
      return builder1.toString();
   }

   @Generated
   SkyBlockCommission(
      SkyBlockCommission.Type type1,
      boolean flag,
      @Nullable FishingType fishingtype3,
      @Nullable String text4,
      @Nullable String text5,
      @Nullable String text6,
      @Nullable String text7,
      @Nullable String text8,
      @Nullable String text9
   ) {
      this.field1 = type1;
      this.field2 = flag;
      this.field3 = fishingtype3;
      this.field4 = text4;
      this.field5 = text5;
      this.field6 = text6;
      this.field7 = text7;
      this.field8 = text8;
      this.field9 = text9;
   }

   @Generated
   public SkyBlockCommission.Type method2() {
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
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof SkyBlockCommission fishing32)) {
         return false;
      } else {
         if (!fishing32.canEqual(this)) {
            return false;
         }

         SkyBlockCommission.Type type3 = this.method2();
         SkyBlockCommission.Type type4 = fishing32.method2();
         if (type3 == null ? type4 == null : type3.equals(type4)) {
            FishingType fishingtype5 = this.method4();
            FishingType fishingtype6 = fishing32.method4();
            if (fishingtype5 == null ? fishingtype6 == null : fishingtype5.equals(fishingtype6)) {
               String text7 = this.method5();
               String text8 = fishing32.method5();
               if (text7 == null ? text8 == null : text7.equals(text8)) {
                  String text9 = this.method6();
                  String text10 = fishing32.method6();
                  if (text9 == null ? text10 == null : text9.equals(text10)) {
                     String text11 = this.method7();
                     String text12 = fishing32.method7();
                     if (text11 == null ? text12 == null : text11.equals(text12)) {
                        String text13 = this.method8();
                        String text14 = fishing32.method8();
                        if (text13 == null ? text14 == null : text13.equals(text14)) {
                           String text15 = this.method9();
                           String text16 = fishing32.method9();
                           if (text15 == null ? text16 == null : text15.equals(text16)) {
                              String text17 = this.method10();
                              String text18 = fishing32.method10();
                              return text17 == null ? text18 == null : text17.equals(text18);
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
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof SkyBlockCommission;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      SkyBlockCommission.Type type3 = this.method2();
      number2 = number2 * 59 + (type3 == null ? 43 : type3.hashCode());
      FishingType fishingtype4 = this.method4();
      number2 = number2 * 59 + (fishingtype4 == null ? 43 : fishingtype4.hashCode());
      String text5 = this.method5();
      number2 = number2 * 59 + (text5 == null ? 43 : text5.hashCode());
      String text6 = this.method6();
      number2 = number2 * 59 + (text6 == null ? 43 : text6.hashCode());
      String text7 = this.method7();
      number2 = number2 * 59 + (text7 == null ? 43 : text7.hashCode());
      String text8 = this.method8();
      number2 = number2 * 59 + (text8 == null ? 43 : text8.hashCode());
      String text9 = this.method9();
      number2 = number2 * 59 + (text9 == null ? 43 : text9.hashCode());
      String text10 = this.method10();
      return number2 * 59 + (text10 == null ? 43 : text10.hashCode());
   }

   @Generated
   public static class Data {
      @Generated
      private SkyBlockCommission.Type field1;
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
      public SkyBlockCommission.Data method1(SkyBlockCommission.Type type1) {
         this.field1 = type1;
         return this;
      }

      @Generated
      public SkyBlockCommission.Data method2(boolean flag) {
         this.completed = flag;
         return this;
      }

      @Generated
      public SkyBlockCommission.Data method3(@Nullable FishingType fishingtype1) {
         this.field2 = fishingtype1;
         return this;
      }

      @Generated
      public SkyBlockCommission.Data method4(@Nullable String text1) {
         this.field3 = text1;
         return this;
      }

      @Generated
      public SkyBlockCommission.Data method5(@Nullable String text1) {
         this.field4 = text1;
         return this;
      }

      @Generated
      public SkyBlockCommission.Data method6(@Nullable String text1) {
         this.field5 = text1;
         return this;
      }

      @Generated
      public SkyBlockCommission.Data method7(@Nullable String text1) {
         this.field6 = text1;
         return this;
      }

      @Generated
      public SkyBlockCommission.Data method8(@Nullable String text1) {
         this.field7 = text1;
         return this;
      }

      @Generated
      public SkyBlockCommission.Data method9(@Nullable String text1) {
         this.field8 = text1;
         return this;
      }

      @Generated
      public SkyBlockCommission method10() {
         return new SkyBlockCommission(this.field1, this.completed, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8);
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

      Type() {
      }
   }
}
