package com.moonsworth.webosr.config;

import com.moonsworth.webosr.wrappers.Browser;
import com.moonsworth.webosr.wrappers.Session;

public final class BrowserConfig {
   public int width = 800;
   public int height = 600;
   public double initialScale = 1.0;
   public Session session = null;
   public Browser.Listener eventHandler = null;
   public boolean initialFocus = true;
   public boolean accelerated = false;
   public boolean transparent = false;
   public boolean images = true;
   public boolean javascript = true;
   public boolean compositing = false;
   public String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/615.1.18.100.1 (KHTML, like Gecko) Version/16.4.1 Safari/615.1.18.100.1";
   public String fontFamilyStandard = "Times New Roman";
   public String fontFamilyFixed = "Courier New";
   public String fontFamilySerif = "Times New Roman";
   public String fontFamilySansSerif = "Arial";
}
