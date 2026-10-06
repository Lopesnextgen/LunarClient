package com.moonsworth.lunar.client.mod.skyblock.chatwaypoints;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.command.StringArgumentParser;
import com.moonsworth.lunar.client.command.ClientCommand;
import com.moonsworth.lunar.client.command.LiteralCommandNode;
import com.moonsworth.lunar.client.command.ArgumentCommandNode;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl.Type;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.rewindhandlers.ChatMessageParser.Extension;
import com.moonsworth.lunar.client.event.mixin.EventNameplateExtension;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.files.ValuePair;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.joml.Vector3d;
import org.joml.Vector3i;
import org.joml.Vector3ic;

public class SkyblockChatWaypoints extends AbstractFeature {
   private final AlertDisplayListener field8 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);
   private static final Pattern field9 = Pattern.compile(
      "(?:[xX]: ?)?(?<x>(?:-|)[0-9.]+),? (?:[yY]: ?)?(?<y>(?:-|)[0-9.]+),? (?:[zZ]: ?)?(?<z>(?:-|)[0-9.]+)(?:\\s*[|:-]\\s*(?<metadata>.+))?"
   );
   private final ColorOption field10 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "chatWaypointColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16776961))
      .method31();
   private final IntegerOption field11 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "chatWaypointLifetime"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(30))
         .method7(10, 60))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "chatWaypointRemoveWhenNear"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("chatWaypointAlerts")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("skyblockChatWaypointWhitelist")
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "skyblockChatWaypointsNoMetaDataAllowed"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ListOption<String> field16 = (ListOption<String>)com.moonsworth.lunar.client.config.option.OptionFactory.method31(
         "skyblockChatWaypointWhilelistList", Codec.STRING.listOf()
      )
      .method31();
   private final Set<String> field17 = new HashSet<>();
   private final List<SkyblockChatWaypoints.Data> field18 = new ArrayList<>();

   public SkyblockChatWaypoints(Skyblock skyblock1) {
      super(true);
      this.method15(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method15(ModTraits.field17, ModCategories.method2(SettingsPage.CHAT));
      this.method15(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method5(
         new ClientCommand(
            LiteralCommandNode.method1("chatwaypointblacklist")
               .method3(arg1x -> SkyBlockChat.sendMessage(Component.text(this.method5("chatWaypointBlacklistInvalidUsage", new Object[0]), NamedTextColor.RED)))
               .method2(ArgumentCommandNode.method1("user", StringArgumentParser.field1).method8(arg1x -> this.method10(arg1x.getString("user"))))
         )
      );
      this.handle(EventSecond.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt.class, this::method8);
      this.handle(EventWorldChange.class, this::method7);
      this.handle(EventNameplateExtension.class, this::method5);
      this.handle(EventTick.class, this::method4);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_CHAT_WAYPOINTS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(
         new OptionProvider[]{
            this.field10,
            this.field11,
            this.field12,
            this.field13,
            com.moonsworth.lunar.client.config.option.OptionFactory.method14("chatWaypointsResetBlacklist").method5(100.0F).method4(this.field17::clear)
         }
      );
      lightingextension231.method7(this.field14, arg1x -> arg1x.method9(new ClientOption[]{this.field15, this.field16}));
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field5})
         .method2(new String[]{"Diana", "Griffin", "Burrow"})
         .method11(this);
   }

   public Optional<Vector3ic> method13() {
      if (!this.isEnabled()) {
         return Optional.empty();
      }

      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 == null) {
         return Optional.empty();
      }

      SkyblockChatWaypoints.Data data2 = null;
      double value3 = Float.MAX_VALUE;

      for (SkyblockChatWaypoints.Data data6 : this.field18) {
         if (data2 == null) {
            data2 = data6;
         } else {
            double value7 = bridge5extension_51.method15(data6.field2.x(), data6.field2.y(), data6.field2.z());
            if (value7 < value3) {
               data2 = data6;
               value3 = value7;
            }
         }
      }

      return data2 == null ? Optional.empty() : Optional.of(data2.field2);
   }

   private void method4(EventTick highlightimpl21) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if ((Boolean)this.field12.get() && bridge5extension_52 != null) {
         this.field18.removeIf(arg1x -> bridge5extension_52.method15(arg1x.method2().x(), arg1x.method2().y(), arg1x.method2().z()) < 25.0);
      }
   }

   private void method5(EventNameplateExtension highlightimpl51) {
      Extension extension2 = highlightimpl51.method1();
      if (!this.field17.contains(extension2.method1())) {
         this.method9(extension2.message())
            .ifPresent(
               arg2x -> {
                  if ((Boolean)this.field14.get()) {
                     if (arg2x.field2 == null) {
                        if (!(Boolean)this.field15.get()) {
                           return;
                        }
                     } else if (((List)this.field16.get()).stream().map(String::toLowerCase).noneMatch(((String)arg2x.field2).toLowerCase()::contains)) {
                        return;
                     }
                  }

                  this.field18
                     .add(
                        new SkyblockChatWaypoints.Data(
                           extension2.method1(), (Vector3ic)arg2x.field1, Ref.method3().bridge$getSystemTime(), (String)arg2x.field2
                        )
                     );
                  SkyBlockChat.sendMessage(
                     ((TextComponent)Component.text(this.method5("chatWaypointAdded", new Object[]{extension2.method1()}), NamedTextColor.GREEN)
                           .hoverEvent(HoverEvent.showText(Component.text(this.method5("chatWaypointAddedHover", new Object[]{extension2.method1()})))))
                        .clickEvent(ClickEvent.runCommand("/chatwaypointblacklist " + extension2.method1()))
                  );
                  if ((Boolean)this.field13.get()) {
                     Object obj3 = Component.text(this.method5("waypointFrom", new Object[]{extension2.method1()}), NamedTextColor.AQUA);
                     if (arg2x.field2 != null) {
                        obj3 = obj3.append(Component.text(" (" + (String)arg2x.field2 + ")").color(NamedTextColor.GRAY));
                     }

                     this.field8
                        .method2(ComparableImpl.method2().method1("CHAT_WAYPOINT").method2((Component)obj3).method3(3000L).method4(Type.CRITICAL).method6());
                  }
               }
            );
      }
   }

   private void method6(EventSecond highlightimpl41) {
      this.field18.removeIf(arg1x -> Ref.method3().bridge$getSystemTime() - arg1x.method3() > 1000L * ((Integer)this.field11.get()).intValue());

      while (this.field18.size() > 10) {
         this.field18.remove(0);
      }
   }

   private void method7(EventWorldChange data31) {
      this.field18.clear();
   }

   private void method8(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt highlightimpl41) {
      AbstractRenderContext bridgeextension_92 = highlightimpl41.method3();
      EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
      bridgeextension_92.push();
      bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
      Vector3d vector3d4 = bridge2_433.bridge$getCameraPos().method6();

      for (SkyblockChatWaypoints.Data data6 : this.field18) {
         Vec3Bridge horsestats157 = Ref.method7().bridge$getEyePosition();
         double value8 = 0.05 + 0.02 * Math.sqrt(Math.hypot(data6.method2().x() - horsestats157.bridge$xCoord(), data6.method2().z() - horsestats157.bridge$zCoord()));
         WorldRenderUtils.drawFilledBox(
            bridgeextension_92,
            AxisAlignedBBBridge.method2(
               data6.method2().x() + 0.5 - value8,
               data6.method2().y() + 1,
               data6.method2().z() + 0.5 - value8,
               data6.method2().x() + 0.5 + value8,
               255.0,
               data6.method2().z() + 0.5 + value8
            ),
            this.field10.method13(),
            LunarRenderTypes.field53
         );
         WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_92, data6.field2, ColorUtils.method22(this.field10.method13(), 34), true);
         Vector3d vector3d10 = new Vector3d(data6.method2().x() + 0.5, data6.method2().y() + 2, data6.method2().z() + 0.5);
         Vector3d vector3d11 = vector3d10.sub(vector3d4, new Vector3d()).normalize(10.0);
         Vector3d vector3d12 = vector3d4.add(vector3d11, new Vector3d());
         String text13 = data6.method1();
         if (data6.field4 != null) {
            text13 = text13 + " (" + data6.field4 + ")";
         }

         WorldRenderUtils.drawString(highlightimpl41.method3(), text13, vector3d12.x(), vector3d12.y(), vector3d12.z(), -1, true);
      }

      bridgeextension_92.pop();
   }

   private Optional<ValuePair<Vector3ic, String>> method9(String text1) {
      try {
         Matcher matcher2 = field9.matcher(text1);
         if (matcher2.matches()) {
            Vector3i vector3i3 = new Vector3i(
               Integer.parseInt(matcher2.group("x").replaceAll(",", "")),
               Integer.parseInt(matcher2.group("y").replaceAll(",", "")),
               Integer.parseInt(matcher2.group("z").replaceAll(",", ""))
            );
            return Optional.of(new ValuePair(vector3i3, matcher2.group("metadata")));
         } else {
            return Optional.empty();
         }
      } catch (NumberFormatException numberformatexception4) {
         return Optional.empty();
      }
   }

   private void method10(String text1) {
      this.field17.add(text1);
      this.field18.removeIf(arg1x -> arg1x.method1().equals(text1));
      SkyBlockChat.sendMessage(Component.text(this.method5("chatWaypointBlacklistAdded", new Object[]{text1}), NamedTextColor.GREEN));
   }

   @Generated
   public ColorOption method14() {
      return this.field10;
   }

   private class Data {
      private final String field1;
      private final Vector3ic field2;
      private final long field3;
      private final String field4;

      private Data(String text1, Vector3ic vector3ic2, long number3, String text5) {
         this.field1 = text1;
         this.field2 = vector3ic2;
         this.field3 = number3;
         this.field4 = text5;
      }

      public String method1() {
         return this.field1;
      }

      public Vector3ic method2() {
         return this.field2;
      }

      public long method3() {
         return this.field3;
      }

      public String method4() {
         return this.field4;
      }
   }
}
