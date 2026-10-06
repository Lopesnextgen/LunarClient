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
import java.util.List;
import net.kyori.adventure.text.format.NamedTextColor;

public class SkyblockKuudraArmorStacksHud extends Framework7Extension2 {
   private final LightingExtension443 field17 = (LightingExtension443)((Data2)Lighting.method7("skyblockOnlyShowWhenStacks")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   public SkyblockKuudraArmorStacksHud(Skyblock var1) {
      super(true, var1);
   }

   public String getId() {
      return "SKYBLOCK_KUUDRA_ARMOR_STACKS_HUD";
   }

   public void method2(LightingExtension23 var1) {
      super.method2(var1);
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field17});
      var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(this.HRROHOIOCHHRRHCORRORRRCIRHOOHC);
   }

   protected Data method13() {
      return new Data(0, 0, Gui2Extension2.MIDDLE_CENTER);
   }

   protected List<com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data> method14() {
      return this.field17.get() && this.IHOIIICRHRORRCORICRIHRRRHHRIOC.method10() == 0
         ? List.of()
         : this.method5(this.IHOIIICRHRORRCORICRIHRRRHHRIOC.method10(), this.IHOIIICRHRORRCORICRIHRRRHHRIOC.method11());
   }

   protected List<com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data> method15() {
      return this.method5(3, 'ѫ');
   }

   private List<com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data> method5(int var1, char var2) {
      return List.of(new com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data(var1, 10, NamedTextColor.GOLD.value(), var2));
   }
}
