package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.ObjectUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.collections.MapUtilities;
import org.jetbrains.annotations.NonNls;

import java.awt.geom.Dimension2D;
import java.beans.Transient;
import java.io.Serializable;
import java.util.function.Function;

public class DoubleDimension2D extends Dimension2D implements Serializable {
	private static final ImmutableList<Function<? super Dimension2D, ?>> OBJECT_VARIABLES = ImmutableList.of(
			Dimension2D::getWidth, Dimension2D::getHeight);
	@NonNls
	private static final ImmutableMap<String, Function<? super Dimension2D, ?>> OBJECT_VARIABLES_MAP = ImmutableMap.copyOf(MapUtilities.zipKeysValues(
			ImmutableList.of("width", "height"),
			getObjectVariables()));
	private static final long serialVersionUID = 4432299344969417136L;
	private double width, height;

	public DoubleDimension2D() { this(0, 0); }

	public DoubleDimension2D(double width, double height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public int hashCode() {
		return ObjectUtilities.hashCode(this, null, getObjectVariables());
	}

	public static ImmutableMap<String, Function<? super Dimension2D, ?>> getObjectVariablesMap() { return OBJECT_VARIABLES_MAP; }

	@Override
	public double getWidth() { return width; }

	@Override
	public double getHeight() { return height; }

	@Override
	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}

	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	@Override
	public boolean equals(Object obj) { return ObjectUtilities.equals(this, Dimension2D.class, obj, true, null, getObjectVariables()); }

	public static ImmutableList<Function<? super Dimension2D, ?>> getObjectVariables() { return OBJECT_VARIABLES; }

	@Override
	public String toString() { return ObjectUtilities.toString(this, super::toString, getObjectVariablesMap()); }

	@Transient
	public DoubleDimension2D getSize() { return new DoubleDimension2D(width, height); }

	@Override
	public DoubleDimension2D clone() { return (DoubleDimension2D) super.clone(); }
}
