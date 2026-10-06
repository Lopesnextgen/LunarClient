package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import com.moonsworth.lunar.client.replay.timeline.HashMapImpl;
import com.moonsworth.lunar.client.replay.timeline.SegmentTimeline;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.Function;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public abstract class Track<T extends RewindIterator<T>> {
   @SerializedName("id")
   private final UUID field1 = UUID.randomUUID();
   @SerializedName("layers")
   private final SegmentTimeline<T> field2;
   @SerializedName("type")
   private final String field3 = this.type();
   @SerializedName("showKeyframes")
   private boolean field4 = true;
   @SerializedName("enabled")
   private boolean enabled = true;

   public Track(UndoRedoManager nameplate21, HashMapImpl hashmapimpl2) {
      this.field2 = new SegmentTimeline<>(nameplate21, hashmapimpl2);
      this.field2.method15(() -> this.method3(null));
   }

   public Entry<Range<Integer>, T> method1(int number1) {
      return this.field2.method7(number1);
   }

   public boolean method2(ValueHolder<ReplayContext> threadmoduledump61, int number2) {
      if (!this.enabled) {
         return false;
      } else {
         Entry entry3 = this.method1(number2);
         if (entry3 != null) {
            ((RewindIterator)entry3.getValue()).method2(threadmoduledump61, (Range<Integer>)entry3.getKey(), number2);
            return true;
         } else {
            return false;
         }
      }
   }

   public void method3(ValueHolder<ReplayContext> threadmoduledump61) {
      for (Entry entry3 : new HashMap<>(this.field2.method11()).entrySet()) {
         Range range4 = (Range)entry3.getKey();
         RewindIterator rewinditerator5 = (RewindIterator)entry3.getValue();
         int number6 = rewinditerator5.method4(range4);
         if (Math.abs(number6) > 1) {
            ReplayTimeline highlight_37 = threadmoduledump61 == null ? null : ((ReplayContext)threadmoduledump61.get()).method4();
            int number8 = highlight_37 == null ? 0 : highlight_37.method15();
            number8 = this.method4(range4, (T)rewinditerator5, number6, number8);
            if (highlight_37 != null) {
               highlight_37.method8(number8);
            }
         } else {
            int number9 = (Integer)range4.getMaximum() - (Integer)range4.getMinimum();
            rewinditerator5.method26(Math.max(0, number9 - rewinditerator5.method5()));
         }
      }
   }

   private int method4(Range<Integer> range1, T value2, int number3, int number4) {
      int number5 = (Integer)range1.getMinimum();
      int number6 = (Integer)range1.getMaximum() - number5;
      int number7 = (Integer)range1.getMaximum() + number3;
      int number8 = Math.max(1, number6 - value2.method20());
      int number9 = Math.max(1, value2.method5());
      double value10 = (double)number9 / number8;
      this.field2.method8(range1);
      this.field2.method10((Integer)range1.getMaximum(), number3, true);
      Range range12 = Range.between(number5, number7);
      this.field2.method1(range12, (T)value2);
      value2.method26(Math.max(0, number7 - number5 - number9));
      Function function13 = arg2x -> (int)Math.round(arg2x.intValue() * value10);
      Range range14 = Range.between(0, number6);
      value2.method18()
         .values()
         .stream()
         .map(PropertyGroup::method12)
         .map(Map::values)
         .flatMap(Collection::stream)
         .map(KeyframeProperty::method27)
         .forEach(arg2x -> {
            HashMap map3x = new HashMap();

            for (Integer index5x : new ArrayList(arg2x.keySet())) {
               if (range14.contains(index5x)) {
                  map3x.put((Integer)function13.apply(index5x), (KeyframeProperty.Keyframe)arg2x.remove(index5x));
               }
            }

            for (Entry entry7x : map3x.entrySet()) {
               arg2x.put((Integer)entry7x.getKey(), (KeyframeProperty.Keyframe)entry7x.getValue());
            }
         });
      if (range1.contains(number4)) {
         number4 = number5 + (Integer)function13.apply(number4 - number5);
      }

      return number4;
   }

   public abstract String type();

   @Generated
   public UUID getId() {
      return this.field1;
   }

   @Generated
   public SegmentTimeline<T> method5() {
      return this.field2;
   }

   @Generated
   public String getType() {
      return this.field3;
   }

   @Generated
   public boolean method6() {
      return this.field4;
   }

   @Generated
   public boolean isEnabled() {
      return this.enabled;
   }

   @Generated
   public void method7(boolean flag1) {
      this.field4 = flag1;
   }

   @Generated
   public void setEnabled(boolean flag1) {
      this.enabled = flag1;
   }
}
