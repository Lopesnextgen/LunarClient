package com.moonsworth.lunar.bridge;

import java.nio.ByteBuffer;

public interface Bridge2_44 {
   ByteBuffer bridge$vertexBuffer();

   ByteBuffer bridge$indexBuffer();

   void bridge$sortQuads(Bridge4Extension var1, BridgeType var2);

   void bridge$close();
}
