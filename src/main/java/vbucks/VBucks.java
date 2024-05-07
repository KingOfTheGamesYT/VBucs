package vbucks;

import vbucks.items.CreditCard;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.*;
import net.minecraft.loot.*;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;

import static vbucks.Content.*;

public class VBucks implements ModInitializer {
	public static final String MOD_ID = "vbucks";

	public static final ItemGroup group = FabricItemGroupBuilder.build(
		new Identifier(MOD_ID, "tab"), () -> new ItemStack(VBUCK));

	private static ConfiguredFeature<?, ?> oreOverworld = Feature.ORE
		.configure(new OreFeatureConfig(
			OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
			VINDERIUM_ORE.getDefaultState(),
			4)) // vein size
		.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
			0, // bottom offset
			5, // min y level
			20))) // max y level
		.spreadHorizontally()
		.repeat(5);

	@Override
	public void onInitialize() {
		for (Content c : all) {
			c.register();
		}

		// register ore generator
		RegistryKey<ConfiguredFeature<?, ?>> oregen = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
			new Identifier(MOD_ID, "ore_vinderium_overworld"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oregen.getValue(), oreOverworld);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oregen);

		// register credit card "spawning"
		Identifier villagerTable = new Identifier("minecraft", "entities/villager");

		LootTableLoadingCallback.EVENT.register((resMgr, lootMgr, id, supplier, setter) -> {
			if (villagerTable.equals(id)) {
				supplier.pool(FabricLootPoolBuilder.builder()
					.rolls(ConstantLootTableRange.create(1))
					.conditionally(RandomChanceLootCondition.builder(CreditCard.DROP_CHANCE))
					.withEntry(ItemEntry.builder(CREDIT_CARD).build()));
			}
		});
	}
}
