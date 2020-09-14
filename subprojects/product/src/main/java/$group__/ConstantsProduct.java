package $group__;

import $group__.utilities.compile.EnumBuildType;

public enum ConstantsProduct {
	;

	public static final String MOD_ID = "${modID}";
	public static final String NAME = "${name}";
	public static final String BUILD_TYPE_STRING = "${buildType}";
	public static final EnumBuildType BUILD_TYPE = EnumBuildType.valueOfSafe(BUILD_TYPE_STRING);
}
