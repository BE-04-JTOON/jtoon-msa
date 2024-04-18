package shop.jtoon.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public enum Phase {
	BEFORE_COMMIT,
	AFTER_COMMIT,
	AFTER_ROLLBACK,
	AFTER_COMPLETION;
}
