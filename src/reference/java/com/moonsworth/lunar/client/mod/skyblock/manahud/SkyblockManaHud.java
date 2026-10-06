package com.moonsworth.lunar.client.mod.skyblock.manahud;

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

public class SkyblockManaHud extends Framework7Extension2 {
   private final ToggleOption field17 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("skyblockShowOverflowMana")
         .method4(true))
      .method31();

   public SkyblockManaHud(Skyblock var1) {
      super(true, var1);
   }

   @Override
   public String getId() {
      return "SKYBLOCK_MANA_HUD";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      super.method2(var1);
      var1.method11(new ClientOption[]{this.field17});
   }

   @Override
   protected Framework7Extension2.Data method13() {
      return new Framework7Extension2.Data(0, 0, HudAnchor.MIDDLE_CENTER);
   }

   @Override
   protected List<Nameplate3.Data> method14() {
      return this.method5(
         this.field8.method12(), this.field8.method13(), this.field8.method15()
      );
   }

   @Override
   protected List<Nameplate3.Data> method15() {
      return this.method5(2404, 5435, 600);
   }

   private List<Nameplate3.Data> method5(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new Nameplate3.Data(var1, var2, NamedTextColor.AQUA.value(), '✎'));
      if (this.field17.get()) {
         var4.add(new Nameplate3.Data(var3, -1, NamedTextColor.DARK_AQUA.value(), 'ʬ'));
      }

      return var4;
   }
}
