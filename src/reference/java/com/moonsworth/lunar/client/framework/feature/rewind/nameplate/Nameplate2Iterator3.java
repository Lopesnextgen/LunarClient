package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Itemcounter4Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;

public class Nameplate2Iterator3 extends Nameplate2 {
   private Map<String, Itemcounter4Extension> field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = new HashMap<>();
      int var2 = var1.readVarInt();

      for (int var3 = 0; var3 < var2; var3++) {
         String var4 = var1.readString();
         double var5 = var1.readDouble();
         double var7 = var1.readDouble();
         double var9 = var1.readDouble();
         double var11 = var1.readDouble();
         double var13 = var1.readDouble();
         double var15 = var1.readDouble();
         int var17 = var1.readVarInt();
         boolean var18 = var1.readBoolean();
         boolean var19 = var1.readBoolean();
         String var20 = var1.readString();
         Itemcounter4Extension var21 = Bridge.method8().method46(var20, var17);
         var21.method1(var5, var9, var7, var11, 0);
         var21.method9(var13, var15);
         var21.setCancelEntry(var18);
         var21.setCancelExit(var19);
         this.field1.put(var4, var21);
      }
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.field1.size());

      for (Entry var3 : this.field1.entrySet()) {
         var1.method1((String)var3.getKey());
         Itemcounter4Extension var4 = (Itemcounter4Extension)var3.getValue();
         var1.writeDouble(var4.OOICIIIHOCOHHHHIHRRHOIOHIRCOII());
         var1.writeDouble(var4.IOCCOCICCHCRHCCIHHCRRIRHORCCCR());
         var1.writeDouble(var4.RHHIOOHRICOHORORROIIRHHICOORIO());
         var1.writeDouble(var4.RICHICOHRRICRIOOCRCHHRRRIRIIIO());
         var1.writeDouble(var4.method19());
         var1.writeDouble(var4.method20());
         var1.method11(var4.getColor());
         var1.writeBoolean(var4.isCancelEntry());
         var1.writeBoolean(var4.isCancelExit());
         var1.method1(var4.getWorld());
      }
   }

   @Override
   public void method3(Nameplate4 var1) {
      ThreadModuleDump63.method4().method63().method2().clear();
      ThreadModuleDump63.method4().method63().method2().putAll(this.field1);
   }

   @Generated
   public Nameplate2Iterator3(Map<String, Itemcounter4Extension> var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2Iterator3() {
   }

   @Generated
   public Map<String, Itemcounter4Extension> method4() {
      return this.field1;
   }
}
