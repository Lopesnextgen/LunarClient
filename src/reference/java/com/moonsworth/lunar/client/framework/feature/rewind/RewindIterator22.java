package com.moonsworth.lunar.client.framework.feature.rewind;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers2;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@Annotation7
public class RewindIterator22 extends RewindIterator2_2<RewindIterator22> {
   @SerializedName("source")
   private Rewindhandlers2<?> field8 = null;

   public RewindIterator22(Nameplate2 var1) {
      super(var1);
   }

   public RewindIterator22 method2(Nameplate2 var1, Range<Integer> var2, Range<Integer> var3) {
      RewindIterator22 var4 = new RewindIterator22(var1);

      try {
         var4.method9(this.field8.copy());
      } catch (IOException var6) {
         throw new RuntimeException(var6);
      }

      return (RewindIterator22)this.method5(var4, var1, var2, var3);
   }

   @Override
   public void method2(ThreadModuleDump6<Nameplate4> var1, Range<Integer> var2, int var3) {
      Nameplate4 var4 = (Nameplate4)var1.get();
      Highlight_3 var5 = var4.method4();
      RewindHandlers var6 = var4.method6();
      if (!var6.method57().method25() || var5.method13().method6()) {
         RewindHandlers2 var7 = var6.method60();
         if (!var7.method2(this.field8)) {
            try {
               var7.method1(this.field8);
            } catch (IOException var15) {
               throw new RuntimeException(var15);
            }
         }

         Fishing2Iterator var8 = this.method18().get("audio");
         float var9 = (Float)var8.method12().get("volume").getOption().get();
         this.field8.setVolume(var9 / 100.0F);
         double var10 = (Double)var8.method12().get("speed").getOption().get();
         this.field8.setPlaybackRate(var10);
         Fishing2Iterator var12 = this.method18().get("decode");
         if (var12 != null) {
            this.field8.setKey(((String)var12.method12().get("key").getOption().get()).toLowerCase());
         } else {
            this.field8.setKey(null);
         }

         try {
            this.field8.updatePlayback(var6, var4.method4(), var6.method57().method25());
         } catch (IOException var14) {
            throw new RuntimeException(var14);
         }

         super.method2(var1, var2, var3);
      }
   }

   @Override
   protected long method3(Nameplate4 var1) {
      return this.field8.getTime();
   }

   @Override
   protected void method4(Highlight_3 var1, RewindHandlers var2, long var3, long var5) {
      try {
         if (!(Math.abs(var5 / this.field8.getPlaybackRate()) <= Math.ceil(var1.method9()))) {
            if (var5 < 0L) {
               this.field8.stop();
               this.field8.play();
               this.field8.skip(var3);
            } else {
               this.field8.skip(var5);
            }
         }
      } catch (IOException var8) {
         throw new RuntimeException(var8);
      }
   }

   @Override
   public long method5(Rewind2_3 var1) {
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
   public JsonElement method16(Highlight_3 var1, Range<Integer> var2, RewindHandlers var3, Runnable var4) {
      JsonObject var5 = super.method16(var1, var2, var3, var4).getAsJsonObject();
      float var6 = Coordinates.getZoom();
      if (var6 > 100.0F) {
         var6 = (int)(var6 / 100.0F) * 100;
      } else if (var6 > 10.0F) {
         var6 = (int)(var6 / 10.0F) * 10;
      }

      Fishing2Iterator var7 = this.method18().get("audio");
      Fishing2Loader var8 = var7.method12().get("volume");
      long var9 = 0L;

      for (Entry var12 : var8.method27().entrySet()) {
         var9 += ((Integer)var12.getKey()).hashCode() + Math.round((Float)((Fishing2Loader.Data)var12.getValue()).getValue() * 10.0F);
      }

      String var13 = var3.method40().method42().method3(var1, var2, this, var6, LcuiScreen.method17(), var9, var4);
      var5.addProperty("waveform", var13);
      var5.addProperty("missing", !this.field8.isValid());
      if (var3.method40().method44().method5().contains(this.field8.getName())) {
         var5.addProperty("name", this.field8.getName().split("@")[0]);
      } else {
         var5.addProperty("name", this.field8.getName());
      }

      return var5;
   }

   @Generated
   public Rewindhandlers2<?> method9() {
      return this.field8;
   }

   @Generated
   public void method9(Rewindhandlers2<?> var1) {
      this.field8 = var1;
   }
}
