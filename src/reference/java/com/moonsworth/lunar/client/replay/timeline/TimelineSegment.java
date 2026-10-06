package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import lombok.Generated;
import org.apache.commons.lang3.Range;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;

@SerializedNameOnly
public abstract class TimelineSegment<T extends TimelineSegment<T>> extends RewindIterator<T> {
   @SerializedName("contentStart")
   private int field7;

   public TimelineSegment(UndoRedoManager nameplate21) {
      super(nameplate21);
   }

   protected T method1(T value1, UndoRedoManager nameplate22, Range<Integer> range3, Range<Integer> range4) {
      value1.method9(this.method8() + (Integer)range4.getMinimum() - (Integer)range3.getMinimum());
      return super.method3((T)value1, nameplate22, range3, range4);
   }

   @Override
   public void method2(ValueHolder<ReplayContext> threadmoduledump61, Range<Integer> range2, int number3) {
      RewindHandlers rewindhandlers4 = ((ReplayContext)threadmoduledump61.get()).method6();
      ReplayTimeline highlight_35 = ((ReplayContext)threadmoduledump61.get()).method4();
      double value6 = highlight_35.method9();
      int number8 = number3 - (Integer)range2.getMinimum();
      double value9 = this.ICRHORIIHOHROHOHOCOOHOOCOORRHO(Range.between(0, number8));
      double value11 = this.field7 + value9 - this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(number8, false);
      long number13 = (long)(value11 * value6) + 100L;
      long number15 = number13 - this.method3((ReplayContext)threadmoduledump61.get());
      if (!rewindhandlers4.method41().method5() && number15 != 0L) {
         this.method4(highlight_35, rewindhandlers4, number13, number15);
      }

      super.method2(threadmoduledump61, range2, number3);
   }

   protected abstract long method3(ReplayContext nameplate41);

   protected abstract void method4(ReplayTimeline highlight_31, RewindHandlers rewindhandlers2, long number3, long number5);

   public abstract long method5(ReplayProjectManager rewind2_31);

   @Override
   public JsonElement method16(ReplayTimeline highlight_31, Range<Integer> range2, RewindHandlers rewindhandlers3, Runnable runnable4) {
      JsonObject json5 = super.method16(highlight_31, range2, rewindhandlers3, runnable4).getAsJsonObject();
      json5.addProperty("trimLeft", this.field7);
      long number6 = this.method5(rewindhandlers3.method40());
      int number8 = (int)(number6 / highlight_31.method9());
      int number9 = number8 - ((Integer)range2.getMaximum() - (Integer)range2.getMinimum()) - this.field7;
      json5.addProperty("trimRight", number9);
      return json5;
   }

   @Generated
   public int method8() {
      return this.field7;
   }

   @Generated
   public void method9(int number1) {
      this.field7 = number1;
   }
}
