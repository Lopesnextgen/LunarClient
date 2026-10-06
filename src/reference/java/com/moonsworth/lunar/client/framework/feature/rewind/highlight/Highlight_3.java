package com.moonsworth.lunar.client.framework.feature.rewind.highlight;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator22;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator23;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui2_2;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiImpl2;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiImpl3;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.TreeMapImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.holograms.Holograms;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2Impl2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin.Rewindhandlers2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump73Type;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@Annotation7
public class Highlight_3 {
   @SerializedName("id")
   private UUID id;
   @SerializedName("name")
   private String name = "Untitled";
   @SerializedName("tracks")
   private Gui2_2 field1;
   @SerializedName("renderSettings")
   private RewindhandlersNameplate field2 = new RewindhandlersNameplate();
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

   public Highlight_3(Nameplate2 var1) {
      this.field1 = new Gui2_2(var1);
   }

   public HashMapImpl method1() {
      return this.field1.method5();
   }

   public void method2(Holograms var1, Rewind3 var2, Highlight3 var3, Rewindhandlers2 var4, Nameplate2 var5, File var6, int var7, UUID var8) {
      var5.method1();
      long var9 = var2.method13().method1();
      Range var11 = Range.between(var7, var7 + (int)(var9 / this.method9()));
      GuiImpl3 var12 = null;

      for (Gui_2 var14 : this.field1.method3()) {
         if (var14.getId().equals(var8)) {
            var12 = (GuiImpl3)var14;
            break;
         }
      }

      if (var12 == null) {
         var12 = new GuiImpl3(var5, this.method1());
         this.field1.method3().add(var12);
      }

      RewindIterator23 var22 = new RewindIterator23(var3, var5);
      var22.method13(var2.method13().getId());
      var22.method1(var11, var1, var5);
      var12.method5().method1(var11, var22);
      Coordinates.setSelectedLayer(var22);
      Coordinates.setLinkSelectionEnabled(true);
      HashSet var23 = new HashSet();
      var23.add(var22);
      List var15 = List.of("system", "mic");
      List var16 = this.field1.method4().stream().toList();
      int var17 = 0;

      for (String var19 : var15) {
         if (var2.has("audio/" + var19 + ".dat")) {
            if (var19.equals("system")) {
               Fishing2Iterator var20 = var22.method18().get("sounds");
               if (var20 != null) {
                  Fishing2Loader.Data var21 = new Fishing2Loader.Data(ThreadModuleDump73Type.LINEAR);
                  var21.setValue(false);
                  var20.method12().get("replayGameSounds").method27().put(Integer.MIN_VALUE, var21);
               }
            }

            GuiImpl2 var24;
            if (var17 >= var16.size()) {
               var24 = new GuiImpl2(var5, this.method1());
               this.field1.method4().add(var24);
            } else {
               var24 = (GuiImpl2)var16.get(var17);
            }

            RewindIterator22 var25 = new RewindIterator22(var5);
            var25.method9(new Rewindhandlers2Impl2(var6, var2.method13().getId() + "://" + var19, var4, var2));
            var25.method5(var11, var1, var5);
            var24.method5().method1(var11, var25);
            var23.add(var25);
            var17++;
         }
      }

      if (this.field1.method4().isEmpty()) {
         this.field1.method4().add(new GuiImpl2(var5, this.method1()));
      }

      this.field1.method6().method9(var5, var23);
      var5.endBatch();
   }

   public int method3() {
      int var1 = 0;

      for (Gui_2 var3 : this.field1) {
         TreeMapImpl var4 = var3.method5().method11();
         if (!var4.isEmpty()) {
            var1 = Math.max(var1, (Integer)((Range)var4.lastKey()).getMaximum());
         }
      }

      return var1;
   }

   public long getDurationMs() {
      return (long)(this.method3() * this.method9());
   }

   public void method4(ThreadModuleDump6<Nameplate4> var1, int var2) {
      for (Gui_2 var4 : this.field1.method4()) {
         var4.method2(var1, var2);
      }

      boolean var6 = false;

      for (Gui_2 var5 : Lists.reverse(this.field1.method3().stream().toList())) {
         if (!(var5 instanceof GuiImpl3) || !var6) {
            var6 |= var5.method2(var1, var2) && var5 instanceof GuiImpl3;
         }
      }

      for (Gui_2 var9 : this.field1.method2()) {
         var9.method2(var1, var2);
      }
   }

   public void method5(Nameplate2 var1, int var2) {
      var1.method1();

      for (Gui_2 var4 : this.field1) {
         var4.method5().method5(var2);
      }

      var1.endBatch();
   }

   public void method6() {
      this.field6 = ThreadModuleDump63.method3().bridge$getRealSystemTime();
      this.field7 = 0L;
      this.field8 = 0.0;
   }

   public void method7(Rewindhandlers var1, boolean var2) {
      long var3 = ThreadModuleDump63.method3().bridge$getRealSystemTime();
      if (this.field6 <= 0L) {
         this.field6 = var3;
      } else {
         long var5 = var3 - this.field6;
         if (var5 > 0L) {
            this.field6 = var3;
            if (!Coordinates.isDragging() && (var2 || !this.paused) && !var1.isSkipping() && var1.isPlayable() && this.field3 < this.method3() && !this.field9) {
               this.field7 += var5;
               double var7 = this.method9();

               while (this.field7 >= (int)var7) {
                  this.field3++;
                  this.field7 -= (int)var7;

                  for (this.field8 += var7 - (int)var7; this.field8 >= 1.0; this.field7--) {
                     this.field8--;
                  }
               }
            }
         }
      }
   }

   public void method8(int var1) {
      if (!this.field9) {
         this.field3 = var1;
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
   public void setId(UUID var1) {
      this.id = var1;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public void setName(String var1) {
      this.name = var1;
   }

   @Generated
   public Gui2_2 method11() {
      return this.field1;
   }

   @Generated
   public void method12(Gui2_2 var1) {
      this.field1 = var1;
   }

   @Generated
   public RewindhandlersNameplate method13() {
      return this.field2;
   }

   @Generated
   public void method14(RewindhandlersNameplate var1) {
      this.field2 = var1;
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
   public void setPaused(boolean var1) {
      this.paused = var1;
   }

   @Generated
   public boolean method18() {
      return this.field4;
   }

   @Generated
   public void method19(boolean var1) {
      this.field4 = var1;
   }

   @Generated
   public boolean method20() {
      return this.field5;
   }

   @Generated
   public void method21(boolean var1) {
      this.field5 = var1;
   }

   @Generated
   public boolean method22() {
      return this.field9;
   }

   @Generated
   public void method23(boolean var1) {
      this.field9 = var1;
   }
}
