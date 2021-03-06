package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.rendering;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nullable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.UIConfiguration;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.UIMarkers;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.construction.IUIRendererArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.construction.UIRendererConstructor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.naming.INamed;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.construction.UIImmutableRendererArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.CastUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.LogMessageBuilder;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.dynamic.AnnotationUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.dynamic.InvokeUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.def.traits.IHasBinding;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.templates.CommonConfigurationTemplate;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Constructor;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public interface IUIRendererContainer<R extends IUIRenderer<?>>
		extends INamed, IHasBinding {
	@SuppressWarnings("UnstableApiUsage")
	static <R extends IUIRenderer<?>> void setRendererImpl(Object container,
	                                                       @Nullable R renderer,
	                                                       Consumer<@Nullable ? super R> setter,
	                                                       @Nullable IUIRenderer<?> previousRenderer) {
		if (!(renderer == null || renderer.getTypeToken().getRawType().isInstance(container)))
			throw new IllegalArgumentException(
					new LogMessageBuilder()
							.addMarkers(UIMarkers.getInstance()::getMarkerUIComponentRenderer)
							.addKeyValue("container", container).addKeyValue("renderer", renderer).addKeyValue("setter", setter)
							.addMessages(() -> StaticHolder.getResourceBundle().getString("renderer.set.impl.instance_of.fail"))
							.build()
			);
		if (previousRenderer != null)
			previousRenderer.onRendererRemoved();
		setter.accept(renderer);
		if (renderer != null)
			renderer.onRendererAdded(CastUtilities.castUnchecked(container));
	}

	static <C extends IUIRendererContainer<? super R>, R extends IUIRenderer<? super C>> void setRendererChecked(C container, R renderer) { container.setRenderer(renderer); }

	@SuppressWarnings("cast")
	static IUIRenderer<?> createDefaultRenderer(IUIRendererContainer<?> rendererContainer)
			throws Throwable {
		Class<?> clazz = rendererContainer.getDefaultRendererClass();
		Constructor<?> constructor = AnnotationUtilities.getElementAnnotatedWith(UIRendererConstructor.class, ImmutableList.copyOf(clazz.getDeclaredConstructors()).iterator());
		MethodHandle constructorHandle = InvokeUtilities.getImplLookup().unreflectConstructor(constructor);
		constructorHandle = constructorHandle.asType(constructorHandle.type().changeReturnType(IUIRenderer.class));

		IUIRendererArguments argument = UIImmutableRendererArguments.of(
				ImmutableMap.of(),
				rendererContainer.getContainer()
						.orElseThrow(IllegalStateException::new).getClass()
		);

		return (IUIRenderer<?>) constructorHandle.invokeExact((IUIRendererArguments) argument);
	}

	Class<? extends R> getDefaultRendererClass();

	Optional<? extends IUIRendererContainerContainer<?>> getContainer();

	Optional<? extends R> getRenderer();

	@Deprecated
		// COMMENT unchecked, use one of the checked variants
	void setRenderer(@Nullable R renderer);

	enum StaticHolder {
		;

		private static final ResourceBundle RESOURCE_BUNDLE = CommonConfigurationTemplate.createBundle(UIConfiguration.getInstance());

		protected static ResourceBundle getResourceBundle() { return RESOURCE_BUNDLE; }
	}
}
