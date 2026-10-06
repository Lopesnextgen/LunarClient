package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BedrockModel {
   private BedrockBone[] field1;
   private String field2;
   private ModelDescription field3;

   public BedrockModel() {
   }

   @JsonProperty("bones")
   public BedrockBone[] method1() {
      return this.field1;
   }

   @JsonProperty("bones")
   public void method2(BedrockBone[] items1) {
      this.field1 = items1;
   }

   @JsonProperty("cape")
   public String getCape() {
      return this.field2;
   }

   @JsonProperty("cape")
   public void method3(String text1) {
      this.field2 = text1;
   }

   @JsonProperty("description")
   public ModelDescription method4() {
      return this.field3;
   }

   @JsonProperty("description")
   public void method5(ModelDescription rewindhandlers61) {
      this.field3 = rewindhandlers61;
   }
}
