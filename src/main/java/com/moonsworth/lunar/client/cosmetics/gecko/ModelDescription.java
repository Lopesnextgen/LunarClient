package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelDescription {
   private Boolean field1;
   private Boolean field2;
   private Boolean field3;
   private Boolean field4;
   private Boolean field5;
   private Boolean field6;
   private Boolean field7;
   private Boolean field8;
   private Boolean field9;
   private Boolean field10;
   private String identifier;
   private Boolean field11;
   private Double field12;
   private Double field13;
   private Double field14;
   private double[] field15;
   private Double field16;

   public ModelDescription() {
   }

   @JsonProperty("animationArmsDown")
   public Boolean method1() {
      return this.field1;
   }

   @JsonProperty("animationArmsDown")
   public void method2(Boolean flag1) {
      this.field1 = flag1;
   }

   @JsonProperty("animationArmsOutFront")
   public Boolean method3() {
      return this.field2;
   }

   @JsonProperty("animationArmsOutFront")
   public void method4(Boolean flag1) {
      this.field2 = flag1;
   }

   @JsonProperty("animationDontShowArmor")
   public Boolean method5() {
      return this.field3;
   }

   @JsonProperty("animationDontShowArmor")
   public void method6(Boolean flag1) {
      this.field3 = flag1;
   }

   @JsonProperty("animationInvertedCrouch")
   public Boolean method7() {
      return this.field4;
   }

   @JsonProperty("animationInvertedCrouch")
   public void method8(Boolean flag1) {
      this.field4 = flag1;
   }

   @JsonProperty("animationNoHeadBob")
   public Boolean method9() {
      return this.field5;
   }

   @JsonProperty("animationNoHeadBob")
   public void method10(Boolean flag1) {
      this.field5 = flag1;
   }

   @JsonProperty("animationSingleArmAnimation")
   public Boolean method11() {
      return this.field6;
   }

   @JsonProperty("animationSingleArmAnimation")
   public void method12(Boolean flag1) {
      this.field6 = flag1;
   }

   @JsonProperty("animationSingleLegAnimation")
   public Boolean method13() {
      return this.field7;
   }

   @JsonProperty("animationSingleLegAnimation")
   public void method14(Boolean flag1) {
      this.field7 = flag1;
   }

   @JsonProperty("animationStationaryLegs")
   public Boolean method15() {
      return this.field8;
   }

   @JsonProperty("animationStationaryLegs")
   public void method16(Boolean flag1) {
      this.field8 = flag1;
   }

   @JsonProperty("animationStatueOfLibertyArms")
   public Boolean method17() {
      return this.field9;
   }

   @JsonProperty("animationStatueOfLibertyArms")
   public void method18(Boolean flag1) {
      this.field9 = flag1;
   }

   @JsonProperty("animationUpsideDown")
   public Boolean method19() {
      return this.field10;
   }

   @JsonProperty("animationUpsideDown")
   public void method20(Boolean flag1) {
      this.field10 = flag1;
   }

   @JsonProperty("identifier")
   public String getIdentifier() {
      return this.identifier;
   }

   @JsonProperty("identifier")
   public void setIdentifier(String text1) {
      this.identifier = text1;
   }

   @JsonProperty("preserve_model_pose")
   public Boolean method21() {
      return this.field11;
   }

   @JsonProperty("preserve_model_pose")
   public void method22(Boolean flag1) {
      this.field11 = flag1;
   }

   @JsonProperty("texture_height")
   public Double method23() {
      return this.field12;
   }

   @JsonProperty("texture_height")
   public void method24(Double value1) {
      this.field12 = value1;
   }

   @JsonProperty("texture_width")
   public Double method25() {
      return this.field13;
   }

   @JsonProperty("texture_width")
   public void method26(Double value1) {
      this.field13 = value1;
   }

   @JsonProperty("visible_bounds_height")
   public Double method27() {
      return this.field14;
   }

   @JsonProperty("visible_bounds_height")
   public void method28(Double value1) {
      this.field14 = value1;
   }

   @JsonProperty("visible_bounds_offset")
   public double[] method29() {
      return this.field15;
   }

   @JsonProperty("visible_bounds_offset")
   public void method30(double[] items1) {
      this.field15 = items1;
   }

   @JsonProperty("visible_bounds_width")
   public Double method31() {
      return this.field16;
   }

   @JsonProperty("visible_bounds_width")
   public void method32(Double value1) {
      this.field16 = value1;
   }
}
