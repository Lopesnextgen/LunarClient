package com.moonsworth.lunar.client.mod.combat.pvpinfo;

import com.moonsworth.lunar.client.network.server.ServerAddressBook;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.pvpinfo.pvp.Framework7Extension2;
import com.moonsworth.lunar.client.ui.hud.row.Gui2Extension;
import com.moonsworth.lunar.client.ui.hud.row.Hitbox2;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;

public class PvpInfoMeleeStats extends Framework7Extension2 {
   private final ToggleOption field26 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("hitAccuracy")
         .method4(true))
      .method31();
   private final ToggleOption field27 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("hitsTaken")
         .method4(true))
      .method31();
   private final ToggleOption field28 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("longestCombo")
         .method4(true))
      .method31();
   private final ToggleOption field29 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("wTapAccuracy")
         .method4(true))
      .method31();

   public PvpInfoMeleeStats(Framework7Extension var1) {
      this.method2(Framework.field16, Framework4.method3(var1));
      this.method2(Framework.field1, new PvpInfoMeleeStats.Data());
   }

   @Override
   public String getId() {
      return "PVP_INFO_MELEE_CHILD";
   }

   @Override
   protected void method3(RootSettingsAssembler var1) {
      var1.OHOOORICRHIIIIRHCICICOCHROICRC(
         "renderOptions", var1x -> var1x.method9(new ClientOption[]{this.field26, this.field27, this.field28, this.field29})
      );
   }

   private class Data extends Framework7Extension2.Data3 {
      @Override
      protected List<Hitbox2> method5(boolean var1) {
         PvpInfo var2 = ThreadModuleDump63.method4().method40().method59();
         byte var3 = 0;
         byte var4 = 0;
         if (com.moonsworth.lunar.client.ui.hud.row.Hitbox.method3(
               PvpInfoMeleeStats.this.OCROHCOHOCOCHORROCOOIIOCHRIOIC.get(),
               this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(),
               (Gui2Extension)PvpInfoMeleeStats.this.OOHOIHICIIIIOHCOICCIOOCHHCIOOH.get()
            )
            == Gui2Extension.RIGHT) {
            var4 = 4;
         }

         ServerAddressBook var5 = var2.method13();
         ArrayList var6 = new ArrayList();
         if (PvpInfoMeleeStats.this.field26.get()
            || PvpInfoMeleeStats.this.field27.get()
            || PvpInfoMeleeStats.this.field28.get()
            || PvpInfoMeleeStats.this.field29.get()) {
            var6.add(
               com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
                  var4,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        this.method16(), PvpInfoMeleeStats.this.ORRCOROCROHHOHOOIRRRORROICRIIH, PvpInfoMeleeStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if (PvpInfoMeleeStats.this.field26.get()) {
            var6.add(
               com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        PvpInfoMeleeStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("meleeAccuracy", new Object[0]),
                        PvpInfoMeleeStats.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        PvpInfoMeleeStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        var5.method2().method1(),
                        PvpInfoMeleeStats.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        PvpInfoMeleeStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if (PvpInfoMeleeStats.this.field27.get()) {
            var6.add(
               com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        PvpInfoMeleeStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("hitsTaken", new Object[0]),
                        PvpInfoMeleeStats.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        PvpInfoMeleeStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        var5.method3().method1(),
                        PvpInfoMeleeStats.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        PvpInfoMeleeStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if (PvpInfoMeleeStats.this.field28.get()) {
            var6.add(
               com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        PvpInfoMeleeStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("longestCombo", new Object[0]),
                        PvpInfoMeleeStats.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        PvpInfoMeleeStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        var5.method4().method1(),
                        PvpInfoMeleeStats.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        PvpInfoMeleeStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if (PvpInfoMeleeStats.this.field29.get()) {
            var6.add(
               com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        PvpInfoMeleeStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("wTapAccuracy", new Object[0]),
                        PvpInfoMeleeStats.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        PvpInfoMeleeStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        var5.method5().method1(),
                        PvpInfoMeleeStats.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        PvpInfoMeleeStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         return var6;
      }

      private String method16() {
         StringBuilder var1 = new StringBuilder();
         if (PvpInfoMeleeStats.this.IRIHRIIICHIRRRHOCHHIIRHROCIRCC.get()) {
            var1.append("§l");
         }

         var1.append(PvpInfoMeleeStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("meleeInfo", new Object[0]));
         if (PvpInfoMeleeStats.this.IORRIRHIRIRCRHIICHROCHRCRORCHC.get()) {
            PvpInfo var2 = ThreadModuleDump63.method4().method40().method59();
            var1.append(" (");
            var1.append(var2.method15().get().toString());
            var1.append(")");
         }

         return var1.toString();
      }

      @Override
      public boolean method4(boolean var1) {
         if (!PvpInfoMeleeStats.this.field26.get()
            && !PvpInfoMeleeStats.this.field27.get()
            && !PvpInfoMeleeStats.this.field28.get()
            && !PvpInfoMeleeStats.this.field29.get()) {
            this.method8(0.0F, 0.0F);
            return false;
         } else {
            return super.ICIHHHCHHOIHHRROOCHOICRHOCHCOI(var1);
         }
      }
   }
}
