package com.moonsworth.lunar.client.cosmetics.skin;

import com.moonsworth.lunar.bridge.AutoCloseableExtension;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3;

public class SolidPixelWrapper {
   private static final float field1 = 1.0F;

   public SolidPixelWrapper() {
   }

   public static Pkg3 method1(AutoCloseableExtension autocloseableextension0, int number1, int number2, int number3, int number4, int number5, boolean flag6, float value7) {
      ArrayList list8 = new ArrayList();
      float value9 = -number1 / 2.0F;
      float value10 = flag6 ? value7 : -number2 + value7;
      float value11 = -number3 / 2.0F;
      SolidPixelWrapper.Origin data12 = new SolidPixelWrapper.Origin(value9, value10, value11);
      SolidPixelWrapper.VoxelDimensions data213 = new SolidPixelWrapper.VoxelDimensions(number1, number2, number3);
      SolidPixelWrapper.TextureUV data314 = new SolidPixelWrapper.TextureUV(number4, number5);

      try {
         for (CubeDirection pkgtype18 : CubeDirection.values()) {
            SolidPixelWrapper.TextureUV data319 = method2(data213, pkgtype18);

            for (int index20 = 0; index20 < data319.field1; index20++) {
               for (int index21 = 0; index21 < data319.field2; index21++) {
                  method6(autocloseableextension0, list8, data12, pkgtype18, data213, new SolidPixelWrapper.TextureUV(index20, index21), data314, data319);
               }
            }
         }
      } catch (Exception exception22) {
         LunarLogger.method7("SkinLayers3D: " + exception22.getMessage(), new Object[0]);
         if (!LunarBuildData.field4) {
            exception22.printStackTrace();
         }

         return new Pkg3(new ArrayList<>());
      }

      return new Pkg3(list8);
   }

   private static SolidPixelWrapper.TextureUV method2(SolidPixelWrapper.VoxelDimensions data20, CubeDirection pkgtype1) {
      return switch (pkgtype1) {
         case DOWN, UP -> new SolidPixelWrapper.TextureUV(data20.field1, data20.field3);
         case NORTH, SOUTH -> new SolidPixelWrapper.TextureUV(data20.field1, data20.field2);
         case WEST, EAST -> new SolidPixelWrapper.TextureUV(data20.field3, data20.field2);
      };
   }

   private static SolidPixelWrapper.TextureUV method3(SolidPixelWrapper.TextureUV data30, SolidPixelWrapper.TextureUV data31, SolidPixelWrapper.VoxelDimensions data22, CubeDirection pkgtype3) {
      return switch (pkgtype3) {
         case DOWN -> new SolidPixelWrapper.TextureUV(data30.field1 + data22.field3 + data31.field1, data30.field2 + data31.field2);
         case UP -> new SolidPixelWrapper.TextureUV(data30.field1 + data22.field1 + data22.field3 + data31.field1, data30.field2 + data31.field2);
         case NORTH -> new SolidPixelWrapper.TextureUV(data30.field1 + data22.field3 + data31.field1, data30.field2 + data22.field3 + data31.field2);
         case SOUTH -> new SolidPixelWrapper.TextureUV(data30.field1 + data22.field3 + data22.field1 + data22.field3 + data31.field1, data30.field2 + data22.field3 + data31.field2);
         case WEST -> new SolidPixelWrapper.TextureUV(data30.field1 + data31.field1, data30.field2 + data22.field3 + data31.field2);
         case EAST -> new SolidPixelWrapper.TextureUV(data30.field1 + data22.field3 + data22.field1 + data31.field1, data30.field2 + data22.field3 + data31.field2);
      };
   }

   private static SolidPixelWrapper.VoxelPosition method4(SolidPixelWrapper.TextureUV data30, SolidPixelWrapper.VoxelDimensions data21, CubeDirection pkgtype2) {
      return switch (pkgtype2) {
         case DOWN -> new SolidPixelWrapper.VoxelPosition(data30.field1, 0, data21.field3 - 1 - data30.field2);
         case UP -> new SolidPixelWrapper.VoxelPosition(data30.field1, data21.field2 - 1, data21.field3 - 1 - data30.field2);
         case NORTH -> new SolidPixelWrapper.VoxelPosition(data30.field1 + 0, data30.field2, 0);
         case SOUTH -> new SolidPixelWrapper.VoxelPosition(data21.field1 - 1 - data30.field1, data30.field2, data21.field3 - 1);
         case WEST -> new SolidPixelWrapper.VoxelPosition(0, data30.field2, data21.field3 - 1 - data30.field1);
         case EAST -> new SolidPixelWrapper.VoxelPosition(data21.field1 - 1, data30.field2, data30.field1 + 0);
      };
   }

   private static SolidPixelWrapper.TextureUV method5(SolidPixelWrapper.VoxelPosition data40, SolidPixelWrapper.VoxelDimensions data21, CubeDirection pkgtype2) {
      return switch (pkgtype2) {
         case DOWN, UP -> new SolidPixelWrapper.TextureUV(data40.field1, data21.field3 - 1 - data40.field3);
         case NORTH -> new SolidPixelWrapper.TextureUV(data40.field1 + 0, data40.field2);
         case SOUTH -> new SolidPixelWrapper.TextureUV(data21.field1 - 1 - data40.field1, data40.field2);
         case WEST -> new SolidPixelWrapper.TextureUV(data21.field3 - 1 - data40.field3, data40.field2);
         case EAST -> new SolidPixelWrapper.TextureUV(data40.field3 + 0, data40.field2);
      };
   }

   private static void method6(
      AutoCloseableExtension autocloseableextension0, List<CustomizableCube> list1, SolidPixelWrapper.Origin data2, CubeDirection pkgtype3, SolidPixelWrapper.VoxelDimensions data24, SolidPixelWrapper.TextureUV data35, SolidPixelWrapper.TextureUV data36, SolidPixelWrapper.TextureUV data37
   ) {
      SolidPixelWrapper.TextureUV data38 = method3(data36, data35, data24, pkgtype3);
      if (method8(autocloseableextension0, data38)) {
         SolidPixelWrapper.VoxelPosition data49 = method4(data35, data24, pkgtype3);
         SolidPixelWrapper.Origin data10 = new SolidPixelWrapper.Origin(data2.field1 + data49.field1, data2.field2 + data49.field2, data2.field3 + data49.field3);
         boolean flag11 = method9(autocloseableextension0, data38);
         HashSet set12 = new HashSet();
         HashSet set13 = new HashSet();
         boolean flag14 = false;
         boolean flag15 = false;

         for (CubeDirection pkgtype19 : CubeDirection.values()) {
            if (pkgtype19.getAxis() != pkgtype3.getAxis()) {
               SolidPixelWrapper.VoxelPosition data420 = new SolidPixelWrapper.VoxelPosition(data49.field1 + pkgtype19.getStepX(), data49.field2 + pkgtype19.getStepY(), data49.field3 + pkgtype19.getStepZ());
               SolidPixelWrapper.TextureUV data321 = method5(data420, data24, pkgtype3);
               if (method7(data321, data37)) {
                  if (method8(autocloseableextension0, method3(data36, data321, data24, pkgtype3))) {
                     if (!flag11 || method9(autocloseableextension0, method3(data36, data321, data24, pkgtype3))) {
                        set12.add(pkgtype19);
                     }
                  } else {
                     SolidPixelWrapper.VoxelPosition data422 = new SolidPixelWrapper.VoxelPosition(data420.field1 + pkgtype19.getStepX(), data420.field2 + pkgtype19.getStepY(), data420.field3 + pkgtype19.getStepZ());
                     SolidPixelWrapper.TextureUV data323 = method5(data422, data24, pkgtype3);
                     if (!method7(data323, data37)) {
                        data323 = method5(data422, data24, pkgtype19);
                        if (method8(autocloseableextension0, method3(data36, data323, data24, pkgtype19)) && (!flag11 || method9(autocloseableextension0, method3(data36, data323, data24, pkgtype19)))) {
                           set12.add(pkgtype19);
                        }
                     }
                  }
               } else {
                  flag14 = true;
                  data321 = method5(data49, data24, pkgtype19);
                  if (method8(autocloseableextension0, method3(data36, data321, data24, pkgtype19))) {
                     flag15 = true;
                     set12.add(pkgtype19);
                     set13.add(new CubeDirection[]{pkgtype3.getOpposite(), pkgtype19});
                  } else {
                     SolidPixelWrapper.TextureUV data325 = method5(
                        new SolidPixelWrapper.VoxelPosition(data49.field1 - pkgtype3.getStepX(), data49.field2 - pkgtype3.getStepY(), data49.field3 - pkgtype3.getStepZ()), data24, pkgtype19
                     );
                     if (method8(autocloseableextension0, method3(data36, data325, data24, pkgtype19))) {
                        flag15 = true;
                     }
                  }
               }
            }
         }

         if (!flag14 || flag15) {
            set12.add(pkgtype3.getOpposite());
         }

         list1.addAll(
            CubeListBuilder.method1(autocloseableextension0.bridge$getWidth(), autocloseableextension0.bridge$getHeight())
               .method2(data38.field1, data38.field2)
               .method5(data10.field1, data10.field2, data10.field3, 1.0F, set12.toArray(CubeDirection[]::new), set13.toArray(CubeDirection[][]::new))
               .method4()
         );
      }
   }

   private static boolean method7(SolidPixelWrapper.TextureUV data30, SolidPixelWrapper.TextureUV data31) {
      return data30.field1 >= 0 && data30.field1 < data31.field1 && data30.field2 >= 0 && data30.field2 < data31.field2;
   }

   private static boolean method8(AutoCloseableExtension autocloseableextension0, SolidPixelWrapper.TextureUV data31) {
      return autocloseableextension0.method1(data31.field1, data31.field2);
   }

   private static boolean method9(AutoCloseableExtension autocloseableextension0, SolidPixelWrapper.TextureUV data31) {
      return autocloseableextension0.method2(data31.field1, data31.field2);
   }

   private static class Origin {
      private final float field1;
      private final float field2;
      private final float field3;

      @Generated
      public Origin(float value1, float value2, float value3) {
         this.field1 = value1;
         this.field2 = value2;
         this.field3 = value3;
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
      public boolean equals(Object obj1) {
         if (obj1 == this) {
            return true;
         } else if (!(obj1 instanceof SolidPixelWrapper.Origin data2)) {
            return false;
         } else if (!data2.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getX(), data2.getX()) != 0) {
            return false;
         } else {
            return Float.compare(this.getY(), data2.getY()) != 0 ? false : Float.compare(this.getZ(), data2.getZ()) == 0;
         }
      }

      @Generated
      protected boolean canEqual(Object obj1) {
         return obj1 instanceof SolidPixelWrapper.Origin;
      }

      @Generated
      @Override
      public int hashCode() {
         byte number1 = 59;
         int number2 = 1;
         number2 = number2 * 59 + Float.floatToIntBits(this.getX());
         number2 = number2 * 59 + Float.floatToIntBits(this.getY());
         return number2 * 59 + Float.floatToIntBits(this.getZ());
      }

      @Generated
      @Override
      public String toString() {
         return "SolidPixelWrapper.Position(x=" + this.getX() + ", y=" + this.getY() + ", z=" + this.getZ() + ")";
      }
   }

   public static class VoxelDimensions {
      private final int field1;
      private final int field2;
      private final int field3;

      @Generated
      public VoxelDimensions(int number1, int number2, int number3) {
         this.field1 = number1;
         this.field2 = number2;
         this.field3 = number3;
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
      public boolean equals(Object obj1) {
         if (obj1 == this) {
            return true;
         } else if (!(obj1 instanceof SolidPixelWrapper.VoxelDimensions data22)) {
            return false;
         } else if (!data22.canEqual(this)) {
            return false;
         } else if (this.getWidth() != data22.getWidth()) {
            return false;
         } else {
            return this.getHeight() != data22.getHeight() ? false : this.getDepth() == data22.getDepth();
         }
      }

      @Generated
      protected boolean canEqual(Object obj1) {
         return obj1 instanceof SolidPixelWrapper.VoxelDimensions;
      }

      @Generated
      @Override
      public int hashCode() {
         byte number1 = 59;
         int number2 = 1;
         number2 = number2 * 59 + this.getWidth();
         number2 = number2 * 59 + this.getHeight();
         return number2 * 59 + this.getDepth();
      }

      @Generated
      @Override
      public String toString() {
         return "SolidPixelWrapper.Dimensions(width=" + this.getWidth() + ", height=" + this.getHeight() + ", depth=" + this.getDepth() + ")";
      }
   }

   private static class TextureUV {
      private final int field1;
      private final int field2;

      @Generated
      public TextureUV(int number1, int number2) {
         this.field1 = number1;
         this.field2 = number2;
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
      public boolean equals(Object obj1) {
         if (obj1 == this) {
            return true;
         } else if (!(obj1 instanceof SolidPixelWrapper.TextureUV data32)) {
            return false;
         } else if (!data32.canEqual(this)) {
            return false;
         } else {
            return this.getU() != data32.getU() ? false : this.method1() == data32.method1();
         }
      }

      @Generated
      protected boolean canEqual(Object obj1) {
         return obj1 instanceof SolidPixelWrapper.TextureUV;
      }

      @Generated
      @Override
      public int hashCode() {
         byte number1 = 59;
         int number2 = 1;
         number2 = number2 * 59 + this.getU();
         return number2 * 59 + this.method1();
      }

      @Generated
      @Override
      public String toString() {
         return "SolidPixelWrapper.UV(u=" + this.getU() + ", v=" + this.method1() + ")";
      }
   }

   private static class VoxelPosition {
      private final int field1;
      private final int field2;
      private final int field3;

      @Generated
      public VoxelPosition(int number1, int number2, int number3) {
         this.field1 = number1;
         this.field2 = number2;
         this.field3 = number3;
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
      public boolean equals(Object obj1) {
         if (obj1 == this) {
            return true;
         } else if (!(obj1 instanceof SolidPixelWrapper.VoxelPosition data42)) {
            return false;
         } else if (!data42.canEqual(this)) {
            return false;
         } else if (this.getX() != data42.getX()) {
            return false;
         } else {
            return this.getY() != data42.getY() ? false : this.getZ() == data42.getZ();
         }
      }

      @Generated
      protected boolean canEqual(Object obj1) {
         return obj1 instanceof SolidPixelWrapper.VoxelPosition;
      }

      @Generated
      @Override
      public int hashCode() {
         byte number1 = 59;
         int number2 = 1;
         number2 = number2 * 59 + this.getX();
         number2 = number2 * 59 + this.getY();
         return number2 * 59 + this.getZ();
      }

      @Generated
      @Override
      public String toString() {
         return "SolidPixelWrapper.VoxelPosition(x=" + this.getX() + ", y=" + this.getY() + ", z=" + this.getZ() + ")";
      }
   }
}
