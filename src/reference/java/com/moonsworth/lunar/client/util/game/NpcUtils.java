package com.moonsworth.lunar.client.util.game;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import java.util.UUID;
import lombok.Generated;

public final class NpcUtils {
   public static boolean method1(String text, UUID uuid1, boolean flag) {
      if (uuid1.version() == 4 || flag && uuid1.version() == 1) {
         if (text.contains(" ")) {
            return true;
         } else {
            return text.isBlank() ? true : text.contains("§");
         }
      } else {
         return true;
      }
   }

   public static boolean method2(Bridge6_10 bridge6_100, boolean flag1) {
      if (bridge6_100.bridge$isSelf()) {
         return false;
      }

      if (bridge6_100 instanceof Bridge5_11) {
         String text2 = ((Bridge5_11)bridge6_100).bridge$getTeamName().orElse("");
         if (text2.contains("npc") || text2.startsWith("CIT-")) {
            return true;
         }
      }

      return method1(bridge6_100.bridge$getName(), bridge6_100.bridge$getUniqueID(), flag1);
   }

   public static boolean method3(EntityPlayerBridge entity, boolean flag1) {
      if (entity.bridge$isSelf()) {
         return false;
      }

      if (entity instanceof Bridge5_11) {
         String text2 = ((Bridge5_11)entity).bridge$getTeamName().orElse("");
         if (text2.contains("npc") || text2.startsWith("CIT-")) {
            return true;
         }
      }

      return method1(entity.bridge$getName(), entity.bridge$getUniqueID(), flag1);
   }

   @Generated
   private NpcUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
