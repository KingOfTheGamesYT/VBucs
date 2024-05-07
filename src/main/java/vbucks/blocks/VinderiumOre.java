package vbucks.blocks;

import vbucks.*;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class VinderiumOre extends OreBlock implements Content {
	public final Identifier ident;

	public VinderiumOre() {
		super(FabricBlockSettings.of(Material.STONE)
			.breakByTool(FabricToolTags.PICKAXES, 2)
			.requiresTool()
			.sounds(BlockSoundGroup.STONE)
			.strength(2f, 2f));

		ident = new Identifier(VBucks.MOD_ID, "vinderium_ore");

		add();
	}

	@Override
	public void register() {
		VBlock.register(this, ident);
	}
}
