package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.mod.render.serverholograms.Serverholograms;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class Nameplate2Iterator6 extends Nameplate2 {
   private Map<String, Serverholograms> field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = new HashMap<>();
      int var2 = var1.readVarInt();

      for (int var3 = 0; var3 < var2; var3++) {
         String var4 = var1.readString();
         String var5 = var1.readString();
         double var6 = var1.readDouble();
         double var8 = var1.readDouble();
         double var10 = var1.readDouble();
         boolean var12 = var1.readBoolean();
         boolean var13 = var1.readBoolean();
         boolean var14 = var1.readBoolean();
         boolean var15 = var1.readBoolean();
         int var16 = var1.readVarInt();
         Component[] var17 = new Component[var16];

         for (int var18 = 0; var18 < var16; var18++) {
            var17[var18] = PlainTextComponentSerializer.plainText().deserialize(var1.readString());
         }

         this.field1.put(var4, new Serverholograms(var5, var17, var6, var8, var10, var12, var13, var14, var15));
      }
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.field1.size());

      for (Entry var3 : this.field1.entrySet()) {
         var1.method1((String)var3.getKey());
         Serverholograms var4 = (Serverholograms)var3.getValue();
         var1.method1(var4.getId());
         var1.writeDouble(var4.getX());
         var1.writeDouble(var4.getY());
         var1.writeDouble(var4.getZ());
         var1.writeBoolean(var4.isShowThroughWalls());
         var1.writeBoolean(var4.method2());
         var1.writeBoolean(var4.isBackground());
         var1.writeBoolean(var4.method3());
         var1.method11(var4.method1().length);

         for (Component var8 : var4.method1()) {
            var1.method1(PlainTextComponentSerializer.plainText().serialize(var8));
         }
      }
   }

   @Override
   public void method3(Nameplate4 var1) {
      ThreadModuleDump63.method4().method57().method3().clear();
      ThreadModuleDump63.method4().method57().method3().putAll(this.field1);
   }

   @Generated
   public Nameplate2Iterator6(Map<String, Serverholograms> var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2Iterator6() {
   }

   @Generated
   public Map<String, Serverholograms> method4() {
      return this.field1;
   }
}
