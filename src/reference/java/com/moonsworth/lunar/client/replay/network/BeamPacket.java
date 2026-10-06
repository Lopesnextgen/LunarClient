package com.moonsworth.lunar.client.replay.network;

import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.module.beam.Beam;
import com.lunarclient.apollo.module.beam.BeamModule;
import com.moonsworth.lunar.client.network.apollo.BeamApolloHandler;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.util.concurrent.ConsumerExtension;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;

public class BeamPacket extends ReplayPacket {
   private Map<String, Beam> field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = new HashMap<>();
      int number2 = bytebufloader1.readVarInt();

      for (int index3 = 0; index3 < number2; index3++) {
         String text4 = bytebufloader1.readString();
         Beam beam5 = Beam.builder()
            .id(bytebufloader1.readString())
            .color(new Color(bytebufloader1.readVarInt()))
            .location(ApolloBlockLocation.builder().x(bytebufloader1.readVarInt()).y(bytebufloader1.readVarInt()).z(bytebufloader1.readVarInt()).build())
            .build();
         this.field1.put(text4, beam5);
      }
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.field1.size());

      for (Entry entry3 : this.field1.entrySet()) {
         bytebufloader1.method1((String)entry3.getKey());
         Beam beam4 = (Beam)entry3.getValue();
         bytebufloader1.method1(beam4.getId());
         bytebufloader1.method11(beam4.getColor().getRGB());
         bytebufloader1.method11(beam4.getLocation().getX());
         bytebufloader1.method11(beam4.getLocation().getY());
         bytebufloader1.method11(beam4.getLocation().getZ());
      }
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Ref.method4().method84().method3(BeamModule.class).ifPresent((ConsumerExtension)arg1x -> {
         BeamApolloHandler highlight3iterator62 = (BeamApolloHandler)arg1x;
         highlight3iterator62.method4().clear();
         highlight3iterator62.method4().putAll(this.field1);
      });
   }

   @Generated
   public BeamPacket(Map<String, Beam> map) {
      this.field1 = map;
   }

   @Generated
   public BeamPacket() {
   }

   @Generated
   public Map<String, Beam> method4() {
      return this.field1;
   }
}
