package com.moonsworth.lunar.client.framework.feature.rewind;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate_3;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.network.MixinHelper;
import com.moonsworth.lunar.network.MixinHelper16;
import com.moonsworth.lunar.network.GameRewindLayerGameplay;
import com.moonsworth.lunar.network.GameRewindLayerAudio;
import com.moonsworth.lunar.network.GameRewindLayerEffect;
import com.moonsworth.lunar.network.MixinHelper22;
import com.moonsworth.lunar.network.MixinHelper23;
import com.moonsworth.lunar.network.GameRewindEditorSessionEventData;
import com.moonsworth.lunar.network.GameRewindExportAudio;
import com.moonsworth.lunar.network.MixinHelper63;
import com.moonsworth.lunar.network.MixinHelper8;
import com.moonsworth.lunar.network.GameRewindLayerAddEvent;
import com.moonsworth.lunar.network.GameRewindProjectExportEvent;
import com.moonsworth.lunar.network.GameRewindEditorSessionEvent;
import com.moonsworth.lunar.network.GameRewindEditorSessionEventData.Type;
import java.io.File;
import java.math.BigDecimal;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

public class Rewind_5 {
   private final Rewind2_3 field1;
   private final RewindHandlers field2;

   public void method1() {
      GameRewindEditorSessionEventData var1 = new GameRewindEditorSessionEventData()
         .method1(this.field1.method31() ? Type.QUICK_VIEW : Type.PROJECT)
         .method11((Boolean)ThreadModuleDump63.method4().method90().method20().get())
         .method4(BigDecimal.valueOf(System.currentTimeMillis() - this.field2.method65()));
      if (!this.field1.method31()) {
         this.method2(var1);
      }

      Client.method109().method105().method2(new GameRewindEditorSessionEvent().method4(var1));
   }

   private void method2(GameRewindEditorSessionEventData var1) {
      for (Highlight_3 var3 : this.field1.method36()) {
         MixinHelper8 var4 = new MixinHelper8().method1(BigDecimal.valueOf(var3.getDurationMs()));

         for (Gui_2 var6 : var3.method11()) {
            for (Entry var8 : var6.method5().method11().entrySet()) {
               RewindIterator var9 = (RewindIterator)var8.getValue();
               Range var10 = (Range)var8.getKey();
               double var11 = ((Integer)var10.getMaximum() - (Integer)var10.getMinimum()) * var3.method9();
               MixinHelper63 var13 = this.method4(var9, var11);
               MixinHelper16 var14 = (MixinHelper16)var13.getInstance();

               for (Fishing2Iterator var16 : var9.method18().values()) {
                  if (!this.method3(var9, var16)) {
                     var14.addPropertiesItem(var16.type());
                  }
               }

               var4.addLayersItem(var13);
            }
         }

         var1.addTimelinesItem(var4);
      }
   }

   private boolean method3(RewindIterator<?> var1, Fishing2Iterator var2) {
      if (!var1.getAssetserverSessionId().contains(var2.type())) {
         return false;
      }

      boolean var3 = true;

      for (Fishing2Loader var5 : var2.method12().values()) {
         if (var5.method4()) {
            var3 = false;
            break;
         }
      }

      return var3;
   }

   private MixinHelper63 method4(RewindIterator<?> var1, double var2) {
      Object var4;
      if (var1 instanceof Rewind_2) {
         var4 = new GameRewindLayerEffect().method4(var1.type());
      } else if (var1 instanceof RewindIterator23) {
         var4 = new GameRewindLayerGameplay();
      } else {
         if (!(var1 instanceof RewindIterator22 var5)) {
            throw new IllegalArgumentException("Unsupported layer type: " + var1.getClass().getName());
         }

         GameRewindLayerAudio var6 = new GameRewindLayerAudio();
         Rewindhandlers2 var7 = var5.method9();
         if (var7 != null && this.field2.method40().method44().method5().contains(var7.getName())) {
            var6.name(var7.getName().split("@")[0]);
         }

         var4 = var6;
      }

      var4.method3(BigDecimal.valueOf(var2));
      MixinHelper63 var8 = new MixinHelper63();
      var8.setInstance(var4);
      return var8;
   }

   public void method5(File var1, Highlight_3 var2, RewindhandlersNameplate var3, RewindhandlersNameplate_3 var4) {
      MixinHelper var5 = new MixinHelper().method1(BigDecimal.valueOf(var3.getWidth())).method4(BigDecimal.valueOf(var3.getHeight()));
      com.moonsworth.lunar.network.GameRewindExportAudio.Type var6 = var3.method9()
         ? com.moonsworth.lunar.network.GameRewindExportAudio.Type.STEREO
         : com.moonsworth.lunar.network.GameRewindExportAudio.Type.MONO;
      GameRewindExportAudio var7 = new GameRewindExportAudio().method1(var6).method4(BigDecimal.valueOf(var3.getFrequency()));
      String var8 = var3.method7() == Gui2Extension2.CUSTOM ? String.valueOf(var3.method8()) : var3.method7().id();
      Gui2Extension3 var9 = var3.method4();
      if (var9 == null) {
         var9 = var4.method8().method5(var3.method3());
      }

      Client.method109()
         .method105()
         .method2(
            new GameRewindProjectExportEvent()
               .method4(
                  new MixinHelper22()
                     .method8(var1.getAbsolutePath())
                     .method1(BigDecimal.valueOf(var2.getDurationMs()))
                     .method9(var3.method2().id())
                     .method10(var3.method3().id())
                     .method14(var9.id())
                     .method14(var5)
                     .method17(BigDecimal.valueOf(var3.method5()))
                     .method23(var3.method6() && var3.method2().isSupportsAudio() ? var7 : null)
                     .method20(var8)
               )
         );
   }

   public void method6(Range<Integer> var1, RewindIterator<?> var2, double var3) {
      double var5 = ((Integer)var1.getMaximum() - (Integer)var1.getMinimum()) * var3;
      MixinHelper63 var7 = this.method4(var2, var5);
      Client.method109().method105().method2(new GameRewindLayerAddEvent().method4(new MixinHelper23().method1(var7)));
   }

   @Generated
   public Rewind_5(Rewind2_3 var1, RewindHandlers var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
