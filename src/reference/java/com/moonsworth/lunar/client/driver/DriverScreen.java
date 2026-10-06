package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.gui.HostWorldScreen;
import java.util.function.Function;
import lombok.Generated;

public enum DriverScreen {
   NULL(arg0 -> null, false),
   SINGLEPLAYER(arg0 -> Bridge.method8().method28(arg0), true),
   MULTIPLAYER(arg0 -> Bridge.method8().method29(arg0), true),
   SETTINGS(arg0 -> Bridge.method8().method30(arg0), false),
   REALMS(arg0 -> Bridge.method8().method32(arg0), true),
   LANGUAGE(arg0 -> Bridge.method8().method31(arg0), false),
   HOSTED_WORLD_SETTINGS(arg0 -> Bridge.method8().method18(new HostWorldScreen("hostedWorldSettings")), true);

   final Function<GuiScreenBridge, GuiScreenBridge> initScreen;
   final boolean requiresAuthentication;

   DriverScreen(Function<GuiScreenBridge, GuiScreenBridge> function3, boolean flag4) {
      this.initScreen = function3;
      this.requiresAuthentication = flag4;
   }

   @Generated
   public Function<GuiScreenBridge, GuiScreenBridge> getInitScreen() {
      return this.initScreen;
   }

   @Generated
   public boolean isRequiresAuthentication() {
      return this.requiresAuthentication;
   }
}
