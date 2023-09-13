package com.asecave;

import net.minecraft.server.MinecraftServer;

public class EventHandler {

    public static void onServerStarted(final MinecraftServer server)
    {
        ViewDistanceChanger.getInstance().init(server);
    }
}
