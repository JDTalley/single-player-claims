package jdtalley.spclaims;

import jdtalley.spclaims.item.ModItems;
import jdtalley.spclaims.item.ModItemsGroups;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SinglePlayerClaims implements ModInitializer {
	public static final String MOD_ID = "sp-claims";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemsGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}