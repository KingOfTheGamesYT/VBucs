package vbucks.blocks;

import vbucks.*;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class VBlock extends Block implements Content {
	public final Identifier ident;

	public VBlock(String name, int light) {
		this(name, FabricBlockSettings.of(Material.STONE)
			.luminance(state -> light)
			.breakByTool(FabricToolTags.PICKAXES, 2)
			.sounds(BlockSoundGroup.STONE)
			.strength(2f, 2f));
	}

	protected VBlock(String name, Settings settings) {
		super(settings);

		ident = new Identifier(VBucks.MOD_ID, name);

		add();
	}

	@Override
	public void register() {
		register(this, ident);
	}

	public static void register(Block block, Identifier ident) {
		Registry.register(Registry.BLOCK, ident, block);
		Registry.register(Registry.ITEM, ident,
			new BlockItem(block, new Item.Settings().group(VBucks.group)));
	}
}
