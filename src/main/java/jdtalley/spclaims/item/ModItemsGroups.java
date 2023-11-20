package jdtalley.spclaims.item;

import jdtalley.spclaims.SinglePlayerClaims;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemsGroups {
    public static final ItemGroup CLAIM_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(SinglePlayerClaims.MOD_ID, "spclaims"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.spclaims"))
                    .icon(() -> new ItemStack(ModItems.GOLDEN_SCEPTER)).entries((displayContext, entries) -> {
                        entries.add(ModItems.WOODEN_SCEPTER);
                        entries.add(ModItems.STONE_SCEPTER);
                        entries.add(ModItems.IRON_SCEPTER);
                        entries.add(ModItems.GOLDEN_SCEPTER);
                        entries.add(ModItems.DIAMOND_SCEPTER);
                        entries.add(ModItems.NETHERITE_SCEPTER);
                    }).build());
    public static void registerItemGroups() {
        SinglePlayerClaims.LOGGER.info("Registering Item Groups for " + SinglePlayerClaims.MOD_ID);
    }
}
