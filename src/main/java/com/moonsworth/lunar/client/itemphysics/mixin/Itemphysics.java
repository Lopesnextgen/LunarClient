package com.moonsworth.lunar.client.itemphysics.mixin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Itemphysics {
   @SerializedName("normal")
   @Expose
   private List<String> field1 = null;
   @SerializedName("high")
   @Expose
   private List<String> field2 = null;

   public List<String> method1() {
      return this.field1;
   }

   public void method2(List<String> var1) {
      this.field1 = var1;
   }

   public List<String> method3() {
      return this.field2;
   }

   public void method4(List<String> var1) {
      this.field2 = var1;
   }
}
