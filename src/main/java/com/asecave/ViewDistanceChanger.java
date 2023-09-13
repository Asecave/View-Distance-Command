package com.asecave;

import net.minecraft.server.MinecraftServer;

public class ViewDistanceChanger {

	private static ViewDistanceChanger instance;

	private MinecraftServer server;

	public static ViewDistanceChanger getInstance() {
		if (instance == null) {
			instance = new ViewDistanceChanger();
		}
		return instance;
	}

	public void init(MinecraftServer server) {
		this.server = server;
	}

	public void setViewDistance(int distance) {
		server.getPlayerManager().setViewDistance(distance);
	}
	
	public void setSimulationDistance(int distance) {
		server.getWorlds().forEach(level -> level.getChunkManager().applySimulationDistance(distance));
		
	}
	
	public void setBothDistances(int distance) {
		setViewDistance(distance);
		setSimulationDistance(distance);
	}
}
