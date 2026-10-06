package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.replay.timeline.Sliceable;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.timeline.TreeMapImpl;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.DriverFieldType;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.math.Easing;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.config.option.InterpolationMode;
import java.util.Objects;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public abstract class KeyframeProperty<T extends ClientOption, V> implements Sliceable<Integer, KeyframeProperty<T, V>> {
   protected final T field1;
   protected final T field2;
   private final V field3;
   @SerializedName("values")
   private final TreeMapImpl<Integer, KeyframeProperty.Keyframe<V>> field4;
   @SerializedName("showInTimeline")
   private boolean field5 = this.method24();
   private Entry<Integer, KeyframeProperty.Keyframe<V>> field6 = null;
   private Entry<Integer, KeyframeProperty.Keyframe<V>> field7 = null;
   private Entry<Integer, KeyframeProperty.Keyframe<V>> field8 = null;
   private Entry<Integer, KeyframeProperty.Keyframe<V>> field9 = null;
   private Integer field10 = null;
   private Range<Integer> field11 = null;
   private boolean field12 = false;

   public KeyframeProperty(UndoRedoManager nameplate21, T value2) {
      this.field2 = (T)value2;
      this.field1 = (T)value2.method20();
      this.field3 = this.method20((T)value2);
      this.field4 = new TreeMapImpl<>(nameplate21);
      this.method5(null, Integer.MIN_VALUE, true);
      this.field1.method9(this::method1);
   }

   protected void method1() {
      if (this.field10 != null && this.field11 != null) {
         this.method5(this.field11, this.field10, false);
         this.method22(this.field2, this.method21(this.field1));
         Ref.method3().bridge$schedule(() -> LunarEventBus.method29().method12(EventSecond.class, EventSecond::new));
      }
   }

   public KeyframeProperty<T, V> method2() {
      this.field12 = true;
      return this;
   }

   public void reset() {
      this.field4.method1(() -> {
         this.field4.clear();
         this.field1.reset();
         this.method3();
         this.method5(null, Integer.MIN_VALUE, true);
         this.method8();
      });
   }

   public void method3() {
      this.method22(this.field2, this.field3);
   }

   public boolean method4() {
      return !this.field4.isEmpty() && !this.field4.containsKey(Integer.MIN_VALUE) || !this.field1.isDefault();
   }

   public void method5(Range<Integer> range1, int number2, boolean flag3) {
      this.field4.method1(() -> {
         if (!this.field4.isEmpty() && (this.field4.size() != 1 || !this.field4.containsKey(Integer.MIN_VALUE))) {
            if (!flag3) {
               if (number2 < 0 || number2 > (Integer)range1.getMaximum() - (Integer)range1.getMinimum()) {
                  return;
               }

               this.method7(number2, this.method21(this.field1));
            }
         } else {
            this.method7(Integer.MIN_VALUE, this.method21(this.field1));
         }

         this.method8();
      });
   }

   public void method6(Range<Integer> range1, int index2) {
      if (index2 == Integer.MIN_VALUE || index2 >= 0 && index2 <= (Integer)range1.getMaximum() - (Integer)range1.getMinimum()) {
         this.field4.method1(() -> {
            if (this.method9(index2)) {
               this.field4.remove(index2);
            } else {
               this.field4.remove(Integer.MIN_VALUE);
               this.method7(index2, this.method21(this.field1));
            }

            this.method8();
         });
      }
   }

   private void method7(int index1, V value2) {
      Object obj3 = this.method23((V)value2);
      this.field4.method1(() -> {
         this.field4.method5().method4(this::method8, this::method8);
         if (index1 != Integer.MIN_VALUE) {
            this.field4.remove(Integer.MIN_VALUE);
         }

         KeyframeProperty.Keyframe data3x = this.field4.get(index1);
         if (data3x != null) {
            Object obj4 = data3x.value;
            this.field4.method5().method4(() -> data3x.value = (V)obj4, () -> data3x.value = (V)obj3);
            data3x.value = (V)obj3;
         } else {
            KeyframeProperty.Keyframe data6 = new KeyframeProperty.Keyframe(this.method25());
            Object obj5 = this.method23(this.method21(this.field1));
            this.field4.method5().method4(() -> data6.value = (V)obj5, () -> data6.value = (V)obj3);
            data6.value = (V)obj3;
            this.field4.put(index1, data6);
         }

         this.field4.method5().method4(this::method8, this::method8);
      });
   }

   public void method8() {
      this.field6 = null;
      this.field7 = null;
      this.field8 = null;
      this.field9 = null;
      this.field10 = null;
   }

   public boolean method9(int number1) {
      return this.field4.containsKey(number1);
   }

   public abstract V method10(Easing threadmoduledump181, V value2, V value3, V value4, V value5, float value6);

   public void method11(int number1) {
      if (this.field4.isEmpty()) {
         KeyframeProperty.Keyframe data2 = new KeyframeProperty.Keyframe(this.method25());
         data2.value = this.method23(this.method21(this.field1));
         this.field4.method3(Integer.MIN_VALUE, data2);
      } else if (this.field4.size() > 1 && this.field4.containsKey(Integer.MIN_VALUE)) {
         this.field4.method5(Integer.MIN_VALUE);
      }

      if (this.field7 == null || this.field7.getKey() > number1 || this.field8 == null || this.field8.getKey() < number1) {
         this.field7 = this.field4.floorEntry(number1);
         this.field8 = this.field4.ceilingEntry(number1);
         if (this.field7 != null) {
            this.field6 = this.field4.lowerEntry(this.field7.getKey());
            if (this.field6 == null) {
               this.field6 = this.field7;
            }
         } else {
            this.field6 = null;
         }

         if (this.field8 != null) {
            this.field9 = this.field4.higherEntry(this.field8.getKey());
            if (this.field9 == null) {
               this.field9 = this.field8;
            }
         } else {
            this.field9 = null;
         }
      }
   }

   public void method12(ValueHolder<ReplayContext> threadmoduledump61, int number2) {
      Object obj3 = this.getAt(number2);
      if (obj3 != null) {
         boolean flag4 = this.field10 == null || number2 != this.field10;
         this.method15((V)obj3, flag4);
      }
   }

   public V getAt(int number1) {
      this.method11(number1);
      if (this.field7 == null && this.field8 == null) {
         return null;
      }

      if (this.field7 != null && (this.field7.getKey() != Integer.MIN_VALUE || this.field8 == null)) {
         if (this.field8 == null) {
            return this.method23(this.field7.getValue().value);
         }

         float value2;
         if (this.field7.getKey().equals(this.field8.getKey())) {
            value2 = 1.0F;
         } else {
            value2 = (float)(number1 - this.field7.getKey()) / (this.field8.getKey() - this.field7.getKey());
         }

         return this.method10(
            this.field8.getValue().method1(this.field7.getValue().field3),
            this.field6.getValue().value,
            this.field7.getValue().value,
            this.field8.getValue().value,
            this.field9.getValue().value,
            value2
         );
      } else {
         return this.method23(this.field8.getValue().value);
      }
   }

   public Integer method13(int number1) {
      if (this.field7 == null || this.field7.getKey() == Integer.MIN_VALUE) {
         return null;
      } else {
         return number1 > this.field7.getKey() ? this.field7.getKey() : this.field4.lowerKey(number1);
      }
   }

   public Integer method14(int number1) {
      if (this.field8 == null) {
         return null;
      } else {
         return number1 < this.field8.getKey() ? this.field8.getKey() : this.field4.higherKey(number1);
      }
   }

   private void method15(V value1, boolean flag2) {
      this.field10 = null;
      this.field11 = null;
      if (!Objects.deepEquals(value1, this.field2.get()) || !Objects.deepEquals(value1, this.field1.get())) {
         if (flag2) {
            this.method22(this.field1, (V)value1);
         }

         this.method22(this.field2, (V)value1);
      }
   }

   public KeyframeProperty<T, V> method16(KeyframeProperty<T, V> fishing2loader1, UndoRedoManager nameplate22, Range<Integer> range3, Range<Integer> range4) {
      fishing2loader1.field12 = this.field12;
      fishing2loader1.field5 = this.field5;
      Range range5 = Range.between((Integer)range4.getMinimum() - (Integer)range3.getMinimum(), (Integer)range4.getMaximum() - (Integer)range3.getMinimum());

      for (Entry entry7 : this.field4.entrySet()) {
         if (range5.contains((Integer)entry7.getKey())) {
            KeyframeProperty.Keyframe data8 = new KeyframeProperty.Keyframe(((KeyframeProperty.Keyframe)entry7.getValue()).field1);
            data8.field2 = ((KeyframeProperty.Keyframe)entry7.getValue()).field2;
            data8.field3 = ((KeyframeProperty.Keyframe)entry7.getValue()).field3;
            data8.value = this.method23(((KeyframeProperty.Keyframe)entry7.getValue()).value);
            fishing2loader1.method27().put((Integer)entry7.getKey() - (Integer)range5.getMinimum(), data8);
         }
      }

      return fishing2loader1;
   }

   public void method17(JsonObject json1) {
      if (this.method4()) {
         com.moonsworth.lunar.client.config.option.OptionDisplay nameplate42 = (com.moonsworth.lunar.client.config.option.OptionDisplay)this.field1
            .HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field2);
         if (nameplate42 != null && nameplate42.method1() != DriverFieldType.CATEGORY) {
            JsonObject json3 = new JsonObject();
            json1.add("values", json3);
            json1.addProperty("showInTimeline", this.field5);

            for (Entry entry5 : this.field4.entrySet()) {
               JsonObject json6 = new JsonObject();
               this.method18(json6, (KeyframeProperty.Keyframe<V>)entry5.getValue());
               json3.add(entry5.getKey() == Integer.MIN_VALUE ? "all" : ((Integer)entry5.getKey()).toString(), json6);
            }
         }
      }
   }

   public void method18(JsonObject json1, KeyframeProperty.Keyframe<V> data2) {
      this.field10 = null;
      this.field11 = null;
      this.method22(this.field1, data2.value);
      this.field1.method1(json1);
      if (!(this instanceof BooleanProperty)) {
         if (data2.field1 != this.method25()) {
            json1.addProperty("interpolation", data2.field1.getId());
         }

         if (!data2.field2) {
            json1.addProperty("in", false);
         }

         if (!data2.field3) {
            json1.addProperty("out", false);
         }
      }
   }

   public void load(JsonObject json1) {
      JsonObject json2 = json1.getAsJsonObject("values");
      this.field5 = json1.get("showInTimeline").getAsBoolean();

      for (Entry entry4 : json2.entrySet()) {
         int number5 = ((String)entry4.getKey()).equals("all") ? Integer.MIN_VALUE : Integer.parseInt((String)entry4.getKey());
         JsonObject json6 = (JsonObject)entry4.getValue();
         KeyframeProperty.Keyframe data7 = new KeyframeProperty.Keyframe(this.method25());
         this.method19(json6, data7);
         this.field4.method3(number5, data7);
      }

      if (this.field4.size() > 1) {
         this.field4.method5(Integer.MIN_VALUE);
      }
   }

   public void method19(JsonObject json1, KeyframeProperty.Keyframe<V> data2) {
      this.field10 = null;
      this.field11 = null;
      this.field1.load(json1);
      data2.value = this.method23(this.method21(this.field1));
      if (!(this instanceof BooleanProperty)) {
         data2.field1 = json1.has("interpolation") ? InterpolationMode.get(json1.get("interpolation").getAsString()) : this.method25();
         data2.field2 = !json1.has("in") || json1.get("in").getAsBoolean();
         data2.field3 = !json1.has("out") || json1.get("out").getAsBoolean();
         if (this.field12 && !data2.field1.isSupportsAverage()) {
            data2.field1 = this.method25();
         }
      }
   }

   protected V method20(T value1) {
      return (V)value1.getDefaultValue();
   }

   public V method21(T value1) {
      return (V)value1.get();
   }

   public void method22(T value1, V value2) {
      value1.method10(value2);
   }

   protected V method23(V value1) {
      return (V)value1;
   }

   protected boolean method24() {
      return true;
   }

   protected InterpolationMode method25() {
      return InterpolationMode.CUBIC;
   }

   public abstract String type();

   @Generated
   public T getOption() {
      return this.field1;
   }

   @Generated
   public T method26() {
      return this.field2;
   }

   @Generated
   public TreeMapImpl<Integer, KeyframeProperty.Keyframe<V>> method27() {
      return this.field4;
   }

   @Generated
   public boolean method28() {
      return this.field5;
   }

   @Generated
   public void method29(boolean flag1) {
      this.field5 = flag1;
   }

   @Generated
   public void method30(Integer number1) {
      this.field10 = number1;
   }

   @Generated
   public Integer method31() {
      return this.field10;
   }

   @Generated
   public void method32(Range<Integer> range1) {
      this.field11 = range1;
   }

   @SerializedNameOnly
   public static class Keyframe<V> {
      @SerializedName("interpolation")
      private InterpolationMode field1;
      @SerializedName("in")
      private boolean field2 = true;
      @SerializedName("out")
      private boolean field3 = true;
      private V value;

      public Keyframe(InterpolationMode threadmoduledump73type1) {
         this.field1 = threadmoduledump73type1;
      }

      public Easing method1(boolean flag1) {
         return this.field1.get(flag1, this.field3);
      }

      @Generated
      public InterpolationMode method2() {
         return this.field1;
      }

      @Generated
      public boolean method3() {
         return this.field2;
      }

      @Generated
      public boolean method4() {
         return this.field3;
      }

      @Generated
      public V getValue() {
         return this.value;
      }

      @Generated
      public void method5(InterpolationMode threadmoduledump73type1) {
         this.field1 = threadmoduledump73type1;
      }

      @Generated
      public void method6(boolean flag1) {
         this.field2 = flag1;
      }

      @Generated
      public void method7(boolean flag1) {
         this.field3 = flag1;
      }

      @Generated
      public void setValue(V value1) {
         this.value = (V)value1;
      }
   }
}
