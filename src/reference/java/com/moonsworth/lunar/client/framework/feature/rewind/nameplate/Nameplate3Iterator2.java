package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers_4;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.Range;

@Annotation7
public class Nameplate3Iterator2 extends Nameplate3Iterator {
   private UUID uuid;

   public Nameplate3Iterator2(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1, List<String> var2, String var3, Rewindhandlers_4 var4
   ) {
      super(var1, var2, var3, var4);
   }

   @Override
   public Fishing2Iterator method3(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1, Range<Integer> var2, Range<Integer> var3
   ) {
      Nameplate3Iterator2 var4 = new Nameplate3Iterator2(var1, this.method10(), this.type(), this.method4());
      return this.method8(var4, var1, var2, var3);
   }

   @Override
   public void setType(String var1) {
      super.setType(var1);
      this.uuid = UUID.fromString(this.type().split("#")[1]);
   }

   @Override
   public String method9() {
      try {
         String var1 = AdventureTextBridge.stripColor(((Bridge6_10)ThreadModuleDump63.method8().bridge$getPlayerByUniqueId(this.uuid).orElseThrow()).bridge$getName());
         if (!var1.isBlank()) {
            return var1;
         }
      } catch (Exception var2) {
         Slayer.method4("Rewind", "Failed to get player name for UUID: %s %s", new Object[]{this.uuid, var2.getMessage()});
      }

      return "Player (" + this.uuid + ")";
   }

   @Override
   protected String getEntityId() {
      return this.uuid.toString();
   }
}
