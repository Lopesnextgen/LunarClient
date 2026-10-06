package com.moonsworth.lunar.client.network.apollo;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.TypeRegistry;
import com.google.protobuf.Value;
import com.google.protobuf.Any.Builder;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.util.JsonFormat.Parser;
import com.google.protobuf.util.JsonFormat.Printer;
import com.lunarclient.apollo.common.v1.LunarClientVersion;
import com.lunarclient.apollo.common.v1.MinecraftVersion;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.lunarclient.apollo.network.NetworkOptions;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.player.v1.EmbeddedCheckoutSupport;
import com.lunarclient.apollo.player.v1.PlayerHandshakeMessage;
import com.lunarclient.apollo.player.v1.UpdatePlayerWorldMessage;
import com.lunarclient.websocket.apollo.v1.TrackFeatureAdoptionRequest;
import com.lunarclient.websocket.server.v1.CheckAuthorizedFeaturesRequest;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeImplementation;
import com.moonsworth.lunar.bridge.Bridge3Extension_2;
import com.moonsworth.lunar.bridge.Bridge7_9;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.client.freelook.Highlight3Handler;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.mixin.gui.ServerChangeEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerPingEvent;
import com.moonsworth.lunar.client.event.mixin.gui.PluginMessageEvent;
import com.moonsworth.lunar.client.mod.misc.debug.ApolloDebugMod;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.Highlight3Iterator14;
import com.moonsworth.lunar.client.Highlight3Iterator13;
import com.moonsworth.lunar.client.Highlight3Iterator12;
import com.moonsworth.lunar.client.Highlight3Iterator9;
import com.moonsworth.lunar.client.Highlight3Iterator21;
import com.moonsworth.lunar.client.Highlight3Iterator30;
import com.moonsworth.lunar.client.Highlight3Iterator6;
import com.moonsworth.lunar.client.Highlight3Iterator5;
import com.moonsworth.lunar.client.MixinExtraType;
import com.moonsworth.lunar.client.Highlight3Iterator3;
import com.moonsworth.lunar.client.Highlight3Iterator18;
import com.moonsworth.lunar.client.Highlight3Iterator23;
import com.moonsworth.lunar.client.Highlight3Iterator10;
import com.moonsworth.lunar.client.Highlight3Iterator20;
import com.moonsworth.lunar.client.Highlight3Iterator;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.Highlight3Iterator31;
import com.moonsworth.lunar.client.Highlight3Iterator27;
import com.moonsworth.lunar.client.Highlight3Iterator25;
import com.moonsworth.lunar.client.cosmetics.CosmeticRenderFilter;
import com.moonsworth.lunar.client.Highlight3Iterator7;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.MixinRewindhandlers;
import com.moonsworth.lunar.client.Highlight3Iterator29;

public class ApolloModuleManager extends com.moonsworth.lunar.client.framework.loading.ItemMapHandler<String, ApolloModuleHandler> implements EventRegistrar {
   private static final ExecutorService field2 = Executors.newSingleThreadExecutor(new DefaultThreadFactory("lunar-apollo-manager-thread", true));
   public static final String field3 = "apollo:json";
   private final Map<Class<? extends ApolloModule>, String> field4 = new IdentityHashMap<>();
   private static TypeRegistry field5;
   private Map<Class<? extends Message>, String> field6;
   private boolean field7;
   private boolean field8;
   @Nullable
   private CosmeticRenderFilter field9;
   private Printer field10;
   private Parser field11;
   private final Set<String> field12 = Sets.newHashSet();

   public static ModuleDefinition method1(Class<? extends ApolloModule> var0) {
      ModuleDefinition var1 = var0.getAnnotation(ModuleDefinition.class);
      if (var1 == null) {
         throw new RuntimeException("Apollo module class " + var0.getSimpleName() + " must be decorated with a ModuleDefinition annotation");
      } else {
         return var1;
      }
   }

   public ApolloModuleManager() {
      this.handle(PluginMessageEvent.class, this::method12);
      this.handle(ServerChangeEvent.class, this::method17);
      this.handle(HighlightImpl_3.class, this::method20);
      this.handle(ServerPingEvent.class, this::method22);
   }

   private String method2(Class<? extends ApolloModule> var1) {
      return this.field4.computeIfAbsent(var1, var1x -> method1(var1).id());
   }

   public <T extends ApolloModuleHandler> Optional<T> method3(Class<? extends ApolloModule> var1) {
      return !this.field7 ? Optional.empty() : this.method4(var1, true);
   }

   public <T extends ApolloModuleHandler> Optional<T> method4(Class<? extends ApolloModule> var1, boolean var2) {
      return this.method6(this.method2(var1), var2);
   }

   public <T extends ApolloModuleHandler> Optional<T> method5(Class<? extends ApolloModule> var1, MixinExtraType var2) {
      return this.method7(method1(var1).id(), true, var2);
   }

   public <T extends ApolloModuleHandler> Optional<T> method6(String var1, boolean var2) {
      return this.method7(var1, var2, null);
   }

   public <T extends ApolloModuleHandler> Optional<T> method7(String var1, boolean var2, MixinExtraType var3) {
      ApolloModuleHandler var4 = (ApolloModuleHandler)this.method3().get(var1);
      if (var4 != null && (!var2 || var4.isEnabled())) {
         return var3 == null || this.field9 != null && this.field9.method4(var3) ? Optional.ofNullable((T)var4) : Optional.empty();
      } else {
         return Optional.empty();
      }
   }

   @Override
   protected Map<String, ApolloModuleHandler> method3() {
      this.field6 = Maps.newHashMap();
      HashMap var1 = new HashMap();
      this.method9(var1, new Highlight3Iterator10());
      this.method9(var1, new BorderApolloHandler());
      this.method9(var1, new Highlight3Iterator6());
      this.method9(var1, new ChatApolloHandler());
      this.method9(var1, new Highlight3Iterator27());
      this.method9(var1, new Highlight3Iterator21());
      this.method9(var1, new Highlight3Iterator25());
      this.method9(var1, new CosmeticApolloHandler());
      this.method9(var1, new Highlight3Iterator12());
      this.method9(var1, new GlintApolloHandler());
      this.method9(var1, new Highlight3Iterator20());
      this.method9(var1, new Highlight3Iterator23());
      this.method9(var1, new HologramApolloHandler());
      this.method9(var1, new InventoryApolloHandler());
      this.method9(var1, new LimbApolloHandler());
      this.method9(var1, new MarkerApolloHandler());
      this.method9(var1, new ModSettingApolloHandler());
      this.method9(var1, new NametagApolloHandler());
      this.method9(var1, new Highlight3Iterator30());
      this.method9(var1, new Highlight3Iterator18());
      this.method9(var1, new Highlight3Iterator31());
      this.method9(var1, new Highlight3Iterator13());
      this.method9(var1, new Highlight3Iterator7());
      this.method9(var1, new Highlight3Iterator5());
      this.method9(var1, new ServerLinkApolloHandler());
      this.method9(var1, new Highlight3Iterator3());
      this.method9(var1, new StaffModApolloHandler());
      this.method9(var1, new StopwatchApolloHandler());
      this.method9(var1, new TeamApolloHandler());
      this.method9(var1, new Highlight3Iterator9());
      this.method9(var1, new Highlight3Iterator14());
      this.method9(var1, new WaypointApolloHandler());
      this.method9(var1, new com.moonsworth.lunar.client.network.transfer.TransferHandler());
      this.method9(var1, new Highlight3Iterator29());
      this.method9(var1, new Highlight3Iterator());
      return var1;
   }

   @Override
   public void close() {
      this.method17(null);
   }

   private void method9(Map<String, ApolloModuleHandler> var1, ApolloModuleHandler var2) {
      String var3 = var2.getId();
      var1.put(var3, var2);
      var2.method2().forEach(var2x -> this.field6.put((Class<? extends Message>)var2x, var3));
   }

   private Printer printer() {
      if (this.field10 == null) {
         this.field10 = JsonFormat.printer().usingTypeRegistry(field5).preservingProtoFieldNames().includingDefaultValueFields();
      }

      return this.field10;
   }

   public Optional<String> method10(Message var1) {
      return this.method11(Any.pack(var1));
   }

   public Optional<String> method11(Any var1) {
      if (field5 == null) {
         return Optional.empty();
      }

      try {
         return Optional.of(this.printer().print(var1));
      } catch (InvalidProtocolBufferException var3) {
         Slayer.method6("Apollo", "Failed to format protobuf as JSON: %s", var3.getMessage());
         return Optional.empty();
      }
   }

   private Parser parser() {
      if (this.field11 == null) {
         this.field11 = JsonFormat.parser().usingTypeRegistry(field5);
      }

      return this.field11;
   }

   private void method12(PluginMessageEvent var1) {
      String var2 = var1.getChannel();
      if (var2.equals("lunar:apollo") || var2.equals("apollo:json")) {
         this.field7 = true;
         byte[] var3 = var1.getData();
         var1.cancel();

         try {
            this.field8 = var2.equals("apollo:json");
            Any var4;
            if (this.field8) {
               Builder var5 = Any.newBuilder();
               this.parser().merge(new String(var3), var5);
               var4 = var5.build();
            } else {
               var4 = Any.parseFrom(var3);
            }

            if (var4.is(OverrideConfigurableSettingsMessage.class) || var4.is(ConfigurableSettings.class)) {
               this.method18(var4);
            }

            ClientEventBus.method29().method12(HighlightImpl_3.class, () -> new HighlightImpl_3(var4));
         } catch (InvalidProtocolBufferException var6) {
            Rewindhandlers3.method6(var6, Arrays.toString(var3));
         }
      }
   }

   public void method13(Class<? extends Message> var1) {
      String var2 = this.field6.get(var1);
      if (var2 != null) {
         this.method14(var2, var1);
      }
   }

   public void method14(String var1, Class<? extends Message> var2) {
      this.method15(var1, var2.getSimpleName());
   }

   public void method15(String var1, String var2) {
      boolean var3 = this.field12.contains(var2);
      ApolloDebugMod var4 = ThreadModuleDump63.method4().method40().method80();
      if (var4 != null && var4.isEnabled() && var4.method17()) {
         var4.method13(var1, var2, var3);
      }

      if (!var3) {
         ThreadModuleDump63.method5().ifPresent(var3x -> {
            var3x.method85().trackFeatureAdoption(null, TrackFeatureAdoptionRequest.newBuilder().setModule(var1).setFeature(var2).build(), var0 -> {});
            this.field12.add(var2);
         });
      }
   }

   public void sendPacket(Message var1) {
      this.method16(var1, null);
   }

   public void method16(Message var1, String var2) {
      ClientPacketListenerBridge var3 = ThreadModuleDump63.method9();
      if (var3 != null) {
         BridgeImplementation var4 = Bridge.method8();
         Any var5 = Any.pack(var1);
         Bridge3Extension_2 var7;
         if (this.field8) {
            String var8;
            try {
               var8 = this.printer().print(var5);
            } catch (InvalidProtocolBufferException var10) {
               var10.printStackTrace();
               return;
            }

            Bridge7_9 var6 = var4.method23(Unpooled.wrappedBuffer(var8.getBytes(StandardCharsets.UTF_8)));
            var7 = var4.method24("apollo:json", var6);
         } else {
            Bridge7_9 var11 = var4.method23(Unpooled.wrappedBuffer(var5.toByteArray()));
            var7 = var4.method24("lunar:apollo", var11);
         }

         var3.bridge$addToSendQueue(var7);
         ApolloDebugMod var12 = ThreadModuleDump63.method4().method40().method80();
         if (var12 != null && var12.isEnabled() && var12.method19()) {
            var12.method9(var1, var2);
         }

         if (var2 != null) {
            this.method14(var2, (Class<? extends Message>)var1.getClass());
         }
      }
   }

   private void method17(@Nullable ServerChangeEvent var1) {
      this.field7 = false;
      this.field12.clear();
      if (var1 == null || var1.method1()) {
         this.field9 = null;
         this.method3().values().stream().filter(ApolloModuleHandler::isEnabled).forEach(ApolloModuleHandler::disable);
      }
   }

   public void method18(Any var1) {
      List var2;
      try {
         if (var1.is(OverrideConfigurableSettingsMessage.class)) {
            OverrideConfigurableSettingsMessage var3 = (OverrideConfigurableSettingsMessage)var1.unpack(OverrideConfigurableSettingsMessage.class);
            var2 = var3.getConfigurableSettingsList();
         } else {
            var2 = List.of((ConfigurableSettings)var1.unpack(ConfigurableSettings.class));
         }
      } catch (InvalidProtocolBufferException var11) {
         throw new RuntimeException(var11);
      }

      for (ConfigurableSettings var4 : var2) {
         if (var4.hasApolloModule()) {
            Optional var5 = this.method6(var4.getApolloModule(), false);
            if (!var5.isEmpty()) {
               ApolloModuleHandler var6 = (ApolloModuleHandler)var5.get();
               if (var4.getEnable()) {
                  var6.enable();
               } else {
                  var6.disable();
               }

               if (var6.isEnabled() && var6.getOptions() instanceof OptionsImpl var7) {
                  for (Entry var9 : var4.getPropertiesMap().entrySet()) {
                     String var10 = (String)var9.getKey();
                     var7.method1(var10).ifPresent(var5x -> {
                        Object var6x = NetworkOptions.unwrapValue((Value)var9.getValue(), var5x.getTypeToken().getType());
                        var7.set(var5x, var6x);
                        if (!(var6 instanceof ModSettingApolloHandler)) {
                           Slayer.method3("[Apollo] [%s] %s updated to '%s'", var6.getName(), var10, var6x);
                           this.method15(var6.getId(), var10 + ":" + var6x);
                        }
                     });
                  }
               }
            }
         }
      }
   }

   public ConfigurableSettings method19(ApolloModuleHandler var1) {
      com.lunarclient.apollo.configurable.v1.ConfigurableSettings.Builder var2 = ConfigurableSettings.newBuilder()
         .setApolloModule(var1.getId())
         .setEnable(var1.isEnabled());
      if (var1.getOptions() instanceof OptionsImpl var3) {
         for (Entry var5 : var3.getRegistry().entrySet()) {
            Option var6 = (Option)var5.getValue();
            Value var7 = NetworkOptions.wrapValue(Value.newBuilder(), var6.getTypeToken().getType(), var3.get(var6));
            if (!var7.hasNullValue()) {
               var2.putProperties((String)var5.getKey(), var7);
            }
         }
      }

      return var2.build();
   }

   private void method20(HighlightImpl_3 var1) {
      var1.unpack(UpdatePlayerWorldMessage.class).ifPresent(var0 -> Client.method109().setWorld(var0.getWorld()));

      for (ApolloModuleHandler var3 : this.method3().values()) {
         var3.method3(var1);
      }
   }

   public void method21() {
      com.lunarclient.apollo.player.v1.PlayerHandshakeMessage.Builder var1 = PlayerHandshakeMessage.newBuilder()
         .setMinecraftVersion(MinecraftVersion.newBuilder().setEnum(Bridge.getMinecraftVersion().getId()))
         .setLunarClientVersion(LunarClientVersion.newBuilder().setGitBranch(LunarBuildData.field1).setGitCommit(LunarBuildData.field3).setSemver(Client.method19()));
      this.<ApolloModuleHandler>method4(ModSettingModule.class, false).ifPresent(var1x -> {
         Map var2x = ((ModSettingApolloHandler)var1x).method17().method1();
         var1.putAllModStatus(var2x);
      });
      ThreadModuleDump63.method6().ifPresentOrElse(var1x -> {
         EmbeddedCheckoutSupport var2x = var1x.method20();
         if (var2x == EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_UNSPECIFIED) {
            var1.clearEmbeddedCheckoutSupport();
         } else {
            var1.setEmbeddedCheckoutSupport(var2x);
         }
      }, var1::clearEmbeddedCheckoutSupport);
      this.sendPacket(var1.build());
      ApolloDebugMod var2 = Client.method109().method40().method80();
      if (var2 != null) {
         var2.method22();
      }
   }

   private void method22(ServerPingEvent var1) {
      this.method23(var1.method2());
   }

   public void method23(String var1) {
      if (var1.equalsIgnoreCase("test.lunarclient.com")) {
         Set var2 = Stream.of(MixinExtraType.values()).collect(Collectors.toSet());
         this.field9 = new CosmeticRenderFilter(var2, List.of(), List.of(), List.of());
      } else {
         ThreadModuleDump63.method5()
            .ifPresent(
               var2x -> var2x.method103()
                  .checkAuthorizedFeatures(
                     null,
                     CheckAuthorizedFeaturesRequest.newBuilder().setServerIp(var1).build(),
                     var1xx -> {
                        Set var2xx = var1xx.getScopesList().stream().map(MixinExtraType::get).filter(Objects::nonNull).collect(Collectors.toSet());
                        if (!var2xx.isEmpty()) {
                           this.field9 = new CosmeticRenderFilter(
                              var2xx, var1xx.getAssociatedCosmeticIdsList(), var1xx.getAssociatedEmoteIdsList(), var1xx.getAssociatedSprayIdsList()
                           );
                        }
                     }
                  )
            );
      }
   }

   @Generated
   public boolean method24() {
      return this.field7;
   }

   @Nullable
   @Generated
   public CosmeticRenderFilter method25() {
      return this.field9;
   }

   static {
      Highlight3Handler.method6("lunar:apollo");
      Highlight3Handler.method6("apollo:json");
      field2.submit(() -> field5 = MixinRewindhandlers.method1());
   }
}
