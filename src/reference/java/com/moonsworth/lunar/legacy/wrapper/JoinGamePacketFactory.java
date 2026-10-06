package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.world.World;

public class JoinGamePacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.WorldJoinPacketFactory {
   public JoinGamePacketFactory(List<MixinHelper_19> var1) {
      super(S01PacketJoinGame.class, var1);
   }

   public Bridge3_21 method1(Itemcounter6 var1, Bridge5Extension_5 var2) {
      World var3 = (World)var1;
      EntityPlayerSP var4 = (EntityPlayerSP)var2;
      PlayerControllerMP var5 = Minecraft.getMinecraft().playerController;
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         return (Bridge3_21)(
            new S01PacketJoinGame(
               var2.bridge$getEntityId(),
               var5.currentGameType,
               var3.worldInfo.isHardcoreModeEnabled(),
               var1.bridge$getDimensionId(),
               var3.difficultySetting$v1_7,
               0,
               var3.worldInfo.getTerrainType()
            )
         );
      } else {
         return ThreadModuleDump63.MC_VERSION <= 1
            ? (Bridge3_21)(
               new S01PacketJoinGame(
                  var2.bridge$getEntityId(),
                  var5.currentGameType,
                  var3.worldInfo.isHardcoreModeEnabled(),
                  var1.bridge$getDimensionId(),
                  var3.worldInfo.getDifficulty(),
                  0,
                  var3.worldInfo.getTerrainType(),
                  var4.hasReducedDebug()
               )
            )
            : (Bridge3_21)(
               new S01PacketJoinGame(
                  var2.bridge$getEntityId(),
                  var5.currentGameType$v1_12,
                  var3.worldInfo.isHardcoreModeEnabled(),
                  var1.bridge$getDimensionId(),
                  var3.worldInfo.getDifficulty(),
                  0,
                  var3.worldInfo.getTerrainType(),
                  var4.hasReducedDebug()
               )
            );
      }
   }
}
