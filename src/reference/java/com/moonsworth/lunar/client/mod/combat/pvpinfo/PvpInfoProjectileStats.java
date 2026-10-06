package com.moonsworth.lunar.client.mod.combat.pvpinfo;

import com.moonsworth.lunar.bridge.Bridge;
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
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;

public class PvpInfoProjectileStats extends Framework7Extension2 {
   private final ToggleOption field26 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("bowAccuracy")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field27 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("rodAccuracy")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field28 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("eggsAndSnowballsUsed")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field29 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("pearlsUsed")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field30 = (ColorOption)((ColorOption.Data)OptionFactory.method8("dividerColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-8355712))
      .method31();

   public PvpInfoProjectileStats(Framework7Extension var1) {
      this.method2(Framework.field16, Framework4.method3(var1));
      this.method2(Framework.field1, new PvpInfoProjectileStats.Data());
   }

   @Override
   public String getId() {
      return "PVP_INFO_PROJECTILE_CHILD";
   }

   @Override
   protected void method3(RootSettingsAssembler var1) {
      var1.OHOOORICRHIIIIRHCICICOCHROICRC("renderOptions", var1x -> {
         var1x.method9(new ClientOption[]{this.field27}).IIOHHROCRCCRCRIHCOHIHRHCOOIHRR(1);
         var1x.method9(new ClientOption[]{this.field26, this.field28, this.field29});
      });
      var1.method9(this.field30, new ClientOption[]{this.field30});
   }

   private class Data extends Framework7Extension2.Data3 {
      @Override
      protected List<Hitbox2> method5(boolean var1) {
         PvpInfo var2 = ThreadModuleDump63.method4().method40().method59();
         byte var3 = 0;
         byte var4 = 0;
         if (com.moonsworth.lunar.client.ui.hud.row.Hitbox.method3(
               PvpInfoProjectileStats.this.OCROHCOHOCOCHORROCOOIIOCHRIOIC.get(),
               this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(),
               (Gui2Extension)PvpInfoProjectileStats.this.OOHOIHICIIIIOHCOICCIOOCHHCIOOH.get()
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
                     this.method16(),
                     PvpInfoProjectileStats.this.ORRCOROCROHHOHOOIRRRORROICRIIH,
                     PvpInfoProjectileStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                  )
               }
            )
         );
         if (PvpInfoProjectileStats.this.field26.get()) {
            var6.add(
               com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        PvpInfoProjectileStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("bowAccuracy", new Object[0]),
                        PvpInfoProjectileStats.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        PvpInfoProjectileStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        var5.method11().method1(),
                        PvpInfoProjectileStats.this.field30,
                        PvpInfoProjectileStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if (Bridge.getMinecraftVersion().method22() && PvpInfoProjectileStats.this.field27.get()) {
            var6.add(
               com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        PvpInfoProjectileStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("rodAccuracy", new Object[0]),
                        PvpInfoProjectileStats.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        PvpInfoProjectileStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        var5.method12().method1(),
                        PvpInfoProjectileStats.this.field30,
                        PvpInfoProjectileStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if (PvpInfoProjectileStats.this.field28.get()) {
            var6.add(
               com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        PvpInfoProjectileStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("eggs", new Object[0]),
                        PvpInfoProjectileStats.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        PvpInfoProjectileStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        " / ", PvpInfoProjectileStats.this.field30, PvpInfoProjectileStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        PvpInfoProjectileStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("snowballsUsed", new Object[0]),
                        PvpInfoProjectileStats.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        PvpInfoProjectileStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        var5.method13().method1(),
                        PvpInfoProjectileStats.this.field30,
                        PvpInfoProjectileStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if (PvpInfoProjectileStats.this.field29.get()) {
            var6.add(
               com.moonsworth.lunar.client.ui.hud.row.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        PvpInfoProjectileStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("pearlsUsed", new Object[0]),
                        PvpInfoProjectileStats.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        PvpInfoProjectileStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.ui.hud.row.Hitbox.method5(
                        var5.method14().method1(),
                        PvpInfoProjectileStats.this.field30,
                        PvpInfoProjectileStats.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         return var6;
      }

      private String method16() {
         StringBuilder var1 = new StringBuilder();
         if (PvpInfoProjectileStats.this.IRIHRIIICHIRRRHOCHHIIRHROCIRCC.get()) {
            var1.append("§l");
         }

         var1.append(PvpInfoProjectileStats.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("projectileInfo", new Object[0]));
         if (PvpInfoProjectileStats.this.IORRIRHIRIRCRHIICHROCHRCRORCHC.get()) {
            PvpInfo var2 = ThreadModuleDump63.method4().method40().method59();
            var1.append(" (");
            var1.append(var2.method15().get().toString());
            var1.append(")");
         }

         return var1.toString();
      }

      @Override
      public boolean method4(boolean var1) {
         if (!PvpInfoProjectileStats.this.field26.get()
            && (ThreadModuleDump63.MC_VERSION > 1 || !PvpInfoProjectileStats.this.field27.get())
            && !PvpInfoProjectileStats.this.field28.get()
            && !PvpInfoProjectileStats.this.field29.get()) {
            this.method8(0.0F, 0.0F);
            return false;
         } else {
            return super.ICIHHHCHHOIHHRROOCHOICRHOCHCOI(var1);
         }
      }
   }
}
