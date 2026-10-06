package com.moonsworth.lunar.bridge;

public enum VertexElementType {
   BYTE,
   UNSIGNED_BYTE,
   SHORT,
   UNSIGNED_SHORT,
   INT,
   UNSIGNED_INT,
   FLOAT,
   TWO_BYTES,
   THREE_BYTES,
   FOUR_BYTES,
   DOUBLE;

   VertexElementType() {
   }

   public int id() {
      return 5120 + this.ordinal();
   }
}
