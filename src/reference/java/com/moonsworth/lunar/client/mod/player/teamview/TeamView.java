package com.moonsworth.lunar.client.mod.player.teamview;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge8_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.lighting.Lighting2;
import com.moonsworth.lunar.bridge.lighting.Lighting3;
import com.moonsworth.lunar.bridge.lighting.Lighting4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.account.TeamMemberManager;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.teamview.Teamview;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler23;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.util.ThreadModuleDump38;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.joml.Vector3f;

public final class TeamView extends AbstractFeature {
   private final GuiRewindhandlersHandler2 field8 = (GuiRewindhandlersHandler2)this.method19(GuiRewindhandlersHandler2.class);
   private final GuiRewindhandlersHandler23 field9 = (GuiRewindhandlersHandler23)this.method19(GuiRewindhandlersHandler23.class);
   private final ToggleOption field10 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("apolloTeams")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("teamviewHypixel")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field12 = (ColorOption)((ColorOption.Data)OptionFactory.method8("hypixelTeamColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-13893823))
      .method31();
   private final ColorOption field13 = (ColorOption)((ColorOption.Data)OptionFactory.method8("hypixelPartyColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-604620))
      .method31();
   private final Teamview field14 = new Teamview();
   private final Set<String> field15 = new HashSet<>();
   private final Set<UUID> field16 = new HashSet<>();

   public TeamView() {
      super(true);
      this.handle(HudRenderLegacyEventAlt.class, this::method3);
      this.handle(EventClientTick.class, this::method7);
   }

   @Override
   public String getId() {
      return "TEAM_VIEW";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.method9(new ClientOption[]{this.field10});
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field11, var1xx -> var1xx.method9(new ClientOption[]{this.field12, this.field13})
               )
               .RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(1);
         }
      );
   }

   @Override
   public void method3(boolean var1) {
      this.field15.clear();
      this.field16.clear();
   }

   private void method3(HudRenderLegacyEventAlt var1) {
      AbstractRenderContext var2 = var1.method3();
      float var3 = this.mc.bridge$getTimer().method1();
      Bridge2_43 var4 = ThreadModuleDump63.method13();
      if (var4 != null && (!Bridge.getMinecraftVersion().method19() || !var4.bridge$getCamera().isEmpty())) {
         float var5 = (float)var4.bridge$renderPosX();
         float var6 = (float)var4.bridge$renderPosY();
         float var7 = (float)var4.bridge$renderPosZ();
         double var8 = (this.mc.bridge$getPlayer().bridge$getRotationPitch() + 90.0) * Math.PI / 180.0;
         double var10 = (this.mc.bridge$getPlayer().bridge$getRotationYaw() + 90.0) * Math.PI / 180.0;
         if (this.mc.bridge$getGameSettings().bridge$getThirdPersonView() == 2) {
            var10 = (this.mc.bridge$getPlayer().bridge$getRotationYaw() - 90.0) * Math.PI / 180.0;
         }

         Vec3Bridge var12 = Vec3Bridge.method2(
            ThreadModuleDump38.sin(var8) * ThreadModuleDump38.method1(var10),
            ThreadModuleDump38.method1(var8),
            ThreadModuleDump38.sin(var8) * ThreadModuleDump38.sin(var10)
         );
         if (this.field10.get()) {
            TeamMemberManager var13 = Client.method109().method60();

            for (Memory var15 : var13.method3().values()) {
               Component var16 = var15.method24();
               int var17 = var15.getColor();
               Optional var18 = this.mc.bridge$getWorld().bridge$getPlayerByUniqueId(var15.method10());
               var18.ifPresentOrElse(var9 -> this.method10(var2, var16, var3, var9, var17, var12, var5, var6, var7), () -> {
                  if (var13.method6(var15)) {
                     this.method9(var2, var16, var17, var12, var5, var6, var7, var15);
                  }
               });
            }
         }

         if (this.field11.get() && Highlight3Iterator.method8(KeystrokesType.HYPIXEL)) {
            this.method4(var7x -> this.method10(var2, var7x.bridge$getDisplayNameComponent(), var3, var7x, this.field12.method13(), var12, var5, var6, var7));
            this.method5(var7x -> this.method10(var2, var7x.bridge$getDisplayNameComponent(), var3, var7x, this.field13.method13(), var12, var5, var6, var7));
         }
      }
   }

   public void method4(Consumer<Bridge6_10> var1) {
      for (UUID var3 : this.field16) {
         this.mc.bridge$getWorld().bridge$getPlayerByUniqueId(var3).filter(TeamView::method6).ifPresent(var1);
      }
   }

   public void method5(Consumer<Bridge6_10> var1) {
      for (String var3 : this.field15) {
         this.mc
            .bridge$getWorld()
            .bridge$getPlayerByName(var3)
            .filter(var1x -> method6(var1x) && !this.field16.contains(var1x.bridge$getUniqueID()))
            .ifPresent(var1);
      }
   }

   private static boolean method6(Bridge6_10 var0) {
      return !var0.bridge$isSelf() && !var0.bridge$isSneaking() && !var0.bridge$isInvisibleToPlayer() && !var0.bridge$isInvisible();
   }

   private void method7(EventClientTick var1) {
      this.field15.clear();
      this.field16.clear();
      if (this.field11.get()
         && ThreadModuleDump63.method7() != null
         && ThreadModuleDump63.method8() != null
         && this.field11.get()
         && Highlight3Iterator.method8(KeystrokesType.HYPIXEL)) {
         if (!this.field14.isLoaded()) {
            try {
               this.field14.load();
            } catch (Exception var3) {
               Inventorymod2.method5(var3, "Loading Hypixel TeamView data json");
            }
         }

         if (!this.field14.method4(this.field9.method7())) {
            if (this.field14.method3(this.field9.method7())) {
               this.field8.method7().map(Rewindhandlers3::method1).ifPresent(this.field15::addAll);
            } else {
               this.method13();
            }
         }
      }
   }

   private void method13() {
      ClientPacketListenerBridge var1 = ThreadModuleDump63.method3().bridge$getClientPacketListener();
      if (var1 != null) {
         Bridge2_33 var2 = var1.bridge$getPlayerInfo(ThreadModuleDump63.method7().bridge$getUniqueID());
         if (var2 != null) {
            Lighting4 var3 = ThreadModuleDump63.method7().bridge$getWorld().bridge$getScoreBoard();
            if (var3 != null) {
               com.moonsworth.lunar.bridge.lighting.Lighting var4 = var3.bridge$getObjectiveInDisplaySlot(1);
               if (var4 != null) {
                  for (Lighting2 var7 : var3.bridge$getSortedScores(var4)) {
                     Lighting3 var8 = var3.bridge$getPlayersTeam(var7.bridge$getPlayerName());
                     Component var9 = var7.bridge$getPlayerComponent();
                     if (var8 != null) {
                        var9 = var8.bridge$formatString(var9);
                     }

                     if (AdventureTextBridge.startsWith(var9, "Waiting...") || AdventureTextBridge.startsWith(var9, "Starting in ")) {
                        return;
                     }
                  }

                  Component var12 = AdventureTextBridge.getFirstColoredComponent(var2.bridge$formatName());
                  if (var12 != null) {
                     int var13 = 0;
                     int var14 = 0;

                     for (Bridge2_33 var10 : var1.bridge$getPlayerInfoMap()) {
                        if (var10 != var2) {
                           Component var11 = AdventureTextBridge.getFirstColoredComponent(var10.bridge$formatName());
                           if (var11 != null) {
                              var13++;
                              if (Objects.equals(var12.style(), var11.style())) {
                                 var14++;
                                 this.field16.add(var10.bridge$getGameProfile().getId());
                              }
                           }
                        }
                     }

                     if (var13 == var14) {
                        this.field16.clear();
                     }
                  }
               }
            }
         }
      }
   }

   private void method9(AbstractRenderContext var1, Component var2, int var3, Vec3Bridge var4, float var5, float var6, float var7, Memory var8) {
      Vec3Bridge var9 = var8.method20();
      Vec3Bridge var10 = var8.method19();
      if (var9 != null && var10 != null) {
         double var11 = var9.bridge$xCoord();
         double var13 = var9.bridge$yCoord();
         double var15 = var9.bridge$zCoord();
         double var17 = var10.bridge$xCoord();
         double var19 = var10.bridge$yCoord();
         double var21 = var10.bridge$zCoord();
         double var23 = var8.method3();
         float var25 = (float)(var17 + (var11 - var17) * var23 - var5);
         float var26 = (float)(var19 + (var13 - var19) * var23 - var6);
         float var27 = (float)(var21 + (var15 - var21) * var23 - var7);
         float var28 = (float)Math.sqrt(var25 * var25 + var26 * var26 + var27 * var27);
         float var29 = this.mc.bridge$getGameSettings().bridge$getRenderDistance() * 16.0F;
         if (var28 > var29) {
            float var30 = var29 / var28;
            var25 *= var30;
            var26 *= var30;
            var27 *= var30;
         }

         this.method11(var1, var2, var3, var25, var26 + 3.0F, var27, var4, var28, true);
      }
   }

   private void method10(
      AbstractRenderContext var1, Component var2, float var3, BridgeExtension var4, int var5, Vec3Bridge var6, float var7, float var8, float var9
   ) {
      if (var4 != this.mc.bridge$getPlayer()) {
         double var10 = var4.bridge$getPosX();
         double var12 = var4.bridge$getPosY();
         double var14 = var4.bridge$getPosZ();
         double var16 = var4.bridge$lastTickX();
         double var18 = var4.bridge$lastTickY();
         double var20 = var4.bridge$lastTickZ();
         float var22 = (float)(var16 + (var10 - var16) * var3 - var7);
         float var23 = (float)(var18 + (var12 - var18) * var3 - var8);
         float var24 = (float)(var20 + (var14 - var20) * var3 - var9);
         float var25 = (float)Math.sqrt(var22 * var22 + var23 * var23 + var24 * var24);
         this.method11(var1, var2, var5, var22, var23 + 3.0F, var24, var6, var25, false);
      }
   }

   private void method11(AbstractRenderContext var1, Component var2, int var3, float var4, float var5, float var6, Vec3Bridge var7, float var8, boolean var9) {
      byte var10 = 6;
      byte var11 = 8;
      var1.push();
      Vector3f var12 = new Vector3f(var4, var5, var6);
      Bridge8_5.method2(var1, var12);
      float var13 = 0.016666668F * (1.0F + Math.min(var8, 128.0F) * 0.15F);
      var1.scale(-var13, -var13, var13);
      this.method12(var1, var3, var10, -(var11 / 2.0F) - var11, var11);
      var1.method6(AbstractRenderContext::method16);
      if (var9) {
         Bridge10_2 var14 = ThreadModuleDump63.method10();
         String var15 = "(" + (int)var8 + "m)";
         Vec3Bridge var16 = Vec3Bridge.method2(var4, var5, var6).bridge$normalize();
         var14.method7(var1, var15, var14.method16(var15, 0.0F), 10.0F, 2030043135, true);
         if (var2 != null && var7.bridge$dotProduct(var16) > 0.99) {
            var14.method11(var1, var2, var14.method18(var2, 0.0F), 20.0F, 2030043135, true);
         }
      }

      var1.pop();
   }

   private void method12(AbstractRenderContext var1, int var2, float var3, float var4, float var5) {
      Bridge2_32 var6 = var1.method10(LunarRenderTypes.field53);
      float var7 = (var2 >> 16 & 0xFF) / 255.0F;
      float var8 = (var2 >> 8 & 0xFF) / 255.0F;
      float var9 = (var2 & 0xFF) / 255.0F;
      var1.push();
      var1.scale(0.5F, 0.5F, 0.5F);
      var1.method4(45.0F, 0.0F, 0.0F, 1.0F);
      var1.translate(var3 * 2.0F, 0.0, 0.0);
      var1.method4(90.0F, 0.0F, 0.0F, -1.0F);
      var6.method1();
      var6.method2(-var3, var4, 0.0).method8(var7, var8, var9, 0.75F).method16();
      var6.method2(-var3, var4 + var5 / 2.0F, 0.0).method8(var7, var8, var9, 0.75F).method16();
      var6.method2(var3, var4 + var5 / 2.0F, 0.0).method8(var7, var8, var9, 0.75F).method16();
      var6.method2(var3, var4, 0.0).method8(var7, var8, var9, 0.75F).method16();
      var6.method17(BufferBuildMode.BATCHED);
      var1.method4(90.0F, 0.0F, 0.0F, -1.0F);
      var1.translate(var3 * 2.0F + 3.0F, var5 / 2.0F + 2.0F, 0.0);
      var6.method1();
      var6.method2(-var3 / 2.0F, var4, 0.0).method8(var7, var8, var9, 0.75F).method16();
      var6.method2(-var3 / 2.0F, var4 + var5 / 2.0F, 0.0).method8(var7, var8, var9, 0.75F).method16();
      var6.method2(var3 - 1.0F, var4 + var5 / 2.0F, 0.0).method8(var7, var8, var9, 0.75F).method16();
      var6.method2(var3 - 1.0F, var4, 0.0).method8(var7, var8, var9, 0.75F).method16();
      var6.method17(BufferBuildMode.BATCHED);
      var1.pop();
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field5).method11(this);
   }

   @Generated
   public ColorOption method14() {
      return this.field12;
   }

   @Generated
   public ColorOption method15() {
      return this.field13;
   }
}
