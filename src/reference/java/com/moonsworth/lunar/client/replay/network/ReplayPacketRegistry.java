package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.CursorPositionPacket;
import com.moonsworth.lunar.client.replay.network.HeldItemPacket;
import com.moonsworth.lunar.client.replay.network.MovementInputPacket;
import com.moonsworth.lunar.client.replay.network.LookPacket;
import com.moonsworth.lunar.client.replay.network.DropItemPacket;
import com.moonsworth.lunar.client.replay.network.StartUsingItemPacket;
import com.moonsworth.lunar.client.replay.network.PlayerAnimationPacket;
import com.moonsworth.lunar.client.replay.network.RelativePositionPacket;
import com.moonsworth.lunar.client.replay.network.PositionPacket;
import com.moonsworth.lunar.client.replay.network.SwingHandPacket;
import com.moonsworth.lunar.client.replay.network.ItemSwapPacket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplayPacketRegistry {
   private static final Map<Integer, List<Class<? extends ReplayPacket>>> field1 = new HashMap<>();

   public ReplayPacketRegistry() {
   }

   public static int method1(int index0, Class<? extends ReplayPacket> clazz1) {
      return field1.get(index0).indexOf(clazz1);
   }

   public static ReplayPacket method2(int index0, int index1) {
      try {
         return field1.get(index0).get(index1).newInstance();
      } catch (InstantiationException | IllegalAccessException instantiationexception3) {
         instantiationexception3.printStackTrace();
         return null;
      }
   }

   static {
      field1.put(
         0,
         List.of(
            com.moonsworth.lunar.client.replay.network.TickMarkerPacket.class,
            com.moonsworth.lunar.client.replay.network.RawNetworkPacket.class,
            PositionPacket.class,
            RelativePositionPacket.class,
            LookPacket.class,
            com.moonsworth.lunar.client.replay.network.SnapshotStartPacket.class,
            com.moonsworth.lunar.client.replay.network.SnapshotEndPacket.class,
            HeldItemPacket.class,
            SwingHandPacket.class,
            com.moonsworth.lunar.client.replay.network.SetSprintingPacket.class,
            com.moonsworth.lunar.client.replay.network.OpenInventoryPacket.class,
            com.moonsworth.lunar.client.replay.network.OpenChatPacket.class,
            com.moonsworth.lunar.client.replay.network.CloseScreenPacket.class,
            MovementInputPacket.class,
            com.moonsworth.lunar.client.replay.network.ScreenAlignmentPacket.class,
            CursorPositionPacket.class,
            com.moonsworth.lunar.client.replay.network.MouseInputPacket.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl.class,
            PlayerAnimationPacket.class,
            com.moonsworth.lunar.client.replay.network.UseItemOnBlockPacket.class,
            com.moonsworth.lunar.client.replay.network.UseItemPacket.class,
            com.moonsworth.lunar.client.replay.network.SetPausedPacket.class,
            StartUsingItemPacket.class,
            com.moonsworth.lunar.client.replay.network.StopUsingItemPacket.class,
            com.moonsworth.lunar.client.replay.network.ResetLevelPacket.class,
            com.moonsworth.lunar.client.replay.network.ServerPackPacket.class,
            ProtobufMessagePacket.class,
            KeybindPacket.class,
            SettingValuePacket.class,
            ModuleConfigPacket.class,
            ButtonStatePacket.class,
            HudPositionPacket.class,
            DisconnectPacket.class,
            MouseWheelPacket.class,
            com.moonsworth.lunar.client.replay.network.PickBlockPacket.class,
            DropItemPacket.class,
            com.moonsworth.lunar.client.replay.network.MessageSignaturePacket.class,
            WorldBorderPacket.class,
            ServerHologramPacket.class,
            WaypointPacket.class,
            BeamPacket.class,
            com.moonsworth.lunar.client.replay.network.ClientPackPacket.class,
            ServerSettingOverridePacket.class,
            com.moonsworth.lunar.client.replay.network.AttackStrengthPacket.class,
            com.moonsworth.lunar.client.replay.network.LocationPacket.class,
            com.moonsworth.lunar.client.replay.network.SnapshotLoadPacket.class,
            com.moonsworth.lunar.client.replay.network.AttackPacket.class,
            com.moonsworth.lunar.client.replay.network.HypixelLocationPacket.class,
            ClickHandlerPacket.class,
            ItemSwapPacket.class
         )
      );
   }
}
