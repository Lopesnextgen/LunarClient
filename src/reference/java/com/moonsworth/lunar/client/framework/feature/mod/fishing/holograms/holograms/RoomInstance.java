package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RoomRotation;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RoomTemplateDetector;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3i;

public class RoomInstance {
   @Nullable
   private final DungeonRoom field1;
   private final String field2;
   private final Vec3iBridge field3;
   private final RoomRotation field4;

   public RoomInstance(@Nullable DungeonRoom holograms1, String text, Vec3iBridge horsestats203, RoomRotation hologramstype_34) {
      this.field1 = holograms1;
      this.field2 = text;
      this.field3 = horsestats203;
      this.field4 = hologramstype_34;
   }

   public Vec3iBridge method1(Vec3iBridge horsestats201) {
      return RoomTemplateDetector.method7(this.field3, horsestats201, this.field4);
   }

   @Deprecated
   public Vec3iBridge method2(Vec3iBridge horsestats201) {
      if (this.field1 != null) {
         RoomRotation hologramstype_32 = this.field1.getBlcRot(this.field4);
         if (hologramstype_32 != this.field4) {
            return this.method3(horsestats201, hologramstype_32);
         }
      }

      return this.method1(horsestats201);
   }

   public Vec3iBridge method3(Vec3iBridge horsestats201, RoomRotation hologramstype_32) {
      return RoomTemplateDetector.method7(this.method25(hologramstype_32), horsestats201, hologramstype_32);
   }

   public Vector3i method4(Vector3i vector3i1) {
      return RoomTemplateDetector.method6(new Vector3i(this.field3.bridge$getX(), this.field3.bridge$getY(), this.field3.bridge$getZ()), vector3i1, this.field4);
   }

   @Deprecated
   public Vector3i method5(Vector3i vector3i1) {
      if (this.field1 != null) {
         RoomRotation hologramstype_32 = this.field1.getBlcRot(this.field4);
         if (hologramstype_32 != this.field4) {
            return this.method6(vector3i1, hologramstype_32);
         }
      }

      return this.method4(vector3i1);
   }

   public Vector3i method6(Vector3i vector3i1, RoomRotation hologramstype_32) {
      Vec3iBridge horsestats203 = this.method25(hologramstype_32);
      return RoomTemplateDetector.method6(new Vector3i(horsestats203.bridge$getX(), horsestats203.bridge$getY(), horsestats203.bridge$getZ()), vector3i1, hologramstype_32);
   }

   public int[] method7(int[] items1) {
      return RoomTemplateDetector.method8(this.field3, items1, this.field4);
   }

   @Deprecated
   public int[] method8(int[] items1) {
      if (this.field1 != null) {
         RoomRotation hologramstype_32 = this.field1.getBlcRot(this.field4);
         if (hologramstype_32 != this.field4) {
            return this.method9(items1, hologramstype_32);
         }
      }

      return this.method7(items1);
   }

   public int[] method9(int[] items1, RoomRotation hologramstype_32) {
      return RoomTemplateDetector.method8(this.method25(hologramstype_32), items1, hologramstype_32);
   }

   public double[] method10(double value1, double value3, double value5) {
      return RoomTemplateDetector.method9(this.field3, value1, value3, value5, this.field4);
   }

   @Deprecated
   public double[] method11(double value1, double value3, double value5) {
      if (this.field1 != null) {
         RoomRotation hologramstype_37 = this.field1.getBlcRot(this.field4);
         if (hologramstype_37 != this.field4) {
            return this.method12(value1, value3, value5, hologramstype_37);
         }
      }

      return this.method10(value1, value3, value5);
   }

   public double[] method12(double value1, double value3, double value5, RoomRotation hologramstype_37) {
      return RoomTemplateDetector.method9(this.method25(hologramstype_37), value1, value3, value5, hologramstype_37);
   }

   public Vec3iBridge method13(Vec3iBridge horsestats201) {
      return RoomTemplateDetector.method10(this.field3, horsestats201, this.field4);
   }

   @Deprecated
   public Vec3iBridge method14(Vec3iBridge horsestats201) {
      if (this.field1 != null) {
         RoomRotation hologramstype_32 = this.field1.getBlcRot(this.field4);
         if (hologramstype_32 != this.field4) {
            return this.method15(horsestats201, hologramstype_32);
         }
      }

      return this.method13(horsestats201);
   }

   public Vec3iBridge method15(Vec3iBridge horsestats201, RoomRotation hologramstype_32) {
      return RoomTemplateDetector.method10(this.method25(hologramstype_32), horsestats201, hologramstype_32);
   }

   public Vector3i method16(Vector3i vector3i1) {
      return RoomTemplateDetector.method11(new Vector3i(this.field3.bridge$getX(), this.field3.bridge$getY(), this.field3.bridge$getZ()), vector3i1, this.field4);
   }

   @Deprecated
   public Vector3i method17(Vector3i vector3i1) {
      if (this.field1 != null) {
         RoomRotation hologramstype_32 = this.field1.getBlcRot(this.field4);
         if (hologramstype_32 != this.field4) {
            return this.method18(vector3i1, hologramstype_32);
         }
      }

      return this.method16(vector3i1);
   }

   public Vector3i method18(Vector3i vector3i1, RoomRotation hologramstype_32) {
      Vec3iBridge horsestats203 = this.method25(hologramstype_32);
      return RoomTemplateDetector.method11(new Vector3i(horsestats203.bridge$getX(), horsestats203.bridge$getY(), horsestats203.bridge$getZ()), vector3i1, hologramstype_32);
   }

   public int[] method19(int[] items1) {
      return RoomTemplateDetector.method12(this.field3, items1[0], items1[1], items1[2], this.field4);
   }

   @Deprecated
   public int[] method20(int[] items1) {
      if (this.field1 != null) {
         RoomRotation hologramstype_32 = this.field1.getBlcRot(this.field4);
         if (hologramstype_32 != this.field4) {
            return this.method21(items1, hologramstype_32);
         }
      }

      return this.method19(items1);
   }

   public int[] method21(int[] items1, RoomRotation hologramstype_32) {
      return RoomTemplateDetector.method12(this.method25(hologramstype_32), items1[0], items1[1], items1[2], hologramstype_32);
   }

   @Deprecated
   public int[] method22(int number1, int number2, int number3) {
      return RoomTemplateDetector.method12(this.field3, number1, number2, number3, this.field4);
   }

   @Deprecated
   public int[] method23(int number1, int number2, int number3) {
      if (this.field1 != null) {
         RoomRotation hologramstype_34 = this.field1.getBlcRot(this.field4);
         if (hologramstype_34 != this.field4) {
            return this.method24(number1, number2, number3, hologramstype_34);
         }
      }

      return this.method22(number1, number2, number3);
   }

   public int[] method24(int number1, int number2, int number3, RoomRotation hologramstype_34) {
      return RoomTemplateDetector.method12(this.method25(hologramstype_34), number1, number2, number3, hologramstype_34);
   }

   private Vec3iBridge method25(RoomRotation hologramstype_31) {
      return hologramstype_31.getCorner(
         this.field4
            .inverseGetCorner(this.field3)
            .bridge$add(new Vector3i(-this.field4.getX(), 0, -this.field4.getZ()))
            .bridge$add(new Vector3i(hologramstype_31.getX(), 0, hologramstype_31.getZ()))
      );
   }

   @Nullable
   public DungeonRoom method26() {
      return this.field1;
   }

   public String hash() {
      return this.field2;
   }

   public Vec3iBridge method27() {
      return this.field3;
   }

   public RoomRotation method28() {
      return this.field4;
   }
}
