package com.moonsworth.lunar.legacy.mixin;

import com.google.common.collect.Ordering;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Bridge_30;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.render.TabListEntryRenderEvent;
import com.moonsworth.lunar.client.mod.hud.tab.Tab;
import com.moonsworth.lunar.client.mod.misc.debug.ApolloDebugMod;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.TextColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(min = 1)
@Mixin(GuiPlayerTabOverlay.class)
public class GuiPlayerTabOverlayMixin2 {
   @Unique
   private static final Predicate<NetworkPlayerInfo> IS_REAL_PLAYER = var0 -> {
      GameProfile var1 = var0.getGameProfile();
      return var1.getId().version() != 2 || var1.getName().startsWith("!");
   };
   @Unique
   private static final UUID EMPTY_UUID = UUID.randomUUID();
   @Shadow
   public IChatComponent header;
   @Shadow
   public IChatComponent footer;
   @Final
   @Shadow
   public static Ordering<NetworkPlayerInfo> ENTRY_ORDERING;
   @Unique
   private UUID lunar$modifyingPlayerEntry = null;

   @ModifyConstant(method = "renderPlayerlist", constant = {@Constant(intValue = 9, ordinal = 0), @Constant(intValue = 9, ordinal = 3)})
   private int lunar$offsetText(int var1) {
      Tab var2 = ThreadModuleDump63.method4().method40().method48();
      return var2.isLunarIconsOnRight() && ThreadModuleDump63.method4().method41().method6().method41().get() ? 18 : var1;
   }

   @Inject(method = "drawPing(IIILnet/minecraft/client/network/NetworkPlayerInfo;)V", at = @At("HEAD"), cancellable = true)
   private void lunar$drawPing(int var1, int var2, int var3, NetworkPlayerInfo var4, CallbackInfo var5, @Local(ordinal = 0, argsOnly = true) LocalIntRef var6) {
      int var7 = var2 + var1 - 2;
      Tab var8 = ThreadModuleDump63.method4().method40().method48();
      boolean var9 = var8.shouldHidePing(var4.getResponseTime());
      byte var10 = 0;
      if (var8.isLunarIconsOnRight()) {
         var10 = 10;
         int var11 = var9 && var8.getHidePing().get() ? 0 : 9;
         BridgeExtension3_5 var12 = AbstractRenderContext.method32();
         if (var8.renderLeftIcon(var12.method42(), var7 - var11, var3, this.lunar$modifyingPlayerEntry)) {
            var12.method25(1.0F, 1.0F, 1.0F, 1.0F);
         }
      }

      this.lunar$modifyingPlayerEntry = null;
      if (var9) {
         var5.cancel();
      } else if (var8.renderPingNumber(AbstractRenderContext.method32(), var4.getResponseTime(), var7 + var10, var3)) {
         var5.cancel();
      } else {
         if (var10 != 0) {
            var6.set(var6.get() + var10);
         }
      }
   }

   @Redirect(method = "renderPlayerlist", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isIntegratedServerRunning()Z"))
   private boolean lunar$isRunningIntegrated(Minecraft var1) {
      Tab var2 = ThreadModuleDump63.method4().method40().method48();
      return var2.isEnabled() && !var2.getDisplayPlayerHead().get() ? false : var1.isIntegratedServerRunning();
   }

   @Annotation2(min = 5)
   @ModifyExpressionValue(method = "renderPlayerlist", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkManager_v1_12;isEncrypted()Z"))
   private boolean lunar$changeIsEncrypted$v1_12(boolean var1) {
      Tab var2 = ThreadModuleDump63.method4().method40().method48();
      return var2.isEnabled() && !var2.getDisplayPlayerHead().get() ? false : var1;
   }

   @Annotation2(max = 1)
   @ModifyExpressionValue(method = "renderPlayerlist", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkManager_v1_8;getIsencrypted()Z"))
   private boolean lunar$changeIsEncrypted$v1_8(boolean var1) {
      Tab var2 = ThreadModuleDump63.method4().method40().method48();
      return var2.isEnabled() && !var2.getDisplayPlayerHead().get() ? false : var1;
   }

   @Redirect(
      method = "renderPlayerlist",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiPlayerTabOverlay;getPlayerName(Lnet/minecraft/client/network/NetworkPlayerInfo;)Ljava/lang/String;",
         ordinal = 1
      )
   )
   private String lunar$onGetPlayerName(GuiPlayerTabOverlay var1, NetworkPlayerInfo var2) {
      this.lunar$modifyingPlayerEntry = ((Bridge2_33)var2).bridge$getProfileTextureId();
      String var3 = var1.getPlayerName(var2);
      TabListEntryRenderEvent var4 = (TabListEntryRenderEvent)ClientEventBus.method29()
         .method12(TabListEntryRenderEvent.class, () -> new TabListEntryRenderEvent((Bridge_30)var2, Component.text(var3)));
      return var4 != null && var4.isChanged() ? AdventureTextBridge.getTextContentForRendering(var4.getComponent()) : var3;
   }

   @Redirect(
      method = "renderPlayerlist",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow$v1_8(Ljava/lang/String;FFI)I")
   )
   private int lunar$onDrawString(FontRenderer var1, String var2, float var3, float var4, int var5) {
      float var6 = 0.0F;
      Tab var7 = ThreadModuleDump63.method4().method40().method48();
      BridgeExtension3_5 var8 = AbstractRenderContext.method32();
      boolean var9 = var7.isEnabled() ? var7.getNameShadow().get() : true;
      var2 = ThreadModuleDump63.method4().method40().method49().method14(var2);
      if (this.lunar$modifyingPlayerEntry != null) {
         if (!var7.isLunarIconsOnRight() && var7.renderLeftIcon(var8.method42(), var3, var4, this.lunar$modifyingPlayerEntry)) {
            var6 = 9.0F;
         }

         int var13;
         if (var7.isEnabled() && var7.getHighlightOwnName().get() && this.lunar$modifyingPlayerEntry.equals(ThreadModuleDump63.method7().bridge$getUniqueID())) {
            Component var11 = AdventureTextBridge.asAdventure((Bridge2_42)(new ChatComponentText(var2)))
               .replaceText(
                  (TextReplacementConfig)TextReplacementConfig.builder()
                     .match(ThreadModuleDump63.method7().bridge$getName())
                     .replacement(
                        var1x -> ((Builder)var1x.color(TextColor.color(var7.getNameColor().method14(0.0F)))).content(ThreadModuleDump63.method7().bridge$getName())
                     )
                     .build()
               );
            var13 = (int)ThreadModuleDump63.method10().bridge$drawString(var8, var11, var3 + var6, var4, var5, var9);
         } else {
            var13 = var1.drawString(var2, var3 + var6, var4, var5, var9);
         }

         if (var7.renderRightIcon(var8.method42(), var13, var4, this.lunar$modifyingPlayerEntry)) {
            var13 += 8;
         }

         return var13;
      } else {
         int var10 = var1.drawString(var2, var3 + var6, var4, var5, var9);
         if (var7.renderRightIcon(var8.method42(), var10, var4, this.lunar$modifyingPlayerEntry)) {
            var10 += 8;
         }

         return var10;
      }
   }

   @Redirect(
      method = "renderPlayerlist",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiPlayerTabOverlay;getPlayerName(Lnet/minecraft/client/network/NetworkPlayerInfo;)Ljava/lang/String;",
         ordinal = 0
      )
   )
   private String lunar$onGetPlayerName$0(GuiPlayerTabOverlay var1, NetworkPlayerInfo var2, @Share("additionWeight") LocalBooleanRef var3) {
      if (ThreadModuleDump63.method4().method41().method6().method15()
         && ((Bridge2_33)var2).bridge$getProfileTextureId() != null
         && ThreadModuleDump63.method4().method91().method3(((Bridge2_33)var2).bridge$getProfileTextureId())) {
         var3.set(true);
         this.lunar$modifyingPlayerEntry = ((Bridge2_33)var2).bridge$getProfileTextureId();
      }

      return var1.getPlayerName(var2);
   }

   @Redirect(
      method = "renderPlayerlist",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;getStringWidth(Ljava/lang/String;)I", ordinal = 0)
   )
   private int lunar$onGetStringWidth(FontRenderer var1, String var2, @Share("additionWeight") LocalBooleanRef var3) {
      int var4 = var3.get() ? ThreadModuleDump63.method4().method91().method4(this.lunar$modifyingPlayerEntry) : 0;
      var3.set(false);
      this.lunar$modifyingPlayerEntry = null;
      return var1.getStringWidth(var2) + var4;
   }

   @ModifyArg(
      method = "renderPlayerlist",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiPlayerTabOverlay;drawRect(IIIII)V", ordinal = 0),
      index = 4
   )
   private int lunar$overrideHeaderColor(int var1) {
      return ThreadModuleDump63.method4().method40().method48().isEnabled()
         ? ThreadModuleDump63.method4().method40().method48().method19().method14(0.0F)
         : var1;
   }

   @ModifyArg(
      method = "renderPlayerlist",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiPlayerTabOverlay;drawRect(IIIII)V", ordinal = 1),
      index = 4
   )
   private int lunar$overrideBackgroundColor(int var1) {
      return ThreadModuleDump63.method4().method40().method48().isEnabled()
         ? ThreadModuleDump63.method4().method40().method48().method22().method14(0.0F)
         : var1;
   }

   @ModifyArg(
      method = "renderPlayerlist",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiPlayerTabOverlay;drawRect(IIIII)V", ordinal = 3),
      index = 4
   )
   private int lunar$overrideFooterColor(int var1) {
      return ThreadModuleDump63.method4().method40().method48().isEnabled()
         ? ThreadModuleDump63.method4().method40().method48().method21().method14(0.0F)
         : var1;
   }

   @Redirect(
      method = "renderPlayerlist",
      at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Ordering;sortedCopy(Ljava/lang/Iterable;)Ljava/util/List;")
   )
   private List<NetworkPlayerInfo> lunar$render$getPlayerInfo(
      Ordering var1, Iterable<NetworkPlayerInfo> var2, @Share("index") LocalIntRef var3, @Share("list") LocalRef<List<NetworkPlayerInfo>> var4
   ) {
      List var5 = ENTRY_ORDERING.sortedCopy(var2);
      if (ThreadModuleDump63.method4().method40().method48().method14()) {
         UUID var6 = Minecraft.getMinecraft().thePlayer.getUniqueID();
         NetworkPlayerInfo var7 = var5.stream().filter(var1x -> var1x.gameProfile != null && var1x.gameProfile.getId().equals(var6)).findFirst().orElse(null);
         if (var7 != null) {
            var5.remove(var7);
            var5.add(0, var7);
         }
      }

      if (ThreadModuleDump63.method4().method40().method48().method13()) {
         var5.removeIf(IS_REAL_PLAYER.negate());
      }

      NickHider var8 = ThreadModuleDump63.method4().method40().method41();
      if (var8.isEnabled() && var8.renderLeftIcon5().get()) {
         Optional var9 = var5.stream().filter(var0 -> var0.teamNameSPT != null && var0.teamNameSPT.getUnformattedText().isEmpty()).findAny();
         var5.replaceAll(var2x -> var2x != null && var8.getHidePing((Bridge2_33)var2x) ? var9.orElse(var2x) : var2x);
      }

      ArrayList var10 = new ArrayList(var5);
      List var11 = var10.subList(0, Math.min(var10.size(), 80));
      this.lunar$queueTabLogoUpdates(var11);
      var4.set(var11);
      var3.set(0);
      return var5;
   }

   @Redirect(method = "renderPlayerlist", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiPlayerTabOverlay;drawRect(IIIII)V", ordinal = 2))
   private void lunar$render$fill$2(
      int var1, int var2, int var3, int var4, int var5, @Share("index") LocalIntRef var6, @Share("list") LocalRef<List<NetworkPlayerInfo>> var7
   ) {
      Tab var8 = ThreadModuleDump63.method4().method40().method48();
      if (!var8.isEnabled()) {
         Gui.drawRect(var1, var2, var3, var4, var5);
      } else {
         int var9 = var6.get();
         int var10 = var8.getRowsColor().method14(var9);
         List var11 = (List)var7.get();
         if (var11 != null && var8.getPingRow().get() && var9 < var11.size()) {
            NetworkPlayerInfo var12 = (NetworkPlayerInfo)var11.get(var9);
            if (var12 != null) {
               var10 = var8.getPingColor(var12.getResponseTime());
               var10 = ThreadModuleDump23.method18(var10, 0.1254902F);
            }
         }

         Gui.drawRect(var1, var2, var3, var4, var10);
         var6.set(++var9);
      }
   }

   @Inject(method = "renderPlayerlist", at = @At("HEAD"))
   private void lunar$render$head(
      int var1,
      Scoreboard var2,
      ScoreObjective var3,
      CallbackInfo var4,
      @Share("header") LocalRef<IChatComponent> var5,
      @Share("footer") LocalRef<IChatComponent> var6
   ) {
      Tab var7 = ThreadModuleDump63.method4().method40().method48();
      if (var7.isEnabled()) {
         if (var7.getDisableHeader().get()) {
            var5.set(this.header);
            this.header = null;
         }

         if (var7.getDisableFooter().get()) {
            var6.set(this.footer);
            this.footer = null;
         }
      }
   }

   @Inject(method = "renderPlayerlist", at = @At("RETURN"))
   private void lunar$render$return(
      int var1,
      Scoreboard var2,
      ScoreObjective var3,
      CallbackInfo var4,
      @Share("header") LocalRef<IChatComponent> var5,
      @Share("footer") LocalRef<IChatComponent> var6
   ) {
      Tab var7 = ThreadModuleDump63.method4().method40().method48();
      if (var7.isEnabled()) {
         if (var7.getDisableHeader().get()) {
            this.header = (IChatComponent)var5.get();
         }

         if (var7.getDisableFooter().get()) {
            this.footer = (IChatComponent)var6.get();
         }
      }
   }

   @Unique
   private void lunar$queueTabLogoUpdates(List<NetworkPlayerInfo> var1) {
      Set var2 = var1.stream().filter(IS_REAL_PLAYER).map(var0 -> var0.getGameProfile().getId()).collect(Collectors.toSet());
      ApolloDebugMod var3 = ThreadModuleDump63.method4().method40().method80();
      if (var3 != null && var3.isEnabled() && var3.method13()) {
         Set var4 = var1.stream().map(var0 -> var0.getGameProfile().getId()).collect(Collectors.toSet());
         var3.method14(var2, var4);
      }

      Client.method109().method91().method5(var2);
   }

   @WrapOperation(method = "renderPlayerlist", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isIntegratedServerRunning()Z"))
   private boolean lunar$rewindRenderPlayerHead(Minecraft var1, Operation<Boolean> var2) {
      Rewind var3 = ThreadModuleDump63.method4().method40().method85();
      return var3.method19() || (Boolean)var2.call(new Object[]{var1});
   }
}
