package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import it.unimi.dsi.fastutil.ints.IntArrayList;

@VersionGate(min = 8)
public class TurboGroupRebuilds extends AbstractFeature {
   private final IntArrayList rebuildSamples = new IntArrayList();
   private final ToggleOption rebuildsPerFrame = (ToggleOption)OptionFactory.method7("rebuildsPerFrame").method31();
   private final IntegerOption avgInSeconds = (IntegerOption)((Data)((Data)OptionFactory.method4("avgInSeconds").method4(1))
         .method7(1, 3600))
      .method31();
   private final IntegerOption sampleAmt = (IntegerOption)((Data)((Data)OptionFactory.method4("sampleAmt").method4(1))
         .method7(1, 120))
      .method31();
   private String displayValue = "0";

   public TurboGroupRebuilds(Framework7Extension framework7extension1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(framework7extension1));
      this.method2(ModTraits.field1, TypedHudRenderer.method22(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, new HudSize(10, 18, 22, 80, 120, 150), this::method2));
      this.handle(EventSecond.class, arg1x -> {
         boolean flag2 = (Boolean)this.rebuildsPerFrame.get();
         if (EventTick.field1 % (flag2 ? 20 : 20 * (Integer)this.avgInSeconds.get()) == 0) {
            TurboEngineManager fogiterator_33 = Ref.method4().method89();
            if (flag2) {
               int number4 = fogiterator_33.method25();
               int number5 = Ref.method3().bridge$getDebugFPS();
               number5 = number5 == 0 ? 1 : number5;
               this.displayValue = String.format("%%%d", Math.round((float)number4 / number5 * 100.0F));
            } else {
               if (this.rebuildSamples.size() >= (Integer)this.sampleAmt.get()) {
                  this.rebuildSamples.popInt();
               }

               this.rebuildSamples.add(fogiterator_33.method25());
               this.displayValue = String.format("%.2f", (float)this.rebuildSamples.intParallelStream().average().orElse(0.0));
            }

            fogiterator_33.method24(0);
         }
      });
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method7(
         SettingsPage.SETTINGS,
         arg1x -> arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.rebuildsPerFrame, arg1xx -> arg1xx.method9(new ClientOption[]{this.avgInSeconds, this.sampleAmt})
         )
      );
      this.avgInSeconds.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.rebuildSamples.clear());
      this.sampleAmt.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.rebuildSamples.clear());
      this.rebuildsPerFrame.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.rebuildSamples.clear());
   }

   public String method2(boolean flag1) {
      return "Group Rebuilds: " + this.displayValue;
   }

   public String getId() {
      return "TURBO_GROUP_REBUILDS_CHILD_HUD_MOD";
   }
}
