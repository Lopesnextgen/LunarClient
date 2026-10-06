package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.bridge.Bridge2_32;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import lombok.Generated;

public final class Click16 {
   private static final double field1 = 10000.0;

   public static List<Click16.Data> mergeEdges(Iterable<Click16.Data2> var0) {
      Click16.Data6 var1 = new Click16.Data6();

      for (Click16.Data2 var3 : var0) {
         addEdgeBox(var1, var3);
      }

      return var1.method2(Click16::isExposedEdge);
   }

   public static List<Click16.Data4> mergeFaces(Iterable<Click16.Data2> var0) {
      Click16.Data6 var1 = new Click16.Data6();

      for (Click16.Data2 var3 : var0) {
         addFaceBox(var1, var3);
      }

      return var1.method2(var0x -> var0x != 3);
   }

   private static boolean isExposedEdge(int var0) {
      return var0 != 3 && var0 != 12 && var0 != 5 && var0 != 10 && var0 != 15;
   }

   private static void addEdgeBox(Click16.Data6<Click16.Data3, Click16.Data> var0, Click16.Data2 var1) {
      double var2 = var1.minX();
      double var4 = var1.method2();
      double var6 = var1.minZ();
      double var8 = var1.maxX();
      double var10 = var1.method3();
      double var12 = var1.maxZ();
      int var14 = var1.method4();
      addEdge(var0, var2, var4, var6, var8, var4, var6, var14, true, true);
      addEdge(var0, var8, var4, var6, var8, var4, var12, var14, false, true);
      addEdge(var0, var8, var4, var12, var2, var4, var12, var14, true, false);
      addEdge(var0, var2, var4, var6, var2, var4, var12, var14, true, true);
      addEdge(var0, var2, var10, var6, var8, var10, var6, var14, false, true);
      addEdge(var0, var8, var10, var6, var8, var10, var12, var14, false, false);
      addEdge(var0, var8, var10, var12, var2, var10, var12, var14, false, false);
      addEdge(var0, var2, var10, var6, var2, var10, var12, var14, true, false);
      addEdge(var0, var2, var4, var6, var2, var10, var6, var14, true, true);
      addEdge(var0, var8, var4, var6, var8, var10, var6, var14, false, true);
      addEdge(var0, var2, var4, var12, var2, var10, var12, var14, true, false);
      addEdge(var0, var8, var4, var12, var8, var10, var12, var14, false, false);
   }

   private static void addFaceBox(Click16.Data6<Click16.Data5, Click16.Data4> var0, Click16.Data2 var1) {
      double var2 = var1.minX();
      double var4 = var1.method2();
      double var6 = var1.minZ();
      double var8 = var1.maxX();
      double var10 = var1.method3();
      double var12 = var1.maxZ();
      int var14 = var1.method4();
      addFace(var0, Click16.Type.Y, var4, var2, var6, var8, var12, var14, false);
      addFace(var0, Click16.Type.Y, var10, var2, var6, var8, var12, var14, true);
      addFace(var0, Click16.Type.Z, var6, var2, var4, var8, var10, var14, false);
      addFace(var0, Click16.Type.Z, var12, var2, var4, var8, var10, var14, true);
      addFace(var0, Click16.Type.X, var2, var6, var4, var12, var10, var14, false);
      addFace(var0, Click16.Type.X, var8, var6, var4, var12, var10, var14, true);
   }

   private static void addEdge(
      Click16.Data6<Click16.Data3, Click16.Data> var0,
      double var1,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      int var13,
      boolean var14,
      boolean var15
   ) {
      var0.method1(
         Click16.Data3.method1(var1, var3, var5, var7, var9, var11, var13),
         new Click16.Data(var1, var3, var5, var7, var9, var11, var13),
         1 << ((var14 ? 1 : 0) | (var15 ? 2 : 0))
      );
   }

   private static void addFace(
      Click16.Data6<Click16.Data5, Click16.Data4> var0,
      Click16.Type var1,
      double var2,
      double var4,
      double var6,
      double var8,
      double var10,
      int var12,
      boolean var13
   ) {
      var0.method1(
         Click16.Data5.method1(var1, var2, var4, var6, var8, var10, var12),
         new Click16.Data4(var1, var2, Math.min(var4, var8), Math.min(var6, var10), Math.max(var4, var8), Math.max(var6, var10), var12, var13),
         var13 ? 1 : 2
      );
   }

   private static long quantize(double var0) {
      return Math.round(var0 * 10000.0);
   }

   @Generated
   private Click16() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public class Data {
      private final double field1;
      private final double field2;
      private final double field3;
      private final double field4;
      private final double field5;
      private final double field6;
      private final int field7;

      public Data(double var1, double var3, double var5, double var7, double var9, double var11, int var13) {
         this.COORDINATE_SCALE = var1;
         this.field2 = var3;
         this.field3 = var5;
         this.field4 = var7;
         this.field5 = var9;
         this.field6 = var11;
         this.field7 = var13;
      }

      public double mergeEdges() {
         return this.COORDINATE_SCALE;
      }

      public double mergeFaces() {
         return this.field2;
      }

      public double isExposedEdge() {
         return this.field3;
      }

      public double addEdgeBox() {
         return this.field4;
      }

      public double addFaceBox() {
         return this.field5;
      }

      public double addEdge() {
         return this.field6;
      }

      public int addFace() {
         return this.field7;
      }
   }

   public class Data2 {
      private final double field1;
      private final double field2;
      private final double field3;
      private final double field4;
      private final double field5;
      private final double field6;
      private final int field7;

      public Data2(double var1, double var3, double var5, double var7, double var9, double var11, int var13) {
         this.COORDINATE_SCALE = var1;
         this.field2 = var3;
         this.field3 = var5;
         this.field4 = var7;
         this.field5 = var9;
         this.field6 = var11;
         this.field7 = var13;
      }

      public static Click16.Data2 mergeEdges(double[] var0, int var1) {
         return new Click16.Data2(var0[0], var0[1], var0[2], var0[3], var0[4], var0[5], var1);
      }

      public double minX() {
         return this.COORDINATE_SCALE;
      }

      public double mergeFaces() {
         return this.field2;
      }

      public double minZ() {
         return this.field3;
      }

      public double maxX() {
         return this.field4;
      }

      public double isExposedEdge() {
         return this.field5;
      }

      public double maxZ() {
         return this.field6;
      }

      public int addEdgeBox() {
         return this.field7;
      }
   }

   private class Data3 {
      private final long field1;
      private final long field2;
      private final long field3;
      private final long field4;
      private final long field5;
      private final long field6;
      private final int field7;

      private Data3(long var1, long var3, long var5, long var7, long var9, long var11, int var13) {
         this.COORDINATE_SCALE = var1;
         this.field2 = var3;
         this.field3 = var5;
         this.field4 = var7;
         this.field5 = var9;
         this.field6 = var11;
         this.field7 = var13;
      }

      static Click16.Data3 mergeEdges(double var0, double var2, double var4, double var6, double var8, double var10, int var12) {
         long var13 = Click16.quantize(var0);
         long var15 = Click16.quantize(var2);
         long var17 = Click16.quantize(var4);
         long var19 = Click16.quantize(var6);
         long var21 = Click16.quantize(var8);
         long var23 = Click16.quantize(var10);
         boolean var25 = var13 > var19 || var13 == var19 && (var15 > var21 || var15 == var21 && var17 > var23);
         return var25 ? new Click16.Data3(var19, var21, var23, var13, var15, var17, var12) : new Click16.Data3(var13, var15, var17, var19, var21, var23, var12);
      }

      public long mergeFaces() {
         return this.COORDINATE_SCALE;
      }

      public long isExposedEdge() {
         return this.field2;
      }

      public long addEdgeBox() {
         return this.field3;
      }

      public long addFaceBox() {
         return this.field4;
      }

      public long addEdge() {
         return this.field5;
      }

      public long addFace() {
         return this.field6;
      }

      public int quantize() {
         return this.field7;
      }
   }

   public class Data4 {
      private final Click16.Type field1;
      private final double field2;
      private final double field3;
      private final double field4;
      private final double field5;
      private final double field6;
      private final int field7;
      private final boolean inverted;

      public Data4(Click16.Type var1, double var2, double var4, double var6, double var8, double var10, int var12, boolean var13) {
         this.COORDINATE_SCALE = var1;
         this.field2 = var2;
         this.field3 = var4;
         this.field4 = var6;
         this.field5 = var8;
         this.field6 = var10;
         this.field7 = var12;
         this.inverted = var13;
      }

      public void mergeEdges(Bridge2_32 var1) {
         switch (this.COORDINATE_SCALE) {
            case Y:
               this.mergeFaces(var1);
               break;
            case Z:
               this.isExposedEdge(var1);
               break;
            case X:
               this.addEdgeBox(var1);
         }
      }

      private void mergeFaces(Bridge2_32 var1) {
         double var2 = this.field2;
         if (this.inverted) {
            var1.method2(this.field3, var2, this.field4).method9(this.field7).method16();
            var1.method2(this.field3, var2, this.field6).method9(this.field7).method16();
            var1.method2(this.field5, var2, this.field6).method9(this.field7).method16();
            var1.method2(this.field5, var2, this.field4).method9(this.field7).method16();
         } else {
            var1.method2(this.field3, var2, this.field4).method9(this.field7).method16();
            var1.method2(this.field5, var2, this.field4).method9(this.field7).method16();
            var1.method2(this.field5, var2, this.field6).method9(this.field7).method16();
            var1.method2(this.field3, var2, this.field6).method9(this.field7).method16();
         }
      }

      private void isExposedEdge(Bridge2_32 var1) {
         double var2 = this.field2;
         if (this.inverted) {
            var1.method2(this.field3, this.field4, var2).method9(this.field7).method16();
            var1.method2(this.field5, this.field4, var2).method9(this.field7).method16();
            var1.method2(this.field5, this.field6, var2).method9(this.field7).method16();
            var1.method2(this.field3, this.field6, var2).method9(this.field7).method16();
         } else {
            var1.method2(this.field3, this.field4, var2).method9(this.field7).method16();
            var1.method2(this.field3, this.field6, var2).method9(this.field7).method16();
            var1.method2(this.field5, this.field6, var2).method9(this.field7).method16();
            var1.method2(this.field5, this.field4, var2).method9(this.field7).method16();
         }
      }

      private void addEdgeBox(Bridge2_32 var1) {
         double var2 = this.field2;
         if (this.inverted) {
            var1.method2(var2, this.field4, this.field3).method9(this.field7).method16();
            var1.method2(var2, this.field6, this.field3).method9(this.field7).method16();
            var1.method2(var2, this.field6, this.field5).method9(this.field7).method16();
            var1.method2(var2, this.field4, this.field5).method9(this.field7).method16();
         } else {
            var1.method2(var2, this.field4, this.field3).method9(this.field7).method16();
            var1.method2(var2, this.field4, this.field5).method9(this.field7).method16();
            var1.method2(var2, this.field6, this.field5).method9(this.field7).method16();
            var1.method2(var2, this.field6, this.field3).method9(this.field7).method16();
         }
      }

      public Click16.Type addFaceBox() {
         return this.COORDINATE_SCALE;
      }

      public double addEdge() {
         return this.field2;
      }

      public double addFace() {
         return this.field3;
      }

      public double quantize() {
         return this.field4;
      }

      public double getMax1() {
         return this.field5;
      }

      public double getMax2() {
         return this.field6;
      }

      public int getColor() {
         return this.field7;
      }

      public boolean isInverted() {
         return this.inverted;
      }
   }

   private class Data5 {
      private final Click16.Type field1;
      private final long field2;
      private final long field3;
      private final long min2;
      private final long field4;
      private final long max2;
      private final int field5;

      private Data5(Click16.Type var1, long var2, long var4, long var6, long var8, long var10, int var12) {
         this.COORDINATE_SCALE = var1;
         this.field2 = var2;
         this.field3 = var4;
         this.min2 = var6;
         this.field4 = var8;
         this.max2 = var10;
         this.field5 = var12;
      }

      static Click16.Data5 mergeEdges(Click16.Type var0, double var1, double var3, double var5, double var7, double var9, int var11) {
         long var12 = Click16.quantize(var3);
         long var14 = Click16.quantize(var5);
         long var16 = Click16.quantize(var7);
         long var18 = Click16.quantize(var9);
         long var20 = Math.min(var12, var16);
         long var22 = Math.min(var14, var18);
         long var24 = Math.max(var12, var16);
         long var26 = Math.max(var14, var18);
         return new Click16.Data5(var0, Click16.quantize(var1), var20, var22, var24, var26, var11);
      }

      public Click16.Type mergeFaces() {
         return this.COORDINATE_SCALE;
      }

      public long isExposedEdge() {
         return this.field2;
      }

      public long addEdgeBox() {
         return this.field3;
      }

      public long addFaceBox() {
         return this.min2;
      }

      public long addEdge() {
         return this.field4;
      }

      public long addFace() {
         return this.max2;
      }

      public int quantize() {
         return this.field5;
      }
   }

   private static final class Data6<K, V> {
      private final Map<K, Click16.Data6.Data<V>> field1 = new LinkedHashMap<>();

      void mergeEdges(K var1, V var2, int var3) {
         this.COORDINATE_SCALE.computeIfAbsent((K)var1, var1x -> new Click16.Data6.Data<>((V)var2)).mask |= var3;
      }

      List<V> mergeFaces(IntPredicate var1) {
         ArrayList var2 = new ArrayList();

         for (Click16.Data6.Data var4 : this.COORDINATE_SCALE.values()) {
            if (var1.test(var4.mask)) {
               var2.add(var4.field1);
            }
         }

         return var2;
      }

      private static final class Data<V> {
         private final V field1;
         private int mask;

         private Data(V var1) {
            this.COORDINATE_SCALE = (V)var1;
         }
      }
   }

   public enum Type {
      X,
      Y,
      Z;
   }
}
