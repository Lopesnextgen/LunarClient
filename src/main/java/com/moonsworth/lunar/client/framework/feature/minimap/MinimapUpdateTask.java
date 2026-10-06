package com.moonsworth.lunar.client.framework.feature.minimap;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import lombok.Generated;

public class MinimapUpdateTask {
   private final Queue<MinimapUpdateTask.Data> field1 = new ConcurrentLinkedDeque<>();
   private final MinimapUpdateTask.Type field2;
   private final int field3;
   private final int field4;
   private final Long2ObjectMap<int[]> field5 = new Long2ObjectOpenHashMap();

   public MinimapUpdateTask(MinimapUpdateTask.Type type, int value, int value2) {
      this.field2 = type;
      this.field3 = value;
      this.field4 = value2;
   }

   @Generated
   public Queue<MinimapUpdateTask.Data> method1() {
      return this.field1;
   }

   @Generated
   public MinimapUpdateTask.Type method2() {
      return this.field2;
   }

   @Generated
   public int method3() {
      return this.field3;
   }

   @Generated
   public int method4() {
      return this.field4;
   }

   @Generated
   public Long2ObjectMap<int[]> method5() {
      return this.field5;
   }

   public class Data {
      private final long field1;
      private final int[] field2;

      public Data(long value, int[] items3) {
         this.field1 = value;
         this.field2 = items3;
      }

      public long method1() {
         return this.field1;
      }

      public int[] method2() {
         return this.field2;
      }
   }

   public enum Type {
      SINGLE_CHUNK,
      FULL_MAP;

      Type() {
      }
   }
}
