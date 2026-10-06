package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.replay.timeline.ThumbnailRequest;
import com.moonsworth.lunar.client.replay.timeline.ThumbnailManager;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.timeline.TimelineElementRegistry;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.Generated;
import org.apache.commons.lang3.Range;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;

@SerializedNameOnly
public class GameplaySegment extends TimelineSegment<GameplaySegment> {
   @SerializedName("rewindId")
   private UUID field8 = null;
   private ThumbnailManager field9;
   private String field10;

   public GameplaySegment(ThumbnailManager highlight31, UndoRedoManager nameplate22) {
      super(nameplate22);
      this.method1(null, highlight31);
   }

   public void method1(Range<Integer> range1, ThumbnailManager highlight32) {
      this.field9 = highlight32;
      if (highlight32 != null) {
         try {
            this.field10 = highlight32.method3(this.field8, this.method8());
            if (this.field10 == null && range1 != null) {
               highlight32.method5(new ThumbnailRequest(this, range1, this.field8, this.method8()));
            }
         } catch (IOException exception4) {
            exception4.printStackTrace();
         }
      }
   }

   @Override
   public void method1(Range<Integer> range1, TimelineElementRegistry holograms2, UndoRedoManager nameplate23) {
      super.method5(range1, holograms2, nameplate23);
      if (this.field9 != null && this.field10 == null) {
         this.field9.method5(new ThumbnailRequest(this, range1, this.field8, this.method8()));
      }
   }

   public GameplaySegment method3(UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3) {
      GameplaySegment rewinditerator234 = new GameplaySegment(this.field9, nameplate21);
      rewinditerator234.method13(this.field8);
      return (GameplaySegment)this.method5(rewinditerator234, nameplate21, range2, range3);
   }

   @Override
   protected long method3(ReplayContext nameplate41) {
      return nameplate41.method6().method41().method8();
   }

   @Override
   protected void method4(ReplayTimeline highlight_31, RewindHandlers rewindhandlers2, long number3, long number5) {
      try {
         if (rewindhandlers2.method40().method10(this.field8)) {
            highlight_31.method6();
            this.method6(rewindhandlers2, number3, -1L);
            return;
         }
      } catch (IOException exception8) {
         throw new RuntimeException(exception8);
      }

      if (number5 < 0L) {
         com.moonsworth.lunar.client.replay.project.RewindFileReader rewind37 = rewindhandlers2.method40().method43().method6().method4(this.field8);
         if ((Boolean)Ref.method4().method90().method20().get() && rewind37 != null && rewind37.method14()) {
            if (-number5 > 300000L) {
               highlight_31.method6();
               this.method6(rewindhandlers2, number3, number5);
            } else {
               rewindhandlers2.method41().method3(number5);
            }
         } else {
            highlight_31.method6();
            this.method6(rewindhandlers2, number3, number5);
         }
      } else if (number5 > 300000L) {
         highlight_31.method6();
         this.method6(rewindhandlers2, number3, number5);
      } else {
         rewindhandlers2.method41().method3(number5);
      }
   }

   private void method6(RewindHandlers rewindhandlers1, long number2, long number4) {
      try {
         long number6 = rewindhandlers1.method3(number2);
         rewindhandlers1.method41().method3(number2 - number6);
      } catch (IOException exception8) {
         throw new RuntimeException(exception8);
      }
   }

   @Override
   public long method5(ReplayProjectManager rewind2_31) {
      com.moonsworth.lunar.client.replay.project.RewindFileReader rewind32 = rewind2_31.method43().method6().method4(this.field8);
      return rewind32 == null ? 0L : rewind32.method13().method1();
   }

   @Override
   public String type() {
      return "gameplay";
   }

   @Override
   public List<String> method15() {
      return List.of("camera", "cameraFov", "speed", "sounds", "packs");
   }

   @Override
   public JsonElement method16(ReplayTimeline highlight_31, Range<Integer> range2, RewindHandlers rewindhandlers3, Runnable runnable4) {
      JsonObject json5 = super.method16(highlight_31, range2, rewindhandlers3, runnable4).getAsJsonObject();
      if (this.field10 != null) {
         json5.addProperty("thumbnail", this.field10);
      }

      boolean flag6 = !rewindhandlers3.method40().method43().method6().method5(this.field8);
      json5.addProperty("missing", flag6);
      com.moonsworth.lunar.client.replay.project.RewindFileReader rewind37 = rewindhandlers3.method40().method43().method6().method4(this.field8);
      if (rewind37 != null) {
         JsonArray array8 = new JsonArray();

         for (long index10 : rewind37.method13().getMarkers()) {
            int index12 = (int)(index10 / highlight_31.method9());
            if (index12 >= this.method8()
               && index12 <= this.method8() + ((Integer)range2.getMaximum() - (Integer)range2.getMinimum())) {
               array8.add(index12 - this.method8());
            }
         }

         json5.add("markers", array8);
      }

      return json5;
   }

   @Generated
   public UUID method10() {
      return this.field8;
   }

   @Generated
   public ThumbnailManager method11() {
      return this.field9;
   }

   @Generated
   public String method12() {
      return this.field10;
   }

   @Generated
   public void method13(UUID uuid1) {
      this.field8 = uuid1;
   }

   @Generated
   public void method14(String text1) {
      this.field10 = text1;
   }
}
