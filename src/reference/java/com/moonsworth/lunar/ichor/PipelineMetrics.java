package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.StageMemberTracker;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import lombok.Generated;

public class PipelineMetrics {
   @Nullable
   private final Map<String, Long> field1 = IchorPipeline.field1 ? new ConcurrentHashMap<>() : null;
   @Nullable
   private final Map<String, Long> field2 = IchorPipeline.field1 ? new ConcurrentHashMap<>() : null;
   @Nullable
   private final Map<String, Long> field3 = IchorPipeline.field1 ? new ConcurrentHashMap<>() : null;
   @Nullable
   public final Map<String, Long> field4 = IchorPipeline.field1 ? new ConcurrentHashMap<>() : null;
   @Nullable
   public final Map<String, Long> field5 = IchorPipeline.field1 ? new ConcurrentHashMap<>() : null;
   @Nullable
   private final Map<String, StageMemberTracker> field6 = IchorPipeline.field1 ? new ConcurrentHashMap<>() : null;
   final AtomicLong field7 = new AtomicLong(0L);
   final AtomicLong field8 = new AtomicLong(0L);
   private final long field9 = System.currentTimeMillis();

   public PipelineMetrics() {
   }

   @Nullable
   @Generated
   public Map<String, Long> method1() {
      return this.field1;
   }

   @Nullable
   @Generated
   public Map<String, Long> method2() {
      return this.field2;
   }

   @Nullable
   @Generated
   public Map<String, Long> method3() {
      return this.field3;
   }

   @Nullable
   @Generated
   public Map<String, Long> method4() {
      return this.field4;
   }

   @Nullable
   @Generated
   public Map<String, Long> method5() {
      return this.field5;
   }

   @Nullable
   @Generated
   public Map<String, StageMemberTracker> method6() {
      return this.field6;
   }

   @Generated
   public AtomicLong method7() {
      return this.field7;
   }

   @Generated
   public AtomicLong method8() {
      return this.field8;
   }

   @Generated
   public long method9() {
      return this.field9;
   }
}
