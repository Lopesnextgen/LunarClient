package com.moonsworth.lunar.client.config;

import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.mod.misc.servercommandconfig.ServerCommandConfig;
import com.moonsworth.lunar.client.mod.render.serverborders.ServerBorders;
import com.moonsworth.lunar.client.mod.render.servercustomnameplate.ServerCustomNameplate;
import com.moonsworth.lunar.client.mod.render.serverholograms.ServerHolograms;
import java.util.Set;
import lombok.Generated;

public class ServerFeaturesSettings extends com.moonsworth.lunar.client.config.FavoriteColorsConfig<Framework7Extension> {
   private ServerCustomNameplate field2;
   private ServerCommandConfig field3;

   @Override
   protected Set<Framework7Extension> method1() {
      return this.method3(
         new Framework7Extension[]{
            new ServerHolograms(), this.field2 = new ServerCustomNameplate(), new ServerBorders(), this.field3 = new ServerCommandConfig()
         }
      );
   }

   @Override
   public String method5() {
      return "features.json";
   }

   @Generated
   public ServerCustomNameplate method10() {
      return this.field2;
   }

   @Generated
   public ServerCommandConfig method11() {
      return this.field3;
   }
}
