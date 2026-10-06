package com.moonsworth.lunar.client.inactive;

import com.eliotlash.molang.ast.Evaluatable;
import com.moonsworth.lunar.Annotation23;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.fps.mixin.EvaluatableImpl;

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
   private final Inactive.Type field12;

   @Annotation23
   public Inactive(
      @Annotation27("hitbox_height") Float var1,
      @Annotation27("hitbox_width") Float var2,
      @Annotation27("step_height") Float var3,
      @Annotation27("eye_height") Float var4,
      @Annotation27("max_fall") Float var5,
      @Annotation27("friction") Float var6,
      @Annotation27("speed") Evaluatable var7,
      @Annotation27("yaw_speed") Evaluatable var8,
      @Annotation27("has_physics") Boolean var9,
      @Annotation27("can_float") Boolean var10,
      @Annotation27("can_jump") Boolean var11,
      @Annotation27("pathfinder") Inactive.Type var12
   ) {
      this(
         var1 == null ? 0.8F : var1,
         var2 == null ? 0.8F : var2,
         var3 == null ? 0.6F : var3,
         var4 == null ? 0.6F : var4,
         var5 == null ? 3.0F : var5,
         var6 == null ? 0.875F : var6,
         var7 == null ? new EvaluatableImpl(0.1F) : var7,
         var8 == null ? new EvaluatableImpl(4.0) : var8,
         var9 == null || var9,
         var10 == null || var10,
         var11 == null || var11,
         var12 == null ? Inactive.Type.GROUND : var12
      );
   }

   public Inactive(
      float var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      Evaluatable var7,
      Evaluatable var8,
      boolean var9,
      boolean var10,
      boolean var11,
      Inactive.Type var12
   ) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
      this.field7 = var7;
      this.field8 = var8;
      this.field9 = var9;
      this.field10 = var10;
      this.field11 = var11;
      this.field12 = var12;
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

   public Inactive.Type method12() {
      return this.field12;
   }

   public enum Type {
      GROUND,
      FLYING;
   }
}
