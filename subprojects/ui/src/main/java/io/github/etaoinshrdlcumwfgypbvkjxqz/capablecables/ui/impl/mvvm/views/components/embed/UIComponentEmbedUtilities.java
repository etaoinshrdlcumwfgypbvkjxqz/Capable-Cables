package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.components.embed;

import com.google.common.collect.Maps;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.binding.IUIPropertyMappingValue;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.construction.IUIComponentArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.collections.MapUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.def.IIdentifier;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.def.IValueHolder;

import java.util.Map;
import java.util.function.Supplier;

public enum UIComponentEmbedUtilities {
	;

	@SuppressWarnings({"rawtypes", "RedundantSuppression"})
	public static <K extends IIdentifier> boolean withMappingsIfUndefined(final IValueHolder<IUIComponentArguments> pointerArguments, // COMMENT cursed
	                                                                      Map<K, ? extends Supplier<? extends IUIPropertyMappingValue>> mappings) {
		IUIComponentArguments arguments = pointerArguments.getValue().orElseThrow(AssertionError::new);
		Map<IIdentifier, ? extends IUIPropertyMappingValue> argumentsMappings = arguments.getMappingsView();

		if (mappings.keySet().stream().unordered()
				.anyMatch(argumentsMappings::containsKey)) {
			return false;
		} else {
			pointerArguments.setValue(
					arguments.withMappings(MapUtilities.concatMaps(
							argumentsMappings,
							Maps.transformValues(mappings, Supplier::get)
					))
			);
			return true;
		}
	}
}
