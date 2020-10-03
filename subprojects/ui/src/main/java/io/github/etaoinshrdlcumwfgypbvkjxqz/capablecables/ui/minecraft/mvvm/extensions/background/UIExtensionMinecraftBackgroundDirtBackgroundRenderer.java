package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.minecraft.mvvm.extensions.background;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.binding.IUIPropertyMappingValue;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.parsers.components.UIRendererConstructor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.minecraft.utilities.UIMinecraftBackgrounds;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.core.INamespacePrefixedString;
import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.geom.Point2D;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class UIExtensionMinecraftBackgroundDirtBackgroundRenderer
		extends UIExtensionMinecraftBackgroundNullBackgroundRenderer {
	@UIRendererConstructor(type = UIRendererConstructor.EnumConstructorType.MAPPINGS)
	public UIExtensionMinecraftBackgroundDirtBackgroundRenderer(Map<INamespacePrefixedString, IUIPropertyMappingValue> mappings) {
		super(mappings);
	}

	@Override
	public void render(Screen screen, Point2D mouse, double partialTicks) { UIMinecraftBackgrounds.renderDirtBackgroundAndNotify(screen.getMinecraft(), screen.width, screen.height, 0); }
}
