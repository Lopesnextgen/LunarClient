package com.moonsworth.lunar.client.mod.skyblock.dungeonteammatehighlight;

import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType2_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data12;
import com.moonsworth.lunar.client.event.entity.EntitySpawnEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.NameTagRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.Annotation5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class SkyblockDungeonTeammateHighlight extends AbstractFeature {
   private final GuiRewindhandlersHandler2_2 field8 = (GuiRewindhandlersHandler2_2)this.method19(GuiRewindhandlersHandler2_2.class);
   private final ColorOption field9 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "skyblockDungeonGlowHealer"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(this.method6(HologramsType2_2.HEALER)))
      .method31();
   private final ColorOption field10 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "skyblockDungeonGlowArcher"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(this.method6(HologramsType2_2.ARCHER)))
      .method31();
   private final ColorOption field11 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "skyblockDungeonGlowTank"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(this.method6(HologramsType2_2.TANK)))
      .method31();
   private final ColorOption field12 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "skyblockDungeonGlowMage"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(this.method6(HologramsType2_2.MAGE)))
      .method31();
   private final ColorOption field13 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "skyblockDungeonGlowBerserk"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(this.method6(HologramsType2_2.BERSERK)))
      .method31();
   private final ColorOption field14 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "skyblockDungeonGlowUnknown"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5592406))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showTeammateName"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showTeammateHealth"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "hideOriginalTeammateNametag"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();

   public SkyblockDungeonTeammateHighlight(Skyblock var1) {
      super(false);
      this.method5(Framework.field16, Framework4.method3(var1));
      this.method5(Framework.field17, Framework2.method2(SettingsPage.DUNGEONS));
      this.method5(Framework.field19, Framework11.method1(this, () -> Click3.getIsland() == Gui2Extension3.DUNGEON));
      this.handle(EntitySpawnEvent.class, this::method1);
      this.handle(Rewindhandlers$Data12.class, this::method2);
      this.handle(HudRenderLegacyEventAlt.class, this::method3);
      this.handle(NameTagRenderEvent.class, this::method4);
   }

   private void method1(EntitySpawnEvent var1) {
      Holograms2_5 var2 = this.field8.method5().orElse(null);
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

   private void method2(Rewindhandlers$Data12 var1) {
      Optional var2 = var1.method1().method27();
      if (!var2.isEmpty()) {
         this.method5((BridgeExtension)var2.get(), var1.method2());
      }
   }

   private void method3(HudRenderLegacyEventAlt var1) {
      if (this.field15.get() || this.field16.get()) {
         Holograms2_5 var2 = this.field8.method5().orElse(null);
         if (var2 != null && this.field8.method12()) {
            Bridge2_43 var3 = ThreadModuleDump63.method13();
            float var4 = var1.method5();
            AbstractRenderContext var5 = var1.method3();
            var5.push();
            var5.translate(-var3.bridge$renderPosX(), -var3.bridge$renderPosY(), -var3.bridge$renderPosZ());

            for (Holograms4Updater var7 : var2.getPlayers()) {
               Optional var8 = var7.method27();
               if (!var8.isEmpty()) {
                  Bridge6_10 var9 = (Bridge6_10)var8.get();
                  if (!var9.bridge$isSelf()) {
                     double var10 = var9.method8(var4);
                     double var12 = var9.method19(var4);
                     double var14 = var9.method10(var4);
                     double var16 = this.field17.get() ? 2.4 : 2.8;
                     float var18 = (float)ThreadModuleDump63.method7().HROHOIOCHIRIHICOORIHOHCIOIRIIH(var10, var12 + var16, var14);
                     float var19 = Math.max(var18 / 10.0F, 1.0F);
                     int var20 = var7.method40();
                     if (this.field16.get() && var20 > 0) {
                        TextComponent var21 = (TextComponent)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD))
                                 .append(Component.text(var20, var7.method42())))
                              .append(Component.text('❤', NamedTextColor.RED)))
                           .build();
                        Click.drawComponentCentered(var5, var21, var10, var12 + var16, var14, true, var19);
                        var16 += 0.4 * var19;
                     }

                     if (this.field15.get()) {
                        Component var25 = var7.method44();
                        HologramsType2_2 var22 = var7.method37();
                        if (var25 != null && var22 != null) {
                           TextColor var23 = TextColor.color(this.method7(var22));
                           TextComponent var24 = (TextComponent)((Builder)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD))
                                       .append(Component.text("[" + var22.getFirstLetter() + "]", NamedTextColor.YELLOW)))
                                    .appendSpace())
                                 .append(var25.color(var23)))
                              .build();
                           Click.drawComponentCentered(var5, var24, var10, var12 + var16, var14, true, var19);
                        }
                     }
                  }
               }
            }

            var5.pop();
         }
      }
   }

   private void method4(NameTagRenderEvent var1) {
      if (this.field17.get()) {
         Holograms2_5 var2 = this.field8.method5().orElse(null);
         if (var2 != null && this.field8.method12()) {
            if (var1.method2() instanceof EntityPlayerBridge) {
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
      };
   }

   @Annotation5
   @Override
   public String getId() {
      return "SKYBLOCK_DUNGEON_TEAMMATE_HIGHLIGHT";
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field5).method11(this);
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL, var1x -> var1x.method9(new ClientOption[]{this.field15, this.field16, this.field17})
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.COLOR,
         var1x -> var1x.method9(
            new ClientOption[]{this.field9, this.field10, this.field11, this.field12, this.field13, this.field14}
         )
      );
   }
}
