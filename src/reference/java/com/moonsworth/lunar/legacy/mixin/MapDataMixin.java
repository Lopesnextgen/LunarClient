package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter2_3;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Map;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.util.Vec4b;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MapData.class)
public class MapDataMixin implements Itemcounter2_3 {
   @Shadow
   public Map<String, Vec4b> mapDecorations;
   @Shadow
   public Map<String, MapDecoration> mapDecorations$v1_12;
   @Shadow
   public byte[] colors;
   @Shadow
   public byte scale;
   @Shadow
   public boolean trackingPosition$v1_12;

   @Override
   public Map<String, Itemcounter_2> bridge$getMapDecorations() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return this.mapDecorations$v1_12;
      } else {
         return ThreadModuleDump63.MC_VERSION >= 1 ? this.mapDecorations : Map.of();
      }
   }

   @Override
   public void bridge$setMapDecorations(Map<String, Itemcounter_2> var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.mapDecorations$v1_12 = var1;
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.mapDecorations = var1;
      }
   }

   @Override
   public byte[] bridge$getColors() {
      return this.colors;
   }

   @Override
   public Bridge3_21 bridge$getMapPacket(Object var1) {
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         return (Bridge3_21)(new S34PacketMaps((Integer)var1, this.colors));
      } else {
         return ThreadModuleDump63.MC_VERSION == 1
            ? (Bridge3_21)(new S34PacketMaps((Integer)var1, this.scale, this.mapDecorations.values(), this.colors, 0, 0, 128, 128))
            : (Bridge3_21)(
               new S34PacketMaps((Integer)var1, this.scale, this.trackingPosition$v1_12, this.mapDecorations$v1_12.values(), this.colors, 0, 0, 128, 128)
            );
      }
   }
}
