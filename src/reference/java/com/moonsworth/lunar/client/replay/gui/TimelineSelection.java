package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.client.replay.timeline.Track;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.util.Map.Entry;
import org.apache.commons.lang3.Range;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;

public class TimelineSelection {
   private final RewindHandlers field1;
   private final ReplayTimeline field2;
   private final UndoRedoManager field3;
   private final Track<? extends RewindIterator<?>> field4;
   private final Entry<Range<Integer>, RewindIterator<?>> field5;

   public TimelineSelection(
      RewindHandlers rewindhandlers1, ReplayTimeline highlight_32, UndoRedoManager nameplate23, Track<? extends RewindIterator<?>> gui_24, Entry<Range<Integer>, RewindIterator<?>> entry
   ) {
      this.field1 = rewindhandlers1;
      this.field2 = highlight_32;
      this.field3 = nameplate23;
      this.field4 = gui_24;
      this.field5 = entry;
   }

   public RewindHandlers method1() {
      return this.field1;
   }

   public ReplayTimeline method2() {
      return this.field2;
   }

   public UndoRedoManager method3() {
      return this.field3;
   }

   public Track<? extends RewindIterator<?>> method4() {
      return this.field4;
   }

   public Entry<Range<Integer>, RewindIterator<?>> method5() {
      return this.field5;
   }
}
