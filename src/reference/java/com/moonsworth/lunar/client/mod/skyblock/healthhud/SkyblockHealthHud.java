package com.moonsworth.lunar.client.mod.skyblock.healthhud;

import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3;
import com.moonsworth.lunar.client.framework.feature.mod.impl.gui.Framework7Extension2;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.Annotation5;
import java.util.List;
import net.kyori.adventure.text.format.NamedTextColor;

public class SkyblockHealthHud extends Framework7Extension2 {
   public SkyblockHealthHud(Skyblock var1) {
      super(true, var1);
   }

   @Annotation5
   @Override
   public String getId() {
      return "SKYBLOCK_HEALTH_HUD";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      super.method2(var1);
      var1.method6(this.field15);
   }

   @Override
   protected Framework7Extension2.Data method13() {
      return new Framework7Extension2.Data(0, 0, HudAnchor.MIDDLE_CENTER);
   }

   @Override
   protected List<Nameplate3.Data> method14() {
      return this.method5(this.field8.method6(), this.field8.method7());
   }

   @Override
   protected List<Nameplate3.Data> method15() {
      return this.method5(4305, 5123);
   }

   private List<Nameplate3.Data> method5(int var1, int var2) {
      return List.of(new Nameplate3.Data(var1, var2, NamedTextColor.RED.value(), '❤'));
   }
}
