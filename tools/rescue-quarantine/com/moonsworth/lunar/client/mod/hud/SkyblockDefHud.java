package com.moonsworth.lunar.client.mod.hud;

import com.moonsworth.lunar.client.framework.Gui2Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.impl.gui.Framework7Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.impl.gui.Framework7Extension2.Data;
import com.moonsworth.lunar.client.lighting.Lighting;
import com.moonsworth.lunar.client.lighting.LightingExtension;
import com.moonsworth.lunar.client.lighting.LightingExtension23;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.lighting.LightingExtension443.Data2;
import com.moonsworth.lunar.client.mod.misc.Skyblock;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.format.NamedTextColor;

public class SkyblockDefHud extends Framework7Extension2 {
   private final LightingExtension443 field17 = (LightingExtension443)((Data2)Lighting.method7("showEHP").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   public SkyblockDefHud(Skyblock var1) {
      super(true, var1);
   }

   public void method2(LightingExtension23 var1) {
      super.method2(var1);
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field17});
      var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(this.OCHCIIOCCOICIIOIOIRCHOIIHCORCH);
   }

   protected Data method13() {
      return new Data(0, 0, Gui2Extension2.MIDDLE_CENTER);
   }

   protected List<com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data> method14() {
      return this.method5(this.IHOIIICRHRORRCORICRIHRRRHHRIOC.method8(), this.IHOIIICRHRORRCORICRIHRRRHHRIOC.method9());
   }

   protected List<com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data> method15() {
      return this.method5(850, 135458);
   }

   private List<com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data> method5(int var1, int var2) {
      ArrayList var3 = new ArrayList();
      var3.add(new com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data(var1, -1, NamedTextColor.GREEN.value(), '❈'));
      if ((Boolean)this.field17.get()) {
         var3.add(new com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data(var2, -1, NamedTextColor.DARK_GREEN.value(), '❣'));
      }

      return var3;
   }

   public String getId() {
      return "SKYBLOCK_DEF_HUD";
   }
}
