package jeed.jeedskins.item;

import jeed.jeedskins.JeedSkins;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
//import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import net.fabricmc.fabric.api.itemgroup.v1.*;

import java.util.function.Function;

public class ModItems {

    public static final FoodComponent EDGE_PIECE_FOOD_COMPONENT = new FoodComponent.Builder()
            .alwaysEdible()
            .nutrition(5)
            .saturationModifier(0.25f)
            .build();

    public static final ConsumableComponent EDGE_PIECE_CONSUMABLE_COMPONENT = ConsumableComponents.food() // why is this like this???
            .consumeSeconds(0.1f)
            .build();

    public static final ToolMaterial PLASTIC_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            64,
            5.0f,
            0.5f,
            1,
            PlasticArmorMaterial.REPAIRS_PLASTIC_ARMOR
    );



//    public static final Item EDGE_PIECE = register("edge_piece", Item::new, new Item.Settings());
    public static final Item EDGE_PIECE = register("edge_piece", Item::new, new Item.Settings()
        .maxCount(12)
        .rarity(Rarity.RARE)
        .food(EDGE_PIECE_FOOD_COMPONENT, EDGE_PIECE_CONSUMABLE_COMPONENT)
        );


    public static final Item PLASTIC_CORE = register(
            "plastic_core",
            settings -> new SwordItem(PLASTIC_TOOL_MATERIAL, 100f, 0.1f, settings),
            new Item.Settings()
    );
    ItemGroupEvents.
            // https://docs.fabricmc.net/develop/items/custom-tools


    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JeedSkins.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {

    }
}
