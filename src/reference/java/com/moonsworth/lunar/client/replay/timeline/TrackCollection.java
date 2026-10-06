package com.moonsworth.lunar.client.replay.timeline;

import com.google.common.collect.Iterators;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import com.moonsworth.lunar.client.replay.timeline.HashMapImpl;
import com.moonsworth.lunar.client.replay.timeline.SegmentLinkManager;
import com.moonsworth.lunar.client.replay.timeline.LinkedHashSetImpl;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import java.util.Iterator;
import java.util.Set;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

@SerializedNameOnly
public class TrackCollection implements Iterable<Track<?>> {
   @SerializedName("effects")
   private Set<EffectTrack> field1;
   @SerializedName("gameplay")
   private Set<GameplayTrack> field2;
   @SerializedName("audios")
   private Set<SoundTrack> field3;
   private final HashMapImpl field4 = new HashMapImpl();
   private SegmentLinkManager field5 = new SegmentLinkManager(this.field4);

   public TrackCollection(UndoRedoManager nameplate21) {
      this.field1 = new LinkedHashSetImpl<>(nameplate21);
      this.field2 = new LinkedHashSetImpl<>(nameplate21);
      this.field3 = new LinkedHashSetImpl<>(nameplate21);
   }

   @NotNull
   @Override
   public Iterator<Track<?>> iterator() {
      return Iterators.concat(this.field3.iterator(), this.field2.iterator(), this.field1.iterator());
   }

   public int size() {
      return this.field1.size() + this.field2.size() + this.field3.size();
   }

   private void method1(Track<?> gui_21) {
      this.field4.putAll(gui_21.method5().method12());
      gui_21.method5().method14(this.field4);
      gui_21.method5().method15(() -> gui_21.method3(null));

      for (RewindIterator rewinditerator3 : gui_21.method5().method11().values()) {
         this.field5.method7(rewinditerator3.method17(), rewinditerator3.getId());
      }
   }

   @Generated
   public Set<EffectTrack> method2() {
      return this.field1;
   }

   @Generated
   public Set<GameplayTrack> method3() {
      return this.field2;
   }

   @Generated
   public Set<SoundTrack> method4() {
      return this.field3;
   }

   @Generated
   public HashMapImpl method5() {
      return this.field4;
   }

   @Generated
   public SegmentLinkManager method6() {
      return this.field5;
   }

   @Generated
   public void method7(SegmentLinkManager highlight21) {
      this.field5 = highlight21;
   }
}
