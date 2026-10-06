package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.TypeRegistry;
import com.lunarclient.apollo.beam.v1.DisplayBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.RemoveBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.ResetBeaconBeamsMessage;
import com.lunarclient.apollo.border.v1.DisplayBorderMessage;
import com.lunarclient.apollo.border.v1.RemoveBorderMessage;
import com.lunarclient.apollo.border.v1.ResetBordersMessage;
import com.lunarclient.apollo.button.v1.Button;
import com.lunarclient.apollo.button.v1.ButtonContent;
import com.lunarclient.apollo.button.v1.ButtonContentPart;
import com.lunarclient.apollo.button.v1.ButtonSize;
import com.lunarclient.apollo.button.v1.ButtonTooltip;
import com.lunarclient.apollo.button.v1.ButtonUpdate;
import com.lunarclient.apollo.chat.v1.ChatButton;
import com.lunarclient.apollo.chat.v1.DisplayChatButtonsMessage;
import com.lunarclient.apollo.chat.v1.DisplayLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.RemoveChatButtonMessage;
import com.lunarclient.apollo.chat.v1.RemoveLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.ResetChatButtonsMessage;
import com.lunarclient.apollo.chat.v1.UpdateChatButtonMessage;
import com.lunarclient.apollo.coloredfire.v1.OverrideColoredFireMessage;
import com.lunarclient.apollo.coloredfire.v1.ResetColoredFireMessage;
import com.lunarclient.apollo.coloredfire.v1.ResetColoredFiresMessage;
import com.lunarclient.apollo.common.v1.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.common.v1.BlockLocation;
import com.lunarclient.apollo.common.v1.Color;
import com.lunarclient.apollo.common.v1.Component;
import com.lunarclient.apollo.common.v1.Cuboid2D;
import com.lunarclient.apollo.common.v1.Cuboid3D;
import com.lunarclient.apollo.common.v1.EntityId;
import com.lunarclient.apollo.common.v1.Icon;
import com.lunarclient.apollo.common.v1.ItemStackIcon;
import com.lunarclient.apollo.common.v1.Location;
import com.lunarclient.apollo.common.v1.LunarClientVersion;
import com.lunarclient.apollo.common.v1.MinecraftVersion;
import com.lunarclient.apollo.common.v1.PlayerLocation;
import com.lunarclient.apollo.common.v1.ResourceLocationIcon;
import com.lunarclient.apollo.common.v1.SimpleResourceLocationIcon;
import com.lunarclient.apollo.common.v1.SimpleResourceLocationSound;
import com.lunarclient.apollo.common.v1.Sound;
import com.lunarclient.apollo.common.v1.Uuid;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.cooldown.v1.CooldownStyle;
import com.lunarclient.apollo.cooldown.v1.DisplayCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.RemoveCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.ResetCooldownsMessage;
import com.lunarclient.apollo.cosmetic.v1.BodyOptions;
import com.lunarclient.apollo.cosmetic.v1.CloakOptions;
import com.lunarclient.apollo.cosmetic.v1.Cosmetic;
import com.lunarclient.apollo.cosmetic.v1.DisplaySprayMessage;
import com.lunarclient.apollo.cosmetic.v1.Emote;
import com.lunarclient.apollo.cosmetic.v1.EquipNpcCosmeticsMessage;
import com.lunarclient.apollo.cosmetic.v1.HatOptions;
import com.lunarclient.apollo.cosmetic.v1.PetOptions;
import com.lunarclient.apollo.cosmetic.v1.RemoveSprayMessage;
import com.lunarclient.apollo.cosmetic.v1.ResetNpcCosmeticsMessage;
import com.lunarclient.apollo.cosmetic.v1.ResetNpcEmotesMessage;
import com.lunarclient.apollo.cosmetic.v1.ResetSpraysMessage;
import com.lunarclient.apollo.cosmetic.v1.StartNpcEmoteMessage;
import com.lunarclient.apollo.cosmetic.v1.StopNpcEmoteMessage;
import com.lunarclient.apollo.cosmetic.v1.UnequipNpcCosmeticsMessage;
import com.lunarclient.apollo.entity.v1.FlipEntityMessage;
import com.lunarclient.apollo.entity.v1.OverrideRainbowSheepMessage;
import com.lunarclient.apollo.entity.v1.ResetFlipedEntityMessage;
import com.lunarclient.apollo.entity.v1.ResetRainbowSheepMessage;
import com.lunarclient.apollo.glow.v1.OverrideGlowEffectMessage;
import com.lunarclient.apollo.glow.v1.ResetGlowEffectMessage;
import com.lunarclient.apollo.glow.v1.ResetGlowEffectsMessage;
import com.lunarclient.apollo.heightlimit.v1.OverrideHeightLimitMessage;
import com.lunarclient.apollo.heightlimit.v1.RemoveHeightLimitMessage;
import com.lunarclient.apollo.heightlimit.v1.ResetHeightLimitsMessage;
import com.lunarclient.apollo.hologram.v1.DisplayHologramMessage;
import com.lunarclient.apollo.hologram.v1.RemoveHologramMessage;
import com.lunarclient.apollo.hologram.v1.ResetHologramsMessage;
import com.lunarclient.apollo.hud.v1.HudPosition;
import com.lunarclient.apollo.inventory.v1.DisplayInventoryButtonsMessage;
import com.lunarclient.apollo.inventory.v1.InventoryButton;
import com.lunarclient.apollo.inventory.v1.RemoveInventoryButtonMessage;
import com.lunarclient.apollo.inventory.v1.ResetInventoryButtonsMessage;
import com.lunarclient.apollo.inventory.v1.UpdateInventoryButtonMessage;
import com.lunarclient.apollo.limb.v1.HideArmorPiecesMessage;
import com.lunarclient.apollo.limb.v1.HideBodyPartMessage;
import com.lunarclient.apollo.limb.v1.ResetArmorPiecesMessage;
import com.lunarclient.apollo.limb.v1.ResetBodyPartMessage;
import com.lunarclient.apollo.marker.v1.BlockTarget;
import com.lunarclient.apollo.marker.v1.DangerMarker;
import com.lunarclient.apollo.marker.v1.DisplayMarkerMessage;
import com.lunarclient.apollo.marker.v1.EntityTarget;
import com.lunarclient.apollo.marker.v1.InfoMarker;
import com.lunarclient.apollo.marker.v1.InterestMarker;
import com.lunarclient.apollo.marker.v1.ItemTarget;
import com.lunarclient.apollo.marker.v1.MarkerFlag;
import com.lunarclient.apollo.marker.v1.MarkerStyle;
import com.lunarclient.apollo.marker.v1.MarkerTarget;
import com.lunarclient.apollo.marker.v1.NormalMarker;
import com.lunarclient.apollo.marker.v1.PlayerTarget;
import com.lunarclient.apollo.marker.v1.RemoveMarkerMessage;
import com.lunarclient.apollo.marker.v1.ResetMarkersMessage;
import com.lunarclient.apollo.modsetting.v1.InstalledModsRequest;
import com.lunarclient.apollo.modsetting.v1.InstalledModsResponse;
import com.lunarclient.apollo.modsetting.v1.Mod;
import com.lunarclient.apollo.modsetting.v1.ModGroup;
import com.lunarclient.apollo.nametag.v1.OverrideNametagMessage;
import com.lunarclient.apollo.nametag.v1.ResetNametagMessage;
import com.lunarclient.apollo.nametag.v1.ResetNametagsMessage;
import com.lunarclient.apollo.nickhider.v1.OverrideNickHiderMessage;
import com.lunarclient.apollo.nickhider.v1.ResetNickHiderMessage;
import com.lunarclient.apollo.notification.v1.DisplayNotificationMessage;
import com.lunarclient.apollo.notification.v1.ResetNotificationsMessage;
import com.lunarclient.apollo.packetenrichment.v1.BlockHit;
import com.lunarclient.apollo.packetenrichment.v1.EntityHit;
import com.lunarclient.apollo.packetenrichment.v1.Miss;
import com.lunarclient.apollo.packetenrichment.v1.PacketInfo;
import com.lunarclient.apollo.packetenrichment.v1.PlayerAttackMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerChatCloseMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerChatOpenMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerInfo;
import com.lunarclient.apollo.packetenrichment.v1.PlayerInventoryCloseMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerInventoryOpenMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerUseItemBucketMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerUseItemMessage;
import com.lunarclient.apollo.packetenrichment.v1.RayTraceResult;
import com.lunarclient.apollo.paynow.v1.OpenPayNowEmbeddedCheckoutMessage;
import com.lunarclient.apollo.player.v1.ModMessage;
import com.lunarclient.apollo.player.v1.PlayerHandshakeMessage;
import com.lunarclient.apollo.player.v1.UpdatePlayerWorldMessage;
import com.lunarclient.apollo.richpresence.v1.OverrideServerRichPresenceMessage;
import com.lunarclient.apollo.richpresence.v1.ResetServerRichPresenceMessage;
import com.lunarclient.apollo.serverlink.v1.AddServerLinkMessage;
import com.lunarclient.apollo.serverlink.v1.OverrideServerLinkResourceMessage;
import com.lunarclient.apollo.serverlink.v1.RemoveServerLinkMessage;
import com.lunarclient.apollo.serverlink.v1.ResetServerLinkResourceMessage;
import com.lunarclient.apollo.serverlink.v1.ResetServerLinksMessage;
import com.lunarclient.apollo.serverlink.v1.ServerLink;
import com.lunarclient.apollo.staffmod.v1.DisableStaffModsMessage;
import com.lunarclient.apollo.staffmod.v1.EnableStaffModsMessage;
import com.lunarclient.apollo.stopwatch.v1.AddStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.AddTimerMessage;
import com.lunarclient.apollo.stopwatch.v1.RemoveStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.RemoveTimerMessage;
import com.lunarclient.apollo.stopwatch.v1.ResetStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.ResetStopwatchesMessage;
import com.lunarclient.apollo.stopwatch.v1.ResetTimerMessage;
import com.lunarclient.apollo.stopwatch.v1.ResetTimersMessage;
import com.lunarclient.apollo.stopwatch.v1.StartStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.StartTimerMessage;
import com.lunarclient.apollo.stopwatch.v1.StopStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.StopTimerMessage;
import com.lunarclient.apollo.team.v1.ResetTeamMembersMessage;
import com.lunarclient.apollo.team.v1.TeamMember;
import com.lunarclient.apollo.team.v1.UpdateTeamMembersMessage;
import com.lunarclient.apollo.tebex.v1.OpenTebexEmbeddedCheckoutMessage;
import com.lunarclient.apollo.title.v1.DisplayTitleMessage;
import com.lunarclient.apollo.title.v1.ResetTitlesMessage;
import com.lunarclient.apollo.tntcountdown.v1.SetTntCountdownMessage;
import com.lunarclient.apollo.transfer.v1.PingData;
import com.lunarclient.apollo.transfer.v1.PingRequest;
import com.lunarclient.apollo.transfer.v1.PingResponse;
import com.lunarclient.apollo.transfer.v1.TransferRequest;
import com.lunarclient.apollo.transfer.v1.TransferResponse;
import com.lunarclient.apollo.vignette.v1.DisplayVignetteMessage;
import com.lunarclient.apollo.vignette.v1.ResetVignetteMessage;
import com.lunarclient.apollo.waypoint.v1.DisplayWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.HideWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.RemoveWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.ResetWaypointsMessage;
import com.lunarclient.apollo.waypoint.v1.ShowWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.WaypointTextStyle;

public final class ApolloTypeRegistry {
   public static TypeRegistry method1() {
      return TypeRegistry.newBuilder()
         .add(DisplayBeaconBeamMessage.getDescriptor())
         .add(RemoveBeaconBeamMessage.getDescriptor())
         .add(ResetBeaconBeamsMessage.getDescriptor())
         .add(DisplayBorderMessage.getDescriptor())
         .add(RemoveBorderMessage.getDescriptor())
         .add(ResetBordersMessage.getDescriptor())
         .add(DisplayLiveChatMessageMessage.getDescriptor())
         .add(RemoveLiveChatMessageMessage.getDescriptor())
         .add(ChatButton.getDescriptor())
         .add(DisplayChatButtonsMessage.getDescriptor())
         .add(RemoveChatButtonMessage.getDescriptor())
         .add(ResetChatButtonsMessage.getDescriptor())
         .add(UpdateChatButtonMessage.getDescriptor())
         .add(ResetColoredFireMessage.getDescriptor())
         .add(OverrideColoredFireMessage.getDescriptor())
         .add(ResetColoredFiresMessage.getDescriptor())
         .add(Cuboid2D.getDescriptor())
         .add(Cuboid3D.getDescriptor())
         .add(Location.getDescriptor())
         .add(BlockLocation.getDescriptor())
         .add(PlayerLocation.getDescriptor())
         .add(Uuid.getDescriptor())
         .add(Color.getDescriptor())
         .add(Component.getDescriptor())
         .add(EntityId.getDescriptor())
         .add(MinecraftVersion.getDescriptor())
         .add(LunarClientVersion.getDescriptor())
         .add(Icon.getDescriptor())
         .add(ItemStackIcon.getDescriptor())
         .add(ResourceLocationIcon.getDescriptor())
         .add(SimpleResourceLocationIcon.getDescriptor())
         .add(AdvancedResourceLocationIcon.getDescriptor())
         .add(Sound.getDescriptor())
         .add(SimpleResourceLocationSound.getDescriptor())
         .add(ConfigurableSettings.getDescriptor())
         .add(OverrideConfigurableSettingsMessage.getDescriptor())
         .add(CooldownStyle.getDescriptor())
         .add(DisplayCooldownMessage.getDescriptor())
         .add(RemoveCooldownMessage.getDescriptor())
         .add(ResetCooldownsMessage.getDescriptor())
         .add(Cosmetic.getDescriptor())
         .add(HatOptions.getDescriptor())
         .add(CloakOptions.getDescriptor())
         .add(PetOptions.getDescriptor())
         .add(BodyOptions.getDescriptor())
         .add(EquipNpcCosmeticsMessage.getDescriptor())
         .add(UnequipNpcCosmeticsMessage.getDescriptor())
         .add(ResetNpcCosmeticsMessage.getDescriptor())
         .add(Emote.getDescriptor())
         .add(StartNpcEmoteMessage.getDescriptor())
         .add(StopNpcEmoteMessage.getDescriptor())
         .add(ResetNpcEmotesMessage.getDescriptor())
         .add(DisplaySprayMessage.getDescriptor())
         .add(RemoveSprayMessage.getDescriptor())
         .add(ResetSpraysMessage.getDescriptor())
         .add(FlipEntityMessage.getDescriptor())
         .add(OverrideRainbowSheepMessage.getDescriptor())
         .add(ResetFlipedEntityMessage.getDescriptor())
         .add(ResetRainbowSheepMessage.getDescriptor())
         .add(ResetGlowEffectMessage.getDescriptor())
         .add(OverrideGlowEffectMessage.getDescriptor())
         .add(ResetGlowEffectsMessage.getDescriptor())
         .add(OverrideHeightLimitMessage.getDescriptor())
         .add(RemoveHeightLimitMessage.getDescriptor())
         .add(ResetHeightLimitsMessage.getDescriptor())
         .add(DisplayHologramMessage.getDescriptor())
         .add(RemoveHologramMessage.getDescriptor())
         .add(ResetHologramsMessage.getDescriptor())
         .add(Button.getDescriptor())
         .add(ButtonSize.getDescriptor())
         .add(ButtonContent.getDescriptor())
         .add(ButtonContentPart.getDescriptor())
         .add(ButtonTooltip.getDescriptor())
         .add(ButtonUpdate.getDescriptor())
         .add(DisplayInventoryButtonsMessage.getDescriptor())
         .add(RemoveInventoryButtonMessage.getDescriptor())
         .add(ResetInventoryButtonsMessage.getDescriptor())
         .add(UpdateInventoryButtonMessage.getDescriptor())
         .add(InventoryButton.getDescriptor())
         .add(HudPosition.getDescriptor())
         .add(HideBodyPartMessage.getDescriptor())
         .add(HideArmorPiecesMessage.getDescriptor())
         .add(ResetArmorPiecesMessage.getDescriptor())
         .add(ResetBodyPartMessage.getDescriptor())
         .add(DisplayMarkerMessage.getDescriptor())
         .add(RemoveMarkerMessage.getDescriptor())
         .add(ResetMarkersMessage.getDescriptor())
         .add(MarkerFlag.getDescriptor())
         .add(NormalMarker.getDescriptor())
         .add(DangerMarker.getDescriptor())
         .add(InfoMarker.getDescriptor())
         .add(InterestMarker.getDescriptor())
         .add(MarkerTarget.getDescriptor())
         .add(ItemTarget.getDescriptor())
         .add(BlockTarget.getDescriptor())
         .add(EntityTarget.getDescriptor())
         .add(PlayerTarget.getDescriptor())
         .add(MarkerStyle.getDescriptor())
         .add(InstalledModsRequest.getDescriptor())
         .add(InstalledModsResponse.getDescriptor())
         .add(ModGroup.getDescriptor())
         .add(Mod.getDescriptor())
         .add(OverrideNametagMessage.getDescriptor())
         .add(ResetNametagMessage.getDescriptor())
         .add(ResetNametagsMessage.getDescriptor())
         .add(OverrideNickHiderMessage.getDescriptor())
         .add(ResetNickHiderMessage.getDescriptor())
         .add(DisplayNotificationMessage.getDescriptor())
         .add(ResetNotificationsMessage.getDescriptor())
         .add(PlayerChatOpenMessage.getDescriptor())
         .add(PlayerChatCloseMessage.getDescriptor())
         .add(PlayerInventoryOpenMessage.getDescriptor())
         .add(PlayerInventoryCloseMessage.getDescriptor())
         .add(PlayerAttackMessage.getDescriptor())
         .add(PlayerUseItemMessage.getDescriptor())
         .add(PlayerUseItemBucketMessage.getDescriptor())
         .add(PlayerInfo.getDescriptor())
         .add(PacketInfo.getDescriptor())
         .add(RayTraceResult.getDescriptor())
         .add(EntityHit.getDescriptor())
         .add(BlockHit.getDescriptor())
         .add(Miss.getDescriptor())
         .add(OpenPayNowEmbeddedCheckoutMessage.getDescriptor())
         .add(PlayerHandshakeMessage.getDescriptor())
         .add(UpdatePlayerWorldMessage.getDescriptor())
         .add(ModMessage.getDescriptor())
         .add(OverrideServerRichPresenceMessage.getDescriptor())
         .add(ResetServerRichPresenceMessage.getDescriptor())
         .add(ServerLink.getDescriptor())
         .add(AddServerLinkMessage.getDescriptor())
         .add(RemoveServerLinkMessage.getDescriptor())
         .add(ResetServerLinksMessage.getDescriptor())
         .add(ResetServerLinkResourceMessage.getDescriptor())
         .add(OverrideServerLinkResourceMessage.getDescriptor())
         .add(EnableStaffModsMessage.getDescriptor())
         .add(DisableStaffModsMessage.getDescriptor())
         .add(AddStopwatchMessage.getDescriptor())
         .add(RemoveStopwatchMessage.getDescriptor())
         .add(StartStopwatchMessage.getDescriptor())
         .add(StopStopwatchMessage.getDescriptor())
         .add(ResetStopwatchMessage.getDescriptor())
         .add(ResetStopwatchesMessage.getDescriptor())
         .add(AddTimerMessage.getDescriptor())
         .add(RemoveTimerMessage.getDescriptor())
         .add(StartTimerMessage.getDescriptor())
         .add(StopTimerMessage.getDescriptor())
         .add(ResetTimerMessage.getDescriptor())
         .add(ResetTimersMessage.getDescriptor())
         .add(TeamMember.getDescriptor())
         .add(UpdateTeamMembersMessage.getDescriptor())
         .add(ResetTeamMembersMessage.getDescriptor())
         .add(OpenTebexEmbeddedCheckoutMessage.getDescriptor())
         .add(DisplayTitleMessage.getDescriptor())
         .add(ResetTitlesMessage.getDescriptor())
         .add(SetTntCountdownMessage.getDescriptor())
         .add(PingRequest.getDescriptor())
         .add(PingResponse.getDescriptor())
         .add(PingData.getDescriptor())
         .add(TransferRequest.getDescriptor())
         .add(TransferResponse.getDescriptor())
         .add(DisplayVignetteMessage.getDescriptor())
         .add(ResetVignetteMessage.getDescriptor())
         .add(DisplayWaypointMessage.getDescriptor())
         .add(RemoveWaypointMessage.getDescriptor())
         .add(ResetWaypointsMessage.getDescriptor())
         .add(ShowWaypointMessage.getDescriptor())
         .add(HideWaypointMessage.getDescriptor())
         .add(WaypointTextStyle.getDescriptor())
         .build();
   }

   private ApolloTypeRegistry() {
   }
}
