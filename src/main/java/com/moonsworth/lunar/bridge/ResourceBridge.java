package com.moonsworth.lunar.bridge;

import java.io.InputStream;

public interface ResourceBridge {
   InputStream bridge$getInputStream();

   boolean bridge$hasMetadata();

   <T extends IMetadataSectionBridge> T bridge$getMetadata(String text1);

   String bridge$getResourcePackName();
}
