package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.parsers.adapters.ui.components.handlers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.jaxb.subprojects.ui.ui.AnyContainer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.jaxb.subprojects.ui.ui.Extension;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.binding.IUIPropertyMappingValue;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.construction.IUIExtensionArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.construction.UIExtensionConstructor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.extensions.IUIExtension;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.parsers.adapters.IJAXBAdapterContext;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.parsers.adapters.ui.components.contexts.IJAXBUIComponentAdapterContext;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.parsers.adapters.ui.components.contexts.IJAXBUIComponentBasedAdapterContext;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.construction.UIImmutableExtensionArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.parsers.adapters.ui.components.JAXBUIComponentUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.parsers.adapters.ui.components.contexts.JAXBUIImmutableComponentAdapterContext;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.AssertionUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.CastUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.collections.MapUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.dynamic.AnnotationUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.dynamic.InvokeUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.core.INamespacePrefixedString;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.extensions.core.IExtensionContainer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.throwable.impl.ThrowableUtilities;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Map;

public class JAXBUIDefaultComponentAdapterExtensionHandler
		extends JAXBUIAbstractSubContextualAdapterHandler<Extension, IJAXBUIComponentAdapterContext> {
	public JAXBUIDefaultComponentAdapterExtensionHandler() {
		super(IJAXBUIComponentAdapterContext.class);
	}

	@Override
	@SuppressWarnings({"deprecation", "cast", "unchecked", "RedundantSuppression"})
	protected void accept0(IJAXBAdapterContext context, IJAXBUIComponentAdapterContext subContext, Extension left) {
		subContext.getContainer()
				.flatMap(container -> CastUtilities.castChecked(IExtensionContainer.class, container))
				.map(CastUtilities::<IExtensionContainer<?>>castUnchecked)
				.ifPresent(container -> {
					Map<INamespacePrefixedString, IUIPropertyMappingValue> mappings = JAXBUIComponentUtilities.createMappings(context, left.getProperty());
					IUIExtensionArguments argument = UIImmutableExtensionArguments.of(mappings, container.getClass(), left.getRendererName());

					Class<?> clazz = AssertionUtilities.assertNonnull(subContext.getAliasesView().get(left.getClazz()));
					Constructor<?> constructor = AnnotationUtilities.getElementAnnotatedWith(UIExtensionConstructor.class,
							Arrays.asList(clazz.getDeclaredConstructors()));
					MethodHandle constructorHandle;
					try {
						constructorHandle = InvokeUtilities.getImplLookup().unreflectConstructor(constructor);
					} catch (IllegalAccessException e) {
						throw ThrowableUtilities.propagate(e);
					}
					constructorHandle = constructorHandle.asType(constructorHandle.type().changeReturnType(IUIExtension.class));

					IUIExtension<?, ?> ret;
					try {
						ret = (IUIExtension<?, ?>) constructorHandle.invokeExact((IUIExtensionArguments) argument);
					} catch (Throwable throwable) {
						throw ThrowableUtilities.propagate(throwable);
					}
					container.addExtension(CastUtilities.castUnchecked(ret)); // COMMENT addExtension should check

					// COMMENT add other stuff after adding the extension
					IJAXBUIComponentAdapterContext subContext1 = new JAXBUIImmutableComponentAdapterContext(subContext.getAliasesView(),
							subContext.getObjectHandlersView(),
							subContext.getElementHandlersView(),
							subContext.getView().orElse(null),
							ret);
					Iterables.concat(
							left.getAnyContainer()
									.<Iterable<Object>>map(AnyContainer::getAny)
									.orElseGet(ImmutableSet::of))
							.forEach(any ->
									IJAXBUIComponentBasedAdapterContext.findHandler(subContext1, any)
											.ifPresent(handler ->
													handler.accept(
															context.withData(
																	MapUtilities.concatMaps(context.getDataView(),
																			ImmutableMap.of(IJAXBUIComponentAdapterContext.class, subContext1))
															),
															CastUtilities.castUnchecked(any) // COMMENT should not throw
													)
											)
							);
				});
	}
}
