package com.moonsworth.lunar.client.framework.feature.rewind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight2_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.holograms.Holograms;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@Annotation7
public class RewindIterator23 extends RewindIterator2_2<RewindIterator23> {
   @SerializedName("rewindId")
   private UUID field8 = null;
   private Highlight3 field9;
   private String field10;

   public RewindIterator23(Highlight3 var1, Nameplate2 var2) {
      super(var2);
      this.method1(null, var1);
   }

   public void method1(Range<Integer> var1, Highlight3 var2) {
      this.field9 = var2;
      if (var2 != null) {
         try {
            this.field10 = var2.method3(this.field8, this.method8());
            if (this.field10 == null && var1 != null) {
               var2.method5(new Highlight2_2(this, var1, this.field8, this.method8()));
            }
         } catch (IOException var4) {
            var4.printStackTrace();
         }
      }
   }

   @Override
   public void method1(Range<Integer> var1, Holograms var2, Nameplate2 var3) {
      super.method5(var1, var2, var3);
      if (this.field9 != null && this.field10 == null) {
         this.field9.method5(new Highlight2_2(this, var1, this.field8, this.method8()));
      }
   }

   public RewindIterator23 method3(Nameplate2 var1, Range<Integer> var2, Range<Integer> var3) {
      RewindIterator23 var4 = new RewindIterator23(this.field9, var1);
      var4.method13(this.field8);
      return (RewindIterator23)this.method5(var4, var1, var2, var3);
   }

   @Override
   protected long method3(Nameplate4 var1) {
      return var1.method6().method41().method8();
   }

   @Override
   protected void method4(Highlight_3 var1, RewindHandlers var2, long var3, long var5) {
      try {
         if (var2.method40().method10(this.field8)) {
            var1.method6();
            this.method6(var2, var3, -1L);
            return;
         }
      } catch (IOException var8) {
         throw new RuntimeException(var8);
      }

      if (var5 < 0L) {
         com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3 var7 = var2.method40().method43().method6().method4(this.field8);
         if ((Boolean)ThreadModuleDump63.method4().method90().method20().get() && var7 != null && var7.method14()) {
            if (-var5 > 300000L) {
               var1.method6();
               this.method6(var2, var3, var5);
            } else {
               var2.method41().method3(var5);
            }
         } else {
            var1.method6();
            this.method6(var2, var3, var5);
         }
      } else if (var5 > 300000L) {
         var1.method6();
         this.method6(var2, var3, var5);
      } else {
         var2.method41().method3(var5);
      }
   }

   private void method6(RewindHandlers var1, long var2, long var4) {
      try {
         long var6 = var1.method3(var2);
         var1.method41().method3(var2 - var6);
      } catch (IOException var8) {
         throw new RuntimeException(var8);
      }
   }

   @Override
   public long method5(Rewind2_3 var1) {
      com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3 var2 = var1.method43().method6().method4(this.field8);
      return var2 == null ? 0L : var2.method13().method1();
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
   public JsonElement method16(Highlight_3 var1, Range<Integer> var2, RewindHandlers var3, Runnable var4) {
      JsonObject var5 = super.method16(var1, var2, var3, var4).getAsJsonObject();
      if (this.field10 != null) {
         var5.addProperty("thumbnail", this.field10);
      }

      boolean var6 = !var3.method40().method43().method6().method5(this.field8);
      var5.addProperty("missing", var6);
      com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3 var7 = var3.method40().method43().method6().method4(this.field8);
      if (var7 != null) {
         JsonArray var8 = new JsonArray();

         for (long var10 : var7.method13().getMarkers()) {
            int var12 = (int)(var10 / var1.method9());
            if (var12 >= this.method8()
               && var12 <= this.method8() + ((Integer)var2.getMaximum() - (Integer)var2.getMinimum())) {
               var8.add(var12 - this.method8());
            }
         }

         var5.add("markers", var8);
      }

      return var5;
   }

   @Generated
   public UUID method10() {
      return this.field8;
   }

   @Generated
   public Highlight3 method11() {
      return this.field9;
   }

   @Generated
   public String method12() {
      return this.field10;
   }

   @Generated
   public void method13(UUID var1) {
      this.field8 = var1;
   }

   @Generated
   public void method14(String var1) {
      this.field10 = var1;
   }
}
