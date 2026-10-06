package com.moonsworth.lunar.client.framework.feature.killsounds.mixin;

import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler23;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import io.leangen.geantyref.TypeToken;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

public class Killsounds4Loader implements Killsounds4 {
   private final List<Killsounds3> field1 = new ArrayList<>();

   @Override
   public Optional<String> parseTargetName(String var1, Data var2) {
      String var3 = var2.CCCCRIIIHIIRHOCIIOICICHHROHHRC();
      if (!StringUtils.isEmpty(var3) && !StringUtils.isWhitespace(var3)) {
         boolean var4 = Highlight3Iterator.method8(KeystrokesType.HYPIXEL);
         String var5 = GuiRewindhandlersHandler23.field7.method7().field2;
         return this.field1.stream().filter(var2x -> var2x.method1(var4, var5)).map(var2x -> var2x.method2(var1, var3)).flatMap(Optional::stream).findFirst();
      } else {
         return Optional.empty();
      }
   }

   public void method1() {
      try {
         this.field1.clear();
         Path var1 = ThreadModuleDump48.field12.resolve("kill-sound-chat-patterns.json");
         String var2 = Files.readString(var1);
         TypeToken var3 = new TypeToken<List<Killsounds3>>() {};
         this.field1.addAll((Collection<? extends Killsounds3>)ThreadModuleDump48.field23.fromJson(var2, var3.getType()));
      } catch (Exception var4) {
         Inventorymod2.method5(var4, "Loading KillSounds chat patterns");
      }
   }
}
