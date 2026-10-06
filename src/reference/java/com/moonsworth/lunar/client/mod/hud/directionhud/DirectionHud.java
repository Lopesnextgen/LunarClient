package com.moonsworth.lunar.client.mod.hud.directionhud;

import com.moonsworth.lunar.bridge.Bridge5Extension9;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.framework.ReducedDebugInfoNotifier;
import com.moonsworth.lunar.client.account.TeamMemberManager;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.directionhud.Directionhud;
import com.moonsworth.lunar.client.framework.feature.directionhud.Directionhud2;
import com.moonsworth.lunar.client.framework.feature.directionhud.Directionhud3;
import com.moonsworth.lunar.client.framework.feature.directionhud.Directionhud3Impl;
import com.moonsworth.lunar.client.framework.feature.directionhud.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import com.moonsworth.lunar.client.mod.hud.memory.Memory;

public class DirectionHud extends AbstractFeature {
   private final FloatOption borderThickness = (FloatOption)((FloatOption.Data)((FloatOption.Data)OptionFactory.method2("borderThickness")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   private final ToggleOption background = (ToggleOption)OptionFactory.method7("background").method31();
   private final ToggleOption border = (ToggleOption)OptionFactory.method7("border").method31();
   private final ToggleOption showWithTab = (ToggleOption)OptionFactory.method7("showWithTab").method31();
   private final EnumOption<DirectionHud.Type> hudStyle = (EnumOption<DirectionHud.Type>)OptionFactory.method10(
         "hudStyle", DirectionHud.Type.NORMAL
      )
      .method31();
   protected final FloatOption width = (FloatOption)((FloatOption.Data)((FloatOption.Data)OptionFactory.method2("width")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(280.0F))
         .method8(168.0F, 448.0F))
      .method31();
   protected final ToggleOption textShadow = (ToggleOption)OptionFactory.method7("textShadow").method31();
   protected final ToggleOption boldDirections = (ToggleOption)OptionFactory.method7("boldDirections").method31();
   protected final ToggleOption showMarker = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showMarker")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption showMarkerValue = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showMarkerValue")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ColorOption borderColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("borderColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   protected final ColorOption backgroundColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("backgroundColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   protected final ColorOption directionColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("directionColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   protected final ColorOption markerColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("markerColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   protected final EnumOption<Gui2Extension> teammates = (EnumOption<Gui2Extension>)OptionFactory.method10("teammates", Gui2Extension.ABOVE)
      .method31();
   protected final EnumOption<Gui2Extension> waypoints = (EnumOption<Gui2Extension>)OptionFactory.method10("waypoints", Gui2Extension.ABOVE)
      .method31();
   protected final EnumOption<Gui2Extension> externalMarkers = (EnumOption<Gui2Extension>)OptionFactory.method10("externalMarkers", Gui2Extension.BELOW)
      .method31();
   protected final ToggleOption showTeammates = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showTeammates")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption showWaypoints = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showWaypoints")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption showExternalMarkers = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showExternalMarkers")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();

   public DirectionHud() {
      super(true);
      this.shouldRender(Framework.field1, new DirectionHud.Data());
      ((Alert2)this.method7(Framework.field4)).method6(var0 -> ReducedDebugInfoNotifier.method1(), false);
   }

   @Override
   public String getId() {
      return "DIRECTION_HUD";
   }

   @Override
   public void registerOptions(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.method9(new ClientOption[]{this.width}).method3(() -> this.hudStyle.get() != DirectionHud.Type.NORMAL);
            var1x.method9(new ClientOption[]{this.textShadow})
               .method3(() -> this.hudStyle.get() == DirectionHud.Type.LEGACY || this.hudStyle.get() == DirectionHud.Type.COMPASS);
            var1x.method9(new ClientOption[]{this.boldDirections}).method3(() -> this.hudStyle.get() != DirectionHud.Type.NORMAL);
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.background, var1xx -> var1xx.method9(new ClientOption[]{this.border, this.borderThickness})
               )
               .method3(() -> this.hudStyle.get() == DirectionHud.Type.LEGACY);
         }
      );
      var1.method1(
         "renderOptions",
         var1x -> {
            var1x.method9(new ClientOption[]{this.hudStyle, this.showWithTab});
            var1x.method9(new ClientOption[]{this.showMarker})
               .method3(() -> this.hudStyle.get() == DirectionHud.Type.SIMPLE || this.hudStyle.get() == DirectionHud.Type.COMPASS);
            var1x.method9(new ClientOption[]{this.showMarkerValue, this.waypoints, this.teammates, this.externalMarkers})
               .method3(() -> this.hudStyle.get() != DirectionHud.Type.NORMAL);
            var1x.method9(new ClientOption[]{this.showWaypoints, this.showTeammates, this.showExternalMarkers})
               .method3(() -> this.hudStyle.get() != DirectionHud.Type.COMPASS);
         }
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.COLOR,
         var1x -> {
            var1x.method9(new ClientOption[]{this.backgroundColor, this.borderColor})
               .method3(() -> this.hudStyle.get() == DirectionHud.Type.LEGACY);
            var1x.method9(new ClientOption[]{this.markerColor})
               .method3(() -> this.hudStyle.get() != DirectionHud.Type.LEGACY && this.hudStyle.get() != DirectionHud.Type.NORMAL);
            var1x.method9(new ClientOption[]{this.directionColor})
               .method3(() -> this.hudStyle.get() != DirectionHud.Type.LEGACY && this.hudStyle.get() != DirectionHud.Type.SIMPLE);
         }
      );
      this.width.CICORRHIOIIOORRRICCORIOIOCIHII(var1x -> {
         if (this.hudStyle.get() == DirectionHud.Type.NORMAL) {
            ((MixinCore9Extension)this.method7(Framework.field1)).method16(this.width.get(), 29.0F);
         }
      });
      this.hudStyle.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> {
         switch (var1x) {
            case NORMAL:
               ((MixinCore9Extension)this.method7(Framework.field1)).method16(this.width.get(), 29.0F);
               break;
            case LEGACY:
               ((MixinCore9Extension)this.method7(Framework.field1)).method16(65.0F, 12.0F);
               break;
            case SIMPLE:
               ((MixinCore9Extension)this.method7(Framework.field1)).method16(24.0F, 24.0F);
               break;
            case COMPASS:
               ((MixinCore9Extension)this.method7(Framework.field1)).method16(128.0F, 128.0F);
         }
      });
   }

   void registerOptions(Consumer<GuiHandler2> var1) {
      if (ThreadModuleDump63.method4().method40().method20().isEnabled()) {
         for (GuiHandler2 var3 : Client.method109().method48().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            if (var3.isVisible() && var3.shouldRender()) {
               var1.accept(var3);
            }
         }
      }
   }

   void forEachTeamMember(Consumer<DirectionHud.Data2> var1) {
      com.moonsworth.lunar.client.memory.Memory var2 = Client.method109().method31();
      TeamMemberManager var3 = Client.method109().method60();

      for (com.moonsworth.lunar.client.memory.Memory var5 : var3.method3().values()) {
         if (var5 != var2) {
            Bridge6_10 var6 = ThreadModuleDump63.method3().bridge$getWorld().bridge$getPlayerByUniqueId(var5.method10()).orElse(null);
            if (var6 != null || var3.method6(var5)) {
               var1.accept(new DirectionHud.Data2(var5, var6));
            }
         }
      }
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER);
      }

      @Override
      public boolean method31() {
         return false;
      }

      @Override
      public void forEachTeamMember(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
         if (!ReducedDebugInfoNotifier.method1() || var4) {
            double var5 = 180.0;
            if (!var4) {
               Bridge5Extension_5 var7 = ThreadModuleDump63.method7();
               if (DirectionHud.this.mc.bridge$getRenderViewEntity() != null) {
                  var5 = DirectionHud.this.mc.bridge$getRenderViewEntity().bridge$getRotationYaw();
               } else if (var7 != null) {
                  var5 = var7.bridge$getRotationYaw();
               }
            }

            DirectionHud.Type var9 = DirectionHud.this.hudStyle.get();
            MixinHelper_4 var8 = var1.method2();
            if (var9 != DirectionHud.Type.LEGACY && DirectionHud.this.background.get()) {
               DirectionHud.this.backgroundColor.method11(var8, var2, var3, this.getWidth(), this.getHeight());
               if (DirectionHud.this.border.get()) {
                  DirectionHud.this.borderColor.method11(var8, this, var2, var3, this.getWidth(), this.getHeight(), DirectionHud.this.borderThickness.get());
               }
            }

            var9.renderer.method1(DirectionHud.this, var8, var2, var3, var5);
         }
      }

      @Override
      public boolean shouldRender(boolean var1) {
         if (var1) {
            return true;
         }

         if (DirectionHud.this.showWithTab.get()) {
            return true;
         }

         Bridge5Extension9 var2 = DirectionHud.this.mc.bridge$getGuiIngame();
         return var2 == null || !var2.bridge$isTabVisible();
      }
   }

   class Data2 {
      @NotNull
      private final Memory playerMemory;
      @Nullable
      private final Bridge6_10 player;

      Data2(@NotNull Memory var1, @Nullable Bridge6_10 var2) {
         this.playerMemory = var1;
         this.player = var2;
      }

      @NotNull
      public Memory render() {
         return this.playerMemory;
      }

      @Nullable
      public Bridge6_10 registerOptions() {
         return this.player;
      }
   }

   interface Extension {
      void render(DirectionHud var1, MixinHelper_4 var2, float var3, float var4, double var5);
   }

   private enum Type implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      NORMAL("normal", new Directionhud3()),
      LEGACY("legacy", new Directionhud()),
      SIMPLE("simple", new Directionhud3Impl()),
      COMPASS("realCompass", new Directionhud2());

      private final String id;
      private final DirectionHud.Extension renderer;

      @Override
      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
      }

      @Generated
      Type(String var3, DirectionHud.Extension var4) {
         this.id = var3;
         this.renderer = var4;
      }
   }
}
