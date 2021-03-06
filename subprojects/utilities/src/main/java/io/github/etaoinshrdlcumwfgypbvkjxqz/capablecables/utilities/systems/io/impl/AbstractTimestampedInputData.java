package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.io.impl;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.ObjectUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.dynamic.DynamicUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.interfaces.ICloneable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.io.def.ITimestampedInputData;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.throwable.impl.ThrowableUtilities;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class AbstractTimestampedInputData
		implements ITimestampedInputData, ICloneable {
	private static final long TIMESTAMP_FIELD_OFFSET;

	static {
		try {
			TIMESTAMP_FIELD_OFFSET = DynamicUtilities.getUnsafe().objectFieldOffset(AbstractTimestampedInputData.class.getDeclaredField("timestamp"));
		} catch (NoSuchFieldException e) {
			throw ThrowableUtilities.propagate(e);
		}
	}

	private final long timestamp = System.currentTimeMillis();

	protected AbstractTimestampedInputData() {}

	@Override
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	@OverridingMethodsMustInvokeSuper
	public AbstractTimestampedInputData recreate() {
		AbstractTimestampedInputData result;
		try {
			result = clone();
		} catch (CloneNotSupportedException e) {
			throw ThrowableUtilities.propagate(e);
		}
		DynamicUtilities.getUnsafe().putLongVolatile(result, getTimestampFieldOffset(), System.currentTimeMillis());
		return result;
	}

	protected static long getTimestampFieldOffset() {
		return TIMESTAMP_FIELD_OFFSET;
	}

	@Override
	public int hashCode() {
		return ObjectUtilities.hashCodeImpl(this, ITimestampedInputData.StaticHolder.getObjectVariableMap().values().iterator());
	}

	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	@Override
	public boolean equals(Object obj) {
		return ObjectUtilities.equalsImpl(this, obj, ITimestampedInputData.class, true, ITimestampedInputData.StaticHolder.getObjectVariableMap().values().iterator());
	}

	@Override
	public AbstractTimestampedInputData clone() throws CloneNotSupportedException {
		return (AbstractTimestampedInputData) super.clone();
	}

	@Override
	public String toString() {
		return ObjectUtilities.toStringImpl(this, ITimestampedInputData.StaticHolder.getObjectVariableMap());
	}
}
