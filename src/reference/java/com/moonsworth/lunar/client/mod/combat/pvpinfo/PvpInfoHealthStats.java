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

public class PvpInfoHealthStats extends Framework7Extension2 {
   private final ToggleOption field26 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("lostHealth")
         .method4(true))
      .method31();
   private final ToggleOption field27 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("recoveredHealth")
         .method4(true))
      .method31();
   private final ToggleOption field28 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("gapplesUsed")
         .method4(true))
      .method31();
   private final ToggleOption field29 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("potionsUsed")
         .method4(true))
      .method31();

   public PvpInfoHealthStats(Framework7Extension var1) {
      this.method2(Framework.field16, Framework4.method3(var1));
      this.method2(Framework.field1, new PvpInfoHealthStats.Data());
   }

   @Override
   public String getId() {
      return "PVP_INFO_HEALTH_CHILD";
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
               PvpInfoHealthStats.this.OCROHCOHOCOCHORROCOOIIOCHRIOIC.get(),
               this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(),
               (Gui2Extension)PvpInfoHealthStats.this.OOHOIHICIIIIOHCOICCIOOCHHCIOOH.get()
            )
            == Gui2Extension.RIGHT) {
            var4 = 4;
         }

         ServerAddressBook var5 = var2.method13();
         ArrayList var6 = new ArrayList();
         var6.add(
            com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
               var4,
               new Hitbox2[]{
                  com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                     this.method16(), PvpInfoHealthStats.this.ORRCOROCROHHOHOOIRRRORROICRIIH, PvpInfoHealthStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                  )
               }
            )
         );
         if (PvpInfoHealthStats.this.field26.get()) {
            var6.add(
               com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        PvpInfoHealthStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("lostHealth", new Object[0]),
                        PvpInfoHealthStats.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        PvpInfoHealthStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        var5.method6().method1(),
                        PvpInfoHealthStats.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        PvpInfoHealthStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if (PvpInfoHealthStats.this.field27.get()) {
            var6.add(
               com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        PvpInfoHealthStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("regennedHealth", new Object[0]),
                        PvpInfoHealthStats.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        PvpInfoHealthStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        var5.method7().method1(),
                        PvpInfoHealthStats.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        PvpInfoHealthStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if (PvpInfoHealthStats.this.field28.get()) {
            var6.add(
               com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        PvpInfoHealthStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("gapplesUsed", new Object[0]),
                        PvpInfoHealthStats.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        PvpInfoHealthStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        var5.method8().method1(),
                        PvpInfoHealthStats.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        PvpInfoHealthStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if (PvpInfoHealthStats.this.field29.get()) {
            var6.add(
               com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        PvpInfoHealthStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("healthPotionsUsed", new Object[0]),
                        PvpInfoHealthStats.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        PvpInfoHealthStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        var5.method9().method1(),
                        PvpInfoHealthStats.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        PvpInfoHealthStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         return var6;
      }

      private String method16() {
         StringBuilder var1 = new StringBuilder();
         if (PvpInfoHealthStats.this.IRIHRIIICHIRRRHOCHHIIRHROCIRCC.get()) {
            var1.append("§l");
         }

         var1.append(PvpInfoHealthStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("healthInfo", new Object[0]));
         if (PvpInfoHealthStats.this.IORRIRHIRIRCRHIICHROCHRCRORCHC.get()) {
            PvpInfo var2 = ThreadModuleDump63.method4().method40().method59();
            var1.append(" (");
            var1.append(var2.method15().get().toString());
            var1.append(")");
         }

         return var1.toString();
      }

      @Override
      public boolean method4(boolean var1) {
         if (!PvpInfoHealthStats.this.field26.get()
            && !PvpInfoHealthStats.this.field27.get()
            && !PvpInfoHealthStats.this.field28.get()
            && !PvpInfoHealthStats.this.field29.get()) {
            this.method8(0.0F, 0.0F);
            return false;
         } else {
            return super.ICIHHHCHHOIHHRROOCHOICRHOCHCOI(var1);
         }
      }
   }
}
