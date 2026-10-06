package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.module.title.TitleModule;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.title.v1.DisplayTitleMessage;
import com.lunarclient.apollo.title.v1.ResetTitlesMessage;
import com.lunarclient.apollo.title.v1.TitleType;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerJoin;
import com.moonsworth.lunar.client.event.mixin.gui.EventTitle;
import com.moonsworth.lunar.client.network.apollo.ApolloPacketUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title.Times;

public class TitleApolloHandler extends ApolloModuleHandler {
   public TitleApolloHandler() {
      super("title", "Title");
      this.handle(EventServerJoin.class, this::method5);
   }

   public Collection<Option<?, ?, ?>> method1() {
      return List.of(TitleModule.CLEAR_TITLE_ON_SERVER_SWITCH);
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(DisplayTitleMessage.class, ResetTitlesMessage.class);
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      highlightimpl_31.unpack(DisplayTitleMessage.class)
         .ifPresent(
            arg0 -> {
               TitleType titletype1x = arg0.getTitleType();
               Component component2 = ApolloPacketUtils.method4(arg0.getAdventureJsonMessage());
               if (component2 != null) {
                  Ref.method3()
                     .bridge$getGuiIngame()
                     .bridge$displayTitle(
                        titletype1x == TitleType.TITLE_TYPE_TITLE ? component2 : null,
                        titletype1x == TitleType.TITLE_TYPE_SUBTITLE ? component2 : null,
                        Times.times(
                           NetworkTypes.fromProtobuf(arg0.getFadeInTime()),
                           NetworkTypes.fromProtobuf(arg0.getDisplayTime()),
                           NetworkTypes.fromProtobuf(arg0.getFadeOutTime())
                        ),
                        arg0.getScale(),
                        arg0.getInterpolationScale(),
                        arg0.getInterpolationRate()
                     );
                  if (titletype1x == TitleType.TITLE_TYPE_TITLE) {
                     LunarEventBus.method29().method12(EventTitle.class, () -> new EventTitle(component2, EventTitle.TitleSource.APOLLO));
                  }
               }
            }
         );
      highlightimpl_31.unpack(ResetTitlesMessage.class).ifPresent(arg1x -> this.method4());
   }

   private void method4() {
      Ref.method3().bridge$getGuiIngame().bridge$clearTitle();
   }

   private void method5(EventServerJoin event) {
      if ((Boolean)this.getOptions().get(TitleModule.CLEAR_TITLE_ON_SERVER_SWITCH)) {
         this.method4();
      }
   }
}
