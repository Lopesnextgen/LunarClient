package com.moonsworth.lunar.bridge;

import java.nio.ByteBuffer;

public interface MeshDataBridge {
   ByteBuffer bridge$vertexBuffer();

   ByteBuffer bridge$indexBuffer();

   void bridge$sortQuads(Bridge4Extension bridge4extension1, VertexSortingMode bridgetype2);

   void bridge$close();
}
