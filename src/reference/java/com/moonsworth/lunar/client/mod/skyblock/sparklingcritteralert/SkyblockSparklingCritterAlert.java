package com.moonsworth.lunar.client.mod.skyblock.sparklingcritteralert;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.HologramEntityListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.EntitySubscription;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl.Type;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.TextOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockSparklingCritterAlert extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^A SPARKLING \ue087 Critter has appeared in the (?<location>[\\w\\s]+)!$");
   private static final Pattern field9 = Pattern.compile("^CAPTURE! You caught a SPARKLING .*!$");
   private static final String field10 = "SPARKLING_CRITTER";
   private static final String field11 = "SPARKLING_CRITTER_LOCATION";
   private final AlertDisplayListener field12 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);
   private final HologramEntityListener field13 = (HologramEntityListener)this.method63(HologramEntityListener.class);
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("spawnedAlert").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final TextOption field15 = (TextOption)((Data)OptionFactory.method12("spawnedAlertText")
         .method2("SPARKLING SPAWNED!"))
      .method31();
   private final ColorOption field16 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "spawnedAlertColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method15()
      .method31();
   private final IntegerOption field17 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "spawnedAlertTimeToShow"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .method7(1, 5))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("nearbyAlert").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final TextOption field19 = (TextOption)((Data)OptionFactory.method12("nearbyAlertText")
         .method2("SPARKLING NEARBY!"))
      .method31();
   private final ColorOption field20 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "nearbyAlertColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method15()
      .method31();
   private final IntegerOption field21 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "nearbyAlertTimeToShow"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .method7(1, 5))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("nearbyWaypoint").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EntitySubscription<EntityArmorStandBridge> field23 = this.field13.method7().method2(arg0 -> {
      Component component1x = arg0.bridge$getCustomName();
      if (component1x == null) {
         return false;
      }

      String text2 = TextBridge.getTextContent(component1x);
      return text2.contains("SPARKLING");
   }).method6(this, () -> (Boolean)this.field18.get() || (Boolean)this.field22.get());
   private boolean field24;
   private Waypoint field25;

   public SkyblockSparklingCritterAlert(Skyblock skyblock1) {
      super(false);
      this.method12(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method12(ModTraits.field17, ModCategories.method2(SettingsPage.FORAGING));
      this.method12(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.SAFARI));
      this.method50(this::reset);
      this.method51(this::reset);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, arg1x -> {
         this.method1(arg1x);
         this.method2(arg1x);
      });
      this.handle(EventTick.class, this::method3);
      this.method3(EventWorldChange.class, this::reset);
   }

   private void method1(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if ((Boolean)this.field14.get()) {
         Matcher matcher2 = field8.matcher(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH());
         if (matcher2.matches()) {
            TextComponent text3 = Component.text((String)this.field15.get(), TextColor.color(this.field16.method14(0.0F)));
            TextComponent text4 = Component.text(matcher2.group("location"), TextColor.color(this.field16.method14(0.0F)));
            this.field12
               .method2(
                  ComparableImpl.method2().method1("SPARKLING_CRITTER").method2(text3).method3((Integer)this.field17.get() * 1000).method4(Type.HIGH).method6()
               );
            this.field12
               .method2(
                  ComparableImpl.method2()
                     .method1("SPARKLING_CRITTER_LOCATION")
                     .method2(text4)
                     .method3((Integer)this.field17.get() * 1000)
                     .method4(Type.LOW)
                     .method6()
               );
         }
      }
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (field9.matcher(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH()).matches()) {
         this.reset();
      }
   }

   private void method3(EventTick highlightimpl21) {
      if (!this.field23.isEmpty()) {
         if ((Boolean)this.field18.get() && !this.field24) {
            this.field24 = true;
            TextComponent text2 = Component.text((String)this.field19.get(), TextColor.color(this.field20.method14(0.0F)));
            this.field12.method2(ComparableImpl.method2().method1("SPARKLING_CRITTER").method2(text2).method3((Integer)this.field21.get() * 1000).method6());
         }

         if ((Boolean)this.field22.get()) {
            EntityArmorStandBridge bridgeextension_24 = (EntityArmorStandBridge)this.field23.method2().get();
            Vec3Bridge horsestats153 = Vec3Bridge.method2(bridgeextension_24.bridge$getPosX(), bridgeextension_24.bridge$getPosY(), bridgeextension_24.bridge$getPosZ());
            this.method13();
            this.field25 = Waypoint.method18()
               .method2("Sparkling Critter")
               .method3(horsestats153)
               .method4(Client.method109().getWorld())
               .method5(Ref.method8().bridge$getDimensionId())
               .method12(WaypointStore.method19())
               .method13(true)
               .method7(true)
               .method6(SkyblockIsland.SAFARI)
               .method19();
            Ref.method4().method48().method6(this.field25);
         }
      }
   }

   private void reset() {
      this.field24 = false;
      this.method13();
   }

   private void method13() {
      if (this.field25 != null) {
         Ref.method4().method48().method9(this.field25);
         this.field25 = null;
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field14, arg1xx -> arg1xx.method9(new ClientOption[]{this.field15, this.field16, this.field17})
            );
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field18, arg1xx -> arg1xx.method9(new ClientOption[]{this.field19, this.field20, this.field21})
            );
            arg1x.method9(new ClientOption[]{this.field22});
         }
      );
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_SPARKLING_CRITTER_ALERT";
   }
}
