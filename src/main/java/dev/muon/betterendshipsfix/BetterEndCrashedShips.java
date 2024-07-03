package dev.muon.betterendshipsfix;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterEndCrashedShips implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("betterend-crashed-ships");

	@Override
	public void onInitialize() {
		LOGGER.info("Applying Betterend Crashed Ship Tweaks");
	}
}