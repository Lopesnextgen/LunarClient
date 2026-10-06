package com.moonsworth.lunar.client.cosmetics.gecko;

import lombok.Generated;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.inactive.mixin.fishing.FishingHandler44;

public enum FishingType {
   MOVE_TO_OWNER("move_to_owner", MoveToOwnerTask.class),
   TELEPORT_TO_OWNER("teleport_to_owner", TeleportToOwnerTask.class),
   RANDOM_WANDER("random_wander", FishingHandler.class),
   ATTACH_TO_OWNER("attach_to_owner", AttachToOwnerTask.class),
   LOOK_AT_OWNER("look_at_owner", LookAtOwnerTask.class),
   LOOK_AT_BLOCK("look_at_block", LookAtBlockTask.class),
   LOOK_AT_TARGET("look_at_target", LookAtTargetTask.class),
   LOOK_AT("look_at", FishingHandler44.class);

   private final String id;
   private final Class<? extends InactiveTask> taskClass;

   public static @Nullable Class<? extends InactiveTask> fromId(String var0) {
      for (FishingType var4 : values()) {
         if (var4.id.equals(var0)) {
            return var4.taskClass;
         }
      }

      return null;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public Class<? extends InactiveTask> getTaskClass() {
      return this.taskClass;
   }

   @Generated
   FishingType(String var3, Class<? extends InactiveTask> var4) {
      this.id = var3;
      this.taskClass = var4;
   }
}
