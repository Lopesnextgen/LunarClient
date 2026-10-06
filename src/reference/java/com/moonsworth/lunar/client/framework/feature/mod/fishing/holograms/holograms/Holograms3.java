package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_4;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3i;

public class Holograms3 {
   @Nullable
   private final Holograms field1;
   private final String field2;
   private final Vector3iBridge field3;
   private final HologramsType_3 field4;

   public Holograms3(@Nullable Holograms var1, String var2, Vector3iBridge var3, HologramsType_3 var4) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
   }

   public Vector3iBridge method1(Vector3iBridge var1) {
      return Holograms_4.method7(this.field3, var1, this.field4);
   }

   @Deprecated
   public Vector3iBridge method2(Vector3iBridge var1) {
      if (this.field1 != null) {
         HologramsType_3 var2 = this.field1.getBlcRot(this.field4);
         if (var2 != this.field4) {
            return this.method3(var1, var2);
         }
      }

      return this.method1(var1);
   }

   public Vector3iBridge method3(Vector3iBridge var1, HologramsType_3 var2) {
      return Holograms_4.method7(this.method25(var2), var1, var2);
   }

   public Vector3i method4(Vector3i var1) {
      return Holograms_4.method6(new Vector3i(this.field3.bridge$getX(), this.field3.bridge$getY(), this.field3.bridge$getZ()), var1, this.field4);
   }

   @Deprecated
   public Vector3i method5(Vector3i var1) {
      if (this.field1 != null) {
         HologramsType_3 var2 = this.field1.getBlcRot(this.field4);
         if (var2 != this.field4) {
            return this.method6(var1, var2);
         }
      }

      return this.method4(var1);
   }

   public Vector3i method6(Vector3i var1, HologramsType_3 var2) {
      Vector3iBridge var3 = this.method25(var2);
      return Holograms_4.method6(new Vector3i(var3.bridge$getX(), var3.bridge$getY(), var3.bridge$getZ()), var1, var2);
   }

   public int[] method7(int[] var1) {
      return Holograms_4.method8(this.field3, var1, this.field4);
   }

   @Deprecated
   public int[] method8(int[] var1) {
      if (this.field1 != null) {
         HologramsType_3 var2 = this.field1.getBlcRot(this.field4);
         if (var2 != this.field4) {
            return this.method9(var1, var2);
         }
      }

      return this.method7(var1);
   }

   public int[] method9(int[] var1, HologramsType_3 var2) {
      return Holograms_4.method8(this.method25(var2), var1, var2);
   }

   public double[] method10(double var1, double var3, double var5) {
      return Holograms_4.method9(this.field3, var1, var3, var5, this.field4);
   }

   @Deprecated
   public double[] method11(double var1, double var3, double var5) {
      if (this.field1 != null) {
         HologramsType_3 var7 = this.field1.getBlcRot(this.field4);
         if (var7 != this.field4) {
            return this.method12(var1, var3, var5, var7);
         }
      }

      return this.method10(var1, var3, var5);
   }

   public double[] method12(double var1, double var3, double var5, HologramsType_3 var7) {
      return Holograms_4.method9(this.method25(var7), var1, var3, var5, var7);
   }

   public Vector3iBridge method13(Vector3iBridge var1) {
      return Holograms_4.method10(this.field3, var1, this.field4);
   }

   @Deprecated
   public Vector3iBridge method14(Vector3iBridge var1) {
      if (this.field1 != null) {
         HologramsType_3 var2 = this.field1.getBlcRot(this.field4);
         if (var2 != this.field4) {
            return this.method15(var1, var2);
         }
      }

      return this.method13(var1);
   }

   public Vector3iBridge method15(Vector3iBridge var1, HologramsType_3 var2) {
      return Holograms_4.method10(this.method25(var2), var1, var2);
   }

   public Vector3i method16(Vector3i var1) {
      return Holograms_4.method11(new Vector3i(this.field3.bridge$getX(), this.field3.bridge$getY(), this.field3.bridge$getZ()), var1, this.field4);
   }

   @Deprecated
   public Vector3i method17(Vector3i var1) {
      if (this.field1 != null) {
         HologramsType_3 var2 = this.field1.getBlcRot(this.field4);
         if (var2 != this.field4) {
            return this.method18(var1, var2);
         }
      }

      return this.method16(var1);
   }

   public Vector3i method18(Vector3i var1, HologramsType_3 var2) {
      Vector3iBridge var3 = this.method25(var2);
      return Holograms_4.method11(new Vector3i(var3.bridge$getX(), var3.bridge$getY(), var3.bridge$getZ()), var1, var2);
   }

   public int[] method19(int[] var1) {
      return Holograms_4.method12(this.field3, var1[0], var1[1], var1[2], this.field4);
   }

   @Deprecated
   public int[] method20(int[] var1) {
      if (this.field1 != null) {
         HologramsType_3 var2 = this.field1.getBlcRot(this.field4);
         if (var2 != this.field4) {
            return this.method21(var1, var2);
         }
      }

      return this.method19(var1);
   }

   public int[] method21(int[] var1, HologramsType_3 var2) {
      return Holograms_4.method12(this.method25(var2), var1[0], var1[1], var1[2], var2);
   }

   @Deprecated
   public int[] method22(int var1, int var2, int var3) {
      return Holograms_4.method12(this.field3, var1, var2, var3, this.field4);
   }

   @Deprecated
   public int[] method23(int var1, int var2, int var3) {
      if (this.field1 != null) {
         HologramsType_3 var4 = this.field1.getBlcRot(this.field4);
         if (var4 != this.field4) {
            return this.method24(var1, var2, var3, var4);
         }
      }

      return this.method22(var1, var2, var3);
   }

   public int[] method24(int var1, int var2, int var3, HologramsType_3 var4) {
      return Holograms_4.method12(this.method25(var4), var1, var2, var3, var4);
   }

   private Vector3iBridge method25(HologramsType_3 var1) {
      return var1.getCorner(
         this.field4
            .inverseGetCorner(this.field3)
            .bridge$add(new Vector3i(-this.field4.getX(), 0, -this.field4.getZ()))
            .bridge$add(new Vector3i(var1.getX(), 0, var1.getZ()))
      );
   }

   @Nullable
   public Holograms method26() {
      return this.field1;
   }

   public String hash() {
      return this.field2;
   }

   public Vector3iBridge method27() {
      return this.field3;
   }

   public HologramsType_3 method28() {
      return this.field4;
   }
}
