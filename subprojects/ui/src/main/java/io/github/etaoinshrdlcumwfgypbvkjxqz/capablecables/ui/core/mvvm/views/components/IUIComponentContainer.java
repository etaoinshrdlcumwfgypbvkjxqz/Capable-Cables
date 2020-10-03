package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components;

import com.google.common.collect.ImmutableMap;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.modifiers.IUIComponentTransformChildrenModifier;

import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public interface IUIComponentContainer
		extends IUIComponent, IUIComponentTransformChildrenModifier {
	@Override
	void transformChildren(AffineTransform transform);

	@SuppressWarnings("UnusedReturnValue")
	boolean addChildren(Iterable<? extends IUIComponent> components);

	boolean addChildAt(int index, IUIComponent component);

	@SuppressWarnings("UnusedReturnValue")
	boolean removeChildren(Iterable<? extends IUIComponent> components);

	boolean moveChildTo(int index, IUIComponent component);

	@SuppressWarnings("UnusedReturnValue")
	boolean moveChildToTop(IUIComponent component);

	@Override
	default List<? extends IUIComponent> getChildNodes() { return getChildrenView(); }

	@SuppressWarnings("UnstableApiUsage")
	default Map<String, IUIComponent> getNamedChildrenMapView() {
		return getChildrenView().stream().unordered()
				.filter(c -> c.getName().isPresent())
				.collect(ImmutableMap.toImmutableMap(c -> {
					Optional<? extends String> id = c.getName();
					assert id.isPresent();
					return id.get();
				}, Function.identity()));
	}

	List<IUIComponent> getChildrenView();
}
