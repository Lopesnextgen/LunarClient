package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.module.title.TitleModule;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.title.v1.DisplayTitleMessage;
import com.lunarclient.apollo.title.v1.ResetTitlesMessage;
import com.lunarclient.apollo.title.v1.TitleType;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.mixin.gui.ServerJoinEvent;
import com.moonsworth.lunar.client.event.mixin.gui.TitleEvent;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title.Times;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator14 extends ApolloModuleHandler {
   public Highlight3Iterator14() {
      super("title", "Title");
      this.handle(ServerJoinEvent.class, this::method5);
   }

   @Override
   public Collection<Option<?, ?, ?>> method1() {
      return List.of(TitleModule.CLEAR_TITLE_ON_SERVER_SWITCH);
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(DisplayTitleMessage.class, ResetTitlesMessage.class);
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(DisplayTitleMessage.class)
         .ifPresent(
            var0 -> {
               TitleType var1x = var0.getTitleType();
               Component var2 = Rewindhandlers3.method4(var0.getAdventureJsonMessage());
               if (var2 != null) {
                  ThreadModuleDump63.method3()
                     .bridge$getGuiIngame()
                     .bridge$displayTitle(
                        var1x == TitleType.TITLE_TYPE_TITLE ? var2 : null,
                        var1x == TitleType.TITLE_TYPE_SUBTITLE ? var2 : null,
                        Times.times(
                           NetworkTypes.fromProtobuf(var0.getFadeInTime()),
                           NetworkTypes.fromProtobuf(var0.getDisplayTime()),
                           NetworkTypes.fromProtobuf(var0.getFadeOutTime())
                        ),
                        var0.getScale(),
                        var0.getInterpolationScale(),
                        var0.getInterpolationRate()
                     );
                  if (var1x == TitleType.TITLE_TYPE_TITLE) {
                     ClientEventBus.method29().method12(TitleEvent.class, () -> new TitleEvent(var2, TitleEvent.Type.APOLLO));
                  }
               }
            }
         );
      var1.unpack(ResetTitlesMessage.class).ifPresent(var1x -> this.method4());
   }

   private void method4() {
      ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$clearTitle();
   }

   private void method5(ServerJoinEvent var1) {
      if ((Boolean)this.getOptions().get(TitleModule.CLEAR_TITLE_ON_SERVER_SWITCH)) {
         this.method4();
      }
   }
}
