package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.MediaPool;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import java.io.File;
import java.util.List;
import java.util.UUID;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class ImageSegment extends EffectSegment {
   @SerializedName("source")
   private UUID field7 = null;

   public ImageSegment(UndoRedoManager nameplate21) {
      super(nameplate21);
   }

   @Override
   public EffectSegment method2(UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3) {
      ImageSegment rewindimpl4 = new ImageSegment(nameplate21);
      return (EffectSegment)this.method16(rewindimpl4, nameplate21, range2, range3);
   }

   @Override
   public List<String> method15() {
      return List.of("image");
   }

   @Override
   public String type() {
      return "image";
   }

   @Override
   public JsonElement method16(ReplayTimeline highlight_31, Range<Integer> range2, RewindHandlers rewindhandlers3, Runnable runnable4) {
      JsonObject json5 = super.method16(highlight_31, range2, rewindhandlers3, runnable4).getAsJsonObject();
      File file6 = rewindhandlers3.method40().method43().method5().method6(this.field7);
      if (file6 != null) {
         json5.addProperty("thumbnail", MediaPool.method15(file6));
      }

      return json5;
   }

   @Generated
   public UUID method4() {
      return this.field7;
   }

   @Generated
   public void method5(UUID uuid1) {
      this.field7 = uuid1;
   }
}
