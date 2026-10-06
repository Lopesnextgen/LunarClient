package com.moonsworth.lunar.client.replay.project;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import java.util.Map;
import lombok.Generated;

@SerializedNameOnly
public class ReplayMetadata {
   @SerializedName("minecraftVersion")
   String field1;
   @SerializedName("minecraftProtocolVersion")
   int field2;
   @SerializedName("externalMods")
   Map<String, String> field3;

   @Generated
   public ReplayMetadata() {
   }

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
   public void method4(String text) {
      this.field1 = text;
   }

   @Generated
   public void method5(int number1) {
      this.field2 = number1;
   }

   @Generated
   public void method6(Map<String, String> map) {
      this.field3 = map;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof ReplayMetadata rewind4_22)) {
         return false;
      } else if (!rewind4_22.canEqual(this)) {
         return false;
      } else if (this.method2() != rewind4_22.method2()) {
         return false;
      } else {
         String text3 = this.method1();
         String text4 = rewind4_22.method1();
         if (text3 == null ? text4 == null : text3.equals(text4)) {
            Map map5 = this.method3();
            Map map6 = rewind4_22.method3();
            return map5 == null ? map6 == null : map5.equals(map6);
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof ReplayMetadata;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + this.method2();
      String text3 = this.method1();
      number2 = number2 * 59 + (text3 == null ? 43 : text3.hashCode());
      Map map4 = this.method3();
      return number2 * 59 + (map4 == null ? 43 : map4.hashCode());
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
