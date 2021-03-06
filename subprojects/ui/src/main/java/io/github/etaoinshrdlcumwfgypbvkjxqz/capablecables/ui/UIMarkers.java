package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui;

import com.google.common.base.Suppliers;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nonnull;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.templates.MarkersTemplate;
import org.slf4j.Marker;

import java.util.function.Supplier;

public final class UIMarkers extends MarkersTemplate {
	private static final Supplier<@Nonnull UIMarkers> INSTANCE = Suppliers.memoize(UIMarkers::new);

	private final Marker markerShape;
	private final Marker markerParser;
	private final Marker markerJAXB;
	private final Marker markerUI;
	private final Marker markerUIInfrastructure;
	private final Marker markerUIView;
	private final Marker markerUIViewModel;
	private final Marker markerUIModel;
	private final Marker markerUIAnchor;
	private final Marker markerUIEvent;
	private final Marker markerUIComponent;
	private final Marker markerUIComponentRenderer;

	{
		// COMMENT structures
		markerShape = addReferences(getMarker("shape"), getMarkerStructure());
		// COMMENT parsing
		markerParser = getMarker("parser");
		markerJAXB = addReferences(getMarker("JAXB"), getMarkerParser());
		// COMMENT UI
		markerUI = getMarker("UI");
		markerUIInfrastructure = addReferences(getMarker("infrastructure"), getMarkerUI());
		markerUIView = addReferences(getMarker("view"), getMarkerUIInfrastructure());
		markerUIViewModel = addReferences(getMarker("view-model"), getMarkerUIInfrastructure());
		markerUIModel = addReferences(getMarker("model"), getMarkerUIInfrastructure());
		markerUIAnchor = addReferences(getMarker("anchor"), getMarkerUIView());
		markerUIEvent = addReferences(getMarker("event"), getMarkerUIView());
		// COMMENT UI component
		markerUIComponent = addReferences(getMarker("component"), getMarkerUIView());
		markerUIComponentRenderer = addReferences(getMarker("renderer"), getMarkerUIComponent());
	}

	private UIMarkers() { super(UIConstants.getModuleName()); }

	public static UIMarkers getInstance() { return INSTANCE.get(); }

	public Marker getMarkerShape() { return markerShape; }

	public Marker getMarkerParser() { return markerParser; }

	public Marker getMarkerUI() { return markerUI; }

	public Marker getMarkerUIInfrastructure() { return markerUIInfrastructure; }

	public Marker getMarkerUIView() { return markerUIView; }

	public Marker getMarkerUIViewModel() { return markerUIViewModel; }

	public Marker getMarkerUIModel() { return markerUIModel; }

	public Marker getMarkerUIComponent() { return markerUIComponent; }

	public Marker getMarkerUIComponentRenderer() { return markerUIComponentRenderer; }

	public Marker getMarkerJAXB() { return markerJAXB; }

	public Marker getMarkerUIEvent() { return markerUIEvent; }

	public Marker getMarkerUIAnchor() { return markerUIAnchor; }
}
