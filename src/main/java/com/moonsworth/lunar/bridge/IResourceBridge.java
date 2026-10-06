package com.moonsworth.lunar.bridge;

import java.io.InputStream;

public interface IResourceBridge {
   InputStream bridge$getInputStream();

   boolean bridge$hasMetadata();

   <T extends Bridge3_14> T bridge$getMetadata(String var1);

   String bridge$getResourcePackName();
}
