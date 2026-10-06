package com.moonsworth.lunar.client.ui.external;

import com.moonsworth.lunar.client.ui.external.ExternalLink;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 26, max = 32)
public interface GuiExternalLink extends ExternalLink {
   int method1();
}
