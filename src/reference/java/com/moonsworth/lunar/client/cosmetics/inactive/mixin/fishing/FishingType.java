package com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing;

import lombok.Generated;
import org.jspecify.annotations.Nullable;

public enum FishingType {
   MOVE_TO_OWNER("move_to_owner", MoveToOwnerTask.class),
   TELEPORT_TO_OWNER("teleport_to_owner", TeleportToOwnerTask.class),
   RANDOM_WANDER("random_wander", FishingHandler.class),
   ATTACH_TO_OWNER("attach_to_owner", AttachToOwnerTask.class),
   LOOK_AT_OWNER("look_at_owner", LookAtOwnerTask.class),
   LOOK_AT_BLOCK("look_at_block", LookAtBlockTask.class),
   LOOK_AT_TARGET("look_at_target", LookAtTargetTask.class),
   LOOK_AT("look_at", LookAtTask.class);

   private final String id;
   private final Class<? extends InactiveTask> taskClass;

   public static @Nullable Class<? extends InactiveTask> fromId(String text0) {
      for (FishingType fishingtype4 : values()) {
         if (fishingtype4.id.equals(text0)) {
            return fishingtype4.taskClass;
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
   FishingType(String text, Class<? extends InactiveTask> clazz4) {
      this.id = text;
      this.taskClass = clazz4;
   }
}
