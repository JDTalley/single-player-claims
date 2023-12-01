package jdtalley.spclaims.item;

import jdtalley.spclaims.SinglePlayerClaims;
import jdtalley.spclaims.item.custom.ClaimItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item WOODEN_SCEPTER = registerItem("wooden_scepter", new ClaimItem(new FabricItemSettings().maxDamage(1)));
    public static final Item STONE_SCEPTER = registerItem("stone_scepter", new ClaimItem(new FabricItemSettings().maxDamage(1)));
    public static final Item IRON_SCEPTER = registerItem("iron_scepter", new ClaimItem(new FabricItemSettings().maxDamage(1)));
    public static final Item GOLDEN_SCEPTER = registerItem("golden_scepter", new ClaimItem(new FabricItemSettings().maxDamage(1)));
    public static final Item DIAMOND_SCEPTER = registerItem("diamond_scepter", new ClaimItem(new FabricItemSettings().maxDamage(1)));
    public static final Item NETHERITE_SCEPTER = registerItem("netherite_scepter", new ClaimItem(new FabricItemSettings().maxDamage(1)));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(WOODEN_SCEPTER);
        entries.add(STONE_SCEPTER);
        entries.add(IRON_SCEPTER);
        entries.add(GOLDEN_SCEPTER);
        entries.add(DIAMOND_SCEPTER);
        entries.add(NETHERITE_SCEPTER);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SinglePlayerClaims.MOD_ID, name), item);
    }
    public static void registerModItems() {
        SinglePlayerClaims.LOGGER.info("Registering Mod Items for " + SinglePlayerClaims.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
