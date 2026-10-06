package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.files.Files6_2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;

public class Holograms7 {
   private final Holograms2 field1;
   private List<int[]> field2 = new ArrayList<>();
   private List<Files6_2<NameplateType, int[]>> field3 = new ArrayList<>();

   public Holograms7(Holograms7 var1, Holograms2 var2) {
      this.field1 = var2;
      this.field2 = var1.field2.stream().map(var0 -> (int[])((int[])var0).clone()).collect(Collectors.toList());
      this.field3 = var1.field3
         .stream()
         .map(var0 -> Files6_2.method1((NameplateType)var0.field1, (int[])((int[])var0.field2).clone()))
         .collect(Collectors.toList());
   }

   public void method1(NameplateType var1, int[] var2) {
      this.field3.add(Files6_2.method1(var1, var2));
   }

   public void method2(int[] var1) {
      if (!this.field2.isEmpty()) {
         int[] var2 = this.field2.get(this.field2.size() - 1);
         if (var2[0] == var1[0] && var2[1] == var1[1] && var2[2] == var1[2]) {
            return;
         }

         if (this.field2.size() >= 2) {
            int[] var3 = this.field2.get(this.field2.size() - 2);
            double[] var4 = new double[]{var3[0] - var2[0], var3[1] - var2[1], var3[2] - var2[2]};
            double[] var5 = new double[]{var3[0] - var1[0], var3[1] - var1[1], var3[2] - var1[2]};

            for (double[] var9 : new double[][]{var4, var5}) {
               double var10 = Math.sqrt(Math.pow(var9[0], 2.0) + Math.pow(var9[1], 2.0) + Math.pow(var9[2], 2.0));
               var9[0] /= var10;
               var9[1] /= var10;
               var9[2] /= var10;
            }

            if (ThreadModuleDump67.method18(var5[0], var4[0], 0.01)
               && ThreadModuleDump67.method18(var5[1], var4[1], 0.01)
               && ThreadModuleDump67.method18(var5[2], var4[2], 0.01)) {
               var2[0] = var1[0];
               var2[1] = var1[1];
               var2[2] = var1[2];
               return;
            }
         }

         double var12 = Math.pow(var2[0] - var1[0], 2.0) + Math.pow(var2[1] - var1[1], 2.0) + Math.pow(var2[2] - var1[2], 2.0);
         if (var12 < 3.61) {
            return;
         }
      }

      this.field2.add(var1);
   }

   public void method3(DataOutputStream var1) {
      var1.writeInt(this.field2.size());

      for (int[] var3 : this.field2) {
         var1.writeInt(var3[0]);
         var1.writeInt(var3[1]);
         var1.writeInt(var3[2]);
      }

      var1.writeInt(this.field3.size());

      for (Files6_2 var6 : this.field3) {
         var1.writeInt(((NameplateType)var6.field1).ordinal());
         int[] var4 = (int[])var6.field2;
         var1.writeInt(var4[0]);
         var1.writeInt(var4[1]);
         var1.writeInt(var4[2]);
      }
   }

   public static Holograms7 method4(DataInputStream var0, Holograms2 var1) {
      Holograms7 var2 = new Holograms7(var1);
      int var3 = var0.readInt();

      for (int var4 = 0; var4 < var3; var4++) {
         int var5 = var0.readInt();
         int var6 = var0.readInt();
         int var7 = var0.readInt();
         var2.field2.add(new int[]{var5, var6, var7});
      }

      int var11 = var0.readInt();

      for (int var12 = 0; var12 < var11; var12++) {
         int var13 = var0.readInt();
         NameplateType var14 = NameplateType.values()[var13];
         int var8 = var0.readInt();
         int var9 = var0.readInt();
         int var10 = var0.readInt();
         var2.field3.add(Files6_2.method1(var14, new int[]{var8, var9, var10}));
      }

      return var2;
   }

   public boolean method5(NameplateType var1, int[] var2) {
      return this.field3.stream().anyMatch(var2x -> var2x.field1 == var1 && Arrays.equals((int[])var2x.field2, var2));
   }

   public int getIndex() {
      return this.field1.getSections().indexOf(this);
   }

   public Holograms10_2 method6() {
      return this.field1.method15(this);
   }

   @Generated
   public Holograms7(Holograms2 var1) {
      this.field1 = var1;
   }

   @Generated
   public Holograms2 method7() {
      return this.field1;
   }

   @Generated
   public List<int[]> method8() {
      return this.field2;
   }

   @Generated
   public List<Files6_2<NameplateType, int[]>> method9() {
      return this.field3;
   }
}
