package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.replay.gui.EntityOptionOverrides;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class PlayerOverrideGroup extends EntityOverrideGroup {
   private UUID uuid;

   public PlayerOverrideGroup(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21, List<String> list2, String text3, EntityOptionOverrides rewindhandlers_44
   ) {
      super(nameplate21, list2, text3, rewindhandlers_44);
   }

   @Override
   public PropertyGroup method3(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3
   ) {
      PlayerOverrideGroup nameplate3iterator24 = new PlayerOverrideGroup(nameplate21, this.method10(), this.type(), this.method4());
      return this.method6(nameplate3iterator24, nameplate21, range2, range3);
   }

   @Override
   public void setType(String text1) {
      super.setType(text1);
      this.uuid = UUID.fromString(this.type().split("#")[1]);
   }

   @Override
   public String method9() {
      try {
         String text1 = TextBridge.stripColor(((Bridge6_10)Ref.method8().bridge$getPlayerByUniqueId(this.uuid).orElseThrow()).bridge$getName());
         if (!text1.isBlank()) {
            return text1;
         }
      } catch (Exception exception2) {
         LunarLogger.method4("Rewind", "Failed to get player name for UUID: %s %s", new Object[]{this.uuid, exception2.getMessage()});
      }

      return "Player (" + this.uuid + ")";
   }

   @Override
   protected String getEntityId() {
      return this.uuid.toString();
   }
}
