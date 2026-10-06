package com.moonsworth.lunar.client.framework.feature.pkg.mixin;

import com.moonsworth.lunar.bridge.AutoCloseableExtension;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import lombok.Generated;

public class Pkg4 {
   private static final float field1 = 1.0F;

   public static Pkg3 method1(AutoCloseableExtension var0, int var1, int var2, int var3, int var4, int var5, boolean var6, float var7) {
      ArrayList var8 = new ArrayList();
      float var9 = -var1 / 2.0F;
      float var10 = var6 ? var7 : -var2 + var7;
      float var11 = -var3 / 2.0F;
      Pkg4.Data var12 = new Pkg4.Data(var9, var10, var11);
      Pkg4.Data2 var13 = new Pkg4.Data2(var1, var2, var3);
      Pkg4.Data3 var14 = new Pkg4.Data3(var4, var5);

      try {
         for (PkgType var18 : PkgType.values()) {
            Pkg4.Data3 var19 = method2(var13, var18);

            for (int var20 = 0; var20 < var19.field1; var20++) {
               for (int var21 = 0; var21 < var19.field2; var21++) {
                  method6(var0, var8, var12, var18, var13, new Pkg4.Data3(var20, var21), var14, var19);
               }
            }
         }
      } catch (Exception var22) {
         Slayer.method7("SkinLayers3D: " + var22.getMessage(), new Object[0]);
         if (!LunarBuildData.field4) {
            var22.printStackTrace();
         }

         return new Pkg3(new ArrayList<>());
      }

      return new Pkg3(var8);
   }

   private static Pkg4.Data3 method2(Pkg4.Data2 var0, PkgType var1) {
      return switch (var1) {
         case DOWN, UP -> new Pkg4.Data3(var0.field1, var0.field3);
         case NORTH, SOUTH -> new Pkg4.Data3(var0.field1, var0.field2);
         case WEST, EAST -> new Pkg4.Data3(var0.field3, var0.field2);
      };
   }

   private static Pkg4.Data3 method3(Pkg4.Data3 var0, Pkg4.Data3 var1, Pkg4.Data2 var2, PkgType var3) {
      return switch (var3) {
         case DOWN -> new Pkg4.Data3(var0.field1 + var2.field3 + var1.field1, var0.field2 + var1.field2);
         case UP -> new Pkg4.Data3(var0.field1 + var2.field1 + var2.field3 + var1.field1, var0.field2 + var1.field2);
         case NORTH -> new Pkg4.Data3(var0.field1 + var2.field3 + var1.field1, var0.field2 + var2.field3 + var1.field2);
         case SOUTH -> new Pkg4.Data3(var0.field1 + var2.field3 + var2.field1 + var2.field3 + var1.field1, var0.field2 + var2.field3 + var1.field2);
         case WEST -> new Pkg4.Data3(var0.field1 + var1.field1, var0.field2 + var2.field3 + var1.field2);
         case EAST -> new Pkg4.Data3(var0.field1 + var2.field3 + var2.field1 + var1.field1, var0.field2 + var2.field3 + var1.field2);
      };
   }

   private static Pkg4.Data4 method4(Pkg4.Data3 var0, Pkg4.Data2 var1, PkgType var2) {
      return switch (var2) {
         case DOWN -> new Pkg4.Data4(var0.field1, 0, var1.field3 - 1 - var0.field2);
         case UP -> new Pkg4.Data4(var0.field1, var1.field2 - 1, var1.field3 - 1 - var0.field2);
         case NORTH -> new Pkg4.Data4(var0.field1 + 0, var0.field2, 0);
         case SOUTH -> new Pkg4.Data4(var1.field1 - 1 - var0.field1, var0.field2, var1.field3 - 1);
         case WEST -> new Pkg4.Data4(0, var0.field2, var1.field3 - 1 - var0.field1);
         case EAST -> new Pkg4.Data4(var1.field1 - 1, var0.field2, var0.field1 + 0);
      };
   }

   private static Pkg4.Data3 method5(Pkg4.Data4 var0, Pkg4.Data2 var1, PkgType var2) {
      return switch (var2) {
         case DOWN, UP -> new Pkg4.Data3(var0.field1, var1.field3 - 1 - var0.field3);
         case NORTH -> new Pkg4.Data3(var0.field1 + 0, var0.field2);
         case SOUTH -> new Pkg4.Data3(var1.field1 - 1 - var0.field1, var0.field2);
         case WEST -> new Pkg4.Data3(var1.field3 - 1 - var0.field3, var0.field2);
         case EAST -> new Pkg4.Data3(var0.field3 + 0, var0.field2);
      };
   }

   private static void method6(
      AutoCloseableExtension var0, List<Pkg2> var1, Pkg4.Data var2, PkgType var3, Pkg4.Data2 var4, Pkg4.Data3 var5, Pkg4.Data3 var6, Pkg4.Data3 var7
   ) {
      Pkg4.Data3 var8 = method3(var6, var5, var4, var3);
      if (method8(var0, var8)) {
         Pkg4.Data4 var9 = method4(var5, var4, var3);
         Pkg4.Data var10 = new Pkg4.Data(var2.field1 + var9.field1, var2.field2 + var9.field2, var2.field3 + var9.field3);
         boolean var11 = method9(var0, var8);
         HashSet var12 = new HashSet();
         HashSet var13 = new HashSet();
         boolean var14 = false;
         boolean var15 = false;

         for (PkgType var19 : PkgType.values()) {
            if (var19.getAxis() != var3.getAxis()) {
               Pkg4.Data4 var20 = new Pkg4.Data4(var9.field1 + var19.getStepX(), var9.field2 + var19.getStepY(), var9.field3 + var19.getStepZ());
               Pkg4.Data3 var21 = method5(var20, var4, var3);
               if (method7(var21, var7)) {
                  if (method8(var0, method3(var6, var21, var4, var3))) {
                     if (!var11 || method9(var0, method3(var6, var21, var4, var3))) {
                        var12.add(var19);
                     }
                  } else {
                     Pkg4.Data4 var22 = new Pkg4.Data4(var20.field1 + var19.getStepX(), var20.field2 + var19.getStepY(), var20.field3 + var19.getStepZ());
                     Pkg4.Data3 var23 = method5(var22, var4, var3);
                     if (!method7(var23, var7)) {
                        var23 = method5(var22, var4, var19);
                        if (method8(var0, method3(var6, var23, var4, var19)) && (!var11 || method9(var0, method3(var6, var23, var4, var19)))) {
                           var12.add(var19);
                        }
                     }
                  }
               } else {
                  var14 = true;
                  var21 = method5(var9, var4, var19);
                  if (method8(var0, method3(var6, var21, var4, var19))) {
                     var15 = true;
                     var12.add(var19);
                     var13.add(new PkgType[]{var3.getOpposite(), var19});
                  } else {
                     Pkg4.Data3 var25 = method5(
                        new Pkg4.Data4(var9.field1 - var3.getStepX(), var9.field2 - var3.getStepY(), var9.field3 - var3.getStepZ()), var4, var19
                     );
                     if (method8(var0, method3(var6, var25, var4, var19))) {
                        var15 = true;
                     }
                  }
               }
            }
         }

         if (!var14 || var15) {
            var12.add(var3.getOpposite());
         }

         var1.addAll(
            Pkg.method1(var0.bridge$getWidth(), var0.bridge$getHeight())
               .method2(var8.field1, var8.field2)
               .method5(var10.field1, var10.field2, var10.field3, 1.0F, var12.toArray(PkgType[]::new), var13.toArray(PkgType[][]::new))
               .method4()
         );
      }
   }

   private static boolean method7(Pkg4.Data3 var0, Pkg4.Data3 var1) {
      return var0.field1 >= 0 && var0.field1 < var1.field1 && var0.field2 >= 0 && var0.field2 < var1.field2;
   }

   private static boolean method8(AutoCloseableExtension var0, Pkg4.Data3 var1) {
      return var0.method1(var1.field1, var1.field2);
   }

   private static boolean method9(AutoCloseableExtension var0, Pkg4.Data3 var1) {
      return var0.method2(var1.field1, var1.field2);
   }

   private static class Data {
      private final float field1;
      private final float field2;
      private final float field3;

      @Generated
      public Data(float var1, float var2, float var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      @Generated
      public float getX() {
         return this.field1;
      }

      @Generated
      public float getY() {
         return this.field2;
      }

      @Generated
      public float getZ() {
         return this.field3;
      }

      @Generated
      @Override
      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof Pkg4.Data var2)) {
            return false;
         } else if (!var2.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getX(), var2.getX()) != 0) {
            return false;
         } else {
            return Float.compare(this.getY(), var2.getY()) != 0 ? false : Float.compare(this.getZ(), var2.getZ()) == 0;
         }
      }

      @Generated
      protected boolean canEqual(Object var1) {
         return var1 instanceof Pkg4.Data;
      }

      @Generated
      @Override
      public int hashCode() {
         byte var1 = 59;
         int var2 = 1;
         var2 = var2 * 59 + Float.floatToIntBits(this.getX());
         var2 = var2 * 59 + Float.floatToIntBits(this.getY());
         return var2 * 59 + Float.floatToIntBits(this.getZ());
      }

      @Generated
      @Override
      public String toString() {
         return "SolidPixelWrapper.Position(x=" + this.getX() + ", y=" + this.getY() + ", z=" + this.getZ() + ")";
      }
   }

   public static class Data2 {
      private final int field1;
      private final int field2;
      private final int field3;

      @Generated
      public Data2(int var1, int var2, int var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      @Generated
      public int getWidth() {
         return this.field1;
      }

      @Generated
      public int getHeight() {
         return this.field2;
      }

      @Generated
      public int getDepth() {
         return this.field3;
      }

      @Generated
      @Override
      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof Pkg4.Data2 var2)) {
            return false;
         } else if (!var2.canEqual(this)) {
            return false;
         } else if (this.getWidth() != var2.getWidth()) {
            return false;
         } else {
            return this.getHeight() != var2.getHeight() ? false : this.getDepth() == var2.getDepth();
         }
      }

      @Generated
      protected boolean canEqual(Object var1) {
         return var1 instanceof Pkg4.Data2;
      }

      @Generated
      @Override
      public int hashCode() {
         byte var1 = 59;
         int var2 = 1;
         var2 = var2 * 59 + this.getWidth();
         var2 = var2 * 59 + this.getHeight();
         return var2 * 59 + this.getDepth();
      }

      @Generated
      @Override
      public String toString() {
         return "SolidPixelWrapper.Dimensions(width=" + this.getWidth() + ", height=" + this.getHeight() + ", depth=" + this.getDepth() + ")";
      }
   }

   private static class Data3 {
      private final int field1;
      private final int field2;

      @Generated
      public Data3(int var1, int var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      @Generated
      public int getU() {
         return this.field1;
      }

      @Generated
      public int method1() {
         return this.field2;
      }

      @Generated
      @Override
      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof Pkg4.Data3 var2)) {
            return false;
         } else if (!var2.canEqual(this)) {
            return false;
         } else {
            return this.getU() != var2.getU() ? false : this.method1() == var2.method1();
         }
      }

      @Generated
      protected boolean canEqual(Object var1) {
         return var1 instanceof Pkg4.Data3;
      }

      @Generated
      @Override
      public int hashCode() {
         byte var1 = 59;
         int var2 = 1;
         var2 = var2 * 59 + this.getU();
         return var2 * 59 + this.method1();
      }

      @Generated
      @Override
      public String toString() {
         return "SolidPixelWrapper.UV(u=" + this.getU() + ", v=" + this.method1() + ")";
      }
   }

   private static class Data4 {
      private final int field1;
      private final int field2;
      private final int field3;

      @Generated
      public Data4(int var1, int var2, int var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      @Generated
      public int getX() {
         return this.field1;
      }

      @Generated
      public int getY() {
         return this.field2;
      }

      @Generated
      public int getZ() {
         return this.field3;
      }

      @Generated
      @Override
      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof Pkg4.Data4 var2)) {
            return false;
         } else if (!var2.canEqual(this)) {
            return false;
         } else if (this.getX() != var2.getX()) {
            return false;
         } else {
            return this.getY() != var2.getY() ? false : this.getZ() == var2.getZ();
         }
      }

      @Generated
      protected boolean canEqual(Object var1) {
         return var1 instanceof Pkg4.Data4;
      }

      @Generated
      @Override
      public int hashCode() {
         byte var1 = 59;
         int var2 = 1;
         var2 = var2 * 59 + this.getX();
         var2 = var2 * 59 + this.getY();
         return var2 * 59 + this.getZ();
      }

      @Generated
      @Override
      public String toString() {
         return "SolidPixelWrapper.VoxelPosition(x=" + this.getX() + ", y=" + this.getY() + ", z=" + this.getZ() + ")";
      }
   }
}
