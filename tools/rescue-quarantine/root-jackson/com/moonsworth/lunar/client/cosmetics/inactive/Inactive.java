package com.moonsworth.lunar.client.cosmetics.inactive;

import com.eliotlash.molang.ast.Evaluatable;
import com.moonsworth.lunar.Annotation23;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.cosmetics.molang.ast.ConstantEvaluatable;

public class Inactive {
   private final float field1;
   private final float field2;
   private final float field3;
   private final float field4;
   private final float field5;
   private final float field6;
   private final Evaluatable field7;
   private final Evaluatable field8;
   private final boolean field9;
   private final boolean field10;
   private final boolean field11;
   private final Inactive.PathfinderType field12;

   @Annotation23
   public Inactive(
      @Annotation27("hitbox_height") Float value1,
      @Annotation27("hitbox_width") Float value2,
      @Annotation27("step_height") Float value3,
      @Annotation27("eye_height") Float value4,
      @Annotation27("max_fall") Float value5,
      @Annotation27("friction") Float value6,
      @Annotation27("speed") Evaluatable evaluatable7,
      @Annotation27("yaw_speed") Evaluatable evaluatable8,
      @Annotation27("has_physics") Boolean flag9,
      @Annotation27("can_float") Boolean flag10,
      @Annotation27("can_jump") Boolean flag11,
      @Annotation27("pathfinder") Inactive.PathfinderType type12
   ) {
      this(
         value1 == null ? 0.8F : value1,
         value2 == null ? 0.8F : value2,
         value3 == null ? 0.6F : value3,
         value4 == null ? 0.6F : value4,
         value5 == null ? 3.0F : value5,
         value6 == null ? 0.875F : value6,
         (Evaluatable)(evaluatable7 == null ? new ConstantEvaluatable(0.1F) : evaluatable7),
         (Evaluatable)(evaluatable8 == null ? new ConstantEvaluatable(4.0) : evaluatable8),
         flag9 == null || flag9,
         flag10 == null || flag10,
         flag11 == null || flag11,
         type12 == null ? Inactive.PathfinderType.GROUND : type12
      );
   }

   public Inactive(
      float value1,
      float value2,
      float value3,
      float value4,
      float value5,
      float value6,
      Evaluatable evaluatable7,
      Evaluatable evaluatable8,
      boolean flag9,
      boolean flag10,
      boolean flag11,
      Inactive.PathfinderType type12
   ) {
      this.field1 = value1;
      this.field2 = value2;
      this.field3 = value3;
      this.field4 = value4;
      this.field5 = value5;
      this.field6 = value6;
      this.field7 = evaluatable7;
      this.field8 = evaluatable8;
      this.field9 = flag9;
      this.field10 = flag10;
      this.field11 = flag11;
      this.field12 = type12;
   }

   public float method1() {
      return this.field1;
   }

   public float method2() {
      return this.field2;
   }

   public float method3() {
      return this.field3;
   }

   public float method4() {
      return this.field4;
   }

   public float method5() {
      return this.field5;
   }

   public float method6() {
      return this.field6;
   }

   public Evaluatable method7() {
      return this.field7;
   }

   public Evaluatable method8() {
      return this.field8;
   }

   public boolean method9() {
      return this.field9;
   }

   public boolean method10() {
      return this.field10;
   }

   public boolean method11() {
      return this.field11;
   }

   public Inactive.PathfinderType method12() {
      return this.field12;
   }

   public enum PathfinderType {
      GROUND,
      FLYING;

      PathfinderType() {
      }
   }
}
