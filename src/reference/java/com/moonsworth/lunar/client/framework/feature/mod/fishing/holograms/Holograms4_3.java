package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.ichor.util.Annotation2;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Annotation2
public class Holograms4_3 {
   private final int dataVersion = 2;
   private final Holograms6_2 requirements;
   private final String rawData;
   private final Holograms11_2 meta;
   private final List<Holograms10_2> sectionMetas;

   public Holograms4_3(Holograms2 var1) {
      this.requirements = var1.method17();
      this.meta = var1.method19();
      this.sectionMetas = var1.method20();
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      DataOutputStream var3 = new DataOutputStream(var2);
      var1.method9(var3);
      this.rawData = Base64.getEncoder().encodeToString(var2.toByteArray());
   }

   public Holograms2 toRoute(String var1) {
      if (this.dataVersion != 1 && this.dataVersion != 2) {
         throw new IOException("Unknown route data version!");
      }

      byte[] var2 = Base64.getDecoder().decode(this.rawData);
      DataInputStream var3 = new DataInputStream(new ByteArrayInputStream(var2));
      Holograms2 var4 = Holograms2.method10(var3);
      var4.method18(this.requirements);
      var4.setName(var1);
      var4.method13(this.meta);
      var4.method14(this.sectionMetas);
      return var4;
   }
}
