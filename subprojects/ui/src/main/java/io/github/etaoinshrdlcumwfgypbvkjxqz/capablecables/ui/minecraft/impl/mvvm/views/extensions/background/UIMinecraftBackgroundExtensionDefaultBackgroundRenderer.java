package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.minecraft.impl.mvvm.views.extensions.background;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.construction.IUIRendererArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.construction.UIRendererConstructor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.minecraft.impl.utilities.UIMinecraftBackgrounds;
import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.geom.Point2D;

@OnlyIn(Dist.CLIENT)
public class UIMinecraftBackgroundExtensionDefaultBackgroundRenderer
		extends UIMinecraftBackgroundExtensionEmptyBackgroundRenderer {
	@UIRendererConstructor
	public UIMinecraftBackgroundExtensionDefaultBackgroundRenderer(IUIRendererArguments arguments) {
		super(arguments);
	}

	@Override
	public void render(Screen screen, Point2D mouse, double partialTicks) { UIMinecraftBackgrounds.renderDefaultBackgroundAndNotify(screen.getMinecraft(), screen.width, screen.height, 0); }
}
