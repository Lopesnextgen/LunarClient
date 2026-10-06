package com.moonsworth.lunar.client.framework.feature.rewind;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import org.apache.commons.lang3.Range;

@Annotation7
public class Fishing2Loader32 extends Fishing2Loader3<Float> {
   private final MixinCore9Extension field13;
   private final boolean field14;

   public Fishing2Loader32(Nameplate2 var1, ClientOption<Float> var2, MixinCore9Extension var3, boolean var4) {
      super(var1, var2);
      this.field13 = var3;
      this.field14 = var4;
      var2.method8(var3x -> {
         RewindHandlers var4x = ThreadModuleDump63.method4().method40().method85().method35();
         if (var4x != null && var4x.method40() != null) {
            Highlight_3 var5 = var4x.method40().method37();
            if (var5 != null) {
               RewindhandlersNameplate var6 = var5.method13();
               int var7 = var6.getWidth();
               int var8 = var6.getHeight();
               ThreadModuleDump71 var9 = new ThreadModuleDump71(ThreadModuleDump63.method3(), var7, var8);
               var3x = var3x * (var4 ? var9.getScaledWidth() - var3.method10() : var9.getScaledHeight() - var3.method11());
               var3x = var3x / 100.0F;
               var3.method17(var4 ? var3x : var3.getX(), var4 ? var3.getY() : var3x);
               this.method2(var3, var4);
            }
         }
      });
   }

   public Fishing2Loader32 method3(Nameplate2 var1, Range<Integer> var2, Range<Integer> var3) {
      Fishing2Loader32 var4 = new Fishing2Loader32(var1, this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR, this.field13, this.field14);
      return (Fishing2Loader32)this.method2(var4, var1, var2, var3);
   }

   private void method2(MixinCore9Extension var1, boolean var2) {
      com.moonsworth.lunar.client.mod.misc.rewind.Rewind var3 = ThreadModuleDump63.method4().method40().method85();
      RewindHandlers var4 = var3.method35();
      if (var4 != null) {
         Highlight_3 var5 = var4.method40().method37();
         if (var5 != null) {
            float var6 = var1.getX();
            float var7 = var1.getY();
            int var8 = var5.method13().getWidth();
            int var9 = var5.method13().getHeight();
            ThreadModuleDump71 var10 = new ThreadModuleDump71(ThreadModuleDump63.method3(), var8, var9);
            double var11 = var10.getScaledWidth_double();
            double var13 = var10.getScaledHeight_double();
            HudAnchor var15 = HudAnchor.getMousePosition(
               new Data2(
                  var2 ? var1.method20(HudAnchor.TOP_LEFT, var11) : var1.method19(var11),
                  var2 ? var1.method21(var13) : var1.method22(HudAnchor.TOP_LEFT, var13)
               ),
               new Data2((double)var8 / LcuiScreen.method17(), (double)var9 / LcuiScreen.method17())
            );
            var1.method27(HudAnchor.TOP_LEFT);
            double var16 = var1.method19(var11) * var1.getScale();
            double var18 = var1.method21(var13) * var1.getScale();
            var1.method17(0.0F, 0.0F);
            double var20 = var1.method20(var15, var11) * var1.getScale();
            double var22 = var1.method22(var15, var13) * var1.getScale();
            var1.method27(var15);
            var1.method17(var2 ? (float)(var16 - var20) : var6, var2 ? var7 : (float)(var18 - var22));
         }
      }
   }
}
