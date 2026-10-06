package com.moonsworth.lunar.client.replay.timeline;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.timeline.AudioSegment;
import com.moonsworth.lunar.client.replay.timeline.GameplaySegment;
import com.moonsworth.lunar.client.replay.timeline.TrackCollection;
import com.moonsworth.lunar.client.replay.timeline.SoundTrack;
import com.moonsworth.lunar.client.replay.timeline.GameplayTrack;
import com.moonsworth.lunar.client.replay.timeline.Track;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.timeline.TreeMapImpl;
import com.moonsworth.lunar.client.replay.timeline.TimelineElementRegistry;
import com.moonsworth.lunar.client.replay.project.RewindFileReader;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.replay.recording.ReplayClock;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2Impl2;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.audio.AudioWaveformRenderer;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.replay.export.ExportSettings;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.config.option.InterpolationMode;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class ReplayTimeline {
   @SerializedName("id")
   private UUID id;
   @SerializedName("name")
   private String name = "Untitled";
   @SerializedName("tracks")
   private TrackCollection field1;
   @SerializedName("renderSettings")
   private ExportSettings field2 = new ExportSettings();
   @SerializedName("playhead")
   private int field3 = 0;
   @SerializedName("paused")
   private boolean paused = false;
   @SerializedName("ruleOfThirdsOverlay")
   private boolean field4 = false;
   @SerializedName("quartersOverlay")
   private boolean field5 = false;
   private long field6 = -1L;
   private long field7 = 0L;
   private double field8 = 0.0;
   private boolean field9 = false;

   public ReplayTimeline(UndoRedoManager nameplate21) {
      this.field1 = new TrackCollection(nameplate21);
   }

   public HashMapImpl method1() {
      return this.field1.method5();
   }

   public void method2(TimelineElementRegistry holograms1, RewindFileReader rewind32, ThumbnailManager highlight33, AudioWaveformRenderer rewindhandlers24, UndoRedoManager nameplate25, File file6, int number7, UUID uuid8) {
      nameplate25.method1();
      long number9 = rewind32.method13().method1();
      Range range11 = Range.between(number7, number7 + (int)(number9 / this.method9()));
      GameplayTrack guiimpl312 = null;

      for (Track gui_214 : this.field1.method3()) {
         if (gui_214.getId().equals(uuid8)) {
            guiimpl312 = (GameplayTrack)gui_214;
            break;
         }
      }

      if (guiimpl312 == null) {
         guiimpl312 = new GameplayTrack(nameplate25, this.method1());
         this.field1.method3().add(guiimpl312);
      }

      GameplaySegment rewinditerator2322 = new GameplaySegment(highlight33, nameplate25);
      rewinditerator2322.method13(rewind32.method13().getId());
      rewinditerator2322.method1(range11, holograms1, nameplate25);
      guiimpl312.method5().method1(range11, rewinditerator2322);
      RewindEditorContext.setSelectedLayer(rewinditerator2322);
      RewindEditorContext.setLinkSelectionEnabled(true);
      HashSet set23 = new HashSet();
      set23.add(rewinditerator2322);
      List list15 = List.of("system", "mic");
      List list16 = this.field1.method4().stream().toList();
      int index17 = 0;

      for (String text19 : list15) {
         if (rewind32.has("audio/" + text19 + ".dat")) {
            if (text19.equals("system")) {
               PropertyGroup fishing2iterator20 = rewinditerator2322.HRCRIHHCHRHIOHHOCHIROOCORCICOI().get("sounds");
               if (fishing2iterator20 != null) {
                  KeyframeProperty.Keyframe data21 = new KeyframeProperty.Keyframe(InterpolationMode.LINEAR);
                  data21.setValue(false);
                  fishing2iterator20.method12().get("replayGameSounds").method27().put(Integer.MIN_VALUE, data21);
               }
            }

            SoundTrack guiimpl224;
            if (index17 >= list16.size()) {
               guiimpl224 = new SoundTrack(nameplate25, this.method1());
               this.field1.method4().add(guiimpl224);
            } else {
               guiimpl224 = (SoundTrack)list16.get(index17);
            }

            AudioSegment rewinditerator2225 = new AudioSegment(nameplate25);
            rewinditerator2225.method9(new Rewindhandlers2Impl2(file6, rewind32.method13().getId() + "://" + text19, rewindhandlers24, rewind32));
            rewinditerator2225.method5(range11, holograms1, nameplate25);
            guiimpl224.method5().method1(range11, rewinditerator2225);
            set23.add(rewinditerator2225);
            index17++;
         }
      }

      if (this.field1.method4().isEmpty()) {
         this.field1.method4().add(new SoundTrack(nameplate25, this.method1()));
      }

      this.field1.method6().method9(nameplate25, set23);
      nameplate25.endBatch();
   }

   public int method3() {
      int number1 = 0;

      for (Track gui_23 : this.field1) {
         TreeMapImpl treemapimpl4 = gui_23.method5().method11();
         if (!treemapimpl4.isEmpty()) {
            number1 = Math.max(number1, (Integer)((Range)treemapimpl4.lastKey()).getMaximum());
         }
      }

      return number1;
   }

   public long getDurationMs() {
      return (long)(this.method3() * this.method9());
   }

   public void method4(ValueHolder<ReplayContext> threadmoduledump61, int number2) {
      for (Track gui_24 : this.field1.method4()) {
         gui_24.method2(threadmoduledump61, number2);
      }

      boolean flag6 = false;

      for (Track gui_25 : Lists.reverse(this.field1.method3().stream().toList())) {
         if (!(gui_25 instanceof GameplayTrack) || !flag6) {
            flag6 |= gui_25.method2(threadmoduledump61, number2) && gui_25 instanceof GameplayTrack;
         }
      }

      for (Track gui_29 : this.field1.method2()) {
         gui_29.method2(threadmoduledump61, number2);
      }
   }

   public void method5(UndoRedoManager nameplate21, int number2) {
      nameplate21.method1();

      for (Track gui_24 : this.field1) {
         gui_24.method5().method5(number2);
      }

      nameplate21.endBatch();
   }

   public void method6() {
      this.field6 = Ref.method3().bridge$getRealSystemTime();
      this.field7 = 0L;
      this.field8 = 0.0;
   }

   public void method7(ReplayClock rewindhandlers1, boolean flag2) {
      long number3 = Ref.method3().bridge$getRealSystemTime();
      if (this.field6 <= 0L) {
         this.field6 = number3;
      } else {
         long number5 = number3 - this.field6;
         if (number5 > 0L) {
            this.field6 = number3;
            if (!RewindEditorContext.isDragging() && (flag2 || !this.paused) && !rewindhandlers1.method5() && rewindhandlers1.method2() && this.field3 < this.method3() && !this.field9) {
               this.field7 += number5;
               double value7 = this.method9();

               while (this.field7 >= (int)value7) {
                  this.field3++;
                  this.field7 -= (int)value7;

                  for (this.field8 += value7 - (int)value7; this.field8 >= 1.0; this.field7--) {
                     this.field8--;
                  }
               }
            }
         }
      }
   }

   public void method8(int number1) {
      if (!this.field9) {
         this.field3 = number1;
      }
   }

   public double method9() {
      return 1000.0 / this.field2.getFps();
   }

   @Generated
   public UUID getId() {
      return this.id;
   }

   @Generated
   public void setId(UUID uuid1) {
      this.id = uuid1;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public void setName(String text1) {
      this.name = text1;
   }

   @Generated
   public TrackCollection method11() {
      return this.field1;
   }

   @Generated
   public void method12(TrackCollection gui2_21) {
      this.field1 = gui2_21;
   }

   @Generated
   public ExportSettings method13() {
      return this.field2;
   }

   @Generated
   public void method14(ExportSettings rewindhandlersnameplate1) {
      this.field2 = rewindhandlersnameplate1;
   }

   @Generated
   public int method15() {
      return this.field3;
   }

   @Generated
   public boolean isPaused() {
      return this.paused;
   }

   @Generated
   public void setPaused(boolean flag1) {
      this.paused = flag1;
   }

   @Generated
   public boolean method18() {
      return this.field4;
   }

   @Generated
   public void method19(boolean flag1) {
      this.field4 = flag1;
   }

   @Generated
   public boolean method20() {
      return this.field5;
   }

   @Generated
   public void method21(boolean flag1) {
      this.field5 = flag1;
   }

   @Generated
   public boolean method22() {
      return this.field9;
   }

   @Generated
   public void method23(boolean flag1) {
      this.field9 = flag1;
   }
}
