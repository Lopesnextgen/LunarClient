package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import com.moonsworth.lunar.client.replay.timeline.GameplaySegment;
import com.moonsworth.lunar.client.replay.timeline.UuidProvider;
import com.moonsworth.lunar.client.replay.timeline.Sliceable;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.timeline.TreeMapImpl;
import com.moonsworth.lunar.client.replay.timeline.HighlightTreeMap;
import com.moonsworth.lunar.client.replay.timeline.SegmentRegistry;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

public class SegmentTimeline<V extends Sliceable<Integer, V> & UuidProvider<UUID>> {
   private final TreeMapImpl<Range<Integer>, V> field1;
   private Map field2;
   private Runnable field3;

   public SegmentTimeline(UndoRedoManager nameplate21, Map map2) {
      this.field2 = map2;
      this.field1 = new HighlightTreeMap<>(nameplate21, Comparator.comparing(Range::getMinimum), this);
   }

   public void method1(Range<Integer> range1, V value2) {
      this.field1.method1(() -> {
         this.method2(range1);
         this.field1.put(range1, (V)value2);
      });
   }

   public void method2(Range<Integer> range1) {
      this.field1.method1(() -> {
         this.field1.method5().method4(this::method3, this::method3);
         HashMap map2 = new HashMap();
         HashMap map3 = new HashMap();

         for (Entry entry5 : this.field1.entrySet()) {
            Range range6 = (Range)entry5.getKey();
            Range range7 = Range.between((Integer)range6.getMinimum(), (Integer)range6.getMaximum() - 1);
            if (range7.isOverlappedBy(range1)) {
               map2.put((Range)entry5.getKey(), (Sliceable)entry5.getValue());
               if (range1.isAfter((Integer)range6.getMinimum())) {
                  Range range8 = Range.between((Integer)range6.getMinimum(), (Integer)range1.getMinimum());
                  Sliceable fishing29 = (Sliceable)entry5.getValue();
                  if (!range8.equals(range6)) {
                     fishing29 = (Sliceable)((Sliceable)entry5.getValue()).method1(this.field1.method5(), range6, range8);
                  }

                  map3.put(range8, fishing29);
                  this.method4((V)fishing29, range8);
               }

               if (range1.isBefore((Integer)range6.getMaximum())) {
                  Range range10 = Range.between((Integer)range1.getMaximum(), (Integer)range6.getMaximum());
                  Sliceable fishing211 = (Sliceable)entry5.getValue();
                  if (!range10.equals(range6)) {
                     fishing211 = (Sliceable)((Sliceable)entry5.getValue()).method1(this.field1.method5(), range6, range10);
                  }

                  map3.put(range10, fishing211);
                  this.method4((V)fishing211, range10);
               }
            }
         }

         this.field1.method5().method4(() -> map2.forEach(this.field1::method3), () -> this.field1.keySet().removeAll(map2.keySet()));
         this.field1.keySet().removeAll(map2.keySet());
         this.field1.putAll(map3);
         this.method3();
         this.field1.method5().method4(this::method3, this::method3);
      });
   }

   private void method3() {
      if (this.field3 != null) {
         this.field3.run();
      }
   }

   private void method4(V value1, Range<Integer> range2) {
      if (value1 instanceof GameplaySegment rewinditerator233) {
         rewinditerator233.method11().method5(new ThumbnailRequest((GameplaySegment)value1, range2, rewinditerator233.method10(), rewinditerator233.method8()));
      }
   }

   public void method5(Integer number1) {
      this.method2(Range.is(number1));
   }

   public V method6(Integer number1) {
      Entry entry2 = this.method7(number1);
      return (V)(entry2 == null ? null : entry2.getValue());
   }

   public Entry<Range<Integer>, V> method7(Integer number1) {
      Entry entry2 = this.field1.floorEntry(Range.is(number1));
      return entry2 != null && ((Range)entry2.getKey()).contains(number1) ? entry2 : null;
   }

   public V method8(Range<Integer> range1) {
      return this.field1.remove(range1);
   }

   public void method9(Range<Integer> range1) {
      boolean flag2 = !this.field1.method5().method3();
      if (flag2) {
         this.field1.method5().method1();
      }

      this.method2(range1);
      int number3 = (Integer)range1.getMaximum() - (Integer)range1.getMinimum();
      this.method10((Integer)range1.getMinimum(), -number3, true);
      if (flag2) {
         this.field1.method5().endBatch();
      }
   }

   public void method10(int number1, int number2, boolean flag3) {
      this.field1.method1(() -> {
         HashMap map4 = new HashMap();
         SortedMap sortedmap5 = flag3 ? this.field1.tailMap(Range.is(number1)) : this.field1.headMap(Range.is(number1));
         sortedmap5.forEach((arg2xx, arg3xx) -> map4.put(Range.between((Integer)arg2xx.getMinimum() + number2, (Integer)arg2xx.getMaximum() + number2), arg3xx));
         HashMap map6 = new HashMap<>(sortedmap5);
         this.field1.method5().method4(() -> this.field1.putAll(map6), () -> this.field1.keySet().removeAll(map6.keySet()));
         sortedmap5.clear();
         this.field1.putAll(map4);
      });
   }

   @Generated
   public TreeMapImpl<Range<Integer>, V> method11() {
      return this.field1;
   }

   @Generated
   public Map method12() {
      return this.field2;
   }

   @Generated
   public Runnable method13() {
      return this.field3;
   }

   @Generated
   public void method14(Map map1) {
      this.field2 = map1;
   }

   @Generated
   public void method15(Runnable runnable1) {
      this.field3 = runnable1;
   }

   public static class HighlightAdapter implements JsonDeserializer<SegmentTimeline<?>>, JsonSerializer<SegmentTimeline<?>> {
      private final ReplayProjectManager field1;

      public JsonElement method1(SegmentTimeline highlight1, Type type2, JsonSerializationContext jsonserializationcontext3) {
         return jsonserializationcontext3.serialize(highlight1.field1);
      }

      public SegmentTimeline<?> method2(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
         SegmentTimeline highlight4 = new SegmentTimeline(this.field1.method40(), new HashMap());

         for (Entry entry6 : element1.getAsJsonObject().entrySet()) {
            Range range7 = (Range)jsondeserializationcontext3.deserialize(new JsonPrimitive((String)entry6.getKey()), Range.class);
            String text8 = ((JsonElement)entry6.getValue()).getAsJsonObject().get("type").getAsString();
            Object obj9 = jsondeserializationcontext3.deserialize((JsonElement)entry6.getValue(), SegmentRegistry.getType(text8));
            if (obj9 instanceof RewindIterator rewinditerator10) {
               rewinditerator10.setId(UUID.randomUUID());
               rewinditerator10.method1(range7, this.field1.method38(), this.field1.method40());
            }

            if (obj9 instanceof GameplaySegment rewinditerator2311) {
               rewinditerator2311.method1(range7, this.field1.method41());
            }

            highlight4.method1(range7, (V)obj9);
         }

         return highlight4;
      }

      @Generated
      public HighlightAdapter(ReplayProjectManager rewind2_31) {
         this.field1 = rewind2_31;
      }
   }
}
