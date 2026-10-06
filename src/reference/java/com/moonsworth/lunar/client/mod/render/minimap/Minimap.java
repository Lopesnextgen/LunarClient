package com.moonsworth.lunar.client.mod.render.minimap;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType_2;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.framework.ReducedDebugInfoNotifier;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.MinimapScreen;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.minimap.Minimap2_2;
import com.moonsworth.lunar.client.framework.feature.minimap.Minimap3;
import com.moonsworth.lunar.client.framework.feature.minimap.MinimapImpl;
import com.moonsworth.lunar.client.framework.feature.minimap.MinimapImpl2;
import com.moonsworth.lunar.client.framework.feature.minimap.MinimapImpl3;
import com.moonsworth.lunar.client.framework.feature.minimap.Minimap_2;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler27;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRunDirectory;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockModified;
import com.moonsworth.lunar.client.event.mixin.fishing.EventChunkLifecycle.EventChunkUnloaded;
import com.moonsworth.lunar.client.event.mixin.fishing.EventChunkLifecycle.EventChunkLoaded;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTickPhase.EventRenderTickFinish;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldLoaded;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerJoinEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump28;
import com.moonsworth.lunar.client.util.ThreadModuleDump38;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.click.Click6;
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

public class Minimap extends AbstractFeature {
   private static final ResourceLocationBridge RECORD_ICON = ResourceLocationBridge.create("lunar", "icons/rewind/record.png");
   public static final int MAX_RENDER_DISTANCE = 14;
   private static final float[] CROSS_LINES = new float[]{-5.0F, 0.0F, 5.0F, 0.0F, 0.0F, -5.0F, 0.0F, 5.0F};
   private final GuiRewindhandlersHandler27 biomeHandler = (GuiRewindhandlersHandler27)this.method19(GuiRewindhandlersHandler27.class);
   private final ToggleOption saveAsPictureFile = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("minimapSaveAsPictureFile")
      .method31();
   private final IntegerOption mapWidth = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "mapWidth"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(8))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 16))
      .method31();
   private final IntegerOption mapHeight = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "mapHeight"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(8))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 16))
      .method31();
   private final FloatOption mapZoom = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "mapZoom"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(1.0F, 2.0F))
      .method31();
   private final EnumOption<Minimap.Type> mapShape = (EnumOption<Minimap.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "mapShape", Minimap.Type.SQUARE
      )
      .method31();
   private final ToggleOption rotateWithPlayer = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("rotateWithPlayer")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption fitTextToWidth = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("fitTextToWidth")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption border = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("border")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption borderColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   private final FloatOption borderThickness = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(1.0F, 5.0F))
      .method31();
   private final ToggleOption compass = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("compass")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption compassColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "compassColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ToggleOption compassShadow = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("compassShadow")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<Minimap.Type3> playerMarkerType = (EnumOption<Minimap.Type3>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "playerMarkerType", Minimap.Type3.TRIANGLE
      )
      .method31();
   private final ColorOption playerMarkerColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "playerMarkerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16776961))
      .method31();
   private final FloatOption playerMarkerSize = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "playerMarkerSize"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(5.0F))
         .method8(1.0F, 10.0F))
      .method31();
   private final EnumOption<Minimap.Type2> entityMarkerType = (EnumOption<Minimap.Type2>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "entityMarkerType", Minimap.Type2.HEAD
      )
      .method31();
   private final FloatOption entityMarkerOpacity = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "entityMarkerOpacity"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final FloatOption entityMarkerSize = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "entityMarkerSize"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(1.0F, 10.0F))
      .method31();
   private final ToggleOption entityMarkerShadow = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("entityMarkerShadow")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showCoordinates = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showCoordinates")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showBiome = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showBiome")
      .method31();
   private final ToggleOption presetBiomeColor = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("presetBiomeColor")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showClock = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showClock")
      .method31();
   private final ToggleOption showAmPm = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showAmPm")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption militaryTime = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("militaryTime")
      .method31();
   private final ModifierKeybindOption fullViewKeybind = (ModifierKeybindOption)com.moonsworth.lunar.client.config.option.OptionFactory.method18("fullView")
      .method31();
   private final ToggleOption showDistantWaypoints = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showDistantWaypoints")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final com.moonsworth.lunar.client.framework.feature.minimap.Minimap minimapManager = new com.moonsworth.lunar.client.framework.feature.minimap.Minimap();
   private final Queue<Itemcounter2> chunkQueue = new ConcurrentLinkedDeque<>();
   private final List<Minimap_2<?>> markerProviders = new ArrayList<>();
   private int lastDimensionId = Integer.MIN_VALUE;
   private String clockText = "";

   public Minimap() {
      super(false);
      this.method13(Framework.field1, new Minimap.Data());
      this.handle(EventChunkLoaded.class, this::method6);
      this.handle(EventChunkUnloaded.class, this::method7);
      this.handle(EventBlockModified.class, this::method8);
      this.handle(ServerJoinEvent.class, this::resetManager);
      this.handle(EventWorldChanged.class, this::method9);
      this.handle(EventWorldLoaded.class, var1 -> {
         this.resetManager(var1);
         this.lastDimensionId = var1.ROHOIOICHOICIOORCHORIOCOCOOCHH() == null ? Integer.MIN_VALUE : var1.ROHOIOICHOICIOORCHORIOCOCOOCHH().bridge$getDimensionId();
      });
      this.handle(EventRenderTickFinish.class, this::method5);
      this.handle(EventRunDirectory.class, Minimap3::method2);
      this.handle(DisconnectEvent.class, var1 -> {
         try {
            if (this.lastDimensionId != Integer.MIN_VALUE) {
               Minimap3.method4(this.minimapManager, this.lastDimensionId);
            }
         } catch (IOException var6) {
            var6.printStackTrace();
         } finally {
            this.lastDimensionId = Integer.MIN_VALUE;
         }
      });
      this.handle(EventClientTick.class, this::method4);
      this.markerProviders.add(new MinimapImpl2(this, this.minimapManager, this.mc));
      this.markerProviders.add(new MinimapImpl3(this, this.minimapManager, this.mc));
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

   public void method2(RootSettingsAssembler var1) {
      var1.method1(
         "general",
         var1x -> {
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.saveAsPictureFile});
            ((SettingsSectionBuilder)var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
                  new OptionSupplier[]{com.moonsworth.lunar.client.config.option.OptionFactory.method14("minimapOpenSaveFolder").method10().method4(this::openSaveFolder)}
               ))
               .method3(() -> !(Boolean)this.saveAsPictureFile.get() || ThreadModuleDump63.method7() == null || !this.isEnabled());
         }
      );
      var1.method1("sizeAndShape", var1x -> {
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.mapWidth});
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.mapHeight}).method3(() -> this.mapShape.get() == Minimap.Type.CIRCLE);
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.mapZoom, this.mapShape, this.rotateWithPlayer, this.fitTextToWidth});
      });
      var1.method1(
         "borderOptions",
         var1x -> var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.border, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.borderColor, this.borderThickness})
         )
      );
      var1.method1(
         "compassOptions",
         var1x -> var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.compass, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.compassColor, this.compassShadow})
         )
      );
      var1.method1(
         "playerMarkerOptions",
         var1x -> {
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.playerMarkerType});
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.playerMarkerColor, this.playerMarkerSize})
               .method3(() -> this.playerMarkerType.get() == Minimap.Type3.NONE || this.playerMarkerType.get() == Minimap.Type3.HEAD);
         }
      );
      ((SettingsSectionImpl)var1.method1(
            "entityMarkerOptions", var1x -> var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.entityMarkerType, var1xx -> {
               var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.entityMarkerOpacity}).method3(() -> this.entityMarkerType.get() == Minimap.Type2.HEAD);
               var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.entityMarkerSize});
               var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.entityMarkerShadow}).method3(() -> this.entityMarkerType.get() == Minimap.Type2.HEAD);
            }, var1xx -> var1xx.method3(() -> this.entityMarkerType.get() == Minimap.Type2.NONE))
         ))
         .method2(() -> !this.method19());
      var1.method1("coordinatesOptions", var1x -> {
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showCoordinates});
         var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showBiome, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.presetBiomeColor}));
      });
      var1.method1(
         "clockOptions",
         var1x -> var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.showClock, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showAmPm, this.militaryTime})
         )
      );
      var1.method1("fullViewOptions", var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.fullViewKeybind}));
      var1.method1("waypointsOptions", var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showDistantWaypoints}));
      this.saveAsPictureFile.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
         if (!var1x) {
            this.minimapManager.method25().reset();
         }
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new Calculator2Handler[]{Calculator2Handler.field3}).method11(this);
   }

   public void method3(boolean var1) {
      if (var1) {
         this.minimapManager.setup();
      } else {
         this.minimapManager.clear();
      }
   }

   private void method4(EventClientTick var1) {
      long var2 = ThreadModuleDump63.method8() == null ? 0L : ThreadModuleDump63.method8().bridge$getDayTime();
      int var4 = (int)(var2 / 24000L);
      int var5 = ((int)((int)((var2 - var4 * 24000L) / 1000L)) + 6) % 24;
      int var6 = (int)(var2 % 1000L / 1000.0 * 60.0);
      String var7;
      if ((Boolean)this.militaryTime.get()) {
         var7 = String.format("%02d", var5) + ":" + String.format("%02d", var6);
      } else {
         int var8 = var5 % 12;
         if (var8 == 0) {
            var8 = 12;
         }

         var7 = String.format("%02d", var8) + ":" + String.format("%02d", var6);
         if ((Boolean)this.showAmPm.get()) {
            if (var5 >= 12) {
               var7 = var7 + " PM";
            } else {
               var7 = var7 + " AM";
            }
         }
      }

      this.clockText = this.method16("worldTime", new Object[]{var4, var7});
   }

   private void method5(EventRenderTickFinish var1) {
      while (!this.chunkQueue.isEmpty()) {
         this.minimapManager.method1(this.chunkQueue.remove());
      }

      this.minimapManager.method5();
   }

   private void method6(EventChunkLoaded var1) {
      this.chunkQueue.add(var1.IIOHICICIRRRHOCCIOORRHHHIHHICR());
   }

   private void method7(EventChunkUnloaded var1) {
      this.minimapManager.method2(var1.IIOHICICIRRRHOCCIOORRHHHIHHICR());
   }

   private void method8(EventBlockModified var1) {
      if (this.mc.bridge$getWorld() != null) {
         Vector3iBridge var2 = var1.method1();
         Itemcounter2 var3 = this.mc.bridge$getWorld().bridge$getChunkFromBlockCoords(new Vector3i(var2.bridge$getX(), var2.bridge$getY(), var2.bridge$getZ()));
         if (var3 != null) {
            this.chunkQueue.add(var3);
         }
      }
   }

   private void method9(EventWorldChanged var1) {
      Itemcounter6Extension var2 = this.mc.bridge$getWorld();
      if (var2 != null) {
         try {
            Minimap3.method3(this.minimapManager, var2.bridge$getDimensionId());
         } catch (IOException var4) {
            var4.printStackTrace();
         }
      }
   }

   private void resetManager(Highlight var1) {
      this.minimapManager.reset();
   }

   public float calculateScale(int var1, int var2, int var3) {
      float var4 = (Float)this.mapZoom.get();
      if ((Boolean)this.rotateWithPlayer.get() && this.mapShape.get() == Minimap.Type.SQUARE) {
         var4 = Math.max(var4, 1.0F);
      }

      float var5 = var4 + 4.5F * (1.0F - var3 / 14.0F);
      float var6 = (this.minimapManager.getTextureWidth() - 16) * var5;
      float var7 = (this.minimapManager.getTextureHeight() - 16) * var5;
      float var8 = var1 / var6;
      float var9 = var2 / var7;
      float var10 = Math.max(var8, var9);
      if (var10 > 1.0F) {
         var5 *= var10;
      }

      return var5;
   }

   public Minimap2_2<?> renderOverlays(MixinHelper_4 var1, float var2, Vector2f var3) {
      int var4 = Math.max(this.minimapManager.method15(), this.minimapManager.method16());
      float var5 = var4 * 16 * var4 * 16;
      Bridge5Extension_5 var6 = this.mc.bridge$getPlayer();
      if (var6 != null && this.mc.bridge$getWorld() != null) {
         Vector2i var7 = new Vector2i(this.minimapManager.method19(), this.minimapManager.method20());
         MutableObject var8 = new MutableObject();

         for (Minimap_2 var10 : this.markerProviders) {
            var10.method1(var6, var5, var7).forEach(var6x -> this.minimapManager.method11(var6x.method8(), var6x.method9(), (var6xx, var7x) -> {
               var6x.method1(var1, var6, var2, var6xx, var7x);
               if (var3 != null) {
                  float var8x = var6x.getWidth();
                  float var9 = var6x.getHeight();
                  if (var3.x >= var6xx - var8x / 2.0F && var3.x <= var6xx + var8x / 2.0F && var3.y >= var7x - var9 / 2.0F && var3.y <= var7x + var9 / 2.0F) {
                     var8.setValue(var6x);
                  }
               }
            }));
         }

         return (Minimap2_2<?>)var8.getValue();
      } else {
         return null;
      }
   }

   private boolean method13(double var1, double var3, float var5, double var6) {
      Minimap.Type var8 = (Minimap.Type)this.mapShape.get();
      double var9 = ((Integer)this.mapWidth.get()).intValue() / 2.0F * 16.0F + var6;
      double var11 = ((Integer)this.mapHeight.get()).intValue() / 2.0F * 16.0F + var6;
      int var13 = (Integer)this.mapWidth.get() * 16;
      int var14 = var8 == Minimap.Type.SQUARE ? (Integer)this.mapHeight.get() * 16 : var13;
      float var15 = this.calculateScale(var13, var14, this.minimapManager.method16());
      var1 *= var15;
      var3 *= var15;
      if (var8 != Minimap.Type.SQUARE) {
         return var1 * var1 + var3 * var3 <= var9 * var9;
      }

      double var16 = Math.cos(var5);
      double var18 = Math.sin(var5);
      double var20 = var1 * var16 - var3 * var18;
      double var22 = var1 * var18 + var3 * var16;
      return var20 >= -var9 && var20 <= var9 && var22 >= -var11 && var22 <= var11;
   }

   public void renderPlayerMarker(MixinHelper_4 var1, float var2, boolean var3) {
      this.renderPlayerMarkerSized(var1, var2, var3, (Float)this.playerMarkerSize.get());
   }

   public void renderPlayerMarkerSized(MixinHelper_4 var1, float var2, boolean var3, float var4) {
      Minimap.Type3 var5 = (Minimap.Type3)this.playerMarkerType.get();
      if (var5.needsRotation) {
         var1.push();
         if (!var3) {
            var1.method42(var2);
         } else {
            var1.method42(180.0F);
         }
      }

      if (var5 == Minimap.Type3.CIRCLE) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method78(var1, 0.0, 0.0, var4, this.playerMarkerColor.method14(0.0F));
      } else if (var5 == Minimap.Type3.TRIANGLE) {
         float var6 = var4 / 5.0F * 8.0F;
         this.method16(var1, var4, var6, this.playerMarkerColor.method14(0.0F));
      } else if (var5 == Minimap.Type3.CROSS) {
         boolean var7 = ThreadModuleDump63.MC_VERSION >= 8 && var4 != 1.0F;
         if (var7) {
            var1.method38(2.5F, 0.0F, 0.0F);
         }

         var1.method32(var4, this.playerMarkerColor.method14(0.0F), var0 -> {
            var0.accept(-5.0F, 0.0F, 5.0F, 0.0F);
            var0.accept(0.0F, -5.0F, 0.0F, 5.0F);
         });
         if (var7) {
            var1.method38(-2.5F, 0.0F, 0.0F);
         }
      } else if (var5 == Minimap.Type3.HEAD && this.mc.bridge$getPlayer() != null) {
         ResourceLocationBridge var8 = this.mc.bridge$getPlayer().bridge$getServerSkinTexture();
         if (var8 != null) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method50(var1, var8, -4.0F, -4.0F, -1, true);
         }
      }

      if (var5.needsRotation) {
         var1.pop();
      }
   }

   public void method16(MixinHelper_4 var1, float var2, float var3, int var4) {
      float var5 = var2 / 2.0F;
      float var6 = var3 / 2.0F;
      var1.method9(LunarRenderTypes.field18, null, -var5, -var6, var2, var3, var3x -> {
         var3x.method5(-var5, -var6).method9(var4).method16();
         var3x.method5(0.0F, var6).method9(var4).method16();
         var3x.method5(var5, -var6).method9(var4).method16();
      });
   }

   private void openSaveFolder() {
      Path var1 = this.minimapManager.method25().method6();
      if (var1 != null) {
         File var2 = var1.toFile().getAbsoluteFile().getParentFile();
         if (!ThreadModuleDump28.method1(var2)) {
            boolean var3 = ThreadModuleDump63.method4().method34().method3();
            ThreadModuleDump63.method4()
               .method69()
               .method7(NotificationType.ERROR, NotificationManager.method15("minimapPopupOpenFolderFailed", new Object[]{var3 ? var2.getName() : var2.getAbsolutePath()}));
         }
      }
   }

   public boolean method19() {
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
   public EnumOption<Minimap.Type2> method24() {
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
   public com.moonsworth.lunar.client.framework.feature.minimap.Minimap getMinimapManager() {
      return this.minimapManager;
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_RIGHT);
      }

      public boolean method31() {
         return !(Boolean)Minimap.this.rotateWithPlayer.get();
      }

      public boolean method4(boolean var1) {
         return ThreadModuleDump63.method11() != MinimapScreen.class && DriverViewportLegacy.method50().method63() != DriverRouteRegistryLegacy.field17
            ? ThreadModuleDump63.method10() != null
            : false;
      }

      public void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
         LegacyGuiGraphicsBridge var5 = var1.method1();
         BridgeExtension var6 = Minimap.this.mc.bridge$getRenderViewEntity();
         Minimap.Type var7 = (Minimap.Type)Minimap.this.mapShape.get();
         boolean var8 = (Boolean)Minimap.this.rotateWithPlayer.get() && var6 != null;
         int var9 = Math.max(2, Math.min(14, Minimap.this.mc.bridge$getGameSettings().bridge$getRenderDistance() * 2));
         Minimap.this.minimapManager.method9(var9, var9);
         int var10 = (Integer)Minimap.this.mapWidth.get() * 16;
         int var11 = var7 == Minimap.Type.SQUARE ? (Integer)Minimap.this.mapHeight.get() * 16 : var10;
         int var12 = var11;
         float var13 = Minimap.this.calculateScale(var10, var11, var9);
         var12 += this.method5();
         this.method58(var10, var12);
         var5.push();
         Click6 var14 = Click6.method8();
         var14.method3(var5);
         var14.method5(var5, var2, var3, var10, var11);
         float var15 = var10 / 2.0F;
         float var16 = var11 / 2.0F;
         var5.method38(var2 + var15, var3 + var16, 0.0F);
         var5.push();
         if (var8) {
            var5.OIRIOIIIRICICCHORICRRIRROHCRIC((float)(180.0 - var6.bridge$getRotationYaw()));
         }

         var5.scale(var13, var13, 1.0F);
         Minimap.this.minimapManager.method3(var5, var6);
         Minimap.this.minimapManager.method4(var5, var6, true);
         Minimap.this.renderOverlays(var5, var8 ? (float)(180.0 - var6.bridge$getRotationYaw()) : 0.0F, null);
         var5.pop();
         if (var7 == Minimap.Type.CIRCLE) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method76(var5, 0.0, 0.0, var15, -1);
         }

         var14.method4(var5);
         MixinHelper_4 var17 = var1.method2();
         var17.push();
         var17.method38(-(var2 + var15), -(var3 + var16), 0.0F);
         var14.method6(var17, var2, var3, var10, var11, this.getScale());
         var17.pop();
         if (Minimap.this.mc.bridge$getPlayer() != null) {
            Minimap.this.renderPlayerMarker(var17, var6 == null ? 0.0F : (float)var6.bridge$getRotationYaw(), var8);
         }

         if ((Boolean)Minimap.this.border.get()) {
            if (var7 == Minimap.Type.SQUARE) {
               Minimap.this.borderColor.method11(var17, this, -var15, -var16, var10, var11, (Float)Minimap.this.borderThickness.get());
            } else {
               com.moonsworth.lunar.client.ui.LcuiScreen.method75(
                  var17, 0.0, 0.0, var15, var15 - (Float)Minimap.this.borderThickness.get(), Minimap.this.borderColor.method14(0.0F)
               );
            }
         }

         if ((Boolean)Minimap.this.showDistantWaypoints.get()) {
            this.method6(var17, var6);
         }

         boolean var18 = ReducedDebugInfoNotifier.method1() && !var4;
         if (!var18 && (Boolean)Minimap.this.compass.get()) {
            this.method7(var17, var6, var4);
         }

         var5.pop();
         TextComponent var19 = null;
         TextComponent var20 = null;
         TextComponent var21 = Minimap.this.showClock.get() ? Component.text(Minimap.this.clockText) : null;
         if (!var18 && (Boolean)Minimap.this.showCoordinates.get()) {
            String var22;
            if (var6 != null && !var4) {
               var22 = String.format("%.2f, %.2f, %.2f", var6.bridge$getPosX(), var6.bridge$getPosY(), var6.bridge$getPosZ());
            } else {
               var22 = String.format("%.2f, %.2f, %.2f", 0.0F, 0.0F, 0.0F);
            }

            var19 = Component.text(var22);
         }

         if (!var18 && (Boolean)Minimap.this.showBiome.get()) {
            com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler27.Data3 var24;
            if (var4) {
               var24 = new com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler27.Data3(
                  ItemcounterType_2.PLAINS, Bridge.method8().method71("biome.minecraft.plains")
               );
            } else {
               var24 = Minimap.this.biomeHandler.method5();
            }

            var20 = Component.text(var24.name());
            if ((Boolean)Minimap.this.presetBiomeColor.get()) {
               var20 = (TextComponent)var20.color(TextColor.color(var24.method1()));
            }
         }

         if ((Boolean)Minimap.this.saveAsPictureFile.get()) {
            var17.push();
            var17.method38(var2 + var10 - 10.0F, var3 + 2.0F, 0.0F);
            var17.scale(0.5F, 0.5F, 0.5F);
            com.moonsworth.lunar.client.ui.LcuiScreen.method46(
               var17, Minimap.RECORD_ICON, 0.0F, 0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F, 16.0F, -65536
            );
            var17.pop();
         }

         this.method4(var17, var2, var3 + var11 + 3.0F, var10, var19, var21, var20);
      }

      private void method4(MixinHelper_4 var1, float var2, float var3, float var4, TextComponent... var5) {
         for (TextComponent var9 : var5) {
            if (var9 != null) {
               float var10 = ThreadModuleDump63.method10().bridge$getStringWidth(var9);
               var1.push();
               var1.method38(var2 + this.getWidth() / 2.0F, var3, 0.0F);
               if (var10 > var4 && (Boolean)Minimap.this.fitTextToWidth.get()) {
                  float var11 = var4 / var10;
                  var1.scale(var11, var11, 1.0F);
                  var3 += (ThreadModuleDump63.method10().method19() + 1) * var11;
               } else {
                  var3 += ThreadModuleDump63.method10().method19() + 0.5F;
               }

               var1.method11(ThreadModuleDump63.method10(), var9, -var10 / 2.0F - 0.5F, 0.0F, -1, true);
               var1.pop();
            }
         }
      }

      private int method5() {
         int var1 = 0;
         int var2 = ThreadModuleDump63.method10().method19();
         if ((Boolean)Minimap.this.showCoordinates.get()) {
            var1 += var2 + 1;
         }

         if ((Boolean)Minimap.this.showBiome.get()) {
            var1 += var2 + 1;
         }

         if ((Boolean)Minimap.this.showClock.get()) {
            var1 += var2 + 1;
         }

         return var1 == 0 ? 0 : var1 + 1;
      }

      private void method6(MixinHelper_4 var1, BridgeExtension var2) {
         Bridge10_2 var3 = ThreadModuleDump63.method10();
         boolean var4 = (Boolean)Minimap.this.compassShadow.get();
         float var5 = var2 == null ? 0.0F : (float)var2.bridge$getRotationYaw();
         float var6 = this.getHeight() - this.method5();
         double var7 = Minimap.this.minimapManager.method19() + Minimap.this.minimapManager.method13();
         double var9 = Minimap.this.minimapManager.method20() + Minimap.this.minimapManager.method14();
         float var11;
         if ((Boolean)Minimap.this.rotateWithPlayer.get()) {
            var11 = (float)Math.toRadians(var5);
         } else {
            var11 = (float) Math.PI;
         }

         Minimap.this.field4.method48().IIORHHIRHIORHRCCCOICCRCHRRCCRH().stream().filter(var6x -> {
            boolean var7x = var6x.isVisible();
            boolean var8 = var6x.shouldRender();
            if (var7x && var8) {
               Vec3Bridge var9x = var6x.method35();
               return !Minimap.this.method13(var9x.bridge$xCoord() - var7, var9x.bridge$zCoord() - var9, -var11, 6.0);
            } else {
               return false;
            }
         }).forEach(var10 -> {
            float var11x = (float)(var10.method35().bridge$xCoord() - var7);
            float var12 = (float)(var10.method35().bridge$zCoord() - var9);
            float var13 = (float)Math.atan2(var12, var11x) - var11;
            if (Minimap.this.mapShape.get() == Minimap.Type.CIRCLE) {
               this.method9(var1, var3, var10.getLabel(), this.getWidth() / 2.0F - 1.0F, (float)(var13 + Math.PI), var10.method46().method4(), var4);
            } else {
               var13 = (float)Math.toDegrees(var13) - 90.0F;
               var13 += 45.0F;
               var13 = (var13 % 360.0F + 360.0F) % 360.0F;
               float var14 = var13 % 90.0F / 90.0F;
               this.method8(var1, var10.method46().method4(), var10.getLabel(), var14, var13, var6, var4);
            }
         });
      }

      private void method7(MixinHelper_4 var1, BridgeExtension var2, boolean var3) {
         boolean var4 = (Boolean)Minimap.this.compassShadow.get();
         Bridge10_2 var5 = ThreadModuleDump63.method10();
         if (var5 != null) {
            float var6 = this.getHeight() - this.method5();
            float var7 = this.getWidth();
            if (!(Boolean)Minimap.this.rotateWithPlayer.get()) {
               float var8 = var5.method19() / 2.0F;
               Minimap.this.compassColor.method11(var1, "N", 0.0F, -var6 / 2.0F - var8, var4);
               Minimap.this.compassColor.method11(var1, "W", -var7 / 2.0F, -var8, var4);
               Minimap.this.compassColor.method11(var1, "S", 0.0F, var6 / 2.0F + 1.0F - var8, var4);
               Minimap.this.compassColor.method11(var1, "E", var7 / 2.0F, -var8, var4);
            } else if (Minimap.this.mapShape.get() == Minimap.Type.CIRCLE) {
               float var14 = var7 / 2.0F;
               float var9 = var2 != null && !var3 ? (float)var2.bridge$getRotationYaw() : 0.0F;
               float var10 = (float)Math.toRadians(-var9 + 90.0F);
               float var11 = (float)Math.toRadians(-var9 + 180.0F);
               float var12 = (float)Math.toRadians(-var9 + 270.0F);
               float var13 = (float)Math.toRadians(-var9);
               this.method9(var1, var5, "N", var14 - 1.0F, var10, Minimap.this.compassColor, var4);
               this.method9(var1, var5, "E", var14 - 1.0F, var11, Minimap.this.compassColor, var4);
               this.method9(var1, var5, "S", var14 - 1.0F, var12, Minimap.this.compassColor, var4);
               this.method9(var1, var5, "W", var14 - 1.0F, var13, Minimap.this.compassColor, var4);
            } else {
               float var15 = var2 != null && !var3 ? (float)var2.bridge$getRotationYaw() : 0.0F;
               var15 = -var15;
               var15 += 45.0F;
               var15 -= 180.0F;
               var15 = (var15 % 360.0F + 360.0F) % 360.0F;
               float var20 = var15 % 90.0F / 90.0F;
               this.method8(var1, Minimap.this.compassColor, "N", var20, var15, var6, var4);
               this.method8(var1, Minimap.this.compassColor, "E", var20, var15 + 90.0F, var6, var4);
               this.method8(var1, Minimap.this.compassColor, "S", var20, var15 + 180.0F, var6, var4);
               this.method8(var1, Minimap.this.compassColor, "W", var20, var15 + 270.0F, var6, var4);
            }
         }
      }

      private void method8(MixinHelper_4 var1, ColorOption var2, String var3, float var4, float var5, float var6, boolean var7) {
         float var8 = this.getWidth();
         float var9 = var8 / 2.0F;
         float var10 = var6 / 2.0F;
         var5 = (var5 % 360.0F + 360.0F) % 360.0F;
         float var11;
         float var12;
         if (var5 < 90.0F) {
            var11 = -var9 + var4 * var8;
            var12 = -var10 - 4.0F;
         } else if (var5 < 180.0F) {
            var11 = var9;
            var12 = -var10 - 4.0F + var4 * var6;
         } else if (var5 < 270.0F) {
            var11 = var9 - var4 * var8;
            var12 = var10 - 4.0F;
         } else {
            var11 = -var9;
            var12 = var10 - 4.0F - var4 * var6;
         }

         var2.method11(var1, var3, var11, var12, var7);
      }

      private void method9(MixinHelper_4 var1, Bridge10_2 var2, String var3, float var4, float var5, ColorOption var6, boolean var7) {
         float var8 = (float)(var4 * ThreadModuleDump38.method1(var5) - var2.bridge$getStringWidth(var3) / 2.0F);
         float var9 = (float)(var4 * ThreadModuleDump38.sin(var5) - var2.method19() / 2.0F);
         var1.method19(var2, var3, var8, var9, var6.method14(0.0F), var7);
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
      Type(String var3) {
         this.id = var3;
      }
   }

   public enum Type2 implements OptionEnumValue {
      NONE("none", false),
      CIRCLE("circle", false),
      TRIANGLE("triangle", true),
      HEAD("head", false);

      private final String id;
      public final boolean needsRotation;

      Type2(String var3, boolean var4) {
         this.id = var3;
         this.needsRotation = var4;
      }

      public String id() {
         return this.id;
      }
   }

   private enum Type3 implements OptionEnumValue {
      NONE("none", false),
      CIRCLE("circle", false),
      TRIANGLE("triangle", true),
      CROSS("cross", false),
      HEAD("head", false);

      private final String id;
      private final boolean needsRotation;

      Type3(String var3, boolean var4) {
         this.id = var3;
         this.needsRotation = var4;
      }

      public String id() {
         return this.id;
      }
   }
}
