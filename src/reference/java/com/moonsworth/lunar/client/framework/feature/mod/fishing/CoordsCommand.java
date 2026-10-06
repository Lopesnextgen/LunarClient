package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;

public class CoordsCommand extends SkyBlockChatCommand {
   public CoordsCommand() {
   }

   @Override
   public String getCommand() {
      return "coords";
   }

   @Override
   public List<String> getAliases() {
      return List.of("pos");
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String text1, String text2, String[] items3, FishingChatType gui2extension4) {
      this.method6(
         gui2extension4,
         "x: "
            + Ref.method7().bridge$getBlockX()
            + ", y: "
            + Ref.method7().bridge$getBlockY()
            + ", z: "
            + Ref.method7().bridge$getBlockZ()
      );
   }
}
