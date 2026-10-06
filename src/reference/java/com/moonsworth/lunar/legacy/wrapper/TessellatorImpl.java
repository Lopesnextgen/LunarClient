package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.TessellatorBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.joml.Vector3d;

public final class TessellatorImpl extends Tessellator implements TessellatorBridge {
   private static TessellatorImpl field1;
   private double field2;

   private TessellatorImpl() {
      this(2097152);
   }

   private TessellatorImpl(int number1) {
      super(number1);
   }

   public static TessellatorImpl method2() {
      if (field1 == null) {
         field1 = new TessellatorImpl();
      }

      return field1;
   }

   public void bridge$setTranslation(double value1, double value3, double value5) {
      if (Ref.MC_VERSION >= 1) {
         this.worldRenderer.setTranslation(value1, value3, value5);
      } else {
         this.setTranslation(value1, value3, value5);
      }
   }

   public void method4() {
      if (Ref.MC_VERSION >= 1) {
         this.worldRenderer.begin(1, DefaultVertexFormats.POSITION_COLOR);
      } else {
         this.startDrawing$v1_7(1);
      }
   }

   public void method3(double value1) {
      this.field2 = value1;
   }

   public void method4(Vector3d vector3d1, int number2, int number3) {
      this.method5(vector3d1, vector3d1, number2, number3);
   }

   public void method5(Vector3d vector3d1, Vector3d vector3d2, int number3, int number4) {
      this.method6(vector3d1, vector3d2, number3, number4, this.field2);
   }

   private void method6(Vector3d vector3d1, Vector3d vector3d2, int number3, int number4, double value5) {
      int number7 = Ref.MC_VERSION >= 1 ? this.worldRenderer.getDrawMode() : this.drawMode$v1_7;
      if (number7 != -1 && number3 != 0) {
         double value8 = vector3d1.x() - value5;
         double value10 = vector3d1.y() - value5;
         double value12 = vector3d1.z() - value5;
         double value14 = vector3d2.x() + 1.0 + value5;
         double value16 = vector3d2.y() + 1.0 + value5;
         double value18 = vector3d2.z() + 1.0 + value5;
         switch (number7) {
            case 1:
               this.method9(value8, value10, value12, value14, value16, value18, number3, number4);
               break;
            case 7:
               this.method7(value8, value10, value12, value14, value16, value18, number3, number4);
               break;
            default:
               throw new IllegalStateException("Unsupported mode for " + number7);
         }
      }
   }

   private void method7(double value1, double value3, double value5, double value7, double value9, double value11, int number13, int number14) {
      int number15 = number14 >>> 24 & 0xFF;
      int number16 = number14 >>> 16 & 0xFF;
      int number17 = number14 >>> 8 & 0xFF;
      int number18 = number14 & 0xFF;
      this.method8(value1, value3, value5, value7, value9, value11, number13, number15, number16, number17, number18);
   }

   private void method8(double value1, double value3, double value5, double value7, double value9, double value11, int number13, int number14, int number15, int number16, int number17) {
      if ((number13 & 1) != 0) {
         this.method13(value7, value3, value5, number15, number16, number17, number14);
         this.method13(value7, value3, value11, number15, number16, number17, number14);
         this.method13(value1, value3, value11, number15, number16, number17, number14);
         this.method13(value1, value3, value5, number15, number16, number17, number14);
      }

      if ((number13 & 2) != 0) {
         this.method13(value7, value9, value5, number15, number16, number17, number14);
         this.method13(value1, value9, value5, number15, number16, number17, number14);
         this.method13(value1, value9, value11, number15, number16, number17, number14);
         this.method13(value7, value9, value11, number15, number16, number17, number14);
      }

      if ((number13 & 4) != 0) {
         this.method13(value7, value3, value5, number15, number16, number17, number14);
         this.method13(value1, value3, value5, number15, number16, number17, number14);
         this.method13(value1, value9, value5, number15, number16, number17, number14);
         this.method13(value7, value9, value5, number15, number16, number17, number14);
      }

      if ((number13 & 8) != 0) {
         this.method13(value1, value3, value11, number15, number16, number17, number14);
         this.method13(value7, value3, value11, number15, number16, number17, number14);
         this.method13(value7, value9, value11, number15, number16, number17, number14);
         this.method13(value1, value9, value11, number15, number16, number17, number14);
      }

      if ((number13 & 16) != 0) {
         this.method13(value1, value3, value5, number15, number16, number17, number14);
         this.method13(value1, value3, value11, number15, number16, number17, number14);
         this.method13(value1, value9, value11, number15, number16, number17, number14);
         this.method13(value1, value9, value5, number15, number16, number17, number14);
      }

      if ((number13 & 32) != 0) {
         this.method13(value7, value3, value11, number15, number16, number17, number14);
         this.method13(value7, value3, value5, number15, number16, number17, number14);
         this.method13(value7, value9, value5, number15, number16, number17, number14);
         this.method13(value7, value9, value11, number15, number16, number17, number14);
      }
   }

   private void method9(double value1, double value3, double value5, double value7, double value9, double value11, int number13, int number14) {
      int number15 = number14 >>> 24 & 0xFF;
      int number16 = number14 >>> 16 & 0xFF;
      int number17 = number14 >>> 8 & 0xFF;
      int number18 = number14 & 0xFF;
      this.method10(value1, value3, value5, value7, value9, value11, number13, number15, number16, number17, number18);
   }

   private void method10(double value1, double value3, double value5, double value7, double value9, double value11, int number13, int number14, int number15, int number16, int number17) {
      if ((number13 & 17) != 0) {
         this.method13(value1, value3, value5, number15, number16, number17, number14);
         this.method13(value1, value3, value11, number15, number16, number17, number14);
      }

      if ((number13 & 18) != 0) {
         this.method13(value1, value9, value5, number15, number16, number17, number14);
         this.method13(value1, value9, value11, number15, number16, number17, number14);
      }

      if ((number13 & 33) != 0) {
         this.method13(value7, value3, value5, number15, number16, number17, number14);
         this.method13(value7, value3, value11, number15, number16, number17, number14);
      }

      if ((number13 & 34) != 0) {
         this.method13(value7, value9, value5, number15, number16, number17, number14);
         this.method13(value7, value9, value11, number15, number16, number17, number14);
      }

      if ((number13 & 5) != 0) {
         this.method13(value1, value3, value5, number15, number16, number17, number14);
         this.method13(value7, value3, value5, number15, number16, number17, number14);
      }

      if ((number13 & 6) != 0) {
         this.method13(value1, value9, value5, number15, number16, number17, number14);
         this.method13(value7, value9, value5, number15, number16, number17, number14);
      }

      if ((number13 & 9) != 0) {
         this.method13(value1, value3, value11, number15, number16, number17, number14);
         this.method13(value7, value3, value11, number15, number16, number17, number14);
      }

      if ((number13 & 10) != 0) {
         this.method13(value1, value9, value11, number15, number16, number17, number14);
         this.method13(value7, value9, value11, number15, number16, number17, number14);
      }

      if ((number13 & 20) != 0) {
         this.method13(value1, value3, value5, number15, number16, number17, number14);
         this.method13(value1, value9, value5, number15, number16, number17, number14);
      }

      if ((number13 & 36) != 0) {
         this.method13(value7, value3, value5, number15, number16, number17, number14);
         this.method13(value7, value9, value5, number15, number16, number17, number14);
      }

      if ((number13 & 24) != 0) {
         this.method13(value1, value3, value11, number15, number16, number17, number14);
         this.method13(value1, value9, value11, number15, number16, number17, number14);
      }

      if ((number13 & 40) != 0) {
         this.method13(value7, value3, value11, number15, number16, number17, number14);
         this.method13(value7, value9, value11, number15, number16, number17, number14);
      }
   }

   public void method2(int number1) {
      if (Ref.MC_VERSION >= 1) {
         this.worldRenderer.begin(number1, DefaultVertexFormats.POSITION_COLOR);
      } else {
         this.startDrawing$v1_7(number1);
      }
   }

   public void method3() {
      if (Ref.MC_VERSION >= 1) {
         this.draw();
      } else {
         this.draw();
      }
   }

   public boolean bridge$isDrawing() {
      return Ref.MC_VERSION >= 1 ? this.worldRenderer.isDrawing : this.isDrawing$v1_7;
   }

   private void method13(double value1, double value3, double value5, int number7, int number8, int number9, int number10) {
      if (Ref.MC_VERSION >= 1) {
         this.worldRenderer.pos(value1, value3, value5).color(number7, number8, number9, number10).endVertex();
      } else {
         this.setColorRGBA$v1_7(number7, number8, number9, number10);
         this.addVertex$v1_7(value1, value3, value5);
      }
   }
}
