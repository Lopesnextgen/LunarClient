package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.client.cosmetics.SprayManager;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.booleans.BooleanObjectPair;
import lombok.Generated;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class SprayPlacementImpl implements SprayPlacement {
   private final SprayEntry field1;
   private Vector3f field2;
   private HorsestatsType_2 field3;
   private float rotation;
   private float field4;
   private boolean valid;
   private Horsestats20Extension2[] field5;
   private int field6;

   @Override
   public float getRotation() {
      return SprayManager.method23(this.rotation + this.field4);
   }

   public void method1(float var1) {
      SprayManager var2 = ThreadModuleDump63.method4().method46();
      var2.method16(false, true, (var3, var4, var5, var6, var7, var8, var9) -> {
         this.field3 = var5;
         this.field2 = var7;
         this.rotation = var8;
         Matrix4f var10 = SprayManager.method28(var7, var5, this.getRotation());
         this.field6 = var1 > 0.5 ? var2.method17(this.field1, var10, var5) : this.field6;
         BooleanObjectPair var11 = SprayManager.method27(this.field1, var10, var5, true);
         if (var11 == null) {
            this.field5 = new Horsestats20Extension2[0];
            this.valid = false;
         } else {
            this.field5 = (Horsestats20Extension2[])var11.value();
            this.valid = var9 && this.field6 <= 8 && var11.keyBoolean();
         }
      }, var1);
   }

   @Generated
   @Override
   public SprayEntry method1() {
      return this.field1;
   }

   @Generated
   @Override
   public Vector3f method2() {
      return this.field2;
   }

   @Generated
   @Override
   public HorsestatsType_2 method3() {
      return this.field3;
   }

   @Generated
   public float method6() {
      return this.field4;
   }

   @Generated
   public boolean isValid() {
      return this.valid;
   }

   @Generated
   @Override
   public Horsestats20Extension2[] method4() {
      return this.field5;
   }

   @Generated
   @Override
   public int method5() {
      return this.field6;
   }

   @Generated
   public SprayPlacementImpl(SprayEntry var1, Vector3f var2, HorsestatsType_2 var3, float var4, float var5, boolean var6, Horsestats20Extension2[] var7, int var8) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.rotation = var4;
      this.field4 = var5;
      this.valid = var6;
      this.field5 = var7;
      this.field6 = var8;
   }

   @Generated
   public void method8(float var1) {
      this.field4 = var1;
   }
}
