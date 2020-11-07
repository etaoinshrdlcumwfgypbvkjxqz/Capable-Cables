package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.parsers.adapters.ui.components;

import com.google.common.collect.Iterables;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.jaxb.subprojects.ui.ui.ComponentTheme;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.jaxb.subprojects.ui.ui.Renderer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.jaxb.subprojects.ui.ui.Using;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.parsers.UIParserCheckedException;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.parsers.UIParserUncheckedException;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.parsers.adapters.ui.components.contexts.IUIAbstractDefaultComponentParserContext;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.parsers.adapters.ui.components.contexts.IUIDefaultComponentThemeParserContext;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.theming.IUITheme;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.parsers.adapters.ui.components.contexts.UIImmutableDefaultComponentThemeParserContext;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.parsers.adapters.ui.components.handlers.UIDefaultDefaultComponentThemeParserRendererHandler;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.theming.UILambdaTheme;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.CastUtilities;

public class UIDefaultComponentThemeParser
		extends UIAbstractDefaultComponentParser<IUITheme, ComponentTheme, UILambdaTheme.Builder, IUIDefaultComponentThemeParserContext> {
	public static <T extends UIDefaultComponentThemeParser> T makeParserStandard(T instance) {
		instance.addObjectHandler(Renderer.class, new UIDefaultDefaultComponentThemeParserRendererHandler());
		return instance;
	}

	@SuppressWarnings("RedundantThrows")
	@Override
	protected Iterable<? extends Using> getRawAliases(ComponentTheme resource)
			throws UIParserCheckedException, UIParserUncheckedException { return resource.getUsing(); }

	@SuppressWarnings({"RedundantThrows", "unchecked"})
	@Override
	public UILambdaTheme.Builder parse1(ComponentTheme resource)
			throws Throwable {
		IUIDefaultComponentThemeParserContext context = new UIImmutableDefaultComponentThemeParserContext(getAliases(), getHandlers(), new UILambdaTheme.Builder());

		Iterables.concat(
				resource.getRenderer()
		).forEach(element ->
				IUIAbstractDefaultComponentParserContext.findHandler(context, element)
						.ifPresent(handler ->
								handler.accept(context,
										CastUtilities.castUnchecked(element)))
		);

		return context.getBuilder();
	}

	@SuppressWarnings("RedundantThrows")
	@Override
	protected IUITheme construct0(UILambdaTheme.Builder parsed)
			throws Throwable {
		return parsed.build();
	}
}