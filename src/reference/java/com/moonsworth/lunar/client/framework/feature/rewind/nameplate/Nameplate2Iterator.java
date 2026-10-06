package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.module.beam.Beam;
import com.lunarclient.apollo.module.beam.BeamModule;
import com.moonsworth.lunar.client.Highlight3Iterator6;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.concurrent.ConsumerExtension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;

public class Nameplate2Iterator extends Nameplate2 {
   private Map<String, Beam> field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = new HashMap<>();
      int var2 = var1.readVarInt();

      for (int var3 = 0; var3 < var2; var3++) {
         String var4 = var1.readString();
         Beam var5 = Beam.builder()
            .id(var1.readString())
            .color(new Color(var1.readVarInt()))
            .location(ApolloBlockLocation.builder().x(var1.readVarInt()).y(var1.readVarInt()).z(var1.readVarInt()).build())
            .build();
         this.field1.put(var4, var5);
      }
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.field1.size());

      for (Entry var3 : this.field1.entrySet()) {
         var1.method1((String)var3.getKey());
         Beam var4 = (Beam)var3.getValue();
         var1.method1(var4.getId());
         var1.method11(var4.getColor().getRGB());
         var1.method11(var4.getLocation().getX());
         var1.method11(var4.getLocation().getY());
         var1.method11(var4.getLocation().getZ());
      }
   }

   @Override
   public void method3(Nameplate4 var1) {
      ThreadModuleDump63.method4().method84().method3(BeamModule.class).ifPresent((ConsumerExtension)var1x -> {
         Highlight3Iterator6 var2 = (Highlight3Iterator6)var1x;
         var2.method4().clear();
         var2.method4().putAll(this.field1);
      });
   }

   @Generated
   public Nameplate2Iterator(Map<String, Beam> var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2Iterator() {
   }

   @Generated
   public Map<String, Beam> method4() {
      return this.field1;
   }
}
