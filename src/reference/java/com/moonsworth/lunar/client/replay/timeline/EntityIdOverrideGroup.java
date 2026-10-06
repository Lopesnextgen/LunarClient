package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.replay.gui.EntityOptionOverrides;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.game.EntityLookup;
import java.util.List;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class EntityIdOverrideGroup extends EntityOverrideGroup {
   private int entityId;
   private String field16;

   public EntityIdOverrideGroup(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21, List<String> list2, String text3, EntityOptionOverrides rewindhandlers_44
   ) {
      super(nameplate21, list2, text3, rewindhandlers_44);
   }

   @Override
   public PropertyGroup method3(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3
   ) {
      EntityIdOverrideGroup nameplate3iterator34 = new EntityIdOverrideGroup(nameplate21, this.method10(), this.type(), this.method4());
      return this.method6(nameplate3iterator34, nameplate21, range2, range3);
   }

   @Override
   public void setType(String text1) {
      super.setType(text1);
      String[] items2 = text1.split("#");
      this.entityId = Integer.parseInt(items2[1]);
      this.field16 = items2[1];
      if (this.field16.length() > 3) {
         this.field16 = this.field16.substring(this.field16.length() - 3);
      }
   }

   @Override
   public String method9() {
      try {
         String text1 = EntityLookup.method2(this.entityId);
         if (text1 != null) {
            return text1 + " (" + this.field16 + ")";
         }
      } catch (Exception exception2) {
         LunarLogger.method4("Rewind", "Failed to get entity name for ID: %d %s", new Object[]{this.entityId, exception2.getMessage()});
      }

      return "Entity (" + this.field16 + ")";
   }

   @Override
   protected String getEntityId() {
      return Integer.toString(this.entityId);
   }
}
