package com.asecave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ViewDistanceCommand implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("view-distance-command");

	@Override
	public void onInitialize() {
		
		ServerLifecycleEvents.SERVER_STARTED.register(EventHandler::onServerStarted);
		
		CommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("viewdistance")
						.requires(source -> source.hasPermissionLevel(4))
						.then(CommandManager.argument("view distance", IntegerArgumentType.integer())
								.executes(context -> {
									int distance = IntegerArgumentType.getInteger(context, "view distance");
									if (!checkDistance(context, distance)) {
										return 0;
									}
									ViewDistanceChanger.getInstance().setViewDistance(distance);
									context.getSource().sendMessage(Text.literal("§2[vdc] §fSet the view distance to §e" + distance + "§f."));
									LOGGER.info(context.getSource().getPlayer().getName().getString() + " set the view distance to " + distance + ".");
									return 1;
								}))));
		CommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("simdistance")
						.requires(source -> source.hasPermissionLevel(4))
						.then(CommandManager.argument("simulation distance", IntegerArgumentType.integer())
								.executes(context -> {
									int distance = IntegerArgumentType.getInteger(context, "simulation distance");
									if (!checkDistance(context, distance)) {
										return 0;
									}
									ViewDistanceChanger.getInstance().setSimulationDistance(distance);
									context.getSource().sendMessage(Text.literal("§2[vdc] §fSet the simulation distance to §e" + distance + "§f."));
									LOGGER.info(context.getSource().getPlayer().getName().getString() + " set the simulation distance to " + distance + ".");
									return 1;
								}))));
		CommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("vsdistance")
						.requires(source -> source.hasPermissionLevel(4))
						.then(CommandManager.argument("view and simulation distance", IntegerArgumentType.integer())
						.executes(context -> {
							int distance = IntegerArgumentType.getInteger(context, "view and simulation distance");
							if (!checkDistance(context, distance)) {
								return 0;
							}
							ViewDistanceChanger.getInstance().setBothDistances(distance);
							context.getSource().sendMessage(Text.literal("§2[vdc] §fSet both distances to §e" + distance + "§f."));
							LOGGER.info(context.getSource().getPlayer().getName().getString() + " set both distances to " + distance + ".");
							return 1;
						}))));

		LOGGER.info("Initialized.");
	}
	
	private boolean checkDistance(CommandContext<ServerCommandSource> context, int distance) {
		if (distance >= 3 && distance <= 32) {
			return true;
		}
		context.getSource().sendMessage(Text.literal("§2[vdc] §cDistance must be a number between 3 and 32 (inclusive)."));
		return false;
	}
}