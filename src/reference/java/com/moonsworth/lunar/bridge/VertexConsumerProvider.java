package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 6, max = 38)
public interface VertexConsumerProvider {
   VertexConsumerBridge bridge$first();
}
