package com.moonsworth.lunar.client.config.option;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import lombok.Generated;
import com.moonsworth.lunar.client.util.math.Easing;

public enum InterpolationMode implements OptionEnumValue {
   LINEAR("linear", Easing.field2),
   QUAD("quad", Easing.field9, Easing.field11, Easing.field10),
   CUBIC("cubic", Easing.field12, Easing.field14, Easing.field13),
   STEP("step", Easing.field3, Easing.field5, Easing.field4),
   SWING("swing", Easing.field31, Easing.field33, Easing.field32),
   SINE("sine", Easing.field15, Easing.field17, Easing.field16),
   EXP("exp", Easing.field18, Easing.field20, Easing.field19),
   CIRCLE("circle", Easing.field21, Easing.field23, Easing.field22),
   ELASTIC("elastic", Easing.field28, Easing.field30, Easing.field29),
   BACK("back", Easing.field24, Easing.field26, Easing.field25),
   SMOOTH("smooth", Easing.field6),
   SMOOTH2("smooth2", Easing.field7),
   SMOOTHER("smoother", Easing.field8),
   CATMULL_ROM("catmull_rom", Easing.field27, false);

   private final String id;
   private final Easing in;
   private final Easing inout;
   private final Easing out;
   private boolean supportsInOut = true;
   private boolean supportsAverage = true;

   InterpolationMode(String text3, Easing threadmoduledump184) {
      this(text3, threadmoduledump184, threadmoduledump184, threadmoduledump184);
      this.supportsInOut = false;
   }

   InterpolationMode(String text3, Easing threadmoduledump184, boolean flag5) {
      this(text3, threadmoduledump184);
      this.supportsAverage = flag5;
   }

   public Easing get(boolean flag1, boolean flag2) {
      if (!this.supportsInOut) {
         return this.in;
      } else if (flag1 && flag2) {
         return this.inout;
      } else if (flag1) {
         return this.in;
      } else if (flag2) {
         return this.out;
      } else {
         return this == STEP ? this.in : Easing.field2;
      }
   }

   public static InterpolationMode get(String text0) {
      for (InterpolationMode threadmoduledump73type4 : values()) {
         if (threadmoduledump73type4.getId().equals(text0)) {
            return threadmoduledump73type4;
         }
      }

      return null;
   }

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
   public Easing getIn() {
      return this.in;
   }

   @Generated
   public Easing getInout() {
      return this.inout;
   }

   @Generated
   public Easing getOut() {
      return this.out;
   }

   @Generated
   public boolean isSupportsInOut() {
      return this.supportsInOut;
   }

   @Generated
   InterpolationMode(String text3, Easing threadmoduledump184, Easing threadmoduledump185, Easing threadmoduledump186) {
      this.id = text3;
      this.in = threadmoduledump184;
      this.inout = threadmoduledump185;
      this.out = threadmoduledump186;
   }

   @Generated
   public boolean isSupportsAverage() {
      return this.supportsAverage;
   }

   public static class Data extends TypeAdapter<InterpolationMode> {
      public Data() {
      }

      public void method1(JsonWriter jsonwriter1, InterpolationMode threadmoduledump73type2) {
         jsonwriter1.value(threadmoduledump73type2.getId());
      }

      public InterpolationMode method2(JsonReader jsonreader1) {
         return InterpolationMode.get(jsonreader1.nextString());
      }
   }
}
