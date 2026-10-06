package com.moonsworth.lunar.client.mod.skyblock.vitalityhud;

import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3;
import com.moonsworth.lunar.client.framework.feature.mod.impl.gui.Framework7Extension2;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.format.NamedTextColor;

public class SkyblockVitalityHud extends Framework7Extension2 {
   private final ToggleOption alwaysShow = (ToggleOption)OptionFactory.method7("alwaysShow").method31();

   public SkyblockVitalityHud(Skyblock var1) {
      super(true, var1);
   }

   @Override
   public String getId() {
      return "SKYBLOCK_VITALITY_HUD";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      super.method2(var1);
      var1.method11(new ClientOption[]{this.alwaysShow});
      var1.method6(this.alwaysShow);
   }

   @Override
   protected Framework7Extension2.Data method13() {
      return new Framework7Extension2.Data(0, 100, HudAnchor.MIDDLE_CENTER);
   }

   @Override
   protected List<Nameplate3.Data> method14() {
      return !this.alwaysShow.get() && !this.field8.method16()
         ? List.of()
         : this.createVitalityLine(this.field8.method17(), this.field8.method18());
   }

   @Override
   protected List<Nameplate3.Data> method15() {
      return this.createVitalityLine(24, 139);
   }

   private List<Nameplate3.Data> createVitalityLine(int var1, int var2) {
      ArrayList var3 = new ArrayList();
      var3.add(new Nameplate3.Data(var1, var2, NamedTextColor.DARK_RED.value(), '♨'));
      return var3;
   }
}
