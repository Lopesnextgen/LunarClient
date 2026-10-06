package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimatedBone;

public class BedrockBone {
   private double[] field1;
   private ModelCube[] field2;
   private Boolean debug;
   private Double field3;
   private Map<String, AnimatedBone> field4;
   private Boolean field5;
   private String name;
   private Boolean field6;
   private String parent;
   private double[] field7 = new double[]{0.0, 0.0, 0.0};
   private PolyMesh field8;
   private Long field9;
   private Boolean field10;
   private double[] field11 = new double[]{0.0, 0.0, 0.0};
   private TextureMesh[] field12;

   public BedrockBone() {
   }

   @JsonProperty("bind_pose_rotation")
   public double[] method1() {
      return this.field1;
   }

   @JsonProperty("bind_pose_rotation")
   public void method2(double[] items1) {
      this.field1 = items1;
   }

   @JsonProperty("cubes")
   public ModelCube[] method3() {
      return this.field2;
   }

   @JsonProperty("cubes")
   public void method4(ModelCube[] items1) {
      this.field2 = items1;
   }

   @JsonProperty("debug")
   public Boolean getDebug() {
      return this.debug;
   }

   @JsonProperty("debug")
   public void setDebug(Boolean flag1) {
      this.debug = flag1;
   }

   @JsonProperty("inflate")
   public Double method5() {
      return this.field3;
   }

   @JsonProperty("inflate")
   public void method6(Double value1) {
      this.field3 = value1;
   }

   @JsonProperty("locators")
   public Map<String, AnimatedBone> method7() {
      return this.field4;
   }

   @JsonProperty("locators")
   public void method8(Map<String, AnimatedBone> map1) {
      this.field4 = map1;
   }

   @JsonProperty("mirror")
   public Boolean method9() {
      return this.field5;
   }

   @JsonProperty("mirror")
   public void method10(Boolean flag1) {
      this.field5 = flag1;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String text1) {
      this.name = text1;
   }

   @JsonProperty("neverRender")
   public Boolean method11() {
      return this.field6;
   }

   @JsonProperty("neverRender")
   public void method12(Boolean flag1) {
      this.field6 = flag1;
   }

   @JsonProperty("parent")
   public String getParent() {
      return this.parent;
   }

   @JsonProperty("parent")
   public void setParent(String text1) {
      this.parent = text1;
   }

   @JsonProperty("pivot")
   public double[] method15() {
      return this.field7;
   }

   @JsonProperty("pivot")
   public void method16(double[] items1) {
      this.field7 = items1;
   }

   @JsonProperty("poly_mesh")
   public PolyMesh method17() {
      return this.field8;
   }

   @JsonProperty("poly_mesh")
   public void method18(PolyMesh rewindhandlers_31) {
      this.field8 = rewindhandlers_31;
   }

   @JsonProperty("render_group_id")
   public Long method19() {
      return this.field9;
   }

   @JsonProperty("render_group_id")
   public void method20(Long number1) {
      this.field9 = number1;
   }

   @JsonProperty("reset")
   public Boolean method21() {
      return this.field10;
   }

   @JsonProperty("reset")
   public void method22(Boolean flag1) {
      this.field10 = flag1;
   }

   @JsonProperty("rotation")
   public double[] method23() {
      return this.field11;
   }

   @JsonProperty("rotation")
   public void method24(double[] items1) {
      this.field11 = items1;
   }

   @JsonProperty("texture_meshes")
   public TextureMesh[] method25() {
      return this.field12;
   }

   @JsonProperty("texture_meshes")
   public void method26(TextureMesh[] items1) {
      this.field12 = items1;
   }
}
