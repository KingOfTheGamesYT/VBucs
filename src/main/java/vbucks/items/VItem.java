package vbucks.items;

import vbucks.*;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class VItem extends Item implements Content {
	public final Identifier ident;

	public VItem(String name) {
		super(new Settings().group(VBucks.group));

		ident = new Identifier(VBucks.MOD_ID, name);

		add();
	}

	@Override
	public void register() {
		Registry.register(Registry.ITEM, ident, this);
	}
}
