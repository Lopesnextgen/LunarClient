package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.client.cosmetics.SprayPlacementTracker;
import com.moonsworth.lunar.client.cosmetics.SprayEntry;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;
import org.joml.Vector3f;

public class Nameplate2Iterator4 extends Nameplate2 {
   private Map<UUID, LinkedList<SprayPlacementTracker>> field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = new HashMap<>();
      int var2 = var1.readVarInt();

      for (int var3 = 0; var3 < var2; var3++) {
         UUID var4 = UUID.fromString(var1.readString());
         LinkedList var5 = new LinkedList();
         int var6 = var1.readVarInt();

         for (int var7 = 0; var7 < var6; var7++) {
            HorsestatsType_2 var8 = var1.method9(HorsestatsType_2.class);
            float var9 = var1.readFloat();
            float var10 = var1.readFloat();
            float var11 = var1.readFloat();
            float var12 = var1.readFloat();
            SprayEntry var13 = new SprayEntry(
               var1.readVarInt(),
               var1.readString(),
               ResourceLocationBridge.create(var1.readString()),
               var1.readInt(),
               var1.readFloat(),
               var1.readFloat(),
               var1.readFloat(),
               var1.readFloat(),
               var1.readBoolean(),
               var1.readBoolean(),
               var1.readBoolean(),
               var1.readInt()
            );
            SprayPlacementTracker var14 = ThreadModuleDump63.method4().method46().method15(var13, new Vector3f(var10, var11, var12), var8, var9, false);
            var5.add(var14);
         }

         this.field1.put(var4, var5);
      }
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.field1.size());

      for (Entry var3 : this.field1.entrySet()) {
         var1.method1(((UUID)var3.getKey()).toString());
         List var4 = (List)var3.getValue();
         var1.method11(var4.size());

         for (SprayPlacementTracker var6 : var4) {
            var1.method10(var6.method3());
            var1.writeFloat(var6.getRotation());
            var1.writeFloat(var6.method2().x);
            var1.writeFloat(var6.method2().y);
            var1.writeFloat(var6.method2().z);
            SprayEntry var7 = var6.method1();
            var1.method11(var7.getId());
            var1.method1(var7.getName());
            var1.method1(var7.method4().toString());
            var1.writeInt(var7.method5());
            var1.writeFloat(var7.getWidth());
            var1.writeFloat(var7.getHeight());
            var1.writeFloat(var7.method6());
            var1.writeFloat(var7.method7());
            var1.writeBoolean(var7.method8());
            var1.writeBoolean(var7.method9());
            var1.writeBoolean(var7.method10());
            var1.writeInt(var7.getDuration());
         }
      }
   }

   @Override
   public void method3(Nameplate4 var1) {
      ThreadModuleDump63.method4().method46().method43().clear();

      for (Entry var3 : this.field1.entrySet()) {
         ThreadModuleDump63.method4().method46().method43().put((UUID)var3.getKey(), (LinkedList)var3.getValue());
      }
   }

   @Generated
   public Nameplate2Iterator4(Map<UUID, LinkedList<SprayPlacementTracker>> var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2Iterator4() {
   }

   @Generated
   public Map<UUID, LinkedList<SprayPlacementTracker>> method4() {
      return this.field1;
   }
}
