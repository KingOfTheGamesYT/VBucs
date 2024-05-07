package deltanedas.vbucks_mod.init;

import deltanedas.vbucks_mod.Recipes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static deltanedas.vbucks_mod.init.InitBlocks.*;
import static deltanedas.vbucks_mod.init.InitItems.*;

public class InitRecipes {
	public InitRecipes() {
		Recipes.addSmelting(vinderiumOre, 1, vinderiumChunk, 1, 5);
		if (!InitConfig.vbuckPackIntegration) { // If it is standalone
			Recipes.addSmelting(vinderiumBlock, 1, vbuckShard, 1, 5);
		} else if (Loaded(deps)) { // V-Buck Pack!!!! :D
			Recipes.addShaped(mechanicalCore, 8,
				"ABC",
				"DVE",
				"FGH",
				'V', new ItemStack(vinderiumBlock, 1, 0),
				'A', new ItemStack(getModItem("galacticraftcore:machine_tiered"), 1, 4),
				'B', new ItemStack(getModItem("galacticraftcore:machine_tiered"), 1, 12),
				'C', new ItemStack(getModItem("enderio:block_sag_mill"), 1, 0),
				'D', new ItemStack(getModItem("rftools:scanner"), 1, 0),
				'E', new ItemStack(getModItem("enderio:block_enhanced_sag_mill"), 1, 0),
				'F', new ItemStack(getModItem("rftools:builder"), 1, 0),
				'G', new ItemStack(getModItem("mekanism:machineblock"), 1, 7),
				'H', new ItemStack(getModItem("mekanism:machineblock"), 1, 5)
			);
			Recipes.addShaped(energeticCore, 8,
				"ABC",
				"DVE",
				"FGH",
				'V', new ItemStack(vinderiumBlock, 1, 0),
				'A', new ItemStack(getModItem("galacticraftcore:machine_tiered"), 1, 0),
				'B', new ItemStack(getModItem("galacticraftcore:machine_tiered"), 1, 8),
				'C', new ItemStack(getModItem("enderio:block_cap_bank"), 1, 2),
				'D', new ItemStack(getModItem("rftools:powercell_advanced"), 1, 0),
				'E', new ItemStack(getModItem("enderio:block_cap_bank"), 1, 3),
				'F', new ItemStack(getModItem("rftools:powercell"), 1, 0),
				'G', new ItemStack(getModItem("mekanism:energycube"), 1, 0),
				'H', new ItemStack(getModItem("mekanism:energytablet"), 1, 0)
			);
			Recipes.addShaped(materialisticCore, 4,
				" A ",
				"BVC",
				" D ",
				'V', new ItemStack(vinderiumBlock, 1, 0),
				'A', new ItemStack(getModItem("enderio:block_alloy"), 1, 9),
				'B', new ItemStack(getModItem("enderio:block_alloy"), 1, 7),
				'C', new ItemStack(getModItem("mekanism:basicblock"), 1, 5),
				'D', new ItemStack(getModItem("mekanism:basicblock"), 1, 2)
			);
			Recipes.addShaped(arcaneCore, 4,
				" A ",
				"BVC",
				" D ",
				'V', new ItemStack(vinderiumBlock, 1, 0),
				'A', new ItemStack(getModItem("wizardry:mana_battery"), 1, 0),
				'B', new ItemStack(getModItem("psi:cad_assembly"), 1, 1),
				'C', new ItemStack(getModItem("psi:spell_drive"), 1, 0),
				'D', new ItemStack(getModItem("wizardry:halo_fake"), 1, 0)
			);

			Recipes.addEmpowering(vbuckShard,
				vinderiumChunk,
				mechanicalCore,
				energeticCore,
				materialisticCore,
				arcaneCore,
				80000,
				300
			);
		}
		Recipes.addShaped(Item.getItemFromBlock(vbuck), 1,
			"RSR",
			"SVS",
			"SRS",
			'R', new ItemStack(vbuckShardRadiant, 1, 0),
			'S', new ItemStack(vbuckShard, 1, 0),
			'V', new ItemStack(vbuckCore, 1, 0)
		);
		Recipes.addShaped(vbuckCore, 1,
			"W W",
			"WRW",
			" W ",
			'W', new ItemStack(vbuckShardWhite, 1, 0),
			'R', new ItemStack(vbuckShardRadiant, 1, 0)
		);
		Recipes.addShapeless(Item.getItemFromBlock(vbuckGlowing), 1,
			new ItemStack(vbuck, 1, 0),
			new ItemStack(vbuckShardRadiant, 1, 0)
		);
		Recipes.addShaped(vbuckShardRadiant, 1,
			"GQG",
			"QWQ",
			"GQG",
			'G', new ItemStack(Items.GLOWSTONE_DUST),
			'Q', new ItemStack(Items.QUARTZ),
			'W', new ItemStack(vbuckShardWhite)
		);
		Recipes.addShaped(vbuckShardWhite, 1,
			" Q ",
			"QSQ",
			" Q ",
			'Q', new ItemStack(Items.QUARTZ),
			'S', new ItemStack(vbuckShard)
		);
		// Compressing recipes
		Recipes.addCompress(vbuckShardBlock, vbuckShard);
		Recipes.addCompress(vbuckShardRadiantBlock, vbuckShardRadiant);
		Recipes.addCompress(vbuckShardWhiteBlock, vbuckShardWhite);
		Recipes.addCompress(vinderiumBlock, vinderiumChunk);
		// Decompressing recipes
		Recipes.addDecompress(vbuckShard, vbuckShardBlock);
		Recipes.addDecompress(vbuckShardRadiant, vbuckShardRadiantBlock);
		Recipes.addDecompress(vbuckShardWhite, vbuckShardWhiteBlock);
		Recipes.addDecompress(vinderiumChunk, vinderiumBlock);
	}
}
