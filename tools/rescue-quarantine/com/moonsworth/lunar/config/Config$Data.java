package com.moonsworth.lunar.config;

import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Config$Data {
   public final String field1;
   public final List<String> field2;
   public final boolean field3;
   public final boolean field4;
   @Nullable
   public final String field5;
   @Nullable
   public final String field6;

   @Generated
   public Config$Data(String text1, List<String> list2, boolean flag3, boolean flag4, @Nullable String text5, @Nullable String text6) {
      this.field1 = text1;
      this.field2 = list2;
      this.field3 = flag3;
      this.field4 = flag4;
      this.field5 = text5;
      this.field6 = text6;
   }

   @Generated
   public String getName() {
      return this.field1;
   }

   @Generated
   public List<String> method1() {
      return this.field2;
   }

   @Generated
   public boolean method2() {
      return this.field3;
   }

   @Generated
   public boolean method3() {
      return this.field4;
   }

   @Nullable
   @Generated
   public String method4() {
      return this.field5;
   }

   @Nullable
   @Generated
   public String method5() {
      return this.field6;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof Config$Data config$data2)) {
         return false;
      } else {
         if (!config$data2.canEqual(this)) {
            return false;
         }

         if (this.method2() != config$data2.method2()) {
            return false;
         }

         if (this.method3() != config$data2.method3()) {
            return false;
         }

         String text3 = this.getName();
         String text4 = config$data2.getName();
         if (text3 == null ? text4 == null : text3.equals(text4)) {
            List list5 = this.method1();
            List list6 = config$data2.method1();
            if (list5 == null ? list6 == null : list5.equals(list6)) {
               String text7 = this.method4();
               String text8 = config$data2.method4();
               if (text7 == null ? text8 == null : text7.equals(text8)) {
                  String text9 = this.method5();
                  String text10 = config$data2.method5();
                  return text9 == null ? text10 == null : text9.equals(text10);
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
      return obj1 instanceof Config$Data;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + (this.method2() ? 79 : 97);
      number2 = number2 * 59 + (this.method3() ? 79 : 97);
      String text3 = this.getName();
      number2 = number2 * 59 + (text3 == null ? 43 : text3.hashCode());
      List list4 = this.method1();
      number2 = number2 * 59 + (list4 == null ? 43 : list4.hashCode());
      String text5 = this.method4();
      number2 = number2 * 59 + (text5 == null ? 43 : text5.hashCode());
      String text6 = this.method5();
      return number2 * 59 + (text6 == null ? 43 : text6.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "MinecraftVersion.ModuleGroup(name="
         + this.getName()
         + ", modules="
         + this.method1()
         + ", def="
         + this.method2()
         + ", priv="
         + this.method3()
         + ", baseModpackVersion="
         + this.method4()
         + ", baseModpackLoader="
         + this.method5()
         + ")";
   }
}
