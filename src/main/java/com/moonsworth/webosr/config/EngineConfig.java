package com.moonsworth.webosr.config;

import com.moonsworth.webosr.TextureHandler;
import com.moonsworth.webosr.handler.Clipboard;
import com.moonsworth.webosr.handler.FileSystem;

public abstract class EngineConfig {
   public FileSystem fileSystem;
   public Integer remoteDebugPort;
   public Clipboard clipboard;
   public TextureHandler textureHandler;
   public SystemConfig system;

   public EngineConfig() {
   }
}
