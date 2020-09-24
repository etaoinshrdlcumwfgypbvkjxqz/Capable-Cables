package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.rendering;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.UIConfiguration;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.UIMarkers;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.LogMessageBuilder;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.binding.core.traits.IHasBinding;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.templates.CommonConfigurationTemplate;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

public interface IUIRendererContainer<R extends IUIRenderer<?>>
		extends IHasBinding {
	Optional<? extends R> getRenderer();

	@Deprecated
		// COMMENT unchecked, use 'setRendererChecked'
	void setRenderer(@Nullable R renderer);

	static <C extends IUIRendererContainer<? super R>, R extends IUIRenderer<? super C>> void setRendererChecked(C container, R renderer) { container.setRenderer(renderer); }

	Class<? extends R> getDefaultRendererClass();

	enum StaticHolder {
		;

		private static final ResourceBundle RESOURCE_BUNDLE = CommonConfigurationTemplate.createBundle(UIConfiguration.getInstance());

		public static <C, R extends IUIRenderer<?>> void setRendererImpl(C container, @Nullable R renderer, BiConsumer<? super C, ? super R> setter) {
			if (!(renderer == null || renderer.getGenericClass().isInstance(container)))
				throw new IllegalArgumentException(
						new LogMessageBuilder()
								.addMarkers(UIMarkers.getInstance()::getMarkerUIComponentRenderer)
								.addKeyValue("container", container).addKeyValue("renderer", renderer).addKeyValue("setter", setter)
								.addMessages(() -> getResourceBundle().getString("renderer.set.impl.instance_of.fail"))
								.build()
				);
			setter.accept(container, renderer);
		}

		protected static ResourceBundle getResourceBundle() { return RESOURCE_BUNDLE; }
	}
}