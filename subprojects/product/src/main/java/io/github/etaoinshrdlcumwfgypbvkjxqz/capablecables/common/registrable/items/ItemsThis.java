package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.common.registrable.items;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ModConstants;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.common.registrable.blocks.BlocksThis;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.common.registrable.items.groups.ItemGroupsThis;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.UIConstants;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.debug.UIDebugMinecraft;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public enum ItemsThis {
	;

	private static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, ModConstants.getModId());

	private static final RegistryObject<Item> WRENCH = getRegister().register("wrench", ItemWrench::new);

	private static final RegistryObject<Item> CABLE = getRegister().register(BlocksThis.getCable().getId().getPath(), () -> new BlockItem(BlocksThis.getCable().orElseThrow(InternalError::new), new Item.Properties().group(ItemGroupsThis.getDefault())));

	@SuppressWarnings("unused")
	@Nullable
	private static final RegistryObject<Item> DEBUG_UI;

	static {
		DEBUG_UI = UIConstants.getBuildType().isDebug()
				? getRegister().register(UIDebugMinecraft.getPath(), () -> new BlockItem(UIDebugMinecraft.getBlockEntry(), new Item.Properties().group(ItemGroupsThis.getDefault())))
				: null;
	}

	public static DeferredRegister<Item> getRegister() {
		return REGISTER;
	}

	public static RegistryObject<Item> getWrench() {
		return WRENCH;
	}

	public static RegistryObject<Item> getCable() {
		return CABLE;
	}

	@SuppressWarnings("unused")
	@Nullable
	private static RegistryObject<Item> getDebugUI() {
		return DEBUG_UI;
	}
}
