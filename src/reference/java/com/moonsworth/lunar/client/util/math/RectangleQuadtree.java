package com.moonsworth.lunar.client.util.math;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import java.util.ArrayList;
import java.util.List;
import com.moonsworth.lunar.client.ui.GuiResolution;

public class RectangleQuadtree {
   private final IntRectangle field1;
   private List<IntRectangle> nodes = null;
   private boolean field2 = false;
   private RectangleQuadtree[] field3 = null;

   public RectangleQuadtree(int number1, int number2, int number3, int number4) {
      this.field1 = IntRectangle.method3(number1, number2, number3, number4);
   }

   public boolean method1(IntRectangle threadmoduledump701) {
      if (!this.field1.method4(threadmoduledump701)) {
         return false;
      }

      if (this.field2) {
         return true;
      }

      if (this.nodes != null) {
         for (IntRectangle threadmoduledump703 : this.nodes) {
            if (threadmoduledump703.method4(threadmoduledump701)) {
               return true;
            }
         }
      }

      if (this.field3 != null) {
         for (RectangleQuadtree threadmoduledump75 : this.field3) {
            if (threadmoduledump75.method1(threadmoduledump701)) {
               return true;
            }
         }
      }

      return false;
   }

   public void method2(IntRectangle threadmoduledump701) {
      if (!this.field2) {
         if (threadmoduledump701.method5(this.field1)) {
            this.field3 = null;
            this.field2 = true;
            this.nodes = null;
         } else if (this.field3 == null) {
            if (this.nodes == null) {
               this.nodes = new ArrayList<>(5);
            }

            this.nodes.add(threadmoduledump701);
            if (this.nodes.size() > 5 && (this.field1.method1() > 5 || this.field1.method2() > 5)) {
               this.method3();
            }
         } else {
            this.method4(threadmoduledump701);
         }
      }
   }

   private void method3() {
      int number1 = (int)Math.ceil(this.field1.method1() / 2.0);
      int number2 = (int)Math.ceil(this.field1.method2() / 2.0);
      int number3 = this.field1.method1() - number1;
      int number4 = this.field1.method2() - number2;
      this.field3 = new RectangleQuadtree[]{
         new RectangleQuadtree(this.field1.method10(), this.field1.method11(), number1, number2),
         new RectangleQuadtree(this.field1.method10() + number1, this.field1.method11(), number3, number2),
         new RectangleQuadtree(this.field1.method10(), this.field1.method11() + number2, number1, number4),
         new RectangleQuadtree(this.field1.method10() + number3, this.field1.method11() + number4, number3, number4)
      };
      List list5 = this.nodes;
      this.nodes = null;

      for (IntRectangle threadmoduledump707 : list5) {
         this.method4(threadmoduledump707);
      }
   }

   private void method4(IntRectangle threadmoduledump701) {
      boolean flag2 = this.field3[0].field1.method4(threadmoduledump701);
      boolean flag3 = this.field3[1].field1.method4(threadmoduledump701);
      boolean flag4 = this.field3[2].field1.method4(threadmoduledump701);
      boolean flag5 = this.field3[3].field1.method4(threadmoduledump701);
      int index6 = 0;
      if (flag2) {
         index6++;
      }

      if (flag3) {
         index6++;
      }

      if (flag4) {
         index6++;
      }

      if (flag5) {
         index6++;
      }

      if (index6 >= 2) {
         if (this.nodes == null) {
            this.nodes = new ArrayList<>();
         }

         this.nodes.add(threadmoduledump701);
      } else {
         if (flag2) {
            this.field3[0].method2(threadmoduledump701);
         }

         if (flag3) {
            this.field3[1].method2(threadmoduledump701);
         }

         if (flag4) {
            this.field3[2].method2(threadmoduledump701);
         }

         if (flag5) {
            this.field3[3].method2(threadmoduledump701);
         }
      }
   }

   public static class Data {
      private final RectangleQuadtree field1;
      private final List<IntRectangle> field2 = new ArrayList<>();

      public Data() {
         GuiResolution threadmoduledump711 = LcuiScreen.method151();
         if (threadmoduledump711 == null) {
            this.field1 = new RectangleQuadtree(0, 0, 1920, 1080);
         } else {
            this.field1 = new RectangleQuadtree(0, 0, LcuiScreen.method151().getScaledWidth(), LcuiScreen.method151().getScaledHeight());
         }
      }

      public void method1(IntRectangle threadmoduledump701) {
         if (this.field1.field1.method4(threadmoduledump701)) {
            this.field1.method2(threadmoduledump701);
         }

         if (!this.field1.field1.method5(threadmoduledump701)) {
            this.field2.add(threadmoduledump701);
         }
      }

      public boolean method2(IntRectangle threadmoduledump701) {
         if (this.field1.method1(threadmoduledump701)) {
            return true;
         }

         if (!this.field1.field1.method5(threadmoduledump701)) {
            for (IntRectangle threadmoduledump703 : this.field2) {
               if (threadmoduledump703.method4(threadmoduledump701)) {
                  return true;
               }
            }
         }

         return false;
      }
   }
}
