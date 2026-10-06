package com.moonsworth.lunar.client.mod.misc.debug;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.player.v1.PlayerHandshakeMessage;
import com.lunarclient.apollo.player.v1.UpdatePlayerWorldMessage;
import com.lunarclient.websocket.cosmetic.v2.LoadTabLogosResponse;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.ScrollableHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.MultiSelectOption.Data;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.hud.tab.Tab;

public class ApolloDebugMod extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)OptionFactory.method7("apolloRegisterPayloadPrint").method31();
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("apolloPacketReceivePrint").method31();
   private final MultiSelectOption field10 = (MultiSelectOption)((Data)OptionFactory.method27("apolloPacketReceiveFilter")
         .method2(this.method23()))
      .method3(this.method23())
      .method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("apolloPacketReceiveJsonInChat").method31();
   private final ToggleOption field12 = (ToggleOption)OptionFactory.method7("apolloPacketSendPrint").method31();
   private final MultiSelectOption field13 = (MultiSelectOption)((Data)OptionFactory.method27("apolloPacketSendFilter")
         .method2(this.method23()))
      .method3(this.method23())
      .method31();
   private final ToggleOption field14 = (ToggleOption)OptionFactory.method7("apolloPacketSendJsonInChat").method31();
   private final ToggleOption field15 = (ToggleOption)OptionFactory.method7("apolloTransferPingPrint").method31();
   private final ToggleOption field16 = (ToggleOption)OptionFactory.method7("apolloFeatureTrackingPrint").method31();
   private final ToggleOption field17 = (ToggleOption)OptionFactory.method7("apolloFeatureTrackingPrintUnfiltered").method31();
   private final ToggleOption field18 = (ToggleOption)OptionFactory.method7("apolloTabUuidPrint").method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("apolloTabUuidPrintFiltered")
         .method4(true))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("apolloTabUuidPrintUnfiltered")
         .method4(true))
      .method31();
   private final ToggleOption field21 = (ToggleOption)OptionFactory.method7("apolloTabUuidPrintAssetResponse").method31();
   private final ToggleOption field22 = (ToggleOption)OptionFactory.method7("apolloModulesInHud").method31();
   private final ToggleOption field23 = (ToggleOption)OptionFactory.method7("apolloModulesShowDisabled").method31();
   private final ToggleOption field24 = (ToggleOption)OptionFactory.method7("apolloInventoryButtonsBoxBorder").method31();
   private final ToggleOption field25 = (ToggleOption)OptionFactory.method7("apolloChatButtonsBoxBorder").method31();
   private final HudComponentGroup field26 = new HudComponentGroup(true);
   private boolean field27;

   public ApolloDebugMod() {
      super(false);
      this.method22(
         ModTraits.field1,
         MixinCore9Base.method9(0.0F, 0.0F, HudAnchor.TOP_LEFT, false, WidgetFactory.withBackground(new ScrollableHudComponent(this.field26).method1(300.0F)))
      );
      this.handle(HighlightImpl_3.class, this::method10);
      this.handle(EventSecond.class, this::method17);
   }

   public String getId() {
      return "APOLLO_DEBUG_MOD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1("apolloDebugRegister", arg1x -> arg1x.method9(new ClientOption[]{this.field8}));
      lightingextension231.method1(
         "apolloDebugPackets",
         arg1x -> {
            arg1x.method7(
               this.field9, arg1xx -> arg1xx.method9(new ClientOption[]{this.field10, this.field11})
            );
            arg1x.method7(
               this.field12, arg1xx -> arg1xx.method9(new ClientOption[]{this.field13, this.field14})
            );
         }
      );
      lightingextension231.method1("apolloDebugTransfer", arg1x -> arg1x.method9(new ClientOption[]{this.field15}));
      lightingextension231.method1(
         "apolloDebugFeatureTracking", arg1x -> arg1x.method9(new ClientOption[]{this.field16, this.field17})
      );
      lightingextension231.method1(
         "apolloDebugTabUuid",
         arg1x -> arg1x.method7(
            this.field18, arg1xx -> arg1xx.method9(new ClientOption[]{this.field19, this.field20, this.field21})
         )
      );
      lightingextension231.method1(
         "apolloDebugModulesHud",
         arg1x -> arg1x.method7(this.field22, arg1xx -> arg1xx.method9(new ClientOption[]{this.field23}))
      );
      lightingextension231.method1("apolloDebugInventoryButtons", arg1x -> arg1x.method9(new ClientOption[]{this.field24}));
      lightingextension231.method1("apolloDebugChatButtons", arg1x -> arg1x.method9(new ClientOption[]{this.field25}));
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method11(this);
   }

   public boolean method13() {
      return (Boolean)this.field18.get();
   }

   public boolean method14() {
      return (Boolean)this.field24.get();
   }

   public boolean method15() {
      return (Boolean)this.field25.get();
   }

   public boolean method16() {
      return (Boolean)this.field8.get();
   }

   public boolean method17() {
      return (Boolean)this.field16.get() || (Boolean)this.field17.get();
   }

   public boolean method19() {
      return (Boolean)this.field12.get();
   }

   public void method9(Message message1, String text2) {
      if ((Boolean)this.field12.get()) {
         String text3 = message1.getClass().getSimpleName();
         Set set4 = (Set)this.field13.get();
         if (set4.isEmpty() || set4.contains(text3)) {
            if (!(Boolean)this.field14.get()) {
               LunarLogger.method4("Apollo", "[Debug] [Send] %s (module=%s)", new Object[]{text3, String.valueOf(text2)});
            } else {
               String text5 = (String)Ref.method4().method84().method10(message1).orElse(null);
               if (text5 != null) {
                  LunarLogger.method4("Apollo", "[Debug] [Send] %s (module=%s)\n%s", new Object[]{text3, String.valueOf(text2), text5});
               }
            }
         }
      }
   }

   private void method10(HighlightImpl_3 highlightimpl_31) {
      if ((Boolean)this.field9.get()) {
         Any any2 = highlightimpl_31.getPacket();
         String text3 = this.method20(any2);
         Set set4 = (Set)this.field10.get();
         if (set4.isEmpty() || set4.contains(text3)) {
            if (!(Boolean)this.field11.get()) {
               LunarLogger.method4("Apollo", "[Debug] [Receive] %s", new Object[]{text3});
            } else {
               String text5 = (String)Ref.method4().method84().method11(any2).orElse(null);
               if (text5 != null) {
                  LunarLogger.method4("Apollo", "[Debug] [Receive] %s\n%s", new Object[]{text3, text5});
               }
            }
         }
      }
   }

   public boolean method21() {
      return (Boolean)this.field15.get();
   }

   public void method12(String text1, Object... items2) {
      if ((Boolean)this.field15.get()) {
         LunarLogger.method4("Apollo", "[Debug] [Transfer] " + text1, items2);
      }
   }

   public void method13(String text1, String text2, boolean flag3) {
      if ((Boolean)this.field17.get()) {
         LunarLogger.method4("Apollo", "[Debug] [Track-Raw] module=%s feature=%s%s", new Object[]{text1, text2, flag3 ? " (already tracked)" : ""});
      }

      if ((Boolean)this.field16.get() && !flag3) {
         LunarLogger.method4("Apollo", "[Debug] [Track] module=%s feature=%s", new Object[]{text1, text2});
      }
   }

   public void method14(Set<UUID> set1, Set<UUID> set2) {
      if ((Boolean)this.field19.get()) {
         LunarLogger.method4("Apollo", "[Debug] [Tab-Filtered] count=%d uuids=%s", new Object[]{set1.size(), set1});
      }

      if ((Boolean)this.field20.get()) {
         LunarLogger.method4("Apollo", "[Debug] [Tab-Unfiltered] count=%d uuids=%s", new Object[]{set2.size(), set2});
      }
   }

   public void method15(LoadTabLogosResponse loadtablogosresponse1) {
      if ((Boolean)this.field21.get()) {
         int number2 = loadtablogosresponse1.getTabLogosCount();
         List list3 = loadtablogosresponse1.getTabLogosList().stream().map(arg0 -> ProtoConverter.method1(arg0.getPlayerUuid())).toList();
         LunarLogger.method4("Apollo", "[Debug] [Tab-Asset] count=%d players=%s", new Object[]{number2, list3});
      }
   }

   public void method16(Collection<String> list1, String text2) {
      LunarLogger.method4("Apollo", "[Debug] [Register] serverChannels=%s", new Object[]{list1});
      LunarLogger.method4("Apollo", "[Debug] [Register] sentPayload='%s'", new Object[]{text2});
   }

   private void method17(EventSecond highlightimpl41) {
      this.field26.method8();
      if ((Boolean)this.field22.get()) {
         ApolloModuleManager foghandler22 = Ref.method4().method84();
         if (foghandler22 != null) {
            boolean flag3 = (Boolean)this.field23.get();

            for (ApolloModuleHandler highlight3iterator_36 : foghandler22.method2()
               .values()
               .stream()
               .sorted(Comparator.comparing(ApolloModuleHandler::getName))
               .toList()) {
               boolean flag7 = highlight3iterator_36.isEnabled();
               if (flag7 || flag3) {
                  TextComponent text8 = Component.text(highlight3iterator_36.getName(), flag7 ? NamedTextColor.GREEN : NamedTextColor.RED);
                  this.field26.method5(new TextHudComponent(text8));
               }
            }
         }
      }
   }

   public void method22() {
      if (!this.field27) {
         Set set1 = this.method23();

         for (ApolloModuleHandler highlight3iterator_33 : Ref.method4().method84().method3().values()) {
            for (Class clazz5 : highlight3iterator_33.method2()) {
               set1.add(clazz5.getSimpleName());
            }
         }

         this.method19(this.field10, set1);
         this.method19(this.field13, set1);
         this.field27 = true;
      }
   }

   private void method19(MultiSelectOption lightingextension49121, Set<String> set2) {
      lightingextension49121.method7().clear();
      lightingextension49121.method7().addAll(set2);
   }

   private String method20(Any any1) {
      String text2 = any1.getTypeUrl();
      return text2.substring(text2.lastIndexOf(46) + 1);
   }

   private Set<String> method23() {
      LinkedHashSet set1 = new LinkedHashSet();
      set1.add(PlayerHandshakeMessage.class.getSimpleName());
      set1.add(UpdatePlayerWorldMessage.class.getSimpleName());
      set1.add(ConfigurableSettings.class.getSimpleName());
      set1.add(OverrideConfigurableSettingsMessage.class.getSimpleName());
      return set1;
   }
}
