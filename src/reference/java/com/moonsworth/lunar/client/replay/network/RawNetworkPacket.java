package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3Extension_4;
import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.Bridge7_9;
import com.moonsworth.lunar.bridge.ConnectionProtocol;
import com.moonsworth.lunar.bridge.PacketDirectionBridge;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import io.netty.buffer.Unpooled;
import lombok.Generated;

public class RawNetworkPacket extends ReplayPacket {
   private int id;
   private byte[] data;
   private PacketDirectionBridge field1;
   private ConnectionProtocol field2;
   private PacketBridge field3;

   public RawNetworkPacket(int value, byte[] items2, PacketDirectionBridge bridgetype_43, ConnectionProtocol bridgetype2_24) {
      this.id = value;
      this.data = items2;
      this.field1 = bridgetype_43;
      this.field2 = bridgetype2_24;
   }

   public RawNetworkPacket(PacketBridge bridge3_211) {
      this.field3 = bridge3_211;
   }

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.id = bytebufloader1.readVarInt();
      this.data = bytebufloader1.readByteArray();
      this.field1 = bytebufloader1.method9(PacketDirectionBridge.class);
      this.field2 = bytebufloader1.method9(ConnectionProtocol.class);
      this.field3 = this.method3();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.id);
      bytebufloader1.method2(this.data);
      bytebufloader1.method10(this.field1);
      bytebufloader1.method10(this.field2);
   }

   private PacketBridge method3() {
      if (this.field3 != null) {
         return this.field3;
      }

      Bridge7_9 bridge7_91 = Bridge.method8().method23(Unpooled.wrappedBuffer(this.data));
      PacketBridge bridge3_212 = Bridge.method59().method1(this.field1, this.field2, this.id, bridge7_91);
      bridge7_91.bridge$release();
      return bridge3_212;
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      PacketBridge bridge3_212 = this.method3();
      if (!(bridge3_212 instanceof Bridge3Extension_4)) {
         Bridge.method59().method35(arg1x -> nameplate41.method10().method1(arg1x), bridge3_212);
         bridge3_212.bridge$handle(nameplate41.method17());
      }
   }

   @Override
   public ReplayPacket method4(ReplayContext nameplate41) {
      PacketBridge bridge3_212 = Bridge.method59().method36(() -> nameplate41.method10().method2(), this.method3());
      return bridge3_212 == null ? null : new RawNetworkPacket(bridge3_212);
   }

   @Override
   public String name() {
      if (this.field2 == ConnectionProtocol.CONFIGURATION) {
         return "Configuration Packet";
      }

      PacketBridge bridge3_211;
      try {
         bridge3_211 = this.method3();
      } catch (Exception exception3) {
         return super.name();
      }

      String text2 = bridge3_211.getClass().getSimpleName();
      if (bridge3_211.getClass().getEnclosingClass() != null) {
         text2 = bridge3_211.getClass().getEnclosingClass().getSimpleName() + "." + text2;
      }

      return text2;
   }

   @Override
   public String data() {
      if (this.field2 == ConnectionProtocol.CONFIGURATION) {
         return "";
      }

      PacketBridge bridge3_211;
      try {
         bridge3_211 = this.method3();
      } catch (Exception exception3) {
         return exception3.getMessage();
      }

      return this.method5(bridge3_211);
   }

   @Generated
   public RawNetworkPacket() {
   }

   @Generated
   public int getId() {
      return this.id;
   }

   @Generated
   public PacketDirectionBridge method6() {
      return this.field1;
   }
}
