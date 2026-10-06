package com.moonsworth.lunar.client.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import lombok.Generated;

public enum ThreadModuleDump73Type implements OptionEnumValue {
   LINEAR("linear", ThreadModuleDump18.LINEAR),
   QUAD("quad", ThreadModuleDump18.QUADRATIC_IN, ThreadModuleDump18.QUADRATIC_IN_OUT, ThreadModuleDump18.QUADRATIC_OUT),
   CUBIC("cubic", ThreadModuleDump18.CUBIC_IN, ThreadModuleDump18.CUBIC_IN_OUT, ThreadModuleDump18.CUBIC_OUT),
   STEP("step", ThreadModuleDump18.STEP_END, ThreadModuleDump18.STEP_MIDDLE, ThreadModuleDump18.STEP_START),
   SWING("swing", ThreadModuleDump18.STEP_END1, ThreadModuleDump18.STEP_END3, ThreadModuleDump18.STEP_END2),
   SINE("sine", ThreadModuleDump18.SINE_IN, ThreadModuleDump18.SINE_IN_OUT, ThreadModuleDump18.SINE_OUT),
   EXP("exp", ThreadModuleDump18.EXPONENTIAL_IN, ThreadModuleDump18.LINEAR0, ThreadModuleDump18.EXPONENTIAL_OUT),
   CIRCLE("circle", ThreadModuleDump18.LINEAR1, ThreadModuleDump18.LINEAR3, ThreadModuleDump18.LINEAR2),
   ELASTIC("elastic", ThreadModuleDump18.LINEAR8, ThreadModuleDump18.STEP_END0, ThreadModuleDump18.LINEAR9),
   BACK("back", ThreadModuleDump18.LINEAR4, ThreadModuleDump18.LINEAR6, ThreadModuleDump18.LINEAR5),
   SMOOTH("smooth", ThreadModuleDump18.SMOOTHSTEP),
   SMOOTH2("smooth2", ThreadModuleDump18.SMOOTHSTEP_DOUBLED),
   SMOOTHER("smoother", ThreadModuleDump18.SMOOTHERSTEP),
   CATMULL_ROM("catmull_rom", ThreadModuleDump18.LINEAR7, false);

   private final String id;
   private final ThreadModuleDump18 in;
   private final ThreadModuleDump18 inout;
   private final ThreadModuleDump18 out;
   private boolean supportsInOut = true;
   private boolean supportsAverage = true;

   ThreadModuleDump73Type(String var3, ThreadModuleDump18 var4) {
      this(var3, var4, var4, var4);
      this.supportsInOut = false;
   }

   ThreadModuleDump73Type(String var3, ThreadModuleDump18 var4, boolean var5) {
      this(var3, var4);
      this.supportsAverage = var5;
   }

   public ThreadModuleDump18 get(boolean var1, boolean var2) {
      if (!this.supportsInOut) {
         return this.in;
      } else if (var1 && var2) {
         return this.inout;
      } else if (var1) {
         return this.in;
      } else if (var2) {
         return this.out;
      } else {
         return this == STEP ? this.in : ThreadModuleDump18.LINEAR;
      }
   }

   public static ThreadModuleDump73Type get(String var0) {
      for (ThreadModuleDump73Type var4 : values()) {
         if (var4.getId().equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public ThreadModuleDump18 getIn() {
      return this.in;
   }

   @Generated
   public ThreadModuleDump18 getInout() {
      return this.inout;
   }

   @Generated
   public ThreadModuleDump18 getOut() {
      return this.out;
   }

   @Generated
   public boolean isSupportsInOut() {
      return this.supportsInOut;
   }

   @Generated
   ThreadModuleDump73Type(String var3, ThreadModuleDump18 var4, ThreadModuleDump18 var5, ThreadModuleDump18 var6) {
      this.id = var3;
      this.in = var4;
      this.inout = var5;
      this.out = var6;
   }

   @Generated
   public boolean isSupportsAverage() {
      return this.supportsAverage;
   }

   public static class Data extends TypeAdapter<ThreadModuleDump73Type> {
      public void method1(JsonWriter var1, ThreadModuleDump73Type var2) {
         var1.value(var2.getId());
      }

      public ThreadModuleDump73Type method2(JsonReader var1) {
         return ThreadModuleDump73Type.get(var1.nextString());
      }
   }
}
