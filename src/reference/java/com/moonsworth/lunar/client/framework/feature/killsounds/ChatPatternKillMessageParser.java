package com.moonsworth.lunar.client.framework.feature.killsounds;

import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.framework.LunarConstants;
import io.leangen.geantyref.TypeToken;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

public class ChatPatternKillMessageParser implements KillMessageParser {
   private final List<KillSoundFilter> field1 = new ArrayList<>();

   public ChatPatternKillMessageParser() {
   }

   @Override
   public Optional<String> parseTargetName(String text1, TypedChatMessage data2) {
      String text3 = data2.CCCCRIIIHIIRHOCIIOICICHHROHHRC();
      if (!StringUtils.isEmpty(text3) && !StringUtils.isWhitespace(text3)) {
         boolean flag4 = ServerBrandWatcher.method8(KeystrokesType.HYPIXEL);
         String text5 = HypixelLocationListener.field7.method7().field2;
         return this.field1.stream().filter(arg2x -> arg2x.method1(flag4, text5)).map(arg2x -> arg2x.method2(text1, text3)).flatMap(Optional::stream).findFirst();
      } else {
         return Optional.empty();
      }
   }

   public void method1() {
      try {
         this.field1.clear();
         Path path1 = LunarConstants.field12.resolve("kill-sound-chat-patterns.json");
         String text2 = Files.readString(path1);
         TypeToken typetoken3 = new TypeToken<List<KillSoundFilter>>() {};
         this.field1.addAll((Collection<? extends KillSoundFilter>)LunarConstants.field23.fromJson(text2, typetoken3.getType()));
      } catch (Exception exception4) {
         CrashReporter.method5(exception4, "Loading KillSounds chat patterns");
      }
   }
}
