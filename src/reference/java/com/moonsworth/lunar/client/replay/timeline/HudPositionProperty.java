package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.export.ExportSettings;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class HudPositionProperty extends NumberProperty<Float> {
   private final MixinCore9Extension field13;
   private final boolean field14;

   public HudPositionProperty(UndoRedoManager nameplate21, ClientOption<Float> lightingextension2, MixinCore9Extension mixincore9extension3, boolean flag4) {
      super(nameplate21, lightingextension2);
      this.field13 = mixincore9extension3;
      this.field14 = flag4;
      lightingextension2.method8(arg3x -> {
         RewindHandlers rewindhandlers4x = Ref.method4().method40().method85().method35();
         if (rewindhandlers4x != null && rewindhandlers4x.method40() != null) {
            ReplayTimeline highlight_35 = rewindhandlers4x.method40().method37();
            if (highlight_35 != null) {
               ExportSettings rewindhandlersnameplate6 = highlight_35.method13();
               int number7 = rewindhandlersnameplate6.getWidth();
               int number8 = rewindhandlersnameplate6.getHeight();
               GuiResolution threadmoduledump719 = new GuiResolution(Ref.method3(), number7, number8);
               arg3x = arg3x * (flag4 ? threadmoduledump719.getScaledWidth() - mixincore9extension3.method10() : threadmoduledump719.getScaledHeight() - mixincore9extension3.method11());
               arg3x = arg3x / 100.0F;
               mixincore9extension3.method17(flag4 ? arg3x : mixincore9extension3.getX(), flag4 ? mixincore9extension3.getY() : arg3x);
               this.method2(mixincore9extension3, flag4);
            }
         }
      });
   }

   public HudPositionProperty method3(UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3) {
      HudPositionProperty fishing2loader324 = new HudPositionProperty(nameplate21, this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR, this.field13, this.field14);
      return (HudPositionProperty)this.method2(fishing2loader324, nameplate21, range2, range3);
   }

   private void method2(MixinCore9Extension mixincore9extension1, boolean flag2) {
      com.moonsworth.lunar.client.mod.misc.rewind.RewindMod rewind3 = Ref.method4().method40().method85();
      RewindHandlers rewindhandlers4 = rewind3.method35();
      if (rewindhandlers4 != null) {
         ReplayTimeline highlight_35 = rewindhandlers4.method40().method37();
         if (highlight_35 != null) {
            float value6 = mixincore9extension1.getX();
            float value7 = mixincore9extension1.getY();
            int number8 = highlight_35.method13().getWidth();
            int number9 = highlight_35.method13().getHeight();
            GuiResolution threadmoduledump7110 = new GuiResolution(Ref.method3(), number8, number9);
            double value11 = threadmoduledump7110.method1();
            double value13 = threadmoduledump7110.method2();
            HudAnchor gui2extension215 = HudAnchor.getMousePosition(
               new Data2(
                  flag2 ? mixincore9extension1.method20(HudAnchor.TOP_LEFT, value11) : mixincore9extension1.method19(value11),
                  flag2 ? mixincore9extension1.method21(value13) : mixincore9extension1.method22(HudAnchor.TOP_LEFT, value13)
               ),
               new Data2((double)number8 / LcuiScreen.method17(), (double)number9 / LcuiScreen.method17())
            );
            mixincore9extension1.method27(HudAnchor.TOP_LEFT);
            double value16 = mixincore9extension1.method19(value11) * mixincore9extension1.getScale();
            double value18 = mixincore9extension1.method21(value13) * mixincore9extension1.getScale();
            mixincore9extension1.method17(0.0F, 0.0F);
            double value20 = mixincore9extension1.method20(gui2extension215, value11) * mixincore9extension1.getScale();
            double value22 = mixincore9extension1.method22(gui2extension215, value13) * mixincore9extension1.getScale();
            mixincore9extension1.method27(gui2extension215);
            mixincore9extension1.method17(flag2 ? (float)(value16 - value20) : value6, flag2 ? value7 : (float)(value18 - value22));
         }
      }
   }
}
