package com.moonsworth.lunar.client.mod.render.minimap;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.world.Biome;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.client.framework.ReducedDebugInfoNotifier;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.MinimapScreen;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.minimap.MinimapLayer;
import com.moonsworth.lunar.client.framework.feature.minimap.MinimapRegionFile;
import com.moonsworth.lunar.client.framework.feature.minimap.MinimapImpl;
import com.moonsworth.lunar.client.framework.feature.minimap.MinimapPlayerProvider;
import com.moonsworth.lunar.client.framework.feature.minimap.MinimapWaypointProvider;
import com.moonsworth.lunar.client.framework.feature.minimap.MinimapMarkerProvider;
import com.moonsworth.lunar.client.framework.listener.BiomeListener;
import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventGameDirectory;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventChunk.EventChunkUnload;
import com.moonsworth.lunar.client.event.mixin.fishing.EventChunk.EventChunkLoad;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTick.EventRenderTickEnd;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldLoad;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerJoin;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.util.io.FileExplorer;
import com.moonsworth.lunar.client.util.math.FastMath;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.pipeline.StencilEmulator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.apache.commons.lang3.mutable.MutableObject;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3i;

public class MinimapMod extends AbstractFeature {
   private static final ResourceLocationBridge RECORD_ICON = ResourceLocationBridge.create("lunar", "icons/rewind/record.png");
   public static final int MAX_RENDER_DISTANCE = 14;
   private static final float[] CROSS_LINES = new float[]{-5.0F, 0.0F, 5.0F, 0.0F, 0.0F, -5.0F, 0.0F, 5.0F};
   private final BiomeListener biomeHandler = (BiomeListener)this.method63(BiomeListener.class);
   private final ToggleOption saveAsPictureFile = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("minimapSaveAsPictureFile")
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final IntegerOption mapWidth = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "mapWidth"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(8))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 16))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final IntegerOption mapHeight = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "mapHeight"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(8))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 16))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final FloatOption mapZoom = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "mapZoom"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(1.0F, 2.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final EnumOption<MinimapMod.Type> mapShape = (EnumOption<MinimapMod.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "mapShape", MinimapMod.Type.SQUARE
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption rotateWithPlayer = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("rotateWithPlayer")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption fitTextToWidth = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("fitTextToWidth")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption border = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("border")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption borderColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final FloatOption borderThickness = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(1.0F, 5.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption compass = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("compass")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption compassColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "compassColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption compassShadow = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("compassShadow")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final EnumOption<MinimapMod.PlayerMarkerType> playerMarkerType = (EnumOption<MinimapMod.PlayerMarkerType>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "playerMarkerType", MinimapMod.PlayerMarkerType.TRIANGLE
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption playerMarkerColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "playerMarkerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16776961))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final FloatOption playerMarkerSize = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "playerMarkerSize"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(5.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(1.0F, 10.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final EnumOption<MinimapMod.EntityMarkerType> entityMarkerType = (EnumOption<MinimapMod.EntityMarkerType>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "entityMarkerType", MinimapMod.EntityMarkerType.HEAD
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final FloatOption entityMarkerOpacity = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "entityMarkerOpacity"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 1.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final FloatOption entityMarkerSize = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "entityMarkerSize"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(1.0F, 10.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption entityMarkerShadow = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("entityMarkerShadow")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption showCoordinates = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showCoordinates")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption showBiome = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showBiome")
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption presetBiomeColor = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("presetBiomeColor")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption showClock = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showClock")
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption showAmPm = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showAmPm")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption militaryTime = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("militaryTime")
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ModifierKeybindOption fullViewKeybind = (ModifierKeybindOption)com.moonsworth.lunar.client.config.option.OptionFactory.method18("fullView")
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption showDistantWaypoints = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showDistantWaypoints")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final com.moonsworth.lunar.client.framework.feature.minimap.MinimapMap minimapManager = new com.moonsworth.lunar.client.framework.feature.minimap.MinimapMap();
   private final Queue<ChunkBridge> chunkQueue = new ConcurrentLinkedDeque<>();
   private final List<MinimapMarkerProvider<?>> markerProviders = new ArrayList<>();
   private int lastDimensionId = Integer.MIN_VALUE;
   private String clockText = "";

   public MinimapMod() {
      super(false);
      this.method13(ModTraits.field1, new MinimapMod.Data());
      this.handle(EventChunkLoad.class, this::method6);
      this.handle(EventChunkUnload.class, this::method7);
      this.handle(EventBlockChange.class, this::method8);
      this.handle(EventServerJoin.class, this::resetManager);
      this.handle(EventWorldChange.class, this::method9);
      this.handle(EventWorldLoad.class, arg1 -> {
         this.resetManager(arg1);
         this.lastDimensionId = arg1.ROHOIOICHOICIOORCHORIOCOCOOCHH() == null ? Integer.MIN_VALUE : arg1.ROHOIOICHOICIOORCHORIOCOCOOCHH().bridge$getDimensionId();
      });
      this.handle(EventRenderTickEnd.class, this::method5);
      this.handle(EventGameDirectory.class, MinimapRegionFile::method2);
      this.handle(EventDisconnect.class, arg1 -> {
         try {
            if (this.lastDimensionId != Integer.MIN_VALUE) {
               MinimapRegionFile.method4(this.minimapManager, this.lastDimensionId);
            }
         } catch (IOException exception6) {
            exception6.printStackTrace();
         } finally {
            this.lastDimensionId = Integer.MIN_VALUE;
         }
      });
      this.handle(EventTick.class, this::method4);
      this.markerProviders.add(new MinimapPlayerProvider(this, this.minimapManager, this.mc));
      this.markerProviders.add(new MinimapWaypointProvider(this, this.minimapManager, this.mc));
      this.markerProviders.add(new MinimapImpl(this, this.minimapManager, this.mc));
      this.fullViewKeybind.method3(() -> {
         if (this.isEnabled()) {
            this.mc.bridge$displayScreen(Bridge.method8().method18(new MinimapScreen(this)));
         }
      });
   }

   public String getId() {
      return "MINIMAP";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1(
         "general",
         arg1x -> {
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.saveAsPictureFile});
            ((SettingsSectionBuilder)arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
                  new OptionProvider[]{com.moonsworth.lunar.client.config.option.OptionFactory.method14("minimapOpenSaveFolder").method10().method4(this::openSaveFolder)}
               ))
               .method3(() -> !(Boolean)this.saveAsPictureFile.get() || Ref.method7() == null || !this.isEnabled());
         }
      );
      lightingextension231.method1("sizeAndShape", arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.mapWidth});
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.mapHeight}).method3(() -> this.mapShape.get() == MinimapMod.Type.CIRCLE);
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.mapZoom, this.mapShape, this.rotateWithPlayer, this.fitTextToWidth});
      });
      lightingextension231.method1(
         "borderOptions",
         arg1x -> arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.border, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.borderColor, this.borderThickness})
         )
      );
      lightingextension231.method1(
         "compassOptions",
         arg1x -> arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.compass, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.compassColor, this.compassShadow})
         )
      );
      lightingextension231.method1(
         "playerMarkerOptions",
         arg1x -> {
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.playerMarkerType});
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.playerMarkerColor, this.playerMarkerSize})
               .method3(() -> this.playerMarkerType.get() == MinimapMod.PlayerMarkerType.NONE || this.playerMarkerType.get() == MinimapMod.PlayerMarkerType.HEAD);
         }
      );
      ((SettingsSectionImpl)lightingextension231.method1(
            "entityMarkerOptions", arg1x -> arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.entityMarkerType, arg1xx -> {
               arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.entityMarkerOpacity}).method3(() -> this.entityMarkerType.get() == MinimapMod.EntityMarkerType.HEAD);
               arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.entityMarkerSize});
               arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.entityMarkerShadow}).method3(() -> this.entityMarkerType.get() == MinimapMod.EntityMarkerType.HEAD);
            }, arg1xx -> arg1xx.method3(() -> this.entityMarkerType.get() == MinimapMod.EntityMarkerType.NONE))
         ))
         .method2(() -> !this.isSingleplayer());
      lightingextension231.method1("coordinatesOptions", arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showCoordinates});
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showBiome, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.presetBiomeColor}));
      });
      lightingextension231.method1(
         "clockOptions",
         arg1x -> arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.showClock, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showAmPm, this.militaryTime})
         )
      );
      lightingextension231.method1("fullViewOptions", arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.fullViewKeybind}));
      lightingextension231.method1("waypointsOptions", arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showDistantWaypoints}));
      this.saveAsPictureFile.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
         if (!arg1x) {
            this.minimapManager.method25().reset();
         }
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field3}).method11(this);
   }

   public void method3(boolean flag1) {
      if (flag1) {
         this.minimapManager.setup();
      } else {
         this.minimapManager.clear();
      }
   }

   private void method4(EventTick highlightimpl21) {
      long number2 = Ref.method8() == null ? 0L : Ref.method8().bridge$getDayTime();
      int number4 = (int)(number2 / 24000L);
      int number5 = ((int)((int)((number2 - number4 * 24000L) / 1000L)) + 6) % 24;
      int number6 = (int)(number2 % 1000L / 1000.0 * 60.0);
      String text7;
      if ((Boolean)this.militaryTime.get()) {
         text7 = String.format("%02d", number5) + ":" + String.format("%02d", number6);
      } else {
         int number8 = number5 % 12;
         if (number8 == 0) {
            number8 = 12;
         }

         text7 = String.format("%02d", number8) + ":" + String.format("%02d", number6);
         if ((Boolean)this.showAmPm.get()) {
            if (number5 >= 12) {
               text7 = text7 + " PM";
            } else {
               text7 = text7 + " AM";
            }
         }
      }

      this.clockText = this.method16("worldTime", new Object[]{number4, text7});
   }

   private void method5(EventRenderTickEnd data61) {
      while (!this.chunkQueue.isEmpty()) {
         this.minimapManager.method1(this.chunkQueue.remove());
      }

      this.minimapManager.method5();
   }

   private void method6(EventChunkLoad data91) {
      this.chunkQueue.add(data91.IIOHICICIRRRHOCCIOORRHHHIHHICR());
   }

   private void method7(EventChunkUnload data81) {
      this.minimapManager.method2(data81.IIOHICICIRRRHOCCIOORRHHHIHHICR());
   }

   private void method8(EventBlockChange highlightimpl91) {
      if (this.mc.bridge$getWorld() != null) {
         Vec3iBridge horsestats202 = highlightimpl91.method1();
         ChunkBridge itemcounter23 = this.mc.bridge$getWorld().bridge$getChunkFromBlockCoords(new Vector3i(horsestats202.bridge$getX(), horsestats202.bridge$getY(), horsestats202.bridge$getZ()));
         if (itemcounter23 != null) {
            this.chunkQueue.add(itemcounter23);
         }
      }
   }

   private void method9(EventWorldChange data31) {
      WorldBridgeExtension itemcounter6extension2 = this.mc.bridge$getWorld();
      if (itemcounter6extension2 != null) {
         try {
            MinimapRegionFile.method3(this.minimapManager, itemcounter6extension2.bridge$getDimensionId());
         } catch (IOException exception4) {
            exception4.printStackTrace();
         }
      }
   }

   private void resetManager(LunarEvent highlight1) {
      this.minimapManager.reset();
   }

   public float calculateScale(int number1, int number2, int number3) {
      float value4 = (Float)this.mapZoom.get();
      if ((Boolean)this.rotateWithPlayer.get() && this.mapShape.get() == MinimapMod.Type.SQUARE) {
         value4 = Math.max(value4, 1.0F);
      }

      float value5 = value4 + 4.5F * (1.0F - number3 / 14.0F);
      float value6 = (this.minimapManager.getTextureWidth() - 16) * value5;
      float value7 = (this.minimapManager.getTextureHeight() - 16) * value5;
      float value8 = number1 / value6;
      float value9 = number2 / value7;
      float value10 = Math.max(value8, value9);
      if (value10 > 1.0F) {
         value5 *= value10;
      }

      return value5;
   }

   public MinimapLayer<?> renderOverlays(MixinHelper_4 mixinhelper_41, float value2, Vector2f vector2f3) {
      int number4 = Math.max(this.minimapManager.method15(), this.minimapManager.method16());
      float value5 = number4 * 16 * number4 * 16;
      Bridge5Extension_5 bridge5extension_56 = this.mc.bridge$getPlayer();
      if (bridge5extension_56 != null && this.mc.bridge$getWorld() != null) {
         Vector2i vector2i7 = new Vector2i(this.minimapManager.method19(), this.minimapManager.method20());
         MutableObject mutableobject8 = new MutableObject();

         for (MinimapMarkerProvider minimap_210 : this.markerProviders) {
            minimap_210.method1(bridge5extension_56, value5, vector2i7).forEach(arg6x -> this.minimapManager.method11(arg6x.method8(), arg6x.method9(), (arg6xx, arg7x) -> {
               arg6x.method1(mixinhelper_41, bridge5extension_56, value2, arg6xx, arg7x);
               if (vector2f3 != null) {
                  float value8x = arg6x.getWidth();
                  float value9 = arg6x.getHeight();
                  if (vector2f3.x >= arg6xx - value8x / 2.0F && vector2f3.x <= arg6xx + value8x / 2.0F && vector2f3.y >= arg7x - value9 / 2.0F && vector2f3.y <= arg7x + value9 / 2.0F) {
                     mutableobject8.setValue(arg6x);
                  }
               }
            }));
         }

         return (MinimapLayer<?>)mutableobject8.getValue();
      } else {
         return null;
      }
   }

   private boolean method13(double value1, double value3, float value5, double value6) {
      MinimapMod.Type type8 = (MinimapMod.Type)this.mapShape.get();
      double value9 = ((Integer)this.mapWidth.get()).intValue() / 2.0F * 16.0F + value6;
      double value11 = ((Integer)this.mapHeight.get()).intValue() / 2.0F * 16.0F + value6;
      int number13 = (Integer)this.mapWidth.get() * 16;
      int number14 = type8 == MinimapMod.Type.SQUARE ? (Integer)this.mapHeight.get() * 16 : number13;
      float value15 = this.calculateScale(number13, number14, this.minimapManager.method16());
      value1 *= value15;
      value3 *= value15;
      if (type8 != MinimapMod.Type.SQUARE) {
         return value1 * value1 + value3 * value3 <= value9 * value9;
      }

      double value16 = Math.cos(value5);
      double value18 = Math.sin(value5);
      double value20 = value1 * value16 - value3 * value18;
      double value22 = value1 * value18 + value3 * value16;
      return value20 >= -value9 && value20 <= value9 && value22 >= -value11 && value22 <= value11;
   }

   public void renderPlayerMarker(MixinHelper_4 mixinhelper_41, float value2, boolean flag3) {
      this.renderPlayerMarkerSized(mixinhelper_41, value2, flag3, (Float)this.playerMarkerSize.get());
   }

   public void renderPlayerMarkerSized(MixinHelper_4 mixinhelper_41, float value2, boolean flag3, float value4) {
      MinimapMod.PlayerMarkerType type35 = (MinimapMod.PlayerMarkerType)this.playerMarkerType.get();
      if (type35.needsRotation) {
         mixinhelper_41.push();
         if (!flag3) {
            mixinhelper_41.method42(value2);
         } else {
            mixinhelper_41.method42(180.0F);
         }
      }

      if (type35 == MinimapMod.PlayerMarkerType.CIRCLE) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method78(mixinhelper_41, 0.0, 0.0, value4, this.playerMarkerColor.method14(0.0F));
      } else if (type35 == MinimapMod.PlayerMarkerType.TRIANGLE) {
         float value6 = value4 / 5.0F * 8.0F;
         this.method16(mixinhelper_41, value4, value6, this.playerMarkerColor.method14(0.0F));
      } else if (type35 == MinimapMod.PlayerMarkerType.CROSS) {
         boolean flag7 = Ref.MC_VERSION >= 8 && value4 != 1.0F;
         if (flag7) {
            mixinhelper_41.method38(2.5F, 0.0F, 0.0F);
         }

         mixinhelper_41.method32(value4, this.playerMarkerColor.method14(0.0F), arg0 -> {
            arg0.accept(-5.0F, 0.0F, 5.0F, 0.0F);
            arg0.accept(0.0F, -5.0F, 0.0F, 5.0F);
         });
         if (flag7) {
            mixinhelper_41.method38(-2.5F, 0.0F, 0.0F);
         }
      } else if (type35 == MinimapMod.PlayerMarkerType.HEAD && this.mc.bridge$getPlayer() != null) {
         ResourceLocationBridge horsestats148 = this.mc.bridge$getPlayer().bridge$getServerSkinTexture();
         if (horsestats148 != null) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method50(mixinhelper_41, horsestats148, -4.0F, -4.0F, -1, true);
         }
      }

      if (type35.needsRotation) {
         mixinhelper_41.pop();
      }
   }

   public void method16(MixinHelper_4 mixinhelper_41, float value2, float value3, int number4) {
      float value5 = value2 / 2.0F;
      float value6 = value3 / 2.0F;
      mixinhelper_41.method9(LunarRenderTypes.field18, null, -value5, -value6, value2, value3, arg3x -> {
         arg3x.method5(-value5, -value6).method9(number4).method16();
         arg3x.method5(0.0F, value6).method9(number4).method16();
         arg3x.method5(value5, -value6).method9(number4).method16();
      });
   }

   private void openSaveFolder() {
      Path path1 = this.minimapManager.method25().method6();
      if (path1 != null) {
         File file2 = path1.toFile().getAbsoluteFile().getParentFile();
         if (!FileExplorer.method1(file2)) {
            boolean flag3 = Ref.method4().method34().method3();
            Ref.method4()
               .method69()
               .method7(NotificationType.ERROR, NotificationManager.method15("minimapPopupOpenFolderFailed", new Object[]{flag3 ? file2.getName() : file2.getAbsolutePath()}));
         }
      }
   }

   public boolean isSingleplayer() {
      return this.mc.bridge$getCurrentServerData() == null;
   }

   @Generated
   public ToggleOption getSaveAsPictureFile() {
      return this.saveAsPictureFile;
   }

   @Generated
   public ToggleOption getRotateWithPlayer() {
      return this.rotateWithPlayer;
   }

   @Generated
   public ToggleOption getCompassShadow() {
      return this.compassShadow;
   }

   @Generated
   public EnumOption<MinimapMod.EntityMarkerType> method24() {
      return this.entityMarkerType;
   }

   @Generated
   public FloatOption getEntityMarkerOpacity() {
      return this.entityMarkerOpacity;
   }

   @Generated
   public FloatOption method26() {
      return this.entityMarkerSize;
   }

   @Generated
   public ToggleOption getEntityMarkerShadow() {
      return this.entityMarkerShadow;
   }

   @Generated
   public ModifierKeybindOption getFullViewKeybind() {
      return this.fullViewKeybind;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.minimap.MinimapMap getMinimapManager() {
      return this.minimapManager;
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_RIGHT);
      }

      public boolean method31() {
         return !(Boolean)MinimapMod.this.rotateWithPlayer.get();
      }

      public boolean method4(boolean flag1) {
         return Ref.method11() != MinimapScreen.class && DriverViewportLegacy.method50().method63() != DriverRouteRegistry.field17
            ? Ref.method10() != null
            : false;
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         LegacyGuiGraphicsBridge mixinhelper55 = highlightimpl1.method1();
         BridgeExtension bridgeextension6 = MinimapMod.this.mc.bridge$getRenderViewEntity();
         MinimapMod.Type type7 = (MinimapMod.Type)MinimapMod.this.mapShape.get();
         boolean flag8 = (Boolean)MinimapMod.this.rotateWithPlayer.get() && bridgeextension6 != null;
         int number9 = Math.max(2, Math.min(14, MinimapMod.this.mc.bridge$getGameSettings().bridge$getRenderDistance() * 2));
         MinimapMod.this.minimapManager.method9(number9, number9);
         int number10 = (Integer)MinimapMod.this.mapWidth.get() * 16;
         int number11 = type7 == MinimapMod.Type.SQUARE ? (Integer)MinimapMod.this.mapHeight.get() * 16 : number10;
         int number12 = number11;
         float value13 = MinimapMod.this.calculateScale(number10, number11, number9);
         number12 += this.method5();
         this.method58(number10, number12);
         mixinhelper55.push();
         StencilEmulator click614 = StencilEmulator.method8();
         click614.method3(mixinhelper55);
         click614.method5(mixinhelper55, value2, value3, number10, number11);
         float value15 = number10 / 2.0F;
         float value16 = number11 / 2.0F;
         mixinhelper55.method38(value2 + value15, value3 + value16, 0.0F);
         mixinhelper55.push();
         if (flag8) {
            mixinhelper55.method42((float)(180.0 - bridgeextension6.bridge$getRotationYaw()));
         }

         mixinhelper55.scale(value13, value13, 1.0F);
         MinimapMod.this.minimapManager.method3(mixinhelper55, bridgeextension6);
         MinimapMod.this.minimapManager.method4(mixinhelper55, bridgeextension6, true);
         MinimapMod.this.renderOverlays(mixinhelper55, flag8 ? (float)(180.0 - bridgeextension6.bridge$getRotationYaw()) : 0.0F, null);
         mixinhelper55.pop();
         if (type7 == MinimapMod.Type.CIRCLE) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method76(mixinhelper55, 0.0, 0.0, value15, -1);
         }

         click614.method4(mixinhelper55);
         MixinHelper_4 mixinhelper_417 = highlightimpl1.method2();
         mixinhelper_417.push();
         mixinhelper_417.method38(-(value2 + value15), -(value3 + value16), 0.0F);
         click614.method6(mixinhelper_417, value2, value3, number10, number11, this.getScale());
         mixinhelper_417.pop();
         if (MinimapMod.this.mc.bridge$getPlayer() != null) {
            MinimapMod.this.renderPlayerMarker(mixinhelper_417, bridgeextension6 == null ? 0.0F : (float)bridgeextension6.bridge$getRotationYaw(), flag8);
         }

         if ((Boolean)MinimapMod.this.border.get()) {
            if (type7 == MinimapMod.Type.SQUARE) {
               MinimapMod.this.borderColor.method11(mixinhelper_417, this, -value15, -value16, number10, number11, (Float)MinimapMod.this.borderThickness.get());
            } else {
               com.moonsworth.lunar.client.ui.LcuiScreen.method75(
                  mixinhelper_417, 0.0, 0.0, value15, value15 - (Float)MinimapMod.this.borderThickness.get(), MinimapMod.this.borderColor.method14(0.0F)
               );
            }
         }

         if ((Boolean)MinimapMod.this.showDistantWaypoints.get()) {
            this.method6(mixinhelper_417, bridgeextension6);
         }

         boolean flag18 = ReducedDebugInfoNotifier.method1() && !flag4;
         if (!flag18 && (Boolean)MinimapMod.this.compass.get()) {
            this.method7(mixinhelper_417, bridgeextension6, flag4);
         }

         mixinhelper55.pop();
         TextComponent text19 = null;
         TextComponent text20 = null;
         TextComponent text21 = MinimapMod.this.showClock.get() ? Component.text(MinimapMod.this.clockText) : null;
         if (!flag18 && (Boolean)MinimapMod.this.showCoordinates.get()) {
            String text22;
            if (bridgeextension6 != null && !flag4) {
               text22 = String.format("%.2f, %.2f, %.2f", bridgeextension6.bridge$getPosX(), bridgeextension6.bridge$getPosY(), bridgeextension6.bridge$getPosZ());
            } else {
               text22 = String.format("%.2f, %.2f, %.2f", 0.0F, 0.0F, 0.0F);
            }

            text19 = Component.text(text22);
         }

         if (!flag18 && (Boolean)MinimapMod.this.showBiome.get()) {
            com.moonsworth.lunar.client.framework.listener.BiomeListener.BiomeDisplay data324;
            if (flag4) {
               data324 = new com.moonsworth.lunar.client.framework.listener.BiomeListener.BiomeDisplay(
                  Biome.PLAINS, Bridge.method8().method71("biome.minecraft.plains")
               );
            } else {
               data324 = MinimapMod.this.biomeHandler.method5();
            }

            text20 = Component.text(data324.name());
            if ((Boolean)MinimapMod.this.presetBiomeColor.get()) {
               text20 = (TextComponent)text20.color(TextColor.color(data324.method1()));
            }
         }

         if ((Boolean)MinimapMod.this.saveAsPictureFile.get()) {
            mixinhelper_417.push();
            mixinhelper_417.method38(value2 + number10 - 10.0F, value3 + 2.0F, 0.0F);
            mixinhelper_417.scale(0.5F, 0.5F, 0.5F);
            com.moonsworth.lunar.client.ui.LcuiScreen.method46(
               mixinhelper_417, MinimapMod.RECORD_ICON, 0.0F, 0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F, 16.0F, -65536
            );
            mixinhelper_417.pop();
         }

         this.method4(mixinhelper_417, value2, value3 + number11 + 3.0F, number10, text19, text21, text20);
      }

      private void method4(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, TextComponent... items5) {
         for (TextComponent text9 : items5) {
            if (text9 != null) {
               float value10 = Ref.method10().bridge$getStringWidth(text9);
               mixinhelper_41.push();
               mixinhelper_41.method38(value2 + this.getWidth() / 2.0F, value3, 0.0F);
               if (value10 > value4 && (Boolean)MinimapMod.this.fitTextToWidth.get()) {
                  float value11 = value4 / value10;
                  mixinhelper_41.scale(value11, value11, 1.0F);
                  value3 += (Ref.method10().method19() + 1) * value11;
               } else {
                  value3 += Ref.method10().method19() + 0.5F;
               }

               mixinhelper_41.method11(Ref.method10(), text9, -value10 / 2.0F - 0.5F, 0.0F, -1, true);
               mixinhelper_41.pop();
            }
         }
      }

      private int method5() {
         int number1 = 0;
         int number2 = Ref.method10().method19();
         if ((Boolean)MinimapMod.this.showCoordinates.get()) {
            number1 += number2 + 1;
         }

         if ((Boolean)MinimapMod.this.showBiome.get()) {
            number1 += number2 + 1;
         }

         if ((Boolean)MinimapMod.this.showClock.get()) {
            number1 += number2 + 1;
         }

         return number1 == 0 ? 0 : number1 + 1;
      }

      private void method6(MixinHelper_4 mixinhelper_41, BridgeExtension bridgeextension2) {
         Bridge10_2 bridge10_23 = Ref.method10();
         boolean flag4 = (Boolean)MinimapMod.this.compassShadow.get();
         float value5 = bridgeextension2 == null ? 0.0F : (float)bridgeextension2.bridge$getRotationYaw();
         float value6 = this.getHeight() - this.method5();
         double value7 = MinimapMod.this.minimapManager.method19() + MinimapMod.this.minimapManager.method13();
         double value9 = MinimapMod.this.minimapManager.method20() + MinimapMod.this.minimapManager.method14();
         float value11;
         if ((Boolean)MinimapMod.this.rotateWithPlayer.get()) {
            value11 = (float)Math.toRadians(value5);
         } else {
            value11 = (float) Math.PI;
         }

         MinimapMod.this.CROIICHHOOCCOCRIRCRIHHRHICICCO.method48().IIORHHIRHIORHRCCCOICCRCHRRCCRH().stream().filter(arg6x -> {
            boolean flag7x = arg6x.isVisible();
            boolean flag8 = arg6x.shouldRender();
            if (flag7x && flag8) {
               Vec3Bridge horsestats159x = arg6x.method35();
               return !MinimapMod.this.method13(horsestats159x.bridge$xCoord() - value7, horsestats159x.bridge$zCoord() - value9, -value11, 6.0);
            } else {
               return false;
            }
         }).forEach(arg10 -> {
            float value11x = (float)(arg10.method35().bridge$xCoord() - value7);
            float value12 = (float)(arg10.method35().bridge$zCoord() - value9);
            float value13 = (float)Math.atan2(value12, value11x) - value11;
            if (MinimapMod.this.mapShape.get() == MinimapMod.Type.CIRCLE) {
               this.method9(mixinhelper_41, bridge10_23, arg10.getLabel(), this.getWidth() / 2.0F - 1.0F, (float)(value13 + Math.PI), arg10.method46().method4(), flag4);
            } else {
               value13 = (float)Math.toDegrees(value13) - 90.0F;
               value13 += 45.0F;
               value13 = (value13 % 360.0F + 360.0F) % 360.0F;
               float value14 = value13 % 90.0F / 90.0F;
               this.method8(mixinhelper_41, arg10.method46().method4(), arg10.getLabel(), value14, value13, value6, flag4);
            }
         });
      }

      private void method7(MixinHelper_4 mixinhelper_41, BridgeExtension bridgeextension2, boolean flag3) {
         boolean flag4 = (Boolean)MinimapMod.this.compassShadow.get();
         Bridge10_2 bridge10_25 = Ref.method10();
         if (bridge10_25 != null) {
            float value6 = this.getHeight() - this.method5();
            float value7 = this.getWidth();
            if (!(Boolean)MinimapMod.this.rotateWithPlayer.get()) {
               float value8 = bridge10_25.method19() / 2.0F;
               MinimapMod.this.compassColor.method11(mixinhelper_41, "N", 0.0F, -value6 / 2.0F - value8, flag4);
               MinimapMod.this.compassColor.method11(mixinhelper_41, "W", -value7 / 2.0F, -value8, flag4);
               MinimapMod.this.compassColor.method11(mixinhelper_41, "S", 0.0F, value6 / 2.0F + 1.0F - value8, flag4);
               MinimapMod.this.compassColor.method11(mixinhelper_41, "E", value7 / 2.0F, -value8, flag4);
            } else if (MinimapMod.this.mapShape.get() == MinimapMod.Type.CIRCLE) {
               float value14 = value7 / 2.0F;
               float value9 = bridgeextension2 != null && !flag3 ? (float)bridgeextension2.bridge$getRotationYaw() : 0.0F;
               float value10 = (float)Math.toRadians(-value9 + 90.0F);
               float value11 = (float)Math.toRadians(-value9 + 180.0F);
               float value12 = (float)Math.toRadians(-value9 + 270.0F);
               float value13 = (float)Math.toRadians(-value9);
               this.method9(mixinhelper_41, bridge10_25, "N", value14 - 1.0F, value10, MinimapMod.this.compassColor, flag4);
               this.method9(mixinhelper_41, bridge10_25, "E", value14 - 1.0F, value11, MinimapMod.this.compassColor, flag4);
               this.method9(mixinhelper_41, bridge10_25, "S", value14 - 1.0F, value12, MinimapMod.this.compassColor, flag4);
               this.method9(mixinhelper_41, bridge10_25, "W", value14 - 1.0F, value13, MinimapMod.this.compassColor, flag4);
            } else {
               float value15 = bridgeextension2 != null && !flag3 ? (float)bridgeextension2.bridge$getRotationYaw() : 0.0F;
               value15 = -value15;
               value15 += 45.0F;
               value15 -= 180.0F;
               value15 = (value15 % 360.0F + 360.0F) % 360.0F;
               float value20 = value15 % 90.0F / 90.0F;
               this.method8(mixinhelper_41, MinimapMod.this.compassColor, "N", value20, value15, value6, flag4);
               this.method8(mixinhelper_41, MinimapMod.this.compassColor, "E", value20, value15 + 90.0F, value6, flag4);
               this.method8(mixinhelper_41, MinimapMod.this.compassColor, "S", value20, value15 + 180.0F, value6, flag4);
               this.method8(mixinhelper_41, MinimapMod.this.compassColor, "W", value20, value15 + 270.0F, value6, flag4);
            }
         }
      }

      private void method8(MixinHelper_4 mixinhelper_41, ColorOption lightingextension42222, String text3, float value4, float value5, float value6, boolean flag7) {
         float value8 = this.getWidth();
         float value9 = value8 / 2.0F;
         float value10 = value6 / 2.0F;
         value5 = (value5 % 360.0F + 360.0F) % 360.0F;
         float value11;
         float value12;
         if (value5 < 90.0F) {
            value11 = -value9 + value4 * value8;
            value12 = -value10 - 4.0F;
         } else if (value5 < 180.0F) {
            value11 = value9;
            value12 = -value10 - 4.0F + value4 * value6;
         } else if (value5 < 270.0F) {
            value11 = value9 - value4 * value8;
            value12 = value10 - 4.0F;
         } else {
            value11 = -value9;
            value12 = value10 - 4.0F - value4 * value6;
         }

         lightingextension42222.method11(mixinhelper_41, text3, value11, value12, flag7);
      }

      private void method9(MixinHelper_4 mixinhelper_41, Bridge10_2 bridge10_22, String text3, float value4, float value5, ColorOption lightingextension42226, boolean flag7) {
         float value8 = (float)(value4 * FastMath.method1(value5) - bridge10_22.bridge$getStringWidth(text3) / 2.0F);
         float value9 = (float)(value4 * FastMath.sin(value5) - bridge10_22.method19() / 2.0F);
         mixinhelper_41.method19(bridge10_22, text3, value8, value9, lightingextension42226.method14(0.0F), flag7);
      }
   }

   private enum Type implements OptionEnumValue {
      SQUARE("square"),
      CIRCLE("circle");

      private final String id;

      public String id() {
         return this.id;
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }

   public enum EntityMarkerType implements OptionEnumValue {
      NONE("none", false),
      CIRCLE("circle", false),
      TRIANGLE("triangle", true),
      HEAD("head", false);

      private final String id;
      public final boolean needsRotation;

      EntityMarkerType(String text3, boolean flag4) {
         this.id = text3;
         this.needsRotation = flag4;
      }

      public String id() {
         return this.id;
      }
   }

   private enum PlayerMarkerType implements OptionEnumValue {
      NONE("none", false),
      CIRCLE("circle", false),
      TRIANGLE("triangle", true),
      CROSS("cross", false),
      HEAD("head", false);

      private final String id;
      private final boolean needsRotation;

      PlayerMarkerType(String text3, boolean flag4) {
         this.id = text3;
         this.needsRotation = flag4;
      }

      public String id() {
         return this.id;
      }
   }
}
