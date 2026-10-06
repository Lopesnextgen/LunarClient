package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.PacketBuilder;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.world.World;

public class JoinGamePacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.WorldJoinPacketBuilder {
   public JoinGamePacketBuilder(List<PacketBuilder> list1) {
      super(S01PacketJoinGame.class, list1);
   }

   @Override
   public PacketBridge method1(Itemcounter6 itemcounter61, Bridge5Extension_5 bridge5extension_52) {
      World world3 = (World)itemcounter61;
      EntityPlayerSP player4 = (EntityPlayerSP)bridge5extension_52;
      PlayerControllerMP playercontrollermp5 = Minecraft.getMinecraft().playerController;
      if (Ref.MC_VERSION <= 0) {
         return (PacketBridge)(
            new S01PacketJoinGame(
               bridge5extension_52.bridge$getEntityId(),
               playercontrollermp5.currentGameType,
               world3.worldInfo.isHardcoreModeEnabled(),
               itemcounter61.bridge$getDimensionId(),
               world3.difficultySetting$v1_7,
               0,
               world3.worldInfo.getTerrainType()
            )
         );
      } else {
         return Ref.MC_VERSION <= 1
            ? (PacketBridge)(
               new S01PacketJoinGame(
                  bridge5extension_52.bridge$getEntityId(),
                  playercontrollermp5.currentGameType,
                  world3.worldInfo.isHardcoreModeEnabled(),
                  itemcounter61.bridge$getDimensionId(),
                  world3.worldInfo.getDifficulty(),
                  0,
                  world3.worldInfo.getTerrainType(),
                  player4.hasReducedDebug()
               )
            )
            : (PacketBridge)(
               new S01PacketJoinGame(
                  bridge5extension_52.bridge$getEntityId(),
                  playercontrollermp5.currentGameType$v1_12,
                  world3.worldInfo.isHardcoreModeEnabled(),
                  itemcounter61.bridge$getDimensionId(),
                  world3.worldInfo.getDifficulty(),
                  0,
                  world3.worldInfo.getTerrainType(),
                  player4.hasReducedDebug()
               )
            );
      }
   }
}
