package com.moonsworth.lunar.genesis;

final class MixinHelper2_14 {
   static final int field1 = 2;
   static final int field2 = 10;
   static final int field3 = 20;
   static final float field4 = 1.0F;
   static final int field5 = 2;
   static final String field6 = "Node %s is not an element of this graph.";
   static final String field7 = "Edge %s is not an element of this graph.";
   static final String field8 = "Edge %s already exists between the following nodes: %s, so it cannot be reused to connect the following nodes: %s.";
   static final String field9 = "Cannot call edgeConnecting() when parallel edges exist between %s and %s. Consider calling edgesConnecting() instead.";
   static final String field10 = "Nodes %s and %s are already connected by a different edge. To construct a graph that allows parallel edges, call allowsParallelEdges(true) on the Builder.";
   static final String field11 = "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.";
   static final String field12 = "Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't.";
   static final String field13 = "Edge %s already exists in the graph.";
   static final String field14 = "Mismatch: unordered endpoints cannot be used with directed graphs";

   private MixinHelper2_14() {
   }
}
