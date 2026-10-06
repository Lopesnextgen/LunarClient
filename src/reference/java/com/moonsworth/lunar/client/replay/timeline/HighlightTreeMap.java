package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.UuidProvider;
import com.moonsworth.lunar.client.replay.timeline.Sliceable;
import com.moonsworth.lunar.client.replay.timeline.SegmentTimeline;
import java.util.Comparator;
import java.util.UUID;
import java.util.AbstractMap.SimpleImmutableEntry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

public class HighlightTreeMap<V extends Sliceable<Integer, V> & UuidProvider<UUID>> extends TreeMapImpl<Range<Integer>, V> {
   private final SegmentTimeline<V> field2;

   public HighlightTreeMap(UndoRedoManager nameplate21, Comparator<Range<Integer>> comparator2, SegmentTimeline<V> highlight3) {
      super(nameplate21, comparator2);
      this.field2 = highlight3;
   }

   public V method1(Range<Integer> range1, V v) {
      this.field2.method12().put(((UuidProvider)v).method1(), new SimpleImmutableEntry<>(range1, v));
      return super.method3(range1, (V)v);
   }

   public V method2(Object object) {
      Sliceable fishing22 = super.method5(object);
      if (fishing22 != null) {
         this.field2.method12().remove(((UuidProvider)fishing22).method1());
      }

      return (V)fishing22;
   }

   @Override
   public void method7() {
      this.field2.method12().clear();
      super.method7();
   }

   @Generated
   public SegmentTimeline<V> method4() {
      return this.field2;
   }
}
