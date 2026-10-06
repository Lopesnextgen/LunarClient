package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.ichor.util.KeepName;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@KeepName
public class RouteDataCodec {
   private final int dataVersion = 2;
   private final RouteConditions requirements;
   private final String rawData;
   private final RouteCompletion meta;
   private final List<RouteSegment> sectionMetas;

   public RouteDataCodec(DungeonRoute holograms21) {
      this.requirements = holograms21.method17();
      this.meta = holograms21.method19();
      this.sectionMetas = holograms21.method20();
      ByteArrayOutputStream bytearrayoutputstream2 = new ByteArrayOutputStream();
      DataOutputStream output3 = new DataOutputStream(bytearrayoutputstream2);
      holograms21.method9(output3);
      this.rawData = Base64.getEncoder().encodeToString(bytearrayoutputstream2.toByteArray());
   }

   public DungeonRoute toRoute(String text1) {
      if (this.dataVersion != 1 && this.dataVersion != 2) {
         throw new IOException("Unknown route data version!");
      }

      byte[] items2 = Base64.getDecoder().decode(this.rawData);
      DataInputStream input3 = new DataInputStream(new ByteArrayInputStream(items2));
      DungeonRoute holograms24 = DungeonRoute.method10(input3);
      holograms24.method18(this.requirements);
      holograms24.setName(text1);
      holograms24.method13(this.meta);
      holograms24.method14(this.sectionMetas);
      return holograms24;
   }
}
