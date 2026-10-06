package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.replay.audio.AudioStream;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindAudioManager;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;

@SerializedNameOnly
public class AudioSegment extends TimelineSegment<AudioSegment> {
   @SerializedName("source")
   private AudioStream<?> field8 = null;

   public AudioSegment(UndoRedoManager nameplate21) {
      super(nameplate21);
   }

   public AudioSegment method2(UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3) {
      AudioSegment rewinditerator224 = new AudioSegment(nameplate21);

      try {
         rewinditerator224.method9(this.field8.method18());
      } catch (IOException exception6) {
         throw new RuntimeException(exception6);
      }

      return (AudioSegment)this.method5(rewinditerator224, nameplate21, range2, range3);
   }

   @Override
   public void method2(ValueHolder<ReplayContext> threadmoduledump61, Range<Integer> range2, int number3) {
      ReplayContext nameplate44 = (ReplayContext)threadmoduledump61.get();
      ReplayTimeline highlight_35 = nameplate44.method4();
      RewindHandlers rewindhandlers6 = nameplate44.method6();
      if (!rewindhandlers6.method57().method25() || highlight_35.method13().method6()) {
         RewindAudioManager rewindhandlers27 = rewindhandlers6.method60();
         if (!rewindhandlers27.method2(this.field8)) {
            try {
               rewindhandlers27.method1(this.field8);
            } catch (IOException exception15) {
               throw new RuntimeException(exception15);
            }
         }

         PropertyGroup fishing2iterator8 = this.HRCRIHHCHRHIOHHOCHIROOCORCICOI().get("audio");
         float value9 = (Float)fishing2iterator8.method12().get("volume").getOption().get();
         this.field8.method11(value9 / 100.0F);
         double value10 = (Double)fishing2iterator8.method12().get("speed").getOption().get();
         this.field8.method12(value10);
         PropertyGroup fishing2iterator12 = this.HRCRIHHCHRHIOHHOCHIROOCORCICOI().get("decode");
         if (fishing2iterator12 != null) {
            this.field8.setKey(((String)fishing2iterator12.method12().get("key").getOption().get()).toLowerCase());
         } else {
            this.field8.setKey(null);
         }

         try {
            this.field8.method13(rewindhandlers6, nameplate44.method4(), rewindhandlers6.method57().method25());
         } catch (IOException exception14) {
            throw new RuntimeException(exception14);
         }

         super.method2(threadmoduledump61, range2, number3);
      }
   }

   @Override
   protected long method3(ReplayContext nameplate41) {
      return this.field8.getTime();
   }

   @Override
   protected void method4(ReplayTimeline highlight_31, RewindHandlers rewindhandlers2, long index3, long index5) {
      try {
         if (!(Math.abs(index5 / this.field8.method19()) <= Math.ceil(highlight_31.method9()))) {
            if (index5 < 0L) {
               this.field8.stop();
               this.field8.play();
               this.field8.skip(index3);
            } else {
               this.field8.skip(index5);
            }
         }
      } catch (IOException exception8) {
         throw new RuntimeException(exception8);
      }
   }

   @Override
   public long method5(ReplayProjectManager rewind2_31) {
      return this.field8.getDuration();
   }

   @Override
   public String type() {
      return "audio";
   }

   @Override
   public List<String> method15() {
      return List.of("audio");
   }

   @Override
   public JsonElement method16(ReplayTimeline highlight_31, Range<Integer> range2, RewindHandlers rewindhandlers3, Runnable runnable4) {
      JsonObject json5 = super.method16(highlight_31, range2, rewindhandlers3, runnable4).getAsJsonObject();
      float value6 = RewindEditorContext.getZoom();
      if (value6 > 100.0F) {
         value6 = (int)(value6 / 100.0F) * 100;
      } else if (value6 > 10.0F) {
         value6 = (int)(value6 / 10.0F) * 10;
      }

      PropertyGroup fishing2iterator7 = this.HRCRIHHCHRHIOHHOCHIROOCORCICOI().get("audio");
      KeyframeProperty fishing2loader8 = fishing2iterator7.method12().get("volume");
      long number9 = 0L;

      for (Entry entry12 : fishing2loader8.method27().entrySet()) {
         number9 += ((Integer)entry12.getKey()).hashCode() + Math.round((Float)((KeyframeProperty.Keyframe)entry12.getValue()).getValue() * 10.0F);
      }

      String text13 = rewindhandlers3.method40().method42().method3(highlight_31, range2, this, value6, LcuiScreen.method17(), number9, runnable4);
      json5.addProperty("waveform", text13);
      json5.addProperty("missing", !this.field8.isValid());
      if (rewindhandlers3.method40().method44().method5().contains(this.field8.getName())) {
         json5.addProperty("name", this.field8.getName().split("@")[0]);
      } else {
         json5.addProperty("name", this.field8.getName());
      }

      return json5;
   }

   @Generated
   public AudioStream<?> method9() {
      return this.field8;
   }

   @Generated
   public void method9(AudioStream<?> rewindhandlers21) {
      this.field8 = rewindhandlers21;
   }
}
