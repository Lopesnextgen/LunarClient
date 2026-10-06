package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.client.gui.HostWorldScreen;
import java.util.function.Function;
import lombok.Generated;

public enum DriverScreenLegacy {
   NULL(var0 -> null, false),
   SINGLEPLAYER(var0 -> Bridge.method8().method28(var0), true),
   MULTIPLAYER(var0 -> Bridge.method8().method29(var0), true),
   SETTINGS(var0 -> Bridge.method8().method30(var0), false),
   REALMS(var0 -> Bridge.method8().method32(var0), true),
   LANGUAGE(var0 -> Bridge.method8().method31(var0), false),
   HOSTED_WORLD_SETTINGS(var0 -> Bridge.method8().method18(new HostWorldScreen("hostedWorldSettings")), true);

   final Function<Bridge5Extension6, Bridge5Extension6> initScreen;
   final boolean requiresAuthentication;

   DriverScreenLegacy(Function<Bridge5Extension6, Bridge5Extension6> var3, boolean var4) {
      this.initScreen = var3;
      this.requiresAuthentication = var4;
   }

   @Generated
   public Function<Bridge5Extension6, Bridge5Extension6> getInitScreen() {
      return this.initScreen;
   }

   @Generated
   public boolean isRequiresAuthentication() {
      return this.requiresAuthentication;
   }
}
