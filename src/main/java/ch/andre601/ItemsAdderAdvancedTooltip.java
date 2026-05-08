package ch.andre601;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemsAdderAdvancedTooltip implements ModInitializer {
	public static final String MOD_ID = "iaat";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Loading ItemsAdderAdvancedTooltip");
	}
}