package com.asecave;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViewDistanceCommand implements ModInitializer {
	
    public static final Logger LOGGER = LoggerFactory.getLogger("view-distance-command");

	@Override
	public void onInitialize() {
		
		LOGGER.info("View Distance Command initialized.");
	}
}