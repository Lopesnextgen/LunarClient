package com.moonsworth.lunar.client.itemphysics.mixin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Itemphysics2 {
   @SerializedName("regex")
   @Expose
   private Itemphysics field1;
   @SerializedName("words")
   @Expose
   private Itemphysics3 field2;

   public Itemphysics method1() {
      return this.field1;
   }

   public void method2(Itemphysics var1) {
      this.field1 = var1;
   }

   public Itemphysics3 method3() {
      return this.field2;
   }

   public void method4(Itemphysics3 var1) {
      this.field2 = var1;
   }
}
