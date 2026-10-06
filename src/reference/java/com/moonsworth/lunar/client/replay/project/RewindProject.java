package com.moonsworth.lunar.client.replay.project;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.replay.timeline.MediaPool;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;

@SerializedNameOnly
public class RewindProject {
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
   MediaPool field5 = new MediaPool();
   @SerializedName("repository")
   com.moonsworth.lunar.client.replay.project.RewindFileCache field6 = new com.moonsworth.lunar.client.replay.project.RewindFileCache();
   @SerializedName("externalMods")
   Map<String, String> field7;

   @Generated
   public RewindProject() {
   }

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
   public MediaPool method5() {
      return this.field5;
   }

   @Generated
   public com.moonsworth.lunar.client.replay.project.RewindFileCache method6() {
      return this.field6;
   }

   @Generated
   public Map<String, String> method7() {
      return this.field7;
   }

   @Generated
   public void setName(String text1) {
      this.name = text1;
   }

   @Generated
   public void method8(int number1) {
      this.field1 = number1;
   }

   @Generated
   public void method9(String text1) {
      this.field2 = text1;
   }

   @Generated
   public void method10(int number1) {
      this.field3 = number1;
   }

   @Generated
   public void method11(UUID uuid1) {
      this.field4 = uuid1;
   }

   @Generated
   public void method12(MediaPool highlight_41) {
      this.field5 = highlight_41;
   }

   @Generated
   public void method13(com.moonsworth.lunar.client.replay.project.RewindFileCache rewind1) {
      this.field6 = rewind1;
   }

   @Generated
   public void method14(Map<String, String> map) {
      this.field7 = map;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof RewindProject rewind32)) {
         return false;
      } else {
         if (!rewind32.canEqual(this)) {
            return false;
         }

         if (this.method1() != rewind32.method1()) {
            return false;
         }

         if (this.method3() != rewind32.method3()) {
            return false;
         }

         String text3 = this.getName();
         String text4 = rewind32.getName();
         if (text3 == null ? text4 == null : text3.equals(text4)) {
            String text5 = this.method2();
            String text6 = rewind32.method2();
            if (text5 == null ? text6 == null : text5.equals(text6)) {
               UUID uuid7 = this.method4();
               UUID uuid8 = rewind32.method4();
               if (uuid7 == null ? uuid8 == null : uuid7.equals(uuid8)) {
                  MediaPool highlight_49 = this.method5();
                  MediaPool highlight_410 = rewind32.method5();
                  if (highlight_49 == null ? highlight_410 == null : highlight_49.equals(highlight_410)) {
                     com.moonsworth.lunar.client.replay.project.RewindFileCache rewind11 = this.method6();
                     com.moonsworth.lunar.client.replay.project.RewindFileCache rewind12 = rewind32.method6();
                     if (rewind11 == null ? rewind12 == null : rewind11.equals(rewind12)) {
                        Map map13 = this.method7();
                        Map map14 = rewind32.method7();
                        return map13 == null ? map14 == null : map13.equals(map14);
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
      return obj1 instanceof RewindProject;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + this.method1();
      number2 = number2 * 59 + this.method3();
      String text3 = this.getName();
      number2 = number2 * 59 + (text3 == null ? 43 : text3.hashCode());
      String text4 = this.method2();
      number2 = number2 * 59 + (text4 == null ? 43 : text4.hashCode());
      UUID uuid5 = this.method4();
      number2 = number2 * 59 + (uuid5 == null ? 43 : uuid5.hashCode());
      MediaPool highlight_46 = this.method5();
      number2 = number2 * 59 + (highlight_46 == null ? 43 : highlight_46.hashCode());
      com.moonsworth.lunar.client.replay.project.RewindFileCache rewind7 = this.method6();
      number2 = number2 * 59 + (rewind7 == null ? 43 : rewind7.hashCode());
      Map map8 = this.method7();
      return number2 * 59 + (map8 == null ? 43 : map8.hashCode());
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
