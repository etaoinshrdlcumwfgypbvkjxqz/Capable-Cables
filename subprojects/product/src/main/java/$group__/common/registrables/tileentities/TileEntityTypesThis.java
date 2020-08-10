package $group__.common.registrables.tileentities;

import $group__.client.ui.debug.GuiComponentDebug;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static $group__.Constants.MOD_ID;

public enum TileEntityTypesThis {
	;

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MOD_ID);

	// todo add debug flag
	@SuppressWarnings("unused")
	private static final RegistryObject<TileEntityType<?>> DEBUG_GUI_COMPONENT = TILE_ENTITIES.register(GuiComponentDebug.PATH, GuiComponentDebug::getTileEntityEntry);
}