package com.moonsworth.lunar.client.chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.itemphysics.mixin.Itemphysics;

public class ProfanityFilterConfig {
   @SerializedName("regex")
   @Expose
   private Itemphysics field1;
   @SerializedName("words")
   @Expose
   private ProfanityWordLists field2;

   public ProfanityFilterConfig() {
   }

   public Itemphysics method1() {
      return this.field1;
   }

   public void method2(Itemphysics itemphysics1) {
      this.field1 = itemphysics1;
   }

   public ProfanityWordLists method3() {
      return this.field2;
   }

   public void method4(ProfanityWordLists itemphysics31) {
      this.field2 = itemphysics31;
   }
}
