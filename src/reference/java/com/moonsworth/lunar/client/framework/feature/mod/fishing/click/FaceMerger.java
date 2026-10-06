package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.bridge.DrawBufferBridge;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import lombok.Generated;

public final class FaceMerger {
   private static final double field1 = 10000.0;

   public static List<FaceMerger.Data> mergeEdges(Iterable<FaceMerger.BlockBox> list0) {
      FaceMerger.MaskedMap data61 = new FaceMerger.MaskedMap();

      for (FaceMerger.BlockBox data23 : list0) {
         addBlockEdges(data61, data23);
      }

      return data61.method2(FaceMerger::isExposed);
   }

   public static List<FaceMerger.BlockFace> mergeFaces(Iterable<FaceMerger.BlockBox> list0) {
      FaceMerger.MaskedMap data61 = new FaceMerger.MaskedMap();

      for (FaceMerger.BlockBox data23 : list0) {
         addBlockFaces(data61, data23);
      }

      return data61.method2(arg0x -> arg0x != 3);
   }

   private static boolean isExposed(int number0) {
      return number0 != 3 && number0 != 12 && number0 != 5 && number0 != 10 && number0 != 15;
   }

   private static void addBlockEdges(FaceMerger.MaskedMap<FaceMerger.EdgeKey, FaceMerger.Data> data60, FaceMerger.BlockBox data21) {
      double value2 = data21.minX();
      double value4 = data21.method2();
      double value6 = data21.minZ();
      double value8 = data21.maxX();
      double value10 = data21.method3();
      double value12 = data21.maxZ();
      int number14 = data21.method4();
      addEdge(data60, value2, value4, value6, value8, value4, value6, number14, true, true);
      addEdge(data60, value8, value4, value6, value8, value4, value12, number14, false, true);
      addEdge(data60, value8, value4, value12, value2, value4, value12, number14, true, false);
      addEdge(data60, value2, value4, value6, value2, value4, value12, number14, true, true);
      addEdge(data60, value2, value10, value6, value8, value10, value6, number14, false, true);
      addEdge(data60, value8, value10, value6, value8, value10, value12, number14, false, false);
      addEdge(data60, value8, value10, value12, value2, value10, value12, number14, false, false);
      addEdge(data60, value2, value10, value6, value2, value10, value12, number14, true, false);
      addEdge(data60, value2, value4, value6, value2, value10, value6, number14, true, true);
      addEdge(data60, value8, value4, value6, value8, value10, value6, number14, false, true);
      addEdge(data60, value2, value4, value12, value2, value10, value12, number14, true, false);
      addEdge(data60, value8, value4, value12, value8, value10, value12, number14, false, false);
   }

   private static void addBlockFaces(FaceMerger.MaskedMap<FaceMerger.FaceKey, FaceMerger.BlockFace> data60, FaceMerger.BlockBox data21) {
      double value2 = data21.minX();
      double value4 = data21.method2();
      double value6 = data21.minZ();
      double value8 = data21.maxX();
      double value10 = data21.method3();
      double value12 = data21.maxZ();
      int number14 = data21.method4();
      addFace(data60, FaceMerger.Type.Y, value4, value2, value6, value8, value12, number14, false);
      addFace(data60, FaceMerger.Type.Y, value10, value2, value6, value8, value12, number14, true);
      addFace(data60, FaceMerger.Type.Z, value6, value2, value4, value8, value10, number14, false);
      addFace(data60, FaceMerger.Type.Z, value12, value2, value4, value8, value10, number14, true);
      addFace(data60, FaceMerger.Type.X, value2, value6, value4, value12, value10, number14, false);
      addFace(data60, FaceMerger.Type.X, value8, value6, value4, value12, value10, number14, true);
   }

   private static void addEdge(
      FaceMerger.MaskedMap<FaceMerger.EdgeKey, FaceMerger.Data> data60,
      double value1,
      double value3,
      double value5,
      double value7,
      double value9,
      double value11,
      int number13,
      boolean flag14,
      boolean flag15
   ) {
      data60.method1(
         FaceMerger.EdgeKey.method1(value1, value3, value5, value7, value9, value11, number13),
         new FaceMerger.Data(value1, value3, value5, value7, value9, value11, number13),
         1 << ((flag14 ? 1 : 0) | (flag15 ? 2 : 0))
      );
   }

   private static void addFace(
      FaceMerger.MaskedMap<FaceMerger.FaceKey, FaceMerger.BlockFace> data60,
      FaceMerger.Type type1,
      double value2,
      double value4,
      double value6,
      double value8,
      double value10,
      int number12,
      boolean flag13
   ) {
      data60.method1(
         FaceMerger.FaceKey.method1(type1, value2, value4, value6, value8, value10, number12),
         new FaceMerger.BlockFace(type1, value2, Math.min(value4, value8), Math.min(value6, value10), Math.max(value4, value8), Math.max(value6, value10), number12, flag13),
         flag13 ? 1 : 2
      );
   }

   private static long quantize(double value0) {
      return Math.round(value0 * 10000.0);
   }

   @Generated
   private FaceMerger() {
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

      public Data(double value1, double value3, double value5, double value7, double value9, double value11, int number13) {
         this.QUANTIZE_SCALE = value1;
         this.field2 = value3;
         this.field3 = value5;
         this.field4 = value7;
         this.field5 = value9;
         this.field6 = value11;
         this.field7 = number13;
      }

      public double mergeEdges() {
         return this.QUANTIZE_SCALE;
      }

      public double mergeFaces() {
         return this.field2;
      }

      public double isExposed() {
         return this.field3;
      }

      public double addBlockEdges() {
         return this.field4;
      }

      public double addBlockFaces() {
         return this.field5;
      }

      public double addEdge() {
         return this.field6;
      }

      public int addFace() {
         return this.field7;
      }
   }

   public class BlockBox {
      private final double field1;
      private final double field2;
      private final double field3;
      private final double field4;
      private final double field5;
      private final double field6;
      private final int field7;

      public BlockBox(double value1, double value3, double value5, double value7, double value9, double value11, int number13) {
         this.QUANTIZE_SCALE = value1;
         this.field2 = value3;
         this.field3 = value5;
         this.field4 = value7;
         this.field5 = value9;
         this.field6 = value11;
         this.field7 = number13;
      }

      public static FaceMerger.BlockBox mergeEdges(double[] items0, int number1) {
         return new FaceMerger.BlockBox(items0[0], items0[1], items0[2], items0[3], items0[4], items0[5], number1);
      }

      public double minX() {
         return this.QUANTIZE_SCALE;
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

      public double isExposed() {
         return this.field5;
      }

      public double maxZ() {
         return this.field6;
      }

      public int addBlockEdges() {
         return this.field7;
      }
   }

   private class EdgeKey {
      private final long field1;
      private final long field2;
      private final long field3;
      private final long field4;
      private final long field5;
      private final long field6;
      private final int field7;

      private EdgeKey(long number1, long number3, long number5, long number7, long number9, long number11, int number13) {
         this.QUANTIZE_SCALE = number1;
         this.field2 = number3;
         this.field3 = number5;
         this.field4 = number7;
         this.field5 = number9;
         this.field6 = number11;
         this.field7 = number13;
      }

      static FaceMerger.EdgeKey mergeEdges(double value0, double value2, double value4, double value6, double value8, double value10, int number12) {
         long number13 = FaceMerger.quantize(value0);
         long number15 = FaceMerger.quantize(value2);
         long number17 = FaceMerger.quantize(value4);
         long number19 = FaceMerger.quantize(value6);
         long number21 = FaceMerger.quantize(value8);
         long number23 = FaceMerger.quantize(value10);
         boolean flag25 = number13 > number19 || number13 == number19 && (number15 > number21 || number15 == number21 && number17 > number23);
         return flag25 ? new FaceMerger.EdgeKey(number19, number21, number23, number13, number15, number17, number12) : new FaceMerger.EdgeKey(number13, number15, number17, number19, number21, number23, number12);
      }

      public long mergeFaces() {
         return this.QUANTIZE_SCALE;
      }

      public long isExposed() {
         return this.field2;
      }

      public long addBlockEdges() {
         return this.field3;
      }

      public long addBlockFaces() {
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

   public class BlockFace {
      private final FaceMerger.Type field1;
      private final double field2;
      private final double field3;
      private final double field4;
      private final double field5;
      private final double field6;
      private final int field7;
      private final boolean field8;

      public BlockFace(FaceMerger.Type type1, double value2, double value4, double value6, double value8, double value10, int number12, boolean flag13) {
         this.QUANTIZE_SCALE = type1;
         this.field2 = value2;
         this.field3 = value4;
         this.field4 = value6;
         this.field5 = value8;
         this.field6 = value10;
         this.field7 = number12;
         this.field8 = flag13;
      }

      public void mergeEdges(DrawBufferBridge bridge2_321) {
         switch (this.QUANTIZE_SCALE) {
            case Y:
               this.mergeFaces(bridge2_321);
               break;
            case Z:
               this.isExposed(bridge2_321);
               break;
            case X:
               this.addBlockEdges(bridge2_321);
         }
      }

      private void mergeFaces(DrawBufferBridge bridge2_321) {
         double value2 = this.field2;
         if (this.field8) {
            bridge2_321.method2(this.field3, value2, this.field4).method9(this.field7).method16();
            bridge2_321.method2(this.field3, value2, this.field6).method9(this.field7).method16();
            bridge2_321.method2(this.field5, value2, this.field6).method9(this.field7).method16();
            bridge2_321.method2(this.field5, value2, this.field4).method9(this.field7).method16();
         } else {
            bridge2_321.method2(this.field3, value2, this.field4).method9(this.field7).method16();
            bridge2_321.method2(this.field5, value2, this.field4).method9(this.field7).method16();
            bridge2_321.method2(this.field5, value2, this.field6).method9(this.field7).method16();
            bridge2_321.method2(this.field3, value2, this.field6).method9(this.field7).method16();
         }
      }

      private void isExposed(DrawBufferBridge bridge2_321) {
         double value2 = this.field2;
         if (this.field8) {
            bridge2_321.method2(this.field3, this.field4, value2).method9(this.field7).method16();
            bridge2_321.method2(this.field5, this.field4, value2).method9(this.field7).method16();
            bridge2_321.method2(this.field5, this.field6, value2).method9(this.field7).method16();
            bridge2_321.method2(this.field3, this.field6, value2).method9(this.field7).method16();
         } else {
            bridge2_321.method2(this.field3, this.field4, value2).method9(this.field7).method16();
            bridge2_321.method2(this.field3, this.field6, value2).method9(this.field7).method16();
            bridge2_321.method2(this.field5, this.field6, value2).method9(this.field7).method16();
            bridge2_321.method2(this.field5, this.field4, value2).method9(this.field7).method16();
         }
      }

      private void addBlockEdges(DrawBufferBridge bridge2_321) {
         double value2 = this.field2;
         if (this.field8) {
            bridge2_321.method2(value2, this.field4, this.field3).method9(this.field7).method16();
            bridge2_321.method2(value2, this.field6, this.field3).method9(this.field7).method16();
            bridge2_321.method2(value2, this.field6, this.field5).method9(this.field7).method16();
            bridge2_321.method2(value2, this.field4, this.field5).method9(this.field7).method16();
         } else {
            bridge2_321.method2(value2, this.field4, this.field3).method9(this.field7).method16();
            bridge2_321.method2(value2, this.field4, this.field5).method9(this.field7).method16();
            bridge2_321.method2(value2, this.field6, this.field5).method9(this.field7).method16();
            bridge2_321.method2(value2, this.field6, this.field3).method9(this.field7).method16();
         }
      }

      public FaceMerger.Type addBlockFaces() {
         return this.QUANTIZE_SCALE;
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

      public double method9() {
         return this.field5;
      }

      public double method10() {
         return this.field6;
      }

      public int method11() {
         return this.field7;
      }

      public boolean method12() {
         return this.field8;
      }
   }

   private class FaceKey {
      private final FaceMerger.Type field1;
      private final long field2;
      private final long field3;
      private final long v0;
      private final long field4;
      private final long v1;
      private final int field5;

      private FaceKey(FaceMerger.Type type1, long number2, long number4, long number6, long number8, long number10, int number12) {
         this.QUANTIZE_SCALE = type1;
         this.field2 = number2;
         this.field3 = number4;
         this.v0 = number6;
         this.field4 = number8;
         this.v1 = number10;
         this.field5 = number12;
      }

      static FaceMerger.FaceKey mergeEdges(FaceMerger.Type type0, double value1, double value3, double value5, double value7, double value9, int number11) {
         long number12 = FaceMerger.quantize(value3);
         long number14 = FaceMerger.quantize(value5);
         long number16 = FaceMerger.quantize(value7);
         long number18 = FaceMerger.quantize(value9);
         long number20 = Math.min(number12, number16);
         long number22 = Math.min(number14, number18);
         long number24 = Math.max(number12, number16);
         long number26 = Math.max(number14, number18);
         return new FaceMerger.FaceKey(type0, FaceMerger.quantize(value1), number20, number22, number24, number26, number11);
      }

      public FaceMerger.Type mergeFaces() {
         return this.QUANTIZE_SCALE;
      }

      public long isExposed() {
         return this.field2;
      }

      public long addBlockEdges() {
         return this.field3;
      }

      public long addBlockFaces() {
         return this.v0;
      }

      public long addEdge() {
         return this.field4;
      }

      public long addFace() {
         return this.v1;
      }

      public int quantize() {
         return this.field5;
      }
   }

   private static final class MaskedMap<K, V> {
      private final Map<K, FaceMerger.MaskedMap.Data<V>> field1 = new LinkedHashMap<>();

      private MaskedMap() {
      }

      void mergeEdges(K value1, V value2, int number3) {
         this.QUANTIZE_SCALE.computeIfAbsent((K)value1, arg1x -> new FaceMerger.MaskedMap.Data<>((V)value2)).mask |= number3;
      }

      List<V> mergeFaces(IntPredicate intpredicate1) {
         ArrayList list2 = new ArrayList();

         for (FaceMerger.MaskedMap.Data data4 : this.QUANTIZE_SCALE.values()) {
            if (intpredicate1.test(data4.mask)) {
               list2.add(data4.field1);
            }
         }

         return list2;
      }

      private static final class Data<V> {
         private final V field1;
         private int mask;

         private Data(V value1) {
            this.QUANTIZE_SCALE = (V)value1;
         }
      }
   }

   public enum Type {
      X,
      Y,
      Z;

      Type() {
      }
   }
}
