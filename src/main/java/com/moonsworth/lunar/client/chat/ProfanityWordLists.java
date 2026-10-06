package com.moonsworth.lunar.client.chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ProfanityWordLists {
   @SerializedName("normal")
   @Expose
   private List<String> field1 = null;
   @SerializedName("high")
   @Expose
   private List<String> field2 = null;

   public ProfanityWordLists() {
   }

   public List<String> method1() {
      return this.field1;
   }

   public void method2(List<String> list1) {
      this.field1 = list1;
   }

   public List<String> method3() {
      return this.field2;
   }

   public void method4(List<String> list1) {
      this.field2 = list1;
   }
}
