package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.replay.timeline.UuidProvider;
import com.moonsworth.lunar.client.replay.timeline.Sliceable;
import com.moonsworth.lunar.client.replay.timeline.SegmentLinkManager;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.PropertyMap;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.timeline.TimelineElementRegistry;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;

@SerializedNameOnly
public abstract class RewindIterator<T extends RewindIterator<T>> implements UuidProvider<UUID>, Sliceable<Integer, T> {
   private UUID id = UUID.randomUUID();
   @SerializedName("link")
   private UUID field1 = null;
   @SerializedName("properties")
   private PropertyMap field2;
   @SerializedName("type")
   private final String field3 = this.type();
   @SerializedName("initialDuration")
   private int field4 = -1;
   @SerializedName("freezeDuration")
   private int field5 = 0;
   private Double field6 = null;

   public RewindIterator(UndoRedoManager nameplate21) {
      this.field2 = new PropertyMap(nameplate21);
   }

   public void method1(Range<Integer> range1, TimelineElementRegistry holograms2, UndoRedoManager nameplate23) {
      if (this.field4 < 0) {
         this.field4 = (Integer)range1.getMaximum() - (Integer)range1.getMinimum();
      }

      for (String text5 : this.method15()) {
         this.field2.putIfAbsent(text5, holograms2.method2(nameplate23, text5));
      }
   }

   public void method2(ValueHolder<ReplayContext> threadmoduledump61, Range<Integer> range2, int number3) {
      if (this.field4 < 0) {
         this.field4 = (int)(((Integer)range2.getMaximum() - (Integer)range2.getMinimum()) * this.method7());
      }

      for (PropertyGroup fishing2iterator5 : this.field2.values()) {
         fishing2iterator5.method6(threadmoduledump61, number3 - (Integer)range2.getMinimum());
      }
   }

   protected T method3(T value1, UndoRedoManager nameplate22, Range<Integer> range3, Range<Integer> range4) {
      ReplayTimeline highlight_35 = RewindEditorContext.getTimeline();
      if (highlight_35 != null) {
         SegmentLinkManager highlight26 = highlight_35.method11().method6();
         long number7 = (Integer)range3.getMinimum() + (Integer)range4.getMinimum();
         if (this.field1 != null) {
            number7 += this.field1.getMostSignificantBits() + this.field1.getLeastSignificantBits();
         }

         Random random9 = new Random(number7);
         byte[] items10 = new byte[16];
         random9.nextBytes(items10);
         items10[6] = (byte)(items10[6] & 15);
         items10[6] = (byte)(items10[6] | 64);
         items10[8] = (byte)(items10[8] & 63);
         items10[8] = (byte)(items10[8] | -128);
         long number11 = 0L;
         long number13 = 0L;

         for (int index15 = 0; index15 < 8; index15++) {
            number11 = number11 << 8 | items10[index15] & 0xFF;
         }

         for (int index20 = 8; index20 < 16; index20++) {
            number13 = number13 << 8 | items10[index20] & 0xFF;
         }

         value1.method23(new UUID(number11, number13));
         highlight26.method7(value1.method17(), value1.getId());
      }

      PropertyMap linkedhashmapimpl16 = new PropertyMap(nameplate22);

      for (Entry entry8 : this.field2.entrySet()) {
         linkedhashmapimpl16.put((String)entry8.getKey(), (PropertyGroup)((PropertyGroup)entry8.getValue()).HRICOROOOCCOCOROCRHHCRRIRCOICO(nameplate22, range3, range4));
      }

      value1.method24(linkedhashmapimpl16);
      int number18 = (Integer)range4.getMaximum() - (Integer)range4.getMinimum();
      int number19 = value1.method6(number18);
      value1.method25((int)((number18 - number19) * this.method7()));
      value1.method26(number19);
      return (T)value1;
   }

   public int method4(Range<Integer> range1) {
      int number2 = (Integer)range1.getMaximum() - (Integer)range1.getMinimum();
      return this.method5() + this.method6(number2) - number2;
   }

   public int method5() {
      return (int)(this.field4 / this.method7());
   }

   public int method6(int number1) {
      return this.method12(number1, true);
   }

   public double method7() {
      return this.method8(this.field4);
   }

   protected double method8(int number1) {
      double value2 = this.method9(Range.between(0, number1));
      double value4 = value2 / number1;
      return value4 < 0.01 ? 1.0 : value4;
   }

   protected double method9(Range<Integer> range1) {
      return this.method11(range1, false);
   }

   protected double method10(Range<Integer> range1) {
      return this.method11(range1, true);
   }

   protected double method11(Range<Integer> range1, boolean flag2) {
      PropertyGroup fishing2iterator3 = this.field2.get("speed");
      if (fishing2iterator3 == null) {
         fishing2iterator3 = this.method18().get("audio");
      }

      if (fishing2iterator3 != null && fishing2iterator3.isEnabled()) {
         NumberProperty fishing2loader34 = (NumberProperty)fishing2iterator3.method12().get("speed");
         if (fishing2loader34.CORHOROOHORCHOHICCOOOCRICHRRHC().isEmpty()) {
            this.field6 = 1.0;
            return (Integer)range1.getMaximum() - (Integer)range1.getMinimum();
         }

         ReplayProjectManager rewind2_35 = RewindEditorContext.getProject();
         if (flag2 && rewind2_35 != null && rewind2_35.method40().method3() && this.field6 != null) {
            return ((Integer)range1.getMaximum() - (Integer)range1.getMinimum()) * this.field6;
         }

         int number6 = (Integer)range1.getMaximum() - (Integer)range1.getMinimum();
         double value7 = 0.0;
         Integer number9 = null;
         Double value10 = null;
         boolean flag11 = false;

         for (Entry entry13 : fishing2loader34.CORHOROOHORCHOHICCOOOCRICHRRHC().entrySet()) {
            int number14 = (Integer)entry13.getKey();
            if (number14 >= number6) {
               break;
            }

            double value15 = (Double)((KeyframeProperty.Keyframe)entry13.getValue()).getValue();
            if (number14 == Integer.MIN_VALUE) {
               number14 = 0;
            }

            if (number14 >= 0) {
               if (value10 == null) {
                  value7 += value15 * Math.min(number14, number6);
               } else if (number6 > number14) {
                  double value17 = ((KeyframeProperty.Keyframe)entry13.getValue()).method1(flag11).method6();
                  value7 += (value10 + (value15 - value10) * value17) * (number14 - number9);
               } else {
                  for (int index19 = number9; index19 < number6; index19++) {
                     value7 += ((KeyframeProperty.Keyframe)entry13.getValue()).method1(flag11).method4(0.0, value10, value15, 0.0, (double)(index19 - number9) / (number14 - number9));
                  }
               }

               value10 = value15;
               number9 = number14;
               flag11 = ((KeyframeProperty.Keyframe)entry13.getValue()).method4();
            }
         }

         if (number9 != null && number9 < number6) {
            value7 += value10 * (number6 - number9);
         }

         this.field6 = ((Integer)range1.getMaximum() - (Integer)range1.getMinimum()) / Math.max(0.001, value7);
         return value7;
      } else {
         this.field6 = 1.0;
         return (Integer)range1.getMaximum() - (Integer)range1.getMinimum();
      }
   }

   protected int method12(int number1, boolean flag2) {
      PropertyGroup fishing2iterator3 = this.field2.get("speed");
      if (fishing2iterator3 == null) {
         fishing2iterator3 = this.field2.get("audio");
      }

      if (fishing2iterator3 != null && fishing2iterator3.isEnabled()) {
         KeyframeProperty fishing2loader4 = fishing2iterator3.method12().get("freeze");
         if (!(fishing2loader4 instanceof BooleanProperty fishing2loader25)) {
            return 0;
         } else {
            int number6 = 0;
            Integer number7 = null;

            for (Entry entry9 : fishing2loader25.method27().entrySet()) {
               int number10 = entry9.getKey() == Integer.MIN_VALUE ? 0 : Math.max(0, (Integer)entry9.getKey());
               boolean flag11 = Boolean.TRUE.equals(((KeyframeProperty.Keyframe)entry9.getValue()).getValue());
               if (flag11) {
                  if (number7 == null) {
                     number7 = number10;
                  }
               } else if (number7 != null) {
                  number6 += this.method13(number7, number10, number1);
                  number7 = null;
               }
            }

            if (number7 != null && !flag2) {
               number6 += this.method13(number7, number1, number1);
            }

            return number6;
         }
      } else {
         return 0;
      }
   }

   private int method13(int number1, int number2, int number3) {
      int number4 = Math.min(Math.max(number1, 0), number3);
      int number5 = Math.min(Math.max(number2, 0), number3);
      return Math.max(0, number5 - number4);
   }

   public UUID getId2() {
      return this.id;
   }

   public abstract String type();

   public List<String> method15() {
      return List.of();
   }

   public JsonElement method16(ReplayTimeline highlight_31, Range<Integer> range2, RewindHandlers rewindhandlers3, Runnable runnable4) {
      JsonObject json5 = new JsonObject();
      json5.addProperty("min", (Number)range2.getMinimum());
      json5.addProperty("max", (Number)range2.getMaximum());
      json5.addProperty("type", this.getType());
      json5.addProperty("id", this.getId().toString());
      json5.addProperty("trimLeft", 1000000);
      json5.addProperty("trimRight", 1000000);
      return json5;
   }

   @Generated
   public UUID getId() {
      return this.id;
   }

   @Generated
   public UUID method17() {
      return this.field1;
   }

   @Generated
   public PropertyMap method18() {
      return this.field2;
   }

   @Generated
   public String getType() {
      return this.field3;
   }

   @Generated
   public int method19() {
      return this.field4;
   }

   @Generated
   public int method20() {
      return this.field5;
   }

   @Generated
   public Double method21() {
      return this.field6;
   }

   @Generated
   public void setId(UUID uuid1) {
      this.id = uuid1;
   }

   @Generated
   public void method23(UUID uuid1) {
      this.field1 = uuid1;
   }

   @Generated
   public void method24(PropertyMap linkedhashmapimpl1) {
      this.field2 = linkedhashmapimpl1;
   }

   @Generated
   public void method25(int number1) {
      this.field4 = number1;
   }

   @Generated
   public void method26(int number1) {
      this.field5 = number1;
   }

   @Generated
   public void method27(Double value1) {
      this.field6 = value1;
   }
}
