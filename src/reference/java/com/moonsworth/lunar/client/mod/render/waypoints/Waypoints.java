package com.moonsworth.lunar.client.mod.render.waypoints;

import com.lunarclient.apollo.module.waypoint.WaypointModule;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.optifine.ShadersBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiLoader;
import com.moonsworth.lunar.client.event.player.EventLocalPlayerDeath;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerJoin;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase.EventRenderHudFocused;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.PhosphorIcon;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.util.math.FastMath;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.ScreenProjection;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.client.ui.GuiResolution;
import com.moonsworth.lunar.client.util.math.Vector2dPair;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2d;

public class Waypoints extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("waypointBeams")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIcon.PI_EYE_ON_STROKE))
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("boxBorder")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIcon.PI_EYE_ON_STROKE))
      .method31();
   private final FloatOption field10 = (FloatOption)((Data)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("boxPadding")
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(4.0F))
            .method8(1.0F, 8.0F))
         .method15(PhosphorIcon.PI_ALIGN_VERTICAL_CENTER_STROKE))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
               "highlightWaypointBlock"
            )
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIcon.PI_EYE_ON_STROKE))
      .method31();
   private final FloatOption field12 = (FloatOption)((Data)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "highlightWaypointBlockLineWidth"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(4.0F))
            .method8(1.5F, 7.5F))
         .method15(PhosphorIcon.PI_ALIGN_VERTICAL_CENTER_STROKE))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
               "waypointShowDistance"
            )
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIcon.PI_EYE_ON_STROKE))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("textShadow")
         .method15(PhosphorIcon.PI_TEXT_CURSOR_ALPHABET_STROKE))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("deathWaypoint")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIcon.PI_DANGER_SKULL_STROKE))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
               "limitDeathWaypoints"
            )
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIcon.PI_DANGER_SKULL_STROKE))
      .method31();
   private final IntegerOption field17 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
                  "maxDeathWaypoints"
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
            .method7(1, 20))
         .method15(PhosphorIcon.PI_DANGER_SKULL_STROKE))
      .method31();
   private final SimpleKeybindOption field18 = (SimpleKeybindOption)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method17(
                     "addWaypoint"
                  )
                  .method2(KeyCode.KEY_N))
               .method18(this))
            .method15(PhosphorIcon.PI_PLUS_SQUARE_STROKE))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final SimpleKeybindOption field19 = (SimpleKeybindOption)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method17(
                  "quickAddWaypoint"
               )
               .method18(this))
            .method15(PhosphorIcon.PI_PLUS_SQUARE_STROKE))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final SimpleKeybindOption field20 = (SimpleKeybindOption)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method17(
                     "toggleWaypoints"
                  )
                  .method2(KeyCode.KEY_M))
               .method18(this))
            .method15(PhosphorIcon.PI_EYE_ON_STROKE))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("onlyShowWhenLookingNear")
         .method15(PhosphorIcon.PI_EYE_ON_STROKE))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showIcons")
         .method15(PhosphorIcon.PI_EYE_ON_STROKE))
      .method31();
   private final FloatOption field23 = (FloatOption)((Data)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("labelScale")
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(0.1F, 2.0F))
         .method15(PhosphorIcon.PI_MAXIMIZE_LINE_ARROW_STROKE))
      .method31();
   private final FloatOption field24 = (FloatOption)((Data)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("iconScale")
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.5F))
            .method8(0.1F, 3.0F))
         .method15(PhosphorIcon.PI_MAXIMIZE_LINE_ARROW_STROKE))
      .method31();
   private final TextOption field25 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method12(
            "quickWaypointNameFormat"
         )
         .method2("Quick Waypoint"))
      .method31();
   private final ToggleOption field26 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
               "addWaypointsFromChat"
            )
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIcon.PI_CHAT_DEFAULT_STROKE))
      .method31();
   private final ToggleOption field27 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("confirmDelete")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIcon.PI_CHECK_TICK_SQUARE_STROKE))
      .method31();
   private boolean field28 = true;
   private long field29;
   private final List<Vector2dPair<Waypoint>> field30 = new ArrayList<>();

   public Waypoints() {
      super(true);
      this.handle(HudRenderLegacyEvent.class, this::method5);
      this.handle(HudRenderLegacyEventAlt.class, this::method1);
      this.handle(EventRenderHudFocused.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventTick.class, this::method4);
      this.handle(EventLocalPlayerDeath.class, this::method3);
      this.handle(EventServerJoin.class, this::method7);
      this.field18.method5(arg0 -> method21());
      this.field19.method5(arg1 -> this.method17());
      this.field20.method5(arg1 -> this.field28 = !this.field28);
   }

   private void method1(HudRenderLegacyEventAlt highlightimpl41) {
      for (Waypoint guihandler23 : this.method13()) {
         this.method2(highlightimpl41.method3(), guihandler23);
      }
   }

   private void method2(AbstractRenderContext bridgeextension_91, Waypoint guihandler22) {
      if ((Boolean)this.field11.get()) {
         boolean flag3;
         if (guihandler22.method44() != null && guihandler22.method44().method24()) {
            flag3 = guihandler22.method44().method23().isHighlightBlock();
         } else {
            flag3 = guihandler22.method46().isHighlightBlock();
         }

         if (flag3) {
            Vec3Bridge horsestats154 = guihandler22.method35();
            bridgeextension_91.push();
            EntityRenderDispatcherBridge bridge2_435 = Ref.method13();
            bridgeextension_91.translate(-bridge2_435.bridge$renderPosX(), -bridge2_435.bridge$renderPosY(), -bridge2_435.bridge$renderPosZ());
            int number6 = (int)Math.floor(horsestats154.bridge$xCoord());
            int number7 = (int)Math.floor(horsestats154.bridge$yCoord() - 1.0);
            int number8 = (int)Math.floor(horsestats154.bridge$zCoord());
            int number9;
            if (guihandler22.method44() != null && guihandler22.method44().method24()) {
               number9 = guihandler22.method44().method23().method4().method14(0.0F);
            } else {
               number9 = guihandler22.method46().method4().method14(0.0F);
            }

            Float value11;
            if (guihandler22.method44() != null && guihandler22.method44().method24()) {
               value11 = guihandler22.method44().method23().method5();
            } else {
               value11 = guihandler22.method46().method5();
            }

            float value10 = value11 != null ? value11 : (Float)this.field12.get();
            WorldRenderUtils.drawFancyBox(bridgeextension_91, AxisAlignedBBBridge.method2(number6, number7, number8, number6 + 1, number7 + 1, number8 + 1), 570425344 | 16777215 & number9, true, value10);
            bridgeextension_91.pop();
         }
      }
   }

   public String getId() {
      return "WAYPOINTS";
   }

   private void method3(EventLocalPlayerDeath highlightimpl61) {
      if (System.currentTimeMillis() - this.field29 >= 1000L) {
         this.field29 = System.currentTimeMillis();
         WaypointStore holograms52 = Client.method109().method48();
         Waypoint guihandler23 = Waypoint.method18()
            .method2(this.method4("playerDeath", new Object[0]) + holograms52.method31().getAndIncrement())
            .method3(
               Vec3Bridge.method2(
                  Ref.method7().bridge$getPosX(),
                  Ref.method7().bridge$getBoundingBox().bridge$getMinY(),
                  Ref.method7().bridge$getPosZ()
               )
            )
            .method4(Client.method109().getWorld())
            .method5(Ref.method8().bridge$getDimensionId())
            .method12(WaypointStore.method19())
            .method13(
               Ref.method4()
                  .method84()
                  .method3(WaypointModule.class)
                  .map(arg0 -> (Boolean)arg0.getOptions().get(WaypointModule.SERVER_HANDLES_WAYPOINTS))
                  .orElse(false)
            )
            .method8(true)
            .method18(new GuiLoader())
            .method19();
         guihandler23.method46().method4().method17();
         holograms52.method6(guihandler23);
         this.method15();
      }
   }

   private void method4(com.moonsworth.lunar.client.event.mixin.fishing.EventTick highlightimpl21) {
      BridgeExtension bridgeextension2 = this.mc.bridge$getRenderViewEntity();
      if (bridgeextension2 != null) {
         for (Waypoint guihandler25 : new ArrayList(Client.method109().method48().IIORHHIRHIORHRCCCOICCRCHRRCCRH())) {
            if (guihandler25.isVisible() && guihandler25.shouldRender()) {
               guihandler25.setDistance(method21(guihandler25, bridgeextension2));
            }
         }
      }
   }

   private void method5(HudRenderLegacyEvent highlightimpl21) {
      this.field30.clear();
      if (this.field28) {
         List list2 = this.method13();

         for (Waypoint guihandler24 : list2) {
            GuiLoader guiloader5 = guihandler24.method46();
            if (guiloader5.isShowText()) {
               com.moonsworth.lunar.client.framework.feature.waypoints.GuiLoader.Data data6 = guiloader5.method6();
               Vec3Bridge horsestats157 = guihandler24.method35();
               boolean flag8 = data6 != null ? data6.isOnlyShowTextWhenLookingNear() : (Boolean)this.field21.get();
               if (!flag8 || this.method14(Ref.method7(), guihandler24)) {
                  Vector2d vector2d9 = ScreenProjection.worldToScreen(horsestats157.bridge$xCoord(), horsestats157.bridge$yCoord() + 2.0, horsestats157.bridge$zCoord());
                  if (vector2d9 != null) {
                     this.field30.add(new Vector2dPair(vector2d9, guihandler24));
                  }
               }
            }
         }

         if ((Boolean)this.field8.get()) {
            Bridge.method5().ifPresent(arg0 -> {
               if (arg0.getConfig().hasShaders()) {
                  ShadersBridge slayer31x = arg0.getShaders();
                  slayer31x.pushUseProgram(slayer31x.getProgramBasic());
               }
            });
            ExternalLinkRegistry.method2(Fishing2Extension.class).ifPresent(Fishing2Extension::beginBeacon);
            AbstractRenderContext bridgeextension_910 = highlightimpl21.method3();

            for (Waypoint guihandler212 : list2) {
               this.method12(bridgeextension_910, guihandler212, guihandler212.getDistance());
            }

            ExternalLinkRegistry.method2(Fishing2Extension.class).ifPresent(Fishing2Extension::endBeacon);
            Bridge.method5().ifPresent(arg0 -> {
               if (arg0.getConfig().hasShaders()) {
                  ShadersBridge slayer31x = arg0.getShaders();
                  slayer31x.popProgram();
               }
            });
            bridgeextension_910.method33();
         }
      }
   }

   private void method6(EventRenderHudBase highlightimpl1) {
      GuiResolution threadmoduledump712 = LcuiScreen.method151();
      double value3 = threadmoduledump712.method3();

      for (Vector2dPair threadmoduledump816 : this.field30) {
         this.method8(highlightimpl1.method2(), Ref.method10(), threadmoduledump816, value3);
      }
   }

   private void method7(EventServerJoin highlightimpl161) {
      GuiHandler guihandler2 = this.method19();
      if (guihandler2 != null) {
         guihandler2.setServer(WaypointStore.method19());
         Client.method109().method48().method20();
      }
   }

   private void method8(MixinHelper_4 mixinhelper_41, Bridge10_2 bridge10_22, Vector2dPair<Waypoint> threadmoduledump813, double value4) {
      Waypoint guihandler26 = (Waypoint)threadmoduledump813.getValue();
      Vector2d vector2d7 = threadmoduledump813.getPosition();
      double value8 = guihandler26.getDistance();
      StringBuilder builder10 = new StringBuilder();
      if (!guihandler26.getName().isBlank()) {
         builder10.append(guihandler26.getName());
      }

      if (this.method9(guihandler26)) {
         if (!guihandler26.getName().isBlank()) {
            builder10.append(" ");
         }

         builder10.append("[").append((int)value8).append("m]");
      }

      String text11 = builder10.toString();
      float value12 = this.method10(value4, bridge10_22.bridge$getStringWidth(text11) / 2.0F);
      mixinhelper_41.push();
      mixinhelper_41.method38(this.method10(value4, vector2d7.x), this.method10(value4, vector2d7.y), 0.0F);
      float value13 = this.field4.method40().method50().method3(mixinhelper_41.method43());
      mixinhelper_41.method40(1.0F / value13, 1.0F / value13);
      com.moonsworth.lunar.client.framework.feature.waypoints.GuiLoader.Data data14 = guihandler26.method46().method6();
      float value15 = data14 != null ? data14.getLabelScale() : (Float)this.field23.get();
      mixinhelper_41.method40(value15, value15);
      float value16 = data14 != null ? data14.getBoxPadding() : (Float)this.field10.get();
      float value17 = -value12 - value16;
      float value18 = -value16;
      float value19 = value12 * 2.0F + value16 * 2.0F;
      float value20 = 8.0F + value16 * 2.0F;
      float value21 = this.method10(value4, value17);
      float value22 = this.method10(value4, value18);
      int number23;
      if (guihandler26.method44() != null && guihandler26.method44().method24()) {
         number23 = guihandler26.method44().method23().method4().method14(0.0F);
      } else {
         number23 = guihandler26.method46().method4().method14(0.0F);
      }

      float value24 = (float)Math.max(0.0, Math.min(10.0, method21(guihandler26, Ref.method7()) - 4.0)) / 10.0F;
      int number25 = (int)(0.4F * value24 * 255.0F) << 24;
      boolean flag26 = data14 != null ? data14.isBoxBorders() : (Boolean)this.field9.get();
      float value27 = 0.5F;
      int number28 = (int)(value24 * 255.0F) << 24 | number23 & 16777215;
      if (flag26) {
         LcuiScreen.method100(mixinhelper_41, value21, value18, value19, value20, value27, number28, number25);
      } else {
         LcuiScreen.method94(mixinhelper_41, value21, value22, value19, value20, number25);
      }

      boolean flag29 = data14 != null ? data14.isTextShadow() : (Boolean)this.field14.get();
      int number30 = (int)Math.max(4.0F, value24 * 255.0F) << 24 | 16777215;
      mixinhelper_41.method19(Ref.method10(), text11, -value12, 0.0F, number30, flag29);
      boolean flag31 = data14 != null ? data14.isShowIcons() : (Boolean)this.field22.get();
      if (flag31) {
         float value32 = data14 != null ? data14.getTextIconScale() : (Float)this.field24.get();
         mixinhelper_41.method40(1.0F / value15, 1.0F / value15);
         mixinhelper_41.method40(value32, value32);
         String text33 = Character.toString(Character.toUpperCase(text11.charAt(0)));
         float value34 = this.method10(value4, bridge10_22.bridge$getStringWidth(text33) / 2.0F);
         float value35 = value34 * 2.0F + value16 * 2.0F;
         float value36 = value18 - value20;
         float value37 = -value34 - value16;
         if (flag26) {
            value36 -= value27;
            LcuiScreen.method100(mixinhelper_41, value37, value36, value35, value20, value27, number28, number25);
         } else {
            LcuiScreen.method94(mixinhelper_41, value37, value36, value35, value20, number25);
         }

         mixinhelper_41.method19(Ref.method10(), text33, -value34, -value20, number30, flag29);
      }

      mixinhelper_41.pop();
   }

   private boolean method9(Waypoint guihandler21) {
      if (!(Boolean)this.field13.get()) {
         return false;
      } else {
         return guihandler21.method44() != null && guihandler21.method44().method24() ? guihandler21.method44().method23().isShowDistance() : guihandler21.method46().isShowDistance();
      }
   }

   private float method10(double value1, double value3) {
      return (float)(Math.round(value3 * value1) / value1);
   }

   private List<Waypoint> method13() {
      if (this.mc.bridge$getRenderViewEntity() == null) {
         return List.of();
      }

      if (Ref.method4().method40().method64().method13()) {
         return List.of();
      }

      ArrayList list1 = new ArrayList(Client.method109().method48().IIORHHIRHIORHRCCCOICCRCHRRCCRH());
      Iterator iterator2 = list1.iterator();

      while (iterator2.hasNext()) {
         Waypoint guihandler23 = (Waypoint)iterator2.next();
         if (guihandler23.isVisible() && guihandler23.shouldRender()) {
            if (guihandler23.getDistance() == -1.0) {
               guihandler23.setDistance(method21(guihandler23, this.mc.bridge$getRenderViewEntity()));
            }
         } else {
            iterator2.remove();
         }
      }

      return list1;
   }

   private void method12(AbstractRenderContext bridgeextension_91, Waypoint guihandler22, double value3) {
      EntityRenderDispatcherBridge bridge2_435 = this.mc.bridge$getEntityRenderDispatcher();
      double value6 = bridge2_435.bridge$renderPosX();
      double value8 = bridge2_435.bridge$renderPosY();
      double value10 = bridge2_435.bridge$renderPosZ();
      bridgeextension_91.push();
      double value12 = (float)guihandler22.method35().bridge$xCoord() - value6;
      double value14 = (float)guihandler22.method35().bridge$zCoord() - value10;
      bridgeextension_91.translate((float)value12, (float)(-value8), (float)value14);
      DrawBufferBridge bridge2_3216 = bridgeextension_91.method10(LunarRenderTypes.field49);
      int number17;
      if (guihandler22.method44() != null && guihandler22.method44().method24()) {
         number17 = guihandler22.method44().method23().method4().method14(0.0F);
      } else {
         number17 = guihandler22.method46().method4().method14(0.0F);
      }

      float value18 = (number17 >> 16 & 0xFF) / 255.0F;
      float value19 = (number17 >> 8 & 0xFF) / 255.0F;
      float value20 = (number17 & 0xFF) / 255.0F;
      boolean flag21;
      if (guihandler22.method44() != null && guihandler22.method44().method24()) {
         flag21 = guihandler22.method44().method23().isShowBeam();
      } else {
         flag21 = guihandler22.method46().isShowBeam();
      }

      if (flag21) {
         float value22 = 0.75F;
         float value23 = 0.0F;
         float value24 = (float)MathUtils.method2(
            Math.abs(this.mc.bridge$getWorld().bridge$getMinBuildHeight()) + Ref.method7().bridge$getPosY() + 50.0,
            guihandler22.method35().bridge$yCoord(),
            256.0
         );
         byte number25 = 20;
         byte number26 = 1;
         float value27 = (float)Math.max(0.0, Math.min(60.0, value3 - 4.0)) / 60.0F;
         float value28 = 0.6F * value27;
         float value29 = (float) (Math.PI * 2) / number25;
         float value30 = (value23 - value22) / number26;
         float value31 = value24 / number26;
         float value32 = value22;
         float value33 = this.mc.bridge$getWorld().bridge$getMinBuildHeight();

         for (int index34 = 0; index34 < number26; index34++) {
            bridge2_3216.method1();

            for (int index35 = 0; index35 < number25; index35++) {
               float value36 = (float)FastMath.sin(index35 * value29);
               float value37 = (float)FastMath.method1(index35 * value29);
               float value38 = (float)FastMath.sin((index35 + 1) * value29);
               float value39 = (float)FastMath.method1((index35 + 1) * value29);
               bridge2_3216.method2(value36 * (value32 + value30), value33 + value31, value37 * (value32 + value30)).method8(value18, value19, value20, value28).method16();
               bridge2_3216.method2(value36 * value32, value33, value37 * value32).method8(value18, value19, value20, value28).method16();
               bridge2_3216.method2(value38 * value32, value33, value39 * value32).method8(value18, value19, value20, value28).method16();
            }

            bridge2_3216.method17(BufferMode.BATCHED);
            value32 += value30;
            value33 += value31;
         }
      }

      boolean flag41;
      if (guihandler22.method44() != null && guihandler22.method44().method24()) {
         flag41 = guihandler22.method44().method23().isHighlightBlock();
      } else {
         flag41 = guihandler22.method46().isHighlightBlock();
      }

      if (!flag41) {
         bridge2_3216 = bridgeextension_91.method10(LunarRenderTypes.field49);
         bridgeextension_91.translate(0.0, (float)(guihandler22.method35().bridge$yCoord() + 0.001F), 0.0);
         this.method13(bridge2_3216, 0.85F, 0.78F, 52, value18, value19, value20, 0.85F);
      }

      bridgeextension_91.pop();
   }

   private void method13(DrawBufferBridge bridge2_321, float value2, float value3, int number4, float value5, float value6, float value7, float value8) {
      float value9 = 0.0F;
      float value10 = -((float)((Math.PI * 2) / number4));
      float value11 = 0.0F;
      float value12 = 0.0F;
      bridge2_321.method1();

      for (int index13 = 0; index13 < number4; index13++) {
         float value14 = (float)FastMath.method1(value9);
         float value15 = (float)FastMath.sin(value9);
         float value16 = (float)FastMath.method1(value9 + value10);
         float value17 = (float)FastMath.sin(value9 + value10);
         value9 += value10;
         bridge2_321.method2(value11 + value14 * value3, 0.0, value12 + value15 * value3)
            .method8(value5, value6, value7, value8)
            .method16()
            .method2(value11 + value14 * value2, 0.0, value12 + value15 * value2)
            .method8(value5, value6, value7, value8)
            .method16()
            .method2(value11 + value16 * value3, 0.0, value12 + value17 * value3)
            .method8(value5, value6, value7, value8)
            .method16()
            .method2(value11 + value16 * value3, 0.0, value12 + value17 * value3)
            .method8(value5, value6, value7, value8)
            .method16()
            .method2(value11 + value14 * value2, 0.0, value12 + value15 * value2)
            .method8(value5, value6, value7, value8)
            .method16()
            .method2(value11 + value16 * value2, 0.0, value12 + value17 * value2)
            .method8(value5, value6, value7, value8)
            .method16();
      }

      bridge2_321.method17(BufferMode.BATCHED);
   }

   private boolean method14(Bridge6_10 bridge6_101, Waypoint guihandler22) {
      float value3 = 20.0F;
      Vec3Bridge horsestats154 = guihandler22.method35();
      double value5 = horsestats154.bridge$xCoord() - bridge6_101.bridge$getPosX();
      double value7 = horsestats154.bridge$yCoord() - bridge6_101.bridge$getPosY() - bridge6_101.bridge$getEyeHeight() + 2.0;
      double value9 = horsestats154.bridge$zCoord() - bridge6_101.bridge$getPosZ();
      double value11 = Math.sqrt(value5 * value5 + value9 * value9);
      float value13 = MathUtils.method13((float)Math.toDegrees(Math.atan2(-value5, value9)));
      float value14 = (float)(-Math.toDegrees(Math.atan2(value7, value11)));
      float value15 = MathUtils.method13(bridge6_101.bridge$getRotationYawHead());
      return Math.abs(MathUtils.method13(value13 - value15)) <= value3 && Math.abs(value14 - bridge6_101.bridge$getRotationPitch()) <= value3;
   }

   private void method15() {
      if ((Boolean)this.field16.get()) {
         List list1 = Client.method109()
            .method48()
            .IIORHHIRHIORHRCCCOICCRCHRRCCRH()
            .stream()
            .filter(Waypoint::method38)
            .sorted(Comparator.comparingLong(Waypoint::method42))
            .toList();
         int number2 = list1.size() - (Integer)this.field17.get();
         if (number2 > 0) {
            for (int index3 = 0; index3 < number2; index3++) {
               Client.method109().method48().method9((Waypoint)list1.get(index3));
            }
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.field26, this.field27, this.field25});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field15, arg1xx -> arg1xx.method9(new ClientOption[]{this.field16, this.field17})
            );
         }
      );
      lightingextension231.method7(
         "renderOptions",
         PhosphorIcon.PI_MONITOR01_SOLID,
         arg1x -> arg1x.method9(
            new ClientOption[]{
               this.field8,
               this.field9,
               this.field14,
               this.field10,
               this.field22,
               this.field23,
               this.field24,
               this.field11,
               this.field12,
               this.field13,
               this.field21
            }
         )
      );
      lightingextension231.method7(
         SettingsPage.CONTROLS, arg1x -> arg1x.method9(new ClientOption[]{this.field20, this.field18, this.field19})
      );
      this.field17.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method15());
      this.field25.method3(this.method4("quickWaypoint", new Object[0]));
   }

   public void method17() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 != null) {
         WaypointStore holograms52 = Client.method109().method48();
         String text3 = this.method4("quickWaypoints", new Object[0]);
         GuiHandler guihandler4 = Optional.ofNullable(this.method19()).orElseGet(() -> {
            GuiHandler guihandler2x = GuiHandler.method13().method2(text3).method3(WaypointStore.method19()).method5(0).method11();
            holograms52.method25(guihandler2x);
            return guihandler2x;
         });
         String text5 = this.method18(bridge5extension_51);
         Waypoint guihandler26 = Waypoint.method18()
            .method16(guihandler4)
            .method3(Vec3Bridge.method2(bridge5extension_51.bridge$getPosX(), bridge5extension_51.bridge$getBoundingBox().bridge$getMinY(), bridge5extension_51.bridge$getPosZ()))
            .method4(Client.method109().getWorld())
            .method5(Ref.method8().bridge$getDimensionId())
            .method12(WaypointStore.method19())
            .method13(false)
            .method18(new GuiLoader((Boolean)this.field8.get(), PhosphorIcon.PI_MAP_PIN_SOLID, (Boolean)this.field11.get(), true, (Boolean)this.field13.get()))
            .method19();
         Set set7 = holograms52.method13();
         String text8 = text5;

         for (int index9 = 0; index9 < 100; index9++) {
            for (Waypoint guihandler211 : set7) {
               if (guihandler211.getName().equals(text8)) {
                  text8 = text5 + " (" + (index9 + 1) + ")";
                  break;
               }
            }
         }

         guihandler26.setName(text8);
         guihandler26.method46().method4().method17();
         holograms52.method6(guihandler26);
      }
   }

   private String method18(Bridge5Extension_5 bridge5extension_51) {
      String text2 = bridge5extension_51.bridge$getWorld().bridge$getDimensionKey();
      String[] items3 = text2.split(":");
      if (items3.length > 1) {
         text2 = items3[1];
      }

      text2 = Character.toUpperCase(text2.charAt(0)) + text2.substring(1);
      return ((String)this.field25.get())
         .replaceAll("\\$X", String.valueOf(bridge5extension_51.bridge$getBlockX()))
         .replaceAll("\\$Y", String.valueOf(bridge5extension_51.bridge$getBlockY()))
         .replaceAll("\\$Z", String.valueOf(bridge5extension_51.bridge$getBlockZ()))
         .replaceAll("\\$W", text2);
   }

   @Nullable
   public GuiHandler method19() {
      String text1 = this.method4("quickWaypoints", new Object[0]);
      return Client.method109().method48().method32().stream().filter(arg1x -> arg1x.getName().equals(text1)).findAny().orElse(null);
   }

   public static void method21() {
      Bridge5Extension_5 bridge5extension_50 = Ref.method7();
      if (bridge5extension_50 != null) {
         DriverViewportLegacy.method50().method16(DriverRouteRegistry.field18);
      }
   }

   public static double method21(Waypoint guihandler20, BridgeExtension bridgeextension1) {
      double value2 = guihandler20.method35().bridge$xCoord() - bridgeextension1.bridge$getPosX();
      double value4 = guihandler20.method35().bridge$yCoord() - bridgeextension1.bridge$getPosY();
      double value6 = guihandler20.method35().bridge$zCoord() - bridgeextension1.bridge$getPosZ();
      return Math.sqrt(value2 * value2 + value4 * value4 + value6 * value6);
   }

   @Generated
   public ToggleOption method22() {
      return this.field14;
   }

   @Generated
   public ToggleOption method23() {
      return this.field15;
   }

   @Generated
   public SimpleKeybindOption method24() {
      return this.field18;
   }

   @Generated
   public SimpleKeybindOption method25() {
      return this.field19;
   }

   @Generated
   public ToggleOption method26() {
      return this.field26;
   }
}
