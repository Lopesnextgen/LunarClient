package com.moonsworth.lunar.client.command;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.client.command.Nameplate2_2;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.util.ThreadModuleDump22;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;

public final class PlayerArgumentParser extends StringArgumentParser {
   public static final PlayerArgumentParser field2 = new PlayerArgumentParser();

   @Override
   public Nameplate2_2 method3() {
      return (var1, var2) -> {
         String var3 = var2.method1().toLowerCase(Locale.ROOT);
         HashSet var4 = new HashSet();
         Stream.concat(
               ThreadModuleDump63.method4().method50().method13().stream().map(Memory::getName),
               Stream.concat(
                  Objects.requireNonNull(ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getPlayerInfoList())
                     .stream()
                     .<GameProfile>map(Bridge2_33::bridge$getGameProfile)
                     .filter(var0 -> !ThreadModuleDump22.method1(var0.getName(), var0.getId(), true))
                     .map(GameProfile::getName),
                  this.method4()
               )
            )
            .filter(var1x -> var1x.toLowerCase(Locale.ROOT).startsWith(var3))
            .forEach(var2x -> {
               if (var4.add(var2x)) {
                  var2.method2(var2x);
               }
            });
      };
   }

   private Stream<String> method4() {
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      return var1 == null
         ? Stream.empty()
         : var1.bridge$getPlayerEntities()
            .stream()
            .filter(var0 -> !ThreadModuleDump22.method1(var0.bridge$getGameProfile().getName(), var0.bridge$getGameProfile().getId(), true))
            .map(var0 -> var0.bridge$getGameProfile().getName());
   }
}
