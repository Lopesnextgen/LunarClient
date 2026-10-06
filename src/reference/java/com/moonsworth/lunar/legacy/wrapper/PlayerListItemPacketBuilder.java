package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.SPacketPlayerListItem.Action;
import net.minecraft.network.play.server.SPacketPlayerListItem.AddPlayerData;
import net.minecraft.world.WorldSettings.GameType;

public class PlayerListItemPacketBuilder extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper29 {
   public PlayerListItemPacketBuilder(List<MixinHelper_19> var1) {
      super(S38PacketPlayerListItem.class, var1);
   }

   @Override
   public List<Bridge3_21> method1(Itemcounter6 var1, ClientPacketListenerBridge var2) {
      WorldClient var3 = (WorldClient)var1;
      NetHandlerPlayClient var4 = (NetHandlerPlayClient)var2;
      if (ThreadModuleDump63.MC_VERSION > 0) {
         ArrayList var11 = new ArrayList();
         S38PacketPlayerListItem var12 = new S38PacketPlayerListItem(Action.ADD_PLAYER, new EntityPlayerMP[0]);
         Class<AddPlayerData> var14 = AddPlayerData.class;
         Constructor var16 = var14.getConstructors()[0];

         for (NetworkPlayerInfo var10 : var4.playerInfoMap.values()) {
            var11.add(var10.getGameProfile().getId());
            var12.getEntries()
               .add(
                  (AddPlayerData)var16.newInstance(
                     var12,
                     var10.getGameProfile(),
                     var10.responseTime,
                     ThreadModuleDump63.MC_VERSION == 1 ? var10.getGameType() : var10.getGameType(),
                     var10.getDisplayName()
                  )
               );
         }

         for (EntityPlayer var19 : var3.playerEntities) {
            if (!var11.contains(var19.getGameProfile().getId())) {
               var12.getEntries()
                  .add(
                     (AddPlayerData)var16.newInstance(
                        var12,
                        var19.getGameProfile(),
                        Integer.MIN_VALUE,
                        ThreadModuleDump63.MC_VERSION == 1 ? GameType.NOT_SET : net.minecraft.world.GameType.NOT_SET,
                        var19.getDisplayName()
                     )
                  );
            }
         }

         return List.of((Bridge3_21)var12);
      } else {
         ArrayList var5 = new ArrayList();
         ArrayList var6 = new ArrayList();

         for (GuiPlayerInfo var8 : var4.playerInfoList$v1_7) {
            var5.add(var8.name);
            var6.add((Bridge3_21)(new S38PacketPlayerListItem(var8.name, true, var8.responseTime)));
         }

         for (EntityPlayer var15 : var3.playerEntities$v1_7) {
            String var9 = var15.getCommandSenderName$v1_7();
            if (!var5.contains(var9)) {
               var6.add((Bridge3_21)(new S38PacketPlayerListItem(var9, true, Integer.MIN_VALUE)));
            }
         }

         return var6;
      }
   }
}
