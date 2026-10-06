package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.holograms.Holograms2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers_4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import lombok.Generated;

@Annotation7
public abstract class Nameplate3Iterator extends Nameplate3 {
   private final Rewindhandlers_4 field15;

   public Nameplate3Iterator(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1, List<String> var2, String var3, Rewindhandlers_4 var4
   ) {
      super(var1, var2, var3, var1x -> {
         ArrayList var2x = new ArrayList();
         Holograms2 var3x = var1x.method40().method39();

         for (Entry var5 : var3x.method3().entrySet()) {
            if (((Set)var5.getValue()).contains(var3)) {
               var2x.add(new Nameplate_2((String)var5.getKey(), Client.method109().method67().method2("settings", (String)var5.getKey(), new Object[0])));
            }
         }

         return var2x;
      });
      this.field15 = var4;
      this.method6(Fishing2Iterator.Type.KEYFRAMES);
   }

   @Override
   public void method6(ThreadModuleDump6<Nameplate4> var1, int var2) {
      super.method6(var1, var2);
      if (this.isEnabled()) {
         for (Fishing2Loader var4 : this.method12().values()) {
            this.field15.add(this.getEntityId(), var4.type(), var4.method26());
         }
      }
   }

   @Override
   protected void method5(String var1) {
      Fishing2Loader var2 = (Fishing2Loader)this.method12().get(var1);
      if (var2 != null) {
         this.field15.remove(this.getEntityId(), var2.type(), var2.method26());
      }
   }

   @Override
   public void method7() {
      super.method7();

      for (Fishing2Loader var2 : this.method12().values()) {
         this.field15.remove(this.getEntityId(), var2.type(), var2.method26());
      }
   }

   protected abstract String getEntityId();

   @Generated
   public Rewindhandlers_4 method4() {
      return this.field15;
   }
}
