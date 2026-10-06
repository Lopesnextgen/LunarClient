package com.moonsworth.lunar.client.mod.render.markers;

import com.google.common.base.Objects;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.markers.mixin.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.markers.mixin.Markers2;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.event.input.InputActionLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButtonLegacy;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.joml.Vector3d;

public class Markers extends AbstractFeature {
   private final GuiIterator guiIterator = new GuiIterator();
   public static final boolean DEBUG = ThreadModuleDump48.field30 || !LunarBuildData.field4;
   private final GuiRewindhandlersHandler2 settingsHandler = (GuiRewindhandlersHandler2)this.method61(GuiRewindhandlersHandler2.class);
   private final ModifierKeybindOption markerKeybind = (ModifierKeybindOption)((ModifierKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18(
            "markerKeybind"
         )
         .method18(this))
      .method11()
      .method31();
   private final ModifierKeybindOption clearMarkersKeybind = (ModifierKeybindOption)((ModifierKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18(
            "clearMarkersKeybind"
         )
         .method18(this))
      .method11()
      .method31();
   private final FloatOption dingVolumeSelf = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "dingVolumeSelf"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.0F, 1.0F))
      .method31();
   private final FloatOption dingVolumeOthers = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "dingVolumeOthers"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.0F, 1.0F))
      .method31();
   private final ToggleOption middleClickRemove = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "middleClickRemove"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption chatNotify = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("chatNotify")
      .method31();
   private final ToggleOption lunarNotify = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("lunarNotify")
      .method31();
   private final ToggleOption teamMembers = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "teamMembers"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption apolloMarkers = this.createSharingToggle(Gui2Extension.APOLLO, true, this.teamMembers);
   private final ToggleOption scoreboardMarkers = this.createSharingToggle(Gui2Extension.SCOREBOARD, false, this.teamMembers);
   private final ToggleOption nameColorMarkers = this.createSharingToggle(Gui2Extension.NAME_COLOR, false, this.teamMembers);
   private final ToggleOption hypixelPartyMarkers = this.createSharingToggle(Gui2Extension.HYPIXEL_PARTY, true, null);
   private final ToggleOption lunarFriendsMarkers = this.createSharingToggle(Gui2Extension.LUNAR_FRIENDS, false, null);
   private final IntegerOption visibleDuration = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "visibleDuration"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(20))
         .method7(5, 120))
      .method31();
   private final FloatOption scale = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "scale"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.5F, 2.0F))
      .method31();
   private final ToggleOption animateMarker = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "animateMarker"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption background = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("background")
      .method31();
   private final EnumOption<Markers.Type> showOwner = (EnumOption<Markers.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "showOwner", Markers.Type.ALWAYS
      )
      .method31();
   private final EnumOption<Markers.Type> showDescription = (EnumOption<Markers.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "showDescription", Markers.Type.HOVER
      )
      .method31();
   private final EnumOption<Markers.Type3> ownerDisplay = (EnumOption<Markers.Type3>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "ownerDisplay", Markers.Type3.HEAD
      )
      .method31();
   private final EnumOption<Markers.Type2> descriptionDisplay = (EnumOption<Markers.Type2>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "descriptionDisplay", Markers.Type2.ICON
      )
      .method31();
   private final EnumOption<Markers.Type> showCoordinates = (EnumOption<Markers.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "showCoordinates", Markers.Type.NEVER
      )
      .method31();
   private final EnumOption<Markers.Type> showDistance = (EnumOption<Markers.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "showDistance", Markers.Type.HOVER
      )
      .method31();
   private final ToggleOption compactMode = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("compactMode")
      .method31();
   private final TextOption ownerSuffix = (TextOption)((TextOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method12(
            "ownerSuffix"
         )
         .method2("'s Marker"))
      .method31();
   private final ToggleOption textShadow = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "textShadow"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption markerColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "markerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1095835140))
      .method31();
   private final ColorOption dangerMarkerColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "dangerMarkerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1090567680))
      .method31();
   private final ColorOption infoMarkerColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "infoMarkerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-13328661))
      .method31();
   private final ColorOption interestMarkerColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "interestMarkerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-22016))
      .method31();
   private final ColorOption backgroundColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1073741824))
      .method31();
   private final com.moonsworth.lunar.client.framework.feature.markers.Markers markerManager = new com.moonsworth.lunar.client.framework.feature.markers.Markers(this);
   private Markers2 markerSettings;
   private final List<Long> dingTimestamps = new ArrayList<>();
   private long keybindPressTime = -1L;
   private int cooldownMessageId = 0;
   private boolean notifyingChat = false;

   public Markers() {
      super(true);
      this.handle(HudBaseRenderEvent.Data4.class, this::onMarkerUpdate);
      this.handle(HudRenderLegacyEvent.class, this::onMarkerRender);
      this.handle(EventWorldLifecycle.EventWorldChanged.class, this::onMarkersCleared);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick.class, this::onTick);
      this.handle(EventMouseButtonLegacy.class, this::onKeyPress);
      this.markerKeybind.method3(() -> this.keybindPressTime = ThreadModuleDump63.method3().bridge$getSystemTime());
      this.markerKeybind.method5(var1 -> {
         this.keybindPressTime = -1L;
         if (var1) {
            this.method2(com.moonsworth.lunar.client.framework.feature.markers.Markers2.Type.NORMAL);
         }
      });
      this.clearMarkersKeybind.method3(this.markerManager.method10()::clear);
      this.guiIterator.method3("flags", Arrays.stream(com.moonsworth.lunar.client.framework.feature.markers.Markers2.Type.values()).map(var0 -> {
         JsonObject var1 = new JsonObject();
         var1.addProperty("id", var0.name());
         var1.addProperty("icon", var0.getResource().bridge$getPath());
         var1.addProperty("name", var0.getName());
         return var1;
      }).collect(JsonArray::new, JsonArray::add, JsonArray::addAll));
   }

   @Override
   public String getId() {
      return "MARKERS";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> var1x.method9(
            new ClientOption[]{this.markerKeybind, this.clearMarkersKeybind, this.dingVolumeSelf, this.dingVolumeOthers, this.middleClickRemove, this.chatNotify, this.lunarNotify}
         )
      );
      var1.method1(
         "markerSharingOptions",
         var1x -> {
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.teamMembers, var1xx -> var1xx.method9(new ClientOption[]{this.scoreboardMarkers, this.apolloMarkers, this.nameColorMarkers})
            );
            var1x.method9(new ClientOption[]{this.hypixelPartyMarkers, this.lunarFriendsMarkers});
         }
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.SETTINGS,
         var1x -> {
            var1x.method9(new ClientOption[]{this.scale, this.visibleDuration, this.animateMarker});
            var1x.method9(new ClientOption[]{this.showOwner, this.showDescription, this.showDistance, this.showCoordinates});
            var1x.method9(new ClientOption[]{this.ownerSuffix})
               .method3(() -> this.compactMode.get() || this.showOwner.get() == Markers.Type.NEVER);
            var1x.method9(new ClientOption[]{this.compactMode});
            var1x.method9(new ClientOption[]{this.ownerDisplay, this.descriptionDisplay}).method3(this.compactMode::get);
            var1x.method9(new ClientOption[]{this.textShadow, this.background});
            var1x.method9(new ClientOption[]{this.backgroundColor}).method3(() -> !this.background.get());
            var1x.method9(new ClientOption[]{this.markerColor, this.dangerMarkerColor, this.infoMarkerColor, this.interestMarkerColor});
         }
      );
   }

   public void method2(com.moonsworth.lunar.client.framework.feature.markers.Markers2.Type var1) {
      if (this.isEnabled()) {
         if (this.dingTimestamps.size() > 3) {
            if (ThreadModuleDump63.method7() != null) {
               if (this.cooldownMessageId != 0) {
                  this.mc.bridge$getGuiIngame().bridge$getChatGUI().method1(this.cooldownMessageId);
               }

               this.cooldownMessageId = this.mc
                  .bridge$getGuiIngame()
                  .bridge$getChatGUI()
                  .bridge$addMessageWithLunarId(AdventureTextBridge.asBridge(Component.text(this.method2("markerCooldown", new Object[0])).color(NamedTextColor.GRAY)));
            }
         } else {
            if (this.markerManager.method1(var1)) {
               long var2 = ThreadModuleDump63.method3().bridge$getSystemTime();
               this.dingTimestamps.add(var2);
            }
         }
      }
   }

   private void onMarkerUpdate(HudBaseRenderEvent var1) {
      this.markerManager.method10().forEach(var1x -> var1x.method6().method1(var1));
   }

   private void onMarkerRender(HudRenderLegacyEvent var1) {
      this.markerManager.method10().forEach(var0 -> var0.method6().method2());
   }

   private void onMarkersCleared(EventWorldLifecycle.EventWorldChanged var1) {
      this.markerManager.method10().clear();
   }

   private void onTick(com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick var1) {
      long var2 = ThreadModuleDump63.method3().bridge$getSystemTime();
      this.dingTimestamps.removeIf(var2x -> var2 - var2x >= 5000L);
      this.markerManager.method2();
      if (this.keybindPressTime != -1L && var2 - this.keybindPressTime > 200L) {
         this.keybindPressTime = -1L;
         DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field22);
      }
   }

   private void onKeyPress(EventMouseButtonLegacy var1) {
      if (ThreadModuleDump63.method7() != null) {
         if (var1.method4() == InputActionLegacy.UP && var1.method1() == KeyCode.KEY_MOUSE3) {
            Iterator var2 = this.markerManager.method10().iterator();

            while (var2.hasNext()) {
               com.moonsworth.lunar.client.framework.feature.markers.Markers2 var3 = (com.moonsworth.lunar.client.framework.feature.markers.Markers2)var2.next();
               if (var3.method6().method19()) {
                  boolean var4;
                  if (var3.method8()) {
                     var4 = var3.method27().isMiddleClickRemove();
                  } else {
                     var4 = this.middleClickRemove.get() && var3.getOwnerId().equals(ThreadModuleDump63.method7().bridge$getUniqueID());
                  }

                  if (var4) {
                     var2.remove();
                     return;
                  }
               }
            }
         }
      }
   }

   protected void addMarker(com.moonsworth.lunar.client.framework.feature.markers.Markers2 var1) {
      if (this.isEnabled()) {
         Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
         if (var2 != null) {
            if (field9) {
               Slayer.method3("[Markers] onMarkerAdded() %s", var1.getOwnerId());
            }

            Vector3d var3 = var1.getPos();
            boolean var4 = Objects.equal(var1.getOwnerId(), var2.bridge$getUniqueID());
            float var5 = this.dingVolumeSelf.get();
            float var6 = this.dingVolumeOthers.get();
            if (var4 && var5 > 0.0F || !var4 && var6 > 0.0F) {
               double var7 = var2.bridge$getPosX();
               double var9 = var2.bridge$getPosY();
               double var11 = var2.bridge$getPosZ();
               double var13 = var3.x - var7;
               double var15 = var3.y - var9;
               double var17 = var3.z - var11;
               double var19 = Math.sqrt(var13 * var13 + var15 * var15 + var17 * var17);
               double var21 = Math.PI;
               double var23 = var7 + var13 / var19 * var21;
               double var25 = var9 + var15 / var19 * var21;
               double var27 = var11 + var17 / var19 * var21;
               String var29 = ThreadModuleDump63.MC_VERSION <= 1 ? "random.successful_hit" : "entity.experience_orb.pickup";
               var2.method2(
                  var29,
                  var23,
                  var25,
                  var27,
                  var4 ? var5 : var6,
                  var1.method25().method5() == com.moonsworth.lunar.client.framework.feature.markers.Markers2.Type.DANGER ? 0.5F : 2.0F
               );
            }

            Component var30 = var4
               ? Component.text(this.method2("you", new Object[0])).color(NamedTextColor.GREEN)
               : Component.text(var1.getOwnerName()).color(NamedTextColor.GRAY);
            double var8 = var3.distance(var2.bridge$getPosX(), var2.bridge$getPosY(), var2.bridge$getPosZ());
            Component var10 = ((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.empty().append(var30))
                           .append(Component.text(" %s ".formatted(this.method2("marked", new Object[0]))).color(NamedTextColor.GRAY)))
                        .append(this.getOwnerComponent(var1)))
                     .append(this.getFlagComponent(var1)))
                  .append(Component.text("%.1f %s".formatted(var8, this.method2("blocksAway", new Object[0]))).color(NamedTextColor.GRAY)))
               .append(Component.text(" (%.1f %.1f %.1f)".formatted(var3.x, var3.y, var3.z)).color(NamedTextColor.DARK_GRAY));
            boolean var31 = var1.method8() ? var1.method27().isChatNotify() : this.chatNotify.get();
            boolean var12 = var1.method8() ? var1.method27().isInGameNotification() : this.lunarNotify.get();
            if (var31) {
               this.notifyingChat = true;
               var2.bridge$addChatMessage(AdventureTextBridge.asBridge(var10));
               this.notifyingChat = false;
            }

            if (var12) {
               String var32 = AdventureChatFormatting.getTextWithoutFormattingCodes(AdventureTextBridge.getTextContent(var10));
               ThreadModuleDump63.method4().method69().method2("Markers", var32);
            }
         }
      }
   }

   public Component getOwnerComponent(com.moonsworth.lunar.client.framework.feature.markers.Markers2 var1) {
      return var1.method4().colorIfAbsent(NamedTextColor.GREEN).append(Component.text(" "));
   }

   public Component getFlagComponent(com.moonsworth.lunar.client.framework.feature.markers.Markers2 var1) {
      switch (var1.method25().method5()) {
         case DANGER:
            return Component.text(this.method2("dangerFlag", new Object[0]) + " ", NamedTextColor.RED);
         case INFO:
            return Component.text(this.method2("infoFlag", new Object[0]) + " ", NamedTextColor.AQUA);
         case INTEREST:
            return Component.text(this.method2("interestFlag", new Object[0]) + " ", NamedTextColor.GOLD);
         default:
            return Component.empty();
      }
   }

   private ToggleOption createSharingToggle(Gui2Extension var1, boolean var2, @Nullable ToggleOption var3) {
      ToggleOption var4 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(var1.id())
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(var2))
         .method31();
      Consumer var5 = var3x -> this.getMarkerSettings().method1(var1, var3x && (var3 == null || var3.get()));
      var4.CICORRHIOIIOORRRICCORIOIOCIHII(var5);
      if (var3 != null) {
         var3.CICORRHIOIIOORRRICCORIOIOCIHII(var2x -> var5.accept(var4.get()));
      }

      return var4;
   }

   public Markers2 getMarkerSettings() {
      if (this.markerSettings == null) {
         this.markerSettings = new Markers2();
      }

      return this.markerSettings;
   }

   @Generated
   public GuiIterator method14() {
      return this.guiIterator;
   }

   @Generated
   public ModifierKeybindOption method15() {
      return this.markerKeybind;
   }

   @Generated
   public ModifierKeybindOption getClearMarkersKeybind() {
      return this.clearMarkersKeybind;
   }

   @Generated
   public FloatOption getDingVolumeSelf() {
      return this.dingVolumeSelf;
   }

   @Generated
   public FloatOption getDingVolumeOthers() {
      return this.dingVolumeOthers;
   }

   @Generated
   public ToggleOption getMiddleClickRemove() {
      return this.middleClickRemove;
   }

   @Generated
   public ToggleOption getChatNotify() {
      return this.chatNotify;
   }

   @Generated
   public ToggleOption getLunarNotify() {
      return this.lunarNotify;
   }

   @Generated
   public ToggleOption getTeamMembers() {
      return this.teamMembers;
   }

   @Generated
   public ToggleOption getApolloMarkers() {
      return this.apolloMarkers;
   }

   @Generated
   public ToggleOption getScoreboardMarkers() {
      return this.scoreboardMarkers;
   }

   @Generated
   public ToggleOption getNameColorMarkers() {
      return this.nameColorMarkers;
   }

   @Generated
   public ToggleOption getHypixelPartyMarkers() {
      return this.hypixelPartyMarkers;
   }

   @Generated
   public ToggleOption getLunarFriendsMarkers() {
      return this.lunarFriendsMarkers;
   }

   @Generated
   public IntegerOption getVisibleDuration() {
      return this.visibleDuration;
   }

   @Generated
   public FloatOption getScale() {
      return this.scale;
   }

   @Generated
   public ToggleOption getAnimateMarker() {
      return this.animateMarker;
   }

   @Generated
   public ToggleOption getBackground() {
      return this.background;
   }

   @Generated
   public EnumOption<Markers.Type> getShowOwner() {
      return this.showOwner;
   }

   @Generated
   public EnumOption<Markers.Type> getShowDescription() {
      return this.showDescription;
   }

   @Generated
   public EnumOption<Markers.Type3> getOwnerDisplay() {
      return this.ownerDisplay;
   }

   @Generated
   public EnumOption<Markers.Type2> getDescriptionDisplay() {
      return this.descriptionDisplay;
   }

   @Generated
   public EnumOption<Markers.Type> getShowCoordinates() {
      return this.showCoordinates;
   }

   @Generated
   public EnumOption<Markers.Type> getShowDistance() {
      return this.showDistance;
   }

   @Generated
   public ToggleOption getCompactMode() {
      return this.compactMode;
   }

   @Generated
   public TextOption getOwnerSuffix() {
      return this.ownerSuffix;
   }

   @Generated
   public ToggleOption getTextShadow() {
      return this.textShadow;
   }

   @Generated
   public ColorOption getMarkerColor() {
      return this.markerColor;
   }

   @Generated
   public ColorOption getDangerMarkerColor() {
      return this.dangerMarkerColor;
   }

   @Generated
   public ColorOption getInfoMarkerColor() {
      return this.infoMarkerColor;
   }

   @Generated
   public ColorOption getInterestMarkerColor() {
      return this.interestMarkerColor;
   }

   @Generated
   public ColorOption getBackgroundColor() {
      return this.backgroundColor;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.markers.Markers getMarkerManager() {
      return this.markerManager;
   }

   @Generated
   public List<Long> getDingTimestamps() {
      return this.dingTimestamps;
   }

   @Generated
   public long getKeybindPressTime() {
      return this.keybindPressTime;
   }

   @Generated
   public int getCooldownMessageId() {
      return this.cooldownMessageId;
   }

   @Generated
   public GuiRewindhandlersHandler2 getSettingsHandler() {
      return this.settingsHandler;
   }

   @Generated
   public boolean isNotifyingChat() {
      return this.notifyingChat;
   }

   public enum Type implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      NEVER("never"),
      HOVER("hover"),
      ALWAYS("always");

      private final String id;

      @Override
      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
      }

      @Generated
      Type(String var3) {
         this.id = var3;
      }
   }

   public enum Type2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      ICON("icon"),
      TEXT("text");

      private final String id;

      @Override
      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
      }

      @Generated
      Type2(String var3) {
         this.id = var3;
      }
   }

   public enum Type3 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      HEAD("head"),
      NAME("name");

      private final String id;

      @Override
      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
      }

      @Generated
      Type3(String var3) {
         this.id = var3;
      }
   }
}
