package com.moonsworth.lunar.client.mod.render;

import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension222;
import com.moonsworth.lunar.bridge.BridgeExtension_9;
import com.moonsworth.lunar.client.framework.Calculator2Handler;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework11;
import com.moonsworth.lunar.client.framework.Framework2;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.Framework7Extension2;
import com.moonsworth.lunar.client.framework.Framework8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType2_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers.Data12;
import com.moonsworth.lunar.client.highlight.fishing.HighlightImpl6_2;
import com.moonsworth.lunar.client.highlight.mixin.highlight.HighlightImpl11;
import com.moonsworth.lunar.client.highlight.mixin.nameplate.HighlightImpl4;
import com.moonsworth.lunar.client.lighting.LightingExtension;
import com.moonsworth.lunar.client.lighting.LightingExtension23;
import com.moonsworth.lunar.client.lighting.LightingExtension4222;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.lighting.LightingExtension4222.Data;
import com.moonsworth.lunar.client.lighting.LightingExtension443.Data2;
import com.moonsworth.lunar.client.lighting.rewindhandlers.RewindhandlersType;
import com.moonsworth.lunar.client.mod.misc.Skyblock;
import com.moonsworth.lunar.client.util.Annotation5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class SkyblockDungeonTeammateHighlight extends Framework7Extension2 {
   private final GuiRewindhandlersHandler2_2 field8 = (GuiRewindhandlersHandler2_2)this.IHRHHRIHICHOOICIRIOOHOICHIRHOI(GuiRewindhandlersHandler2_2.class);
   private final LightingExtension4222 field9 = (LightingExtension4222)((Data)com.moonsworth.lunar.client.lighting.Lighting.method8("skyblockDungeonGlowHealer")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(this.method6(HologramsType2_2.HEALER)))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field10 = (LightingExtension4222)((Data)com.moonsworth.lunar.client.lighting.Lighting.method8(
            "skyblockDungeonGlowArcher"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(this.method6(HologramsType2_2.ARCHER)))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field11 = (LightingExtension4222)((Data)com.moonsworth.lunar.client.lighting.Lighting.method8("skyblockDungeonGlowTank")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(this.method6(HologramsType2_2.TANK)))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field12 = (LightingExtension4222)((Data)com.moonsworth.lunar.client.lighting.Lighting.method8("skyblockDungeonGlowMage")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(this.method6(HologramsType2_2.MAGE)))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field13 = (LightingExtension4222)((Data)com.moonsworth.lunar.client.lighting.Lighting.method8(
            "skyblockDungeonGlowBerserk"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(this.method6(HologramsType2_2.BERSERK)))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field14 = (LightingExtension4222)((Data)com.moonsworth.lunar.client.lighting.Lighting.method8(
            "skyblockDungeonGlowUnknown"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5592406))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field15 = (LightingExtension443)((Data2)com.moonsworth.lunar.client.lighting.Lighting.method7("showTeammateName")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field16 = (LightingExtension443)((Data2)com.moonsworth.lunar.client.lighting.Lighting.method7("showTeammateHealth")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field17 = (LightingExtension443)((Data2)com.moonsworth.lunar.client.lighting.Lighting.method7(
            "hideOriginalTeammateNametag"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   public SkyblockDungeonTeammateHighlight(Skyblock var1) {
      super(false);
      this.method5(Framework.field16, Framework4.method3(var1));
      this.method5(Framework.field17, Framework2.method2(RewindhandlersType.DUNGEONS));
      this.method5(Framework.field19, Framework11.method1(this, () -> Click3.method2() == Gui2Extension3.DUNGEON));
      this.handle(HighlightImpl6_2.class, this::method1);
      this.handle(Data12.class, this::method2);
      this.handle(HighlightImpl4.class, this::method3);
      this.handle(HighlightImpl11.class, this::method4);
   }

   private void method1(HighlightImpl6_2 var1) {
      Holograms2_5 var2 = (Holograms2_5)this.field8.method5().orElse(null);
      if (var2 != null && this.field8.method12()) {
         BridgeExtension var3 = var1.field1;
         if (var3 instanceof Bridge6_10) {
            for (Holograms4Updater var5 : var2.getPlayers()) {
               Optional var6 = var5.method27();
               if (!var6.isEmpty() && var6.get() == var3) {
                  this.method5(var3, var5.method37());
                  return;
               }
            }
         }
      }
   }

   private void method2(Data12 var1) {
      Optional var2 = var1.method1().method27();
      if (!var2.isEmpty()) {
         this.method5((BridgeExtension)var2.get(), var1.method2());
      }
   }

   private void method3(HighlightImpl4 var1) {
      if ((Boolean)this.field15.get() || (Boolean)this.field16.get()) {
         Holograms2_5 var2 = (Holograms2_5)this.field8.method5().orElse(null);
         if (var2 != null && this.field8.method12()) {
            Bridge2_43 var3 = ThreadModuleDump63.method13();
            float var4 = var1.method5();
            BridgeExtension_9 var5 = var1.method3();
            var5.push();
            var5.translate(-var3.bridge$renderPosX(), -var3.bridge$renderPosY(), -var3.bridge$renderPosZ());

            for (Holograms4Updater var7 : var2.getPlayers()) {
               Optional var8 = var7.method27();
               if (!var8.isEmpty()) {
                  Bridge6_10 var9 = (Bridge6_10)var8.get();
                  if (!var9.bridge$isSelf()) {
                     double var10 = var9.RHIRRICCRHHHIIHHIHHOHRCHIOORCC(var4);
                     double var12 = var9.IHRHHRIHICHOOICIRIOOHOICHIRHOI(var4);
                     double var14 = var9.IIORCIOOIHRRRICOHIRCIHOOCCOHRO(var4);
                     double var16 = this.field17.get() ? 2.4 : 2.8;
                     float var18 = (float)ThreadModuleDump63.method7().HROHOIOCHIRIHICOORIHOHCIOIRIIH(var10, var12 + var16, var14);
                     float var19 = Math.max(var18 / 10.0F, 1.0F);
                     int var20 = var7.method40();
                     if ((Boolean)this.field16.get() && var20 > 0) {
                        TextComponent var21 = (TextComponent)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD))
                                 .append(Component.text(var20, var7.method42())))
                              .append(Component.text('❤', NamedTextColor.RED)))
                           .build();
                        Click.method11(var5, var21, var10, var12 + var16, var14, true, var19);
                        var16 += 0.4 * var19;
                     }

                     if ((Boolean)this.field15.get()) {
                        Component var25 = var7.method44();
                        HologramsType2_2 var22 = var7.method37();
                        if (var25 != null && var22 != null) {
                           TextColor var23 = TextColor.color(this.method7(var22));
                           TextComponent var24 = (TextComponent)((Builder)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD))
                                       .append(Component.text("[" + var22.getFirstLetter() + "]", NamedTextColor.YELLOW)))
                                    .appendSpace())
                                 .append(var25.color(var23)))
                              .build();
                           Click.method11(var5, var24, var10, var12 + var16, var14, true, var19);
                        }
                     }
                  }
               }
            }

            var5.pop();
         }
      }
   }

   private void method4(HighlightImpl11 var1) {
      if ((Boolean)this.field17.get()) {
         Holograms2_5 var2 = (Holograms2_5)this.field8.method5().orElse(null);
         if (var2 != null && this.field8.method12()) {
            if (var1.method2() instanceof BridgeExtension222) {
               var1.setCancelled(true);
            }
         }
      }
   }

   private void method5(BridgeExtension var1, HologramsType2_2 var2) {
      var1.bridge$setGlowing(true);
      var1.bridge$setGlowingColor(this.method7(var2));
   }

   private int method6(HologramsType2_2 var1) {
      return var1.getColor().value() | 0xFF000000;
   }

   private int method7(HologramsType2_2 var1) {
      if (var1 == null) {
         return this.field14.method14(0.0F);
      }

      return switch (var1) {
         case HEALER -> this.field9.method14(0.0F);
         case ARCHER -> this.field10.method14(0.0F);
         case TANK -> this.field11.method14(0.0F);
         case MAGE -> this.field12.method14(0.0F);
         case BERSERK -> this.field13.method14(0.0F);
         default -> throw new IncompatibleClassChangeError();
      };
   }

   @Annotation5
   public String getId() {
      return "SKYBLOCK_DUNGEON_TEAMMATE_HIGHLIGHT";
   }

   protected Framework8 method20() {
      return Framework8.method7().method1(new Calculator2Handler[]{Calculator2Handler.field5}).method11(this);
   }

   public void method2(LightingExtension23 var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         RewindhandlersType.GENERAL, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field15, this.field16, this.field17})
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         RewindhandlersType.COLOR,
         var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new LightingExtension[]{this.field9, this.field10, this.field11, this.field12, this.field13, this.field14}
         )
      );
   }
}
