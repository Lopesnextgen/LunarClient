package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import java.io.File;
import lombok.Generated;

@SerializedNameOnly
public class TimelineRenderJob extends ReplayTimeline {
   @SerializedName("savePath")
   private String field10;
   private File field11;

   public TimelineRenderJob(UndoRedoManager nameplate21) {
      super(nameplate21);
   }

   @Generated
   public void method1(String text) {
      this.field10 = text;
   }

   @Generated
   public void method2(File file1) {
      this.field11 = file1;
   }

   @Generated
   public String method4() {
      return this.field10;
   }

   @Generated
   public File method5() {
      return this.field11;
   }
}
