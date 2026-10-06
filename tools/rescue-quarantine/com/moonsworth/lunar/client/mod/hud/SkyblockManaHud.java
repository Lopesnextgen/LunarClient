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

public class SkyblockManaHud extends Framework7Extension2 {
   private final LightingExtension443 field17 = (LightingExtension443)((Data2)Lighting.method7("skyblockShowOverflowMana").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   public SkyblockManaHud(Skyblock var1) {
      super(true, var1);
   }

   public String getId() {
      return "SKYBLOCK_MANA_HUD";
   }

   public void method2(LightingExtension23 var1) {
      super.method2(var1);
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field17});
   }

   protected Data method13() {
      return new Data(0, 0, Gui2Extension2.MIDDLE_CENTER);
   }

   protected List<com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data> method14() {
      return this.method5(
         this.IHOIIICRHRORRCORICRIHRRRHHRIOC.method12(), this.IHOIIICRHRORRCORICRIHRRRHHRIOC.method13(), this.IHOIIICRHRORRCORICRIHRRRHHRIOC.method15()
      );
   }

   protected List<com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data> method15() {
      return this.method5(2404, 5435, 600);
   }

   private List<com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data> method5(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data(var1, var2, NamedTextColor.AQUA.value(), '✎'));
      if ((Boolean)this.field17.get()) {
         var4.add(new com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data(var3, -1, NamedTextColor.DARK_AQUA.value(), 'ʬ'));
      }

      return var4;
   }
}
