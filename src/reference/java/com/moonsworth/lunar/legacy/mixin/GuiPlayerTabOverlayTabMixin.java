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
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.NetworkPlayerInfoBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.render.EventRenderTabListEntry;
import com.moonsworth.lunar.client.mod.hud.tab.Tab;
import com.moonsworth.lunar.client.mod.misc.debug.ApolloDebugMod;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
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

@VersionGate(min = 1)
@Mixin(GuiPlayerTabOverlay.class)
public class GuiPlayerTabOverlayTabMixin {
   @Unique
   private static final Predicate<NetworkPlayerInfo> IS_REAL_PLAYER = arg0 -> {
      GameProfile gameprofile1 = arg0.getGameProfile();
      return gameprofile1.getId().version() != 2 || gameprofile1.getName().startsWith("!");
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

   public GuiPlayerTabOverlayTabMixin() {
   }

   @ModifyConstant(method = "renderPlayerlist", constant = {@Constant(intValue = 9, ordinal = 0), @Constant(intValue = 9, ordinal = 3)})
   private int lunar$offsetText(int number1) {
      Tab tab2 = Ref.method4().method40().method48();
      return tab2.isLunarIconsOnRight() && Ref.method4().method41().method6().method41().get() ? 18 : number1;
   }

   @Inject(method = "drawPing(IIILnet/minecraft/client/network/NetworkPlayerInfo;)V", at = @At("HEAD"), cancellable = true)
   private void lunar$drawPing(int number1, int number2, int number3, NetworkPlayerInfo networkplayerinfo4, CallbackInfo callback5, @Local(ordinal = 0, argsOnly = true) LocalIntRef localintref6) {
      int number7 = number2 + number1 - 2;
      Tab tab8 = Ref.method4().method40().method48();
      boolean flag9 = tab8.shouldHidePing(networkplayerinfo4.getResponseTime());
      byte index10 = 0;
      if (tab8.isLunarIconsOnRight()) {
         index10 = 10;
         int number11 = flag9 && tab8.getHidePing().get() ? 0 : 9;
         BridgeExtension3_5 bridgeextension3_512 = AbstractRenderContext.method32();
         if (tab8.renderLeftIcon(bridgeextension3_512.method42(), number7 - number11, number3, this.lunar$modifyingPlayerEntry)) {
            bridgeextension3_512.method25(1.0F, 1.0F, 1.0F, 1.0F);
         }
      }

      this.lunar$modifyingPlayerEntry = null;
      if (flag9) {
         callback5.cancel();
      } else if (tab8.renderPingNumber(AbstractRenderContext.method32(), networkplayerinfo4.getResponseTime(), number7 + index10, number3)) {
         callback5.cancel();
      } else {
         if (index10 != 0) {
            localintref6.set(localintref6.get() + index10);
         }
      }
   }

   @Redirect(method = "renderPlayerlist", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isIntegratedServerRunning()Z"))
   private boolean lunar$isRunningIntegrated(Minecraft minecraft1) {
      Tab tab2 = Ref.method4().method40().method48();
      return tab2.isEnabled() && !tab2.getDisplayPlayerHead().get() ? false : minecraft1.isIntegratedServerRunning();
   }

   @VersionGate(min = 5)
   @ModifyExpressionValue(method = "renderPlayerlist", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkManager_v1_12;isEncrypted()Z"))
   private boolean lunar$changeIsEncrypted$v1_12(boolean flag1) {
      Tab tab2 = Ref.method4().method40().method48();
      return tab2.isEnabled() && !tab2.getDisplayPlayerHead().get() ? false : flag1;
   }

   @VersionGate(max = 1)
   @ModifyExpressionValue(method = "renderPlayerlist", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkManager_v1_8;getIsencrypted()Z"))
   private boolean lunar$changeIsEncrypted$v1_8(boolean flag1) {
      Tab tab2 = Ref.method4().method40().method48();
      return tab2.isEnabled() && !tab2.getDisplayPlayerHead().get() ? false : flag1;
   }

   @Redirect(
      method = "renderPlayerlist",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiPlayerTabOverlay;getPlayerName(Lnet/minecraft/client/network/NetworkPlayerInfo;)Ljava/lang/String;",
         ordinal = 1
      )
   )
   private String lunar$onGetPlayerName(GuiPlayerTabOverlay guiplayertaboverlay1, NetworkPlayerInfo networkplayerinfo2) {
      this.lunar$modifyingPlayerEntry = ((PlayerInfoBridge)networkplayerinfo2).bridge$getProfileTextureId();
      String text3 = guiplayertaboverlay1.getPlayerName(networkplayerinfo2);
      EventRenderTabListEntry highlightimpl44 = (EventRenderTabListEntry)LunarEventBus.method29()
         .method12(EventRenderTabListEntry.class, () -> new EventRenderTabListEntry((NetworkPlayerInfoBridge)networkplayerinfo2, Component.text(text3)));
      return highlightimpl44 != null && highlightimpl44.isChanged() ? TextBridge.getTextContentForRendering(highlightimpl44.getComponent()) : text3;
   }

   @Redirect(
      method = "renderPlayerlist",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow$v1_8(Ljava/lang/String;FFI)I")
   )
   private int lunar$onDrawString(FontRenderer font1, String text2, float value3, float value4, int number5) {
      float value6 = 0.0F;
      Tab tab7 = Ref.method4().method40().method48();
      BridgeExtension3_5 bridgeextension3_58 = AbstractRenderContext.method32();
      boolean flag9 = tab7.isEnabled() ? (Boolean)tab7.getNameShadow().get() : true;
      text2 = Ref.method4().method40().method49().method14(text2);
      if (this.lunar$modifyingPlayerEntry != null) {
         if (!tab7.isLunarIconsOnRight() && tab7.renderLeftIcon(bridgeextension3_58.method42(), value3, value4, this.lunar$modifyingPlayerEntry)) {
            value6 = 9.0F;
         }

         int number13;
         if (tab7.isEnabled() && (Boolean)tab7.getHighlightOwnName().get() && this.lunar$modifyingPlayerEntry.equals(Ref.method7().bridge$getUniqueID())) {
            Component component11 = TextBridge.asAdventure((Bridge2_42)(new ChatComponentText(text2)))
               .replaceText(
                  (TextReplacementConfig)TextReplacementConfig.builder()
                     .match(Ref.method7().bridge$getName())
                     .replacement(
                        arg1x -> ((Builder)arg1x.color(TextColor.color(tab7.getNameColor().method14(0.0F)))).content(Ref.method7().bridge$getName())
                     )
                     .build()
               );
            number13 = (int)Ref.method10().bridge$drawString(bridgeextension3_58, component11, value3 + value6, value4, number5, flag9);
         } else {
            number13 = font1.drawString(text2, value3 + value6, value4, number5, flag9);
         }

         if (tab7.renderRightIcon(bridgeextension3_58.method42(), number13, value4, this.lunar$modifyingPlayerEntry)) {
            number13 += 8;
         }

         return number13;
      } else {
         int number10 = font1.drawString(text2, value3 + value6, value4, number5, flag9);
         if (tab7.renderRightIcon(bridgeextension3_58.method42(), number10, value4, this.lunar$modifyingPlayerEntry)) {
            number10 += 8;
         }

         return number10;
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
   private String lunar$onGetPlayerName$0(GuiPlayerTabOverlay guiplayertaboverlay1, NetworkPlayerInfo networkplayerinfo2, @Share("additionWeight") LocalBooleanRef localbooleanref3) {
      if (Ref.method4().method41().method6().method15()
         && ((PlayerInfoBridge)networkplayerinfo2).bridge$getProfileTextureId() != null
         && Ref.method4().method91().method3(((PlayerInfoBridge)networkplayerinfo2).bridge$getProfileTextureId())) {
         localbooleanref3.set(true);
         this.lunar$modifyingPlayerEntry = ((PlayerInfoBridge)networkplayerinfo2).bridge$getProfileTextureId();
      }

      return guiplayertaboverlay1.getPlayerName(networkplayerinfo2);
   }

   @Redirect(
      method = "renderPlayerlist",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;getStringWidth(Ljava/lang/String;)I", ordinal = 0)
   )
   private int lunar$onGetStringWidth(FontRenderer font1, String text2, @Share("additionWeight") LocalBooleanRef localbooleanref3) {
      int number4 = localbooleanref3.get() ? Ref.method4().method91().method4(this.lunar$modifyingPlayerEntry) : 0;
      localbooleanref3.set(false);
      this.lunar$modifyingPlayerEntry = null;
      return font1.getStringWidth(text2) + number4;
   }

   @ModifyArg(
      method = "renderPlayerlist",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiPlayerTabOverlay;drawRect(IIIII)V", ordinal = 0),
      index = 4
   )
   private int lunar$overrideHeaderColor(int number1) {
      return Ref.method4().method40().method48().isEnabled()
         ? Ref.method4().method40().method48().method19().method14(0.0F)
         : number1;
   }

   @ModifyArg(
      method = "renderPlayerlist",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiPlayerTabOverlay;drawRect(IIIII)V", ordinal = 1),
      index = 4
   )
   private int lunar$overrideBackgroundColor(int number1) {
      return Ref.method4().method40().method48().isEnabled()
         ? Ref.method4().method40().method48().method22().method14(0.0F)
         : number1;
   }

   @ModifyArg(
      method = "renderPlayerlist",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiPlayerTabOverlay;drawRect(IIIII)V", ordinal = 3),
      index = 4
   )
   private int lunar$overrideFooterColor(int number1) {
      return Ref.method4().method40().method48().isEnabled()
         ? Ref.method4().method40().method48().method21().method14(0.0F)
         : number1;
   }

   @Redirect(
      method = "renderPlayerlist",
      at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Ordering;sortedCopy(Ljava/lang/Iterable;)Ljava/util/List;")
   )
   private List<NetworkPlayerInfo> lunar$render$getPlayerInfo(
      Ordering ordering1, Iterable<NetworkPlayerInfo> list2, @Share("index") LocalIntRef localintref3, @Share("list") LocalRef<List<NetworkPlayerInfo>> localref4
   ) {
      List list5 = ENTRY_ORDERING.sortedCopy(list2);
      if (Ref.method4().method40().method48().method14()) {
         UUID uuid6 = Minecraft.getMinecraft().thePlayer.getUniqueID();
         NetworkPlayerInfo networkplayerinfo7 = list5.stream().filter(arg1x -> arg1x.gameProfile != null && arg1x.gameProfile.getId().equals(uuid6)).findFirst().orElse(null);
         if (networkplayerinfo7 != null) {
            list5.remove(networkplayerinfo7);
            list5.add(0, networkplayerinfo7);
         }
      }

      if (Ref.method4().method40().method48().method13()) {
         list5.removeIf(IS_REAL_PLAYER.negate());
      }

      NickHider nickhider8 = Ref.method4().method40().method41();
      if (nickhider8.isEnabled() && (Boolean)nickhider8.getHideLobbyID().get()) {
         Optional optional9 = list5.stream().filter(arg0 -> arg0.teamNameSPT != null && arg0.teamNameSPT.getUnformattedText().isEmpty()).findAny();
         list5.replaceAll(arg2x -> arg2x != null && nickhider8.method34((PlayerInfoBridge)arg2x) ? optional9.orElse(arg2x) : arg2x);
      }

      ArrayList list10 = new ArrayList(list5);
      List list11 = list10.subList(0, Math.min(list10.size(), 80));
      this.lunar$queueTabLogoUpdates(list11);
      localref4.set(list11);
      localintref3.set(0);
      return list5;
   }

   @Redirect(method = "renderPlayerlist", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiPlayerTabOverlay;drawRect(IIIII)V", ordinal = 2))
   private void lunar$render$fill$2(
      int number1, int number2, int number3, int number4, int number5, @Share("index") LocalIntRef localintref6, @Share("list") LocalRef<List<NetworkPlayerInfo>> localref7
   ) {
      Tab tab8 = Ref.method4().method40().method48();
      if (!tab8.isEnabled()) {
         Gui.drawRect(number1, number2, number3, number4, number5);
      } else {
         int index9 = localintref6.get();
         int number10 = tab8.getRowsColor().method14(index9);
         List list11 = (List)localref7.get();
         if (list11 != null && (Boolean)tab8.getPingRow().get() && index9 < list11.size()) {
            NetworkPlayerInfo networkplayerinfo12 = (NetworkPlayerInfo)list11.get(index9);
            if (networkplayerinfo12 != null) {
               number10 = tab8.getPingColor(networkplayerinfo12.getResponseTime());
               number10 = ColorUtils.method18(number10, 0.1254902F);
            }
         }

         Gui.drawRect(number1, number2, number3, number4, number10);
         localintref6.set(++index9);
      }
   }

   @Inject(method = "renderPlayerlist", at = @At("HEAD"))
   private void lunar$render$head(
      int number1,
      Scoreboard scoreboard2,
      ScoreObjective scoreobjective3,
      CallbackInfo callback4,
      @Share("header") LocalRef<IChatComponent> localref5,
      @Share("footer") LocalRef<IChatComponent> localref6
   ) {
      Tab tab7 = Ref.method4().method40().method48();
      if (tab7.isEnabled()) {
         if ((Boolean)tab7.getDisableHeader().get()) {
            localref5.set(this.header);
            this.header = null;
         }

         if ((Boolean)tab7.getDisableFooter().get()) {
            localref6.set(this.footer);
            this.footer = null;
         }
      }
   }

   @Inject(method = "renderPlayerlist", at = @At("RETURN"))
   private void lunar$render$return(
      int number1,
      Scoreboard scoreboard2,
      ScoreObjective scoreobjective3,
      CallbackInfo callback4,
      @Share("header") LocalRef<IChatComponent> localref5,
      @Share("footer") LocalRef<IChatComponent> localref6
   ) {
      Tab tab7 = Ref.method4().method40().method48();
      if (tab7.isEnabled()) {
         if ((Boolean)tab7.getDisableHeader().get()) {
            this.header = (IChatComponent)localref5.get();
         }

         if ((Boolean)tab7.getDisableFooter().get()) {
            this.footer = (IChatComponent)localref6.get();
         }
      }
   }

   @Unique
   private void lunar$queueTabLogoUpdates(List<NetworkPlayerInfo> list1) {
      Set set2 = list1.stream().filter(IS_REAL_PLAYER).map(arg0 -> arg0.getGameProfile().getId()).collect(Collectors.toSet());
      ApolloDebugMod apollodebugmod3 = Ref.method4().method40().method80();
      if (apollodebugmod3 != null && apollodebugmod3.isEnabled() && apollodebugmod3.method13()) {
         Set set4 = list1.stream().map(arg0 -> arg0.getGameProfile().getId()).collect(Collectors.toSet());
         apollodebugmod3.method14(set2, set4);
      }

      Client.method109().method91().method5(set2);
   }

   @WrapOperation(method = "renderPlayerlist", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isIntegratedServerRunning()Z"))
   private boolean lunar$rewindRenderPlayerHead(Minecraft minecraft1, Operation<Boolean> operation2) {
      RewindMod rewind3 = Ref.method4().method40().method85();
      return rewind3.method19() || (Boolean)operation2.call(new Object[]{minecraft1});
   }
}
