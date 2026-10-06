package com.moonsworth.lunar.client.mod.combat;

import com.moonsworth.lunar.client.fog.holograms.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.pvpinfo.pvp.Framework7Extension2;
import com.moonsworth.lunar.client.framework.feature.pvpinfo.pvp.Framework7Extension2.Data3;
import com.moonsworth.lunar.client.hitbox.Gui2Extension;
import com.moonsworth.lunar.client.hitbox.Hitbox2;
import com.moonsworth.lunar.client.lighting.Lighting;
import com.moonsworth.lunar.client.lighting.LightingExtension;
import com.moonsworth.lunar.client.lighting.LightingExtension23;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.lighting.LightingExtension443.Data2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;

public class PvpInfoMeleeChild extends Framework7Extension2 {
   private final LightingExtension443 field26 = (LightingExtension443)((Data2)Lighting.method7("hitAccuracy").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field27 = (LightingExtension443)((Data2)Lighting.method7("hitsTaken").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field28 = (LightingExtension443)((Data2)Lighting.method7("longestCombo").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field29 = (LightingExtension443)((Data2)Lighting.method7("wTapAccuracy").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   public PvpInfoMeleeChild(Framework7Extension var1) {
      this.HORHROIOIOICIRHIOCOICHHHIHCIIO(Framework.field16, Framework4.method3(var1));
      this.HORHROIOIOICIRHIOCOICHHHIHCIIO(Framework.field1, new PvpInfoMeleeChild.Data());
   }

   public String getId() {
      return "PVP_INFO_MELEE_CHILD";
   }

   protected void method3(LightingExtension23 var1) {
      var1.OHOOORICRHIIIIRHCICICOCHROICRC(
         "renderOptions", var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field26, this.field27, this.field28, this.field29})
      );
   }

   private class Data extends Data3 {
      private Data() {
         super(PvpInfoMeleeChild.this);
      }

      protected List<Hitbox2> method5(boolean var1) {
         PvpInfo var2 = ThreadModuleDump63.method4().method40().method59();
         byte var3 = 0;
         byte var4 = 0;
         if (com.moonsworth.lunar.client.hitbox.Hitbox.method3(
               (Boolean)PvpInfoMeleeChild.this.OCROHCOHOCOCHORROCOOIIOCHRIOIC.get(),
               this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(),
               (Gui2Extension)PvpInfoMeleeChild.this.OOHOIHICIIIIOHCOICCIOOCHHCIOOH.get()
            )
            == Gui2Extension.RIGHT) {
            var4 = 4;
         }

         Coordinates var5 = var2.method13();
         ArrayList var6 = new ArrayList();
         if ((Boolean)PvpInfoMeleeChild.this.field26.get()
            || (Boolean)PvpInfoMeleeChild.this.field27.get()
            || (Boolean)PvpInfoMeleeChild.this.field28.get()
            || (Boolean)PvpInfoMeleeChild.this.field29.get()) {
            var6.add(
               com.moonsworth.lunar.client.hitbox.Hitbox.method4(
                  var4,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        this.method16(),
                        PvpInfoMeleeChild.this.ORRCOROCROHHOHOOIRRRORROICRIIH,
                        (Boolean)PvpInfoMeleeChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if ((Boolean)PvpInfoMeleeChild.this.field26.get()) {
            var6.add(
               com.moonsworth.lunar.client.hitbox.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        PvpInfoMeleeChild.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("meleeAccuracy", new Object[0]),
                        PvpInfoMeleeChild.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        (Boolean)PvpInfoMeleeChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        var5.method2().method1(),
                        PvpInfoMeleeChild.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        (Boolean)PvpInfoMeleeChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if ((Boolean)PvpInfoMeleeChild.this.field27.get()) {
            var6.add(
               com.moonsworth.lunar.client.hitbox.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        PvpInfoMeleeChild.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("hitsTaken", new Object[0]),
                        PvpInfoMeleeChild.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        (Boolean)PvpInfoMeleeChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        var5.method3().method1(),
                        PvpInfoMeleeChild.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        (Boolean)PvpInfoMeleeChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if ((Boolean)PvpInfoMeleeChild.this.field28.get()) {
            var6.add(
               com.moonsworth.lunar.client.hitbox.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        PvpInfoMeleeChild.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("longestCombo", new Object[0]),
                        PvpInfoMeleeChild.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        (Boolean)PvpInfoMeleeChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        var5.method4().method1(),
                        PvpInfoMeleeChild.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        (Boolean)PvpInfoMeleeChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if ((Boolean)PvpInfoMeleeChild.this.field29.get()) {
            var6.add(
               com.moonsworth.lunar.client.hitbox.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        PvpInfoMeleeChild.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("wTapAccuracy", new Object[0]),
                        PvpInfoMeleeChild.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        (Boolean)PvpInfoMeleeChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        var5.method5().method1(),
                        PvpInfoMeleeChild.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        (Boolean)PvpInfoMeleeChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         return var6;
      }

      private String method16() {
         StringBuilder var1 = new StringBuilder();
         if ((Boolean)PvpInfoMeleeChild.this.IRIHRIIICHIRRRHOCHHIIRHROCIRCC.get()) {
            var1.append("§l");
         }

         var1.append(PvpInfoMeleeChild.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("meleeInfo", new Object[0]));
         if ((Boolean)PvpInfoMeleeChild.this.IORRIRHIRIRCRHIICHROCHRCRORCHC.get()) {
            PvpInfo var2 = ThreadModuleDump63.method4().method40().method59();
            var1.append(" (");
            var1.append(((com.moonsworth.lunar.client.framework.feature.pvpinfo.Gui2Extension)var2.method15().get()).toString());
            var1.append(")");
         }

         return var1.toString();
      }

      public boolean method4(boolean var1) {
         if (!(Boolean)PvpInfoMeleeChild.this.field26.get()
            && !(Boolean)PvpInfoMeleeChild.this.field27.get()
            && !(Boolean)PvpInfoMeleeChild.this.field28.get()
            && !(Boolean)PvpInfoMeleeChild.this.field29.get()) {
            this.CCROIHHHCOCHHOHORCIRHOCRROIOCI(0.0F, 0.0F);
            return false;
         } else {
            return super.ICIHHHCHHOIHHRROOCHOICRHOCHCOI(var1);
         }
      }
   }
}
