package com.moonsworth.lunar.bridge.world;

import java.io.File;
import lombok.Generated;

public class LevelData {
   private String path;
   private String name;
   private String version;
   private File field1;

   @Generated
   public String getPath() {
      return this.path;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String getVersion() {
      return this.version;
   }

   @Generated
   public File method1() {
      return this.field1;
   }

   @Generated
   public void setPath(String text1) {
      this.path = text1;
   }

   @Generated
   public void setName(String text1) {
      this.name = text1;
   }

   @Generated
   public void setVersion(String text1) {
      this.version = text1;
   }

   @Generated
   public void method3(File file1) {
      this.field1 = file1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof LevelData itemcounter82)) {
         return false;
      } else {
         if (!itemcounter82.canEqual(this)) {
            return false;
         }

         String text3 = this.getPath();
         String text4 = itemcounter82.getPath();
         if (text3 == null ? text4 == null : text3.equals(text4)) {
            String text5 = this.getName();
            String text6 = itemcounter82.getName();
            if (text5 == null ? text6 == null : text5.equals(text6)) {
               String text7 = this.getVersion();
               String text8 = itemcounter82.getVersion();
               if (text7 == null ? text8 == null : text7.equals(text8)) {
                  File file9 = this.method1();
                  File file10 = itemcounter82.method1();
                  return file9 == null ? file10 == null : file9.equals(file10);
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
      return obj1 instanceof LevelData;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      String text3 = this.getPath();
      number2 = number2 * 59 + (text3 == null ? 43 : text3.hashCode());
      String text4 = this.getName();
      number2 = number2 * 59 + (text4 == null ? 43 : text4.hashCode());
      String text5 = this.getVersion();
      number2 = number2 * 59 + (text5 == null ? 43 : text5.hashCode());
      File file6 = this.method1();
      return number2 * 59 + (file6 == null ? 43 : file6.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "LevelData(path=" + this.getPath() + ", name=" + this.getName() + ", version=" + this.getVersion() + ", icon=" + this.method1() + ")";
   }

   @Generated
   public LevelData(String text1, String text, String text3, File file4) {
      this.path = text1;
      this.name = text;
      this.version = text3;
      this.field1 = file4;
   }
}
