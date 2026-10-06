package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.files.ValuePair;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;

public class RouteSection {
   private final DungeonRoute field1;
   private List<int[]> field2 = new ArrayList<>();
   private List<ValuePair<NameplateType, int[]>> field3 = new ArrayList<>();

   public RouteSection(RouteSection holograms71, DungeonRoute holograms22) {
      this.field1 = holograms22;
      this.field2 = holograms71.field2.stream().map(arg0 -> (int[])((int[])arg0).clone()).collect(Collectors.toList());
      this.field3 = holograms71.field3
         .stream()
         .map(arg0 -> ValuePair.method1((NameplateType)arg0.field1, (int[])((int[])arg0.field2).clone()))
         .collect(Collectors.toList());
   }

   public void method1(NameplateType nameplatetype1, int[] items2) {
      this.field3.add(ValuePair.method1(nameplatetype1, items2));
   }

   public void method2(int[] items1) {
      if (!this.field2.isEmpty()) {
         int[] items2 = this.field2.get(this.field2.size() - 1);
         if (items2[0] == items1[0] && items2[1] == items1[1] && items2[2] == items1[2]) {
            return;
         }

         if (this.field2.size() >= 2) {
            int[] items3 = this.field2.get(this.field2.size() - 2);
            double[] items4 = new double[]{items3[0] - items2[0], items3[1] - items2[1], items3[2] - items2[2]};
            double[] items5 = new double[]{items3[0] - items1[0], items3[1] - items1[1], items3[2] - items1[2]};

            for (double[] items9 : new double[][]{items4, items5}) {
               double value10 = Math.sqrt(Math.pow(items9[0], 2.0) + Math.pow(items9[1], 2.0) + Math.pow(items9[2], 2.0));
               items9[0] /= value10;
               items9[1] /= value10;
               items9[2] /= value10;
            }

            if (MathUtils.method18(items5[0], items4[0], 0.01)
               && MathUtils.method18(items5[1], items4[1], 0.01)
               && MathUtils.method18(items5[2], items4[2], 0.01)) {
               items2[0] = items1[0];
               items2[1] = items1[1];
               items2[2] = items1[2];
               return;
            }
         }

         double value12 = Math.pow(items2[0] - items1[0], 2.0) + Math.pow(items2[1] - items1[1], 2.0) + Math.pow(items2[2] - items1[2], 2.0);
         if (value12 < 3.61) {
            return;
         }
      }

      this.field2.add(items1);
   }

   public void method3(DataOutputStream output1) {
      output1.writeInt(this.field2.size());

      for (int[] items3 : this.field2) {
         output1.writeInt(items3[0]);
         output1.writeInt(items3[1]);
         output1.writeInt(items3[2]);
      }

      output1.writeInt(this.field3.size());

      for (ValuePair files6_26 : this.field3) {
         output1.writeInt(((NameplateType)files6_26.field1).ordinal());
         int[] items4 = (int[])files6_26.field2;
         output1.writeInt(items4[0]);
         output1.writeInt(items4[1]);
         output1.writeInt(items4[2]);
      }
   }

   public static RouteSection method4(DataInputStream input0, DungeonRoute holograms21) {
      RouteSection holograms72 = new RouteSection(holograms21);
      int number3 = input0.readInt();

      for (int index4 = 0; index4 < number3; index4++) {
         int number5 = input0.readInt();
         int number6 = input0.readInt();
         int number7 = input0.readInt();
         holograms72.field2.add(new int[]{number5, number6, number7});
      }

      int number11 = input0.readInt();

      for (int index12 = 0; index12 < number11; index12++) {
         int index13 = input0.readInt();
         NameplateType nameplatetype14 = NameplateType.values()[index13];
         int number8 = input0.readInt();
         int number9 = input0.readInt();
         int number10 = input0.readInt();
         holograms72.field3.add(ValuePair.method1(nameplatetype14, new int[]{number8, number9, number10}));
      }

      return holograms72;
   }

   public boolean method5(NameplateType nameplatetype1, int[] items2) {
      return this.field3.stream().anyMatch(arg2x -> arg2x.field1 == nameplatetype1 && Arrays.equals((int[])arg2x.field2, items2));
   }

   public int getIndex() {
      return this.field1.getSections().indexOf(this);
   }

   public RouteSegment method6() {
      return this.field1.method15(this);
   }

   @Generated
   public RouteSection(DungeonRoute holograms21) {
      this.field1 = holograms21;
   }

   @Generated
   public DungeonRoute method7() {
      return this.field1;
   }

   @Generated
   public List<int[]> method8() {
      return this.field2;
   }

   @Generated
   public List<ValuePair<NameplateType, int[]>> method9() {
      return this.field3;
   }
}
