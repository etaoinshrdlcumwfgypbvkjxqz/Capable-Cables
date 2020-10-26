package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.parsers.components.handlers;

import com.google.common.collect.ImmutableList;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.jaxb.subprojects.ui.ui.RendererContainer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.IUIView;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.IUIViewComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.rendering.IUIRendererContainer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.rendering.IUIRendererContainerContainer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.parsers.components.contexts.IUIDefaultComponentParserContext;

public class UIDefaultDefaultComponentParserRendererContainerHandler
		extends UIAbstractDefaultParserObjectHandler<IUIDefaultComponentParserContext, RendererContainer> {
	@Override
	public void acceptNonnull(IUIDefaultComponentParserContext context, RendererContainer object) {
		context.getContainer().ifPresent(container -> {
			if (!(container instanceof IUIRendererContainerContainer))
				return;
			IUIRendererContainerContainer<?> rendererContainerContainer = ((IUIRendererContainerContainer<?>) container);

			rendererContainerContainer.initializeRendererContainer(object.getName());
			IUIRendererContainer<?> rendererContainer = rendererContainerContainer.getRendererContainer();

			IUIViewComponent<?, ?> view = context.getView()
					.orElseThrow(AssertionError::new);
			IUIView.getNamedTrackers(view).add(IUIRendererContainer.class, rendererContainer);
			IUIView.getThemeStack(view).element().apply(ImmutableList.of(rendererContainer));
		});
	}
}
