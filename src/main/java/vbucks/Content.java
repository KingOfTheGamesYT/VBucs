package vbucks;

import vbucks.blocks.*;
import vbucks.items.*;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public interface Content {
	public static List<Content> all = new ArrayList<>();

	/* Items */
	public static final VItem
		VBUCK_CORE = new VItem("vbuck_core"),
		VBUCK_SHARD = new VItem("vbuck_shard"),
		WHITE_SHARD = new VItem("white_shard"),
		RADIANT_SHARD = new VItem("radiant_shard"),
		VINDERIUM_CHUNK = new VItem("vinderium_chunk"),

		CREDIT_CARD = new CreditCard();

	/* Blocks */
	public static final Block
		VBUCK = new VBuck("vbuck", 0),
		GLOWING_VBUCK = new VBuck("glowing_vbuck", 10),

		VBUCK_BLOCK = new VBlock("vbuck_block", 0),
		WHITE_BLOCK = new VBlock("white_block", 2),
		RADIANT_BLOCK = new VBlock("radiant_block", 7),
		VINDERIUM_BLOCK = new VBlock("vinderium_block", 0),
		VINDERIUM_ORE = new VinderiumOre(),

		BOBUCKS = new Bobucks(),
		BOBUCK_BLOCK = new VBlock("bobuck_block", 0);

	/* Content interface - every item and block needs to implement this */
	public void register();

	// mark this content to be registered on mod load
	default void add() {
		all.add(this);
	}
}
