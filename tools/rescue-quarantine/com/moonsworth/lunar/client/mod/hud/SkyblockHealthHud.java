package com.moonsworth.lunar.client.mod.hud;

import com.moonsworth.lunar.client.framework.Gui2Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.impl.gui.Framework7Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.impl.gui.Framework7Extension2.Data;
import com.moonsworth.lunar.client.lighting.LightingExtension23;
import com.moonsworth.lunar.client.mod.misc.Skyblock;
import com.moonsworth.lunar.client.util.Annotation5;
import java.util.List;
import net.kyori.adventure.text.format.NamedTextColor;

public class SkyblockHealthHud extends Framework7Extension2 {
   public SkyblockHealthHud(Skyblock var1) {
      super(true, var1);
   }

   @Annotation5
   public String getId() {
      return "SKYBLOCK_HEALTH_HUD";
   }

   public void method2(LightingExtension23 var1) {
      super.method2(var1);
      var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(this.HRROHOIOCHHRRHCORRORRRCIRHOOHC);
   }

   protected Data method13() {
      return new Data(0, 0, Gui2Extension2.MIDDLE_CENTER);
   }

   protected List<com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data> method14() {
      return this.method5(this.IHOIIICRHRORRCORICRIHRRRHHRIOC.method6(), this.IHOIIICRHRORRCORICRIHRRRHHRIOC.method7());
   }

   protected List<com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data> method15() {
      return this.method5(4305, 5123);
   }

   private List<com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data> method5(int var1, int var2) {
      return List.of(new com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3.Data(var1, var2, NamedTextColor.RED.value(), '❤'));
   }
}
