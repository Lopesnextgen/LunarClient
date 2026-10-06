package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.module.serverlink.ServerLinkModule;
import com.lunarclient.apollo.module.serverlink.pausemenu.LegacyServerLinkPlacement;
import com.lunarclient.apollo.module.serverlink.pausemenu.ModernServerLinkPlacement;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.serverlink.v1.AddServerLinkMessage;
import com.lunarclient.apollo.serverlink.v1.OverrideServerLinkResourceMessage;
import com.lunarclient.apollo.serverlink.v1.RemoveServerLinkMessage;
import com.lunarclient.apollo.serverlink.v1.ResetServerLinkResourceMessage;
import com.lunarclient.apollo.serverlink.v1.ResetServerLinksMessage;
import com.lunarclient.apollo.serverlink.v1.ServerLink;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import java.net.URI;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public class ServerLinkApolloHandler extends ApolloModuleHandler {
   @Nullable
   private ResourceLocationBridge field4;
   private final Map<String, ServerLinkApolloHandler.Data> field5 = new LinkedHashMap<>();

   public ServerLinkApolloHandler() {
      super("server_link", "Server Link");
   }

   @Override
   protected void onDisable() {
      this.method10();
      this.field4 = null;
   }

   @Override
   public Collection<Option<?, ?, ?>> method1() {
      return List.of(ServerLinkModule.LEGACY_BUTTON_PLACEMENT, ServerLinkModule.MODERN_BUTTON_PLACEMENT);
   }

   public LegacyServerLinkPlacement method3() {
      LegacyServerLinkPlacement var1 = (LegacyServerLinkPlacement)this.getOptions().get(ServerLinkModule.LEGACY_BUTTON_PLACEMENT);
      return var1 != null ? var1 : LegacyServerLinkPlacement.NEW_ROW;
   }

   public ModernServerLinkPlacement method4() {
      ModernServerLinkPlacement var1 = (ModernServerLinkPlacement)this.getOptions().get(ServerLinkModule.MODERN_BUTTON_PLACEMENT);
      return var1 != null ? var1 : ModernServerLinkPlacement.REPLACE_REPORT_BUGS;
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(
         OverrideServerLinkResourceMessage.class,
         ResetServerLinkResourceMessage.class,
         AddServerLinkMessage.class,
         RemoveServerLinkMessage.class,
         ResetServerLinksMessage.class
      );
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(OverrideServerLinkResourceMessage.class).ifPresent(var1x -> {
         if (var1x.hasIcon()) {
            String var2 = NetworkTypes.fromProtobuf(var1x.getIcon()).getResourceLocation();
            if (var2 != null) {
               this.field4 = ResourceLocationBridge.create(var2);
            }
         }
      });
      var1.unpack(ResetServerLinkResourceMessage.class).ifPresent(var1x -> this.field4 = null);
      var1.unpack(AddServerLinkMessage.class)
         .ifPresent(var1x -> var1x.getServerLinksList().stream().map(this::method6).filter(Objects::nonNull).forEach(this::method8));
      var1.unpack(RemoveServerLinkMessage.class).ifPresent(var1x -> var1x.getServerLinkIdsList().forEach(this::method9));
      var1.unpack(ResetServerLinksMessage.class).ifPresent(var1x -> this.method10());
   }

   private ServerLinkApolloHandler.Data method6(ServerLink var1) {
      Component var2 = Rewindhandlers3.method4(var1.getDisplayNameAdventureJsonLines());
      if (var2 == null) {
         return null;
      }

      URI var3 = Rewindhandlers3.method8(var1.getUrl());
      return var3 == null ? null : new ServerLinkApolloHandler.Data(var1.getId(), var2, var3);
   }

   public Collection<ServerLinkApolloHandler.Data> method8() {
      return this.field5.values();
   }

   public void method8(ServerLinkApolloHandler.Data var1) {
      this.field5.put(var1.id(), var1);
   }

   public void method9(String var1) {
      this.field5.remove(var1);
   }

   public void method10() {
      this.field5.clear();
   }

   @Nullable
   @Generated
   public ResourceLocationBridge getResource() {
      return this.field4;
   }

   public class Data {
      private final String field1;
      private final Component field2;
      private final URI uri;

      public Data(String var1, Component var2, URI var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.uri = var3;
      }

      public String id() {
         return this.field1;
      }

      public Component method1() {
         return this.field2;
      }
   }
}
