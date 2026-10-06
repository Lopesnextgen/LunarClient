package com.moonsworth.lunar.client.util.math;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import java.util.ArrayList;
import java.util.List;
import com.moonsworth.lunar.client.ui.GuiResolution;

public class WeightedQuadtree {
   private final WeightedQuadtree.QuadTreeEntry field1;
   private List<WeightedQuadtree.QuadTreeEntry> nodes = null;
   private float field2;
   private WeightedQuadtree[] field3 = null;

   public WeightedQuadtree(int number1, int number2, int number3, int number4, float value5) {
      this.weight = value5;
      this.entry = new WeightedQuadtree.QuadTreeEntry(IntRectangle.method3(number1, number2, number3, number4), value5);
   }

   public float getWeight(IntRectangle threadmoduledump701) {
      if (!this.entry.field1.method4(threadmoduledump701)) {
         return Float.MIN_VALUE;
      }

      if (threadmoduledump701.method5(this.entry.field1)) {
         return this.weight;
      }

      float value2 = this.entry.field2;
      if (this.nodes != null) {
         for (WeightedQuadtree.QuadTreeEntry data24 : this.nodes) {
            if (data24.field1.method4(threadmoduledump701)) {
               value2 = Math.max(value2, data24.field2);
            }
         }
      }

      if (this.children != null) {
         for (WeightedQuadtree threadmoduledump476 : this.children) {
            value2 = Math.max(value2, threadmoduledump476.method1(threadmoduledump701));
         }
      }

      return value2;
   }

   private void insert(WeightedQuadtree.QuadTreeEntry data21) {
      this.weight = Math.max(this.weight, data21.field2);
      if (this.children == null) {
         if (this.nodes == null) {
            this.nodes = new ArrayList<>(5);
         }

         this.nodes.add(data21);
         if (this.nodes.size() > 5 && (this.entry.field1.method1() > 5 || this.entry.field1.method2() > 5)) {
            this.split();
         }
      } else {
         this.insertIntoChild(data21);
      }
   }

   private void split() {
      int number1 = (int)Math.ceil(this.entry.field1.method1() / 2.0);
      int number2 = (int)Math.ceil(this.entry.field1.method2() / 2.0);
      int number3 = this.entry.field1.method1() - number1;
      int number4 = this.entry.field1.method2() - number2;
      this.children = new WeightedQuadtree[]{
         new WeightedQuadtree(this.entry.field1.method10(), this.entry.field1.method11(), number1, number2, this.entry.field2),
         new WeightedQuadtree(this.entry.field1.method10() + number1, this.entry.field1.method11(), number3, number2, this.entry.field2),
         new WeightedQuadtree(this.entry.field1.method10(), this.entry.field1.method11() + number2, number1, number4, this.entry.field2),
         new WeightedQuadtree(this.entry.field1.method10() + number3, this.entry.field1.method11() + number4, number3, number4, this.entry.field2)
      };
      List list5 = this.nodes;
      this.nodes = null;

      for (WeightedQuadtree.QuadTreeEntry data27 : list5) {
         this.insertIntoChild(data27);
      }
   }

   private void insertIntoChild(WeightedQuadtree.QuadTreeEntry data21) {
      boolean flag2 = this.children[0].field1.field1.method4(data21.field1);
      boolean flag3 = this.children[1].field1.field1.method4(data21.field1);
      boolean flag4 = this.children[2].field1.field1.method4(data21.field1);
      boolean flag5 = this.children[3].field1.field1.method4(data21.field1);
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

         this.nodes.add(data21);
      } else {
         if (flag2) {
            this.children[0].method2(data21);
         }

         if (flag3) {
            this.children[1].method2(data21);
         }

         if (flag4) {
            this.children[2].method2(data21);
         }

         if (flag5) {
            this.children[3].method2(data21);
         }
      }
   }

   public static class Data {
      private final WeightedQuadtree field1;
      private final List<WeightedQuadtree.QuadTreeEntry> field2 = new ArrayList<>();
      private final float field3;
      private float field4;

      public Data(float value1) {
         this.children = value1;
         this.field4 = value1;
         GuiResolution threadmoduledump712 = LcuiScreen.method151();
         if (threadmoduledump712 == null) {
            this.entry = new WeightedQuadtree(0, 0, 1920, 1080, value1);
         } else {
            this.entry = new WeightedQuadtree(0, 0, LcuiScreen.method151().getScaledWidth(), LcuiScreen.method151().getScaledHeight(), value1);
         }
      }

      public void getWeight(IntRectangle threadmoduledump701, float value2) {
         WeightedQuadtree.QuadTreeEntry data23 = new WeightedQuadtree.QuadTreeEntry(threadmoduledump701, value2);
         if (this.entry.field1.field1.method4(data23.field1)) {
            this.entry.method2(data23);
         }

         if (!this.entry.field1.field1.method5(data23.field1)) {
            this.weight.add(data23);
         }

         this.field4 = Math.max(this.field4, data23.field2);
      }

      public float insert(IntRectangle threadmoduledump701) {
         float value2 = this.children;
         value2 = Math.max(value2, this.entry.method1(threadmoduledump701));
         if (!this.entry.field1.field1.method5(threadmoduledump701)) {
            for (WeightedQuadtree.QuadTreeEntry data24 : this.weight) {
               if (data24.field1.method4(threadmoduledump701)) {
                  value2 = Math.max(value2, data24.field2);
               }
            }
         }

         return value2;
      }

      public float max() {
         return this.field4;
      }
   }

   private class QuadTreeEntry {
      private final IntRectangle field1;
      private final float field2;

      private QuadTreeEntry(IntRectangle threadmoduledump701, float value2) {
         this.entry = threadmoduledump701;
         this.weight = value2;
      }

      public IntRectangle getWeight() {
         return this.entry;
      }

      public float value() {
         return this.weight;
      }
   }
}
