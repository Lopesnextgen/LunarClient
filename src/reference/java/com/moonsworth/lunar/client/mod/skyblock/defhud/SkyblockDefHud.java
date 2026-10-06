package com.moonsworth.lunar.client.mod.skyblock.defhud;

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

public class SkyblockDefHud extends Framework7Extension2 {
   private final ToggleOption field17 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showEHP")
         .method4(true))
      .method31();

   public SkyblockDefHud(Skyblock var1) {
      super(true, var1);
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      super.method2(var1);
      var1.method11(new ClientOption[]{this.field17});
      var1.method6(this.field17);
   }

   @Override
   protected Framework7Extension2.Data method13() {
      return new Framework7Extension2.Data(0, 0, HudAnchor.MIDDLE_CENTER);
   }

   @Override
   protected List<Nameplate3.Data> method14() {
      return this.method5(this.field8.method8(), this.field8.method9());
   }

   @Override
   protected List<Nameplate3.Data> method15() {
      return this.method5(850, 135458);
   }

   private List<Nameplate3.Data> method5(int var1, int var2) {
      ArrayList var3 = new ArrayList();
      var3.add(new Nameplate3.Data(var1, -1, NamedTextColor.GREEN.value(), '❈'));
      if (this.field17.get()) {
         var3.add(new Nameplate3.Data(var2, -1, NamedTextColor.DARK_GREEN.value(), '❣'));
      }

      return var3;
   }

   @Override
   public String getId() {
      return "SKYBLOCK_DEF_HUD";
   }
}
