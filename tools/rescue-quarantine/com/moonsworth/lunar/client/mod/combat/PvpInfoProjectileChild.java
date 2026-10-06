package com.moonsworth.lunar.client.mod.combat;

import com.moonsworth.lunar.bridge.Bridge;
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
import com.moonsworth.lunar.client.lighting.LightingExtension4222;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.lighting.LightingExtension443.Data2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;

public class PvpInfoProjectileChild extends Framework7Extension2 {
   private final LightingExtension443 field26 = (LightingExtension443)((Data2)Lighting.method7("bowAccuracy").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field27 = (LightingExtension443)((Data2)Lighting.method7("rodAccuracy").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field28 = (LightingExtension443)((Data2)Lighting.method7("eggsAndSnowballsUsed").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field29 = (LightingExtension443)((Data2)Lighting.method7("pearlsUsed").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field30 = (LightingExtension4222)((com.moonsworth.lunar.client.lighting.LightingExtension4222.Data)Lighting.method8(
            "dividerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-8355712))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   public PvpInfoProjectileChild(Framework7Extension var1) {
      this.HORHROIOIOICIRHIOCOICHHHIHCIIO(Framework.field16, Framework4.method3(var1));
      this.HORHROIOIOICIRHIOCOICHHHIHCIIO(Framework.field1, new PvpInfoProjectileChild.Data());
   }

   public String getId() {
      return "PVP_INFO_PROJECTILE_CHILD";
   }

   protected void method3(LightingExtension23 var1) {
      var1.OHOOORICRHIIIIRHCICICOCHROICRC("renderOptions", var1x -> {
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field27}).IIOHHROCRCCRCRIHCOHIHRHCOOIHRR(1);
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field26, this.field28, this.field29});
      });
      var1.HHRROIIHRRICIIHIIHICRHHRHOHHOO(this.RCICCOHCHROOOICROOOORIIHCRIHCH, new LightingExtension[]{this.field30});
   }

   private class Data extends Data3 {
      private Data() {
         super(PvpInfoProjectileChild.this);
      }

      protected List<Hitbox2> method5(boolean var1) {
         PvpInfo var2 = ThreadModuleDump63.method4().method40().method59();
         byte var3 = 0;
         byte var4 = 0;
         if (com.moonsworth.lunar.client.hitbox.Hitbox.method3(
               (Boolean)PvpInfoProjectileChild.this.OCROHCOHOCOCHORROCOOIIOCHRIOIC.get(),
               this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(),
               (Gui2Extension)PvpInfoProjectileChild.this.OOHOIHICIIIIOHCOICCIOOCHHCIOOH.get()
            )
            == Gui2Extension.RIGHT) {
            var4 = 4;
         }

         Coordinates var5 = var2.method13();
         ArrayList var6 = new ArrayList();
         var6.add(
            com.moonsworth.lunar.client.hitbox.Hitbox.method4(
               var4,
               new Hitbox2[]{
                  com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                     this.method16(),
                     PvpInfoProjectileChild.this.ORRCOROCROHHOHOOIRRRORROICRIIH,
                     (Boolean)PvpInfoProjectileChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                  )
               }
            )
         );
         if ((Boolean)PvpInfoProjectileChild.this.field26.get()) {
            var6.add(
               com.moonsworth.lunar.client.hitbox.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        PvpInfoProjectileChild.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("bowAccuracy", new Object[0]),
                        PvpInfoProjectileChild.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        (Boolean)PvpInfoProjectileChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        var5.method11().method1(),
                        PvpInfoProjectileChild.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        (Boolean)PvpInfoProjectileChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if (Bridge.getMinecraftVersion().method22() && (Boolean)PvpInfoProjectileChild.this.field27.get()) {
            var6.add(
               com.moonsworth.lunar.client.hitbox.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        PvpInfoProjectileChild.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("rodAccuracy", new Object[0]),
                        PvpInfoProjectileChild.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        (Boolean)PvpInfoProjectileChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        var5.method12().method1(),
                        PvpInfoProjectileChild.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        (Boolean)PvpInfoProjectileChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if ((Boolean)PvpInfoProjectileChild.this.field28.get()) {
            var6.add(
               com.moonsworth.lunar.client.hitbox.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        PvpInfoProjectileChild.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("eggs", new Object[0]),
                        PvpInfoProjectileChild.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        (Boolean)PvpInfoProjectileChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        " / ", PvpInfoProjectileChild.this.field30, (Boolean)PvpInfoProjectileChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        PvpInfoProjectileChild.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("snowballsUsed", new Object[0]),
                        PvpInfoProjectileChild.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        (Boolean)PvpInfoProjectileChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        var5.method13().method1(),
                        PvpInfoProjectileChild.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        (Boolean)PvpInfoProjectileChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         if ((Boolean)PvpInfoProjectileChild.this.field29.get()) {
            var6.add(
               com.moonsworth.lunar.client.hitbox.Hitbox.method4(
                  var3,
                  new Hitbox2[]{
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        PvpInfoProjectileChild.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("pearlsUsed", new Object[0]),
                        PvpInfoProjectileChild.this.OCOICCIIOCCIHORRIRCOOHHIIICHHH,
                        (Boolean)PvpInfoProjectileChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     ),
                     com.moonsworth.lunar.client.hitbox.Hitbox.method5(
                        var5.method14().method1(),
                        PvpInfoProjectileChild.this.RCICCOHCHROOOICROOOORIIHCRIHCH,
                        (Boolean)PvpInfoProjectileChild.this.CIOCIIIOHCOHRIICRHIRIIRCOIIROR.get()
                     )
                  }
               )
            );
         }

         return var6;
      }

      private String method16() {
         StringBuilder var1 = new StringBuilder();
         if ((Boolean)PvpInfoProjectileChild.this.IRIHRIIICHIRRRHOCHHIIRHROCIRCC.get()) {
            var1.append("§l");
         }

         var1.append(PvpInfoProjectileChild.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("projectileInfo", new Object[0]));
         if ((Boolean)PvpInfoProjectileChild.this.IORRIRHIRIRCRHIICHROCHRCRORCHC.get()) {
            PvpInfo var2 = ThreadModuleDump63.method4().method40().method59();
            var1.append(" (");
            var1.append(((com.moonsworth.lunar.client.framework.feature.pvpinfo.Gui2Extension)var2.method15().get()).toString());
            var1.append(")");
         }

         return var1.toString();
      }

      public boolean method4(boolean var1) {
         if (!(Boolean)PvpInfoProjectileChild.this.field26.get()
            && (ThreadModuleDump63.MC_VERSION > 1 || !(Boolean)PvpInfoProjectileChild.this.field27.get())
            && !(Boolean)PvpInfoProjectileChild.this.field28.get()
            && !(Boolean)PvpInfoProjectileChild.this.field29.get()) {
            this.CCROIHHHCOCHHOHORCIRHOCRROIOCI(0.0F, 0.0F);
            return false;
         } else {
            return super.ICIHHHCHHOIHHRROOCHOICRHOCHCOI(var1);
         }
      }
   }
}
