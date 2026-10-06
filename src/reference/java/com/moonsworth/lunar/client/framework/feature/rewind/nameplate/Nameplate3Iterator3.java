package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers_4;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump73;
import java.util.List;
import org.apache.commons.lang3.Range;

@Annotation7
public class Nameplate3Iterator3 extends Nameplate3Iterator {
   private int entityId;
   private String field16;

   public Nameplate3Iterator3(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1, List<String> var2, String var3, Rewindhandlers_4 var4
   ) {
      super(var1, var2, var3, var4);
   }

   @Override
   public Fishing2Iterator method3(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1, Range<Integer> var2, Range<Integer> var3
   ) {
      Nameplate3Iterator3 var4 = new Nameplate3Iterator3(var1, this.method10(), this.type(), this.method4());
      return this.method8(var4, var1, var2, var3);
   }

   @Override
   public void setType(String var1) {
      super.setType(var1);
      String[] var2 = var1.split("#");
      this.entityId = Integer.parseInt(var2[1]);
      this.field16 = var2[1];
      if (this.field16.length() > 3) {
         this.field16 = this.field16.substring(this.field16.length() - 3);
      }
   }

   @Override
   public String method9() {
      try {
         String var1 = ThreadModuleDump73.getEntityTypeName(this.entityId);
         if (var1 != null) {
            return var1 + " (" + this.field16 + ")";
         }
      } catch (Exception var2) {
         Slayer.method4("Rewind", "Failed to get entity name for ID: %d %s", new Object[]{this.entityId, var2.getMessage()});
      }

      return "Entity (" + this.field16 + ")";
   }

   @Override
   protected String getEntityId() {
      return Integer.toString(this.entityId);
   }
}
