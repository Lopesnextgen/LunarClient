package com.moonsworth.lunar.client.mod.skyblock.core;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget;
import com.moonsworth.lunar.client.ui.menu.ModMenuWidget;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.Module;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommandKeybinds;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.Lotusfish;
import com.moonsworth.lunar.client.framework.feature.mod.gui.SkyBlockCommandsGui;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.ContainerOverlayPreviewMode;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ActionBarStatsListener;
import com.moonsworth.lunar.client.framework.feature.mod.mixin.WarpCommand;
import com.moonsworth.lunar.client.framework.feature.mod.mixin.SendCoordsCommand;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.TriState;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SoundOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SoundOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.mod.skyblock.damagesplash.SkyblockDamageSplash;
import com.moonsworth.lunar.client.mod.skyblock.tabwidget.SkyBlockTabWidgetHud;
import com.moonsworth.lunar.client.mod.skyblock.anglehud.SkyblockAngleHud;
import com.moonsworth.lunar.client.mod.skyblock.arrowpoisonhud.SkyblockArrowPoisonHud;
import com.moonsworth.lunar.client.mod.skyblock.bosstimer.SkyblockBossTimer;
import com.moonsworth.lunar.client.mod.skyblock.caughtcrittershud.SkyblockCaughtCrittersHud;
import com.moonsworth.lunar.client.mod.skyblock.composterhud.SkyblockComposterHud;
import com.moonsworth.lunar.client.mod.skyblock.croptrackerhud.SkyblockCropTrackerHud;
import com.moonsworth.lunar.client.mod.skyblock.deathcounterhud.SkyblockDeathCounterHud;
import com.moonsworth.lunar.client.mod.skyblock.defhud.SkyblockDefHud;
import com.moonsworth.lunar.client.mod.skyblock.dianamobhud.SkyblockDianaMobHud;
import com.moonsworth.lunar.client.mod.skyblock.dungeonblessinghud.SkyblockDungeonBlessingHud;
import com.moonsworth.lunar.client.mod.skyblock.dungeonmilestonehud.SkyblockDungeonMilestoneHud;
import com.moonsworth.lunar.client.mod.skyblock.dungeontimer.SkyblockDungeonTimer;
import com.moonsworth.lunar.client.mod.skyblock.farminghud.SkyblockFarmingHud;
import com.moonsworth.lunar.client.mod.skyblock.firefreezetimer.SkyblockFireFreezeTimer;
import com.moonsworth.lunar.client.mod.skyblock.fishingbaithud.SkyblockFishingBaitHud;
import com.moonsworth.lunar.client.mod.skyblock.fishinginfohud.SkyblockFishingInfoHud;
import com.moonsworth.lunar.client.mod.skyblock.fishingreeltimerhud.SkyblockFishingReelTimerHud;
import com.moonsworth.lunar.client.mod.skyblock.gianthpatfeet.SkyblockGiantHpAtFeet;
import com.moonsworth.lunar.client.mod.skyblock.harvestfeasttrackerhud.SkyblockHarvestFeastTrackerHud;
import com.moonsworth.lunar.client.mod.skyblock.healthhud.SkyblockHealthHud;
import com.moonsworth.lunar.client.mod.skyblock.hoppityegghud.SkyblockHoppityEggHud;
import com.moonsworth.lunar.client.mod.skyblock.invincibilityhud.SkyblockInvincibilityHud;
import com.moonsworth.lunar.client.mod.skyblock.kuudraarmorstackshud.SkyblockKuudraArmorStacksHud;
import com.moonsworth.lunar.client.mod.skyblock.lividsolverhud.SkyblockLividSolverHud;
import com.moonsworth.lunar.client.mod.skyblock.manahud.SkyblockManaHud;
import com.moonsworth.lunar.client.mod.skyblock.netherbosshud.SkyblockNetherBossHud;
import com.moonsworth.lunar.client.mod.skyblock.pickonimbusdurability.SkyblockPickonimbusDurability;
import com.moonsworth.lunar.client.mod.skyblock.powdertrackerhud.SkyblockPowderTrackerHud;
import com.moonsworth.lunar.client.mod.skyblock.professorguardianhp.SkyblockProfessorGuardianHp;
import com.moonsworth.lunar.client.mod.skyblock.quiverhud.SkyblockQuiverHud;
import com.moonsworth.lunar.client.mod.skyblock.raffletaskshud.SkyblockRaffleTasksHud;
import com.moonsworth.lunar.client.mod.skyblock.revivehud.SkyblockReviveHud;
import com.moonsworth.lunar.client.mod.skyblock.scathatrackerhud.SkyblockScathaTrackerHud;
import com.moonsworth.lunar.client.mod.skyblock.skeletonhelmethud.SkyblockSkeletonHelmetHud;
import com.moonsworth.lunar.client.mod.skyblock.skillglobehud.SkyblockSkillGlobeHud;
import com.moonsworth.lunar.client.mod.skyblock.skillprogressbarhud.SkyblockSkillProgressBarHud;
import com.moonsworth.lunar.client.mod.skyblock.skillprogresshud.SkyblockSkillProgressHud;
import com.moonsworth.lunar.client.mod.skyblock.skillxptrackerhud.SkyblockSkillXpTrackerHud;
import com.moonsworth.lunar.client.mod.skyblock.speedhud.SkyblockSpeedHud;
import com.moonsworth.lunar.client.mod.skyblock.spiderdenrainhud.SkyblockSpiderDenRainHud;
import com.moonsworth.lunar.client.mod.skyblock.stormhud.SkyblockStormHud;
import com.moonsworth.lunar.client.mod.skyblock.terminalstatus.SkyblockTerminalStatus;
import com.moonsworth.lunar.client.mod.skyblock.terracottatimer.SkyblockTerracottaTimer;
import com.moonsworth.lunar.client.mod.skyblock.ticktimerhud.SkyblockTickTimerHud;
import com.moonsworth.lunar.client.mod.skyblock.visitorhud.SkyblockVisitorHud;
import com.moonsworth.lunar.client.mod.skyblock.visitorshoppinglisthud.SkyblockVisitorShoppingListHud;
import com.moonsworth.lunar.client.mod.skyblock.visitortrackerhud.SkyblockVisitorTrackerHud;
import com.moonsworth.lunar.client.mod.skyblock.vitalityhud.SkyblockVitalityHud;
import com.moonsworth.lunar.client.mod.skyblock.whispertrackerhud.SkyblockWhisperTrackerHud;
import com.moonsworth.lunar.client.mod.skyblock.witherhealthhud.SkyblockWitherHealthHud;
import com.moonsworth.lunar.client.mod.skyblock.smoothteleport.SkyblockSmoothTeleport;
import com.moonsworth.lunar.client.mod.skyblock.fishinghideplayers.SkyblockFishingHidePlayers;
import com.moonsworth.lunar.client.mod.skyblock.hidearmor.SkyblockHideArmor;
import com.moonsworth.lunar.client.mod.skyblock.hidechumbuckets.SkyblockHideChumBuckets;
import com.moonsworth.lunar.client.mod.skyblock.hideexcoops.SkyblockHideExCoops;
import com.moonsworth.lunar.client.mod.skyblock.hidefallingblocks.SkyblockHideFallingBlocks;
import com.moonsworth.lunar.client.mod.skyblock.hidefarentities.SkyblockHideFarEntities;
import com.moonsworth.lunar.client.mod.skyblock.hidefood.SkyblockHideFood;
import com.moonsworth.lunar.client.mod.skyblock.hidemagicsoupmessages.SkyblockHideMagicSoupMessages;
import com.moonsworth.lunar.client.mod.skyblock.hidemidasblocks.SkyblockHideMidasBlocks;
import com.moonsworth.lunar.client.mod.skyblock.hideothersgifts.SkyblockHideOthersGifts;
import com.moonsworth.lunar.client.mod.skyblock.hideplayersnearnpc.SkyblockHidePlayersNearNpc;
import com.moonsworth.lunar.client.mod.skyblock.inventorybuttons.SkyblockInventoryButtons;
import com.moonsworth.lunar.client.mod.skyblock.lockmouse.SkyblockLockMouse;
import com.moonsworth.lunar.client.mod.skyblock.wardrobehotkeys.SkyblockWardrobeHotkeys;
import com.moonsworth.lunar.client.mod.skyblock.bettermap.BettermapPrimary;
import com.moonsworth.lunar.client.mod.skyblock.bettermap.BettermapSecondary;
import com.moonsworth.lunar.client.mod.skyblock.dungeonsecretwaypoints.DungeonSecretWaypoints;
import com.moonsworth.lunar.client.mod.skyblock.dungeonwaypoints.DungeonWaypoints;
import com.moonsworth.lunar.client.mod.skyblock.etherwarppreview.EtherwarpPreview;
import com.moonsworth.lunar.client.mod.skyblock.gravitystormpreview.GravityStormPreview;
import com.moonsworth.lunar.client.mod.skyblock.armordyecolor.SkyblockArmorDyeColor;
import com.moonsworth.lunar.client.mod.skyblock.chatwaypoints.SkyblockChatWaypoints;
import com.moonsworth.lunar.client.mod.skyblock.croesuschests.SkyblockCroesusChests;
import com.moonsworth.lunar.client.mod.skyblock.crystalhollowsmap.SkyblockCrystalHollowsMap;
import com.moonsworth.lunar.client.mod.skyblock.dungeoncolors.SkyblockDungeonColors;
import com.moonsworth.lunar.client.mod.skyblock.dungeonhighlightdoors.SkyblockDungeonHighlightDoors;
import com.moonsworth.lunar.client.mod.skyblock.dungeonnametags.SkyblockDungeonNametags;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.mod.skyblock.dungeonteammatehighlight.SkyblockDungeonTeammateHighlight;
import com.moonsworth.lunar.client.mod.skyblock.enchantdisplay.SkyblockEnchantDisplay;
import com.moonsworth.lunar.client.mod.skyblock.endnodehighlight.SkyblockEndNodeHighlight;
import com.moonsworth.lunar.client.mod.skyblock.farmingtoolcropicon.SkyblockFarmingToolCropIcon;
import com.moonsworth.lunar.client.mod.skyblock.fishingcircleoverlay.SkyblockFishingCircleOverlay;
import com.moonsworth.lunar.client.mod.skyblock.fishinghotspotlocator.SkyblockFishingHotspotLocator;
import com.moonsworth.lunar.client.mod.skyblock.fishingmarker.SkyblockFishingMarker;
import com.moonsworth.lunar.client.mod.skyblock.fossilexcavationsolver.SkyblockFossilExcavationSolver;
import com.moonsworth.lunar.client.mod.skyblock.galateamobhighlight.SkyblockGalateaMobHighlight;
import com.moonsworth.lunar.client.mod.skyblock.glowingmushroomhighlight.SkyblockGlowingMushroomHighlight;
import com.moonsworth.lunar.client.mod.skyblock.gravitywellwaypoints.SkyblockGravityWellWaypoints;
import com.moonsworth.lunar.client.mod.skyblock.healingcircleoverlay.SkyblockHealingCircleOverlay;
import com.moonsworth.lunar.client.mod.skyblock.hidelotusfishnametag.SkyblockHideLotusfishNametag;
import com.moonsworth.lunar.client.mod.skyblock.highlightfilledbzorders.SkyblockHighlightFilledBzOrders;
import com.moonsworth.lunar.client.mod.skyblock.highlighttrashdungeonitems.SkyblockHighlightTrashDungeonItems;
import com.moonsworth.lunar.client.mod.skyblock.inactiveeffigydisplay.SkyblockInactiveEffigyDisplay;
import com.moonsworth.lunar.client.mod.skyblock.magebeam.SkyblockMageBeam;
import com.moonsworth.lunar.client.mod.skyblock.metaldetector.SkyblockMetalDetector;
import com.moonsworth.lunar.client.mod.skyblock.mineshaftcorpsewaypoints.SkyblockMineshaftCorpseWaypoints;
import com.moonsworth.lunar.client.mod.skyblock.rainbowbellwaypoints.SkyblockRainbowBellWaypoints;
import com.moonsworth.lunar.client.mod.skyblock.replacelavatexture.SkyblockReplaceLavaTexture;
import com.moonsworth.lunar.client.mod.skyblock.storagehoverpreview.SkyblockStorageHoverPreview;
import com.moonsworth.lunar.client.mod.skyblock.vanillaitemmodels.SkyblockVanillaItemModels;
import com.moonsworth.lunar.client.mod.skyblock.wishingcompass.SkyblockWishingCompass;
import com.moonsworth.lunar.client.mod.skyblock.spiritleapoverlay.SpiritLeapOverlay;
import com.moonsworth.lunar.client.mod.skyblock.starredmobhighlight.StarredMobHighlight;
import com.moonsworth.lunar.client.mod.skyblock.storageoverlay.StorageOverlay;
import com.moonsworth.lunar.client.mod.skyblock.teamcakehighlight.TeamCakeHighlight;
import com.moonsworth.lunar.client.mod.skyblock.tiersasstacksize.TiersAsStackSize;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.skyblock.completedcommissions.SkyblockCompletedCommissions;
import com.moonsworth.lunar.client.mod.skyblock.visitoralert.SkyblockVisitorAlert;
import com.moonsworth.lunar.client.mod.skyblock.visitorbazaarhelper.SkyblockVisitorBazaarHelper;
import com.moonsworth.lunar.client.mod.skyblock.dungeonpuzzles.SkyblockDungeonPuzzles;
import com.moonsworth.lunar.client.mod.skyblock.dungeonquality.SkyblockDungeonQuality;
import com.moonsworth.lunar.client.mod.skyblock.mutebeeheemothsounds.SkyblockMuteBeeheemothSounds;
import com.moonsworth.lunar.client.mod.skyblock.kuudrahealth.SkyblockKuudraHealth;
import com.moonsworth.lunar.client.mod.skyblock.seacreaturealert.SkyblockSeaCreatureAlert;
import com.moonsworth.lunar.client.mod.skyblock.custommenuclick.SkyblockCustomMenuClick;
import com.moonsworth.lunar.client.mod.skyblock.test.TestCustomGuiOverlay;
import com.moonsworth.lunar.client.mod.skyblock.priceinlore.SkyblockPriceInLore;
import com.moonsworth.lunar.client.mod.skyblock.sendcoords.SkyblockSendCoords;
import com.moonsworth.lunar.client.mod.skyblock.autorequeue.SkyblockAutoRequeue;
import com.moonsworth.lunar.client.mod.skyblock.profileviewer.SkyblockProfileViewer;
import com.moonsworth.lunar.client.mod.skyblock.dungeonbreaker.SkyblockDungeonBreaker;
import com.moonsworth.lunar.client.mod.skyblock.experimentsolvers.SkyblockExperimentSolvers;
import com.moonsworth.lunar.client.mod.skyblock.itemidlore.SkyblockItemIdLore;
import com.moonsworth.lunar.client.mod.skyblock.gemstoneprofit.SkyblockGemstoneProfit;
import com.moonsworth.lunar.client.mod.skyblock.kuudra.SkyblockKuudra;
import com.moonsworth.lunar.client.mod.skyblock.test.TestCustomGuiOverride;
import com.moonsworth.lunar.client.mod.skyblock.titaniumspawnalert.SkyblockTitaniumSpawnAlert;
import com.moonsworth.lunar.client.mod.skyblock.middleclickgui.SkyblockMiddleClickGui;
import com.moonsworth.lunar.client.mod.skyblock.gardenpests.SkyblockGardenPests;
import com.moonsworth.lunar.client.mod.skyblock.enchants.SkyblockEnchants;
import com.moonsworth.lunar.client.mod.skyblock.raritybackground.SkyblockRarityBackground;
import com.moonsworth.lunar.client.mod.skyblock.unfinishedterminals.SkyblockUnfinishedTerminals;
import com.moonsworth.lunar.client.mod.skyblock.sphinxhelper.SkyblockSphinxHelper;
import com.moonsworth.lunar.client.mod.skyblock.treasurechesthelper.SkyblockTreasureChestHelper;
import com.moonsworth.lunar.client.mod.skyblock.dungeonbathelper.SkyblockDungeonBatHelper;
import com.moonsworth.lunar.client.mod.skyblock.visitorprofit.SkyblockVisitorProfit;
import com.moonsworth.lunar.client.mod.skyblock.chestprofit.SkyblockChestProfit;
import com.moonsworth.lunar.client.mod.skyblock.autocopyraredrops.SkyblockAutoCopyRareDrops;
import com.moonsworth.lunar.client.mod.skyblock.dnaanalyzersolver.SkyblockDnaAnalyzerSolver;
import com.moonsworth.lunar.client.mod.skyblock.skymallbuffalert.SkyblockSkyMallBuffAlert;
import com.moonsworth.lunar.client.mod.skyblock.minibossalert.SkyblockMiniBossAlert;
import com.moonsworth.lunar.client.mod.skyblock.creationdate.SkyblockCreationDate;
import com.moonsworth.lunar.client.mod.skyblock.chocolatefactory.SkyblockChocolateFactory;
import com.moonsworth.lunar.client.mod.skyblock.trophyfishexchangerate.SkyblockTrophyFishExchangeRate;
import com.moonsworth.lunar.client.mod.skyblock.spiritleapannounce.SkyblockSpiritLeapAnnounce;
import com.moonsworth.lunar.client.mod.skyblock.chestlooted.SkyblockChestLooted;
import com.moonsworth.lunar.client.mod.skyblock.blazeslayer.SkyblockBlazeSlayer;
import com.moonsworth.lunar.client.mod.skyblock.websiteadblock.SkyblockWebsiteAdblock;
import com.moonsworth.lunar.client.mod.skyblock.floorfour.SkyblockFloorFour;
import com.moonsworth.lunar.client.mod.skyblock.terminalsplits.SkyblockTerminalSplits;
import com.moonsworth.lunar.client.mod.skyblock.enhancedminionmenu.SkyblockEnhancedMinionMenu;
import com.moonsworth.lunar.client.mod.skyblock.dungeonbloodcamphelper.SkyblockDungeonBloodCampHelper;
import com.moonsworth.lunar.client.mod.skyblock.protectitem.SkyblockProtectItem;
import com.moonsworth.lunar.client.mod.skyblock.burrowsharing.SkyblockBurrowSharing;
import com.moonsworth.lunar.client.mod.skyblock.divanalltoolsalert.SkyblockDivanAllToolsAlert;
import com.moonsworth.lunar.client.mod.skyblock.fairysouls.SkyblockFairySouls;
import com.moonsworth.lunar.client.mod.skyblock.glacitecommissions.SkyblockGlaciteCommissions;
import com.moonsworth.lunar.client.mod.skyblock.endermanslayer.SkyblockEndermanSlayer;
import com.moonsworth.lunar.client.mod.skyblock.dungeonscorealert.SkyblockDungeonScoreAlert;
import com.moonsworth.lunar.client.mod.skyblock.lilypadhelper.SkyblockLilyPadHelper;
import com.moonsworth.lunar.client.mod.skyblock.dragonfeatures.SkyblockDragonFeatures;
import com.moonsworth.lunar.client.mod.skyblock.lassohelper.SkyblockLassoHelper;
import com.moonsworth.lunar.client.mod.skyblock.vampireslayer.SkyblockVampireSlayer;
import com.moonsworth.lunar.client.mod.skyblock.partyfinder.SkyblockPartyFinder;
import com.moonsworth.lunar.client.mod.skyblock.dungeonfeedback.SkyblockDungeonFeedback;
import com.moonsworth.lunar.client.mod.skyblock.foragingbeaconsolver.SkyblockForagingBeaconSolver;
import com.moonsworth.lunar.client.mod.skyblock.miningspeedboostalert.SkyblockMiningSpeedBoostAlert;
import com.moonsworth.lunar.client.mod.skyblock.burrowlocating.SkyblockBurrowLocating;
import com.moonsworth.lunar.client.mod.skyblock.criticaldungeonteammate.SkyblockCriticalDungeonTeammate;
import com.moonsworth.lunar.client.mod.skyblock.collectedsecret.SkyblockCollectedSecret;
import com.moonsworth.lunar.client.mod.skyblock.stackingenchants.SkyblockStackingEnchants;
import com.moonsworth.lunar.client.mod.skyblock.skymarthelper.SkyblockSkymartHelper;
import com.moonsworth.lunar.client.mod.skyblock.terminalsolvers.SkyblockTerminalSolvers;
import com.moonsworth.lunar.client.mod.skyblock.wormscathaalert.SkyblockWormScathaAlert;
import com.moonsworth.lunar.client.mod.skyblock.bitsshophelper.SkyblockBitsShopHelper;
import com.moonsworth.lunar.client.mod.skyblock.chatcommands.SkyblockChatCommands;
import com.moonsworth.lunar.client.mod.skyblock.seacreaturemessages.SkyblockSeaCreatureMessages;
import com.moonsworth.lunar.client.mod.skyblock.sparklingcritteralert.SkyblockSparklingCritterAlert;
import com.moonsworth.lunar.client.mod.skyblock.calculatorinsigns.SkyblockCalculatorInSigns;

public class Skyblock extends AbstractFeature {
   private final GuiIterator field8 = new GuiIterator();
   private final Module field9 = new Module();
   private final ActionBarStatsListener field10 = (ActionBarStatsListener)this.method63(ActionBarStatsListener.class);
   private final SoundOption field11 = (SoundOption)((Data)OptionFactory.method13("chimeSound")
         .HIIIOHRRROCICIOIORRRIRCRCHHIII("minecraft:entity.experience_orb.pickup"))
      .method31();
   private final FloatOption field12 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "skyblockLineThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(4.0F))
         .method8(1.0F, 10.0F))
      .method31();
   private final ColorOption field13 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skyblockSubjectColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-22016))
      .method31();
   private final ColorOption field14 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skyblockSeparatorColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption field15 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skyblockValueColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5592406))
      .method31();
   private final FloatOption field16 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "skyblockAlertScale"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.2F, 2.5F))
      .method31();
   private final ToggleOption field17 = (ToggleOption)OptionFactory.method7("skyblockHideHearts").method31();
   private final ToggleOption field18 = (ToggleOption)OptionFactory.method7("skyblockHideAbsorb").method31();
   private final ToggleOption field19 = (ToggleOption)OptionFactory.method7("fixLavaBobber").method31();
   private final ToggleOption field20 = (ToggleOption)OptionFactory.method7("onlyMoversOnSkyblock").method31();
   private final ModifierKeybindOption field21 = (ModifierKeybindOption)OptionFactory.method18("skyBlockOpenCommandsUI")
      .method5(KeyCode.KEY_V)
      .method31();
   private final EnumOption<TriState> field22 = (EnumOption<TriState>)OptionFactory.method10(
         "skyblockOverrideEzpz", TriState.DISABLED
      )
      .method31();
   private final EnumOption<ContainerOverlayPreviewMode> field23 = (EnumOption<ContainerOverlayPreviewMode>)OptionFactory.method10(
         "skyblockContainerOverlayPreview", ContainerOverlayPreviewMode.RECENT
      )
      .method31();
   private final ToggleOption field24 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockUseTickTimers").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field25 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockAutocompleteWarps")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field26 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("middleClickArmorFix").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final MultiSelectOption field27 = (MultiSelectOption)((com.moonsworth.lunar.client.config.option.MultiSelectOption.Data)OptionFactory.method27(
            "skyBlockTabWidgetHuds"
         )
         .HIIIOHRRROCICIOIORRRIRCRCHHIII(new LinkedHashSet()))
      .method3(Lotusfish.ids())
      .method6(arg0 -> {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish lotusfish1 = Lotusfish.method1(arg0);
         return lotusfish1 == null ? arg0 : lotusfish1.title();
      })
      .method5(this::method3)
      .method31();
   private final SkyblockHideFood field28 = new SkyblockHideFood(this);
   private final SkyblockHideArmor field29 = new SkyblockHideArmor(this);
   private final SkyblockBossTimer field30 = new SkyblockBossTimer(this);
   private final SkyblockMiniBossAlert field31 = new SkyblockMiniBossAlert(this);
   private final SkyblockInactiveEffigyDisplay field32 = new SkyblockInactiveEffigyDisplay(this);
   private final SkyblockGravityWellWaypoints field33 = new SkyblockGravityWellWaypoints(this);
   private final SkyblockVampireSlayer field34 = new SkyblockVampireSlayer(this);
   private final SkyblockHideFallingBlocks field35 = new SkyblockHideFallingBlocks(this);
   private final SkyblockHideMidasBlocks field36 = new SkyblockHideMidasBlocks(this);
   private final SkyblockReplaceLavaTexture field37 = new SkyblockReplaceLavaTexture(this);
   private final SkyblockAutoCopyRareDrops field38 = new SkyblockAutoCopyRareDrops(this);
   private final SkyblockWishingCompass field39 = new SkyblockWishingCompass(this);
   private final SkyblockGiantHpAtFeet field40 = new SkyblockGiantHpAtFeet(this);
   private final SkyblockProfessorGuardianHp field41 = new SkyblockProfessorGuardianHp(this);
   private final SkyblockKuudraHealth field42 = new SkyblockKuudraHealth(this);
   private final SkyblockBurrowLocating field43 = new SkyblockBurrowLocating(this);
   private final SkyblockBurrowSharing field44 = new SkyblockBurrowSharing(this);
   private final SkyblockDianaMobHud field45 = new SkyblockDianaMobHud(this);
   private final SkyblockSphinxHelper field46 = new SkyblockSphinxHelper(this);
   private final SkyblockPickonimbusDurability field47 = new SkyblockPickonimbusDurability(this);
   private final SkyblockGemstoneProfit field48 = new SkyblockGemstoneProfit(this);
   private final SkyblockMetalDetector field49 = new SkyblockMetalDetector(this);
   private final SkyblockCompletedCommissions field50 = new SkyblockCompletedCommissions(this);
   private final SkyblockCreationDate field51 = new SkyblockCreationDate(this);
   private final SkyblockChatWaypoints field52 = new SkyblockChatWaypoints(this);
   private final SkyblockSendCoords field53 = new SkyblockSendCoords(this);
   private final SkyblockItemIdLore field54 = new SkyblockItemIdLore(this);
   private final SkyblockDungeonFeedback field55 = new SkyblockDungeonFeedback(this);
   private final SkyblockFishingHidePlayers field56 = new SkyblockFishingHidePlayers(this);
   private final SkyblockFishingHotspotLocator field57 = new SkyblockFishingHotspotLocator(this);
   private final SkyblockEndNodeHighlight field58 = new SkyblockEndNodeHighlight(this);
   private final SkyblockGlowingMushroomHighlight field59 = new SkyblockGlowingMushroomHighlight(this);
   private final SkyblockCustomMenuClick field60 = new SkyblockCustomMenuClick(this);
   private final SkyblockDungeonRoutes field61 = new SkyblockDungeonRoutes(this);
   private final SkyblockMiddleClickGui field62 = new SkyblockMiddleClickGui(this);
   private final SkyblockDungeonQuality field63 = new SkyblockDungeonQuality(this);
   private final SkyblockDungeonNametags field64 = new SkyblockDungeonNametags(this);
   private final SkyblockTerminalSplits field65 = new SkyblockTerminalSplits(this);
   private final SkyblockHideExCoops field66 = new SkyblockHideExCoops(this);
   private final SkyblockHideMagicSoupMessages field67 = new SkyblockHideMagicSoupMessages(this);
   private final SkyblockHidePlayersNearNpc field68 = new SkyblockHidePlayersNearNpc(this);
   private final SkyblockHideOthersGifts field69 = new SkyblockHideOthersGifts(this);
   private final SkyblockWebsiteAdblock field70 = new SkyblockWebsiteAdblock(this);
   private final SkyblockHealthHud field71 = new SkyblockHealthHud(this);
   private final SkyblockManaHud field72 = new SkyblockManaHud(this);
   private final SkyblockKuudraArmorStacksHud field73 = new SkyblockKuudraArmorStacksHud(this);
   private final SkyblockDefHud field74 = new SkyblockDefHud(this);
   private final SkyblockSpeedHud field75 = new SkyblockSpeedHud(this);
   private final SkyblockVitalityHud field76 = new SkyblockVitalityHud(this);
   private final SkyblockVisitorHud field77 = new SkyblockVisitorHud(this);
   private final SkyblockNetherBossHud field78 = new SkyblockNetherBossHud(this);
   private final EtherwarpPreview field79 = new EtherwarpPreview(this);
   private final SkyblockLassoHelper field80 = new SkyblockLassoHelper(this);
   private final SkyblockDamageSplash field81 = new SkyblockDamageSplash(this);
   private final SkyblockComposterHud field82 = new SkyblockComposterHud(this);
   private final SkyblockVisitorAlert field83 = new SkyblockVisitorAlert(this);
   private final SpiritLeapOverlay field84 = new SpiritLeapOverlay(this, this.field8);
   private final SkyblockRarityBackground field85 = new SkyblockRarityBackground(this);
   private final SkyblockVanillaItemModels field86 = new SkyblockVanillaItemModels(this);
   private final TiersAsStackSize field87 = new TiersAsStackSize(this);
   private final SkyblockDungeonScoreAlert field88 = new SkyblockDungeonScoreAlert(this);
   private final BettermapPrimary field89 = new BettermapPrimary(this);
   private final BettermapSecondary field90 = new BettermapSecondary(this);
   private final SkyblockCriticalDungeonTeammate field91 = new SkyblockCriticalDungeonTeammate(this);
   private final SkyblockDungeonBatHelper field92 = new SkyblockDungeonBatHelper(this);
   private final SkyblockLividSolverHud field93 = new SkyblockLividSolverHud(this);
   private final SkyblockTerminalSolvers field94 = new SkyblockTerminalSolvers(this);
   private final StarredMobHighlight field95 = new StarredMobHighlight(this);
   private final TeamCakeHighlight field96 = new TeamCakeHighlight(this);
   private final SkyblockRaffleTasksHud field97 = new SkyblockRaffleTasksHud(this);
   private final SkyblockDungeonPuzzles field98 = new SkyblockDungeonPuzzles(this);
   private final SkyblockEndermanSlayer field99 = new SkyblockEndermanSlayer(this);
   private final SkyblockBlazeSlayer field100 = new SkyblockBlazeSlayer(this);
   private final DungeonSecretWaypoints field101 = new DungeonSecretWaypoints(this);
   private final DungeonWaypoints field102 = new DungeonWaypoints(this);
   private final SkyblockLockMouse field103 = new SkyblockLockMouse(this);
   private final SkyblockAngleHud field104 = new SkyblockAngleHud(this);
   private final SkyblockQuiverHud field105 = new SkyblockQuiverHud(this);
   private final SkyblockDungeonHighlightDoors field106 = new SkyblockDungeonHighlightDoors(this);
   private final SkyblockChocolateFactory field107 = new SkyblockChocolateFactory(this);
   private final SkyblockHoppityEggHud field108 = new SkyblockHoppityEggHud(this);
   private final SkyblockExperimentSolvers field109 = new SkyblockExperimentSolvers(this);
   private final SkyblockDragonFeatures field110 = new SkyblockDragonFeatures(this);
   private final SkyblockPowderTrackerHud field111 = new SkyblockPowderTrackerHud(this);
   private final SkyblockWhisperTrackerHud field112 = new SkyblockWhisperTrackerHud(this);
   private final SkyblockGlaciteCommissions field113 = new SkyblockGlaciteCommissions(this);
   private final SkyblockTitaniumSpawnAlert field114 = new SkyblockTitaniumSpawnAlert(this);
   private final SkyblockFossilExcavationSolver field115 = new SkyblockFossilExcavationSolver(this);
   private final SkyblockMineshaftCorpseWaypoints field116 = new SkyblockMineshaftCorpseWaypoints(this);
   private final SkyblockDivanAllToolsAlert field117 = new SkyblockDivanAllToolsAlert(this);
   private final SkyblockTreasureChestHelper field118 = new SkyblockTreasureChestHelper(this);
   private final SkyblockMiningSpeedBoostAlert field119 = new SkyblockMiningSpeedBoostAlert(this);
   private final SkyblockSkyMallBuffAlert field120 = new SkyblockSkyMallBuffAlert(this);
   private final SkyblockCrystalHollowsMap field121 = new SkyblockCrystalHollowsMap(this);
   private final SkyblockDungeonBloodCampHelper field122 = new SkyblockDungeonBloodCampHelper(this);
   private final SkyblockKuudra field123 = new SkyblockKuudra(this);
   private final SkyblockFarmingHud field124 = new SkyblockFarmingHud(this);
   private final SkyblockVisitorTrackerHud field125 = new SkyblockVisitorTrackerHud(this);
   private final SkyblockVisitorShoppingListHud field126 = new SkyblockVisitorShoppingListHud(this);
   private final SkyblockCropTrackerHud field127 = new SkyblockCropTrackerHud(this);
   private final SkyblockGardenPests field128 = new SkyblockGardenPests(this);
   private final SkyblockDnaAnalyzerSolver field129 = new SkyblockDnaAnalyzerSolver(this);
   private final GravityStormPreview field130 = new GravityStormPreview(this);
   private final SkyblockFloorFour field131 = new SkyblockFloorFour(this);
   private final SkyblockCroesusChests field132 = new SkyblockCroesusChests(this);
   private final SkyblockDungeonTimer field133 = new SkyblockDungeonTimer(this);
   private final SkyblockSeaCreatureAlert field134 = new SkyblockSeaCreatureAlert(this);
   private final SkyblockFishingMarker field135 = new SkyblockFishingMarker(this);
   private final SkyblockDungeonTeammateHighlight field136 = new SkyblockDungeonTeammateHighlight(this);
   private final SkyblockFireFreezeTimer field137 = new SkyblockFireFreezeTimer(this);
   private final SkyblockSkeletonHelmetHud field138 = new SkyblockSkeletonHelmetHud(this);
   private final SkyblockArrowPoisonHud field139 = new SkyblockArrowPoisonHud(this);
   private final SkyblockSpiderDenRainHud field140 = new SkyblockSpiderDenRainHud(this);
   private final SkyblockHighlightTrashDungeonItems field141 = new SkyblockHighlightTrashDungeonItems(this);
   private final SkyblockHealingCircleOverlay field142 = new SkyblockHealingCircleOverlay(this);
   private final SkyblockDungeonMilestoneHud field143 = new SkyblockDungeonMilestoneHud(this);
   private final SkyblockDungeonBlessingHud field144 = new SkyblockDungeonBlessingHud(this);
   private final SkyblockPartyFinder field145 = new SkyblockPartyFinder(this);
   private final SkyblockDungeonColors field146 = new SkyblockDungeonColors(this);
   private final SkyblockUnfinishedTerminals field147 = new SkyblockUnfinishedTerminals(this);
   private final SkyblockCollectedSecret field148 = new SkyblockCollectedSecret(this);
   private final SkyblockInvincibilityHud field149 = new SkyblockInvincibilityHud(this);
   private final SkyblockTerracottaTimer field150 = new SkyblockTerracottaTimer(this);
   private final SkyblockTickTimerHud field151 = new SkyblockTickTimerHud(this);
   private final SkyblockTerminalStatus field152 = new SkyblockTerminalStatus(this);
   private final SkyblockGalateaMobHighlight field153 = new SkyblockGalateaMobHighlight(this);
   private final SkyblockInventoryButtons field154 = new SkyblockInventoryButtons(this);
   private final SkyblockWormScathaAlert field155 = new SkyblockWormScathaAlert(this);
   private final SkyblockScathaTrackerHud field156 = new SkyblockScathaTrackerHud(this);
   private final SkyblockChestLooted field157 = new SkyblockChestLooted(this);
   private final SkyblockAutoRequeue field158 = new SkyblockAutoRequeue(this);
   private final SkyblockPriceInLore field159 = new SkyblockPriceInLore(this);
   private final SkyblockDeathCounterHud field160 = new SkyblockDeathCounterHud(this);
   private final SkyblockReviveHud field161 = new SkyblockReviveHud(this);
   private final SkyblockDungeonBreaker field162 = new SkyblockDungeonBreaker(this);
   private final SkyblockEnchants field163 = new SkyblockEnchants(this);
   private final SkyblockChestProfit field164 = new SkyblockChestProfit(this);
   private final SkyblockBitsShopHelper field165 = new SkyblockBitsShopHelper(this);
   private final SkyblockSkymartHelper field166 = new SkyblockSkymartHelper(this);
   private final SkyblockVisitorProfit field167 = new SkyblockVisitorProfit(this);
   private final SkyblockVisitorBazaarHelper field168 = new SkyblockVisitorBazaarHelper(this);
   private final SkyblockWardrobeHotkeys field169 = new SkyblockWardrobeHotkeys(this);
   private final SkyblockArmorDyeColor field170 = new SkyblockArmorDyeColor(this);
   private final SkyblockHideFarEntities field171 = new SkyblockHideFarEntities(this);
   private final SkyblockProtectItem field172 = new SkyblockProtectItem(this);
   private final TestCustomGuiOverride field173 = new TestCustomGuiOverride(this);
   private final TestCustomGuiOverlay field174 = new TestCustomGuiOverlay(this);
   private final StorageOverlay field175 = new StorageOverlay(this);
   private final SkyblockChatCommands field176 = new SkyblockChatCommands(this);
   private final SkyblockSpiritLeapAnnounce field177 = new SkyblockSpiritLeapAnnounce(this);
   private final SkyblockSkillXpTrackerHud field178 = new SkyblockSkillXpTrackerHud(this);
   private final SkyblockSkillProgressHud field179 = new SkyblockSkillProgressHud(this);
   private final SkyblockSkillGlobeHud field180 = new SkyblockSkillGlobeHud(this);
   private final SkyblockWitherHealthHud field181 = new SkyblockWitherHealthHud(this);
   private final SkyblockHighlightFilledBzOrders field182 = new SkyblockHighlightFilledBzOrders(this);
   private final SkyblockCalculatorInSigns field183 = new SkyblockCalculatorInSigns(this);
   private final SkyblockSkillProgressBarHud field184 = new SkyblockSkillProgressBarHud(this);
   private final SkyblockMageBeam field185 = new SkyblockMageBeam(this);
   private final SkyblockHarvestFeastTrackerHud field186 = new SkyblockHarvestFeastTrackerHud(this);
   private final SkyblockFarmingToolCropIcon field187 = new SkyblockFarmingToolCropIcon(this);
   private final SkyblockStackingEnchants field188 = new SkyblockStackingEnchants(this);
   private final SkyblockHideChumBuckets field189 = new SkyblockHideChumBuckets(this);
   private final SkyblockTrophyFishExchangeRate field190 = new SkyblockTrophyFishExchangeRate(this);
   private final SkyblockLilyPadHelper field191 = new SkyblockLilyPadHelper(this);
   private final SkyblockHideLotusfishNametag field192 = new SkyblockHideLotusfishNametag(this);
   private final SkyblockFishingCircleOverlay field193 = new SkyblockFishingCircleOverlay(this);
   private final SkyblockFishingReelTimerHud field194 = new SkyblockFishingReelTimerHud(this);
   private final SkyblockFishingInfoHud field195 = new SkyblockFishingInfoHud(this);
   private final SkyblockSeaCreatureMessages field196 = new SkyblockSeaCreatureMessages(this);
   private final SkyblockFishingBaitHud field197 = new SkyblockFishingBaitHud(this);
   private final SkyblockFairySouls field198 = new SkyblockFairySouls(this);
   private final Map<com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish, SkyBlockTabWidgetHud> field199 = new HashMap<>();
   private final SkyblockProfileViewer field200 = new SkyblockProfileViewer(this);
   private final SkyblockStormHud field201 = new SkyblockStormHud(this);
   private final SkyblockEnchantDisplay field202 = new SkyblockEnchantDisplay(this);
   private final SkyblockStorageHoverPreview field203 = new SkyblockStorageHoverPreview(this);
   private final SkyblockEnhancedMinionMenu field204 = new SkyblockEnhancedMinionMenu(this);
   private final SkyblockSmoothTeleport field205 = new SkyblockSmoothTeleport(this);
   private final SkyblockForagingBeaconSolver field206 = new SkyblockForagingBeaconSolver(this);
   private final SkyblockMuteBeeheemothSounds field207 = new SkyblockMuteBeeheemothSounds(this);
   private final SkyblockRainbowBellWaypoints field208 = new SkyblockRainbowBellWaypoints(this);
   private final SkyblockCaughtCrittersHud field209 = new SkyblockCaughtCrittersHud(this);
   private final SkyblockSparklingCritterAlert field210 = new SkyblockSparklingCritterAlert(this);
   private final SkyBlockCommandKeybinds field211 = new SkyBlockCommandKeybinds();
   private final Set<HudTimer> field212 = Collections.newSetFromMap(new WeakHashMap<>());

   public Skyblock() {
      super(false);
      this.method218(ModTraits.field18, arg0 -> arg0.RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(33));
      this.field8.method3("locations", Arrays.stream(SkyblockIsland.values()).map(arg0 -> {
         JsonObject json1 = new JsonObject();
         json1.addProperty("id", arg0.name());
         json1.addProperty("name", arg0.getMapValue());
         json1.addProperty("mode", arg0.getMode());
         return json1;
      }).collect(JsonArray::new, JsonArray::add, JsonArray::addAll));
      this.method226(new SendCoordsCommand());
      this.method226(new WarpCommand());
      this.handle(EventTick.class, arg0 -> ChatMessageQueue.method2());
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.GameDataLoadEvent.class, arg1 -> this.field211.method3());
      this.field24.CICORRHIOIIOORRRICCORIOIOCIHII(arg1 -> this.field212.forEach(arg1x -> arg1x.method4(arg1)));
   }

   public void method3(boolean flag1) {
      if (flag1) {
         if (!this.field9.method8()) {
            new Thread(this.field9::method1).start();
         }

         SkyblockItemRegistry.onEnable();
      }
   }

   public String getId() {
      return "SKYBLOCK";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field21, this.field16})
      );
      lightingextension231.method7(SettingsPage.INVENTORY, arg1x -> arg1x.method9(new ClientOption[]{this.field26}));
      lightingextension231.method7(SettingsPage.CHAT, arg1x -> arg1x.method9(new ClientOption[]{this.field25}));
      lightingextension231.method7(
         SettingsPage.HUD,
         arg1x -> arg1x.method9(
            new ClientOption[]{this.field17, this.field18, this.field20, this.field23, this.field13, this.field14, this.field15}
         )
      );
      lightingextension231.method5(SettingsPage.ITEMS);
      lightingextension231.method5(SettingsPage.EVENT);
      lightingextension231.method7(SettingsPage.TAB_WIDGETS, arg1x -> arg1x.method9(new ClientOption[]{this.field27}));
      lightingextension231.method5(SettingsPage.FARMING);
      lightingextension231.method5(SettingsPage.FORAGING);
      lightingextension231.method5(SettingsPage.MINING);
      lightingextension231.method7(SettingsPage.FISHING, arg1x -> arg1x.method9(new ClientOption[]{this.field19}));
      lightingextension231.method5(SettingsPage.SLAYER);
      lightingextension231.method5(SettingsPage.CRIMSON_ISLE);
      lightingextension231.method5(SettingsPage.END);
      lightingextension231.method5(SettingsPage.SPIDERS_DEN);
      lightingextension231.method5(SettingsPage.LOTUS_ATOLL);
      lightingextension231.method7(SettingsPage.DUNGEONS, arg1x -> arg1x.method9(new ClientOption[]{this.field22}));
      lightingextension231.method7(SettingsPage.TIMERS, arg1x -> arg1x.method9(new ClientOption[]{this.field24}));
      lightingextension231.method5(SettingsPage.SKILLS);
      lightingextension231.method7(SettingsPage.RENDER, arg1x -> arg1x.method9(new ClientOption[]{this.field12}));
      lightingextension231.method7(SettingsPage.OTHER, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field24});
         arg1x.method9(new ClientOption[]{this.field11});
      });
      this.field21.method5(arg1x -> {
         if (this.mc.bridge$getCurrentScreen() == null) {
            if (this.field211.method8()) {
               this.mc.bridge$displayScreen(Bridge.method8().method18(new SkyBlockCommandsGui()));
            }
         }
      });
      this.field27.CICORRHIOIIOORRRICCORIOIOCIHII(this::method4);
   }

   private void method3(String text1) {
      com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish lotusfish2 = Lotusfish.method1(text1);
      boolean flag3 = this.field27.contains(text1);
      if (flag3) {
         if (lotusfish2 != null) {
            SkyBlockTabWidgetHud skyblocktabwidgethud4 = SkyBlockTabWidgetHud.method1(this, lotusfish2);
            this.field199.put(lotusfish2, skyblocktabwidgethud4);
            this.method11(skyblocktabwidgethud4);
         }
      } else {
         this.method12((Framework7Extension)this.field199.remove(lotusfish2));
      }

      LcuiScreen.method145();
      LcuiScreen.method134().ifPresent(arg0 -> {
         ModMenuWidget calculator2iterator61x = arg0.method10();
         FeatureSettingsWidget calculator2iterator32x = (FeatureSettingsWidget)calculator2iterator61x.method17();
         FeatureSettingsWidget calculator2iterator33x = new FeatureSettingsWidget(null, Ref.method4().method40().method82());
         calculator2iterator61x.method2(calculator2iterator33x);
         calculator2iterator33x.method18().method14(calculator2iterator32x.method18().method3());
      });
   }

   private void method4(Set<String> set1) {
      this.method13();

      for (String text3 : set1) {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish lotusfish4 = Lotusfish.method1(text3);
         if (lotusfish4 != null) {
            SkyBlockTabWidgetHud skyblocktabwidgethud5 = SkyBlockTabWidgetHud.method1(this, lotusfish4);
            this.field199.put(lotusfish4, skyblocktabwidgethud5);
            this.method11(skyblocktabwidgethud5);
         }
      }
   }

   public boolean method8() {
      return !Ref.field1;
   }

   public void method4() {
      this.method13();
   }

   private void method13() {
      this.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field5).ifPresent(arg1 -> this.field199.values().forEach(arg1::method3));
      this.field199.clear();
      LcuiScreen.method145();
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field3}).method11(this);
   }

   protected List<Framework7Extension> method9() {
      Builder builder1 = ImmutableList.builder();
      builder1.add(
         new Framework7Extension[]{
            this.field200,
            this.field71,
            this.field72,
            this.field73,
            this.field74,
            this.field75,
            this.field76,
            this.field77,
            this.field78,
            this.field79,
            this.field80,
            this.field81,
            this.field82,
            this.field84,
            this.field83,
            this.field85,
            this.field86,
            this.field88,
            this.field87,
            this.field91,
            this.field92,
            this.field93,
            this.field94,
            this.field99,
            this.field100,
            this.field105,
            this.field101,
            this.field102,
            this.field103,
            this.field104,
            this.field95,
            this.field98,
            this.field106,
            this.field108,
            this.field107,
            this.field109,
            this.field110,
            this.field130,
            this.field131,
            this.field132,
            this.field133,
            this.field111,
            this.field112,
            this.field113,
            this.field114,
            this.field115,
            this.field116,
            this.field117,
            this.field118,
            this.field119,
            this.field120,
            this.field121,
            this.field122,
            this.field123,
            this.field124,
            this.field125,
            this.field126,
            this.field127,
            this.field128,
            this.field129,
            this.field134,
            this.field135,
            this.field136,
            this.field137,
            this.field138,
            this.field139,
            this.field140,
            this.field141,
            this.field142,
            this.field143,
            this.field144,
            this.field145,
            this.field146,
            this.field147,
            this.field152,
            this.field151,
            this.field150,
            this.field149,
            this.field148,
            this.field153,
            this.field154,
            this.field155,
            this.field156,
            this.field157,
            this.field158,
            this.field162,
            this.field159,
            this.field160,
            this.field161,
            this.field164,
            this.field163,
            this.field44,
            this.field165,
            this.field166,
            this.field167,
            this.field168,
            this.field169,
            this.field170,
            this.field45,
            this.field46,
            this.field96,
            this.field97,
            this.field171,
            this.field175,
            this.field176,
            this.field177,
            this.field178,
            this.field179,
            this.field180,
            this.field181,
            this.field201,
            this.field202,
            this.field182,
            this.field183,
            this.field184,
            this.field185,
            this.field29,
            this.field28,
            this.field172,
            this.field186,
            this.field187,
            this.field47,
            this.field48,
            this.field188,
            this.field189,
            this.field190,
            this.field191,
            this.field192,
            this.field193,
            this.field194,
            this.field195,
            this.field196,
            this.field197,
            this.field30,
            this.field31,
            this.field32,
            this.field33,
            this.field34,
            this.field35,
            this.field36,
            this.field37,
            this.field38,
            this.field39,
            this.field40,
            this.field41,
            this.field42,
            this.field43,
            this.field49,
            this.field50,
            this.field62,
            this.field63,
            this.field51,
            this.field52,
            this.field53,
            this.field54,
            this.field55,
            this.field56,
            this.field57,
            this.field58,
            this.field59,
            this.field61,
            this.field64,
            this.field65,
            this.field66,
            this.field67,
            this.field68,
            this.field69,
            this.field70,
            this.field203,
            this.field204,
            this.field205,
            this.field198,
            this.field206,
            this.field207,
            this.field208,
            this.field209,
            this.field210
         }
      );
      if (this.field89 != null && this.field90 != null) {
         builder1.add(new Framework7Extension[]{this.field89, this.field90});
      }

      if (!LunarBuildData.field4) {
         builder1.add(this.field173);
         builder1.add(this.field174);
      }

      return new ArrayList<>(builder1.build());
   }

   public void method1(JsonObject json1) {
      super.method1(json1);
      this.field211.method13(json1);
   }

   public void load(JsonObject json1) {
      super.load(json1);
      this.field211.load(json1);
   }

   private void method11(Framework7Extension framework7extension1) {
      ((ModChildren)this.method226(ModTraits.field5, arg0 -> ModChildren.method3())).HICCRORORCRIHCORCCIORIOROORIHR(framework7extension1);
   }

   private void method12(Framework7Extension framework7extension1) {
      this.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field5).ifPresent(arg1x -> arg1x.HRORICORIHHHRICRIRCIIOHCRIRRHI(framework7extension1));
   }

   @Generated
   public GuiIterator method14() {
      return this.field8;
   }

   @Generated
   public Module method15() {
      return this.field9;
   }

   @Generated
   public ActionBarStatsListener method16() {
      return this.field10;
   }

   @Generated
   public SoundOption method17() {
      return this.field11;
   }

   @Generated
   public FloatOption method19() {
      return this.field12;
   }

   @Generated
   public ColorOption method21() {
      return this.field13;
   }

   @Generated
   public ColorOption method22() {
      return this.field14;
   }

   @Generated
   public ColorOption method23() {
      return this.field15;
   }

   @Generated
   public FloatOption method24() {
      return this.field16;
   }

   @Generated
   public ToggleOption method25() {
      return this.field17;
   }

   @Generated
   public ToggleOption method26() {
      return this.field18;
   }

   @Generated
   public ToggleOption method27() {
      return this.field19;
   }

   @Generated
   public ToggleOption method28() {
      return this.field20;
   }

   @Generated
   public ModifierKeybindOption method29() {
      return this.field21;
   }

   @Generated
   public EnumOption<TriState> method30() {
      return this.field22;
   }

   @Generated
   public EnumOption<ContainerOverlayPreviewMode> method34() {
      return this.field23;
   }

   @Generated
   public ToggleOption method35() {
      return this.field24;
   }

   @Generated
   public ToggleOption method36() {
      return this.field25;
   }

   @Generated
   public ToggleOption method37() {
      return this.field26;
   }

   @Generated
   public MultiSelectOption method38() {
      return this.field27;
   }

   @Generated
   public SkyblockHideFood method39() {
      return this.field28;
   }

   @Generated
   public SkyblockHideArmor method40() {
      return this.field29;
   }

   @Generated
   public SkyblockBossTimer method41() {
      return this.field30;
   }

   @Generated
   public SkyblockMiniBossAlert method42() {
      return this.field31;
   }

   @Generated
   public SkyblockInactiveEffigyDisplay method43() {
      return this.field32;
   }

   @Generated
   public SkyblockGravityWellWaypoints method44() {
      return this.field33;
   }

   @Generated
   public SkyblockVampireSlayer method45() {
      return this.field34;
   }

   @Generated
   public SkyblockHideFallingBlocks method46() {
      return this.field35;
   }

   @Generated
   public SkyblockHideMidasBlocks method47() {
      return this.field36;
   }

   @Generated
   public SkyblockReplaceLavaTexture method48() {
      return this.field37;
   }

   @Generated
   public SkyblockAutoCopyRareDrops method49() {
      return this.field38;
   }

   @Generated
   public SkyblockWishingCompass method50() {
      return this.field39;
   }

   @Generated
   public SkyblockGiantHpAtFeet method51() {
      return this.field40;
   }

   @Generated
   public SkyblockProfessorGuardianHp method52() {
      return this.field41;
   }

   @Generated
   public SkyblockKuudraHealth method53() {
      return this.field42;
   }

   @Generated
   public SkyblockBurrowLocating method54() {
      return this.field43;
   }

   @Generated
   public SkyblockBurrowSharing method55() {
      return this.field44;
   }

   @Generated
   public SkyblockDianaMobHud method56() {
      return this.field45;
   }

   @Generated
   public SkyblockSphinxHelper method57() {
      return this.field46;
   }

   @Generated
   public SkyblockPickonimbusDurability method58() {
      return this.field47;
   }

   @Generated
   public SkyblockGemstoneProfit method59() {
      return this.field48;
   }

   @Generated
   public SkyblockMetalDetector method60() {
      return this.field49;
   }

   @Generated
   public SkyblockCompletedCommissions method61() {
      return this.field50;
   }

   @Generated
   public SkyblockCreationDate method62() {
      return this.field51;
   }

   @Generated
   public SkyblockChatWaypoints method63() {
      return this.field52;
   }

   @Generated
   public SkyblockSendCoords method64() {
      return this.field53;
   }

   @Generated
   public SkyblockItemIdLore method65() {
      return this.field54;
   }

   @Generated
   public SkyblockDungeonFeedback method66() {
      return this.field55;
   }

   @Generated
   public SkyblockFishingHidePlayers method67() {
      return this.field56;
   }

   @Generated
   public SkyblockFishingHotspotLocator method68() {
      return this.field57;
   }

   @Generated
   public SkyblockEndNodeHighlight method69() {
      return this.field58;
   }

   @Generated
   public SkyblockGlowingMushroomHighlight method70() {
      return this.field59;
   }

   @Generated
   public SkyblockCustomMenuClick method71() {
      return this.field60;
   }

   @Generated
   public SkyblockDungeonRoutes method72() {
      return this.field61;
   }

   @Generated
   public SkyblockMiddleClickGui method73() {
      return this.field62;
   }

   @Generated
   public SkyblockDungeonQuality method74() {
      return this.field63;
   }

   @Generated
   public SkyblockDungeonNametags method75() {
      return this.field64;
   }

   @Generated
   public SkyblockTerminalSplits method76() {
      return this.field65;
   }

   @Generated
   public SkyblockHideExCoops method77() {
      return this.field66;
   }

   @Generated
   public SkyblockHideMagicSoupMessages method78() {
      return this.field67;
   }

   @Generated
   public SkyblockHidePlayersNearNpc method79() {
      return this.field68;
   }

   @Generated
   public SkyblockHideOthersGifts method80() {
      return this.field69;
   }

   @Generated
   public SkyblockWebsiteAdblock method81() {
      return this.field70;
   }

   @Generated
   public SkyblockHealthHud method82() {
      return this.field71;
   }

   @Generated
   public SkyblockManaHud method83() {
      return this.field72;
   }

   @Generated
   public SkyblockKuudraArmorStacksHud method84() {
      return this.field73;
   }

   @Generated
   public SkyblockDefHud method85() {
      return this.field74;
   }

   @Generated
   public SkyblockSpeedHud method86() {
      return this.field75;
   }

   @Generated
   public SkyblockVitalityHud method87() {
      return this.field76;
   }

   @Generated
   public SkyblockVisitorHud method88() {
      return this.field77;
   }

   @Generated
   public SkyblockNetherBossHud method89() {
      return this.field78;
   }

   @Generated
   public EtherwarpPreview method90() {
      return this.field79;
   }

   @Generated
   public SkyblockLassoHelper method91() {
      return this.field80;
   }

   @Generated
   public SkyblockDamageSplash method92() {
      return this.field81;
   }

   @Generated
   public SkyblockComposterHud method93() {
      return this.field82;
   }

   @Generated
   public SkyblockVisitorAlert method94() {
      return this.field83;
   }

   @Generated
   public SpiritLeapOverlay method95() {
      return this.field84;
   }

   @Generated
   public SkyblockRarityBackground method96() {
      return this.field85;
   }

   @Generated
   public SkyblockVanillaItemModels method97() {
      return this.field86;
   }

   @Generated
   public TiersAsStackSize method98() {
      return this.field87;
   }

   @Generated
   public SkyblockDungeonScoreAlert method99() {
      return this.field88;
   }

   @Generated
   public BettermapPrimary method100() {
      return this.field89;
   }

   @Generated
   public BettermapSecondary method101() {
      return this.field90;
   }

   @Generated
   public SkyblockCriticalDungeonTeammate method102() {
      return this.field91;
   }

   @Generated
   public SkyblockDungeonBatHelper method103() {
      return this.field92;
   }

   @Generated
   public SkyblockLividSolverHud method104() {
      return this.field93;
   }

   @Generated
   public SkyblockTerminalSolvers method105() {
      return this.field94;
   }

   @Generated
   public StarredMobHighlight method106() {
      return this.field95;
   }

   @Generated
   public TeamCakeHighlight method107() {
      return this.field96;
   }

   @Generated
   public SkyblockRaffleTasksHud method108() {
      return this.field97;
   }

   @Generated
   public SkyblockDungeonPuzzles method109() {
      return this.field98;
   }

   @Generated
   public SkyblockEndermanSlayer method110() {
      return this.field99;
   }

   @Generated
   public SkyblockBlazeSlayer method111() {
      return this.field100;
   }

   @Generated
   public DungeonSecretWaypoints method112() {
      return this.field101;
   }

   @Generated
   public DungeonWaypoints method113() {
      return this.field102;
   }

   @Generated
   public SkyblockLockMouse method114() {
      return this.field103;
   }

   @Generated
   public SkyblockAngleHud method115() {
      return this.field104;
   }

   @Generated
   public SkyblockQuiverHud method116() {
      return this.field105;
   }

   @Generated
   public SkyblockDungeonHighlightDoors method117() {
      return this.field106;
   }

   @Generated
   public SkyblockChocolateFactory method118() {
      return this.field107;
   }

   @Generated
   public SkyblockHoppityEggHud method119() {
      return this.field108;
   }

   @Generated
   public SkyblockExperimentSolvers method120() {
      return this.field109;
   }

   @Generated
   public SkyblockDragonFeatures method121() {
      return this.field110;
   }

   @Generated
   public SkyblockPowderTrackerHud method122() {
      return this.field111;
   }

   @Generated
   public SkyblockWhisperTrackerHud method123() {
      return this.field112;
   }

   @Generated
   public SkyblockGlaciteCommissions method124() {
      return this.field113;
   }

   @Generated
   public SkyblockTitaniumSpawnAlert method125() {
      return this.field114;
   }

   @Generated
   public SkyblockFossilExcavationSolver method126() {
      return this.field115;
   }

   @Generated
   public SkyblockMineshaftCorpseWaypoints method127() {
      return this.field116;
   }

   @Generated
   public SkyblockDivanAllToolsAlert method128() {
      return this.field117;
   }

   @Generated
   public SkyblockTreasureChestHelper method129() {
      return this.field118;
   }

   @Generated
   public SkyblockMiningSpeedBoostAlert method130() {
      return this.field119;
   }

   @Generated
   public SkyblockSkyMallBuffAlert method131() {
      return this.field120;
   }

   @Generated
   public SkyblockCrystalHollowsMap method132() {
      return this.field121;
   }

   @Generated
   public SkyblockDungeonBloodCampHelper method133() {
      return this.field122;
   }

   @Generated
   public SkyblockKuudra method134() {
      return this.field123;
   }

   @Generated
   public SkyblockFarmingHud method135() {
      return this.field124;
   }

   @Generated
   public SkyblockVisitorTrackerHud method136() {
      return this.field125;
   }

   @Generated
   public SkyblockVisitorShoppingListHud method137() {
      return this.field126;
   }

   @Generated
   public SkyblockCropTrackerHud method138() {
      return this.field127;
   }

   @Generated
   public SkyblockGardenPests method139() {
      return this.field128;
   }

   @Generated
   public SkyblockDnaAnalyzerSolver method140() {
      return this.field129;
   }

   @Generated
   public GravityStormPreview method141() {
      return this.field130;
   }

   @Generated
   public SkyblockFloorFour method142() {
      return this.field131;
   }

   @Generated
   public SkyblockCroesusChests method143() {
      return this.field132;
   }

   @Generated
   public SkyblockDungeonTimer method144() {
      return this.field133;
   }

   @Generated
   public SkyblockSeaCreatureAlert method145() {
      return this.field134;
   }

   @Generated
   public SkyblockFishingMarker method146() {
      return this.field135;
   }

   @Generated
   public SkyblockDungeonTeammateHighlight method147() {
      return this.field136;
   }

   @Generated
   public SkyblockFireFreezeTimer method148() {
      return this.field137;
   }

   @Generated
   public SkyblockSkeletonHelmetHud method149() {
      return this.field138;
   }

   @Generated
   public SkyblockArrowPoisonHud method150() {
      return this.field139;
   }

   @Generated
   public SkyblockSpiderDenRainHud method151() {
      return this.field140;
   }

   @Generated
   public SkyblockHighlightTrashDungeonItems method152() {
      return this.field141;
   }

   @Generated
   public SkyblockHealingCircleOverlay method153() {
      return this.field142;
   }

   @Generated
   public SkyblockDungeonMilestoneHud method154() {
      return this.field143;
   }

   @Generated
   public SkyblockDungeonBlessingHud method155() {
      return this.field144;
   }

   @Generated
   public SkyblockPartyFinder method156() {
      return this.field145;
   }

   @Generated
   public SkyblockDungeonColors method157() {
      return this.field146;
   }

   @Generated
   public SkyblockUnfinishedTerminals method158() {
      return this.field147;
   }

   @Generated
   public SkyblockCollectedSecret method159() {
      return this.field148;
   }

   @Generated
   public SkyblockInvincibilityHud method160() {
      return this.field149;
   }

   @Generated
   public SkyblockTerracottaTimer method161() {
      return this.field150;
   }

   @Generated
   public SkyblockTickTimerHud method162() {
      return this.field151;
   }

   @Generated
   public SkyblockTerminalStatus method163() {
      return this.field152;
   }

   @Generated
   public SkyblockGalateaMobHighlight method164() {
      return this.field153;
   }

   @Generated
   public SkyblockInventoryButtons method165() {
      return this.field154;
   }

   @Generated
   public SkyblockWormScathaAlert method166() {
      return this.field155;
   }

   @Generated
   public SkyblockScathaTrackerHud method167() {
      return this.field156;
   }

   @Generated
   public SkyblockChestLooted method168() {
      return this.field157;
   }

   @Generated
   public SkyblockAutoRequeue method169() {
      return this.field158;
   }

   @Generated
   public SkyblockPriceInLore method170() {
      return this.field159;
   }

   @Generated
   public SkyblockDeathCounterHud method171() {
      return this.field160;
   }

   @Generated
   public SkyblockReviveHud method172() {
      return this.field161;
   }

   @Generated
   public SkyblockDungeonBreaker method173() {
      return this.field162;
   }

   @Generated
   public SkyblockEnchants method174() {
      return this.field163;
   }

   @Generated
   public SkyblockChestProfit method175() {
      return this.field164;
   }

   @Generated
   public SkyblockBitsShopHelper method176() {
      return this.field165;
   }

   @Generated
   public SkyblockSkymartHelper method177() {
      return this.field166;
   }

   @Generated
   public SkyblockVisitorProfit method178() {
      return this.field167;
   }

   @Generated
   public SkyblockVisitorBazaarHelper method179() {
      return this.field168;
   }

   @Generated
   public SkyblockWardrobeHotkeys method180() {
      return this.field169;
   }

   @Generated
   public SkyblockArmorDyeColor method181() {
      return this.field170;
   }

   @Generated
   public SkyblockHideFarEntities method182() {
      return this.field171;
   }

   @Generated
   public SkyblockProtectItem method183() {
      return this.field172;
   }

   @Generated
   public TestCustomGuiOverride method184() {
      return this.field173;
   }

   @Generated
   public TestCustomGuiOverlay method185() {
      return this.field174;
   }

   @Generated
   public StorageOverlay method186() {
      return this.field175;
   }

   @Generated
   public SkyblockChatCommands method187() {
      return this.field176;
   }

   @Generated
   public SkyblockSpiritLeapAnnounce method188() {
      return this.field177;
   }

   @Generated
   public SkyblockSkillXpTrackerHud method189() {
      return this.field178;
   }

   @Generated
   public SkyblockSkillProgressHud method190() {
      return this.field179;
   }

   @Generated
   public SkyblockSkillGlobeHud method191() {
      return this.field180;
   }

   @Generated
   public SkyblockWitherHealthHud method192() {
      return this.field181;
   }

   @Generated
   public SkyblockHighlightFilledBzOrders method193() {
      return this.field182;
   }

   @Generated
   public SkyblockCalculatorInSigns method194() {
      return this.field183;
   }

   @Generated
   public SkyblockSkillProgressBarHud method195() {
      return this.field184;
   }

   @Generated
   public SkyblockMageBeam method196() {
      return this.field185;
   }

   @Generated
   public SkyblockHarvestFeastTrackerHud method197() {
      return this.field186;
   }

   @Generated
   public SkyblockFarmingToolCropIcon method198() {
      return this.field187;
   }

   @Generated
   public SkyblockStackingEnchants method199() {
      return this.field188;
   }

   @Generated
   public SkyblockHideChumBuckets method200() {
      return this.field189;
   }

   @Generated
   public SkyblockTrophyFishExchangeRate method201() {
      return this.field190;
   }

   @Generated
   public SkyblockLilyPadHelper method202() {
      return this.field191;
   }

   @Generated
   public SkyblockHideLotusfishNametag method203() {
      return this.field192;
   }

   @Generated
   public SkyblockFishingCircleOverlay method204() {
      return this.field193;
   }

   @Generated
   public SkyblockFishingReelTimerHud method205() {
      return this.field194;
   }

   @Generated
   public SkyblockFishingInfoHud method206() {
      return this.field195;
   }

   @Generated
   public SkyblockSeaCreatureMessages method207() {
      return this.field196;
   }

   @Generated
   public SkyblockFishingBaitHud method208() {
      return this.field197;
   }

   @Generated
   public SkyblockFairySouls method209() {
      return this.field198;
   }

   @Generated
   public Map<com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish, SkyBlockTabWidgetHud> method210() {
      return this.field199;
   }

   @Generated
   public SkyblockProfileViewer method211() {
      return this.field200;
   }

   @Generated
   public SkyblockStormHud method212() {
      return this.field201;
   }

   @Generated
   public SkyblockEnchantDisplay method213() {
      return this.field202;
   }

   @Generated
   public SkyblockStorageHoverPreview method214() {
      return this.field203;
   }

   @Generated
   public SkyblockEnhancedMinionMenu method215() {
      return this.field204;
   }

   @Generated
   public SkyblockSmoothTeleport method216() {
      return this.field205;
   }

   @Generated
   public SkyblockForagingBeaconSolver method217() {
      return this.field206;
   }

   @Generated
   public SkyblockMuteBeeheemothSounds method218() {
      return this.field207;
   }

   @Generated
   public SkyblockRainbowBellWaypoints method219() {
      return this.field208;
   }

   @Generated
   public SkyblockCaughtCrittersHud method220() {
      return this.field209;
   }

   @Generated
   public SkyblockSparklingCritterAlert method221() {
      return this.field210;
   }

   @Generated
   public SkyBlockCommandKeybinds method222() {
      return this.field211;
   }

   @Generated
   public Set<HudTimer> method223() {
      return this.field212;
   }
}
